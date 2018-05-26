package com.fh.controller.bmf.app;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.fh.entity.bmf.product.Product;
import com.fh.service.bmf.product.ProductService;
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
import com.fh.controller.bmf.app.entity.list3D4Month;
import com.fh.controller.bmf.app.entity.list3D4MonthUnorder;
import com.fh.controller.bmf.app.entity.list3D4Year;
import com.fh.controller.bmf.app.entity.list3D4YearUnorder;
import com.fh.entity.bmf.member.Member3DMatchMethod;
import com.fh.extend.logic.AccessTokenManager;
import com.fh.extend.logic.AuthCodeManager;
import com.fh.extend.util.FileUploadUtil;
import com.fh.service.bmf.member.Member3DMatchMethodService;
import com.fh.service.bmf.member.MemberService;
import com.fh.service.bmf.receiver.ReceiverService;
import com.fh.service.bmf.sms.SmsSendService;
import com.fh.util.PageData;
import com.fh.util.Tools;
import com.fh.util.UuidUtil;

/**
 */
@Controller("AppMember3DMatchMethodController")
@RequestMapping(value = "/app/user/3d/method")
public class Member3DMatchMethodController extends AppBaseController {

	@Resource(name = "memberService")
	private MemberService memberService;

	@Resource(name="member3DMatchMethodService")
	private Member3DMatchMethodService member3DMatchMethodService;

	@Resource(name="productService")
	private ProductService productService;

