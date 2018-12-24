define(['app', 'service/userService/sysApproveCompleteServices', 'service/Notifier'], function(app) {
    'use strict'

    app.controller('SysApproveCompleteFormController', function($scope, $rootScope, SysApproveCompleteService, $state, Notifier, $uibModal) {

            $scope.totalItems = 0;
            $scope.currentPage = 1;
            $scope.maxSize = 7;
            $scope.sysName = "";
            $scope.pageSize = 5;

            $scope.deviceName = '';
            $scope.status = '';
            $scope.startTime = '';
            $scope.endTime = '';

            $scope.getApproveList = function() {
                var queryObj = {
                    "groupId": $scope.groupId,
                    "systemName": $scope.systemName,
                    "endTime": $scope.endTime,
                    "startTime": $scope.startTime,
                    "startPage": $scope.currentPage,
                    "pageSize": $scope.pageSize,
                    "status": $scope.status
                };

                SysApproveCompleteService.getSysCheckFormList(queryObj).then(
                    function success(data) {
                        if (data) {
                            $scope.showInfo = data.data;
                            $scope.totalItems = data.total;
                        }
                    });
            };

            //$scope.getApproveList();

            $scope.$watch('currentPage', function() {
                $scope.getApproveList();
            });

            $scope.formater = function(value) {
                var returnStr = '';
                if (value == '0') {
                    returnStr = '新建';
                } else if (value == '1') {
                    returnStr = '审批中';
                } else if (value == '2') {
                    returnStr = '通过';
                } else if (value == '3') {
                    returnStr = '不通过';
                } else {
                    returnStr = '待创建'
                }
                return returnStr;
            }

            $scope.subApprove = function(info) {
                var subApproveObj = {
                    approveId: info.id,
                    processId: info.processId,
                    status: info.status
                }
                SysApproveCompleteService.subApplicationForm(subApproveObj).then(
                    function success(data) {
                        $scope.getApproveList();
                    }
                );
            }

            $scope.openImg = function(processId) {
                var modalInstance = $uibModal.open({
                    //animation: $scope.animationsEnabled,
                    templateUrl: 'prcossImg',
                    controller: 'ShowImgCtrl',
                    size: 'lg',
                    resolve: {
                        header: function() {
                            return processId
                        },
                        userModel: function() {
                            return $scope.userModel;
                        }
                    }
                });
            }

            $scope.newSysCheck = function(info) {
                $rootScope.state = {
                    optType: 'newSysCheck',
                    sysApproveId: $scope.systemApproveId,
                    infoObj: info
                };
                $state.go('newSysCheckForm');
            };

            $scope.editData = function(info) {
                $rootScope.state = {
                    optType: 'editSysCheck',
                    sysApproveId: $scope.systemApproveId,
                    infoObj: info
                };
                $state.go('newSysCheckForm');
            };

            $scope.viewData = function(systemApproveId) {
                console.log(systemApproveId);
                var modalInstance = $uibModal.open({
                    controller: 'SysExamineController',
                    templateUrl: 'html/sysApproveManage/sysExamine.html',
                    size: 'lg',
                    resolve: {
                        sysApproveId: function() {
                            return systemApproveId;
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

            $scope.deleteApprove = function(approveId) {
                SysApproveCompleteService.cancelApplicationForm(approveId).then(
                    function succsss(data) {
                        $scope.getApproveList();
                    });
            }

        })
        .controller('ShowImgCtrl', function($scope, $http, $uibModalInstance, userModel, header) {
            $scope.imgUrl = "/EmApl/imgServlet?imgType=2&processDefinitionId=" + header;
            $scope.ok = function() {
                $uibModalInstance.close($scope.result);
            };
            $scope.cancel = function() {
                $uibModalInstance.dismiss('cancel');
            };


            var flag = '0';
            $scope.imgwd = 880;
            $scope.enlarge = function() {
                if (flag == '0') {
                    $scope.imgwd = 880 * 1.5;
                    flag = '1';
                } else {
                    $scope.imgwd = 880;
                    flag = '0';
                }
            }
        })
        .controller('SysExamineController', function($scope, $http, SysApproveCompleteService, $uibModalInstance, Notifier, sysApproveId) {
            $scope.showData = false;
            var records = $scope.records = [];
            var approveId = sysApproveId;

            var imgSer = "http://" + '/';
            if (imgSer.indexOf('"') > -1) {
                imgSer = imgSer.replace('"', '');
                if (imgSer.indexOf('"') > -1) {
                    imgSer = imgSer.replace('"', '');
                }
            }

            var format = function(fileData) {
                    var tempArr = [];
                    if (fileData != null) {
                        var tmp = fileData.split(",");
                        for (var i = 0; i < tmp.length; i++) {
                            var obj = {};
                            obj.value = imgSer + tmp[i];
                            obj.name = tmp[i].substring(tmp[i].lastIndexOf('/') + 1);
                            tempArr.push(obj);
                        }
                    }
                    return tempArr;
                }
                /*var loadData = function() {
                    SysApproveCompleteService.examineApplicationForm(approveId).then(
                        function success(data) {
                            $scope.showInfo = data.data;
                            $scope.processId = $scope.showInfo.processId;
                            //temporary data
                            var tempData = data.data;
                            if($scope.showInfo.approveImg != null && $scope.showInfo.approveImg != undefined && $scope.showInfo.approveImg != ''){
                                $scope.showInfo.approveImg = format(tempData.approveImg);
                            }
                            if($scope.showInfo.departmentsImg != null && $scope.showInfo.departmentsImg != undefined && $scope.showInfo.departmentsImg != ''){
                                $scope.showInfo.departmentsImg = format(tempData.departmentsImg);
                            }
                            if($scope.showInfo.plansFile != null && $scope.showInfo.plansFile != undefined && $scope.showInfo.plansFile != ''){
                                $scope.showInfo.plansFile = format(tempData.plansFile);
                            }
                            if($scope.showInfo.solutionFile != null && $scope.showInfo.solutionFile != undefined && $scope.showInfo.solutionFile != ''){
                                $scope.showInfo.solutionFile = format(tempData.solutionFile);
                            }
                            if($scope.showInfo.expertOpinionImg != null && $scope.showInfo.expertOpinionImg != undefined && $scope.showInfo.expertOpinionImg != ''){
                                $scope.showInfo.expertOpinionImg = format(tempData.expertOpinionImg);
                            }

                            console.log($scope.processId);
                            if (data.dataChk) {
                                $scope.showExamineInfo = data.dataChk;
                            }
                            //console.log($scope.processId);
                            //异步加载
                            $scope.chkApprove = function() {

                                var flag = 1;
                                if ($scope.result == '0') {
                                    flag = 0;
                                }
                                //遍历所有的审批记录，如果有不通过的则不能继续走
                                if ($scope.showExamineInfo) {
                                    for (var i = 0; i < $scope.showExamineInfo.length; i++) {
                                        if ($scope.showExamineInfo[i].approvalResult == '不通过') {
                                            flag = 0;
                                            break;
                                        }
                                    }
                                }

                                var paramData = {
                                    approvalId: flag,
                                    applyId: approveId,
                                    applyType: '0',
                                    approvalUser: header.userId,
                                    approvalComments: $scope.reason,
                                    approvalResult: $scope.result,
                                    taskId: header.taskId,
                                    extension: $scope.processId,
                                    isSystem: '1'
                                };
                                console.log(paramData);
                                // debugger;
                                SysApproveCompleteService.subExamineApprove(paramData).then(
                                    function success(data) {
                                        Notifier.notifySuccess("审批完成！");
                                        $uibModalInstance.close($scope.result);
                                    });

                            }

                        });
                }

                loadData();
*/
            $scope.cancel = function() {
                $uibModalInstance.dismiss('cancel');
            }

            var loadCheckData = function() {
                SysApproveCompleteService.examineApplicationForm(approveId).then(
                    function success(data) {
                        $scope.showInfo = data.data;
                        $scope.oldData = data.data;
                        $scope.processId = $scope.showInfo.processId;
                        if (data.dataChk) {
                            $scope.showExamineInfo = data.dataChk;
                        }

                        //tags show-hide true or false
                        //加载附件
                        if ($scope.oldData.approveImg != '' && $scope.oldData.approveImg != null && $scope.oldData.approveImg != 'undefined') {
                            $scope.showInfo.approveImg = format($scope.oldData.approveImg);
                        }
                        if ($scope.oldData.departmentsImg != '' && $scope.oldData.departmentsImg != null && $scope.oldData.departmentsImg != 'undefined') {
                            $scope.showInfo.departmentsImg = format($scope.oldData.departmentsImg);
                        }
                        if ($scope.oldData.solutionFile != '' && $scope.oldData.solutionFile != null && $scope.oldData.solutionFile != 'undefined') {
                            $scope.showInfo.solutionFile = format($scope.oldData.solutionFile);
                        }
                        if ($scope.oldData.plansFile != '' && $scope.oldData.plansFile != null && $scope.oldData.plansFile != 'undefined') {
                            $scope.showInfo.plansFile = format($scope.oldData.plansFile);
                        }
                        if ($scope.oldData.expertOpinionImg != '' && $scope.oldData.expertOpinionImg != null && $scope.oldData.expertOpinionImg != 'undefined') {
                            $scope.showInfo.expertOpinionImg = format($scope.oldData.expertOpinionImg);
                        }

                        if ($scope.oldData.fundsFile != '' && $scope.oldData.fundsFile != null && $scope.oldData.fundsFile != 'undefined') {
                            $scope.showFundsFile = true;
                            $scope.showInfo.fundsFile = format($scope.oldData.fundsFile);
                        } else {
                            $scope.showInfo.fundsFile = [];
                        }
                        if ($scope.oldData.purchaseFile != '' && $scope.oldData.purchaseFile != null && $scope.oldData.purchaseFile != 'undefined') {
                            $scope.showPurchaseFile = true;
                            $scope.showInfo.purchaseFile = format($scope.oldData.purchaseFile);
                        } else {
                            $scope.showInfo.purchaseFile = [];
                        }
                        if ($scope.oldData.checkFile != '' && $scope.oldData.checkFile != null && $scope.oldData.checkFile != 'undefined') {
                            $scope.showCheckFile = true;
                            $scope.showInfo.checkFile = format($scope.oldData.checkFile);
                        } else {
                            $scope.showInfo.checkFile = [];
                        }
                        if ($scope.oldData.contractFile != '' && $scope.oldData.contractFile != null && $scope.oldData.contractFile != 'undefined') {
                            $scope.showContractFile = true;
                            $scope.showInfo.contractFile = format($scope.oldData.contractFile);
                        } else {
                            $scope.showInfo.contractFile = [];
                        }
                        if ($scope.oldData.remarkFile != '' && $scope.oldData.remarkFile != null && $scope.oldData.remarkFile != 'undefined') {
                            $scope.showRemarkFile = true;
                            $scope.showInfo.remarkFile = format($scope.oldData.remarkFile);
                        } else {
                            $scope.showInfo.remarkFile = [];
                        }
                        //console.log($scope.processId);
                        //异步加载
                        $scope.chkApprove = function() {

                            var flag = 1;
                            if ($scope.result == '0') {
                                flag = 0;
                            }
                            //遍历所有的审批记录，如果有不通过的则不能继续走
                            if ($scope.showExamineInfo) {
                                for (var i = 0; i < $scope.showExamineInfo.length; i++) {
                                    if ($scope.showExamineInfo[i].approvalResult == '不通过') {
                                        flag = 0;
                                        break;
                                    }
                                }
                            }

                            var paramData = {
                                approvalId: flag,
                                applyId: approveId,
                                applyType: '0',
                                approvalUser: header.userId,
                                approvalComments: $scope.reason,
                                approvalResult: $scope.result,
                                taskId: header.taskId,
                                extension: $scope.processId,
                                isSystem: '2'
                            };
                            console.log(paramData);
                            // debugger;
                            SysApproveCompleteService.subExamineApprove(paramData).then(
                                function success(data) {
                                    Notifier.notifySuccess("审批完成！");
                                    $uibModalInstance.close($scope.result);
                                });

                        }

                    });
            }

            loadCheckData();

            /* if (checkType == 1) {
                 loadData();
             } else if (checkType == 2) {
                 loadCheckData();
             }*/

            // console.log($scope.showInfo.processId);
            //遍历所有的审批记录，如果有不通过的则不能继续走
        });

});
