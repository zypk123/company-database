'use strict';
//jQuery插件
$.fn.extend({
    //按照object的格式获取form对象中的所有参数
    getParams: function () {
        var paramsArray = this.serializeArray();
        var object = {};
        if (paramsArray && paramsArray.length > 0) {
            for (var index in paramsArray) {
                object[paramsArray[index].name] = paramsArray[index].value;
            }
        }
        return object;
    }
});
/**
 * datagrid 查询结果为0时，显示查询无记录的扩展
 */
var oldRender = $.fn.datagrid.defaults.view.onAfterRender;
$.extend($.fn.datagrid.defaults.view,{
    onAfterRender:function(target){
    	oldRender.call(this,target);
        var opts = $(target).datagrid('options');
        var vc = $(target).datagrid('getPanel').children('div.datagrid-view');
        vc.children('div.datagrid-empty').remove();
        if (!$(target).datagrid('getRows').length){
            var d = $('<div class="datagrid-empty"></div>').html(opts.emptyMsg || '没有查到记录').appendTo(vc);
            d.css({
                position:'absolute',
                left:0,
                top:80,
                width:'100%',
                color: '#FF0000',
                textAlign:'center'
            });
        }
    }
}); 
//设置easyui默认参数
//layout
$.fn.layout.defaults.fit = true;
//dialog
$.fn.dialog.defaults.cache = false;
//datagrid
$.extend($.fn.datagrid.defaults, {
	emptyMsg: '没有查到记录',
    border: false,
    pagination: true,
    pageSize: 15,
    pageList: [15, 30, 45, 60],
    checkOnSelect: false,
    selectOnCheck: false,
    singleSelect:true,
    striped: true,
    fit: true,
    fitColumns: true,
    scrollbarSize: 20
});
//combobox
$.extend($.fn.combobox.defaults, {
    panelHeight: null,
    panelMinHeight: 22,
    panelMaxHeight: 200,
    prompt:'请选择',
    editable : false,
    value:''
});
//dialog
$.extend($.fn.dialog.defaults, {
    modal: true
});
//combotree
$.extend($.fn.combotree.defaults, {
    panelHeight: null,
    panelWidth: null,
    panelMinHeight: 22,
    panelMaxHeight: 200,
    panelMinWidth: 200,
    prompt:"请选择"
});
//menubutton，splitbutton
$.fn.menubutton.defaults.plain = false;
$.fn.splitbutton.defaults.plain = false;

var vehNumberData;
//号牌选择器
$(function () {
    var localData = localStorage.getItem("vehNumberData");
    if (!localData) {
        vehNumberData = [
			null, null, null, null, null, null, null, null, null,
			{ text: "京", value: "京", type: "1" },
			{ text: "津", value: "津", type: "1" },
			{ text: "冀", value: "冀", type: "1" },
			{ text: "晋", value: "晋", type: "1" },
			{ text: "蒙", value: "蒙", type: "1" },
			{ text: "辽", value: "辽", type: "1" },
			{ text: "吉", value: "吉", type: "1" },
			{ text: "黑", value: "黑", type: "1" },
			{ text: "警", value: "警", type: "3" },
			{ text: "沪", value: "沪", type: "1" },
			{ text: "苏", value: "苏", type: "1" },
			{ text: "浙", value: "浙", type: "1" },
			{ text: "皖", value: "皖", type: "1" },
			{ text: "闽", value: "闽", type: "1" },
			{ text: "赣", value: "赣", type: "1" },
			{ text: "鲁", value: "鲁", type: "1" },
			{ text: "军", value: "军", type: "2" },
			{ text: "学", value: "学", type: "3" },
			{ text: "豫", value: "豫", type: "1" },
			{ text: "鄂", value: "鄂", type: "1" },
			{ text: "湘", value: "湘", type: "1" },
			{ text: "粤", value: "粤", type: "1" },
			{ text: "桂", value: "桂", type: "1" },
			{ text: "琼", value: "琼", type: "1" },
			{ text: "总", value: "总", type: "2" },
			{ text: "空", value: "空", type: "2" },
			{ text: "挂", value: "挂", type: "3" },
			{ text: "渝", value: "渝", type: "1" },
			{ text: "川", value: "川", type: "1" },
			{ text: "贵", value: "贵", type: "1" },
			{ text: "云", value: "云", type: "1" },
			{ text: "藏", value: "藏", type: "1" },
			{ text: "海", value: "海", type: "2" },
			{ text: "炮", value: "炮", type: "2" },
			{ text: "武", value: "WJ", type: "2" },
			{ text: "使", value: "使", type: "3" },
			{ text: "陕", value: "陕", type: "1" },
			{ text: "甘", value: "甘", type: "1" },
			{ text: "青", value: "青", type: "1" },
			{ text: "宁", value: "宁", type: "1" },
			{ text: "新", value: "新", type: "1" },
			{ text: "港", value: "港", type: "1" },
			{ text: "澳", value: "澳", type: "1" },
			{ text: "台", value: "台", type: "1" },
			{ text: "领", value: "领", type: "3" }
        ];
        //缓存本地数据
        localStorage.setItem("vehNumberData",JSON.stringify(vehNumberData));
    } else {
        vehNumberData = $.parseJSON(localData);
    }
    //初始化html
    initHtml(vehNumberData);
});

