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
			<li><a href="#"><strong>新增用户信息</strong></a></li>
		</ol>
	</div>
	
	<div class="panel panel-default">
				<div class="panel-heading">
					<div class="text-center" style="font-size:25px;">新增用户信息</div>
				</div>
	
				<div class="panel-body">
					<div class="row">
						<div class="col-sm-12">
							<form name="f1" id="f1" action="<%=path %>/action/UserServlet?action=save" method="post">
								<table id="table-3" style="max-width:600px;" 
								class="table table-bordered table-responsive table-condensed table-hover" 
								border="1" align="center">
									<tr>
										<td>帐户名(如工号)：</td>
										<td><input class="form-control"  type="text" name="username"></td>
									</tr>
									<tr>
										<td>密码：</td>
										<td><input class="form-control"  type="text" name="password"></td>
									</tr>
									<tr>
										<td>帐户类型：</td>
										<td>
										<select class="form-control"  name ="type">
										<option value="0">操作员</option>
										<option value="1">管理员</option>
										</select>
										</td>
									</tr>
									<tr>
										<td>帐户状态：</td>
										<td>
										<select class="form-control"  name ="status">
										<option value="0">启用</option>
										<option value="1">禁用</option>
										</select>
										</td>
									</tr>
									<tr>
										<td>用户姓名：</td>
										<td><input class="form-control"  type="text" name="name"></td>
									</tr>
									<tr>
										<td>身份证号：</td>
										<td><input class="form-control"  type="text" name="idnumber"></td>
									</tr>
									<tr>
										<td>地址：</td>
										<td><input class="form-control"  type="text" name="address"></td>
									</tr>
									<tr>
										<td>手机号：</td>
										<td><input class="form-control"  type="text" name="phonenumber"></td>
									</tr>
									<tr>
										<td>备注：</td>
										<td><input class="form-control"  type="text" name="comments"></td>
									</tr>
									<tr>
									<td colspan="2" align="center"><input class="btn btn-info"  type="submit" value="添加"></td>
									</tr>
								</table>
							</form>
						</div>
					</div>
				</div>
			</div>
	<jsp:include  page="bottom.jsp"/>
	
	<jsp:include  page="/view/base/script.jsp"/>
	
</body>
</html>	