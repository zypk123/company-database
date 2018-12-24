define(['app', 'service/common/ajaxService'], function(app) {
    'use strict'

    app.factory('ApplicationFromService', function($http, $q) {
        return {
            getApplicationFormList: function(queryObj) {
                // var url = "mockdata/userInfo";
                var url = "/EmApl/service/workFlow/dAservice/queryDeviceApproveData";
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
            getApproveFormStatus: function(approveId) {
                console.log(approveId);
                var url = "/EmApl/service/workFlow/dAservice/queryDeviceApproveDataById?approveId=" + approveId;
                var deferred = $q.defer();
                //console.log(queryObj);
                $http.get(url).then(
                    function success(respData) {
                        //console.log(respData);
                        deferred.resolve(respData.data);
                    },
                    function erro(reason) {
                        deferred.resolve(reason.data.ErrorCode);
                        deferred.reject(reason);
                    });
                return deferred.promise;
            },
            getHistoryApprove: function(queryObj) {
                var url = "/EmApl/service/workFlow/dAservice/queryDeviceApproveHistoryData";
                var deferred = $q.defer();
                //console.log(queryObj);
                $http.post(url,queryObj).then(
                    function success(respData) {
                        //console.log(respData);
                        deferred.resolve(respData.data);
                    },
                    function erro(reason) {
                        deferred.resolve(reason.data.ErrorCode);
                        deferred.reject(reason);
                    });
                return deferred.promise;
            },
            getHistoryApproveDetail: function(approveId) {
                console.log(approveId);
                var url = "/EmApl/service/workFlow/dAservice/queryDeviceApproveDataById?approveId=" + approveId;
                var deferred = $q.defer();
                //console.log(queryObj);
                $http.get(url).then(
                    function success(respData) {
                        //console.log(respData);
                        deferred.resolve(respData.data);
                    },
                    function erro(reason) {
                        deferred.resolve(reason.data.ErrorCode);
                        deferred.reject(reason);
                    });
                return deferred.promise;
            }

        }

    });
});
