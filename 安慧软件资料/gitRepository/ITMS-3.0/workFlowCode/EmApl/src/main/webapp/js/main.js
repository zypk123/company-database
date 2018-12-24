require.config({
	//配置总路径
	baseUrl : "js/scripts",

	paths : {
		// 其他模块会依赖他
		'jquery12' : '../lib/jquery-1.12.3.min',
		'ui.route':'../lib/angular/angular-ui-router',
		'angular' : '../lib/angular/angular',
		'ngAnimate' : '../lib/angular/angular-animate',
		'angular-route' : '../lib/angular/angular-route',
		'angularAMD' : '../lib/angular/angularAMD',
		'ngCookies' : '../lib/angular/angular-cookies',
		'angularTreeview' : '../lib/treeview/angular.treeview',
		'ngLocale' : '../lib/angular/i18n/angular-locale_zh',
		'css' : '../lib/requirejs/css.min',
		'zTree' : '../lib/jquery.ztree.all',
		'ngload':'../lib/upload/ng-file-upload',
		'ui-bootstrap':'../lib/angular-plugins/angular-ui-bootstrap/ui-bootstrap-tpls-1.2.5.min',
		'angular-sanitize':'../lib/angular/angular-sanitize',
		'ngFileUpload':'../lib/upload/ng-file-upload',
		'toastr':'../lib/toastr/toastr'
	},

	shim : {
		// 表明该模块依赖angular
		'angular': [ 'jquery12'],
		'angularAMD' : [ 'angular'],
		'angular-route' : [ 'angular'],
		'ui.route':['angular'],
		'ui-bootstrap' : [ 'angular'],
		'ngLocale' : [ 'angular'],
		'ngload' : [ 'angular'],
		'zTree' : [ 'jquery12'],
		'angularTreeview' : [ 'angular'],
		'ngCookies' : [ 'angular'],
        'angular-sanitize' : [ 'angular' ],
        'ngFileUpload':[ 'angular' ],
        	'toastr':[ 'jquery12' ]

	},
	urlArgs : "v=" + new Date().getTime(),
	// 启动程序 js/scripts/app.js
	deps : [ 'app' ]
});


