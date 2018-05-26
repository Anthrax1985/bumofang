package com.fh.controller.bmf.app;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.jsp.jstl.sql.ResultSupport;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fh.common.model.ResponseMessageEnum;
import com.fh.controller.base.AppBaseController;
import com.fh.entity.bmf.app.ProductDetailResItem;
import com.fh.entity.bmf.app.ProductMathMethodItem;
import com.fh.entity.bmf.app.ProductResItem;
import com.fh.entity.bmf.app.SelectProductReq;
import com.fh.entity.bmf.member.MemberFavorProduct;
import com.fh.entity.bmf.product.Product;
import com.fh.entity.bmf.product.ProductEviNameAsso;
import com.fh.entity.bmf.productrecord.ProductMatchSchemeItem;
import com.fh.entity.bmf.productrecord.ProductRecordApplication;
import com.fh.entity.bmf.productrecord.ProductRecordMatchScheme;
import com.fh.entity.bmf.productrecord.ProductRecordStyle;
import com.fh.entity.bmf.productrecord.ProductRecordWashingMethod;
import com.fh.entity.bmf.statistics.ProductBrowse;
import com.fh.extend.logic.AccessTokenManager;
import com.fh.extend.logic.AuthCodeManager;
import com.fh.service.bmf.member.MemberFavorProductService;
import com.fh.service.bmf.member.MemberService;
import com.fh.service.bmf.product.ProductService;
import com.fh.service.bmf.productparam.ProductParamApplicationService;
import com.fh.service.bmf.productparam.ProductParamColorService;
import com.fh.service.bmf.productparam.ProductParamCraftService;
import com.fh.service.bmf.productparam.ProductParamMaterialService;
import com.fh.service.bmf.productparam.ProductParamStyleService;
import com.fh.service.bmf.productparam.ProductParamWashingMethodService;
import com.fh.service.bmf.productrecord.ProductRecordApplicationService;
import com.fh.service.bmf.productrecord.ProductRecordColorService;
import com.fh.service.bmf.productrecord.ProductRecordMatchSchemeService;
import com.fh.service.bmf.productrecord.ProductRecordStyleService;
import com.fh.service.bmf.productrecord.ProductRecordWashingMethodService;
import com.fh.service.bmf.receiver.ReceiverService;
import com.fh.service.bmf.sms.SmsSendService;
import com.fh.service.bmf.statistics.ProductBrowseService;
import com.fh.util.PageData;
import com.fh.util.Tools;
import com.fh.util.UuidUtil;

/**
 */
@Controller("ProductController")
@RequestMapping(value = "/app/product")
public class ProductController extends AppBaseController {

	
	@Resource(name="productService")
	private ProductService productService;
	
	@Resource(name="productParamColorService")
	private ProductParamColorService productParamColorService;
	
	@Resource(name="productRecordColorService")
	private ProductRecordColorService productRecordColorService;
	
	@Resource(name="productParamStyleService")
	private ProductParamStyleService productParamStyleService;
	
	@Resource(name="productRecordStyleService")
	private ProductRecordStyleService productRecordStyleService;
	
	@Resource(name="productParamCraftService")
	private ProductParamCraftService productParamCraftService;
	
	@Resource(name="productParamMaterialService")
	private ProductParamMaterialService productParamMaterialService;
	
	@Resource(name="productParamApplicationService")
	private ProductParamApplicationService productParamApplicationService;
	
	@Resource(name="productRecordApplicationService")
	private ProductRecordApplicationService productRecordApplicationService;
	
	@Resource(name="productParamWashingMethodService")
	private ProductParamWashingMethodService productParamWashingMethodService;
	
	@Resource(name="productRecordWashingMethodService")
	private ProductRecordWashingMethodService productRecordWashingMethodService;
	
	@Resource(name="productRecordMatchSchemeService")
	private ProductRecordMatchSchemeService productRecordMatchSchemeService;
	
	@Resource(name="memberFavorProductService")
	private MemberFavorProductService memberFavorProductService;
	
