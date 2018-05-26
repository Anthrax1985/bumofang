package com.fh.util;

import org.springframework.context.ApplicationContext;

public class Const
{
  public static final String SESSION_SECURITY_CODE = "sessionSecCode";
  public static final String SESSION_USER = "sessionUser";
  public static final String SESSION_ROLE_RIGHTS = "sessionRoleRights";
  public static final String SESSION_menuList = "menuList";
  public static final String SESSION_allmenuList = "allmenuList";
  public static final String SESSION_QX = "QX";
  public static final String SESSION_userpds = "userpds";
  public static final String SESSION_USERROL = "USERROL";
  public static final String SESSION_USERNAME = "USERNAME";
  public static final String SESSION_NAME = "NAME";
  public static final String TRUE = "T";
  public static final String FALSE = "F";
  public static final String LOGIN = "/login_toLogin.do";
  public static final String SYSNAME = "admin/config/SYSNAME.txt";
  public static final String PAGE = "admin/config/PAGE.txt";
  public static final String EMAIL = "admin/config/EMAIL.txt";
  public static final String SMS_TEMPLATE = "admin/config/SMS_TEMPLATE.txt";
  public static final String EXPRESS_COMPANY = "admin/config/EXPRESS_COMPANY.txt";
  public static final String APP_VERSION = "admin/config/APP_VERSION.txt";
  public static final String BLINK_LICENSE = "admin/config/BLINK_LICENSE.txt";
  public static final String SMS1 = "admin/config/SMS1.txt";
  public static final String SMS2 = "admin/config/SMS2.txt";
  public static final String FWATERM = "admin/config/FWATERM.txt";
  public static final String IWATERM = "admin/config/IWATERM.txt";
  public static final String WEIXIN = "admin/config/WEIXIN.txt";
  public static final String FILEPATHIMG = "uploadFiles/uploadImgs/";
  public static final String FILEPATHFILE = "uploadFiles/file/";
  public static final String FILEPATHTWODIMENSIONCODE = "uploadFiles/twoDimensionCode/";
  public static final String NO_INTERCEPTOR_PATH = ".*/((login)|(logout)|(code)|(app)|(upload)|(weixin)|(static)|(main)|(websocket)).*";
  public static ApplicationContext WEB_APP_CONTEXT = null;
  
  public static String PATH_TOMCAT_BIN = null;
  public static String PATH_LOG_DIR = null;

  public static final String[] APP_REGISTERED_PARAM_ARRAY = { "countries", "uname", "passwd", "title", "full_name", "company_name", "countries_code", "area_code", "telephone", "mobile" };
  public static final String[] APP_REGISTERED_VALUE_ARRAY = { "国籍", "邮箱帐号", "密码", "称谓", "名称", "公司名称", "国家编号", "区号", "电话", "手机号" };

  public static final String[] APP_GETAPPUSER_PARAM_ARRAY = { "USERNAME" };
  public static final String[] APP_GETAPPUSER_VALUE_ARRAY = { "用户名" };
}