<%@ page import="java.util.Map" %>
<%@ page import="net.blf2.util.Consts" %>
<%--
  Created by IntelliJ IDEA.
  User: blf2
  Date: 17-2-20
  Time: 下午10:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生信息提交</title>
  <%@include file="HtmlPart/bootstrapCDN.html"%>
  <script type="text/javascript">
    var index = 1;
    function addNewItem(){
      var itemsInfoListName = "itemsInfoList["+(index - 1).toString()+"].itemName";
      var itemsInfoListValue = "itemsInfoList["+(index - 1).toString() + "].itemValue";
      var curentParent = document.getElementById("itemInfo");
      var str = '<label>项目：</label> '+
                '<input type="text" class="form-control" name="'+itemsInfoListName+'"/>'+
                ' <label>加减分：（请用正负数表示）</label>'+
                '<input type="number" class="form-control" name="'+itemsInfoListValue+'"/>'+
                '<button type="button" class="btn btn-danger" onclick="deleteNode('+"\'itemInfo"+index+'\')"'+'>删除此项</button>';

      var firstDiv = document.createElement("div");
      firstDiv.setAttribute("class","form-group");
      firstDiv.setAttribute("id","itemInfo"+index);
      firstDiv.innerHTML = str;
      curentParent.appendChild(firstDiv);
      index++;
    }
    function deleteNode(divId){
      var delDiv = document.getElementById(divId);
      if(delDiv != null)
        delDiv.parentNode.removeChild(delDiv);
    }
    function checkPswd(){
      var pswd1 = document.getElementById("userPswd").value;
      var pswd2 = document.getElementById("userPswd1").value;
      if(pswd1 != pswd2){
        var obj = document.getElementById("infoForCheckPswd");
        obj.innerHTML="两次输入的密码不一致";
      }else{
        var obj = document.getElementById("infoForCheckPswd");
        obj.innerHTML="";
      }
    }
  </script>
</head>
<body>
<div class="row">
<div class="col-md-4"></div>
  <div class="col-md-4">
    <h2>学生信息提交</h2>
    <form action="/User/submitInfo" method="post">
      <div class="form-group">
        <label for="userNum">学号：</label>
        <input type="text" class="form-control" id="userNum" name="userNum" />
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
        <label for="validateCode">邀请码：</label>
        <input name="validateCode" id="validateCode" type="text" class="form-control"/>
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
