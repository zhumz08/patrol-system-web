<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<div
	style="text-align:center;margin:50px 0; font:normal 14px/24px 'MicroSoft YaHei';">
	<p>电子巡更管理系统 <a href="<%=path %>/view/index.jsp">返回主页</a> <a href="javascript:history.go(-1);">返回上页</a></p>
	<p>
		为了更好的体验请使用配套手机APP客户端
	</p>
</div>

