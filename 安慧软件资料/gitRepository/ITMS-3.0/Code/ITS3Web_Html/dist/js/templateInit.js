/*! its3.0 2015-08-12 */
function InitPage(params,loadBeforeFn){function $invokeEvent(a){var b=$(this).attr("cy-"+a.type),c=$scope.$apply(b);$scope.$excute(c,a)}function search(a,b,c){$scope.$updateData(a);var d=$scope.$apply(a);if($.extend(b,d),d&&d.url){var e=$scope.$dom.datagrid[a];$(e).datagrid("loading"),$.ajax({type:"get",url:d.url,data:d,beforeSend:function(a){a.setRequestHeader("ContextID",$rootScope.ContextId)},dataType:"json",success:function(a){$(e).datagrid("loaded"),""!==a.Error?alert(a.Error):0!==a.Result.rows.length&&(d.totalCount=a.Result.total,$(e).datagrid("loadData",a.Result),$scope.$excute(c,a.Result))}})}}var $scope={};$scope.$dom={form:{},datagrid:{}},$.extend($scope,$rootScope),$scope.param={},params.push("$params"),params.push("$closeSelf"),params.push("$destroySelf");for(var index in params)$scope.param[params[index]]=$scope.$getParam(params[index]);if($.extend($scope,$scope.param),delete $scope.param,$scope.$tabs){var currentTab=$scope.$tabs.tabs("getSelected");currentTab.data("scopeObject")||currentTab.data("scopeObject",$scope)}$.parser.onComplete=function(a){$.parser.onComplete=function(){},a=void 0!==a?a:$(document),$scope.$context=a,$scope.$=function(a){return $scope.$context.find(a)},$scope.$eventProxy(),$scope.$excute(loadBeforeFn,$scope),$scope.$parser(),$scope.$excute($scope.load)},$scope.$eventProxy=function(){$scope.$("[cy-click]").unbind("click").on("click",$invokeEvent),$scope.$("[cy-dblclick]").unbind("dblclick").on("dblclick",$invokeEvent),$scope.$("[cy-focus]").unbind("focus").on("focus",$invokeEvent),$scope.$("[cy-blur]").unbind("blur").on("blur",$invokeEvent),$scope.$("[cy-mouseenter]").unbind("mouseenter").on("mouseenter",$invokeEvent),$scope.$("[cy-mouseleave]").unbind("mouseleave").on("mouseleave",$invokeEvent),$scope.$("[cy-change]").unbind("change").on("change",$invokeEvent),$scope.$("[cy-mousedown]").unbind("mousedown").on("mousedown",$invokeEvent),$scope.$("[cy-mouseup]").unbind("mouseup").on("mouseup",$invokeEvent),$scope.$("[cy-mousemove]").unbind("mousemove").on("mousemove",$invokeEvent),$scope.$("[cy-resize]").unbind("resize").on("resize",$invokeEvent),$scope.$("[cy-keydown]").unbind("keydown").on("keydown",$invokeEvent),$scope.$("[cy-keyup]").unbind("keyup").on("keyup",$invokeEvent),$scope.$("[cy-keypress]").unbind("keypress").on("keypress",$invokeEvent),$scope.$("[cy-scroll]").unbind("scroll").on("scroll",$invokeEvent),$scope.$("[cy-select]").unbind("select").on("select",$invokeEvent),$scope.$("[cy-submit]").unbind("submit").on("submit",$invokeEvent)},$scope.$parser=function(){function a(){var a,b=$scope.$(".cy-brandbox");b.each(function(){var b=$(this),c=b.val();b.textbox({prompt:"请选择车辆品牌",onChange:function(a,b){$(this).textbox("setText",$scope.$getBrandNameByCode(a))}}).textbox("setText",$scope.$getBrandNameByCode(c)),b.textbox("textbox").click(function(a){var c=$(this);$("#brandbox").data("selectIndex",-1),$("#brandbox").data("textbox",b),$("#brandbox").children().show().removeClass(".brandItem-hover"),$("#brandbox").show().css({left:c.offset().left,top:c.offset().top+c.height()+5}).scrollTop(0)}).unbind("blur").blur(function(){$(this).parent().removeClass("textbox-focused"),""===$(this).val()&&b.textbox("setValue","")}).bind("input",function(b){var c=this;clearTimeout(a),a=setTimeout(function(){var a=c.value;""!==a?$("#brandbox").children(".letterItem").hide():$("#brandbox").children(".letterItem").show();var b=$("#brandbox").children(".brandItem");b.each(function(){$(this).show();var b=$(this).data("brandData"),c=!1;for(var d in b.searchIndex)if(b.searchIndex[d].indexOf(a.toLowerCase())>=0){c=!0;break}c||$(this).hide()}),$("#brandbox").scrollTop(0)},500)})})}function b(){function a(a,b){var c=a.data("vehNumber");b.textbox("setValue",a.val().toUpperCase()),c.init(),b.textbox("setValue",c.getVehNumber())}function b(){var a=$(this).textbox("textbox");$("#vehNumberCookie").show().css({left:a.offset().left,top:a.offset().top+a.outerHeight()+3}).data("vehNumber",a.data("vehNumber"))}var c=$scope.$(".cy-vehNumberbox");c.textbox({buttonIcon:"icon-edit",prompt:"请输入车牌号",onClickButton:b}),c.each(function(){var b=this,c=$(this).textbox("textbox"),d=new VehNumber($(this));c.data("vehNumber",d),isIE()?c.bind("keyup",function(){a($(this),$(b))}):c.bind("input",function(){a($(this),$(b))})})}function c(){$scope.$("[cy-name]").each(function(a,b){var c=$(this).attr("cy-name"),d=$(this).attr("cy-filter"),e=$scope.$apply(c);e&&d?$(this).text($scope.$apply(d+"("+e+")")):e&&$(this).text(e)})}function d(){var a=$scope.$(".cy-chooseLocation");a.each(function(){var a=this,b=$("<a class='button'>选择点位</a>"),c=$("<div><div class='mapChooseLocation'>地图选择</div><div class='clearLocation'>清空</div></div>");c.find(".clearLocation").click(function(b){$(a).textbox("setValue",""),$(a).textbox("textbox").tooltip("destroy"),$(a).removeData("currentLocationData");for(var c in $scope.$dialog)c.startWith("chooseLocationDailog")&&($scope.$dialog[c].dialog("destroy"),delete $scope.$dialog[c])});var d="chooseLocationDailog"+(new Date).getTime();c.find(".mapChooseLocation").click(function(){$scope.$openDialog(d,{title:"选择点位",width:800,height:600,href:"tpls/passvehicle/track_query/chooseLocation.html"},!1)}),$(this).after(b).after(c),$(this).textbox({readonly:!0});var f=$(this).attr("size");f=f?parseInt(f):20,$(this).removeAttr("size"),b.attr("dialogname","chooseLocationDailog"+(new Date).getTime()).splitbutton({menu:c}).css("margin-left",5).click(function(){e(a,f,$(this).attr("dialogname"))})})}function e(a,b,c){var d=function(c){f(a,c,b)};$scope.$setParam("setLocationData",d),$scope.$setParam("currentLocationData",$(a).data("currentLocationData"));var e=$scope.$openDialog(c,{title:"选择点位",width:700,height:550,href:"tpls/public/chooseLocation.html"},!1);$scope.$setParam("dialog",e)}function f(a,b,c){var d="",e="",f=b.checkedData,g=b.formData,h=$("<div class='tip'></div>");if(b.checkedData){for(var i in f)d+=","+f[i].text,e+=","+f[i].id,h.append("<div class='tipItem'>"+f[i].text+"</div>");d=d.substring(1)}else{for(var i in g)"string"==typeof g[i]&&(e+=","+g[i]);d=g.text,h.append("<div class='tipItem'>"+d+"</div>")}e=e.substring(1),d.length>c&&(d=d.substring(0,c)+"..."),$(a).data("currentLocationData",b),$(a).textbox("setValue",e);var j=$(a).textbox("textbox");j.tooltip("destroy"),0!==h.children().length&&j.tooltip({content:h})}function g(){$scope.$("form[cy-form]").each(function(a,b){var c=$(this).attr("cy-form");if(c){$scope.$initObject(c),$scope.$dom.form[c]=this;var d=$scope.$apply(c);$(this).form("load",d),$.extend(d,$(this).getParams()),$(this).data(c,d)}})}function h(){var a=$scope.$("table[cy-datagrid]");0!==a.length&&(a.each(function(){var a=$(this).attr("cy-datagrid");$scope.$initObject(a);var b=$scope.$apply(a);if(!b)return!0;var c=b.onLoadSuccess;b.onLoadSuccess=function(a){$scope.$eventProxy(),$scope.$excute(c,a)},$(this).datagrid(b);var d=$(this).attr("cy-querydata");if(d){var e=$(this).attr("cy-url");$scope.$initObject(d);var f=$scope.$apply(d);f.url=e,$scope.$dom.datagrid[d]=this,$(this).data(d,f);var g=$(this).datagrid("getPager");if(g.length>0){var h=g.pagination("options");f.pageSize=h.pageSize,f.pageNumber=h.pageNumber,$(this).datagrid("getPager").pagination({onSelectPage:function(a,b){f.pageSize=b,f.pageNumber=a,$scope.$search(d)}})}}}),$scope.$search=search)}b(),d(),a(),c(),g(),h()},$scope.$initObject=function(a){for(var b=a.split("."),c=$scope,d=0;d<b.length;d++)c=c[b[d]]?c[b[d]]:c[b[d]]={}},$scope.$updateData=function(a){var b=$scope.$apply(a),c=$scope.$dom.form[a],d=$(c).getParams();$.extend(b,d)},$scope.$updateDom=function(a){$scope.$("form[cy-form='"+a+"']").each(function(){$(this).form("load",$scope.$apply(a))}),$scope.$("[cy-name^='"+a+"']").each(function(){var a=$(this).attr("cy-name"),b=$(this).attr("cy-filter"),c=$scope.$apply(a);c&&b?$(this).text($scope.$apply(b+"("+c+")")):c&&$(this).text(c)})},$scope.$apply=function(script){return eval("$scope."+script)},$scope.$destroy=function(){for(var a in $scope.$dialog)$scope.$dialog[a].dialog("destroy");$scope=null},$scope.$dialog={},$scope.$openDialog=function(a,b,c){return(void 0===c||null===c)&&(c=!0),b.cache=!0,c?(b.href&&null!==b.href&&""!==b.href&&($scope.$setParam("$params",new URL("http://"+b.href).searchParams),$scope.$setParam("$closeSelf",function(){$scope.$getDialog(a).dialog("close")}),$scope.$setParam("$destroySelf",function(){$scope.$getDialog(a).dialog("destroy"),delete $scope.$dialog[a]})),$scope.$dialog[a]&&$scope.$dialog[a].dialog("destroy"),$scope.$dialog[a]=$("<div></div>").dialog(b).dialog("center"),$scope.$dialog[a]):$scope.$dialog[a]?$scope.$dialog[a].dialog("open").dialog("center"):(b.href&&null!==b.href&&""!==b.href&&($scope.$setParam("$params",new URL("http://"+b.href).searchParams),$scope.$setParam("$closeSelf",function(){$scope.$getDialog(a).dialog("close")}),$scope.$setParam("$destroySelf",function(){$scope.$getDialog(a).dialog("destroy"),delete $scope.$dialog[a]})),$scope.$dialog[a]&&$scope.$dialog[a].dialog("destroy"),$scope.$dialog[a]=$("<div></div>").dialog(b).dialog("center"),$scope.$dialog[a])},$scope.$getDialog=function(a){return $scope.$dialog[a]}}