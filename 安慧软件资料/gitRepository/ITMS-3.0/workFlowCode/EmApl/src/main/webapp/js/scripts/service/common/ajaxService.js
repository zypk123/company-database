define([ 'app' ], function(app) {
	app.service('ajaxService', function($http) {
		var me = this;
		me.ajaxPost = function(url, data, success) {
			$http({
				method : "POST",
				url : url,
				params : data
			}).success(function(data) {
				success(data);
			})
		}
		me.ajaxPostForm = function(url, data, success) {
        	$http({
			method  : 'POST',
			url     : url,
        	headers : {'Content-Type' : 'application/json'},
			data    :  data  // pass in data as strings
			})
			.success(function(data) {
				success(data);
			});
        }
	});
});
