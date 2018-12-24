//定义监控展示框对象
var Monitor = function(options) {
	this.options = {
		//目标函数
		target : null,
		//关闭方法
		onClose : function() {
		},
		//停止方法
		onStop : function() {
		},
		//继续方法
		onStart : function() {
		},
		//展示视频
		onShowVideo : function(){
			
		}
	};
	$.extend(this.options, options);
	this.target = this.options.target;
	//外部容器
	this.init();
};

Monitor.prototype = {
	//参数
	options : null,
	//容器，必须是panel
	target : null,
	//内部窗口数量
	count : 0,
	//监视器窗口
	monitors : {},
	//当前监视器高度
	currentWidth : 0,
	//当前的监视器宽度
	currentHeight : 0,
	//显示模式  0列表展示模式，1大小模式
	mode : 0,
	//大小模式下大面板，
	largePanel : null,
	//大小模式下滚动面板
	scrollPanel : null,
	//初始化方法
	init : function() {
		//初始化数据
		this.monitors = {};
		this.currentWidth = 0;
		this.currentHeight = 0;
		this.mode = 0;
		this.target.css({
			overflow : "hidden"
		});
		//初始化大小模式面板
		this._initMaxMode();
	},

	/**
	 * 初始化大小模式面板
	 * @return {[type]} [description]
	 */
	_initMaxMode : function() {
		this.largePanel = $("<div class='largePanelInner'></div>");
		this.scrollPanel = $("<div class='scrollPanelInner'></div>");
		this.target.append(this.largePanel).append(this.scrollPanel);
		this.largePanel.panel({
			cls : "largePanel",
			width : "70%",
			height : "100%",
			border : false,
			closed : true
		});
		this.scrollPanel.panel({
			cls : "scrollPanel",
			width : "30%",
			height : "100%",
			border : false,
			closed : true
		});
	},
	/**
	 * 手动调用自适应窗口方法，外部调用接口 
	 */
	fitMonitor : function(){
		if(this.mode == 0){
			//普通模式
			if(this.count == 1){
				this._fitMonitor(0,1);
			}else if(this.count >= 2 && this.count <= 4){
				this._fitMonitor(1,2);
			}else if(this.count >= 5 && this.count <= 9){
				this._fitMonitor(4,5);
			}else if(this.count >= 10){
				this._fitMonitor(9,10);
			}
		}else{
			//大小模式
			//先隐藏所有图片
			$("img.imageInner").css({
				height : "",
				width : "",
				display : "none"
			});
			//所有图片自适应大小
			for(var index in this.monitors){
				this._fitImageSize(this.monitors[index].find("img.imageInner"));
			}
		}
	},
	/**
	 * 自动适应监视窗口的个数
	 * @param  {[type]} old 原有监视窗口个数
	 * @param  {[type]} new 现在监视窗口个数
	 */
	_fitMonitor : function(old, current) {
		//由0-1或者由2-1
		if (current == 1) {
			this._changeMonitorSize("100%", "100%");
		}
		//由1-2或者由5-4
		if (old == 1 && current == 2 || old == 5 && current == 4) {
			this._changeMonitorSize("50%", "50%");
		}
		//由4-5或者由10-9
		if (old == 4 && current == 5 || old == 10 && current == 9) {
			this._changeMonitorSize("33.3333%", "33.3333%");
		}
		//由9-10
		if (old == 9 && current == 10) {
			this._changeMonitorSize("25%", "25%");
		}
	},
	/**
	 * 调整所有监视器大小
	 * @param  {[type]} width  宽度
	 * @param  {[type]} height 高度
	 */
	_changeMonitorSize : function(width, height) {
		//先隐藏所有图片
		$("img.imageInner").css({
			height : "",
			width : "",
			display : "none"
		});
		//修改容器大小
		this.currentWidth = width;
		this.currentHeight = height;
		for (var index in this.monitors) {
			this.monitors[index].panel("resize", {
				width : width,
				height : height
			});
		}
		//所有图片自适应大小
		for(var index in this.monitors){
			this._fitImageSize(this.monitors[index].find("img.imageInner"));
		}
	},

	/**
	 * 创建新的监视窗口
	 * @param  {[type]} id   id
	 * @param  {[type]} text 窗口标题
	 * @return {[type]}      返回新创建的panel
	 */ 
	_createMonitor : function(id, text) {
		var that = this;
		var div = $("<div class='monitorInner'></div>");
		var img = $("<span><img class='imageInner'/></span>").attr("id", id);
		div.append(img);
		this.target.append(div);
		div.panel({
			title : text,
			closable : true,
			cls : "monitorItem",
			height : that.currentHeight,
			width : that.currentWidth,
			tools : [{
				iconCls : "icon-stop-solid",
				handler : function() {
					if ($(this).hasClass("icon-stop-solid")) {
						this.title = "继续";
						div.panel("panel").find(".panel-title").css("color", "red");
						$(this).removeClass("icon-stop-solid").addClass("icon-play-solid");
						that.options.onStop(id);
					} else {
						this.title = "暂停";
						div.panel("panel").find(".panel-title").css("color", "green");
						$(this).removeClass("icon-play-solid").addClass("icon-stop-solid");
						that.options.onStart(id);
					}
				}
			}, {
				iconCls : "icon-maximize",
				handler : function() {
					if ($(this).hasClass('icon-maximize')) {
						//最大化
						this.title = "还原";
						that._maximizeMonitor(id, $(this));
					} else {
						//还原
						this.title = "最大化";
						that._restoreMonitor($(this));
					}
				}
			},{
				iconCls : "icon-video-solid",
				handler : function(){
					that.options.onShowVideo(id);
				}
			}],
			onBeforeClose : function() {
				that.options.onClose(id);
			}
		});
		div.panel("panel").find(".icon-stop-solid").attr("title", "暂停");
		div.panel("panel").find(".icon-maximize").attr("title", "最大化");
		div.panel("panel").find(".icon-video-solid").attr("title", "查看视频");
		div.panel("panel").find(".panel-tool-close").attr("title", "关闭");
		div.panel("panel").find(".panel-title").css("color", "green");
		this.monitors[id] = div;
		return div;
	},

	/**
	 * 更新图片信息
	 */
	updateImage : function(id, src) {
		var panel = this.getMonitor(id);
		var img = panel.find("img.imageInner");
		img.hide().attr("src", src);
		var $this = this;
		img.unbind("load").bind("load", function() {
			$this._fitImageSize($(this));
		});
	},
	/**
	 * 图片自适应方法
	 */
	_fitImageSize : function(img) {
		if(!img && img.length == 0){
			//img不存在，不做任何操作
			return;
		}
		var maxWidth = img.parent().parent().innerWidth();
		var maxHeight = img.parent().parent() .innerHeight();
		var imageHeight = img.height();
		var imageWidth = img.width();
		if (maxWidth < imageWidth || maxHeight < imageHeight) {
			//图片超出了范围才进行调整
			if (imageHeight / maxHeight > imageWidth / maxWidth) {
				img.width("").height(maxHeight - 4);
			} else {
				img.height("").width(maxWidth);
			}
		}
		img.show();
	},

	/**
	 * 还原监视窗口
	 * @return {[type]} [description]
	 */
	_restoreMonitor : function(button) {
		//更改显示模式
		this.mode = 0;
		//更改按钮样式
		button.addClass("icon-maximize").removeClass("icon-minimize");
		//先隐藏所有图片
		$("img.imageInner").css({
			height : "",
			width : "",
			display : "none"
		});
		//还原窗口
		this.largePanel.panel("close");
		this.scrollPanel.panel("close");
		var width, height;
		if (this.count == 1) {
			width = "100%";
			height = "100%";
		} else if (this.count >= 2 && this.count <= 4) {
			width = "50%";
			height = "50%";
		} else if (this.count >= 5 && this.count <= 9) {
			width = "33.3333%";
			height = "33.3333%";
		} else if (this.count >= 10) {
			width = "25%";
			height = "25%";
		}
		var i = 0;
		for (var index in this.monitors) {
			this.target.append(this.monitors[index].panel("panel"));
			this.monitors[index].panel("resize", {
				width : width,
				height : height
			});
			i++;
		}
		//所有图片自适应大小
		for(var index in this.monitors){
			this._fitImageSize(this.monitors[index].find("img.imageInner"));
		}
	},
	/**
	 * 最大化窗口
	 * @return {[type]} [description]
	 */
	_maximizeMonitor : function(id, button) {
		if (this.count <= 1) {
			//只有一个监控窗口不做任何变化
			return;
		}
		//更改显示模式
		this.mode = 1;
		//更改按钮样式
		this.target.find(".icon-minimize").addClass("icon-maximize").removeClass("icon-minimize");
		button.addClass("icon-minimize").removeClass("icon-maximize");
		//先隐藏所有图片
		$("img.imageInner").css({
			height : "",
			width : "",
			display : "none"
		});
		//展示最大化效果
		var panel = this.getMonitor(id);
		//将最大化监视窗口添加到largePanel中
		var largePanelBody = this.largePanel.panel("open").panel("body");
		largePanelBody.append(panel.panel("panel"));
		panel.panel("resize", {
			width : "100%",
			height : "100%"
		});
		//将其他的监视器窗口添加到scrollPanel中
		var scrollPanelBody = this.scrollPanel.panel("open").panel("body");
		for (var index in this.monitors) {
			//除了需要放大的以外，其他的都调整相应的大小
			if (id == index) {
				continue;
			}
			var itemPanel = this.monitors[index];
			scrollPanelBody.append(itemPanel.panel("panel"));
			itemPanel.panel("open").panel("resize", {
				width : "95%",
				height : "25%"
			});
		}
		//设置滚动条位置到顶部
		scrollPanelBody.scrollTop(0);
		//所有图片自适应大小
		for(var index in this.monitors){
			this._fitImageSize(this.monitors[index].find("img.imageInner"));
		}
	},

	/**
	 * 添加监视窗口
	 * @param {[type]} id   新增监视窗口ID
	 */
	addMonitor : function(id, text) {
		if (this.getMonitor(id)) {
			//监视窗口已经存在，不做任何操作
			return false;
		}
		//针对当前的页面布局，调整内部监视器的排版
		if (this.mode === 0) {
			if (this.count < 16) {
				var newMonitor = this._createMonitor(id, text);
				this._fitMonitor(this.count, ++this.count);
				return true;
			}else{
				return false;
			}
		} else {
			if (this.count < 16) {
				var newMonitor = this._createMonitor(id, text);
				this.scrollPanel.panel("body").append(newMonitor.panel("panel"));
				newMonitor.panel("resize", {
					width : "95%",
					height : "25%"
				});
				this.count++;
				return true;
			}else{
				return false;
			}
		}
	},
	/**
	 * 删除一个监视窗口
	 * @param  {[type]} id 监视窗口Id
	 */
	removeMonitor : function(id) {
		//删除DOM
		var monitor = this.getMonitor(id);
		if (!monitor)
			return false;
		//监视窗口不存在，不做任何操作
		monitor.panel("destroy");
		//删除缓存
		delete this.monitors[id];
		if (this.mode === 0) {
			this._fitMonitor(this.count, --this.count);
		} else {
			this.count--;
		}
		return true;
	},
	/**
	 * 根据ID获得一个监视窗口
	 * @param  {[type]} id 监视窗口ID
	 * @return {[type]}    监视窗口JQuery对象
	 */
	getMonitor : function(id) {
		return this.monitors[id];
	}
};
