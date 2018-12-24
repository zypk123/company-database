/**
 * /** // 主题: 地图公共js文件 // 描述: 地图地图加载类 // 作者: 陈志英 // 修改日志: // 日期 修改人 版本 修改原因及内容 //
 * 2015.7.29 陈志英
 * 
 * @version 1.0 创建
 */
// 地图切片一些初始配置
// 切片的数据源类型，目前取值为：ARCGIS、GOOGLEMAP中一种
var tileSourceType = "ARCGIS";
// 切片URL
var mapTileUrl = "http://192.168.10.186/yun4Map/";
// "http://192.168.10.240/chishui2/";
/* ==发布的切片地图范围配置-START==== */
/*
 * 对于是googleMap的切片切片范围取值，直接从切片路径下的conf.xml的配置文件拷贝 <Left、Bottom、Right、Top>这几个值；
 * 对于arcigs的切片切片范围取值，直接从切片路径下的conf.cdi文件拷贝<XMin、YMin、XMax、YMax>这几个值；
 */
// 切片地图左下角经度值
var mapTileLeftLon = 97.087597650000006;
// 105.35334671875;
// 切片地图左下角纬度值
var mapTileBottomLat = 20.737943899999991;
// 28.0157713763807;
// 切片地图右上角经度值
var mapTileRightLon = 106.63032535000004;
// 106.45197953125;
// 切片地图右上角纬度值
var mapTileTopLat = 29.6566361;
// 28.9812820367225;
/* ==发布的切片地图范围配置-END================================ */

// 切片地图的最小等级
var mapTileMinLevel = 0;
// 11;
// 切片地图最大等级
var mapTileMaxLevel = 5;
// 16;

// 地图聚合等级，小于等于这个等级的做聚合
var clusterLevel = 3;
// 13;

/* ==地首次初始显示范围配置（要根据各地方实际情况调节下面值）-START======= */
var firstLeftLon = 95.2096529605908;
// 105.59333;
// 首次初始显示范围左下角经度值
var firstBottomLat = 22.1995384384504;
// 28.57996;
// 首次初始显示范围左下角纬度值
var firstRightLon = 108.237201967512;
// 105.80928;
// 首次初始显示范围右上角经度值
var firstTopLat = 28.4932127988715;
// 28.70621;
// 首次初始显示范围右上角纬度值
/* ==首次初始显示范围配置-END================================ */
// 常量请勿修改
var google_resolution_const = 156543.033928;
/*
 * 切片的投影，arcgis切片默认取值为："EPSG:4326"； googlemap默认取值为："EPSG:900913"；
 */
var mapProjection = "EPSG:4326";
// "EPSG:900913"
/*
 * 地图切片每个等级的解析度，arcgis切片解析度取值，切片路径下的conf.xml文件拷贝,填入下面数组中；
 * googlemap切片则要将mapResolution右边中括号值清空
 */
var mapResolution = [ 0.0190356880466422, 0.00951784402332112,
		0.00475892201166056, 0.00237946100583028, 0.00118973050291514,
		0.00059486525145757 ];

function CYMap(mapId, _options) {
	// 存放一些属性
	this.options = {
		// 地图移动回调函数
		mapMoveendCallBack : function() {
		},
		locationLayer : null,
		isShowOverview : true
	};
	$.extend(this.options, _options);
	this.mapId = mapId;
	this.map = null;
	this.controls = null;
	this.initMap();
}

