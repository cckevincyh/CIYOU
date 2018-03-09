<#assign base=request.contextPath />
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>CIYOU | 视频管理</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.7 -->
    <link rel="stylesheet" href="${base}/static/bower_components/bootstrap/dist/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="${base}/static/bower_components/font-awesome/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="${base}/static/bower_components/Ionicons/css/ionicons.min.css">
    <!-- treeview -->
    <link rel="stylesheet" href="${base}/static/bower_components/bootstrap-treeview/bootstrap-treeview.min.css" />
    <!-- DataTables -->
    <link rel="stylesheet" href="${base}/static/bower_components/datatables.net-bs/css/dataTables.bootstrap.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="${base}/static/dist/css/AdminLTE.min.css">
    <!-- AdminLTE Skins. Choose a skin from the css/skins
         folder instead of downloading all of them to reduce the load. -->
    <link rel="stylesheet" href="${base}/static/dist/css/skins/_all-skins.min.css">
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
    <a href="${base}/admin/admin" class="logo">
      <!-- mini logo for sidebar mini 50x50 pixels -->
      <span class="logo-mini"><b>C</b>.</span>
      <!-- logo for regular state and mobile devices -->
      <span class="logo-lg"><b>CI</b>YOU</span>
    </a>
    <!-- Header Navbar: style can be found in header.less -->
    <!-- 顶部菜单栏 -->
    <nav class="navbar navbar-static-top">
      <!-- Sidebar toggle button-->
      <a href="${base}/admin/admin" class="sidebar-toggle" data-toggle="push-menu" role="button">
        <span class="sr-only">Toggle navigation</span>
      </a>

     <!-- 顶部菜单栏 Message，Notifications：消息提示信息菜单栏-->
      <div class="navbar-custom-menu">
        <ul class="nav navbar-nav">
          <!-- 个人信息-->
          <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <span class="hidden-xs"><i class="glyphicon glyphicon-user"></i>   ${Session.admin.name!}</span>
            </a>
              <ul class="dropdown-menu">
                  <li><a href="#updateinfo" data-toggle="modal">个人资料</a></li>
                  <li role="presentation" class="divider"></li>
                  <li><a href="#updatepwd" data-toggle="modal">修改密码</a></li>
                  <li role="presentation" class="divider"></li>
                  <li><a href="${base}/admin/adminLogout">退出</a></li>
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

            <ul class="sidebar-menu" data-widget="tree">
                <li class="header">菜单导航</li>
                <li class="treeview">
                    <a href="#">
                        <i class="fa fa-group"></i> <span>用户管理</span>
                        <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
                    </a>
                    <ul class="treeview-menu">
                        <li><a href="${base}/admin/manageAdmin"><i class="fa fa-user"></i> 管理员管理</a></li>
                        <li><a href="${base}/admin/manageTeacher"><i class="fa fa-user"></i> 教师管理</a></li>
                        <li><a href="${base}/admin/manageStudent"><i class="fa fa-user"></i> 学生管理</a></li>
                    </ul>
                </li>
                <li>
                    <a href="${base}/admin/managePermission">
                        <i class="fa  fa-unlock-alt"></i>
                        <span>权限管理</span>
                    </a>
                </li>
                <li>
                    <a href="${base}/admin/manageGrade">
                        <i class="fa fa-graduation-cap"></i> <span>年级管理</span>
                    </a>
                </li>
                <li>
                    <a href="${base}/admin/manageClasses">
                        <i class="fa fa-graduation-cap"></i> <span>班级管理</span>
                    </a>
                </li>
                <li>
                    <a href="${base}/admin/manageSubject">
                        <i class="fa  fa-book"></i> <span>科目管理</span>
                    </a>
                </li>
                <li class="active">
                    <a href="${base}/admin/checkVideo">
                        <i class="fa fa-video-camera"></i> <span>视频管理</span>
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
        视频管理
        <small>视频审核</small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="${base}/admin/admin"><i class="fa fa-home"></i> 首页</a></li>
        <li class="active"><i class="fa fa-video-camera"></i> 视频管理</li>
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
                          <div class="box-tools">
                              <form class="form-horizontal" action="${base}/admin/queryVideo" method="get">
                                  <div class="input-group input-group-sm" style="width: 150px;">
                                      <input type="text" name="searchContent" class="form-control pull-right" placeholder="Search">
                                      <div class="input-group-btn">
                                          <button type="submit" class="btn btn-default"><i class="fa fa-search"></i></button>
                                      </div>
                                  </div>
                              </form>
                          </div>
                      </div>
                      <!-- /.box-header -->
                      <div class="box-body table-responsive no-padding">
                          <table class="table table-hover">
                              <tr>
                                  <th>ID</th>
                                  <th>视频名称</th>
                                  <th>视频</th>
                                  <th>年级</th>
                                  <th>科目</th>
                                  <th>教师</th>
                                  <th>创建时间</th>
                                  <th>状态</th>
                                  <th>审核</th>
                              </tr>
                          <#if pageInfo?? && pageInfo.list?? && (pageInfo.list?size > 0) >
                              <#list pageInfo.list as video>
                                  <tr>
                                      <td>${video.videoId!}</td>
                                      <td>${video.videoName!}</td>
                                      <td>
                                          <video class="video-js vjs-default-skin vjs-big-play-centered" controls  preload="auto" poster="${video.imgUrl!}" width="250" height="150" data-setup="{}">
                                              <source src="${base}${video.videoUrl!}" type="video/mp4"/>
                                          </video>
                                      </td>
                                      <#if video.grade??>
                                          <td>${video.grade.gradeName!}</td>
                                      <#else >
                                          <td></td>
                                      </#if>
                                      <#if video.subject??>
                                          <td>${video.subject.subjectName!}</td>
                                      <#else >
                                          <td></td>
                                      </#if>
                                      <#if video.teacher??>
                                          <td>${video.teacher.name!}</td>
                                      <#else >
                                          <td></td>
                                      </#if>
                                      <td>${video.createTime?string("yyyy-MM-dd HH:mm")!}</td>
                                      <#if video.videoType == 1>
                                          <td>未审核</td>
                                      <#elseif video.videoType == 2>
                                          <td>通过审核</td>
                                      </#if>
                                      <td>
                                          <button class="btn btn-success btn-xs"  onclick="passVideo(${video.videoId!})"><i class="fa fa-fw fa-check"></i></button>
                                          <button class="btn btn-danger btn-xs"  onclick="notPassVideo(${video.videoId!})"><i class="fa fa-fw fa-close"></i></button>
                                      </td>
                                  </tr>
                              </#list>
                          <#else >
                              <tr>
                                  <td colspan="9" align="center">暂无数据</td>
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





