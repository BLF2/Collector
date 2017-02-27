<%@ page import="java.util.List" %>
<%@ page import="net.blf2.util.Consts" %>
<%--
  Created by IntelliJ IDEA.
  User: blf2
  Date: 17-2-20
  Time: 下午3:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <%@include file="HtmlPart/bootstrapCDN.html"%>
</head>
<body>
<a href="/User/toUpdateScore"><button class="btn btn-primary" type="button">填写或修改本人加减分项</button></a>
<a href=""><button class="btn btn-primary" type="button">查看和修改本班信息</button></a>
<a href=""><button class="btn btn-primary" type="button">查看本班所有信息</button></a>
<form action="/User/generateValidateCode" method="post">
    <select name="userNum">
        <option value="">请选择专业班级</option>
        <%
            HttpSession httpSession = request.getSession();
            String validateCode = (String) httpSession.getAttribute(Consts.VALIDATE_CODE);
            List<String> majorNameGradeNumList = (List<String>) httpSession.getAttribute(Consts.MAJORNAME_GRADE_NUM_LIST);
            if (majorNameGradeNumList != null){
            httpSession.removeAttribute(Consts.MAJORNAME_GRADE_NUM_LIST);
            for (String str : majorNameGradeNumList){
        %>
        <option value="<%=str%>"><%=str%></option>
        <%}}%>

    </select>
    <button type="submit" class="btn btn-default">点击获取验证码</button>
    <%
        if(validateCode != null){
            httpSession.removeAttribute(Consts.VALIDATE_CODE);
    %>
    <h3>验证码为：</h3><h2><%=validateCode%></h2><hr />
    <h3 style="color: red">请在本日24点前使用，本验证码会在24点后失效。</h3>
    <%}
    %>
</form>
</body>
</html>
