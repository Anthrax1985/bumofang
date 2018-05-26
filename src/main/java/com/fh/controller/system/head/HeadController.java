package com.fh.controller.system.head;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fh.common.logic.FileConfigCacher;
import com.fh.common.model.ResponseMessageEnum;
import com.fh.controller.base.BaseController;
import com.fh.service.system.appuser.AppuserService;
import com.fh.service.system.user.UserService;
import com.fh.util.AppUtil;
import com.fh.util.Const;
import com.fh.util.PageData;
import com.fh.util.SmsUtil;
import com.fh.util.Tools;
import com.fh.util.Watermark;
import com.fh.util.mail.SimpleMailSender;

@Controller
@RequestMapping({"/head"})
public class HeadController extends BaseController
{

  @Resource(name="userService")
  private UserService userService;

  @Resource(name="appuserService")
  private AppuserService appuserService;

  @RequestMapping({"/getUname"})
  @ResponseBody
  public Object getList()
  {
    PageData pd = new PageData();
    Map map = new HashMap();
    try {
      pd = getPageData();
      List pdList = new ArrayList();

      Subject currentUser = SecurityUtils.getSubject();
      Session session = currentUser.getSession();

      PageData pds = new PageData();
      pds = (PageData)session.getAttribute("userpds");

      if (pds == null) {
        String USERNAME = session.getAttribute("USERNAME").toString();
        pd.put("USERNAME", USERNAME);
        pds = this.userService.findByUNForVo(pd);
        session.setAttribute("userpds", pds);
      }

      pdList.add(pds);
      map.put("list", pdList);
    } catch (Exception e) {
      this.logger.error(e.toString(), e);
    } finally {
      logAfter(this.logger);
    }
    return AppUtil.returnObject(pd, map);
  }

  @RequestMapping({"/setSKIN"})
  public void setSKIN(PrintWriter out)
  {
    PageData pd = new PageData();
    try {
      pd = getPageData();

      Subject currentUser = SecurityUtils.getSubject();
      Session session = currentUser.getSession();

      String USERNAME = session.getAttribute("USERNAME").toString();
      pd.put("USERNAME", USERNAME);
      this.userService.setSKIN(pd);
      session.removeAttribute("userpds");
      session.removeAttribute("USERROL");
      out.write("success");
      out.close();
    } catch (Exception e) {
      this.logger.error(e.toString(), e);
    }
  }

  @RequestMapping({"/editEmail"})
  public ModelAndView editEmail()
    throws Exception
  {
    ModelAndView mv = getModelAndView();
    PageData pd = new PageData();
    pd = getPageData();
    mv.setViewName("system/head/edit_email");
    mv.addObject("pd", pd);
    return mv;
  }

  @RequestMapping({"/goSendSms"})
  public ModelAndView goSendSms()
    throws Exception
  {
    ModelAndView mv = getModelAndView();
    PageData pd = new PageData();
    pd = getPageData();
    mv.setViewName("system/head/send_sms");
    mv.addObject("pd", pd);
    return mv;
  }

  @RequestMapping({"/sendSms"})
  @ResponseBody
  public Object sendSms()
  {
    PageData pd = new PageData();
    pd = getPageData();
    Map map = new HashMap();
    String msg = "ok";
    int count = 0;
    int zcount = 0;

    List pdList = new ArrayList();

    String PHONEs = pd.getString("PHONE");
    String CONTENT = pd.getString("CONTENT");
    String isAll = pd.getString("isAll");
    String TYPE = pd.getString("TYPE");
    String fmsg = pd.getString("fmsg");

    if ("yes".endsWith(isAll)) {
      try {
        List userList = new ArrayList();

        userList = "appuser".equals(fmsg) ? this.appuserService.listAllUser(pd) : this.userService.listAllUser(pd);

        zcount = userList.size();
        try {
          for (int i = 0; i < userList.size(); i++) {
            if (Tools.checkMobileNumber(((PageData)userList.get(i)).getString("PHONE"))) {
              if ("1".equals(TYPE))
                SmsUtil.sendSms1(((PageData)userList.get(i)).getString("PHONE"), CONTENT);
              else {
                SmsUtil.sendSms2(((PageData)userList.get(i)).getString("PHONE"), CONTENT);
              }
              count++;
            }

          }

          msg = "ok";
        } catch (Exception e) {
          msg = "error";
        }
      }
      catch (Exception e) {
        msg = "error";
      }
    } else {
      PHONEs = PHONEs.replace("；", ";");
      PHONEs = PHONEs.replace(" ", "");
      String[] arrTITLE = PHONEs.split(";");
      zcount = arrTITLE.length;
      try {
        for (int i = 0; i < arrTITLE.length; i++) {
          if (Tools.checkMobileNumber(arrTITLE[i])) {
            if ("1".equals(TYPE))
              SmsUtil.sendSms1(arrTITLE[i], CONTENT);
            else {
              SmsUtil.sendSms2(arrTITLE[i], CONTENT);
            }
            count++;
          }

        }

        msg = "ok";
      } catch (Exception e) {
        msg = "error";
      }
    }
    pd.put("msg", msg);
    pd.put("count", Integer.valueOf(count));
    pd.put("ecount", Integer.valueOf(zcount - count));
    pdList.add(pd);
    map.put("list", pdList);
    return AppUtil.returnObject(pd, map);
  }