// geometry.transform(this.map.displayProjection,
// this.map.getProjectionObject());
CYMap.prototype = {
	initMap : function() {
		if (tileSourceType == "GOOGLEMAP") {
			mapResolution.length = 0;
			for (var i = mapTileMinLevel; i <= mapTileMaxLevel; i++) {
				mapResolution.push(resolution / Math.pow(2, i));
			}
		}
		this.map = new OpenLayers.Map(this.mapId, {
			projection : mapProjection,
			displayProjection : "EPSG:4326",
			// maxExtent : maxExtent,
			resolutions : mapResolution,
			controls : []
		// 删除多余的放大缩小工具条
		});
		var maxExtent = new OpenLayers.Bounds(mapTileLeftLon, mapTileBottomLat,
				mapTileRightLon, mapTileTopLat);
		maxExtent.transform(this.map.displayProjection, this.map
				.getProjectionObject());
		this.map.maxExtent = maxExtent;
		var layer;
		if (tileSourceType == "GOOGLEMAP") {
			layer = new OpenLayers.Layer.LocalGoogleMap("baselayer", mapTileUrl);
		} else if (tileSourceType == "ARCGIS") {
			layer = new OpenLayers.Layer.ArcGISTileMapLayer("baselayer",
					mapTileUrl, {
						isBaseLayer : true
					});
		}
		this.map.addLayer(layer);
		if (this.options.locationLayer == null)// 如果没有传入定位图层，则建立默认的定位图层
		{
			this.options.locationLayer = new OpenLayers.Layer.Vector(
					"locationLayer");
			this.map.addLayer(this.options.locationLayer);
		}
		this.map.addControl(new OpenLayers.Control.Navigation());

		this.map.addControl(new OpenLayers.Control.MousePosition({
			displayClass : "mousePositionCss"
		}));
		if (this.options.isShowOverview == true) {
			// 鹰眼控件初始化参数
			var overviewOptions = {
				minRectSize : 10,
				autoPan : true,
				// resolutionFactor : 10,
				size : new OpenLayers.Size(182, 100),
				mapOptions : {
					maxExtent : new OpenLayers.Bounds(mapTileLeftLon,
							mapTileBottomLat, mapTileRightLon, mapTileTopLat),
					resolutions : mapResolution,
				},
				layers : [ new OpenLayers.Layer.ArcGISTileMapLayer(
						"overviewlay", mapTileUrl) ],
				maximized : false,
				maximizeTitle : '展开鹰眼',
				minimizeTitle : '隐藏鹰眼'
			};
			var overviewCtl = new OpenLayers.Control.OverviewMap(
					overviewOptions);
			this.map.addControl(overviewCtl);
			// 自定义鹰眼样式
			// this.map.addControl(new
			// OpenLayers.Control.LTOverviewMap(overviewOptions));
		}
		// 给map注册事件
		this.map.events.register("moveend", null,
				this.options.mapMoveendCallBack);

		var initExtent = new OpenLayers.Bounds(firstLeftLon, firstBottomLat,
				firstRightLon, firstTopLat);
		initExtent.transform(this.map.displayProjection, this.map
				.getProjectionObject());
		this.map.zoomToExtent(initExtent, true);

	},
	/**
	 * 添加定位地物
	 */
	addLocationFeature : function(geometry) {
		this.options.locationLayer.removeAllFeatures();
		var style = null;
		if (geometry.CLASS_NAME == "OpenLayers.Geometry.Point") {
			style = {
				externalGraphic : "themes/default/images/location.png",
				graphicWidth : 36,
				graphicHeight : 36
			};
		} else if (geometry.CLASS_NAME == "OpenLayers.Geometry.LineString") {
			style = {
				strokeWidth : 4,
				strokeColor : "cyan",
				fillColor : "cyan"
			};
		} else {// 其他
			style = {
				// pointRadius : 10,
				strokeWidth : 4,
				strokeColor : "cyan",
				fillColor : "orange"
			};
		}
		var attr = {
			"layerType" : "location"
		};
		var feature = new OpenLayers.Feature.Vector(geometry, attr, style);
		this.options.locationLayer.addFeatures(feature);
		return feature;
	},
	/**
	 * 居中显示地物
	 */
	centerFeature : function(geometry) {
		if (geometry.CLASS_NAME == "OpenLayers.Geometry.Point") {
			this.map.panTo(new OpenLayers.LonLat(geometry.x, geometry.y));
		} else {
			var bounds = geometry.getBounds();
			var lonlat = bounds.getCenterLonLat();
			var zoom = this.map.getZoomForExtent(bounds, false) - 1;
			this.map.setCenter(lonlat, zoom);
		}
	},
	/**
	 * 定位地物：5秒后定位图标自动消失 对于点状地物，直接居中定位，不改变视图当前的比例尺；
	 * 对于线状、面状地物居中点位，同时将当前视图的比例尺改为该地物的范围所在的比例尺 默认是添加定位地物
	 */
	location : function(geometry, isNeedAddLocFeature) {
		var that = this;
		geometry.transform(this.map.displayProjection, this.map
				.getProjectionObject());
		if (isNeedAddLocFeature != false) {
			var feature = this.addLocationFeature(geometry);
			var timeOutId = setTimeout(function() {
				that.options.locationLayer.removeFeatures(feature);
				clearTimeout(timeOutId);
			}, 5000);
		}
		this.centerFeature(geometry);
	}
};

