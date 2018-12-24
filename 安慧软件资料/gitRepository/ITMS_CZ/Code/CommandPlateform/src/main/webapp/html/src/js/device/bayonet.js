/**
 * 卡口对象
 * 外部接口：
 * selectLane(lanNbr) 选择一条车道
 * getSelectedLane() 获得已选中的车道编号数组
 * addCamera(lanNbr) 在指定车道上添加相机
 * deleteCamera(lanNbr) 删除指定车道上的相机
 * updateCamera(cameraInfo) 根据传递的相机信息更新相机
 * getCameras() 获得所有已配置好的相机
 * getCamera(laneNbr) 获得车道上的相机
 * distroy() 销毁自己
 * 事件：
 * onAddButtonClick 参数：添加按钮所在车道号
 * onDeleteButtonClick 参数：删除按钮所在车道号
 * onCameraButtonClick 参数：编辑的相机信息
 */
var Bayonet = function(options) {
	this.$options = {
		content : null,// 容器
		directionType : "1",// 方向类型 1或者2，默认1
		directionName : "",// 方向名称
		laneList : [],//车道信息,
		cameraList : [],//相机信息
		//点击添加按钮事件
		onAddButtonClick : function(){
			
		},
		//点击删除按钮事件
		onDeleteButtonClick : function(){
			
		},
		//点击相机按钮事件
		onCameraButtonClick : function(){
			
		}
	};
	$.extend(this.$options, options);// 整合参数

	this.$init();
};
/**
 * 定义原型
 */
