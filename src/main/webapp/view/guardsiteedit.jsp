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
			<li><a href="#"><i class="fa-home"></i>巡更计划</a></li>
			<li><a href="#"><strong>编辑巡更站点</strong></a></li>
		</ol>
	</div>
	
	<div class="row">
		<div class="col-sm-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">编辑巡更站点</h3>
				</div>
	
				<div class="panel-body">
					<div class="row">
						<center>
			<form name="f1" id="f1"  style="max-width:600px;" 
				action="<%=path%>/action/GuardSiteServlet?action=update" method="post">
				<table id="table-3" border="1" class="table table-bordered table-responsive" align="center">
					<tr>
						<td>巡更站点编号：</td>
						<td><input class="form-control" type="text" name="guardsiteid" value="${GUARDSITE.guardsiteid }" readonly="readonly"
							style="background-color: gray " /></td>
					</tr>
					<tr>
						<td>巡更站点名称：</td>
						<td><input class="form-control" type="text" name="guardsitename" value="${GUARDSITE.guardsitename }" ></td>
					</tr>
					<tr>
						<td>摄像机编号：</td>
						<td><input class="form-control" type="text" name="cameraid" value="${GUARDSITE.cameraid }" ></td>
					</tr>
					<tr>
						<td>摄像机名称：</td>
						<td><input class="form-control" type="text" name="cameraname" value="${GUARDSITE.cameraname }" ></td>
					</tr>
					<tr>
						<td>预置位号：</td>
						<td><input class="form-control" type="text" name="presetid" value="${GUARDSITE.presetid }" ></td>
					</tr>
					
					<tr>
						<td colspan="2" align="center"><input class="btn"
							type="submit" value="更新"></td>
					</tr>
				</table>
			</form>
		</center>

		<font color="red"> <c:if test="${Msg != null }">${Msg }</c:if>
		</font> 
					</div>
				</div>
			</div>
		</div>
	</div>
					
	<jsp:include  page="/view/base/script.jsp"/>
	
	
	<jsp:include page="bottom.jsp" />
</body>
</html>
