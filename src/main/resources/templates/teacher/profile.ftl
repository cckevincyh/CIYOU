<#assign base=request.contextPath />
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>CIYOU | 个人设置</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <!-- Bootstrap 3.3.7 -->
  <link rel="stylesheet" href="${base}/static/bower_components/bootstrap/dist/css/bootstrap.min.css">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="${base}/static/bower_components/font-awesome/css/font-awesome.min.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="${base}/static/bower_components/Ionicons/css/ionicons.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="${base}/static/dist/css/AdminLTE.min.css">
  <!-- AdminLTE Skins. Choose a skin from the css/skins
       folder instead of downloading all of them to reduce the load. -->
  <link rel="stylesheet" href="${base}/static/dist/css/skins/_all-skins.min.css">

  <!-- Date Picker -->
  <link rel="stylesheet" href="${base}/static/bower_components/bootstrap-datepicker/dist/css/bootstrap-datepicker.min.css">
  <!-- Daterange picker -->
  <link rel="stylesheet" href="${base}/static/bower_components/bootstrap-daterangepicker/daterangepicker.css">
  <!-- bootstrap wysihtml5 - text editor -->
  <link rel="stylesheet" href="${base}/static/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css">
    <!-- bootstrap-validator -->
    <link rel="stylesheet" href="${base}/static/bower_components/bootstrap-validator/dist/css/bootstrap-validator.css"/>

    <!--引入CSS-->
    <link rel="stylesheet" type="text/css" href="${base}/static/webuploader/webuploader.css">
    <link rel="stylesheet" type="text/css" href="${base}/static/webuploader/cropper.css" />
    <link rel="stylesheet" type="text/css" href="${base}/static/webuploader/style.css" />

