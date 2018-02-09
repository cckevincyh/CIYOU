<#assign base=request.contextPath />
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>CIYOU | 管理员管理</title>
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
    <a href="#" class="logo">
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
          <!-- Messages: style can be found in dropdown.less-->
          <!-- messages-menu信封标志的菜单栏 -->
          <li class="dropdown messages-menu">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <i class="fa fa-envelope-o"></i>
              <span class="label label-success">4</span>
            </a>
            <ul class="dropdown-menu">
              <li class="header">You have 4 messages</li>
              <li>
                <!-- inner menu: contains the actual data -->
                <ul class="menu">
                  <li><!-- start message -->
                    <a href="#">
                      <div class="pull-left">
                        <img src="${base}/static/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
                      </div>
                      <h4>
                        Support Team
                        <small><i class="fa fa-clock-o"></i> 5 mins</small>
                      </h4>
                      <p>Why not buy a new awesome theme?</p>
                    </a>
                  </li>
                  <!-- end message -->
                  <li>
                    <a href="#">
                      <div class="pull-left">
                        <img src="${base}/static/dist/img/user3-128x128.jpg" class="img-circle" alt="User Image">
                      </div>
                      <h4>
                        AdminLTE Design Team
                        <small><i class="fa fa-clock-o"></i> 2 hours</small>
                      </h4>
                      <p>Why not buy a new awesome theme?</p>
                    </a>
                  </li>
                  <li>
                    <a href="#">
                      <div class="pull-left">
                        <img src="${base}/static/dist/img/user4-128x128.jpg" class="img-circle" alt="User Image">
                      </div>
                      <h4>
                        Developers
                        <small><i class="fa fa-clock-o"></i> Today</small>
                      </h4>
                      <p>Why not buy a new awesome theme?</p>
                    </a>
                  </li>
                  <li>
                    <a href="#">
                      <div class="pull-left">
                        <img src="${base}/static/dist/img/user3-128x128.jpg" class="img-circle" alt="User Image">
                      </div>
                      <h4>
                        Sales Department
                        <small><i class="fa fa-clock-o"></i> Yesterday</small>
                      </h4>
                      <p>Why not buy a new awesome theme?</p>
                    </a>
                  </li>
                  <li>
                    <a href="#">
                      <div class="pull-left">
                        <img src="${base}/static/dist/img/user4-128x128.jpg" class="img-circle" alt="User Image">
                      </div>
                      <h4>
                        Reviewers
                        <small><i class="fa fa-clock-o"></i> 2 days</small>
                      </h4>
                      <p>Why not buy a new awesome theme?</p>
                    </a>
                  </li>
                </ul>
              </li>
              <li class="footer"><a href="#">See All Messages</a></li>
            </ul>
          </li>
          <!-- Notifications: style can be found in dropdown.less -->
          <!-- notifications提示菜单栏 -->
          <li class="dropdown notifications-menu">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <i class="fa fa-bell-o"></i>
              <span class="label label-warning">10</span>
            </a>
            <ul class="dropdown-menu">
              <li class="header">You have 10 notifications</li>
              <li>
                <!-- inner menu: contains the actual data -->
                <ul class="menu">
                  <li>
                    <a href="#">
                      <i class="fa fa-users text-aqua"></i> 5 new members joined today
                    </a>
                  </li>
                  <li>
                    <a href="#">
                      <i class="fa fa-warning text-yellow"></i> Very long description here that may not fit into the
                      page and may cause design problems
                    </a>
                  </li>
                  <li>
                    <a href="#">
                      <i class="fa fa-users text-red"></i> 5 new members joined
                    </a>
                  </li>
                  <li>
                    <a href="#">
                      <i class="fa fa-shopping-cart text-green"></i> 25 sales made
                    </a>
                  </li>
                  <li>
                    <a href="#">
                      <i class="fa fa-user text-red"></i> You changed your username
                    </a>
                  </li>
                </ul>
              </li>
              <li class="footer"><a href="#">View all</a></li>
            </ul>
          </li>
          <!-- Tasks: style can be found in dropdown.less -->
          <li class="dropdown tasks-menu">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <i class="fa fa-flag-o"></i>
              <span class="label label-danger">9</span>
            </a>
            <ul class="dropdown-menu">
              <li class="header">You have 9 tasks</li>
              <li>
                <!-- inner menu: contains the actual data -->
                <ul class="menu">
                  <li><!-- Task item -->
                    <a href="#">
                      <h3>
                        Design some buttons
                        <small class="pull-right">20%</small>
                      </h3>
                      <div class="progress xs">
                        <div class="progress-bar progress-bar-aqua" style="width: 20%" role="progressbar"
                             aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
                          <span class="sr-only">20% Complete</span>
                        </div>
                      </div>
                    </a>
                  </li>
                  <!-- end task item -->
                  <li><!-- Task item -->
                    <a href="#">
                      <h3>
                        Create a nice theme
                        <small class="pull-right">40%</small>
                      </h3>
                      <div class="progress xs">
                        <div class="progress-bar progress-bar-green" style="width: 40%" role="progressbar"
                             aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
                          <span class="sr-only">40% Complete</span>
                        </div>
                      </div>
                    </a>
                  </li>
                  <!-- end task item -->
                  <li><!-- Task item -->
                    <a href="#">
                      <h3>
                        Some task I need to do
                        <small class="pull-right">60%</small>
                      </h3>
                      <div class="progress xs">
                        <div class="progress-bar progress-bar-red" style="width: 60%" role="progressbar"
                             aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
                          <span class="sr-only">60% Complete</span>
                        </div>
                      </div>
                    </a>
                  </li>
                  <!-- end task item -->
                  <li><!-- Task item -->
                    <a href="#">
                      <h3>
                        Make beautiful transitions
                        <small class="pull-right">80%</small>
                      </h3>
                      <div class="progress xs">
                        <div class="progress-bar progress-bar-yellow" style="width: 80%" role="progressbar"
                             aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
                          <span class="sr-only">80% Complete</span>
                        </div>
                      </div>
                    </a>
                  </li>
                  <!-- end task item -->
                </ul>
              </li>
              <li class="footer">
                <a href="#">View all tasks</a>
              </li>
            </ul>
          </li>
          <!-- User Account: style can be found in dropdown.less -->
          <!-- 个人信息-->
          <li class="dropdown user user-menu">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <img src="${base}/static/dist/img/user2-160x160.jpg" class="user-image" alt="User Image">
              <span class="hidden-xs">Alexander Pierce</span>
            </a>
            <ul class="dropdown-menu">
              <!-- User image -->
              <li class="user-header">
                <img src="${base}/static/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">

                <p>
                  Alexander Pierce - Web Developer
                  <small>Member since Nov. 2012</small>
                </p>
              </li>
              <!-- Menu Body -->
        	<!-- 个人信息-->
              <!-- Menu Footer-->
              <li class="user-footer">
                <div class="pull-left">
                  <a href="#" class="btn btn-default btn-flat">Profile</a>
                </div>
                <div class="pull-right">
                  <a href="#" class="btn btn-default btn-flat">Sign out</a>
                </div>
              </li>
            </ul>
          </li>
          <!-- Control Sidebar Toggle Button -->
          <li>
            <a href="#" data-toggle="control-sidebar"><i class="fa fa-gears"></i></a>
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
          <img src="${base}/static/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
        </div>
        <div class="pull-left info">
          <p>Alexander Pierce</p>
          <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
        </div>
      </div>
     
     
      <ul class="sidebar-menu" data-widget="tree">
        <li class="header">菜单导航</li>
        <li class="treeview">
          <a href="#">
            <i class="fa fa-dashboard"></i> <span>用户管理</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li class="active"><a href="#"><i class="fa fa-circle-o"></i> 管理员管理</a></li>
            <li><a href="#"><i class="fa fa-circle-o"></i> 教师管理</a></li>
            <li><a href="#"><i class="fa fa-circle-o"></i> 学生管理</a></li>
          </ul>
        </li>
        <li>
          <a href="#">
            <i class="fa fa-files-o"></i>
            <span>权限管理</span>
          </a>
        </li>
        <li>
          <a href="#">
            <i class="fa fa-th"></i> <span>年级管理</span>
          </a>
        </li>
         <li>
          <a href="#">
            <i class="fa fa-th"></i> <span>班级管理</span>
          </a>
        </li>
         <li>
          <a href="#">
            <i class="fa fa-th"></i> <span>课程管理</span>
          </a>
        </li>
         <li>
          <a href="#">
            <i class="fa fa-th"></i> <span>视频管理</span>
          </a>
		  </li>
		  <li>
          <a href="#">
            <i class="fa fa-th"></i> <span>系统消息</span>
          </a>
		  </li>
		   <li>
          <a href="#">
            <i class="fa fa-th"></i> <span>正在开发</span>
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
        用户管理
        <small>管理员管理</small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> 首页</a></li>
        <li class="active">管理员管理</li>
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
                          <div class="col-md-3 col-sm-4"><button class="btn btn-default btn-xs" id="btn_add" data-toggle="modal" data-target="#addModal"><i class="fa fa-fw fa-user-plus"></i></button> 添加管理员</div>
                          <div class="box-tools">
                              <div class="input-group input-group-sm" style="width: 150px;">
                                  <input type="text" name="table_search" class="form-control pull-right" placeholder="Search">

                                  <div class="input-group-btn">
                                      <button type="submit" class="btn btn-default"><i class="fa fa-search"></i></button>
                                  </div>
                              </div>
                          </div>
                      </div>
                      <!-- /.box-header -->
                      <div class="box-body table-responsive no-padding">
                          <table class="table table-bordered">
                              <tr>
                                  <th>ID</th>
                                  <th>账号</th>
                                  <th>姓名</th>
                                  <th>电话</th>
                                  <th>账号操作</th>
                                  <th>权限操作</th>
                              </tr>
                              <#list pageInfo.list as admin>
                                  <tr>
                                      <td>${admin.adminId!}</td>
                                      <td>${admin.adminName!}</td>
                                      <td>${admin.name!}</td>
                                      <td>${admin.phone!}</td>
                                      <td><button class="btn btn-warning btn-xs"  data-toggle="modal" data-target="#updateModal" onclick="updateAdmin(${admin.adminId!})"><i class="fa fa-fw fa-edit"></i></button>  <button class="btn btn-danger btn-xs" onclick="deleteAdmin(${admin.adminId!})"><i class="fa fa-fw fa-trash"></i></button></td>
                                      <td><button class="btn btn-warning btn-xs"><i class="fa fa-fw fa-edit"></i></button> </td>
                                  </tr>
                              </#list>
                          </table>
                      </div>
                      <!-- /.box-body -->

                      <div class="box-footer clearfix">
                          <ul class="pagination pagination-sm no-margin pull-right">
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


