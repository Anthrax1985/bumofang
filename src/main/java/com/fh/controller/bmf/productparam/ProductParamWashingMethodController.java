package com.fh.controller.bmf.productparam;

import java.sql.Timestamp;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fh.controller.base.BaseWebController;
import com.fh.service.BaseService;
import com.fh.service.bmf.productparam.ProductParamWashingMethodService;
import com.fh.util.PageData;
import com.fh.entity.bmf.productparam.ProductParamColor;
import com.fh.entity.bmf.productparam.ProductParamWashingMethod;
import com.fh.extend.util.FileUploadUtil;

/** 
 * 类名称：ProductParamWashingMethodController
 * 创建人：tyj
 * 创建时间：2017-07-19
 */
@Controller
@RequestMapping(value="/productparamwashingmethod")
public class ProductParamWashingMethodController extends BaseWebController<ProductParamWashingMethod> {
	
	String menuUrl = "productparamwashingmethod/list.do"; //菜单地址(权限用)
	@Resource(name="productParamWashingMethodService")
	private ProductParamWashingMethodService productParamWashingMethodService;
	
	@Override
	protected BaseService<ProductParamWashingMethod> getBaseService() {
		return productParamWashingMethodService;
	}
	@Override
	protected String getPackageName() {
		return "productparam";
	}
	@Override
	protected String getObjectName() {
		return "ProductParamWashingMethod";
	}
	 /**
	   * 保存/编辑水洗标志
	   */
	  @RequestMapping(value={"/rest/saveWashingMethed"})
	  @ResponseBody
	  public ModelAndView saveWashingMethed(
			  @RequestParam Map<String, Object> params, 
			  @RequestParam(value="washingMethodIcon") MultipartFile washingMethodIcon,
			  HttpServletRequest request) throws Exception  {
		  PageData pd = new PageData();
		  pd.putAll(params);	
		  logger.info("i am save washingMethodIcon");
		  ProductParamWashingMethod resWashingMethod = new ProductParamWashingMethod();
		  //获取颜色名称
		  resWashingMethod.setWashingMethodName(pd.getString("washingMethodName"));
		  //获取个人信息
			 logger.info("this WashingMethodIcon is"+washingMethodIcon);
			long time = System.currentTimeMillis();			
			if(washingMethodIcon != null && !washingMethodIcon.isEmpty()){
				String washingMethodIconUrl = FileUploadUtil.upload(washingMethodIcon, request, "product/param/washingmethod", "washingmethod" + time);
				resWashingMethod.setWashingMethodIcon(washingMethodIconUrl);
				 logger.info("this washingMethodIconUrl is"+washingMethodIconUrl);
			}
			//获取颜色排序
			resWashingMethod.setWashingMethodOrder(pd.getInteger("washingMethodOrder"));
			//保存或编辑
			if(pd.get("id") == null ||pd.get("id").equals("")){
				logger.info("this operation is add");
				resWashingMethod.setCreateUserId(getSessionUserId());
				resWashingMethod.setCreateTime(new Timestamp(System.currentTimeMillis()));
				productParamWashingMethodService.save(resWashingMethod);
			}else{
				logger.info("this operation is edit");
				resWashingMethod.setId(pd.getLong("id"));
				productParamWashingMethodService.edit(resWashingMethod);
			}
		  logger.info("this colorInfo has been done");
		  //返回结果
			return list(this.getPage());
		//  return ResponseMessageEnum.SUCCESS.toString();
	  } 
	
}