	@Resource(name="productBrowseService")
	private ProductBrowseService productBrowseService;

	/*产品详情*/
	/**
	 * 
	 * @param map  {"mobile":"15068865038","code":"987776"}
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = { "/detail" }, method = {RequestMethod.GET }, produces = {JSON_UTF8})
	@ResponseBody
	public String productDetail(@RequestParam(value="id",required=true) Long productId, @RequestParam(value="is_new", defaultValue="0") Long is_new) throws Exception {
		ProductDetailResItem productItem = new ProductDetailResItem();
		if(productService.findById(productId) ==  null){
			return ResponseMessageEnum.SERVER_DATA_NOTEXIST.toString();
		}
		Product dbProduct = productService.findById(productId);
		//产品id、品名、成分、每平方克重、幅宽单幅、幅宽宽幅,花型尺寸-水平、花型尺寸-垂直、原产地、质检报告
		productItem.setId(productId);
		productItem.setProductName(dbProduct.getProductName());
		productItem.setProductComponent(dbProduct.getProductComponent());
		productItem.setProductUnitWeight(dbProduct.getProductUnitWeight()+"");
		productItem.setProductNarrowWidth(dbProduct.getProductNarrowWidth()+"cm±3cm");
		productItem.setProductWideWidth(dbProduct.getProductWideWidth()+"cm±3cm");
		productItem.setProductNarrowPrice(dbProduct.getProductNarrowPrice());
		productItem.setProductWidePrice(dbProduct.getProductWidePrice());
		productItem.setProductUploadTime(dbProduct.getProductUploadTime());
		productItem.setD3dUploadTime(dbProduct.getD3dUploadTime());

		//低版本没有的字段不输出
		if(is_new == 0){
			productItem.setProductUploadTime(null);
			productItem.setD3dUploadTime(null);
		}

		String patternHorizontalSize = null;
		if(dbProduct.getPatternHorizontalSize() < 0.01){
			patternHorizontalSize = "-";//当花型尺寸为0是，用"-"代替
		}else{
			patternHorizontalSize = "横向"+dbProduct.getPatternHorizontalSize().toString()+"cm";
		}
		productItem.setPatternHorizontalSize(patternHorizontalSize);
		String patternVerticalSize = null;
		if(dbProduct.getPatternVerticalSize() < 0.01){
			patternVerticalSize = "";//花型尺寸为0是，用"-"代替
		}else{
			patternVerticalSize = "纵向"+dbProduct.getPatternVerticalSize().toString()+"cm";
		}
		productItem.setPatternVerticalSize(patternVerticalSize);
		productItem.setProductSourceArea(dbProduct.getProductSourceArea());
		productItem.setQualityControlReport(dbProduct.getQualityControlReport());
		//产品质地图
		ArrayList<String> qualityPicList = new ArrayList<String>();
		qualityPicList.add(dbProduct.getQualityPicture1());
		qualityPicList.add(dbProduct.getQualityPicture2());
		qualityPicList.add(dbProduct.getQualityPicture3());
		qualityPicList.add(dbProduct.getQualityPicture4());
		//产品应用列表
		ArrayList<String> applicationNameList = new ArrayList<String>();
		ProductRecordApplication applicationRes = new ProductRecordApplication();
		applicationRes.setProductId(productId );
		List<ProductRecordApplication> dbAppInfoList = productRecordApplicationService.findByCondition(applicationRes);
		for(ProductRecordApplication cycle : dbAppInfoList){
			applicationNameList.add(cycle.getApplicationName());
		}
		productItem.setApplicationNameList(applicationNameList);
		//产品水洗方案
		ArrayList<String> washMethodIconList = new ArrayList<String>();
		ProductRecordWashingMethod washMethodRes = new ProductRecordWashingMethod();
		washMethodRes.setProductId(productId);
		List<ProductRecordWashingMethod> dbwashMethodInfoList = productRecordWashingMethodService.findByCondition(washMethodRes);
		for(ProductRecordWashingMethod cycle :dbwashMethodInfoList){
			washMethodIconList.add(cycle.getWashingMethodIcon());
		}
		productItem.setWashMethodIconList(washMethodIconList);
		//搭配方案，
		List<ProductMatchSchemeItem> matchItemList = productRecordMatchSchemeService.findByProductId(productId);
		
		for(ProductMatchSchemeItem cycle :matchItemList){
			Long  matchProductId = cycle.getMatchProductId();
			ArrayList<String> matchProcuctApplicationLetterInfo = getMatchProcuctApplicationLetterInfo(matchProductId);
			cycle.setMatchProductLetterList(matchProcuctApplicationLetterInfo);

			//低版本没有的字段不输出
			if(is_new == 0){
				cycle.setProductUploadTime(null);
				cycle.setD3dUploadTime(null);
			}
		}
		productItem.setProductMatchSchemeList(matchItemList);
		//右侧相同花型不同颜色选择
		String productName = dbProduct.getProductName();
		String[] productNameList = productName.split("-");
		String prefix = productNameList[0];
		logger.info("prefix is "+prefix);
		List<ProductResItem> sameDesignedProList = productService.listSameDesignedPro(prefix);	
		for(ProductResItem cycle: sameDesignedProList ){
			Long sameDesignedproductId = cycle.getProductId();
			ArrayList<String> applicationLetterList = getMatchProcuctApplicationLetterInfo(sameDesignedproductId);
			cycle.setApplicationLetterList(applicationLetterList);

			//低版本没有的字段不输出
			if(is_new == 0){
				cycle.setProductUploadTime(null);
				cycle.setD3dUploadTime(null);
			}
		}

		productItem.setSameDesignedProList(sameDesignedProList);
		//质地图400:200(长方形) Q1
		//productItem.setQualitypicture(dbProduct.getQualityPicture2());
		productItem.setQualitypicture(dbProduct.getProductName() + "-Q1.jpg");

		//收藏状态	
		productItem.setFavorStatus(new Integer(0));
		if(request.getParameter("token")!=null){
			Object result = getUser();
			if(!(result instanceof ResponseMessageEnum)){
				PageData  memberInfo = (PageData)result;
				Long memberId = memberInfo.getLong("ID");
				MemberFavorProduct favorInfo = new MemberFavorProduct();
				favorInfo.setMemberId(memberId);
				favorInfo.setProductId(productId);
				List<MemberFavorProduct> favorInfoList = memberFavorProductService.findByCondition(favorInfo);
				if(favorInfoList.size()>0){
					productItem.setFavorStatus(new Integer(1));
				}
				//浏览统计 
				ProductBrowse browseInfo = new ProductBrowse();
				browseInfo.setMemberId(memberId);
				browseInfo.setProductId(productId);
				browseInfo.setIpadIp("0.0.0.0.0.0.0.0.0");//暂时先写死
				browseInfo.setBrowseTime(new Timestamp(System.currentTimeMillis()));
				browseInfo.setCreateTime(new Timestamp(System.currentTimeMillis()));
				browseInfo.setCreateUserId(memberId);
				productBrowseService.save(browseInfo);
			}	
		}
		//风格字母缩写列表
		ProductRecordStyle styleReq = new ProductRecordStyle();
		styleReq.setProductId(productId);
		List<ProductRecordStyle> styleInfoList = productRecordStyleService.findByCondition(styleReq);
		ArrayList<String> styleLetterList = new ArrayList<String>();
		for(ProductRecordStyle cycle : styleInfoList){
			long  styleId= cycle.getStyleId();
			String styleLetter = transformStyleLetter(styleId);
			styleLetterList.add(styleLetter);
		}
		productItem.setStyleLetterList(styleLetterList);
		//应用列表
		ArrayList<String> applicationLetterInfo = getMatchProcuctApplicationLetterInfo(productId);
		productItem.setApplicationLetterList(applicationLetterInfo);
		return ResponseMessageEnum.SUCCESS.appendObject(productItem);		
	}
	
	/*所有产品类表接口接口*/
	@RequestMapping(value = { "/all" }, method = {RequestMethod.GET }, produces = {JSON_UTF8})
	@ResponseBody
	public String productAll(@RequestParam(value="is_new", defaultValue="0") Long is_new) throws Exception {
		List<ProductResItem> ProductResList=productService.listAll4App();

		//低版本没有的字段不输出
		if(is_new == 0){
			for(ProductResItem item : ProductResList){
				item.setProductUploadTime(null);
				item.setD3dUploadTime(null);
			}
		}

		Collections.shuffle(ProductResList);
		return ResponseMessageEnum.SUCCESS.appendObject(ProductResList);
	}

