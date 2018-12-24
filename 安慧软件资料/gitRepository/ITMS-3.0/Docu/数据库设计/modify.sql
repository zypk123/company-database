--2015/11/11 左鹏
--修改警员表字段EVENT_LECEL->EVENT_LEVEL
alter table ITMS3.T_SYS_POLICE rename column EVENT_LECEL to EVENT_LEVEL;

alter table ITMS3.T_SYS_POLICE_POST modify MANAGE_ORG  not null;

alter table ITMS3.T_VIO_ASSO_USER_DEVICE rename column DEVICE_NBR to DEVICE_SYS_NBR;

alter table ITMS3.T_SYS_LANE rename column SITE_ID to SECTION_ID;
alter table ITMS3.T_SYS_LANE drop (DIRECTION_TYPE,DIRECTION_NAME);
alter table ITMS3.T_SYS_LANE drop (ROAD_BRANCH_ID);

create table T_SYS_FESTIVAL 
(
   FESTIVAL_ID          VARCHAR2(32)         not null,
   FESTIVAL_TYPE        VARCHAR2(2)          not null,
   START_DATE           DATE                 not null,
   END_DATE             DATE,
   YEAR                 DATE,
   REMARK               VARCHAR2(128),
   constraint PK_T_SYS_FESTIVAL primary key (FESTIVAL_ID)
);

comment on table T_SYS_FESTIVAL is
'节假日信息表';

comment on column T_SYS_FESTIVAL.FESTIVAL_TYPE is
'1 元旦
2 春节
3 清明节
4 劳动节
5 端午节
6 国庆节
7 中秋节';

alter table ITMS3.T_VEHTRACK_RESTRICT add FESTIVAL_ID VARCHAR2(32);

alter table ITMS3.T_SYS_ORG drop (ORG_SIGNATURE);
alter table ITMS3.T_SYS_ORG add ORG_SIGNATURE  VARCHAR2(32);
alter table ITMS3.T_SYS_ORG drop (SIGNATURE_NOTIFIER);
alter table ITMS3.T_SYS_ORG add SIGNATURE_NOTIFIER  VARCHAR2(32);

--2015/11/14 杨杰
--修改断面字段：删除ROAD_BRANCH_ID
alter table ITMS3.T_SYS_SECTION drop (ROAD_BRANCH_ID);

--2015/11/16 杨杰
--修改系统功能表数据：支持气象采集->1010，支持绿灯抓拍模式->1011,增加流量采集功能
update t_device_sys_function  set function_code='1010' where function_code='2001';
update t_device_sys_function  set function_code='1011' where function_code='2002';
insert into t_device_sys_function (DEVICE_SYS_FUNCTION_ID,FUNCTION_NAME,FUNCTION_CODE,FUNCTION_DESC) values ('1000','流量采集',‘1012’,‘支持流量采集’);

--2015/11/17 杨杰
--违法表里面增加管理机构权限代码字段
alter table T_VIO_VIOLATION add ORG_AUTHORITY_CODE VARCHAR2(32);

--2015/11/18 杨杰
--系统代码表通行状态增加“无状态”
insert into t_sys_code (CODE_TYPE, CODE_NO, CODE_NAME, EDITABLE, ENABLE_FLAG, SORT_INDEX, REMARK)
values ('501', '0', '无状态', '1', '1', null, null);
--t_vio_asso_action表道路类型字段长度改为32位
alter table t_vio_asso_action modify ROAD_TYPE varchar2(32);--区间系统表增加区间系统编号、关联的LED屏字段
delete from T_DEVICE_REGION;
alter table T_DEVICE_REGION add INTEGRATE_PLATFORM_NBR VARCHAR2(32) not null;
alter table T_DEVICE_REGION add RELATED_LED_ID VARCHAR2(256) ;
--删除资源类型表
drop table T_SYS_RESOURCE_TYPE;
--新建导出文件表
create table T_SYS_EXPORT 
(
   "task_id"            VARCHAR2(32)         not null,
   "func_key"           VARCHAR2(32)         not null,
   "file_name"          VARCHAR2(128)        not null,
   "export_sql"         VARCHAR2(4000)       not null,
   "export_sql2"        VARCHAR2(4000),
   "status"             CHAR(1)              not null,
   "update_time"        DATE                 not null,
   "login_name"         VARCHAR2(32)         not null,
   "file_url"           VARCHAR2(128),
   "remark"             VARCHAR2(128),
   constraint PK_T_SYS_EXPORT primary key ("task_id")
);

comment on table T_SYS_EXPORT is
'存放导出的文件。';

comment on column T_SYS_EXPORT."task_id" is
'导出文件表ID';

comment on column T_SYS_EXPORT."func_key" is
'对应功能唯一码';

comment on column T_SYS_EXPORT."file_name" is
'导出文件名称';

comment on column T_SYS_EXPORT."export_sql" is
'导出文件sql';

comment on column T_SYS_EXPORT."export_sql2" is
'导出文件备用SQL。如果SQL过长可以切割成两个，字段加上可暂时不考虑。';

comment on column T_SYS_EXPORT."status" is
'导出状态
1 新增
2 排队中
3 正在导出 
4  已完成';

comment on column T_SYS_EXPORT."update_time" is
'更新时间';

comment on column T_SYS_EXPORT."login_name" is
'操作人';

comment on column T_SYS_EXPORT."file_url" is
'文件路径';

comment on column T_SYS_EXPORT."remark" is
'备注';

--删除设备配置的点位不在点位表里的设备
delete from t_device_sys where t_device_sys.site_id not in 
   ( select distinct t_sys_site.site_id from t_sys_site);
--删除设备表中所有数据
delete from t_device_sys
--code表组间类型增加11：第三方图片存储服务器
insert into t_sys_code (CODE_TYPE, CODE_NO, CODE_NAME, EDITABLE, ENABLE_FLAG, SORT_INDEX, REMARK)
values ('436', '11', '第三方图片存储服务器', '1', '1', null, null);
--code表将a：云台控制服务器->10：云台控制服务器
update T_SYS_CODE  set code_no='10' where code_no='a' and code_type='436';
--断面表增加进出城标记字段
alter table T_SYS_SECTION add ENTER_OR_EXIT_CITY CHAR(1);

--2015/11/19 杨杰
--修改code表进出城标记的值
delete from T_SYS_CODE where code_type='410' and code_no='2';
update T_SYS_CODE set code_no='2' where code_type='410' and code_no='3';

--code_type表和code表增加导出状态
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('163', '导出状态','status', '1', null, '1');
insert into t_sys_code (CODE_TYPE, CODE_NO, CODE_NAME, EDITABLE, ENABLE_FLAG, SORT_INDEX, REMARK)
values ( '163', '1', '新增', '1', '1', null, null);
insert into t_sys_code (CODE_TYPE, CODE_NO, CODE_NAME, EDITABLE, ENABLE_FLAG, SORT_INDEX, REMARK)
values ( '163', '2', '排队中', '1', '1', null, null);
insert into t_sys_code (CODE_TYPE, CODE_NO, CODE_NAME, EDITABLE, ENABLE_FLAG, SORT_INDEX, REMARK)
values ( '163', '3', '正在导出', '1', '1', null, null);
insert into t_sys_code (CODE_TYPE, CODE_NO, CODE_NAME, EDITABLE, ENABLE_FLAG, SORT_INDEX, REMARK)
values ( '163', '4', '已完成', '1', '1', null, null);

--code表方向类型去掉进城、出城值
delete from T_SYS_CODE where code_type='070' and code_no='20';
delete from T_SYS_CODE where code_type='070' and code_no='21';

--时间处理单修改处理人、处理时间、处理人电话字段名称
alter table ITMS3.T_TRAFFIC_EVENT_PROCESS rename column CONFIRM_PERSON to DEAL_PERSON;
alter table ITMS3.T_TRAFFIC_EVENT_PROCESS rename column CONFIRM_TIME to DEAL_TIME;
alter table ITMS3.T_TRAFFIC_EVENT_PROCESS rename column CONFIRM_PERSON_PHONE to DEAL_PERSON_PHONE;

--清除道路表中数据
delete from t_sys_road

--修改道路方向类型
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('071', '方向类型（城市）','DIRECTION_TYPE_CITY', '1', null, '1'); 
insert into t_sys_code (CODE_TYPE, CODE_NO, CODE_NAME, EDITABLE, ENABLE_FLAG, SORT_INDEX, REMARK)
values ( '071', '1', '由东向西', '1', '1', null, null);
insert into t_sys_code (CODE_TYPE, CODE_NO, CODE_NAME, EDITABLE, ENABLE_FLAG, SORT_INDEX, REMARK)
values ( '071', '2', '由西向东', '1', '1', null, null);
insert into t_sys_code (CODE_TYPE, CODE_NO, CODE_NAME, EDITABLE, ENABLE_FLAG, SORT_INDEX, REMARK)
values ( '071', '3', '由南向北', '1', '1', null, null);
insert into t_sys_code (CODE_TYPE, CODE_NO, CODE_NAME, EDITABLE, ENABLE_FLAG, SORT_INDEX, REMARK)
values ( '071', '4', '由北向南', '1', '1', null, null);
insert into t_sys_code (CODE_TYPE, CODE_NO, CODE_NAME, EDITABLE, ENABLE_FLAG, SORT_INDEX, REMARK)
values ( '071', '5', '东北', '1', '1', null, null);
insert into t_sys_code (CODE_TYPE, CODE_NO, CODE_NAME, EDITABLE, ENABLE_FLAG, SORT_INDEX, REMARK)
values ( '071', '6', '西南', '1', '1', null, null);
insert into t_sys_code (CODE_TYPE, CODE_NO, CODE_NAME, EDITABLE, ENABLE_FLAG, SORT_INDEX, REMARK)
values ( '071', '7', '东南', '1', '1', null, null);
insert into t_sys_code (CODE_TYPE, CODE_NO, CODE_NAME, EDITABLE, ENABLE_FLAG, SORT_INDEX, REMARK)
values ( '071', '8', '西北', '1', '1', null, null);
delete from t_sys_code where code_type='070' and code_no='3';
delete from t_sys_code where code_type='070' and code_no='4';
delete from t_sys_code where code_type='070' and code_no='5';
delete from t_sys_code where code_type='070' and code_no='6';
delete from t_sys_code where code_type='070' and code_no='7';
delete from t_sys_code where code_type='070' and code_no='8';
delete from t_sys_code where code_type='070' and code_no='9';
delete from t_sys_code where code_type='070' and code_no='10';

--2015/11/20  杨杰
--创建限行信息表
CREATE TABLE T_VEHTRACK_RESTRICT 
(
   RESTRICT_INFO_ID     VARCHAR2(32)         NOT NULL,
   ROAD_ID              VARCHAR2(5),
   TOLLGATE_ID_LIST     VARCHAR2(256),
   RESTRICT_VEHICLE_TYPE CHAR(2),
   RESTRICT_OUTSIDE_VEHICLE CHAR(1),
   RESTRICT_TIME_PERIOD VARCHAR2(64),
   RESTRICT_DIRECTION   CHAR(1),
   RESTRICT_PLATE_NBR   CHAR(1),
   RESTRICT_LAST_PLATE_NBR VARCHAR2(32),
   FESTIVAL_ID          VARCHAR2(32),
   MANAGE_ORG           VARCHAR2(32),
   VALID_START_DATE     DATE,
   VALID_END_DATE       DATE,
   CONSTRAINT PK_T_VEHTRACK_RESTRICT PRIMARY KEY (RESTRICT_INFO_ID)
);

COMMENT ON TABLE T_VEHTRACK_RESTRICT IS
'按照道路、车型、归属地、时段等条件限行。';

COMMENT ON COLUMN T_VEHTRACK_RESTRICT.ROAD_ID IS
'道路编码';

COMMENT ON COLUMN T_VEHTRACK_RESTRICT.RESTRICT_VEHICLE_TYPE IS
'H1：大货车；H2：中型货车；';

COMMENT ON COLUMN T_VEHTRACK_RESTRICT.RESTRICT_OUTSIDE_VEHICLE IS
'0：不分属地；1：限外市车；2：限外省车。';

COMMENT ON COLUMN T_VEHTRACK_RESTRICT.RESTRICT_TIME_PERIOD IS
'多个时段组合。例如：6.5，9；16.5，20表示早上6点半至9点，下午4点半至晚上8点。';

COMMENT ON COLUMN T_VEHTRACK_RESTRICT.RESTRICT_DIRECTION IS
'0：不分方向；
1：上行；
2：下行；
3：由东向西
4：由西向东
5：由南向北
6：由北向南
7：东北
8：东南
9：西南
10：西北
COMMENT ON COLUMN T_VEHTRACK_RESTRICT.RESTRICT_PLATE_NBR IS
'1 单号禁行
2 双号禁行';

--创建恶劣天气人工记录表
CREATE TABLE T_TRAFFIC_MANUAL_WEATHER 
(
   BAD_WEATHER_ID       VARCHAR2(32)         NOT NULL,
   METEOROLOGY_TYPE     CHAR(1),
   ROAD_ID              VARCHAR2(32),
   ROAD_SECTION_ID      VARCHAR2(32),
   DIRECTION_TYPE       CHAR(1),
   DIRECTION_NAME       VARCHAR2(32),
   LON_LAT              VARCHAR2(256),
   EVENT_DESC           VARCHAR2(128),
   REPORT_BY            VARCHAR2(32),
   REPORT_TIME          DATE,
   EVENT_RELEASE_TYPE   CHAR(1),
   ORG_CODE             VARCHAR2(18)         NOT NULL,
   ORG_PRIVILEGE_CODE   VARCHAR2(8)          NOT NULL,
   REMARK               VARCHAR2(256),
   CONSTRAINT PK_T_TRAFFIC_MANUAL_WEATHER PRIMARY KEY (BAD_WEATHER_ID)
);

COMMENT ON TABLE T_TRAFFIC_MANUAL_WEATHER IS
'人工记录恶劣天气';

COMMENT ON COLUMN T_TRAFFIC_MANUAL_WEATHER.METEOROLOGY_TYPE IS
'1 能见度预警
2 路面温度预警
3 路面积水预警
4 风力预警
5 路面结冰预警';

COMMENT ON COLUMN T_TRAFFIC_MANUAL_WEATHER.REPORT_BY IS
'人工上报预警信息时候，填写此字段。';

COMMENT ON COLUMN T_TRAFFIC_MANUAL_WEATHER.EVENT_RELEASE_TYPE IS
'0 未解除
1 已解除';

COMMENT ON COLUMN T_TRAFFIC_MANUAL_WEATHER.ORG_PRIVILEGE_CODE IS
'机构权限过滤代码编码规则，例：安徽省总队编码用2位表示，34；总队下面的支队用4位编码表示，3401；大队用6位编码表示，340101；中队用8位编码表示，34010101。
支队、大队、中队包括高速支队、大队等机构编码序号系统自动生成。';

--创建道路事件人工记录表
CREATE TABLE T_TRAFFIC_MANUAL_EVENT 
(
   ROAD_EVENT_ID        VARCHAR2(32)         NOT NULL,
   EVENT_TYPE           CHAR(2),
   ROAD_ID              VARCHAR2(32),
   ROAD_SECTION_ID      VARCHAR2(32),
   DIRECTION_TYPE       CHAR(1),
   DIRECTION_NAME       VARCHAR2(32),
   LON_LAT              VARCHAR2(256),
   EVENT_DESC           VARCHAR2(128),
   REPORT_BY            VARCHAR2(32),
   REPORT_TIME          DATE,
   EVENT_RELEASE_TYPE   CHAR(1),
   ORG_CODE             VARCHAR2(18)         NOT NULL,
   ORG_PRIVILEGE_CODE   VARCHAR2(8)          NOT NULL,
   REMARK               VARCHAR2(256),
   CONSTRAINT PK_T_TRAFFIC_MANUAL_EVENT PRIMARY KEY (ROAD_EVENT_ID)
);

