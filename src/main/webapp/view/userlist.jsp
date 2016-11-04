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
			<li><a href="#"><i class="fa-home"></i>用户管理</a></li>
			<li><a href="#"><strong>用户查询</strong></a></li>
		</ol>
	</div>
	
	<div class="panel panel-default">
		<div class="panel-heading">
			<div class="text-center" style="font-size:25px;">用户查询(支持模糊查询)</div>
		</div>

		<div class="panel-body">
			<form name="f1" id="f1" class="form-inline" action="<%=path%>/action/UserServlet?action=list" method="post">
				<div class="form-group">
					<label class="control-label">帐户名：</label>
					<input class="form-control"  type="text" name="username" <c:if test="${UserSession.type == 0 }"> value="${UserSession.username }" readonly="readonly" style="background-color: gray " </c:if>  value="${username }" />
				</div>
				<div class="form-group">
					<label class="control-label">用户姓名：</label>
					<input class="form-control"  type="text" name="name" <c:if test="${UserSession.type == 0 }"> value="${UserSession.name }" readonly="readonly" style="background-color: gray " </c:if>  value="${name }" />
				</div>
				<div class="form-group" style="padding-top:12px;">
					<input type="submit" class="btn btn-info " value="查询">
				</div>
			</form>
			
			<div>
			   <table id="table-3"  class="table table-bordered table-responsive table-hover" border="1" align="center">
				<thead>
					<tr>
						<th>帐户名</th>
						<th>帐户类型</th>
						<th>帐户状态</th>
						<th>用户姓名</th>
						<th>编辑详细</th>
						<th>
							<input type="button" class="btn btn-info btn-sm" style="padding:3px;margin:0px;"
							 value="删除" onclick="del();" <c:if test="${UserSession.type == 0 }">
							  disabled="disabled" </c:if> >
						</th>
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
							<td><a
								href="<%=path%>/action/UserServlet?action=get&id=${u.id }">修改</a></td>
							<td><input type="checkbox" name="ids" value="${u.id }" <c:if test="${UserSession.type == 0||u.username=='admin' }"> disabled="disabled" </c:if>></td>
						</tr>
					</c:forEach>
				</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
		<jsp:include  page="bottom.jsp"/>

	<jsp:include  page="/view/base/script.jsp"/>
	
</body>
<script type="text/javascript">
	function del() {
		var i,sl=0;
		for (i = 0; i < document.all("ids").length; i++) {
			if (document.all("ids")[i].checked){
				sl ++;
			}
		}
		if(sl==0){
			alert("没有选择要删除的记录！")
			return;
		}
		if (confirm("您是否确认删除所选的人员记录，对应的巡更记录将无法正常显示，删除后将无法恢复，请谨慎操作。")) {
			var f = document.forms[1];
			f.action = "<%=path%>/action/UserServlet?action=delete";
			f.submit();
		}
		else {
			return;
		}
	}
</script>
</html>