<%@ page import="java.util.Map" %>
<%@ page import="net.blf2.util.Consts" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.net.URLEncoder" %>
<%--
  Created by IntelliJ IDEA.
  User: blf2
  Date: 17-2-27
  Time: 下午6:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
  <%@include file="HtmlPart/bootstrapCDN.html" %>
  <%
    HttpSession httpSession = request.getSession();
    Map<String,Map<String,Object>> userInfoDetailMap = (Map<String, Map<String, Object>>)
            httpSession.getAttribute(Consts.USER_DETAIL_INFO_BY_USER_GRADE);
    String filePath = (String) httpSession.getAttribute(Consts.EXCEL_DOWNLOAD_PATH);
    filePath = URLEncoder.encode(filePath,"UTF-8");
  %>
</head>
<body>
<h2 align="centre">汇总表</h2>
<table class="table table-bordered" align="centre">
  <tr>
    <td>序号</td>
    <td>学号</td>
    <td>综合测评加减分</td>
    <td>合计</td>
  </tr>
  <%
    if(userInfoDetailMap == null){%>
      <h3>无数据</h3>
    <%}else{
      httpSession.removeAttribute(Consts.USER_DETAIL_INFO_BY_USER_GRADE);
      int index = 1;
      Set<String> keys = userInfoDetailMap.keySet();
      for(String key : keys){
        Map<String,Object>itermsMap = userInfoDetailMap.get(key);
        if(itermsMap != null){
        String str = "";
        for(Map.Entry<String,Object>entry : itermsMap.entrySet()){
          String iKey = entry.getKey();
          Object iValue = entry.getValue();
          if("_id".equals(iKey) || Consts.USER_SUM_SCORE.equals(iKey))
            continue;
          str += (iKey + " " + iValue+ "<br />");
        }
        Double sum = (Double) itermsMap.get(Consts.USER_SUM_SCORE);
      %>
      <tr>
        <td><%=index++%></td>
        <td><%=key%></td>
        <td><%=str%></td>
        <td><%=sum%></td>
      </tr>
    <%}}}
  %>
</table>
<h3>附件下载：</h3><a href="/resources/<%=filePath%>">点我下载</a>
</body>
</html>
