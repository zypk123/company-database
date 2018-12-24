set define off
spool 13_系统代码.log

prompt Deleting T_SYS_CODE...
delete from T_SYS_CODE;
commit;
prompt Deleting T_SYS_CODE_TYPE...
delete from T_SYS_CODE_TYPE;
commit;
prompt Loading T_SYS_CODE_TYPE...
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('230', '免处罚标记', '1', null, '1', 'NO_PUNISH_FLAG');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('231', '查车保护标记', '1', null, '1', 'QUERY_PROTECT_FLAG');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('232', '确认标记', '1', null, '1', 'CONFIRM_FLAG');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('233', '是否已转入违法', '1', null, '1', 'IS_CHANGE_VIO');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('234', '套牌标记', '1', null, '1', 'CLONE_FLAG');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('235', '假牌标记', '1', null, '1', 'FAKE_FLAG');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('236', '假牌或套牌', '1', null, '1', 'FAKE_OR_CLONE');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('237', '布控标记', '1', null, '1', 'TRACK_FLAG');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('510', '管制原因', '1', null, '1', 'CONTROL_REASON');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('513', '清洁等级', '1', null, '1', 'CLEAN_DEGRE');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('514', '降雨类型', '1', null, '1', 'WATER_TYPE');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('163', '导出状态', '1', null, '1', 'status');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('072', '道路走向', '1', null, '1', 'ROAD_DIRECTION_TYPE');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('400', '设备类型', '1', null, '1', 'DEVICE_TYPE');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('139', '单向或双向', '1', null, '1', 'IS_ONE_WAY');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('140', '路口渠化', '1', null, '1', 'CANALIZATION');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('141', '车道类型', '1', null, '1', 'LANE_TYPE');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('142', '是否限行', '1', null, '1', 'IS_RESTRICT');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('143', '有无应急车道', '1', null, '1', 'HAS_EMERGENCY_LANE');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('144', '有无非机动车道', '1', null, '1', 'HAS_BICYCLE_LANE');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('145', '有无人行道', '1', null, '1', 'HAS_PAVEMENT');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('146', '进出路口标识', '1', null, '1', 'ENTER_OR_EXIT');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('147', '路口类型', '1', null, '1', 'CROSS_TYPE');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('148', '路段类型', '1', null, '1', 'ROAD_SECTION_TYPE');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('149', '是否单向通行', '1', null, '1', 'IS_ONE_DIRECTION');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('150', '点位类型', '1', null, '1', 'SITE_TYPE');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('151', '点位地形', '1', null, '1', 'SITE_LANDSCAPE');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('152', '服务资源类型', '1', null, '1', 'SERVICE_RESOURCE_TYPE');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('153', '特殊路段类型', '1', null, '1', 'SPECIAL_SECTION_TYPE');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('154', '服务区类型', '1', null, '1', 'SERVICE_AREA_TYPE');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('155', '进出服务区形式', '1', null, '1', 'ENTRANCE_TYPE');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('156', '服务区等级', '1', null, '1', 'SERVICE_AREA_GRADE');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('157', '是否有加油站', '1', null, '1', 'HAS_GAS_STATION');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('158', '油品种类', '1', null, '1', 'OIL_TYPE_LIST');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('159', '互通类型', '1', null, '1', 'INTERFLOW_TYPE');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('160', '出口或入口', '1', null, '1', 'ENTRANCE_OR_EXIT');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('161', '有无执法服务站', '1', null, '1', 'HAS_LAW_SERVICE_STATION');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('162', '闸道类型', '1', null, '1', 'RAMP_TYPE');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('402', '前端设备网络类型', '1', null, '1', 'NETWORK_TYPE');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('403', '安全接入方式', '1', null, '1', 'SAFE_CONNECT_MEANS');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('404', '安装方式', '1', null, '1', 'MOUNTING_FACILITY_TYPE');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('405', '使用状态标识', '1', null, '1', 'USE_STATUS_FLAG');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('406', '同步标识', '1', null, '1', 'SYNC_STATUS');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('407', '统计检定率标识', '1', null, '1', 'STAT_CERT_RATE');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('408', '统计在线率标识', '1', null, '1', 'STAT_ONLINE_RATE');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('409', '监测方向标识', '1', null, '1', 'DIRECTION_FLAG');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('410', '进出城方向标记', '1', null, '1', 'ERTRANCE_OR_EXIT');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('411', '在线状态', '1', null, '1', 'ONLINE_STATUS');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('412', '设备状态', '1', null, '1', 'STATUS_TYPE');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('413', '球机或枪机', '1', null, '1', 'DOME_GUNLOCK');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('414', '视频分辨率', '1', null, '1', 'VIDEO_RESOLUTION');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('418', '是否需要后台控制恢复预置位', '1', null, '1', 'IS_BACKSTAGE_RECOVERY');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('419', '建设归属', '1', null, '1', 'OWNERSHIP');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('420', '是否接入稽查布控系统', '1', null, '1', 'IS_CONNECT_TRACK_SYS');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('421', '卡口类型', '1', null, '1', 'TOLLGATE_TYPE');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('422', '拦截条件', '1', null, '1', 'INTERCEPT_CONDITIONS');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('434', '事件类型', '1', null, '1', 'EVENT_TYPE');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('436', '服务器组件类型', '1', null, '1', 'COMPONENT_TYPE');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('437', '合同类型', '1', null, '1', 'CONTRACT_TYPE');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('438', '系统组件（部件）类型', '1', null, '1', 'SYS_COMPONENT_TYPE');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('439', '图片类型', '1', null, '1', 'SYS_COMPONENT_TYPE');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('441', '检定结果', '1', null, '1', 'CERTIFICATED_RESULT');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('442', '故障类型', '1', null, '1', 'FAULT_TYPE');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('443', '维护级别', '1', null, '1', 'MAINTENANCE_LEVEL');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('001', '车辆类型', '1', null, '1', 'VEHTYPE');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('002', '号牌种类', '1', null, '1', 'PLATETYPE');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('003', '号牌颜色', '1', null, '1', 'PLATECOLOR');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('004', '车辆外形', '1', null, '1', 'APPEARANCE_TYPE');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('005', '机动车状态', '1', null, '1', 'VEHICLE_STATUS');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('006', '车辆颜色', '1', null, '1', 'VEHCOLOR');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('008', '发证机关', '1', null, '1', 'ISSUEORG');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('009', '证件类型', '1', null, '1', 'ID_TYPE');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('010', '车辆使用特性', '1', null, '1', 'VEHCHARCTER');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('011', '违法类型', '1', null, '1', 'VIOLATION_TYPE');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('012', '车辆品牌', '1', null, '1', 'VEHBRAND');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('013', '采集方式', '1', null, '1', 'VIOLATION_SOURCE');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('444', '设备外观', '1', null, '1', 'DEVICE_APPEARANCE');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('445', '设备杆件', '1', null, '1', 'DEVICE_BAR');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('016', '道路类型', '1', null, '1', 'ROAD_TYPE');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('017', '道路级别', '1', null, '1', 'ROAD_LEVEL');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('200', '值守模式', '1', null, '1', 'DUTY_MODEL');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('201', '是否接入公安网', '1', null, '1', 'IS_CONNECT_POLICE_NET');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('202', '数据来源', '1', null, '1', 'DATA_SOURCE');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('203', '评估结果', '1', null, '1', 'EVALUATE_RESULT');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('204', '识别类型', '1', null, '1', 'IDENTIFY_TYPE');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('205', '套牌车认定方式', '1', null, '1', 'COUNT_FORM');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('206', '布控原因', '1', null, '1', 'TRACK_REASON');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('207', '布控原因子类型', '1', null, '1', 'TRACK_SUB_REASON');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('208', '布控性质', '1', null, '1', 'TRACK_CHARACTER');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('209', '布控等级', '1', null, '1', 'TRACK_LEVEL');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('210', '涉危标记', '1', null, '1', 'IS_DANGEROUS');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('211', '是否涉密车辆', '1', null, '1', 'IS_SECRET_VEHICLE');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('212', '布控警种', '1', null, '1', 'TRACK_POLICE_TYPE');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('213', '布控数据来源', '1', null, '1', 'TRACK_DATA_RESOURCE');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('214', '布控范围类型', '1', null, '1', 'TRACK_SCOPE_TYPE');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('215', '查控措施', '1', null, '1', 'TRACK_ACTION');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('216', '是否短信通知', '1', null, '1', 'IS_SEND_MESSAGE');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('217', '是否增加报警接收人', '1', null, '1', 'HAS_ADD_ALARM_PERSON');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('218', '布控状态', '1', null, '1', 'TRACK_STATUS');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('219', '审核标记', '1', null, '1', 'AUDIT_FLAG');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('220', '撤控原因代码', '1', null, '1', 'REVOKE_REASON_CODE');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('221', '报警模式', '1', null, '1', 'ALARM_MODEL');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('222', '是否密控的报警', '1', null, '1', 'TRACK_SECRET_FLAG');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('223', '报警来源', '1', null, '1', 'ALARM_RESOURCE');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('224', '签收结果', '1', null, '1', 'SIGN_RESULT');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('225', '拦截状态', '1', null, '1', 'INTERCEPT_STATUS');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('226', '是否处理完成', '1', null, '1', 'IS_DISPOSED');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('227', '是否已交罚款', '1', null, '1', 'IS_PAYED');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('301', '记录状态', '1', null, '1', 'STATUS');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('302', '取消上传类型', '1', null, '1', 'CANCEL_UPLOAD_TYPE');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('303', '执法类型', '1', null, '1', 'ENFORCE_CATEGORY');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('304', '违法特性', '1', null, '1', 'VIO_CHARACTER');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('305', '上传状态', '1', null, '1', 'UPLOAD_STATUS');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('306', '废弃类别', '1', null, '1', 'DISCARD_TYPE');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('307', '处罚类型', '1', null, '1', 'PENALTY_TYPE');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('308', '分捡标识', '1', null, '1', 'COLLATE_FLAG');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('446', '防护部件', '1', null, '1', 'PROTECTION_COMPONENTS');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('447', '电源', '1', null, '1', 'POWER');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('448', '电缆', '1', null, '1', 'CABLE');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('449', '避雷设施', '1', null, '1', 'LIGHTING_PROTECTION');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('450', '接地', '1', null, '1', 'GROUND_CONNECTION');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('451', '通信设备', '1', null, '1', 'COMMUNICATION_DEVICE');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('452', '校准时钟', '1', null, '1', 'DEVICE_TIME');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('453', '清理防护罩', '1', null, '1', 'CLEAN_PROTECTIVE_COVER');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('454', '清理镜头积灰', '1', null, '1', 'CLEAN_CAMERA_ASH');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('455', '安装情况', '1', null, '1', 'INSTALLATION');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('456', '标志标线', '1', null, '1', 'TRAFFIC_SIGN');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('457', '车辆通行信息', '1', null, '1', 'VEHICLE_PASS_INFO');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('458', '车辆违法记录信息', '1', null, '1', 'VEHICLE_VIOLATION');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('459', '气象数据', '1', null, '1', 'METEOROLOGY_DATA');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('460', '视频质量', '1', null, '1', 'VIDEO_QUALITY');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('461', '视频云台控制', '1', null, '1', 'PTZ_CONTROL');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('462', 'LED自检情况', '1', null, '1', 'SELF_CHECK');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('463', '信号灯工作情况', '1', null, '1', 'TRAFFIC_LIGHT');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('464', '信号机工作情况', '1', null, '1', 'SIGNAL_DEVICE');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('465', '故障/报警有效性', '1', null, '1', 'IS_VALIDITY');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('466', '处理状态', '1', null, '1', 'PROCESS_STATE');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('467', '采集方式', '1', null, '1', 'COLLECT_METHOD');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('468', '维护单状态', '1', null, '1', 'MAINTENANCE_STATUS');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('469', '维修结论', '1', null, '1', 'MAINTENDANCE_RESULT');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('470', '进出高速公路卡口标识', '1', null, '1', 'HIGHWAY_ENTRANCE_EXIT');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('471', '进出服务区卡口标识', '1', null, '1', 'SERVICE_ENTRANCE_EXIT');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('472', '停用标识', '1', null, '1', 'DISABLE_FLAG');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('473', '供电类型', '1', null, '1', 'POWER_TYPE');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('474', '传输方式', '1', null, '1', 'TRANSMISSION_MODE');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('475', '视频监控类型', '1', null, '1', 'VIDEO_SUPERVISE_TYPE');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('487', '服务状态', '1', null, '1', 'processStatus');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('477', '故障或报警', '1', null, '1', 'FAULT_ALARM');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('478', '故障子类型', '1', null, '1', 'FAULT_SUB_TYPE');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('479', '故障解除标识', '1', null, '1', 'RESOLVE_FLAG');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('480', '重启标识', '1', null, '1', 'RESTART_FLAG');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('481', '运行状态', '1', null, '1', 'RUNNING_STATE');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('511', '路况预警事件类别', '1', null, '1', 'ALARM_EVENT_TYPE');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('512', '预警事件子类型', '1', null, '1', 'SUB_ALARM_EVENT');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('427', '抓拍模式', '1', null, '1', 'PHOTO_MODEL');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('228', '归属地', '1', null, '1', 'VEH_LOCALIZATION');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('229', '车辆分类', '1', null, '1', 'VEH_CATEGORY');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('018', '启用标识', '1', null, '1', 'ENABLE_FLAG');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('019', '编辑标识', '1', null, '1', 'EDITABLE');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('239', '撤控标识', '1', null, '1', 'REVOKE_FLAG');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('240', '执法服务站等级', '1', null, '1', 'STATION_RATING');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('483', '维护周期', '1', null, '1', 'MAINTENANCE_CYCLE');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('484', '装备类型', '1', null, '1', 'EQUIPMENT_TYPE');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('485', '视频接入方式', '1', null, '1', 'ACCESS_MODE');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('491', '设备或区间', '1', null, '1', 'DEVICE_OR_REGION');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('130', '超速行为认定方式', '1', null, '1', 'VIO_CONFIRM_MODEL');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('501', '通行状态', '1', null, '1', 'TRAFFIC_STATE');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('050', '通行方式', '1', null, '1', 'DRIVE_MODE');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('070', '方向类型', '1', null, '1', 'DIRECTION_TYPE');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('131', '是否单向通行', '1', null, '1', 'IS_ONE_DIRECTION');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('132', '路面结构', '1', null, '1', 'ROAD_STRUCTURE');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('133', '路面地形', '1', null, '1', 'ROAD_LANDSCAPE');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('134', '道路线形', '1', null, '1', 'ROAD_LINEAR');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('135', '道路物理隔离设施', '1', null, '1', 'ROAD_ISOLATION');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('136', '中央隔离设施', '1', null, '1', 'CENTRAL_ISOLATION');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('137', '防护设施类型', '1', null, '1', 'PROTECT_FACILITIES');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('138', '道路表记录状态', '1', null, '1', 'ROAD_RECORD_STATUS');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('500', '记录类型', '1', null, '1', 'REPORT_TYPE');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('502', '气象预警类型', '1', null, '1', 'METEOROLOGY_TYPE');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('503', '事件解除标识', '1', null, '1', 'EVENT_RELEASE_TYPE');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('504', '有效或无效', '1', null, '1', 'VALIDITY');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('505', '事件处理状态', '1', null, '1', 'PROCESS_STATUS');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('506', '流量预警类型', '1', null, '1', 'FLOW_ALARM_TYPE');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('507', '预警级别', '1', null, '1', 'ALARM_GRADE');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('508', '阀值类型', '1', null, '1', 'ALARM_VALUE_TYPE');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('509', '路面状况类型', '1', null, '1', 'ROAD_CONDITION_TYPE');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('020', '节假日类型', '1', null, '1', 'FESTIVAL_TYPE');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('482', '检测方式', '1', null, '1', 'DETECTION_MODE');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('515', '管制方向', '1', null, '1', 'DIRECTION_TYPE');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('401', '设备子类型', '1', null, '1', 'DEVICE_TYPE_SUB');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('238', '记录来源', '1', null, '1', 'RECORD_ORGIN');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('309', '测速类别', '1', null, '1', 'SPEEDING_TYPE');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('310', '异常数据类型', '1', null, '1', 'BNORMAL_DATA_TYPE');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('311', '特殊车辆类型', '1', null, '1', 'SPECIAL_VEH_TYPE');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('312', '废弃原因', '1', null, '1', 'DISCARDED_REASON');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('313', '本地处罚标记', '1', null, '1', 'LOCAL_PUNISH_FLAG');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('314', '导出标记', '1', null, '1', 'EXPORT_FLAG');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('315', '锁定标识', '1', null, '1', 'LOCK_FLAG');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('316', '违法大类', '1', null, '1', 'VIO_PRIOR_TYPE');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('317', '警告标记', '1', null, '1', 'FLAG_WARN');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('318', '罚款标记', '1', null, '1', 'FLAG_FINE');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('319', '暂扣标记', '1', null, '1', 'FLAG_SUSPEND');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('320', '吊销标记', '1', null, '1', 'FLAG_CANCEL');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('321', '拘留标记', '1', null, '1', 'FLAG_DETAIN');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('322', '撤销行驶证标记', '1', null, '1', 'FLAG_REVOKE_VEHICLE');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('323', '撤销驾驶证标记', '1', null, '1', 'FLAG_REVOKE_DRIVER');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('324', '是否国标', '1', null, '1', 'IS_GB');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('325', '是否常用', '1', null, '1', 'IS_COMMON_USED');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('326', '强制措施类型', '1', null, '1', 'FORCE_TYPE');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('100', '警员类型', '1', null, '1', 'POLICE_TYPE');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('101', '编制类型', '1', null, '1', 'AUTHORIZED_TYPE');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('102', '业务岗位', '1', null, '1', 'BUSINESS_POST');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('103', '事故处理等级', '1', null, '1', 'EVENT_LEVEL');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('104', '领导级别', '1', null, '1', 'LEADER_LEVEL');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('105', '性别', '1', null, '1', 'SEX');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('106', '警衔', '1', null, '1', 'POLICE_RANK');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('107', '执法资格等级', '1', null, '1', 'QUALIFICATION_GRADE');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('108', '警员表记录状态', '1', null, '1', 'P_RECORD_STATUS');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('071', '方向类型（城市）', '1', null, '1', 'DIRECTION_TYPE_CITY');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('110', 'SSO用户标记', '1', null, '1', 'SSO_FLAG');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('111', '机构类型', '1', null, '1', 'ORG_TYPE');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('112', '是否是独立机构', '1', null, '1', 'IS_DEPARTMENT');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('113', '是否是高速管理部门', '1', null, '1', 'IS_HIGHWAY_ORG');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('114', '机构级别', '1', null, '1', 'ORG_LEVEL');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('115', '行政区划级别', '1', null, '1', 'DISTRICT_GRADE');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('116', '撤销标记', '1', null, '1', 'CANCEL_FLAG');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('117', '登陆状态', '1', null, '1', 'IS_ONLINE');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('486', '校时状态', '1', null, '1', 'timeResult');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('119', '数据权限类型', '1', null, '1', 'DATA_ACCESS_TYPE');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('488', '需检定类型', '1', null, '1', 'CERTIFICATED_TYPE');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('489', '检定状态', '1', null, '1', 'CERTIFICATED_DATE_STATUS');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('122', '值的类型', '1', null, '1', 'VALUE_TYPE');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('123', '是否支持回滚', '1', null, '1', 'REBACKABLE');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('124', '操作结果', '1', null, '1', 'OPERATE_RESULT');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('125', '操作类型', '1', null, '1', 'OPERATE_TYPE');
insert into T_SYS_CODE_TYPE (code_type, code_type_name, editable, regex, enable_flag, english_name)
values ('490', '导入标记', '1', null, '1', 'IMPORT_MARK');
commit;
prompt 236 records loaded
prompt Loading T_SYS_CODE...
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125663', '239', '0', '未撤控', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125664', '239', '1', '已撤控', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125224', '138', '0', '删除', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125225', '138', '1', '正常', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125772', '163', '1', '新增', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125773', '163', '2', '排队中', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125774', '163', '3', '正在导出', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125775', '163', '4', '已完成', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125776', '071', '3', '由东向西', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125777', '071', '4', '由西向东', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125778', '071', '5', '由南向北', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125779', '071', '6', '由北向南', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125780', '071', '7', '东北', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125781', '071', '8', '西南', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125782', '071', '9', '东南', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125783', '071', '10', '西北', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124760', '301', '0', '新记录', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124761', '301', '1', '已筛选', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124762', '301', '2', '已录入', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124763', '303', '1', '上传处罚车辆', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124764', '303', '2', '军警车辆', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124765', '303', '3', '农用车辆', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124766', '303', '4', '套牌车辆', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124767', '303', '5', '假牌车辆', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124768', '303', '6', '白名单车辆', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124769', '303', '7', '本地处罚', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124770', '304', '1', '特殊车辆', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124771', '304', '2', '现场处罚', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124772', '304', '3', '黄标车辆', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124773', '304', '4', '特大违法', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124774', '305', '0', '未上传', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124775', '305', '1', '待上传', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124776', '305', '2', '已上传', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124777', '305', '3', '上传失败', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124778', '305', '4', '取消上传', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124779', '312', '01', '异常数据', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124780', '312', '21', '套牌车', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124781', '312', '22', '假牌车', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124782', '312', '23', '白名单车', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124783', '312', '24', '军警车', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124784', '312', '25', '农用车', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124785', '312', '26', '摩托车', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124786', '312', '03', '重复记录', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124787', '312', '04', '无效图像', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124788', '312', '05', '号牌不全', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124789', '312', '06', '无号牌 ', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124790', '307', '0', '未处罚', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124791', '307', '1', '已处罚', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124792', '307', '2', '免于处罚', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124793', '307', '3', '适时处罚', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124794', '308', '1', '新记录', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124795', '308', '2', '已分捡', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124796', '308', '3', '已废弃', '2', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124941', '312', '07', '超时', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124943', '306', '1', '单张废弃', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124944', '306', '2', '自动废弃', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124945', '306', '3', '批量废弃', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124946', '309', '1', '移动测速', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124947', '309', '2', '区间测速', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124948', '309', '3', '固定点测速', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124949', '309', '4', '卡口线圈测速', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124950', '309', '5', '卡口雷达测速', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124951', '310', '1', '车速异常', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124952', '310', '2', '测试数据', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124953', '311', '1', '套牌车', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124954', '311', '2', '假牌车', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124955', '311', '3', '白名单车', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124956', '311', '4', '军警车', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124957', '311', '5', '农用车', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124958', '311', '6', '摩托车', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124959', '313', '0', '未处罚', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124960', '313', '1', '已处罚', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124961', '314', '0', '未导出', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124962', '314', '1', '已导出', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124963', '315', '0', '未锁定', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124964', '315', '1', '已锁定', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124965', '316', '01', '机动车违法', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124966', '316', '02', '非机动车违法', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124967', '316', '03', '行人乘车人违法', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124968', '316', '04', '道路', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124969', '316', '05', '其它违法', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124970', '316', '06', '非违法过错', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124971', '316', '07', '意外', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124972', '316', '09', '其它', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124973', '317', '0', '否', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124974', '317', '1', '是', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124975', '318', '0', '否', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124976', '318', '1', '是', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124977', '319', '0', '否', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124978', '319', '1', '是', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124979', '320', '0', '否', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124980', '320', '1', '是', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124981', '321', '0', '否', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124982', '321', '1', '是', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124983', '322', '0', '否', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124984', '322', '1', '是', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124985', '323', '0', '否', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124986', '323', '1', '是', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124987', '324', '0', '否', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124988', '324', '1', '是', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124989', '325', '0', '否', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124990', '325', '1', '是', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124991', '326', '1', '扣留', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124992', '326', '2', '收缴', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124993', '326', '3', '拖移机动车', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124994', '326', '4', '检验血液', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124995', '326', '5', '排除障碍', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('126272', '478', '11000', '补光异常', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('126273', '478', '11001', '相机无联系', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('126274', '478', '11002', '相机无图片', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('126275', '478', '11003', '没有图像信号', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('126276', '478', '11004', '相机数据错误', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('126277', '478', '12000', '无雷达信号', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('126278', '478', '12001', '雷达无联系', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('126279', '478', '12002', '雷达无测速', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('126280', '478', '12003', '雷达数据错误', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('126281', '478', '12004', '高频率分机出错', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('126282', '478', '12005', '放大电路故障', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('126283', '478', '12006', 'CPU故障', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('126284', '478', '13000', '交流供电中断', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('126285', '478', '13001', 'ups电池电压偏低', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('126286', '478', '13002', 'ups失效', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('126287', '478', '13003', '没有ups信号', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('126288', '478', '14000', '线圈无联系', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('126289', '478', '14001', '没有线圈信号', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('126290', '478', '14002', '线圈数据故障', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('126291', '478', '15000', '无法建立网络连接', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('126292', '478', '15001', '无法连接服务器', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('126293', '478', '15002', '接受服务器数据异常', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('126294', '478', '16000', '无法打开GPS模块端口', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('126295', '478', '16001', '无法接收GPS数据', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('126296', '478', '16002', 'GPS数据解析错误', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('126297', '478', '16003', 'GPS校时错误', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('126298', '478', '17000', '能见度仪无传感器', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('126299', '478', '17001', '重度污染', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('126300', '478', '17002', 'USB缺失或故障', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('126301', '478', '17003', '端口打开故障', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('126302', '478', '17004', '时间异常', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('126303', '478', '18000', 'LED端口打开故障', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('126304', '478', '18001', 'LED无数据', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('126305', '478', '99999', '其他子故障', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124819', '210', '3', '涉恐', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124820', '210', '4', '涉剧毒危化品', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124821', '211', '0', '否', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124822', '211', '1', '是', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124823', '212', '01', '国内安全保卫', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124824', '212', '02', '经济犯罪侦查', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124825', '212', '03', '治安管理', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124826', '212', '04', '边防管理', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124827', '212', '05', '刑事侦查', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124828', '212', '06', '出入境管理', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124829', '212', '07', '消防', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124830', '212', '08', '警卫', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124831', '212', '09', '中办警卫', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124832', '212', '10', '铁道（行业）', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124833', '212', '11', '公共信息网络安全监察', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124834', '212', '12', '行动技术', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124835', '212', '13', '监所管理', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124836', '212', '14', '交通（行业）', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124837', '212', '15', '民航（行业）', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124838', '212', '16', '林业（行业）', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124839', '212', '17', '交通管理', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124840', '212', '18', '法制', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124841', '212', '19', '外事', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124842', '212', '20', '装备财务', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124843', '212', '21', '禁毒', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124844', '212', '22', '科技', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124845', '212', '23', '信息通信', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124846', '213', '1', '本地录入', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124847', '213', '2', '总队转入', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124848', '213', '3', '部局转入', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124849', '213', '4', '协查地转入', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124850', '214', '1', '全局布控', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124851', '214', '2', '按行政区划布控', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124852', '214', '3', '按卡口布控', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124853', '214', '4', '按道路布控', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124854', '215', '0', '拦截车辆', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124855', '215', '1', '检查盘查', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124856', '215', '2', '观察跟踪', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124857', '215', '3', '报告', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124858', '216', '0', '否', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124859', '216', '1', '是', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124860', '217', '0', '否', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124861', '217', '1', '是', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124862', '218', '1', '未布控', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124863', '218', '2', '已布控', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124864', '218', '3', '已撤销', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124865', '219', '0', '未审核', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124866', '219', '1', '通过审核', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124867', '219', '2', '未通过审核', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124868', '219', '3', '无需审核', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124869', '220', '01', '已过期', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124870', '220', '02', '已处理', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124871', '220', '03', '无效信息', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124872', '220', '04', '错误信息', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124873', '220', '05', '未通过审核', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124874', '220', '99', '其它原因撤控', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124875', '221', '1', '内网预警', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124876', '221', '2', '外网预警', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124877', '221', '3', '前端预警', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125897', '072', '1', '东西方向', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125898', '072', '2', '南北方向', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125899', '072', '3', '东北-西南方向', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125900', '072', '4', '东南-西北方向', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125680', '240', '1', '一级', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125681', '240', '2', '二级', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125682', '240', '3', '三级', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125530', '479', '0', '未解决', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125531', '479', '1', '已解决', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125532', '480', '0', '不重启', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125533', '480', '1', '重启', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125534', '481', '1', '正在运行', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125535', '481', '2', '运行结束', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125563', '481', '1', '正在运行', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125564', '481', '2', '运行结束', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125080', '108', '0', '删除', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125081', '108', '1', '正常', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125089', '112', '1', '是独立机构', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125090', '112', '2', '不是独立机构', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125911', '484', '2', '执法记录仪', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125912', '484', '3', 'GPS', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125756', '513', '0', '清洁', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125757', '513', '1', '轻度污染', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125758', '513', '2', '重度污染', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125759', '514', '0', '无降水', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125760', '514', '1', '未知降水', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125761', '514', '2', '液态降水', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125910', '484', '1', '酒检', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125053', '103', '0', '无等级', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125054', '103', '1', '高级', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125055', '103', '2', '中级', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125056', '103', '3', '初级', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125057', '104', 'D0', '总队领导', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125058', '104', 'D1', '支队领导', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125059', '104', 'D2', '大队领导', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125060', '104', 'D3', '中队领导', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125061', '104', 'ZZ', '其他', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125084', '110', '0', '不允许单点登录', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125085', '110', '1', '允许单点登录', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125086', '111', '1', '地市、城区', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125087', '111', '2', '县市或公路', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125088', '111', '3', '高速', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125091', '113', '0', '否', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125092', '113', '1', '是', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125093', '114', '1', '（省厅）总队', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125094', '114', '2', '（市局）支队', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125095', '114', '3', '（县局）大队', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125096', '114', '4', '（派出所）中队', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125097', '115', '1', '省', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125098', '115', '2', '市', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125099', '115', '3', '县', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125102', '117', '0', '离线', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125103', '117', '1', '在线', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125106', '119', '0', '本机构及下属机构', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125107', '119', '1', '本机构', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125108', '119', '2', '下属机构', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125109', '119', '3', '自定义', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125114', '122', '0', '枚举值', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125115', '122', '1', '数字', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125116', '122', '2', '小数', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125117', '123', '0', '不支持', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125118', '123', '1', '支持', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125121', '125', '1', '新增', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125122', '125', '2', '修改', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125123', '125', '3', '删除', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125124', '125', '4', '查询', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125795', '405', '0', '未启用', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125796', '405', '1', '启用', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125797', '405', '2', '停用', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125798', '405', '3', '报废', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('126217', '489', '1', '正常', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('126218', '489', '2', '两个月后超期', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('126219', '489', '3', '一个月后超期', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('126220', '489', '4', '超期', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('126221', '490', '1', '导入成功', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('126222', '490', '2', '未登记', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('126223', '490', '3', '非交警设备', '1', null, '交警,交巡警,交通警察,总队,支队,大队,中队', '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('126224', '490', '4', '其他原因', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125000', '207', '0601', '多次违法未处理本地车辆', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125001', '207', '0602', '违法未处理外省车辆', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125002', '207', '0603', '违法未处理重点车辆', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125003', '207', '0401', '逾期未年检重点车辆', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125004', '207', '0402', '逾期未年检普通车辆', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125005', '207', '0501', '逾期未报废重点车辆', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125006', '207', '0502', '逾期未报废普通车辆', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125007', '207', '3301', '公路客运车辆', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125008', '207', '3302', '旅游客运车辆', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125009', '207', '5101', '驾证注销撤销吊销', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125010', '207', '5102', '驾证暂扣', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125011', '207', '5103', '驾驶满分', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125012', '207', '0604', '实时违法未处理车辆', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125310', '400', '11', '违停', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125311', '400', '12', '大车占道', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125312', '400', '13', '逆行', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125313', '400', '14', '信号机', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125314', '400', '15', '匝道口信号机', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125315', '400', '16', '车载', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125771', '436', '11', '第三方图片存储服务器', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124747', '427', '1', '车头抓拍', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124748', '427', '2', '车尾抓拍', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124749', '427', '3', '车头车尾都抓拍', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125708', '511', '506', '流量预警', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125709', '511', '502', '气象预警', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125710', '511', '434', '交通事件', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125711', '511', '500', '人工记录事件', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125871', '050', '1', '超低速', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125872', '050', '2', '冲红灯', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125873', '050', '3', '逆行', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125874', '050', '4', '不按车道通行', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125875', '050', '5', '左转', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125876', '050', '6', '右转', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125877', '050', '7', '掉头', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125878', '050', '8', '泊车', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125879', '050', '9', '正常通行', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125880', '050', '100', '套牌通行', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125881', '050', '101', '禁行', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125870', '050', '0', '超高速', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124879', '222', '0', '否', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124880', '222', '1', '是', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124881', '223', '1', '套牌车', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124882', '223', '2', '人工布控', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124883', '223', '3', '其他', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124884', '223', '4', '稽查布控报警系统', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124885', '224', '0', '未签收', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124886', '224', '1', '已签收', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124887', '225', '0', '未拦截', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124888', '225', '1', '已拦截到布控车辆', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124889', '225', '2', '已拦截到但并非布控车辆', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124890', '225', '3', '未拦截到车辆', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124891', '226', '0', '否', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124892', '226', '1', '是', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124893', '227', '0', '否', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124894', '227', '1', '是', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125600', '500', '1', '道路状况', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125601', '500', '2', '气象事件', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125602', '500', '3', '交通事件', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125603', '501', '3', '拥堵', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125604', '501', '2', '缓行', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125605', '501', '1', '畅通', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125606', '502', '1', '能见度预警', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125607', '502', '2', '路面温度预警', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125608', '502', '3', '路面积水预警', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125609', '502', '4', '风力预警', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125610', '502', '5', '路面结冰预警', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125611', '503', '0', '未解除', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125612', '503', '1', '已解除', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125613', '504', '0', '无效', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125614', '504', '1', '有效', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125615', '505', '1', '未处理', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125616', '505', '2', '正在处理', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125617', '505', '3', '已处理完', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125618', '506', '01', '流量突变预警', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125619', '506', '02', '断流预警', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125620', '506', '03', '断面车速差预警', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125621', '506', '04', '区间旅行时间超长预警', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125622', '506', '05', '区间饱和预警', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125623', '506', '06', '白天大型车辆占比超标预警', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125624', '507', '1', '一级', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125625', '507', '2', '二级', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125626', '507', '3', '三级', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125627', '507', '4', '四级', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125628', '508', '01', '断面小时流量上升幅度', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125629', '508', '02', '断面车速差', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125630', '508', '03', '断面断流持续时间', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125631', '508', '04', '平均旅行时间', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125632', '508', '05', '区间饱和度', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125633', '508', '06', '大型车辆占比', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125634', '508', '31', '能见度', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125635', '508', '32', '路面温度', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125636', '508', '33', '水膜厚度', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125637', '508', '34', '路面状况', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125638', '509', '0', '干', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125639', '509', '1', '潮', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125640', '509', '2', '湿', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125641', '509', '3', '冰', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125642', '509', '4', '雪/冰', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125643', '509', '5', '湿含融雪', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125644', '509', '6', '冰水混合', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125645', '509', '7', '雪', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125262', '148', '24', '窄路', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125044', '102', '01', '城区管理执勤', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125045', '102', '02', '国省道执勤', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125046', '102', '03', '高速执勤', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125047', '102', '04', '县乡执勤', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125048', '102', '05', '事故处理', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125049', '102', '06', '车驾管', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125050', '102', '07', '道路宣传', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125051', '102', '08', '科技管理', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125052', '102', '09', '其它', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125261', '148', '23', '变窄路段', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125263', '148', '25', '桥梁', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125264', '148', '26', '隧道', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125265', '148', '27', '路段进出处', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125266', '148', '28', '路侧险要路段', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125267', '148', '29', '其它特殊路段', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125268', '149', '0', '否', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125269', '149', '1', '是', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125270', '150', '1', '路口点位', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125271', '150', '2', '路段点位', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125272', '150', '3', '道路点位', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125273', '151', '1', '平原', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125274', '151', '2', '丘陵', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125275', '151', '3', '山区', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125276', '152', '1', '110', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125277', '152', '2', '120', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125278', '153', '1', '桥梁', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125279', '153', '2', '隧道', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125280', '153', '3', '坡道', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125281', '153', '4', '弯道', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125282', '153', '5', '事故易发段', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125901', '483', '1', '一个月', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125902', '483', '2', '两个月', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125903', '483', '3', '三个月', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125904', '483', '4', '六个月', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125905', '483', '5', '一年', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125890', '020', '1', '元旦', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125891', '020', '2', '春节', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125892', '020', '3', '清明节', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125893', '020', '4', '劳动节', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125894', '020', '5', '端午节', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125895', '020', '6', '国庆节', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125896', '020', '7', '中秋节', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124798', '400', '01', '卡口', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124799', '400', '02', '电警', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124800', '400', '03', '视频', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124801', '400', '04', '固定点测速', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124802', '400', '05', '气象设备', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124803', '400', '06', '可变限速牌', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124804', '400', '07', '诱导屏', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124805', '400', '08', '事件检测', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124806', '400', '09', '流量检测', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124807', '400', '10', '短信基站', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124808', '401', '0401', '固定点', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124809', '401', '0402', '移动点', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124810', '401', '0201', '普通电警', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124811', '401', '0202', '卡警', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125790', '011', 'e', '未系安全带1', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125907', '001', 'k11', '小车 ', '0', null, '红色', '0');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125039', '100', '1', '交警', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125040', '100', '2', '其他警种', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125041', '101', '1', '公安编', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125042', '101', '2', '事业编', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125043', '101', '3', '地方编', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125283', '154', '1', '双侧分离式', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125284', '154', '2', '单侧集中式', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125285', '154', '3', '主线下穿式', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125286', '155', '1', '双向进入', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125287', '155', '2', '上行方向进入', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125288', '155', '3', '下行方向进入', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125289', '156', '1', '一级', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125290', '156', '2', '二级', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125291', '156', '3', '三级', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125292', '157', '0', '无', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125293', '157', '1', '有', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125294', '158', '1', '90#车用汽油', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125295', '158', '2', '93#车用汽油', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125296', '158', '3', '97#车用汽油', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125297', '158', '4', '0#轻柴油', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125298', '158', '5', '10#轻柴油', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125299', '158', '6', '20#轻柴油', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125300', '159', '1', '高速公路服务互通', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125301', '159', '2', '高速公路枢纽互通', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125302', '159', '3', '其它路互通', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125303', '160', '1', '出口', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125304', '160', '2', '入口', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125305', '161', '0', '无', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125306', '161', '1', '有', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125307', '162', '1', '高速公路出入口匝道', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125308', '162', '2', '互通枢纽匝道', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125309', '162', '3', '其它路匝道', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125316', '401', '0101', '治安卡口', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125317', '401', '0102', '路段卡口', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125318', '401', '0103', '收费站卡口', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125319', '401', '0104', '城市路口卡口', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125320', '401', '0105', '停车场卡口', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125321', '401', '0301', '球机', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125322', '401', '0302', '枪机', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125323', '401', '0501', '路感', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125324', '401', '0502', '能见度', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125325', '401', '0503', '气象仪', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125326', '401', '0701', '复合屏', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125327', '401', '0702', '点阵屏', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125328', '401', '0703', '光带屏', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125329', '402', '1', '公安网', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125330', '402', '2', '专网', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125331', '402', '3', '互联网', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125332', '403', '1', '安全接入平台', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125333', '403', '2', '一机两用', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125334', '403', '3', '直接接入', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125335', '404', '1', 'L杆', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125336', '404', '2', 'M型龙门架', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125337', '404', '3', 'N型龙门架', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125338', '404', '4', 'F杆', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125339', '404', '5', '其它', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125343', '406', '0', '未同步', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125344', '406', '1', '已同步', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125345', '407', '0', '否', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125346', '407', '1', '是', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125347', '408', '0', '否', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125348', '408', '1', '是', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125349', '409', '0', '无方向', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125350', '409', '1', '单向', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125351', '409', '2', '双向', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125352', '410', '1', '进城', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125353', '410', '0', '出城', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125354', '411', '1', '在线', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125355', '411', '2', '离线', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125356', '412', '1', '正常', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125358', '412', '2', '脱机', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125360', '412', '3', '故障', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125361', '413', '1', '球机', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125362', '413', '2', '枪机', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125363', '414', '1', '高清', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125364', '414', '2', '普清', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125365', '418', '0', '否', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125366', '418', '1', '是', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125367', '419', '1', '交警自建', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125368', '419', '2', '公安建设', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125369', '419', '3', '社会建设', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125370', '420', '0', '否', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125371', '420', '1', '是', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125372', '421', '01', '国界卡口', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125373', '421', '02', '省际卡口', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125374', '421', '03', '市际卡口', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125375', '421', '04', '县际卡口', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125376', '421', '05', '公路主线卡口', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125377', '421', '06', '公路收费站卡口', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125378', '421', '07', '城区道路卡口', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125379', '421', '08', '城区路口卡口', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125380', '421', '09', '电子警察卡口', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125381', '421', '10', '服务区卡口', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125382', '421', '99', '其他卡口', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125383', '422', '0', '不具备', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125384', '422', '1', '具备', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125397', '436', '0', '视频Cms服务器', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125398', '436', '1', '通信服务器', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125399', '436', '2', '订阅服务器', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125400', '436', '3', 'FTP服务器', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125401', '436', '4', '图片存储服务器', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125402', '436', '5', '视频Pag服务器', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125403', '436', '6', '视频用户接入服务器（流媒体）', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125404', '436', '7', '视频存储管理服务器', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125405', '436', '8', '视频Web服务器', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125406', '436', '9', '比对报警服务器', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125407', '436', '10', '云台控制服务器', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125408', '437', '1', '新签合同', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125409', '437', '2', '续签合同', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125410', '438', '01', '视频采集', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125411', '438', '02', '抓拍相机', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125412', '438', '03', '测速', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125413', '438', '04', '补光', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125414', '438', '05', '数据处理', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125415', '438', '06', '网络传输', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125416', '438', '07', '存储', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125417', '439', '1', '设备图片', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125418', '439', '2', '部件图片', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125419', '439', '3', '安装图片', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125420', '441', '1', '合格', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125421', '441', '2', '不合格', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125430', '443', '1', '一级', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125431', '443', '2', '二级', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125432', '443', '3', '三级', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125433', '444', '0', '不合格', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125434', '444', '1', '合格', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125435', '445', '0', '不正常', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125436', '445', '1', '正常', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125437', '446', '0', '不正常', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125438', '446', '1', '正常', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125439', '447', '0', '不正常', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125440', '447', '1', '正常', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125441', '448', '0', '不正常', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125442', '448', '1', '正常', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125443', '449', '0', '不正常', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125444', '449', '1', '正常', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125445', '450', '0', '不正常', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125446', '450', '1', '正常', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125447', '451', '0', '不正常', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125448', '451', '1', '正常', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125449', '452', '0', '否', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125450', '452', '1', '是', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125451', '453', '0', '未清理', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125452', '453', '1', '已清理', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125453', '454', '0', '未清理', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125454', '454', '1', '已清理', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125455', '455', '0', '不合格', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125456', '455', '1', '合格', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125457', '456', '0', '不合格', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125458', '456', '1', '合格', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125459', '457', '0', '不正常', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125460', '457', '1', '正常', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125461', '458', '0', '不正常', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125462', '458', '1', '正常', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125463', '459', '0', '不正常', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125464', '459', '1', '正常', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125465', '460', '0', '差', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125466', '460', '1', '好', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125467', '461', '0', '不正常', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125468', '461', '1', '正常', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125469', '462', '0', '失败', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125470', '462', '1', '成功', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125471', '463', '0', '不正常', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125472', '463', '1', '正常', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125473', '464', '0', '不正常', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125474', '464', '1', '正常', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125475', '465', '1', '有效', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125476', '465', '2', '无效', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125479', '467', '1', '前端设备上传', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125480', '467', '2', '系统分析', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125481', '467', '3', '人工添加', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125482', '468', '0', '未分派', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125483', '468', '1', '已分派', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125484', '469', '1', '全部未维修好', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125485', '469', '2', '部分未维修好', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125486', '469', '3', '完全修好', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125487', '470', '0', '非进出高速公路卡口', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125488', '470', '1', '进高速公路卡口', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125489', '470', '2', '出高速公路卡口', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125490', '471', '0', '非进出服务区卡口', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125491', '471', '1', '进服务区卡口', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125492', '471', '2', '出服务区卡口', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125493', '472', '0', '在用', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125494', '472', '1', '停用', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125495', '473', '1', '农电', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125496', '473', '2', '市电', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125497', '473', '3', '太阳能', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125498', '473', '4', 'UPS', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125499', '474', '1', '光纤', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125500', '474', '2', '4G', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125501', '475', '1', '交通监控', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125502', '475', '2', '治安监控', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125505', '477', '1', '故障', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125506', '477', '2', '报警', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125566', '301', '9', '已废弃', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125567', '301', '3', '待重录', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125180', '130', '1', '高速公路认定方式', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125181', '130', '2', '国省道认定方式', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125182', '131', '0', '否', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125183', '131', '1', '是', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125184', '132', '1', '沥青', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125185', '132', '2', '水泥', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125186', '132', '3', '砂石', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125187', '132', '4', '土路', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125188', '132', '9', '其它', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125189', '133', '1', '平原', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125190', '133', '2', '丘陵', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125191', '133', '3', '山区', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125192', '134', '01', '平直', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125193', '134', '02', '一般弯', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125194', '134', '03', '一般坡', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125195', '134', '04', '急弯', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125196', '134', '05', '陡坡', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125197', '134', '06', '连续下坡', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125198', '134', '07', '一般弯坡', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125199', '134', '08', '急弯陡坡', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125200', '134', '09', '一般坡急弯', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125201', '134', '10', '一般弯陡坡', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125202', '135', '1', '无隔离', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125203', '135', '2', '中心隔离', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125204', '135', '3', '机非隔离', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125205', '135', '4', '中心隔离加机非隔离', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125206', '136', '1', '绿化带', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125207', '136', '2', '混凝土护拦', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125208', '136', '3', '波形护拦', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125209', '136', '4', '金属护拦', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125210', '136', '5', '柔性护拦', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125211', '136', '6', '活动护栏', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125212', '136', '7', '隔离墩(柱)', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125213', '137', '01', '无防护', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125214', '137', '02', '行道树', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125215', '137', '03', '绿化带', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125216', '137', '04', '混凝土护拦', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125217', '137', '05', '防护墩(柱)', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125218', '137', '06', '波形护拦', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125219', '137', '07', '金属护拦', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125220', '137', '08', '柔性护拦', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125221', '137', '09', '缓冲物', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125222', '137', '10', '避险车道', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125223', '137', '19', '其它', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125226', '139', '1', '单向', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125227', '139', '2', '双向', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125228', '140', '0', '未隔离', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125229', '140', '1', '已隔离', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125230', '141', '01', '普通车道', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125231', '141', '02', '小车道', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125232', '141', '03', '大客车道', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125233', '141', '04', '货车道', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125234', '141', '05', '超车道', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125235', '141', '06', '行车道', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125236', '141', '07', '公交专用车道', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125237', '141', '08', '应急车道', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125238', '141', '09', '非机动车车道', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125239', '141', '11', '城市左转及调头车道', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125240', '141', '12', '城市左转车道', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125241', '141', '13', '城市直行车道', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125242', '141', '14', '城市右转车道', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125243', '141', '15', '城市右转及直行车道', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125244', '142', '0', '限行', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125245', '142', '1', '不限行', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125246', '143', '0', '无', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125247', '143', '1', '有', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125248', '144', '0', '无', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125249', '144', '1', '有', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125250', '145', '0', '无', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125251', '145', '1', '有', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125252', '146', '1', '进路口', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125253', '146', '2', '出路口', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125254', '147', '1', '三枝分叉口', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125255', '147', '2', '四枝分叉口', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125256', '147', '3', '多枝分叉口', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125257', '147', '4', '环形交叉口', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125258', '147', '5', '匝道口', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125259', '148', '21', '普通路段', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125260', '148', '22', '高架路段', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125730', '504', '2', '未确认', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124895', '230', '0', '不免处罚', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124896', '230', '1', '免处罚', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124897', '231', '0', '不保护', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124898', '231', '1', '保护kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124899', '232', '0', '未确认', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124900', '232', '1', '已确认', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124901', '233', '0', '未转入', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124902', '233', '1', '已转入', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125666', '234', '1', '已确认', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125667', '234', '2', '证据不足', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125668', '234', '3', '识别错误', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124906', '235', '0', '不是假牌', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124907', '235', '1', '是假牌', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124908', '235', '2', '不确定', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124909', '236', '1', '假牌', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124910', '236', '2', '套牌', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124911', '237', '0', '未布控', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124912', '237', '1', '已布控', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124942', '312', '08', '其它', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125013', '212', '00', '公安厅/局', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125014', '212', '24', '海关（行业）', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125015', '212', '26', '反邪教', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125016', '212', '27', '反恐怖', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125017', '212', '31', '办公厅（室）', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125018', '212', '32', '纪委', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125019', '212', '33', '监察', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125020', '212', '34', '督查', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125021', '212', '35', '政工', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125022', '212', '36', '人事训练', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125023', '212', '38', '机关党委', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125024', '212', '39', '离退休干部中心', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125025', '212', '40', '机关服务中心', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125026', '212', '41', '审计', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125027', '212', '89', '出版社', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125028', '212', '90', '院校', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125029', '212', '91', '研究所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125030', '212', '92', '医院', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125031', '212', '93', '训练基地', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125032', '212', '94', '边检', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125033', '212', '95', '巡警', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125034', '212', '96', '派出所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125035', '212', '97', '金盾办', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125036', '212', '98', '科技委', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125665', '234', '0', '未确认', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125160', '070', '1', '上行', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125161', '070', '2', '下行', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125750', '508', '41', '道路通行状态', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125660', '482', '1', '视频', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125661', '482', '2', '线圈', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125662', '482', '3', '雷达', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('42cfbdbe1d794e20bfb7c05311e2807e', '001', '皖AAAAAA', '军车 ', '0', null, '红色车  ', '0');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124812', '228', '1', '本市', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124813', '228', '2', '本省', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124814', '228', '3', '外省', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124815', '228', '4', '军车', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124816', '228', '5', '警车', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124817', '229', '1', '红名单车辆', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124818', '229', '2', '大车', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120000', '001', 'K11', '大型普通客车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120001', '001', 'K12', '大型双层客车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120002', '001', 'K13', '大型卧铺客车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120003', '001', 'K14', '大型铰接客车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120004', '001', 'K15', '大型越野客车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120005', '001', 'K21', '中型普通客车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120006', '001', 'K22', '中型双层客车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120007', '001', 'K23', '中型卧铺客车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120008', '001', 'K24', '中型铰接客车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120009', '001', 'K25', '中型越野客车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120010', '001', 'K31', '小型普通客车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120011', '001', 'K32', '小型越野客车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120012', '001', 'K33', '轿车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120013', '001', 'K41', '微型普通客车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120014', '001', 'K42', '微型越野客车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120015', '001', 'K43', '微型轿车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120016', '001', 'H11', '重型普通货车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120017', '001', 'H12', '重型厢式货车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120018', '001', 'H13', '重型封闭货车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120019', '001', 'H14', '重型罐式货车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120020', '001', 'H15', '重型平板货车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120021', '001', 'H16', '重型集装厢车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120022', '001', 'H17', '重型自卸货车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120023', '001', 'H18', '重型特殊结构货车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120024', '001', 'H21', '中型普通货车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120025', '001', 'H22', '中型厢式货车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120026', '001', 'H23', '中型封闭货车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120027', '001', 'H24', '中型罐式货车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120028', '001', 'H25', '中型平板货车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120029', '001', 'H26', '中型集装厢车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120030', '001', 'H27', '中型自卸货车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120031', '001', 'H28', '中型特殊结构货车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120032', '001', 'H31', '轻型普通货车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120033', '001', 'H32', '轻型厢式货车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120034', '001', 'H33', '轻型封闭货车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120035', '001', 'H34', '轻型罐式货车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120036', '001', 'H35', '轻型平板货车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120037', '001', 'H37', '轻型自卸货车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120038', '001', 'H38', '轻型特殊结构货车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120039', '001', 'H41', '微型普通货车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120040', '001', 'H42', '微型厢式货车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120041', '001', 'H43', '微型封闭货车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120042', '001', 'H44', '微型罐式货车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120043', '001', 'H45', '微型自卸货车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120044', '001', 'H46', '微型特殊结构货车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120045', '001', 'H51', '低速普通货车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120046', '001', 'H52', '低速厢式货车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120047', '001', 'H53', '低速罐式货车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120048', '001', 'H54', '低速自卸货车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120049', '001', 'Q11', '重型半挂牵引车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120050', '001', 'Q21', '中型半挂牵引车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120051', '001', 'Q31', '轻型半挂牵引车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120052', '001', 'Z', '专项作业车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120053', '001', 'Z11', '大型专项作业车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120054', '001', 'Z21', '中型专项作业车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120055', '001', 'Z31', '小型专项作业车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120056', '001', 'Z41', '微型专项作业车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120057', '001', 'Z51', '重型专项作业车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120058', '001', 'Z71', '轻型专项作业车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120059', '001', 'D11', '无轨电车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120060', '001', 'D12', '有轨电车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120061', '001', 'M11', '普通正三轮摩托车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120062', '001', 'M12', '轻便正三轮摩托车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120063', '001', 'M13', '正三轮载客摩托车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120064', '001', 'M14', '正三轮载货摩托车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120065', '001', 'M15', '侧三轮摩托车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120066', '001', 'M21', '普通二轮摩托车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120067', '001', 'M22', '轻便二轮摩托车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120068', '001', 'N11', '三轮汽车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120069', '001', 'T11', '大型轮式拖拉机', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120070', '001', 'T21', '小型轮式拖拉机', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120071', '001', 'T22', '手扶拖拉机', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120072', '001', 'T23', '手扶变形运输机', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120073', '001', 'J11', '轮式装载机械', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120074', '001', 'J12', '轮式挖掘机械', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120075', '001', 'J13', '轮式平地机械', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120076', '001', 'G11', '重型普通全挂车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120077', '001', 'G12', '重型厢式全挂车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120078', '001', 'G13', '重型罐式全挂车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120079', '001', 'G14', '重型平板全挂车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120080', '001', 'G15', '重型集装箱全挂车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120081', '001', 'G16', '重型自卸全挂车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120082', '001', 'G21', '中型普通全挂车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120083', '001', 'G22', '中型厢式全挂车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120084', '001', 'G23', '中型罐式全挂车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120085', '001', 'G24', '中型平板全挂车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120086', '001', 'G25', '中型集装箱全挂车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120087', '001', 'G26', '中型自卸全挂车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120088', '001', 'G31', '轻型普通全挂车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120089', '001', 'G32', '轻型厢式全挂车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120090', '001', 'G33', '轻型罐式全挂车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120091', '001', 'G34', '轻型平板全挂车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120092', '001', 'G35', '轻型自卸全挂车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120093', '001', 'B11', '重型普通半挂车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120094', '001', 'B12', '重型厢式半挂车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120095', '001', 'B13', '重型罐式半挂车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120096', '001', 'B14', '重型平板半挂车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120097', '001', 'B15', '重型集装箱半挂车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120098', '001', 'B16', '重型自卸半挂车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120099', '001', 'B17', '重型特殊结构半挂车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120100', '001', 'B21', '中型普通半挂车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120101', '001', 'B22', '中型厢式半挂车', '0', null, null, '0');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125700', '510', '1', '气象', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125701', '510', '2', '事故', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125702', '510', '3', '施工', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125703', '510', '4', '特勤保卫', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125810', '412', '4', '异常', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125841', '434', '0', '拥堵', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125842', '434', '1', '停车', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125843', '434', '2', '逆行', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125844', '434', '3', '行人', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125845', '434', '4', '遗留物，抛洒物碎片', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125846', '434', '5', '烟雾', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125847', '434', '6', '压线', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125848', '434', '7', '黑名单数据', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125849', '434', '8', '超速', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125850', '434', '9', '变道', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125851', '434', '10', '掉头', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125037', '238', '1', '车驾管同步', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125038', '238', '2', '执法服务站登记', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('126172', '485', '1', '海康平台', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('126173', '485', '2', '海康直连设备', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('126174', '485', '3', '大华平台', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('126175', '485', '4', '大华直连设备', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('126176', '485', '5', '超远平台', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125477', '465', '3', '未确认', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('126193', '487', '0', '正常', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('126194', '487', '1', '离线', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('126195', '487', '2', '异常', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('126210', '228', '0', '未知', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120920', '001', 'B23', '中型罐式半挂车', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120921', '001', 'B24', '中型平板半挂车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120922', '001', 'B25', '中型集装箱半挂车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120923', '001', 'B26', '中型自卸半挂车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120924', '001', 'B27', '中型特殊结构半挂车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120925', '001', 'B31', '轻型普通半挂车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120926', '001', 'B32', '轻型厢式半挂车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120928', '001', 'B34', '轻型平板半挂车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120929', '001', 'B35', '轻型自卸半挂车', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120930', '001', 'X99', '其它', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120931', '002', '01', '大型汽车', '0', '1', '黄底黑字(含02式号牌部分)', '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120932', '002', '02', '小型汽车', '0', '2', '蓝底白字(含02式号牌部分)', '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120933', '002', '03', '使馆汽车', '0', '9', '黑底白字、红“使”字', '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120934', '002', '04', '领馆汽车', '0', '9', '黑底白字、红“领”字', '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120935', '002', '05', '境外汽车', '0', '9', '黑底白/红字', '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120936', '002', '06', '外籍汽车', '0', '3', '黑底白字', '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120937', '002', '07', '两、三轮摩托车', '0', '9', '黄底黑字', '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120938', '002', '08', '轻便摩托车', '0', '9', '蓝底白字', '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120939', '002', '09', '使馆摩托车', '0', '9', '黑底白字、红“使”字', '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120940', '002', '10', '领馆摩托车', '0', '9', '黑底白字、红“领”字', '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120941', '002', '11', '境外摩托车', '0', '9', '黑底白字', '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120942', '002', '12', '外籍摩托车', '0', '9', '黑底白字', '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120943', '002', '13', '农用运输车', '0', '9', '黄底黑字黑框线，已按《道路交通安全法》取消农用运输车，不再发放', '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120944', '002', '14', '拖拉机', '0', '9', '黄底黑字', '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120945', '002', '15', '挂车', '0', '4', '黄底黑字黑框线', '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120946', '002', '16', '教练汽车', '0', '5', '黄底黑字黑框线', '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120947', '002', '17', '教练摩托车', '0', '9', '黄底黑字黑框线', '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120948', '002', '18', '试验汽车', '0', '9', null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120949', '002', '19', '试验摩托车', '0', '9', null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120950', '002', '20', '临时入境汽车', '0', '9', '白底红字黑“临时入境”', '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120951', '002', '21', '临时入境摩托车', '0', '9', '白底红字黑“临时入境”', '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120952', '002', '22', '临时行驶车', '0', '9', '白底黑字黑框线', '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120953', '002', '23', '警用汽车', '0', '9', null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120954', '002', '24', '警用摩托', '0', '9', null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120955', '002', '99', '其他', '0', '9', null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120956', '003', '0', '白牌', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120957', '003', '1', '黄牌', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120958', '003', '2', '蓝牌', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120959', '003', '3', '黑牌', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120960', '003', '4', '其他', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120962', '004', '01', '大车', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120963', '004', '02', '小车', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120964', '004', '03', '中型车', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120965', '004', '05', '摩托车', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120966', '004', '06', '超长车', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120967', '006', 'A', '白', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120968', '006', 'B', '灰', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120969', '006', 'C', '黄', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120970', '006', 'D', '粉', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120971', '006', 'E', '红', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120972', '006', 'F', '紫', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120973', '006', 'G', '绿', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120974', '006', 'H', '蓝', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120975', '006', 'I', '棕', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120976', '006', 'J', '黑', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('120977', '006', 'Z', '其他', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122337', '008', '沪H', '上海市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122338', '008', '沪I', '上海市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122339', '008', '沪J', '上海市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122340', '008', '沪K', '上海市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122341', '008', '沪L', '上海市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122342', '008', '沪M', '上海市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122343', '008', '沪N', '上海市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122344', '008', '沪O', '上海市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122345', '008', '沪P', '上海市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122346', '008', '沪Q', '上海市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122347', '008', '沪R', '上海市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122348', '008', '沪S', '上海市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122349', '008', '沪T', '上海市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122350', '008', '沪U', '上海市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122351', '008', '沪V', '上海市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122352', '008', '沪W', '上海市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122353', '008', '沪X', '上海市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122354', '008', '沪Y', '上海市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122355', '008', '沪Z', '上海市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122356', '008', '苏A', '南京市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122357', '008', '苏B', '无锡市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122358', '008', '苏C', '徐州市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122359', '008', '苏D', '常州市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122360', '008', '苏E', '苏州市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122361', '008', '苏F', '南通市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122362', '008', '苏G', '连云港市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122363', '008', '苏H', '淮安市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122364', '008', '苏J', '盐城市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122365', '008', '苏K', '扬州市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122366', '008', '苏L', '镇江市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122367', '008', '苏M', '泰州市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122368', '008', '苏N', '宿迁市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122369', '008', '苏O', '江苏省公安厅车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122370', '008', '浙A', '杭州市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122371', '008', '浙B', '宁波市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122372', '008', '浙C', '温州市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122373', '008', '浙D', '绍兴市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122374', '008', '浙E', '湖州市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122375', '008', '浙F', '嘉兴市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122376', '008', '浙G', '金华市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122377', '008', '浙H', '衢州市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122378', '008', '浙J', '台州市公安局车辆管理所', '1', null, null, '1');
commit;
prompt 1000 records committed...
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122379', '008', '浙K', '丽水市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122380', '008', '浙L', '舟山市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122381', '008', '浙O', '浙江省公安厅车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122382', '008', '皖A', '合肥市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122383', '008', '皖B', '芜湖市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122384', '008', '皖C', '蚌埠市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122385', '008', '皖D', '淮南市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122386', '008', '皖E', '马鞍山市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122387', '008', '皖F', '淮北市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122388', '008', '皖G', '铜陵市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122389', '008', '皖H', '安庆市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122390', '008', '皖J', '黄山市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122391', '008', '皖K', '阜阳市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122392', '008', '皖L', '宿州市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122393', '008', '皖M', '滁州市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122394', '008', '皖N', '六安市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122395', '008', '皖O', '安徽省公安厅车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122396', '008', '皖P', '宣城市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122397', '008', '皖Q', '巢湖市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122398', '008', '皖R', '池州市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122399', '008', '皖S', '亳州市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122400', '008', '闽A', '福州市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122401', '008', '闽B', '莆田市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122402', '008', '闽C', '泉州市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122403', '008', '闽D', '厦门市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122404', '008', '闽E', '漳州市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122405', '008', '闽F', '龙岩市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122406', '008', '闽G', '三明市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122407', '008', '闽H', '南平市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122408', '008', '闽J', '宁德市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122409', '008', '闽K', '福州市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122410', '008', '闽O', '福建省公安厅车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122411', '008', '赣A', '南昌市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122412', '008', '赣B', '赣州市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122413', '008', '赣C', '宜春市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122414', '008', '赣D', '吉安市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122415', '008', '赣E', '上饶市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122416', '008', '赣F', '抚州市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122417', '008', '赣G', '九江市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122418', '008', '赣H', '景德镇市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122419', '008', '赣J', '萍乡市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122420', '008', '赣K', '新余市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122421', '008', '赣L', '鹰潭市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122422', '008', '赣M', '南昌市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122423', '008', '赣O', '江西省公安厅车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122424', '008', '鲁A', '济南市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122425', '008', '鲁B', '青岛市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122426', '008', '鲁C', '淄博市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122427', '008', '鲁D', '枣庄市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122428', '008', '鲁E', '东营市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122429', '008', '鲁F', '烟台市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122430', '008', '鲁G', '潍坊市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122431', '008', '鲁H', '济宁市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122432', '008', '鲁J', '泰安市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122433', '008', '鲁K', '威海市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122434', '008', '鲁L', '日照市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122435', '008', '鲁M', '滨州市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122436', '008', '鲁N', '德州市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122437', '008', '鲁O', '山东省公安厅车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122438', '008', '鲁P', '聊城市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122439', '008', '鲁Q', '临沂市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122440', '008', '鲁R', '菏泽市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122441', '008', '鲁S', '莱芜市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122442', '008', '鲁U', '青岛市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122443', '008', '鲁V', '潍坊市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122444', '008', '鲁Y', '烟台市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122445', '008', '豫A', '郑州市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122446', '008', '豫B', '开封市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122447', '008', '豫C', '洛阳市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122448', '008', '豫D', '平顶山市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122449', '008', '豫E', '安阳市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122450', '008', '豫F', '鹤壁市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122451', '008', '豫G', '新乡市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122452', '008', '豫H', '焦作市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122453', '008', '豫J', '濮阳市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122454', '008', '豫K', '许昌市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122455', '008', '豫L', '漯河市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122456', '008', '豫M', '三门峡市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122457', '008', '豫N', '商丘市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122458', '008', '豫O', '河南省公安厅车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122459', '008', '豫P', '周口市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122460', '008', '豫Q', '驻马店市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122461', '008', '豫R', '南阳市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122462', '008', '豫S', '信阳市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122463', '008', '豫U', '济源市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122464', '008', '鄂A', '武汉市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122465', '008', '云D', '曲靖市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122466', '008', '云E', '楚雄彝族自治州公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122467', '008', '云F', '玉溪市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122468', '008', '云G', '红河哈尼族彝族自治州公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122469', '008', '云H', '文山壮族苗族自治州公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122470', '008', '云J', '普洱市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122471', '008', '云K', '西双版纳傣族自治州公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122472', '008', '云L', '大理白族自治州公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122473', '008', '云M', '保山市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122474', '008', '云N', '德宏傣族景颇族自治州公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122475', '008', '云O', '云南省公安厅车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122938', '008', '鄂B', '黄石市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122939', '008', '鄂C', '十堰市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122940', '008', '鄂D', '荆州市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122941', '008', '鄂E', '宜昌市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122942', '008', '鄂F', '襄樊市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122943', '008', '鄂G', '鄂州市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122944', '008', '鄂H', '荆门市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122945', '008', '鄂J', '黄冈市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122946', '008', '鄂K', '孝感市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122947', '008', '鄂L', '咸宁市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122948', '008', '鄂M', '仙桃市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122949', '008', '鄂N', '潜江市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122950', '008', '鄂O', '湖北省公安厅车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122951', '008', '鄂P', '神农架林区公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122952', '008', '鄂Q', '恩施土家族苗族自治州公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122953', '008', '鄂R', '天门市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122954', '008', '鄂S', '随州市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122955', '008', '湘A', '长沙市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122956', '008', '湘B', '株州市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122957', '008', '湘C', '湘潭市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122958', '008', '湘D', '衡阳市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122959', '008', '湘E', '邵阳市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122960', '008', '湘F', '岳阳市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122961', '008', '湘G', '张家界市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122962', '008', '湘H', '益阳市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122963', '008', '湘J', '常德市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122964', '008', '湘K', '娄底市公安处车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122965', '008', '湘L', '郴州市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122966', '008', '湘M', '永州市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122967', '008', '湘N', '怀化市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122968', '008', '湘O', '湖南省公安厅车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122969', '008', '湘S', '长沙市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122970', '008', '湘U', '湘西土家族苗族自治州公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122971', '008', '粤A', '广州市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122972', '008', '粤B', '深圳市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122973', '008', '粤C', '珠海市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122974', '008', '粤D', '汕头市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122975', '008', '粤E', '佛山市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122976', '008', '粤F', '韶关市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122977', '008', '粤G', '湛江市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122978', '008', '粤H', '肇庆市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122979', '008', '粤J', '江门市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122980', '008', '粤K', '茂名市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122981', '008', '粤L', '惠州市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122982', '008', '粤M', '梅州市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122983', '008', '粤N', '汕尾市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122984', '008', '粤O', '广东省公安厅车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122985', '008', '粤P', '河源市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122986', '008', '粤Q', '阳江市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122987', '008', '粤R', '清远市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122988', '008', '粤S', '东莞市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122989', '008', '粤T', '中山市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122990', '008', '粤U', '潮州市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122991', '008', '粤V', '揭阳市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122992', '008', '粤W', '云浮市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122993', '008', '粤X', '佛山市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122994', '008', '粤Y', '佛山市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122995', '008', '粤Z', '广东省公安厅交通管理局车辆管理所', '1', null, '港澳入出境车号牌', '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122996', '008', '桂A', '南宁市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122997', '008', '桂B', '柳州市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122998', '008', '桂C', '桂林市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122999', '008', '桂D', '梧州市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123000', '008', '桂E', '北海市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123001', '008', '桂F', '崇左市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123002', '008', '桂G', '来宾市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123003', '008', '桂H', '桂林市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123004', '008', '桂J', '贺州市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123005', '008', '桂K', '玉林市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123006', '008', '桂L', '百色市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123007', '008', '桂M', '河池市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123008', '008', '桂N', '钦州市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123009', '008', '桂O', '广西壮族自治区公安厅车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123010', '008', '桂P', '防城港市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123011', '008', '桂R', '贵港市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123012', '008', '琼A', '海口市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123013', '008', '琼B', '三亚市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123014', '008', '琼C', '海南省公安厅交通警察总队琼北车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123015', '008', '琼D', '海南省公安厅交通警察总队琼南车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123016', '008', '琼E', '洋浦经济开发区公安局交通警察支队车辆管理所（海南省公安厅交通警察总队车辆管理所代管）', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123017', '008', '琼O', '海南省公安厅车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123018', '008', '渝A', '重庆市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123019', '008', '渝B', '重庆市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123020', '008', '渝C', '重庆市江津区永川区合川区公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123021', '008', '渝F', '重庆市万州区公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123022', '008', '渝G', '重庆市涪陵区公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123023', '008', '渝H', '重庆市黔江区公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123024', '008', '川A', '成都市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123025', '008', '川B', '绵阳市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123026', '008', '川C', '自贡市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123027', '008', '川D', '攀枝花市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123028', '008', '川E', '泸州市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123029', '008', '川F', '德阳市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123030', '008', '川H', '广元市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123031', '008', '川J', '遂宁市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123032', '008', '川K', '内江市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123033', '008', '川L', '乐山市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123034', '008', '川M', '资阳市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123035', '008', '川O', '四川省公安厅车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123036', '008', '川Q', '宜宾市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123037', '008', '川R', '南充市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123038', '008', '川S', '达州市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123039', '008', '川T', '雅安市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123040', '008', '川U', '阿坝藏族羌族自治州公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123041', '008', '川V', '甘孜藏族自治州公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123042', '008', '川W', '凉山彝族自治州公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123043', '008', '川X', '广安市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123044', '008', '川Y', '巴中市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123045', '008', '川Z', '眉山市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123046', '008', '贵A', '贵阳市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123047', '008', '贵B', '六盘水市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123048', '008', '贵C', '遵义市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123049', '008', '贵D', '铜仁地区公安处车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123050', '008', '贵E', '黔西南布依族苗族自治州公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123051', '008', '贵F', '毕节地区公安处车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123052', '008', '贵G', '安顺市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123053', '008', '贵H', '黔东南苗族侗族自治州公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123054', '008', '贵J', '黔南布依族苗族自治州公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123055', '008', '贵O', '贵州省公安厅车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123056', '008', '云A', '昆明市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123057', '008', '云C', '昭通市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122476', '008', '云P', '丽江市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122477', '008', '云Q', '怒江傈僳族自治州公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122478', '008', '云R', '迪庆藏族自治州公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122479', '008', '云S', '临沧市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122480', '008', '藏A', '拉萨市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122481', '008', '藏B', '昌都地区公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122482', '008', '藏C', '山南地区公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122483', '008', '藏D', '日喀则地区公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122484', '008', '藏E', '那曲地区公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122485', '008', '藏F', '阿里地区公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122486', '008', '藏G', '林芝地区公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122487', '008', '藏H', '西藏自治区公安厅驻四川双流车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122488', '008', '藏J', '西藏自治区公安厅驻青海格尔木交通警察支队车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122489', '008', '藏O', '西藏自治区公安厅车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122490', '008', '陕A', '西安市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122491', '008', '陕B', '铜川市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122492', '008', '陕C', '宝鸡市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122493', '008', '陕D', '咸阳市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122494', '008', '陕E', '渭南市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122495', '008', '陕F', '汉中市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122496', '008', '陕G', '安康市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122497', '008', '陕H', '商洛市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122498', '008', '陕J', '延安市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122499', '008', '陕K', '榆林市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122500', '008', '陕O', '陕西省公安厅车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122501', '008', '陕V', '陕西省公安厅车辆管理所杨凌分所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122502', '008', '甘A', '兰州市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122503', '008', '甘B', '嘉峪关市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122504', '008', '甘C', '金昌市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122505', '008', '甘D', '白银市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122506', '008', '甘E', '天水市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122507', '008', '甘F', '酒泉市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122508', '008', '甘G', '张掖市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122509', '008', '甘H', '武威市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122510', '008', '甘J', '定西市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122511', '008', '甘K', '陇南市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122512', '008', '甘L', '平凉市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122513', '008', '甘M', '庆阳市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122514', '008', '甘N', '临夏回族自治州公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122515', '008', '甘O', '甘肃省公安厅车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122516', '008', '甘P', '甘南藏族自治州公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122517', '008', '青A', '西宁市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122518', '008', '青B', '海东地区公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122519', '008', '青C', '海北藏族自治州公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122520', '008', '青D', '黄南藏族自治州公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122521', '008', '青E', '海南藏族自治州公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122522', '008', '青F', '果洛藏族自治州公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122523', '008', '青G', '玉树藏族自治州公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122524', '008', '青H', '海西蒙古族藏族自治州公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122525', '008', '青O', '青海省公安厅车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122526', '008', '宁A', '银川市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122527', '008', '宁B', '石嘴山市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122528', '008', '宁C', '吴忠市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122529', '008', '宁D', '固原市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122530', '008', '宁E', '中卫市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122531', '008', '宁O', '宁夏回族自治区公安厅车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122532', '008', '新A', '乌鲁木齐市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122533', '008', '新B', '昌吉回族自治州公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122534', '008', '新C', '石河子市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122535', '008', '新D', '奎屯市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122536', '008', '新E', '博尔塔拉蒙古自治州公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122537', '008', '新F', '伊犁哈萨克自治州公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122538', '008', '新G', '塔城地区公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122539', '008', '新H', '阿勒泰地区公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122540', '008', '新J', '克拉玛依市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122541', '008', '新K', '吐鲁番地区公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122542', '008', '新L', '哈密地区公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122543', '008', '新M', '巴音郭楞蒙古自治州公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122544', '008', '新N', '阿克苏地区公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122545', '008', '新O', '新疆维吾尔自治区公安厅车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122546', '008', '新P', '克孜勒苏尔克孜自治州公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122547', '008', '新Q', '喀什地区公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122548', '008', '新R', '和田地区公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122549', '009', 'A', '居民身份证', '0', null, '中华人民共和国居民身份证', '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122550', '009', 'B', '组织机构代码证书', '0', null, '机关、企业、事业单位、社会团体、外商在华独资企业、外商驻华机构的身份证明', '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122551', '009', 'C', '军官证', '0', null, '现役军官身份证明和警官证、文职证', '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122552', '009', 'D', '士兵证', '0', null, '现役士兵身份证明', '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122553', '009', 'E', '军官离退休证', '0', null, '退休军官身份证明', '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122554', '009', 'F', '境外人员身份证', '0', null, '指香港、澳门特别行政区、台湾地区居民和外国人入境的身份证明', '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122555', '009', 'G', '外交人员身份证件', '0', null, '指外国驻华使馆、领馆人员、国际组织驻华代表机构人员的身份证明，是外交部核发的有效身份证件', '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122556', '009', 'H', '居民户口簿', '0', null, '中华人民共和国居民户口簿', '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122557', '009', 'K', '居住暂住证明', '0', null, '指在暂住地居住的内地居民，由公安机关核发的居住暂住证明', '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122558', '009', 'L', '驻华机构证明', '0', null, '各国驻华使领馆和外国驻华办事机构的身份证明', '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('122559', '009', 'Z', '其他证件', '0', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('126331', '491', '2', '区间', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('126330', '491', '1', '设备', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123886', '010', 'D', '出租客运', '1', null, '以行驶里程和时间计费，将乘客运载至其指定地点的、以获取利润为目的的机动车', '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123887', '010', 'E', '旅游客运', '1', null, '专门运载游客的、以获取利润为目的的机动车', '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123888', '010', 'F', '货运', '1', null, '专门从事货物运输的、以获取利润为目的的机动车', '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123889', '010', 'G', '租赁', '1', null, '专门租赁给其他单位或者个人使用，以租用时间或者租用里程计费的、以获取利润为目的的机动车', '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123890', '010', 'H', '警用', '1', null, '"a)公安机关车辆b)国家安全机车辆c)人民检察院囚车d)人民法院囚车、刑场指挥车、死刑执行车、法医勘察车、强制执行用车和其他警务用车e)司法行政机囚车或专用车辆和追辑逃犯的车辆"', '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123891', '010', 'I', '消防', '1', null, '公安消防部队和其他消防部门用于灭火的专用机动车和现场指挥机动车', '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123892', '010', 'J', '救护', '1', null, '急救、医疗机构和卫生防疫部门用于抢救危重病人或处理紧急疫情的专用机动车;公共卫生事件中用于现场医疗救援和辅助现场医疗救援的专用车辆', '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123893', '010', 'K', '工程抢险车', '1', null, '防汛、水利、电力、矿山、城建、交通、铁道等部门用于抢修公用设施、抢救人民生命财产的专用机动车和现场指挥机动车', '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123894', '010', 'L', '营转非', '1', null, '原为营运车辆，现改为非营运车辆', '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123895', '010', 'M', '出租转非', '1', null, '原为出租车，现改为非营运车辆', '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123896', '010', 'N', '禁行大客车', '1', null, '禁行大客车', '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123897', '010', 'O', '营运车辆', '1', null, '营运车辆', '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123898', '010', 'Z', '其他', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123899', '011', '0', '大车占道', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123900', '011', '1', '超高速', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123901', '011', '2', '超低速', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123902', '011', '3', '逆行', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123903', '011', '4', '闯红灯', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123904', '011', '5', '压黄线', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123905', '011', '6', '违章停车', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123906', '011', '7', '区间超速', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123907', '011', '8', '禁行', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123908', '011', '9', '其他', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123909', '011', 'c', '不按导向行驶', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123910', '011', 'd', '压线', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123911', '011', 'a', '违法占道', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123912', '011', 'b', '遮挡号牌', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123913', '012', '48,2', '江铃-域虎', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123914', '012', '48,3', '江铃-宝典', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123915', '012', '48,4', '江铃-宝威', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123916', '012', '48,5', '江铃-福特新世代全顺', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123917', '012', '48,6', '江铃-顺达', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123918', '012', '48,7', '江铃-驭胜', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123919', '012', '21,0', '沃尔沃-其它', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123920', '012', '21,1', '沃尔沃-C30', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123921', '012', '21,2', '沃尔沃-S40', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123922', '012', '21,3', '沃尔沃-S60', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123923', '012', '21,4', '沃尔沃-S80L', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123924', '012', '21,5', '沃尔沃-XC60', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123925', '012', '21,6', '沃尔沃-XC90', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123926', '012', '56,0', '海格-其它', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123927', '012', '56,1', '海格-H8', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123928', '012', '56,2', '海格-H92', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123929', '012', '56,3', '海格-V7V8', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123930', '012', '56,4', '海格-客车车型5', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123931', '012', '56,5', '海格-海格H7V', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123932', '012', '56,6', '海格-龙威', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123933', '012', '70,1', '长丰猎豹-CS7', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123934', '012', '70,2', '长丰猎豹-奇兵', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123935', '012', '70,3', '长丰猎豹-猎豹CS6', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123936', '012', '70,4', '长丰猎豹-福铃皮卡', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123937', '012', '70,5', '长丰猎豹-飞扬皮卡', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123938', '012', '70,6', '长丰猎豹-飞腾', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123939', '012', '70,7', '长丰猎豹-黑金刚', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123940', '012', '89,0', '陆风-其它', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123941', '012', '89,1', '陆风-X6', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123942', '012', '89,2', '陆风-陆风X5', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123943', '012', '89,3', '陆风-陆风X8', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123944', '012', '89,4', '陆风-风华', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123945', '012', '89,5', '陆风-风尚', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123946', '012', '75,0', '陕汽重卡-其它', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123947', '012', '75,1', '陕汽重卡-奥龙', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123948', '012', '75,2', '陕汽重卡-德龙', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123949', '012', '47,0', '雷诺-其它', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123950', '012', '47,1', '雷诺-塔菲克', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123951', '012', '47,2', '雷诺-梅甘娜', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123952', '012', '47,3', '雷诺-科雷傲', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123953', '012', '47,4', '雷诺-风景', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123954', '012', '47,5', '雷诺-风朗', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123955', '012', '62,0', '黄海-其他', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123956', '012', '62,1', '黄海-傲骏', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123957', '012', '62,2', '黄海-大柴神', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123958', '012', '62,3', '黄海-客车车型4', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123959', '012', '62,4', '黄海-挑战者', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123960', '012', '48,1', '江铃-凯运', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123961', '012', '62,5', '黄海-旗胜V3', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123962', '012', '62,6', '黄海-翱龙CUV', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123963', '012', '62,7', '黄海-领航者', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123964', '012', '62,8', '黄海-风驰-A款', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123965', '012', '33,0', '吉利英伦-其它', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123966', '012', '33,1', '吉利英伦-SC3', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123967', '012', '33,2', '吉利英伦-SC5', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123968', '012', '33,3', '吉利英伦-SC6-2012', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123969', '012', '33,4', '吉利英伦-SC7', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123970', '012', '33,5', '吉利英伦-SX7', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123971', '012', '33,6', '吉利英伦-金刚', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123972', '012', '33,7', '吉利英伦-金鹰', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123973', '012', '24,0', '吉利帝豪-其它', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123974', '012', '24,1', '吉利帝豪-EC7', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123975', '012', '31,0', '江淮-其它', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123976', '012', '31,1', '江淮-同悦', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123977', '012', '31,2', '江淮-和悦', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123978', '012', '31,3', '江淮-好运', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123979', '012', '31,4', '江淮-威铃', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123980', '012', '31,5', '江淮-客车', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123981', '012', '31,6', '江淮-宾悦', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123982', '012', '31,7', '江淮-帅铃', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123983', '012', '31,8', '江淮-康铃-', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123984', '012', '31,9', '江淮-悦悦', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123985', '012', '31,10', '江淮-星锐', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123986', '012', '31,11', '江淮-格尔发', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123987', '012', '31,12', '江淮-瑞铃', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123988', '012', '31,13', '江淮-瑞风', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123989', '012', '31,14', '江淮-瑞鹰', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123990', '012', '31,15', '江淮-骏铃', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123991', '012', '48,0', '江铃-其它', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123992', '012', '81,1', '申龙客车-客车车型4', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123993', '012', '114,0', '瑞麒-其它', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123994', '012', '114,1', '瑞麒-G3', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123995', '012', '114,2', '瑞麒-G5', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123996', '012', '114,3', '瑞麒-M1', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123997', '012', '114,4', '瑞麒-X1', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123998', '012', '81,0', '申龙客车-其它', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123999', '012', '39,0', '福田-其它', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124000', '012', '39,1', '福田-奥铃', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124001', '012', '39,2', '福田-欧曼', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124002', '012', '39,3', '福田-欧辉客车', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124003', '012', '39,4', '福田-欧马可', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124004', '012', '39,5', '福田-萨普', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124005', '012', '39,6', '福田-蒙派克', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124006', '012', '39,7', '福田-迷迪', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124007', '012', '39,8', '福田-风景系列', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124008', '012', '120,0', '福迪-其它', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124009', '012', '120,1', '福迪-小超人皮卡', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124010', '012', '120,2', '福迪-探索者6', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124011', '012', '120,3', '福迪-雄狮F16皮卡', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124012', '012', '229,0', '红旗-其它', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124013', '012', '229,1', '红旗-明仕', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124014', '012', '107,0', '纳智捷-其它', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124015', '012', '107,1', '纳智捷-大7-SUV', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124016', '012', '139,0', '美亚(夏利)-其它', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124017', '012', '139,1', '美亚(夏利)-奇兵', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124018', '012', '200,0', '舒驰客车-客车-其它', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124019', '012', '200,1', '舒驰客车-客车-A款', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124020', '012', '65,0', '英菲尼迪-其它', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124021', '012', '65,1', '英菲尼迪-EX', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124022', '012', '65,2', '英菲尼迪-FX', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124023', '012', '65,3', '英菲尼迪-G', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124024', '012', '65,4', '英菲尼迪-JX', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124025', '012', '16,0', '荣威-其它', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124026', '012', '16,1', '荣威-350', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124027', '012', '16,2', '荣威-550', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124028', '012', '16,3', '荣威-750', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124029', '012', '16,4', '荣威-950', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124030', '012', '24,2', '吉利帝豪-EC8', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124031', '012', '66,0', '吉利全球鹰-其它', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124032', '012', '66,1', '吉利全球鹰-GC7', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124033', '012', '66,2', '吉利全球鹰-GX2', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124034', '012', '66,3', '吉利全球鹰-GX7', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124035', '012', '66,4', '吉利全球鹰-熊猫', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124036', '012', '66,5', '吉利全球鹰-自由舰', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124037', '012', '66,6', '吉利全球鹰-远景', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124038', '012', '135,0', '江南-其它', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124039', '012', '135,1', '江南-江南TT', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124040', '012', '127,0', '永源-其它', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124041', '012', '127,1', '永源-A380', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124042', '012', '52,0', '昌河-其它', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124043', '012', '52,1', '昌河-CH6321', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124044', '012', '52,2', '昌河-昌河新单双排', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124045', '012', '52,3', '昌河-爱迪尔', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124046', '012', '52,4', '昌河-福瑞达面包车', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124047', '012', '32,0', '斯巴鲁-其它', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124048', '012', '32,1', '斯巴鲁-傲虎', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124049', '012', '32,2', '斯巴鲁-力狮轿车', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124050', '012', '32,3', '斯巴鲁-斯巴鲁XV', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124051', '012', '32,4', '斯巴鲁-森林人', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124052', '012', '116,0', '捷豹-其它', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124053', '012', '116,1', '捷豹-XF', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124054', '012', '116,2', '捷豹-XJ', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124055', '012', '36,0', '庆铃-其它', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124056', '012', '36,1', '庆铃-中型商用车', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124057', '012', '36,2', '庆铃-五十铃皮卡', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124058', '012', '36,3', '庆铃-竞技者', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124059', '012', '112,0', '广汽吉奥-其它', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124060', '012', '112,1', '广汽吉奥-G3', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124061', '012', '112,2', '广汽吉奥-G5', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124062', '012', '112,3', '广汽吉奥-帅舰', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124063', '012', '112,4', '广汽吉奥-星旺M1', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124064', '012', '112,5', '广汽吉奥-财运500', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124065', '012', '88,0', '广汽传祺-其它', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124066', '012', '88,1', '广汽传祺-GA5', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124067', '012', '88,2', '广汽传祺-GS5', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124068', '012', '190,0', '宾利-其它', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124069', '012', '190,1', '宾利-欧陆', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124070', '012', '86,0', '宝骏-其它', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124071', '012', '86,1', '宝骏-宝骏630', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124072', '012', '80,0', '安凯-其它', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124073', '012', '80,1', '安凯-客车车型', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124074', '012', '72,0', '时代汽车-其它', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124075', '012', '72,1', '时代汽车-小卡之星', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124076', '012', '72,2', '时代汽车-康瑞', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124077', '012', '72,3', '时代汽车-瑞沃', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124078', '012', '72,4', '时代汽车-金刚', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124079', '012', '72,5', '时代汽车-领航', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124080', '012', '72,6', '时代汽车-驭菱', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124081', '012', '12,0', '雪铁龙-其它', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124082', '012', '12,1', '雪铁龙-C2', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124083', '012', '12,10', '雪铁龙-萨拉-毕加索', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124084', '012', '12,2', '雪铁龙-C4L', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124085', '012', '12,3', '雪铁龙-C4毕加索', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124086', '012', '12,4', '雪铁龙-C5', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124087', '012', '12,5', '雪铁龙-世嘉三厢', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124088', '012', '12,6', '雪铁龙-世嘉两厢', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124089', '012', '12,7', '雪铁龙-凯旋', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124090', '012', '12,8', '雪铁龙-富康', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124091', '012', '12,9', '雪铁龙-爱丽舍三厢', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124092', '012', '49,0', 'MG-其它', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124093', '012', '49,1', 'MG-MG-3SW', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124094', '012', '49,2', 'MG-MG3', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124095', '012', '49,3', 'MG-MG5', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124096', '012', '49,4', 'MG-MG6', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124097', '012', '49,5', 'MG-MG7', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124098', '012', '87,0', '北汽威旺-其它', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124099', '012', '87,1', '北汽威旺-205', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124100', '012', '87,2', '北汽威旺-306', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124101', '012', '41,0', '奔驰-其它', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124102', '012', '41,1', '奔驰-A级', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124103', '012', '41,2', '奔驰-B级', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124104', '012', '41,3', '奔驰-C级', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124105', '012', '41,4', '奔驰-E级', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124106', '012', '41,5', '奔驰-GLK级', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124107', '012', '41,6', '奔驰-GL级', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124108', '012', '41,7', '奔驰-G级', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124109', '012', '41,8', '奔驰-MB100', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124110', '012', '41,9', '奔驰-ML级', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124111', '012', '41,10', '奔驰-R级', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124112', '012', '41,11', '奔驰-SLK级', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124113', '012', '41,12', '奔驰-S级', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124114', '012', '41,13', '奔驰-凌特', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124115', '012', '41,14', '奔驰-唯雅诺', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124116', '012', '41,15', '奔驰-威霆', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124117', '012', '41,16', '奔驰-客车车型1', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124118', '012', '26,0', '比亚迪-其它', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124119', '012', '26,1', '比亚迪-F0', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124120', '012', '26,2', '比亚迪-F3R', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124121', '012', '26,3', '比亚迪-F6', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124122', '012', '26,4', '比亚迪-G3R', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124123', '012', '26,5', '比亚迪-G6', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124124', '012', '26,6', '比亚迪-L3-', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124125', '012', '26,7', '比亚迪-M6', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124126', '012', '26,8', '比亚迪-S6', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124127', '012', '26,9', '比亚迪-福莱尔', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124128', '012', '26,10', '比亚迪-速锐', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124129', '012', '91,0', '北京汽车-其它', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124130', '012', '91,1', '北京汽车-E系列', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124131', '012', '59,0', '北奔重汽-其它', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124132', '012', '59,1', '北奔重汽-NG80', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124133', '012', '59,2', '北奔重汽-北奔V3', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124134', '012', '63,0', '保时捷-其它', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124135', '012', '63,1', '保时捷-panamera', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124136', '012', '63,2', '保时捷-卡宴', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124137', '012', '143,0', '道奇-其它', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124138', '012', '143,1', '道奇-酷搏', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124139', '012', '143,2', '道奇-酷威', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124140', '012', '25,0', '东风-其它', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124141', '012', '25,1', '东风-C35', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124142', '012', '25,2', '东风-K07', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124143', '012', '25,3', '东风-V21', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124144', '012', '25,4', '东风-东风嘉龙', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124145', '012', '25,5', '东风-东风小康风光', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124146', '012', '25,6', '东风-俊风CV03', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124147', '012', '25,7', '东风-凯普特', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124148', '012', '25,8', '东风-多利卡', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124149', '012', '25,9', '东风-天锦', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124150', '012', '25,10', '东风-小霸王', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124151', '012', '25,11', '东风-梦卡', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124152', '012', '25,12', '东风-福瑞卡', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124153', '012', '25,13', '东风-锐骐皮卡', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124154', '012', '25,14', '东风风神-A60', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124155', '012', '25,15', '东风风神-H30', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124156', '012', '25,16', '东风风行-景逸', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124157', '012', '25,17', '东风风行-菱智', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124158', '012', '37,0', '东南-其它', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124159', '012', '37,1', '东南-V3菱悦', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124160', '012', '37,2', '东南-V5菱致', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124161', '012', '37,3', '东南-希旺', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124162', '012', '37,4', '东南-得利卡', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124163', '012', '37,5', '东南-菱帅', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124164', '012', '37,6', '东南-富利卡', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124165', '012', '35,0', '哈飞-其它', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124166', '012', '35,1', '哈飞-新民意货车', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124167', '012', '35,2', '哈飞-民意货车', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124168', '012', '35,3', '哈飞-赛豹', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124169', '012', '35,4', '哈飞-赛马', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124170', '012', '35,5', '哈飞-路宝', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124171', '012', '35,6', '哈飞-路尊小霸王', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124172', '012', '35,7', '哈飞-锐意', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124173', '012', '35,8', '哈飞-骏意', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124174', '012', '29,0', '海马-其它', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124175', '012', '29,1', '海马-丘比特', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124176', '012', '29,2', '海马-普力马', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124177', '012', '29,3', '海马-欢动', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124178', '012', '29,4', '海马-海福星', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124179', '012', '29,5', '海马-海马3', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124180', '012', '29,6', '海马-福美来', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124181', '012', '29,7', '海马-骑士', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124182', '012', '29,8', '海马商用车-新鸿达', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124183', '012', '29,9', '海马商用车-海马王子', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124184', '012', '29,10', '海马商用车-荣达', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124185', '012', '60,0', '华菱星马-其它', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124186', '012', '60,1', '华菱星马-星凯马', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124187', '012', '96,0', '华普-其它', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124188', '012', '96,1', '华普-海域', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124189', '012', '96,2', '华普-海尚', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124190', '012', '96,3', '华普-海景', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124191', '012', '96,4', '华普-海迅', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124192', '012', '28,0', '金杯-其它', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124193', '012', '28,1', '金杯-智尚S30', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124194', '012', '28,2', '金杯-海星', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124195', '012', '28,3', '金杯-海狮', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124196', '012', '28,4', '金杯-金典', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124197', '012', '28,5', '金杯-阁瑞斯MPV', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124198', '012', '28,6', '金杯-霸道SUV', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124199', '012', '64,0', '凯迪拉克-其它', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124200', '012', '64,1', '凯迪拉克-ATS', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124201', '012', '64,2', '凯迪拉克-CTS', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124202', '012', '64,3', '凯迪拉克-SLS赛威', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124203', '012', '64,4', '凯迪拉克-SRX', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124204', '012', '64,5', '凯迪拉克-凯雷德', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124205', '012', '50,0', '凯马KAMA-其它', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124206', '012', '50,1', '凯马KAMA-凯马', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124207', '012', '50,2', '凯马KAMA-福来卡', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124208', '012', '50,3', '凯马KAMA-金运卡', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124209', '012', '50,4', '凯马KAMA-骏威卡车', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124210', '012', '111,0', '克莱斯勒-其它', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124211', '012', '111,1', '克莱斯勒-300C', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124212', '012', '111,2', '克莱斯勒-PT漫步者', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124213', '012', '22,0', '雷克萨斯-其它', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124214', '012', '22,1', '雷克萨斯-CT', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124215', '012', '22,2', '雷克萨斯-ES', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124216', '012', '22,3', '雷克萨斯-GS', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124217', '012', '22,4', '雷克萨斯-GX', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124218', '012', '22,5', '雷克萨斯-IS', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124219', '012', '22,6', '雷克萨斯-LS', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124220', '012', '22,7', '雷克萨斯-LX', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124221', '012', '22,8', '雷克萨斯-RX', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124222', '012', '44,0', '力帆-其它', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124223', '012', '44,1', '力帆-320', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124224', '012', '44,2', '力帆-520', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124225', '012', '44,3', '力帆-620', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124226', '012', '44,4', '力帆-X60', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124227', '012', '44,5', '力帆-丰顺', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124228', '012', '27,0', '铃木-其它', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124229', '012', '27,1', '铃木-利亚纳三厢', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124230', '012', '27,2', '铃木-北斗星', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124231', '012', '27,3', '铃木-吉姆尼', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124232', '012', '27,4', '铃木-天语SX4三厢', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124233', '012', '27,5', '铃木-奥拓', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124234', '012', '27,6', '铃木-派喜', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124235', '012', '27,7', '铃木-浪迪', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124236', '012', '27,8', '铃木-羚羊', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124237', '012', '27,9', '铃木-超级维特拉', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124238', '012', '27,10', '铃木-锋驭', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124239', '012', '27,11', '铃木-雨燕', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124240', '012', '27,12', '铃木-昌铃王', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124241', '012', '68,0', '路虎-其它', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124242', '012', '68,1', '路虎-发现', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124243', '012', '68,2', '路虎-揽胜', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124244', '012', '68,3', '路虎-神行者2代', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124245', '012', '51,0', '众泰-其它', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124246', '012', '51,1', '众泰-2008', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124247', '012', '51,2', '众泰-5008', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124248', '012', '51,3', '众泰-Z300', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124249', '012', '109,0', '中兴-其它', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124250', '012', '109,1', '中兴-威虎', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124251', '012', '11,0', '雪佛兰-其它', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124252', '012', '11,1', '雪佛兰-乐风', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124253', '012', '11,10', '雪佛兰-迈锐宝', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124254', '012', '11,2', '雪佛兰-乐驰', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124255', '012', '11,3', '雪佛兰-乐骋', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124256', '012', '11,4', '雪佛兰-景程', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124257', '012', '11,5', '雪佛兰-爱唯欧三厢', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124258', '012', '11,6', '雪佛兰-科帕奇', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124259', '012', '11,7', '雪佛兰-科迈罗', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124260', '012', '11,8', '雪佛兰-科鲁兹', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124261', '012', '11,9', '雪佛兰-赛欧三厢', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124262', '012', '13,0', '现代-其它', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124263', '012', '13,1', '现代-i30', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124264', '012', '13,10', '现代-朗动', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124265', '012', '13,11', '现代-格锐', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124266', '012', '13,12', '现代-瑞奕', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124267', '012', '13,13', '现代-瑞纳三厢', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124268', '012', '13,14', '现代-瑞风', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124269', '012', '13,15', '现代-索纳塔', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124270', '012', '13,16', '现代-维拉克斯', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124271', '012', '13,17', '现代-美佳', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124272', '012', '13,18', '现代-进口辉翼', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124273', '012', '13,19', '现代-途胜', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124274', '012', '13,2', '现代-ix35', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124275', '012', '13,20', '现代-酷派', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124276', '012', '13,21', '现代-雅绅特', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124277', '012', '13,22', '现代-领翔', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124278', '012', '13,23', '现代-飞思', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124279', '012', '13,3', '现代-伊兰特', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124280', '012', '13,4', '现代-名图', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124281', '012', '13,5', '现代-名驭', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124282', '012', '13,6', '现代-圣达菲', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124283', '012', '13,7', '现代-御翔', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124284', '012', '13,8', '现代-悦动', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124285', '012', '13,9', '现代-新胜达', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124286', '012', '8,0', '日产-其它', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124287', '012', '8,1', '日产-NV200', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124288', '012', '8,10', '日产-阳光', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124289', '012', '8,11', '日产-颐达', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124290', '012', '8,12', '日产-风度', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124291', '012', '8,13', '日产-风雅', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124292', '012', '8,14', '日产-骊威', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124293', '012', '8,15', '日产-骏逸', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124294', '012', '8,16', '日产-骐达', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124295', '012', '8,17', '日产-锐骐皮卡', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124296', '012', '8,2', '日产-天籁', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124297', '012', '8,3', '日产-奇骏', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124298', '012', '8,4', '日产-帕拉丁', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124299', '012', '8,5', '日产-楼兰', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124300', '012', '8,6', '日产-蓝鸟', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124301', '012', '8,7', '日产-贵士', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124302', '012', '8,8', '日产-轩逸', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124303', '012', '8,9', '日产-逍客', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124304', '012', '10,0', '马自达-其它', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124305', '012', '10,1', '马自达-CX-5', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124306', '012', '10,2', '马自达-CX-7', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124307', '012', '10,3', '马自达-Mazda2两厢', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124308', '012', '10,4', '马自达-Mazda3三厢', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124309', '012', '10,5', '马自达-Mazda5', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124310', '012', '10,6', '马自达-Mazda6', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124311', '012', '10,7', '马自达-Mazda8', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124312', '012', '10,8', '马自达-普力马', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124313', '012', '10,9', '马自达-睿翼', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124314', '012', '7,0', '福特-其它', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124315', '012', '7,1', '福特-全顺', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124316', '012', '7,2', '福特-嘉年华', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124317', '012', '7,3', '福特-探险者', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124318', '012', '7,4', '福特-福克斯', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124319', '012', '7,5', '福特-翼博', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124320', '012', '7,6', '福特-翼虎', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124321', '012', '7,7', '福特-蒙迪欧', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124322', '012', '7,8', '福特-锐界', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124323', '012', '7,9', '福特-麦柯斯', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124324', '012', '6,0', '丰田-其它子品牌', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124325', '012', '6,1', '丰田-RAV4-2012手动经典版,2011,2010,2009', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124326', '012', '6,10', '丰田-普瑞维亚-2004', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124327', '012', '6,11', '丰田-柯斯达-2007', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124328', '012', '6,12', '丰田-汉兰达-2011,2009', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124329', '012', '6,13', '丰田-海狮-2001', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124330', '012', '6,14', '丰田-皇冠-1999', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124331', '012', '6,15', '丰田-红杉-未知', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124332', '012', '6,16', '丰田-花冠-2006,2005,2004', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124333', '012', '6,17', '丰田-逸致-2014,2012,2011', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124334', '012', '6,18', '丰田-锐志-2006,2005', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124335', '012', '6,19', '丰田-陆地巡洋舰-未知', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124336', '012', '6,2', '丰田-丰田86-未知', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124337', '012', '6,20', '丰田-雅力士-2009,2008,2007', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124338', '012', '6,21', '丰田-YARiS-L', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124339', '012', '6,22', '丰田-PLATZ-1999', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124340', '012', '6,3', '丰田-佳美-2001,2000,1999,1998', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124341', '012', '6,4', '丰田-兰德酷路泽-2011中东版,2010,2007', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124342', '012', '6,5', '丰田-凯美瑞-2008,2007,2006', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124343', '012', '6,6', '丰田-卡罗拉-2010,2009,2008,2007,2006', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124344', '012', '6,7', '丰田-埃尔法-2011,2010', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124345', '012', '6,8', '丰田-威驰-2004,2003', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124346', '012', '6,9', '丰田-普拉多-2007,2006,2005,2004', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124347', '012', '2,0', '别克其它子品牌', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124348', '012', '2,1', '别克-GL8', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124349', '012', '2,10', '别克-英朗XT', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124350', '012', '2,11', '别克-新世纪', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124351', '012', '2,12', '别克-荣御', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124352', '012', '2,2', '别克-昂科拉', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124353', '012', '2,3', '别克-昂科雷', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124354', '012', '2,4', '别克-君威', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124355', '012', '2,5', '别克-君越', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124356', '012', '2,6', '别克-凯越', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124357', '012', '2,7', '别克-林荫大道', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124358', '012', '2,8', '别克-赛欧', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124359', '012', '2,9', '别克-英朗GT', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124360', '012', '5,0', '标致其它子品牌', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124361', '012', '5,1', '标致-206', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124362', '012', '5,2', '标致-207两厢', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124363', '012', '5,3', '标致-3008-2014,2013', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124364', '012', '5,4', '标致-301-未知', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124365', '012', '5,5', '标致-307三厢-2007,2006,2005,2004', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124366', '012', '5,6', '标致-308-2014,2013,2012', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124367', '012', '5,7', '标致-407-未知', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124368', '012', '5,8', '标致-408-2012,2011,2010', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124369', '012', '5,9', '标致-508-2014,2013,2012,2011', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124370', '012', '4,0', '本田其它子品牌', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124371', '012', '4,1', '本田-CR-V', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124372', '012', '4,10', '本田-艾力绅', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124373', '012', '4,11', '本田-锋范', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124374', '012', '4,12', '本田-雅阁', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124375', '012', '4,13', '本田-飞度', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124376', '012', '4,2', '本田-凌派', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124377', '012', '4,3', '本田-奥德赛', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124378', '012', '4,4', '本田-思域', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124379', '012', '4,5', '本田-思迪', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124380', '012', '4,6', '本田-思铂睿', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124381', '012', '4,7', '本田-思铭', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124382', '012', '4,8', '本田-杰德', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124383', '012', '4,9', '本田-歌诗图', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124384', '012', '3,0', '宝马其它子品牌', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124385', '012', '3,1', '宝马-1系', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124386', '012', '3,10', '宝马-Z4', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124387', '012', '3,2', '宝马-3系', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124388', '012', '3,3', '宝马-5系', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124389', '012', '3,4', '宝马-6系', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124390', '012', '3,5', '宝马-7系', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124391', '012', '3,6', '宝马-X1', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124392', '012', '3,7', '宝马-X3', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124393', '012', '3,8', '宝马-X5', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124394', '012', '3,9', '宝马-X6', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124395', '012', '9,0', '奥迪-其它', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124396', '012', '9,1', '奥迪-100', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124397', '012', '9,10', '奥迪-A7', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124398', '012', '9,11', '奥迪-A8L', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124399', '012', '9,12', '奥迪-Q3', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124400', '012', '9,13', '奥迪-Q5', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124401', '012', '9,14', '奥迪-Q7', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124402', '012', '9,15', '奥迪-S7', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124403', '012', '9,16', '奥迪-S8', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124404', '012', '9,17', '奥迪-TT', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124405', '012', '9,2', '奥迪-200', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124406', '012', '9,3', '奥迪-A1', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124407', '012', '9,4', '奥迪-A3', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124408', '012', '9,5', '奥迪-A4', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124409', '012', '9,6', '奥迪-A4L', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124410', '012', '9,7', '奥迪-A5', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124411', '012', '9,8', '奥迪-A6', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124412', '012', '9,9', '奥迪-A6L', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124413', '012', '109,2', '中兴-无限', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124414', '012', '85,0', '中通客车-其它', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124415', '012', '85,1', '中通客车-世纪', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124416', '012', '85,2', '中通客车-凯旋', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124417', '012', '85,3', '中通客车-凯驰A', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124418', '012', '85,4', '中通客车-阳光', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124419', '012', '85,5', '中通客车-领御', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124420', '012', '85,6', '中通客车-领秀', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124421', '012', '85,7', '中通客车-领航', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124422', '012', '85,8', '中通客车-领韵A', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123323', '008', '津Y', '天津市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123324', '008', '津Z', '天津市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123325', '008', '冀A', '石家庄市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123326', '008', '冀B', '唐山市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123327', '008', '冀C', '秦皇岛市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123328', '008', '冀D', '邯郸市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123329', '008', '冀E', '邢台市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123330', '008', '冀F', '保定市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123331', '008', '冀G', '张家口市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123332', '008', '冀H', '承德市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123333', '008', '冀J', '沧州市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123334', '008', '冀O', '河北省公安厅车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123335', '008', '冀R', '廊坊市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123336', '008', '冀T', '衡水市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123337', '008', '晋A', '太原市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123338', '008', '晋B', '大同市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123339', '008', '晋C', '阳泉市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123340', '008', '晋D', '长治市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123341', '008', '晋E', '晋城市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123342', '008', '晋F', '朔州市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123343', '008', '晋H', '忻州市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123344', '008', '晋J', '吕梁市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123345', '008', '晋K', '晋中市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123346', '008', '晋L', '临汾市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123347', '008', '晋M', '运城市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123348', '008', '晋O', '山西省公安厅车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123349', '008', '蒙A', '呼和浩特市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123350', '008', '蒙B', '包头市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123351', '008', '蒙C', '乌海市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123352', '008', '蒙D', '赤峰市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123353', '008', '蒙E', '呼伦贝尔市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123354', '008', '蒙F', '兴安市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123355', '008', '蒙G', '通辽市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123356', '008', '蒙H', '锡林郭勒盟公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123357', '008', '蒙J', '乌兰察布市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123358', '008', '蒙K', '鄂尔多斯市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123359', '008', '蒙L', '巴彦淖尔市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123360', '008', '蒙M', '阿拉善盟公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123361', '008', '蒙O', '内蒙古自治区公安厅车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123362', '008', '辽A', '沈阳市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123363', '008', '辽B', '大连市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123364', '008', '辽C', '鞍山市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123365', '008', '辽D', '抚顺市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123366', '008', '辽E', '本溪市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123367', '008', '辽F', '丹东市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123368', '008', '辽G', '锦州市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123369', '008', '辽H', '营口市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123370', '008', '辽J', '阜新市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123371', '008', '辽K', '辽阳市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123372', '008', '辽L', '盘锦市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123373', '008', '辽M', '铁岭市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123374', '008', '辽N', '朝阳市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123375', '008', '辽O', '辽宁省公安厅车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123376', '008', '辽P', '葫芦岛市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123377', '008', '吉A', '长春市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123378', '008', '吉B', '吉林市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123379', '008', '吉C', '四平市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123380', '008', '吉D', '辽源市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123381', '008', '吉E', '通化市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123382', '008', '吉F', '白山市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123383', '008', '吉G', '白城市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123384', '008', '吉H', '延边州公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123385', '008', '吉J', '松原市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123386', '008', '吉K', '吉林省公安厅交通警察总队长白山保护开发区车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123387', '008', '吉O', '吉林省公安厅车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123388', '008', '黑A', '哈尔滨市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123389', '008', '黑B', '齐齐哈尔市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123390', '008', '黑C', '牡丹江市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123391', '008', '黑D', '佳木斯市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123392', '008', '黑E', '大庆市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123393', '008', '黑F', '伊春市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123394', '008', '黑G', '鸡西市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123395', '008', '黑H', '鹤岗市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123396', '008', '黑J', '双鸭市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123397', '008', '黑K', '七台河市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123398', '008', '黑M', '绥化市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123399', '008', '黑N', '黑河市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123400', '008', '黑O', '黑龙江省公安厅车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123401', '008', '黑P', '大兴安岭地区公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123402', '008', '黑R', '黑龙江省垦区公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123403', '008', '沪A', '上海市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123404', '008', '沪B', '上海市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123405', '008', '沪C', '上海市公安局车辆管理所', '1', null, '远郊区', '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123406', '008', '沪D', '上海市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123407', '008', '沪E', '上海市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123408', '008', '沪F', '上海市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123409', '008', '沪G', '上海市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('126191', '486', '1', '校时成功', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('126192', '486', '2', '校时失败', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('126150', '515', '0', '双向', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('126151', '515', '1', '上行', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('126152', '515', '2', '下行', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('126190', '486', '0', '未校时', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123817', '008', '京A', '北京市公安交通管理局车辆管理所', '1', null, '1996年前', '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123818', '008', '京B', '北京市公安交通管理局车辆管理所', '1', null, '出租车', '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123819', '008', '京C', '北京市公安交通管理局车辆管理所', '1', null, '1996年', '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123820', '008', '京D', '北京市公安交通管理局车辆管理所', '1', null, '临时', '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123821', '008', '京E', '北京市公安交通管理局车辆管理所', '1', null, '1997年', '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123822', '008', '京F', '北京市公安交通管理局车辆管理所', '1', null, '2002年', '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123823', '008', '京G', '北京市公安交通管理局车辆管理所', '1', null, '郊区车辆', '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123824', '008', '京H', '北京市公安交通管理局车辆管理所', '1', null, '2004年', '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123825', '008', '京I', '北京市公安交通管理局车辆管理所', '1', null, '暂未启用', '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123826', '008', '京J', '北京市公安交通管理局车辆管理所', '1', null, '2005年', '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123827', '008', '京K', '北京市公安交通管理局车辆管理所', '1', null, '2006年', '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123828', '008', '京L', '北京市公安交通管理局车辆管理所', '1', null, '2007年', '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123829', '008', '京M', '北京市公安交通管理局车辆管理所', '1', null, '2008年', '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123830', '008', '京N', '北京市公安交通管理局车辆管理所', '1', null, '2009年', '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123831', '008', '京O', '北京市公安交通管理局车辆管理所', '1', null, '公安部专用', '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123832', '008', '京P', '北京市公安交通管理局车辆管理所', '1', null, '2009年', '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123833', '008', '京Q', '北京市公安交通管理局车辆管理所', '1', null, '暂未启用', '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123834', '008', '京R', '北京市公安交通管理局车辆管理所', '1', null, '暂未启用', '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123835', '008', '京S', '北京市公安交通管理局车辆管理所', '1', null, '暂未启用', '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123836', '008', '京T', '北京市公安交通管理局车辆管理所', '1', null, '暂未启用', '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123837', '008', '京U', '北京市公安交通管理局车辆管理所', '1', null, '暂未启用', '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123838', '008', '京V', '北京市公安交通管理局车辆管理所', '1', null, '暂未启用', '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123839', '008', '京W', '北京市公安交通管理局车辆管理所', '1', null, '暂未启用', '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123840', '008', '京X', '北京市公安交通管理局车辆管理所', '1', null, '暂未启用', '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123841', '008', '京Y', '北京市公安交通管理局车辆管理所', '1', null, '郊区车辆', '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123842', '008', '京Z', '北京市公安交通管理局车辆管理所', '1', null, '暂未启用', '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123843', '008', '津A', '天津市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123844', '008', '津B', '天津市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123845', '008', '津C', '天津市公安局车辆管理所', '1', null, '1996年', '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123846', '008', '津D', '天津市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123847', '008', '津E', '天津市公安局车辆管理所', '1', null, '出租车', '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123848', '008', '津F', '天津市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123849', '008', '津G', '天津市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123850', '008', '津H', '天津市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123851', '008', '津I', '天津市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123852', '008', '津J', '天津市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123853', '008', '津K', '天津市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123854', '008', '津L', '天津市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123855', '008', '津M', '天津市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123856', '008', '津N', '天津市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123857', '008', '津O', '天津市公安局车辆管理所', '1', null, '公安部', '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123858', '008', '津P', '天津市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123859', '008', '津Q', '天津市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123860', '008', '津R', '天津市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123861', '008', '津S', '天津市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123862', '008', '津T', '天津市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123863', '008', '津U', '天津市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123864', '008', '津V', '天津市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123865', '008', '津W', '天津市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123866', '008', '津X', '天津市公安局车辆管理所', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123867', '005', 'A', '正常', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123868', '005', 'B', '转出', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123869', '005', 'C', '被盗抢', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123870', '005', 'D', '停驶', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123871', '005', 'E', '注销', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123872', '005', 'G', '违法未处理', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123873', '005', 'H', '海关监管', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123874', '005', 'I', '事故未处理', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123875', '005', 'J', '嫌疑车', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123876', '005', 'K', '查封', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123877', '005', 'L', '暂扣', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123878', '005', 'M', '强制注销', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123879', '005', 'N', '事故逃逸', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123880', '005', 'O', '锁定', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123881', '001', 'K39', '小型面包车', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123882', '001', 'K49', '微型面包车', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123883', '010', 'A', '非营运', '1', null, '个人或者单位不以获取运输利润为目的而使用的机动车', '1');
commit;
prompt 2000 records committed...
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123884', '010', 'B', '公路客运', '1', null, '专门从事公路旅客运输的、以获取利润为目的的机动车', '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('123885', '010', 'C', '公交客运', '1', null, '城市内专门从事公共交通客运的、以获取利润为目的的机动车', '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125913', '508', '35', '风速', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125580', '018', '0', '未启用', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125581', '018', '1', '启用', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125582', '019', '0', '否', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125583', '019', '1', '是', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125062', '105', '1', '男', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125063', '105', '2', '女', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125100', '116', '0', '撤销', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125101', '116', '1', '正常', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125705', '312', '02', '车速不足', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125119', '124', '0', '失败', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125120', '124', '1', '成功', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125064', '106', '01', '总警监', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125065', '106', '02', '副总警监', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125066', '106', '03', '一级警监', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125067', '106', '04', '二级警监', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125068', '106', '05', '三级警监', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125069', '106', '06', '一级警督', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125070', '106', '07', '二级警督', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125071', '106', '08', '三级警督', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125072', '106', '09', '一级警司', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125073', '106', '10', '二级警司', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125074', '106', '11', '三级警司', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125075', '106', '12', '一级警员', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125076', '106', '13', '二级警员', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125077', '107', '1', '初级', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125078', '107', '2', '中级', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125079', '107', '3', '高级', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('126310', '011', 'h', '限行', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125707', '410', '2', '其它', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('126311', '011', 'm', '危化品运输车行驶线路违规', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('126312', '011', 'i', '疲劳驾驶', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('126211', '488', '48840001', '卡口', '1', null, '机动车超速自动检测系统(卡口）', '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('126212', '488', '48840002', '电警', '1', null, '电子警察自动记录系统', '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('126213', '488', '48840004', '固定测速', '1', null, '机动车超速自动监测系统', '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('126214', '488', '48840016', '车载', '1', null, '雷达测速仪', '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('126215', '488', '4884841', '酒检', '1', null, '呼出气体酒精含量探测器', '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('126216', '488', '48800001', '区间测速', '1', null, '机动车区间测速检测系统,机动车区间测速监测系统', '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124423', '012', '20,0', '中华-其它', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124424', '012', '20,1', '中华-H230', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124425', '012', '20,2', '中华-H320', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124426', '012', '20,3', '中华-H330', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124427', '012', '20,4', '中华-H530', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124428', '012', '20,5', '中华-V5', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124429', '012', '20,6', '中华-尊驰', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124430', '012', '20,7', '中华-酷宝', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124431', '012', '20,8', '中华-骏捷', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124432', '012', '20,9', '中华-骏捷cross', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124433', '012', '20,10', '中华-骏捷FRV', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124434', '012', '20,11', '中华-骏捷FSV', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124435', '012', '58,0', '中国重汽-其它', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124436', '012', '58,1', '中国重汽-HOKA', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124437', '012', '58,2', '中国重汽-HOWO', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124438', '012', '58,3', '中国重汽-斯太尔王', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124439', '012', '58,4', '中国重汽-豪瀚', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124440', '012', '58,5', '中国重汽-豪运', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124441', '012', '58,6', '中国重汽-金王子重卡', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124442', '012', '34,0', '长城-其它', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124443', '012', '34,1', '长城-凌傲', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124444', '012', '34,2', '长城-哈弗', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124445', '012', '34,3', '长城-炫丽', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124446', '012', '34,4', '长城-精灵', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124447', '012', '34,5', '长城-赛影', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124448', '012', '34,6', '长城-赛酷', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124449', '012', '34,7', '长城-赛铃', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124450', '012', '34,8', '长城-迪尔', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124451', '012', '34,9', '长城-酷熊', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124452', '012', 'Unknown', '未知', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124453', '012', 'Audi', '奥迪', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124454', '012', 'Honda', '本田', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124455', '012', 'Buick', '别克', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124456', '012', 'Toyota', '丰田', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124457', '012', 'BMW', '宝马', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124458', '012', 'Peugeot', '标致', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124459', '012', 'Ford', '福特', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124460', '012', 'Mazda', '马自达', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124461', '012', 'Nissan', '尼桑', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124462', '012', 'Hyundai', '现代', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124463', '012', 'Suzuki', '铃木', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124464', '012', 'Citroen', '雪铁龙', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124465', '012', 'Benz', '奔驰', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124466', '012', 'BYD', '比亚迪', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124467', '012', 'Geely', '吉利', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124468', '012', 'Lexus', '雷克萨斯', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124469', '012', 'Chery', '奇瑞', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124470', '012', 'Kia', '起亚', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124471', '012', 'Charade', '夏利', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124472', '012', 'DF', '东风', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124473', '012', 'Naveco', '依维柯', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124474', '012', 'SGMW', '五菱', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124475', '012', 'Jinbei', '金杯', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124476', '012', 'JAC', '江淮', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124477', '012', 'Emgrand', '帝豪', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124478', '012', 'ChangAn', '长安', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124479', '012', 'Skoda', '斯柯达', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124480', '012', 'BaoJun', '宝骏', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124481', '012', 'Subaru', '斯巴鲁', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124482', '012', 'LandWind', '陆风', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124483', '012', 'Luxgen', '纳智捷', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124484', '012', 'Renault', '雷诺', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124485', '012', 'Roewe', '荣威', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124486', '012', 'Cadillac', '凯迪拉克', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124487', '012', 'MG', '名爵', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124488', '012', 'Zotye', '众泰', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124489', '012', 'ZhongHua', '中华', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124490', '012', 'Foton', '福田', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124491', '012', 'Opel', '欧宝', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124492', '012', 'HongQi', '一汽红旗', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124493', '012', 'Fiat', '菲亚特', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124494', '012', 'Jaguar', '捷豹', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124495', '012', 'Volvo', '沃尔沃', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124496', '012', 'Acura', '讴歌', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124497', '012', 'Porsche', '保时捷', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124498', '012', 'Jeep', '吉普', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124499', '012', 'Bentley', '宾利', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124500', '012', 'Bugatti', '布加迪', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124501', '012', 'ChuanQi', '传祺', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124502', '012', 'Daewoo', '大宇', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124503', '012', 'DongNan', '东南', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124504', '012', 'Ferrari', '法拉利', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124505', '012', 'Fudi', '福迪', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124506', '012', 'Huapu', '华普', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124507', '012', 'HawTai', '华泰', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124508', '012', 'JMC', '江铃', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124509', '012', 'JingLong', '金龙客车', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124510', '012', 'JoyLong', '九龙', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124511', '012', 'Karry', '开瑞', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124512', '012', 'Chrysler', '克莱斯勒', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124513', '012', 'Linian', '理念', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124514', '012', 'LiFan', '力帆', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124515', '012', 'LieBao', '猎豹', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124516', '012', 'Lincoln', '林肯', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124517', '012', 'Lotus', '路特斯', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124518', '012', 'Maserati', '玛莎拉蒂', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124519', '012', 'Maybach', '迈巴赫', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124520', '012', 'Mclaren', '迈凯轮', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124521', '012', 'Youngman', '青年客车', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124522', '012', 'Tesla', '特斯拉', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124523', '012', 'Rely', '威麟', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124524', '012', 'Lsuzu', '五十铃', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124525', '012', 'Yiqi', '一汽', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124526', '012', 'Infiniti', '英菲尼迪', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124527', '012', 'YuTong', '宇通客车', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124528', '012', 'AnKai', '安凯客车', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124529', '012', 'Canghe', '昌河', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124530', '012', 'HaiMa', '海马', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124531', '012', 'Crown', '丰田皇冠', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124532', '012', 'HuangHai', '黄海', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124533', '012', 'JinLv', '金旅客车', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124534', '012', 'JinNing', '精灵', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124535', '012', 'KuBo', '酷博', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124536', '012', 'MINI', '迷你', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124537', '012', 'Gleagle', '全球鹰', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124538', '012', 'ShiDai', '时代', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124539', '012', 'TianYe', '田野', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124540', '012', 'WeiZi', '威姿', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124541', '012', 'Englon', '英伦', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124542', '012', 'Changan', '长安轿车', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124543', '012', 'Yuejin', '跃进', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124544', '012', 'Taurus', '金牛星', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124545', '012', 'Alto', '奥拓', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124546', '012', 'Weiwang', '威旺', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124547', '012', 'Haige', '海格', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124548', '012', 'Shaolin', '少林客车', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124549', '012', 'Beifang', '北方客车', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124550', '012', 'Beijing', '北京汽车', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124551', '012', 'Hafu', '哈弗', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124552', '012', '34,10', '长城-金迪尔', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124553', '012', '34,11', '长城-长城', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124554', '012', '34,12', '长城-风骏', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124555', '012', '57,0', '宇通-其它', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124556', '012', '57,1', '宇通-8系', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124557', '012', '57,2', '宇通-客车车型', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124558', '012', '43,0', '依维柯-其它', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124559', '012', '43,1', '依维柯-Daily', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124560', '012', '43,2', '依维柯-Power', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124561', '012', '43,3', '依维柯-Turbo', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124562', '012', '43,4', '依维柯-Venice', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124563', '012', '43,5', '依维柯-宝迪', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124564', '012', '43,6', '依维柯-得意货车', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124565', '012', '43,7', '依维柯-都灵', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124566', '012', '42,0', '一汽-其它', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124567', '012', '42,1', '一汽-501-轻卡', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124568', '012', '42,2', '一汽-N3+两厢', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124569', '012', '42,3', '一汽-佳宝货车', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124570', '012', '42,4', '一汽-夏利', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124571', '012', '42,5', '一汽-奥威J5P', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124572', '012', '42,6', '一汽-奥星', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124573', '012', '42,7', '一汽-威乐', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124574', '012', '42,8', '一汽-威姿', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124575', '012', '42,9', '一汽-威志V2', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124576', '012', '42,10', '一汽-小解放', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124577', '012', '42,11', '一汽-悍威', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124578', '012', '42,12', '一汽-新大威', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124579', '012', '42,13', '一汽-新悍威', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124580', '012', '42,14', '一汽-森雅M80', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124581', '012', '42,15', '一汽-腾威L5M', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124582', '012', '42,16', '一汽-解放J4R', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124583', '012', '42,17', '一汽-赛龙', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124584', '012', '42,18', '一汽-骏威J5K', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124585', '012', '42,19', '一汽-奔腾B50', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124586', '012', '42,20', '一汽-奔腾B70', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124587', '012', '42,21', '一汽-奔腾B90', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124588', '012', '30,0', '五菱-其它', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124589', '012', '30,1', '五菱-五菱之光', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124590', '012', '30,2', '五菱-兴旺面包车', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124591', '012', '30,3', '五菱-宏光', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124592', '012', '30,4', '五菱-小旋风', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124593', '012', '30,5', '五菱-扬光', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124594', '012', '30,6', '五菱-荣光面包车', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124595', '012', '30,7', '五菱-鸿途', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124596', '012', '93,0', '威麟-其它', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124597', '012', '93,1', '威麟-H3', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124598', '012', '93,2', '威麟-V5', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124599', '012', '93,3', '威麟-X5', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124600', '012', '18,0', '斯柯达-其它', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124601', '012', '18,1', '斯柯达-昊锐', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124602', '012', '18,2', '斯柯达-明锐', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124603', '012', '18,3', '斯柯达-昕锐', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124604', '012', '18,4', '斯柯达-晶锐', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124605', '012', '18,5', '斯柯达-速派', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124606', '012', '18,6', '斯柯达-野帝', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124607', '012', '123,0', '双环-其它', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124608', '012', '123,1', '双环-SCEO', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124609', '012', '17,0', '三菱-其它', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124610', '012', '17,1', '三菱-三菱翼神', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124611', '012', '17,2', '三菱-劲炫', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124612', '012', '17,3', '三菱-君阁', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124613', '012', '17,4', '三菱-帕杰罗-国产', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124614', '012', '17,5', '三菱-帕杰罗劲畅', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124615', '012', '17,6', '三菱-戈蓝', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124616', '012', '17,7', '三菱-格蓝迪', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124617', '012', '17,8', '三菱-欧蓝德', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124618', '012', '17,9', '三菱-菱悦', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124619', '012', '17,10', '三菱-蓝瑟', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124620', '012', '103,0', '启辰-其它', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124621', '012', '103,1', '启辰-D50', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124622', '012', '14,0', '奇瑞-其它', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124623', '012', '14,1', '奇瑞-A1', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124624', '012', '14,2', '奇瑞-A3两厢', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124625', '012', '14,3', '奇瑞-A5', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124626', '012', '14,4', '奇瑞-QQ', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124627', '012', '14,5', '奇瑞-东方之子', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124628', '012', '14,6', '奇瑞-优雅', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124629', '012', '14,7', '奇瑞-奇瑞E3', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124630', '012', '14,8', '奇瑞-奇瑞E5', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124631', '012', '14,9', '奇瑞-旗云1', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124632', '012', '14,10', '奇瑞-旗云', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124633', '012', '14,11', '奇瑞-旗云2', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124634', '012', '14,12', '奇瑞-旗云3', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124635', '012', '14,13', '奇瑞-瑞虎', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124636', '012', '14,14', '奇瑞-风云2三厢', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124637', '012', '14,15', '奇瑞-艾瑞泽7', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124638', '013', '1', '闯红灯设备', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124639', '013', '2', '公路卡口设备', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124640', '013', '3', '测速设备', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124641', '013', '4', '闭路电视', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124642', '013', '5', '移动摄像', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124643', '013', '6', '警务通', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124644', '013', '7', '区间测速', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124645', '013', '8', '卫星定位装置', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124646', '013', '9', '其它电子设备', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125669', '205', '1', '车辆信息与本系统车辆登记信息不符', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125670', '205', '2', '车辆信息与车驾管信息不符 ', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125671', '205', '3', '两过车记录中车辆信息不相同 ', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125672', '205', '4', '直线距离计算方式 ', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125673', '205', '5', '点位实际距离 ', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125770', '501', '0', '无状态', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125791', '466', '0', '未处理', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125792', '466', '1', '无需处理', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125793', '466', '2', '未解决', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('125794', '466', '3', '已解决', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124660', '016', '0', '高速公路', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124661', '016', '1', '国道', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124662', '016', '2', '省道', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124663', '016', '3', '县道', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124664', '016', '4', '乡村道', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124665', '016', '5', '城市快速路', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124666', '016', '6', '城市主干道', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124667', '016', '7', '城市次干道', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124668', '016', '8', '城市支路', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124669', '016', '9', '其他道路', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124670', '017', '1', '国道', '1', null, '国家干线公路', '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124671', '017', '2', '省道', '1', null, '省、自治区、直辖市干线公路', '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124672', '017', '3', '县道', '1', null, '县公路', '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124673', '017', '4', '专用公路', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124674', '200', '1', '24小时值守', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124675', '200', '2', '白天值守', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124676', '200', '3', '夜间值守', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124677', '201', '0', '否', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124678', '201', '1', '是', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124679', '202', '1', '卡口设备上传', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124680', '202', '2', '系统数据分析', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124681', '202', '3', '区间测速系统', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124682', '202', '4', '人工采集', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124683', '203', '1', '完全符合', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124684', '203', '2', '未按报备线路行驶', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124685', '203', '3', '未按预定时间行驶', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124686', '203', '4', '超速行驶', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124691', '204', '01', '红眼客车', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124692', '204', '02', '疲劳驾驶', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124693', '204', '03', '危化品车', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124694', '204', '31', '危化品车未报备行驶', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124695', '204', '32', '危化品车未按报备线路行驶', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124696', '204', '33', '危化品车未在预定时间范围内经过指定点位', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124697', '204', '04', '违反禁行', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124698', '204', '05', '违反限行', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124699', '204', '06', '未系安全带', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124700', '204', '07', '大车占道', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124701', '204', '08', '黄标车', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124702', '204', '09', '前后号牌不一致', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124719', '206', '01', '事故逃逸车辆', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124720', '206', '02', '套牌车辆', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124721', '206', '03', '假牌车辆', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124722', '206', '04', '逾期未年检车辆', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124723', '206', '05', '逾期未报废车辆', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124724', '206', '06', '违法未处理车辆', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124725', '206', '08', '套牌嫌疑车辆', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124726', '206', '09', '未安装切断装置危化品车辆', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124727', '206', '21', '刑事案件', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124728', '206', '22', '重大治安案件', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124729', '206', '23', '违法犯罪嫌疑交通工具', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124730', '206', '24', '被盗抢机动车辆', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124731', '206', '25', '治安常态管控', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124732', '206', '31', '无牌车辆', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124733', '206', '32', '特殊省份车辆', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124734', '206', '33', '凌晨2-5点继续上路行驶客运车辆', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124735', '206', '41', '借用车辆（假牌）', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124736', '206', '42', '借用超期车辆（假牌）', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124737', '206', '51', '无效驾证车主车辆', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124738', '208', '1', '公开', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124739', '208', '2', '秘密', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124740', '209', '1', '一级（红色）', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124741', '209', '2', '二级（橙色）', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124742', '209', '3', '三级（黄色）', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124743', '209', '4', '四级（蓝色）', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124744', '209', '9', '不使用布控级别', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124745', '210', '1', '涉枪', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('124746', '210', '2', '涉爆', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('126263', '442', '11', '相机子故障', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('126264', '442', '12', '雷达子故障', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('126265', '442', '13', '电源子故障', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('126266', '442', '14', '线圈子故障', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('126267', '442', '15', '网络子故障', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('126268', '442', '16', 'GPS故障子故障', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('126269', '442', '17', '能见度仪子故障', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('126270', '442', '18', 'LED子故障', '1', null, null, '1');
insert into T_SYS_CODE (code_id, code_type, code_no, code_name, editable, sort_index, remark, enable_flag)
values ('126271', '442', '99', '其他故障', '1', null, null, '1');
commit;
prompt 2350 records loaded


spool off

exit;
