package com.fh.entity;

import com.fh.util.Const;
import com.fh.util.PageData;
import com.fh.util.Tools;

public class Page {

  private int showCount; //每页显示记录数

  private int totalPage; //总页数

  private int totalResult; //总记录数

  private int currentPage; //当前页

  private int currentResult; //当前记录起始索引

  private boolean entityOrField; //true:需要分页的地方，传入的参数就是Page实体；false:需要分页的地方，传入的参数所代表的实体拥有Page属性

  private String pageStr; //最终页面显示的底部翻页导航，详细见：getPageStr();

  private String ajaxPageStr; //弹出窗口页面显示的底部翻页导航，采用ajax方式刷新,详细见：getAjaxPageStr();

  private PageData pd = new PageData();
  
  private static final String STRING_TOP_JZTS = "try{top.jzts();}catch(e){}";

  private String pageInfo;

  public Page() {
    try {
      this.showCount = Integer.parseInt(Tools.readTxtFile(Const.PAGE));
    } catch (Exception e) {
      this.showCount = 15;
    }
  }

  public int getTotalPage() {
    if (totalResult % showCount == 0)
      totalPage = totalResult / showCount;
    else
      totalPage = totalResult / showCount + 1;
    return totalPage;
  }

  public void setTotalPage(int totalPage) {
    this.totalPage = totalPage;
  }

  public int getTotalResult() {
    return totalResult;
  }

  public void setTotalResult(int totalResult) {
    this.totalResult = totalResult;
  }

  public int getCurrentPage() {
    if (currentPage <= 0)
      currentPage = 1;
    if (currentPage > getTotalPage())
      currentPage = getTotalPage();
    return currentPage;
  }

  public void setCurrentPage(int currentPage) {
    this.currentPage = currentPage;
  }

  public String getPageInfo() {
    StringBuffer sb = new StringBuffer();
    if (totalResult > 0) {
      sb.append("	<ul>\n");
      //当前是第一页
      if (currentPage == 1) {
        //当总页数为1页时
        if (totalPage == 1) {
          sb.append("<ul class='pagination'>" + "<li class='disabled'><span>«</span></li>"
              + "<li class='active'><span>1</span></li>" + "<li class='disabled'><a>»</a></li>" + "</ul>");
        } else {
          sb.append("<ul class='pagination'>" + "<li class='disabled'><span>«</span></li>"
              + "<li class='active'><span>1</span></li>" + "<li><a href='" + (currentPage + 1) + "'>"
              + (currentPage + 1) + "</a></li>" + "<li><a href='" + totalPage + "' rel=\"next\">»</a></li>" + "</ul>");
        }
        //当前是最后一页
      } else if (currentPage == totalPage) {
        sb.append("<ul class='pagination'>" + "<li><a href='" + 1 + "'>«</a></li>" + "<li><a href='" + (currentPage - 1)
            + "'>" + (currentPage - 1) + "</a></li>" + "<li class='active'><span>" + currentPage + "</span></li>"
            + "<li class='disabled'><a rel='next'>»</a></li>" + "</ul>");
      }
      //当前非第一页也非最后一页
      else {
        sb.append("<ul class='pagination'>" + "<li><a href='" + 1 + "'>«</a></li>" + "<li><a href='" + (currentPage - 1)
            + "'>" + (currentPage - 1) + "</a></li>" + "<li class='active'><span>" + currentPage + "</span></li>"
            + "<li><a href='" + (currentPage + 1) + "'>" + (currentPage + 1) + "</a></li>" + "<li><a href='" + totalPage
            + "' rel='next'>»</a></li>" + "</ul>");
      }
    }
    pageStr = sb.toString();
    return pageStr;
  }

