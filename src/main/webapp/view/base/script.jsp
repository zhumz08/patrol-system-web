<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- Bottom Scripts -->
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<script src="<%=path %>/assets/js/bootstrap.min.js"></script>
<script src="<%=path %>/assets/js/TweenMax.min.js"></script>
<script src="<%=path %>/assets/js/resizeable.js"></script>
<script src="<%=path %>/assets/js/joinable.js"></script>
<script src="<%=path %>/assets/js/xenon-api.js"></script>
<script src="<%=path %>/assets/js/xenon-toggles.js"></script>

<!-- JavaScripts initializations and stuff -->
<script src="<%=path %>/assets/js/xenon-custom.js"></script>