<!--------------------------------------添加的模糊框------------------------>
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
                        添加新管理员
                    </h4>
                </div>
                <div class="modal-body">

                    <!---------------------表单-------------------->
                    <div class="form-group">
                        <label for="firstname" class="col-sm-3 control-label">用户名</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="addUsername"  placeholder="请输入管理员用户名">
                            <label class="control-label" for="addUsername" style="display:none;"></label>
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="firstname" class="col-sm-3 control-label">真实姓名</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="addName"  placeholder="请输入管理员真实姓名">
                            <label class="control-label" for="addName" style="display:none;"></label>
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="firstname" class="col-sm-3 control-label">联系电话</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="addPhone"  placeholder="请输入管理员联系电话">
                            <label class="control-label" for="addPhone" style="display:none;"></label>
                        </div>
                    </div>
                    <!---------------------表单-------------------->
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                    </button>
                    <button type="button" class="btn btn-primary" id="addAdmin">
                        添加
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
    <div class="modal fade" id="updateModal" tabindex="-1" role="dialog" aria-labelledby="updateModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        &times;
                    </button>
                    <h4 class="modal-title" id="updateModalLabel">
                        修改管理员信息
                    </h4>
                </div>
                <div class="modal-body">

                    <!---------------------表单-------------------->

                    <div class="form-group">
                        <label for="firstname" class="col-sm-3 control-label">用户名</label>
                        <div class="col-sm-7">
                            <input type="hidden" id="updateId">
                            <input type="text" class="form-control" id="updateUsername" placeholder="请输入管理员用户名">
                            <label class="control-label" for="updateUsername" style="display:none;"></label>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="firstname" class="col-sm-3 control-label">真实姓名</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="updateName"  placeholder="请输入管理员真实姓名">
                            <label class="control-label" for="updateName" style="display:none;"></label>
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="firstname" class="col-sm-3 control-label">联系电话</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="updatePhone"  placeholder="请输入管理员联系电话">
                            <label class="control-label" for="updatePhone" style="display:none;"></label>
                        </div>
                    </div>


                    <!---------------------表单-------------------->

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                    </button>
                    <button type="button" class="btn btn-primary" id="updateAdmin">
                        修改
                    </button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>

</form>
<!-------------------------------------------------------------->



<div class="modal fade" id="modal_info" tabindex="-1" role="dialog" aria-labelledby="addModalLabel">
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
                <button type="button" class="btn btn-default" id="btn_info_close" data-dismiss="modal">关闭</button>
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
<!-- AdminLTE App -->
<script src="${base}/static/dist/js/adminlte.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="${base}/static/dist/js/demo.js"></script>

<script src="${base}/static/js/admin/addAdmin.js"></script>
<script src="${base}/static/js/admin/updateAdmin.js"></script>
<script src="${base}/static/js/admin/deleteAdmin.js"></script>

</body>
</html>