  public String getPageStr() {
    StringBuffer sb = new StringBuffer();
    if (totalResult > 0) {
      sb.append("	<ul>\n");
      if (currentPage == 1) {
        sb.append("	<li><a>共<font color=red>" + totalResult + "</font>条</a></li>\n");
/*        sb.append(
            "	<li><input type=\"number\" value=\"\" id=\"toGoPage\" style=\"width:50px;text-align:center;float:left\" placeholder=\"页码\"/></li>\n");
        sb.append(
            "	<li style=\"cursor:pointer;\"><a onclick=\"toTZ();\"  class=\"btn btn-mini btn-info\">跳转</a></li>\n");*/
        sb.append("	<li><a>首页</a></li>\n");
        sb.append("	<li><a>上页</a></li>\n");
      } else {
        sb.append("	<li><a>共<font color=red>" + totalResult + "</font>条</a></li>\n");
/*        sb.append(
            "	<li><input type=\"number\" value=\"\" id=\"toGoPage\" style=\"width:50px;text-align:center;float:left\" placeholder=\"页码\"/></li>\n");
        sb.append(
            "	<li style=\"cursor:pointer;\"><a onclick=\"toTZ();\"  class=\"btn btn-mini btn-info\">跳转</a></li>\n");*/
        sb.append("	<li style=\"cursor:pointer;\"><a onclick=\"nextPage(1)\">首页</a></li>\n");
        sb.append("	<li style=\"cursor:pointer;\"><a onclick=\"nextPage(" + (currentPage - 1) + ")\">上页</a></li>\n");
      }
      int showTag = 5;//分页标签显示数量
      int startTag = 1;
      if (currentPage > showTag) {
        startTag = currentPage - 1;
      }
      int endTag = startTag + showTag - 1;
      for (int i = startTag; i <= totalPage && i <= endTag; i++) {
        if (currentPage == i)
          sb.append("<li><a><font color='#808080'>" + i + "</font></a></li>\n");
        else
          sb.append("	<li style=\"cursor:pointer;\"><a onclick=\"nextPage(" + i + ")\">" + i + "</a></li>\n");
      }
      if (currentPage == totalPage) {
        sb.append("	<li><a>下页</a></li>\n");
        sb.append("	<li><a>尾页</a></li>\n");
      } else {
        sb.append("	<li style=\"cursor:pointer;\"><a onclick=\"nextPage(" + (currentPage + 1) + ")\">下页</a></li>\n");
        sb.append("	<li style=\"cursor:pointer;\"><a onclick=\"nextPage(" + totalPage + ")\">尾页</a></li>\n");
      }
      sb.append("	<li><a>第" + currentPage + "页</a></li>\n");
      sb.append("	<li><a>共" + totalPage + "页</a></li>\n");

/*      sb.append("	<li><select title='显示条数' style=\"width:55px;float:left;\" onchange=\"changeCount(this.value)\">\n");
      sb.append("	<option value='" + showCount + "'>" + showCount + "</option>\n");
      sb.append("	<option value='10'>10</option>\n");
      sb.append("	<option value='20'>20</option>\n");
      sb.append("	<option value='30'>30</option>\n");
      sb.append("	<option value='40'>40</option>\n");
      sb.append("	<option value='50'>50</option>\n");
      sb.append("	<option value='60'>60</option>\n");
      sb.append("	<option value='70'>70</option>\n");
      sb.append("	<option value='80'>80</option>\n");
      sb.append("	<option value='90'>90</option>\n");
      sb.append("	<option value='99'>99</option>\n");
      sb.append("	</select>\n");
      sb.append("	</li>\n");*/

      sb.append("</ul>\n");
      sb.append("<script type=\"text/javascript\">\n");

      //换页函数
      sb.append("function nextPage(page){");
      sb.append(STRING_TOP_JZTS);
      sb.append("	if(true && document.forms[0]){\n");
      sb.append("		var url = document.forms[0].getAttribute(\"action\");\n");
      sb.append(
          "		if(url.indexOf('?')>-1){url += \"&" + (entityOrField ? "currentPage" : "page.currentPage") + "=\";}\n");
      sb.append("		else{url += \"?" + (entityOrField ? "currentPage" : "page.currentPage") + "=\";}\n");
      sb.append(
          "		url = url + page + \"&" + (entityOrField ? "showCount" : "page.showCount") + "=" + showCount + "\";\n");
      sb.append("		document.forms[0].action = url;\n");
      sb.append("		document.forms[0].submit();\n");
      sb.append("	}else{\n");
      sb.append("		var url = document.location+'';\n");
      sb.append("		if(url.indexOf('?')>-1){\n");
      sb.append("			if(url.indexOf('currentPage')>-1){\n");
      sb.append("				var reg = /currentPage=\\d*/g;\n");
      sb.append("				url = url.replace(reg,'currentPage=');\n");
      sb.append("			}else{\n");
      sb.append("				url += \"&" + (entityOrField ? "currentPage" : "page.currentPage") + "=\";\n");
      sb.append("			}\n");
      sb.append("		}else{url += \"?" + (entityOrField ? "currentPage" : "page.currentPage") + "=\";}\n");
      sb.append(
          "		url = url + page + \"&" + (entityOrField ? "showCount" : "page.showCount") + "=" + showCount + "\";\n");
      sb.append("		document.location = url;\n");
      sb.append("	}\n");
      sb.append("}\n");

      //调整每页显示条数
      sb.append("function changeCount(value){");
      sb.append(STRING_TOP_JZTS);
      sb.append("	if(true && document.forms[0]){\n");
      sb.append("		var url = document.forms[0].getAttribute(\"action\");\n");
      sb.append(
          "		if(url.indexOf('?')>-1){url += \"&" + (entityOrField ? "currentPage" : "page.currentPage") + "=\";}\n");
      sb.append("		else{url += \"?" + (entityOrField ? "currentPage" : "page.currentPage") + "=\";}\n");
      sb.append("		url = url + \"1&" + (entityOrField ? "showCount" : "page.showCount") + "=\"+value;\n");
      sb.append("		document.forms[0].action = url;\n");
      sb.append("		document.forms[0].submit();\n");
      sb.append("	}else{\n");
      sb.append("		var url = document.location+'';\n");
      sb.append("		if(url.indexOf('?')>-1){\n");
      sb.append("			if(url.indexOf('currentPage')>-1){\n");
      sb.append("				var reg = /currentPage=\\d*/g;\n");
      sb.append("				url = url.replace(reg,'currentPage=');\n");
      sb.append("			}else{\n");
      sb.append("				url += \"1&" + (entityOrField ? "currentPage" : "page.currentPage") + "=\";\n");
      sb.append("			}\n");
      sb.append("		}else{url += \"?" + (entityOrField ? "currentPage" : "page.currentPage") + "=\";}\n");
      sb.append("		url = url + \"&" + (entityOrField ? "showCount" : "page.showCount") + "=\"+value;\n");
      sb.append("		document.location = url;\n");
      sb.append("	}\n");
      sb.append("}\n");

      //跳转函数 
      sb.append("function toTZ(){");
      sb.append("var toPaggeVlue = document.getElementById(\"toGoPage\").value;");
      sb.append("if(toPaggeVlue == ''){document.getElementById(\"toGoPage\").value=1;return;}");
      sb.append("if(isNaN(Number(toPaggeVlue))){document.getElementById(\"toGoPage\").value=1;return;}");
      sb.append("nextPage(toPaggeVlue);");
      sb.append("}\n");
      sb.append("</script>\n");
    }
    pageStr = sb.toString();
    return pageStr;
  }
  public String getAjaxPageStr() {
    StringBuffer sb = new StringBuffer();
    if(totalResult>0){
      sb.append(" <ul>\n");
      if(currentPage==1){
        sb.append(" <li><a>共<font color=red>"+totalResult+"</font>条</a></li>\n");
        sb.append(" <li><input type=\"number\" value=\"\" id=\"toGoPage\" style=\"width:50px;text-align:center;float:left\" placeholder=\"页码\"/></li>\n");
        sb.append(" <li style=\"cursor:pointer;\"><a onclick=\"toTZ();\"  class=\"btn btn-mini btn-info\">跳转</a></li>\n");
        sb.append(" <li><a>首页</a></li>\n");
        sb.append(" <li><a>上页</a></li>\n");
      }else{
        sb.append(" <li><a>共<font color=red>"+totalResult+"</font>条</a></li>\n");
        sb.append(" <li><input type=\"number\" value=\"\" id=\"toGoPage\" style=\"width:50px;text-align:center;float:left\" placeholder=\"页码\"/></li>\n");
        sb.append(" <li style=\"cursor:pointer;\"><a onclick=\"toTZ();\"  class=\"btn btn-mini btn-info\">跳转</a></li>\n");
        sb.append(" <li style=\"cursor:pointer;\"><a onclick=\"nextPage(1)\">首页</a></li>\n");
        sb.append(" <li style=\"cursor:pointer;\"><a onclick=\"nextPage("+(currentPage-1)+")\">上页</a></li>\n");
      }
      int showTag = 5;//分页标签显示数量
      int startTag = 1;
      if(currentPage>showTag){
        startTag = currentPage-1;
      }
      int endTag = startTag+showTag-1;
      for(int i=startTag; i<=totalPage && i<=endTag; i++){
        if(currentPage==i)
          sb.append("<li><a><font color='#808080'>"+i+"</font></a></li>\n");
        else
          sb.append(" <li style=\"cursor:pointer;\"><a onclick=\"nextPage("+i+")\">"+i+"</a></li>\n");
      }
      if(currentPage==totalPage){
        sb.append(" <li><a>下页</a></li>\n");
        sb.append(" <li><a>尾页</a></li>\n");
      }else{
        sb.append(" <li style=\"cursor:pointer;\"><a onclick=\"nextPage("+(currentPage+1)+")\">下页</a></li>\n");
        sb.append(" <li style=\"cursor:pointer;\"><a onclick=\"nextPage("+totalPage+")\">尾页</a></li>\n");
      }
      sb.append(" <li><a>第"+currentPage+"页</a></li>\n");
      sb.append(" <li><a>共"+totalPage+"页</a></li>\n");   
      sb.append("</ul>\n");
      
      sb.append("<script type=\"text/javascript\">\n");
      //换页函数
      sb.append("function nextPage(page){");
      sb.append(STRING_TOP_JZTS);
      sb.append(" var reqUrl;");
      sb.append(" var condition;");
      sb.append(" if(true && document.forms[0]){\n");
      sb.append("   var url = document.forms[0].getAttribute(\"action\");\n");
      sb.append(
          "   if(url.indexOf('?')>-1){url += \"&" + (entityOrField ? "currentPage" : "page.currentPage") + "=\";}\n");
      sb.append("   else{url += \"?" + (entityOrField ? "currentPage" : "page.currentPage") + "=\";}\n");
      sb.append(
          "   url = url + page + \"&" + (entityOrField ? "showCount" : "page.showCount") + "=" + showCount + "\";\n");
      sb.append("   document.forms[0].action = url;\n");
      sb.append("  reqUrl = url;\n");
      sb.append("  condition = $(document.forms[0]).serialize();\n");
      sb.append(" }else{\n");
      sb.append("   var url = document.location+'';\n");
      sb.append("   if(url.indexOf('?')>-1){\n");
      sb.append("     if(url.indexOf('currentPage')>-1){\n");
      sb.append("       var reg = /currentPage=\\d*/g;\n");
      sb.append("       url = url.replace(reg,'currentPage=');\n");
      sb.append("     }else{\n");
      sb.append("       url += \"&" + (entityOrField ? "currentPage" : "page.currentPage") + "=\";\n");
      sb.append("     }\n");
      sb.append("   }else{url += \"?" + (entityOrField ? "currentPage" : "page.currentPage") + "=\";}\n");
      sb.append(
          "   url = url + page + \"&" + (entityOrField ? "showCount" : "page.showCount") + "=" + showCount + "\";\n");
      sb.append("   reqUrl = url;\n");
      sb.append(" }\n");
      sb.append(" ajaxNext(reqUrl,condition);\n");
//      sb.append("   $.ajax({\n");
//      sb.append("  type: 'POST',\n");
//      sb.append("  url: reqUrl,\n");
//      sb.append("   data: condition,\n");
//      sb.append("  success: function(msg){\n");
//      sb.append("  $('.bootbox-body').html(msg);\n");
//      sb.append("  }\n");
//      sb.append("  });\n");  
      sb.append("}\n");

     

      //跳转函数 
      sb.append("function toTZ(){");
      sb.append("var toPaggeVlue = document.getElementById(\"toGoPage\").value;");
      sb.append("if(toPaggeVlue == ''){document.getElementById(\"toGoPage\").value=1;return;}");
      sb.append("if(isNaN(Number(toPaggeVlue))){document.getElementById(\"toGoPage\").value=1;return;}");
      sb.append("nextPage(toPaggeVlue);");
      sb.append("}\n");
      sb.append("</script>\n");
    }
    pageStr = sb.toString();
    return pageStr;
  }
 /*
  * 同一个页面有两个分页条时，会产生冲突，所以加了这个方法
  */
  public String getAjaxPageStr2() {
    StringBuffer sb = new StringBuffer();
    if(totalResult>0){
      sb.append(" <ul>\n");
      if(currentPage==1){
        sb.append(" <li><a>共<font color=red>"+totalResult+"</font>条</a></li>\n");
        sb.append(" <li><input type=\"number\" value=\"\" id=\"toGoPage2\" style=\"width:50px;text-align:center;float:left\" placeholder=\"页码\"/></li>\n");
        sb.append(" <li style=\"cursor:pointer;\"><a onclick=\"toTZ2();\"  class=\"btn btn-mini btn-info\">跳转</a></li>\n");
        sb.append(" <li><a>首页</a></li>\n");
        sb.append(" <li><a>上页</a></li>\n");
      }else{
        sb.append(" <li><a>共<font color=red>"+totalResult+"</font>条</a></li>\n");
        sb.append(" <li><input type=\"number\" value=\"\" id=\"toGoPage2\" style=\"width:50px;text-align:center;float:left\" placeholder=\"页码\"/></li>\n");
        sb.append(" <li style=\"cursor:pointer;\"><a onclick=\"toTZ2();\"  class=\"btn btn-mini btn-info\">跳转</a></li>\n");
        sb.append(" <li style=\"cursor:pointer;\"><a onclick=\"nextPage2(1)\">首页</a></li>\n");
        sb.append(" <li style=\"cursor:pointer;\"><a onclick=\"nextPage2("+(currentPage-1)+")\">上页</a></li>\n");
      }
      int showTag = 5;//分页标签显示数量
      int startTag = 1;
      if(currentPage>showTag){
        startTag = currentPage-1;
      }
      int endTag = startTag+showTag-1;
      for(int i=startTag; i<=totalPage && i<=endTag; i++){
        if(currentPage==i)
          sb.append("<li><a><font color='#808080'>"+i+"</font></a></li>\n");
        else
          sb.append(" <li style=\"cursor:pointer;\"><a onclick=\"nextPage2("+i+")\">"+i+"</a></li>\n");
      }
      if(currentPage==totalPage){
        sb.append(" <li><a>下页</a></li>\n");
        sb.append(" <li><a>尾页</a></li>\n");
      }else{
        sb.append(" <li style=\"cursor:pointer;\"><a onclick=\"nextPage2("+(currentPage+1)+")\">下页</a></li>\n");
        sb.append(" <li style=\"cursor:pointer;\"><a onclick=\"nextPage2("+totalPage+")\">尾页</a></li>\n");
      }
      sb.append(" <li><a>第"+currentPage+"页</a></li>\n");
      sb.append(" <li><a>共"+totalPage+"页</a></li>\n");   
      sb.append("</ul>\n");
      
      sb.append("<script type=\"text/javascript\">\n");
      //换页函数
      sb.append("function nextPage2(page){");
      sb.append(STRING_TOP_JZTS);
      sb.append(" var reqUrl;");
      sb.append(" var condition;");
      sb.append(" if(true && document.forms[0]){\n");
      sb.append("   var url = document.forms[0].getAttribute(\"action\");\n");
      sb.append(
          "   if(url.indexOf('?')>-1){url += \"&" + (entityOrField ? "currentPage" : "page.currentPage") + "=\";}\n");
      sb.append("   else{url += \"?" + (entityOrField ? "currentPage" : "page.currentPage") + "=\";}\n");
      sb.append(
          "   url = url + page + \"&" + (entityOrField ? "showCount" : "page.showCount") + "=" + showCount + "\";\n");
      sb.append("   document.forms[0].action = url;\n");
      sb.append("  reqUrl = url;\n");
      sb.append("  condition = $(document.forms[0]).serialize();\n");
      sb.append(" }else{\n");
      sb.append("   var url = document.location+'';\n");
      sb.append("   if(url.indexOf('?')>-1){\n");
      sb.append("     if(url.indexOf('currentPage')>-1){\n");
      sb.append("       var reg = /currentPage=\\d*/g;\n");
      sb.append("       url = url.replace(reg,'currentPage=');\n");
      sb.append("     }else{\n");
      sb.append("       url += \"&" + (entityOrField ? "currentPage" : "page.currentPage") + "=\";\n");
      sb.append("     }\n");
      sb.append("   }else{url += \"?" + (entityOrField ? "currentPage" : "page.currentPage") + "=\";}\n");
      sb.append(
          "   url = url + page + \"&" + (entityOrField ? "showCount" : "page.showCount") + "=" + showCount + "\";\n");
      sb.append("   reqUrl = url;\n");
      sb.append(" }\n");
      sb.append(" ajaxNext2(reqUrl,condition);\n");
//      sb.append("   $.ajax({\n");
//      sb.append("  type: 'POST',\n");
//      sb.append("  url: reqUrl,\n");
//      sb.append("   data: condition,\n");
//      sb.append("  success: function(msg){\n");
//      sb.append("  $('.bootbox-body').html(msg);\n");
//      sb.append("  }\n");
//      sb.append("  });\n");  
      sb.append("}\n");

     

      //跳转函数 
      sb.append("function toTZ2(){");
      sb.append("var toPaggeVlue = document.getElementById(\"toGoPage2\").value;");
      sb.append("if(toPaggeVlue == ''){document.getElementById(\"toGoPage2\").value=1;return;}");
      sb.append("if(isNaN(Number(toPaggeVlue))){document.getElementById(\"toGoPage2\").value=1;return;}");
      sb.append("nextPage2(toPaggeVlue);");
      sb.append("}\n");
      sb.append("</script>\n");
    }
    pageStr = sb.toString();
    return pageStr;
  }
  public void setPageStr(String pageStr) {
    this.pageStr = pageStr;
  }

  public int getShowCount() {
    return showCount;
  }

  public void setShowCount(int showCount) {

    this.showCount = showCount;
  }

  public int getCurrentResult() {
    currentResult = (getCurrentPage() - 1) * getShowCount();
    if (currentResult < 0)
      currentResult = 0;
    return currentResult;
  }

  public void setCurrentResult(int currentResult) {
    this.currentResult = currentResult;
  }

  public boolean isEntityOrField() {
    return entityOrField;
  }

  public void setEntityOrField(boolean entityOrField) {
    this.entityOrField = entityOrField;
  }

  public PageData getPd() {
    return pd;
  }

  public void setPd(PageData pd) {
    this.pd = pd;
  }

}
