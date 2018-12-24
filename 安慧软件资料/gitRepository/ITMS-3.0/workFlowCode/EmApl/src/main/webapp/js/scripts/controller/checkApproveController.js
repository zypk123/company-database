define(
    ['app', 'service/CheckApproveServices', 'service/userService/sysApproveServices', 'service/userService/sysApproveCompleteServices', 'service/Notifier'],
    function(app) {
        app.controller('checkApproveController', function($scope, $http, $cookies, $uibModal, checkApproveService) {
                var items = $scope.items = [];
                $scope.initPage = function() {
                    console.log('initPage');
                    var pData = {
                        user: $scope.loginName,
                        pageInfo: '0',
                        serviceMethod: 'selectTaskByUser'
                    };
                    checkApproveService.ajaxPostForm('/EmApl/service/workFlow/commonBaseService/selectAllData', pData,
                        function(data) {
                            console.log(data);
                            items.splice(0, items.length);
                            var arry = data.data;
                            for (var i = 0; i < arry.length; i++) {
                                console.log(arry[i]);
                                items.push(arry[i]);
                            }
                        });
                };
                $scope.tackClaim = function(taskId, approveId, queryValue) {
                    $scope.approveChk(taskId, approveId, queryValue);
                };
                $scope.initPage();

                $scope.approveChk = function(taskId, approveId, queryValue) {
                    var pdata = {
                        taskId: taskId,
                        approveId: approveId,
                        userId: $scope.loginName,
                        servieImg: $scope.servieImg
                    }
                    console.log(queryValue);
                    var tempUrl = '';
                    var pageController = '';
                    if (queryValue == 0) {
                        tempUrl = 'examine';
                        pageController = 'ChkInstanceCtrl';
                    } else if (queryValue == 1 || queryValue == 2) {
                        tempUrl = 'html/sysApproveManage/sysExamine.html';
                        pageController = 'SysExamineController';
                    }
                    var modalInstance = $uibModal.open({
                        animation: $scope.animationsEnabled,
                        templateUrl: tempUrl,
                        controller: pageController,
                        size: 'lg',
                        resolve: {
                            header: function() {
                                return pdata;
                            },
                            userModel: function() {
                                return $scope.userModel;
                            },
                            checkType: function() {
                                return queryValue;
                            }
                        }
                    });
                    modalInstance.result.then(function(result) {
                        //console.log(result);
                        $scope.initPage();
                    }, function(reason) {
                        console.log(reason);
                    });
                }
            })
            .controller(
                'ChkInstanceCtrl',
                function($scope, $http, $uibModalInstance,
                    userModel, header, checkApproveService) {
                    var slides = $scope.slides = [];
                    var records = $scope.records = [];

                    var scopeData;
                    var pData = { keys: 'roadType,roadLevel,terrain,pavementLevel,approveType,content,devicePosition' };
                    checkApproveService.postUrl('/EmApl/service/workFlow/dictionaryService/getAllDictionaryByKeys', pData, function(data) {
                        scopeData = data;
                        $scope.queryAjax('/EmApl/service/workFlow/dAservice/queryDeviceApproveDataById', pData, 'showData');
                    });

                    $scope.results = [{
                        value: "0",
                        name: "不通过"
                    }, {
                        value: "1",
                        name: "通过"
                    }];
                    $scope.result = '1';
                    var approveId = header.approveId;
                    var userId = header.userId;
                    var taskId = header.taskId;
                    var pData = {
                        approveId: approveId
                    };
                    $scope.queryAjax = function(url, pData, funcName) {
                        $http({
                            method: 'POSt',
                            url: url,
                            params: pData
                        }).success(function(data) {
                            eval("$scope." + funcName + "(data)");
                        });
                    };
                    $scope.queryAjaxForm = function(url, pData,
                        funcName) {
                        $http({
                            method: 'POSt',
                            url: url,
                            headers: {
                                'Content-Type': 'application/json'
                            },
                            data: pData
                        }).success(function(data) {
                            eval("$scope." + funcName + "(data)");
                        });
                    };

                    var imgSer = "http://" + header.servieImg + "/";
                    if (imgSer.indexOf('"') > -1) {
                        imgSer = imgSer.replace('"', '');
                        if (imgSer.indexOf('"') > -1) {
                            imgSer = imgSer.replace('"', '');
                        }
                    }

                    $scope.showData = function(data) {
                        var approveDevice = $scope.approveDevice = data.data;
                        //清空审批记录
                        $scope.records = [];
                        var array = data.dataChk;
                        for (var i = 0; i < array.length; i++) {
                            $scope.records.push(array[i]);
                        }

                        if (approveDevice.approveType != '000005') {
                            var roadStatusImgs = "";
                            if (approveDevice.roadStatusImg != null && approveDevice.roadStatusImg != undefined) {
                                roadStatusImgs = approveDevice.roadStatusImg;
                            }
                            var tmp = roadStatusImgs.split(",");
                            slides.splice(0, slides.length);
                            for (var i = 0; i < tmp.length; i++) {
                                slides.push({
                                    image: imgSer + tmp[i],
                                    text: '',
                                    id: i
                                });
                            }
                        }
                        //单位签章
                        $scope.signatureImgFile = imgSer + approveDevice.signatureImg;
                        //技术方案可能多个
                        $scope.solides = [];
                        if (approveDevice.solutionFile != null) {
                            var tmp = approveDevice.solutionFile.split(",");
                            for (var i = 0; i < tmp.length; i++) {
                                var obj = {};
                                obj.value = imgSer + tmp[i];
                                obj.name = tmp[i].substring(tmp[i].lastIndexOf('/') + 1);
                                $scope.solides.push(obj);
                            }
                        }
                        //把下拉列表值转换
                        $scope.getKeyName('roadType');
                        $scope.getKeyName('roadLevel');
                        $scope.getKeyName('terrain');
                        $scope.getKeyName('pavementLevel');
                        $scope.getKeyName('approveType');
                        $scope.getKeyName('content');
                        $scope.getKeyName('devicePosition');
                    }

                    $scope.getKeyName = function(key) {
                        var array = scopeData[key];
                        for (var i = 0; i < array.length; i++) {
                            if (array[i].value == $scope.approveDevice[key]) {
                                $scope.approveDevice[key] = array[i].name;
                                break;
                            }
                        }
                    }

                    //为了实现分支流合并时如果一条为不通过，则会直接结束，会签结果保存在approvalId
                    $scope.chkApprove = function() {
                        //遍历所有的审批记录，如果有不通过的则不能继续走
                        $scope.results = [{
                            value: "0",
                            name: "不通过"
                        }, {
                            value: "1",
                            name: "通过"
                        }];
                        var flag = 1;
                        if ($scope.result == '0') {
                            flag = 0;
                        }

                        for (var i = 0; i < $scope.records.length; i++) {
                            if ($scope.records[i].approvalResult == '不通过') {
                                flag = 0;
                                break;
                            }
                        }
                        var paramData = {
                            approvalId: flag,
                            applyId: approveId,
                            applyType: '0',
                            approvalUser: userId,
                            approvalComments: $scope.reason,
                            approvalResult: $scope.result,
                            taskId: taskId,
                            extension: $scope.approveDevice.processId,
                            isSystem: '0'
                        };
                        $scope.queryAjaxForm(
                            '/EmApl/service/workFlow/aCservice/processChkRecord',
                            paramData,
                            'submitChkApprove');
                    }

                    $scope.submitChkApprove = function(data) {
                        $uibModalInstance.close($scope.result);
                    }
                    $scope.ok = function() {
                        $uibModalInstance.close($scope.result);
                    };
                    $scope.cancel = function() {
                        $uibModalInstance.dismiss('cancel');
                    };
                })
            .controller('SysExamineController', function($scope, $http, SysApproveService, SysApproveCompleteService, $uibModalInstance, Notifier, userModel, header, checkType) {
                $scope.showData = true;
                var records = $scope.records = [];
                var approveId = header.approveId;

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
                var loadData = function() {
                    SysApproveService.examineApplicationForm(approveId).then(
                        function success(data) {
                            $scope.showInfo = data.data;
                            $scope.processId = $scope.showInfo.processId;
                            //temporary data
                            var tempData = data.data;

                            if ($scope.showInfo.approveImg != null && $scope.showInfo.approveImg != undefined && $scope.showInfo.approveImg != '') {
                                $scope.showInfo.approveImg = format(tempData.approveImg);
                            }
                            if ($scope.showInfo.departmentsImg != null && $scope.showInfo.departmentsImg != undefined && $scope.showInfo.departmentsImg != '') {
                                $scope.showInfo.departmentsImg = format(tempData.departmentsImg);
                            }
                            if ($scope.showInfo.plansFile != null && $scope.showInfo.plansFile != undefined && $scope.showInfo.plansFile != '') {
                                $scope.showInfo.plansFile = format(tempData.plansFile);
                            }
                            if ($scope.showInfo.solutionFile != null && $scope.showInfo.solutionFile != undefined && $scope.showInfo.solutionFile != '') {
                                $scope.showInfo.solutionFile = format(tempData.solutionFile);
                            }
                            if ($scope.showInfo.expertOpinionImg != null && $scope.showInfo.expertOpinionImg != undefined && $scope.showInfo.expertOpinionImg != '') {
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
                                SysApproveService.subExamineApprove(paramData).then(
                                    function success(data) {
                                        Notifier.notifySuccess("审批完成！");
                                        $uibModalInstance.close($scope.result);
                                    });

                            }

                        });
                }

                var loadCheckData = function() {
                    SysApproveCompleteService.examineApplicationForm(approveId).then(
                        function success(data) {
                            $scope.showInfo = data.data;
                            $scope.processId = $scope.showInfo.processId;
                            var tempData = data.data;
                            if (data.dataChk) {
                                $scope.showExamineInfo = data.dataChk;
                            }

                            //tags show-hide true or false
                            if ($scope.showInfo.approveImg != null && $scope.showInfo.approveImg != undefined && $scope.showInfo.approveImg != '') {
                                $scope.showInfo.approveImg = format(tempData.approveImg);
                            }
                            if ($scope.showInfo.departmentsImg != null && $scope.showInfo.departmentsImg != undefined && $scope.showInfo.departmentsImg != '') {
                                $scope.showInfo.departmentsImg = format(tempData.departmentsImg);
                            }
                            if ($scope.showInfo.plansFile != null && $scope.showInfo.plansFile != undefined && $scope.showInfo.plansFile != '') {
                                $scope.showInfo.plansFile = format(tempData.plansFile);
                            }
                            if ($scope.showInfo.solutionFile != null && $scope.showInfo.solutionFile != undefined && $scope.showInfo.solutionFile != '') {
                                $scope.showInfo.solutionFile = format(tempData.solutionFile);
                            }
                            if ($scope.showInfo.expertOpinionImg != null && $scope.showInfo.expertOpinionImg != undefined && $scope.showInfo.expertOpinionImg != '') {
                                $scope.showInfo.expertOpinionImg = format(tempData.expertOpinionImg);
                            }

                            if ($scope.showInfo.fundsFile != null && $scope.showInfo.fundsFile != undefined && $scope.showInfo.fundsFile != '') {
                                $scope.showFundsFile = true;
                                $scope.showInfo.fundsFile = format(tempData.fundsFile);
                            }
                            if ($scope.showInfo.purchaseFile != null && $scope.showInfo.purchaseFile != undefined && $scope.showInfo.purchaseFile != '') {
                                $scope.showPurchaseFile = true;
                                $scope.showInfo.purchaseFile = format(tempData.purchaseFile);
                            }
                            if ($scope.showInfo.contractFile != null && $scope.showInfo.contractFile != undefined && $scope.showInfo.contractFile != '') {
                                $scope.showContractFile = true;
                                $scope.showInfo.contractFile = format(tempData.contractFile);
                            }
                            if ($scope.showInfo.checkFile != null && $scope.showInfo.checkFile != undefined && $scope.showInfo.checkFile != '') {
                                $scope.showCheckFile = true;
                                $scope.showInfo.checkFile = format(tempData.checkFile);
                            }
                            if ($scope.showInfo.remarkFile != null && $scope.showInfo.remarkFile != undefined && $scope.showInfo.remarkFile != '') {
                                $scope.showRemarkFile = true;
                                $scope.showInfo.remarkFile = format(tempData.remarkFile);
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

                if (checkType == 1) {
                    loadData();
                } else if (checkType == 2) {
                    loadCheckData();
                }

                // console.log($scope.showInfo.processId);
                //遍历所有的审批记录，如果有不通过的则不能继续走
            });
    });
