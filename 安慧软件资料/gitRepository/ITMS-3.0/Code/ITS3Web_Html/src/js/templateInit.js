/**
 * 页面初始化方法
 * @param params 参数数组
 * @param loadBeforeFn {Function} 页面功能加载前调用函数
 * @param loadAfterFn {Function} 页面功能加载后调用函数
 * @return {[type]}
 */
function InitPage(params,loadBeforeFn){
    'use strict';
	//定义当前作用域
	var $scope = {};
    //用来保存dom对象,暂时只能保存与数据绑定的from和datagrid，后期会扩展
    $scope.$dom = {
        form : {},
        datagrid : {}
    };
    //导入全局scope对象属性
    $.extend($scope, $rootScope);
    //获得参数
    $scope.param = {};
    params.push("$url");//当前页面链接，只有tab页面和弹框页面有
    params.push("$params");//url中的参数，只有tab页面和弹框页面有
    params.push("$closeSelf");//关闭页面方法，只有弹框页面有，其他页面此方法为undefined
    params.push("$destroySelf");//销毁页面方法，只有弹框页面有，其他页面此方法为undefined
    params.push("$isHome");//标记当前是否为首页
    for(var index in params){
       $scope.param[params[index]] = $scope.$getParam(params[index]);
    }
    $.extend($scope, $scope.param);
    delete $scope.param;
    //用来指示当前打开的页面是否是tab页签页面，默认不是
    var isTabPageflag = false;
    //获得全局tabs对象，并将数据页面数据保存到当前tab
    if($scope.$tabs){
        var currentTab = $scope.$tabs.tabs("getSelected");
        if(!currentTab.data("scopeObject")){
            currentTab.data("scopeObject",$scope);
            //标记当前页面为打开页面
            isTabPageflag = true;
        }  
    }
	 /**
	 * 定义页面初始化动作
	 * context 页面DOM元素JQuery对象
	 */
    $.parser.onComplete = function (context) {
        //重置初始事件防止重复加载
        $.parser.onComplete = function () {};
        //如果context为空，则为首页，此时的context=$(window)
        if(context !== undefined){
            if(isTabPageflag || $scope.$isHome){
                //是tab页打开的页面或者是首页，登录页才显示遮罩，其他通过panel的href加载的页面都不展示遮罩
                //显示加载中。。。
                $scope.$loading.show({
                    height : context.height(),
                    width : context.width(),
                    left : context.offset().left,
                    top : context.offset().top
                });
                //0.6秒后关闭
                setTimeout($scope.$loading.hide, 600);
            }
            
        }else{
            context = $(document);
        }
        //保存context
        $scope.$context = context;
        //定义选择器 selecter 选择器字符串，参考jQueryAPI
        $scope.$ = function (selecter) {
            return $scope.$context.find(selecter);
        };
        //加一些全局事件
        $scope.$commonOperate();
        //事件代理
        $scope.$eventProxy();
        //执行自定义初始化方法
        $scope.$excute(loadBeforeFn, $scope);
        //解析html中内容
        $scope.$parser();
        //执行加载完毕方法
        $scope.$excute($scope.load);

        //测试用，右键双击界面，可以显示当前的链接地址
        if(!$scope.$isHome){
            var flag = false;
            context.on("contextmenu",function(event) {
                event.preventDefault();
                if(flag){
                    $.messager.alert('调试',$scope.$url,'info');
                }else{
                    flag = true;
                    setTimeout(function(){
                        flag = false;
                    },500);
                }        
            });
        }  
    };

    /**
     * 一般基础操作
     */
    $scope.$commonOperate = function(){
        //给textarea添加class，和鼠标进入离开的样式，与textbox统一
        $scope.$("textarea").on("focus",function(){
            $(this).toggleClass("textbox-focused");
        }).on("blur",function(){
            $(this).toggleClass("textbox-focused");
        });        
    };

    //事件代理方法实现
    $scope.$eventProxy = function(){
        //click事件
        $scope.$("[cy-click]").unbind('click').on("click",$invokeEvent);
        //dblclick事件
        $scope.$("[cy-dblclick]").unbind('dblclick').on("dblclick",$invokeEvent);
        //focus
        $scope.$("[cy-focus]").unbind('focus').on("focus",$invokeEvent);
        //blur
        $scope.$("[cy-blur]").unbind('blur').on("blur",$invokeEvent);
        //mouseenter
        $scope.$("[cy-mouseenter]").unbind('mouseenter').on("mouseenter",$invokeEvent);
        //mouseleave
        $scope.$("[cy-mouseleave]").unbind('mouseleave').on("mouseleave",$invokeEvent);
        //change
        $scope.$("[cy-change]").unbind('change').on("change",$invokeEvent);
        //mousedown
        $scope.$("[cy-mousedown]").unbind('mousedown').on("mousedown",$invokeEvent);
        //mouseup
        $scope.$("[cy-mouseup]").unbind('mouseup').on("mouseup",$invokeEvent);
        //mousemove
        $scope.$("[cy-mousemove]").unbind('mousemove').on("mousemove",$invokeEvent);
        //resize
        $scope.$("[cy-resize]").unbind('resize').on("resize",$invokeEvent);
        //keydown
        $scope.$("[cy-keydown]").unbind('keydown').on("keydown",$invokeEvent);
        //keyup
        $scope.$("[cy-keyup]").unbind('keyup').on("keyup",$invokeEvent);
        //keypress
        $scope.$("[cy-keypress]").unbind('keypress').on("keypress",$invokeEvent);
        //scroll
        $scope.$("[cy-scroll]").unbind('scroll').on("scroll",$invokeEvent);
        //select
        $scope.$("[cy-select]").unbind('select').on("select",$invokeEvent);
        //submit
        $scope.$("[cy-submit]").unbind('submit').on("submit",$invokeEvent);
    };
    //执行事件函数
    function $invokeEvent(event){
        var eventStr = $(this).attr("cy-" + event.type);
        var fn = $scope.$apply(eventStr);
        $scope.$excute(fn,event);
    }
    /**
     * 解析html，组件转换
     * @return {[type]}
     */
    $scope.$parser = function(){
        //找到class含有cy-vehNumberbox的标签，并初始化
        $initVehNumberBox();
        //找到class为cy-location的标签，并初始化
        $initChooseLocation();
        //找到class为cy-brandbox的标签，并初始化
        $initBrandBox();
    	//找到含有cy-name属性标签,自动填充值
    	$parseName();
		//找到含有cy-form属性标签,自动填充值
		$parseForm();
		//找到带有cy-datagrid属性标签，并初始化
		$parseDatagrid();

        /**
         * 初始化选择品牌方法
         */
        function $initBrandBox(){
            var boxs = $scope.$(".cy-brandbox");
            var timeout;
            boxs.each(function(){
                var box = $(this);
                var initValue = box.val();
                box.textbox({
                    prompt : "请选择车辆品牌",
                    onChange : function(newValue,oldValue){  
                        $(this).textbox("setText",$scope.$getBrandNameByCode(newValue));
                    }
                }).textbox("setText",$scope.$getBrandNameByCode(initValue));
                box.textbox("textbox").click(function(event){
                    var text = $(this);
                    $("#brandbox").data("selectIndex",-1);
                    $("#brandbox").data("textbox",box);
                    //初始化选择框
                    $("#brandbox").children().show().removeClass(".brandItem-hover");
                    $("#brandbox").show().css({
                        left : text.offset().left,
                        top : text.offset().top + text.height() + 5
                    }).scrollTop(0);
                }).unbind("blur").blur(function(){
                    $(this).parent().removeClass("textbox-focused");
                    if($(this).val() === ""){
                        box.textbox("setValue","");
                    }
                }).bind("input",function(event){
                    var that = this;
                    clearTimeout(timeout);
                    timeout = setTimeout(function(){
                        var value = that.value;
                        //隐藏所有的字母索引
                        if(value !== ""){
                            $("#brandbox").children(".letterItem").hide();
                        }else{
                            $("#brandbox").children(".letterItem").show();
                        }
                       
                        //获取所有的品牌项
                        var items = $("#brandbox").children(".brandItem");
                        items.each(function(){
                            $(this).show();
                            var data = $(this).data("brandData");
                            var flag = false;
                            for(var i in data.searchIndex){
                                if(data.searchIndex[i].indexOf(value.toLowerCase()) >= 0){
                                    flag = true;
                                    break;
                                }
                            }
                            if(!flag){
                                $(this).hide();
                            }
                        });
                        //滚动到顶层
                        $("#brandbox").scrollTop(0);
                    }, 500);
                });
            });
        }
        //$initVehNumberBox方法实现
        function $initVehNumberBox(){
            var box = $scope.$(".cy-vehNumberbox");
            var options = box.attr("data-options");
            var defaultOptions = {
                buttonIcon: "icon-edit",
                prompt : "请输入车牌号",
                onClickButton: $showNumberBox
            }
            if(options){
                $.extend(defaultOptions,eval("{" + options +  "}"));
            }
            box.textbox(defaultOptions);
            box.each(function () {
                var that = this;
                var textbox = $(this).textbox("textbox");
                var vehNumber = new VehNumber($(this));
                textbox.data("vehNumber", vehNumber);
                if (isIE()) {
                    //IE
                    textbox.bind("keyup", function () {
                        $input($(this), $(that));
                    });
                } else {
                    //非IE
                    textbox.bind("input", function () {
                        $input($(this), $(that));
                    });
                }
            });
            //输入变化操作
            function $input(text, box) {
                var vehNumber = text.data("vehNumber");
                box.textbox("setValue", text.val().toUpperCase());
                vehNumber.init();
                box.textbox("setValue", vehNumber.getVehNumber());
            }

            //打开选择窗
            function $showNumberBox() {
                var textbox = $(this).textbox("textbox");
                $("#vehNumberCookie").show().css({
                    left: textbox.offset().left,
                    top: textbox.offset().top + textbox.outerHeight() + 3
                }).data("vehNumber", textbox.data("vehNumber"));
            }
        }
    	//paeseName方法实现
    	function $parseName(){
    		$scope.$("[cy-name]").each(function(index, el) {
    			//获取name属性值
    			var name = $(this).attr("cy-name");
    			var filter = $(this).attr("cy-filter");
    			//找到$scope中对应的属性
    			var value = $scope.$apply(name);
    			if(value && filter){
    				$(this).html($scope.$apply(filter + "('" + value + "')"));
    			}else if(value){
    				$(this).text(value);
    			}
    		});
    	}

        //初始化点位选择组件的实现
        function $initChooseLocation(){
            var input = $scope.$(".cy-chooseLocation");
            input.each(function () {
                //添加按钮
                var that = this;
                var button = $("<a class='button'>选择范围</a>");
                var menu = $("<div><div class='mapChooseLocation'>地图选择</div><div class='clearLocation'>清空</div></div>");
                //清空按钮方法
                menu.find(".clearLocation").click(function (e) {
                    $(that).textbox("setValue", "");//清空数据
                    $(that).textbox("textbox").tooltip("destroy");//删除tip
                    $(that).removeData("currentLocationData");//删除缓存的数据
                    //删除选择点位的弹框缓存
                    for(var index in $scope.$dialog){
                        if(index.startWith("chooseLocationDailog")){
                            $scope.$dialog[index].dialog("destroy");
                            delete $scope.$dialog[index];
                        }
                    }
                });
                var dialogName = "chooseLocationDailog" + new Date().getTime();
                //地图选择按钮方法
                menu.find(".mapChooseLocation").click(function () {
                    $scope.$openDialog(dialogName,{
                        title: "地图选择",
                        width: 800,
                        height: 600,
                        href: "tpls/passvehicle/track_query/choose-location.html"
                    },false);
                });
                $(this).after(button).after(menu);
                //设置easyui样式
                $(this).textbox({
                    readonly: true
                });
                //定义选择方位类型隐藏框
                var areaTypeName = $(this).attr("areaTypeName");
                areaTypeName = areaTypeName ? areaTypeName : "areaTypeName";
                var areaTypeInput = $("<input type='hidden' name='" + areaTypeName + "'/>");
                $(this).after(areaTypeInput);
                //获取最大显示字数
                var size = $(this).attr("size");
                size = size ? parseInt(size):20;
                $(this).removeAttr("size");
                //给按钮一个弹框名称属性，用来对应不同的弹框
                button.attr("dialogname","chooseLocationDailog" + new Date().getTime()).splitbutton({
                    menu: menu
                }).css("margin-left", 5).click(function () {
                    //打开选择点位界面
                    $chooseLocationDialog($(that),areaTypeInput, size, $(this).attr("dialogname"));
                });
            });
        }

        //打开选择点位弹窗
        function $chooseLocationDialog(inputEl,areaTypeInput, maxSize, dialogName) {
            //定义数据展现方法
            var $setLocationData = function (data) {
                $viewLocationData(inputEl, areaTypeInput, data, maxSize);
            };
            //将显示方法添加到参数中
            $scope.$setParam("setLocationData",$setLocationData);
            //打开窗口，只在第一次加载新窗口
            var dialog = $scope.$openDialog(dialogName,{ 
                title: "选择范围",
                width: 700,
                height: 550,
                href: "tpls/public/choose-location.html"
            },false);
        }
        //展现选择的点位数据
        function $viewLocationData(inputEl, areaTypeInput, data, maxSize) {
            //注入选择范围值
            areaTypeInput.val(data.areaType);
            //注入选择数据值
            inputEl.textbox("setValue",data.value.join(","));
            //注入显示值
            var displayText = data.areaText + "：" + data.text;
            //长度大于规定值，显示。。。
            if(displayText.length > maxSize){
                displayText = displayText.substring(0,maxSize) + "...";
            }
            inputEl.textbox("setText",displayText);
            //更新tips
            var tip = $("<div class='tip'></div>");
            var texts = data.text.split(",");
            for(var index in texts){
                var text = texts[index];
                tip.append("<div class='tipItem'>" + text + "</div>");
            }
            var textbox = inputEl.textbox("textbox");
            textbox.tooltip("destroy");
            if (texts.length !== 0) {
                textbox.tooltip({
                    position: "top",
                    content: tip
                });
            }
        }

        
    	//parseForm方法实现
    	function $parseForm(){
    		$scope.$("form[cy-form]").each(function(index, el) {
    			//获得name属性值
    			var name = $(this).attr("cy-form");
    			if(name){
	    			//初始化name表达的对象
	    			$scope.$initObject(name);
	    			//用整个name字符串来缓存from的DOM元素
	    			$scope.$dom.form[name] = this;
	    			//获得scope中的属性值
    				var values = $scope.$apply(name);
                    //填充数据
    				$(this).form("load",values);
                    //导入form中原有的数据
                    $.extend(values, $(this).getParams());
	    			//DOM中缓存数据
    				$(this).data(name,values);
    			}
    		});
    	}
		//parseDatagrid的实现方法
    	function $parseDatagrid(){
            var datagrids = $scope.$("table[cy-datagrid]");
            if(datagrids.length === 0){
                return;
            }
    		datagrids.each(function(){
    			//初始化datagrid
                var optionName = $(this).attr("cy-datagrid");
                //初始化optionName表达的对象
                $scope.$initObject(optionName);
                var options = $scope.$apply(optionName);
                if(!options){
                    //没有参数则不初始化datagrid
                    return true;
                }
                var userLoadSuccess = options.onLoadSuccess;
                //给options添加初初始化方法
                options.onLoadSuccess = function(data){
                   //重新初始化代理事件
                   $scope.$eventProxy();
                   //调用自定义的加载事件方法   
                   $scope.$excute(userLoadSuccess,data);
                };
                $(this).datagrid(options);
                //初始化查询操作
    			var name = $(this).attr("cy-querydata");//查询数据字段名称
    			if(name){
	    			var url = $(this).attr("cy-url");
	    			//初始化data表达的对象
	    			$scope.$initObject(name);
                    var param = $scope.$apply(name);
                    //保存url到参数中
                    param.url = url;
	    			//数据中缓存DOM
	    			$scope.$dom.datagrid[name] = this;
	    			//dom中缓存数据
	    			$(this).data(name,param);
	    			//获得分页对象并初始化分页参数到数据中
                    var pager = $(this).datagrid("getPager");
                    if(pager.length > 0){
                        var paginationOptions = pager.pagination("options");
                        param.pageSize = paginationOptions.pageSize;
                        param.pageNumber = paginationOptions.pageNumber;
                        //增加分页操作
                        $(this).datagrid("getPager").pagination({
                            onSelectPage: function (pageNumber, pageSize) {
                                param.pageSize = pageSize;
                                param.pageNumber = pageNumber;
                                $scope.$search(name);
                            }
                        });
                    }
			    }
    		});
            $scope.$search = search;
    	}
    };

    /**
     * 初始化字符串中的对象，如：a.b.c.d,初始化为$scope.a = {b : { c: { d: {}}}}
     * @param  {[type]} 对象字符串，如a.b.c
     * @return {[type]}
     */
    $scope.$initObject = function(objectStr){
    	var props = objectStr.split(".");
    	var pare = $scope;
    	for(var index=0; index<props.length; index++){
    		if(!pare[props[index]]){
    			pare = pare[props[index]] = {};
    		}else{
    			pare = pare[props[index]];
    		}
    	}
    };
    /**
     * 执行查询操作
     * url ： 查询链接
     * param ： 查询参数
     * callback ： 回调函数
     * @type {[type]}
     */
    function search(dataStr,param,callback){
        $scope.$updateData(dataStr);
    	var gridData = $scope.$apply(dataStr);
    	$.extend(param, gridData);
    	//没有数据或数据中没有url则没有查询操作
    	if(!gridData || !gridData.url) return;
    	//缓存中取出与参数对应的datagrid
    	var datagrid = $scope.$dom.datagrid[dataStr];
    	//显示加载中。。
    	$(datagrid).datagrid("loading");
    	//ajax查询
        $.ajax({
            type: "get", 
            url: gridData.url,
            data: gridData,
			beforeSend: function (request) {
			    request.setRequestHeader("ContextID", $rootScope.ContextId);
			},
            dataType: "json",
            success: function (data) {
                $(datagrid).datagrid("loaded");
                if (data.Error !== "") {
                    alert(data.Error);
                } else {
                    if (data.Result.rows.length !== 0) {
                        //记录页面总数
                        gridData.totalCount = data.Result.total;
                        //查询成功 加载数据
                        $(datagrid).datagrid("loadData", data.Result);
                        $scope.$excute(callback, data.Result);
                    }
                }
            }
        });
    }

    
    /**
     * 更新数据，从DOM到data
     * @param  {[type]} name [description]
     * @return {[type]}      [description]
     */
    $scope.$updateData = function(name){
        var data = $scope.$apply(name);
        var form = $scope.$dom.form[name];
        var formData = $(form).getParams();
        $.extend(data,formData);
    };

    /**
     * 更新数据，从Data到DOM
     * @param  {[type]} name [description]
     * @return {[type]}      [description]
     */
    $scope.$updateDom = function(name){
        //更新cy-form
        $scope.$("form[cy-form='" + name + "']").each(function(){
            $(this).form("load",$scope.$apply(name));
        });
        //更新cy-name
        $scope.$("[cy-name^='" + name + "']").each(function(){
            var field = $(this).attr("cy-name");
            var filter = $(this).attr("cy-filter");
            //找到$scope中对应的属性
            var value = $scope.$apply(field);
            if(value && filter){
                $(this).text($scope.$apply(filter + "(" + value + ")"));
            }else if(value){
                $(this).text(value);
            }
        });
    };
    /**
     * 执行一段javascript代码
     * @param  {[type]}
     * @return {[type]}
     */
    $scope.$apply = function(script){
        var data;
        try{
            data =  eval("$scope." + script);
        }catch(e){
            data = undefined;
        }
        return data;
    };

    /**
     * 销毁本页面
     * @return {[type]} [description]
     */
    $scope.$destroy = function(){
        //清空所有的弹框
        for(var index in $scope.$dialog){
            $scope.$dialog[index].dialog("destroy");
        }
        //清空所有的html
        // $scope.$context.children().remove();
        //清空$scope
        $scope = null;
    };
    /**
     * 弹框区
     */
    $scope.$dialog = {};
    /**
     * 打开新窗口
     * @param  {[type]}  name    窗口名称，唯一
     * @param  {[type]}  options 窗口属性
     * @param  {Boolean} isNew   是否重新打开，默认为true
     * @return {[type]}          
     */
    $scope.$openDialog = function(name,options,isNew){
        //默认重新加载页面
        if(isNew === undefined || isNew === null){
            isNew = true;
        }
        options.cache = true;
        //如果给定的参数的打开新窗口，则每次都打开新窗口，否则，居中展示已经打开的窗口，如窗口不存在，则重新打开
        if(isNew){
            //打开窗口
            openNew();
            return $scope.$dialog[name];
        }else{
            if($scope.$dialog[name]){
                return $scope.$dialog[name].dialog("open").dialog("center");
            }else{
                openNew();
                return $scope.$dialog[name];
            }
        }

        function openNew(){
            //如果是href方式打开，设置一些默认参数
            if(options.href && options.href !== null &&  options.href !== ""){
                $scope.$setParam("$params",new URL("http://" + options.href).searchParams);
                $scope.$setParam("$url",options.href);
                $scope.$setParam("$closeSelf",function(){
                    $scope.$getDialog(name).dialog("close");
                });
                $scope.$setParam("$destroySelf",function(){
                    $scope.$getDialog(name).dialog("destroy");
                    delete $scope.$dialog[name];
                });
            }
            //销毁先前的弹框
            if($scope.$dialog[name]){
                $scope.$dialog[name].dialog("destroy");
            }
            $scope.$dialog[name] = $("<div></div>").dialog(options).dialog("center");
        }
    };
    /**
     * 获得窗口
     * @param  {[type]} name 窗口唯一名称
     * @return {[type]}      [description]
     */
    $scope.$getDialog = function(name){
        return $scope.$dialog[name];
    };

    
}