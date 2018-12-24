define(['app', 'service/common/ajaxService'], function(app) {
    'use strict'

    app.factory('UserManageService', function($http, $q) {
        return {
            getUserList: function(queryObj) {
                // var url = "mockdata/userInfo";
                var url = "/EmApl/service/workFlow/sysUserService/queryUsersWithGroupinfo";
                var deferred = $q.defer();
                console.log(queryObj);
                $http.post(url, queryObj).then(
                    function success(respData) {
                        //console.log(respData);
                        deferred.resolve(respData.data.data);
                    },
                    function erro(reason) {
                        deferred.resolve(reason.data.ErrorCode);
                        deferred.reject(reason);
                    });
                return deferred.promise;
            },
            getOrgList: function(queryObj) {
                // var url = "mockdata/userInfo";
                console.log(queryObj);
                var url = "/EmApl/service/workFlow/sysGroupService/queryGroups";
                var deferred = $q.defer();
                $http.post(url, queryObj).then(
                    function success(respData) {
                        deferred.resolve(respData.data.data.data);
                    },
                    function erro(reason) {
                        deferred.resolve(reason.data.ErrorCode);
                        deferred.reject(reason);
                    });
                return deferred.promise;
            },
            selectMenu: function(menuObj) {
                // var url = "mockdata/userInfo";
                console.log(menuObj);
                var url = "/EmApl/service/workFlow/wfMenuService/addGroupMenuMapRecord";
                var deferred = $q.defer();
                $http.post(url, menuObj).then(
                    function success(respData) {
                        console.log(respData);
                        deferred.resolve(respData.data);
                    },
                    function erro(reason) {
                        deferred.resolve(reason.data.ErrorCode);
                        deferred.reject(reason);
                    });
                return deferred.promise;
            },
            getSelectNodeByOrgId: function(groupId) {
                var orgId = {
                    groupId: groupId
                };
                console.log(groupId);
                var url = "/EmApl/service/workFlow/wfMenuService/queryMenuListByGroupId";
                var deferred = $q.defer();
                $http.post(url, orgId).then(
                    function success(respData) {
                        console.log(respData);
                        deferred.resolve(respData.data.data.data);
                    },
                    function erro(reason) {
                        deferred.resolve(reason.data.ErrorCode);
                        deferred.reject(reason);
                    });
                return deferred.promise;
            },
            getSysNotice: function(queryObj) {
                var url = "/EmApl/service/workFlow/wfMessageService/queryMessagesByRecord";
                var deferred = $q.defer();
                $http.post(url, queryObj).then(
                    function success(respData) {
                        console.log(respData);
                        deferred.resolve(respData.data.data);
                    },
                    function erro(reason) {
                        deferred.resolve(reason.data.ErrorCode);
                        deferred.reject(reason);
                    });
                return deferred.promise;
            },
            addNoticeInfo: function(newData) {
                var url = "/EmApl/service/workFlow/wfMessageService/addMessage";
                var deferred = $q.defer();
                $http.post(url, newData).then(
                    function success(respData) {
                        console.log(respData);
                        deferred.resolve(respData.data.data);
                    },
                    function erro(reason) {
                        deferred.resolve(reason.data.ErrorCode);
                        deferred.reject(reason);
                    });
                return deferred.promise;
            },
            editNoticeInfo: function(newData) {
                var url = "/EmApl/service/workFlow/wfMessageService/updateMessage";
                var deferred = $q.defer();
                $http.post(url, newData).then(
                    function success(respData) {
                        console.log(respData);
                        deferred.resolve(respData.data.data);
                    },
                    function erro(reason) {
                        deferred.resolve(reason.data.ErrorCode);
                        deferred.reject(reason);
                    });
                return deferred.promise;
            },
            deleteNoticeInfo: function(noticeId) {
                var url = "/EmApl/service/workFlow/wfMessageService/deleteMessageById/" + noticeId;
                var deferred = $q.defer();
                $http.get(url).then(
                    function success(respData) {
                        console.log(respData);
                        deferred.resolve(respData.data.data);
                    },
                    function erro(reason) {
                        deferred.resolve(reason.data.ErrorCode);
                        deferred.reject(reason);
                    });
                return deferred.promise;
            },
            getUnSignForm: function(queryObj) {
                var url = "/EmApl/service/workFlow/commonBaseService/selectAllData";
                var deferred = $q.defer();
                $http.post(url, queryObj).then(
                    function success(respData) {
                        console.log(respData);
                        deferred.resolve(respData.data.data);
                    },
                    function erro(reason) {
                        deferred.resolve(reason.data.ErrorCode);
                        deferred.reject(reason);
                    });
                return deferred.promise;
            },
            getUnApproveForm: function(queryObj) {
                var url = "/EmApl/service/workFlow/commonBaseService/selectAllData";
                var deferred = $q.defer();
                $http.post(url, queryObj).then(
                    function success(respData) {
                        console.log(respData);
                        deferred.resolve(respData.data.data);
                    },
                    function erro(reason) {
                        deferred.resolve(reason.data.ErrorCode);
                        deferred.reject(reason);
                    });
                return deferred.promise;
            },
            getSysInfo: function(queryObj) {
                var url = "/EmApl/service/workFlow/commonBaseService/selectAllData";
                var deferred = $q.defer();
                $http.post(url, queryObj).then(
                    function success(respData) {
                        console.log(respData);
                        deferred.resolve(respData.data.data);
                    },
                    function erro(reason) {
                        deferred.resolve(reason.data.ErrorCode);
                        deferred.reject(reason);
                    });
                return deferred.promise;
            },


        }

    });
});
