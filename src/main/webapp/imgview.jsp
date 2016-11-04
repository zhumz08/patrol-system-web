<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.text.*"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html lang="en">

<jsp:include  page="/view/base/top.jsp"/>

<body class="page-body">

	<div class="panel panel-default" style="padding:0px;">
		<div class="panel-heading" style="background: #398dee;padding:10px;">
			<div style="color:white;"><i onclick="window.history.back();" class="fa fa-mail-reply text-info right"></i> 巡更记录列表  </div>
		</div>
		<img src='<%=path + "/" + request.getParameter("path") %>' width="100%" height="100%" />
	</div>
	<jsp:include  page="/view/base/script.jsp"/>

</body>
</html>
