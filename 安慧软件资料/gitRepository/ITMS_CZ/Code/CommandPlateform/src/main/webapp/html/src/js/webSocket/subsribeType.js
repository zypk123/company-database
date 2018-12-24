var SubsribeType_typeList = {
	vehicleMonitor : {
		key : "vehicleMonitor",
		name : "过车监控"
	},
	deviceMonitor : {
		key : "deviceMonitor",
		name : "设备监控"
	},
	trafficStatus : {
		key : "trafficStatus",
		name : "实时路况"
	},
	deviceWarning : {
		key : "deviceWarning",
		name : "设备预警"
	},
	vehicleWarning : {
		key : "vehicleWarning",
		name : "车辆预警"
	},
	trafficWarning : {
		key : "trafficWarning",
		name : "路况预警"
	},
	serverMoniter : {
		key : "serverMoniter",
		name : "服务器状态监控"
	}
};

var SubsribeType = {

	/**
	 * 实时路况-设备状态
	 */
	trafficSituationDevStatusMonitor : {
		messageKey : "trafficSituationDevStatusMonitor",
		name : "实时路况中设备状态监控",
		type : SubsribeType_typeList.deviceMonitor.key
	},

	/**
	 * 实时路况-实时路况
	 */
	trafficStatus : {
		messageKey : "trafficStatus",
		name : "实时路况",
		type : SubsribeType_typeList.trafficStatus.key
	},
	/**
	 * 实时路况-断面流量
	 */
	trafficFlow : {
		messageKey : "trafficFlow",
		name : "断面流量",
		type : SubsribeType_typeList.trafficStatus.key
	},

	/**
	 * 实时路况-能见度
	 */
	trafficVisibility : {
		messageKey : "trafficVisibility",
		name : "能见度",
		type : SubsribeType_typeList.trafficStatus.key
	},
	/**
	 * 实时路况-气象仪
	 */
	trafficWeather : {
		messageKey : "trafficWeather",
		name : "气象仪",
		type : SubsribeType_typeList.trafficStatus.key
	},
	/**
	 * 实时路况-路感
	 */
	trafficRoadsensor : {
		messageKey : "trafficRoadsensor",
		name : "路感",
		type : SubsribeType_typeList.trafficStatus.key
	},
	/**
	 * 过车监控数据
	 */
	vehicleMonitor : {
		messageKey : "vehicleMonitor",
		name : "过车监控",
		type : SubsribeType_typeList.vehicleMonitor.key
	},

	/**
	 * 卡扣设备监控数据
	 */
	deviceVehicleMonitor : {
		messageKey : "deviceVehicleMonitor",
		name : "卡扣过车监控",
		type : SubsribeType_typeList.vehicleMonitor.key
	},
	/**
	 * 设备首页状态监控数据
	 */
	deviceHomeMonitor : {
		messageKey : "deviceHomeMonitor",
		name : "设备首页状态监控",
		type : SubsribeType_typeList.deviceMonitor.key
	},
	/**
	 * 设备配置管理中设备状态监控数据
	 */
	deviceConfigMonitor : {
		messageKey : "deviceConfigMonitor",
		name : "设备配置管理中设备状态监控",
		type : SubsribeType_typeList.deviceMonitor.key
	},

	/**
	 * 
	 */
	realVideoMonitor : {
		messageKey : "realVideoMonitor",
		name : "视频实时监控中设备状态监控",
		type : SubsribeType_typeList.deviceMonitor.key
	},

	/**
	 * 设备监控数据
	 */
	deviceMonitor : {
		messageKey : "deviceMonitor",
		name : "设备监控",
		type : SubsribeType_typeList.deviceMonitor.key
	},
	/**
	 * 设备预警-设备故障
	 */
	deviceFault : {
		messageKey : "deviceFault",
		name : "设备故障",
		type : SubsribeType_typeList.deviceWarning.key
	},
	/**
	 * 设备预警-服务器状态异常
	 */
	serverAbnormalState : {
		messageKey : "serverAbnormalState",
		name : "服务器状态异常",
		type : SubsribeType_typeList.deviceWarning.key
	},
	/**
	 * 车辆预警-布控报警
	 */
	vehicleTrackAlarm : {
		messageKey : "vehicleTrackAlarm",
		name : "布控报警",
		type : SubsribeType_typeList.vehicleWarning.key
	},
	/**
	 * 车辆预警-假牌报警
	 */
	vehicleFakeAlarm : {
		messageKey : "vehicleFakeAlarm",
		name : "假牌报警",
		type : SubsribeType_typeList.vehicleWarning.key
	},
	/**
	 * 车辆预警-套牌报警
	 */
	vehicleFakeCloneAlarm : {
		messageKey : "vehicleFakeCloneAlarm",
		name : "套牌报警",
		type : SubsribeType_typeList.vehicleWarning.key
	},
	/**
	 * 车辆预警-危化品车报警
	 */
	vehicleDangerousGoodsAlarm : {
		messageKey : "vehicleDangerousGoodsAlarm",
		name : "危化品车报警",
		type : SubsribeType_typeList.vehicleWarning.key
	},
	/**
	 * 车辆预警-禁行，限行报警
	 */
	vehicleForbiddenAlarm : {
		messageKey : "vehicleForbiddenAlarm",
		name : "禁行，限行报警",
		type : SubsribeType_typeList.vehicleWarning.key
	},

	/**
	 * 服务器管理状态监控
	 */
	serverMgrMonitor : {
		messageKey : "serverMgrMonitor",
		name : "服务器管理状态监控",
		type : SubsribeType_typeList.serverMoniter.key
	},

	/**
	 * 路况预警-交通事件预警
	 */
	trafficEvent : {
		messageKey : "trafficEvent",
		name : "交通事件预警",
		type : SubsribeType_typeList.trafficWarning.key
	},
	// /**
	 // * 路况预警-旅行时间超长预警
	 // */
	// travalAlarm : {
		// messageKey : "travalAlarm",
		// name : "旅行时间超长预警",
		// type : SubsribeType_typeList.trafficWarning.key
	// },
	/**
	 * 路况预警-断流预警
	 */
	sectionAlarm : {
		messageKey : "sectionAlarm",
		name : "断流预警",
		type : SubsribeType_typeList.trafficWarning.key
	},
	/**
	 * 路况预警-路面积水预警
	 */
	waterThicknessAlarm : {
		messageKey : "waterThicknessAlarm",
		name : "路面积水预警",
		type : SubsribeType_typeList.trafficWarning.key
	},
	/**
	 * 路况预警-路面状况预警
	 */
	roadCondtionAlarm : {
		messageKey : "roadCondtionAlarm",
		name : "路面状况预警",
		type : SubsribeType_typeList.trafficWarning.key
	},
	/**
	 * 路况预警-高温预警
	 */
	temperatureAlarm : {
		messageKey : "temperatureAlarm",
		name : "高温预警",
		type : SubsribeType_typeList.trafficWarning.key
	},
	/**
	 * 路况预警-大风预警
	 */
	windAlarm : {
		messageKey : "windAlarm",
		name : "大风预警",
		type : SubsribeType_typeList.trafficWarning.key
	},
	/**
	 * 路况预警-能见度预警
	 */
	visibilityAlarm : {
		messageKey : "visibilityAlarm",
		name : "能见度预警",
		type : SubsribeType_typeList.trafficWarning.key
	}
};
