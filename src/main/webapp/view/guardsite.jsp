<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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

	<jsp:include  page="/view/base/menu.jsp"/>

	<div class="breadcrumb-env"
		style="height: 35px;margin-top:55px; box-shadow: 0 1px 0 rgba(0,1,1,.08), inset 0 1px 0 #ededed;">
		<ol class="breadcrumb bc-1">
			<li><a href="dashboard-1.html"><i class="fa-home"></i>巡更计划</a></li>
			<li><a href="ui-panels.html"><strong>添加巡更站点</strong></a></li>
		</ol>
	</div>
	
	<div class="panel panel-default">
				<div class="panel-heading">
					<div class="text-center" style="font-size:25px;">添加巡更站点</div>
				</div>
	
				<div class="panel-body">
					<form name="f1" id="f1"
							action="<%=path%>/action/GuardSiteServlet?action=save" method="post">
							<table id="table-3" style="max-width:600px;" 
								class="table table-bordered table-responsive table-condensed table-hover" 
							 border="1" align="center">
								<tr>
									<td>巡更站点编号：</td>
									<td><input class="form-control " type="text" name="guardsiteid"></td>
								</tr>
								<tr>
									<td>巡更站点名称：</td>
									<td><input class="form-control" type="text" name="guardsitename"></td>
								</tr>
								<tr>
									<td>摄像机编号：</td>
									<td><input class="form-control" type="text" name="cameraid"></td>
								</tr>
								<tr>
									<td>摄像机名称：</td>
									<td><input class="form-control" type="text" name="cameraname"></td>
								</tr>
								<tr>
									<td>预置位号：</td>
									<td><input class="form-control" type="text" name="presetid"></td>
								</tr>
								
								<tr>
									<td colspan="2" align="center"><input class="btn btn-info"
										type="submit" value="添加"></td>
								</tr>
							</table>
						</form>
					<font color="red"> <c:if test="${Msg != null }">${Msg }</c:if>
				</div>
		</div>
	<jsp:include  page="bottom.jsp"/>
	<jsp:include  page="/view/base/script.jsp"/>

</body>
</html>
