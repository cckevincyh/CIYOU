<#assign base=request.contextPath />
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>CIYOU | 班级管理</title>
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
                <li class="active">
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
          班级管理
          <small>分配班级</small>
      </h1>
      <ol class="breadcrumb">
          <li><a href="${base}/teacher/index"><i class="fa fa-home"></i> 首页</a></li>
          <li class="active"><i class="fa fa-sitemap"></i> 班级管理</a></li>
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
                          <div class="col-md-3 col-sm-4"><button class="btn btn-default btn-xs" id="btn_add" data-toggle="modal" data-target="#addModal"><i class="fa fa-fw fa-plus"></i></button> 分配班级</div>
                      </div>
                      <!-- /.box-header -->
                      <div class="box-body table-responsive no-padding">
                          <table class="table table-hover">
                              <tr>
                                  <th>年级</th>
                                  <th>班级</th>
                                  <th>教师</th>
                                  <th>科目</th>
                                  <th>操作</th>
                              </tr>
                          <#if pageInfo?? && pageInfo.list?? && (pageInfo.list?size > 0) >
                              <#list pageInfo.list as roster>
                                  <tr>
                                      <#if roster.classes?? && roster.classes.grade??>
                                      <td>${roster.classes.grade.gradeName!}</td>
                                      <#else >
                                          <td></td>
                                      </#if>
                                      <#if roster.classes??>
                                      <td>${roster.classes.classes!}</td>
                                      <#else >
                                          <td></td>
                                      </#if>
                                      <#if roster.teacher??>
                                      <td>${roster.teacher.name!}</td>
                                      <#else >
                                          <td></td>
                                      </#if>
                                       <#if roster.subject??>
                                       <td>${roster.subject.subjectName!}</td>
                                       <#else >
                                           <td></td>
                                       </#if>
                                      <td><button class="btn btn-warning btn-xs"  data-toggle="modal" data-target="#updateModal" onclick="updateRoster(${roster.rid!})"><i class="fa fa-fw fa-edit"></i></button>
                                          <button class="btn btn-danger btn-xs" onclick="deleteRoster(${roster.rid!})"><i class="fa fa-fw fa-trash"></i></button></td>
                                  </tr>
                              </#list>
                          <#else >
                              <tr>
                                  <td colspan="5" align="center">暂无数据</td>
                          <tr>
                          </#if>
                          </table>
                      </div>
                      <!-- /.box-body -->
                  <#if pageInfo?? && pageInfo.list?? && (pageInfo.list?size > 0) >
                      <div class="box-footer clearfix">
                          <ul class="pagination no-margin pull-right">
                              <li class="disabled"><a href="#">第${pageInfo.pageNum}页/共${pageInfo.pages}页</a></li>
                              <#if pageInfo.pageNum == 1>
                                  <li class="disabled"><a>&laquo;</a></li>
                              <#else>
                                  <li><a href="${pageInfo.url}page=${pageInfo.pageNum - 1}">&laquo;</a></li>
                              </#if>
                              <#list pageInfo.navigatepageNums as num>
                                  <#if pageInfo.pageNum == num>
                                      <li class="active"><a>${num}</a></li>
                                  <#else>
                                      <li><a href="${pageInfo.url}page=${num}">${num}</a></li>
                                  </#if>
                              </#list>
                              <#if pageInfo.pageNum == pageInfo.pages>
                                  <li class="disabled"><a>&raquo;</a></li>
                              <#else>
                                  <li><a href="${pageInfo.url}page=${pageInfo.pageNum + 1}">&raquo;</a></li>
                              </#if>
                          </ul>
                      </div>
                  </#if>
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

<!-------添加的模糊框----->
<form class="form-horizontal">   <!--保证样式水平不混乱-->
    <!-- 模态框（Modal） -->
    <div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        &times;
                    </button>
                    <h4 class="modal-title" id="myModalLabel">
                        分配新班级
                    </h4>
                </div>
                <div class="modal-body">

                    <!---------------------表单-------------------->
                    <div class="form-group">
                        <label for="firstname" class="col-sm-3 control-label">年级</label>
                        <div class="col-sm-7">
                            <select class="form-control" id="addGrade">
                                <option value="0">请选择</option>
                            </select>
                            <label class="control-label" for="addGrade" style="display:none;"></label>
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="firstname" class="col-sm-3 control-label">班级</label>
                        <div class="col-sm-7">
                            <select class="form-control" id="addClasses">
                                <option value="0">请选择</option>
                            </select>
                            <label class="control-label" for="addClasses" style="display:none;"></label>
                        </div>
                    </div>
                    <!---------------------表单-------------------->
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal"><i class="fa fa-close"></i>关闭
                    </button>
                    <button type="button" class="btn btn-primary" id="addRoster"><i class="fa fa-save"></i>
                        保存
                    </button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>

</form>



<!-- 修改模态框（Modal） -->
<form class="form-horizontal">   <!--保证样式水平不混乱-->
    <div class="modal fade" id="updateModal" tabindex="-1" role="dialog" aria-labelledby="updateModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        &times;
                    </button>
                    <h4 class="modal-title" id="updateModalLabel">
                        修改班级分配
                    </h4>
                </div>
                <div class="modal-body">

                    <!---------------------表单-------------------->

                    <div class="form-group">
                        <label for="firstname" class="col-sm-3 control-label">年级</label>
                        <div class="col-sm-7">
                            <select class="form-control" id="updateGrade">
                                <option value="0">请选择</option>
                            </select>
                            <label class="control-label" for="updateGrade" style="display:none;"></label>
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="firstname" class="col-sm-3 control-label">班级</label>
                        <div class="col-sm-7">
                            <select class="form-control" id="updateClasses">
                                <option value="0">请选择</option>
                            </select>
                            <label class="control-label" for="updateClasses" style="display:none;"></label>
                        </div>
                    </div>

                    <!---------------------表单-------------------->

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal"><i class="fa fa-close"></i>关闭
                    </button>
                    <button type="button" class="btn btn-primary" id="updateRoster"><i class="fa fa-save"></i>
                        保存
                    </button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>

</form>

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

<script src="${base}/static/js/teacher/roster/addRoster.js"></script>
<script src="${base}/static/js/teacher/roster/updateRoster.js"></script>
<script src="${base}/static/js/teacher/roster/deleteRoster.js"></script>
</body>
</html>
