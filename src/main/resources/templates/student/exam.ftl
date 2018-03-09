<#assign base=request.contextPath />
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>CIYOU | 小测练习</title>
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
                <li>
                    <a href="${base}/student/index">
                        <i class="fa fa-video-camera"></i>
                        <span>观看视频</span>
                    </a>
                </li>
                <li class="active">
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
          小测练习
      </h1>
      <ol class="breadcrumb">
          <li class="active"><i class="fa fa-book"></i> 小测练习</a></li>
      </ol>
    </section>



      <!-- Main content -->
      <section class="content">
          <!-- /.row -->
          <div class="row">
              <div class="col-xs-12">
                  <div class="box">
                      <div class="box-header with-border">
                      </div>
                      <div class="box-body table-responsive no-padding">
                          <form class="form-horizontal" action="" method="post">
                              <div class="col-lg-12 form-group">
                                  <label class="col-lg-6 control-label" for="query_ano"><h4><strong>${quiz.quizName!}</strong><h4></label>
                              </div>
                              <div class="col-lg-12 form-group">
                                  <label class="col-lg-6 control-label" for="query_ano"><i>(卷面总分:${quiz.allScore!})</i></label>
                              </div>
                              <div class="col-lg-3 form-group">
                                  <label class="col-lg-6 control-label" for="query_bno1"><i class="fa   fa-book"></i>  课程:</label>
                                  <input type="hidden" id="test_quizId" value="${quiz.quizId!}">
                                  <label class="col-lg-6 control-label" for="query_bno1"><i>${quiz.subject.subjectName!}</i></label>
                              </div>

                              <div class="col-lg-3 form-group">
                                  <label class="col-lg-6 control-label" for="query_bno1"><i class="fa  fa-clock-o"></i>  小测时间:</label>
                                  <input type="hidden" id="test_time" value="${quiz.quizTime!}">
                                  <label class="col-lg-6 control-label" for="query_bno1"><i>${quiz.quizTime!}分钟</i></label>
                              </div>

                              <div class="col-lg-3 form-group">
                                  <label class="col-lg-6 control-label" for="query_bno1"><i class="fa  fa-graduation-cap"></i>  姓名:</label>
                                  <input type="hidden" id="test_studentId" value="${Session.student.sid!}"/>
                              <label class="col-lg-6 control-label" for="query_bno1"><i>${Session.student.name!}</i> </label>
                              </div>

                              <div class="col-lg-3  form-group">
                                  <label class="col-lg-6 control-label" for="query_bno1"><i class="fa  fa-clock-o"></i>  剩余时间:</label>
                                  <label class="col-lg-6 control-label" for="query_bno1" id="remainTime"></label>
                              </div>
                          </form>
                      </div>
                  </div>
              </div>
          </div>
          <div class="row">
          <#assign index = 1 /><!--统计题目 -->
              <#if quiz??>
                  <!--选择题 -->
                <#list quiz.choices as choice>
                    <div class="col-xs-12">
                        <div class="box">
                            <div class="box-header with-border">
                                <!--题目-->
                            ${index }.${choice.question}?(选择题)
                                <#assign index = index + 1 />
                            </div>
                            <div class="box-body table-responsive no-padding">
                                <ul>
                                    <div class="radio">
                                        <label>
                                            <input type="radio" name='choice_${choice.choiceId}' id='optionsA_${choice.choiceId}' value='${choice.choiceId}_1_A'>A. ${choice.optionA}
                                        </label>
                                    </div>
                                    <div class="radio">
                                        <label>
                                            <input type="radio" name='choice_${choice.choiceId}' id='optionsB_${choice.choiceId}' value='${choice.choiceId}_1_B'>B. ${choice.optionB}
                                        </label>
                                    </div>
                                    <div class="radio">
                                        <label>
                                            <input type="radio" name='choice_${choice.choiceId}' id='optionsC_${choice.choiceId}' value='${choice.choiceId}_1_C'>C. ${choice.optionC}
                                        </label>
                                    </div>
                                    <div class="radio">
                                        <label>
                                            <input type="radio" name='choice_${choice.choiceId}' id='optionsD_${choice.choiceId}' value='${choice.choiceId}_1_D'>D. ${choice.optionD}
                                        </label>
                                    </div>
                                </ul>
                            </div>
                        </div>
                    </div>
                </#list>
              <#list quiz.judges as judge>
                  <div class="col-xs-12">
                      <div class="box">
                          <div class="box-header with-border">
                              <!--题目-->
                          ${index }.${judge.question}?(判断题)
                              <#assign index = index + 1 />
                          </div>
                          <div class="box-body table-responsive no-padding">
                              <ul>
                                  <div class="radio">
                                      <label>
                                          <input type="radio" name='judge_${judge.judgeId}' id='optionsY_${judge.judgeId}' value='${judge.judgeId}_2_Y'>对
                                      </label>
                                  </div>
                                  <div class="radio">
                                      <label>
                                          <input type="radio" name='judge_${judge.judgeId}' id='optionsN_${judge.judgeId}' value='${judge.judgeId}_2_N'>错
                                      </label>
                                  </div>
                              </ul>
                          </div>
                      </div>
                  </div>
              </#list>
              </#if>
              <div class="col-md-12" align="center">
                  <button type="button" class="btn btn-primary" onclick="assignment()">提交试卷</button>
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
<script src="${base}/static/js/student/quiz/getQuiz.js"></script>
<script src="${base}/static/js/student/quiz/quiz.js"></script>
<script src="${base}/static/js/student/quiz/exam.js"></script>
</body>
</html>
