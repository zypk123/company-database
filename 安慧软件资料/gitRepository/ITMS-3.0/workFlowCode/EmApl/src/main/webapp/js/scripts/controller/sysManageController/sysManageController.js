 define(['app', 'service/userService/userManageServices', 'service/TreeServices'], function(app) {
     'use strict'

     app.controller('SysManageController', function($rootScope, $scope, UserManageService, treeService, $log) {
         $scope.totalItems = 0;
         $scope.currentPage = 1;
         $scope.maxSize = 7;

         //load orgTree
         //$scope.groupId get from app.js file
         $scope.userGroupId = $scope.groupId;
         //page flag
         $scope.noCheck = 1;
         $scope.$on("menus", function(event, params) {
             var nodeList = [];
             //$scope.groupId = $cookies.get('groupId');
             params.groupId = $scope.userGroupId;
             console.log(params);
             treeService.postUrl("/EmApl/service/workFlow/dictionaryService/getGroupTrees", params, function(data) {
                 //发送消息给子控制器
                 $scope.treeData = {
                     data: data
                 };
                 console.log(data);
                 $scope.$broadcast("menuData", $scope.treeData);
             });
         });

         $scope.$on("slecttreeNode", function(event, treeNode) {
             //发送消息给子控制器
             $scope.userGroupId = treeNode.id;
             console.log(treeNode.id);
             $scope.getUserList();
             //console.log($scope.nodes);
         });

         //$scope.userGroupId = $scope.userGroupId;
         $scope.getUserList = function() {
             if (!$scope.orgName) {
                 $scope.groupId = "";
             }

             var queryObj = {
                 id: $scope.id,
                 first: $scope.first,
                 groupId: $scope.userGroupId,
                 startPage: $scope.currentPage,
                 pageSize: 10
             };
             console.log(queryObj)
             UserManageService.getUserList(queryObj).then(
                 function success(data) {
                     //console.log(data.data);
                     $scope.showInfo = data.data;
                     $scope.totalItems = data.totalsize;
                 });
         };
         //init data
         $scope.getUserList();

         $scope.$watch('currentPage', function() {
             //console.log('@@@@');
             $scope.getUserList();
         });

     })

 });