COMMENT ON TABLE T_TRAFFIC_MANUAL_EVENT IS
'人工记录道路上发生的拥堵、事故等事件';

COMMENT ON COLUMN T_TRAFFIC_MANUAL_EVENT.EVENT_TYPE IS
'0  抛洒物
1  拥堵
2 停车
3 违停
4 逆行
5 行人
6 遗留物，抛洒物碎片
7 烟雾
';

COMMENT ON COLUMN T_TRAFFIC_MANUAL_EVENT.REPORT_BY IS
'人工上报预警信息时候，填写此字段。';

COMMENT ON COLUMN T_TRAFFIC_MANUAL_EVENT.EVENT_RELEASE_TYPE IS
'0 未解除
1 已解除';

COMMENT ON COLUMN T_TRAFFIC_MANUAL_EVENT.ORG_PRIVILEGE_CODE IS
'机构权限过滤代码编码规则，例：安徽省总队编码用2位表示，34；总队下面的支队用4位编码表示，3401；大队用6位编码表示，340101；中队用8位编码表示，34010101。
支队、大队、中队包括高速支队、大队等机构编码序号系统自动生成。';

--MAP_CHISHUI_GAOSU表增加road_code,区间ID字段
alter table MAP_CHISHUI_GAOSU add road_code VARCHAR2(6);
alter table MAP_CHISHUI_GAOSU add REGIONAL_ID VARCHAR2(32);

--2015/11/23  杨杰
--相机编号（设备编号）增加唯一性约束
alter table T_DEVICE_SYS_COMPONENT add unique (DEVICE_NBR);

--修改区间系统表限速值字段类型
alter table T_DEVICE_REGION modify LIMIT_OTHERS NUMBER(3);
alter table T_DEVICE_REGION modify LIMIT_LARGE_MARGIN NUMBER(3);
alter table T_DEVICE_REGION modify LIMIT_SMALL_MARGIN NUMBER(3);

--修改道路区间表，区间距离字段可空
alter table T_SYS_REGION modify DISTANCE NUMBER(10) null ;

--修改设备故障/报警表，删除故障解除标识，故障解决时间->处理时间
alter table T_DEVICE_FAULT drop (RESOLVE_FLAG);
alter table T_DEVICE_FAULT rename column RESOLVE_TIME to PROCESS_TIME;

--修改code表，故障处理状态
delete from t_sys_code where code_type='466';
insert into t_sys_code (CODE_TYPE, CODE_NO, CODE_NAME, EDITABLE, ENABLE_FLAG, SORT_INDEX, REMARK)
values ('466', '0', '未处理', '1', '1', null, null);
insert into t_sys_code (CODE_TYPE, CODE_NO, CODE_NAME, EDITABLE, ENABLE_FLAG, SORT_INDEX, REMARK)
values ('466', '1', '无需处理', '1', '1', null, null);
insert into t_sys_code (CODE_TYPE, CODE_NO, CODE_NAME, EDITABLE, ENABLE_FLAG, SORT_INDEX, REMARK)
values ('466', '2', '未解决', '1', '1', null, null);
insert into t_sys_code (CODE_TYPE, CODE_NO, CODE_NAME, EDITABLE, ENABLE_FLAG, SORT_INDEX, REMARK)
values ('466', '3', '已解决', '1', '1', null, null);

--修改断面表，车道数以及3个标识字段可为空,方向名称不可为空
alter table T_SYS_SECTION modify LANE_NUM null;
alter table T_SYS_SECTION modify HAS_EMERGENCY_LANE null;
alter table T_SYS_SECTION modify HAS_BICYCLE_LANE null;
alter table T_SYS_SECTION modify HAS_PAVEMENT null;
alter table T_SYS_SECTION modify DIRECTION_NAME not null;

--修改设备表，删除停用标识、报废时间、统计检定率标识、统计在线率标识，启用时间->使用状态更新时间
alter table T_DEVICE_SYS drop (DISABLE_FLAG);
alter table T_DEVICE_SYS drop (USELESS_DATE);
alter table T_DEVICE_SYS drop (STAT_CERT_RATE);
alter table T_DEVICE_SYS drop (STAT_ONLINE_RATE);
alter table T_DEVICE_SYS rename column ENABLE_DATE to ENABLE_UPDATE_DATE;

--修改code表，使用状态：1 启用 2 停用 3 报废
delete from t_sys_code where code_type='405';
insert into t_sys_code (CODE_TYPE, CODE_NO, CODE_NAME, EDITABLE, ENABLE_FLAG, SORT_INDEX, REMARK)
values ('405', '0', '未启用', '1', '1', null, null);
insert into t_sys_code (CODE_TYPE, CODE_NO, CODE_NAME, EDITABLE, ENABLE_FLAG, SORT_INDEX, REMARK)
values ('405', '1', '启用', '1', '1', null, null);
insert into t_sys_code (CODE_TYPE, CODE_NO, CODE_NAME, EDITABLE, ENABLE_FLAG, SORT_INDEX, REMARK)
values ('405', '2', '停用', '1', '1', null, null);
insert into t_sys_code (CODE_TYPE, CODE_NO, CODE_NAME, EDITABLE, ENABLE_FLAG, SORT_INDEX, REMARK)
values ('405', '3', '报废', '1', '1', null, null);

--2015/11/24  杨杰（测试数据库同步到23号）
--MAP_CHISHUI_GAOSU表增加道路起点名称、道路终点名称字段
alter table MAP_CHISHUI_GAOSU add FRoadName VARCHAR2(64);
alter table MAP_CHISHUI_GAOSU add BRoadName VARCHAR2(64);
COMMENT ON COLUMN MAP_CHISHUI_GAOSU.FRoadName IS '起点名称';
COMMENT ON COLUMN MAP_CHISHUI_GAOSU.BRoadName IS '终点名称';

--修改进行限行表，增加限行组合数字段
alter table T_VEHTRACK_RESTRICT add RESTRICT_CONBINATION CHAR(1);
COMMENT ON COLUMN T_VEHTRACK_RESTRICT.RESTRICT_CONBINATION IS '限行组合数。值为1-7，为限行条件数。';

COMMENT ON COLUMN T_VEHTRACK_RESTRICT.RESTRICT_INFO_ID IS '限行信息ID';
COMMENT ON COLUMN T_VEHTRACK_RESTRICT.TOLLGATE_ID_LIST IS '卡口ID列表';
COMMENT ON COLUMN T_VEHTRACK_RESTRICT.RESTRICT_LAST_PLATE_NBR IS '号牌尾数禁止';
COMMENT ON COLUMN T_VEHTRACK_RESTRICT.FESTIVAL_ID IS '节假日限行';
COMMENT ON COLUMN T_VEHTRACK_RESTRICT.MANAGE_ORG IS '管理部门';
COMMENT ON COLUMN T_VEHTRACK_RESTRICT.VALID_START_DATE IS '有效期始';
COMMENT ON COLUMN T_VEHTRACK_RESTRICT.VALID_END_DATE IS '有效期至';

--修改故障/报警日志表，增加组间ID字段
alter table T_DEVICE_FAULT_LOG add SYS_COMPONENT_ID VARCHAR2(32);
COMMENT ON COLUMN T_DEVICE_FAULT_LOG.SYS_COMPONENT_ID IS '组间ID';
COMMENT ON COLUMN T_DEVICE_FAULT_LOG.DEVICE_ID IS '电子监控系统ID';

--修改设备故障/报警表
alter table T_DEVICE_FAULT add SYS_COMPONENT_ID VARCHAR2(32);
COMMENT ON COLUMN T_DEVICE_FAULT.SYS_COMPONENT_ID IS '组间ID';
COMMENT ON COLUMN T_DEVICE_FAULT.DEVICE_ID IS '电子监控系统ID';


--2015/11/25  杨杰
--设备状态增加：4 异常
insert into t_sys_code (CODE_TYPE, CODE_NO, CODE_NAME, EDITABLE, ENABLE_FLAG, SORT_INDEX, REMARK)
values ('412', '4', '异常', '1', '1', null, null);

--2015/11/26  杨杰
--修改维护单登记表
alter table T_DEVICE_MAINTAIN_REGISTER modify CREATE_TIME null;
alter table T_DEVICE_MAINTAIN_REGISTER rename column CREATE_BY to ASSIGN_BY;
alter table T_DEVICE_MAINTAIN_REGISTER rename column CREATE_TIME to ASSIGN_TIME;

--创建故障与维护单关系表
CREATE TABLE T_DEVICE_FAULT_MAINTAIN 
(
   FAULT_MAINTENANCE_ID VARCHAR2(32)         NOT NULL,
   MAINTENANCE_ID       VARCHAR2(32)         NOT NULL,
   FAULT_ID             VARCHAR2(32)         NOT NULL,
   CONSTRAINT PK_T_DEVICE_FAULT_MAINTAIN PRIMARY KEY (FAULT_MAINTENANCE_ID)
);

COMMENT ON TABLE T_DEVICE_FAULT_MAINTAIN IS
'故障与维护单关系表';
COMMENT ON COLUMN T_DEVICE_FAULT_MAINTAIN.FAULT_MAINTENANCE_ID IS
'故障与维护单关系ID';
COMMENT ON COLUMN T_DEVICE_FAULT_MAINTAIN.MAINTENANCE_ID IS
'维护单ID';
COMMENT ON COLUMN T_DEVICE_FAULT_MAINTAIN.FAULT_ID IS
'故障信息ID';

ALTER TABLE T_DEVICE_FAULT_MAINTAIN
   ADD CONSTRAINT FK_T_DEVICE_REFERENCE_T_FAULT FOREIGN KEY (FAULT_ID)
      REFERENCES T_DEVICE_FAULT (FAULT_ID);

ALTER TABLE T_DEVICE_FAULT_MAINTAIN
   ADD CONSTRAINT FK_T_DEVICE_MAINTENANCE FOREIGN KEY (MAINTENANCE_ID)
      REFERENCES T_DEVICE_MAINTAIN_REGISTER (MAINTENANCE_ID);

--删除故障/报警表维护单ID
alter table T_DEVICE_FAULT drop (MAINTENANCE_ID);


--2015/11/27  杨杰
--修改维护单登记表字段类型
alter table T_DEVICE_MAINTAIN_REGISTER drop (PHOTO);
alter table T_DEVICE_MAINTAIN_REGISTER add MAINTENANCE_PHOTO VARCHAR2(256);
COMMENT ON COLUMN T_DEVICE_MAINTAIN_REGISTER.MAINTENANCE_PHOTO IS '图片';

--导出表增加功能名称、创建时间
alter table T_SYS_EXPORT ADD func_name VARCHAR2(32);
alter table T_SYS_EXPORT ADD create_time DATE;
COMMENT ON COLUMN T_SYS_EXPORT.func_name IS '功能名称';
COMMENT ON COLUMN T_SYS_EXPORT.create_time IS '创建时间';


--2015/11/28  杨杰
--道路表增加方向类型字段
alter table T_SYS_ROAD ADD DIRECTION_TYPE VARCHAR2(6);
COMMENT ON COLUMN T_SYS_ROAD.DIRECTION_TYPE is '方向类型';

--删除道路表上行方向名称、下行方向名称字段

--路段表增加方向类型字段
alter table T_SYS_ROAD_SECTION ADD DIRECTION_TYPE VARCHAR2(6);
COMMENT ON COLUMN T_SYS_ROAD_SECTION.DIRECTION_TYPE is '方向类型';

--路段表上行方向名称、下行方向名称改为起点名称、重点名称

--2015/11/30  杨杰
--故障报警表增加机构权限代码字段
delete from T_DEVICE_FAULT;
alter table T_DEVICE_FAULT add ORG_PRIVILEGE_CODE VARCHAR2(8) not null;
COMMENT ON COLUMN  T_DEVICE_FAULT.ORG_PRIVILEGE_CODE is '机构权限过滤代码';

--故障报警日志表增加机构权限代码字段
delete from T_DEVICE_FAULT_LOG;
alter table T_DEVICE_FAULT_LOG add ORG_PRIVILEGE_CODE VARCHAR2(8) not null;
COMMENT ON COLUMN  T_DEVICE_FAULT_LOG.ORG_PRIVILEGE_CODE is '机构权限过滤代码';

--维护单登记表增加机构权限代码字段
delete from T_DEVICE_MAINTAIN_REGISTER ;
alter table T_DEVICE_MAINTAIN_REGISTER add ORG_PRIVILEGE_CODE VARCHAR2(8) not null;
COMMENT ON COLUMN  T_DEVICE_MAINTAIN_REGISTER.ORG_PRIVILEGE_CODE is '机构权限过滤代码';

--新增设备权限分项表
CREATE TABLE T_DEVICE_SHARE_RIGHTS 
(
   SHARE_ID             VARCHAR2(32)         NOT NULL,
   DEVICE_ID            VARCHAR2(32)         NOT NULL,
   ORG_PRIVILEGE_CODE   VARCHAR2(8)          NOT NULL,
   CONSTRAINT PK_T_DEVICE_SHARE_RIGHTS PRIMARY KEY (SHARE_ID)
);

COMMENT ON TABLE T_DEVICE_SHARE_RIGHTS IS
'设备权限分享表';

COMMENT ON COLUMN T_DEVICE_SHARE_RIGHTS.ORG_PRIVILEGE_CODE IS
'机构权限过滤代码编码规则，例：安徽省总队编码用2位表示，34；总队下面的支队用4位编码表示，3401；大队用6位编码表示，340101；中队用8位编码表示，34010101。支队、大队、中队包括高速支队、大队等机构编码序号系统自动生成。';

ALTER TABLE T_DEVICE_SHARE_RIGHTS
   ADD CONSTRAINT FK_T_DEVICE_REFERENCE_T_SHARE FOREIGN KEY (DEVICE_ID)
      REFERENCES T_DEVICE_SYS (DEVICE_ID);

--电子监控系统表增加道路ID字段
alter table T_DEVICE_SYS add ROAD_ID VARCHAR2(32);
COMMENT ON COLUMN T_DEVICE_SYS.ROAD_ID is '道路ID';
alter table T_DEVICE_SYS add ORG_PRIVILEGE_CODE VARCHAR2(8) ;
COMMENT ON COLUMN T_DEVICE_SYS.ORG_PRIVILEGE_CODE is '机构权限过滤代码'

--路段表增加路段起点名称、路段结束点名称，删除上行方向名称、下行方向名称、方向类型

alter table T_SYS_ROAD_SECTION add ROAD_BEGIN_NAME VARCHAR2(128);
alter table T_SYS_ROAD_SECTION add ROAD_END_NAME VARCHAR2(128);
COMMENT ON COLUMN T_SYS_ROAD_SECTION.ROAD_BEGIN_NAME is'路段起点名称';
COMMENT ON COLUMN T_SYS_ROAD_SECTION. ROAD_END_NAME is'路段结束点名称';

alter table T_SYS_ROAD_SECTION drop (UP_DIRECTION);
alter table T_SYS_ROAD_SECTION drop (DOWN_DIRECTION);
alter table T_SYS_ROAD_SECTION drop (DIRECTION_TYPE);

--能见度数据表、气象数据表、路感数据表删除路况预警事件ID
alter table T_TRAFFIC_VISIBILITY drop (ALARM_EVENT_ID);
alter table T_TRAFFIC_WEATHER drop (ALARM_EVENT_ID);
alter table T_TRAFFIC_ROADSENSOR drop (ALARM_EVENT_ID);

--能见度数据表、气象数据表、路感数据表记录时间改为不可为空
alter table T_TRAFFIC_VISIBILITY modify RECORD_TIME not null;
alter table T_TRAFFIC_WEATHER modify RECORD_TIME not null;
alter table T_TRAFFIC_ROADSENSOR modify RECORD_TIME not null;

--2015/12/1  杨杰

