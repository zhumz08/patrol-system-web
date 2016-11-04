<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>    

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<link rel="stylesheet" href="<%=path %>/static/css/main.css" />
<script type="text/javascript" src="<%=path %>/static/js/jquery.js"></script>


<br/>
<h1 align="center">欢迎使用电子巡更管理系统</h1>

<div class="nav">
	<!--导航条-->
	<ul class="nav-main">
		<li><a href="<%=path %>/view/index.jsp">首页</a>></li>
		<li id="li-1">人员管理<span></span></li>
		<li id="li-2">巡更计划<span></span></li>
		<li id="li-3">巡更记录<span></span></li>
		<li id="li-4">巡更报表<span></span></li>
		
<c:if test="${UserSession.name != null }"><li>欢迎 <font color="red">${UserSession.name }</font> <a href="<%=path %>/action/LoginServlet?action=logout">退出</a></li></c:if>
<c:if test="${UserSession.name == null }"><li><font color="red">用户未登录</font> 请登录</li></c:if>

		
	</ul>
	<div id="box-1" class="hidden-box hidden-loc-index">
		<ul>
			<li><a href="<%=path%>/action/UserServlet?action=get">当前用户信息</a></li>
			<li><a href="<%=path %>/view/user.jsp">新增注册用户</a></li>
			<li><a href="<%=path %>/view/userlist.jsp">用户信息管理</a></li>
		</ul>
	</div>
	<div id="box-2" class="hidden-box hidden-loc-us">
		<ul>
			<li><a href="<%=path %>/view/guardsite.jsp">巡更站点设置</a></li>
			<li><a href="<%=path %>/view/guardsitelist.jsp">巡更站点维护</a></li>
			<li><a href="<%=path %>/tool/GuardTourSystemTool-dist.zip">二维码工具下载</a></li>
		</ul>
	</div>
	<div id="box-3" class="hidden-box hidden-loc-info">
		<ul>
			<li><a href="<%=path %>/view/guardrecord.jsp">登记巡更记录</a></li>
			<li><a href="<%=path %>/view/guardrecordlist.jsp">查询巡更记录</a></li>
		</ul>
	</div>
	<div id="box-4" class="hidden-box hidden-loc-info box04">
		<ul>
			<li><a href="<%=path %>/view/userreport.jsp">人员信息统计</a></li>
			<li><a href="<%=path %>/view/guardrecordreport.jsp">巡更记录统计</a></li>
		</ul>
	</div>
</div>
<div style="text-align:center;margin:3px 0; font:normal 14px/24px 'MicroSoft YaHei';">
</div>

<script type="text/javascript" src="<%=path %>/static/js/main.js"></script>