  @RequestMapping({"/goSendEmail"})
  public ModelAndView goSendEmail()
    throws Exception
  {
    ModelAndView mv = getModelAndView();
    PageData pd = new PageData();
    pd = getPageData();
    mv.setViewName("system/head/send_email");
    mv.addObject("pd", pd);
    return mv;
  }

  @RequestMapping({"/sendEmail"})
  @ResponseBody
  public Object sendEmail()
  {
    PageData pd = new PageData();
    pd = getPageData();
    Map map = new HashMap();
    String msg = "ok";
    int count = 0;
    int zcount = 0;

    String strEMAIL = Tools.readTxtFile("admin/config/EMAIL.txt");

    List pdList = new ArrayList();

    String toEMAIL = pd.getString("EMAIL");
    String TITLE = pd.getString("TITLE");
    String CONTENT = pd.getString("CONTENT");
    String TYPE = pd.getString("TYPE");
    String isAll = pd.getString("isAll");

    String fmsg = pd.getString("fmsg");

    if ((strEMAIL != null) && (!"".equals(strEMAIL))) {
      String[] strEM = strEMAIL.split(",fh,");
      if (strEM.length == 4) {
        if ("yes".endsWith(isAll)) {
          try {
            List userList = new ArrayList();

            userList = "appuser".equals(fmsg) ? this.appuserService.listAllUser(pd) : this.userService.listAllUser(pd);

            zcount = userList.size();
            try {
              for (int i = 0; i < userList.size(); i++) {
                if (Tools.checkEmail(((PageData)userList.get(i)).getString("EMAIL"))) {
                  SimpleMailSender.sendEmail(strEM[0], strEM[1], strEM[2], strEM[3], ((PageData)userList.get(i)).getString("EMAIL"), TITLE, CONTENT, TYPE);
                  count++;
                }

              }

              msg = "ok";
            } catch (Exception e) {
              msg = "error";
            }
          }
          catch (Exception e) {
            msg = "error";
          }
        } else {
          toEMAIL = toEMAIL.replace("；", ";");
          toEMAIL = toEMAIL.replace(" ", "");
          String[] arrTITLE = toEMAIL.split(";");
          zcount = arrTITLE.length;
          try {
            for (int i = 0; i < arrTITLE.length; i++) {
              if (Tools.checkEmail(arrTITLE[i])) {
                SimpleMailSender.sendEmail(strEM[0], strEM[1], strEM[2], strEM[3], arrTITLE[i], TITLE, CONTENT, TYPE);
                count++;
              }

            }

            msg = "ok";
          } catch (Exception e) {
            msg = "error";
          }
        }
      }
      else msg = "error"; 
    }
    else
    {
      msg = "error";
    }
    pd.put("msg", msg);
    pd.put("count", Integer.valueOf(count));
    pd.put("ecount", Integer.valueOf(zcount - count));
    pdList.add(pd);
    map.put("list", pdList);
    return AppUtil.returnObject(pd, map);
  }