/*------------------------------公共方法---------------------------------*/
function lon2mercator(lon) {
	return lon * 20037508.34 / 180;
}

function lat2mercator(lat) {
	var Y = Math.log(Math.tan((90 + lat) * Math.PI / 360)) / (Math.PI / 180);
	return Y * 20037508.34 / 180;
}

// Google地图纠偏算法 bygyf
var pi = 3.14159265358979324;
var a = 6378245.0;
var ee = 0.00669342162296594323;

function transformFromWktToGeometry(wkt) {

	var wkt_c = new OpenLayers.Format.WKT();
	var geometry = wkt_c.read(wkt).geometry.clone();

	if (geometry.CLASS_NAME == "OpenLayers.Geometry.Point") {
		return transformPoint(geometry);
	} else if (geometry.CLASS_NAME == "OpenLayers.Geometry.LineString") {
		var old = geometry.getVertices();
		var newPoints = [];
		for ( var index in old) {
			var newP = transformPoint(old[index]);
			newPoints.push(newP);
		}
		return new OpenLayers.Geometry.LineString(newPoints);
	} else {// 其他

	}
}

/**
 * 根据wkt创建一个 geometry
 */
function createGeoFromWkt(wkt) {
	var wkt_c = new OpenLayers.Format.WKT();
	var geometry = wkt_c.read(wkt).geometry.clone();
	return geometry;
}

/**
 * 纠偏一个geometry，纠偏后的点适合google地图
 */
function rectifyGeometryAdaptGoogle(geometry) {
	var oldPoint = geometry.getVertices();
	var newPoints = [];
	for (var i = 0; i < oldPoint.length; i++) {
		var newP = transformPoint(oldPoint[i]);
		newPoints.push(newP);
	}
	if (geometry.CLASS_NAME == "OpenLayers.Geometry.Point") {
		return newPoints[0];
	} else if (geometry.CLASS_NAME == "OpenLayers.Geometry.LineString") {
		return new OpenLayers.Geometry.LineString(newPoints);
	} else if (geometry.CLASS_NAME == "OpenLayers.Geometry.Polygon") {
		return new OpenLayers.Geometry.Polygon(newPoints);
	} else {// 目前常用的就是上面三种类型地物，其他类型暂不考虑
		return new OpenLayers.Geometry.Collection(newPoints);
	}
}

function transformPoint(point) {
	var ll = [ point.y, point.x ];
	if (tileSourceType == "GOOGLEMAP") {
		ll = transform(point.y, point.x);
	}
	return new OpenLayers.Geometry.Point(ll[1], ll[0]);
}

/*
 * ===================84经纬度点，在google地图上显示需要进行纠偏，算法如下
 * start=======================
 */
function transform(wgLat, wgLon) {
	var latlng = [];
	if (outOfChina(wgLat, wgLon)) {
		latlng[0] = wgLat;
		latlng[1] = wgLon;
		return latlng;
	}
	var dLat = transformLat(wgLon - 105.0, wgLat - 35.0);
	var dLon = transformLon(wgLon - 105.0, wgLat - 35.0);
	var radLat = wgLat / 180.0 * pi;
	var magic = Math.sin(radLat);
	magic = 1 - ee * magic * magic;
	var sqrtMagic = Math.sqrt(magic);
	dLat = (dLat * 180.0) / ((a * (1 - ee)) / (magic * sqrtMagic) * pi);
	dLon = (dLon * 180.0) / (a / sqrtMagic * Math.cos(radLat) * pi);
	latlng[0] = wgLat + dLat;
	latlng[1] = wgLon + dLon;
	return latlng;
}

function outOfChina(lat, lon) {
	if (lon < 72.004 || lon > 137.8347)
		return true;
	if (lat < 0.8293 || lat > 55.8271)
		return true;
	return false;
}

/**
 * 判断设置的经纬度是否在地图范围内
 */
function checkMap(lat, lon) {
	if ((lat >= mapTileBottomLat && lat <= mapTileTopLat)
			&& (lon >= mapTileLeftLon && lon <= mapTileRightLon)) {
		return true;
	} else {
		return false;
	}
}

