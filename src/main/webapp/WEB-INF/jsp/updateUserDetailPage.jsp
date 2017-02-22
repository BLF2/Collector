<%--
  Created by IntelliJ IDEA.
  User: blf2
  Date: 17-2-22
  Time: 下午3:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
  <%@include file="HtmlPart/bootstrapCDN.html"%>
</head>
<%

%>
<body>
<div class="row">
  <div class="col-md-4"></div>
  <div class="col-md-4">
    <h2>学生信息提交</h2>
    <form action="/User/submitInfo" method="post">
      <div class="form-group">
        <label for="userNum">学号：</label>
        <input type="text" class="form-control" id="userNum" name="userNum" value="<%=%>"/>
      </div>
      <div class="form-group">
        <label for="userPswd">密码：</label>
        <input type="password" class="form-control" id="userPswd" name="userPswd"/>
      </div>
      <div class="form-group">
        <label for="userPswd1">确认密码：</label>
        <input type="password" class="form-control" id="userPswd1" name="userPswd1" onblur="checkPswd()"/>
        <div id="infoForCheckPswd" style="color:red"></div>
      </div>
      <div class="form-group">
        <label for="userPhone">手机号：（方便班长联系到你）</label>
        <input type="text" class="form-control" id="userPhone" name="userPhone"/>
      </div>
      <div class="form-group">
        <label for="userGrade">专业班级：</label>
        <select id="userGrade" name="userGrade">
          <option value="0">请选择专业班级</option>
          <%
            HttpSession httpSession = request.getSession();
            Map<Integer,String> map = (Map<Integer,String>)httpSession.getAttribute(Consts.USER_GRADE_NAME_VALUE);
            if(map != null){
              httpSession.removeAttribute(Consts.USER_GRADE_NAME_VALUE);
              for(Map.Entry<Integer,String>entry : map.entrySet()){
          %>
          <option value="<%=entry.getKey()%>"><%=entry.getValue()%></option>
          <%}}
          %>
        </select>
      </div>
      <div class="form-group">
        <label for="userNote">备注：（选填）</label>
        <textarea class="form-control" id="userNote" name="userNote"></textarea>
      </div>
      <hr />
      <h3>综合测评加减分统计</h3>
      <div id="itemInfo">

      </div>
      <button class="btn btn-primary" type="button" onclick="addNewItem()">添加一项</button>
      <button type="submit" class="btn btn-primary">提交</button>
    </form>
  </div>
  <div class="col-md-4"></div>
</div>
</body>
</html>
