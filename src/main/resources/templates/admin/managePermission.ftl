<#assign base=request.contextPath />
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>CIYOU | 权限管理</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.7 -->
    <link rel="stylesheet" href="${base}/static/bower_components/bootstrap/dist/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="${base}/static/bower_components/font-awesome/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="${base}/static/bower_components/Ionicons/css/ionicons.min.css">
    <!-- DataTables -->
    <link rel="stylesheet" href="${base}/static/bower_components/datatables.net-bs/css/dataTables.bootstrap.min.css">
    <!-- treeview -->
    <link rel="stylesheet" href="${base}/static/bower_components/bootstrap-treeview/bootstrap-treeview.min.css" />
    <!-- bootstrap-validator -->
    <link rel="stylesheet" href="${base}/static/bower_components/bootstrap-validator/dist/css/bootstrap-validator.css"/>

    <!-- Theme style -->
    <link rel="stylesheet" href="${base}/static/dist/css/AdminLTE.min.css">
    <!-- AdminLTE Skins. Choose a skin from the css/skins
         folder instead of downloading all of them to reduce the load. -->
    <link rel="stylesheet" href="${base}/static/dist/css/skins/_all-skins.min.css">


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
                <li class="active">
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
                <li>
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
        权限管理
        <small></small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="${base}/admin/admin"><i class="fa fa-home"></i> 首页</a></li>
        <li class="active"><i class="fa fa-unlock-alt"></i>  权限管理</li>
      </ol>
    </section>


      <!-- Main content -->
      <section class="content">

          <div class="row">
              <div class="col-md-3">

                  <!-- Profile Image -->
                  <div class="box">
                      <div class="box-body box-profile">
                          <div id="tree" class="treeview">
                          </div>
                      </div>
                      <!-- /.box-body -->
                  </div>
                  <!-- /.box -->
              </div>
              <!-- /.col -->
              <div class="col-md-9">
                  <div class="nav-tabs-custom">
                      <ul class="nav nav-tabs" id="myTab">
                          <li class=""><a href="#view" data-toggle="tab" aria-expanded="true">查看权限详情</a></li>
                          <li class=""><a href="#root" data-toggle="tab" aria-expanded="false">添加根权限</a></li>
                          <li class=""><a href="#add" data-toggle="tab" aria-expanded="false">添加子权限</a></li>
                          <li class=""><a href="#edit" data-toggle="tab" aria-expanded="false">编辑权限</a></li>
                          <li class=""><a href="javascript:void(0)" onclick="deletePermission()" data-toggle="tab" aria-expanded="false">删除权限</a></li>
                      </ul>
                      <div class="tab-content">
                          <div class="tab-pane fade in active" id="view">
                              <form class="form-horizontal bv-form" id="dict-form-view" novalidate="novalidate">
                                  <div class="form-group">
                                      <label for="viewParentId" class="col-sm-2 control-label">父权限</label>
                                      <div class="col-sm-9">
                                          <input type="text" class="form-control" disabled="disabled" id="viewParentId" name="viewParentId" placeholder="系统权限" readonly="readonly">
                                      </div>
                                  </div>

                                  <div class="form-group has-feedback">
                                      <label for="viewName" class="col-sm-2 control-label">权限名</label>
                                      <div class="col-sm-9">
                                          <input type="text" class="form-control" id="viewName" name="viewName" placeholder="权限名" data-bv-field="viewName" readonly="readonly">
                                          <i class="form-control-feedback" data-bv-icon-for="viewName" style="display: none;"></i>
                                          <small data-bv-validator="notEmpty" data-bv-validator-for="viewName" class="help-block" style="display: none;"></small></div>
                                  </div>
                                  <div class="form-group has-feedback">
                                      <label for="viewPermission" class="col-sm-2 control-label">权限字符串</label>
                                      <div class="col-sm-9">
                                          <input type="text" class="form-control" id="viewPermission" name="viewPermission" placeholder="权限字符串" data-bv-field="viewPermission" readonly="readonly">
                                          <i class="form-control-feedback" data-bv-icon-for="viewPermission" style="display: none;"></i>
                                          <small data-bv-validator="notEmpty" data-bv-validator-for="viewPermission" class="help-block" style="display: none;"></small>
                                      </div>
                                  </div>

                                  <div class="form-group has-feedback">
                                      <label for="viewURL" class="col-sm-2 control-label">资源路径</label>
                                      <div class="col-sm-9">
                                          <input type="text" class="form-control" id="viewURL" name="viewURL" placeholder="资源路径" data-bv-field="viewURL" readonly="readonly">
                                          <i class="form-control-feedback" data-bv-icon-for="viewURL" style="display: none;"></i>
                                          <small data-bv-validator="notEmpty" data-bv-validator-for="viewURL" class="help-block" style="display: none;"></small></div>
                                  </div>
                              </form>
                          </div>

                          <div class="tab-pane fade" id="root">
                              <form class="form-horizontal bv-form" id="dict-form-root" novalidate="novalidate">
                                  <div class="form-group">
                                      <label for="addRootParentId" class="col-sm-2 control-label">父权限</label>
                                      <div class="col-sm-9">
                                          <input type="text" class="form-control" disabled="disabled" id="addRootParentId" name="addRootParentId" placeholder="系统权限" readonly="readonly">
                                      </div>
                                  </div>

                                  <div class="form-group has-feedback">
                                      <label for="addRootName" class="col-sm-2 control-label">权限名</label>
                                      <div class="col-sm-9">
                                          <input type="text" class="form-control" id="addRootName" name="addRootName" placeholder="权限名" data-bv-field="addRootName">
                                          <i class="form-control-feedback" data-bv-icon-for="addRootName" style="display: none;"></i>
                                          <small data-bv-validator="notEmpty" data-bv-validator-for="addRootName" class="help-block" style="display: none;"></small></div>
                                  </div>
                                  <div class="form-group has-feedback">
                                      <label for="addRootPermission" class="col-sm-2 control-label">权限字符串</label>
                                      <div class="col-sm-9">
                                          <input type="text" class="form-control" id="addRootPermission" name="addRootPermission" placeholder="权限字符串" data-bv-field="addRootPermission">
                                          <i class="form-control-feedback" data-bv-icon-for="addRootPermission" style="display: none;"></i>
                                          <small data-bv-validator="notEmpty" data-bv-validator-for="addRootPermission" class="help-block" style="display: none;"></small>
                                      </div>
                                  </div>

                                  <div class="form-group has-feedback">
                                      <label for="addRootURL" class="col-sm-2 control-label">资源路径</label>
                                      <div class="col-sm-9">
                                          <input type="text" class="form-control" id="addRootURL" name="addRootURL" placeholder="资源路径" data-bv-field="addRootURL">
                                          <i class="form-control-feedback" data-bv-icon-for="addRootURL" style="display: none;"></i>
                                          <small data-bv-validator="notEmpty" data-bv-validator-for="addRootURL" class="help-block" style="display: none;"></small></div>
                                  </div>
                                  <div class="box-footer">
                                      <div class="text-center">
                                          <button type="reset" class="btn btn-default" data-btn-type="cancel" id="rootCancel">
                                              <i class="fa fa-reply">&nbsp;取消</i>
                                          </button>
                                          <button type="button" class="btn btn-primary" id="rootButton">
                                              <i class="fa fa-save">&nbsp;保存</i>
                                          </button>
                                      </div>
                                  </div>
                              </form>
                          </div>
                          <!-- /.tab-pane -->
                          <div class="tab-pane fade " id="add">
                              <form class="form-horizontal bv-form" id="dict-form-add" novalidate="novalidate">
                                  <div class="form-group">
                                      <label for="addParentId" class="col-sm-2 control-label">父权限</label>
                                      <div class="col-sm-9">
                                          <input type="hidden" name="addPid" id="addPid">
                                          <input type="text" class="form-control" disabled="disabled" id="addParentId" name="addParentId" placeholder="系统权限" readonly="readonly">
                                      </div>
                                  </div>

                                  <div class="form-group has-feedback">
                                      <label for="addName" class="col-sm-2 control-label">权限名</label>
                                      <div class="col-sm-9">
                                          <input type="text" class="form-control" id="addName" name="addName" placeholder="权限名" data-bv-field="addName">
                                          <i class="form-control-feedback" data-bv-icon-for="addName" style="display: none;"></i>
                                          <small data-bv-validator="notEmpty" data-bv-validator-for="addName" class="help-block" style="display: none;"></small></div>
                                  </div>
                                  <div class="form-group has-feedback">
                                      <label for="addPermission" class="col-sm-2 control-label">权限字符串</label>
                                      <div class="col-sm-9">
                                          <input type="text" class="form-control" id="addPermission" name="addPermission" placeholder="权限字符串" data-bv-field="addPermission">
                                          <i class="form-control-feedback" data-bv-icon-for="addPermission" style="display: none;"></i>
                                          <small data-bv-validator="notEmpty" data-bv-validator-for="addPermission" class="help-block" style="display: none;"></small>
                                      </div>
                                  </div>

                                  <div class="form-group has-feedback">
                                      <label for="addURL" class="col-sm-2 control-label">资源路径</label>
                                      <div class="col-sm-9">
                                          <input type="text" class="form-control" id="addURL" name="addURL" placeholder="资源路径" data-bv-field="addURL">
                                          <i class="form-control-feedback" data-bv-icon-for="addURL" style="display: none;"></i>
                                          <small data-bv-validator="notEmpty" data-bv-validator-for="addURL" class="help-block" style="display: none;"></small></div>
                                  </div>
                                  <div class="box-footer">
                                      <div class="text-center">
                                          <button type="reset" class="btn btn-default" data-btn-type="cancel" id="addCancel">
                                              <i class="fa fa-reply">&nbsp;取消</i>
                                          </button>
                                          <button type="button" class="btn btn-primary" id="addButton">
                                              <i class="fa fa-save">&nbsp;保存</i>
                                          </button>
                                      </div>
                                  </div>
                              </form>
                          </div>
                          <!-- /.tab-pane -->
                          <div class="tab-pane fade " id="edit">
                              <form class="form-horizontal bv-form" id="dict-form-edit" novalidate="novalidate">
                                  <div class="form-group">
                                      <label for="editParentId" class="col-sm-2 control-label">父权限</label>
                                      <div class="col-sm-9">
                                          <input type="hidden" name="editId" id="editId">
                                          <input type="text" class="form-control" disabled="disabled" id="editParentId" name="editParentId" placeholder="系统权限" readonly="readonly">
                                      </div>
                                  </div>

                                  <div class="form-group has-feedback">
                                      <label for="editName" class="col-sm-2 control-label">权限名</label>
                                      <div class="col-sm-9">
                                          <input type="text" class="form-control" id="editName" name="editName" placeholder="权限名" data-bv-field="editName">
                                          <i class="form-control-feedback" data-bv-icon-for="editName" style="display: none;"></i>
                                          <small data-bv-validator="notEmpty" data-bv-validator-for="editName" class="help-block" style="display: none;"></small></div>
                                  </div>
                                  <div class="form-group has-feedback">
                                      <label for="editPermission" class="col-sm-2 control-label">权限字符串</label>
                                      <div class="col-sm-9">
                                          <input type="text" class="form-control" id="editPermission" name="editPermission" placeholder="权限字符串" data-bv-field="editPermission">
                                          <i class="form-control-feedback" data-bv-icon-for="editPermission" style="display: none;"></i>
                                          <small data-bv-validator="notEmpty" data-bv-validator-for="editPermission" class="help-block" style="display: none;"></small>
                                      </div>
                                  </div>

                                  <div class="form-group has-feedback">
                                      <label for="editURL" class="col-sm-2 control-label">资源路径</label>
                                      <div class="col-sm-9">
                                          <input type="text" class="form-control" id="editURL" name="editURL" placeholder="资源路径" data-bv-field="editURL">
                                          <i class="form-control-feedback" data-bv-icon-for="editURL" style="display: none;"></i>
                                          <small data-bv-validator="notEmpty" data-bv-validator-for="editURL" class="help-block" style="display: none;"></small></div>
                                  </div>
                                  <div class="box-footer">
                                      <div class="text-center">
                                          <button type="reset" class="btn btn-default" data-btn-type="cancel" id="editCancel">
                                              <i class="fa fa-reply">&nbsp;取消</i>
                                          </button>
                                          <button type="button" class="btn btn-primary" id="editButton">
                                              <i class="fa fa-save">&nbsp;保存</i>
                                          </button>
                                      </div>
                                  </div>
                              </form>
                          </div>
                          <!-- /.tab-pane -->
                      </div>
                      <!-- /.tab-content -->
                  </div>
                  <!-- /.nav-tabs-custom -->
              </div>
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
<!-- FastClick -->
<script src="${base}/static/bower_components/fastclick/lib/fastclick.js"></script>
<!-- treeview -->
<script src="${base}/static/bower_components/bootstrap-treeview/bootstrap-treeview.js"></script>
<!-- bootstrap-validator -->
<script src="${base}/static/bower_components/bootstrap-validator/dist/js/bootstrap-validator.js"></script>
<!-- AdminLTE App -->
<script src="${base}/static/dist/js/adminlte.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="${base}/static/dist/js/demo.js"></script>

<script src="${base}/static/js/admin/admin/adminUpdatePwd.js"></script>
<script src="${base}/static/js/admin/admin/adminUpdateInfo.js"></script>
<script src="${base}/static/js/admin/permission/permissionTree.js"></script>
<script src="${base}/static/js/admin/permission/permissionValidator.js"></script>
<script src="${base}/static/js/admin/permission/addRootPermission.js"></script>
<script src="${base}/static/js/admin/permission/addPermission.js"></script>
<script src="${base}/static/js/admin/permission/editPermission.js"></script>
<script src="${base}/static/js/admin/permission/deletePermission.js"></script>
</body>
</html>