function transformLat(x, y) {
	var ret = -100.0 + 2.0 * x + 3.0 * y + 0.2 * y * y + 0.1 * x * y + 0.2
			* Math.sqrt(Math.abs(x));
	ret += (20.0 * Math.sin(6.0 * x * pi) + 20.0 * Math.sin(2.0 * x * pi)) * 2.0 / 3.0;
	ret += (20.0 * Math.sin(y * pi) + 40.0 * Math.sin(y / 3.0 * pi)) * 2.0 / 3.0;
	ret += (160.0 * Math.sin(y / 12.0 * pi) + 320 * Math.sin(y * pi / 30.0)) * 2.0 / 3.0;
	return ret;
}

function transformLon(x, y) {
	var ret = 300.0 + x + 2.0 * y + 0.1 * x * x + 0.1 * x * y + 0.1
			* Math.sqrt(Math.abs(x));
	ret += (20.0 * Math.sin(6.0 * x * pi) + 20.0 * Math.sin(2.0 * x * pi)) * 2.0 / 3.0;
	ret += (20.0 * Math.sin(x * pi) + 40.0 * Math.sin(x / 3.0 * pi)) * 2.0 / 3.0;
	ret += (150.0 * Math.sin(x / 12.0 * pi) + 300.0 * Math.sin(x / 30.0 * pi)) * 2.0 / 3.0;
	return ret;
}

/* ===================84经纬度点，在google地图上显示需要进行纠偏，算法如下 end======================= */
/**
 * Class: OpenLayers.Strategy.AttributeCluster Strategy for vector feature
 * clustering based on feature attributes.
 * 
 * Inherits from: - <OpenLayers.Strategy.Cluster>
 */
OpenLayers.Strategy.AttributeCluster = OpenLayers.Class(
		OpenLayers.Strategy.Cluster, {
			/**
			 * the attribute to use for comparison
			 */
			attribute : null,
			/**
			 * Method: shouldCluster Determine whether to include a feature in a
			 * given cluster.
			 * 
			 * Parameters: cluster - {<OpenLayers.Feature.Vector>} A cluster.
			 * feature - {<OpenLayers.Feature.Vector>} A feature.
			 * 
			 * Returns: {Boolean} The feature should be included in the cluster.
			 */
			shouldCluster : function(cluster, feature) {
				var cc_attrval = cluster.cluster[0].attributes[this.attribute];
				var fc_attrval = feature.attributes[this.attribute];
				var superProto = OpenLayers.Strategy.Cluster.prototype;
				return cc_attrval === fc_attrval
						&& superProto.shouldCluster.apply(this, arguments);
			},
			CLASS_NAME : "OpenLayers.Strategy.AttributeCluster"
		});

/* ===================偏移线要素图元，算法如下 start======================= */
// / <summary>
// / 求起始点p1的偏移点；线的方向是p1-p2; 向右偏移
// / </summary>
// / <param name="p1"> 起点</param>
// / <param name="p2">结束点</param>
// / <param name="iPixelOffset">偏移量</param>
// / <returns>返回起点偏移后的点</returns>
function GetFirstPointBufferCorrdinate(p1, p2, iPixelOffset) {
	if (p1.x == p2.x) {
		if (p1.y < p2.y)
			return new OpenLayers.Geometry.Point(p1.x - iPixelOffset, p1.y);
		else
			return new OpenLayers.Geometry.Point(p1.x + iPixelOffset, p1.y);
	} else {
		var K = (p2.y - p1.y) / (p2.x - p1.x);
		var angle = Math.atan(Math.abs(K));

		var deltX = iPixelOffset * Math.sin(angle);
		var deltY = iPixelOffset * Math.cos(angle);

		var vectorX = p2.x - p1.x;

		if (K > 0) {
			if (vectorX > 0)
				deltX = -deltX;
			else
				deltY = -deltY;
		} else {
			if (vectorX < 0) {
				deltX = -deltX;
				deltY = -deltY;
			}
		}

		return new OpenLayers.Geometry.Point(p1.x + deltX, p1.y + deltY);

	}

}