</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

  <header class="main-header">
    <!-- Logo -->
    <a href="${base}/teacher/index" class="logo">
      <!-- mini logo for sidebar mini 50x50 pixels -->
      <span class="logo-mini"><b>C</b>.</span>
      <!-- logo for regular state and mobile devices -->
      <span class="logo-lg"><b>CI</b>YOU</span>
    </a>
    <!-- Header Navbar: style can be found in header.less -->
    <!-- 顶部菜单栏 -->
    <nav class="navbar navbar-static-top">
      <!-- Sidebar toggle button-->
      <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
        <span class="sr-only">Toggle navigation</span>
      </a>

     <!-- 顶部菜单栏 Message，Notifications：消息提示信息菜单栏-->
      <div class="navbar-custom-menu">
        <ul class="nav navbar-nav">
          <!-- 个人信息-->
          <li class="dropdown user user-menu">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <img src="${base}${Session.teacher.picImg!}" class="user-image" alt="User Image">
              <span class="hidden-xs">${Session.teacher.name!}</span>
            </a>
            <ul class="dropdown-menu">
              <!-- User image -->
              <li class="user-header">
                <img src="${base}${Session.teacher.picImg!}" class="img-circle" alt="User Image">

                <p>
                       ${Session.teacher.name!}
                </p>
              </li>
              <!-- Menu Body -->
        	<!-- 个人信息-->
              <!-- Menu Footer-->
              <li class="user-footer">
                <div class="pull-left">
                  <a href="${base}/teacher/profile" class="btn btn-default btn-flat">个人资料</a>
                </div>
                <div class="pull-right">
                  <a href="${base}/teacher/teacherLogout" class="btn btn-default btn-flat">登出</a>
                </div>
              </li>
            </ul>
          </li>
        </ul>
      </div>
    </nav>
  </header>
  <!-- Left side column. contains the logo and sidebar -->
    <!-- 左部菜单栏 -->
    <aside class="main-sidebar">
        <!-- sidebar: style can be found in sidebar.less -->
        <section class="sidebar">
            <!-- Sidebar user panel -->
            <div class="user-panel">
                <div class="pull-left image">
                    <img src="${base}${Session.teacher.picImg!}" class="img-circle" alt="User Image">
                </div>
                <div class="pull-left info">
                    <p>${Session.teacher.name!}</p>
                    <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
                </div>
            </div>


            <ul class="sidebar-menu" data-widget="tree">
                <li class="header">菜单导航</li>
                <li>
                    <a href="${base}/teacher/manageVideo">
                        <i class="fa fa-video-camera"></i>
                        <span>视频管理</span>
                    </a>
                </li>
                <li>
                    <a href="${base}/teacher/manageRoster">
                        <i class="fa fa-sitemap"></i>
                        <span>班级分配</span>
                    </a>
                </li>
                <li>
                    <a href="${base}/teacher/manageStudent">
                        <i class="fa fa-mortar-board"></i> <span>学生管理</span>
                    </a>
                </li>
                <li>
                    <a href="${base}/teacher/manageQuiz">
                        <i class="fa fa-book"></i> <span>小测管理</span>
                    </a>
                </li>
                <li>
                    <a href="${base}/teacher/manageScore">
                        <i class="fa fa-search"></i> <span>成绩查询</span>
                    </a>
                </li>
                <li>
                    <a href="${base}/teacher/analyseClasses">
                        <i class="fa fa-bar-chart"></i> <span>班级分析</span>
                    </a>
                </li>
                <li>
                    <a href="${base}/teacher/analyseQuiz">
                        <i class="fa fa-bar-chart"></i> <span>小测分析</span>
                    </a>
                </li>
                <li>
                    <a href="${base}/teacher/analyseStudent">
                        <i class="fa fa-bar-chart"></i> <span>学生分析</span>
                    </a>
                </li>
                <li class="active">
                    <a href="${base}/teacher/profile">
                        <i class="fa fa-cogs"></i> <span>个人设置</span>
                    </a>
                </li>
                <li>
                    <a href="#">
                        <i class="fa fa-spinner"></i> <span>正在开发</span>
                    </a>
                </li>




            </ul>
        </section>
        <!-- /.sidebar -->
    </aside>

  <!-- 中间部分 -->
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper" style="min-height: 200px;">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
          个人设置
        <small></small>
      </h1>
      <ol class="breadcrumb">
          <li><a href="${base}/teacher/index"><i class="fa fa-home"></i> 首页</a></li>
        <li class="active"><i class="fa fa-cogs"></i> 个人设置</a></li>
      </ol>
    </section>

      <section class="content">
          <div class="row">
              <div class="col-xs-12">
                      <div class="box">
                            <div class="nav-tabs-custom">
                                <ul class="nav nav-tabs" id="myTab">
                                    <li class=""><a href="#profile" data-toggle="tab" aria-expanded="true">个人资料</a></li>
                                    <li class=""><a href="#password" data-toggle="tab" aria-expanded="false">修改密码</a></li>
                                    <li class=""><a href="#picImg" data-toggle="tab" aria-expanded="false">上传头像</a></li>
                                </ul>
                                <div class="tab-content">
                                    <div class="tab-pane fade" id="profile">
                                        <form class="form-horizontal bv-form" id="dict-form-profile">
                                            <div class="form-group has-feedback">
                                                <label for="name" class="col-sm-2 control-label">姓名</label>
                                                <div class="col-sm-4">
                                                    <input type="hidden" name="BASE_URL" id="BASE_URL" value="${base}">
                                                    <input type="hidden" name="tid" id="tid" value="${Session.teacher.tid!}">
                                                    <input type="text" class="form-control" id="name" name="name" placeholder="教师姓名"  data-bv-field="name">
                                                    <i class="form-control-feedback" data-bv-icon-for="name" style="display: none;"></i>
                                                    <small data-bv-validator="notEmpty" data-bv-validator-for="name" class="help-block" style="display: none;"></small>
                                                </div>
                                            </div>

                                            <div class="form-group has-feedback">
                                                <label for="sex" class="col-sm-2 control-label">性别</label>
                                                <div class="col-sm-4">
                                                    <input type="radio" name="sex" id="sex1" value="1" checked> 男
                                                    <input type="radio" name="sex" id="sex2" value="2" style="margin-left: 10px;" data-bv-field="sex">女
                                                    <i class="form-control-feedback" data-bv-icon-for="sex" style="display: none;"></i>
                                                    <small data-bv-validator="notEmpty" data-bv-validator-for="sex" class="help-block" style="display: none;"></small>
                                                </div>
                                            </div>
                                            <div class="form-group has-feedback">
                                                <label for="subject" class="col-sm-2 control-label">科目</label>
                                                <div class="col-sm-4">
                                                    <select class="form-control" id="subject" name="subject" data-bv-field="subject">
                                                        <option value="0">请选择</option>
                                                    </select>
                                                    <i class="form-control-feedback" data-bv-icon-for="subject" style="display: none;"></i>
                                                    <small data-bv-validator="callback" data-bv-validator-for="subject" class="help-block" style="display: none;"></small>
                                                </div>
                                            </div>

                                            <div class="form-group has-feedback">
                                                <label for="phone" class="col-sm-2 control-label">电话</label>
                                                <div class="col-sm-4">
                                                    <input type="text" class="form-control" id="phone" name="phone" placeholder="教师电话号码" data-bv-field="phone">
                                                    <i class="form-control-feedback" data-bv-icon-for="phone" style="display: none;"></i>
                                                    <small data-bv-validator="callback" data-bv-validator-for="phone" class="help-block" style="display: none;"></small></div>
                                            </div>

                                            <div class="form-group has-feedback">
                                                <label for="email" class="col-sm-2 control-label">邮箱</label>
                                                <div class="col-sm-4">
                                                    <input type="text" class="form-control" id="email" name="email" placeholder="教师邮箱" data-bv-field="email">
                                                    <i class="form-control-feedback" data-bv-icon-for="email" style="display: none;"></i>
                                                    <small data-bv-validator="callback" data-bv-validator-for="email" class="help-block" style="display: none;"></small></div>
                                            </div>

                                            <div class="box-footer">
                                                <div class="col-sm-5"></div>
                                                <div class="col-sm-6">
                                                    <button type="button" class="btn btn-primary" id="profileButton">
                                                        <i class="fa fa-save">&nbsp;保存</i>
                                                    </button>
                                                </div>
                                            </div>
                                        </form>
                                    </div>

                                    <div class="tab-pane fade" id="password">
                                        <form class="form-horizontal bv-form" id="dict-form-password">
                                            <div class="form-group has-feedback">
                                                <label for="password" class="col-sm-2 control-label">原密码</label>
                                                <div class="col-sm-4">
                                                    <input type="password" class="form-control" id="oldPwd" name="oldPwd" placeholder="原密码" data-bv-field="oldPwd">
                                                    <i class="form-control-feedback" data-bv-icon-for="oldPwd" style="display: none;"></i>
                                                    <small data-bv-validator="notEmpty" data-bv-validator-for="oldPwd" class="help-block" style="display: none;"></small>
                                                </div>
                                            </div>

                                            <div class="form-group has-feedback">
                                                <label for="newPwd" class="col-sm-2 control-label">新密码</label>
                                                <div class="col-sm-4">
                                                    <input type="password" class="form-control" id="newPwd" name="newPwd" placeholder="新密码" data-bv-field="newPwd">
                                                    <i class="form-control-feedback" data-bv-icon-for="newPwd" style="display: none;"></i>
                                                    <small data-bv-validator="notEmpty" data-bv-validator-for="newPwd" class="help-block" style="display: none;"></small></div>
                                            </div>
                                            <div class="form-group has-feedback">
                                                <label for="confirmPwd" class="col-sm-2 control-label">确认密码</label>
                                                <div class="col-sm-4">
                                                    <input type="password" class="form-control" id="confirmPwd" name="confirmPwd" placeholder="确认密码" data-bv-field="confirmPwd">
                                                    <i class="form-control-feedback" data-bv-icon-for="confirmPwd" style="display: none;"></i>
                                                    <small data-bv-validator="notEmpty" data-bv-validator-for="confirmPwd" class="help-block" style="display: none;"></small></div>
                                            </div>

                                            <div class="box-footer">
                                                <div class="col-sm-5"></div>
                                                <div class="col-sm-6">
                                                    <button type="button" class="btn btn-primary" id="passwordButton">
                                                        <i class="fa fa-save">&nbsp;保存</i>
                                                    </button>
                                                </div>
                                            </div>
                                        </form>
                                    </div>

                                    <div class="tab-pane fade in active" id="picImg">
                                        <form class="form-horizontal bv-form" id="dict-form-profile">
                                            <div class="form-group">
                                                <label for="email" class="col-sm-2 control-label" style="margin-top: 15px;">上传头像</label>
                                                <div class="col-sm-4">
                                                    <div id="wrapper">
                                                        <div class="uploader-container">
                                                            <div id="filePicker">选择文件</div>
                                                        </div>

                                                        <!-- Croper container -->
                                                        <div class="cropper-wraper webuploader-element-invisible">
                                                            <div class="img-container">
                                                                <img src="" alt="" />
                                                            </div>

                                                            <div class="upload-btn">上传所选区域</div>

                                                            <div class="img-preview"></div>
                                                        </div>


                                                    </div>
                                                </div>
                                            </div>
                                        </form>
                                    </div>

                                </div>
                                <!-- /.tab-content -->
                            </div>
                              <!-- /.nav-tabs-custom -->
                      </div>
                </div>
            </div>
        </section>
    
    


 
   
    <!-- /.content -->
  </div>
    <!-- /.content-wrapper -->
    <footer class="main-footer">
        <div class="pull-right hidden-xs">
            <b>Version</b> 1.0.0
        </div>
        <strong>Copyright &copy; 2017-2018 CIYOU Education.</strong> All rights
        reserved.
    </footer>


