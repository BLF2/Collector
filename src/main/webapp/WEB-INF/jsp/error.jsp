<%@ page import="net.blf2.util.Consts" %>
<%--
  Created by IntelliJ IDEA.
  User: blf2
  Date: 17-2-13
  Time: 下午4:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<%
  HttpSession httpSession = request.getSession();
  String errMsg = (String)httpSession.getAttribute(Consts.WEB_ERROR_MWSSAGE);
  if(errMsg != null){
    httpSession.removeAttribute(Consts.WEB_ERROR_MWSSAGE);
%>
<h1><%=errMsg%></h1>
  <%}else{%>
    <H1>未知错误，请联系管理员！</H1>
  <%}
%>
</body>
</html>