Bayonet.prototype = {
		
	/**
	 * 初始化方法
	 */
	$init : function() {
		// 初始化DOM
		this.$initSectionDom();
		// 添加初始化的相机信息
		this.$initCamera();
	},
	/**
	 * 初始化DOM
	 */
	$initSectionDom : function() {
		//初始化车道区域
		var laneArea = $("<div></div>");
		laneArea.css({
			height : "100%",
			width : 575,
			float : 'left',
			paddingLeft : 100
		});
		if(this.$options.directionType == "1"){
			laneArea.css("background","url(themes/default/images/direction_up.png) no-repeat 10px center");
		}else if(this.$options.directionType == "2"){
			laneArea.css("background","url(themes/default/images/direction_down.png) no-repeat 10px center");
		}
		this.$options.content.append(laneArea);
		//添加相机DOM
		var cameraDiv = $("<div></div>");
		cameraDiv.css({
			height : "100%",
			width : 65,
			float : "left",
			paddingLeft : 10
		});
		this.$options.content.append(cameraDiv);
		//根据车道数，调整容器高度
		this.$options.content.css({
			height : 30 * this.$options.laneList.length
		});
		this.laneList = {};
		this.cameraList = {};
		//添加车道信息
		for(var index=0; index<this.$options.laneList.length; index++){
			var laneInfo = this.$options.laneList[index];
			var laneDom = this.$createLaneDiv(laneInfo.laneNbr);
			var cameraOperateDom = this.$createCameraOperateDom(laneInfo.laneNbr);
			if(this.$options.directionType == "1"){
				laneArea.prepend(laneDom);
				cameraDiv.prepend(cameraOperateDom);
			}else if(this.$options.directionType == "2"){
				laneArea.append(laneDom);
				cameraDiv.append(cameraOperateDom);
			}
			//将车道信息缓存，并初始化选中状态和拥有设备状态
			this.laneList[laneInfo.laneNbr] = laneInfo;
			this.laneList[laneInfo.laneNbr].selected = false;
			this.laneList[laneInfo.laneNbr].hasCamera = false;
			this.laneList[laneInfo.laneNbr].dom = laneDom;
			this.laneList[laneInfo.laneNbr].cameraOperateDom = cameraOperateDom;
		}
		//添加方向名称DOM
		var directionNameDiv = $("<div></div>");
		directionNameDiv.css({
			marginTop : 2,
			width : 80,
			float : 'left',
			color : "#eee",
			height : 30 * this.$options.laneCount,
			lineHeight : (30 * this.$options.laneList.length) + "px"
		}).text(this.$options.directionName.endWith("方向") ? this.$options.directionName : this.$options.directionName + "方向");
		this.$options.content.append(directionNameDiv);
	},
	/**
	 * 初始化相机信息
	 */
	$initCamera : function() {
		for (var index in this.$options.cameraList) {
			var item = this.$options.cameraList[index];
			this.addCamera(item);
		}
	},
	/**
	 * 创建车道DOM
	 */
	$createLaneDiv : function(laneNbr){
		var laneDiv = $("<div></div>");
		laneDiv.css({
			height : 28,
			lineHeight : "28px",
			paddingLeft : 5,
			width : "100%",
			color : "#eee"
		});
		if(this.$options.directionType == "1" && laneNbr != 1){
			laneDiv.css("borderBottom","dashed 2px #EEE");
		}else if(this.$options.directionType == "2" && laneNbr != 1){
			laneDiv.css("borderTop","dashed 2px #EEE");
		}
		var laneNbrSpan = $("<span class='laneNbr'>" + "车道" + laneNbr + "</span>");
		laneNbrSpan.css({
			marginLeft : 15,
			float:"left"
		});
		laneDiv.append(laneNbrSpan);
		//选中事件
		var that = this;
		laneDiv.attr("laneNbr",laneNbr).click(function(){
			that.selectLane($(this).attr("laneNbr"));
		});
		return laneDiv;
	},
	/**
	 * 获得相机操作DOM
	 */
	$createCameraOperateDom : function(laneNbr){
		var that = this;
		var operateDom = $("<div class='cameraOpearateDom'><div>");
		operateDom.css({
			width : "100%",
			height : 28,
			lineHeight : "28px"
		});
		var addButton = $("<a title='添加相机' class='addButton'></a>").attr("laneNbr",laneNbr);
		var deleteButton = $("<a title='删除相机' class='deleteButton'></a>").attr("laneNbr",laneNbr);
		var cameraButton = $("<a title='编辑相机' class='cameraButton'></a>").attr("laneNbr",laneNbr);
		operateDom.append(addButton).append(cameraButton).append(deleteButton);
		addButton.linkbutton({
			plain : true,
			iconCls : "icon-add"
		}).click(function(){
			if(typeof that.$options.onAddButtonClick == "function"){
				that.$options.onAddButtonClick($(this).attr("laneNbr"));
			}
		}).hide();
		deleteButton.linkbutton({
			plain : true,
			iconCls : "icon-remove"
		}).click(function(){
			if(typeof that.$options.onDeleteButtonClick == "function"){
				that.$options.onDeleteButtonClick($(this).attr("laneNbr"));
			}
		}).hide();
		cameraButton.linkbutton({
			plain : true,
			iconCls : "icon-camera"
		}).click(function(){
			if(typeof that.$options.onDeleteButtonClick == "function"){
				that.$options.onCameraButtonClick(that.getCamera($(this).attr("laneNbr")));
			}
		}).hide();
		return operateDom;
	},
	/**
	 * 选择车道
	 */
	selectLane : function(laneNbr){
		//选择车道
		var laneInfo = this.laneList[laneNbr];
		if(laneInfo.hasCamera){
			//已经配置了相机，不做任何操作
			return;
		}
		if(laneInfo.selected){
			//已经选中，反选
			laneInfo.dom.css({
				backgroundColor : ""
			});
			laneInfo.selected = false;
			//检查其他已经选择的车道状态
			var laneNbrs = this.getSelectedLane();
			var stayNbr = laneNbrs[0];
			laneNbrs.splice(0,1);
			this.$checkLaneList(stayNbr,laneNbrs);
		}else{
			//未选中
			var laneNbrs = this.getSelectedLane();
			this.$checkLaneList(laneNbr,laneNbrs);
			laneInfo.dom.css({
				backgroundColor : "orange"
			});
			laneInfo.selected = true;
		}
		//更新相机操作栏
		this.$updateCameraOperateButton();
	},
	/**
	 * 更新相机操作栏
	 */
	$updateCameraOperateButton : function(){
		//先还原初始状态
		this.$resetCameraOperateDom();
		var selectedLanes = this.getSelectedLane();
		if(selectedLanes.length == 0){
			return;
		}
		selectedLanes.sort(function(a,b){
			return a-b;
		});
		var mainLaneInfo;
		if(this.$options.directionType == "1"){
			//上行方向以最大的为准
			mainLaneInfo = this.laneList[selectedLanes[selectedLanes.length - 1]];
		}else if(this.$options.directionType == "2"){
			//下行方向以最小车道为准
			mainLaneInfo = this.laneList[selectedLanes[0]];
		}
		//已选其他的操作栏隐藏
		for(var index in selectedLanes){
			if(selectedLanes[index] != mainLaneInfo.laneNbr){
				this.laneList[selectedLanes[index]].cameraOperateDom.hide();
			}
		}
		var cameraOperateDom = mainLaneInfo.cameraOperateDom;
		cameraOperateDom.css({
			height : 28 * selectedLanes.length,
			lineHeight : (28 * selectedLanes.length) + "px"
		});
		cameraOperateDom.find(".addButton").attr({
			laneNbr : selectedLanes.join(",")
		}).show();
	},
	/**
	 * 重置相机操作栏
	 */
	$resetCameraOperateDom : function(){
		for(var index in this.laneList){
			var lane = this.laneList[index];
			if(!lane.hasCamera){
				lane.cameraOperateDom.show().css({
					height : 28,
					lineHeight : "28px"
				}).find(".addButton,.deleteButton.cameraButton").hide();
			}
		}
	},
	/**
	 * 检查已经选中的车道是否符合连续要求
	 */
	$checkLaneList : function(laneNbr,laneNbrs){
		if(laneNbrs.length >= 1){
			if(laneNbrs.lenght > 1){
				laneNbrs.sort(function(a,b){
					return a-b;
				});
			}
			var nbr = laneNbr;
			if(laneNbr < laneNbrs[0]){
				for(var index=0; index<laneNbrs.length; index++,nbr++){
					if(laneNbrs[index] - nbr > 1){
						for(var j=index; j<laneNbrs.length; j++){
							this.selectLane(laneNbrs[j]);
						}
					}
				}
			}else if(laneNbr > laneNbrs[laneNbrs.length-1]){
				for(var index=laneNbrs.length-1; index>=0; index--,nbr--){
					if( nbr - laneNbrs[index] > 1){
						for(var j=0; j<=index; j++){
							this.selectLane(laneNbrs[j]);
						}
					}
				}
			}
		}
	},
	/**
	 * 获得已经选中的车道
	 */
	getSelectedLane : function(){
		var result = [];
		for(var index in this.laneList){
			if(this.laneList[index].selected){
				result.push(this.laneList[index].laneNbr);
			}
		}
		return result;
	},
	/**
	 * 添加相机
	 * @param cameraInfo
	 */
	addCamera : function(cameraInfo){
		//保存相机信息到列表中
		this.cameraList[cameraInfo.laneNbr] = cameraInfo;
		//设置车道状态
		var laneNbrs = cameraInfo.laneNbr.split(",");
		laneNbrs.sort(function(a,b){
			return a-b;
		});
		var mainLaneInfo;
		if(this.$options.directionType == "1"){
			//上行方向以最大的为准
			mainLaneInfo = this.laneList[laneNbrs[laneNbrs.length - 1]];
		}else if(this.$options.directionType == "2"){
			//下行方向以最小车道为准
			mainLaneInfo = this.laneList[laneNbrs[0]];
		}
		for(var index in laneNbrs){
			this.laneList[laneNbrs[index]].selected = false;
			this.laneList[laneNbrs[index]].hasCamera = true;
			this.laneList[laneNbrs[index]].dom.css({
				backgroundColor : "blue"
			});
			if(laneNbrs[index] != mainLaneInfo.laneNbr){
				this.laneList[laneNbrs[index]].cameraOperateDom.hide();
			}
		}
		var cameraOperateDom = mainLaneInfo.cameraOperateDom;
		cameraOperateDom.css({
			height : 28 * laneNbrs.length,
			lineHeight : (28 * laneNbrs.length) + "px"
		});
		cameraOperateDom.find(".addButton").hide();
		cameraOperateDom.find(".deleteButton,.cameraButton").attr({
			laneNbr : cameraInfo.laneNbr
		}).show();
	},
	/**
	 * 编辑相机
	 */
	updateCamera : function(cameraInfo){
		this.cameraList[cameraInfo.laneNbr] = cameraInfo;
	},
	/**
	 * 删除相机
	 * @param laneNbr
	 */
	deleteCamera : function(laneNbrs){
		var laneNbrs = laneNbrs.split(",");
		this.$resetCameraOperateDom(); 
		for(var index in laneNbrs){
			this.laneList[laneNbrs[index]].selected = false;
			this.laneList[laneNbrs[index]].hasCamera = false;
			this.laneList[laneNbrs[index]].dom.css({
				backgroundColor : ""
			});
			this.laneList[laneNbrs[index]].cameraOperateDom.css({
				height : 28,
				lineHeight : "28px"
			}).show().find(".addButton,.deleteButton,.cameraButton").hide();
		}
		delete this.cameraList[laneNbrs];
	},
	getCameras : function(){
		var result = [];
		for(var index in this.cameraList){
			result.push(this.cameraList[index]);
		}
		return result;
	},
	/**
	 * 销毁
	 */
	distroy : function(){
		this.$options.content.html("").css({
			height : 28
		});
	},
	/**
	 * 根据车道号查找相机
	 * @param laneNbr
	 * @returns
	 */
	getCamera : function(laneNbr){
		var result = {};
		$.extend(result,this.cameraList[laneNbr]);
		return result;
	}
	
};
