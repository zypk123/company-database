define(['app', 'service/userService/userManageServices','service/Notifier' ], function(app) {
    'use strict'

    app.controller('SysNoticeController', function($rootScope, $scope, UserManageService, Notifier, $uibModal) {
            $scope.totalItems = 0;
            $scope.currentPage = 1;
            $scope.maxSize = 7;
            $scope.sysName = "";
            var pageSize = 10;
            $scope.title = '';
            $scope.startTime = '';
            $scope.endTime = '';

            $scope.getSysNoticeList = function() {
                console.log($scope.startTime);
                var queryObj = {
                    messageTitle: $scope.title,
                    startTime: $scope.startTime,
                    endTime: $scope.endTime,
                    startPage: $scope.currentPage,
                    pageSize: 10
                };
                //console.log($scope.title);
                UserManageService.getSysNotice(queryObj).then(
                    function success(data) {
                        $scope.showInfo = data.data;
                        $scope.totalItems = data.totalsize;
                    });
            };

            $scope.getSysNoticeList();

            $scope.$watch('currentPage', function() {
                //console.log('@@@@');
                $scope.getSysNoticeList();
            });

            $scope.addNotice = function() {
                var modalInstance = $uibModal.open({
                    controller: 'NoticeOptController',
                    templateUrl: 'html/sysFunc/noticeOpt.html',
                    size: 'mm',
                    resolve: {
                        optType: function() {
                            return 'add';
                        },
                        noticeInfo: function() {
                            return '';
                        }
                    }
                });
                modalInstance.result.then(function(dataObj) {
                    //console.log(selectNodeObj);
                    UserManageService.addNoticeInfo(dataObj).then(
                        function success(data) {
                            Notifier.notifySuccess("添加成功！");
                            $scope.showInfo = data;
                            $scope.getSysNoticeList();
                            //console.log(data);
                        });

                });
            };

            $scope.editNotice = function(info) {
                var modalInstance = $uibModal.open({
                    controller: 'NoticeOptController',
                    templateUrl: 'html/sysFunc/noticeOpt.html',
                    size: 'mm',
                    resolve: {
                        optType: function() {
                            return 'edit';
                        },
                        noticeInfo: function() {
                            return info;
                        }
                    }
                });
                modalInstance.result.then(function(dataObj) {
                    //console.log(selectNodeOb阿勒软塌j);
                    UserManageService.editNoticeInfo(dataObj).then(
                        function success(data) {
                            // debugger;
                            if (data == 1) {
                                Notifier.notifySuccess("修改成功！");
                                //$scope.notifierService.setMessage('修改成功！', 'success');
                                $scope.$emit('change');
                            } else {
                                Notifier.notifyWarning("修改失败！");
                            }

                        });

                });
            };

            $scope.deleteNotice = function(info) {
                var noticeId = info.messageId;
                console.log(noticeId);
                var modalInstance = $uibModal.open({
                    controller: 'DeleteTipController',
                    templateUrl: 'html/sysFunc/deleteTip.html',
                    size: 'sm',
                    resolve: {
                        noticeId: function() {
                            return noticeId;
                        }
                    }
                });
                modalInstance.result.then(function(dataObj) {
                    //console.log(selectNodeObj);
                    UserManageService.deleteNoticeInfo(dataObj).then(
                        function success(data) {
                            //$scope.showInfo = data;
                           Notifier.notifySuccess("删除成功！");
                            $scope.$emit('change');
                        });

                });
            };

            $scope.$on('change', function(event) {
                $scope.getSysNoticeList();
            });

        })
        .controller('NoticeOptController', function($rootScope, $scope, UserManageService, optType, noticeInfo, $uibModalInstance) {
            //console.log(optType);
            $scope.showTitle = true;
            $scope.oldInfo = noticeInfo;

            if (optType == 'edit') {
                $scope.showTitle = !$scope.showTitle;
                $scope.title = $scope.oldInfo.messageTitle;
                $scope.messageContent = $scope.oldInfo.messageContent;
            }

            $scope.save = function() {
                if (optType == 'edit') {
                    console.log('@@@@');
                    $scope.infoObj = {
                        messageId: $scope.oldInfo.messageId,
                        messageTitle: $scope.title,
                        messageContent: $scope.messageContent
                    };
                } else {
                    $scope.infoObj = {
                        messageTitle: $scope.title,
                        messageContent: $scope.messageContent
                    };
                }

                $uibModalInstance.close($scope.infoObj);
            };
            $scope.cancel = function() {
                $uibModalInstance.dismiss('cancel');
            }

        })
        .controller('DeleteTipController', function($rootScope, $scope, noticeId, $uibModalInstance) {

            $scope.ok = function() {
                $uibModalInstance.close(noticeId);
            }
            $scope.cancel = function() {
                $uibModalInstance.dismiss('cancel');
            }

        })

});
