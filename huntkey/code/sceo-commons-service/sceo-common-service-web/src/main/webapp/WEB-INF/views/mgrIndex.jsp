<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%> 
<html>
	<head>
		<title>首页</title>
		<script type="text/javascript">
		window.location.href = "<%= basePath%>" + "static/dist/index.html";
		</script>
	</head>
	<body>
	sdfdfsg方法
	</body>
</html>