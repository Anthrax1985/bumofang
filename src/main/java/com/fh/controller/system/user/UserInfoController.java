package com.fh.controller.system.user;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fh.common.model.ResponseMessageEnum;
import com.fh.common.model.SysLogEnum;
import com.fh.controller.base.BaseController;
import com.fh.entity.Page;
import com.fh.entity.system.Role;
import com.fh.entity.system.User;
import com.fh.extend.util.FileUploadUtil;
import com.fh.service.bmf.syslog.SysLogBizService;
import com.fh.service.system.menu.MenuService;
import com.fh.service.system.role.RoleService;
import com.fh.service.system.user.UserService;
import com.fh.util.AppUtil;
import com.fh.util.Const;
import com.fh.util.FileDownload;
import com.fh.util.FileUpload;
import com.fh.util.GetPinyin;
import com.fh.util.Jurisdiction;
import com.fh.util.ObjectExcelRead;
import com.fh.util.ObjectExcelView;
import com.fh.util.PageData;
import com.fh.util.PathUtil;
import com.fh.util.Tools;

/** 
 * Controller - 用户
 * 类名称：UserController
 * 创建人：FH 
 * 创建时间：2014年6月28日
 * @version
 */
@Controller
@RequestMapping(value="/user")
public class UserInfoController extends BaseController {
	
	String menuUrl = "user/listThisUsers.do"; //菜单地址(权限用)
	@Resource(name="userService")
	private UserService userService;
	@Resource(name="roleService")
	private RoleService roleService;
	@Resource(name="menuService")
	private MenuService menuService;
	
	@Resource(name="sysLogBizService")
	private SysLogBizService sysLogBizService;
	
	@Autowired
	protected HttpServletRequest request;
	
	private String BMF = "bumofang";


