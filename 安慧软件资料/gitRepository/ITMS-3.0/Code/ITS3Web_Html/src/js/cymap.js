/**
 // 主题: 地图公共js文件
 // 描述: 地图地图加载类
 // 作者: 陈志英
 // 修改日志:
 // 日期         修改人    版本             修改原因及内容
 // 2015.7.29   陈志英    @version 1.0       创建
 */
//地图切片一些初始配置
var mapTileUrl = "http://192.168.10.144/chishui/";

/*==发布的切片地图范围配置（直接从切图的配置文件拷贝）-START====*/
var mapTileLeftLon = 105.563460244141; //切片地图左下角经度值
var mapTileBottomLat = 28.4634176718798; //切片地图左下角纬度值
var mapTileRightLon = 105.838118447266; //切片地图右上角经度值
var mapTileTopLat = 28.7046038137659; //切片地图右上角纬度值
/*==发布的切片地图范围配置-END================================*/

var mapTileMinLevel = 12;//切片地图的最小等级
var mapTileMaxLevel = 17; //切片地图最大等级

/*==地首次初始显示范围配置（要根据各地方实际情况调节下面值）-START=======*/
var firstLeftLon = 105.59333; //首次初始显示范围左下角经度值
var firstBottomLat = 28.57996; //首次初始显示范围左下角纬度值
var firstRightLon = 105.80928; //首次初始显示范围右上角经度值
var firstTopLat = 28.70621; //首次初始显示范围右上角纬度值
/*==首次初始显示范围配置-END================================*/
var resolution = 156543.033928;//常量请勿修改

function CYMap(mapId)
{
    this.mapId=mapId;
    this.map=null;
    this.controls=null;
    this.shpType=null;//数据类型”point“，”polyline“，”polygon“ bygyf
    this.activeLayer=null;//临时活动图层OpenLayers.Layer.Vector bygyf
    this.activeControl=null;//临时活动绘制控件OpenLayers.Control.DrawFeature bygyf
    this.initMap();
}

CYMap.prototype.initMap=function()
{
    var resols = [];
    for (var i = mapTileMinLevel; i <= mapTileMaxLevel; i++) {
        resols.push(resolution / Math.pow(2, i));
    }
    this.map = new OpenLayers.Map(this.mapId, {
        projection: "EPSG:900913",
        displayProjection: "EPSG:4326",
        maxExtent: new OpenLayers.Bounds(lon2mercator(mapTileLeftLon), lat2mercator(mapTileBottomLat), lon2mercator(mapTileRightLon), lat2mercator(mapTileTopLat)),
        resolutions: resols,
        controls:[]//删除多余的放大缩小工具条
    });
    layer = new OpenLayers.Layer.LocalGoogleMap("baselayer", mapTileUrl);
    this.map.addLayer(layer);

    this.map.addControl(new OpenLayers.Control.Navigation());//guanyafei
    this.map.addControl(new OpenLayers.Control.MousePosition({displayClass:"mousePositionCss"}));
    this.map.addControl(new OpenLayers.Control.PanZoomBar({ zoomWorldIcon: true }));


    //定义测量距离样式
    var measureOptions = SetMeasureStyle();

    controls = {
        zbIn: new OpenLayers.Control.ZoomBox(),
        zbOut: new OpenLayers.Control.ZoomBox({ out: true }), //out:true改变zoombox为缩小功能,
        dragMap: new OpenLayers.Control.DragPan(),
        measureLength: new OpenLayers.Control.Measure(OpenLayers.Handler.Path, measureOptions)//添加测量距离控件
    };
    //将控件加到地图中
    for (var key in this.controls) {
        this.map.addControl(this.controls[key]);
    }
/*    //当点击测量距离图标时，显示floatPage
    this.controls["measureLength"].events.on({
        "activate": CreateResultDiv,
        "deactivate": CloseResultDiv
    });

    //指定测量距离时执行的函数handleMeasurements
    this.controls["measureLength"].events.on({
        "measure": handleMeasurements,
        "measurepartial": handleMeasurements
    });*/

//    var lonLat = new OpenLayers.LonLat(105.7007893457035, 28.58401074282285);
    //    lonLat.transform(map.displayProjection, map.getProjectionObject());
   this.map.zoomToExtent(new OpenLayers.Bounds(lon2mercator(firstLeftLon), lat2mercator(firstBottomLat), lon2mercator(firstRightLon), lat2mercator(firstTopLat)), true);

};