--违法表增加路口代码字段,将路口/路段代码改为路段代码；
alter table T_VIO_VIOLATION add CROSS_CODE VARCHAR2(12);
alter table T_VIO_VIOLATION rename column JUNCTION_CODE to ROAD_SECTION_CODE;

--创建网巡预案信息表
create table T_TRAFFIC_PLAN 
(
   VIDEO_PLAN_ID        VARCHAR2(32)         not null,
   ORG_ID               VARCHAR2(32)         not null,
   VIDEO_PLAN_NAME      VARCHAR2(256)        not null,
   ENABLE_FLAG          CHAR(1)              not null,
   CREATE_BY            VARCHAR2(32)         not null,
   CREATE_TIME          DATE                 not null,
   UPDATE_BY            VARCHAR2(32),
   UPDATE_TIME          DATE,
   constraint PK_T_TRAFFIC_PLAN primary key (VIDEO_PLAN_ID)
);

comment on table T_TRAFFIC_PLAN is
'网巡预案信息';

--创建网巡日志信息表
create table T_TRAFFIC_PLAN_LOG 
(
   VIDEO_PLAN_LOG_ID    VARCHAR2(32)         not null,
   VIDEO_PLAN_ID        VARCHAR2(32),
   VIDEO_PLAN_NBR       VARCHAR2(32),
   VIDEO_PLAN_START_TIME DATE                 not null,
   VIDEO_PLAN_END_TIME  DATE                 not null,
   VIDEO_PLAN_PERSON    VARCHAR2(32)         not null,
   COMPUTER_IP          VARCHAR2(32),
   constraint PK_T_TRAFFIC_PLAN_LOG primary key (VIDEO_PLAN_LOG_ID)
);

comment on table T_TRAFFIC_PLAN_LOG is
'记录每次网巡的日志信息';

create index "Relationship_2_FK" on T_TRAFFIC_PLAN_LOG (
   VIDEO_PLAN_ID ASC
);


---创建网巡视频日志信息表
create table T_TRAFFIC_VIDEO_LOG 
(
   VIDEO_LOG_ID         VARCHAR2(32)         not null,
   VIDEO_PLAN_LOG_ID    VARCHAR2(32),
   DEVICE_ID            VARCHAR2(32)         not null,
   QUESTION_FIND        VARCHAR2(256),
   RESULT_DESC          VARCHAR2(256),
   VIDEO_PLAN_START_TIME DATE                 not null,
   VIDEO_PLAN_END_TIME  DATE                 not null,
   constraint PK_T_TRAFFIC_VIDEO_LOG primary key (VIDEO_LOG_ID)
);

comment on table T_TRAFFIC_VIDEO_LOG is
'记录一次网巡过程中单独的视频日志信息。';

create index "Relationship_3_FK" on T_TRAFFIC_VIDEO_LOG (VIDEO_PLAN_LOG_ID ASC);

alter table T_TRAFFIC_VIDEO_LOG
   add constraint FK_T_TRAFFIC_T_VIDEO foreign key (VIDEO_PLAN_LOG_ID)
      references T_TRAFFIC_PLAN_LOG (VIDEO_PLAN_LOG_ID);

--创建预案关联视频信息表
create table T_TRAFFIC_PLAN_VIDEO 
(
   RELATED_VIDEO_ID     VARCHAR2(32)          not null,
   VIDEO_PLAN_ID        VARCHAR2(32),
   DEVICE_ID            VARCHAR2(32)         not null,
   SORT_INDEX           INTEGER              not null,
   PRESET_LIST          VARCHAR2(1024)       not null,
   CRUISE_TIME          INTEGER,
   constraint PK_T_TRAFFIC_PLAN_VIDEO primary key (RELATED_VIDEO_ID)
);

comment on table T_TRAFFIC_PLAN_VIDEO is
'预案关联视频信息';

comment on column T_TRAFFIC_PLAN_VIDEO.SORT_INDEX is
'视频设备在一个预案中的排序。';

comment on column T_TRAFFIC_PLAN_VIDEO.PRESET_LIST is
'以“|”隔开';

comment on column T_TRAFFIC_PLAN_VIDEO.CRUISE_TIME is
'自动预案时不可为空，人工预案时为空。';

create index "Relationship_1_FK" on T_TRAFFIC_PLAN_VIDEO (
   VIDEO_PLAN_ID ASC
);

alter table T_TRAFFIC_PLAN_VIDEO
   add constraint FK_T_TRAFFIC_RELATED_VIDEO foreign key (VIDEO_PLAN_ID)
      references T_TRAFFIC_PLAN (VIDEO_PLAN_ID);

--创建视频预置位信息表
create table T_TRAFFIC_PRESET 
(
   PRESET_RECORD_ID     VARCHAR2(32)         not null,
   DEVICE_ID            VARCHAR2(32)         not null,
   SYS_COMPONENT_ID     VARCHAR2(32),
   PRESET               INTEGER              not null,
   PRESET_DESC          VARCHAR2(32),
   CREATE_BY            VARCHAR2(32)         not null,
   CREATE_TIME          DATE                 not null,
   UPDATE_BY            VARCHAR2(32),
   UPDATE_TIME          DATE,
   constraint PK_T_TRAFFIC_PRESET primary key (PRESET_RECORD_ID)
);

comment on table T_TRAFFIC_PRESET is
'存储视频的预置位信息';

--2015/12/2  杨杰
--路况预警事件子类型中事件类型
delete from t_sys_code where code_type='512' and code_no between 300 and 306;
insert into t_sys_code (CODE_TYPE, CODE_NO, CODE_NAME, EDITABLE, ENABLE_FLAG, SORT_INDEX, REMARK)
values ('512', '300', '拥堵', '1', '1', null, null);
insert into t_sys_code (CODE_TYPE, CODE_NO, CODE_NAME, EDITABLE, ENABLE_FLAG, SORT_INDEX, REMARK)
values ('512', '301', '停车', '1', '1', null, null);
insert into t_sys_code (CODE_TYPE, CODE_NO, CODE_NAME, EDITABLE, ENABLE_FLAG, SORT_INDEX, REMARK)
values ('512', '302', '逆行', '1', '1', null, null);
insert into t_sys_code (CODE_TYPE, CODE_NO, CODE_NAME, EDITABLE, ENABLE_FLAG, SORT_INDEX, REMARK)
values ('512', '303', '行人', '1', '1', null, null);
insert into t_sys_code (CODE_TYPE, CODE_NO, CODE_NAME, EDITABLE, ENABLE_FLAG, SORT_INDEX, REMARK)
values ('512', '304', '遗留物，抛洒物碎片', '1', '1', null, null);
insert into t_sys_code (CODE_TYPE, CODE_NO, CODE_NAME, EDITABLE, ENABLE_FLAG, SORT_INDEX, REMARK)
values ('512', '305', '烟雾', '1', '1', null, null);
insert into t_sys_code (CODE_TYPE, CODE_NO, CODE_NAME, EDITABLE, ENABLE_FLAG, SORT_INDEX, REMARK)
values ('512', '306', '压线', '1', '1', null, null);
insert into t_sys_code (CODE_TYPE, CODE_NO, CODE_NAME, EDITABLE, ENABLE_FLAG, SORT_INDEX, REMARK)
values ('512', '307', '黑名单数据', '1', '1', null, null);
insert into t_sys_code (CODE_TYPE, CODE_NO, CODE_NAME, EDITABLE, ENABLE_FLAG, SORT_INDEX, REMARK)
values ('512', '308', '超速', '1', '1', null, null);
insert into t_sys_code (CODE_TYPE, CODE_NO, CODE_NAME, EDITABLE, ENABLE_FLAG, SORT_INDEX, REMARK)
values ('512', '309', '变道', '1', '1', null, null);
insert into t_sys_code (CODE_TYPE, CODE_NO, CODE_NAME, EDITABLE, ENABLE_FLAG, SORT_INDEX, REMARK)
values ('512', '310', '掉头', '1', '1', null, null);

--修改事件类型值的类型
delete from t_sys_code where code_type='434';
insert into t_sys_code (CODE_TYPE, CODE_NO, CODE_NAME, EDITABLE, ENABLE_FLAG, SORT_INDEX, REMARK)
values ('434', '0', '拥堵', '1', '1', null, null);
insert into t_sys_code (CODE_TYPE, CODE_NO, CODE_NAME, EDITABLE, ENABLE_FLAG, SORT_INDEX, REMARK)
values ('434', '1', '停车', '1', '1', null, null);
insert into t_sys_code (CODE_TYPE, CODE_NO, CODE_NAME, EDITABLE, ENABLE_FLAG, SORT_INDEX, REMARK)
values ('434', '2', '逆行', '1', '1', null, null);
insert into t_sys_code (CODE_TYPE, CODE_NO, CODE_NAME, EDITABLE, ENABLE_FLAG, SORT_INDEX, REMARK)
values ('434', '3', '行人', '1', '1', null, null);
insert into t_sys_code (CODE_TYPE, CODE_NO, CODE_NAME, EDITABLE, ENABLE_FLAG, SORT_INDEX, REMARK)
values ('434', '4', '遗留物，抛洒物碎片', '1', '1', null, null);
insert into t_sys_code (CODE_TYPE, CODE_NO, CODE_NAME, EDITABLE, ENABLE_FLAG, SORT_INDEX, REMARK)
values ('434', '5', '烟雾', '1', '1', null, null);
insert into t_sys_code (CODE_TYPE, CODE_NO, CODE_NAME, EDITABLE, ENABLE_FLAG, SORT_INDEX, REMARK)
values ('434', '6', '压线', '1', '1', null, null);
insert into t_sys_code (CODE_TYPE, CODE_NO, CODE_NAME, EDITABLE, ENABLE_FLAG, SORT_INDEX, REMARK)
values ('434', '7', '黑名单数据', '1', '1', null, null);
insert into t_sys_code (CODE_TYPE, CODE_NO, CODE_NAME, EDITABLE, ENABLE_FLAG, SORT_INDEX, REMARK)
values ('434', '8', '超速', '1', '1', null, null);
insert into t_sys_code (CODE_TYPE, CODE_NO, CODE_NAME, EDITABLE, ENABLE_FLAG, SORT_INDEX, REMARK)
values ('434', '9', '变道', '1', '1', null, null);
insert into t_sys_code (CODE_TYPE, CODE_NO, CODE_NAME, EDITABLE, ENABLE_FLAG, SORT_INDEX, REMARK)
values ('434', '10', '掉头', '1', '1', null, null);

--电子监控系统表增加承建商ID字段
alter table T_DEVICE_SYS add CONTRACTOR_ID VARCHAR2(32);
COMMENT ON COLUMN T_DEVICE_SYS.CONTRACTOR_ID is'承建商ID';

--流量表机构ID字段改为机构权限过滤代码
alter table T_FLOW_FIVE_MIN drop (ORG_CODE);
alter table T_FLOW_FIVE_MIN add ORG_PRIVILEGE_CODE VARCHAR2(8) ;
COMMENT ON COLUMN T_FLOW_FIVE_MIN.ORG_PRIVILEGE_CODE is '机构权限过滤代码';

--删除code表中预警事件子类型的值
delete from t_sys_code where code_type='512';

--违法表录入人字段长度改为32位
alter table T_VIO_VIOLATION modify ENTRY_BY VARCHAR2(32);

--道路区间表
alter table T_SYS_REGION modify DISTANCE not null;

--2015/12/3  杨杰（测试组更新到12月2号）
--修改区间距离字段类型：number(10,2)
alter table T_SYS_REGION modify DISTANCE  null;
update T_SYS_REGION  set DISTANCE='';
alter table T_SYS_REGION modify DISTANCE number(10,2);
update T_SYS_REGION  set DISTANCE='0.3';
alter table T_SYS_REGION modify DISTANCE not null;

--更新系统操作日志表中的数据
update T_SYS_OPERATION_LOG set sys_function_code='01020201';

--道路事件人工记录表事件类型字段类型改为VARCHAR2(2)
alter table T_TRAFFIC_MANUAL_EVENT modify EVENT_TYPE VARCHAR2(2);

--2015/12/4（测试更新到12月3号）
--修改区间表区间距离可为空（界面没做好）
alter table T_SYS_REGION modify DISTANCE  null;

--删除系统管理模块系统公告菜单
delete from  T_SYS_MENU where menu_code='0505';

--流量表增加创建时间字段
alter table T_FLOW_FIVE_MIN add CREATE_TIME DATE ; 
COMMENT ON COLUMN T_FLOW_FIVE_MIN.CREATE_TIME IS '创建时间';

--更新操作日志表中的数据
update T_SYS_OPERATION_LOG set ope_user_name='355e8f04ce7447929dde896e289c84ef';

--2015/12/7  杨杰
--修改危化品车行驶线路备案表，删除GPS经纬度字段，增加经纬度字段
alter table T_VEHTRACK_DANGER_VEH_REG drop (GPS);
alter table T_VEHTRACK_DANGER_VEH_REG add LON_LAT VARCHAR2(128);
COMMENT ON COLUMN T_VEHTRACK_DANGER_VEH_REG.LON_LAT is '经纬度';

--修改区间系统配置表，区间字段值类型由number(10)->number(10,2)
alter table T_DEVICE_REGION modify DISTANCE  null;
update T_DEVICE_REGION  set DISTANCE='';
alter table T_DEVICE_REGION modify DISTANCE NUMBER(10,2);
update T_DEVICE_REGION  set DISTANCE='10';
alter table T_DEVICE_REGION modify DISTANCE not null;

--修改路口类型值类型
alter table T_SYS_CROSS modify CROSS_TYPE VARCHAR2(2);
alter table T_SYS_CROSS modify CROSS_LONGITUDE NUMBER(10,6);
alter table T_SYS_CROSS modify CROSS_LATITUDE  NUMBER(10,6);

--道路表增加唯一性约束，道路编号
alter table T_SYS_ROAD add constraint ROAD_CODE_UNIQUE unique(ROAD_CODE);

--路段表增加唯一性约束，路段代码
delete from T_SYS_ROAD_SECTION;
alter table T_SYS_ROAD_SECTION add constraint ROAD_SECTION_CODE_UNIQUE unique(ROAD_SECTION_CODE);

--路口表增加唯一性约束，路口编号
alter table T_SYS_CROSS add constraint CROSS_CODE_UNIQUE unique(CROSS_CODE);

--点位表增加唯一性约束，点位代码
alter table T_SYS_SITE add constraint SITE_CODE_UNIQUE unique(SITE_CODE);

--断面表增加唯一性约束，点位ID+方向类型
--查找表中重复记录
/* select t.*, t.rowid
  from T_SYS_SECTION t
 where ((SELECT COUNT(*)
           FROM T_SYS_SECTION
          where site_id = t.site_id
            and direction_type = t.direction_type) > 1)
 order by site_id, direction_type; */

alter table T_SYS_SECTION add constraint SITE_ID_DIRECTION_TYPE unique(SITE_ID,DIRECTION_TYPE);

--区间表添加唯一性约束
alter table T_SYS_REGION add constraint REGIONAL_CODE_UNIQUE unique(REGIONAL_CODE);

--修改过车表字段值的类型
alter table T_VEHTRACK_PASS modify VEHICLE_SHAPE VARCHAR2(2);

