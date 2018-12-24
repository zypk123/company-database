define(['app', 'service/userService/sysApproveServices', 'service/TreeServices', 'service/Notifier'], function(app) {
    'use strict'

    app.controller('NewSysApproveController', function($scope, $rootScope, $http, SysApproveService, $state, $stateParams, $uibModal, Notifier, Upload) {
            //approveImg arry
            $scope.showInfo = {};
            $scope.approveImgArray = [];
            $scope.showInfo.approveImg = [];
            $scope.departmentsImgArray = [];
            $scope.showInfo.departmentsImg = [];
            $scope.solutionFileArray = [];
            $scope.showInfo.solutionFile = [];
            $scope.plansFileArray = [];
            $scope.showInfo.plansFile = [];
            $scope.expertOpinionImgArray = [];
            $scope.showInfo.expertOpinionImg = [];

            $scope.oldApproveImg = [];
            $scope.oldDepartmentsImg = [];
            $scope.oldSolutionFile = [];
            $scope.oldPlansFile = [];
            $scope.oldExpertOpinionImg = [];

            $scope.delApproveImg = [];
            $scope.delDepartmentsImg = [];
            $scope.delSolutionFile = [];
            $scope.delPlansFile = [];
            $scope.delExpertOpinionImg = [];
            $scope.showTitle = true;

            if ($rootScope.state == null || $rootScope.state == 'undefined' || $rootScope.state == '') {
                $rootScope.state = {};
                $state.go('sysApproveForm');

            } else if ($rootScope.state.optType == 'edit') {
                var sysApproveId = $rootScope.state.sysApproveId;
                var imgSer = "http://";
                if (imgSer.indexOf('"') > -1) {
                    imgSer = imgSer.replace('"', '');
                    if (imgSer.indexOf('"') > -1) {
                        imgSer = imgSer.replace('"', '');
                    }
                }
                SysApproveService.examineApplicationForm(sysApproveId).then(
                    function success(data) {
                        //$scope.showInfo = data.data;
                        var tempInfo = data.data;
                        // console.log(tempInfo.approveImg);
                        $scope.showInfo = tempInfo;

                        if (tempInfo.approveImg != null) {
                            var temp = $scope.filterFunc(tempInfo.approveImg);
                            $scope.showInfo.approveImg = temp;
                            $scope.oldApproveImg = temp;
                        }else{
                            $scope.showInfo.approveImg = [];
                        }
                        if (tempInfo.departmentsImg) {
                            var temp = $scope.filterFunc(tempInfo.departmentsImg);
                            $scope.showInfo.departmentsImg = temp;
                            $scope.oldDepartmentsImg = temp;
                        }else{
                            $scope.showInfo.departmentsImg = [];
                        }
                        if (tempInfo.solutionFile) {
                            var temp = $scope.filterFunc(tempInfo.solutionFile);
                            $scope.showInfo.solutionFile = temp;
                            $scope.oldSolutionFile = temp;
                        }else{
                            $scope.showInfo.solutionFile = [];
                        }
                        if (tempInfo.plansFile) {
                            var temp = $scope.filterFunc(tempInfo.plansFile);
                            $scope.showInfo.plansFile = temp;
                            $scope.oldPlansFile = temp;
                        }else{
                            $scope.showInfo.plansFile = [];
                        }
                        if (tempInfo.expertOpinionImg) {
                            var temp = $scope.filterFunc(tempInfo.expertOpinionImg);
                            $scope.showInfo.expertOpinionImg = temp;
                            $scope.oldExpertOpinionImg = temp;
                            console.log($scope.showInfo.expertOpinionImg);
                        }else{
                            $scope.showInfo.expertOpinionImg = [];
                        }

                    });

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
            console.log($scope.groupId);
            $scope.searchUnit = function() {
                var modalInstance = $uibModal.open({
                    controller: 'UnitListController',
                    templateUrl: 'html/sysApproveManage/userUnit.html',
                    size: 'sm',
                    // backdrop: 'static',
                    // keyboard: false,
                    resolve: {
                        groupId: function() {
                            return $scope.groupId;
                        }
                    }
                });
                modalInstance.result.then(function(unitInfo) {
                    //console.log(selectNodeObj);
                    $scope.showInfo.unit = unitInfo.id;
                    $scope.showInfo.unitName = unitInfo.name;
                    console.log($scope.showInfo.unit);
                });
            }

            $scope.uploadAccessories = function(fileobj, fileType) {
                var files = fileobj.files;
                // debugger;
                switch (fileType) {
                    case 'approveImgType':
                        {
                            // $scope.approveImg = "";
                            $scope.approveImgArray.splice(0, $scope.approveImgArray.length);
                            // $scope.showInfo.approveImg.splice(0, $scope.showInfo.approveImg.length);
                            for (var i = 0; i < files.length; i++) {
                                $scope.approveImgArray.push(files[i]);
                                var temp = {};
                                temp.name = files[i].name;
                                temp.value = "#";
                                $scope.showInfo.approveImg.push(temp);
                                console.log($scope.showInfo.approveImg);
                            }
                            // if ($scope.oldApproveImg.length > 0) {
                            //     for (var i = 0; i < $scope.oldApproveImg.length; i++) {
                            //         $scope.showInfo.approveImg.push($scope.oldApproveImg[i]);
                            //     }
                            // }
                            // LocalStorage.add('approveImgeArr', approveImgArray);
                            $scope.$apply();
                        }
                        break;
                    case 'departmentsImgType':
                        {
                            // $scope.departmentsImg = "";
                            $scope.departmentsImgArray.splice(0, $scope.departmentsImgArray.length);
                            for (var i = 0; i < files.length; i++) {
                                $scope.departmentsImgArray.push(files[i]);
                                var temp = {};
                                temp.name = files[i].name;
                                temp.value = "#";
                                $scope.showInfo.departmentsImg.push(temp);
                            }
                            // if ($scope.oldDepartmentsImg.length > 0) {
                            //     for (var i = 0; i < $scope.oldDepartmentsImg.length; i++) {
                            //         $scope.showInfo.departmentsImg.push($scope.oldDepartmentsImg[i]);
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
                    case 'solutionFileType':
                        {
                            // $scope.solutionFile = "";
                            $scope.solutionFileArray.splice(0, $scope.solutionFileArray.length);
                            // if (files.length > 5) {
                            //     Notifier.notifyWarning("请示文件不能超出5个，请重新选择！");
                            //     return;
                            // }
                            for (var i = 0; i < files.length; i++) {
                                $scope.solutionFileArray.push(files[i]);
                                var temp = {};
                                temp.name = files[i].name;
                                temp.value = "#";
                                $scope.showInfo.solutionFile.push(temp);
                            }
                            // if ($scope.oldSolutionFile.length > 0) {
                            //     for (var i = 0; i < $scope.oldSolutionFile.length; i++) {
                            //         $scope.showInfo.solutionFile.push($scope.oldSolutionFile[i]);
                            //     }
                            // }
                            // LocalStorage.add('approveImgeArr', solutionFileArray);
                            $scope.$apply();
                        }
                        break;
                    case 'plansFileType':
                        {
                            // $scope.plansFile = "";
                            $scope.plansFileArray.splice(0, $scope.plansFileArray.length);
                            if (files.length > 5) {
                                Notifier.notifyWarning("请示文件不能超出5个，请重新选择！");
                                return;
                            }
                            for (var i = 0; i < files.length; i++) {
                                $scope.plansFileArray.push(files[i]);
                                var temp = {};
                                temp.name = files[i].name;
                                temp.value = "#";
                                $scope.showInfo.plansFile.push(temp);
                            }
                            // if ($scope.oldPlansFile.length > 0) {
                            //     for (var i = 0; i < $scope.oldPlansFile.length; i++) {
                            //         $scope.showInfo.plansFile.push($scope.oldPlansFile[i]);
                            //     }
                            // }
                            // LocalStorage.add('approveImgeArr', plansFileArray);
                            $scope.$apply();
                        }
                        break;
                    case 'expertOpinionImgType':
                        {
                            // $scope.expertOpinionImg = "";
                            $scope.expertOpinionImgArray.splice(0, $scope.expertOpinionImgArray.length);
                            if (files.length > 5) {
                                Notifier.notifyWarning("请示文件不能超出5个，请重新选择！");
                                return;
                            }
                            for (var i = 0; i < files.length; i++) {
                                $scope.expertOpinionImgArray.push(files[i]);
                                var temp = {};
                                temp.name = files[i].name;
                                temp.value = "#";
                                $scope.showInfo.expertOpinionImg.push(temp);
                            }
                            // if ($scope.oldExpertOpinionImg.length > 0) {
                            //     for (var i = 0; i < $scope.oldExpertOpinionImg.length; i++) {
                            //         $scope.showInfo.expertOpinionImg.push($scope.oldExpertOpinionImg[i]);
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
                $scope.delSize = 1;
                console.log("deleteAccessories");
                if ($rootScope.state.optType == 'add') {
                    switch (fileType) {
                        case 'approveImgType':
                            {
                                if (infoObj != null || $scope.approveImgArray != null) {
                                    // console.log("@@@@@@");
                                    for (var i = 0; i < $scope.approveImgArray.length; i++) {
                                        if (infoObj.name == $scope.approveImgArray[i].name) {
                                            // console.log("#####");
                                            $scope.approveImgArray.splice(i, 1);
                                            $scope.showInfo.approveImg.splice(i, 1);
                                        }
                                    }
                                    // $scope.$apply();
                                }
                            }
                            break;
                        case 'departmentsImgType':
                            {
                                if (infoObj != null || $scope.departmentsImgArray != null) {
                                    for (var i = 0; i < $scope.departmentsImgArray.length; i++) {
                                        if (infoObj.name == $scope.departmentsImgArray[i].name) {
                                            $scope.departmentsImgArray.splice(i, 1);
                                            $scope.showInfo.departmentsImg.splice(i, 1);
                                        }
                                    }
                                    // $scope.$apply();
                                }
                            }
                            break;
                        case 'solutionFileType':
                            {
                                if (infoObj != null || $scope.solutionFileArray != null) {
                                    for (var i = 0; i < $scope.solutionFileArray.length; i++) {
                                        if (infoObj.name == $scope.solutionFileArray[i].name) {
                                            $scope.solutionFileArray.splice(i, 1);
                                            $scope.showInfo.solutionFile.splice(i, 1);
                                        }
                                    }
                                    // $scope.$apply();
                                }
                            }
                            break;
                        case 'plansFileType':
                            {
                                if (infoObj != null || $scope.plansFileArray != null) {
                                    for (var i = 0; i < $scope.plansFileArray.length; i++) {
                                        if (infoObj.name == $scope.plansFileArray[i].name) {
                                            $scope.plansFileArray.splice(i, 1);
                                            $scope.showInfo.plansFile.splice(i, 1);
                                        }
                                    }
                                    // $scope.$apply();
                                }
                            }
                            break;
                        case 'expertOpinionImgType':
                            {
                                if (infoObj != null || $scope.expertOpinionImgArray != null) {
                                    for (var i = 0; i < $scope.expertOpinionImgArray.length; i++) {
                                        if (infoObj.name == $scope.expertOpinionImgArray[i].name) {
                                            $scope.expertOpinionImgArray.splice(i, 1);
                                            $scope.showInfo.expertOpinionImg.splice(i, 1);
                                        }
                                    }
                                    // $scope.$apply();
                                }
                            }
                            break;
                    }
                } else if ($rootScope.state.optType == 'edit') {
                    switch (fileType) {
                        case 'approveImgType':
                            {
                                if (infoObj != null || $scope.showInfo.approveImg != null) {
                                    // console.log("@@@@@@");
                                    for (var i = 0; i < $scope.showInfo.approveImg.length; i++) {
                                        if (infoObj.name == $scope.showInfo.approveImg[i].name) {
                                            // console.log("#####");
                                            if ($scope.approveImgArray.length > 0) {
                                                for (var m = 0; m < $scope.approveImgArray.length; m++) {
                                                    if (infoObj.name == $scope.approveImgArray[m].name) {
                                                        $scope.approveImgArray.splice(m, 1);
                                                    }
                                                }
                                            }
                                            $scope.delApproveImg.push($scope.showInfo.approveImg[i].value.replace(imgSer, ""));
                                            $scope.showInfo.approveImg.splice(i, 1);
                                            console.log($scope.showInfo.approveImg);
                                        }
                                    }
                                    // $scope.$apply();
                                }
                            }
                            break;
                        case 'departmentsImgType':
                            {
                                if (infoObj != null || $scope.showInfo.departmentsImg != null) {
                                    for (var i = 0; i < $scope.showInfo.departmentsImg.length; i++) {
                                        if (infoObj.name == $scope.showInfo.departmentsImg[i].name) {
                                            if ($scope.departmentsImgArray.length > 0) {
                                                for (var m = 0; m < $scope.departmentsImgArray.length; m++) {
                                                    if (infoObj.name == $scope.departmentsImgArray[m].name) {
                                                        $scope.departmentsImgArray.splice(m, 1);
                                                    }
                                                }
                                            }
                                            // $scope.departmentsImgArray.splice(i, 1);
                                            $scope.delDepartmentsImg.push($scope.showInfo.departmentsImg[i].value.replace(imgSer, ""));
                                            $scope.showInfo.departmentsImg.splice(i, 1);
                                        }
                                    }
                                    // $scope.$apply();
                                }
                            }
                            break;
                        case 'solutionFileType':
                            {
                                if (infoObj != null || $scope.showInfo.solutionFile != null) {
                                    for (var i = 0; i < $scope.showInfo.solutionFile.length; i++) {
                                        if (infoObj.name == $scope.showInfo.solutionFile[i].name) {
                                            if ($scope.solutionFileArray.length > 0) {
                                                for (var m = 0; m < $scope.solutionFileArray.length; m++) {
                                                    if (infoObj.name == $scope.solutionFileArray[m].name) {
                                                        $scope.solutionFileArray.splice(m, 1);
                                                    }
                                                }
                                            }
                                            // $scope.solutionFileArray.splice(i, 1);
                                            $scope.delSolutionFile.push($scope.showInfo.solutionFile[i].value.replace(imgSer, ""));
                                            $scope.showInfo.solutionFile.splice(i, 1);
                                        }
                                    }
                                    // $scope.$apply();
                                }
                            }
                            break;
                        case 'plansFileType':
                            {
                                if (infoObj != null || $scope.showInfo.plansFile != null) {
                                    for (var i = 0; i < $scope.showInfo.plansFile.length; i++) {
                                        if (infoObj.name == $scope.showInfo.plansFile[i].name) {
                                            if ($scope.plansFileArray.length > 0) {
                                                for (var m = 0; m < $scope.plansFileArray.length; m++) {
                                                    if (infoObj.name == $scope.plansFileArray[m].name) {
                                                        $scope.plansFileArray.splice(m, 1);
                                                    }
                                                }
                                            }
                                            // $scope.plansFileArray.splice(i, 1);
                                            $scope.delPlansFile.push($scope.showInfo.plansFile[i].value.replace(imgSer, ""));
                                            $scope.showInfo.plansFile.splice(i, 1);
                                        }
                                    }
                                    // $scope.$apply();
                                }
                            }
                            break;
                        case 'expertOpinionImgType':
                            {
                                if (infoObj != null || $scope.showInfo.expertOpinionImg != null) {
                                    for (var i = 0; i < $scope.showInfo.expertOpinionImg.length; i++) {
                                        if (infoObj.name == $scope.showInfo.expertOpinionImg[i].name) {
                                            if ($scope.expertOpinionImgArray.length > 0) {
                                                for (var m = 0; m < $scope.expertOpinionImgArray.length; m++) {
                                                    if (infoObj.name == $scope.expertOpinionImgArray[m].name) {
                                                        $scope.expertOpinionImgArray.splice(m, 1);
                                                    }
                                                }
                                            }
                                            // $scope.expertOpinionImgArray.splice(i, 1);
                                            $scope.delExpertOpinionImg.push($scope.showInfo.expertOpinionImg[i].value.replace(imgSer, ""));
                                            $scope.showInfo.expertOpinionImg.splice(i, 1);
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
                // console.log('#########')
                    //文件总数清零
                    //上传文件
                    //debugger;
                if ($scope.approveImgArray.length > 0) {
                    $scope.size += 1;
                }
                if ($scope.departmentsImgArray.length > 0) {
                    $scope.size += 1;
                }
                if ($scope.solutionFileArray.length > 0) {
                    $scope.size += 1;
                }
                if ($scope.plansFileArray.length > 0) {
                    $scope.size += 1;
                }
                if ($scope.expertOpinionImgArray.length > 0) {
                    $scope.size += 1;
                }
                //文件上传
                //$scope.formModel.roadStatusImg = '';
                //$scope.formModel.solutionFile = '';
                $scope.uploads = 0;
                if ($scope.approveImgArray.length > 0) {
                    $scope.fileUpload($scope.approveImgArray, 'approveImg');
                }
                if ($scope.departmentsImgArray.length > 0) {
                    $scope.fileUpload($scope.departmentsImgArray, 'departmentsImg');
                }
                if ($scope.solutionFileArray.length > 0) {
                    $scope.fileUpload($scope.solutionFileArray, 'solutionFile');
                }
                if ($scope.plansFileArray.length > 0) {
                    $scope.fileUpload($scope.plansFileArray, 'plansFile');
                }
                if ($scope.expertOpinionImgArray.length > 0) {
                    $scope.fileUpload($scope.expertOpinionImgArray, 'expertOpinionImg');
                }
                //only delete option
                if ($scope.size == 0 && $scope.delSize != 0) {
                    // console.log('@@@@@@@');
                    if ($scope.delApproveImg.length > 0) {
                        $scope.approveImgData = $scope.jiontURL('', $scope.showInfo.approveImg);
                    }
                    if ($scope.delDepartmentsImg.length > 0) {
                        $scope.departmentsImgData = $scope.jiontURL('', $scope.showInfo.departmentsImg);
                    }
                    if ($scope.delSolutionFile.length > 0) {
                        $scope.solutionFileData = $scope.jiontURL('', $scope.showInfo.solutionFile);
                    }
                    if ($scope.delPlansFile.length > 0) {
                        $scope.plansFileData = $scope.jiontURL('', $scope.showInfo.plansFile);
                    }
                    if ($scope.delExpertOpinionImg.length > 0) {
                        $scope.expertOpinionImgData = $scope.jiontURL('', $scope.showInfo.expertOpinionImg);
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
                    if (type == 'approveImg') {
                        $scope.approveImgData = data.fileUrl;
                        if ($scope.showInfo.approveImg.length > 0) {
                            $scope.approveImgData = $scope.jiontURL(data.fileUrl, $scope.showInfo.approveImg);
                        }
                    } else if (type == 'departmentsImg') {
                        //如果第一次的值得为isUpload
                        $scope.departmentsImgData = data.fileUrl;
                        if ($scope.showInfo.departmentsImg.length > 0) {
                            $scope.departmentsImgData = $scope.jiontURL(data.fileUrl, $scope.showInfo.departmentsImg);
                        }
                    } else if (type == 'solutionFile') {
                        $scope.solutionFileData = data.fileUrl;
                        if ($scope.showInfo.solutionFile.length > 0) {
                            $scope.solutionFileData = $scope.jiontURL(data.fileUrl, $scope.showInfo.solutionFile);
                        }
                    } else if (type == 'plansFile') {
                        $scope.plansFileData = data.fileUrl;
                        if ($scope.showInfo.plansFile.length > 0) {
                            $scope.plansFileData = $scope.jiontURL(data.fileUrl, $scope.showInfo.plansFile);
                        }
                    } else if (type == 'expertOpinionImg') {
                        $scope.expertOpinionImgData = data.fileUrl;
                        if ($scope.showInfo.expertOpinionImg.length > 0) {
                            $scope.expertOpinionImgData = $scope.jiontURL(data.fileUrl, $scope.showInfo.expertOpinionImg);
                        }
                    }
                    if ($scope.uploads == $scope.size) {
                        $scope.processForm();
                    }
                }).error(function(data, status, headers, config) {
                    console.log('error status: ' + status);
                });

            }

            $scope.jiontURL = function(newData, oldData) {
                var tempStr = newData;
                console.log(imgSer);
                for (var i = 0; i < oldData.length; i++) {
                    if (oldData[i].value != '#') {
                        var temp = oldData[i].value;
                        temp.replace(imgSer, "");
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
                // console.log($scope.expertOpinionImgData);
                $scope.infoObj = {
                    unit: $scope.showInfo.unit,
                    unitName: $scope.showInfo.unitName,
                    systemName: $scope.showInfo.systemName,
                    userDepartments: $scope.showInfo.userDepartments,
                    systemManageer: $scope.showInfo.systemManageer,
                    phone: $scope.showInfo.phone,
                    sysytemFunction: $scope.showInfo.sysytemFunction,
                    approveImg: $scope.approveImgData,
                    departmentsImg: $scope.departmentsImgData,
                    solutionFile: $scope.solutionFileData,
                    plansFile: $scope.plansFileData,
                    expertOpinionImg: $scope.expertOpinionImgData,
                    processId: 'deviceApprove'
                };
                // console.log($scope.infoObj);
                if ($rootScope.state.optType == 'add') {
                    SysApproveService.addApplicationFormList($scope.infoObj).then(
                        function success(data) {
                            Notifier.notifySuccess("添加成功！");
                            // console.log('@@@@@');

                        });
                } else if ($rootScope.state.optType == 'edit') {
                    // console.log("EDIT");
                    var editObj = {
                        systemApproveId: $scope.showInfo.systemApproveId,
                        unit: $scope.showInfo.unit,
                        unitName: $scope.showInfo.unitName,
                        systemName: $scope.showInfo.systemName,
                        userDepartments: $scope.showInfo.userDepartments,
                        systemManageer: $scope.showInfo.systemManageer,
                        phone: $scope.showInfo.phone,
                        sysytemFunction: $scope.showInfo.sysytemFunction,
                        approveImg: $scope.approveImgData,
                        departmentsImg: $scope.departmentsImgData,
                        solutionFile: $scope.solutionFileData,
                        plansFile: $scope.plansFileData,
                        expertOpinionImg: $scope.expertOpinionImgData,
                        processId: 'deviceApprove'
                    }
                    SysApproveService.editApplicationFormList(editObj).then(
                        function success(data) {
                            Notifier.notifySuccess("修改成功！");
                            // console.log('！！！！！！！');

                        });
                }

                $state.go('sysApproveForm');
            }

            $scope.uploadType = 0;
            $scope.save = function() {
                // if($scope.approveImg.length > 0){
                //     for(var i = 0; i < $scope.approveImg.length; i++){
                //         $scope.approveImg =
                //     }
                // }
                // console.log("SAVE");
                $scope.uploadFiles();

            };
            $scope.cancel = function() {
                $state.go('sysApproveForm');
            }
        })
        .controller('UnitListController', function($scope, treeService, $uibModalInstance, groupId) {
            //$scope.groupId get from app.js file
            $scope.userGroupId = groupId;
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
                $scope.unit = treeNode;
                $uibModalInstance.close($scope.unit);
                // $scope.getOrgList();
            });

            $scope.cancel = function() {
                $uibModalInstance.dismiss('cancel');
            }

        })
        .controller('DeleteFileController', function($scope, treeService, $uibModalInstance, arrayType, fileType) {
            //$scope.groupId get from app.js file
            $scope.showInfo = arrayType;
            // console.log($scope.showInfo);

            $scope.deleteFile = function(infoObj) {
                if (infoObj.lastModified) {
                    for (var i = 0; i < $scope.showInfo.length; i++) {
                        if (infoObj.lastModified == $scope.showInfo[i].lastModified) {
                            $scope.showInfo.splice(i, 1);
                        }
                    }
                    // $scope.$apply();
                }
            }
            var deleteObj = {
                data: $scope.showInfo,
                fileType: fileType
            }
            $scope.save = function() {
                $uibModalInstance.close(deleteObj);
            }
            $scope.cancel = function() {
                $uibModalInstance.dismiss('cancel');
            }

        });
});
