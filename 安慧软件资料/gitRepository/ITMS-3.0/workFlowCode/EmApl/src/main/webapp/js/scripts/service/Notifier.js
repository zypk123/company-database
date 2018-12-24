define(['app', 'toastr'], function(app,toastr) {
    'use strict'

    app.factory('Notifier', function($http, $q) {
        toastr.options = {
            "closeButton": true,
            "positionClass": "toast-bottom-right",
            "timeOut": "3000"
        }
        return {
            notifyInfo: function(message) {
                toastr['info'](message);
            },
            notifyWarning: function(message) {
                toastr['warning'](message);
            },
            notifyError: function(message) {
                toastr['error'](message);
            },
            notifySuccess: function(message) {
                toastr['success'](message);
            }
        }

    });
});
