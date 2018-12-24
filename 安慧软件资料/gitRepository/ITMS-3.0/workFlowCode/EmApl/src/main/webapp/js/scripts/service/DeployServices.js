define([ 'app', 'service/common/ajaxService' ], function(app) {
	app.service('deployService', function($http, ajaxService) {
		var me = this;
		me.postUrl = function(url, data, success) {
			ajaxService.ajaxPost(url, data, success);
		}

	});
});