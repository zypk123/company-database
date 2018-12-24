define([ 'app', 'service/common/ajaxService' ], function(app) {
	app.service('deviceApproveService', function($http, ajaxService) {
		var me = this;
		me.postUrl = function(url, data, success) {
			ajaxService.ajaxPost(url, data, success);
		}
		me.ajaxPostForm = function(url, data, success) {
			ajaxService.ajaxPostForm(url, data, success);
		}
	});
});