define(['app', 'service/common/ajaxService'], function(app) {
    'use strict'

    app.factory('SysApproveCompleteService', function($http, $q) {
        return {
            getSysCheckFormList: function(queryObj) {
                // var url = "mockdata/userInfo";
                console.log(queryObj);
                var url = "/EmApl/service/workFlow/sACservice/querySysApproveCompleteData";
                var deferred = $q.defer();
                //console.log(queryObj);
                $http.post(url, queryObj).then(
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
            addSysCheckFormList: function(queryObj) {
                // var url = "mockdata/userInfo";
                console.log(queryObj);
                var url = "/EmApl/service/workFlow/sACservice/saveSysApproveComplete";
                var deferred = $q.defer();
                //console.log(queryObj);
                $http.post(url, queryObj).then(
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
            editSysCheckFormList: function(queryObj) {
                // var url = "mockdata/userInfo";
                console.log(queryObj);
                var url = "/EmApl/service/workFlow/sACservice/updateSysApproveComplete";
                var deferred = $q.defer();
                //console.log(queryObj);
                $http.post(url, queryObj).then(
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
            subApplicationForm: function(queryObj) {
                var url = "/EmApl/service/workFlow/sACservice/submitSysApproveComplete?approveId=" + queryObj.approveId + "&processId=" + queryObj.processId + "&status=" + queryObj.status;
                var deferred = $q.defer();
                // console.log(queryObj);
                $http.get(url).then(
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
            examineApplicationForm: function(queryObj) {
                var url = "/EmApl/service/workFlow/sACservice/querySysApproveCompleteDataById?approveId=" + queryObj;
                var deferred = $q.defer();
                // console.log(queryObj);
                $http.get(url).then(
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
            cancelApplicationForm: function(queryObj) {
                var url = "/EmApl/service/workFlow/sACservice/deleteSystemApprove?approveId=" + queryObj;
                var deferred = $q.defer();
                //console.log(queryObj);
                $http.get(url).then(
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
            subExamineApprove: function(queryObj) {
                console.log(queryObj);
                var url = "/EmApl/service/workFlow/aCservice/processChkRecord";
                var deferred = $q.defer();
                // console.log(queryObj);
                $http.post(url, queryObj).then(
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
             querySysHistoryApprove: function(queryObj) {
                console.log(queryObj);
                var url = "/EmApl/service/workFlow/sAservice/querySystemApproveHistoryData";
                var deferred = $q.defer();
                // console.log(queryObj);
                $http.post(url, queryObj).then(
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
            getSysApproveDetail: function(queryObj) {
                var url = "/EmApl/service/workFlow/sAservice/querySystemApproveDataById?approveId=" + queryObj;
                var deferred = $q.defer();
                // console.log(queryObj);
                $http.get(url).then(
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
        }
    });
});
