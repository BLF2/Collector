<%--
  Created by IntelliJ IDEA.
  User: blf2
  Date: 17-2-20
  Time: 下午4:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
  <%@include file="HtmlPart/bootstrapCDN.html"%>
</head>
<body>
<form>
  <div class="form-group">
    <label for="majorName">专业名称(如：会计)</label>
    <input type="text" class="form-control" id="majorName" name="majorName">
  </div>
  <div class="form-group">
    <label for="classGrade">专业年级(如：2015)</label>
    <input type="text" class="form-control" id="classGrade" name="classGrade">
  </div>
  <div class="form-group">
    <label for="classNum">专业班级(如03)</label>
    <input type="text" class="form-control" id="classNum" name="classNum">
  </div>
  <div class="form-group">
    <label for="classNote">班级备注</label>
    <textarea class="form-control" id="classNote" name="classNote" />
  </div>
  <button type="submit" class="btn btn-default">提交</button>
</form>
<a href=""><button type="button" class="btn btn-primary">返回</button></a>
</body>
</html>
