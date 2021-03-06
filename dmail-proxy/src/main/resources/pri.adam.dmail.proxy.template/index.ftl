<!DOCTYPE html>
<html>
<head lang="zh-cn">
    <title>欢迎来到YMail</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!--<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">-->
    <!-- 可选的Bootstrap主题文件（一般不用引入） -->
    <!--<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap-theme.min.css">-->
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <!--<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>-->
    <!--<script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>-->
    <link rel="stylesheet" href="css/pagecontext.css">
    <link rel="stylesheet" type="text/css" href="http://fonts.useso.com/css?family=Tangerine">
    <script src="js/jquery-2.1.1.min.js"></script>
    <link rel="stylesheet" href="css/bootstrap.min.css"/>
    <!--<link rel="stylesheet" href="css/bootstrap-theme.min.css"/>-->
    <script src="js/bootstrap.min.js"></script>
</head>
<body>
<!--首页导航栏开始-->
<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">
                <font class="font-logo">YMail</font>
                <!--<img src="imgs/logo.jpg" width="20%" height="100%"/>-->
            </a>
        </div>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="#"><span class="glyphicon glyphicon-user" data-toggle="tooltip" data-placement="bottom"
                                  title="Adam"></span></a></li>
            <li><a href="#"><span class="glyphicon glyphicon-log-out" data-toggle="tooltip" data-placement="bottom"
                                  title="退出"></span></a></li>
            <li></li>
        </ul>
    </div>
</nav>
<br/><br/><br/>
<!--导航栏介绍-->
<!--首页主体开始-->
<div class="container">
    <!--首页巨屏-->
    <div class="container theme-showcase" role="main">
        <div class="jumbotron">
            <h1>欢迎使用<font class="font-style" style="font-size: 68px">YMail</font></h1>

            <p><font class="font-style">YMail</font> 是一个.....</p>

            <p>
                <a class="btn btn-primary btn-lg" href="#" role="button" data-toggle="modal"
                   data-target="#signUpModal">注册 &raquo;</a>
                <!--<a class="btn btn-info btn-lg" href="#" role="button">登陆 &raquo;</a>-->
            </p>
        </div>
    </div>
    <!--巨屏结束-->
    <!--内容块介绍开始-->
    <!--Web块-->
    <div class="row">
        <div class="col-lg-4">
            <h2><font class="font-style">YMail</font>&nbsp; for Web</h2>

            <p>YMail for Web是YMail邮箱的Web客户端... </p>

            <p><a class="btn btn-primary" href="#" role="button" data-toggle="modal"
                  data-target="#loginModal">登陆 &raquo;</a>
        </div>
        <!--Web块结束-->
        <!--Desktop开始-->
        <div class="col-lg-4">
            <h2><font class="font-style">YMail</font>&nbsp; for Desktop</h2>

            <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris
                condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis
                euismod. Donec sed odio dui. </p>

            <p><a class="btn btn-primary" href="#" role="button">下载 &raquo;</a></p>
        </div>
        <!--Desktop结束-->
        <!--Android开始-->
        <div class="col-lg-4">
            <h2><font class="font-style">YMail</font>&nbsp; for Android</h2>

            <p>Donec sed odio dui. Cras justo odio, dapibus ac facilisis in, egestas eget quam. Vestibulum id ligula
                porta felis euismod semper. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut
                fermentum massa.</p>

            <p><a class="btn btn-primary" href="#" role="button">下载 &raquo;</a></p>
        </div>
        <!--Android结束-->
    </div>
    <!--内容块结束-->


    <!--注册框开始-->
    <div class="modal fade" id="signUpModal" tabindex="-1" role="dialog" aria-labelledby="signUpModalLabel"
         aria-hidden="true">
        <div class="modal-dialog modal-sm">
            <div class="modal-content">
                <form class="form-signin " role="form">
                    <div class="modal-header">
                        <h2 class="modal-title" id="signUpModalLabel" style="text-align: center">欢迎新用户</h2>
                    </div>
                    <div class="modal-body">
                        <p class="text-danger hidden">注册失败</p>
                        <input type="text" class="form-control" placeholder="用户名" required><br/>
                        <input type="password" class="form-control" placeholder="密码" required data-toggle="tooltip"
                               data-placement="bottom" title="6-12位，不包含特殊字符"><br/>
                        <input type="password" class="form-control" placeholder="密码确认" required data-toggle="tooltip"
                               data-placement="bottom" title="6-12位，不包含特殊字符"><br/>
                        <!--<input type="email" class="form-control" placeholder="邮箱" required autofocus> <br/>-->
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" value="login">登陆
                            </label>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        <button type="submit" class="btn btn-primary" >注册</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <!--注册框结束-->
    <!--登陆框-->
    <!-- Modal -->
    <div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="loginModalLabel"
         aria-hidden="true">
        <div class="modal-dialog modal-sm">
            <div class="modal-content">
                <form class="form-signin " role="form">
                    <div class="modal-header">
                        <h2 class="modal-title" id="loginModalLabel" style="text-align: center">登陆</h2>
                    </div>
                    <div class="modal-body">
                        <p class="text-danger hidden">登录失败</p>
                        <input type="email" class="form-control" placeholder="邮箱" required autofocus>
                        <br/>
                        <input type="password" class="form-control" placeholder="密码" required>

                        <div class="checkbox">
                            <label>
                                <input type="checkbox" value="remember-me">记住我
                            </label>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        <button type="submit" class="btn btn-primary" >登陆</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <!--登陆框结束-->
</div>
<!--首页主体结束-->
<!--版权-->
<div class="footer">
    <hr>
    <p class="copyright">&copy; <a href="mailto:adamchen.pub@gmail.com">adamchen.pub@gmail.com </a>2014 </p>
</div>
<!--版权结束-->
</body>
</html>