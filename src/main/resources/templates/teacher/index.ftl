<#assign base=request.contextPath />
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>CIYOU | 首页</title>
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
          <li>
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
          首页
        <small>功能描述</small>
      </h1>
      <ol class="breadcrumb">
        <li class="active"><a href="${base}/teacher/index"><i class="fa fa-home"></i> 首页</a></li>
      </ol>
    </section>


      <!-- Main content -->
      <section class="content">
          <div class="row">

              <div class="col-md-4">
                  <div class="box box-default">
                      <div class="box-header with-border">
                          <h3 class="box-title">视频管理</h3>

                          <div class="box-tools pull-right">
                              <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
                              </button>
                          </div>
                          <!-- /.box-tools -->
                      </div>
                      <!-- /.box-header -->
                      <div class="box-body">
                          <li><i class="fa fa-fw fa-plus"></i>  添加视频</li>
                          <li><i class="fa fa-fw fa-edit"></i>  修改视频</li>
                          <li><i class="fa fa-fw fa-trash"></i>  删除视频</li>
                      </div>
                      <!-- /.box-body -->
                  </div>
                  <!-- /.box -->
              </div>

              <div class="col-md-4">
                  <div class="box box-default">
                      <div class="box-header with-border">
                          <h3 class="box-title">班级分配</h3>

                          <div class="box-tools pull-right">
                              <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
                              </button>
                          </div>
                          <!-- /.box-tools -->
                      </div>
                      <!-- /.box-header -->
                      <div class="box-body">
                          <li><i class="fa fa-fw fa-plus"></i>  添加任课班级</li>
                          <li><i class="fa fa-fw fa-edit"></i>  修改任课班级</li>
                          <li><i class="fa fa-fw fa-trash"></i>  删除任课班级</li>
                      </div>
                      <!-- /.box-body -->
                  </div>
                  <!-- /.box -->
              </div>

              <div class="col-md-4">
                  <div class="box box-default">
                      <div class="box-header with-border">
                          <h3 class="box-title">学生管理</h3>

                          <div class="box-tools pull-right">
                              <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
                              </button>
                          </div>
                          <!-- /.box-tools -->
                      </div>
                      <!-- /.box-header -->
                      <div class="box-body">
                          <li><i class="fa fa-fw fa-eye"></i>  查看任课班级的学生</li>
                         <br/>
                          <br/>
                      </div>
                      <!-- /.box-body -->
                  </div>
                  <!-- /.box -->
              </div>

              <div class="col-md-4">
                  <div class="box box-default">
                      <div class="box-header with-border">
                          <h3 class="box-title">小测管理</h3>

                          <div class="box-tools pull-right">
                              <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
                              </button>
                          </div>
                          <!-- /.box-tools -->
                      </div>
                      <!-- /.box-header -->
                      <div class="box-body">
                          <li><i class="fa fa-fw fa-plus"></i>  添加小测</li>
                          <li><i class="fa fa-fw fa-edit"></i>  修改小测</li>
                          <li><i class="fa fa-fw fa-trash"></i>  删除小测</li>
                          <li><i class="fa fa-fw fa-paint-brush"></i>小测试题管理</li>
                      </div>
                      <!-- /.box-body -->
                  </div>
                  <!-- /.box -->
              </div>


              <div class="col-md-4">
                  <div class="box box-default">
                      <div class="box-header with-border">
                          <h3 class="box-title">成绩查询</h3>

                          <div class="box-tools pull-right">
                              <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
                              </button>
                          </div>
                          <!-- /.box-tools -->
                      </div>
                      <!-- /.box-header -->
                      <div class="box-body">
                          <li><i class="fa fa-search"></i>  查询任课班级的学生成绩</li>
                          <li><i class="fa fa-fw fa-eye"></i>  查看学生小测详情</li>
                          <br/>
                          <br/>
                      </div>
                      <!-- /.box-body -->
                  </div>
                  <!-- /.box -->
              </div>

              <div class="col-md-4">
                  <div class="box box-default">
                      <div class="box-header with-border">
                          <h3 class="box-title">班级分析</h3>

                          <div class="box-tools pull-right">
                              <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
                              </button>
                          </div>
                          <!-- /.box-tools -->
                      </div>
                      <!-- /.box-header -->
                      <div class="box-body">
                          <li><i class="fa fa-bar-chart"></i>  班级各科平均成绩的统计图表</li>
                          <li><i class="fa fa-bar-chart"></i>  年级各科平均成绩的统计图表</li>
                          <li><i class="fa fa-bar-chart"></i>  班级与年级各科平均成绩的对比统计图表</li>
                          <br/>
                      </div>
                      <!-- /.box-body -->
                  </div>
                  <!-- /.box -->
              </div>

              <div class="col-md-4">
                  <div class="box box-default">
                      <div class="box-header with-border">
                          <h3 class="box-title">小测分析</h3>

                          <div class="box-tools pull-right">
                              <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
                              </button>
                          </div>
                          <!-- /.box-tools -->
                      </div>
                      <!-- /.box-header -->
                      <div class="box-body">
                          <li><i class="fa fa-bar-chart"></i>  小测各题错误率对比统计图表</li>
                          <br/>
                          <br/>
                          <br/>
                      </div>
                      <!-- /.box-body -->
                  </div>
                  <!-- /.box -->
              </div>

              <div class="col-md-4">
                  <div class="box box-default">
                      <div class="box-header with-border">
                          <h3 class="box-title">学生分析</h3>

                          <div class="box-tools pull-right">
                              <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
                              </button>
                          </div>
                          <!-- /.box-tools -->
                      </div>
                      <!-- /.box-header -->
                      <div class="box-body">
                          <li><i class="fa fa-bar-chart"></i>  学生各科成绩的统计图表</li>
                          <li><i class="fa fa-bar-chart"></i>  班级各科平均成绩的统计图表</li>
                          <li><i class="fa fa-bar-chart"></i>  年级各科平均成绩的统计图表</li>
                          <li><i class="fa fa-bar-chart"></i>  学生与班级与年级各科平均成绩的对比统计图表</li>
                      </div>
                      <!-- /.box-body -->
                  </div>
                  <!-- /.box -->
              </div>

              <div class="col-md-4">
                  <div class="box box-default">
                      <div class="box-header with-border">
                          <h3 class="box-title">个人设置</h3>

                          <div class="box-tools pull-right">
                              <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
                              </button>
                          </div>
                          <!-- /.box-tools -->
                      </div>
                      <!-- /.box-header -->
                      <div class="box-body">
                          <li><i class="fa fa-fw fa-edit"></i>  修改个人资料</li>
                          <li><i class="fa fa-fw fa-edit"></i>  修改个人密码</li>
                          <li><i class="fa fa-fw fa-edit"></i>  修改个人头像</li>
                          <br/>
                      </div>
                      <!-- /.box-body -->
                  </div>
                  <!-- /.box -->
              </div>

              <!-- /.col -->
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
<!-- FastClick -->
<script src="${base}/static/bower_components/fastclick/lib/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="${base}/static/dist/js/adminlte.min.js"></script>
<!-- AdminLTE dashboard demo (This is only for demo purposes) -->
<script src="${base}/static/dist/js/pages/dashboard.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="${base}/static/dist/js/demo.js"></script>
</body>
</html>