--增加通行方式（050）的值
insert into t_sys_code (CODE_TYPE, CODE_NO, CODE_NAME, EDITABLE, ENABLE_FLAG, SORT_INDEX, REMARK)
values ( '050', '0', '超高速', '1', '1', null, null);
insert into t_sys_code (CODE_TYPE, CODE_NO, CODE_NAME, EDITABLE, ENABLE_FLAG, SORT_INDEX, REMARK)
values ( '050', '1', '超低速', '1', '1', null, null);
insert into t_sys_code (CODE_TYPE, CODE_NO, CODE_NAME, EDITABLE, ENABLE_FLAG, SORT_INDEX, REMARK)
values ( '050', '2', '冲红灯', '1', '1', null, null);
insert into t_sys_code (CODE_TYPE, CODE_NO, CODE_NAME, EDITABLE, ENABLE_FLAG, SORT_INDEX, REMARK)
values ( '050', '3', '逆行', '1', '1', null, null);
insert into t_sys_code (CODE_TYPE, CODE_NO, CODE_NAME, EDITABLE, ENABLE_FLAG, SORT_INDEX, REMARK)
values ( '050', '4', '不按车道通行', '1', '1', null, null);
insert into t_sys_code (CODE_TYPE, CODE_NO, CODE_NAME, EDITABLE, ENABLE_FLAG, SORT_INDEX, REMARK)
values ( '050', '5', '左转', '1', '1', null, null);
insert into t_sys_code (CODE_TYPE, CODE_NO, CODE_NAME, EDITABLE, ENABLE_FLAG, SORT_INDEX, REMARK)
values ( '050', '6', '右转', '1', '1', null, null);
insert into t_sys_code (CODE_TYPE, CODE_NO, CODE_NAME, EDITABLE, ENABLE_FLAG, SORT_INDEX, REMARK)
values ( '050', '7', '掉头', '1', '1', null, null);
insert into t_sys_code (CODE_TYPE, CODE_NO, CODE_NAME, EDITABLE, ENABLE_FLAG, SORT_INDEX, REMARK)
values ( '050', '8', '泊车', '1', '1', null, null);
insert into t_sys_code (CODE_TYPE, CODE_NO, CODE_NAME, EDITABLE, ENABLE_FLAG, SORT_INDEX, REMARK)
values ( '050', '9', '正常通行', '1', '1', null, null);
insert into t_sys_code (CODE_TYPE, CODE_NO, CODE_NAME, EDITABLE, ENABLE_FLAG, SORT_INDEX, REMARK)
values ( '050', '100', '套牌通行', '1', '1', null, null);
insert into t_sys_code (CODE_TYPE, CODE_NO, CODE_NAME, EDITABLE, ENABLE_FLAG, SORT_INDEX, REMARK)
values ( '050', '101', '禁行', '1', '1', null, null);

--2015/12/8  杨杰
--修改点位表
delete from t_sys_site where site_id='c587c181691a4674bfd0128cb2ed6046' or site_id='147ba844d7fa4922a9de9ddbf398de38';
alter table t_sys_site modify org_id not null;

--修改区间表区间距离不可为空（界面已经做好）
alter table T_SYS_REGION modify DISTANCE not null;

--修改路段表路段起始里程、路段结束里程字段类型
update T_SYS_ROAD_SECTION set BEGIN_METER='';
update T_SYS_ROAD_SECTION set END_METER='';
alter table T_SYS_ROAD_SECTION modify BEGIN_METER VARCHAR2(10);
alter table T_SYS_ROAD_SECTION modify END_METER   VARCHAR2(10);

--修改气象预警事件表数据
update T_TRAFFIC_ALARM_EVENT set ALARM_EVENT_TYPE='';
update T_TRAFFIC_ALARM_EVENT set SUB_ALARM_EVENT='';
alter table T_TRAFFIC_ALARM_EVENT modify ALARM_EVENT_TYPE CHAR(3)
alter table T_TRAFFIC_ALARM_EVENT modify SUB_ALARM_EVENT VARCHAR2(2)
update T_TRAFFIC_ALARM_EVENT set ALARM_EVENT_TYPE='506' where  ALARM_EVENT_ID='101011';
update T_TRAFFIC_ALARM_EVENT set SUB_ALARM_EVENT='02' where  ALARM_EVENT_ID='101011';

update T_TRAFFIC_ALARM_EVENT set ALARM_EVENT_TYPE='506' where  ALARM_EVENT_ID='101012';
update T_TRAFFIC_ALARM_EVENT set SUB_ALARM_EVENT='05' where  ALARM_EVENT_ID='101012';

update T_TRAFFIC_ALARM_EVENT set ALARM_EVENT_TYPE='502' where  ALARM_EVENT_ID='101013';
update T_TRAFFIC_ALARM_EVENT set SUB_ALARM_EVENT='2' where  ALARM_EVENT_ID='101013';

update T_TRAFFIC_ALARM_EVENT set ALARM_EVENT_TYPE='502' where  ALARM_EVENT_ID='101014';
update T_TRAFFIC_ALARM_EVENT set SUB_ALARM_EVENT='3' where  ALARM_EVENT_ID='101014';

update T_TRAFFIC_ALARM_EVENT set ALARM_EVENT_TYPE='502' where  ALARM_EVENT_ID='101015';
update T_TRAFFIC_ALARM_EVENT set SUB_ALARM_EVENT='4' where  ALARM_EVENT_ID='101015';

update T_TRAFFIC_ALARM_EVENT set ALARM_EVENT_TYPE='502' where  ALARM_EVENT_ID='101016';
update T_TRAFFIC_ALARM_EVENT set SUB_ALARM_EVENT='5' where  ALARM_EVENT_ID='101016';

update T_TRAFFIC_ALARM_EVENT set ALARM_EVENT_TYPE='434' where  ALARM_EVENT_ID='101017';
update T_TRAFFIC_ALARM_EVENT set SUB_ALARM_EVENT='3' where  ALARM_EVENT_ID='101017';

update T_TRAFFIC_ALARM_EVENT set ALARM_EVENT_TYPE='502' where  ALARM_EVENT_ID='101010';
update T_TRAFFIC_ALARM_EVENT set SUB_ALARM_EVENT='1' where  ALARM_EVENT_ID='101010';

--2015/12/9  杨杰
--修改code_type表，气象类型->气象预警类型
update T_SYS_CODE_TYPE set code_type_name='气象预警类型' where code_type='502';

--修改日常维护表字段注释。设备ID->电子监控系统ID
COMMENT ON COLUMN T_DEVICE_DAILY_MAINTAIN.DEVICE_ID is '电子监控系统ID';

--增加节假日类型code_type与code值
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('020', '节假日类型','FESTIVAL_TYPE', '1', null, '1'); 

insert into t_sys_code (CODE_TYPE, CODE_NO, CODE_NAME, EDITABLE, ENABLE_FLAG, SORT_INDEX, REMARK)
values ( '020', '1', '元旦', '1', '1', null, null);
insert into t_sys_code (CODE_TYPE, CODE_NO, CODE_NAME, EDITABLE, ENABLE_FLAG, SORT_INDEX, REMARK)
values ( '020', '2', '春节', '1', '1', null, null);
insert into t_sys_code (CODE_TYPE, CODE_NO, CODE_NAME, EDITABLE, ENABLE_FLAG, SORT_INDEX, REMARK)
values ( '020', '3', '清明节', '1', '1', null, null);
insert into t_sys_code (CODE_TYPE, CODE_NO, CODE_NAME, EDITABLE, ENABLE_FLAG, SORT_INDEX, REMARK)
values ( '020', '4', '劳动节', '1', '1', null, null);
insert into t_sys_code (CODE_TYPE, CODE_NO, CODE_NAME, EDITABLE, ENABLE_FLAG, SORT_INDEX, REMARK)
values ( '020', '5', '端午节', '1', '1', null, null);
insert into t_sys_code (CODE_TYPE, CODE_NO, CODE_NAME, EDITABLE, ENABLE_FLAG, SORT_INDEX, REMARK)
values ( '020', '6', '国庆节', '1', '1', null, null);
insert into t_sys_code (CODE_TYPE, CODE_NO, CODE_NAME, EDITABLE, ENABLE_FLAG, SORT_INDEX, REMARK)
values ( '020', '7', '中秋节', '1', '1', null, null);

--系统功能表增加服务ID字段
alter table T_SYS_FUNCTION  add SERVICE_ID VARCHAR2(32);
COMMENT ON COLUMN  T_SYS_FUNCTION.SERVICE_ID is '服务ID';

--菜单表修改父菜单编码字段长度
alter table T_SYS_MENU modify PARENT_CODE VARCHAR2(32);

--增加道路走向字段
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('072', '道路走向','ROAD_DIRECTION_TYPE', '1', null, '1'); 
insert into t_sys_code (CODE_TYPE, CODE_NO, CODE_NAME, EDITABLE, ENABLE_FLAG, SORT_INDEX, REMARK)
values ( '072', '1', '东西方向', '1', '1', null, null);
insert into t_sys_code (CODE_TYPE, CODE_NO, CODE_NAME, EDITABLE, ENABLE_FLAG, SORT_INDEX, REMARK)
values ( '072', '2', '南北方向', '1', '1', null, null);
insert into t_sys_code (CODE_TYPE, CODE_NO, CODE_NAME, EDITABLE, ENABLE_FLAG, SORT_INDEX, REMARK)
values ( '072', '3', '东北-西南方向', '1', '1', null, null);
insert into t_sys_code (CODE_TYPE, CODE_NO, CODE_NAME, EDITABLE, ENABLE_FLAG, SORT_INDEX, REMARK)
values ( '072', '4', '东南-西北方向', '1', '1', null, null);

--2015/12/10  杨杰
--更新511：路况预警事件类别
update T_SYS_CODE set code_no='506' where code_type='511' and code_no='1';
update T_SYS_CODE set code_no='502' where code_type='511' and code_no='2';
update T_SYS_CODE set code_no='434' where code_type='511' and code_no='3';
update T_SYS_CODE set code_no='500' where code_type='511' and code_no='4';

--修改相机表在线状态字段值类型
alter table T_DEVICE_SYS_COMPONENT modify ONLINE_STATUS VARCHAR2(1);

--修改道路表道路长度字段类型
update T_SYS_ROAD set ROAD_LENGTH='';
alter table T_SYS_ROAD modify ROAD_LENGTH number(10,2);

--新增断面小时流量表
CREATE TABLE T_TRAFFIC_ONE_HOUR_FLOW 
(
   SECTION_STATE_ID     VARCHAR2(32)         NOT NULL,
   SECTION_ID           VARCHAR2(32)         NOT NULL,
   TOTAL_NUM            NUMBER(6),
   LARGE_NUM            NUMBER(6),
   SMALL_NUM            NUMBER(6),
   OTHER_NUM            NUMBER(6),
   AVR_SPEED            NUMBER(6,2),
   VEH_TAIL_SPACE       NUMBER(6,2),
   UPDATE_TIME          DATE                 NOT NULL,
   ONE_HOUR_FLOW        NUMBER(6),
   ONE_HOUR_AVR_SPEED   NUMBER(6,2),
   ORG_PRIVILEGE_CODE   VARCHAR2(8)          NOT NULL,
   CONSTRAINT PK_T_TRAFFIC_ONE_HOUR_FLOW PRIMARY KEY (SECTION_STATE_ID)
);

COMMENT ON TABLE T_TRAFFIC_ONE_HOUR_FLOW IS
'断面一小时流量表';

COMMENT ON COLUMN T_TRAFFIC_ONE_HOUR_FLOW.TOTAL_NUM IS
'区间内总车数';

COMMENT ON COLUMN T_TRAFFIC_ONE_HOUR_FLOW.LARGE_NUM IS
'大车数';

COMMENT ON COLUMN T_TRAFFIC_ONE_HOUR_FLOW.SMALL_NUM IS
'小车数';

COMMENT ON COLUMN T_TRAFFIC_ONE_HOUR_FLOW.OTHER_NUM IS
'其它车数';

COMMENT ON COLUMN T_TRAFFIC_ONE_HOUR_FLOW.AVR_SPEED IS
'平均车速';

COMMENT ON COLUMN T_TRAFFIC_ONE_HOUR_FLOW.VEH_TAIL_SPACE IS
'车头时距';

COMMENT ON COLUMN T_TRAFFIC_ONE_HOUR_FLOW.ORG_PRIVILEGE_CODE IS
'机构权限过滤代码编码规则，例：安徽省总队编码用2位表示，34；总队下面的支队用4位编码表示，3401；大队用6位编码表示，340101；中队用8位编码表示，34010101。支队、大队、中队包括高速支队、大队等机构编码序号系统自动生成。';

--断面5分钟流量表删除一小时车流量、一小时平均车速平均车速字段
alter table T_TRAFFIC_SECTION_FLOW drop(ONE_HOUR_FLOW);
alter table T_TRAFFIC_SECTION_FLOW drop(ONE_HOUR_AVR_SPEED);

--断面1小时流量表删除一小时车流量、一小时平均车速平均车速字段
alter table T_TRAFFIC_ONE_HOUR_FLOW drop(ONE_HOUR_FLOW);
alter table T_TRAFFIC_ONE_HOUR_FLOW drop(ONE_HOUR_AVR_SPEED);

--增加断面一小时流量表：断面流量ID自增序列。
--创建sequence
create sequence SEQ_T_FLOW_ONE_HOUR
minvalue 1
maxvalue 99999999999
start with 1
increment by 1
cache 20;

--创建trigger
create or replace trigger TRIG_T_FLOW_ONE_HOUR
  before insert on T_TRAFFIC_ONE_HOUR_FLOW
  for each row
begin
  if(:new.SECTION_STATE_ID is null or :new.SECTION_STATE_ID = '') then
  select SEQ_T_FLOW_ONE_HOUR.nextval into :new.SECTION_STATE_ID from dual;
  end if;
end TRIG_T_FLOW_ONE_HOUR;

--日常维护表增加维护人员、联系方式字段
alter table T_DEVICE_DAILY_MAINTAIN add MAINTAIN_PERSON VARCHAR2(32);
alter table T_DEVICE_DAILY_MAINTAIN add PHONE VARCHAR2(32);
COMMENT ON COLUMN T_DEVICE_DAILY_MAINTAIN.MAINTAIN_PERSON is '维护人员';
COMMENT ON COLUMN T_DEVICE_DAILY_MAINTAIN.PHONE is '联系方式';

--车辆查控模块相关表增加车辆外形字段
--危化品车行驶线路备案表：T_VEHTRACK_DANGER_VEH_REG增加车辆外形字段
alter table T_VEHTRACK_DANGER_VEH_REG add VEHICLE_SHAPE VARCHAR2(2);
COMMENT ON COLUMN T_VEHTRACK_DANGER_VEH_REG.VEHICLE_SHAPE is '车辆外形';
--区间过车表:T_VEHTRACK_REGIONAL_PASS增加车辆外形字段
alter table T_VEHTRACK_REGIONAL_PASS add VEHICLE_SHAPE VARCHAR2(2);
COMMENT ON COLUMN T_VEHTRACK_REGIONAL_PASS.VEHICLE_SHAPE is '车辆外形';
--违法嫌疑车辆表:T_VEHTRACK_PASS_TO_VIO增加车辆外形字段
alter table T_VEHTRACK_PASS_TO_VIO add VEHICLE_SHAPE VARCHAR2(2);
COMMENT ON COLUMN T_VEHTRACK_PASS_TO_VIO.VEHICLE_SHAPE is '车辆外形';
--套牌车分析记录表:T_VEHTRACK_CLONE_VEHICLE增加车辆外形字段
alter table T_VEHTRACK_CLONE_VEHICLE add VEHICLE_SHAPE VARCHAR2(2);
COMMENT ON COLUMN T_VEHTRACK_CLONE_VEHICLE.VEHICLE_SHAPE is '车辆外形';
--假牌车分析记录表：T_VEHTRACK_FAKE_VEHICLE增加车辆外形字段
alter table T_VEHTRACK_FAKE_VEHICLE add VEHICLE_SHAPE VARCHAR2(2);
COMMENT ON COLUMN T_VEHTRACK_FAKE_VEHICLE.VEHICLE_SHAPE is '车辆外形';
--假牌/套牌车辆表：T_VEHTRACK_FAKE_CLONE增加车辆外形字段
alter table T_VEHTRACK_FAKE_CLONE add VEHICLE_SHAPE VARCHAR2(2);
COMMENT ON COLUMN T_VEHTRACK_FAKE_CLONE.VEHICLE_SHAPE is '车辆外形';
--布控撤控表：T_VEHTRACK_TRACK增加车辆外形字段
alter table T_VEHTRACK_TRACK add VEHICLE_SHAPE VARCHAR2(2);
COMMENT ON COLUMN T_VEHTRACK_TRACK.VEHICLE_SHAPE is '车辆外形';
--二次分析预警表：T_VEHTRACK_SEC_ALARM增加车辆外形字段
alter table T_VEHTRACK_SEC_ALARM add VEHICLE_SHAPE VARCHAR2(2);
COMMENT ON COLUMN T_VEHTRACK_SEC_ALARM.VEHICLE_SHAPE is '车辆外形';
--布控报警表：T_VEHTRACK_ALARM增加车辆外形字段
alter table T_VEHTRACK_ALARM add VEHICLE_SHAPE VARCHAR2(2);
COMMENT ON COLUMN T_VEHTRACK_ALARM.VEHICLE_SHAPE is '车辆外形';
--车辆信息表：T_VEHTRACK_VEHICLE增加车辆外形字段
alter table T_VEHTRACK_VEHICLE add VEHICLE_SHAPE VARCHAR2(2);
COMMENT ON COLUMN T_VEHTRACK_VEHICLE.VEHICLE_SHAPE is '车辆外形';
--重点车辆信息表：T_VEHTRACK_IMPORTANT_VEH增加车辆外形字段
alter table T_VEHTRACK_IMPORTANT_VEH add VEHICLE_SHAPE VARCHAR2(2);
COMMENT ON COLUMN T_VEHTRACK_IMPORTANT_VEH.VEHICLE_SHAPE is '车辆外形';
--车辆检查记录表：T_VEHTRACK_CHECK_REGISTER增加车辆外形字段
alter table T_VEHTRACK_CHECK_REGISTER add VEHICLE_SHAPE VARCHAR2(2);
COMMENT ON COLUMN T_VEHTRACK_CHECK_REGISTER.VEHICLE_SHAPE is '车辆外形';