	/*所有产品类表接口接口*/
	@RequestMapping(value = { "/search" }, method = {RequestMethod.GET }, produces = {JSON_UTF8})
	@ResponseBody
	public String search(@RequestParam(value="name",required=false) String name, @RequestParam(value="is_new", defaultValue="0") Long is_new) throws Exception {
		if (name == null) {
			return ResponseMessageEnum.ARGUMENT_EXCEPTION.toString();
		}

		if(name.length() < 3){
			return ResponseMessageEnum.ERROR_SEARCH_EXIST.toString();
		}

		List<ProductResItem> ProductResList=productService.search4App(name);

		//低版本没有的字段不输出
		if(is_new == 0){
			for(ProductResItem item : ProductResList){
				item.setProductUploadTime(null);
				item.setD3dUploadTime(null);
			}
		}

		Collections.shuffle(ProductResList);
		return ResponseMessageEnum.SUCCESS.appendObject(ProductResList);
	}
	
	/*颜色筛选接口*/
	/**
	 * @param colorId
	 * @return List<ProductResItem>
	 * @throws Exception
	 */
	@RequestMapping(value = { "/select/color" }, method = {RequestMethod.GET }, produces = {JSON_UTF8})
	@ResponseBody
	public String selectColor(@RequestParam(value="colorId",required=true) Long colorId) throws Exception {
		ProductResItem productReq = new ProductResItem();
		productReq.setSelectEleId(colorId);
		List<ProductResItem> ProductResList=productService.listAllColor(productReq);
		Collections.shuffle(ProductResList);
		return ResponseMessageEnum.SUCCESS.appendObject(ProductResList);
	}
	
