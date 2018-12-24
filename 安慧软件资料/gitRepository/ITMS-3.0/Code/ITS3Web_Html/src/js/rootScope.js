'use strict';
var $rootScope = {
    /**
     * 数据缓存区
     * @type {Object}
     */
    $scopeData : {},
    /**
     * 参数区
     * @type {Object}
     */
    $params : {},
    

    /**
     * 缓存数据
     * @param  {数据名，键，值} 
     * @return {[type]}
     */
    $setData : function(name,key,value){
    	if(!$rootScope.$scopeData[name]){
    		$rootScope.$scopeData[name] = {};
    	}
    	$rootScope.$scopeData[name][key] = value;
    },
    /**
     * 获取缓存数据
     * @return {数据名，键}
     */
    $getData : function(name,key){
    	return $rootScope.$scopeData[name][key];
    },

    /**
     * 执行一个函数
     * @param  {Function} fn     [函数体] 
     * @param  {[type]}   param1 [参数1]
     * @param  {[type]}   param2 [参数2]
     * @param  {[type]}   param3 [参数3]
     * @return {[type]}          [函数返回值]
     */
    $excute : function(fn,param1,param2,param3){
    	if (fn !== undefined && fn !== null && typeof fn == "function") {
	        return fn(param1, param2, param3);
	    }
	    return null;
    },

    /**
     * 设置参数
     * @param {[type]} obj [description]
     */
    $setParam : function(key,value){
    	$rootScope.$params[key] = value;
    },

    /**
     * 获取参数
     * @param  {[type]} key 键
     * @return {[type]}     值
     */
    $getParam : function(key){
        //获得参数
        var param = $rootScope.$params[key];
        //获取后删除
        delete $rootScope.$params[key];
        return param;
    },
    

    //ajax提交
    $ajaxRequest : function(_options) {
        var options = {
            url: "",
            params: {},
            contextId: "",
            success: function (result) { },
            fail: function (errMsg) { }
        };
        $.extend(options, _options);
        $.ajax({
            url: options.url,
            type: "get",
            data: options.params,
            dataType: "json",
            beforeSend: function (request) {
                request.setRequestHeader("ContextID", $rootScope.ContextId);
                request.setRequestHeader("OrgCode", $rootScope.UserInfo.OrgCode);
            },
            success: function (retData) {
                $rootScope.$excute(options.success, retData);
            },
            error: function (err) {
                $rootScope.$excute(options.fail, err.responseText);
            }
        });
    },
    //form提交
    $formSubmit : function(_options) {
        var options = {
            formId: "formId",
            ContextId: "",
            actionUrl: "",
            success: function (result) { },
            onSubmit: function (frm) { return true; },
            fail: function (errMsg) { }
        };
        $.extend(options, _options);
        $(options.formId).form('submit', {
            url: options.actionUrl,
            onSubmit: function (param) {
                param.ContextId = $rootScope.ContextId;
            },
            success: function (data) {
                data = JSON.parse(data);
                if (data.Error !== '') {
                    if (data.Error == 'invalid request') {
                        // 到登录界面
                    } else {
                        $rootScope.$excute(options.success,data.Error);
                    }
                } else {
                    $rootScope.$excute(options.success, data.Result);
                }
            }
        });
    },

    /**
     * 加载页面
     * @param  {[type]} "main.html" [description]
     * @return {[type]}             [description]
     */
    $gotoPage : function(page){
        $("#topPanel").panel("refresh",page);
        //清空缓存
        $rootScope.$destroy();
        //标记当前页面性质为首页，用来给框架区分当前页面是否为登录页或者首页
        $rootScope.$setParam("$isHome",true);
    },

    /**
     * 清空缓存中的原色，销毁缓存
     * @return {[type]} [description]
     */
    $destroy : function(){
        $rootScope.$scopeData = {};
        $rootScope.$params = {};
        for(var index in $rootScope.$dialog){
            $rootScope.$dialog[index].dialog("destroy");
        }
        $rootScope.$dialog = {};
    },

    /**
     * 根据品牌编码获得品牌名称
     * @param  {[type]} code 编码
     * @return {[type]}      名称
     */
    $getBrandNameByCode : function(code){
        for(var index in brandData){
            var item = brandData[index];
            if(item.id == code){
                return item.name;
            }
        }
    }
};
//默认是主页
$rootScope.$setParam("$isHome",true);