--增加维护周期code_type与code值
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('483', '维护周期','MAINTENANCE_CYCLE', '1', null, '1');

insert into t_sys_code (CODE_TYPE, CODE_NO, CODE_NAME, EDITABLE, ENABLE_FLAG, SORT_INDEX, REMARK)
values ('483', '1', '一个月', '1', '1', null, null);
insert into t_sys_code (CODE_TYPE, CODE_NO, CODE_NAME, EDITABLE, ENABLE_FLAG, SORT_INDEX, REMARK)
values ('483', '2', '两个月', '1', '1', null, null);
insert into t_sys_code (CODE_TYPE, CODE_NO, CODE_NAME, EDITABLE, ENABLE_FLAG, SORT_INDEX, REMARK)
values ('483', '3', '三个月', '1', '1', null, null);
insert into t_sys_code (CODE_TYPE, CODE_NO, CODE_NAME, EDITABLE, ENABLE_FLAG, SORT_INDEX, REMARK)
values ('483', '4', '六个月', '1', '1', null, null);
insert into t_sys_code (CODE_TYPE, CODE_NO, CODE_NAME, EDITABLE, ENABLE_FLAG, SORT_INDEX, REMARK)
values ('483', '5', '一年', '1', '1', null, null);

--2015/12/11  杨杰
--创建断面日流量表
CREATE TABLE T_TRAFFIC_DAILY_FLOW 
(
   SECTION_STATE_ID     VARCHAR2(32)         NOT NULL,
   SECTION_ID           VARCHAR2(32)         NOT NULL,
   TOTAL_NUM            NUMBER(6),
   LARGE_NUM            NUMBER(6),
   SMALL_NUM            NUMBER(6),
   OTHER_NUM            NUMBER(6),
   AVR_SPEED            NUMBER(6,2),
   VEH_TAIL_SPACE       NUMBER(6,2),
   UPDATE_TIME          DATE                 NOT NULL,
   ORG_PRIVILEGE_CODE   VARCHAR2(8)          NOT NULL,
   CONSTRAINT PK_T_TRAFFIC_DAILY_FLOW PRIMARY KEY (SECTION_STATE_ID)
);

COMMENT ON TABLE T_TRAFFIC_DAILY_FLOW IS
'断面每日流量表';

COMMENT ON COLUMN T_TRAFFIC_DAILY_FLOW.SECTION_STATE_ID IS
'断面流量ID';

COMMENT ON COLUMN T_TRAFFIC_DAILY_FLOW.SECTION_ID IS
'断面ID';

COMMENT ON COLUMN T_TRAFFIC_DAILY_FLOW.TOTAL_NUM IS
'总车数';

COMMENT ON COLUMN T_TRAFFIC_DAILY_FLOW.LARGE_NUM IS
'大车数';

COMMENT ON COLUMN T_TRAFFIC_DAILY_FLOW.SMALL_NUM IS
'小车数';

COMMENT ON COLUMN T_TRAFFIC_DAILY_FLOW.OTHER_NUM IS
'其它车数';

COMMENT ON COLUMN T_TRAFFIC_DAILY_FLOW.AVR_SPEED IS
'平均车速';

COMMENT ON COLUMN T_TRAFFIC_DAILY_FLOW.VEH_TAIL_SPACE IS
'车头时距';

COMMENT ON COLUMN T_TRAFFIC_DAILY_FLOW.UPDATE_TIME IS
'数据更新时间';

COMMENT ON COLUMN T_TRAFFIC_DAILY_FLOW.ORG_PRIVILEGE_CODE IS
'机构权限过滤代码。编码规则，例：安徽省总队编码用2位表示，34；总队下面的支队用4位编码表示，3401；大队用6位编码表示，340101；中队用8位编码表示，34010101。支队、大队、中队包括高速支队、大队等机构编码序号系统自动生成。';

--增加断面日流量表：断面流量ID自增序列。
--创建sequence
create sequence SEQ_T_FLOW_DAILY
minvalue 1
maxvalue 99999999999
start with 1
increment by 1
cache 20;

--创建trigger
create or replace trigger TRIG_T_FLOW_DAILY
  before insert on T_TRAFFIC_DAILY_FLOW
  for each row
begin
  if(:new.SECTION_STATE_ID is null or :new.SECTION_STATE_ID = '') then
  select SEQ_T_FLOW_DAILY.nextval into :new.SECTION_STATE_ID from dual;
  end if;
end TRIG_T_FLOW_DAILY;

--人工干预路况表：增加REGION_ID
alter table T_TRAFFIC_MANUAL_STATE add REGIONAL_ID VARCHAR2(32);
COMMENT ON COLUMN T_TRAFFIC_MANUAL_STATE.REGIONAL_ID is '区间ID';

--2015/12/14  杨杰（测试组已经更新）
--5分钟流量表、小时流量表、日流量表增加系统编号字段
alter table T_TRAFFIC_SECTION_FLOW add DEVICE_SYS_NBR VARCHAR2(18);
alter table T_TRAFFIC_ONE_HOUR_FLOW add DEVICE_SYS_NBR VARCHAR2(18);
alter table T_TRAFFIC_DAILY_FLOW add DEVICE_SYS_NBR VARCHAR2(18);

--点位表增加机构权限过滤代码
alter table T_SYS_SITE add ORG_PRIVILEGE_CODE VARCHAR2(8);
COMMENT ON COLUMN T_SYS_SITE.ORG_PRIVILEGE_CODE is '机构权限过滤代码';

--更新点位表机构权限代码的值
update t_sys_site t set t.org_privilege_code = (
       select o.org_privilege_code from t_sys_org o where o.org_id = t.org_id
);

--增加装备表
CREATE TABLE T_DEVICE_EQUIPMENT 
(
   EQUIPMENT_ID         VARCHAR2(32)         NOT NULL,
   EQUIPMENT_NBR        VARCHAR2(18),
   INTEGRATE_PLATFORM_NBR VARCHAR2(32),
   EQUIPMENT_TYPE       VARCHAR2(2)          NOT NULL,
   EQUIPMENT_NAME       VARCHAR2(32),
   ORG_ID               VARCHAR2(32)         NOT NULL,
   ORG_PRIVILEGE_CODE   VARCHAR2(8)          NOT NULL,
   CONTRACT_ID          VARCHAR2(32),
   CONTRACTOR_ID        VARCHAR2(32),
   DEVICE_SYS_MODEL_ID  VARCHAR2(32),
   DEVICE_BRAND         VARCHAR2(32),
   SPECIFICATION        VARCHAR2(128),
   MANUFACTURE_SN       VARCHAR2(32),
   SOFTWARE_VERSION     VARCHAR2(32),
   INSTALL_DATE         DATE,
   USE_STATUS_FLAG      CHAR(1)              NOT NULL,
   ENABLE_UPDATE_DATE   DATE,
   CREATE_BY            VARCHAR2(32),
   CREATE_TIME          DATE                 NOT NULL,
   UPDATE_BY            VARCHAR2(32),
   UPDATE_TIME          DATE,
   REMARK               VARCHAR2(32),
   CONSTRAINT PK_T_DEVICE_EQUIPMENT PRIMARY KEY (EQUIPMENT_ID)
);

COMMENT ON TABLE T_DEVICE_EQUIPMENT IS
'交警装备表';

COMMENT ON COLUMN T_DEVICE_EQUIPMENT.EQUIPMENT_ID IS
'装备ID';

COMMENT ON COLUMN T_DEVICE_EQUIPMENT.EQUIPMENT_NBR IS
'装备编号';

COMMENT ON COLUMN T_DEVICE_EQUIPMENT.INTEGRATE_PLATFORM_NBR IS
'综合平台登记编号';

COMMENT ON COLUMN T_DEVICE_EQUIPMENT.EQUIPMENT_TYPE IS
'装备类型：
1 酒检
2 执法记录仪
3 GPS';

COMMENT ON COLUMN T_DEVICE_EQUIPMENT.EQUIPMENT_NAME IS
'装备名称';

COMMENT ON COLUMN T_DEVICE_EQUIPMENT.ORG_ID IS
'机构ID';

COMMENT ON COLUMN T_DEVICE_EQUIPMENT.ORG_PRIVILEGE_CODE IS
'机构权限过滤代码。编码规则，例：安徽省总队编码用2位表示，34；总队下面的支队用4位编码表示，3401；大队用6位编码表示，340101；中队用8位编码表示，34010101。支队、大队、中队包括高速支队、大队等机构编码序号系统自动生成。';

COMMENT ON COLUMN T_DEVICE_EQUIPMENT.CONTRACT_ID IS
'合同ID';

COMMENT ON COLUMN T_DEVICE_EQUIPMENT.CONTRACTOR_ID IS
'承建商ID';

COMMENT ON COLUMN T_DEVICE_EQUIPMENT.DEVICE_SYS_MODEL_ID IS
'系统型号ID';

COMMENT ON COLUMN T_DEVICE_EQUIPMENT.DEVICE_BRAND IS
'品牌';

COMMENT ON COLUMN T_DEVICE_EQUIPMENT.SPECIFICATION IS
'规格';

COMMENT ON COLUMN T_DEVICE_EQUIPMENT.MANUFACTURE_SN IS
'出厂序列号';

COMMENT ON COLUMN T_DEVICE_EQUIPMENT.SOFTWARE_VERSION IS
'软件版本';

COMMENT ON COLUMN T_DEVICE_EQUIPMENT.INSTALL_DATE IS
'安装日期';

COMMENT ON COLUMN T_DEVICE_EQUIPMENT.USE_STATUS_FLAG IS
'使用状态标识：
1 启用
2 停用
3 报废
';

COMMENT ON COLUMN T_DEVICE_EQUIPMENT.ENABLE_UPDATE_DATE IS
'使用状态更新时间';

COMMENT ON COLUMN T_DEVICE_EQUIPMENT.CREATE_BY IS
'创建人';

COMMENT ON COLUMN T_DEVICE_EQUIPMENT.CREATE_TIME IS
'创建时间';

COMMENT ON COLUMN T_DEVICE_EQUIPMENT.UPDATE_BY IS
'更新人员';

COMMENT ON COLUMN T_DEVICE_EQUIPMENT.UPDATE_TIME IS
'更新时间';

COMMENT ON COLUMN T_DEVICE_EQUIPMENT.REMARK IS
'备注';

--图片表、检定信息表、使用状态记录表增加装备ID字段
alter table T_DEVICE_PHOTO add EQUIPMENT_ID VARCHAR2(32);
COMMENT ON COLUMN T_DEVICE_PHOTO.EQUIPMENT_ID is '装备ID';

alter table T_DEVICE_CERTIFICATION add EQUIPMENT_ID VARCHAR2(32);
COMMENT ON COLUMN T_DEVICE_CERTIFICATION.EQUIPMENT_ID is '装备ID';

alter table T_DEVICE_SYS_USE_STATUS add EQUIPMENT_ID VARCHAR2(32);
COMMENT ON COLUMN T_DEVICE_SYS_USE_STATUS.EQUIPMENT_ID is '装备ID';

--增加装备类型code_type与code值
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('484', '装备类型','EQUIPMENT_TYPE', '1', null, '1');
insert into t_sys_code (CODE_TYPE, CODE_NO, CODE_NAME, EDITABLE, ENABLE_FLAG, SORT_INDEX, REMARK)
values ('484', '1', '酒检', '1', '1', null, null);
insert into t_sys_code (CODE_TYPE, CODE_NO, CODE_NAME, EDITABLE, ENABLE_FLAG, SORT_INDEX, REMARK)
values ('484', '2', '执法记录仪', '1', '1', null, null);
insert into t_sys_code (CODE_TYPE, CODE_NO, CODE_NAME, EDITABLE, ENABLE_FLAG, SORT_INDEX, REMARK)
values ('484', '3', 'GPS', '1', '1', null, null);

--路段表增加机构权限过滤代码
alter table T_SYS_ROAD_SECTION add ORG_PRIVILEGE_CODE VARCHAR2(8);
COMMENT ON COLUMN T_SYS_ROAD_SECTION.ORG_PRIVILEGE_CODE is '机构权限过滤代码';

--道路表增加机构权限过滤代码
alter table T_SYS_ROAD add ORG_PRIVILEGE_CODE VARCHAR2(8);
COMMENT ON COLUMN T_SYS_ROAD.ORG_PRIVILEGE_CODE is '机构权限过滤代码';

--路口表增加机构权限代码字段
alter table T_SYS_CROSS add ORG_PRIVILEGE_CODE VARCHAR2(8);
COMMENT ON COLUMN T_SYS_CROSS.ORG_PRIVILEGE_CODE is '机构权限过滤代码';

--更新路段表中的机构权限过滤代码的值
update t_sys_road_section s
   set s.org_privilege_code =
       (select o.org_privilege_code
          from t_sys_org o
         where s.org_id = o.org_id);

--更新路口表中的机构权限过滤代码
update T_SYS_CROSS s
   set s.org_privilege_code =
       (select o.org_privilege_code
          from t_sys_org o
         where s.org_id = o.org_id);

--更改日流量表平均车速字段名
alter table T_TRAFFIC_DAILY_FLOW rename column AVR_SPEED to AVG_SPEED;

--更改小时流量表平均车速字段名
alter table T_TRAFFIC_ONE_HOUR_FLOW rename column AVR_SPEED to AVG_SPEED;

--更改5分钟流量表平均车速字段名
alter table T_TRAFFIC_SECTION_FLOW rename column AVR_SPEED to AVG_SPEED;

--更改区间流量表平均车速、平均旅行时间字段名
alter table T_TRAFFIC_REGION_FLOW rename column AVR_SPEED to AVG_SPEED;
alter table T_TRAFFIC_REGION_FLOW rename column AVR_TRAVEL_TIME to AVG_TRAVEL_TIME;

--删除系统代码code_type和code值
delete from t_sys_code where code_type='007';
delete from t_sys_code_type where code_type='007';

--更改道路表字段长度
alter table t_sys_road modify ORG_PRIVILEGE_CODE VARCHAR2(256);


