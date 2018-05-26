package com.fh.controller.bmf.productparam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fh.controller.base.BaseWebController;
import com.fh.service.BaseService;
import com.fh.service.bmf.productparam.ProductParamColorService;
import com.fh.entity.bmf.productparam.ProductParamColor;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fh.common.model.ResponseMessageEnum;
import com.fh.common.model.SysLogEnum;
import com.fh.entity.BaseEntity;
import com.fh.entity.Page;
import com.fh.entity.system.User;
import com.fh.extend.util.FileUploadUtil;
import com.fh.service.BaseService;
import com.fh.util.Const;
import com.fh.util.Jurisdiction;
import com.fh.util.PageData;

/** 
 * 类名称：ProductParamColorController
 * 创建人：tyj
 * 创建时间：2017-07-18
 */
@Controller
@RequestMapping(value="/productparamcolor")
public class ProductParamColorController extends BaseWebController<ProductParamColor> {
	
	String menuUrl = "productparamcolor/list.do"; //菜单地址(权限用)
	@Resource(name="productParamColorService")
	private ProductParamColorService productParamColorService;
	
	@Override
	protected BaseService<ProductParamColor> getBaseService() {
		return productParamColorService;
	}
	@Override
	protected String getPackageName() {
		return "productparam";
	}
	@Override
	protected String getObjectName() {
		return "ProductParamColor";
	}
	
	 /**
	   * 保存/编辑颜色
	   */
	  @RequestMapping(value={"/rest/saveColor"})
	  @ResponseBody
	  public ModelAndView saveColor(
			  @RequestParam Map<String, Object> params, 
			  @RequestParam(value="colorIcon") MultipartFile colorIcon,
			  HttpServletRequest request) throws Exception  {
		  PageData pd = new PageData();
		  pd.putAll(params);	
		  logger.info("i am save colorIcon");
		  ProductParamColor resColor = new ProductParamColor();
		  //获取颜色名称
		  resColor.setColorName(pd.getString("colorName"));
		  //获取个人信息
			 logger.info("this colorIcon is"+colorIcon);
			long time = System.currentTimeMillis();			
			if(colorIcon != null && !colorIcon.isEmpty()){
				String colorIconUrl = FileUploadUtil.upload(colorIcon, request, "product/param/color", "color" + time);
				resColor.setColorIcon(colorIconUrl);
				 logger.info("this avatarUrl is"+colorIconUrl);
			}
			//获取颜色排序
			resColor.setColorOrder(pd.getInteger("colorOrder"));
			if(pd.get("id") == null ||pd.get("id").equals("")){
				logger.info("this operation is add");
				resColor.setCreateUserId(getSessionUserId());
				resColor.setCreateTime(new Timestamp(System.currentTimeMillis()));
				productParamColorService.save(resColor);
			}else{
				logger.info("this operation is edit");
				resColor.setId(pd.getLong("id"));
				productParamColorService.edit(resColor);
			}
		  logger.info("this colorInfo has been done");
		  //返回结果
			return list(this.getPage());
		//  return ResponseMessageEnum.SUCCESS.toString();
	  } 
}
