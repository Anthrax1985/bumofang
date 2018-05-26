package com.fh.controller.bmf.product;

import com.fh.common.model.ResponseMessageEnum;
import com.fh.controller.base.BaseWebController;
import com.fh.entity.bmf.product.Product;
import com.fh.entity.bmf.scene.Scene;
import com.fh.entity.bmf.scene.SceneMask;
import com.fh.service.BaseService;
import com.fh.service.bmf.product.ProductSaveBizService;
import com.fh.service.bmf.product.ProductService;
import com.fh.service.bmf.productparam.*;
import com.fh.service.bmf.productrecord.*;
import com.fh.service.bmf.scene.SceneService;
import com.fh.service.bmf.syslog.SysLogBizService;
import com.fh.util.*;
import com.qiniu.common.QiniuException;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.storage.model.FileListing;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 
 * 类名称：ProductController
 * 创建人：tyj
 * 创建时间：2017-07-17
 */
@Controller
@RequestMapping(value="/productPicUpload")
public class ProductPicUploadController extends BaseWebController<Product> {

	String menuUrl = "product/list.do"; //菜单地址(权限用)
	
	@Resource(name="productService")
	private ProductService productService;

	@Resource(name="sceneService")
	private SceneService sceneService;
	
	@Autowired
	protected HttpServletRequest request;

	@Override
	protected BaseService<Product> getBaseService() {
		return productService;
	}
	@Override
	protected String getPackageName() {
		return "product";
	}
	@Override
	protected String getObjectName() {
		return "Product";
	}
	
	protected String getViewNameSuffix() {
		return "bmf/" + getPackageName() + "/" + getObjectName().toLowerCase();
	}
	
	
	/**
	 * 去新增页面
	 */
	@RequestMapping(value = "/upload_list")
	public ModelAndView upload_list() {
		ModelAndView mv = this.getModelAndView();

		QNUploadUtil qnUploader = new QNUploadUtil();
		String token = qnUploader.getUpToken();
		PageData pd = new PageData();
		mv.addObject("uploadDomain", qnUploader.getUploadDomain());
		mv.addObject("imgProductDomain", qnUploader.getImgProductDomain());
		mv.addObject("imgD3dDomain", qnUploader.getImgD3dDomain());
		mv.addObject("imgSceneDomain", qnUploader.getImgSceneDomain());
		mv.addObject("token", token);
		mv.setViewName(getViewNameSuffix() + "_upload_list");

		return mv;
	}

	@RequestMapping(value = { "/rest/uptoken" }, method = {RequestMethod.GET }, produces = {JSON_UTF8})
	@ResponseBody
	public String uptoken() {
		QNUploadUtil qnUploader = new QNUploadUtil();
		String token = qnUploader.getUpToken();

		Map rsMap = new HashMap();
		rsMap.put("token", token);
		JSONObject jsonNode = JSONObject.fromObject(rsMap);
		return jsonNode.toString();
	}


