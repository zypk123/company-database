'use strict';
/**
 * 时间轴控件 
 * @param {Object} 参数
 */
var Axis = function(options) {
	//默认参数
	var _options = {
		dom:null,//容器DOM对象
		item : [],//一级菜单列表
		times : {},//二级菜单列表
		onSelect : function(){//选择时间，回调函数
			
		},
		onExpand : function(){//打开事件
			
		}
	};
	//保存参数
	this.options = $.extend(_options,options);
	//初始化DOM
	this.init();
};

Axis.prototype = {
	//参数
	options : null,
	//大类
	data : {},
	//初始化方法
	init : function(){
		var that = this;
		var jDom = $(this.options.dom);
		jDom.html("");
		for(var index in this.options.item){
			var item = this.options.item[index];
			var head = $("<div class='axis-head'></div>");
			head.text(item);
			var ul = $("<ul class='axis-item-ul'></ul>");
			if(index == 0){
				head.addClass("current");
				ul.addClass("current");
				//调用用户自定义展开事件
				this.options.onExpand();
			}
			jDom.append(head).append(ul);
			//保存数据
			var data = (this.data[item] = {});
			data.headTarget = head;
			data.target = ul;
			head.click(item,function(e){
				that.expand(e.data);
			});
			//填充时间刻度
			this.setTimes(ul,item);
		}
		this.fitSize();
	},
	//适应页面大小
	fitSize : function(){
		var jDom = $(this.options.dom);
		var ul = jDom.find("ul.current");
		var height = jDom.height() * 0.95;
		var ulHeight = height - 41 * this.options.item.length;
		ul.height(ulHeight);
		//设置li的高度
		ul.find("li").show();
		var uls = jDom.find("ul");
		uls.each(function(){
			var lis = $(this).find("li").not(".axis-item-ul-top");
			lis.css("margin-top",(ulHeight - lis.length * 25) / (lis.length + 1));
		});
	},
	//填充刻度
	setTimes : function(ul,item){
		var times = this.options.times[item];
		var data = this.data[item];
		data.children = {};
		ul.append("<li class='axis-item-ul-top'></li>");//添加一个空的li
		for(var index in times){
			var time = times[index];
			var li = $("<li class='axis-item-li'></li>");
			li.text(time);
			ul.append(li);
			if(!ul.is(".current")){
				li.hide();
			}else if(index == 0){
				li.addClass("current");
				//调用用户自定义事件
				this.options.onSelect(item,time);
			}
			//保存数据
			data.children[time] = li;
			li.click([time,this,item],function(e){
				e.data[1].selectTime(e.data[2],e.data[0])
			});
		}
	},
	//展开
	expand : function(item){
		var current = this.getItem(item);
		var currentHead = this.getHead(item);
		if(!current.is(".current")){
			//收缩已经展开的
			this.expandDate(item);
			//选择第一个时间点
			this.selectTime(item,current.children().eq(1).text());
		}
	},
	//展开一个日期
	expandDate : function(item){
		var current = this.getItem(item);
		var currentHead = this.getHead(item);
		if(!current.is(".current")){
			//收缩已经展开的
			var jDom = $(this.options.dom);
			var ul = jDom.find(".axis-item-ul.current");
			var head = jDom.find(".axis-head.current");
			head.removeClass("current");
			currentHead.addClass("current");
			var height = ul.height();
			ul.children().hide();
			ul.css({
				height : 10
			}).removeClass("current");
			//展开当前的
			current.css({
				height:height
			}).addClass("current");
			current.children().show();
			//调用用户定义事件
			this.options.onExpand(current);
		}
	},
	//选择一个时间，并触发选中事件
	selectTime : function(item,time){
		this.chooseTime(item,time);
		//执行用户自定义方法
		this.options.onSelect(item,time);
	},
	//改变选择状态
	chooseTime : function(item,time){
		var jDom = $(this.options.dom);
		var li = this.data[item].children[time];
		jDom.find(".axis-item-li.current").removeClass("current");
		li.addClass("current");
	},
	getItem : function(item){
		return this.data[item].target;
	},
	getHead : function(item){
		return this.data[item].headTarget;
	}
};