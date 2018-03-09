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
    <link rel="stylesheet" href="${base}/static/css/zz.css" />
    <link rel="stylesheet" href="${base}/static/video/css/video-js.css" type="text/css">
    <style type="text/css">
        video::-internal-media-controls-download-button {
            display:none;
        }

        video::-webkit-media-controls-enclosure {
            overflow:hidden;
        }

        video::-webkit-media-controls-panel {
            width: calc(100% + 30px);
        }
    </style>

</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

  <header class="main-header">
    <!-- Logo -->
    <a href="${base}/student/index" class="logo">
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
              <img src="${base}${Session.student.picImg!}" class="user-image" alt="User Image">
              <span class="hidden-xs">${Session.student.name!}</span>
            </a>
            <ul class="dropdown-menu">
              <!-- User image -->
              <li class="user-header">
                <img src="${base}${Session.student.picImg!}" class="img-circle" alt="User Image">

                <p>
                       ${Session.student.name!}
                     <#if (student.classes)?? && (student.classes.grade)??>
                        <small>${Session.student.classes.grade.gradeName!} - ${Session.student.classes.classes!}班</small>
                     </#if>
                </p>
              </li>
              <!-- Menu Body -->
        	<!-- 个人信息-->
              <!-- Menu Footer-->
              <li class="user-footer">
                <div class="pull-left">
                  <a href="${base}/student/profile" class="btn btn-default btn-flat">个人资料</a>
                </div>
                <div class="pull-right">
                  <a href="${base}/logout" class="btn btn-default btn-flat">登出</a>
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
          <img src="${base}${Session.student.picImg!}" class="img-circle" alt="User Image">
        </div>
        <div class="pull-left info">
          <p>${Session.student.name!}</p>
          <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
        </div>
      </div>
     
     
      <ul class="sidebar-menu" data-widget="tree">
        <li class="header">菜单导航</li>
          <li class="active">
              <a href="${base}/student/index">
                  <i class="fa fa-video-camera"></i>
                  <span>观看视频</span>
              </a>
          </li>
        <li>
          <a href="${base}/student/quiz">
            <i class="fa fa-book"></i> <span>小测练习</span>
          </a>
        </li>
          <li>
              <a href="${base}/student/score">
                  <i class="fa fa-search"></i> <span>我的成绩</span>
              </a>
          </li>
          <li>
              <a href="${base}/student/profile">
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
          观看视频
      </h1>
      <ol class="breadcrumb">
        <li class="active"><a href="#"><i class="fa fa-video-camera"></i> 观看视频</a></li>
      </ol>
    </section>
    
    
    <!-- Main content -->
    <section class="content">
        <div class="row">
            <div class="col-xs-12">
                <div class="box">
                   <div class="container clearfix">
                            <div class="course-nav-box">
                                 <div class="nav-container">
                                    <div class="title">
                                        <span class="title-allCourse-yang">全部课程:</span>
                                    </div>
                                    <div class="direction">
                                        <div class="head">年级：</div>
                                        <div class="choice">
                                            <span id = "grade_0" class = "current" onclick="setGrade(0)" name="grade">全部</span>
                                            <#list grades as grade>
                                                <span id = "grade_${grade.gradeId!}" onclick="setGrade(${grade.gradeId!})" name="grade">${grade.gradeName}</span>
                                            </#list>
                                        </div>
                                    </div>
                                    <div class="level">
                                        <div class="head">科目：</div>
                                        <div class="choice">
                                            <span id = "subject_0" class="current" onclick="setSubject(0)" name="subject">全部</span>
                                        <#list subjects as subject>
                                            <span id = "subject_${subject.subjectId!}" onclick="setSubject(${subject.subjectId!})" name="subject">${subject.subjectName}</span>
                                        </#list>
                                        </div>
                                    </div>
                                    <div class="row">
                                  <#list pageInfo.list as video>
                                      <div class="col-lg-3 col-xs-6">
                                          <!-- small box -->
                                          <div class="small-box">
                                              <div class="inner">
                                                  <video class="video-js vjs-default-skin vjs-big-play-centered" controls  preload="auto" poster="${video.imgUrl!}" width="250" height="150" data-setup="{}">
                                                      <source src="${base}${video.videoUrl!}" type="video/mp4"/>
                                                  </video>
                                              </div>
                                              <a href="#" class="small-box-footer">
                                              ${video.videoName!} <i class="fa fa-video-camera"></i>
                                              </a>
                                          </div>
                                      </div>
                                  </#list>
                                    </div>
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
                            </div>
                     </div>
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

<script src="${base}/static/js/student/video/setGrade.js"></script>
<script src="${base}/static/js/student/video/setSubject.js"></script>
<script type="text/javascript" src="${base}/static/video/js/video.min.js"></script>
<script>
    //去除右键事件
    $("video").bind("contextmenu",function(){//取消右键事件
        return false;
    });
</script>
</body>
</html>
