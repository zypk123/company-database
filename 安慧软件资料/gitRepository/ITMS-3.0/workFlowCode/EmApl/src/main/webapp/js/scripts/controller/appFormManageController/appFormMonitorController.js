 define(['app', 'service/userService/applicationFromServices', 'service/DeployServices'], function(app) {
     'use strict'

     app.controller('AppFormMonitorController', function($rootScope, $scope, $uibModal, ApplicationFromService) {
         //load org infomation
         $scope.showPanel = true;
         $scope.totalItems = 0;
         $scope.currentPage = 1;
         $scope.maxSize = 2;
         $scope.pageSize = 6;
         $scope.deviceName = "";
         // $scope.numPages = 5;
         $scope.status = 1;

         $scope.searchData = function() {
             $scope.queryObj = {
                 deviceName: $scope.deviceName,
                 startPage: $scope.currentPage,
                 pageSize: $scope.pageSize,
                 status: $scope.status
             };
             ApplicationFromService.getApplicationFormList($scope.queryObj).then(
                 function success(data) {
                     //hide right panel
                     $scope.showImg = false;
                     if (data.total) {
                         $scope.showPanel = true;
                         $scope.showInfo = data.data;
                         $scope.totalItems = data.total;
                         //The default display first in right aera
                         $scope.showImg = true;
                         $scope.approveStatus($scope.showInfo[0]);
                     } else {
                         $scope.showPanel = false;
                     }
                 });
         };

         $scope.searchData();

         $scope.$watch('currentPage', function() {
             $scope.searchData();
         });

         $scope.approveStatus = function(info) {
             console.log(info);
             $scope.showImg = true;
             $scope.imgwd = 800;
             $scope.header = info.processId;
             //load img
             $scope.imgUrl = "/EmApl/imgServlet?imgType=2&processDefinitionId=" + $scope.header;
             //load form
             ApplicationFromService.getApproveFormStatus(info.deviceApproveId).then(
                 function success(data) {
                     $scope.formList = data.dataChk;
                     console.log($scope.formList);
                 });
         };

         var flag = '0';
         $scope.details = function() {
             console.log(flag);
             if (flag == '0') {
                 $scope.imgwd = 800 * 1.5;
                 flag = '1';
             } else {
                 $scope.imgwd = 800;
                 flag = '0';
             }
         };

     })

 });