	/*添加3D方案*/
	/**
	 *@param Member3DMatchMethod {productId, methodName, sceneNum,  layerChannel}
	 *{memberId,productId, methodName, sceneNum,  layerChannel, updateTime}
	 * @throws Exception 
	 * 
	 */
	@RequestMapping(value={"/add"},method={RequestMethod.POST},produces={JSON_UTF8})
	@ResponseBody
	public Object add3D(@RequestBody Member3DMatchMethod reqInfo) throws Exception{
		Object result = getUser();
		if(result instanceof ResponseMessageEnum){
			return result.toString();
		}
		PageData pd = (PageData)result;		
		Member3DMatchMethod saveInfo = new Member3DMatchMethod();
		Long memberId = pd.getLong("ID");
		saveInfo.setMemberId(memberId);
		saveInfo.setProductId(Long.valueOf(reqInfo.getProductIdStr()));
		saveInfo.setMethodName(reqInfo.getMethodName());
		saveInfo.setSceneNum(reqInfo.getSceneNum());
		saveInfo.setLayerChannel(reqInfo.getLayerChannel());
		saveInfo.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		saveInfo.setCreateUserId(memberId);
		saveInfo.setCreateTime(new Timestamp(System.currentTimeMillis()));
	/*	try {*/
			member3DMatchMethodService.save(saveInfo);
/*		} catch (Exception e) {
			// TODO: handle exception
			return ResponseMessageEnum.SERVER_SQL_ERROR.toString();
		}*/
		 return ResponseMessageEnum.SUCCESS.appendEmptyData();
	 }
	/*删除3D方案*/
	/**
	 *@param Member3DMatchMethod {id,productId, methodName, sceneNum,  layerChannel}
	 *{memberId,productId, methodName, sceneNum,  layerChannel, updateTime}
	 * @throws Exception 
	 * 
	 */
	@RequestMapping(value={"/delete"},method={RequestMethod.GET},produces={JSON_UTF8})
	@ResponseBody
	public Object edit3D(@RequestParam(value="methodId") Long methodId ) throws Exception{
		Object result = getUser();
		if(result instanceof ResponseMessageEnum){
			return result.toString();
		}
		PageData pd = (PageData)result;	
		try {
			member3DMatchMethodService.delete(methodId);
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseMessageEnum.SERVER_SQL_ERROR.toString();
		}
		 return ResponseMessageEnum.SUCCESS.appendEmptyData().toString();
	 }
	/*编辑3D方案*/
	/**
	 *@param Member3DMatchMethod {id,productId, methodName, sceneNum,  layerChannel}
	 *{memberId,productId, methodName, sceneNum,  layerChannel, updateTime}
	 * @throws Exception 
	 * 
	 */
	@RequestMapping(value={"/edit"},method={RequestMethod.POST},produces={JSON_UTF8})
	@ResponseBody
	public Object edit3D(@RequestBody Member3DMatchMethod reqInfo) throws Exception{
		Object result = getUser();
		if(result instanceof ResponseMessageEnum){
			return result.toString();
		}
		PageData pd = (PageData)result;
		if(member3DMatchMethodService.findById(reqInfo.getId()) == null){
			return ResponseMessageEnum.ERROR_NOT_3D_MATCH_METHOD.toString();
		}
		Member3DMatchMethod saveInfo = new Member3DMatchMethod();
		saveInfo.setId(reqInfo.getId());
		Long memberId = pd.getLong("ID");
		saveInfo.setMemberId(memberId);
		saveInfo.setProductId(reqInfo.getProductId());
		saveInfo.setMethodName(reqInfo.getMethodName());
		saveInfo.setSceneNum(reqInfo.getSceneNum());
		saveInfo.setLayerChannel(reqInfo.getLayerChannel());
		saveInfo.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		try {
			member3DMatchMethodService.edit(saveInfo);
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseMessageEnum.SERVER_SQL_ERROR.toString();
		}
		return ResponseMessageEnum.SUCCESS.appendEmptyData().toString();
	}
	/*单个3D方案详情*/
	/**
	 *@param methodId
	 *
	 * @throws Exception 
	 * 
	 */
	@RequestMapping(value={"/detail"},method={RequestMethod.GET},produces={JSON_UTF8})
	@ResponseBody
	public Object detail3D(@RequestParam(value="methodId") Long methodId) throws Exception{
		Object result = getUser();
		if(result instanceof ResponseMessageEnum){
			return result.toString();
		}
		if(member3DMatchMethodService.findById(methodId) == null){
			return ResponseMessageEnum.ERROR_NOT_3D_MATCH_METHOD.toString();
		}
		try {
			Member3DMatchMethod resInfo = member3DMatchMethodService.findById(methodId);
			return ResponseMessageEnum.SUCCESS.appendObject(resInfo).toString();
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseMessageEnum.SERVER_SQL_ERROR.toString();
		}
	}
	/*3D方案列表*/
	/**
	 *@param Member3DMatchMethod {id,productId, methodName, sceneNum,  layerChannel}
	 *{memberId,productId, methodName, sceneNum,  layerChannel, updateTime}
	 * @throws Exception 
	 * 
	 */
	@RequestMapping(value={"/list"},method={RequestMethod.GET},produces={JSON_UTF8})
	@ResponseBody
	public Object list3D(@RequestParam(value="is_new", defaultValue="0") Long is_new) throws Exception{
		Object result = getUser();
		if(result instanceof ResponseMessageEnum){
			return result.toString();
		}
		PageData pd = (PageData)result;
		Long memberId = pd.getLong("ID");
		Member3DMatchMethod req = new Member3DMatchMethod();
		req.setMemberId(memberId);

/*		try {*/
			List<Product> allProduct = productService.listAll();
			Map<String, PageData> prodMap = new HashMap<String, PageData>();
			for(Product item: allProduct){
				PageData prodInfo = new PageData();
				prodInfo.put("matchProductId", item.getId());
				prodInfo.put("matchProductName", item.getProductName());

				if(is_new > 0){
					prodInfo.put("productUploadTime", item.getProductUploadTime());
					prodInfo.put("d3dUploadTime", item.getD3dUploadTime());
					prodInfo.put("matchProductIcon", item.getProductName() + "-Q1.jpg");
				}

				prodMap.put(item.getId().toString(), prodInfo);
			}

			List<Member3DMatchMethod> dbInfoList = member3DMatchMethodService.findByCondition(req);
			if(dbInfoList.size()==0){
				return ResponseMessageEnum.SUCCESS.appendEmptyData().toString();
			}
			System.out.println("dbInfoList size is "+dbInfoList.size());
			System.out.println("dbInfoList first info "+dbInfoList.get(0).toString());
			List<list3D4Year> list3D4Year = new ArrayList<list3D4Year>(); 
			List<list3D4YearUnorder> member3dMethodSortByYear = get3DMethodSortByYear(dbInfoList);//按年份进行分类
			System.out.println("member3dMethodSortByYear size"+member3dMethodSortByYear.size());
			for(list3D4YearUnorder cycle : member3dMethodSortByYear){//1个cycle包含一年的所有方案信息
				System.out.println("cycle size"+cycle.getMethodList().size());
				System.out.println("cycle size time"+cycle.getMethodList().get(0).getId());
				list3D4Year newYear = new list3D4Year();
				DateFormat sdfYear = new SimpleDateFormat("yyyy");
				//String[] thisEditTimeArray = member3dMatchMethod.getUpdateTime().toString().split("-");
				String thisYear = sdfYear.format(cycle.getMethodList().get(0).getUpdateTime());
				newYear.setYear(thisYear);
				 List<list3D4Month> thisMonthList = new ArrayList<list3D4Month>();
				List<list3D4MonthUnorder> member3dMethodSortByMonth = get3DMethodSortByMonth(cycle.getMethodList());
				for(list3D4MonthUnorder cycle1 : member3dMethodSortByMonth){//1个cycle包含一年的某月所有方案信息

					list3D4Month newMonth = new list3D4Month();
					DateFormat sdfMonth = new SimpleDateFormat("MM");
					String thisMonth = sdfMonth.format(cycle1.getMethodList().get(0).getUpdateTime());
					logger.info("cycle1 is thisMonth"+thisMonth);
					newMonth.setMonth(thisMonth);
					List<Member3DMatchMethod> member3DMatchMethod = cycle1.getMethodList();

					//低版本没有的字段不输出
					if(is_new > 0) {
						for (Member3DMatchMethod member : member3DMatchMethod) {
							String layerChannel = member.getLayerChannel();
							String[] arr = layerChannel.split("&");
							List<PageData> productList = new ArrayList<>();
							if (arr.length >= 2) {
								String ids = arr[1];
								String[] idsArr = ids.split(",");
								for (String prod_id : idsArr) {
									PageData prod_item = prodMap.get(prod_id);
									if(prod_item != null){
										productList.add(prod_item);
									}
								}
							}
							layerChannel = removeDeleteProduct(layerChannel, prodMap);
							member.setLayerChannel(layerChannel);
							member.setProductList(productList);
						}
					}

					newMonth.setMember3DMatchMethodList(member3DMatchMethod);
					thisMonthList.add(newMonth);
				}
				newYear.setMonthList(thisMonthList);
				list3D4Year.add(newYear);
			}
			return ResponseMessageEnum.SUCCESS.appendObject(list3D4Year).toString();
/*		} catch (Exception e) {
			// TODO: handle exception
			return ResponseMessageEnum.SERVER_SQL_ERROR.toString();
		}*/
	}

