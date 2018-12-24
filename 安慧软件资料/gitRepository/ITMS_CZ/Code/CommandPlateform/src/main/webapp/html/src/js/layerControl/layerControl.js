/**
 * 图层控制加载控件 create
 * 
 * @param options
 * @constructor
 */
var LayerControl = function(options) {
	var _options = {
		dom : null, // 容器DOM对象
		// item : [], //要加载的图层项数组
		showItem : [], // 要显示的图层项数组
		notShowItem : [], // 不显示的图层项数组
		setItemImgUrl : null, // 图层设置图标URL
		onLayerClick : function(isLayerSelected, layerType) {// 点击图层事件
		},
		onLayerSetComplete : function(notShowItem)// 图层设置保存结束事件
		{
		},
		saveKey : null
	};
	// 保存参数
	this.options = $.extend(_options, options);
	this.dockWidth = null;
	this.dialog = null;
	// 初始化DOM
	this.init();
};
LayerControl.prototype = {
	// options : null,

	init : function() {
		var layerDom = $(this.options.dom);
		layerDom.html("");
		var itemContainer = $("<ul class='dock'></ul>");
		var showItem = this.options.showItem;
		if (showItem != null && showItem.length > 0) {
			// 设置dock长度
			var width = 46 * (showItem.length + 1);
			this.dockWidth = width;
			// 着个加载图层项
			for (var i = 0; i < showItem.length; i++) {
				itemContainer.append(this.createLayerItem(showItem[i]));
			}
		}
		itemContainer.append(this.createSplitLine());
		// 加载设置项
		itemContainer.append(this.createSetItem());
		layerDom.append(itemContainer);
	},
	// 创建图层项
	createLayerItem : function(layerItem) {
		var that = this;
		var li = $("<li></li>");
		var item = $("<a></a>");
		var iconUrl = layerItem.iconDefaultUrl;
		if (layerItem.isLayerDefaultShow)// 如果需要默认就加载该图层，则将其图层项图标变为选中状态
		// 直接触发点击事件
		{
			iconUrl = layerItem.iconSelectUrl;
			item.addClass("itemSelected");
			// if (layerItem.layerType.length == 2)// 设备图层
			// {
			this.options.onLayerClick(true, layerItem.layerType);
			// }
		}
		item.css("background", "url('" + iconUrl + "')");
		item.attr("title", layerItem.layerName);
		item.click(function(e) {
			var isSelected = that.layerItemClick(layerItem, e);
			that.options.onLayerClick(isSelected, layerItem.layerType);
		});
		item.hover(function(e) {
			$(e.currentTarget).css("background",
					"url('" + layerItem.iconSelectUrl + "')");
		}, function(e) {
			if (!$(e.currentTarget).hasClass("itemSelected")) {
				$(e.currentTarget).css("background",
						"url('" + layerItem.iconDefaultUrl + "')");
			}
		});
		li.append(item);
		// 判断是否有子图层
		if (this.hasSubItem(layerItem)) {
			var subItemUl = $("<ul></ul>");
			for (var i = 0; i < layerItem.childItems.length; i++) {
				var subLi = this.createLayerItem(layerItem.childItems[i]);
				subItemUl.append(subLi);
			}
			li.append(subItemUl);
		}
		return li;
	},

	hasSubItem : function(layerItem) {
		if (layerItem.childItems != null && layerItem.childItems.length > 0) {
			return true;
		}
		return false;
	},

	// 图层项点击事件
	layerItemClick : function(layerItem, event) {
		var isSelected = false;
		var clickItem = $(event.currentTarget);
		isSelected = this.changeLayerItemStatus(clickItem, layerItem);
		var parentLi = $(clickItem.parent().get(0));
		if (this.hasSubItem(layerItem)) {// 如果有子图层，说明当前点击的是父图层，则子图层也要相应的改变，父图层选择，子图层都选中，父图层未选中，子图层都不选中
			// var parentLi=clickItem.parent().get(0);
			var ul = parentLi.children("ul");
			var lis = $(ul[0]).children("li");
			for (var i = 0; i < lis.length; i++) {
				var a = $(lis[i]).children("a")[0];
				this.changeLayerItemStatusByStatus($(a), layerItem.childItems[i],isSelected);
			}
		} else if (layerItem.layerType.length > 2) {// 当前点击的是子图层，则父图层也要相应变化，子图层有一个点击，则父图层选中

			var subbg = layerItem.iconDefaultUrl;
			var subbgArry = subbg.split(".");
			var parentBgLeft = subbgArry[0].substr(0, subbgArry[0].length-1);
			var parentLayerItemA = $(parentLi.parent()).prev();
			var parentBg = "";
		   // debugger;
			if (isSelected)// 如果是选中，则判断父图层原先是否处于选中状态，如果是非选中则选中
			{
				if (!$(parentLayerItemA).hasClass("itemSelected")) {

					parentBg = parentBgLeft + "_1." + subbgArry[1];
					$(parentLayerItemA).css("background",
							"url('" + parentBg + "')");
					$(parentLayerItemA).addClass("itemSelected");
				}
			} else// 如果是取消选中，则判断其他兄弟节点有没有选中的，如果都没有，则将其父节点也取消选中
			{
				var siblNodes = parentLi.siblings();
				var isAllNoSelected = true;
				for (var s = 0; s < siblNodes.length; s++) {
					var siblNode = siblNodes[s];
					var childA = $(siblNode).children("a")[0];
					if ($(childA).hasClass("itemSelected")) {
						isAllNoSelected = false;
					}
				}
				if (isAllNoSelected) {
					parentBg = parentBgLeft + "." + subbgArry[1];
					$(parentLayerItemA).css("background",
							"url('" + parentBg + "')");
					$(parentLayerItemA).removeClass("itemSelected");
				}
			}
		}
		// if (clickItem.hasClass("itemSelected")) {// 已经点击过了，则去除点击状态
		// clickItem.css("background", "url('" + layerItem.iconDefaultUrl
		// + "')");
		// clickItem.removeClass("itemSelected");
		//
		// } else {// 还没点击过，则让其处于点击状态
		// isSelected = true;
		// clickItem.css("background", "url('" + layerItem.iconSelectUrl
		// + "')");
		// clickItem.addClass("itemSelected");
		//
		// }
		return isSelected;
	},
	changeLayerItemStatusByStatus : function(clickItem, layerItem, isSelected) {
		if (isSelected) {
			clickItem.css("background", "url('" + layerItem.iconSelectUrl
					+ "')");
			clickItem.addClass("itemSelected");
		} else {
			clickItem.css("background", "url('" + layerItem.iconDefaultUrl
					+ "')");
			clickItem.removeClass("itemSelected");
		}
	},
	changeLayerItemStatus : function(clickItem, layerItem) {
		var isSelected = false;
		if (clickItem.hasClass("itemSelected")) {// 已经点击过了，则去除点击状态
			clickItem.css("background", "url('" + layerItem.iconDefaultUrl
					+ "')");
			clickItem.removeClass("itemSelected");

		} else {// 还没点击过，则让其处于点击状态
			isSelected = true;
			clickItem.css("background", "url('" + layerItem.iconSelectUrl
					+ "')");
			clickItem.addClass("itemSelected");

		}
		return isSelected;
	},
	createSplitLine : function() {
		// 创建分割线
		var setLi = $("<li></li>");
		var setA = $("<a></a>");
		setLi.append(setA);
		return setLi;
	},
	// 创建设置按钮
	createSetItem : function() {
		// 创建设置图标
		var setLi = $("<li></li>");
		var setA = $("<a title='设置'></a>");
		setA.css("background", "url('" + this.options.setItemImgUrl + "')");
		var that = this;
		setA.click(function() {
			that.showSet();
		});
		setLi.append(setA);
		return setLi;
	},
	// 显示设置弹出框
	showSet : function() {
		var that = this;
		$rootScope.$setParam("showItem", this.options.showItem.slice(0));
		$rootScope.$setParam("notShowItem", this.options.notShowItem.slice(0));
		$rootScope.$setParam("layerSetDialogClose", function(isSave, showRows,
				notShowRows) {
			that.layerSetDialogClose(isSave, showRows, notShowRows);
		});
		this.dialog = $("<div></div>").dialog({
			title : "图层设置",
			width : 560,
			height : 500,
			href : "js/layerControl/layerSetWnd.html",
			cache : false,
			onClose : function() {
				$(this).dialog("destroy");
			}
		}).dialog("center");
	},
	layerSetDialogClose : function(isSave, showRows, notShowRows) {
		if (isSave) {
			// 更新图层条
			this.options.showItem = null;
			this.options.showItem = showRows.slice(0);
			this.options.notShowItem = null;
			this.options.notShowItem = notShowRows.slice(0);
			this.init();
			this.options.onLayerSetComplete(this.options.notShowItem,
					this.options.showItem);
			// 将用户图层设置保存到本地浏览器数据库中
			this.savePersonalSet();
		}
		this.dialog.dialog("destroy");
	},
	savePersonalSet : function() {
		var key = this.options.saveKey;
		// 先清除旧的保存信息
		localStorage.removeItem(key + "-showItem");
		localStorage.removeItem(key + "-notShowItem");
		localStorage.setItem(key + "-showItem", this
				.savePersonalSetContent(this.options.showItem));
		localStorage.setItem(key + "-notShowItem", this
				.savePersonalSetContent(this.options.notShowItem));
	},
	savePersonalSetContent : function(items) {
		var storeItems = {};
		for (var i = 0; i < items.length; i++) {
			storeItems[items[i].layerType] = new storeLayerItem(
					items[i].layerType, items[i].layerName,
					items[i].isLayerDefaultShow);
		}
		return JSON.stringify(storeItems);
	}
};

function storeLayerItem(layerType, layerName, isLayerDefaultShow) {
	this.layerType = layerType;
	this.layerName = layerName;
	this.isDefaultShow = isLayerDefaultShow;
}

/**
 * 图层项对象
 * 
 * @param layerType
 * @param layerId
 * @param layerName
 * @param iconDefaultUrl
 * @param iconSelectUrl
 * @param isLayerDefaultShow
 * @constructor
 */
function LayerItem(layerType, layerId, layerName, iconDefaultUrl,
		iconSelectUrl, isLayerDefaultShow, childItems) {
	this.layerType = layerType;
	this.layerId = layerId;
	this.layerName = layerName;
	this.iconDefaultUrl = iconDefaultUrl;
	this.iconSelectUrl = iconSelectUrl;
	this.isLayerDefaultShow = isLayerDefaultShow;
	this.childItems = childItems || null;
}
