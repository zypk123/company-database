define(['app', 'service/userService/applicationFromServices'], function(app) {
    'use strict'

    app.controller('HistoryrecordsController', function($rootScope, $scope, $uibModal, ApplicationFromService) {

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

                ApplicationFromService.getHistoryApprove(queryObj).then(
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
                    controller: 'HistortDetailController',
                    templateUrl: 'html/appFormManage/historyDetail.html',
                    size: 'lg',
                    resolve: {
                        deviceApproveId: function() {
                            return recordInfo.deviceApproveId;
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
        .controller('HistortDetailController', function($rootScope, $scope, $uibModal, $uibModalInstance, ApplicationFromService, deviceApproveId) {
            // console.log(deviceApproveId);
            $scope.getHistorydetails = function() {
                    ApplicationFromService.getHistoryApproveDetail(deviceApproveId).then(
                        function success(data) {
                            $scope.approveDevice = data.data;
                            //console.log(data.dataChk);
                            if (data.dataChk) {
                                $scope.approveInfo = data.dataChk;
                            }
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