--2015/12/15  杨杰
--阀值类型增加35：风速
insert into t_sys_code (CODE_TYPE, CODE_NO, CODE_NAME, EDITABLE, ENABLE_FLAG, SORT_INDEX, REMARK)
values ('508', '35', '风速', '1', '1', null, null);

--新建设备与装备的视图
create or replace view v_device_equipment as
select device_id,
       device_sys_model_id,
       contract_id,
       device_sys_nbr,
       device_name,
       org_id,
       device_type,
       device_brand,
       manufacture_sn,
       software_version,
       network_type,
       safe_connect_means,
       device_ip,
       device_port,
       user_name,
       password,
       sim_nbr,
       install_date,
       install_by,
       mounting_facility_type,
       enable_update_date,
       use_status_flag,
       longitude,
       latitude,
       status_type,
       status_update_time,
       sync_status,
       create_by,
       create_time,
       update_by,
       update_time,
       remark,
       power_type,
       transmission_mode,
       bandwidth,
       ownership,
       site_id,
       section_id_list,
       server_group_type_id,
       collection_type,
       start_time,
       end_time,
       road_id,
       org_privilege_code,
       contractor_id,
        '1' as record_type
  from t_device_sys
union all
select equipment_id as device_id,
       device_sys_model_id,
       contract_id,
       equipment_nbr as device_sys_nbr,
       equipment_name as device_name,
       org_id,
       equipment_type as device_type,
       device_brand,
       manufacture_sn,
       software_version,
       '' as network_type,
       '' as safe_connect_means,
       '' as device_ip,
       null as device_port,
       '' as user_name,
       '' as password,
       '' as sim_nbr,
       install_date,
       '' as install_by,
       '' as mounting_facility_type,
       enable_update_date,
       use_status_flag,
       null as longitude,
       null as latitude,
       '' as status_type,
       null as status_update_time,
       '' as sync_status,
       create_by,
       create_time,
       update_by,
       update_time,
       remark,
       '' as power_type,
       '' as transmission_mode,
       '' as bandwidth,
       '' as ownership,
       '' as site_id,
       '' as section_id_list,
       '' as server_group_type_id,
       '' as collection_type,
       null as start_time,
       null as end_time,
       '' as road_id,
       org_privilege_code,
       contractor_id,
        '0' as record_type
  from t_device_equipment;

--删除设备图片表中装备ID字段
alter table T_DEVICE_PHOTO drop (EQUIPMENT_ID);

--删除检定表装备ID字段
alter table T_DEVICE_CERTIFICATION drop (EQUIPMENT_ID);

--删除使用状态表装备ID字段
alter table T_DEVICE_SYS_USE_STATUS drop (EQUIPMENT_ID);

--删除断面5分钟流量表系统编号字段
alter table T_TRAFFIC_SECTION_FLOW drop (DEVICE_SYS_NBR);

--删除断面小时流量表系统编号字段
alter table T_TRAFFIC_ONE_HOUR_FLOW drop (DEVICE_SYS_NBR);


--2015-12-16 
--增加统计分析菜单
delete from T_SYS_MENU t where t.menu_code like '0601%';

insert into T_SYS_MENU (MENU_CODE, SYS_CONFIG_ID, MENU_NAME, TARGET_URL, PARENT_CODE, SORT_INDEX, MENU_ENABLE_FLAG, REMARK, MENU_IMAGE_URL)values ('0601', '0601', '卡口过车分析', null, '06', '1', '1', null, 'themes/default/images/a1.png');

insert into T_SYS_MENU (MENU_CODE, SYS_CONFIG_ID, MENU_NAME, TARGET_URL, PARENT_CODE, SORT_INDEX, MENU_ENABLE_FLAG, REMARK, MENU_IMAGE_URL)values ('060101', '060101', '单点卡口分析', 'tpls/statisticsAnalysis/flowAnalysis/single-site-flow-analysis.html', '0601', '1', '1', null, 'themes/default/images/a1.png');

insert into T_SYS_MENU (MENU_CODE, SYS_CONFIG_ID, MENU_NAME, TARGET_URL, PARENT_CODE, SORT_INDEX, MENU_ENABLE_FLAG, REMARK, MENU_IMAGE_URL)values ('060102', '060102', '道路卡口分析', 'tpls/statisticsAnalysis/flowAnalysis/road-flow-analysis.html', '0601', '2', '1', null, 'themes/default/images/a1.png');

insert into T_SYS_MENU (MENU_CODE, SYS_CONFIG_ID, MENU_NAME, TARGET_URL, PARENT_CODE, SORT_INDEX, MENU_ENABLE_FLAG, REMARK, MENU_IMAGE_URL)values ('060103', '060103', '辖区卡口分析', 'tpls/statisticsAnalysis/flowAnalysis/district-flow-analysis.html', '0601', '3', '1', null, 'themes/default/images/a1.png');

insert into T_SYS_MENU (MENU_CODE, SYS_CONFIG_ID, MENU_NAME, TARGET_URL, PARENT_CODE, SORT_INDEX, MENU_ENABLE_FLAG, REMARK, MENU_IMAGE_URL)values ('060104', '060104', '多点卡口分析', 'tpls/statisticsAnalysis/flowAnalysis/multi-site-flow-analysis.html', '0601', '4', '1', null, 'themes/default/images/a1.png');
--增加道路区间表
create table MAP_ROAD
(
  name        VARCHAR2(100),
  kind        VARCHAR2(30),
  popname     VARCHAR2(50),
  direction   NUMBER,
  district    VARCHAR2(10),
  type        NUMBER,
  length      NUMBER,
  lanes       NUMBER,
  formofway   NUMBER,
  updatetime  NUMBER,
  policearea  VARCHAR2(10),
  wkt         CLOB,
  id          VARCHAR2(10),
  cyid        VARCHAR2(10),
  road_code   VARCHAR2(6),
  regional_id VARCHAR2(32),
  froadname   VARCHAR2(64),
  broadname   VARCHAR2(64)
)
tablespace ITMS_DATA
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 16K
    next 8K
    minextents 1
    maxextents unlimited
  );
--添加道路区间表中数据
--执行本目录下“modify_sql附件”文件夹中的“maproad.dmp”

--添加统计分析相关表和视图
--执行本目录下“modify_sql附件”文件夹中的“创建统计表类型序列.sql”
--执行本目录下“modify_sql附件”文件夹中的“函数与视图.sql”

--2015-12-17 左鹏
--增加函数，机构id转化成机构权限编码，支持逗号分隔形式
create or replace function convert_orgids_to_prv_code(ids varchar2) return varchar2 is
  result varchar2(256);
  lv_str varchar2(256);
  item_str varchar2(32);
  org_code_str varchar2(32);
begin
  lv_str := ids || ',';
  while instr(lv_str,',')<>0 loop
     org_code_str := substr(lv_str,1,instr(lv_str,',')-1);
     lv_str:=substr(lv_str,instr(lv_str,',')+1,length(lv_str));
     dbms_output.put_line(org_code_str);  
     select o.org_privilege_code into item_str from t_sys_org o where o.org_id = org_code_str;
     dbms_output.put_line(item_str); 
     result := result || item_str || ',';
  end loop;
  return(substr(result,1,length(result)-1));
end convert_orgids_to_prv_code;
--给道路表中的机构权限编码赋值
update t_sys_road r set r.org_privilege_code = convert_orgids_to_prv_code(r.org_id);
--更新菜单图片url
update t_sys_menu m set m.menu_image_url = 'themes/default/images/clck.png' where m.menu_code = '01';
update t_sys_menu m set m.menu_image_url = 'themes/default/images/wfgl.png' where m.menu_code = '02';
update t_sys_menu m set m.menu_image_url = 'themes/default/images/sbyw.png' where m.menu_code = '03';
update t_sys_menu m set m.menu_image_url = 'themes/default/images/lkjk.png' where m.menu_code = '04';
update t_sys_menu m set m.menu_image_url = 'themes/default/images/xtgl.png' where m.menu_code = '05';
update t_sys_menu m set m.menu_image_url = 'themes/default/images/tjfx.png' where m.menu_code = '06';

--设备系统表和部件表增加device_key字段
alter table t_device_sys add  device_key VARCHAR2(64);
alter table t_device_sys_component add  device_key VARCHAR2(64);

--增加日流量和小时流量表
create table ITMS3.X_F_D_FLOW
(
  flow_id            VARCHAR2(32) not null,
  district_code      VARCHAR2(6),
  road_code          VARCHAR2(6),
  site_code          VARCHAR2(16) not null,
  device_sys_nbr     VARCHAR2(18) not null,
  direction_type     VARCHAR2(2),
  direction_name     VARCHAR2(32),
  lane               VARCHAR2(2),
  count_time         DATE not null,
  total_num          NUMBER(6),
  avr_speed          NUMBER(6,2),
  avr_length         NUMBER(6,2),
  large_num          NUMBER(6),
  middle_num         NUMBER(6),
  small_num          NUMBER(6),
  motor_num          NUMBER(6),
  supper_len_veh_num NUMBER(6,2),
  other_num          NUMBER(6),
  org_privilege_code VARCHAR2(8),
  create_time        DATE
)
tablespace ITMS_DATA
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on table ITMS3.X_F_D_FLOW
  is '五分钟流量表';
comment on column ITMS3.X_F_D_FLOW.flow_id
  is '流量ID';
comment on column ITMS3.X_F_D_FLOW.site_code
  is '点位代码';
comment on column ITMS3.X_F_D_FLOW.device_sys_nbr
  is '系统编号';
comment on column ITMS3.X_F_D_FLOW.direction_type
  is '方向类型';
comment on column ITMS3.X_F_D_FLOW.lane
  is '车道';
comment on column ITMS3.X_F_D_FLOW.count_time
  is '统计时间';
comment on column ITMS3.X_F_D_FLOW.total_num
  is '总车数';
comment on column ITMS3.X_F_D_FLOW.avr_speed
  is '平均车速';
comment on column ITMS3.X_F_D_FLOW.large_num
  is '大车数';
comment on column ITMS3.X_F_D_FLOW.middle_num
  is '中型车数';
comment on column ITMS3.X_F_D_FLOW.small_num
  is '小车数';
comment on column ITMS3.X_F_D_FLOW.motor_num
  is '摩托车数';
comment on column ITMS3.X_F_D_FLOW.supper_len_veh_num
  is '超长车数量';
comment on column ITMS3.X_F_D_FLOW.other_num
  is '其它车数';
comment on column ITMS3.X_F_D_FLOW.org_privilege_code
  is '机构权限过滤代码';
alter table ITMS3.X_F_D_FLOW
  add constraint PK_X_F_D_FLOW primary key (FLOW_ID)
  using index 
  tablespace ITMS_DATA
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

create table ITMS3.X_F_H_FLOW
(
  flow_id            VARCHAR2(32) not null,
  district_code      VARCHAR2(6),
  road_code          VARCHAR2(6),
  site_code          VARCHAR2(16) not null,
  device_sys_nbr     VARCHAR2(18) not null,
  direction_type     VARCHAR2(2),
  direction_name     VARCHAR2(32),
  lane               VARCHAR2(2),
  count_time         DATE not null,
  total_num          NUMBER(6),
  avr_speed          NUMBER(6,2),
  avr_length         NUMBER(6,2),
  large_num          NUMBER(6),
  middle_num         NUMBER(6),
  small_num          NUMBER(6),
  motor_num          NUMBER(6),
  supper_len_veh_num NUMBER(6,2),
  other_num          NUMBER(6),
  org_privilege_code VARCHAR2(8),
  create_time        DATE
)
tablespace ITMS_DATA
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on table ITMS3.X_F_H_FLOW
  is '五分钟流量表';
comment on column ITMS3.X_F_H_FLOW.flow_id
  is '流量ID';
comment on column ITMS3.X_F_H_FLOW.site_code
  is '点位代码';
comment on column ITMS3.X_F_H_FLOW.device_sys_nbr
  is '系统编号';
comment on column ITMS3.X_F_H_FLOW.direction_type
  is '方向类型';
comment on column ITMS3.X_F_H_FLOW.lane
  is '车道';
comment on column ITMS3.X_F_H_FLOW.count_time
  is '统计时间';
comment on column ITMS3.X_F_H_FLOW.total_num
  is '总车数';
comment on column ITMS3.X_F_H_FLOW.avr_speed
  is '平均车速';
comment on column ITMS3.X_F_H_FLOW.large_num
  is '大车数';
comment on column ITMS3.X_F_H_FLOW.middle_num
  is '中型车数';
comment on column ITMS3.X_F_H_FLOW.small_num
  is '小车数';
comment on column ITMS3.X_F_H_FLOW.motor_num
  is '摩托车数';
comment on column ITMS3.X_F_H_FLOW.supper_len_veh_num
  is '超长车数量';
comment on column ITMS3.X_F_H_FLOW.other_num
  is '其它车数';
comment on column ITMS3.X_F_H_FLOW.org_privilege_code
  is '机构权限过滤代码';
alter table ITMS3.X_F_H_FLOW
  add constraint PK_X_F_H_FLOW primary key (FLOW_ID)
  using index 
  tablespace ITMS_DATA
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
  
  --20151218 金海波 视频相关表修改
alter table t_traffic_plan_log modify video_plan_end_time DATE null;

alter table t_traffic_video_log modify video_plan_end_time DATE null;

update t_sys_menu set menu_name='巡航日志' ,target_url='tpls/trafficMonitor/videoMonitor/cruise-log-list.html' where sys_config_id='040404';

alter table t_traffic_video_log add place varchar2(128);
comment on column T_TRAFFIC_VIDEO_LOG.place
  is '发生地点';
  
alter table t_device_sys modify device_sys_nbr varchar2(32);

--20151222 统计报表相关
--执行本目录下“modify_sql附件”文件夹中的“t_sys_function_insert(20151222).sql”
--执行本目录下“modify_sql附件”文件夹中的“t_sys_menu_insert(20151222).sql”
--执行本目录下“modify_sql附件”文件夹中的“触发器,序列(20151222).sql”

--2015/12/28 杨杰
--清除图片表、检定信息表、使用状态记录表中数据
delete from t_device_photo; 
delete from T_DEVICE_CERTIFICATION; 
delete from T_DEVICE_SYS_USE_STATUS; 

--增加检定信息与装备表的外键关系
ALTER TABLE T_DEVICE_CERTIFICATION
   ADD CONSTRAINT FK_T_DEVICE_EQUIPMENT FOREIGN KEY (DEVICE_ID)
      REFERENCES T_DEVICE_EQUIPMENT (EQUIPMENT_ID) ON DELETE CASCADE;

--增加图片信息与装备表的外键关系
ALTER TABLE T_DEVICE_PHOTO
   ADD CONSTRAINT FK_T_DEVICE_PHOTO_EQUIPMENT FOREIGN KEY (DEVICE_ID)
      REFERENCES T_DEVICE_EQUIPMENT (EQUIPMENT_ID) ON DELETE CASCADE;

--增加使用状态记录表与装备表的外键关系
ALTER TABLE T_DEVICE_SYS_USE_STATUS
   ADD CONSTRAINT FK_T_USE_STATUS_EQUIPMENT FOREIGN KEY (DEVICE_ID)
      REFERENCES T_DEVICE_EQUIPMENT (EQUIPMENT_ID);

--修改人工上报事件表，道路ID字段不为空
ALTER TABLE T_traffic_manual_event modify ROAD_ID not null;
--修改交通管制表，机构ID，开始时间，结束时间不为空
ALTER TABLE T_TRAFFIC_CONTROL modify ORG_ID not null;
ALTER TABLE T_TRAFFIC_CONTROL modify START_TIME not null;
ALTER TABLE T_TRAFFIC_CONTROL modify END_TIME not null;
--修改人工记录恶劣天气表，气象类型不为空
ALTER TABLE T_TRAFFIC_MANUAL_WEATHER modify METEOROLOGY_TYPE not null;