	//删除不存在的商品
	private String removeDeleteProduct(String layerChannel, Map<String, PageData> prodMap) {
		String newLayerChannel = "";
		LinkedHashMap<Integer, ArrayList> map = new LinkedHashMap<Integer, ArrayList>();
		String[] arr = layerChannel.split("&");
		if(arr.length >= 2){
			for(int idx=0; idx<arr.length; idx++){
				String item = arr[idx];
				String[] itemArr = item.split(",");

				for(int itemIdx=0; itemIdx<itemArr.length; itemIdx++){
					String col = itemArr[itemIdx];

					if(!map.containsKey(itemIdx)){
						ArrayList subList = new ArrayList();
						map.put(itemIdx, subList);
					}

					ArrayList subList = map.get(itemIdx);
					subList.add(col);
				}
			}

			LinkedHashMap<Integer, ArrayList> new_map = new LinkedHashMap<Integer, ArrayList>();
			Iterator<Map.Entry<Integer, ArrayList>> iterator= map.entrySet().iterator();
			while(iterator.hasNext())
			{
				Map.Entry<Integer, ArrayList> entry = iterator.next();
				ArrayList subList = entry.getValue();
				String prod_id = subList.get(1).toString();
				if(prodMap.containsKey(prod_id)){
					int idx = 0;
					for(Object str : subList){
						if(!new_map.containsKey(idx)){
							new_map.put(idx, new ArrayList());
						}
						new_map.get(idx).add(str);
						idx++;
					}
				}
			}

			iterator= new_map.entrySet().iterator();
			while(iterator.hasNext())
			{
				Map.Entry<Integer, ArrayList> entry = iterator.next();
				ArrayList oList = entry.getValue();

				if(!newLayerChannel.equals("")){
					newLayerChannel = newLayerChannel + "&";
				}

				String strTemp = "";
				for(Object str : oList){
					if(!strTemp.equals("")){
						strTemp = strTemp + ",";
					}
					strTemp += str.toString();
				}
				newLayerChannel += strTemp;
			}
		}

		return newLayerChannel;
	}

