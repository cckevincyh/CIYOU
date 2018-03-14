<#assign base=request.contextPath />
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>CIYOU | 试题管理</title>
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
    <!-- DataTables -->
    <link rel="stylesheet" href="${base}/static/bower_components/datatables.net-bs/css/dataTables.bootstrap.min.css">
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
                <li class="active">
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
          试题管理
          <small>${quiz.quizName} 选择题、判断题管理</small>
      </h1>
      <ol class="breadcrumb">
          <li><a href="${base}/teacher/index"><i class="fa fa-home"></i> 首页</a></li>
          <li><a href="${base}/teacher/manageQuiz"><i class="fa fa-book"></i> 小测管理</a></li>
          <li class="active"><i class="fa fa-paint-brush"></i> 试题管理</a></li>
      </ol>
    </section>



      <!-- Main content -->

      <!-- Main content -->
      <section class="content">
          <div class="row">
              <div class="col-xs-12">
                  <div class="box">
                      <!-- /.box-header -->
                      <div class="box-body">
                          <div class="col-md-6 col-sm-4"><button class="btn btn-default btn-xs" id="btn_add" data-toggle="modal" data-target="#choiceModal"><i class="fa fa-fw fa-plus"></i></button> 添加选择题</div>
                          <div class="col-md-6 col-sm-4"><button class="btn btn-default btn-xs" id="btn_add" data-toggle="modal" data-target="#judgeModal"><i class="fa fa-fw fa-plus"></i></button> 添加判断题</div>
                          <table id="example2" class="table table-bordered table-hover">
                              <thead>
                              <tr>
                                  <th>问题</th>
                                  <th>类型</th>
                                  <th>分数</th>
                                  <th>操作</th>
                              </tr>
                              </thead>
                              <tbody>
                              <#list quiz.choices as choices>
                              <tr>
                                  <td>${choices.question!}</td>
                                  <td>单选题</td>
                                  <td>${quiz.choiceScore!}分</td>
                                  <td>
                                      <button type="button" class="btn btn-info btn-xs" data-toggle="modal" data-target="#findChoiceModal" onclick="getChoice(${choices.choiceId!})" ><i class="fa fa-fw fa-eye"></i></button>
                                      <button type="button" class="btn btn-warning btn-xs" data-toggle="modal" data-target="#updateChoiceModal" onclick="updateChoice(${choices.choiceId!})"><i class="fa fa-fw fa-edit"></i></button>
                                      <button type="button" class="btn btn-danger btn-xs" onclick="deleteChoice(${choices.choiceId!})"><i class="fa fa-fw fa-trash"></i></button>
                                  </td>
                              </tr>
                              </#list>
                              <#list quiz.judges as judges>
                              <tr>
                                  <td>${judges.question!}</td>
                                  <td>判断题</td>
                                  <td>${quiz.judgeScore!}分</td>
                                  <td>
                                      <button type="button" class="btn btn-info btn-xs" data-toggle="modal" data-target="#findJudgeModal" onclick="getJudge(${judges.judgeId!})" ><i class="fa fa-fw fa-eye"></i></button>
                                      <button type="button" class="btn btn-warning btn-xs" data-toggle="modal" data-target="#updateJudgeModal" onclick="updateJudge(${judges.judgeId!})"><i class="fa fa-fw fa-edit"></i></button>
                                      <button type="button" class="btn btn-danger btn-xs" onclick="deleteJudge(${judges.judgeId!})"><i class="fa fa-fw fa-trash"></i></button>
                                  </td>
                              </tr>
                              </#list>
                              </tbody>
                          </table>
                      </div>
                      <!-- /.box-body -->
                  </div>
                  <!-- /.box -->
              </div>
              <!-- /.col -->
          </div>
          <!-- /.row -->
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