	/*风格筛选接口*/
	/**
	 * @param styleId
	 * @return List<ProductResItem>
	 * @throws Exception
	 */
	@RequestMapping(value = { "/select/style" }, method = {RequestMethod.GET }, produces = {JSON_UTF8})
	@ResponseBody
	public String selectStyle(@RequestParam(value="styleId",required=true) Long styleId) throws Exception {
		ProductResItem productReq = new ProductResItem();
		productReq.setSelectEleId(styleId);
		List<ProductResItem> ProductResList=productService.listAllStyle(productReq);
		Collections.shuffle(ProductResList);
		return ResponseMessageEnum.SUCCESS.appendObject(ProductResList);
	}
	
	/*材质筛选接口*/
	/**
	 * @param materialId
	 * @return List<ProductResItem>
	 * @throws Exception
	 */
	@RequestMapping(value = { "/select/material" }, method = {RequestMethod.GET }, produces = {JSON_UTF8})
	@ResponseBody
	public String selectMaterial(@RequestParam(value="materialId",required=true) Long materialId) throws Exception {
		ProductResItem productReq = new ProductResItem();
		productReq.setSelectEleId(materialId);
		List<ProductResItem> ProductResList=productService.listAllMaterial(productReq);
		Collections.shuffle(ProductResList);
		return ResponseMessageEnum.SUCCESS.appendObject(ProductResList);
	}
	