--增加交通管制方向code_type与值
insert into t_sys_code_type (CODE_TYPE, CODE_TYPE_NAME, ENGLISH_NAME,EDITABLE, REGEX, ENABLE_FLAG)
values ('515', '管制方向','DIRECTION_TYPE', '1', null, '1');
insert into t_sys_code (CODE_TYPE, CODE_NO, CODE_NAME, EDITABLE, ENABLE_FLAG, SORT_INDEX, REMARK)
values ('515', '0', '双向', '1', '1', null, null);
insert into t_sys_code (CODE_TYPE, CODE_NO, CODE_NAME, EDITABLE, ENABLE_FLAG, SORT_INDEX, REMARK)
values ('515', '1', '上行', '1', '1', null, null);
insert into t_sys_code (CODE_TYPE, CODE_NO, CODE_NAME, EDITABLE, ENABLE_FLAG, SORT_INDEX, REMARK)
values ('515', '2', '下行', '1', '1', null, null);

--删除图片、检定信息、使用状态记录外键约束
ALTER TABLE t_device_photo drop CONSTRAINT FK_T_DEVICE_PHOTO_EQUIPMENT;
ALTER TABLE t_device_photo drop CONSTRAINT RELATIONSHIP_5;
ALTER TABLE T_DEVICE_CERTIFICATION drop CONSTRAINT FK_T_DEVICE_EQUIPMENT;
ALTER TABLE T_DEVICE_CERTIFICATION drop CONSTRAINT RELATIONSHIP_6;
ALTER TABLE T_DEVICE_SYS_USE_STATUS drop CONSTRAINT FK_T_USE_STATUS_EQUIPMENT;
ALTER TABLE T_DEVICE_SYS_USE_STATUS drop CONSTRAINT RELATIONSHIP_9;

--套牌车分析记录表增加过车图片1URL、过车图片2URL
ALTER TABLE T_VEHTRACK_CLONE_VEHICLE add IMAGE_URL1 VARCHAR2(128);
ALTER TABLE T_VEHTRACK_CLONE_VEHICLE add IMAGE_URL2 VARCHAR2(128);
COMMENT ON COLUMN T_VEHTRACK_CLONE_VEHICLE.IMAGE_URL1 is '过车图片1';
COMMENT ON COLUMN T_VEHTRACK_CLONE_VEHICLE.IMAGE_URL2 is '过车图片2';

--电子监控系统表DEVICE_KEY为可空
ALTER TABLE T_DEVICE_SYS modify device_key null;
ALTER TABLE T_DEVICE_SYS_COMPONENT modify device_key null;

--2015/12/29 杨杰
--系统组间表增加组间名称字段
ALTER TABLE T_DEVICE_SYS_COMPONENT add COMPONENT_NAME VARCHAR2(128);
COMMENT ON COLUMN T_DEVICE_SYS_COMPONENT.COMPONENT_NAME is '组间名称';

--t_device_video表增加access_mode(char(1))，另增加视频接入方式系统代码
alter table t_device_video add access_mode(char(1)));
comment on column t_device_video.access_mode  is '相机接入方式';
insert into T_SYS_CODE_TYPE (CODE_TYPE, CODE_TYPE_NAME, EDITABLE, REGEX, ENABLE_FLAG, ENGLISH_NAME)
values ('485', '相机接入方式', '1', null, '1', 'ACCESS_MODE');
insert into t_sys_code (CODE_TYPE, CODE_NO, CODE_NAME, EDITABLE, SORT_INDEX, REMARK, ENABLE_FLAG)
values ( '485', '1', '海康平台', '1', null, null, '1');
insert into t_sys_code (CODE_TYPE, CODE_NO, CODE_NAME, EDITABLE, SORT_INDEX, REMARK, ENABLE_FLAG)
values ( '485', '2', '海康直连设备', '1', null, null, '1');
insert into t_sys_code (CODE_TYPE, CODE_NO, CODE_NAME, EDITABLE, SORT_INDEX, REMARK, ENABLE_FLAG)
values ( '485', '3', '大华平台', '1', null, null, '1');
insert into t_sys_code (CODE_TYPE, CODE_NO, CODE_NAME, EDITABLE, SORT_INDEX, REMARK, ENABLE_FLAG)
values ( '485', '4', '大华直连设备', '1', null, null, '1');
insert into t_sys_code (CODE_TYPE, CODE_NO, CODE_NAME, EDITABLE, SORT_INDEX, REMARK, ENABLE_FLAG)
values ( '485', '5', '超远平台', '1', null, null, '1'); 

--2015/12/30 杨杰
--创建过车数据传输轨迹表
CREATE TABLE T_DEVICE_DATA_PATH 
(
   DATA_PATH_ID         VARCHAR2(32)         NOT NULL,
   DEVICE_SYS_NBR       VARCHAR2(18)         NOT NULL,
   DEVICE_NBR           VARCHAR2(18),
   SNAP_NBR             VARCHAR2(32)         NOT NULL,
   PASS_TIME            DATE                 NOT NULL,
   SERVER_RECEIVING_TIME DATE,
   ICE2MQ_TIME          DATE,
   PRE_TIME             DATE,
   AFTER_TIME           DATE,
   UPLOAD_TIME          DATE                 NOT NULL,
   UPLOAD_END_TIME      DATE                 NOT NULL,
   TOTAL_TIME           NUMBER(12，2),
   CONSTRAINT PK_T_DEVICE_DATA_PATH PRIMARY KEY (DATA_PATH_ID)
);

COMMENT ON TABLE T_DEVICE_DATA_PATH IS
'记录过车数据在传输过程中的时间轨迹';

COMMENT ON COLUMN T_DEVICE_DATA_PATH.DATA_PATH_ID IS
'记录ID';

COMMENT ON COLUMN T_DEVICE_DATA_PATH.DEVICE_SYS_NBR IS
'系统编号';

COMMENT ON COLUMN T_DEVICE_DATA_PATH.DEVICE_NBR IS
'设备编号';

COMMENT ON COLUMN T_DEVICE_DATA_PATH.SNAP_NBR IS
'抓拍编号';

COMMENT ON COLUMN T_DEVICE_DATA_PATH.PASS_TIME IS
'过车时间';

COMMENT ON COLUMN T_DEVICE_DATA_PATH.SERVER_RECEIVING_TIME IS
'监控服务器处理时间';

COMMENT ON COLUMN T_DEVICE_DATA_PATH.ICE2MQ_TIME IS
'ICE2MQ处理时间';

COMMENT ON COLUMN T_DEVICE_DATA_PATH.PRE_TIME IS
'前置机处理时间';

COMMENT ON COLUMN T_DEVICE_DATA_PATH.AFTER_TIME IS
'后置机处理时间';

COMMENT ON COLUMN T_DEVICE_DATA_PATH.UPLOAD_TIME IS
'上传开始时间';

COMMENT ON COLUMN T_DEVICE_DATA_PATH.UPLOAD_END_TIME IS
'上传结束时间';

COMMENT ON COLUMN T_DEVICE_DATA_PATH.TOTAL_TIME IS
'总耗时，单位为秒。';

--方向类型（城市）：071，code_no全部加上2
update t_sys_code set code_no=code_no+2 where code_type='071';

--系统组建表增加车道编号字段
alter table T_DEVICE_SYS_COMPONENT add LANE_NBR VARCHAR2(32);
COMMENT ON COLUMN T_DEVICE_SYS_COMPONENT.LANE_NBR is'车道编号';

--2015/12/31 杨杰
--菜单表增加数据传输轨迹值
insert into T_SYS_MENU (MENU_CODE, SYS_CONFIG_ID, MENU_NAME, TARGET_URL, PARENT_CODE, SORT_INDEX, MENU_ENABLE_FLAG, REMARK, MENU_IMAGE_URL)
values ('030602', '030602', '数据传输轨迹查看', 'tpls/deviceManage/systemMaintainManage/data-transmission-path.html', '0306', '2', '1', null, 'themes/default/images/a1.png');

--过车数据传输轨迹表增加device_key字段
alter table T_DEVICE_DATA_PATH add DEVICE_KEY VARCHAR2(64);
COMMENT ON COLUMN T_DEVICE_DATA_PATH.DEVICE_KEY is '唯一值';

--过车数据轨迹表删除系统编号字段
alter table  T_DEVICE_DATA_PATH drop (DEVICE_SYS_NBR);

--修改区间表，道路ID字段长度为32位
alter table T_DEVICE_REGION modify ROAD_ID VARCHAR2(32);

--2016/1/4 杨杰
--过车数据传输轨迹表增加电子监控系统id、组件ID、机构权限过滤代码字段
alter table T_DEVICE_DATA_PATH add DEVICE_ID VARCHAR2(32) not null;
alter table T_DEVICE_DATA_PATH add SYS_COMPONENT_ID VARCHAR2(32);
alter table T_DEVICE_DATA_PATH add ORG_PRIVILEGE_CODE VARCHAR2(8) not null;
COMMENT ON COLUMN T_DEVICE_DATA_PATH.DEVICE_ID is '电子监控系统ID';
COMMENT ON COLUMN T_DEVICE_DATA_PATH.SYS_COMPONENT_ID is '组件ID';
COMMENT ON COLUMN T_DEVICE_DATA_PATH.ORG_PRIVILEGE_CODE is '机构权限过滤代码';

--过车数据传输轨迹表删除设备编号字段
alter table T_DEVICE_DATA_PATH drop (DEVICE_NBR);

--2016/1/5 杨杰
--增加校时状态和服务器状态code以及code_type
insert into T_SYS_CODE_TYPE (CODE_TYPE, CODE_TYPE_NAME, EDITABLE, REGEX, ENABLE_FLAG, ENGLISH_NAME)
values ('486', '校时状态', '1', null, '1', 'timeResult');
insert into t_sys_code (CODE_TYPE, CODE_NO, CODE_NAME, EDITABLE, SORT_INDEX, REMARK, ENABLE_FLAG)
values ( '486', '0', '未校时', '1', null, null, '1');
insert into t_sys_code (CODE_TYPE, CODE_NO, CODE_NAME, EDITABLE, SORT_INDEX, REMARK, ENABLE_FLAG)
values ( '486', '1', '校时成功', '1', null, null, '1');
insert into t_sys_code (CODE_TYPE, CODE_NO, CODE_NAME, EDITABLE, SORT_INDEX, REMARK, ENABLE_FLAG)
values ( '486', '2', '校时失败', '1', null, null, '1');

insert into T_SYS_CODE_TYPE (CODE_TYPE, CODE_TYPE_NAME, EDITABLE, REGEX, ENABLE_FLAG, ENGLISH_NAME)
values ('487', '服务状态', '1', null, '1', 'processStatus');
insert into t_sys_code (CODE_TYPE, CODE_NO, CODE_NAME, EDITABLE, SORT_INDEX, REMARK, ENABLE_FLAG)
values ( '487', '0', '正常', '1', null, null, '1');
insert into t_sys_code (CODE_TYPE, CODE_NO, CODE_NAME, EDITABLE, SORT_INDEX, REMARK, ENABLE_FLAG)
values ( '487', '1', '离线', '1', null, null, '1');
insert into t_sys_code (CODE_TYPE, CODE_NO, CODE_NAME, EDITABLE, SORT_INDEX, REMARK, ENABLE_FLAG)
values ( '487', '2', '异常', '1', null, null, '1');

--修改道路区间表，道路ID字段为32位
alter table  T_SYS_REGION modify ROAD_ID VARCHAR2(32);

--更新相机表中device_key的值
update T_DEVICE_SYS_COMPONENT c set c.device_key=c.device_nbr where c.device_key is null;

--更新视频设备device_key的值
update t_device_sys d
   set d.device_key = d.device_sys_nbr
 where d.device_type = '03'
   and d.device_key is null;

--2016/1/6 杨杰
--修改菜单表中数据传输轨迹菜单启用状态
update T_SYS_MENU set menu_enable_flag='0' where menu_code='030601';

---修改卡口参数表，稽查布控系统编号可为空
alter table T_DEVICE_TOLLGATE_SYS modify TRACK_SYS_TOLLGATE_NBR null;

--2016/1/8 杨杰
--修改电子监控系统使用状态记录表，增加使用状态结束时间字段
alter table T_DEVICE_SYS_USE_STATUS add STATUS_END_TIME date ;
COMMENT ON COLUMN T_DEVICE_SYS_USE_STATUS.STATUS_END_TIME is '状态结束时间';

--修改过车表过车图像位置以及人脸图像位置长度
alter table T_VEHTRACK_PASS modify VEHICLE_PLATE_PLACE VARCHAR2(128);
alter table T_VEHTRACK_PASS modify FACE_PLACE VARCHAR2(128);


--2016/1/11 杨杰
--修改过车表进出城方向标记字段名称
alter table T_VEHTRACK_PASS rename column ERTRANCE_OR_EXIT to ENTRANCE_OR_EXIT;

--修改人工记录恶劣天气表：org_code->org_id
alter table T_TRAFFIC_MANUAL_WEATHER rename column ORG_CODE to ORG_ID;
COMMENT ON COLUMN T_TRAFFIC_MANUAL_WEATHER.ORG_ID is '机构ID';
alter table T_TRAFFIC_MANUAL_WEATHER modify ORG_ID VARCHAR2(32);

--修改道路事件人工记录表：org_code->org_id
alter table T_TRAFFIC_MANUAL_EVENT rename column ORG_CODE to ORG_ID;
COMMENT ON COLUMN T_TRAFFIC_MANUAL_EVENT.ORG_ID is '机构ID';
alter table T_TRAFFIC_MANUAL_EVENT modify ORG_ID VARCHAR2(32);

--交通管制表增加机构权限代码
alter table T_TRAFFIC_CONTROL add ORG_PRIVILEGE_CODE VARCHAR2(8);
COMMENT ON COLUMN T_TRAFFIC_CONTROL.ORG_PRIVILEGE_CODE is '机构权限过滤代码';

--创建视频用户组表，金海波
-- Create table
create table T_TRAFFIC_VIDEO_GROUP
(
group_id VARCHAR2(32) not null,
group_name VARCHAR2(32) not null,
create_user VARCHAR2(32) not null,
create_time DATE not null,
update_time DATE,
org_authority_code VARCHAR2(32) not null,
remark VARCHAR2(256),
group_content VARCHAR2(2048) not null
)
tablespace ITMS_DATA
pctfree 10
initrans 1
maxtrans 255;
-- Add comments to the columns 
comment on column T_TRAFFIC_VIDEO_GROUP.group_id
is '视频组id';
comment on column T_TRAFFIC_VIDEO_GROUP.group_name
is '视频级名称';
comment on column T_TRAFFIC_VIDEO_GROUP.create_user
is '创建人用户名';
comment on column T_TRAFFIC_VIDEO_GROUP.create_time
is '创建时间';
comment on column T_TRAFFIC_VIDEO_GROUP.update_time
is '更新时间';
comment on column T_TRAFFIC_VIDEO_GROUP.org_authority_code
is '机构权限代码';
comment on column T_TRAFFIC_VIDEO_GROUP.remark
is '备注';
comment on column T_TRAFFIC_VIDEO_GROUP.group_content
is '视频id集合，ID以"|"分隔';
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_TRAFFIC_VIDEO_GROUP
add constraint PK_TRAFFIC_VIDEO_GROUP primary key (GROUP_ID)
using index 
tablespace ITMS_DATA
pctfree 10
initrans 2
maxtrans 255;
  
--新增视频监控一级和二级菜单，从原有路况监控下菜单拆出
  
update t_sys_menu set sort_index=sort_index+1

