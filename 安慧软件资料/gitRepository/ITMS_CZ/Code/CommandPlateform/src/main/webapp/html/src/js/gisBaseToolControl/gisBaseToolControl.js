/**
 * 
 */
var iconRootPath_gisBaseToolControl = 'js/gisBaseToolControl/';
// 用到的图标所在工程的根目录

var toolItemType = {
	zoomIn : {
		id : "zoomIn",
		imgUrl : "images/zoomIn_on.png",
		title : '放大'
	},
	zoomOut : {
		id : "zoomOut",
		imgUrl : "images/zoomOut_on.png",
		title : '缩小'
	},
	screen : {
		id : "screen",
		imgUrl : "images/screen.png",
		title : '全图显示'
	},
	dragMap : {
		id : "dragMap",
		imgUrl : "images/pan_on.png",
		title : '平移地图'
	},
	navPre : {
		id : "navPre",
		imgUrl : "images/view_previous_on.png",
		title : '前一视图'
	},
	navNext : {
		id : "navNext",
		imgUrl : "images/view_next_on.png",
		title : '后一视图'
	},
	measureLength : {
		id : "measureLength",
		imgUrl : "images/measure_lenght_on.png",
		title : '测量距离'
	},
	measureArea : {
		id : "measureArea",
		imgUrl : "images/measure_area_on.png",
		title : '测量面积'
	}

};

var GisBaseToolControl = function(option) {
	var _option = {
		mapObj : null, // 地图基本工具栏附加到的地图对象
		dom : null, // 容器DOM对象
		customToolItem : null, // 自定义要现实的工具项，其值为toolItemType中的值
		onItemClick : function(isActive, itemId)// 按钮点击事件
		{

		}
	};
	this.option = $.extend(_option, option);

	this.nav = null;
	this.measureResultDiv = null;
	this.itemControl = {};
	this.init();
};

GisBaseToolControl.prototype = {
	// nav : null,
	// measureResultDiv : null,
	// itemControl : {},
	measureOptions : function() {
		var sketchSymbolizers = {
			"Point" : {
				pointRadius : 4,
				graphicName : "square",
				fillColor : "white",
				fillOpacity : 1,
				strokeWidth : 1,
				strokeOpacity : 1,
				strokeColor : "#333333"
			},
			"Line" : {
				strokeWidth : 2,
				strokeOpacity : 1,
				strokeColor : "#666666",
				strokeDashstyle : "solid"
			},
			"Polygon" : {
				strokeWidth : 2,
				strokeOpacity : 1,
				strokeColor : "#666666",
				fillColor : "white",
				fillOpacity : 0.3
			}
		};
		var style = new OpenLayers.Style();
		style.addRules([ new OpenLayers.Rule({
			symbolizer : sketchSymbolizers
		}) ]);
		var styleMap = new OpenLayers.StyleMap({
			"default" : style
		});

		var options = {
			// title: "测量",
			handlerOptions : {
				// style : "default", // this forces default render intent
				layerOptions : {
					styleMap : styleMap
				}
			},
			persist : true
		};
		return options;
	},
	getNavCtrl : function() {
		var nav = new OpenLayers.Control.NavigationHistory();
		return nav;
	},

	init : function() {
		// 加载地图控件
		this.addControlToMap();
		var toolDom = $(this.option.dom);
		toolDom.html("");
		var gisBaseToolDiv = $("<div class='gisBaseToolDiv'></div>");

		if (this.option.customToolItem == null
				|| this.option.customToolItem.lenght == 0)// 没有自定义要显示的tool项，则显示所有
		{
			for ( var ti in toolItemType) {
				var item = this.createItem(toolItemType[ti]);
				gisBaseToolDiv.append(item);
			}
		} else {
			for ( var c in this.option.customToolItem) {
				var item = this.createItem(this.option.customToolItem[c]);
				gisBaseToolDiv.append(item);
			}
		}
		toolDom.append(gisBaseToolDiv);
	},
	createItem : function(item) {
		var that = this;
		var img = $("<img />");
		img.attr("src", iconRootPath_gisBaseToolControl + item.imgUrl);
		img.attr("title", item.title);
		img.click(function(e) {
			that.triggerOperate(item, e);
			var isActive = true;
			// todo
			that.option.onItemClick(isActive, item.id);
		});
		return img;
	},

	triggerOperate : function(ele, event) {
		if (ele.id == "zoomIn") {
			this.option.mapObj.zoomIn();
		} else if (ele.id == "zoomOut") {
			this.option.mapObj.zoomOut();
		} else if (ele.id == "screen") {
			this.option.mapObj.zoomToMaxExtent();
		} else if (ele.id == "navNext") {
			this.nav.nextTrigger();
		} else if (ele.id == "navPre") {
			this.nav.previousTrigger();
		} //else {
			for ( var ctl in this.itemControl) {
				var control = this.itemControl[ctl];
				if (ele.id == ctl) {
					if (control.active) {
						control.deactivate();
					} else {
						control.activate();
					}
				} else {
					control.deactivate();
				}
			}
		//}
	},
	addControlToMap : function() {
		var that = this;
		var zoomIn = new OpenLayers.Control.ZoomBox();
		// out:true改变zoombox为缩小功能
		var zoomOut = new OpenLayers.Control.ZoomBox({
			out : true
		});
		this.nav = new OpenLayers.Control.NavigationHistory();
		var dragMap = new OpenLayers.Control.DragPan();
		// 添加测量距离控件
		var measureLength = new OpenLayers.Control.Measure(
				OpenLayers.Handler.Path, this.measureOptions());
		// 添加测量面积控件
		var measureArea = new OpenLayers.Control.Measure(
				OpenLayers.Handler.Polygon, this.measureOptions());

		//this.itemControl.zoomIn = zoomIn;
		//this.itemControl.zoomOut = zoomOut;
		this.itemControl.dragMap = dragMap;
		//this.itemControl.nav = nav;

		this.itemControl.measureArea = measureArea;

		this.itemControl.measureLength = measureLength;

		for ( var key in this.itemControl) {
			var contr = this.itemControl[key];
			if (key == "measureLength" || key == "measureArea") {
				// 当点击测量距离图标时，显示结果div
				contr.events.on({
					"activate" : function() {
						// that.createResultDiv();
					},
					"deactivate" : function() {
						that.closeResultDiv();
					}
				});
				// 指定测量距离时执行的函数handleMeasurements
				contr.events.on({
					"measure" : function(event) {
						that.handleMeasurements(event);
					},
					"measurepartial" : function(event) {
						that.handleMeasurements(event);
					}
				});

			}
			this.option.mapObj.addControl(contr);
		}
		// 前后视图控件不存在失效，所以无需加入到this.itemControl中
		this.option.mapObj.addControl(this.nav);

	},
	createResultDiv : function() {
		this.measureResultDiv = $('<div class="gisBaseToolMesureDiv"></div>');
		$(this.option.dom).append(this.measureResultDiv);
	},
	closeResultDiv : function() {
		this.measureResultDiv.remove();
		this.measureResultDiv = null;
	},
	handleMeasurements : function(event) {
		var geometry = event.geometry;
		var units = event.units;
		var order = event.order;
		var measure = event.measure;
		var out = "";
		if (order == 1) {
			out += "测量结果： " + measure.toFixed(3) + " " + units;
		} else {
			out += "测量结果： " + measure.toFixed(3) + " " + units + "<sup>2</sup>";
		}
		if (this.measureResultDiv == null) {
			this.createResultDiv();
		}
		this.measureResultDiv.html('');
		this.measureResultDiv.html(out);
	}
};