  @RequestMapping({"/goSystem"})
  public ModelAndView goEditConfig()
    throws Exception
  {
    ModelAndView mv = getModelAndView();
    PageData pd = new PageData();
    pd = getPageData();
    pd.put("YSYNAME", FileConfigCacher.read(Const.SYSNAME));
    pd.put("COUNTPAGE", FileConfigCacher.read(Const.PAGE));
    pd.put("SMS_TEMPLATE", FileConfigCacher.read(Const.SMS_TEMPLATE));
    pd.put("EXPRESS_COMPANY", FileConfigCacher.read(Const.EXPRESS_COMPANY));
    pd.put("APP_VERSION", FileConfigCacher.read(Const.APP_VERSION));
    pd.put("BLINK_LICENSE", FileConfigCacher.read(Const.BLINK_LICENSE));

    mv.setViewName("system/head/sys_edit");
    mv.addObject("pd", pd);

    return mv;
  }

  @RequestMapping({"/saveSys"})
  public ModelAndView saveSys()
    throws Exception
  {
    ModelAndView mv = getModelAndView();
    PageData pd = new PageData();
    pd = getPageData();
    FileConfigCacher.write(Const.SYSNAME, pd.getString("YSYNAME"));
    FileConfigCacher.write(Const.PAGE, pd.getString("COUNTPAGE"));
    FileConfigCacher.write(Const.SMS_TEMPLATE, pd.getString("SMS_TEMPLATE"));
    FileConfigCacher.write(Const.EXPRESS_COMPANY, pd.getString("EXPRESS_COMPANY"));
    FileConfigCacher.write(Const.APP_VERSION, pd.getString("APP_VERSION"));
    FileConfigCacher.write(Const.BLINK_LICENSE, pd.getString("BLINK_LICENSE"));

    mv.addObject("msg", "OK");
    mv.setViewName("save_result");
    return mv;
  }

  @RequestMapping({"/saveSys2"})
  public ModelAndView saveSys2()
    throws Exception
  {
    ModelAndView mv = getModelAndView();
    PageData pd = new PageData();
    pd = getPageData();
    Tools.writeFile("admin/config/FWATERM.txt", pd.getString("isCheck1") + ",fh," + pd.getString("fcontent") + ",fh," + pd.getString("fontSize") + ",fh," + pd.getString("fontX") + ",fh," + pd.getString("fontY"));
    Tools.writeFile("admin/config/IWATERM.txt", pd.getString("isCheck2") + ",fh," + pd.getString("imgUrl") + ",fh," + pd.getString("imgX") + ",fh," + pd.getString("imgY"));
    Watermark.fushValue();
    mv.addObject("msg", "OK");
    mv.setViewName("save_result");
    return mv;
  }

  @RequestMapping({"/saveSys3"})
  public ModelAndView saveSys3()
    throws Exception
  {
    ModelAndView mv = getModelAndView();
    PageData pd = new PageData();
    pd = getPageData();
    Tools.writeFile("admin/config/WEIXIN.txt", pd.getString("Token"));
    mv.addObject("msg", "OK");
    mv.setViewName("save_result");
    return mv;
  }

  @RequestMapping({"/goProductCode"})
  public ModelAndView goProductCode()
    throws Exception
  {
    ModelAndView mv = getModelAndView();
    mv.setViewName("system/head/productCode");
    return mv;
  }
  
  @RequestMapping(value = { "/rest/saveSys" }, method = { RequestMethod.POST }, produces = {
	JSON_UTF8 })
	@ResponseBody
	public String restSaveSys(@RequestBody Map<String, String> map) throws Exception {
		PageData pd = new PageData();
		pd.putAll(map);
		FileConfigCacher.write(Const.SYSNAME, pd.getString("YSYNAME"));
		FileConfigCacher.write(Const.PAGE, pd.getString("COUNTPAGE"));
		FileConfigCacher.write(Const.SMS_TEMPLATE, pd.getString("SMS_TEMPLATE"));
		FileConfigCacher.write(Const.EXPRESS_COMPANY, pd.getString("EXPRESS_COMPANY"));
		FileConfigCacher.write(Const.APP_VERSION, pd.getString("APP_VERSION"));
		FileConfigCacher.write(Const.BLINK_LICENSE, pd.getString("BLINK_LICENSE"));
		
		return ResponseMessageEnum.SUCCESS.toString();
	}
}