// / <summary>
// / 求结束点p2的偏移点; 线的方向是p1-p2; 向右偏移
// / </summary>
// / <param name="p1"> 起点</param>
// / <param name="p2">结束点</param>
// / <param name="iPixelOffset">偏移量</param>
// / <returns>返回结束点偏移后的点</returns>
function GetLastPointBufferCorrdinate(p1, p2, iPixelOffset) {

	if (p1.x == p2.x) {
		if (p1.y < p2.y)
			return new OpenLayers.Geometry.Point(p2.x - iPixelOffset, p2.y);
		else
			return new OpenLayers.Geometry.Point(p2.x + iPixelOffset, p2.y);
	} else {
		var K = (p1.y - p2.y) / (p1.x - p2.x);
		var angle = Math.atan(Math.abs(K));

		var deltX = iPixelOffset * Math.sin(angle);
		var deltY = iPixelOffset * Math.cos(angle);

		var vectorX = p2.x - p1.x;
		if (K > 0) {
			if (vectorX > 0)
				deltX = -deltX;
			else
				deltY = -deltY;
		} else {
			if (vectorX < 0) {
				deltX = -deltX;
				deltY = -deltY;
			}
		}
		return new OpenLayers.Geometry.Point(p2.x + deltX, p2.y + deltY);

	}
}

