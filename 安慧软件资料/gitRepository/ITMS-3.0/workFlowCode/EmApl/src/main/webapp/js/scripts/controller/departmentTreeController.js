define(['app','service/TreeServices'], function (app) {
    app.controller('departmentTreeController', function ($scope,treeService) {
    	$scope.$on("checkData",function(event,nodes){  
 	   //发送消息给子控制器  
		 	   console.log(nodes);  
		 }); 
 
	});
}); 