<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户管理</title>

<link rel="stylesheet" type="text/css"
	href="<%=path%>/static/css/styles.css">
	
<link rel="icon" href="<%=basePath%>/favicon.ico" />
<link rel="shortcut icon" href="<%=basePath%>/favicon.ico" />
<link rel="bookmark" href="<%=basePath%>/favicon.ico" />

</head>
<body>
<jsp:include  page="menu.jsp"/>

<div id="center"  style="background-image:url(<%=path %>/static/img/background3.jpg); background-position:center; background-repeat:no-repeat">
<hr/>
<h1>添加用户</h1>
<hr/>
<br>

<center>
<form name="f1" id="f1" action="<%=path %>/action/UserServlet?action=save" method="post">
	<table id="table-3" border="1" align="center">
		<tr>
			<td>帐户名(如工号)：</td>
			<td><input class="input-t"  type="text" name="username"></td>
		</tr>
		<tr>
			<td>密码：</td>
			<td><input class="input-t"  type="text" name="password"></td>
		</tr>
		<tr>
			<td>帐户类型：</td>
			<td>
			<select class="input-t"  name ="type">
			<option value="0">操作员</option>
			<option value="1">管理员</option>
			</select>
			</td>
		</tr>
		<tr>
			<td>帐户状态：</td>
			<td>
			<select class="input-t"  name ="status">
			<option value="0">启用</option>
			<option value="1">禁用</option>
			</select>
			</td>
		</tr>
		<tr>
			<td>用户姓名：</td>
			<td><input class="input-t"  type="text" name="name"></td>
		</tr>
		<tr>
			<td>身份证号：</td>
			<td><input class="input-t"  type="text" name="idnumber"></td>
		</tr>
		<tr>
			<td>地址：</td>
			<td><input class="input-t"  type="text" name="address"></td>
		</tr>
		<tr>
			<td>手机号：</td>
			<td><input class="input-t"  type="text" name="phonenumber"></td>
		</tr>
		<tr>
			<td>备注：</td>
			<td><input class="input-t"  type="text" name="comments"></td>
		</tr>
		<tr>
		<td colspan="2" align="center"><input class="btn"  type="submit" value="添加"></td>
		</tr>
	</table>
</form>
</center>

<font color="red">
<c:if test="${AddMsg != null }">${AddMsg }</c:if>
</font>
<br>
<hr/>
</div>

<jsp:include  page="bottom.jsp"/>
</body>
</html>