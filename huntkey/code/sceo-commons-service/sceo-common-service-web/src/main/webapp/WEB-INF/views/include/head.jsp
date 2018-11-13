<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    String basePathStatic = basePath + "static/";
    String clientUrl = "http://localhost:10001";
    String version = "v1";
%>
<c:set var="ctx" value="<%=basePath%>"/>
<c:set var="ctxStatic" value="<%=basePathStatic%>"/>
<c:set var="clientUrl" value="<%=clientUrl %>"/>
<c:set var="version" value="<%=version %>"/>
<script type="text/javascript">
    var ctx = "<%=basePath%>";
    var ctxStatic = "<%=basePathStatic%>";
    var clientUrl = "<%=clientUrl%>";
    var version = "<%=version%>";
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/static/bootstrap/css/bootstrap.min.css" type="text/css">
<script type="text/javascript" src="/static/jquery/jquery-1.9.1.js"></script>
<script type="text/javascript" src="/static/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/static/js/axios.min.js"></script>
