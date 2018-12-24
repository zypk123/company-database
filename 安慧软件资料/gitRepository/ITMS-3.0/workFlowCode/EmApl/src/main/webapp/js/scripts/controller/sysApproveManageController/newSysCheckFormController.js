define(['app', 'service/userService/sysApproveCompleteServices', 'service/Notifier'], function(app) {
    'use strict'

    app.controller('NewSysCheckFormController', function($scope, $rootScope, SysApproveCompleteService, $state, Notifier, Upload, $uibModal) {
        $scope.showInfo = {};
        $scope.showInfo.fundsFile = [];
        $scope.showInfo.purchaseFile = [];
        $scope.showInfo.contractFile = [];
        $scope.showInfo.checkFile = [];
        $scope.showInfo.remarkFile = [];

        $scope.fundsFileArray = [];
        $scope.purchaseFileArray = [];
        $scope.contractFileArray = [];
        $scope.checkFileArray = [];
        $scope.remarkFileArray = [];

        $scope.delFundsFile = [];
        $scope.delPurchaseFile = [];
        $scope.delContractFile = [];
        $scope.delCheckFile = [];
        $scope.delRemarkFile = [];

        // console.log($rootScope);
        if ($rootScope.state == null || $rootScope.state == 'undefined' || $rootScope.state == '') {
            $rootScope.state = {};
            $state.go('sysApproveCompleteForm');
            return;
        }
        $scope.sysApproveId = $rootScope.state.sysApproveId;
        $scope.oldData = $rootScope.state.infoObj;
        $scope.showInfo = $scope.oldData;

        var imgSer = "http://";
        if (imgSer.indexOf('"') > -1) {
            imgSer = imgSer.replace('"', '');
            if (imgSer.indexOf('"') > -1) {
                imgSer = imgSer.replace('"', '');
            }
        }

        // filter path function
        $scope.filterFunc = function(dataObj) {
            // console.log(dataObj);
            // debugger;
            if (dataObj) {
                var tempObj = dataObj.split(",");
                var newTemp = [];
                $scope.newFiles = "";
                for (var i = 0; i < tempObj.length; i++) {
                    var obj = {};
                    obj.value = imgSer + tempObj[i];
                    obj.name = tempObj[i].substring(tempObj[i].lastIndexOf('/') + 1);
                    $scope.newFiles += obj.name + "，";
                    newTemp.push(obj);
                }
                return newTemp;
            } else {
                return '';
            }
        }

        //加载附件
        if ($scope.oldData.approveImg != '' && $scope.oldData.approveImg != null && $scope.oldData.approveImg != 'undefined') {
            $scope.showInfo.approveImg = $scope.filterFunc($scope.oldData.approveImg);
        }
        if ($scope.oldData.departmentsImg != '' && $scope.oldData.departmentsImg != null && $scope.oldData.departmentsImg != 'undefined') {
            $scope.showInfo.departmentsImg = $scope.filterFunc($scope.oldData.departmentsImg);
        }
        if ($scope.oldData.solutionFile != '' && $scope.oldData.solutionFile != null && $scope.oldData.solutionFile != 'undefined') {
            $scope.showInfo.solutionFile = $scope.filterFunc($scope.oldData.solutionFile);
        }
        if ($scope.oldData.plansFile != '' && $scope.oldData.plansFile != null && $scope.oldData.plansFile != 'undefined') {
            $scope.showInfo.plansFile = $scope.filterFunc($scope.oldData.plansFile);
        }
        if ($scope.oldData.expertOpinionImg != '' && $scope.oldData.expertOpinionImg != null && $scope.oldData.expertOpinionImg != 'undefined') {
            $scope.showInfo.expertOpinionImg = $scope.filterFunc($scope.oldData.expertOpinionImg);
        }

        if ($scope.oldData.fundsFile != '' && $scope.oldData.fundsFile != null && $scope.oldData.fundsFile != 'undefined') {
            $scope.showInfo.fundsFile = $scope.filterFunc($scope.oldData.fundsFile);
        } else {
            $scope.showInfo.fundsFile = [];
        }
        if ($scope.oldData.purchaseFile != '' && $scope.oldData.purchaseFile != null && $scope.oldData.purchaseFile != 'undefined') {
            $scope.showInfo.purchaseFile = $scope.filterFunc($scope.oldData.purchaseFile);
        } else {
            $scope.showInfo.purchaseFile = [];
        }
        if ($scope.oldData.checkFile != '' && $scope.oldData.checkFile != null && $scope.oldData.checkFile != 'undefined') {
            $scope.showInfo.checkFile = $scope.filterFunc($scope.oldData.checkFile);
        } else {
            $scope.showInfo.checkFile = [];
        }
        if ($scope.oldData.contractFile != '' && $scope.oldData.contractFile != null && $scope.oldData.contractFile != 'undefined') {
            $scope.showInfo.contractFile = $scope.filterFunc($scope.oldData.contractFile);
        } else {
            $scope.showInfo.contractFile = [];
        }
        if ($scope.oldData.remarkFile != '' && $scope.oldData.remarkFile != null && $scope.oldData.remarkFile != 'undefined') {
            $scope.showInfo.remarkFile = $scope.filterFunc($scope.oldData.remarkFile);
        } else {
            $scope.showInfo.remarkFile = [];
        }


        $scope.uploadAccessories = function(fileobj, fileType) {
            var files = fileobj.files;
            // debugger;
            switch (fileType) {
                case 'fundsFileType':
                    {
                        // $scope.approveImg = "";
                        $scope.fundsFileArray.splice(0, $scope.fundsFileArray.length);
                        // $scope.showInfo.fundsFile.splice(0, $scope.showInfo.fundsFile.length);
                        for (var i = 0; i < files.length; i++) {
                            $scope.fundsFileArray.push(files[i]);
                            var temp = {};
                            temp.name = files[i].name;
                            temp.value = "#";
                            $scope.showInfo.fundsFile.push(temp);
                            // console.log($scope.showInfo.fundsFile);
                        }
                        $scope.$apply();
                    }
                    break;
                case 'purchaseFileType':
                    {
                        // $scope.purchaseFile = "";
                        $scope.purchaseFileArray.splice(0, $scope.purchaseFileArray.length);
                        for (var i = 0; i < files.length; i++) {
                            $scope.purchaseFileArray.push(files[i]);
                            var temp = {};
                            temp.name = files[i].name;
                            temp.value = "#";
                            $scope.showInfo.purchaseFile.push(temp);
                        }
                        // if ($scope.oldpurchaseFile.length > 0) {
                        //     for (var i = 0; i < $scope.oldpurchaseFile.length; i++) {
                        //         $scope.showInfo.purchaseFile.push($scope.oldpurchaseFile[i]);
                        //     }
                        // }
                        // for (var i = 0; i < files.length; i++) {
                        //     $scope.departmentsImgArray.push(files[i]);
                        //     $scope.departmentsImg += files[i].name + "，";
                        // }
                        // LocalStorage.add('approveImgeArr', departmentsImgArray);
                        $scope.$apply();
                    }
                    break;
                case 'contractFileType':
                    {
                        // $scope.contractFile = "";
                        $scope.contractFileArray.splice(0, $scope.contractFileArray.length);
                        // if (files.length > 5) {
                        //     Notifier.notifyWarning("请示文件不能超出5个，请重新选择！");
                        //     return;
                        // }
                        for (var i = 0; i < files.length; i++) {
                            $scope.contractFileArray.push(files[i]);
                            var temp = {};
                            temp.name = files[i].name;
                            temp.value = "#";
                            $scope.showInfo.contractFile.push(temp);
                        }
                        // if ($scope.oldcontractFile.length > 0) {
                        //     for (var i = 0; i < $scope.oldcontractFile.length; i++) {
                        //         $scope.showInfo.contractFile.push($scope.oldcontractFile[i]);
                        //     }
                        // }
                        // LocalStorage.add('approveImgeArr', solutionFileArray);
                        $scope.$apply();
                    }
                    break;
                case 'checkFileType':
                    {
                        // $scope.checkFile = "";
                        $scope.checkFileArray.splice(0, $scope.checkFileArray.length);
                        if (files.length > 5) {
                            Notifier.notifyWarning("请示文件不能超出5个，请重新选择！");
                            return;
                        }
                        for (var i = 0; i < files.length; i++) {
                            $scope.checkFileArray.push(files[i]);
                            var temp = {};
                            temp.name = files[i].name;
                            temp.value = "#";
                            $scope.showInfo.checkFile.push(temp);
                        }
                        // if ($scope.oldcheckFile.length > 0) {
                        //     for (var i = 0; i < $scope.oldcheckFile.length; i++) {
                        //         $scope.showInfo.checkFile.push($scope.oldcheckFile[i]);
                        //     }
                        // }
                        // LocalStorage.add('approveImgeArr', plansFileArray);
                        $scope.$apply();
                    }
                    break;
                case 'remarkFileType':
                    {
                        // $scope.remarkFile = "";
                        $scope.remarkFileArray.splice(0, $scope.remarkFileArray.length);
                        if (files.length > 5) {
                            Notifier.notifyWarning("请示文件不能超出5个，请重新选择！");
                            return;
                        }
                        for (var i = 0; i < files.length; i++) {
                            $scope.remarkFileArray.push(files[i]);
                            var temp = {};
                            temp.name = files[i].name;
                            temp.value = "#";
                            $scope.showInfo.remarkFile.push(temp);
                        }
                        // if ($scope.oldremarkFile.length > 0) {
                        //     for (var i = 0; i < $scope.oldremarkFile.length; i++) {
                        //         $scope.showInfo.remarkFile.push($scope.oldremarkFile[i]);
                        //     }
                        // }
                        // LocalStorage.add('approveImgeArr', expertOpinionImgArray);
                        $scope.$apply();
                    }
                    break;
            }
        }

        $scope.delSize = 0;
        $scope.deleteAccessories = function(infoObj, fileType) {
            // console.log("deleteAccessories");
            $scope.delSize = 1;
            if ($rootScope.state.optType == 'newSysCheck') {
                switch (fileType) {
                    case 'fundsFileType':
                        {
                            if (infoObj != null || $scope.fundsFileArray != null) {
                                // console.log("@@@@@@");
                                for (var i = 0; i < $scope.fundsFileArray.length; i++) {
                                    if (infoObj.name == $scope.fundsFileArray[i].name) {
                                        // console.log("#####");
                                        $scope.fundsFileArray.splice(i, 1);
                                        $scope.showInfo.fundsFile.splice(i, 1);
                                    }
                                }
                                // $scope.$apply();
                            }
                        }
                        break;
                    case 'purchaseFileType':
                        {
                            if (infoObj != null || $scope.purchaseFileArray != null) {
                                for (var i = 0; i < $scope.purchaseFileArray.length; i++) {
                                    if (infoObj.name == $scope.purchaseFileArray[i].name) {
                                        $scope.purchaseFileArray.splice(i, 1);
                                        $scope.showInfo.purchaseFile.splice(i, 1);
                                    }
                                }
                                // $scope.$apply();
                            }
                        }
                        break;
                    case 'contractFileType':
                        {
                            if (infoObj != null || $scope.contractFileArray != null) {
                                for (var i = 0; i < $scope.contractFileArray.length; i++) {
                                    if (infoObj.name == $scope.contractFileArray[i].name) {
                                        $scope.contractFileArray.splice(i, 1);
                                        $scope.showInfo.contractFile.splice(i, 1);
                                    }
                                }
                                // $scope.$apply();
                            }
                        }
                        break;
                    case 'checkFileType':
                        {
                            if (infoObj != null || $scope.checkFileArray != null) {
                                for (var i = 0; i < $scope.checkFileArray.length; i++) {
                                    if (infoObj.name == $scope.checkFileArray[i].name) {
                                        $scope.checkFileArray.splice(i, 1);
                                        $scope.showInfo.checkFile.splice(i, 1);
                                    }
                                }
                                // $scope.$apply();
                            }
                        }
                        break;
                    case 'remarkFileType':
                        {
                            if (infoObj != null || $scope.remarkFileArray != null) {
                                for (var i = 0; i < $scope.remarkFileArray.length; i++) {
                                    if (infoObj.name == $scope.remarkFileArray[i].name) {
                                        $scope.remarkFileArray.splice(i, 1);
                                        $scope.showInfo.remarkFile.splice(i, 1);
                                    }
                                }
                                // $scope.$apply();
                            }
                        }
                        break;
                }
            } else if ($rootScope.state.optType == 'editSysCheck') {
                switch (fileType) {
                    case 'fundsFileType':
                        {
                            if (infoObj != null || $scope.showInfo.fundsFile != null) {
                                // console.log("@@@@@@");
                                for (var i = 0; i < $scope.showInfo.fundsFile.length; i++) {
                                    if (infoObj.name == $scope.showInfo.fundsFile[i].name) {
                                        // console.log("#####");
                                        if ($scope.fundsFileArray.length > 0) {
                                            for (var m = 0; m < $scope.fundsFileArray.length; m++) {
                                                if (infoObj.name == $scope.fundsFileArray[m].name) {
                                                    $scope.fundsFileArray.splice(m, 1);
                                                }
                                            }
                                        }
                                        $scope.delFundsFile.push($scope.showInfo.fundsFile[i].value.replace(imgSer, ""));
                                        $scope.showInfo.fundsFile.splice(i, 1);
                                    }
                                }
                                // $scope.$apply();
                            }
                        }
                        break;
                    case 'purchaseFileType':
                        {
                            if (infoObj != null || $scope.showInfo.purchaseFile != null) {
                                for (var i = 0; i < $scope.showInfo.purchaseFile.length; i++) {
                                    if (infoObj.name == $scope.showInfo.purchaseFile[i].name) {
                                        if ($scope.purchaseFileArray.length > 0) {
                                            for (var m = 0; m < $scope.purchaseFileArray.length; m++) {
                                                if (infoObj.name == $scope.purchaseFileArray[m].name) {
                                                    $scope.purchaseFileArray.splice(m, 1);
                                                }
                                            }
                                        }
                                        // $scope.purchaseFileArray.splice(i, 1);
                                        $scope.delPurchaseFile.push($scope.showInfo.purchaseFile[i].value.replace(imgSer, ""));
                                        $scope.showInfo.purchaseFile.splice(i, 1);
                                    }
                                }
                                // $scope.$apply();
                            }
                        }
                        break;
                    case 'contractFileType':
                        {
                            if (infoObj != null || $scope.showInfo.contractFile != null) {
                                for (var i = 0; i < $scope.showInfo.contractFile.length; i++) {
                                    if (infoObj.name == $scope.showInfo.contractFile[i].name) {
                                        if ($scope.contractFileArray.length > 0) {
                                            for (var m = 0; m < $scope.contractFileArray.length; m++) {
                                                if (infoObj.name == $scope.contractFileArray[m].name) {
                                                    $scope.contractFileArray.splice(m, 1);
                                                }
                                            }
                                        }
                                        // $scope.contractFileArray.splice(i, 1);
                                        $scope.delContractFile.push($scope.showInfo.contractFile[i].value.replace(imgSer, ""));
                                        $scope.showInfo.contractFile.splice(i, 1);
                                    }
                                }
                                // $scope.$apply();
                            }
                        }
                        break;
                    case 'checkFileType':
                        {
                            if (infoObj != null || $scope.showInfo.checkFile != null) {
                                for (var i = 0; i < $scope.showInfo.checkFile.length; i++) {
                                    if (infoObj.name == $scope.showInfo.checkFile[i].name) {
                                        if ($scope.checkFileArray.length > 0) {
                                            for (var m = 0; m < $scope.checkFileArray.length; m++) {
                                                if (infoObj.name == $scope.checkFileArray[m].name) {
                                                    $scope.checkFileArray.splice(m, 1);
                                                }
                                            }
                                        }
                                        // $scope.checkFileArray.splice(i, 1);
                                        $scope.delCheckFile.push($scope.showInfo.checkFile[i].value.replace(imgSer, ""));
                                        $scope.showInfo.checkFile.splice(i, 1);
                                    }
                                }
                                // $scope.$apply();
                            }
                        }
                        break;
                    case 'remarkFileType':
                        {
                            if (infoObj != null || $scope.showInfo.remarkFile != null) {
                                for (var i = 0; i < $scope.showInfo.remarkFile.length; i++) {
                                    if (infoObj.name == $scope.showInfo.remarkFile[i].name) {
                                        if ($scope.remarkFileArray.length > 0) {
                                            for (var m = 0; m < $scope.remarkFileArray.length; m++) {
                                                if (infoObj.name == $scope.remarkFileArray[m].name) {
                                                    $scope.remarkFileArray.splice(m, 1);
                                                }
                                            }
                                        }
                                        // $scope.remarkFileArray.splice(i, 1);
                                        $scope.delRemarkFile.push($scope.showInfo.remarkFile[i].value.replace(imgSer, ""));
                                        $scope.showInfo.remarkFile.splice(i, 1);
                                    }
                                }
                                // $scope.$apply();
                            }
                        }
                        break;
                }
            }
        }


        $scope.size = 0;
        $scope.uploadFiles = function() {
            //文件总数清零
            //上传文件
            //debugger;
            if ($scope.fundsFileArray.length > 0) {
                $scope.size += 1;
            }
            if ($scope.purchaseFileArray.length > 0) {
                $scope.size += 1;
            }
            if ($scope.contractFileArray.length > 0) {
                $scope.size += 1;
            }
            if ($scope.checkFileArray.length > 0) {
                $scope.size += 1;
            }
            if ($scope.remarkFileArray.length > 0) {
                $scope.size += 1;
            }
            //文件上传
            //$scope.formModel.roadStatusImg = '';
            //$scope.formModel.contractFile = '';
            $scope.uploads = 0;
            if ($scope.fundsFileArray.length > 0) {
                $scope.fileUpload($scope.fundsFileArray, 'fundsFile');
            }
            if ($scope.purchaseFileArray.length > 0) {
                $scope.fileUpload($scope.purchaseFileArray, 'purchaseFile');
            }
            if ($scope.contractFileArray.length > 0) {
                $scope.fileUpload($scope.contractFileArray, 'contractFile');
            }
            if ($scope.checkFileArray.length > 0) {
                $scope.fileUpload($scope.checkFileArray, 'checkFile');
            }
            if ($scope.remarkFileArray.length > 0) {
                $scope.fileUpload($scope.remarkFileArray, 'remarkFile');
            }
            //only delete option
            if ($scope.size == 0 && $scope.delSize != 0) {
                // console.log('@@@@@@@');
                if ($scope.delFundsFile.length > 0) {
                    $scope.fundsFileData = $scope.jiontURL('', $scope.showInfo.fundsFile);
                }
                if ($scope.delPurchaseFile.length > 0) {
                    $scope.purchaseFileData = $scope.jiontURL('', $scope.showInfo.purchaseFile);
                }
                if ($scope.delContractFile.length > 0) {
                    $scope.contractFileData = $scope.jiontURL('', $scope.showInfo.contractFile);
                }
                if ($scope.delCheckFile.length > 0) {
                    $scope.checkFileData = $scope.jiontURL('', $scope.showInfo.checkFile);
                }
                if ($scope.delRemarkFile.length > 0) {
                    $scope.remarkFileData = $scope.jiontURL('', $scope.showInfo.remarkFile);
                }

                $scope.processForm();
            }
        }

        //$scope.size = 0;
        $scope.uploads = 0;
        $scope.fileUpload = function(files, type) {
            // console.log(files);
            Upload.upload({
                url: '/EmApl/fileUpload',
                data: {},
                file: files
            }).success(function(data) {
                //debugger;
                // console.log(data);
                $scope.uploads += 1;
                if (type == 'fundsFile') {
                    $scope.fundsFileData = data.fileUrl;
                    if ($scope.showInfo.fundsFile.length > 0) {
                        $scope.fundsFileData = $scope.jiontURL(data.fileUrl, $scope.showInfo.fundsFile);
                    }
                } else if (type == 'purchaseFile') {
                    //如果第一次的值得为isUpload
                    $scope.purchaseFileData = data.fileUrl;
                    if ($scope.showInfo.purchaseFile.length > 0) {
                        $scope.purchaseFileData = $scope.jiontURL(data.fileUrl, $scope.showInfo.purchaseFile);
                    }
                } else if (type == 'contractFile') {
                    $scope.contractFileData = data.fileUrl;
                    if ($scope.showInfo.contractFile.length > 0) {
                        $scope.contractFileData = $scope.jiontURL(data.fileUrl, $scope.showInfo.contractFile);
                    }
                } else if (type == 'checkFile') {
                    $scope.checkFileData = data.fileUrl;
                    if ($scope.showInfo.checkFile.length > 0) {
                        $scope.checkFileData = $scope.jiontURL(data.fileUrl, $scope.showInfo.checkFile);
                    }
                } else if (type == 'remarkFile') {
                    $scope.remarkFileData = data.fileUrl;
                    if ($scope.showInfo.remarkFile.length > 0) {
                        $scope.remarkFileData = $scope.jiontURL(data.fileUrl, $scope.showInfo.remarkFile);
                    }
                }
                if ($scope.uploads == $scope.size) {
                    $scope.processForm();
                }
            }).error(function(data, status, headers, config) {
                console.log('error status: ' + status);
            });

        }
        //jiont url function
        $scope.jiontURL = function(newData, oldData) {
            // debugger;
            var tempStr = newData;
            for (var i = 0; i < oldData.length; i++) {
                if (oldData[i].value != '#') {
                    var temp = oldData[i].value;
                    console.log(temp);
                    temp.replace(imgSer,"");
                    console.log("处理后" + temp);
                    if (tempStr == '') {
                        tempStr += temp;
                    } else {
                        tempStr += ',' + temp;
                    }
                }
            }
            return tempStr;

        }

        $scope.processForm = function() {
            // console.log($scope.remarkFileData);
            $scope.infoObj = {
                id: $scope.showInfo.id,
                systemApproveId: $scope.showInfo.systemApproveId,
                fundsFile: $scope.fundsFileData,
                purchaseFile: $scope.purchaseFileData,
                contractFile: $scope.contractFileData,
                checkFile: $scope.checkFileData,
                remarkFile: $scope.remarkFileData,
                processId: 'deviceApprove',
                status: $scope.showInfo.status
            };
            // console.log($scope.infoObj);
            if ($rootScope.state.optType == 'newSysCheck') {
                SysApproveCompleteService.addSysCheckFormList($scope.infoObj).then(
                    function success(data) {
                        Notifier.notifySuccess("添加成功！");
                    });
            } else if ($rootScope.state.optType == 'editSysCheck') {
                // console.log("EDIT");
                var editObj = {
                    id: $scope.showInfo.id,
                    systemApproveId: $scope.showInfo.systemApproveId,
                    fundsFile: $scope.fundsFileData,
                    purchaseFile: $scope.purchaseFileData,
                    contractFile: $scope.contractFileData,
                    checkFile: $scope.checkFileData,
                    remarkFile: $scope.remarkFileData,
                    processId: 'deviceApprove'
                }
                SysApproveCompleteService.editSysCheckFormList(editObj).then(
                    function success(data) {
                        Notifier.notifySuccess("修改成功！");
                        // console.log('！！！！！！！');
                    });
            }

            $state.go('sysApproveCompleteForm');
        }

        $scope.uploadType = 0;
        $scope.save = function() {
            $scope.uploadFiles();

        };
        $scope.cancel = function() {
            $state.go('sysApproveForm');
        }

        $scope.getApproveList = function() {

            SysApproveCompleteService.getApplicationFormList($scope.sysApproveId).then(
                function success(data) {
                    if (data) {
                        $scope.showInfo = data.data;
                        $scope.totalItems = data.total;
                    }
                });
        };

        //$scope.getApproveList();

    })

});