	@InitBinder
	public void initBinder(WebDataBinder binder){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,true));
	}	

	/* ===============================权限================================== */
	public Map<String, String> getHC(){
		Subject currentUser = SecurityUtils.getSubject();  //shiro管理的session
		Session session = currentUser.getSession();
		return (Map<String, String>)session.getAttribute(Const.SESSION_QX);
	}
	/* ===============================权限================================== */
	
		/**
		 * 显示个人账户信息
		 */
		@RequestMapping(value="/listThisUsers")
		public ModelAndView listThisUsers(Page page)throws Exception{
			ModelAndView mv = this.getModelAndView();
			PageData pd = new PageData();
			User user = this.getSessionUser(getRequest());
			pd.put("USER_ID", user.getUSER_ID());
			logger.info("user.getUSER_ID()"+user.getUSER_ID());
			PageData userInfo = userService.findByUiId(pd);
			String lastLogin = userInfo.getString("LAST_LOGIN");
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date thisDate = df.parse(lastLogin);
			DateFormat format1= new SimpleDateFormat("yyyy年M月dd日(E)HH:mm");
			String dateInfo = format1.format(thisDate);
			userInfo.put("LAST_LOGIN_SHOW",dateInfo);
			System.out.println("userInfo.getString()"+userInfo.getString("LAST_LOGIN"));
			
			
			logger.info(userInfo.toString());
			
			mv.setViewName("system/user/user_info");
			mv.addObject("userInfo", userInfo);
			mv.addObject("pd", pd);
			mv.addObject(Const.SESSION_QX,this.getHC());	//按钮权限
			return mv;
		}	  
		/**
		 * 去修改密码界面
		 */	  
		@RequestMapping(value="/goChangePassword")
		public ModelAndView goChangePassword() throws Exception{
			ModelAndView mv = this.getModelAndView();
			PageData pd = new PageData();
			pd = this.getPageData();
			logger.info("this getin pd is "+pd.toString());
			pd = userService.findByUiId(pd);							//根据ID读取
			logger.info("this userID for changePassword is"+pd.getLong("USER_ID"));
			mv.setViewName("system/user/user_changepassword");
			mv.addObject("msg", "changePassword");
			mv.addObject("pd", pd);
			return mv;
		}
		/**
		 * 用户修改密码
		 */
		  @RequestMapping(value={"/rest/changePassword"}, method={RequestMethod.POST}, produces={JSON_UTF8})
		  @ResponseBody
		  public String changePassword(@RequestBody Map<String, String> map) throws Exception  {
			PageData pd = new PageData();
			pd.putAll(map);	
			//获取个人信息
			PageData dbUser = userService.findByUiId(pd);
			//修改密码
			if(pd.getString("PASSWORD") != null && !"".equals(pd.getString("PASSWORD"))){
				String newPassword = pd.getString("PASSWORD");
				logger.info("newPassword"+newPassword);
				logger.info("dbUser.toString()"+dbUser.toString());
				dbUser.put("PASSWORD", new SimpleHash("SHA-1", BMF, newPassword).toString());
				logger.info("dbUser.getString()"+dbUser.getString("PASSWORD"));
				/*if(Jurisdiction.buttonJurisdiction(menuUrl, "edit")){*/
					userService.editU(dbUser);
					logger.info("this password has been changded and saved");
				/*}*/
				//日志
				User user = getCurrentUser();
				sysLogBizService.saveLog(request, user,
						SysLogEnum.USER_CHANGE_PASWORD.getName(), SysLogEnum.USER_CHANGE_PASWORD.getContent());
			}
			//返回结果
				return ResponseMessageEnum.SUCCESS.toString();
		}
		 
	  /**
	   * 去修改邮箱界面
	   */
	  @RequestMapping(value="/goChangeUserName")
	  public ModelAndView goChangeUserName() throws Exception{
		  ModelAndView mv = this.getModelAndView();
		  PageData pd = new PageData();
		  pd = this.getPageData();
		  logger.info("this getin pd is "+pd.toString());
		  pd = userService.findByUiId(pd);							//根据ID读取
		  logger.info("this userID for changePassword is"+pd.getLong("USER_ID"));
		  mv.setViewName("system/user/user_changeusername");
		  mv.addObject("msg", "changeUserName");
		  mv.addObject("pd", pd);
		  return mv;
	  }
	  /**
	   * 用户修改邮箱
	   */
	  @RequestMapping(value={"/rest/changeUserName"}, method={RequestMethod.POST}, produces={JSON_UTF8})
	  @ResponseBody
	  public String changeUserName(@RequestBody Map<String, String> map) throws Exception  {
		  PageData pd = new PageData();
		  pd.putAll(map);	
		  //获取个人信息
		  PageData dbUser = userService.findByUiId(pd);
		  //修改邮箱（unsername）
		  dbUser.put("USERNAME", map.get("USERNAME"));
		 logger.info("this username is "+map.get("USERNAME"));
		  /*if(Jurisdiction.buttonJurisdiction(menuUrl, "edit")){*/
		  userService.editU(dbUser);
		  logger.info("this username has been changded and saved");
		//日志
			User user = getCurrentUser();
			sysLogBizService.saveLog(request, user,
					SysLogEnum.USER_CHANGE_EMAIL.getName(), SysLogEnum.USER_CHANGE_EMAIL.getContent());
		  /*}*/
		  //返回结果
		  return ResponseMessageEnum.SUCCESS.toString();
	  }
	  /**
	   * 去上传头像页面
	   */
	  @RequestMapping(value="/goUploadAvatar")
	  public ModelAndView goUploadAvatar() throws Exception{
		  ModelAndView mv = this.getModelAndView();
		  PageData pd = new PageData();
		  pd = this.getPageData();
		  pd = userService.findByUiId(pd);							//根据ID读取
		  mv.setViewName("system/user/user_uploadavatar");
		  mv.addObject("msg", "uploadAvatar");
		  mv.addObject("pd", pd);
		  return mv;
	  }
	  /**
	   * 上传头像
	   */
	  @RequestMapping(value={"/rest/uploadAvatar"})
	  @ResponseBody
	  public ModelAndView uploadAvatar(
			  @RequestParam Map<String, Object> params, 
			  @RequestParam(value="AVATAR") MultipartFile AVATAR,
			  HttpServletRequest request) throws Exception  {
		  PageData pd = new PageData();
		  pd.putAll(params);	
		  logger.info("i am uploading avatar1");
		  //获取个人信息
		  PageData dbUser = userService.findByUiId(pd);
			 logger.info("this AVATAR is"+AVATAR);
			long time = System.currentTimeMillis();			
			if(AVATAR != null && !AVATAR.isEmpty()){
				String avatarUrl = FileUploadUtil.upload(AVATAR, request, "avatar/sysuser", "sysuser" + time);
				dbUser.put("AVATAR", avatarUrl);
				 logger.info("this avatarUrl is"+avatarUrl);
			}

		  /*if(Jurisdiction.buttonJurisdiction(menuUrl, "edit")){*/
		  userService.editU(dbUser);
		  logger.info("this AVATAR has been changded and saved");
			//日志
			User user = getCurrentUser();
			sysLogBizService.saveLog(request, user,
					SysLogEnum.USER_UPLOAD_AVATAR.getName(), SysLogEnum.USER_UPLOAD_AVATAR.getContent());
		  /*}*/
		  //返回结果
			return listThisUsers(this.getPage());
		//  return ResponseMessageEnum.SUCCESS.toString();
	  }  
	  

}
