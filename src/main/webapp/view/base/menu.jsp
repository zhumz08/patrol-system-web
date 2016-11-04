<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<nav class="navbar horizontal-menu navbar-fixed-top navbar-minimal">
	<!-- set fixed position by adding class "navbar-fixed-top" -->

	<div class="navbar-inner" style="background: #398dee;">

		<!-- Navbar Brand -->
		<div class="navbar-brand">
			<a href="<%=path %>/view/index.jsp" class="logo" style="color: white;font-size: 25px;"> 电子巡更管理系统 </a>
		</div>

		<!-- Mobile Toggles Links -->
		<div class="nav navbar-mobile">

			<!-- This will toggle the mobile menu and will be visible only on mobile devices -->
			<div class="mobile-menu-toggle">
				<!-- This will open the popup with user profile settings, you can use for any purpose, just be creative -->
<!-- 				<a href="#" data-toggle="settings-pane" data-animate="true"> <i
					class="linecons-cog"></i>
				</a> <a href="#" data-toggle="user-info-menu-horizontal"> <i
					class="fa-bell-o"></i> <span class="badge badge-success">7</span>
				</a>
 -->
				<!-- data-toggle="mobile-menu-horizontal" will show horizontal menu links only -->
				<!-- data-toggle="mobile-menu" will show sidebar menu links only -->
				<!-- data-toggle="mobile-menu-both" will show sidebar and horizontal menu links -->
				<a href="#" data-toggle="mobile-menu-horizontal" style="padding:20px 0 0 0;">
					<i class="fa-bars" style="color:white;" ></i>
				</a>
			</div>

		</div>

		<div class="navbar-mobile-clear"></div>

		<!-- main menu -->
		<ul class="navbar-nav ">
			<li><a href="<%=path %>/view/index.jsp"> <i
					class="glyphicon glyphicon-home text-blue"></i> <span class="title">首页</span>
			</a></li>
			<li class="opened"><a href="#"> <i
					class="glyphicon glyphicon-user text-blue"></i> <span class="title">用户管理</span>
			</a>
				<ul>
					<li><a  href="<%=path%>/action/UserServlet?action=get"> <span class="title">修改用户信息</span>
					</a></li>
					<li><a href="<%=path %>/view/user.jsp"> <span class="title">新增注册用户</span>
					</a></li>
					<li><a href="<%=path %>/view/userlist.jsp"> <span class="title">用户信息管理</span>
					</a></li>
				</ul></li>
			<li><a href="#"> <i
					class="linecons-note text-blue"></i> <span class="title">巡更计划</span>
			</a>
				<ul>
					<li><a href="<%=path %>/view/guardsite.jsp"> <span class="title">巡更站点设置</span>
					</a></li>
					<li><a href="<%=path %>/view/guardsitelist.jsp"> <span class="title">巡更站点维护</span>
					</a></li>
					<li><a href="<%=path %>/tool/GuardTourSystemTool-dist.zip"> <span class="title">二维码工具下载</span>
					</a></li>
				</ul></li>
			<li><a href="#"> <i
					class="glyphicon glyphicon-book text-blue"></i> <span class="title">巡更记录</span>
			</a>
				<ul>
					<li><a href="<%=path %>/view/guardrecord.jsp"> <span class="title">登记巡更记录</span>
					</a></li>
					<li><a href="<%=path %>/view/guardrecordlist.jsp"> <span class="title">查询巡更记录</span>
					</a></li>
				</ul></li>
			<li><a href="#"> <i
					class="glyphicon glyphicon-stats text-blue"></i> <span
					class="title">巡更报表</span>
			</a>
				<ul>
					<li><a href="<%=path %>/view/userreport.jsp"> <span class="title">人员信息统计</span>
					</a></li>
					<li><a href="<%=path %>/view/guardrecordreport.jsp"> <span class="title">巡更记录统计</span>
					</a></li>
				</ul>
			</li>
			<c:if test="${UserSession.name != null }">
				<li><a style="color:black;" href="<%=path %>/action/LoginServlet?action=logout"><i class="glyphicon glyphicon-log-out text-blue"></i>退出</a></li>
			</c:if>
			<c:if test="${UserSession.name == null }">
				<li><a style="color:black;"><font color="red">用户未登录</font> 请登录</a></li>
			</c:if>
		</ul>


		<!-- notifications and other links -->
		<!--
                            <ul class="nav nav-userinfo navbar-right">

                                <li class="search-form">&lt;!&ndash; You can add "always-visible" to show make the search input visible &ndash;&gt;

                                    <form method="get" action="extra-search.html">
                                        <input type="text" name="s" class="form-control search-field" placeholder="Type to search..." />

                                        <button type="submit" class="btn btn-link">
                                            <i class="linecons-search"></i>
                                        </button>
                                    </form>

                                </li>

                                <li class="dropdown xs-left">

                                    <a href="#" data-toggle="dropdown" class="notification-icon">
                                        <i class="fa-envelope-o"></i>
                                        <span class="badge badge-green">15</span>
                                    </a>

                                    <ul class="dropdown-menu messages">
                                        <li>

                                            <ul class="dropdown-menu-list list-unstyled ps-scrollbar">

                                                <li class="active">&lt;!&ndash; "active" class means message is unread &ndash;&gt;
                                                    <a href="#">
                                                        <span class="line">
                                                            <strong>Luc Chartier</strong>
                                                            <span class="light small">- yesterday</span>
                                                        </span>

                                                        <span class="line desc small">
                                                            This ain’t our first item, it is the best of the rest.
                                                        </span>
                                                    </a>
                                                </li>

                                                <li class="active">
                                                    <a href="#">
                                                        <span class="line">
                                                            <strong>Salma Nyberg</strong>
                                                            <span class="light small">- 2 days ago</span>
                                                        </span>

                                                        <span class="line desc small">
                                                            Oh he decisively impression attachment friendship so if everything.
                                                        </span>
                                                    </a>
                                                </li>

                                                <li>
                                                    <a href="#">
                                                        <span class="line">
                                                            Hayden Cartwright
                                                            <span class="light small">- a week ago</span>
                                                        </span>

                                                        <span class="line desc small">
                                                            Whose her enjoy chief new young. Felicity if ye required likewise so doubtful.
                                                        </span>
                                                    </a>
                                                </li>

                                                <li>
                                                    <a href="#">
                                                        <span class="line">
                                                            Sandra Eberhardt
                                                            <span class="light small">- 16 days ago</span>
                                                        </span>

                                                        <span class="line desc small">
                                                            On so attention necessary at by provision otherwise existence direction.
                                                        </span>
                                                    </a>
                                                </li>

                                                &lt;!&ndash; Repeated &ndash;&gt;

                                                <li class="active">&lt;!&ndash; "active" class means message is unread &ndash;&gt;
                                                    <a href="#">
                                                        <span class="line">
                                                            <strong>Luc Chartier</strong>
                                                            <span class="light small">- yesterday</span>
                                                        </span>

                                                        <span class="line desc small">
                                                            This ain’t our first item, it is the best of the rest.
                                                        </span>
                                                    </a>
                                                </li>

                                                <li class="active">
                                                    <a href="#">
                                                        <span class="line">
                                                            <strong>Salma Nyberg</strong>
                                                            <span class="light small">- 2 days ago</span>
                                                        </span>

                                                        <span class="line desc small">
                                                            Oh he decisively impression attachment friendship so if everything.
                                                        </span>
                                                    </a>
                                                </li>

                                                <li>
                                                    <a href="#">
                                                        <span class="line">
                                                            Hayden Cartwright
                                                            <span class="light small">- a week ago</span>
                                                        </span>

                                                        <span class="line desc small">
                                                            Whose her enjoy chief new young. Felicity if ye required likewise so doubtful.
                                                        </span>
                                                    </a>
                                                </li>

                                                <li>
                                                    <a href="#">
                                                        <span class="line">
                                                            Sandra Eberhardt
                                                            <span class="light small">- 16 days ago</span>
                                                        </span>

                                                        <span class="line desc small">
                                                            On so attention necessary at by provision otherwise existence direction.
                                                        </span>
                                                    </a>
                                                </li>

                                            </ul>

                                        </li>

                                        <li class="external">
                                            <a href="blank-sidebar.html">
                                                <span>All Messages</span>
                                                <i class="fa-link-ext"></i>
                                            </a>
                                        </li>
                                    </ul>

                                </li>

                                <li class="dropdown xs-left">
                                    <a href="#" data-toggle="dropdown" class="notification-icon notification-icon-messages">
                                        <i class="fa-bell-o"></i>
                                        <span class="badge badge-purple">7</span>
                                    </a>

                                    <ul class="dropdown-menu notifications">
                                        <li class="top">
                                            <p class="small">
                                                <a href="#" class="pull-right">Mark all Read</a>
                                                You have <strong>3</strong> new notifications.
                                            </p>
                                        </li>

                                        <li>
                                            <ul class="dropdown-menu-list list-unstyled ps-scrollbar">
                                                <li class="active notification-success">
                                                    <a href="#">
                                                        <i class="fa-user"></i>

                                                        <span class="line">
                                                            <strong>New user registered</strong>
                                                        </span>

                                                        <span class="line small time">
                                                            30 seconds ago
                                                        </span>
                                                    </a>
                                                </li>

                                                <li class="active notification-secondary">
                                                    <a href="#">
                                                        <i class="fa-lock"></i>

                                                        <span class="line">
                                                            <strong>Privacy settings have been changed</strong>
                                                        </span>

                                                        <span class="line small time">
                                                            3 hours ago
                                                        </span>
                                                    </a>
                                                </li>

                                                <li class="notification-primary">
                                                    <a href="#">
                                                        <i class="fa-thumbs-up"></i>

                                                        <span class="line">
                                                            <strong>Someone special liked this</strong>
                                                        </span>

                                                        <span class="line small time">
                                                            2 minutes ago
                                                        </span>
                                                    </a>
                                                </li>

                                                <li class="notification-danger">
                                                    <a href="#">
                                                        <i class="fa-calendar"></i>

                                                        <span class="line">
                                                            John cancelled the event
                                                        </span>

                                                        <span class="line small time">
                                                            9 hours ago
                                                        </span>
                                                    </a>
                                                </li>

                                                <li class="notification-info">
                                                    <a href="#">
                                                        <i class="fa-database"></i>

                                                        <span class="line">
                                                            The server is status is stable
                                                        </span>

                                                        <span class="line small time">
                                                            yesterday at 10:30am
                                                        </span>
                                                    </a>
                                                </li>

                                                <li class="notification-warning">
                                                    <a href="#">
                                                        <i class="fa-envelope-o"></i>

                                                        <span class="line">
                                                            New comments waiting approval
                                                        </span>

                                                        <span class="line small time">
                                                            last week
                                                        </span>
                                                    </a>
                                                </li>
                                            </ul>
                                        </li>

                                        <li class="external">
                                            <a href="#">
                                                <span>View all notifications</span>
                                                <i class="fa-link-ext"></i>
                                            </a>
                                        </li>
                                    </ul>
                                </li>

                                <li class="dropdown user-profile">
                                    <a href="#" data-toggle="dropdown">
                                        <img src="assets/images/user-1.png" alt="user-image" class="img-circle img-inline userpic-32" width="28" />
                                            <span>
                                                Arlind Nushi
                                                <i class="fa-angle-down"></i>
                                            </span>
                                    </a>

                                    <ul class="dropdown-menu user-profile-menu list-unstyled">
                                        <li>
                                            <a href="#edit-profile">
                                                <i class="fa-edit"></i>
                                                New Post
                                            </a>
                                        </li>
                                        <li>
                                            <a href="#settings">
                                                <i class="fa-wrench"></i>
                                                Settings
                                            </a>
                                        </li>
                                        <li>
                                            <a href="#profile">
                                                <i class="fa-user"></i>
                                                Profile
                                            </a>
                                        </li>
                                        <li>
                                            <a href="#help">
                                                <i class="fa-info"></i>
                                                Help
                                            </a>
                                        </li>
                                        <li class="last">
                                            <a href="extra-lockscreen.html">
                                                <i class="fa-lock"></i>
                                                Logout
                                            </a>
                                        </li>
                                    </ul>
                                </li>

                                <li>
                                    <a href="#" data-toggle="chat">
                                        <i class="fa-comments-o"></i>
                                    </a>
                                </li>

                            </ul>
                -->

	</div>

</nav>