// / <summary>
// / 获得给定三点中中间点的缓冲后的坐标，取左侧点
// / </summary>
// / <param name="p1">给定第一点</param>
// / <param name="p2">给定第二点</param>
// / <param name="p3">给定第三点</param>
// / <param name="distance">缓冲半径</param>
// / <returns>返回给定三点中中间点的缓冲后的坐标</returns>
function GetMiddlePointBufferCorrdinate(p1, p2, p3, distance) {
	var returnPoint;

	// 下面所做的处理是：在多段线的相邻两断线段的斜率只有一个存在存在的情况
	var K;

	if (p1.x == p2.x)
		K = (p2.y - p3.y) / (p2.x - p3.x);

	if (p2.x == p3.x)
		K = (p1.y - p2.y) / (p1.x - p2.x);

	if (K) {
		var angle1 = Math.atan(Math.abs(K));
		var angle2 = 0;
		var deltaX = 0;
		var deltaY = 0;

		var vectorX1 = p2.x - p1.x;
		var vectorY1 = p2.y - p1.y;
		var vectorX2 = p3.x - p2.x;
		var vectorY2 = p3.y - p2.y;

		if (((vectorX1 * vectorY2 < 0 || vectorX2 * vectorY1 < 0) && K >= 0)
				|| ((vectorX1 * vectorY2 > 0 || vectorX2 * vectorY1 > 0) && K <= 0)) {
			angle2 = 0.25 * Math.PI + 0.5 * angle1;
			deltaX = distance;
			deltaY = distance * Math.tan(angle2);
		}
		if (((vectorX1 * vectorY2 > 0 || vectorX2 * vectorY1 > 0) && K > 0)
				|| ((vectorX1 * vectorY2 < 0 || vectorX2 * vectorY1 < 0) && K < 0)) {
			angle2 = 0.25 * Math.PI - 0.5 * angle1;
			deltaX = distance;
			deltaY = distance * Math.tan(angle2);
		}

		if (vectorY1 < 0 && vectorX2 < 0 || vectorX1 < 0 && vectorY2 < 0)
			deltaY = -deltaY;
		if (vectorY1 > 0 && vectorX2 > 0 || vectorX1 > 0 && vectorY2 > 0)
			deltaX = -deltaX;
		if (vectorX1 < 0 && vectorY2 > 0 && vectorX2 == 0) {
			deltaX = -deltaX;
			deltaY = -deltaY;
		}
		if (vectorY1 > 0 && vectorX2 < 0 && vectorX1 == 0) {
			deltaX = -deltaX;
			deltaY = -deltaY;
		}

		returnPoint = new OpenLayers.Geometry.Point(p2.x + deltaX, p2.y
				+ deltaY);
	}

	// 下面处理的是：在多段线的相邻两断线段斜率都存在的情况
	if (p1.x != p2.x && p2.x != p3.x) {
		var vectorX1 = p2.x - p1.x;
		var vectorX2 = p3.x - p2.x;
		var K1 = (p1.y - p2.y) / (p1.x - p2.x);
		var K2 = (p2.y - p3.y) / (p2.x - p3.x);

		var a1 = Math.atan(Math.abs(K1));
		var a2 = Math.atan(Math.abs(K2));

		// 下面处理的是：在多段线的相邻两断线段斜率都存在且同号的情况
		if (K1 * K2 >= 0) {

			var deltaX0 = 0;
			var deltaY0 = 0;

			if (vectorX1 * vectorX2 > 0) {
				var a3 = 0.5 * Math.abs(Math.PI - a1 - a2);

				var a4 = a3 + ((a1 > a2) ? a2 : a1);
				var s = distance / Math.sin(a4);

				deltaX0 = s * Math.cos(a3);
				deltaY0 = s * Math.sin(a3);
			} else {
				var a3 = 0.5 * Math.abs(a1 - a2);

				var a4 = a3 + ((a1 > a2) ? a2 : a1);
				var s = distance / Math.sin(a3);

				deltaX0 = s * Math.cos(a4);
				deltaY0 = s * Math.sin(a4);
			}

			if (K1 >= 0 && K2 >= 0) {
				if (vectorX1 < 0 && vectorX2 < 0)
					deltaY0 = -deltaY0;

				if (vectorX1 > 0 && vectorX2 > 0)
					deltaX0 = -deltaX0;

				if (vectorX1 > 0 && vectorX2 < 0 && a1 > a2) {
					deltaX0 = -deltaX0;
					deltaY0 = -deltaY0;
				}

				if (vectorX1 < 0 && vectorX2 > 0 && a1 < a2) {
					deltaX0 = -deltaX0;
					deltaY0 = -deltaY0;
				}

			}

			if (K1 <= 0 && K2 <= 0) {

				if (vectorX1 < 0 && vectorX2 < 0) {
					deltaX0 = -deltaX0;
					deltaY0 = -deltaY0;
				}

				if (vectorX1 > 0 && vectorX2 < 0) {
					if (a1 > a2)
						deltaY0 = -deltaY0;
					else
						deltaX0 = -deltaX0;
				}

				if (vectorX1 < 0 && vectorX2 > 0) {
					if (a1 < a2)
						deltaY0 = -deltaY0;
					else
						deltaX0 = -deltaX0;

				}

			}

			returnPoint = new OpenLayers.Geometry.Point(p2.x + deltaX0, p2.y
					+ deltaY0);

		}

		// 下面处理的是：在多段线的相邻两断线段斜率都存在且异号的情况

		if (K1 * K2 < 0) {
			var deltaX1 = 0;
			var deltaY1 = 0;

			if (vectorX1 * vectorX2 > 0) {
				var a3 = 0.5 * (Math.PI - a1 - a2);

				var s = distance / Math.sin(a3);
				var a4 = a3 + ((a1 > a2) ? a2 : a1);

				deltaX1 = s * Math.cos(a4);
				deltaY1 = s * Math.sin(a4);
			} else if (vectorX1 * vectorX2 < 0) {
				var a3 = 0.5 * (a1 + a2);

				var s = distance / Math.sin(a3);
				var a4 = 0.5 * Math.abs(a1 - a2);

				deltaX1 = s * Math.cos(a4);
				deltaY1 = s * Math.sin(a4);
			}

			if (K1 < 0 && K2 > 0) {

				if (vectorX1 > 0 && vectorX2 > 0 && a1 < a2)
					deltaX1 = -deltaX1;

				if (vectorX1 < 0 && vectorX2 < 0) {
					deltaY1 = -deltaY1;
					if (a1 > a2)
						deltaX1 = -deltaX1;
				}

				if (vectorX1 > 0 && vectorX2 < 0 && a1 > a2)
					deltaY1 = -deltaY1;
				if (vectorX1 < 0 && vectorX2 > 0) {
					deltaX1 = -deltaX1;
					if (a1 < a2)
						deltaY1 = -deltaY1;
				}

			}

			if (K1 > 0 && K2 < 0) {

				if (vectorX1 > 0 && vectorX2 > 0 && a1 > a2)
					deltaX1 = -deltaX1;

				if (vectorX1 < 0 && vectorX2 < 0) {
					deltaY1 = -deltaY1;
					if (a1 < a2)
						deltaX1 = -deltaX1;
				}

				if (vectorX1 > 0 && vectorX2 < 0) {
					deltaX1 = -deltaX1;
					if (a1 > a2)
						deltaY1 = -deltaY1;
				}
				if (vectorX1 < 0 && vectorX2 > 0 && a1 < a2)
					deltaY1 = -deltaY1;
			}

			returnPoint = new OpenLayers.Geometry.Point(p2.x + deltaX1, p2.y
					+ deltaY1);

		}
	}
	return returnPoint;
}

// 小角度阀值（20度），用于处理折线在拐角小于20度的偏移
var OffsetAngleThreshold = Math.cos(20 * Math.PI / 180);

