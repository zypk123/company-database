 define(['app', 'service/userService/userManageServices', 'service/TreeServices', 'service/Notifier'], function(app) {
     'use strict'

     app.controller('OrgManageController', function($rootScope, $scope, UserManageService, treeService, Notifier, $uibModal, $cookies) {
             //load orgTree
             //$scope.groupId get from app.js file
             $scope.userGroupId = $scope.groupId;
             $scope.noCheck = 1;
             $scope.$on("menus", function(event, params) {
                 var nodeList = [];
                 //$scope.groupId = $cookies.get('groupId');
                 params.groupId = $scope.userGroupId;
                 // console.log(params);
                 treeService.postUrl("/EmApl/service/workFlow/dictionaryService/getGroupTrees", params, function(data) {
                     //发送消息给子控制器
                     $scope.treeData = {
                         data: data
                     };
                     $scope.$broadcast("menuData", $scope.treeData);
                 });
             });

             $scope.$on("slecttreeNode", function(event, treeNode) {
                 //发送消息给子控制器
                 $scope.id = treeNode.id;
                 $scope.getOrgList();
             });

             //load org infomation
             $scope.id = $scope.userGroupId;
             $scope.getOrgList = function() {
                 if (!$scope.orgName) {
                     $scope.groupId = "";
                 }
                 var queryObj = {
                     id: $scope.id,
                     startPage: 1,
                     pageSize: 200
                 };

                 UserManageService.getOrgList(queryObj).then(
                     function success(data) {
                         $scope.showInfo = data;
                     });
             };

             $scope.getOrgList();

             //assign authority
             $scope.assignAuthority = function(info) {
                 var modalInstance = $uibModal.open({
                     controller: 'AssignAuthorityController',
                     templateUrl: 'html/sysFunc/assignAuthority.html',
                     size: 'mm',
                     resolve: {
                         orgName: function() {
                             return info.name;
                         },
                         orgId: function() {
                             return info.id;
                         }
                     }
                 });
                 modalInstance.result.then(function(selectNodeObj) {
                     UserManageService.selectMenu(selectNodeObj).then(
                         function success(data) {
                             if (data && data.status == "AAAAAA") {
                                 Notifier.notifySuccess("保存成功！");
                             } else {
                                 Notifier.notifyWarning("保存失败！");
                             }
                             //$scope.showInfo = data;
                             //console.log(data);
                         });
                 });
             };
         })
         .controller('AssignAuthorityController', function($rootScope, $scope, UserManageService, treeService, orgName, orgId, Notifier, $uibModalInstance) {
             $scope.$on('nodeInit', function(event, nodeInit) {
                 if (nodeInit != null) {
                     for (var i = 0; i < nodeInit.length; i++) {
                         nodeInit[i].name = nodeInit[i].menuName;
                     }
                     $scope.nodes = nodeInit;
                 }
             });
             //接收子控制器的消息
             $scope.orgName = orgName;
             $scope.$on("menus", function(event, params) {
                 var nodeList = [];
                 treeService.postUrl("/EmApl/service/workFlow/dictionaryService/getMenus", params, function(data) {
                     //发送消息给子控制器
                     UserManageService.getSelectNodeByOrgId(orgId).then(
                         function success(respData) {
                             nodeList = respData;
                             //$scope.nodes = nodeList;
                             $scope.treeData = {
                                 data: data,
                                 nodeList: nodeList
                             };
                             $scope.$broadcast("menuData", $scope.treeData);
                         });
                 });
             });

             $scope.$on("checkedTree", function(event, nodes) {
                 //发送消息给子控制器
                 var data = [];
                 for (var i = 0; i < nodes.length; i++) {
                     //若是子菜单，存进data数组
                     if (nodes[i].pId != null && nodes[i].pId != 'undefind' && nodes[i].pId != 0) {
                         data.push(nodes[i]);
                     }
                 }
                 $scope.nodes = data;
                 if ($scope.$root.$$phase != '$apply' && $scope.$root.$$phase != '$digest') {
                     $scope.$apply();
                 }
             });

             $scope.save = function() {
                 // var selectNodes = ' ';
                 var arr = "";
                 if ($scope.nodes.length) {
                     for (var i = 0; i < $scope.nodes.length; i++) {
                         if ($scope.nodes[i].pId != null && $scope.nodes[i].pId != 'undefind' && $scope.nodes[i].pId != 0) {
                             arr = arr + $scope.nodes[i].id + ",";
                         }
                     }
                     arr = arr.substring(0, arr.length - 1);
                     console.log(arr);
                     $scope.selectNodeObj = {
                         groupId: orgId,
                         menuId: arr
                     };

                     $uibModalInstance.close($scope.selectNodeObj);
                 } else {
                    Notifier.notifyInfo("请至少勾选一项菜单！");
                 };
             };

             $scope.cancel = function() {
                 $uibModalInstance.dismiss('cancel');
             };
         });
 });