	/*应用筛选接口*/
	/**
	 * @param applicationId
	 * @return List<ProductResItem>
	 * @throws Exception
	 */
	@RequestMapping(value = { "/select/application" }, method = {RequestMethod.GET }, produces = {JSON_UTF8})
	@ResponseBody
	public String selectApplication(@RequestParam(value="applicationId",required=true) Long applicationId) throws Exception {
		ProductResItem productReq = new ProductResItem();
		productReq.setSelectEleId(applicationId);
		List<ProductResItem> ProductResList=productService.listAllApplication(productReq);
		Collections.shuffle(ProductResList);
		return ResponseMessageEnum.SUCCESS.appendObject(ProductResList);
	}
	/*多次筛选产品接口*/
	/**
	 * @param {"colorId":"1","styleId":"1","applicationId":"1","materialId":"1"}
	 * @return List<ProductResItem>
	 * @throws Exception
	 */
	@RequestMapping(value = { "/multi/select" }, method = {RequestMethod.POST }, produces = {JSON_UTF8})
	@ResponseBody
	public String multiSelect(@RequestBody SelectProductReq req, @RequestParam(value="is_new", defaultValue="0") Long is_new) throws Exception {
		List<ProductResItem> ProductResList=productService.listByMultiSelected(req);

		//低版本没有的字段不输出
		if(is_new == null || is_new == 0){
			for(ProductResItem item : ProductResList){
				item.setProductUploadTime(null);
				item.setD3dUploadTime(null);
			}
		}
		Collections.shuffle(ProductResList);
		return ResponseMessageEnum.SUCCESS.appendObject(ProductResList);
	}
	
	/*由产品id筛选有效场景接口*/
	/**
	 * @param productId
	 * @return List<ProductEviNameAsso>
	 * @throws Exception
	 */
	@RequestMapping(value = { "/select/userful/evi" }, method = {RequestMethod.GET }, produces = {JSON_UTF8})
	@ResponseBody
	public String userfulEvi(@RequestParam(value="productId",required=true) Long productId) throws Exception {
		List<ProductEviNameAsso> userfulEvi4ProList = productService.userfulEvi4Pro(productId);
		return ResponseMessageEnum.SUCCESS.appendObject(userfulEvi4ProList);
	}
	
/*以下是本类私有方法*/
	//将产品风格信息转换为风格字母缩写  中式-CN、美式-AM、欧式-EU、现代-CO、混搭-MI;
	private String transformStyleLetter(long styleId){
		String styleLetter = null;
		if(styleId ==1){
			styleLetter ="CN";
		}else if(styleId ==2){
			styleLetter ="AM";
		}else if(styleId ==3){
			styleLetter ="EU";
		}else if(styleId ==4){
			styleLetter ="CO";
		}else{
			styleLetter ="MI";
		}
		
		return styleLetter;
	}
	//根据产品id,得到该产品的应用信息，并已应用字母缩写的形式返回
	//目前暂定：沙发1-SF，窗帘2-DR，墙布3-WA，抱枕4-PI，床品5-BR，其它6-OT
	private ArrayList<String> getMatchProcuctApplicationLetterInfo(Long productId) throws Exception{
		ProductRecordApplication req = new ProductRecordApplication();
		req.setProductId(productId);
		List<ProductRecordApplication> applicationInfoList = productRecordApplicationService.findByCondition(req);		
		ArrayList<String> res = new ArrayList<String>();
		for(ProductRecordApplication cycle : applicationInfoList){
			long applicationId = cycle.getApplicationId();
			String applicationLetter  = null;
			if(applicationId == 1){
				applicationLetter = "SF";
			}else if(applicationId == 2){
				applicationLetter = "DR";
			}else if(applicationId == 3){
				applicationLetter = "WA";
			}else if(applicationId == 4){
				applicationLetter = "PI";
			}else if(applicationId == 5){
				applicationLetter = "BR";
			}else{
				applicationLetter = "OT";
			}
			res.add(applicationLetter);
		}
		return res;
	}



	/*所有产品类表接口接口*/
	@RequestMapping(value = { "/addShopping" }, method = {RequestMethod.POST }, produces = {JSON_UTF8})
	@ResponseBody
	public String addShopping() throws Exception {
		List<ProductResItem> ProductResList=productService.listAll4App();
		Collections.shuffle(ProductResList);
		return ResponseMessageEnum.SUCCESS.appendObject(ProductResList);
	}
}
