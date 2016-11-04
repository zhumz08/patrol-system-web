<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
	<div class="panel panel-default" style="padding-top:55px;">
			<div class="panel-body">
				<div class="row">
					<center>						
					<h2 class="panel-title">首页</h2>
					</center>					
					<table  align="center"  
							class="table table-bordered"
		 style="height: 35px;margin-top:35px;max-width:600px;">
		<tr>
			<td><a href="<%=path%>/view/user.jsp">注册用户</a></td>
			<td><a href="<%=path%>/view/userlist.jsp">用户管理</a></td>
		</tr>
		<tr>
			<td><a href="<%=path%>/view/guardrecord.jsp">登记巡更</a></td>
			<td><a href="<%=path%>/view/guardrecordlist.jsp">查询巡更</a></td>
		</tr>
	</table>
	
	<font color="red"> <c:if
			test="${Msg != null }">${Msg }</c:if>
	</font>
				</div>
			</div>
		</div>
		
		
	<jsp:include  page="bottom.jsp"/>
	<jsp:include  page="/view/base/script.jsp"/>
	</body>
</html>
