define(['routes', 'loader', 'angularAMD', 'ui-bootstrap',
		'angular-sanitize', 'ui.route','ngCookies','zTree','ngLocale','ngFileUpload','angularTreeview','toastr'], function(config, loader,
		angularAMD) {
	var app = angular.module("webapp", [  'ngSanitize',
			'ui.bootstrap', 'ui.router','ngCookies','ngLocale','ngFileUpload','angularTreeview']);

	app.controller('IndexController', function($http,$scope,$cookies,$location) {
		//直接index.html进来的用户
		if($cookies.get('loginName')==undefined){
				window.location.href="/EmApl";
		}
		//从cookie中获取用户的相关信息
		$scope.loginName = $cookies.get('loginName');
		$scope.groupId = $cookies.get('groupId');
		$scope.servieImg = $cookies.get('servieImg');
		$scope.userName = $cookies.get('userName');
		//信息不会变
		$scope.items= menus = [];
		$scope.menuId  = '1';
		$scope.menuName  = '申报审批';
		$scope.currSys={};
		$scope.status = {
		    isFirstOpen: true,
		    isFirstDisabled: false
	    };
	    	    //用户登出
	    $scope.loginOut = function(){
	    	//清空数据，包含cookies数据
	    	$scope.loginName ="";
	    	$scope.groupId ="";
	    	$scope.servieImg ="";
	    	$scope.userName ="";
	    	//清除cookie数据
	    	$cookies.remove('loginName');
	    	$cookies.remove('groupId');
	    	$cookies.remove('servieImg');
	    	$cookies.remove('userName');
	    	//直接退出
	    	window.location.href="/EmApl";
	    }

		$scope.selectRestaurant=function(row) {
		       $scope.rowIndex=row;
		};
		//接收子控制器的消息
	    $scope.$on("menus",function(event,params){
	       $scope.ajaxPost("/EmApl/service/workFlow/dictionaryService/getMenus",params,function(data) {
	    	    //发送消息给子控制器
	            $scope.$broadcast("menuData",data);
			});
	    });

	    $scope.$on("checkedTree",function(event,nodes){
	    	   //发送消息给子控制器
	    	    $scope.$broadcast("checkData",nodes);
	    });

	    $scope.setMenu=function(id){
	    	$scope.menuId=id;
	    	menus.splice(0,menus.length);
	    	for(var  i =0;i<menuall.length;i++){
	    		if(menuall[i].id==id){
	    			$scope.menuName  = menuall[i].name;
	    		}
	    		if(menuall[i].pId == $scope.menuId){
	    			menus.push(menuall[i]);
	    		}
	    	}
	    }

	    $scope.ajaxPost = function(url, data, success) {
			$http({
				method : "POST",
				url : url,
				params : data
			}).success(function(data) {
				success(data);
			})
		}
		$scope.currGroupId = "";
		var menuall=[];
	    var params={menuId:$scope.groupId};
	    $scope.ajaxPost("/EmApl/service/workFlow/wfMenuService/findMenuByGroupId",params,
	     function(data) {
	    	menus.splice(0,menus.length);
	    	menuall  = data;
	    	for(var  i=0;i<data.length;i++){
	    		if(data[i].pId == $scope.menuId){
	    			menus.push(data[i]);
	    		}
	    	}
		});
	});

	app.config(function($stateProvider, $urlRouterProvider) {
		// 配置路由
		if (config.routes != undefined) {
			angular.forEach(config.routes, function(route, path) {
				$stateProvider.state(path, {
					templateUrl : route.templateUrl,
					url : route.url,
					resolve : loader(route.dependencies),
					// allowAnonymous: route.allowAnonymous
				});
			});
		}
		if(config.defaultRoute != undefined) {
			$urlRouterProvider.when("",config.defaultRoute);
		}
	})
	return angularAMD.bootstrap(app);
});