<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<%@ page import="cn.zhaoliugang.guard.util.Constant"%>

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
			<li><a href="#"><i class="fa-home"></i>巡更报表</a></li>
			<li><a href="#"><strong>人员信息统计报表</strong></a></li>
		</ol>
	</div>
	
	<div class="panel panel-default">
		<div class="panel-heading">
			<div class="text-center" style="font-size:25px;">人员信息统计报表</div>
		</div>

		<div class="panel-body">
			<form name="f1" id="f1" class="form-inline"
			action="" method="post">
			<div class="form-group">
				<label class="control-label">帐户名：</label>
				<input class="form-control"  type="text" name="username" <c:if test="${UserSession.type == 0 }"> value="${UserSession.username }" readonly="readonly" style="background-color: gray " </c:if>  value="${username }" >
			</div>
			<div class="form-group">
				<label class="control-label">用户姓名：</label>
				<input class="form-control"  type="text" name="name" <c:if test="${UserSession.type == 0 }"> value="${UserSession.name }" readonly="readonly" style="background-color: gray " </c:if>  value="${name }" >
			</div>
			<div class="form-group" style="padding-top:12px;">
				<input type="button" class="btn btn-info" value="预览" onclick="showreport();" >
				<input type="button" class="btn btn-info" value="导出" onclick="exportreport();" >
			</div>	
		</form>
		
		<form name="f2" id="f2" action="" method="post" style="margin-top:15px;">
			<table id="table-3"  class="table table-bordered table-responsive" border="1" align="center">
			<thead>
				<tr>
					<th>帐户名</th>
					<th>帐户类型</th>
					<th>帐户状态</th>
					<th>用户姓名</th>
					<th>身份证号</th>
					<th>地址</th>
					<th>手机号</th>
					<th>备注</th>
				</tr>
			</thead>
			<tbody>	
				<c:forEach var="u" items="${USERS}">
					<tr>
						<td>${u.username }</td>
						<td><c:choose>
								<c:when test="${u.type==1 }">管理员</c:when>
								<c:otherwise>操作员</c:otherwise>
							</c:choose></td>
						<td><c:choose>
								<c:when test="${u.status==0 }">启用</c:when>
								<c:otherwise>禁用</c:otherwise>
							</c:choose></td>
						<td>${u.name }</td>
						<td>${u.idnumber }</td>
						<td>${u.address }</td>
						<td>${u.phonenumber }</td>
						<td>${u.comments }</td>
					</tr>
				</c:forEach>
			</tbody>
			</table>
		</form>
		</div>
	</div>
	
	<jsp:include  page="bottom.jsp"/>
	<jsp:include  page="/view/base/script.jsp"/>

</body>
<script type="text/javascript">
	function showreport() {
		var f = document.forms[0];
		f.action = "<%=path%>/action/UserServlet?action=show";
		f.submit();
	}
	function exportreport() {
		var f = document.forms[0];
		f.action = "<%=path%>/action/UserServlet?action=export";
		f.submit();
	}
</script>
</html>