// / <summary>
// / 点串（线）的偏移
// / </summary>
// / <param name="points">要进行偏移的点串</param>
// / <param name="iPixelOffset">偏移距离</param>
// / <returns>返回偏移后的点串</returns>
function OffsetPoint(points, iPixelOffset) {
	if (points == null || points.length < 1) {
		return null;
	}

	var pointList = [];

	// 如果点串points中只有一个节点，则返回该点
	if (points.length == 1) {
		pointList.push(new OpenLayers.Geometry.Point(points[0].x, points[0].y));
		return pointList;
	}

	var count = points.length;
	// 需要修改
	for (var i = count - 2; i >= 0; i--) {
		// 删除相等的相邻两点中的后一点
		if (points[i].x == points[i + 1].x && points[i].y == points[i + 1].y) {
			points.splice(i + 1, 1);
		}
	}

	if (points.length > 1) {
		// 下面是第一个节点的偏移
		var p0 = GetFirstPointBufferCorrdinate(points[0], points[1],
				iPixelOffset);
		pointList.push(p0);

		// 如果点串points节点数多于2个，则进行除第一个点和最后一个点以外的节点，即中间节点的偏移
		// 节点的偏移策略是依次遍历相邻三点，求取中间节点的偏移。
		if (points.length > 2) {
			var vectorX1;
			var vectorY1;
			var vectorX2;
			var vectorY2;

			var icos;

			for (var i = 0; i < points.length - 2; i++) {
				vectorX1 = points[i + 1].x - points[i].x;
				vectorY1 = points[i + 1].y - points[i].y;
				vectorX2 = points[i + 2].x - points[i + 1].x;
				vectorY2 = points[i + 2].y - points[i + 1].y;

				// 三点共线的中间点的偏移
				// 斜率不存在的情况
				if (points[i].x == points[i + 1].x
						&& points[i + 1].x == points[i + 2].x) {
					if (vectorX1 * vectorX2 + vectorY1 * vectorY2 > 0) {
						pointList.push(GetLastPointBufferCorrdinate(points[i],
								points[i + 1], iPixelOffset));
						continue;
					} else {
						pointList.push(GetLastPointBufferCorrdinate(points[i],
								points[i + 1], iPixelOffset));
						pointList.push(GetFirstPointBufferCorrdinate(
								points[i + 1], points[i + 2], iPixelOffset));
						continue;
					}
				}

				// 斜率存在且相等的情况
				if (points[i].x != points[i + 1].x
						&& points[i + 1].x != points[i + 2].x) {
					var K1 = (points[i].y - points[i + 1].y)
							/ (points[i].x - points[i + 1].x);
					var K2 = (points[i + 1].y - points[i + 2].y)
							/ (points[i + 1].x - points[i + 2].x);

					if (K1 == K2) {
						if (vectorX1 * vectorX2 + vectorY1 * vectorY2 > 0) {
							pointList.push(GetLastPointBufferCorrdinate(
									points[i], points[i + 1], iPixelOffset));
							continue;
						} else {
							pointList.push(GetLastPointBufferCorrdinate(
									points[i], points[i + 1], iPixelOffset));
							pointList
									.push(GetFirstPointBufferCorrdinate(
											points[i + 1], points[i + 2],
											iPixelOffset));
							continue;
						}
					}
				}

				// 三点不共线的中间点偏移
				icos = (-vectorX1 * vectorX2 - vectorY1 * vectorY2)
						/ ((Math
								.sqrt(vectorX1 * vectorX1 + vectorY1 * vectorY1)) * (Math
								.sqrt(vectorX2 * vectorX2 + vectorY2 * vectorY2)));
				var p = GetMiddlePointBufferCorrdinate(points[i],
						points[i + 1], points[i + 2], iPixelOffset);

				// 进行小角度过滤，角度阈值20度
				if (icos > OffsetAngleThreshold) {
					pointList.push(new OpenLayers.Geometry.Point(
							(p.x * 0.7 + points[i + 1].x * 0.3),
							(p.y * 0.7 + points[i + 1].y * 0.3)));

				} else {
					pointList.push(p);

				}
			}
		}

		// 下面是最后一个节点的偏移
		var num = points.length;
		var pd = GetLastPointBufferCorrdinate(points[num - 2], points[num - 1],
				iPixelOffset);
		pointList.push(pd);

		return pointList;
	} else
		return null;
}

/* ===================偏移线要素图元，算法如上 end======================= */