	private List<list3D4YearUnorder> get3DMethodSortByYear(List<Member3DMatchMethod> member3DList){
		List<list3D4YearUnorder> res= new ArrayList<list3D4YearUnorder>();
		Member3DMatchMethod  temporaryMethod = new Member3DMatchMethod();
		for(Member3DMatchMethod cycle : member3DList){
			DateFormat sdf0 = new SimpleDateFormat("yyyy");
			String year = sdf0.format(cycle.getUpdateTime());
			System.out.println("cycle.getUpdateTime()"+year);
			int num =0;
			int yearOrder = -1;

			for(int i =0 ;i<res.size();i++){
				List<Member3DMatchMethod> methodList = res.get(i).getMethodList();
				DateFormat sdf = new SimpleDateFormat("yyyy");
				String thisYear = sdf.format(methodList.get(0).getUpdateTime());
				if(year.equals(thisYear)){
					num = 1;
					yearOrder = i;
					break;
				}
			}
			if(num ==1){
				res.get(yearOrder).getMethodList().add(cycle);
				num =0;
				yearOrder = -1;
				temporaryMethod = null;
			}else{
				list3D4YearUnorder newYear= new list3D4YearUnorder();
				List<Member3DMatchMethod> newMethodList = new ArrayList<Member3DMatchMethod>();
				newMethodList.add(cycle);
				newYear.setMethodList(newMethodList);
				res.add(newYear);
			}
		}
		return  res;
	}
	
	private List<list3D4MonthUnorder> get3DMethodSortByMonth(List<Member3DMatchMethod> member3DList){
		List<list3D4MonthUnorder> res= new ArrayList<list3D4MonthUnorder>();
		Member3DMatchMethod  temporaryMethod = new Member3DMatchMethod();
		
		for(Member3DMatchMethod cycle : member3DList){
			//String[] editTimeArray = cycle.getUpdateTime().toString().split("-");
			//String month = editTimeArray[1];
			DateFormat sdf = new SimpleDateFormat("MM");
			String month = sdf.format(cycle.getUpdateTime());
			int num =0;
			int monthOrder = -1;			
			for(int i =0 ;i<res.size();i++){
				List<Member3DMatchMethod> methodList = res.get(i).getMethodList();
				DateFormat sdf1 = new SimpleDateFormat("MM");
				String thisMonth = sdf1.format(methodList.get(0).getUpdateTime());
				if(month.equals(thisMonth)){
					num = 1;
					monthOrder = i;
					break;
				}
			}
			if(num ==1){
				res.get(monthOrder).getMethodList().add(cycle);
				num =0;
				monthOrder = -1;
				temporaryMethod = null;
			}else{
				list3D4MonthUnorder newMonth= new list3D4MonthUnorder();
				List<Member3DMatchMethod> newMethodList = new ArrayList<Member3DMatchMethod>();
				newMethodList.add(cycle);
				newMonth.setMethodList(newMethodList);
				res.add(newMonth);
			}
		}
		return  res;
	}
}
