define(['app', 'service/common/ajaxService'], function(app) {
    'use strict'

    app.factory('SysApproveService', function($http, $q) {
        return {
            getApplicationFormList: function(queryObj) {
                // var url = "mockdata/userInfo";
                console.log(queryObj);
                var url = "/EmApl/service/workFlow/sAservice/querySystemApproveData";
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
            addApplicationFormList: function(queryObj) {
                // var url = "mockdata/userInfo";
                console.log(queryObj);
                var url = "/EmApl/service/workFlow/sAservice/saveSAFrom";
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
            editApplicationFormList: function(queryObj) {
                // var url = "mockdata/userInfo";
                console.log(queryObj);
                var url = "/EmApl/service/workFlow/sAservice/updateSAFrom";
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
                var url = "/EmApl/service/workFlow/sAservice/submitSystemApprove?systemApproveId=" + queryObj.systemApproveId + "&processId=" + queryObj.processId;
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
            examineApplicationForm: function(queryObj) {
                var url = "/EmApl/service/workFlow/sAservice/querySystemApproveDataById?approveId=" + queryObj;
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
            cancelApplicationForm: function(queryObj) {
                var url = "/EmApl/service/workFlow/sAservice/deleteSystemApprove?approveId=" + queryObj;
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
             querySysHistoryApprove: function(queryObj) {
                console.log(queryObj);
                var url = "/EmApl/service/workFlow/sAservice/querySystemApproveHistoryData";
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
            getSysApproveDetail: function(queryObj) {
                var url = "/EmApl/service/workFlow/sAservice/querySystemApproveDataById?approveId=" + queryObj;
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


        }

    });
});
