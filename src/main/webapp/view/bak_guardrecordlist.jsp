<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.text.*"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html lang="en">

<jsp:include  page="/view/base/top.jsp"/>
<% 
String datetime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()); //获取系统时间 
request.setAttribute("currentTime",datetime);
String startdatetime=new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()); //获取系统时间 
if(request.getAttribute("startTime")==null){
	request.setAttribute("startTime",startdatetime+" 00:00:01");
}
String enddatetime=new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()); //获取系统时间 
if(request.getAttribute("endTime")==null){
	request.setAttribute("endTime",enddatetime+" 23:59:59");
}

%>
<body class="page-body">

	<jsp:include  page="/view/base/menu.jsp"/>

	<div class="breadcrumb-env"
		style="height: 35px;margin-top:55px; box-shadow: 0 1px 0 rgba(0,1,1,.08), inset 0 1px 0 #ededed;">
		<ol class="breadcrumb bc-1">
			<li><a href="dashboard-1.html"><i class="fa-home"></i>巡更记录</a></li>
			<li><a href="ui-panels.html"><strong>巡更记录查询</strong></a></li>
		</ol>
	</div>
	
	<div class="panel panel-default">
		<div class="panel-heading">
			<div class="text-center" style="font-size:25px;">巡更记录列表</div>
		</div>

		<div class="panel-body">
			<div class="row">
				<div class="col-sm-12">
				<center>
					
					<form name="f1" id="f1" class="form-inline" 
						action="<%=path%>/action/GuardRecordServlet?action=list"
						method="post" >
						
						<div class="form-group hidden">
							<label class="control-label">帐户名(工号)：</label>
							<input class="form-control"  type="text" name="username" <c:if test="${UserSession.type == 0 }"> value="${UserSession.id }" readonly="readonly" style="background-color: gray " </c:if>  <c:if test="${UserSession.type == 1 }"> value="${username }" </c:if>  >
						</div>
						<div class="form-group hidden">
							<label class="control-label">巡更人员：</label>
							<input class="form-control"  type="text" name="name" <c:if test="${UserSession.type == 0 }"> value="${UserSession.name }" readonly="readonly" style="background-color: gray " </c:if>  <c:if test="${UserSession.type == 1 }"> value="${name }" </c:if>  >
						</div>
						<div class="form-group hidden">
							<label class="control-label">开始时间：</label>
							<input class="form-control"  type="text" name="starttime"  value="0" >
						</div>
						<div class="form-group hidden">
							<label class="control-label">结束时间：</label>
							<input class="form-control"  type="text" name="endtime"  value="9999999999999" >
						</div>
						<div class="form-group" style="padding-top:12px;">
							<input type="submit" class="btn btn-info" value="查询">
						</div>
					</form>
					
					<table  id="table-3" border="1" class="table table-bordered table-responsive"  align="center">
						<thead>
							<th>帐户名</th>
							<th>巡更人员</th>
							<th>巡更时间</th>
							<th>巡更图片</th>
							<th>巡更地点</th>
							<th>备注信息</th>
						</thead>
						<c:forEach var="g" items="${GUARDRECORDS }">
							<tr>
								<td>${g.username }</td>
								<td>${g.name }</td>
								<td>${g.guardtime }</td>
								<td><img src="<%=path %>/${g.guardpicture }" width="50" height="50" ><a href="<%=path %>/${g.guardpicture }" target="view_window" >详情</a></td>
								<td>${g.guardaddress }</td>
								<td>${g.comments }</td>
							</tr>
						</c:forEach>
					</table>
					<font color="red"> <c:if test="${Msg != null }">${Msg }</c:if>
					</font> 
				</center>
				</div>
			</div>
		</div>
	</div>
	<jsp:include  page="bottom.jsp"/>
	<jsp:include  page="/view/base/script.jsp"/>

</body>
</html>
