
function Lane(options){
	this.$options = {
		section : null,//断面对象
		sectionId : "",//断面ID
		laneNbr : 1,//车道号
		laneType : null,//车道类型
		isRestrict  : 0,//是否现行
		limitLarge : 0,//大车限速
		limitSmall : 0,//小车限速
		limitOthers : 0,//其他车限速
		direction : "1",//默认上行方向车道
		content : null,//容器
		onClick : function(){
			
		}
	};
	$.extend(this.$options,options);
	this.$init();
}

Lane.laneTypeList = {
	"01" : "普通车道",
	"02" : "小车道",
	"03" : "大客车道",
	"04" : "货车道",
	"05" : "超车道",
	"06" : "行车道",
	"07" : "公交专用车道",
	"08" : "应急车道",
	"09" : "非机动车车道",
	"11" : "城市左转及调头车道",
	"12" : "城市左转车道",
	"13" : "城市直行车道",
	"14" : "城市右转车道",
	"15" : "城市右转及直行车道"
};

Lane.prototype = {
		
	//dom元素
	dom : null,	
	
	//初始化操作
	$init : function(){
		//初始化DOM
		this.$initDom();
	},
	//初始化DOM
	$initDom : function(){
		var that = this;
		var laneDiv = $("<div/>");
		this.dom = laneDiv;
		laneDiv.css({
			height : 28,
			lineHeight : "28px",
			paddingLeft : 5,
			width : "100%",
			color : "#eee"
		});
		if(this.$options.direction == "1" && this.$options.laneNbr != 1){
			laneDiv.css("borderBottom","dashed 2px #EEE");
		}else if(this.$options.direction == "2" && this.$options.laneNbr != 1){
			laneDiv.css("borderTop","dashed 2px #EEE");
		}
		var deleteButton = $("<a title='删除车道'></a>");
		var laneNbrSpan = $("<span class='laneNbr'>" + "车道" + this.$options.laneNbr + "</span>");
		laneNbrSpan.css({
			marginLeft : 15,
			float:"left"
		});
		var laneInfoSpan = $("<div class='laneInfo'></div>");
		laneInfoSpan.html(this.$getLaneInfoDesc());
		laneInfoSpan.css({
			cursor : "pointer",
			marginLeft : 15,
			width : 400,
			float:"left"
		}).click(function(){
			that.$options.onClick(that.$getLaneInfo());
		});
		laneDiv.append(laneNbrSpan).append(laneInfoSpan).append(deleteButton);
		deleteButton.linkbutton({
			plain : true,
            iconCls : "icon-cancel"
		}).click(function(){
			that.$options.section.deleteLane(that.$options.direction,that.$options.laneNbr);
		});
		if(this.$options.direction == "1"){
			//上行
			this.$options.content.prepend(laneDiv);
		}else if(this.$options.direction == "2"){
			//下行
			this.$options.content.append(laneDiv);
		}
	},
	/**
	 * 获得车道信息描述
	 */
	$getLaneInfoDesc : function(){
		var typeName = this.$getTypeName(this.$options.laneType);
		if(typeName == ""){
			return "点击编辑车道详细信息";
		}else{
			if(this.$options.isRestrict == "0"){
				return typeName;
			}else{
				return typeName + "&nbsp;&nbsp;&nbsp;限速为（大车：" + this.$options.limitLarge + "，小车：" + this.$options.limitSmall + "，其他车辆：" + this.$options.limitOthers + "）";
			}
		}
	},
	/**
	 * 获得车道类型名称
	 * @param type
	 * @returns
	 */
	$getTypeName : function(type){
		var name = Lane.laneTypeList[type];
		if(name){
			return name;
		}else{
			return "";
		}
	},
	/**
	 * 更新车道信息
	 */
	$update : function(data){
		//合并参数
		$.extend(this.$options,data);
		//更新数据
		this.dom.find(".laneInfo").html(this.$getLaneInfoDesc());
	},
	/**
	 * 更新车道号 
	 */
	updateLaneNumber : function(nbr){
		this.$options.laneNbr = nbr;
		this.dom.find(".laneNbr").text("车道" + this.$options.laneNbr);
		if(nbr == 1){
			if(this.$options.direction == "1"){
				this.dom.css("border-bottom","none");
			}else{
				this.dom.css("border-top","none");
			}
			
		}
	},
	/**
	 * 销毁
	 */
	destroy : function(){
		this.dom.remove();
	},
	/**
	 * 获得车道号
	 * @returns
	 */
	getLaneNbr : function(){
		return this.$options.laneNbr;
	},
	/**
	 * 获得基本信息对象
	 */
	$getLaneInfo : function(){
		return {
			sectionId : this.$options.sectionId,
			direction : this.$options.direction,
			laneNbr : this.$options.laneNbr,
			laneType : this.$options.laneType,
			isRestrict : this.$options.isRestrict,
			limitLarge : this.$options.limitLarge,
			limitSmall : this.$options.limitSmall,
			limitOthers : this.$options.limitOthers
		};
	},
	/**
	 * 验证
	 */
	validate : function(){
		if(!this.$options.laneType){
			var direction = this.$options.direction == "1" ? "上行" : "下行";
			return {
				result : false,
				message : direction + "方向车道" + this.$options.laneNbr + "信息不完整"
			};
		}else{
			return {
				result : true
			};
		}
	}
};