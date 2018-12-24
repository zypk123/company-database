'use strict';
var $rootScope = {
    /**
     * 双工通讯对象 
     */
    $webSocket : null,
    /**
     * 换粗系统代码
     */
    $code : {},
    
    /**
     * 缓存机构信息 
     */
    $org : null,
    
    /**
     * 缓存道路信息 
     */
    $road: null,
    
    /**
     * 缓存行政区划信息 
     */
    $district : null,

    /**
     * rest接口根路径
     */
    $restRoot : "../../service/",
    /**
     * servlet接口根路径 
     */
    $servletRoot : "../../",
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
     * 缓存号牌前缀信息 
     */
    $platePrefix : null,

    /**
     * 缓存数据
     * @param  {数据名，键，值}
     * @return {[type]}
     */
    $setData : function(name, key, value) {
        if (!$rootScope.$scopeData[name]) {
            $rootScope.$scopeData[name] = {};
        }
        $rootScope.$scopeData[name][key] = value;
    },
    /**
     * 获取缓存数据
     * @return {数据名，键}
     */
    $getData : function(name, key) {
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
    $excute : function(fn, param1, param2, param3) {
        if (fn !== undefined && fn !== null && typeof fn == "function") {
            return fn(param1, param2, param3);
        }
        return null;
    },

    /**
     * 设置参数
     * @param {[type]} obj [description]
     */
    $setParam : function(key, value) {
        $rootScope.$params[key] = value;
    },

    /**
     * 获取参数
     * @param  {[type]} key 键
     * @return {[type]}     值
     */
    $getParam : function(key) {
        //获得参数
        var param = $rootScope.$params[key];
        //获取后删除
        delete $rootScope.$params[key];
        return param;
    },

    //ajax提交
    $ajaxRequest : function(_options) {
        var options = {
            url : "",
            params : {},
            contextId : "",
            method : "post",
            success : function(result) {
            },
            fail : function(errMsg) {
            }
        };
        $.extend(options, _options);
        options.params.currentOrgId = $rootScope.UserInfo.orgId;
        options.params.currentOrgCode = $rootScope.UserInfo.orgCode;
        options.params.currentOrgPrivilegeCode = $rootScope.UserInfo.orgPrivCode;
        options.params.currentUserId = $rootScope.UserInfo.userId;
        options.params.currentUserName = $rootScope.UserInfo.userName;
        options.params.currentUserLoginName = $rootScope.UserInfo.loginName;
        
        $.ajax({
            url : options.url,
            type : "post",
            data : options.params,
            timeout:1000000,
           // dataType : "json",
            success : function(retData) {
                $rootScope.$excute(options.success, retData);
            },
            error : function(err) {
            	var err = err.responseText.substring(err.responseText.indexOf(":")+1);
                $rootScope.$excute(options.fail, err);
            }
        });
    },
    //form提交
    $formSubmit : function(_options) {
        var options = {
            formId : "formId",
            ContextId : "",
            actionUrl : "",
            success : function(result) {
            },
            onSubmit : function(frm) {
                return true;
            },
            fail : function(errMsg) {
            }
        };
        $.extend(options, _options);
        $(options.formId).form('submit', {
            url : options.actionUrl,
            onSubmit : function(param) {
                param.ContextId = $rootScope.ContextId;
            },
            success : function(data) {
                data = JSON.parse(data);
                if (data.Error !== '') {
                    if (data.Error == 'invalid request') {
                        // 到登录界面
                    } else {
                        $rootScope.$excute(options.success, data.Error);
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
    $gotoPage : function(page) {
        $("#topPanel").panel("refresh", page);
        //清空缓存
        $rootScope.$destroy();
        //标记当前页面性质为首页，用来给框架区分当前页面是否为登录页或者首页
        $rootScope.$setParam("$isHome", true);
    },

    /**
     * 清空缓存中的原色，销毁缓存
     * @return {[type]} [description]
     */
    $destroy : function() {
        $rootScope.$params = {};
    },

    /**
     * 根据品牌编码获得品牌名称
     * @param  {[type]} code 编码
     * @return {[type]}      名称
     */
    $getBrandNameByCode : function(code) {
        for (var index in brandData) {
            var item = brandData[index];
            if (item.id == code) {
                return item.name;
            }
        }
    },

    /**
     * 异步请求加载机构树
     */
    $initOrg : function() {
        //加载机构树
        $rootScope.$ajaxRequest({
            url : $rootScope.$restRoot + "/sysCfg/org/getOrgRoot",
            success : function(data) {
                //缓存机构树
                $rootScope.$org = data;
            }
        });
    },
    /**
     * 异步请求加载道路树
     */
    $initRoad : function() {
        //加载机构树
        $rootScope.$ajaxRequest({
            url : $rootScope.$restRoot + "/road/roadCode/getRoadCodeTree",
            success : function(data) {
                //缓存机构树
                $rootScope.$road = data;
            }
        });
    },
    /**
     * 异步请求加载行政区划树
     */
    $initDistrict : function() {
        //加载行政区划
        $rootScope.$ajaxRequest({
            url : $rootScope.$restRoot + "/sysCfg/sysCode/getDistrict",
            params : {districtCode:'530000'},
            success : function(data) {
                  $rootScope.$district = data;
            }
        });
    },
    /**
     * 异步请求加载号牌前缀
     */
    $initPlatePrefix : function() {
        //加载行政区划
        $rootScope.$ajaxRequest({
            url : $rootScope.$restRoot + "/sysCfg/sysCode/getPlatePrefix",
            success : function(data) {
                  $rootScope.$platePrefix = data;
            }
        });
    },
    
    $getPlatePrefix:function(orgCode){
    	if(!orgCode){
    		return ;
    	}
    	return $rootScope.$platePrefix[orgCode]
    },
    /**
     * 根据系统代码类型和id获取系统代码名称
     */
    $getCodeName : function(type, id) {
        var codes = $rootScope.$code[type];
        if (codes) {
            for (var index in codes) {
                var code = codes[index];
                if (code.value == id) {
                    return code.text;
                }
            }
            return "";
        }else{
            return "";
        }
    },
    /**
     * 根据行政区划代码获得行政区划名称
     */
    $getDistrictName : function(code) {
        if(!code || code == ""){
            return "";
        }
        var codes = code.split(",");
        var name = "";
        for(var index in codes){
            name += $rootScope._getDistrictName($rootScope.$district[0], codes[index]) + ",";
        }
        name = name.substring(0,name.length -1);
        if(!name){
            name = "";
        }
        return name;
    },
    /**
     * 递归查找名称
     */
    _getDistrictName : function(district, code) {
        if (district.id == code || district.attribute && district.attribute.code == code) {
            return district.text;
        } else {
            for (var index in district.children) {
                var name = $rootScope._getDistrictName(district.children[index], code);
                if (name) {
                    return name;
                }
            }
            return "";
        }
    },
    /**
     *  获取机构权限编码
     */
	$getOrgPrivCode : function(code){
		if(!code || code == ""){
            return "";
        }
        var codes = code.split(",");
        var orgPrivCode = "";
        for(var index in codes){
            orgPrivCode += $rootScope._getOrgPrivCode($rootScope.$org[0], codes[index]) + ",";
        }
        orgPrivCode = orgPrivCode.substring(0,orgPrivCode.length -1);
        if(!orgPrivCode){
            orgPrivCode = "";
        }
        return orgPrivCode;
	},
    /**
     * 根据机构代码获得机构名称
     */
    $getOrgName : function(code) {
        if(!code || code == ""){
            return "";
        }
        var codes = code.split(",");
        var name = "";
        for(var index in codes){
            name += $rootScope._getOrgName($rootScope.$org[0], codes[index]) + ",";
        }
        name = name.substring(0,name.length -1);
        if(!name){
            name = "";
        }
        return name;
    },
    /**
     * 递归查找机构权限编码
     */
    _getOrgPrivCode : function(org, code) {
        if (org.id == code || org.attribute && org.attribute.code == code) {
            return org.attribute.orgPrivCode;
        } else {
            for (var index in org.children) {
                var orgPrivCode = $rootScope._getOrgPrivCode(org.children[index], code);
                if (orgPrivCode) {
                    return orgPrivCode;
                }
            }
            return "";
        }
    },
    /**
     * 递归查找名称
     */
    _getOrgName : function(org, code) {
        if (org.id == code || org.attribute && org.attribute.code == code) {
            return org.text;
        } else {
            for (var index in org.children) {
                var name = $rootScope._getOrgName(org.children[index], code);
                if (name) {
                    return name;
                }
            }
            return "";
        }
    },
    
    /**
     * 根据道路代码获得道路名称
     */
    $getRoadCode : function(id) {
        if(!id || id == ""){
            return "";
        }
        var ids = id.split(",");
        var codes = "";
        for(var index in ids){
        	codes += $rootScope._getRoadCodes($rootScope.$road[0], ids[index]) + ",";
        }
        codes = codes.substring(0,codes.length -1);
        if(!codes){
        	codes = "";
        }
        return codes;
    },
    
    /**
     * 递归查找名称
     */
    _getRoadCodes : function(road, id) {
        if (road.id == id || road.attribute && road.attribute.code == id) {
            return road.attribute.code;
        } else {
            for (var index in road.children) {
                var code = $rootScope._getRoadCodes(road.children[index], id);
                if (code) {
                    return code;
                }
            }
            return "";
        }
    },
    
    /**
     * 根据道路代码获得道路名称
     */
    $getRoadName : function(code) {
        if(!code || code == ""){
            return "";
        }
        var codes = code.split(",");
        var name = "";
        for(var index in codes){
            name += $rootScope._getRoadName($rootScope.$road[0], codes[index]) + ",";
        }
        name = name.substring(0,name.length -1);
        if(!name){
            name = "";
        }
        return name;
    },
    /**
     * 递归查找名称
     */
    _getRoadName : function(road, code) {
        if (road.id == code || road.attribute && road.attribute.code == code) {
            return road.text;
        } else {
            for (var index in road.children) {
                var name = $rootScope._getRoadName(road.children[index], code);
                if (name) {
                    return name;
                }
            }
            return "";
        }
    },
    /**
     * 设置开始时间和结束时间的关系 
     */
    $initSearchDate : function(start,end){
    	start.datebox("calendar").calendar({
    		validator : $rootScope.$beforeWith(end,start.datetimebox("spinner"))
    	});
    	end.datebox("calendar").calendar({
    		validator : $rootScope.$afterWith(start,end.datetimebox("spinner"))
    	});
    },
    /**
     * 最大时间过滤器
 	 * @param {Object} el 结束时间元素 
     */
    $beforeWith : function(el,spinner){
    	return function(date){
    		var dateStr = el.textbox("getValue");
			if(dateStr == ""){
				return true;
			}
			var minDate = new Date(dateStr.replace(/-/g,"/"));
			if(!minDate.getTime()){
				return true;
			}
    		if(date.getTime() >= minDate.getTime()){
    			return false;
    		}
    		return true;
    	};
    },
    /**
     * 最小时间过滤器
 	 * @param {Object} el
     */
    $afterWith : function(el){
    	return function(date){
			var dateStr = el.textbox("getValue");
			if(dateStr == ""){
				return true;
			}
			var maxDate = new Date(dateStr.replace(/-/g,"/"));
			if(!maxDate.getTime()){
				return true;
			}
    		if(date.getTime()+24*3600*1000 <= maxDate.getTime()){
    			return false;
    		}
    		return true;
    	};
    },
    
    /**
     * 增减时间
     * @param strType 时间增减类型
     * @param numInterval 增减刻度
     * @param dtTmp 时间
     * @returns {Date}
     */
    $dateAdd : function (strType, numInterval, dtTmp) {
        if (dtTmp == null | dtTmp == "")
            dtTmp = new Date();
        switch (strType) {
            case "h":
                return new Date(dtTmp.getTime()+ (3600000 * numInterval));
            case "d":
                return new Date(dtTmp.getTime()+ (86400000 * (numInterval + 1)));
            case "w":
                return new Date(dtTmp.getTime()+ ((86400000 * 7) * numInterval) + 86400000);
            case "m":
                return new Date(dtTmp.getFullYear(), (dtTmp.getMonth()) + numInterval, dtTmp.getDate(), dtTmp.getHours(), dtTmp.getMinutes(), dtTmp.getSeconds());
            case "y":
                return new Date((dtTmp.getFullYear() + numInterval), dtTmp.getMonth(), dtTmp.getDate(), dtTmp.getHours(), dtTmp.getMinutes(), dtTmp.getSeconds());
        }
    },
    
    
     /**
     * 报警队列 
     */
    $alarmCache : [],
    
    $alarmEventQueue : {
    	
    },
    /**
     * 新增报警
     */
    $addAlarmInfoIntoCache : function(subscribeType,alermInfo){
    	//报警信息入队列
    	var data = {};
    	data[subscribeType.messageKey] = alermInfo;
    	data["isSign"] = false;//默认未签收
    	data["isHanlde"] = false;//默认未处置
    	this.$alarmCache.push(data);
    	//控制报警信息数量在50以内
    	if(this.$alarmCache.length > 50){
    		this.$alarmCache.splice(0,1);
    	}
    	//执行报警注册事件
    	if(this.$alarmEventQueue[subscribeType.messageKey] && this.$alarmEventQueue[subscribeType.messageKey].length > 0){
    		for(var index in this.$alarmEventQueue[subscribeType.messageKey]){
    			var item = this.$alarmEventQueue[subscribeType.messageKey][index];
    			if(typeof item == "function"){
    				item(alermInfo);
    			}
    		}
    	}
    },
    /**
     * 注册报警事件
     */
    $bindAlarmEvent : function(subscribeType,callBack){
    	if(this.$alarmEventQueue[subscribeType.messageKey]){
    		this.$alarmEventQueue[subscribeType.messageKey].push(callBack);
    	}else{
    		this.$alarmEventQueue[subscribeType.messageKey] = [callBack];
    	}
    	//立即执行当前已产生的报警
		for(var index in this.$alarmCache){
			var item = this.$alarmCache[index];
			if(item[subscribeType.messageKey]){
				callBack(item[subscribeType.messageKey]);
			}
		}
    },
    /**
     * 解除事件绑定
 	 * @param {Object} subscribeType
     */
    $unbindAlarmEvent : function(subscribeType,callBack){
    	//注册到监听队列
    	if(this.$alarmEventQueue[subscribeType.messageKey] && this.$alarmEventQueue[subscribeType.messageKey].length > 0){
    		for(var index=0; index<this.$alarmEventQueue[subscribeType.messageKey].length; index++){
    			var item = this.$alarmEventQueue[subscribeType.messageKey][index];
    			if(item === callBack){
    				this.$alarmEventQueue[subscribeType.messageKey].splice(index--,1);
    			}
    		}
    	}
    }
};
//默认是主页
$rootScope.$setParam("$isHome", true);