//初始化号牌选择器html
function initHtml(data) {
    //创建html
    var tableDiv = $("<div id='vehNumberCookie' class='table'></div>");
    var trDiv;
    for (var index = 0; index < data.length; index++) {
        if (index % 9 === 0) {
            trDiv = $("<div class='tr'></div>");
            tableDiv.append(trDiv);
        }
        var tdDiv = $("<div class='td'></div>").appendTo(trDiv);
        if (!data[index]) {
            tdDiv.data("vehNumberData", null);
            tdDiv.css("backgroundColor", "#FFE038");
        } else {
            tdDiv.data("vehNumberData", data[index]).text(data[index].text);
            if (index < 9) {
                tdDiv.css("backgroundColor", "#FFE038");
            } else {
                switch (data[index].type) {
                    case "1": tdDiv.css("backgroundColor", "#ACF5FF"); break;
                    case "2": tdDiv.css("backgroundColor", "#EEEEEE"); break;
                    case "3": tdDiv.css("backgroundColor", "#A0C1FF"); break;
                }
            }

        }
    }
    //加全局加事件，隐藏选择框
    $("body").append(tableDiv).mousedown(function (e) {
        var targetDiv = $("#vehNumberCookie");
        if (e.clientX < targetDiv.offset().left || 
            e.clientX > targetDiv.offset().left + targetDiv.outerWidth() || 
            e.clientY < targetDiv.offset().top || 
            e.clientY > targetDiv.offset().top + targetDiv.outerHeight()) {
            $("#vehNumberCookie").hide();
        }
    });
    //增加选中事件  
    tableDiv.click(function (e) {
        var data = $(e.target).data("vehNumberData");
        if (data === null) {
            return false;
        } else if (data) {
            var vehNumber = $(this).data("vehNumber");
            switch (data.type) {
                case "1": vehNumber.setHead(data.value); break;
                case "2": vehNumber.setHead(data.value); break;
                case "3": vehNumber.setTail(data.value); break;
            }
            $("#vehNumberCookie").hide();

            //本地数据相关操作
            //选中的是第一排已经有的数据,不修改本地数据
            for (var index = 0; index < 9; index++) {
                if (vehNumberData[index] && vehNumberData[index].value == data.value) {
                    //不做任何处理
                    return false;
                }
            }
            var tr = $(this).find(".tr").eq(0);
            var tds = tr.find(".td");
            vehNumberData.splice(8, 1);
            vehNumberData.splice(0, 0, data);
            tds.eq(8).data("vehNumberData", data);
            tds.eq(8).text(data.value);
            tr.prepend(tds.eq(8));
            //保存本地数据
            localStorage.setItem("vehNumberData",JSON.stringify(vehNumberData));
        }
    });
}


//车牌对象
var VehNumber = function (textBox) {
    this.textBox = textBox;
    this.init();
};

