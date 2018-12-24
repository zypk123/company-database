define(['app', 'service/userService/userManageServices'], function(app) {
    'use strict'

    app.controller('HomePageController', function($rootScope, $scope, $state, UserManageService) {
        //load org infomation
        console.log("homepage");
        var queryObj = {
            startPage: 1,
            pageSize: 5
        };
        var unAproveObj = {
            groupId: $scope.groupId,
            pageInfo: '0',
            serviceMethod: 'selectTaskByGroup'
        };
        var unSignObj = {
            user: $scope.loginName,
            pageInfo: '0',
            serviceMethod: 'selectTaskByUser'
        };
        var sysInfoObj = {
            user: $scope.loginName,
            pageInfo: '0',
            serviceMethod: 'selectNoticeByUser'
        };
        $scope.getNotice = function() {
            //load sysNotice
            UserManageService.getSysNotice(queryObj).then(
                function success(data) {
                    $scope.showInfo = data.data;
                    console.log($scope.showInfo);
                });
            //load unApprove form
            UserManageService.getUnApproveForm(unAproveObj).then(
                function success(data) {
                    $scope.unAproveInfo = data;
                });
            //load unsign form
            UserManageService.getUnSignForm(unSignObj).then(
                function success(data) {
                    $scope.unSingInfo = data;
                });
            //load sysInfo
            UserManageService.getSysInfo(sysInfoObj).then(
                function success(data) {
                    $scope.sysInfo = data;
                });
        }

        $scope.getNotice();

        $scope.editNotice = function() {
            //console.log("@@@@@");
            $state.go("sysNotice");
        };

        $scope.goApprovePage = function() {
            $state.go("signApprove");
        };
        $scope.goSignPage = function() {
            $state.go("checkApprove");
        }



    })

});
