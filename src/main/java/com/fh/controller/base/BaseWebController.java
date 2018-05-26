package com.fh.controller.base;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fh.common.model.ResponseMessageEnum;
import com.fh.common.model.SysLogEnum;
import com.fh.entity.BaseEntity;
import com.fh.entity.Page;
import com.fh.entity.system.User;
import com.fh.service.BaseService;
import com.fh.util.Const;
import com.fh.util.Jurisdiction;
import com.fh.util.PageData;

public abstract class BaseWebController<T extends BaseEntity> extends BaseController {

	protected String menuUrl = getObjectName().toLowerCase() + "/list.do"; // 菜单地址(权限用)

	protected abstract BaseService<T> getBaseService();

	protected abstract String getPackageName();

	protected abstract String getObjectName();

	/**
	 * 
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = { JSON_UTF8 })
	@ResponseBody
	public String save(@RequestBody T entity) throws Exception {
		if (!Jurisdiction.buttonJurisdiction(menuUrl, "add")) {
			return ResponseMessageEnum.VISIT_NOT_AUTHORIZED.toString();
		} // 校验权限
		logBefore(logger, "新增" + getObjectName());

		try {
			User user = getSessionUser();
			entity.setDelFlag(0);
			entity.setCreateTime(new Timestamp(System.currentTimeMillis()));
			entity.setCreateUserId(user.getUSER_ID());
			beforeSave(entity);
			getBaseService().save(entity);
		} catch (Exception e) {
			logger.warn(e.toString(), e);
			return ResponseMessageEnum.SERVER_SQL_ERROR.toString();
		}

		return getSaveSuccessResponse(entity);
	}

	protected void beforeSave(T entity) {
	}

	protected String getSaveSuccessResponse(T entity) {
		return ResponseMessageEnum.SUCCESS.toString();
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST, produces = { JSON_UTF8 })
	@ResponseBody
	public String delete(@PathVariable(value = "id") Long id) {
		if (!Jurisdiction.buttonJurisdiction(menuUrl, "del")) {
			return ResponseMessageEnum.VISIT_NOT_AUTHORIZED.toString(); // 校验权限
		}
		logBefore(logger, "删除" + getObjectName());
		try {
			getBaseService().delete(id);
		} catch (Exception e) {
			logger.warn(e.toString(), e);
			return ResponseMessageEnum.SERVER_SQL_ERROR.toString();
		}
		return ResponseMessageEnum.SUCCESS.toString();
	}


	/**
	 * RESTFUL修改
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.POST, produces = { JSON_UTF8 })
	@ResponseBody
	public String edit(@RequestBody T entity) throws Exception {
		if (!Jurisdiction.buttonJurisdiction(menuUrl, "edit")) {
			return ResponseMessageEnum.VISIT_NOT_AUTHORIZED.toString();
		} // 校验权限
		logBefore(logger, "修改" + getObjectName());

		try {

			getBaseService().edit(entity);
		} catch (Exception e) {
			logger.warn(e.toString(), e);
			return ResponseMessageEnum.SERVER_SQL_ERROR.toString();
		}

		return ResponseMessageEnum.SUCCESS.toString();
	}

	/**
	 * 列表
	 */
	@RequestMapping(value = "/list")
	public ModelAndView list(Page page) {
		if (!Jurisdiction.buttonJurisdiction(menuUrl, "cha")) {
			return null;
		} // 校验权限
		logBefore(logger, "列表" + getObjectName());
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try {
			pd = this.getPageData();
			page.setPd(pd);
			/*beforeList(page);*/
			List<T> varList = getBaseService().list(page); // 列出列表
			listAfter(varList);
			mv.addObject("varList", varList);
			mv.setViewName(getViewNameSuffix() + "_list");
			mv.addObject("pd", pd);
			mv.addObject("page", page);
			mv.addObject(Const.SESSION_QX, this.getHC()); // 按钮权限
			listChangeModel(mv);
		} catch (Exception e) {
			logger.warn(e.toString(), e);
		}
		return mv;
	}
	

	protected String getViewNameSuffix() {
		return "bmf/" + getPackageName() + "/" + getObjectName().toLowerCase();
	}

