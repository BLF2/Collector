<%--
  Created by IntelliJ IDEA.
  User: blf2
  Date: 17-1-8
  Time: 下午2:33
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

  <title>Collector</title>

  <!-- Bootstrap core CSS -->
  <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

  <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
  <link href="http://v3.bootcss.com/assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="http://v3.bootcss.com/examples/offcanvas/offcanvas.css" rel="stylesheet">

  <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
  <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
  <script src="http://v3.bootcss.com/assets/js/ie-emulation-modes-warning.js"></script>

  <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
  <!--[if lt IE 9]>
  <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
  <![endif]-->
</head>

<body>
<nav class="navbar navbar-fixed-top navbar-inverse">
  <div class="container">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">Collector</a>
    </div>
    <div id="navbar" class="collapse navbar-collapse">
      <ul class="nav navbar-nav">
        <li class="active"><a href="#">Home</a></li>
        <li><a href="#about">About</a></li>
        <li><a href="#contact">Contact</a></li>
      </ul>
    </div><!-- /.nav-collapse -->
  </div><!-- /.container -->
</nav><!-- /.navbar -->

<div class="container">

  <div class="row row-offcanvas row-offcanvas-right">

    <div class="col-xs-12 col-sm-9">
      <p class="pull-right visible-xs">
        <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas">Toggle nav</button>
      </p>
      <div class="jumbotron">
        <h1>Collector</h1>
        <p>这是BLF2专门为ECHO开发的名为收集者的软件，班里的同学可以通过此平台填写表格来上传个人的综合测评加减分。</p>
      </div>
      <div class="row">
        <div class="col-xs-6 col-lg-4">
          <h2>班长注册</h2>
          <p>如果你是班长并且还没有账号，请点击这里。 </p>
          <p><a class="btn btn-default" href="#" role="button">View details &raquo;</a></p>
        </div><!--/.col-xs-6.col-lg-4-->
        <div class="col-xs-6 col-lg-4">
          <h2>班长信息修改</h2>
          <p>如果你是班长，并且已有自己的账号，你想创建班级或者修改信息，请点击这里。</p>
          <p><a class="btn btn-default" href="#" role="button">View details &raquo;</a></p>
        </div><!--/.col-xs-6.col-lg-4-->
        <div class="col-xs-6 col-lg-4">
          <h2>学生信息提交</h2>
          <p>如果你是学生，并且你的班长已经注册了，请点击这里提交你的信息。 </p>
          <p><a class="btn btn-default" href="#" role="button">View details &raquo;</a></p>
        </div><!--/.col-xs-6.col-lg-4-->
        <div class="col-xs-6 col-lg-4">
          <h2>学生信息修改</h2>
          <p>如果你发现你的综合测评加减分有问题，请点击这里这里修改。 </p>
          <p><a class="btn btn-default" href="#" role="button">View details &raquo;</a></p>
        </div><!--/.col-xs-6.col-lg-4-->
        <div class="col-xs-6 col-lg-4">
          <h2>学生信息查询</h2>
          <p>如果你想查看你的已经提交的信息，请点击这里。</p>
          <p><a class="btn btn-default" href="#" role="button">View details &raquo;</a></p>
        </div><!--/.col-xs-6.col-lg-4-->
        <div class="col-xs-6 col-lg-4">
          <h2>管理员进入</h2>
          <p>管理员入口。 </p>
          <p><a class="btn btn-default" href="/User/toSignin" role="button">View details &raquo;</a></p>
        </div><!--/.col-xs-6.col-lg-4-->
      </div><!--/row-->
    </div><!--/.col-xs-12.col-sm-9-->

    <div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar">
      <div class="list-group">
        <%--<a href="#" class="list-group-item active"></a>--%>
        <%--<a href="#" class="list-group-item"></a>--%>
        <%--<a href="#" class="list-group-item"></a>--%>
        <%--<a href="#" class="list-group-item"></a>--%>
        <%--<a href="#" class="list-group-item"></a>--%>
        <%--<a href="#" class="list-group-item"></a>--%>
        <%--<a href="#" class="list-group-item"></a>--%>
        <%--<a href="#" class="list-group-item"></a>--%>
        <%--<a href="#" class="list-group-item"></a>--%>
        <%--<a href="#" class="list-group-item"></a>--%>
      </div>
    </div><!--/.sidebar-offcanvas-->
  </div><!--/row-->

  <hr>

  <footer>
    <p>&copy; BLF2 2017</p>
  </footer>

</div><!--/.container-->


<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="http://v3.bootcss.com/assets/js/ie10-viewport-bug-workaround.js"></script>
<script src="http://v3.bootcss.com/examples/offcanvas/offcanvas.js"></script>
</body>
</html>