	@RequestMapping(value = { "/rest/uploadComplete" }, method = {RequestMethod.POST }, produces = {JSON_UTF8})
	@ResponseBody
	public String uploadComplete(@RequestBody Map<Object,Object>map) {
		String file_name = String.valueOf(map.get("file_name"));
		if(Tools.root_path == null){
			Tools.root_path = request.getSession().getServletContext().getRealPath("/");
		}

		Long timestamp = System.currentTimeMillis();

		//图片下载到本地
//		String url = "http://p0l8q7nll.bkt.clouddn.com/" + file_name + "?tm=" + timestamp + "&imageMogr2/thumbnail/!400x";
//		InputStream inputStream = HttpURLConnectionUtil.getInputStreamByGet(url);
//		HttpURLConnectionUtil.saveData(inputStream, "product_scene", Tools.root_path, file_name);

		QNUploadUtil qnUploader = new QNUploadUtil();
		BucketManager mgr = qnUploader.getBucketManager();
		String toBucket = "";
		try {
			String[] arr = file_name.split("_");
			if(file_name.substring(0, 3).equals("FAH")) {
				toBucket = "img-product";
			}else if(arr.length == 4){
				toBucket = "img-d3d";
			}else{
				toBucket = "img-scene";
			}
			mgr.copy("upload", file_name, toBucket, file_name, true);
			mgr.delete("upload", file_name);
		} catch (QiniuException e) {

		}

		file_name = file_name.toUpperCase().replace(".PNG", "").replace(".JPG", "").replace(".TXT", "");
		//file_name = file_name.replace("-", "_");
		String[] arr = file_name.split("_");

		try {
			if(toBucket.equals("img-d3d")){
				String scene_name = arr[0] + "_" + arr[1];
				String mask_name = arr[0] + "_" + arr[1] + "_" + arr[2];
				String product_name = arr[3];

				//更新上传3D图片的时间，Pad端会更新图片
				sceneService.updateD3dUploadTime(product_name);

				PageData scene = new PageData();
				scene.put("NAME", scene_name);
				scene.put("MASK_NAME", mask_name);
				sceneService.saveScene(scene);

				PageData productScene = new PageData();
				productScene.put("NAME", file_name);
				productScene.put("PRODUCT_NAME", product_name);
				productScene.put("SCENE_NAME", scene_name);
				productScene.put("MASK_NAME", mask_name);

				Product prod = productService.findByProductName(product_name);
				if(prod != null){
					productScene.put("PRODUCT_ID", prod.getId());
				}

				Scene sceneObj = sceneService.findBySceneName(scene_name);
				if(sceneObj != null){
					productScene.put("SCENE_ID", sceneObj.getId());
				}

				SceneMask maskObj = sceneService.findBySceneMaskName(mask_name);
				if(maskObj != null){
					productScene.put("MASK_ID", maskObj.getId());
				}
				sceneService.saveProductScene(productScene);

			}else if(toBucket.equals("img-product")){
				//更新上传布料图片的时间，Pad端会更新图片
				sceneService.updateProductUploadTime(file_name);
			}else{
				if(arr.length >= 2){
					String scene_name = arr[0] + "_" + arr[1];
					sceneService.updateSceneUploadTime(scene_name);

					if(arr.length == 3){
						String mask_name = arr[0] + "_" + arr[1] + "_" + arr[2];
						PageData scene = new PageData();
						scene.put("NAME", scene_name);
						scene.put("MASK_NAME", mask_name);
						sceneService.saveScene(scene);
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ResponseMessageEnum.SUCCESS.toString();
	}


	@RequestMapping(value = { "/rest/uploadSceneComplete" }, method = {RequestMethod.POST }, produces = {JSON_UTF8})
	@ResponseBody
	public String uploadSceneComplete(@RequestBody Map<Object,Object>map) {
		String file_name = String.valueOf(map.get("file_name"));
		if(Tools.root_path == null){
			Tools.root_path = request.getSession().getServletContext().getRealPath("/");
		}
		Long timestamp = System.currentTimeMillis();
		String url = "http://p0l8q7nll.bkt.clouddn.com/" + file_name + "?tm=" + timestamp + "&imageMogr2/thumbnail/!400x";
		InputStream inputStream = HttpURLConnectionUtil.getInputStreamByGet(url);
		HttpURLConnectionUtil.saveData(inputStream, "scene", Tools.root_path, file_name);


		return ResponseMessageEnum.SUCCESS.toString();
	}


	@RequestMapping(value = { "/rest/getZipPath" }, method = {RequestMethod.POST }, produces = {JSON_UTF8})
	@ResponseBody
	public String getZipPath() {
		if(Tools.root_path == null){
			Tools.root_path = request.getSession().getServletContext().getRealPath("/");
		}

		String qiniu_upload_path = Tools.root_path + "upload/qiniu";
		String zip_path = Tools.root_path + "upload/files.zip";

		logger.info("qiniu_upload_path = " + qiniu_upload_path);
		logger.info("zip_path = " + zip_path);

		try {
			FileZip.zip(qiniu_upload_path, zip_path);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("FileZip.zip error " , e);
		}

		return ResponseMessageEnum.SUCCESS.toString();
	}


	@RequestMapping(value = { "/rest/remove" }, method = {RequestMethod.POST }, produces = {JSON_UTF8})
	@ResponseBody
	public String remove(@RequestBody Map<Object,Object>map) {
		if(Tools.root_path == null){
			Tools.root_path = request.getSession().getServletContext().getRealPath("/");
		}

		List<String> name_list = (List<String>)map.get("name_list");

		BucketManager bucketManager = new QNUploadUtil().getBucketManager();
		for (String key : name_list){
			try {
				bucketManager.delete(QNUploadUtil.bucket_name, key);
			} catch (QiniuException e) {
			}
		}

		return ResponseMessageEnum.SUCCESS.toString();
	}


	@RequestMapping(value = { "/rest/remove_all" }, method = {RequestMethod.POST }, produces = {JSON_UTF8})
	@ResponseBody
	public String removeAll() {
		if(Tools.root_path == null){
			Tools.root_path = request.getSession().getServletContext().getRealPath("/");
		}

		String qiniu_upload_path = Tools.root_path + "upload/qiniu";
		String zip_path = Tools.root_path + "upload/files.zip";

		try {
			File dir = new File(qiniu_upload_path);
			for (File f : dir.listFiles()){
				f.delete();
			}
			File zipFile = new File(zip_path);
			if(zipFile.exists()){
				zipFile.delete();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ResponseMessageEnum.SUCCESS.toString();
	}

	@RequestMapping(value = { "/renameTool" }, method = {RequestMethod.GET }, produces = {JSON_UTF8})
	@ResponseBody
	public String renameTool(@RequestParam(value="prefix",required=true) String prefix) throws Exception {
		QNUploadUtil qnUploader = new QNUploadUtil();
		BucketManager mgr = qnUploader.getBucketManager();

		String[] prefixArr = {"A/", "C/", "E/", "M/", "O/"};
		//for(String prefix : prefixArr){
		prefix = prefix + "/";
		for(int i=0; i<30; i++){
			ArrayList res = renameToolRoop(mgr, prefix);
			if(res.size() == 0){
				break;
			}
			System.out.println(i);
		}
		System.out.println(prefix);
		//}

		return ResponseMessageEnum.SUCCESS.appendEmptyData();
	}

	@RequestMapping(value = { "/renameTool2" }, method = {RequestMethod.GET }, produces = {JSON_UTF8})
	@ResponseBody
	public String renameTool2(@RequestParam(value="prefix",required=true) String prefix) throws Exception {
		QNUploadUtil qnUploader = new QNUploadUtil();
		BucketManager mgr = qnUploader.getBucketManager();

		String[] prefixArr = {"A/", "C/", "E/", "M/", "O/"};
		//for(String prefix : prefixArr){
		prefix = "M/" + prefix;
		for(int i=0; i<30; i++){
			ArrayList res = renameToolRoop(mgr, prefix);
			if(res.size() == 0){
				break;
			}
			System.out.println(i);
		}
		System.out.println(prefix);
		//}

		return ResponseMessageEnum.SUCCESS.appendEmptyData();
	}

	private ArrayList renameToolRoop(BucketManager mgr, String prefix) throws Exception{
		FileListing list = mgr.listFiles("img-d3d", prefix, null, 1000, "");
		FileInfo[] items = list.items;
		ArrayList res = new ArrayList();
		for(FileInfo fileInfo : items){
			String key = fileInfo.key;
			String[] arr = key.split("/");
			String fileName = arr[arr.length-1];
			mgr.rename("img-d3d", key, fileName, true);
			res.add(fileName);
		}

		return res;
	}


	@RequestMapping(value = { "/listNameTool" }, method = {RequestMethod.GET }, produces = {JSON_UTF8})
	@ResponseBody
	public String listNameTool(@RequestParam(value="prefix",required=true) String prefix) throws Exception {
		if(prefix ==  null || prefix.equals("")){
			return ResponseMessageEnum.SERVER_DATA_NOTEXIST.toString();
		}

		QNUploadUtil qnUploader = new QNUploadUtil();
		BucketManager mgr = qnUploader.getBucketManager();

		//String[] prefixArr = {"A", "C", "E", "M", "O"};
		String[] prefixArr = {"E"};
		ArrayList<String> allList = new ArrayList<String>();
		//for(String prefix : prefixArr){
		//	prefix = prefix;
			for(int i=0; i<100; i++){
				ArrayList res = nameListToolRoop(mgr, prefix);
				if(res.size() == 0){
					break;
				}
				System.out.println(i);
				allList.addAll(res);
			}
			System.out.println(prefix);
		//}

		return ResponseMessageEnum.SUCCESS.appendObject(allList);
	}

	private ArrayList nameListToolRoop(BucketManager mgr, String prefix) throws Exception{
		FileListing list = mgr.listFiles("img-d3d", prefix, null, 1000, "");
		FileInfo[] items = list.items;
		ArrayList res = new ArrayList();
		for(FileInfo fileInfo : items){
			String key = fileInfo.key;
			res.add(key);
		}

		return res;
	}

}
