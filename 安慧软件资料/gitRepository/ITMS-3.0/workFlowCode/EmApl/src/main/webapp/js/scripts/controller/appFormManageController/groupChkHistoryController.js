define(['app', 'service/userService/groupChkHistoryServices'], function(app) {
    'use strict'

    app.controller('GroupChkHistoryController', function($rootScope, $scope, $uibModal, GroupChkHistoryService) {

            $scope.totalItems = 0;
            $scope.currentPage = 1;
            $scope.maxSize = 7;
            $scope.sysName = "";
            $scope.pageSize = 15;

            $scope.deviceName = '';
            $scope.status = '';
            $scope.startTime = '';
            $scope.endTime = '';

            $scope.optionsArray = [{
                key: "设备申请",
                value: "selectDevChkByGroup"
            }, {
                key: "系统申请",
                value: "selectSysChkByGroup"
            },
            {
                 key: "系统验收申请",
                value: "selectSysCompleteChkByGroup"
            }]
            // default select value
            $scope.appType = $scope.optionsArray[0];

            $scope.getGroupChkAppList = function() {
                var appTypeValue = $scope.appType.value;
                var queryObj = {
                    "serviceMethod": appTypeValue,
                    "groupId": $scope.groupId,
                    "pageInfo": "1",
                    "currentPage": $scope.currentPage,
                    "pageSize": $scope.pageSize
                };

                GroupChkHistoryService.getGroupChkAppList(queryObj).then(
                    function success(data) {
                        if (data) {
                            $scope.showInfo = data.data;
                            $scope.totalItems = data.total;
                        }
                    });
            };

            $scope.getGroupChkAppList();

            $scope.$watch('currentPage', function() {
                $scope.getGroupChkAppList();
            });

            $scope.queryDetail = function(recordInfo) {
                console.log(recordInfo);
                var modalInstance = $uibModal.open({
                    controller: 'SysHistortDetailController',
                    templateUrl: 'html/sysApproveManage/sysExamine.html',
                    size: 'lg',
                    resolve: {
                        sysApproveId: function() {
                            return recordInfo.systemApproveId;
                        }
                    }
                });
                modalInstance.result.then(function(dataObj) {
                    //console.log(selectNodeObj);
                    UserManageService.deleteNoticeInfo(dataObj).then(
                        function success(data) {
                            //$scope.showInfo = data;
                            // console.log(data);
                            $scope.$emit('change');
                        });
                });
            }

        })
        .controller('SysHistortDetailController', function($rootScope, $scope, $uibModal, $uibModalInstance, SysApproveService, sysApproveId) {
            // console.log(deviceApproveId);
            $scope.showData = false;
            $scope.getHistorydetails = function() {
                    SysApproveService.getSysApproveDetail(sysApproveId).then(
                        function success(data) {
                            $scope.showInfo = data.data;
                            $scope.processId = $scope.showInfo.processId;
                            console.log($scope.processId);
                            if (data.dataChk) {
                                $scope.showExamineInfo = data.dataChk;
                            }
                            // $scope.approveDevice = $scope.showInfo.deviceApprove;
                        });
                }
                //load data
            $scope.getHistorydetails();

            $scope.cancel = function() {
                console.log('close button');
                $uibModalInstance.dismiss('cancel');
            }

        })

});
