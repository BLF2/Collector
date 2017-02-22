<%@ page import="net.blf2.entity.UserInfo" %>
<%@ page import="net.blf2.util.Consts" %>
<%@ page import="java.util.Map" %>
<%--
  Created by IntelliJ IDEA.
  User: blf2
  Date: 17-2-22
  Time: 下午3:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
  HttpSession httpSession = request.getSession();
  UserInfo userInfo = (UserInfo)httpSession.getAttribute(Consts.LOGIN_INFO);
  Map<String,Double> scoreMap = (Map<String, Double>) httpSession.getAttribute(Consts.FRONT_SCORE_MAP);
  Map<Integer,String> userGradeMap = (Map<Integer, String>) httpSession.getAttribute(Consts.USER_GRADE_NAME_VALUE);
%>
<head>
    <title></title>
  <%@include file="HtmlPart/bootstrapCDN.html"%>
  <script type="text/javascript">
    var index = <%=scoreMap != null ? scoreMap.size() - 1 : 1%>;
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
    function clearHidden(obj){
      var hiddenIds = ["userPswd1Div","userGrade","addNewOne","saveInfo"];
      var readOnlyIds = ["userPswd","userPhone","userNote"];
      clearAtrributes(hiddenIds,"hidden");
      clearAtrributes(readOnlyIds,"readonly");
      var doc = document.getElementById("userGradeShow");
      doc.parentNode.removeChild(doc);
      for(var i = 1;i < index;i++){
        document.getElementById("readOnlyName"+i+"").removeAttribute("readonly");
        document.getElementById("readOnlyValue"+i+"").removeAttribute("readonly");
      }
      document.getElementById("addNewOne").style.display = "block";
      document.getElementById("saveInfo").style.display = "block";
      obj.style.display = "none";
    }
    function clearAtrributes(ids,feild){
      for(var i = 0 ;i< ids.length;i++){
        var ele = document.getElementById(ids[i]);
        ele.removeAttribute(feild);
      }
    }
  </script>
</head>
<body>
<div class="row">
  <div class="col-md-4"></div>
  <div class="col-md-4">
    <h2>学生信息查看和更新<button class="btn btn-info" type="button" onclick="clearHidden(this)">编辑</button></h2>
    <form action="/User/updateScore" method="post">
      <input type="hidden" name="userId" value="<%=userInfo.getUserId()%>" />
      <div class="form-group">
        <label for="userNum">学号：</label>
        <input type="text" readonly="readonly" class="form-control" id="userNum" name="userNum" value="<%=userInfo.getUserNum()%>"/>
      </div>
      <div class="form-group">
        <label for="userPswd">密码：</label>
        <input  readonly="readonly" type="password" class="form-control" id="userPswd" name="userPswd" value="<%=userInfo.getUserPswd()%>"/>
      </div>
      <div class="form-group" hidden="hidden" id="userPswd1Div">
        <label for="userPswd1">确认密码：</label>
        <input type="password" class="form-control" id="userPswd1" name="userPswd1" value="<%=userInfo.getUserPswd()%>" onblur="checkPswd()"/>
        <div id="infoForCheckPswd" style="color:red"></div>
      </div>
      <div class="form-group">
        <label for="userPhone">手机号：（方便班长联系到你）</label>
        <input readonly="readonly" type="text" class="form-control" id="userPhone" name="userPhone" value="<%=userInfo.getUserPhone()%>"/>
      </div>
      <div class="form-group">
        <label for="userGrade">专业班级：</label>
        <input  readonly="readonly" id="userGradeShow"value="<%=userInfo.getUserGrade()%>" />
        <select id="userGrade" name="userGrade" hidden="hidden">
          <option value="0">请选择专业班级</option>
          <%

            if(userGradeMap != null){
              httpSession.removeAttribute(Consts.USER_GRADE_NAME_VALUE);
              for(Map.Entry<Integer,String>entry : userGradeMap.entrySet()){
          %>
          <option value=" <%=entry.getKey()%>" <%=entry.getValue().equals(userInfo.getUserGrade()) ? "selected='selected'" : ""%> ><%=entry.getValue()%></option>
          <%}}
          %>
        </select>
      </div>
      <div class="form-group">
        <label for="userNote">备注：（选填）</label>
        <textarea class="form-control" id="userNote" name="userNote" readonly="readonly"><%=userInfo.getUserNote()%></textarea>
      </div>
      <hr />
      <h3>综合测评加减分统计</h3>
      <div id="itemInfo">
        <%
          if(scoreMap != null){
            httpSession.removeAttribute(Consts.FRONT_SCORE_MAP);
            int index = 1;
            for(Map.Entry<String,Double> entry : scoreMap.entrySet()){
              if("_id".equals(entry.getKey()) || Consts.USER_SUM_SCORE.equals(entry.getKey()))
                continue;
              String id = "itemInfo" + index;
              String itemsInfoListName = "itemsInfoList["+(index - 1)+"].itemName";
              String itemsInfoListValue = "itemsInfoList["+(index - 1) + "].itemValue";
        %>
              <div class="form-group" id="<%=id%>" >
                <label>项目：</label>
                <input readonly="readonly" type="text" class="form-control" id='<%="readOnlyName"+index%>' name="<%=itemsInfoListName%>" value="<%=entry.getKey()%>"/>
                <label>加减分：（请用正负数表示）</label>
                <input  readonly="readonly" type="number" class="form-control" id='<%="readOnlyValue"+index%>' name="<%=itemsInfoListValue%>" value="<%=entry.getValue()%>"/>
                <button hidden="hidden" type="button" id='<%="hiddenButton"+index%>' class="btn btn-danger" onclick="deleteNode('<%=id%>')">删除此项</button>
              </div>
            <%
                  index++;
                }
          }
        %>
      </div>
      <button id="addNewOne" style="display: none" class="btn btn-primary" type="button" onclick="addNewItem()">添加一项</button>
      <button id="saveInfo" type="submit" class="btn btn-primary" style="display: none">保存更新信息</button>
    </form>
  </div>
  <div class="col-md-4"></div>
</div>
</body>
</html>
