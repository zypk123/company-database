<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!doctype html>
<html>
<head>
	<base href='<%= request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/html/src/" %>'>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta HTTP-EQUIV="Pragma" CONTENT="no-cache">
	<meta HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
	<meta HTTP-EQUIV="Expires" CONTENT="0">
    <title>公安智能交通综合管控平台</title>
    <!--系统基本框架，jQuery，easyui等-->
    <link rel="stylesheet" type="text/css" href="frameworks/easyui/themes/bootstrap/easyui.css">
    <link rel="stylesheet" type="text/css" href="frameworks/easyui/themes/icon.css">
    <script src="frameworks/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="frameworks/easyui/json2.js"></script>
    <script type="text/javascript" src="frameworks/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="frameworks/easyui/locale/easyui-lang-zh_CN.js"></script>
    <!-- 验证配置 -->
    <script type="text/javascript" src="js/validation.js"></script>
    <!-- 自定义框架文件 -->
    <link rel="stylesheet" type="text/css" href="themes/default/css/its3.css">
    <link rel="stylesheet" type="text/css" href="themes/default/css/violationMgr.css">
    <script type="text/javascript" src="js/rootScope.js"></script>
    <script type="text/javascript" src="js/public.js"></script>
    <script type="text/javascript" src="js/templateInit.js"></script>
    <script type="text/javascript" src="js/date-util.js"></script>
    <script type="text/javascript" src="js/violationMgr.js"></script>

    <!-- 地图组件 -->
    <link rel="stylesheet" type="text/css" href="frameworks/Openlayers/theme/default/style.css">
    <script type="text/javascript" src="frameworks/Openlayers/OpenLayers.js"></script>
    <script type="text/javascript" src="frameworks/Openlayers/lib/OpenLayers/Layer/LocalGoogleMapLayer.js"></script>
    <script type="text/javascript" src="frameworks/Openlayers/lib/OpenLayers/Layer/ArcGISTileMapLayer.js"></script>
    <script type="text/javascript" src="js/cymap.js"></script>
    <script type="text/javascript" src="frameworks/echarts/js/echarts.js"></script>
    <script type="text/javascript" src="js/marquee.js"></script>
    <script type="text/javascript" src="js/point.js"></script>
    <!-- 图层控制组件 -->
    <link rel="stylesheet" type="text/css" href="js/layerControl/layerControl.css">
    <script type="text/javascript" src="js/layerControl/layerControl.js"></script>
    <!-- 地图基本操作组件 -->
    <link rel="stylesheet" type="text/css" href="js/gisBaseToolControl/gisBaseToolControl.css">
    <script type="text/javascript" src="js/gisBaseToolControl/gisBaseToolControl.js"></script>
    
    <!-- 上传组件 -->
    <script type="text/javascript" src="frameworks/plupload-2.1.3/js/plupload.full.min.js"></script>
    <script type="text/javascript" src="frameworks/plupload-2.1.3/js/i18n/zh_CN.js"></script>
<!--     <script type="text/javascript" src="js/countUp.min.js"></script> -->

	<!-- 时间轴组件 -->
	<link rel="stylesheet" type="text/css" href="js/axis/axis.css">
    <script type="text/javascript" src="js/axis/axis.js"></script>

    <%--视频时间轴--%>
    <link rel="stylesheet" type="text/css" href="js/videoAxis/videoAxis.css">
    <script type="text/javascript" src="js/videoAxis/videoAxis.js"></script>

    <!-- 查看图片组件 -->
    <link rel="stylesheet" type="text/css" href="frameworks/fancybox/jquery.fancybox.css">
    <script type="text/javascript" src="frameworks/fancybox/jquery.fancybox.js"></script>
    <link rel="stylesheet" type="text/css" href="frameworks/fancybox/jquery.fancybox-thumbs.css">
    <script type="text/javascript" src="frameworks/fancybox/jquery.fancybox-thumbs.js"></script>
    
    <!-- 双工通讯组件 -->
    <script type="text/javascript" src="js/webSocket/cy-webscoket.js"></script>
    <script type="text/javascript" src="js/webSocket/subsribe.js"></script>
    <script type="text/javascript" src="js/webSocket/subsribeType.js"></script>

	<!-- highCharts和 highCharts下载打印 组件 -->
	<!-- <script type="text/javascript" src="js/highcharts.js"></script>
	<script type="text/javascript" src="js/exporting.js"></script> -->
	
	<!-- jquery 表格下载控件 -->
	<script type="text/javascript" src="js/tableExport.js"></script>
	

</head>
<body>
    <div id="topPanel" class="easyui-panel" data-options="fit:true">
		
    </div>
    <script type="text/javascript">
    InitPage([],function($scope){
		
    	//判断浏览器是否为火狐，非火狐浏览器自动下载火狐安装程序
    	 if(!isFF()){
    		//非火狐浏览器提示
    		$.messager.confirm("提示","当前浏览器为非火狐浏览器，不支持本系统的功能使用，是否下载火狐浏览器？",function(r){
    			if(r){
    				downloadFile("../../file/Firefox-full-latest.exe");
    			}
    		});
         
    		return;
    	} 
    	
    	
        var userData = sessionStorage["its3.userData"];
        if(userData){
            //已经登录用户跳转到主页
            var userData = $.parseJSON(userData);
            $rootScope.UserInfo = userData;
            //初始化机构树
            $scope.$initOrg();
            //初始化道路
            $scope.$initRoad();
            //初始化行政区划树
            $scope.$initDistrict();
          	//初始化号牌前缀
            $scope.$initPlatePrefix();
            //初始化webSocket
            $rootScope.$webSocket = new CyWebSocket({
            	addr : "<%= cy.its.web.GlobalProerty.getGlobalProerty().validate_ip + '/' + cy.its.web.GlobalProerty.getGlobalProerty().authorityPName %>/service/websckServer"
            });
            //跳转到主页
            $scope.$gotoPage("main.html");
          	//定时发送ajax，保持登录状态
            $rootScope.loginInterval = setInterval(function(){
            	$.post($scope.$servletRoot + "/LoginServlet",{loginType:3});
            },4*60*1000);
        }else{
            //跳转到登录界面，后期可以在此判断是否已登录操作
            $scope.$gotoPage("login.jsp");
        }
        /*$(document).keypress(function(event) {
            if(event.key == "F5"){
                //屏蔽f5键刷新操作
                event.preventDefault();
                //自定义跳转动作
                if($rootScope.UserInfo){
                    $scope.$gotoPage("main.html");
                }else{
                    $scope.$gotoPage("login.html");
                }
            }
        });*/
        
        
        function downloadFile(url) {   
            try{ 
                var elemIF = document.createElement("iframe");   
                elemIF.src = url;   
                elemIF.style.display = "none";   
                document.body.appendChild(elemIF);   
            }catch(e){ 
     
            } 
        }
    });
    </script>
</body>
</html>
