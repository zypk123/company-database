prompt Importing table t_sys_code_type...
set feedback off
set define off
prompt 系统管理 
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, EDITABLE, REGEX, ENABLE_FLAG)
values ('001', '车辆类型', '1', null, '1');

insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, EDITABLE, REGEX, ENABLE_FLAG)
values ('002', '号牌种类', '1', null, '1');

insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, EDITABLE, REGEX, ENABLE_FLAG)
values ('003', '号牌颜色', '1', null, '1');

insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, EDITABLE, REGEX, ENABLE_FLAG)
values ('004', '车辆外形', '1', null, '1');

insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, EDITABLE, REGEX, ENABLE_FLAG)
values ('005', '机动车状态', '1', null, '1');

insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, EDITABLE, REGEX, ENABLE_FLAG)
values ('006', '车辆颜色', '1', null, '1');

insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, EDITABLE, REGEX, ENABLE_FLAG)
values ('007', '行政区划', '1', null, '1');

insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, EDITABLE, REGEX, ENABLE_FLAG)
values ('008', '发证机关', '1', null, '1');

insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, EDITABLE, REGEX, ENABLE_FLAG)
values ('009', '证件类型', '1', null, '1');

insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, EDITABLE, REGEX, ENABLE_FLAG)
values ('010', '车辆使用特性', '1', null, '1');

insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, EDITABLE, REGEX, ENABLE_FLAG)
values ('011', '违法类型', '1', null, '1');

insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, EDITABLE, REGEX, ENABLE_FLAG)
values ('012', '车辆品牌', '1', null, '1'); 

insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('013', '采集方式','VIOLATION_SOURCE', '1', null, '1');

insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME,ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('014', '机构类型','ORG_TYPE', '1', null, '1');

insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('015', '机构级别','ORG_LEVEL', '1', '', '1');

insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('016', '道路类型','ROAD_TYPE', '1', null, '1'); 

insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('017', '道路级别','ROAD_LEVEL', '1', null, '1'); 

insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('018', '启用标识','ROAD_LEVEL', '1', null, '1'); 

insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('019', '编辑标识','ROAD_LEVEL', '1', null, '1'); 

insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('071', '方向类型（城市）','DIRECTION_TYPE_CITY', '1', null, '1'); 

insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('020', '节假日类型','FESTIVAL_TYPE', '1', null, '1'); 

insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('072', '道路走向','ROAD_DIRECTION_TYPE', '1', null, '1'); 


------系统管理-----

insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('100', '警员类型','POLICE_TYPE', '1', null, '1'); 
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('101', '编制类型','AUTHORIZED_TYPE', '1', null, '1'); 
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('102', '业务岗位','BUSINESS_POST', '1', null, '1'); 
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('103', '事故处理等级','EVENT_LEVEL', '1', null, '1'); 
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('104', '领导级别','LEADER_LEVEL', '1', null, '1'); 
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('105', '性别','SEX', '1', null, '1'); 
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('106', '警衔','POLICE_RANK', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('107', '执法资格等级','QUALIFICATION_GRADE', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('108', '警员表记录状态','P_RECORD_STATUS', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('109', '警员表启用标识','P_ENABLE_FLAG', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('110', 'SSO用户标记','SSO_FLAG', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('111', '机构类型','ORG_TYPE', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('112', '是否是部门','IS_DEPARTMENT', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('113', '是否是高速管理部门','IS_HIGHWAY_ORG', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('114', '机构级别','ORG_LEVEL', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('115', '行政区划级别','DISTRICT_GRADE', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('116', '撤销标记','CANCEL_FLAG', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('117', '登陆状态','IS_ONLINE', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('118', '启用标识','ENABLE_FLAG', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('119', '数据权限类型','DATA_ACCESS_TYPE', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('120', '系统功能启用标识','FUNCTION_FLAG', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('121', '菜单启用标识','MENU_ENABLE_FLAG', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('122', '值的类型','VALUE_TYPE', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('123', '是否支持回滚','REBACKABLE', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('124', '操作结果','OPERATE_RESULT', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('125', '操作类型','OPERATE_TYPE', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('126', '编辑标识','EDITABLE', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('127', '启用标识','ENABLE_FLAG', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('128', '编辑标识','EDITABLE', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('129', '启用标识','ENABLE_FLAG', '1', null, '1');

insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('130', '超速行为认定方式','VIO_CONFIRM_MODEL', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('131', '是否单向通行','IS_ONE_DIRECTION', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('132', '路面结构','ROAD_STRUCTURE', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('133', '路面地形','ROAD_LANDSCAPE', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('134', '道路线形','ROAD_LINEAR', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('135', '道路物理隔离设施','ROAD_ISOLATION', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('136', '中央隔离设施','CENTRAL_ISOLATION', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('137', '防护设施类型','PROTECT_FACILITIES', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('138', '道路表记录状态','ROAD_RECORD_STATUS', '1', null, '1');

insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('139', '单向或双向','IS_ONE_WAY', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('140', '路口渠化','CANALIZATION', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('141', '车道类型','LANE_TYPE', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('142', '是否限行','IS_RESTRICT', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('143', '有无应急车道','HAS_EMERGENCY_LANE', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('144', '有无非机动车道','HAS_BICYCLE_LANE', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('145', '有无人行道','HAS_PAVEMENT', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('146', '进出路口标识','ENTER_OR_EXIT', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('147', '路口类型','CROSS_TYPE', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('148', '路段类型','ROAD_SECTION_TYPE', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('149', '是否单向通行','IS_ONE_DIRECTION', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('150', '点位类型','SITE_TYPE', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('151', '点位地形','SITE_LANDSCAPE', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('152', '服务资源类型','SERVICE_RESOURCE_TYPE', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('153', '特殊路段类型','SPECIAL_SECTION_TYPE', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('154', '服务区类型','SERVICE_AREA_TYPE', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('155', '进出服务区形式','ENTRANCE_TYPE', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('156', '服务区等级','SERVICE_AREA_GRADE', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('157', '是否有加油站','HAS_GAS_STATION', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('158', '油品种类','OIL_TYPE_LIST', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('159', '互通类型','INTERFLOW_TYPE', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('160', '出口或入口','ENTRANCE_OR_EXIT', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('161', '有无执法服务站','HAS_LAW_SERVICE_STATION', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('162', '闸道类型','RAMP_TYPE', '1', null, '1');

insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('163', '导出状态','status', '1', null, '1');

prompt 车辆查控2 
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, EDITABLE, REGEX, ENABLE_FLAG)
values ('200', '值守模式', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, EDITABLE, REGEX, ENABLE_FLAG)
values ('201', '是否接入公安网', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, EDITABLE, REGEX, ENABLE_FLAG)
values ('202', '数据来源', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, EDITABLE, REGEX, ENABLE_FLAG)
values ('203', '评估结果', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, EDITABLE, REGEX, ENABLE_FLAG)
values ('204', '识别类型', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, EDITABLE, REGEX, ENABLE_FLAG)
values ('205', '距离计算方式', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, EDITABLE, REGEX, ENABLE_FLAG)
values ('206', '布控原因', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, EDITABLE, REGEX, ENABLE_FLAG)
values ('207', '布控原因子类型', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, EDITABLE, REGEX, ENABLE_FLAG)
values ('208', '布控性质', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, EDITABLE, REGEX, ENABLE_FLAG)
values ('209', '布控等级', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, EDITABLE, REGEX, ENABLE_FLAG)
values ('210', '涉危标记', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, EDITABLE, REGEX, ENABLE_FLAG)
values ('211', '是否涉密车辆', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, EDITABLE, REGEX, ENABLE_FLAG)
values ('212', '布控警种', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, EDITABLE, REGEX, ENABLE_FLAG)
values ('213', '布控数据来源', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, EDITABLE, REGEX, ENABLE_FLAG)
values ('214', '布控范围类型', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, EDITABLE, REGEX, ENABLE_FLAG)
values ('215', '查控措施', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, EDITABLE, REGEX, ENABLE_FLAG)
values ('216', '是否短信通知', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, EDITABLE, REGEX, ENABLE_FLAG)
values ('217', '是否增加报警接收人', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, EDITABLE, REGEX, ENABLE_FLAG)
values ('218', '布控状态', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, EDITABLE, REGEX, ENABLE_FLAG)
values ('219', '审核标记', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, EDITABLE, REGEX, ENABLE_FLAG)
values ('220', '撤控原因代码', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, EDITABLE, REGEX, ENABLE_FLAG)
values ('221', '报警模式', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, EDITABLE, REGEX, ENABLE_FLAG)
values ('222', '是否密控的报警', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, EDITABLE, REGEX, ENABLE_FLAG)
values ('223', '报警来源', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, EDITABLE, REGEX, ENABLE_FLAG)
values ('224', '签收结果', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, EDITABLE, REGEX, ENABLE_FLAG)
values ('225', '拦截状态', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, EDITABLE, REGEX, ENABLE_FLAG)
values ('226', '是否处理完成', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, EDITABLE, REGEX, ENABLE_FLAG)
values ('227', '是否已交罚款', '1', null, '1'); 

insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, EDITABLE, REGEX, ENABLE_FLAG)
values ('228', '归属地', '1', null, '1'); 
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, EDITABLE, REGEX, ENABLE_FLAG)
values ('229', '车辆分类', '1', null, '1'); 
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('230', '免处罚标记','NO_PUNISH_FLAG', '1', null, '1'); 
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('231', '查车保护标记','QUERY_PROTECT_FLAG', '1', null, '1'); 
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('232', '确认标记','CONFIRM_FLAG', '1', null, '1'); 
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('233', '是否已转入违法','IS_CHANGE_VIO', '1', null, '1'); 
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('234', '套牌标记','CLONE_FLAG', '1', null, '1'); 
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('235', '假牌标记','FAKE_FLAG', '1', null, '1'); 
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('236', '假牌或套牌','FAKE_OR_CLONE', '1', null, '1'); 
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('237', '布控标记','TRACK_FLAG', '1', null, '1'); 
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('238', '记录来源','RECORD_ORGIN', '1', null, '1'); 

insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('239', '撤控标识','REVOKE_FLAG', '1', null, '1'); 

insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('240', '执法服务站等级','STATION_RATING', '1', null, '1'); 


prompt 违法管理3

insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('301', '记录状态','STATUS', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('302', '取消上传类型','CANCEL_UPLOAD_TYPE', '1', null, '1');

insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('303', '执法类型','ENFORCE_CATEGORY', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('304', '违法特性', 'VIO_CHARACTER','1', null, '1'); 

insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('305', '上传状态', 'UPLOAD_STATUS','1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('306', '废弃类别', 'DISCARD_TYPE','1', null, '1');


insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('307', '处罚类型','PENALTY_TYPE', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('308', '分捡标识','COLLATE_FLAG', '1', null, '1'); 

insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME,EDITABLE, REGEX, ENABLE_FLAG, ENGLISH_NAME)
values ('309', '测速类别', '1', null, '1','SPEEDING_TYPE'); 
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('310', '异常数据类型','BNORMAL_DATA_TYPE', '1', null, '1'); 
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('311', '特殊车辆类型','SPECIAL_VEH_TYPE', '1', null, '1'); 
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('312', '废弃原因','DISCARDED_REASON', '1', null, '1'); 
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('313', '本地处罚标记','LOCAL_PUNISH_FLAG', '1', null, '1'); 
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('314', '导出标记','EXPORT_FLAG', '1', null, '1'); 
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('315', '锁定标识','LOCK_FLAG', '1', null, '1'); 
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('316', '违法大类','VIO_PRIOR_TYPE', '1', null, '1'); 
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('317', '警告标记','FLAG_WARN', '1', null, '1'); 
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('318', '罚款标记','FLAG_FINE', '1', null, '1'); 
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('319', '暂扣标记','FLAG_SUSPEND', '1', null, '1'); 
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('320', '吊销标记','FLAG_CANCEL', '1', null, '1'); 
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('321', '拘留标记','FLAG_DETAIN', '1', null, '1'); 
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('322', '撤销行驶证标记','FLAG_REVOKE_VEHICLE', '1', null, '1'); 
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('323', '撤销驾驶证标记','FLAG_REVOKE_DRIVER', '1', null, '1'); 
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('324', '是否国标','IS_GB', '1', null, '1'); 
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('325', '是否常用','IS_COMMON_USED', '1', null, '1'); 
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('326', '强制措施类型','FORCE_TYPE', '1', null, '1'); 



prompt 设备运维1 

insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('400', '设备类型','DEVICE_TYPE', '1', null, '1'); 
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('401', '设备子类型','DEVICE_TYPE_SUB', '1', null, '1'); 

insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('402', '前端设备网络类型','NETWORK_TYPE', '1', null, '1'); 
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('403', '安全接入方式','SAFE_CONNECT_MEANS', '1', null, '1'); 
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('404', '安装方式','MOUNTING_FACILITY_TYPE', '1', null, '1'); 
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('405', '使用状态标识','USE_STATUS_FLAG', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('406', '同步标识','SYNC_STATUS', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('407', '统计检定率标识','STAT_CERT_RATE', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('408', '统计在线率标识','STAT_ONLINE_RATE', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('409', '监测方向标识','DIRECTION_FLAG', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('410', '进出城方向标记','ERTRANCE_OR_EXIT', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('411', '在线状态','ONLINE_STATUS', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('412', '设备故障状态','STATUS_TYPE', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('413', '球机或枪机','DOME_GUNLOCK', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('414', '视频分辨率','VIDEO_RESOLUTION', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('418', '是否需要后台控制恢复预置位','IS_BACKSTAGE_RECOVERY', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('419', '建设归属','OWNERSHIP', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('420', '是否接入稽查布控系统','IS_CONNECT_TRACK_SYS', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('421', '卡口类型','TOLLGATE_TYPE', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('422', '拦截条件','INTERCEPT_CONDITIONS', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('434', '事件类型','EVENT_TYPE', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('436', '服务器组件类型','COMPONENT_TYPE', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('437', '合同类型','CONTRACT_TYPE', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('438', '系统组件（部件）类型','SYS_COMPONENT_TYPE', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('439', '图片类型','SYS_COMPONENT_TYPE', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('441', '检定结果','CERTIFICATED_RESULT', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('442', '故障类型','FAULT_TYPE', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('443', '维护级别','MAINTENANCE_LEVEL', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('444', '设备外观','DEVICE_APPEARANCE', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('445', '设备杆件','DEVICE_BAR', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('446', '防护部件','PROTECTION_COMPONENTS', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('447', '电源','POWER', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('448', '电缆','CABLE', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('449', '避雷设施','LIGHTING_PROTECTION', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('450', '接地','GROUND_CONNECTION', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('451', '通信设备','COMMUNICATION_DEVICE', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('452', '校准时钟','DEVICE_TIME', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('453', '清理防护罩','CLEAN_PROTECTIVE_COVER', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('454', '清理镜头积灰','CLEAN_CAMERA_ASH', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('455', '安装情况','INSTALLATION', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('456', '标志标线','TRAFFIC_SIGN', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('457', '车辆通行信息','VEHICLE_PASS_INFO', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('458', '车辆违法记录信息','VEHICLE_VIOLATION', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('459', '气象数据','METEOROLOGY_DATA', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('460', '视频质量','VIDEO_QUALITY', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('461', '视频云台控制','PTZ_CONTROL', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('462', 'LED自检情况','SELF_CHECK', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('463', '信号灯工作情况','TRAFFIC_LIGHT', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('464', '信号机工作情况','SIGNAL_DEVICE', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('465', '故障/报警有效性','IS_VALIDITY', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('466', '处理状态','PROCESS_STATE', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('467', '采集方式','COLLECT_METHOD', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('468', '维护单状态','MAINTENANCE_STATUS', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('469', '维修结论','MAINTENDANCE_RESULT', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('470', '进出高速公路卡口标识','HIGHWAY_ENTRANCE_EXIT', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('471', '进出服务区卡口标识','SERVICE_ENTRANCE_EXIT', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('472', '停用标识','DISABLE_FLAG', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('473', '供电类型','POWER_TYPE', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('474', '传输方式','TRANSMISSION_MODE', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('475', '视频监控类型','VIDEO_SUPERVISE_TYPE', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('476', '启用标识','ENABLE_FLAG', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('477', '故障或报警','FAULT_ALARM', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('478', '故障子类型','FAULT_SUB_TYPE', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('479', '故障解除标识','RESOLVE_FLAG', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('480', '重启标识','RESTART_FLAG', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('481', '运行状态','RUNNING_STATE', '1', null, '1');

insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('482', '检测方式','DETECTION_MODE', '1', null, '1');

insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('427', '抓拍模式','PHOTO_MODEL', '1', null, '1');

insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('483', '维护周期','MAINTENANCE_CYCLE', '1', null, '1');

insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('484', '装备类型','EQUIPMENT_TYPE', '1', null, '1');

prompt 路况监控

insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('500', '记录类型','REPORT_TYPE', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('501', '通行状态','TRAFFIC_STATE', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('502', '气象类型','METEOROLOGY_TYPE', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('503', '事件解除标识','EVENT_RELEASE_TYPE', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('504', '有效或无效','VALIDITY', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('505', '事件处理状态','PROCESS_STATUS', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('506', '流量预警类型','FLOW_ALARM_TYPE', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('507', '预警级别','ALARM_GRADE', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('508', '阀值类型','ALARM_VALUE_TYPE', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('509', '路面状况类型','ROAD_CONDITION_TYPE', '1', null, '1');

insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('510', '管制原因','CONTROL_REASON', '1', null, '1');

insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('511', '路况预警事件类别','ALARM_EVENT_TYPE', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('512', '预警事件子类型','SUB_ALARM_EVENT', '1', null, '1');

insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('513', '清洁等级','CLEAN_DEGRE', '1', null, '1');
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('514', '降雨类型','WATER_TYPE', '1', null, '1');

prompt Done.
