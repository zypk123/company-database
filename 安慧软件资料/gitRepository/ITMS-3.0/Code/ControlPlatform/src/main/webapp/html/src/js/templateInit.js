/**
 * 页面初始化方法
 * @param params 参数数组
 * @param loadBeforeFn {Function} 页面功能加载前调用函数
 * @param loadAfterFn {Function} 页面功能加载后调用函数
 * @return {[type]}
 */
function InitPage(params, loadBeforeFn) {
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
	params.push("$url");
	//当前页面链接，只有tab页面和弹框页面有
	params.push("$params");
	//url中的参数，只有tab页面和弹框页面有
	params.push("$closeSelf");
	//关闭页面方法，只有弹框页面有，其他页面此方法为undefined
	params.push("$destroySelf");
	//弹窗对象,只有弹窗窗口有
	params.push("$dialogObject");
	//销毁页面方法，只有弹框页面有，其他页面此方法为undefined
	params.push("$isHome");
	//标记当前是否为首页
	for (var index in params) {
		$scope.param[params[index]] = $scope.$getParam(params[index]);
	}
	$.extend($scope, $scope.param);
	//如果是弹框页面，则把子页面的$scope对象注入到父页面的弹框对象数据缓存中
	if ($scope.$dialogObject) {
		$scope.$dialogObject.data("winScope", $scope);
	}
	delete $scope.param;
	//用来指示当前打开的页面是否是tab页签页面，默认不是
	var isTabPageflag = false;
	//获得全局tabs对象，并将数据页面数据保存到当前tab
	if ($scope.$tabs) {
		var currentTab = $scope.$tabs.tabs("getSelected");
		if (!currentTab.data("scopeObject")) {//注释 by jinhb at 2016.4.13
			currentTab.data("scopeObject", $scope);
			//标记当前页面为打开页面
			isTabPageflag = true;
		}
	}
	/**
	 * 定义页面初始化动作
	 * context 页面DOM元素JQuery对象
	 */
	$.parser.onComplete = function(context) {
		//重置初始事件防止重复加载
		$.parser.onComplete = function() {
		};
		//如果context为空，则为首页，此时的context=$(window)
		if (context !== undefined) {
			if (isTabPageflag || $scope.$isHome) {
				//是tab页打开的页面或者是首页，登录页才显示遮罩，其他通过panel的href加载的页面都不展示遮罩
				//显示加载中。。。
				$scope.$loading.show({
					height : context.height(),
					width : context.width(),
					left : context.offset().left,
					top : context.offset().top
				});
			}

		} else {
			context = $(document);
		}
		//保存context
		$scope.$context = context;
		//定义选择器 selecter 选择器字符串，参考jQueryAPI
		$scope.$ = function(selecter) {
			return $scope.$context.find(selecter);
		};
		//加一些全局事件
		$scope.$commonOperate();
		//页面权限验证
		$scope.$authValidate();
		//事件代理
		$scope.$eventProxy();
		//执行自定义初始化方法
		$scope.$excute(loadBeforeFn, $scope);
		//获取页面初始数据
		$scope.$initCode(function() {
			//解析html中内容
			$scope.$parser();
			//延迟图片加载
			$scope.$parseLazyImage();
			//执行加载完毕方法
			$scope.$excute($scope.load);
			//关闭加载中界面
			if ($scope.$loading) {
				$scope.$loading.hide();
			}
		});
		//测试用，右键双击界面，可以显示当前的链接地址
		if (!$scope.$isHome) {
			var flag = false;
			context.on("contextmenu", function(event) {
				event.preventDefault();
				if (flag) {
					$.messager.alert('调试', $scope.$url, 'info');
				} else {
					flag = true;
					setTimeout(function() {
						flag = false;
					}, 500);
				}
			});
		}
	};

	$scope.$currentCodes = [];
	/**
	 * 设置需要获取的系统代码
	 */
	$scope.$getDefaultCode = function(codes, defaultCodeCallback) {
		var currentCodes = [];
		for (var index in codes) {
			//删除已经请求过的
			if (!$scope.$code[codes[index]]) {
				currentCodes.push(codes[index]);
			}
		}
		$scope.$currentCodes = currentCodes;
		$scope.$defaultCodeCallback = defaultCodeCallback;
	};

	/**
	 * 获取初始数据
	 */
	$scope.$initCode = function(callback) {
		//获得所有的codeType，组装成字符串
		var codeEls = $scope.$("[cy-code]");
		codeEls.each(function() {
			var codeType = $(this).attr("cy-code");
			var oldChange = $(this).combobox("options").onChange;
			$(this).combobox({
				onChange : function(newValue,oldValue) {
					$scope.$excute(oldChange,newValue,oldValue);
					if (newValue == "") {
						$(this).combobox("setText", "");
					}
				}
			});
			//获取缓存中没有的codeType
			if (!$scope.$code[codeType]) {
				if ($scope.$currentCodes.indexOf(codeType) < 0) {
					$scope.$currentCodes.push(codeType);
				}
			} else {
				var data = [{
					text : "请选择",
					value : ""
				}].concat($scope.$code[codeType]);
				$(this).combobox("loadData", data);
				if($(this).combobox("getValue") == ""){
					$(this).combobox("setText","");
				}
			}
		});
		//链接单独请求的系统代码串
		if ($scope.$currentCodes.length == 0) {
			//执行回调函数
			$scope.$excute(callback);
			//执行defaultCodeCallback
			$scope.$excute($scope.$defaultCodeCallback, $scope.$code);
			return;
		}
		//后台请求code值
		$scope.$ajaxRequest({
			url : $scope.$restRoot + "sysCfg/sysCode/getSysCodes",
			params : {
				codeTypesString : $scope.$currentCodes.join(",")
			},
			dataType : "json",
			success : function(data) {
				//缓存获取的数据字典
				for (var codeType in data) {
					$scope.$code[codeType] = data[codeType];
					var currentData = [{
						text : "请选择",
						value : ""
					}].concat(data[codeType]);
					if ($scope.$("[cy-code=" + codeType + "]").length > 0) {
						$scope.$("[cy-code=" + codeType + "]").combobox("loadData", currentData);
						if($scope.$("[cy-code=" + codeType + "]").combobox("getValue") == ""){
							$scope.$("[cy-code=" + codeType + "]").combobox("setText","");
						}
					}
				}
				//执行回调函数
				$scope.$excute(callback);
				//执行defaultCodeCallback
				$scope.$excute($scope.$defaultCodeCallback, $scope.$code);
			}
		});
	};
	/**
	 * 页面权限验证
	 */
	$scope.$authValidate = function() {
		$scope.$("auth").each(function() {
			var resourceCode = $(this).attr("resourceCode");
			if (resourceCode && resourceCode != '') {
				if (!$scope.$hasAuth(resourceCode)) {
					$(this).remove();
				}
			}
		});
	};

	/**
	 * 判断是否有权限
	 */
	$scope.$hasAuth = function(code) {
		var resourceList = $scope.UserInfo.menus;
		return hasAuth(resourceList, code);
	};
	/**
	 * 递归遍历资源列表
	 */
	function hasAuth(resources, code) {
		var flag;
		for (var index in resources) {
			var item = resources[index];
			if (item.id == code) {
				return true;
			} else {
				flag = hasAuth(item.children, code);
				if (flag) {
					return flag;
				}
			}
		}
		return false;
	}

	/**
	 * 跳转到菜单
	 */
	$scope.$goto = function(menuCode, param) {
		var menu = findMenu($scope.UserInfo.menus, menuCode);
		if (menu) {
			//注入参数
			$scope.$setParam("$params", new URL("http://" + menu.attribute.url).searchParams);
			$scope.$setParam("$url", menu.resourceURL);
			if (param && typeof param == 'object') {
				for (var index in param) {
					$scope.$setParam(index, param[index]);
				}
			}
			//刷新已存在的tab页或者重新打开
			if ($scope.$tabs.tabs('exists', menu.text)) {
				$scope.$tabs.tabs('select', menu.text);
				//选中并刷新
				$scope.$tabs.tabs("getSelected").removeData("scopeObject").panel("refresh");
			} else {
				//获取url中的参数
				$scope.$tabs.tabs('add', {
					title : menu.text,
					href : menu.attribute.url,
					closable : true
				});
				//记录功能使用记录
				$.post($scope.$servletRoot + "/menuServlet", {
					currentUserId : $scope.UserInfo.userId,
					currentOrgId : $scope.UserInfo.orgId,
					menuId : menuCode,
					menuName : menu.text
				});
			}
		}
	};

	/**
	 * 遍历资源查找菜单
	 */
	function findMenu(resources, code) {
		for (var index in resources) {
			var item = resources[index];
			if (item.id == code) {
				return item;
			} else {
				if (item.children && item.children.length > 0) {
					var menu = findMenu(item.children, code);
					if (menu != null) {
						return menu;
					}
				}
			}
		}
	}

	/**
	 * 一般基础操作
	 */
	$scope.$commonOperate = function() {
		//给textarea添加class，和鼠标进入离开的样式，与textbox统一
		$scope.$("textarea").on("focus", function() {
			$(this).toggleClass("textbox-focused");
		}).on("blur", function() {
			$(this).toggleClass("textbox-focused");
		});
	};

	var imageContent = [];
	/**
	 * 初始化延迟加载图片
	 */
	$scope.$parseLazyImage = function() {
		$scope.$(".cy-imageContent").each(function(i, n) {
			var itemData = {};
			itemData.index = i;
			itemData.content = $(this);
			itemData.imageList = [];
			$(this).data("contentData", itemData);
			$scope.$parseCyImage(itemData);
			itemData.content.unbind("scroll").scroll(function() {
				$scope.$showImage($(this).data("contentData"));
			});

		});
	};

	$scope.$parseCyImage = function(contentData) {
		contentData.content.find("img[cy-src]").each(function() {
			//缓存所有的img
			contentData.imageList.push(this);
		});
		$scope.$showImage(contentData);
	};

	/**
	 * 判断并显示框架内的图片
	 */
	$scope.$showImage = function(contentData) {
		var imageContent = contentData.content;
		if (!imageContent || imageContent.length == 0)
			return;
		//容器距离上边高度
		var contentTop = imageContent.offset().top;
		//容器距离左边的长度
		var contentLeft = imageContent.offset().left;
		//容器高度
		var contentHeight = imageContent.height();
		//容器的宽度
		var contentWidth = imageContent.width();
		//判断已经缓存的img标签是否在已经出现在容器可视范围内
		for (var index = 0; index < contentData.imageList.length; index++) {
			var item = contentData.imageList[index];
			if ($(item).offset().top < contentTop + contentHeight && $(item).offset().left < contentLeft + contentWidth) {
				$(item).attr("src", $(item).attr("cy-src"));
				$(item).removeAttr("cy-src");
				contentData.imageList.splice(index--, 1);
			}
		}
	};
	//事件代理方法实现
	$scope.$eventProxy = function() {
		//click事件
		$scope.$("[cy-click]").unbind('click').on("click", $invokeEvent);
		//dblclick事件
		$scope.$("[cy-dblclick]").unbind('dblclick').on("dblclick", $invokeEvent);
		//focus
		$scope.$("[cy-focus]").unbind('focus').on("focus", $invokeEvent);
		//blur
		$scope.$("[cy-blur]").unbind('blur').on("blur", $invokeEvent);
		//mouseenter
		$scope.$("[cy-mouseenter]").unbind('mouseenter').on("mouseenter", $invokeEvent);
		//mouseleave
		$scope.$("[cy-mouseleave]").unbind('mouseleave').on("mouseleave", $invokeEvent);
		//change
		$scope.$("[cy-change]").unbind('change').on("change", $invokeEvent);
		//mousedown
		$scope.$("[cy-mousedown]").unbind('mousedown').on("mousedown", $invokeEvent);
		//mouseup
		$scope.$("[cy-mouseup]").unbind('mouseup').on("mouseup", $invokeEvent);
		//mousemove
		$scope.$("[cy-mousemove]").unbind('mousemove').on("mousemove", $invokeEvent);
		//resize
		$scope.$("[cy-resize]").unbind('resize').on("resize", $invokeEvent);
		//keydown
		$scope.$("[cy-keydown]").unbind('keydown').on("keydown", $invokeEvent);
		//keyup
		$scope.$("[cy-keyup]").unbind('keyup').on("keyup", $invokeEvent);
		//keypress
		$scope.$("[cy-keypress]").unbind('keypress').on("keypress", $invokeEvent);
		//scroll
		$scope.$("[cy-scroll]").unbind('scroll').on("scroll", $invokeEvent);
		//select
		$scope.$("[cy-select]").unbind('select').on("select", $invokeEvent);
		//submit
		$scope.$("[cy-submit]").unbind('submit').on("submit", $invokeEvent);
	};
	//执行事件函数
	function $invokeEvent(event) {
		var eventStr = $(this).attr("cy-" + event.type);
		var fn = $scope.$apply(eventStr);
		if (fn) {
			this.$eventCallBack = fn;
			this.$eventCallBack(event);
		}
	}

	/**
	 * 解析html，组件转换
	 * @return {[type]}
	 */
	$scope.$parser = function() {
		//找到class含有cy-vehNumberbox的标签，并初始化
		$initVehNumberBox();
		//找到class为cy-location的标签，并初始化
		$initChooseLocation();
		//找到class为cy-brandbox的标签，并初始化
		$initBrandBox();
		//找到cy-org-radio单选机构树组件，并初始化
		$initOrgTreeRadio();
		//找到cy-org-checkbox多选树组件，并初始化
		$initOrgTreeCheckbox();
		//找到cy-road-raido单选树组件，并初始化
		$initRoadTreeRadio();
		//找到cy-road-checkbox多选树组件，并初始化
		$initRoadTreeCheckbox();
		//找到cy-district-radio单选树组件，并初始化
		$initDistrictRadio();
		//找到cy-district-checkbox多选树组件，并初始化
		$initDistrictCheckbox();
		//找到cy-site-radio单选树组件，并初始化
		$initSiteRadio();
		//找到cy-site-checkbox多选树组件，并初始化
		$initSiteCheckbox();
		//找到cy-cross-redio单选组件，并初始化
		$initCrossRadio();
		//找到cy-road-section-radio单选组件，并初始化
		$initRoadSectionRadio();
		//找到含有cy-name属性标签,自动填充值
		$parseName();
		//找到含有cy-form属性标签,自动填充值
		$parseForm();
		//找到带有cy-datagrid属性标签，并初始化
		$parseDatagrid();
		//找到带有cy-foreach的属性标签，并初始化
		$parseForeach();
		//找到带有cy-area-choose的属性标签，并初始化
		$initArea();

		/**
		 * 初始化foreach组件
		 */
		function $parseForeach() {
			var foreachEl = $scope.$("[cy-foreach]");
			foreachEl.each(function() {
				//找到内部原始DOM对象，并隐藏，缓存
				var content = $(this).children();
				$(this).data("content", content);
				content.hide();
				var name = $(this).attr("cy-foreach");
				if (name) {
					//初始化name表达的对象
					$scope.$initObject(name);
					//获得scope中的属性值
					var values = $scope.$apply(name);
					if ( values instanceof Array && values.length > 0) {
						//遍历数据
						for (var index in values) {
							//数据项
							var itemData = values[index];
							//复制DOM
							var itemContent = content.clone();
							//缓存数据
							itemContent.data("itemData", itemData).data("index", index);
							var itemEls = itemContent.find("[cy-item]");
							itemEls.each(function() {
								var itemName = $(this).attr("cy-item");
								var filter = $(this).attr("cy-filter");
								var value = itemData[itemName];
								filter = $scope.$apply(filter);
								if (value && filter) {
									$(this).html($scope.$excute(filter, value, itemData));
								} else if (value) {
									$(this).text(value);
								}
							});
							$(this).append(itemContent.show());
						}
					}
				}
			});
			//重新初始化代理事件
			$scope.$eventProxy();
			//重新权限验证
			$scope.$authValidate();
		}

		/**
		 * 初始化单选路段组件
		 */
		function $initRoadSectionRadio() {
			var textbox = $scope.$(".cy-road-section-radio");
			textbox.each(function() {
				var that = this;
				var options = eval("({" + $(this).attr("data-options") + "})");
				var afterChange = $(this).attr("after-change");
				var filter = $(this).attr("filter");
				var dialogName = new Date().getTime() + parseInt(Math.random() * 1000);
				var defaultOptions = {
					//readonly : true,
					buttonIcon : "icon-road-section",
					prompt : "请选择路段",
					onChange : function(newValue, oldValue) {
						if (afterChange && afterChange != "") {
							var afterChangeFunction = eval("$scope." + afterChange);
							$scope.$excute(afterChangeFunction, newValue, oldValue);
						}
					},
					onClickButton : function() {
						$scope.$setParam("choose", function(id, code, name) {
							var isCode = $(that).attr("iscode");
							if (isCode == "true") {
								if ($(that).textbox("getValue") == code) {
									return;
								}
								$(that).textbox("setValue", code);
							} else {
								if ($(that).textbox("getValue") == id) {
									return;
								}
								$(that).textbox("setValue", id);
							}
							$(that).textbox("setText", name);
						});
						if (filter) {
							$scope.$setParam("filter", $scope.$apply(filter));
						}
						$scope.$openDialog(dialogName, {
							width : 400,
							height : 500,
							title : "选择路段",
							href : "tpls/public/choose-road-section-radio.html"
						}, filter ? true : false);
					}
				};
				if (options) {
					$.extend(defaultOptions, options);
				}
				$(this).textbox(defaultOptions);
				// $(this).textbox("textbox").unbind("blur").blur(function() {
					// $(this).parent().removeClass("textbox-focused");
					// if ($(this).val() === "") {
						// $(that).textbox("setValue", "");
					// }
				// });
			});
		}

		/**
		 * 获取地域列表
		 */
		function $getAreaList(areaType, that) {
			var formData = {};
			formData.areaType = areaType;
			//formData.currentOrgPrivilegeCode = '53';
			//获取后台数据
			$scope.$ajaxRequest({
				url : $scope.$restRoot + "/sysCfg/area/getAreaList",
				params : formData,
				success : function(data) {
					if (data && data.result && data.result.length > 0) {
						$(that).combobox({
							data : data.result,
							valueField : 'siteCodeList',
							textField : 'areaName'
						});
					}
				},
				fail : function() {
					$.messager.progress("close");
					$.messager.alert("提示", "服务器繁忙", "info");
				}
			});
		}

		/**
		 * 增加新的区域
		 */
		function $addArea(dialogName, that) {
			$scope.$setParam("refreshAreaList", function(areaType) {
				$getAreaList(areaType, that);
			});
			$scope.$openDialog("高危地域", {
				width : 680,
				height : 550,
				title : "增加高危地域",
				href : "tpls/public/choose-area-checkbox.html"
			}, true);
		}

		/**
		 * 初始化区域组件
		 */
		function $initArea() {
			var textbox = $scope.$(".cy-area-choose");
			textbox.each(function() {
				var that = this;
				$getAreaList("0", that);
				var options = eval("({" + $(this).attr("data-options") + "})");
				var afterChange = $(this).attr("after-change");
				var filter = $(this).attr("filter");
				var dialogName = new Date().getTime() + parseInt(Math.random() * 1000);
				var button = $("<a></a>");
				button.linkbutton({
					iconCls : 'icon-add'
				});
				//button.attr("data-options","iconCls:'icon-add'");
				button.bind('click', function() {
					$addArea(dialogName, that);
				});
				$(that).after(button);
			});
		}

		/**
		 * 初始化单选路口组件
		 */
		function $initCrossRadio() {
			var textbox = $scope.$(".cy-cross-radio");
			textbox.each(function() {
				var that = this;
				var options = eval("({" + $(this).attr("data-options") + "})");
				var afterChange = $(this).attr("after-change");
				var filter = $(this).attr("filter");
				var dialogName = new Date().getTime() + parseInt(Math.random() * 1000);
				var defaultOptions = {
					//readonly : true,
					buttonIcon : "icon-cross",
					prompt : "请选择路口",
					onChange : function(newValue, oldValue) {
						if (afterChange && afterChange != "") {
							var afterChangeFunction = eval("$scope." + afterChange);
							$scope.$excute(afterChangeFunction, newValue, oldValue);
						}
					},
					onClickButton : function() {
						$scope.$setParam("choose", function(id, code, name) {
							var isCode = $(that).attr("iscode");
							if (isCode == "true") {
								if ($(that).textbox("getValue") == code) {
									return;
								}
								$(that).textbox("setValue", code);
							} else {
								if ($(that).textbox("getValue") == id) {
									return;
								}
								$(that).textbox("setValue", id);
							}
							$(that).textbox("setText", name);
						});
						if (filter) {
							$scope.$setParam("filter", $scope.$apply(filter));
						}
						$scope.$openDialog(dialogName, {
							width : 400,
							height : 500,
							title : "选择路口",
							href : "tpls/public/choose-cross-radio.html"
						}, filter ? true : false);
					}
				};
				if (options) {
					$.extend(defaultOptions, options);
				}
				$(this).textbox(defaultOptions);
				// $(this).textbox("textbox").unbind("blur").blur(function() {
					// $(this).parent().removeClass("textbox-focused");
					// if ($(this).val() === "") {
						// $(that).textbox("setValue", "");
					// }
				// });
			});
		}

		/**
		 * 初始化单选点位组件
		 */
		function $initSiteRadio() {
			var textbox = $scope.$(".cy-site-radio");
			textbox.each(function() {
				var that = this;
				var options = eval("({" + $(this).attr("data-options") + "})");
				var afterChange = $(this).attr("after-change");
				var bayonet = $(this).attr("bayonet");
				var filter = $(this).attr("filter");
				var dialogName = new Date().getTime() + parseInt(Math.random() * 1000);
				var defaultOptions = {
					//readonly : true,
					buttonIcon : "icon-site",
					prompt : "请选择点位",
					onChange : function(newValue, oldValue) {
						if (afterChange && afterChange != "") {
							var afterChangeFunction = eval("$scope." + afterChange);
							$scope.$excute(afterChangeFunction, newValue, oldValue);
						}
					},
					onClickButton : function() {
						$scope.$setParam("choose", function(id, code, name) {
							var isCode = $(that).attr("iscode");
							if (isCode == "true") {
								if ($(that).textbox("getValue") == code) {
									return;
								}
								$(that).textbox("setValue", code);
							} else {
								if ($(that).textbox("getValue") == id) {
									return;
								}
								$(that).textbox("setValue", id);
							}
							$(that).textbox("setText", name);
						});
						if (filter) {
							$scope.$setParam("filter", $scope.$apply(filter));
						}
						if (bayonet) {
							$scope.$setParam("isBayonet", bayonet);
						}
						$scope.$openDialog(dialogName, {
							width : 400,
							height : 500,
							title : "选择点位",
							href : "tpls/public/choose-site-radio.html"
						}, filter ? true : false);
					}
				};
				if (options) {
					$.extend(defaultOptions, options);
				}
				$(this).textbox(defaultOptions);
				// $(this).textbox("textbox").unbind("blur").blur(function() {
					// $(this).parent().removeClass("textbox-focused");
					// if ($(this).val() === "") {
						// $(that).textbox("setValue", "");
					// }
				// });
			});
		}

		/**
		 * 初始化多选点位组件
		 */
		function $initSiteCheckbox() {
			var textbox = $scope.$(".cy-site-checkbox");
			textbox.each(function() {
				var that = this;
				var options = $(this).attr("data-options");
				var filter = $(this).attr("filter");
				var bayonet = $(this).attr("bayonet");
				var afterChange = $(this).attr("after-change");
				var dialogName = new Date().getTime() + parseInt(Math.random() * 1000);
				var defaultOptions = {
					buttonIcon : "icon-site",
					prompt : "请选择点位",
					onChange : function(newValue, oldValue) {
						if (afterChange && afterChange != "") {
							var afterChangeFunction = eval("$scope." + afterChange);
							$scope.$excute(afterChangeFunction, newValue, oldValue);
						}
					},
					onClickButton : function() {
						$scope.$setParam("choose", function(ids, codes, names) {
							var isCode = $(that).attr("iscode");
							if (isCode == "true") {
								if ($(that).textbox("getValue") == codes) {
									return;
								}
								//填入code值
								$(that).textbox("setValue", codes);
							} else {
								if ($(that).textbox("getValue") == ids) {
									return;
								}
								//填入id值
								$(that).textbox("setValue", ids);
							}
							$(that).textbox("setText", names);
							//删除旧的提示框
							$(that).tooltip("destroy");
							//添加新的提示框
							addTooltip($(that).textbox("textbox"), names);
						});
						if (filter) {
							$scope.$setParam("filter", $scope.$apply(filter));
						}
						if (bayonet) {
							$scope.$setParam("isBayonet", bayonet);
						}
						$scope.$openDialog(dialogName, {
							width : 700,
							height : 500,
							title : "选择点位",
							href : "tpls/public/choose-site-checkbox.html"
						}, filter ? true : false);
					}
				};
				if (options) {
					$.extend(defaultOptions, eval("({" + options + "})"));
				}
				$(this).textbox(defaultOptions);
				// $(this).textbox("textbox").unbind("blur").blur(function() {
					// $(this).parent().removeClass("textbox-focused");
					// if ($(this).val() === "") {
						// $(that).textbox("setValue", "");
						// //删除提示框
						// $(that).tooltip("destroy");
					// }
				// });
			});
		}

		/**
		 * 初始化单选行政区划组件
		 */
		function $initDistrictRadio() {
			var textbox = $scope.$(".cy-district-radio");
			textbox.each(function() {
				var that = this;
				var options = $(this).attr("data-options");
				var afterChange = $(this).attr("after-change");
				var filter = $(this).attr("filter");
				var dialogName = new Date().getTime() + parseInt(Math.random() * 1000);
				var defaultOptions = {
					//readonly : true,
					buttonIcon : "icon-district",
					prompt : "请选择行政区划",
					onChange : function(newValue, oldValue) {
						$(this).textbox("setText", $scope.$getDistrictName(newValue));
						if (afterChange && afterChange != "") {
							var afterChangeFunction = eval("$scope." + afterChange);
							$scope.$excute(afterChangeFunction, newValue, oldValue);
						}
					},
					onClickButton : function() {
						$scope.$setParam("choose", function(id, code, name) {
							var isCode = $(that).attr("iscode");
							if (isCode == "true") {
								if ($(that).textbox("getValue") == code) {
									return;
								}
								//填入code值
								$(that).textbox("setValue", code);
							} else {
								if ($(that).textbox("getValue") == id) {
									return;
								}
								//填入id值
								$(that).textbox("setValue", id);
							}
						});
						if (filter) {
							$scope.$setParam("filter", $scope.$apply(filter));
						}
						$scope.$openDialog(dialogName, {
							width : 400,
							height : 500,
							title : "选择行政区划",
							href : "tpls/public/choose-district-radio.html"
						}, filter ? true : false);
					}
				};
				if (options) {
					$.extend(defaultOptions, eval("({" + options + "})"));
				}
				$(this).textbox(defaultOptions);
				// $(this).textbox("textbox").unbind("blur").blur(function() {
					// $(this).parent().removeClass("textbox-focused");
					// if ($(this).val() === "") {
						// $(that).textbox("setValue", "");
					// }
				// });
			});
		}

		/**
		 * 初始化多选行政区划组件
		 */
		function $initDistrictCheckbox() {
			var textbox = $scope.$(".cy-district-checkbox");
			textbox.each(function() {
				var that = this;
				var options = $(this).attr("data-options");
				var filter = $(this).attr("filter");
				var afterChange = $(this).attr("after-change");
				var dialogName = new Date().getTime() + parseInt(Math.random() * 1000);
				var defaultOptions = {
					buttonIcon : "icon-district",
					prompt : "请选择行政区划",
					onChange : function(newValue, oldValue) {
						$(this).textbox("setText", $scope.$getDistrictName(newValue));
						//删除旧的提示框
						$(that).tooltip("destroy");
						//添加新的提示框
						addTooltip($(that).textbox("textbox"), $scope.$getDistrictName(newValue));
						if (afterChange && afterChange != "") {
							var afterChangeFunction = eval("$scope." + afterChange);
							$scope.$excute(afterChangeFunction, newValue, oldValue);
						}
					},
					onClickButton : function() {
						$scope.$setParam("choose", function(ids, codes, names) {
							var isCode = $(that).attr("iscode");
							if (isCode == "true") {
								if ($(that).textbox("getValue") == codes) {
									return;
								}
								//填入code值
								$(that).textbox("setValue", codes);
							} else {
								if ($(that).textbox("getValue") == ids) {
									return;
								}
								//填入id值
								$(that).textbox("setValue", ids);
							}
						});
						if (filter) {
							$scope.$setParam("filter", $scope.$apply(filter));
						}
						$scope.$openDialog(dialogName, {
							width : 700,
							height : 500,
							title : "选择行政区划",
							href : "tpls/public/choose-district-checkbox.html"
						}, filter ? true : false);
					}
				};
				if (options) {
					$.extend(defaultOptions, eval("({" + options + "})"));
				}
				$(this).textbox(defaultOptions);
				// $(this).textbox("textbox").unbind("blur").blur(function() {
					// $(this).parent().removeClass("textbox-focused");
					// if ($(this).val() === "") {
						// $(that).textbox("setValue", "");
						// //删除提示框
						// $(that).tooltip("destroy");
					// }
				// });
			});
		}

		/**
		 * 初始化单选道路树组件
		 */
		function $initRoadTreeRadio() {
			var textbox = $scope.$(".cy-road-radio");
			textbox.each(function() {
				var that = this;
				var options = $(this).attr("data-options");
				var afterChange = $(this).attr("after-change");
				var filter = $(this).attr("filter");
				var dialogName = new Date().getTime() + parseInt(Math.random() * 1000);
				var defaultOptions = {
					//readonly : true,
					buttonIcon : "icon-road",
					prompt : "请选择道路",
					onChange : function(newValue, oldValue) {
						$(this).textbox("setText", $scope.$getRoadName(newValue));
						if (afterChange && afterChange != "") {
							var afterChangeFunction = eval("$scope." + afterChange);
							$scope.$excute(afterChangeFunction, newValue, oldValue);
						}
					},
					onClickButton : function() {
						$scope.$setParam("choose", function(id, code, name) {
							var isCode = $(that).attr("iscode");
							if (isCode == "true") {
								if ($(that).textbox("getValue") == code) {
									return;
								}
								//填入code值
								$(that).textbox("setValue", code);
							} else {
								if ($(that).textbox("getValue") == id) {
									return;
								}
								//填入id值
								$(that).textbox("setValue", id);
							}

						});
						if (filter) {
							$scope.$setParam("filter", $scope.$apply(filter));
						}
						$scope.$openDialog(dialogName, {
							width : 300,
							height : 500,
							title : "选择道路",
							href : "tpls/public/choose-road-radio.html"
						}, filter ? true : false);
					}
				};
				if (options) {
					$.extend(defaultOptions, eval("({" + options + "})"));
				}
				$(this).textbox(defaultOptions);
				// $(this).textbox("textbox").unbind("blur").blur(function() {
					// $(this).parent().removeClass("textbox-focused");
					// if ($(this).val() === "") {
						// $(that).textbox("setValue", "");
					// }
				// });
			});
		}

		/**
		 *  初始化多选道路组件
		 */
		function $initRoadTreeCheckbox() {
			var textbox = $scope.$(".cy-road-checkbox");
			textbox.each(function() {
				var that = this;
				var options = $(this).attr("data-options");
				var afterChange = $(this).attr("after-change");
				var filter = $(this).attr("filter");
				var dialogName = new Date().getTime() + parseInt(Math.random() * 1000);
				var defaultOptions = {
					buttonIcon : "icon-road",
					prompt : "请选择道路",
					onChange : function(newValue, oldValue) {
						$(this).textbox("setText", $scope.$getRoadName(newValue));
						//删除旧的提示框
						$(that).tooltip("destroy");
						//添加新的提示框
						addTooltip($(that).textbox("textbox"), $scope.$getRoadName(newValue));
						if (afterChange && afterChange != "") {
							var afterChangeFunction = eval("$scope." + afterChange);
							$scope.$excute(afterChangeFunction, newValue, oldValue);
						}
					},
					onClickButton : function() {
						$scope.$setParam("choose", function(ids, codes, names) {
							var isCode = $(that).attr("iscode");
							if (isCode == "true") {
								if ($(that).textbox("getValue") == codes) {
									return;
								}
								//填入code值
								$(that).textbox("setValue", codes);
							} else {
								if ($(that).textbox("getValue") == ids) {
									return;
								}
								//填入id值
								$(that).textbox("setValue", ids);
							}
						});
						if (filter) {
							$scope.$setParam("filter", $scope.$apply(filter));
						}
						$scope.$openDialog(dialogName, {
							width : 600,
							height : 500,
							title : "选择道路",
							href : "tpls/public/choose-road-checkbox.html"
						}, filter ? true : false);
					}
				};
				if (options) {
					$.extend(defaultOptions, eval("({" + options + "})"));
				}
				$(this).textbox(defaultOptions);
				// $(this).textbox("textbox").unbind("blur").blur(function() {
					// $(this).parent().removeClass("textbox-focused");
					// if ($(this).val() === "") {
						// $(that).textbox("setValue", "");
						// //删除提示框
						// $(that).tooltip("destroy");
					// }
				// });
			});
		}

		/**
		 * 初始化多选机构树组件
		 */
		function $initOrgTreeCheckbox() {
			var textbox = $scope.$(".cy-org-checkbox");
			textbox.each(function() {
				var that = this;
				var options = $(this).attr("data-options");
				var afterChange = $(this).attr("after-change");
				var filter = $(this).attr("filter");
				var dialogName = new Date().getTime() + parseInt(Math.random() * 1000);
				var defaultOptions = {
					buttonIcon : "icon-org",
					prompt : "请选择机构",
					onChange : function(newValue, oldValue) {
						$(that).textbox("setText", $scope.$getOrgName(newValue));
						//删除旧的提示框
						$(that).tooltip("destroy");
						//添加新的提示框
						addTooltip($(that).textbox("textbox"), $scope.$getOrgName(newValue));
						if (afterChange && afterChange != "") {
							var afterChangeFunction = eval("$scope." + afterChange);
							$scope.$excute(afterChangeFunction, newValue, oldValue);
						}
					},
					onClickButton : function() {
						$scope.$setParam("choose", function(ids, codes, names) {
							var isCode = $(that).attr("iscode");
							if (isCode == "true") {
								if ($(that).textbox("getValue") == codes) {
									return;
								}
								//填入code值
								$(that).textbox("setValue", codes);
							} else {
								if ($(that).textbox("getValue") == ids) {
									return;
								}
								//填入id值
								$(that).textbox("setValue", ids);
							}
						});
						if (filter) {
							$scope.$setParam("filter", $scope.$apply(filter));
						}
						$scope.$openDialog(dialogName, {
							width : 700,
							height : 500,
							title : "选择机构",
							href : "tpls/public/choose-org-checkbox.html"
						}, filter ? true : false);
					}
				};
				if (options) {
					$.extend(defaultOptions, eval("({" + options + "})"));
				}
				$(this).textbox(defaultOptions);
				// $(this).textbox("textbox").unbind("blur").blur(function() {
					// $(this).parent().removeClass("textbox-focused");
					// if ($(this).val() === "") {
						// $(that).textbox("setValue", "");
						// //删除提示框
						// $(that).tooltip("destroy");
					// }
				// });
			});
		}

		/**
		 * 添加新的提示框
		 */
		function addTooltip(el, names) {
			var tip = $("<div></div>").css("backgroundColor", "#f3f3f3");
			names = names.split(",");
			for (var index in names) {
				var name = names[index];
				var item = $("<div></div>").text(name).css("text-align", "center");
				tip.append(item);
			}
			$(el).tooltip({
				content : tip
			});
		}

		/**
		 * 初始化单选机构树组件
		 */
		function $initOrgTreeRadio() {
			var textbox = $scope.$(".cy-org-radio");
			textbox.each(function() {
				var that = this;
				var options = $(this).attr("data-options");
				var afterChange = $(this).attr("after-change");
				var dialogName = new Date().getTime() + parseInt(Math.random() * 1000);
				var defaultOptions = {
					buttonIcon : "icon-org",
					prompt : "请选择机构",
					onChange : function(newValue, oldValue) {
						$(that).textbox("setText", $scope.$getOrgName(newValue));
						if (afterChange && afterChange != "") {
							var afterChangeFunction = eval("$scope." + afterChange);
							$scope.$excute(afterChangeFunction, newValue, oldValue);
						}
					},
					onClickButton : function() {
						$scope.$setParam("choose", function(id, code, name) {
							var isCode = $(that).attr("iscode");
							if (isCode == "true") {
								if ($(that).textbox("getValue") == code) {
									return;
								}
								//填入code值
								$(that).textbox("setValue", code);
							} else {
								if ($(that).textbox("getValue") == id) {
									return;
								}
								//填入id值
								$(that).textbox("setValue", id);
							}
						});
						$scope.$openDialog(dialogName, {
							width : 400,
							height : 500,
							title : "选择机构",
							href : "tpls/public/choose-org-radio.html"
						}, false);
					}
				};
				if (options) {
					$.extend(defaultOptions, eval("({" + options + "})"));
				}
				$(this).textbox(defaultOptions);
				// $(this).textbox("textbox").unbind("blur").blur(function() {
					// $(this).parent().removeClass("textbox-focused");
					// if ($(this).val() === "") {
						// $(that).textbox("setValue", "");
					// }
				// });
			});
		}

		/**
		 * 初始化选择品牌方法
		 */
		function $initBrandBox() {
			var boxs = $scope.$(".cy-brandbox");
			var timeout;
			boxs.each(function() {
				var box = $(this);
				var initValue = box.val();
				box.textbox({
					prompt : "请选择车辆品牌",
					onChange : function(newValue, oldValue) {
						$(this).textbox("setText", $scope.$getBrandNameByCode(newValue));
					}
				}).textbox("setText", $scope.$getBrandNameByCode(initValue));
				box.textbox("textbox").click(function(event) {
					var text = $(this);
					$("#brandbox").data("selectIndex", -1);
					$("#brandbox").data("textbox", box);
					//初始化选择框
					$("#brandbox").children().show().removeClass(".brandItem-hover");
					$("#brandbox").show().css({
						left : text.offset().left,
						top : text.offset().top + text.height() + 5
					}).scrollTop(0);
				}).unbind("blur").blur(function() {
					$(this).parent().removeClass("textbox-focused");
					if ($(this).val() === "") {
						box.textbox("setValue", "");
					}
				}).bind("input", function(event) {
					var that = this;
					clearTimeout(timeout);
					timeout = setTimeout(function() {
						var value = that.value;
						//隐藏所有的字母索引
						if (value !== "") {
							$("#brandbox").children(".letterItem").hide();
						} else {
							$("#brandbox").children(".letterItem").show();
						}

						//获取所有的品牌项
						var items = $("#brandbox").children(".brandItem");
						items.each(function() {
							$(this).show();
							var data = $(this).data("brandData");
							var flag = false;
							for (var i in data.searchIndex) {
								if (data.searchIndex[i].indexOf(value.toLowerCase()) >= 0) {
									flag = true;
									break;
								}
							}
							if (!flag) {
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
		function $initVehNumberBox() {
			var box = $scope.$(".cy-vehNumberbox");
			box.each(function() {
				var that = this;
				var options = $(this).attr("data-options");
				var defaultOptions = {
					prompt : "请输入车牌号"
				};
				if (options) {
					$.extend(defaultOptions, eval("({" + options + "})"));
				}
				$(this).textbox(defaultOptions);
				//鼠标进入事件
				$(this).textbox("textbox").click(function() {
					$("#vehNumberCookie").show().css({
						left : $(this).offset().left,
						top : $(this).offset().top + textbox.outerHeight() + 3
					}).data("vehNumber", $(this).data("vehNumber"));
				});
				var textbox = $(this).textbox("textbox");
				var vehNumber = new VehNumber($(this));
				textbox.data("vehNumber", vehNumber);
				if (isIE()) {
					//IE
					textbox.bind("keyup", function() {
						$input($(this), $(that));
					});
				} else {
					//非IE
					textbox.bind("input", function() {
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

		}

		//paeseName方法实现
		function $parseName() {
			$scope.$("[cy-name]").each(function(index, el) {
				//获取name属性值
				var name = $(this).attr("cy-name");
				var filter = $(this).attr("cy-filter");
				//找到$scope中对应的属性
				var value = $scope.$apply(name);
				if (value != undefined && value != null && filter) {
					$(this).html($scope.$apply(filter + "('" + value + "')"));
				} else if (value != undefined && value != null) {
					$(this).text(value);
				}
			});
		}

		//初始化点位选择组件的实现
		function $initChooseLocation() {
			var input = $scope.$(".cy-chooseLocation");
			input.each(function() {
				//添加按钮
				var that = this;
				var button = $("<a class='button'>选择范围</a>");
				var menu = $("<div><div class='mapChooseLocation'>地图选择</div><div class='clearLocation'>清空</div></div>");
				//清空按钮方法
				menu.find(".clearLocation").click(function(e) {
					$(that).textbox("setValue", "");
					//清空数据
					$(that).textbox("textbox").tooltip("destroy");
					//删除tip
					$(that).removeData("currentLocationData");
					//删除缓存的数据
					//删除选择点位的弹框缓存
					for (var index in $scope.$dialog) {
						if (index.startWith("chooseLocationDailog")) {
							$scope.$dialog[index].dialog("destroy");
							delete $scope.$dialog[index];
						}
					}
				});
				var dialogName = "chooseLocationDailog" + new Date().getTime();
				//地图选择按钮方法
				menu.find(".mapChooseLocation").click(function() {
					$scope.$openDialog(dialogName, {
						title : "地图选择",
						width : 800,
						height : 600,
						href : "tpls/passvehicle/track_query/choose-location.html"
					}, false);
				});
				$(this).after(button).after(menu);
				//设置easyui样式
				$(this).textbox({
					readonly : true
				});
				//定义选择方位类型隐藏框
				var areaTypeName = $(this).attr("areaTypeName");
				areaTypeName = areaTypeName ? areaTypeName : "areaTypeName";
				var areaTypeInput = $("<input type='hidden' name='" + areaTypeName + "'/>");
				$(this).after(areaTypeInput);
				//获取最大显示字数
				var size = $(this).attr("size");
				size = size ? parseInt(size) : 20;
				$(this).removeAttr("size");
				//给按钮一个弹框名称属性，用来对应不同的弹框
				button.attr("dialogname", "chooseLocationDailog" + new Date().getTime()).splitbutton({
					menu : menu
				}).css("margin-left", 5).click(function() {
					//打开选择点位界面
					$chooseLocationDialog($(that), areaTypeInput, size, $(this).attr("dialogname"));
				});
			});
		}

		//打开选择点位弹窗
		function $chooseLocationDialog(inputEl, areaTypeInput, maxSize, dialogName) {
			//定义数据展现方法
			var $setLocationData = function(data) {
				$viewLocationData(inputEl, areaTypeInput, data, maxSize);
			};
			//将显示方法添加到参数中
			$scope.$setParam("setLocationData", $setLocationData);
			//打开窗口，只在第一次加载新窗口
			var dialog = $scope.$openDialog(dialogName, {
				title : "选择范围",
				width : 700,
				height : 550,
				href : "tpls/public/choose-location.html"
			}, false);
		}

		//展现选择的点位数据
		function $viewLocationData(inputEl, areaTypeInput, data, maxSize) {
			//注入选择范围值
			areaTypeInput.val(data.areaType);
			//注入选择数据值
			inputEl.textbox("setValue", data.value.join(","));
			//注入显示值
			var displayText = data.areaText + "：" + data.text;
			//长度大于规定值，显示。。。
			if (displayText.length > maxSize) {
				displayText = displayText.substring(0, maxSize) + "...";
			}
			inputEl.textbox("setText", displayText);
			//更新tips
			var tip = $("<div class='tip'></div>");
			var texts = data.text.split(",");
			for (var index in texts) {
				var text = texts[index];
				tip.append("<div class='tipItem'>" + text + "</div>");
			}
			var textbox = inputEl.textbox("textbox");
			textbox.tooltip("destroy");
			if (texts.length !== 0) {
				textbox.tooltip({
					position : "top",
					content : tip
				});
			}
		}

		//parseForm方法实现
		function $parseForm() {
			$scope.$("form[cy-form]").each(function(index, el) {
				//获得name属性值
				var name = $(this).attr("cy-form");
				if (name) {
					//初始化name表达的对象
					$scope.$initObject(name);
					//用整个name字符串来缓存from的DOM元素
					$scope.$dom.form[name] = this;
					//获得scope中的属性值
					var values = $scope.$apply(name);
					//填充数据
					$(this).form("load", values);
					//导入form中原有的数据
					$.extend(values, $(this).getParams());
					//DOM中缓存数据
					$(this).data(name, values);
				}
			});
		}

		//parseDatagrid的实现方法
		function $parseDatagrid() {
			var datagrids = $scope.$("table[cy-datagrid]");
			if (datagrids.length === 0) {
				return;
			}
			datagrids.each(function() {
				//初始化datagrid
				var optionName = $(this).attr("cy-datagrid");
				//初始化optionName表达的对象
				$scope.$initObject(optionName);
				var options = $scope.$apply(optionName);
				if (!options) {
					//没有参数则不初始化datagrid
					return true;
				}
				var userLoadSuccess = options.onLoadSuccess;
				//给options添加初初始化方法
				options.onLoadSuccess = function(data) {
					//重新初始化代理事件
					$scope.$eventProxy();
					//重新权限验证
					$scope.$authValidate();
					//调用自定义的加载事件方法
					$scope.$excute(userLoadSuccess, data);
				};
				$(this).datagrid(options);
				//初始化查询操作
				var name = $(this).attr("cy-querydata");
				//查询数据字段名称
				if (name) {
					var url = $(this).attr("cy-url");
					url = $scope.$restRoot + url;
					//初始化data表达的对象
					$scope.$initObject(name);
					var param = $scope.$apply(name);
					//保存url到参数中
					param.url = url;
					//数据中缓存DOM
					$scope.$dom.datagrid[name] = this;
					//dom中缓存数据
					$(this).data(name, param);
					//获得分页对象并初始化分页参数到数据中
					var pager = $(this).datagrid("getPager");
					if (pager.length > 0) {
						var paginationOptions = pager.pagination("options");
						param.pageSize = paginationOptions.pageSize;
						param.pageNumber = paginationOptions.pageNumber;
						if (param.pageNumber == 0) {
							param.pageNumber = 1;
						}
						//增加分页操作
						$(this).datagrid("getPager").pagination({
							onSelectPage : function(pageNumber, pageSize) {
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
	$scope.$initObject = function(objectStr) {
		var props = objectStr.split(".");
		var pare = $scope;
		for (var index = 0; index < props.length; index++) {
			if (!pare[props[index]]) {
				pare = pare[props[index]] = {};
			} else {
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
	function search(dataStr, param, callback) {
		//$scope.$updateData(dataStr);
		var gridData = $scope.$apply(dataStr);
		$.extend(gridData, param);
		//没有数据或数据中没有url则没有查询操作
		if (!gridData || !gridData.url)
			return;
		//缓存中取出与参数对应的datagrid
		var datagrid = $scope.$dom.datagrid[dataStr];
		//显示加载中。。
		$(datagrid).datagrid("loading");
		//ajax查询
		$scope.$ajaxRequest({
			url : gridData.url,
			params : gridData,
			success : function(data) {
				$(datagrid).datagrid("loaded");
				if (data.error && data.error != null && data.error !== "") {
					alert(data.error);
				} else {
					//记录页面总数
					gridData.totalCount = data.result.total;
					//列表部分滚动到顶部
					$(datagrid).datagrid("getPanel").find(".datagrid-body").scrollTop(0);
					//查询成功 加载数据
					$(datagrid).datagrid("loadData", data.result);
					$scope.$excute(callback, data.result);
				}
			}
		});
	}

	/**
	 * 更新数据，从DOM到data
	 * @param  {[type]} name [description]
	 * @return {[type]}      [description]
	 */
	$scope.$updateData = function(name) {
		var data = $scope.$apply(name);
		var form = $scope.$dom.form[name];
		var formData = $(form).getParams();
		$.extend(data, formData);
	};

	/**
	 * 更新数据，从Data到DOM
	 * @param  {[type]} name [description]
	 * @return {[type]}      [description]
	 */
	$scope.$updateDom = function(name) {
		//更新cy-form
		$scope.$("form[cy-form='" + name + "']").each(function() {
			$(this).form("load", $scope.$apply(name));
		});
		//更新cy-name
		$scope.$("[cy-name^='" + name + "']").each(function() {
			var field = $(this).attr("cy-name");
			var filter = $(this).attr("cy-filter");
			//找到$scope中对应的属性
			var value = $scope.$apply(field);
			filter = $scope.$apply(filter);
			if (value != undefined && value != null && filter) {
				$(this).html($scope.$excute(filter, value));
			} else if (value != undefined && value != null) {
				$(this).text(value);
			}
		});
		//更新cy-foreach
		$scope.$("[cy-foreach='" + name + "']").each(function() {
			//先清空全部内容
			var content = $(this).data("content");
			$(this).children().not(content).remove();
			var name = $(this).attr("cy-foreach");
			if (name) {
				//初始化name表达的对象
				$scope.$initObject(name);
				//获得scope中的属性值
				var values = $scope.$apply(name);
				if ( values instanceof Array && values.length > 0) {
					//遍历数据
					for (var index in values) {
						//数据项
						var itemData = values[index];
						//复制DOM
						var itemContent = content.clone();
						//缓存数据
						itemContent.data("itemData", itemData).data("index", index);
						var itemEls = itemContent.find("[cy-item]");
						itemEls.each(function() {
							var itemName = $(this).attr("cy-item");
							var filter = $(this).attr("cy-filter");
							var value = itemData[itemName];
							filter = $scope.$apply(filter);
							if (value != undefined && value != null && filter) {
								$(this).html($scope.$excute(filter, value, itemData));
							} else if (value != undefined && value != null) {
								$(this).text(value);
							}
						});
						$(this).append(itemContent.show());
					}
				}
			}
		});
		//重新初始化代理事件
		$scope.$eventProxy();
		//重新权限验证
		$scope.$authValidate();
		//重新加载延迟图片
		$scope.$parseLazyImage();
	};
	/**
	 * 执行一段javascript代码
	 * @param  {[type]}
	 * @return {[type]}
	 */
	$scope.$apply = function(script) {
		var data;
		try {
			data = eval("$scope." + script);
		} catch(e) {
			data = undefined;
		}
		return data;
	};

	/**
	 * 销毁本页面
	 * @return {[type]} [description]
	 */
	$scope.$destroy = function() {
		//清空所有的弹框
		for (var index in $scope.$dialog) {
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
	$scope.$openDialog = function(name, options, isNew) {
		//默认重新加载页面
		if (isNew === undefined || isNew === null) {
			isNew = true;
		}
		options.cache = true;
		//如果给定的参数的打开新窗口，则每次都打开新窗口，否则，居中展示已经打开的窗口，如窗口不存在，则重新打开
		if (isNew) {
			//打开窗口
			openNew();
			return $scope.$dialog[name];
		} else {
			if ($scope.$dialog[name]) {
				return $scope.$dialog[name].dialog("open").dialog("center");
			} else {
				openNew();
				return $scope.$dialog[name];
			}
		}
		//根据机器的分辨率重新计算弹出框的宽度和高度
		function calculateWidthAndHeight(options) {
			//页面定义的宽度
			var w = options.width;
			//页面定义的高度
			var h = options.height;
			//当前机器的宽度
			var screenW = $(window).width();
			//当前机器的高度
			var screenH = $(window).height();
			//判断页面定义的高度与当前机器高度的大小
			if (screenW < w) {
				w = screenW * 0.8;
			}
			if (screenH < h) {
				h = screenH * 0.7;
			}
			//给弹出框赋重新计算的值
			options.width = w;
			options.height = h;
			return options;
		}

		function openNew() {
			//销毁先前的弹框
			if ($scope.$dialog[name]) {
				$scope.$dialog[name].dialog("destroy");
			}
			//判断宽高是否符合当前机器的分辨率，并重新计算宽高并赋值
			calculateWidthAndHeight(options);
			$scope.$dialog[name] = $("<div></div>").dialog(options).dialog("center");
			//如果是href方式打开，设置一些默认参数
			if (options.href && options.href !== null && options.href !== "") {
				$scope.$setParam("$params", new URL("http://" + options.href).searchParams);
				$scope.$setParam("$url", options.href);
				$scope.$setParam("$dialogObject", $scope.$dialog[name]);
				$scope.$setParam("$closeSelf", function() {
					$scope.$getDialog(name).dialog("close");
				});
				$scope.$setParam("$destroySelf", function() {
					$scope.$getDialog(name).dialog("destroy");
					delete $scope.$dialog[name];
				});
			}
		}

	};
	/**
	 * 获得窗口
	 * @param  {[type]} name 窗口唯一名称
	 * @return {[type]}      [description]
	 */
	$scope.$getDialog = function(name) {
		return $scope.$dialog[name];
	};

}