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
		request.setAttribute("startTime","1970-01-01 00:00:01");
	}
	String enddatetime=new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()); //获取系统时间 
	if(request.getAttribute("endTime")==null){
		request.setAttribute("endTime","2999-01-01 23:59:59");
	}

%>
<body class="page-body">

	<div class="panel panel-default" style="padding:0px;">
		<div class="panel-heading" style="background: #398dee;padding:10px;">
			<div style="font-size:25px;color:white;"><i  onclick="document.getElementById('f1').submit();"  class="fa fa-refresh text-info"></i> 巡更记录列表  </div>
		</div>
		
		<form name="f1" id="f1" class="form-inline hidden"  
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
				<input class="form-control"  type="text" name="starttime"  value="${startTime }" >
			</div>
			<div class="form-group hidden">
				<label class="control-label">结束时间：</label>
				<input class="form-control"  type="text" name="endtime"  value="${endTime }" >
			</div>
			<div class="form-group" style="padding-top:12px;">
				<input type="submit" class="btn btn-info" value="查询">
				<input  type="hidden" name="showType" value="list"  /> 
			</div>
		</form>
	
		<%--
		
		<table  id="table-3" border="1" class="table table-bordered table-striped table-condensed table-hover"  align="center">
			<thead>
				<th>帐户</th>
				<th>人员</th>
				<th>时间</th>
				<th>图片</th>
				<th>地点</th>
				<th>备注</th>
			</thead>
			<c:forEach var="g" items="${GUARDRECORDS }">
				<tr>
					<td>${g.username }</td>
					<td>${g.name }</td>
					<td>${g.guardtime }</td>
					<td><img onclick="window.location.href='<%=path %>/imgview.jsp?path=${g.guardpicture}'" 
						style="cursor:pointer;"
					 src="<%=path %>/${g.guardpicture }" width="50" height="50" /> </td>
					<td>${g.guardaddress }</td>
					<td>${g.comments }</td>
				</tr>
			</c:forEach>
		</table>
			
		 --%>
		
		<div style="background:#eeeeee;">
			<c:forEach var="g" items="${GUARDRECORDS }">
				<div class="record-item">
					<img src="<%=path %>/${g.guardpicture }"
						onclick="window.location.href='<%=path %>/imgview.jsp?path=${g.guardpicture}'" 
						style="cursor:pointer;"
						class="record-img" />
					<span>账户：${g.username }</span>
					<span>人员：${g.name }</span>
					<span>时间：${g.guardtime }</span>
					<span>地点：${g.guardaddress }</span>
					<span>备注：${g.comments }</span>
				</div>
			</c:forEach>
		</div>
	</div>
	
	<div class="page-loading-overlay">
		<div class="loader-2"></div>
	</div>
	
	<jsp:include  page="/view/base/script.jsp"/>

</body>
</html>
