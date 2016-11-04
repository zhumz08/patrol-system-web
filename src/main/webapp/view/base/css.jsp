<%@ page language="java"  pageEncoding="UTF-8" %>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

	<link rel="stylesheet" href="<%=path %>/assets/css/fonts/linecons/css/linecons.css">
	<link rel="stylesheet" href="<%=path %>/assets/css/fonts/fontawesome/css/font-awesome.min.css">
	<link rel="stylesheet" href="<%=path %>/assets/css/bootstrap.css">
	<link rel="stylesheet" href="<%=path %>/assets/css/xenon-core.css">
	<link rel="stylesheet" href="<%=path %>/assets/css/xenon-forms.css">
	<link rel="stylesheet" href="<%=path %>/assets/css/xenon-components.css">
	<link rel="stylesheet" href="<%=path %>/assets/css/xenon-skins.css">
	<link rel="stylesheet" href="<%=path %>/assets/css/custom.css">
	<style>
		html,body,div,nav,a,span{
			font-family:"微软雅黑";
			color:black;
		}
	</style>

	<script src="<%=path %>/assets/js/jquery-1.11.1.min.js"></script>

	<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!--[if lt IE 9]>
	<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
	<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->
