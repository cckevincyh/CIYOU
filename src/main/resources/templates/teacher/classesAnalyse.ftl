<#assign base=request.contextPath />
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>CIYOU | 班级分析</title>
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
                <li class="active">
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
        <#if roster.classes?? && roster.classes.grade??>
            <input type="hidden" id="grade" value="${roster.classes.grade.gradeId!}">
            <input type="hidden" id="classes" value="${roster.classes.classesId!}">
            ${roster.classes.grade.gradeName!}-${roster.classes.classes!}班成绩分析
            <small>${roster.classes.grade.gradeName!}-${roster.classes.classes!}班小测成绩分析</small>
        </#if>
      </h1>
      <ol class="breadcrumb">
          <li><a href="${base}/teacher/index"><i class="fa fa-home"></i> 首页</a></li>
          <li><a href="${base}/teacher/analyseClasses"><i class="fa fa-bar-chart"></i> 班级分析</a></li>
          <li class="active"><i class="fa fa-bar-chart"></i>  ${roster.classes.grade.gradeName!}-${roster.classes.classes!}班成绩分析</a></li>
      </ol>
    </section>



      <!-- Main content -->
      <section class="content">
          <!-- /.row -->
          <div class="row">
              <div class="col-xs-12">
                  <div class="box">
                      <div class="box-header with-border">
                          <h3 class="box-title"></h3>
                      </div>
                      <!-- /.box-header -->
                      <div class="box-body table-responsive no-padding">
                          <div class="col-md-6">
                              <!-- AREA CHART -->
                              <div class="box box-primary">
                                  <div class="box-header with-border">
                                      <h3 class="box-title">Area Chart</h3>

                                      <div class="box-tools pull-right">
                                          <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
                                          </button>
                                          <button type="button" class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
                                      </div>
                                  </div>
                                  <div class="box-body" style="">
                                      <div class="chart">
                                          <canvas id="areaChart" style="height: 234px; width: 558px;" width="697" height="292"></canvas>
                                      </div>
                                  </div>
                                  <!-- /.box-body -->
                              </div>
                              <!-- /.box -->

                              <!-- Radar Chart -->
                              <div class="box box-danger">
                                  <div class="box-header with-border">
                                      <h3 class="box-title">Radar Chart</h3>

                                      <div class="box-tools pull-right">
                                          <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
                                          </button>
                                          <button type="button" class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
                                      </div>
                                  </div>
                                  <div class="box-body" style="">
                                      <canvas id="radarChart" style="height: 289px; width: 579px;" width="723" height="361"></canvas>
                                  </div>
                                  <!-- /.box-body -->
                              </div>
                              <!-- /.box -->

                          </div>

                          <div class="col-md-6">
                              <!-- LINE CHART -->
                              <div class="box box-info">
                                  <div class="box-header with-border">
                                      <h3 class="box-title">Line Chart</h3>

                                      <div class="box-tools pull-right">
                                          <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
                                          </button>
                                          <button type="button" class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
                                      </div>
                                  </div>
                                  <div class="box-body">
                                      <div class="chart">
                                          <canvas id="lineChart" style="height: 249px; width: 684px;" width="855" height="311"></canvas>
                                      </div>
                                  </div>
                                  <!-- /.box-body -->
                              </div>
                              <!-- /.box -->

                              <!-- BAR CHART -->
                              <div class="box box-success">
                                  <div class="box-header with-border">
                                      <h3 class="box-title">Bar Chart</h3>

                                      <div class="box-tools pull-right">
                                          <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
                                          </button>
                                          <button type="button" class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
                                      </div>
                                  </div>
                                  <div class="box-body">
                                      <div class="chart">
                                          <canvas id="barChart" style="height: 229px; width: 684px;" width="855" height="286"></canvas>
                                      </div>
                                  </div>
                                  <!-- /.box-body -->
                              </div>
                              <!-- /.box -->

                          </div>

                      </div>
                  </div>
                  <!-- /.box -->
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
<!-- FastClick -->
<script src="${base}/static/bower_components/fastclick/lib/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="${base}/static/dist/js/adminlte.min.js"></script>
<!-- AdminLTE dashboard demo (This is only for demo purposes) -->
<script src="${base}/static/dist/js/pages/dashboard.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="${base}/static/dist/js/demo.js"></script>
<script src="${base}/static/bower_components/Chart.js/Chart.js"></script>

<script src="${base}/static/js/teacher/analyse/classesAnalyse.js"></script>
</body>
</html>
