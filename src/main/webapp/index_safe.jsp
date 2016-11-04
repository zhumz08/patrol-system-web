<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page language="java" import="javax.servlet.http.Cookie" %>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta name="description" content="Xenon Boostrap Admin Panel" />
	<meta name="author" content="" />
	
	<title>巡更系统</title>

	<jsp:include  page="/view/base/css.jsp"/>

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
 
 	<script type="text/javascript">
 		function doLogin(userName,passWord){
 			document.getElementById("username").value = userName;
 			document.getElementById("passwd").value = passWord;
 			
 			
 			document.getElementById("login").submit();
 		}
 	</script>
 
</head>

<body style="background: white;" onload="doLogin('admin','123')">

	<div class="panel panel-default">
		<div class="panel-body">
			<div class="row">
				<div class="col-sm-2"></div>
				<div class="col-sm-8" style="padding: 15px;">
					<font color="red"> <c:if test="${Msg != null }">${Msg }</c:if>
					</font>
				
					<div style="background: url(<%=path %>/assets/images/login6.jpg) 15px;display: block;
						padding: 125px 15px 15px 35px;height: 524px;max-width: 1024px;">
						<div class="row">
							<div class="col-sm-12">
								<!-- Add class "fade-in-effect" for login form effect -->
								<form method="post" role="form" id="login" 
								action="<%=path%>/action/LoginServlet?action=login"
								class="login-form fade-in-effect">
									<div class="login-header">
										<h2 style="color: white;padding: 25px 0px 25px 0px;">欢迎使用巡更管理系统</h2>
									</div>
									
									<div class="form-group">
										<input type="text" style="height: 40px;max-width: 400px;"
										class="form-control" name="username" id="username"
											value="${un }"  placeholder="用户名"
										 autocomplete="off" />
									</div>

									<div class="form-group">
										<input type="password"  style="height: 40px;max-width: 400px;"
										class="form-control " name="password"
											value="${pw }"  placeholder="密码"
										id="passwd" autocomplete="off" />
									</div>

									<div class="form-group">
										<button type="submit" style="height: 40px;max-width: 400px;"
										class="btn btn-info  btn-block text-left">
											<i class="fa-lock"></i>
											登 录
										</button>
									</div>

								</form>
							</div>
						</div>
					</div>
				</div>
				<div class="col-sm-2"></div>
			</div>
		</div>
	</div>

	<jsp:include  page="/view/base/script.jsp"/>
	<jsp:include  page="view/bottom.jsp"/>
</body>
</html>