VehNumber.prototype = {
    textBox: null,
    //头部标识，省，单位等信息
    head: "",
    //号码
    no: "",
    //尾部标识
    tail: "",
    //初始化
    init: function () {
        var text = this.textBox.textbox("getText");
        this.no = text;
        this.handleHead(text);
        this.handleTail(text);
    },
    //获得完整号牌
    getVehNumber: function () {
        return this.head + this.no + this.tail;
    },
    setHead: function (head) {
        this.init();
        this.head = head;
        this.textBox.textbox("setValue", this.getVehNumber());
        this.textBox.textbox("textbox").focus().get(0).selectionStart = 100;
    },
    setTail: function (tail) {
        this.init();
        this.tail = tail;
        this.textBox.textbox("setValue", this.getVehNumber());
        this.textBox.textbox("textbox").focus().get(0).selectionStart = 100;
    },
    //处理头部
    handleHead: function (text) {
        this.head = "";
        for (var index = 0; index < vehNumberData.length; index++) {
            if (vehNumberData[index] && text.startWith(vehNumberData[index].value) && vehNumberData[index].type != "3") {
                this.head = vehNumberData[index].value;
                this.no = text.substring(this.head.length);
                break;
            }
        }
    },
    //处理尾部
    handleTail: function (text) {
        this.tail = "";
        for (var index = 0; index < vehNumberData.length; index++) {
            if (vehNumberData[index] && text.endWith(vehNumberData[index].value) && vehNumberData[index].type == "3") {
                this.tail = vehNumberData[index].value;
                this.no = text.substring(text.lastIndexOf(this.tail), this.tail.length);
                break;
            }
        }
    }
};

//增加两个String方法
String.prototype.startWith = function (str) {
    var reg = new RegExp("^" + str);
    return reg.test(this);
};

String.prototype.endWith = function (str) {
    var reg = new RegExp(str + "$");
    return reg.test(this);
};
var explorer = window.navigator.userAgent;
//判断是否是IE浏览器
function isIE() {
    return explorer.indexOf("MSIE") >= 0;
}
//是否是火狐
function isFF() {
    return explorer.indexOf("Firefox") >= 0;
}
//是否是谷歌
function isCH() {
    return explorer.indexOf("Chrome") >= 0;
}
//是否是Opera
function isOP() {
    return explorer.indexOf("Opera") >= 0;
}
//是否是safari
function isSA() {
    return explorer.indexOf("Safari") >= 0;
}



var brandData;
/**
 * 定义品牌选择器html
 */
