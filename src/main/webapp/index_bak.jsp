<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page language="java" import="javax.servlet.http.Cookie" %>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>巡更系统</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="icon" href="<%=basePath%>/favicon.ico" />
<link rel="shortcut icon" href="<%=basePath%>/favicon.ico" />
<link rel="bookmark" href="<%=basePath%>/favicon.ico" />

<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
</head>

<%
		Cookie[] cs = request.getCookies();
		if(cs!=null && cs.length>0){
			for(int i=0;i<cs.length;i++){
				Cookie c = cs[i];
				System.out.println("##jsp cookie name:"+c.getName()+" value:"+c.getValue());
				
				if(c.getName().equals("username")){
					String value=c.getValue();
					request.setAttribute("un", value);
				}
				
				if(c.getName().equals("password")){
					String value=c.getValue();
					request.setAttribute("pw", value);
				}
			}
		}
 %>

<body  style="background-image:url(<%=path %>/static/img/background4.jpg); background-position:center; background-repeat:repeat-y">
	<br />
	<h1 align="center">欢迎使用电子巡更管理系统</h1>
	<br />
	<div id="center">
		<form name="f1" id="f1"
			action="<%=path%>/action/LoginServlet?action=login" method="post">
			<table border="0" align="center">
				<tr>
					<td>用户名：</td>
					<td><input type="text" name="username" style="width:120px;" value="${un }" ></td>
				</tr>
				<tr>
					<td>密码：</td>
					<td><input type="password" name="password" style="width:120px;" value="${pw }" ></td>
				</tr>
				<tr>
					<td><input type="checkbox" name="auto" value="1" checked="checked">自动登录</td>
					<td align=center><input type="submit" value="登录"></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td><a href="<%=path%>/view/index.jsp">已登录直接进入</a></td>
				</tr>
			</table>
		</form>
	</div>
	<br/>
	<br/>
	<div>
		<table border="0" align="center">
			<tr>
				<td><font color="red"> <c:if test="${Msg != null }">${Msg }</c:if>
				</font>
				</td>
			</tr>
		</table>
	</div>
	<jsp:include page="view/bottom.jsp" />
</body>
</html>
