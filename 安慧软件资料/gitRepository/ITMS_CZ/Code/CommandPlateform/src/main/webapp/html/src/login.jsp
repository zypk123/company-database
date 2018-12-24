﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<div class="loginTop">
	<div class="lgdiv">
		<div class="loginTitle">
		</div>
		<div class="loginBg">
			<form cy-form="loginForm" id="loginForm" cy-keypress="keyPressSubmit" class="easyui-form table" method="post" data-options="novalidate:true">
				<div class="table">
					<div class="tr">
						<div class="td" style="height:50px;width:100%;padding-top:40px;">
							<input id="name" class="easyui-textbox" name="loginName"
							data-options="
							prompt : '请输入用户名',
							iconCls:'icon-man',
							iconAlign:'left',
							iconWidth:34,    
							height:40,
							width:216,
							required:true,
							validType:['notBlank','length[1,12]']" />
						</div>
					</div>
					<div class="tr">
						<div class="td"  style="height:50px;width:100%;padding-top:20px;">
							<input id="password" class="easyui-textbox" name="password"
							data-options="
							type : 'password',
							prompt :'请输入密码',
							iconCls:'icon-lock',
							iconAlign:'left',
							iconWidth:34,
							width:216,
							height:40,
							required:true,
							validType:['notBlank','length[6,20]']" />
						</div>
					</div>
					<div class="tr" style="margin-top:10px;">
						<div class="td loginButton" cy-click="loginSubmit" style="height:32px;">
						</div>
					</div>
				</div>
			</form>
		</div>
		<div class="discription">
			<p>
				平台有问题请联系：总队运维中心0871-65025241或总队系统管理员陈宇红：0871-65025345
			</p>
			<p>
				（正式平台，添加修改信息在此平台上操作） 承建方：安徽蓝盾光电子股份有限公司
			</p>
			<p>
				Copyright ©2008 ITS Version 2.1.018 develop 20826.00
			</p>
		</div>
	</div>
</div>
<script type="text/javascript">
    InitPage([], function($scope) {
        
    	$scope.$("#name").textbox("textbox").focus();
    	
    	/**
         * 登录方法
         * @return {[type]} [description]
         */
        $scope.loginSubmit = function() {
            $scope.$("#loginForm").form("enableValidation");
            if(!$scope.$("#loginForm").form("validate")){
            	return false;
            }
        	 var win = $.messager.progress({
                title : '请稍候',
                msg : '登录中...'
            });
      
            // 测试用
            /* if (window.location.href.toLowerCase().indexOf("http://") > -1) {
             FormSubmit(
             {
             formId: "#ff",
             actionUrl: "/cy.com.web.loginService.IndexControl/Login",
             onSubmit: function (frm) {
             if (!frm.form('enableValidation').form('validate')) {
             $.messager.progress('close');
             return false;
             }
             return true;
             },
             success: function (result) {
             if (result.IsOK) {
             logIn(result);
             $("#error").html(JSON.stringify(result));
             }
             $.messager.progress('close');
             },
             fail: function (errMsg) {
             $.messager.progress('close');
             alert(errMsg);
             }
             });
             } else {*/
            // $.getJSON("data/menu/result.json?a=" + new Date().getTime(), function(rslt) {
                // try {
                    // if (rslt.AuthenrizeResult.IsOK) {
                        // logIn(rslt.AuthenrizeResult);
                    // }
                // } catch (e) {
                    // alert(e.toString());
                // }
// 
                // $.messager.progress('close');
            // });
            $scope.$updateData("loginForm");
            //0代表登录
            $scope.loginForm.loginType = "0";
            $.ajax({
            	url : $scope.$servletRoot + "/LoginServlet",
            	data : $scope.loginForm,
            	type : "post",
            	dataType : "json",
            	success : function(result){
            		if(result.status == "2"){
            			//根据返回的用户信息，获得权限相关信息
            			result.data.token = result.info;
            			getAutherInfo(result.data);
            		}else{
            			$.messager.progress('close');
            			//登录失败
            			$.messager.alert("提示",result.info);
            		}
            		
            	},
            	error : function(){
            		//服务器报错
            		$.messager.progress('close');
            		$.messager.alert("提示","服务器错误");
            	}
            });
        }; 
        
        /**
         * 鼠标回车提交表单
         */
        $scope.keyPressSubmit = function(e){
			if(e.keyCode == 13){
				$scope.loginSubmit();
			}
        }
        
        /**
         * 根据用户信息获得权限相关信息 
         */
        var getAutherInfo = function(userData){
        	 $.ajax({
        	 	url : $scope.$restRoot + "/sysCfg/permission/findUserResource",
        	 	data : {userId:userData.userId},
        	 	type : "post",
        	 	dataType : "json",
        	 	success : function(data){
        	 		$.messager.progress('close');
        	 		userData.menus = data.functionPermission;
        	 		userData.subsribePermission = data.subsribePermission;
        	 		login(userData);
        	 	}
        	 });
        };
        
        /**
         * 登录处理
         * @param  {[type]} logInfo [description]
         * @return {[type]}         [description]
         */
        function login(userData) {
        	//保存用户信息
            $rootScope.UserInfo = userData;
            //保存参数到浏览页页面session对象中
            sessionStorage["its3.userData"] = JSON.stringify(userData);
            //跳转到页面
            $scope.$gotoPage("main.html");
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
            //定时发送ajax，保持登录状态
            $rootScope.loginInterval = setInterval(function(){
            	$.post($scope.$servletRoot + "/LoginServlet",{loginType:3});
            },4*60*1000);
        }
         
         
         
    }); 
</script>