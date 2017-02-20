<%--
  Created by IntelliJ IDEA.
  User: blf2
  Date: 17-2-16
  Time: 下午3:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Signin</title>

  <!-- Bootstrap core CSS -->
  <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

  <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
  <link href="http://v3.bootcss.com/assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="http://v3.bootcss.com/examples/signin/signin.css" rel="stylesheet">

</head>

<body>

<div class="container">

  <form class="form-signin" action="/User/login" method="post">
    <h2 class="form-signin-heading">请登录</h2>
    <label for="userNum" class="sr-only">学号</label>
    <input type="text" id="userNum" name="userNum" class="form-control" placeholder="user num" required autofocus>
    <label for="userPswd" class="sr-only">密码</label>
    <input type="password" id="userPswd" name="userPswd" class="form-control" placeholder="user pswd" required>
    <div class="checkbox">
      <label>
        <input type="checkbox" value="remember-me" name="rememberMe"> 记住我
      </label>
    </div>
    <button class="btn btn-lg btn-primary btn-block" type="submit">登陆</button>
  </form>

</div> <!-- /container -->


<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="http://v3.bootcss.com/assets/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>

</html>
