function Section(options) {
	this.$options = {
		siteId : "",
		id : "",
		direction : "0",// 方向类型，默认双向
		upLaneNum : 1,// 上行车道数
		downLaneNum : 1,// 下行车道数
		content : null,
		onClickLane : function() {

		}
	// 展示容器
	};
	// 合并参数
	$.extend(this.$options, options);
	this.$init();
}

Section.prototype = {
	// 上行断面信息
	$upSection : {},
	// 下行断面信息
	$downSection : {},
	// 初始化方法
	$init : function() {
		// DOM初始化
		this.$initDOM();

	},

	// 初始化DOM
	$initDOM : function() {
		// 初始化上行，下行区域
		var line = $("<div></div>");
		line.css({
			width : "98%",
			margin : "0 1%",
			height : 1,
			borderTop : "2px dashed orange",
			backgroundColor : "#1B6982"
		});
		this.$options.content.append(line);
		if (this.$options.direction == "0") {
			this.addUpSection();
			this.addDownSection();
		} else if (this.$options.direction == "1") {
			this.addUpSection();
		} else if (this.$options.direction == "2") {
			this.addDownSection();
		}
	},
	// 添加断面
	addUpSection : function(sectionInfo) {
		// 上行
		var upArea = $("<div></div>");
		upArea
				.css({
					height : 30,
					width : "98%",
					margin : "0 1%",
					paddingTop : 1,
					background : "url(themes/default/images/direction_up.png) no-repeat 10px center",
					backgroundColor : "#1B6982"
				});
		var laneArea = $("<div></div>");
		laneArea.css({
			height : "100%",
			width : 500,
			marginLeft : 75,
			float : 'left',
			overFlow : "auto"
		});
		var directionNameArea = $("<div></div>");
		directionNameArea.css({
			marginTop : 2,
			width : 155,
			float : 'left',
			textAlign : "center"
		});
		var directionInput = $("<input/>");
		var enterOrExit = $("<select><option value='1'>进城</option><option value='0'>出城</option></select>");
		enterOrExit.add(directionInput).css({
			width : 70,
			height : 30,
			fontSize : 14
		});
		directionNameArea.append(directionInput).append(" ").append(enterOrExit);
		var that = this;
		directionInput.textbox({
			prompt : "上行方向",
			height : 25,
			onChange : function(newValue, oldValue) {
				if (!newValue.endWith("方向")) {
					$(this).textbox("setText", newValue + "方向");
				}
				that.$upSection.directionName = newValue;
			}
		});
		enterOrExit.combobox({
			prompt : "进/出城",
			value : "",
			height : 25,
			value : sectionInfo && sectionInfo.enterOrExit && sectionInfo.enterOrExit != "" ? sectionInfo.enterOrExit : "",
			onChange : function(value){
				if(value == ""){
					that.$downSection.enterOrExitInput.combobox("setValue","");
				}else if(value == "1"){
					that.$downSection.enterOrExitInput.combobox("setValue","0");
				}else if(value == "0"){
					that.$downSection.enterOrExitInput.combobox("setValue","1");
				}
				that.$upSection.enterOrExit = value;
			}
		});
		if (sectionInfo && sectionInfo.directionName
				&& sectionInfo.directionName != "") {
			directionInput.textbox("setValue", sectionInfo.directionName);
		}
		upArea.append(laneArea).append(directionNameArea);
		this.$options.content.prepend(upArea);
		this.$upSection.content = upArea;
		this.$upSection.laneArea = laneArea;
		this.$upSection.directionNameArea = directionNameArea;
		this.$upSection.enterOrExitInput = enterOrExit;
		this.$upSection.direction = "";
		this.$upSection.laneList = [];
		if (sectionInfo && sectionInfo.sectionId) {
			this.$upSection.sectionId = sectionInfo.sectionId;
		}
		// 根据车道数添加车道
		if (sectionInfo && sectionInfo.laneList
				&& sectionInfo.laneList.length > 0) {
			for (var index = 0; index < sectionInfo.laneList.length; index++) {
				this.addLane("1", sectionInfo.laneList[index]);
			}
		} else {
			for (var index = 0; index < this.$options.upLaneNum; index++) {
				this.addLane("1");
			}
		}

	},
	// 删除上行断面
	deleteUpSection : function() {
		this.$upSection.content.remove();
		this.$upSection = {};
	},
	addDownSection : function(sectionInfo) {
		// 下行
		var downArea = $("<div></div>");
		downArea
				.css({
					width : "98%",
					margin : "0 1%",
					background : "url(themes/default/images/direction_down.png) no-repeat 10px center",
					backgroundColor : "#1B6982"
				});
		var laneArea = $("<div></div>");
		laneArea.css({
			height : "100%",
			width : 500,
			float : 'left',
			marginLeft : 75
		});
		var directionNameArea = $("<div></div>");
		directionNameArea.css({
			marginTop : 2,
			width : 155,
			float : 'left',
			textAlign : "center",
		});
		var directionInput = $("<input/>");
		directionInput.css({
			width : 80,
			height : 30,
			fontSize : 14
		});
		var enterOrExit = $("<select><option value='1'>进城</option><option value='0'>出城</option></select>");
		enterOrExit.add(directionInput).css({
			width : 70,
			height : 30,
			fontSize : 14
		});
		directionNameArea.append(directionInput).append(" ").append(enterOrExit);
		var that = this;
		directionInput.textbox({
			prompt : "下行方向",
			height : 25,
			onChange : function(newValue, oldValue) {
				if (!newValue.endWith("方向")) {
					$(this).textbox("setText", newValue + "方向");
				}
				that.$downSection.directionName = newValue;
			}
		});
		enterOrExit.combobox({
			prompt : "进/出城",
			value : "",
			height : 25,
			value : sectionInfo && sectionInfo.enterOrExit && sectionInfo.enterOrExit != "" ? sectionInfo.enterOrExit : "",
			onChange : function(value){
				if(value == ""){
					that.$upSection.enterOrExitInput.combobox("setValue","");
				}else if(value == "1"){
					that.$upSection.enterOrExitInput.combobox("setValue","0");
				}else if(value == "0"){
					that.$upSection.enterOrExitInput.combobox("setValue","1");
				}
				that.$downSection.enterOrExit = value;
			}
		});
		if (sectionInfo && sectionInfo.directionName
				&& sectionInfo.directionName != "") {
			directionInput.textbox("setValue", sectionInfo.directionName);
		}
		downArea.append(laneArea).append(directionNameArea);
		this.$options.content.append(downArea);
		this.$downSection.content = downArea;
		this.$downSection.laneArea = laneArea;
		this.$downSection.directionNameArea = directionNameArea;
		this.$downSection.enterOrExitInput = enterOrExit;
		this.$downSection.direction = "";
		this.$downSection.laneList = [];
		if (sectionInfo && sectionInfo.sectionId) {
			this.$downSection.sectionId = sectionInfo.sectionId;
		}
		// 根据车道数添加车道
		if (sectionInfo && sectionInfo.laneList
				&& sectionInfo.laneList.length > 0) {
			for (var index = 0; index < sectionInfo.laneList.length; index++) {
				this.addLane("2", sectionInfo.laneList[index]);
			}
		} else {
			for (var index = 0; index < this.$options.downLaneNum; index++) {
				this.addLane("2");
			}
		}
	},
	// 删除下行断面
	deleteDownSection : function() {
		this.$downSection.content.remove();
		this.$downSection = {};
	},
	/**
	 * 添加车道
	 */
	addLane : function(direction, initData) {
		if (direction == "1") {
			// 添加上行车道
			var lane = new Lane(
					{
						section : this,
						sectionId : this.$upSection.sectionId,
						laneNbr : initData && initData.laneNbr ? initData.laneNbr
								: this.$upSection.laneList.length + 1,
						direction : direction,
						laneType : initData && initData.laneType ? initData.laneType
								: null,
						isRestrict : initData && initData.isRestrict ? initData.isRestrict
								: 0,
						limitLarge : initData && initData.limitLarge ? initData.limitLarge
								: 0,
						limitSmall : initData && initData.limitSmall ? initData.limitSmall
								: 0,
						limitOthers : initData && initData.limitOthers ? initData.limitOthers
								: 0,
						content : this.$upSection.laneArea,
						onClick : this.$options.onClickLane
					});
			this.$upSection.laneList.push(lane);
		} else if (direction == "2") {
			// 添加下行车道
			var lane = new Lane(
					{
						section : this,
						sectionId : this.$downSection.sectionId,
						laneNbr : initData && initData.laneNbr ? initData.laneNbr
								: this.$downSection.laneList.length + 1,
						direction : direction,
						laneType : initData && initData.laneType ? initData.laneType
								: null,
						isRestrict : initData && initData.isRestrict ? initData.isRestrict
								: 0,
						limitLarge : initData && initData.limitLarge ? initData.limitLarge
								: 0,
						limitSmall : initData && initData.limitSmall ? initData.limitSmall
								: 0,
						limitOthers : initData && initData.limitOthers ? initData.limitOthers
								: 0,
						content : this.$downSection.laneArea,
						onClick : this.$options.onClickLane
					});
			this.$downSection.laneList.push(lane);
		}
		this.$updateSectionDom(direction);
	},

	/**
	 * 更新断面样式
	 * 
	 * @param direction
	 */
	$updateSectionDom : function(direction) {
		if (direction == '1') {
			var lanNum = this.$upSection.laneList.length;
			this.$upSection.content.height(lanNum * 30);
			this.$upSection.directionNameArea.css({
				paddingTop : (lanNum - 1) * 15,
				paddingBottom : (lanNum - 1) * 15
			});
		} else {
			var lanNum = this.$downSection.laneList.length;
			this.$downSection.content.height(lanNum * 30);
			this.$downSection.directionNameArea.css({
				paddingTop : (lanNum - 1) * 15,
				paddingBottom : (lanNum - 1) * 15
			});
		}
	},
	/**
	 * 删除车道
	 * 
	 * @param direction
	 * @param laneNbr
	 * @returns
	 */
	deleteLane : function(direction, laneNbr) {
		if (direction == "1" && this.$upSection.laneList.length == 1
				|| direction == "2" && this.$downSection.laneList.length == 1) {
			$.messager.alert("提示", "至少保留一个车道");
			return;
		}
		var result = this.$findLane(direction, laneNbr);
		if (result) {
			result[1].destroy();
			if (direction == "1") {
				this.$upSection.laneList.splice(result[0], 1);
			} else if (direction == "2") {
				this.$downSection.laneList.splice(result[0], 1);
			}
		}
		this.$updateLaneNumber(direction);
		this.$updateSectionDom(direction);
	},
	/**
	 * 更新车道
	 */
	updateLane : function(laneInfo) {
		var lane = this.$findLane(laneInfo.direction, laneInfo.laneNbr)[1];
		lane.$update(laneInfo);
	},
	/**
	 * 更新车道号
	 */
	$updateLaneNumber : function(direction) {
		var laneList;
		if (direction == "1") {
			laneList = this.$upSection.laneList;
		} else {
			laneList = this.$downSection.laneList;
		}
		for (var index = 0; index < laneList.length; index++) {
			var item = laneList[index];
			item.updateLaneNumber(index + 1);
		}
	},
	/**
	 * 查找车道
	 */
	$findLane : function(direction, laneNbr) {
		if (direction == "1") {
			// 上行
			for (var index = 0; index < this.$upSection.laneList.length; index++) {
				var item = this.$upSection.laneList[index];
				if (item.getLaneNbr() == laneNbr) {
					return [ index, item ];
				}
			}
		} else if (direction == "2") {
			// 下行
			for (var index = 0; index < this.$downSection.laneList.length; index++) {
				var item = this.$downSection.laneList[index];
				if (item.getLaneNbr() == laneNbr) {
					return [ index, item ];
				}
			}
		}
		return null;
	},
	/**
	 * 修改方向类型
	 */
	updateDirection : function(direction) {
		if (direction == this.$options.direction) {
			// 方向无变化
			return;
		}
		if (direction == "0") {
			if (this.$options.direction == "1") {
				// 当前是上行，修改为双向，添加下行方向信息
				this.addDownSection();
			} else if (this.$options.direction == "2") {
				// 当前是下行，修改为双向，添加下行方向信息
				this.addUpSection();
			}
		} else if (direction == "1") {
			if (this.$options.direction == "2") {
				// 当前是下行，修改为上行，删除下行方向，添加上行方向
				this.deleteDownSection();
				this.addUpSection();
			} else if (this.$options.direction == "0") {
				// 当前是双向，修改为上行，删除下行方向
				this.deleteDownSection();
			}
		} else if (direction == "2") {
			if (this.$options.direction == "1") {
				// 当前是上行，修改为下行，删除上行方向，添加下行方向
				this.deleteUpSection();
				this.addDownSection();
			} else if (this.$options.direction == "0") {
				// 当前双向，修改为下行，删除上行方向
				this.deleteUpSection();
			}
		}
		this.$options.direction = direction;
	},
	/**
	 * 导出断面信息
	 */
	getSectionInfo : function() {
		var sectionList = [];
		if (this.$upSection.directionName) {
			var upSection = {
				siteId : this.$options.siteId,
				sectionId : this.$upSection.sectionId,
				directionType : "1",
				directionName : this.$upSection.directionName,
				laneNum : this.$upSection.laneList.length,
				enterOrExit : this.$upSection.enterOrExit,
				laneList : []
			};
			// 将车道信息天骄到断面中
			for ( var index in this.$upSection.laneList) {
				var item = this.$upSection.laneList[index];
				upSection.laneList.push(item.$getLaneInfo());
			}
			sectionList.push(upSection);
		}
		if (this.$downSection.directionName) {
			var downSection = {
				siteId : this.$options.siteId,
				sectionId : this.$downSection.sectionId,
				directionType : "2",
				directionName : this.$downSection.directionName,
				laneNum : this.$downSection.laneList.length,
				enterOrExit : this.$downSection.enterOrExit,
				laneList : []
			};
			// 将车道信息天骄到断面中
			for ( var index in this.$downSection.laneList) {
				var item = this.$downSection.laneList[index];
				downSection.laneList.push(item.$getLaneInfo());
			}
			sectionList.push(downSection);
		}

		return sectionList;
	},
	/**
	 * 验证
	 */
	validate : function() {
		// 上下行信息分别验证
		var result = this.$validateUpSection();
		if (result.result) {
			return this.$validateDownSection();
		} else {
			return result;
		}
	},
	/**
	 * 验证上行方向
	 */
	$validateUpSection : function() {
		if (this.$options.direction == "1" || this.$options.direction == "0") {
			if (!this.$upSection) {
				return {
					result : false,
					message : "上行方向不存在"
				};
			}
			if (!this.$upSection.directionName
					|| this.$upSection.directionName == "") {
				return {
					result : false,
					message : "上行方向名称不可为空"
				};
			}
			// 验证车道
			for ( var index in this.$upSection.laneList) {
				var result = this.$upSection.laneList[index].validate();
				if (!result.result) {
					return result;
				}
			}
		}
		return {
			result : true
		};
	},
	/**
	 * 验证下行方向
	 */
	$validateDownSection : function() {
		if (this.$options.direction == "2" || this.$options.direction == "0") {
			if (!this.$downSection) {
				return {
					result : false,
					message : "下行方向不存在"
				};
			}
			if (!this.$downSection.directionName
					|| this.$downSection.directionName == "") {
				return {
					result : false,
					message : "下行方向名称不可为空"
				};
			}
			// 验证车道
			for ( var index in this.$downSection.laneList) {
				var result = this.$downSection.laneList[index].validate();
				if (!result.result) {
					return result;
				}
			}
		}
		return {
			result : true
		};
	},
	/**
	 * 加载数据
	 */
	load : function(data) {
		for ( var index in data) {
			var item = data[index];
			this.$options.siteId = item.siteId;
			if (item.directionType == "1") {
				// 删除已存在的上行车道
				this.deleteUpSection();
				// 加载上行信息
				this.addUpSection(item);
			} else if (item.directionType == "2") {
				// 删除已存在下行车道
				this.deleteDownSection();
				// 下载下行车道
				this.addDownSection(item);
			}
		}
	}
};