<!--------------------------------------添加的模糊框------------------------>
<form class="form-horizontal">   <!--保证样式水平不混乱-->
    <!-- 模态框（Modal） -->
    <div class="modal fade" id="choiceModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        &times;
                    </button>
                    <h4 class="modal-title" id="myModalLabel">
                        添加新的选择题
                    </h4>
                </div>
                <div class="modal-body">

                    <!---------------------表单-------------------->
                    <div class="form-group">
                        <label for="firstname" class="col-sm-3 control-label">问题</label>
                        <div class="col-sm-7">
                            <input type="hidden" id="quizId" value="${quiz.quizId}">
                            <input type="text" class="form-control" id="choice_question"  placeholder="请输入选择题问题">
                            <label class="control-label" for="choice_question" style="display:none;"></label>
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="firstname" class="col-sm-3 control-label">选项A</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="optionA"  placeholder="请输入选项A答案">
                            <label class="control-label" for="optionA" style="display:none;"></label>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="firstname" class="col-sm-3 control-label">选项B</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="optionB"  placeholder="请输入选项B答案">
                            <label class="control-label" for="optionB" style="display:none;"></label>
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="firstname" class="col-sm-3 control-label">选项C</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="optionC"  placeholder="请输入选项C答案">
                            <label class="control-label" for="optionC" style="display:none;"></label>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="firstname" class="col-sm-3 control-label">选项D</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="optionD"  placeholder="请输入选项D答案">
                            <label class="control-label" for="optionC" style="display:none;"></label>
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="firstname" class="col-sm-3 control-label">正确答案</label>
                        <div class="col-sm-7">
                            <select class="form-control" id="choice_answer">
                                <option value="0">请选择</option>
                                <option value="A">A</option>
                                <option value="B">B</option>
                                <option value="C">C</option>
                                <option value="D">D</option>
                            </select>
                            <label class="control-label" for="choice_answer" style="display: none;"></label>
                        </div>
                    </div>

                    <!---------------------表单-------------------->
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal"><i class="fa fa-close"></i> 关闭
                    </button>
                    <button type="button" class="btn btn-primary" id="addChoice"><i class="fa fa-save"></i>
                        保存
                    </button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>

</form>
<!--------------------------------------添加的模糊框------------------------>





<!--------------------------------------添加的模糊框------------------------>
<form class="form-horizontal">   <!--保证样式水平不混乱-->
    <!-- 模态框（Modal） -->
    <div class="modal fade" id="judgeModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        &times;
                    </button>
                    <h4 class="modal-title" id="myModalLabel">
                        添加新的判断题
                    </h4>
                </div>
                <div class="modal-body">

                    <!---------------------表单-------------------->
                    <div class="form-group">
                        <label for="firstname" class="col-sm-3 control-label">问题</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="judge_question"  placeholder="请输入选择题问题">
                            <label class="control-label" for="judge_question" style="display:none;"></label>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="firstname" class="col-sm-3 control-label">正确答案</label>
                        <div class="col-sm-7">
                            <select class="form-control" id="judge_answer">
                                <option value="0">请选择</option>
                                <option value="Y">对</option>
                                <option value="N">错</option>
                            </select>
                            <label class="control-label" for="judge_answer" style="display: none;"></label>
                        </div>
                    </div>

                    <!---------------------表单-------------------->
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal"><i class="fa fa-close"></i> 关闭
                    </button>
                    <button type="button" class="btn btn-primary" id="addJudge"><i class="fa fa-save"></i>
                        保存
                    </button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>

</form>
<!--------------------------------------添加的模糊框------------------------>



<!-- 修改模态框（Modal） -->
<!-------------------------------------------------------------->

<!-- 修改模态框（Modal） -->
<form class="form-horizontal">   <!--保证样式水平不混乱-->
    <div class="modal fade" id="updateChoiceModal" tabindex="-1" role="dialog" aria-labelledby="updateChoiceModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        &times;
                    </button>
                    <h4 class="modal-title" id="updateChoiceModalLabel">
                        修改选择题
                    </h4>
                </div>
                <div class="modal-body">

                    <!---------------------表单-------------------->
                    <div class="form-group">
                        <label for="firstname" class="col-sm-3 control-label">问题</label>
                        <div class="col-sm-7">
                            <input type="hidden" class="form-control" id="updateChoiceId">
                            <input type="text" class="form-control" id="updateChoice_question"  placeholder="请输入选择题问题">
                            <label class="control-label" for="updateChoice_question" style="display:none;"></label>
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="firstname" class="col-sm-3 control-label">选项A</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="updateOptionA"  placeholder="请输入选项A答案">
                            <label class="control-label" for="updateOptionA" style="display:none;"></label>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="firstname" class="col-sm-3 control-label">选项B</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="updateOptionB"  placeholder="请输入选项B答案">
                            <label class="control-label" for="updateOptionB" style="display:none;"></label>
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="firstname" class="col-sm-3 control-label">选项C</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="updateOptionC"  placeholder="请输入选项C答案">
                            <label class="control-label" for="updateOptionC" style="display:none;"></label>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="firstname" class="col-sm-3 control-label">选项D</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="updateOptionD"  placeholder="请输入选项D答案">
                            <label class="control-label" for="updateOptionD" style="display:none;"></label>
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="firstname" class="col-sm-3 control-label">正确答案</label>
                        <div class="col-sm-7">
                            <select class="form-control" id="updateChoice_answer">
                                <option value="0">请选择</option>
                                <option value="A">A</option>
                                <option value="B">B</option>
                                <option value="C">C</option>
                                <option value="D">D</option>
                            </select>
                            <label class="control-label" for="updateChoice_answer" style="display: none;"></label>
                        </div>
                    </div>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal"><i class="fa fa-close"></i> 关闭
                    </button>
                    <button type="button" class="btn btn-primary" id="updateChoice"><i class="fa fa-save"></i>
                        保存
                    </button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>

