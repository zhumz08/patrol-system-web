<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
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
			<li><a href="#"><strong>修改用户信息</strong></a></li>
		</ol>
	</div>
	
	<div class="panel panel-default">
				<div class="panel-heading">
					<div class="text-center" style="font-size:25px;">用户信息</div>
				</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-12">
									<form name="f1" id="f1"
				action="<%=path%>/action/UserServlet?action=update" method="post">
				<input type="hidden" name="id" value="${USER.id }">
				<table id="table-3" style="max-width:600px;"
				 class="table table-bordered table-responsive table-condensed table-hover" 
				 border="1" align="center">
					<tr>
						<td>帐户名：</td>
						<td><input class="form-control" type="text"
							name="username" value="${USER.username }" readonly="readonly"
							style="background-color: gray " /></td>
					</tr>
					<tr>
						<td>密码：</td>
						<td><input class="form-control" type="text"
							name="password" value="${USER.password }" /></td>
					</tr>
					<tr>
						<td>帐户类型：</td>
						<td><select class="form-control" name="type"
							<c:if test="${UserSession.type == 0 }"> style="background-color: gray " onfocus="this.defaultIndex=this.selectedIndex;" onchange="this.selectedIndex=this.defaultIndex;" </c:if>>
								<option value="0"
									<c:if test="${USER.type == 0 }"> selected="selected" </c:if>>操作员</option>
								<c:if test="${UserSession.type == 1 }">
									<option value="1"
										<c:if test="${USER.type == 1 }"> selected="selected" </c:if>>管理员</option>
								</c:if>
						</select></td>
					</tr>
					<tr>
						<td>帐户状态：</td>
						<td><select class="form-control" name="status"
							<c:if test="${UserSession.type == 0 }"> style="background-color: gray " onfocus="this.defaultIndex=this.selectedIndex;" onchange="this.selectedIndex=this.defaultIndex;" </c:if>>
								<option value="0"
									<c:if test="${USER.status == 0 }"> selected="selected" </c:if>>启用</option>
								<c:if test="${UserSession.type == 1 }">
									<option value="1"
										<c:if test="${USER.status == 1 }"> selected="selected" </c:if>>禁用</option>
								</c:if>
						</select></td>
					</tr>
					<tr>
						<td>用户姓名：</td>
						<td><input class="form-control" type="text"
							name="name" value="${USER.name }" /></td>
					</tr>
					<tr>
						<td>身份证号：</td>
						<td><input class="form-control" type="text"
							name="idnumber" value="${USER.idnumber }" /></td>
					</tr>
					<tr>
						<td>地址：</td>
						<td><input class="form-control" type="text"
							name="address" value="${USER.address }" /></td>
					</tr>
					<tr>
						<td>手机号：</td>
						<td><input class="form-control" type="text"
							name="phonenumber" value="${USER.phonenumber }" /></td>
					</tr>
					<tr>
						<td>备注：</td>
						<td><input class="form-control" type="text"
							name="comments" value="${USER.comments }" /></td>
					</tr>
					<tr>
						<td colspan="2" align="center"><input type="submit"
							class="btn btn-info" value="更新"></td>
					</tr>
				</table>
			</form>
			<center>

				<font color="red"> <c:if test="${Msg != null }">${Msg }</c:if>
				</font> <br>
				<hr />
						</div>
					</div>
				</div>
			</div>
	<jsp:include  page="bottom.jsp"/>
	
	<jsp:include  page="/view/base/script.jsp"/>
	
</body>
</html>	