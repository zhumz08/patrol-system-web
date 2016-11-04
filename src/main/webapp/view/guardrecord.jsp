<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ page import="java.text.*"%>

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

	<title>Xenon - Horizontal Menu Minimal</title>

	<link rel="stylesheet" href="<%=path %>/assets/css/fonts/linecons/css/linecons.css">
	<link rel="stylesheet" href="<%=path %>/assets/css/fonts/fontawesome/css/font-awesome.min.css">
	<link rel="stylesheet" href="<%=path %>/assets/css/bootstrap.css">
	<link rel="stylesheet" href="<%=path %>/assets/css/xenon-core.css">
	<link rel="stylesheet" href="<%=path %>/assets/css/xenon-forms.css">
	<link rel="stylesheet" href="<%=path %>/assets/css/xenon-components.css">
	<link rel="stylesheet" href="<%=path %>/assets/css/xenon-skins.css">
	<link rel="stylesheet" href="<%=path %>/assets/css/custom.css">
	<style>
		html,body,div,nav,a,span{
			font-family:"微软雅黑";
			color: black;
		}
	</style>
	
	<style type="text/css">
		#preview {
			width: 260px;
			height: 190px;
			border: 1px solid #000;
			overflow: hidden;
		}
		
		#imghead {
			filter: progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=image);
		}
	</style>

	<script src="<%=path %>/assets/js/jquery-1.11.1.min.js"></script>

	<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!--[if lt IE 9]>
	<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
	<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->
</head>


<% 
String datetime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()); //获取系统时间 
request.setAttribute("currentTime",datetime);
%>
</head>

<body>

	<jsp:include  page="/view/base/menu.jsp"/>
	
	
	<div class="breadcrumb-env"
		style="height: 35px;margin-top:55px; box-shadow: 0 1px 0 rgba(0,1,1,.08), inset 0 1px 0 #ededed;">
		<ol class="breadcrumb bc-1">
			<li><a href="dashboard-1.html"><i class="fa-home"></i>巡更计划</a></li>
			<li><a href="ui-panels.html"><strong>添加巡更站点</strong></a></li>
		</ol>
	</div>
	
	<div class="panel panel-default">
		<div class="panel-heading">
			<div class="text-center" style="font-size:25px;">添加巡更站点</div>
		</div>
		<div class="panel-body">
			<center>
				<form name="f1" id="f1" style="max-width:600px;" 
				action="<%=path%>/action/GuardRecordServlet?action=save"
				method="post" enctype="multipart/form-data">
				<input class="form-control"  type="hidden" name="uid" value="${UserSession.id }" disabled="disabled">
				<table id="table-3" class="table table-responsive table-hover"  border="1" align="center">
					<tr>
						<td>帐户名(工号)：</td>
						<td><input class="form-control"  type="text" name="uid" value="${UserSession.username }" disabled="disabled"></td>
					</tr>
					<tr>
						<td>巡更人员：</td>
						<td><input class="form-control"  type="text" name="name" value="${UserSession.name }" disabled="disabled"></td>
					</tr>
					<tr>
						<td>巡更时间：</td>
						<td><input class="form-control"  type="text" name="guardtime" value="${currentTime }" disabled="disabled"></td>
					</tr>
					<tr>
						<td>巡更图片：</td>
						<td>
							<div id="preview">
								<img id="imghead" border="0" src="head_180.jpg" width="180"
									height="180">
							</div> 
							<input type="file" onchange="previewImage(this)"
							name="guardpicture">
						</td>
	
					</tr>
					<tr><td>巡更站点（编号）：</td>
						<td><input class="form-control"  type="text" name="guardaddress"></td></tr>
					<tr>
						<td>备注信息（可选）：</td>
						<td><input class="form-control"  type="text" name="comments"></td>
					</tr>
					<tr>
						<td colspan="2" align="center"><input class="btn btn-info"  type="submit" value="添加"></td>
					</tr>
				</table>
			</form>
			<font color="red">
			<c:if test="${Msg != null }">${Msg }</c:if>
			</font>
			</center>
		</div>
	</div>
	<jsp:include  page="bottom.jsp"/>

	<jsp:include  page="/view/base/script.jsp"/>

</body>
<script type="text/javascript">
	//图片上传预览    IE是用了滤镜。
	function previewImage(file) {
		var MAXWIDTH = 260;
		var MAXHEIGHT = 180;
		var div = document.getElementById('preview');
		if (file.files && file.files[0]) {
			div.innerHTML = '<img id=imghead>';
			var img = document.getElementById('imghead');
			img.onload = function() {
				var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
				img.width = rect.width;
				img.height = rect.height;
				//                 img.style.marginLeft = rect.left+'px';
				img.style.marginTop = rect.top + 'px';
			}
			var reader = new FileReader();
			reader.onload = function(evt) {
				img.src = evt.target.result;
			}
			reader.readAsDataURL(file.files[0]);
		}
		else //兼容IE
		{
			var sFilter = 'filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';
			file.select();
			var src = document.selection.createRange().text;
			div.innerHTML = '<img id=imghead>';
			var img = document.getElementById('imghead');
			img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src;
			var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
			status = ('rect:' + rect.top + ',' + rect.left + ',' + rect.width + ',' + rect.height);
			div.innerHTML = "<div id=divhead style='width:" + rect.width + "px;height:" + rect.height + "px;margin-top:" + rect.top + "px;" + sFilter + src + "\"'></div>";
		}
	}
	function clacImgZoomParam(maxWidth, maxHeight, width, height) {
		var param = {
			top : 0,
			left : 0,
			width : width,
			height : height
		};
		if (width > maxWidth || height > maxHeight) {
			rateWidth = width / maxWidth;
			rateHeight = height / maxHeight;
			if (rateWidth > rateHeight) {
				param.width = maxWidth;
				param.height = Math.round(height / rateWidth);
			}
			else {
				param.width = Math.round(width / rateHeight);
				param.height = maxHeight;
			}
		}

		param.left = Math.round((maxWidth - param.width) / 2);
		param.top = Math.round((maxHeight - param.height) / 2);
		return param;
	}
</script>
</html>