</form>
<!-------------------------------------------------------------->






<!-- 修改模态框（Modal） -->
<!-------------------------------------------------------------->

<!-- 修改模态框（Modal） -->
<form class="form-horizontal">   <!--保证样式水平不混乱-->
    <div class="modal fade" id="updateJudgeModal" tabindex="-1" role="dialog" aria-labelledby="updateJudgeModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        &times;
                    </button>
                    <h4 class="modal-title" id="updateJudgeModalLabel">
                        修改判断题
                    </h4>
                </div>
                <div class="modal-body">

                    <!---------------------表单-------------------->
                    <div class="form-group">
                        <label for="firstname" class="col-sm-3 control-label">问题</label>
                        <div class="col-sm-7">
                            <input type="hidden" class="form-control" id="updateJudgeId" >
                            <input type="text" class="form-control" id="updateJudge_question"  placeholder="请输入选择题问题">
                            <label class="control-label" for="updateJudge_question" style="display:none;"></label>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="firstname" class="col-sm-3 control-label">正确答案</label>
                        <div class="col-sm-7">
                            <select class="form-control" id="updateJudge_answer">
                                <option value="0">请选择</option>
                                <option value="Y">对</option>
                                <option value="N">错</option>
                            </select>
                            <label class="control-label" for="updateJudge_answer" style="display: none;"></label>
                        </div>
                    </div>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal"><i class="fa fa-close"></i> 关闭
                    </button>
                    <button type="button" class="btn btn-primary" id="updateJudge"><i class="fa fa-save"></i>
                        保存
                    </button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>

</form>
<!-------------------------------------------------------------->



<!--------------------------------------查看的模糊框------------------------>
<form class="form-horizontal">   <!--保证样式水平不混乱-->
    <!-- 模态框（Modal） -->
    <div class="modal fade" id="findChoiceModal" tabindex="-1" role="dialog" aria-labelledby="findChoiceModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        &times;
                    </button>
                    <h4 class="modal-title" id="findChoiceModalLabel">
                        查看选择题信息
                    </h4>
                </div>
                <div class="modal-body">

                    <!---------------------表单-------------------->
                    <div class="form-group">
                        <label for="firstname" class="col-sm-3 control-label">所属年级</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="findChoice_grade" readonly="readonly">
                            <label class="control-label" for="findChoice_grade" style="display:none;"></label>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="firstname" class="col-sm-3 control-label">所属课程</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="findChoice_subject" readonly="readonly">
                            <label class="control-label" for="findChoice_subject" style="display:none;"></label>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="firstname" class="col-sm-3 control-label">所属试卷</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="findChoice_quiz" readonly="readonly">
                            <label class="control-label" for="findChoice_quiz" style="display:none;"></label>
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="firstname" class="col-sm-3 control-label">问题</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="findChoice_question" readonly="readonly">
                            <label class="control-label" for="findChoice_question" style="display:none;"></label>
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="firstname" class="col-sm-3 control-label">选项A</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="findOptionA" readonly="readonly">
                            <label class="control-label" for="findOptionA" style="display:none;"></label>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="firstname" class="col-sm-3 control-label">选项B</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="findOptionB" readonly="readonly">
                            <label class="control-label" for="findOptionB" style="display:none;"></label>
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="firstname" class="col-sm-3 control-label">选项C</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="findOptionC" readonly="readonly">
                            <label class="control-label" for="findOptionC" style="display:none;"></label>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="firstname" class="col-sm-3 control-label">选项D</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="findOptionD" readonly="readonly">
                            <label class="control-label" for="findOptionD" style="display:none;"></label>
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="firstname" class="col-sm-3 control-label">正确答案</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="findChoice_answer" readonly="readonly">
                            <label class="control-label" for="findChoice_answer" style="display:none;"></label>
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="firstname" class="col-sm-3 control-label">分值</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="findChoice_score" readonly="readonly">
                            <label class="control-label" for="findChoice_score" style="display:none;"></label>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal"><i class="fa fa-close"></i> 关闭
                    </button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>

