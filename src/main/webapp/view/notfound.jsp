<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	isErrorPage="true"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>这个.. 页面没有找到！！！</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="icon" href="<%=basePath%>/favicon.ico" />
<link rel="shortcut icon" href="<%=basePath%>/favicon.ico" />
<link rel="bookmark" href="<%=basePath%>/favicon.ico" />

<style type="text/css">
body {
	margin: 0;
	padding: 0;
	background: #efefef;
	font-family: Georgia, Times, Verdana, Geneva, Arial, Helvetica,
		sans-serif;
}

div#mother {
	margin: 0 auto;
	width: 943px;
	height: 572px;
	position: relative;
}

div#errorBox {
	background: url(<%=path%>/static/img/404_bg.png) no-repeat top left;
	width: 943px;
	height: 572px;
	margin: auto;
}

div#errorText {
	color: #39351e;
	padding: 146px 0 0 446px
}

div#errorText p {
	width: 303px;
	font-size: 14px;
	line-height: 26px;
}

div.link { /*background:#f90;*/
	height: 50px;
	width: 145px;
	float: left;
}

div#home {
	margin: 20px 0 0 444px;
}

div#contact {
	margin: 20px 0 0 25px;
}

h1 {
	font-size: 40px;
	margin-bottom: 35px;
}
</style>
</head>

<body>
	<div id="mother">
		<div id="errorBox">
			<div id="errorText">
				<h1>Sorry..页面没有找到！</h1>
				<p>似乎你所寻找的网页已移动或丢失了。
				<p>或者也许你只是键入错误了一些东西。</p>
				请不要担心，这没事。如果该资源对你很重要，请与管理员联系。
				</p>

				<p>
					火星不太安全，我可以免费送你回地球<a href="javascript:history.go(-1);">返回上页</a>
				</p>

			</div>
			<a href="<%=path%>/view/index.jsp" title="返回首页">
				<div class="link" id="home"></div>
			</a> <a href="<%=path%>/index.jsp" title="联系管理">
				<div class="link" id="contact"></div>

			</a>
		</div>
	</div>
</body>
</html>