<%@ page import="net.blf2.util.Consts" %>
<%--
  Created by IntelliJ IDEA.
  User: blf2
  Date: 17-2-16
  Time: 下午3:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
  <%@ include file="HtmlPart/bootstrapCDN.html"%>
</head>
<body>
<!-- 输入学号，生成邀请码！！！-->
<form action="/User/generateValidateCode" method="post">
  <label for="userNum">请输入学号或班级名称（如会计201501）</label>
  <input id="userNum" name="userNum" />
  <button type="submit">点击获取邀请码</button>
</form>
<%
  HttpSession httpSession = request.getSession();
  String validateCode = (String)httpSession.getAttribute(Consts.VALIDATE_CODE);
  if(validateCode != null){
    httpSession.removeAttribute(Consts.VALIDATE_CODE);
%>
    <h3>验证码为：</h3><h2><%=validateCode%></h2><hr />
    <h3 style="color: red">请在本日24点前注册，本验证码会在24点后失效。</h3>
  <%}
%>
</body>
</html>