$(function(){
    $.ajax({
        url : "data/public/brand.json",
        dataType : "json",
        success : function(data){
            brandData = data;
            var box = $("<div id='brandbox' class='table'></div>");
            //加全局加事件，隐藏选择框
            $("body").append(box).mousedown(function (e) {
                var targetDiv = $("#brandbox");
                if (e.clientX < targetDiv.offset().left || 
                    e.clientX > targetDiv.offset().left + targetDiv.outerWidth() || 
                    e.clientY < targetDiv.offset().top || 
                    e.clientY > targetDiv.offset().top + targetDiv.outerHeight()) {
                    targetDiv.hide();
                }
            });
            //按照字母顺序排序
            brandData.sort(function(a,b){
                if(a.imgName < b.imgName){
                    return -1;
                }else{
                    return 1;
                }
            });
            var firstLetter = " ";
            $.each(brandData,function(){
                var name = this.name;
                var imgName = this.imgName;
                var imgNameIndex = imgName.substring(0,imgName.lastIndexOf("."));
                //获取大写字母
                var capitals = getCapital(imgNameIndex);
                //检索索引
                this.searchIndex = [];
                this.searchIndex.push(capitals.toLowerCase());
                this.searchIndex.push(imgNameIndex.toLowerCase());
                //创建html
                if(capitals[0] > firstLetter){
                    //创建首字母索引
                    var letterIndexDiv = $("<div class='letterItem tr'>" + capitals[0] + "</div>");
                    box.append(letterIndexDiv);
                    firstLetter = capitals[0];
                }
                var brandItem = $("<div class='brandItem tr'></div>");
                var brandIcon = $("<div class='brandIcon td'><img class='brandImg' src='themes/default/images/brand/" + imgName + "'/></div>");
                var brandText = $("<div class='brandText td'>" + name + "</div>");
                box.append(brandItem.append(brandIcon).append(brandText));
                brandItem.data("brandData",this);
                //定义点击事件
                brandItem.click(function(){
                    var brandData = $(this).data("brandData");
                    var textbox = box.data("textbox");
                     if(textbox.textbox("getValue") != brandData.id){
                        textbox.textbox("setValue",brandData.id);
                    }
                    // textbox.textbox("setText",brandData.name);
                    
                    box.hide();
                }).hover(function(){
                    $(this).addClass('brandItem-hover');
                },function(){
                    $(this).removeClass('brandItem-hover');
                });
            });

            //定义键盘选择事件
            $(document).keydown(function(event){
                //如果当前不是选择品牌状态，不做任何操作
                if($("#brandbox:visible").length === 0){
                    return;
                }
                var selectIndex = $("#brandbox").data("selectIndex");
                var textbox = $("#brandbox").data("textbox");
                if(event.key == "ArrowUp"){
                    //方向上键
                    event.preventDefault();
                    //去除原来原则的行
                    $("#brandbox").children(".brandItem.brandItem-hover:visible").removeClass("brandItem-hover");
                    var items = $("#brandbox").children(".brandItem:visible");
                    if(selectIndex === 0){
                        items.eq(0).addClass("brandItem-hover");
                    }else{
                        items.eq(--selectIndex).addClass("brandItem-hover");
                    }
                    $("#brandbox").data("selectIndex",selectIndex);
                    textbox.textbox("textbox").blur();
                    var item = items.eq(selectIndex);
                    var brandData = item.data("brandData");
                    if(textbox.textbox("getValue") != brandData.id){
                        textbox.textbox("setValue",brandData.id);
                    }
                    // textbox.textbox("setText",brandData.name);
                    //调整滚动条
                    var position = item.position().top;
                    if(position < 0){
                        $("#brandbox").scrollTop($("#brandbox").scrollTop() + position);
                    }
                }else if(event.key == "ArrowDown"){
                    //方向下键
                    event.preventDefault();
                    //去除原来原则的行
                    $("#brandbox").children(".brandItem.brandItem-hover:visible").removeClass("brandItem-hover");
                    var items = $("#brandbox").children(".brandItem:visible");
                    if(selectIndex == items.length - 1){
                        items.eq(items.length - 1).addClass("brandItem-hover");
                    }else{
                        items.eq(++selectIndex).addClass("brandItem-hover");
                    }
                    $("#brandbox").data("selectIndex",selectIndex);
                    textbox.textbox("textbox").blur();
                    var item = items.eq(selectIndex);
                    var brandData = item.data("brandData");
                    if(textbox.textbox("getValue") != brandData.id){
                        textbox.textbox("setValue",brandData.id);
                    }
                    // textbox.textbox("setText",brandData.name);
                    //调整滚动条
                    var position = item.position().top + item.outerHeight() + $("#brandbox").scrollTop() - $("#brandbox").height()  + 5;
                    if(position > 0){
                        $("#brandbox").scrollTop(position);
                    }
                }else if(event.key == "Enter"){
                    //回车键
                    event.preventDefault();
                    //关闭窗口
                    $("#brandbox").hide();
                }
            });
        }
    });
    /**
     * 获得单词中的大写字母
     * @param  {[type]} word [description]
     * @return {[type]}      [description]
     */
    function getCapital(word){
        var capitals = "";
        for(var index=0; index<word.length; index++){
            var char = word[index];
            if(char >= "A" && char <= "Z"){
                capitals += char;
            }
        }
        return capitals;
    }
});

//初始化加载中覆盖层
$(function(){
    //定义全局变量到rootScope中
    $rootScope.$loading = {};
    //初始化DOM加到document中
    var div = $("<div class='loading'><div><span></span></div></div>");
    var img = $("<img src='themes/default/images/loading.gif' width='407px' height='200px'/>");
    div.find("span").append(img);
    $("body").append(div);
    $rootScope.$loading.el = div;
    //定义覆盖层显示和隐藏方法
    $rootScope.$loading.show = function(css){
        var el = $rootScope.$loading.el;
        el.show().css(css);
    };
    $rootScope.$loading.hide = function(){
        $rootScope.$loading.el.hide();
    };
});

/**
 * 对对象中的每个元素进行trim操作 
 * @param {Object} obj
 */
function trimObject(obj){
	for(var index in obj){
		if(typeof obj[index] == "string"){
			obj[index] = String.trim(obj[index]);
		}
	}
}
