<%--
  Created by IntelliJ IDEA.
  User: blf2
  Date: 17-2-13
  Time: 下午2:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>班长注册</title>
  <%@ include file="HtmlPart/bootstrapCDN.html" %>
</head>
<body>
<div class="row">
<div class="col-md-4"></div>
<div class="col-md-4">
  <h2>班长注册</h2>
  <form action="/User/registerMonitor" method="post">
    <div class="form-group">
      <label for="userNum">学号</label>
      <input type="text" class="form-control" id="userNum" name="userNum"/>
      <div id="isHasExistForUserNum"></div>
    </div>
    <div class="form-group">
      <label for="userPswd">登陆密码</label>
      <input type="password" class="form-control" id="userPswd" name="userPswd" />
    </div>
    <div class="form-group">
      <label for="userPswd1">确认密码</label>
      <input type="password" class="form-control" id="userPswd1" name="userPswd1" onblur="checkPassword()" onfocus="clearText()"/>
      <span><div id="isSameAsUserPawd" style="color: red"></div></span>
    </div>
    <div class="form-group">
      <label for="userPhone">手机号</label>
      <input type="text" class="form-control" id="userPhone" name="userPhone" />
      <div id="isHasExistForUserPhone"></div>
    </div>
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
      <label for="classNote">班级备注(可选填)</label>
      <textarea class="form-control" id="classNote" name="classNote" ></textarea>
    </div>
    <div class="form-group">
      <label for="userNote">自我介绍（可选填）</label>
      <textarea class="form-control" id="userNote" name="userNote" ></textarea>
    </div>
    <div class="form-group">
      <label for="invitationCode">邀请码</label>
      <input type="text" class="form-control" id="invitationCode" name="invitationCode" />
    </div>
    <button type="submit" class="btn btn-default">注册</button>
  </form>
  <a href="/index.jsp"><button type="button" class="btn btn-default">返回</button></a>
</div>
<div class="col-md-4"></div>
</div>
<script type="text/javascript">
  function checkPassword(){
    var pswd1 = document.getElementById("userPswd");
    var pswd2 = document.getElementById("userPswd1");
    if(pswd1 != "" && pswd2 != "" && pswd1 != pswd2){
      var messageForPswd = document.getElementById("isSameAsUserPawd");
      messageForPswd.innerHTML='两次输入的密码不一致';
    }
  }
  function clearText(){
    var pswd1 = document.getElementById("userPswd");
    var pswd2 = document.getElementById("userPswd1");
    if(pswd1 != "" && pswd2 != "" && pswd1 == pswd2){
      var messageForPswd = document.getElementById("isSameAsUserPawd");
      messageForPswd.innerHTML="";
    }
  }
</script>
</body>
</html>
