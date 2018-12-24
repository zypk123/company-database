define(['app', 'service/userService/sysApproveServices'], function(app) {
    'use strict'

    app.controller('SysHistoryRecordsController', function($rootScope, $scope, $uibModal, SysApproveService) {

            $scope.totalItems = 0;
            $scope.currentPage = 1;
            $scope.maxSize = 7;
            $scope.sysName = "";
            $scope.pageSize = 15;

            $scope.deviceName = '';
            $scope.status = '';
            $scope.startTime = '';
            $scope.endTime = '';

            $scope.getApproveList = function() {
                var queryObj = {
                    "groupId": $scope.groupId,
                    "endTime": $scope.endTime,
                    "deviceName": $scope.deviceName,
                    "startTime": $scope.startTime,
                    "startPage": 1,
                    "pageSize": 15,
                    "status": $scope.status
                };

                SysApproveService.querySysHistoryApprove(queryObj).then(
                    function success(data) {
                        if (data) {
                            $scope.showInfo = data.data;
                            $scope.totalItems = data.total;
                        }
                    });
            };

            $scope.getApproveList();

            $scope.$watch('currentPage', function() {
                $scope.getApproveList();
            });

            $scope.queryDetail = function(recordInfo) {
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