</form>
<!--------------------------------------查看的模糊框------------------------>




<!--------------------------------------查看的模糊框------------------------>
<form class="form-horizontal">   <!--保证样式水平不混乱-->
    <!-- 模态框（Modal） -->
    <div class="modal fade" id="findJudgeModal" tabindex="-1" role="dialog" aria-labelledby="findJudgeModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        &times;
                    </button>
                    <h4 class="modal-title" id="findJudgeModalLabel">
                        查看判断题信息
                    </h4>
                </div>
                <div class="modal-body">

                    <!---------------------表单-------------------->
                    <div class="form-group">
                        <label for="firstname" class="col-sm-3 control-label">所属年级</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="findJudge_grade" readonly="readonly">
                            <label class="control-label" for="findJudge_grade" style="display:none;"></label>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="firstname" class="col-sm-3 control-label">所属课程</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="findJudge_subject" readonly="readonly">
                            <label class="control-label" for="findJudge_subject" style="display:none;"></label>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="firstname" class="col-sm-3 control-label">所属试卷</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="findJudge_quiz" readonly="readonly">
                            <label class="control-label" for="findJudge_quiz" style="display:none;"></label>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="firstname" class="col-sm-3 control-label">问题</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="findJudge_question" readonly="readonly">
                            <label class="control-label" for="findJudge_question" style="display:none;"></label>
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="firstname" class="col-sm-3 control-label">正确答案</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="findJudge_answer" readonly="readonly">
                            <label class="control-label" for="findJudge_answer" style="display:none;"></label>
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="firstname" class="col-sm-3 control-label">分值</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="findJudge_score" readonly="readonly">
                            <label class="control-label" for="findJudge_score" style="display:none;"></label>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal"><i class="fa fa-close"></i> 关闭
                    </button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>

</form>
<!--------------------------------------查看的模糊框------------------------>





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
<!-- DataTables -->
<script src="${base}/static/bower_components/datatables.net/js/jquery.dataTables.min.js"></script>
<script src="${base}/static/bower_components/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
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
<script src="${base}/static/js/teacher/quiz/addChoice.js"></script>
<script src="${base}/static/js/teacher/quiz/addJudge.js"></script>
<script src="${base}/static/js/teacher/quiz/getChoice.js"></script>
<script src="${base}/static/js/teacher/quiz/getJudge.js"></script>
<script src="${base}/static/js/teacher/quiz/updateChoice.js"></script>
<script src="${base}/static/js/teacher/quiz/updateJudge.js"></script>
<script src="${base}/static/js/teacher/quiz/deleteChoice.js"></script>
<script src="${base}/static/js/teacher/quiz/deleteJudge.js"></script>
<script>
    $(function () {
        $('#example2').DataTable({
            'paging'      : true,
            'lengthChange': false,
            'searching'   : true,
            'ordering'    : true,
            'info'        : true,
            'autoWidth'   : false,
            language: {
                "sProcessing": "处理中...",
                "sLengthMenu": "显示 _MENU_ 项结果",
                "sZeroRecords": "没有匹配结果",
                "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
                "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
                "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
                "sInfoPostFix": "",
                "sSearch": "搜索:",
                "sUrl": "",
                "sEmptyTable": "表中数据为空",
                "sLoadingRecords": "载入中...",
                "sInfoThousands": ",",
                "oPaginate": {
                    "sFirst": "首页",
                    "sPrevious": "&laquo;",
                    "sNext": "&raquo;",
                    "sLast": "末页"
                },
                "oAria": {
                    "sSortAscending": ": 以升序排列此列",
                    "sSortDescending": ": 以降序排列此列"
                }
            }
        })
    })
</script>
</body>
</html>
