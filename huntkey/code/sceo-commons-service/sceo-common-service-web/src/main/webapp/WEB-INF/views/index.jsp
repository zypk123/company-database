<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
	<head>
		<title>首页</title>
		<%@ include file="/WEB-INF/views/include/head.jsp"%>
		<link rel="stylesheet" href="/static/home/home.css" type="text/css">
		<script type="text/javascript" src="/static/home/main.js"></script>
		<script type="text/javascript">
			$(function(){
				HOME.init();
			});
		</script>
	</head>
	<body>
		<div class="h-head"></div>
		<div class="h-body">
			<div class="b-menu panel-group" id="menu_accordion"></div>
			<div class="b-content" id="menu_conent">
				  <ul class="nav nav-tabs tab-header" role="tablist" id="tab_list">
				    <li role="presentation" id="closetabli" class="active">
				    	<a href="#home" aria-controls="closetab" role="tab" data-toggle="tab">
				    		<span>首页</span>&nbsp;&nbsp;
    					</a>
    				</li>
				  </ul>
				  <div class="tab-content" id="tab-content">
				    <div role="tabpanel" class="tab-pane active" id="home">首页</div>
				  </div>
			</div>
		</div>
		<div class="h-foot clearfix">
				Copyright2017深圳嘉源锐信软件有限公司版权所有
		</div>
	</body>
</html>