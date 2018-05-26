package com.fh.service.bmf.product;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fh.service.BaseService;
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
import com.fh.service.bmf.syslog.SysLogBizService;
import com.fh.common.model.SysLogEnum;
import com.fh.entity.Page;
import com.fh.entity.bmf.app.ProductResItem;
import com.fh.entity.bmf.product.Product;
import com.fh.entity.bmf.productrecord.ProductRecordApplication;
import com.fh.entity.bmf.productrecord.ProductRecordColor;
import com.fh.entity.bmf.productrecord.ProductRecordMatchScheme;
import com.fh.entity.bmf.productrecord.ProductRecordStyle;
import com.fh.entity.bmf.productrecord.ProductRecordWashingMethod;
import com.fh.entity.system.User;


/** 
 * 类名称：Product
 * 创建人：tyj
 * 创建时间：2017-07-17
 */

@Service("productSaveBizService")
public class ProductSaveBizService{
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
	
	@Resource(name="sysLogBizService")
	private SysLogBizService sysLogBizService;
	
	@Autowired
	protected HttpServletRequest request;
	
	//录入产品
	public void saveProduct(Product  productItem,Map<String,Object>map,Long userId,User user) throws Exception{
		String product_name = productItem.getProductName();
		Product find = productService.findByProductName(product_name);
		if(find != null){
			return;
		}

		//保存产品
		productService.save(productItem);
		Long productId = productItem.getId();
		//保存产品多选参数-颜色
		String colorIdStrList =map.get("productColorList").toString();
		String[] colorIdList = colorIdStrList.split(",");
		for(String colorId:colorIdList ){
			ProductRecordColor saveColor = new ProductRecordColor();
			saveColor.setColorId(Long.valueOf(colorId));
			saveColor.setProductId(productId);
			saveColor.setCreateUserId(userId);
			saveColor.setCreateTime(new Timestamp(System.currentTimeMillis()));
			productRecordColorService.save(saveColor);
		}
		//保存产品多选参数-风格
		String styleIdStrList =map.get("productStyleList").toString(); 
		String[] styleIdList = styleIdStrList.split(",");
		for(String styleId:styleIdList ){
			ProductRecordStyle saveStyle = new ProductRecordStyle();
			saveStyle.setStyleId(Long.valueOf(styleId));
			saveStyle.setProductId(productId);
			saveStyle.setCreateUserId(userId);
			saveStyle.setCreateTime(new Timestamp(System.currentTimeMillis()));
			productRecordStyleService.save(saveStyle);
		}
		//保存产品多选参数-应用
		String applicationIdStrList =map.get("productApplicationList").toString();
		String[] applicationIdList = applicationIdStrList.split(",");
		for(String  applicationId:applicationIdList ){
			ProductRecordApplication saveApplication = new ProductRecordApplication(); 
			saveApplication.setApplicationId(Long.valueOf(applicationId));
			saveApplication.setProductId(productId);
			saveApplication.setCreateUserId(userId);
			saveApplication.setCreateTime(new Timestamp(System.currentTimeMillis()));
			productRecordApplicationService.save(saveApplication);
		}
		//保存产品多选参数-水洗标志
		String washMethodIdStrList =map.get("washingMethodList").toString();
		String[] washMethodIdList = washMethodIdStrList.split(",");
		for(String washMethodId:washMethodIdList ){
			ProductRecordWashingMethod saveWashMethod = new ProductRecordWashingMethod();
			saveWashMethod.setWashingMethodId(Long.valueOf(washMethodId));
			saveWashMethod.setProductId(productId);
			saveWashMethod.setCreateUserId(userId);
			saveWashMethod.setCreateTime(new Timestamp(System.currentTimeMillis()));
			productRecordWashingMethodService.save(saveWashMethod);
		}
		//保存产品搭配方案  productMatchIdList
		String productMatchIdStrList =map.get("productMatchIdList").toString();
		String[] productMatchIdList = productMatchIdStrList.split(",");
		for(String productMatchId:productMatchIdList ){
			ProductRecordMatchScheme productMatch = new ProductRecordMatchScheme();
			if(!productMatchId.equals("")) {
				productMatch.setMatchProductId(Long.valueOf(productMatchId));
				productMatch.setProductId(productId);
				productMatch.setCreateUserId(userId);
				productMatch.setCreateTime(new Timestamp(System.currentTimeMillis()));
				productRecordMatchSchemeService.save(productMatch);
			}
		}
	
        sysLogBizService.saveLog(request, user,
				SysLogEnum.PRODUCT_ADD.getName(), SysLogEnum.PRODUCT_ADD.getContent()+":"+productItem.getProductName());
	}
	//编辑产品
	public void editProduct(Product  productItem,Map<String,Object>map,Long userId,User user)throws Exception{
		//保存产品
		productService.edit(productItem);		
		Long productId = productItem.getId();
		//删除该产品关联的数据表里关于该产品的所有信息：颜色 风格 应用 水洗标志 搭配方案
		productRecordColorService.deleteProductColor(productId);
		productRecordStyleService.deleteProductStyle(productId);
		productRecordApplicationService.deleteProductApplication(productId);
		productRecordWashingMethodService.deleteProductWashingMethod(productId);
		productRecordMatchSchemeService.deleteProductMatchSheme(productId); 
		//保存产品多选参数-颜色
		String colorIdStrList =map.get("productColorList").toString();
		String[] colorIdList = colorIdStrList.split(",");
		for(String colorId:colorIdList ){
			ProductRecordColor saveColor = new ProductRecordColor();
			saveColor.setColorId(Long.valueOf(colorId));
			saveColor.setProductId(productId);
			saveColor.setCreateUserId(userId);
			saveColor.setCreateTime(new Timestamp(System.currentTimeMillis()));
			productRecordColorService.save(saveColor);
		}
		//保存产品多选参数-风格
		String styleIdStrList =map.get("productStyleList").toString(); 
		String[] styleIdList = styleIdStrList.split(",");
		for(String styleId:styleIdList ){
			ProductRecordStyle saveStyle = new ProductRecordStyle();
			saveStyle.setStyleId(Long.valueOf(styleId));
			saveStyle.setProductId(productId);
			saveStyle.setCreateUserId(userId);
			saveStyle.setCreateTime(new Timestamp(System.currentTimeMillis()));
			productRecordStyleService.save(saveStyle);
		}
		//保存产品多选参数-应用
		String applicationIdStrList =map.get("productApplicationList").toString();
		String[] applicationIdList = applicationIdStrList.split(",");
		for(String  applicationId:applicationIdList ){
			ProductRecordApplication saveApplication = new ProductRecordApplication(); 
			saveApplication.setApplicationId(Long.valueOf(applicationId));
			saveApplication.setProductId(productId);
			saveApplication.setCreateUserId(userId);
			saveApplication.setCreateTime(new Timestamp(System.currentTimeMillis()));
			productRecordApplicationService.save(saveApplication);
		}
		//保存产品多选参数-水洗标志
		String washMethodIdStrList =map.get("washingMethodList").toString();
		String[] washMethodIdList = washMethodIdStrList.split(",");
		for(String washMethodId:washMethodIdList ){
			ProductRecordWashingMethod saveWashMethod = new ProductRecordWashingMethod();
			saveWashMethod.setWashingMethodId(Long.valueOf(washMethodId));
			saveWashMethod.setProductId(productId);
			saveWashMethod.setCreateUserId(userId);
			saveWashMethod.setCreateTime(new Timestamp(System.currentTimeMillis()));
			productRecordWashingMethodService.save(saveWashMethod);
		}
		//保存产品搭配方案  productMatchIdList
		String productMatchIdStrList =map.get("productMatchIdList").toString();
		String[] productMatchIdList = productMatchIdStrList.split(",");
		for(String productMatchId:productMatchIdList ){
			ProductRecordMatchScheme productMatch = new ProductRecordMatchScheme();
			productMatch.setMatchProductId(Long.valueOf(productMatchId));
			productMatch.setProductId(productId);
			productMatch.setCreateUserId(userId);
			productMatch.setCreateTime(new Timestamp(System.currentTimeMillis()));
			productRecordMatchSchemeService.save(productMatch);
		}
		 sysLogBizService.saveLog(request, user,
					SysLogEnum.PRODUCT_EDIT.getName(), SysLogEnum.PRODUCT_EDIT.getContent()+":"+productItem.getProductName());
	}
}