insert into t_sys_menu
  (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values
  ('07', '07', '视频监控', '', 'root', 1, 1, '', 'themes/default/images/spjk.png');
  
update t_sys_menu set menu_code='0701',sys_config_id='0701',
parent_code='07' where substr(menu_code,1,4)='0404' ;
  
update t_sys_menu set menu_code='0701'||substr(menu_code,5,2),sys_config_id='0701'||substr(sys_config_id,5,2),
parent_code='0701' where substr(menu_code,1,4)='0404' ;

update t_sys_role_permission set function_code='0701'||substr(function_code,5,4)
where substr(function_code,1,4)='0404' ;

update T_SYS_FUNCTION set function_code='0701'||substr(function_code,5,4),
menu_code='0701'||substr(function_code,5,2)
where substr(menu_code,1,4)='0404' ;

--2016/1/12 杨杰
--道路区间表增加机构权限代码
alter table T_SYS_REGION add ORG_PRIVILEGE_CODE VARCHAR2(8);
COMMENT ON COLUMN T_SYS_REGION.ORG_PRIVILEGE_CODE is '机构权限过滤代码';


--2016/1/12 李洁（测试组已更新）
--修改统计中间表字段长度 
alter table X_P_H_PASS_QTR modify(PASS_NUM number(6));
alter table X_P_H_PASS_QTR modify(PLACE_LOCAL_CITY number(6));
alter table X_P_H_PASS_QTR modify(PLACE_LOCAL_PR number(6));
alter table X_P_H_PASS_QTR modify(PLACE_OTHER_PR number(6));
alter table X_P_H_PASS_QTR modify(PLACE_MILITARY number(6));
alter table X_P_H_PASS_QTR modify(PLACE_UNKNOWN number(6));

alter table X_P_H_PASS modify(NUM_FLOW number(6));
alter table X_P_H_PASS modify(PLACE_LOCAL_CITY number(6));
alter table X_P_H_PASS modify(PLACE_LOCAL_PR number(6));
alter table X_P_H_PASS modify(PLACE_OTHER_PR number(6));
alter table X_P_H_PASS modify(PLACE_MILITARY number(6));
alter table X_P_H_PASS modify(PLACE_UNKNOWN number(6));

--李洁（测试组已更新）
set define off
spool StatOnlineRate.log

prompt
prompt Creating procedure STATICDEVICEONLINERATE
prompt =========================================
prompt
CREATE OR REPLACE PROCEDURE ITMS3.StaticDeviceOnlineRate(orgCode      varchar2,
                                                   deviceType   varchar2,
                                                   contractId   varchar2,
                                                   contractorId varchar2,
                                                   timeWay      varchar2,
                                                   beginTime    varchar2,
                                                   endTime      varchar2,
                                                   myCursor     out sys_refcursor) IS
  orgLevel         integer;
  orgPrivilegeCode varchar2(32);
BEGIN
  select t.org_level, t.org_privilege_code
    into orgLevel, orgPrivilegeCode
    from t_sys_org t
   where t.org_code = orgCode;
  if (orglevel < 3) then
    --如果是总队或支队，显示下属机构的在线率
    if (timeWay = 'W' or timeWay = 'M') then --如果是周或者月，按日显示在线率
      open mycursor for
        with org_gradation as
         (select a.org_id,
                 a.org_code,
                 a.org_name,
                 a.level_hierarchy,
                 case when a.level_hierarchy >= 3 then substr(a.collapse_hierarchy, instr(a.collapse_hierarchy, '->', 1, 2) + 2,12) else a.org_code end display_org_code
            from (select a.org_id,
                         a.org_code,
                         a.org_name,
                         level level_hierarchy,
                         sys_connect_by_path(org_code, '->') collapse_hierarchy
                    from t_sys_org a
                   where substr(a.org_privilege_code, 0, length(orgPrivilegeCode)) = orgPrivilegeCode
                   start with a.org_code = orgCode
                  connect by prior a.org_id = a.parent_instruct_org_id) a
           order by a.org_code),
        show_orgname as (select t.org_code, t.org_name from org_gradation t where t.level_hierarchy in ('1', '2'))
        select *
          from (select t.display_org_code as org_code,
                       t.org_name,
                       nvl(to_char(t.statistics_date, 'yyyy-mm-dd'), '平均') as time_stamp,
                       round(avg(primary_online), 4) primary_online,
                       round(avg(correct_online), 4) correct_online
                  from (select t.device_id,
                               statistics_date,
                               primary_online,
                               correct_online,
                               v.display_org_code,
                               r.org_name
                          from (select t.device_id,
                                       t.org_id,
                                       t.statistics_date,
                                       case when t.duration_secs is null then 0 when t.duration_secs >= 86400 then 1 else t.duration_secs / 86400 end as primary_online,
                                       case when t.correct_should_secs is null then 0 when t.duration_secs >= t.correct_should_secs then 1 else t.duration_secs / t.correct_should_secs end as correct_online
                                  from x_d_d_status_log t
                                 where t.org_id in (select a.org_id from org_gradation a)
                                   and func_varchar_filter(t.contract_id,contractId) = 'true'
                                   and func_varchar_filter(t.contractor_id, contractorId) = 'true'
                                   and decode(deviceType,NULL,'true',func_funtion_filter(deviceType, t.device_type)) = 'true'
                                   and t.stat_online = '1'
                                   and t.statistics_date >= to_date(beginTime, 'yyyy-mm-dd')
                                   and t.statistics_date <=to_date(endTime, 'yyyy-mm-dd')) t,
                               org_gradation v,
                               show_orgname r
                         where t.org_id = v.org_id(+)
                           and v.display_org_code = r.org_code(+)) t
                 group by rollup(t.display_org_code,t.org_name,t.statistics_date)
                having grouping_id(t.display_org_code, t.org_name, t.statistics_date) <= 1) 
                unpivot(online_rate for data_type in(correct_online as '2', primary_online as '1'));
    else --其他 按月显示在线率
      open myCursor for
       with org_gradation as
         (select a.org_id,
                 a.org_code,
                 a.org_name,
                 a.level_hierarchy,
                 case when a.level_hierarchy >= 3 then substr(a.collapse_hierarchy, instr(a.collapse_hierarchy, '->', 1, 2) + 2,12) else a.org_code end display_org_code
            from (select a.org_id,
                         a.org_code,
                         a.org_name,
                         level level_hierarchy,
                         sys_connect_by_path(org_code, '->') collapse_hierarchy
                    from t_sys_org a
                   where substr(a.org_privilege_code, 0, length(orgPrivilegeCode)) = orgPrivilegeCode
                   start with a.org_code = orgCode
                  connect by prior a.org_id = a.parent_instruct_org_id) a
           order by a.org_code),
       show_orgname as (select t.org_code, t.org_name from org_gradation t where t.level_hierarchy in ('1', '2'))
       select * from(select t.display_org_code as org_code, 
                         t.org_name, 
                         nvl(to_char(t.statistics_month,'yyyy-mm'),'平均') as time_stamp, 
                         round(avg(primary_online),4)primary_online, 
                         round(avg(correct_online),4) correct_online from( 
        select t.device_id,statistics_month, primary_online,correct_online,v.display_org_code, r.org_name 
          from (select t.device_id, 
               t.org_id, 
               t.statistics_month, 
               t.primary_online,
               t.correct_online  
          from x_d_m_status_log t  
           where t.org_id in (select a.org_id from org_gradation a)
                                   and func_varchar_filter(t.contract_id,contractId) = 'true'
                                   and func_varchar_filter(t.contractor_id, contractorId) = 'true'
                                   and decode(deviceType,NULL,'true',func_funtion_filter(deviceType, t.device_type)) = 'true'
                                   and t.stat_online = '1'
                                   and t.statistics_month >= to_date(beginTime,'yyyy-mm-dd')  
                                   and t.statistics_month <= to_date(endTime,'yyyy-mm-dd')) t, 
       org_gradation v, show_orgname r 
 where t.org_id = v.org_id(+) 
   and v.display_org_code = r.org_code(+) 
   )t group by rollup(t.display_org_code, t.org_name, t.statistics_month) 
     having grouping_id(t.display_org_code, t.org_name, t.statistics_month)<=1) unpivot(online_rate for data_type in(correct_online as  '2',primary_online as '1'));
     end if;
  else
    --如果是大队或更低级别机构，显示管辖的设备在线率
    if (timeWay = 'W' or timeWay='M') then--如果是周或者月，按日显示在线率
      open myCursor for
       with org_gradation as
         (select a.org_id,
                 a.org_code,
                 a.org_name,
                 a.level_hierarchy,
                 case when a.level_hierarchy >= 3 then substr(a.collapse_hierarchy, instr(a.collapse_hierarchy, '->', 1, 2) + 2,12) else a.org_code end display_org_code
            from (select a.org_id,
                         a.org_code,
                         a.org_name,
                         level level_hierarchy,
                         sys_connect_by_path(org_code, '->') collapse_hierarchy
                    from t_sys_org a
                   where substr(a.org_privilege_code, 0, length(orgPrivilegeCode)) = orgPrivilegeCode
                   start with a.org_code = orgCode
                  connect by prior a.org_id = a.parent_instruct_org_id) a
           order by a.org_code)
        select * from(select t.org_code as org_code,
                       t.org_name,
                       nvl(to_char(t.time_stamp, 'yyyy-mm-dd'), '平均') as time_stamp,
                       round(avg(primary_online), 4) primary_online,
                       round(avg(correct_online), 4) correct_online
                  from(select t.device_id as org_code,v.device_name as org_name,t.statistics_date as time_stamp,t.primary_online,t.correct_online from(select t.device_id,
                                       t.org_id,
                                       t.statistics_date,
                                       case when t.duration_secs is null then 0 when t.duration_secs >= 86400 then 1 else t.duration_secs / 86400 end as primary_online,
                                       case when t.correct_should_secs is null then 0 when t.duration_secs >= t.correct_should_secs then 1 else t.duration_secs / t.correct_should_secs end as correct_online
                                  from x_d_d_status_log t
                                 where t.org_id in (select a.org_id from org_gradation a)
                                   and func_varchar_filter(t.contract_id,contractId) = 'true'
                                   and func_varchar_filter(t.contractor_id, contractorId) = 'true'
                                   and decode(deviceType,NULL,'true',func_funtion_filter(deviceType, t.device_type)) = 'true'
                                   and t.stat_online = '1'
                                   and t.statistics_date >= to_date(beginTime, 'yyyy-mm-dd')
                                   and t.statistics_date <=to_date(endTime, 'yyyy-mm-dd'))t,t_device_sys v where t.device_id=v.device_id)t
                                   group by rollup(t.org_code, t.org_name,t.time_stamp)
                                   having grouping_id(t.org_code, t.org_name, t.time_stamp) <= 1) 
                                   unpivot(online_rate for data_type in(correct_online as '2',primary_online as '1'));
    else --其他 按月显示在线率
      open myCursor for
       with org_gradation as
         (select a.org_id,
                 a.org_code,
                 a.org_name,
                 a.level_hierarchy,
                 case when a.level_hierarchy >= 3 then substr(a.collapse_hierarchy, instr(a.collapse_hierarchy, '->', 1, 2) + 2,12) else a.org_code end display_org_code
            from (select a.org_id,
                         a.org_code,
                         a.org_name,
                         level level_hierarchy,
                         sys_connect_by_path(org_code, '->') collapse_hierarchy
                    from t_sys_org a
                   where substr(a.org_privilege_code, 0, length(orgPrivilegeCode)) = orgPrivilegeCode
                   start with a.org_code = orgCode
                  connect by prior a.org_id = a.parent_instruct_org_id) a
           order by a.org_code)
        select * from(select t.org_code, 
                         t.org_name, 
                         nvl(to_char(t.time_stamp,'yyyy-mm'),'平均') as time_stamp, 
                         round(avg(primary_online),4)primary_online, 
                         round(avg(correct_online),4) correct_online from(
                         select t.device_id as org_code,v.device_name as org_name,t.statistics_month as time_stamp,t.primary_online,t.correct_online from(select t.device_id,
                                       t.org_id,
                                       t.statistics_month,
                                       t.primary_online,
                                       t.correct_online
                                  from x_d_m_status_log t
                                 where t.org_id in (select a.org_id from org_gradation a)
                                   and func_varchar_filter(t.contract_id,contractId) = 'true'
                                   and func_varchar_filter(t.contractor_id, contractorId) = 'true'
                                   and decode(deviceType,NULL,'true',func_funtion_filter(deviceType, t.device_type)) = 'true'
                                   and t.stat_online = '1'
                                   and t.statistics_month >= to_date(beginTime, 'yyyy-mm-dd')
                                   and t.statistics_month <=to_date(endTime, 'yyyy-mm-dd'))t,t_device_sys v where t.device_id=v.device_id)t
                                   group by rollup(t.org_code, t.org_name, t.time_stamp) 
                                   having grouping_id(t.org_code, t.org_name, t.time_stamp)<=1)
                                   unpivot(online_rate for data_type in(correct_online as '2',primary_online as '1'));
    END if;
  end if;
END;
/


spool off


--归属地增加0：未知
insert into t_sys_code (CODE_TYPE, CODE_NO, CODE_NAME, EDITABLE, SORT_INDEX, REMARK, ENABLE_FLAG)
values ( '228', '0', '未知', '1', null, null, '1');

--五分钟流量表方向类型不为空
delete from T_FLOW_FIVE_MIN where direction_type is null;
alter table T_FLOW_FIVE_MIN modify direction_type not null;

--修改设备状态日志信息表：
delete from T_DEVICE_STATUS_LOG where device_id is null or start_time is null or status_type is null;
alter table T_DEVICE_STATUS_LOG modify device_id not null;
alter table T_DEVICE_STATUS_LOG modify start_time not null;
alter table T_DEVICE_STATUS_LOG modify status_type not null;


--2016/1/14 杨杰
--交通管制表增加实际结束时间
alter table T_TRAFFIC_CONTROL ADD REAL_END_TIME DATE;
COMMENT ON COLUMN T_TRAFFIC_CONTROL.REAL_END_TIME is '实际结束时间';

--修改违法表违法地点数据类型
update T_VIO_VIOLATION set MILEAGE=null;
alter table T_VIO_VIOLATION modify MILEAGE VARCHAR2(32);


--2016/1/18 杨杰
--套牌车分析记录表、假牌车分析记录表增加号牌类型字段。
alter table T_VEHTRACK_CLONE_VEHICLE add PLATE_TYPE VARCHAR2(2);
COMMENT ON COLUMN T_VEHTRACK_CLONE_VEHICLE.PLATE_TYPE is '号牌类型';

alter table T_VEHTRACK_FAKE_VEHICLE add PLATE_TYPE VARCHAR2(2);
COMMENT ON COLUMN T_VEHTRACK_FAKE_VEHICLE.PLATE_TYPE is '号牌类型';

--修改视频参数表
alter table t_device_video add PREVIEW_PARAM varchar2(2048);
comment on column t_device_video.PREVIEW_PARAM
is '视频预览参数，主要用于海康视频';
alter table t_device_video add PLAYBACK_PARAM varchar2(2048);
comment on column t_device_video.PLAYBACK_PARAM
is '视频回放参数，主要用于海康视频';

--2016/1/19  金海波
--修改视频参数表
alter table t_device_video add CAMERA_SN varchar2(32);
comment on column t_device_video.CAMERA_SN  is '视频对接时的编号';
  
  --2016/3/2 李洁
  --增加流量统计分析报表菜单
  insert into T_SYS_MENU (MENU_CODE, SYS_CONFIG_ID, MENU_NAME, TARGET_URL, PARENT_CODE, SORT_INDEX, MENU_ENABLE_FLAG, REMARK, MENU_IMAGE_URL)
values ('060105', '060105', '节假日流量分析', 'tpls/statisticsAnalysis/flowAnalysis/holidayflowAnalysis.html', '0601', '6', '1', null, 'themes/default/images/a1.png');

insert into T_SYS_FUNCTION (FUNCTION_CODE, MENU_CODE, FUNCTION_NAME, FUNCTION_FLAG, FUNCTION_DEPENDENCY, SERVICE_ID)
values ('06010501', '060105', '节假日流量分析', '1', null, null); 