<!--个人资料模糊框------->

<form class="form-horizontal">   <!--保证样式水平不混乱-->
    <!-- 模态框（Modal） -->
    <div class="modal fade" id="updateinfo" tabindex="-1" role="dialog" aria-labelledby="ModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        &times;
                    </button>
                    <h4 class="modal-title" id="ModalLabel">
                        个人资料
                    </h4>
                </div>

                <div class="modal-body">

                    <!--正文-->
                    <div class="form-group">
                        <label for="firstname" class="col-sm-3 control-label">用户名</label>
                        <div class="col-sm-7">
                            <input type="hidden" id="adminId" value="${Session.admin.adminId!}">
                            <input type="text" class="form-control" id="username"  value="${Session.admin.adminName!}" readonly="readonly">
                            <label class="control-label" for="username" style="display:none;"></label>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="firstname" class="col-sm-3 control-label">真实姓名</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="name"  placeholder="请输入您的真实姓名" value="${Session.admin.name!}">
                            <label class="control-label" for="name" style="display:none;"></label>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="firstname" class="col-sm-3 control-label">联系号码</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="phone"  placeholder="请输入您的联系号码" value="${Session.admin.phone!}">
                            <label class="control-label" for="phone" style="display:none;"></label>
                        </div>
                    </div>

                    <!--正文-->

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal"><i class="fa fa-close"></i>关闭
                    </button>
                    <button type="button" class="btn btn-primary" id="admin_updateInfo" ><i class="fa fa-save"></i>
                        保存
                    </button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>

</form>


<!------修改密码模糊框------->

<form class="form-horizontal">   <!--保证样式水平不混乱-->
    <!-- 模态框（Modal） -->
    <div class="modal fade" id="updatepwd" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        &times;
                    </button>
                    <h4 class="modal-title" id="myModalLabel">
                        修改密码
                    </h4>
                </div>

                <div class="modal-body">

                    <!--正文-->
                    <div class="form-group">
                        <label for="firstname" class="col-sm-3 control-label">原密码</label>
                        <div class="col-sm-7">
                            <input type="password" class="form-control" id="oldPwd"  placeholder="请输入原密码">
                            <label class="control-label" for="oldPwd" style="display:none;"></label>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="firstname" class="col-sm-3 control-label">新密码</label>
                        <div class="col-sm-7">
                            <input type="password" class="form-control" id="newPwd"  placeholder="请输入新密码">
                            <label class="control-label" for="newPwd" style="display:none;"></label>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="firstname" class="col-sm-3 control-label">确认密码</label>
                        <div class="col-sm-7">
                            <input type="password" class="form-control" id="confirmPwd"  placeholder="请输入确认密码">
                            <label class="control-label" for="confirmPwd" style="display:none;"></label>
                        </div>
                    </div>
                    <!--正文-->


                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal"><i class="fa fa-close"></i>关闭
                    </button>
                    <button type="button" class="btn btn-primary" id="update_adminPwd"><i class="fa fa-save"></i>
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
<!-- Bootstrap 3.3.7 -->
<script src="${base}/static/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- SlimScroll -->
<script src="${base}/static/bower_components/jquery-slimscroll/jquery.slimscroll.min.js"></script>
<!-- treeview -->
<script src="${base}/static/bower_components/bootstrap-treeview/bootstrap-treeview.js"></script>
<!-- FastClick -->
<script src="${base}/static/bower_components/fastclick/lib/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="${base}/static/dist/js/adminlte.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="${base}/static/dist/js/demo.js"></script>

<script src="${base}/static/js/admin/admin/adminUpdatePwd.js"></script>
<script src="${base}/static/js/admin/admin/adminUpdateInfo.js"></script>
<script src="${base}/static/js/admin/video/checkVideo.js"></script>
<script type="text/javascript" src="${base}/static/video/js/video.min.js"></script>
<script>
    //去除右键事件
    $("video").bind("contextmenu",function(){//取消右键事件
        return false;
    });
</script>
</body>
</html>