</div>
<!-- ./wrapper -->



<!-- 提示 -->
<div class="modal fade" id="modal_info" tabindex="-1" role="dialog" aria-labelledby="infoModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="infoModalLabel">提示</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-lg-12" id="div_info"></div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" id="btn_info_close" data-dismiss="modal"><i class="fa fa-close"></i>关闭</button>
            </div>
        </div>
    </div>
</div>


<!-- 提示 -->
<div class="modal fade" id="modal_info1" tabindex="-1" role="dialog" aria-labelledby="infoModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="infoModalLabel">提示</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-lg-12" id="div_info1"></div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" id="btn_info_close" data-dismiss="modal"><i class="fa fa-close"></i>关闭</button>
            </div>
        </div>
    </div>
</div>

<!-- jQuery 3 -->
<script src="${base}/static/bower_components/jquery/dist/jquery.min.js"></script>
<!-- jQuery UI 1.11.4 -->
<script src="${base}/static/bower_components/jquery-ui/jquery-ui.min.js"></script>
<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
<script>
  $.widget.bridge('uibutton', $.ui.button);
</script>
<!-- Bootstrap 3.3.7 -->
<script src="${base}/static/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- Morris.js charts -->
<script src="${base}/static/bower_components/raphael/raphael.min.js"></script>
<script src="${base}/static/bower_components/morris.js/morris.min.js"></script>
<!-- Sparkline -->
<script src="${base}/static/bower_components/jquery-sparkline/dist/jquery.sparkline.min.js"></script>
<!-- jvectormap -->
<script src="${base}/static/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
<script src="${base}/static/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
<!-- jQuery Knob Chart -->
<script src="${base}/static/bower_components/jquery-knob/dist/jquery.knob.min.js"></script>
<!-- daterangepicker -->
<script src="${base}/static/bower_components/moment/min/moment.min.js"></script>
<script src="${base}/static/bower_components/bootstrap-daterangepicker/daterangepicker.js"></script>
<!-- datepicker -->
<script src="${base}/static/bower_components/bootstrap-datepicker/dist/js/bootstrap-datepicker.min.js"></script>
<!-- Bootstrap WYSIHTML5 -->
<script src="${base}/static/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>
<!-- Slimscroll -->
<script src="${base}/static/bower_components/jquery-slimscroll/jquery.slimscroll.min.js"></script>
<!-- bootstrap-validator -->
<script src="${base}/static/bower_components/bootstrap-validator/dist/js/bootstrap-validator.js"></script>
<!-- FastClick -->
<script src="${base}/static/bower_components/fastclick/lib/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="${base}/static/dist/js/adminlte.min.js"></script>
<!-- AdminLTE dashboard demo (This is only for demo purposes) -->
<script src="${base}/static/dist/js/pages/dashboard.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="${base}/static/dist/js/demo.js"></script>

<!--引入JS-->
<script type="text/javascript" src="${base}/static/webuploader/webuploader.js"></script>
<script type="text/javascript" src="${base}/static/webuploader/cropper.js"></script>

<script src="${base}/static/js/teacher/profile/profileValidator.js"></script>
<script src="${base}/static/js/teacher/profile/updateProfile.js"></script>
<script src="${base}/static/js/teacher/profile/passwordValidator.js"></script>
<script src="${base}/static/js/teacher/profile/updatePassword.js"></script>
<script src="${base}/static/js/teacher/profile/ImgUploader.js"></script>
</body>
</html>