//自定义在地图上测量时候点、线、面的样式
function SetMeasureStyle() {
    sketchSymbolizers = {
        "Point": {
            pointRadius: 4,
            graphicName: "square",
            fillColor: "white",
            fillOpacity: 1,
            strokeWidth: 1,
            strokeOpacity: 1,
            strokeColor: "#333333"
        },
        "Line": {
            strokeWidth: 2,
            strokeOpacity: 1,
            strokeColor: "#666666",
            strokeDashstyle: "solid"
        },
        "Polygon": {
            strokeWidth: 2,
            strokeOpacity: 1,
            strokeColor: "#666666",
            fillColor: "white",
            fillOpacity: 0.3
        }
    };
    var style = new OpenLayers.Style();
    style.addRules([
        new OpenLayers.Rule({ symbolizer: sketchSymbolizers })
    ]);
    var styleMap = new OpenLayers.StyleMap({ "default": style });

    var options = {
        title: "测量距离",
        handlerOptions: {
            style: "default", // this forces default render intent
            layerOptions: { styleMap: styleMap },
            persist: true
        }
    };
    return options;
}
//添加图元bygyf
function addCyFeature(shpType,cyMap,addCyFeatureCallback)
{
    var ispopup=false;
    if(cyMap.activeControl) {
        cyMap.activeControl.deactivate();
        cyMap.map.removeControl(cyMap.activeControl);
        cyMap.activeControl=null;
    }
    if(cyMap.activeLayer) {
        if (shpType == "point") {
            cyMap.activeControl = new OpenLayers.Control.DrawFeature(cyMap.activeLayer, OpenLayers.Handler.Point);
        }
        else if (shpType == "polyline") {
            cyMap.activeControl = new OpenLayers.Control.DrawFeature(cyMap.activeLayer, OpenLayers.Handler.Path);
        }
        else if (shpType == "polygon") {
            cyMap.activeControl = new OpenLayers.Control.DrawFeature(cyMap.activeLayer, OpenLayers.Handler.Polygon);
        }
        cyMap.activeControl.featureAdded = addCyFeatureCallback;
        cyMap.map.addControl(cyMap.activeControl);
        cyMap.activeControl.activate();
    }
    else{
        ispopup=true;
        alert("目标图层为空！");
    }
    return ispopup;
}
//修改图元bygyf
function modifyCyFeature(cyMap,modifyCyFeatureCallback){
    var ispopup=false;
    if(cyMap.activeControl) {
        cyMap.activeControl.deactivate();
        cyMap.map.removeControl(cyMap.activeControl);
        cyMap.activeControl=null;
    }
    if(cyMap.activeLayer){
        cyMap.activeControl= new OpenLayers.Control.ModifyFeature(cyMap.activeLayer);
        cyMap.activeControl.onModificationEnd=modifyCyFeatureCallback;
        cyMap.map.addControl(cyMap.activeControl);
        cyMap.activeControl.activate();
    }
    else{
        ispopup=true;
        alert("目标图层为空！");
    }
    return ispopup;
}

//删除图元bygyf
function deleteCyFeature(cyMap,deleteCyFeatureCallback){
    var ispopup=false;
    if(cyMap.activeControl) {
        cyMap.activeControl.deactivate();
        cyMap.map.removeControl(cyMap.activeControl);
        cyMap.activeControl=null;
    }
    if(cyMap.activeLayer){
        cyMap.activeControl= new OpenLayers.Control.SelectFeature(cyMap.activeLayer,  {
            clickout: true, toggle: false,
            multiple: false, hover: false,
            toggleKey: "ctrlKey", // ctrl key removes from selection
            multipleKey: "shiftKey" // shift key adds to selection
        });
        cyMap.activeControl.onSelect=deleteCyFeatureCallback;
        cyMap.map.addControl(cyMap.activeControl);
        cyMap.activeControl.activate();
    }
    else{
        ispopup=true;
        alert("目标图层为空！");
    }
    return ispopup;
}
/*------------------------------公共方法---------------------------------*/
function lon2mercator(lon) {
    return lon * 20037508.34 / 180;
}
function lat2mercator(lat) {
    var Y = Math.log(Math.tan((90 + lat) * Math.PI / 360)) / (Math.PI / 180);
    return Y * 20037508.34 / 180;
}