	protected void listChangeModel(ModelAndView modelAndView) {

	}

	protected void listAfter(List<T> varList) {

	}

	/**
	 * 去新增页面
	 */
	@RequestMapping(value = "/goAdd")
	public ModelAndView goAdd() {
		logBefore(logger, "去新增页面" + getObjectName());
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName(getViewNameSuffix() + "_edit");
			mv.addObject("msg", "save");
			mv.addObject("pd", pd);
			goAddChangeModel(mv);
		} catch (Exception e) {
			logger.warn(e.toString(), e);
		}
		return mv;
	}

	protected void goAddChangeModel(ModelAndView modelAndView) {

	}

	/**
	 * 去修改页面
	 */
	@RequestMapping(value = "/goEdit/{id}")
	public ModelAndView goEdit(@PathVariable(value = "id") Long id) {
		logBefore(logger, "去修改页面" + getObjectName());
		ModelAndView mv = this.getModelAndView();
		try {
			T entity = getBaseService().findById(id); // 根据ID读取
			entityAfter(entity);
			mv.setViewName(getViewNameSuffix() + "_edit");
			mv.addObject("msg", "edit");
			mv.addObject("entity", entity);
			goAddChangeModel(mv);
		} catch (Exception e) {
			logger.warn(e.toString(), e);
		}
		return mv;
	}
	

	protected void entityAfter(T entity) {

	}

	/**
	 * 批量删除
	 */
	@RequestMapping(value = "/deleteBatch", method = RequestMethod.POST, produces = { JSON_UTF8 })
	@ResponseBody
	public Object deleteBatch(@RequestBody Map<String, String> idsMap) {
		if (!Jurisdiction.buttonJurisdiction(menuUrl, "del")) {
			return null;
		} // 校验权限
		logBefore(logger, "批量删除" + getObjectName());
		String ids = idsMap.get("ids");
		try {
			String[] idArray = ids.split(",");
			getBaseService().deleteBatch(idArray);
		} catch (Exception e) {
			logger.warn(e.toString(), e);
			return ResponseMessageEnum.SERVER_SQL_ERROR.toString();
		}
		return ResponseMessageEnum.SUCCESS.toString();
	}

	/**
	 * 去查看页面
	 */
	@RequestMapping(value = "/goView/{id}")
	public ModelAndView goView(@PathVariable(value = "id") Long id) {
		if (!Jurisdiction.buttonJurisdiction(menuUrl, "cha")) {
			return null;
		} // 校验权限
		logBefore(logger, "去查看页面" + getObjectName());
		ModelAndView mv = this.getModelAndView();
		try {
			T entity = getBaseService().findById(id); // 根据ID读取
			beforeView(mv, entity);
			mv.setViewName(getViewNameSuffix() + "_view");
			mv.addObject("msg", "view");
			mv.addObject("entity", entity);
		} catch (Exception e) {
			logger.warn(e.toString(), e);
		}
		return mv;
	}
	
	/**
	 * 根据条件获取
	 */
	@RequestMapping(value = "/list/condition", method = RequestMethod.POST, produces = { JSON_UTF8 })
	@ResponseBody
	public String listByCondition(@RequestBody T entity) {
		try {
			List<T> resultList = getBaseService().findByCondition(entity);
			return ResponseMessageEnum.SUCCESS.appendObject2String(resultList);
		} catch (Exception e) {
			logger.warn(e.toString(), e);
			return ResponseMessageEnum.SERVER_SQL_ERROR.toString();
		}
	}

	/**
	 * 返回view页面之前需要处理的操作
	 * 
	 * @param entity
	 * @throws Exception
	 */
	protected void beforeView(ModelAndView mv, T entity) throws Exception {
	}
	
	/**
	 * 去审核页面
	 * @return
	 */
	@RequestMapping(value="/goAudit/{id}")
	public ModelAndView goAudit(@PathVariable(value="id")Long id){
		logBefore(logger, "去审核页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.addObject("id",id);
			mv.addObject("msg", "audit");
			mv.setViewName("youbao/audit/common" + "_audit");
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return mv;
	}
	

}
