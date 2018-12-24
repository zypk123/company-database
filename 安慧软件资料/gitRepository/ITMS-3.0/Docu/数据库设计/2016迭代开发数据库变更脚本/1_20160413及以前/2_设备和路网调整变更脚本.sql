set define off
spool 2_设备和路网调整变更脚本.log

--删除道路路口关系表[T_SYS_ROAD_CROSS]
DROP TABLE T_SYS_ROAD_CROSS;

--修改路口表[T_SYS_CROSS]
--增加字段 道路ID；
alter table T_SYS_CROSS add ROAD_ID varchar2(32) ;
comment on column T_SYS_CROSS.ROAD_ID is '所属道路ID';

--增加外键FK_CROSS_ROAD，道路ID作为外键列，指向道路信息表[T_SYS_ROAD]主键;
alter table T_SYS_CROSS add constraint FK_CROSS_ROAD foreign key (ROAD_ID)
  references T_SYS_ROAD(ROAD_ID) on delete cascade;

--删除列 路口相交道路[CROSS_ROAD_ID] 路口所属主干路[MAIN_ROAD_ID]
alter table T_SYS_CROSS drop column cross_road_id;
alter table T_SYS_CROSS drop column main_road_id;

--道路表 增加道路别名列
alter table T_SYS_ROAD add road_alias_name VARCHAR2(128);
comment on column T_SYS_ROAD.road_alias_name is '道路别名';

--修改路段表
--取消路段代码的唯一约束   20160315下午 杨杰反馈现场问题
alter table T_SYS_ROAD_SECTION
  drop constraint ROAD_SECTION_CODE_UNIQUE cascade;


--修改道路区间配置表[T_SYS_REGION]
--修改表名
comment on table T_SYS_REGION is '流量区间配置表';

--删除列 是否支持区间测速[HAS_SUPPORT_SPEED] 是否支持区间流量监控[HAS_SUPPORT_VEHICLE_FLOW]  
--大车限速容许值[LIMIT_LARGE_MARGIN]   小车限速容许值[LIMIT_SMALL_MARGIN]  关联可变限速牌编号列表[RELATED_VARIABLE_SPEED]  其它车限高速[LIMIT_LOWER];
alter table T_SYS_REGION drop column has_support_speed;
alter table T_SYS_REGION drop column has_support_vehicle_flow;
alter table T_SYS_REGION drop column limit_large_margin;
alter table T_SYS_REGION drop column limit_small_margin;
alter table T_SYS_REGION drop column related_variable_speed;
alter table T_SYS_REGION drop column limit_others;
alter table T_SYS_REGION modify org_privilege_code VARCHAR2(8);
--杨杰 提出 20160317
alter table T_SYS_REGION modify lane_num null;

--点位表、断面表和车道限速表移至设备运维模块；


--修改点位信息表[T_SYS_SITE]
--增加点位别名字段
alter table T_SYS_SITE add site_alias_name VARCHAR2(128);
comment on column T_SYS_SITE.site_alias_name is '点位别名';

--增加公里数字段
alter table T_SYS_SITE add kilomileage number;
comment on column T_SYS_SITE.kilomileage is '公里数';


--修改执行路口表的外键的级联操作：点位指向的路口表记录被删除时，点位保留，其路口ID设定为空；
alter table T_SYS_SITE drop constraint FK_T_SYS_SI_REFERENCE_T_SYS_CR;
alter table T_SYS_SITE add constraint FK_T_SYS_SI_REFERENCE_T_SYS_CR foreign key (CROSS_ID) references T_SYS_CROSS (CROSS_ID) on delete set null;

--修改指向路段表的外键的级联操作：点位指向的路段表记录被删除时，点位保留，其路段ID设定为空；

alter table T_SYS_SITE drop constraint FK_T_SYS_SI_REFERENCE_T_SYS_RO;
alter table T_SYS_SITE add constraint FK_T_SYS_SI_REFERENCE_T_SYS_RO foreign key (ROAD_SECTION_ID) references T_SYS_ROAD_SECTION (ROAD_SECTION_ID) on delete set null;

--增加指向道路表的外键
alter table T_SYS_SITE add constraint FK_T_SITE_ROAD foreign key (ROAD_ID) references t_sys_road (ROAD_ID) on delete set null;

--修改电子监控系统表[T_DEVICE_SYS]
--增加 网络体系结构 列
alter table T_DEVICE_SYS add architecture char(1) default 1 not null;
comment on column T_DEVICE_SYS.architecture is '网络体系结构   1 主从式； 2 分布式；';

--增加到设备承建商表的外键约束
alter table T_DEVICE_SYS add constraint FK_DEVSYS_CONTRACTOR foreign key (CONTRACTOR_ID) references t_device_contractor (CONTRACTOR_ID) on delete set null;

--删除列 唯一值[DEVICE_KEY] 服务器类型组ID[SERVER_GROUP_TYPE_ID] 道路ID[ROAD_ID] 设备状态开始时间[START_TIME] 设备状态结束时间[END_TIME]
alter table T_DEVICE_SYS drop column server_group_type_id;
alter table T_DEVICE_SYS drop column road_id;
alter table T_DEVICE_SYS drop column device_key;
alter table T_DEVICE_SYS drop column start_time;
alter table T_DEVICE_SYS drop column end_time;

--增加列
alter table T_DEVICE_SYS add server_plat_id VARCHAR2(32);

--修改系统组件表[T_DEVICE_SYS_COMPONENT]
--删除列 监控车道ID[DRIVE_LANE_ID]
alter table T_DEVICE_SYS_COMPONENT drop column drive_lane_id;

--修改车道编号列名为车道编号列表
comment on column T_DEVICE_SYS_COMPONENT.lane_nbr is '车道编号列表';

--修改设备编号列名为组件编号
comment on column T_DEVICE_SYS_COMPONENT.device_nbr  is '组件编号 相机等组件上配置的业务编号';

--修改组件类型[SYS_COMPONENT_TYPE]列注释
comment on column T_DEVICE_SYS_COMPONENT.sys_component_type is '组件类型:	';

--修改系统代码表,取消原部件类型定义,如下:
--438	系统组件（部件）类型	1	视频采集
--438	系统组件（部件）类型	2	抓拍相机
--438	系统组件（部件）类型	3	测速
--438	系统组件（部件）类型	4	补光
--438	系统组件（部件）类型	5	数据处理
--438	系统组件（部件）类型	6	网络传输
--438	系统组件（部件）类型	7	存储



--修改唯一值[DEVICE_KEY]列名为相机唯一序号
comment on column T_DEVICE_SYS_COMPONENT.device_key is '相机唯一序号';

--添加是否支持视频字段
alter table T_DEVICE_SYS_COMPONENT add is_video_support CHAR(1);
comment on column T_DEVICE_SYS_COMPONENT.is_video_support is '是否支持视频  1 支持 0 不支持';

--添加视频平台备案编号列[CAMERA_SN]

alter table T_DEVICE_SYS_COMPONENT add camera_sn VARCHAR2(32);
comment on column T_DEVICE_SYS_COMPONENT.camera_sn is '视频平台备案编号 支持视频输出的相机在视频平台中的编号';

--添加接入方式列[ACCESS_MODE]
alter table T_DEVICE_SYS_COMPONENT add access_mode CHAR(1);
comment on column T_DEVICE_SYS_COMPONENT.access_mode  is '接入方式 工控机接入;终端盒接入:终端盒会对其数据进行处理(如改变设备编号)后再转发，但不处理图片，图片上叠加编号仍为相机上配置的编号;终端盒转发:直接转发;监控服务器直接接入;';

--增加视频接入方式列[VIDEO_ACCESS_MODE]
alter table T_DEVICE_SYS_COMPONENT add video_access_mode CHAR(1);
comment on column T_DEVICE_SYS_COMPONENT.video_access_mode is '视频接入方式  定义见系统代码类型:485';
alter table T_DEVICE_SYS_COMPONENT add preview_param VARCHAR2(2048);
alter table T_DEVICE_SYS_COMPONENT add playback_param VARCHAR2(2048);
comment on column T_DEVICE_SYS_COMPONENT.preview_param is '视频预览参数，主要用于海康视频';
comment on column T_DEVICE_SYS_COMPONENT.playback_param is '视频回放参数，主要用于海康视频';

--删除部件 设备编号 唯一约束
alter table T_DEVICE_SYS_COMPONENT drop unique(DEVICE_NBR);
--修正部件 外键约束 名字  RELATIONSHIP_1=>FK_COMPONENT_DEVICESYS
alter table T_DEVICE_SYS_COMPONENT drop constraint RELATIONSHIP_1;
alter table T_DEVICE_SYS_COMPONENT add constraint FK_COMPONENT_DEVICESYS foreign key (DEVICE_ID) references T_DEVICE_SYS (DEVICE_ID) on delete cascade;


--修改卡口系统参数表[T_DEVICE_TOLLGATE_SYS]
--添加是否支持违法取证列
alter table T_DEVICE_TOLLGATE_SYS add is_vio_support CHAR(1);
comment on column T_DEVICE_TOLLGATE_SYS.is_vio_support is '是否支持违法取证  1 支持 0 不支持';

--添加综合平台设备登记编号列
alter table T_DEVICE_TOLLGATE_SYS add integrate_platform_nbr VARCHAR2(32); 
comment on column T_DEVICE_TOLLGATE_SYS.integrate_platform_nbr is '综合平台设备登记编号';

--修改视频监控系统参数表[T_DEVICE_VIDEO]
--修改相机接入方式列名为视频接入方式
alter table T_DEVICE_VIDEO add video_access_mode CHAR(1);
comment on column T_DEVICE_VIDEO.video_access_mode is '视频接入方式  定义见系统代码类型:485';
alter table T_DEVICE_VIDEO rename column camera_param to CAMERA_SN;
alter table T_DEVICE_VIDEO modify camera_sn VARCHAR2(32);
comment on column T_DEVICE_VIDEO.camera_sn
  is '视频平台备案编号 支持视频输出的相机在视频平台中的编号';
  

--修改气象监测系统参数[T_DEVICE_METEOROLOGIC]
--添加是否支持气象采集列

alter table T_DEVICE_METEOROLOGIC add is_weather_support CHAR(1);
comment on column T_DEVICE_METEOROLOGIC.is_weather_support is '是否支持气象采集     1 支持； 0 不支持';

--添加是否支持能见度列
alter table T_DEVICE_METEOROLOGIC add is_visibility_support CHAR(1);
comment on column T_DEVICE_METEOROLOGIC.is_visibility_support is '是否支持能见度    1 支持； 0 不支持';

--添加是否支持路感列
alter table T_DEVICE_METEOROLOGIC add is_roadsensor_support CHAR(1);
comment on column T_DEVICE_METEOROLOGIC.is_roadsensor_support is '是否支持路感    1 支持； 0 不支持';

--添加关联可变限速牌编号列表
alter table T_DEVICE_METEOROLOGIC add related_variable_speed VARCHAR2(256);
comment on column T_DEVICE_METEOROLOGIC.related_variable_speed is '关联可变限速牌编号列表';

--修改违法取证系统表[T_DEVICE_VIO_DEVICE]
--添加是否支持卡口过车列
alter table T_DEVICE_VIO_DEVICE add is_tollgate_support CHAR(1);
comment on column T_DEVICE_VIO_DEVICE.is_tollgate_support is '是否支持卡口过车 1 支持 ；0  不支持';

--添加是否支持稽查布控系统上传列 
alter table T_DEVICE_VIO_DEVICE add is_connect_track_sys CHAR(1); 
comment on column T_DEVICE_VIO_DEVICE.is_connect_track_sys is '是否支持稽查布控系统 上传当支持卡口过车时，本属性有意义；1 支持 ；0  不支持';


--添加稽查布控系统备案编号配置字典列 
alter table T_DEVICE_VIO_DEVICE add track_sys_nbr_map VARCHAR2(256);
comment on column T_DEVICE_VIO_DEVICE.track_sys_nbr_map is '稽查布控系统备案编号配置字典 当违法取证系统支持过车监测数据并需要上传稽查布控系统时,即支持稽查布控系统上传时,需配置本属性,此时对应的电子监控系统类型为电警,由于电警系统在稽查布控系统备案时，无锡所推荐按道路来，如电警系统在十字路口时，推荐备案为两套系统，即每条路的两个方向上的监控设备（无论多少），在稽查布控系统中备案为一套系统，一条路对应一个备案编号；因此本字段存储电警系统所在路口点位涉及的道路编码及备案编号；内容格式为：道路1编码1:备案编号1;道路2编码:备案编号2;...... 注:当支持卡口过车时，且支持稽查布控系统上传时，本属性有意义；';

--修改区间系统配置表[T_DEVICE_REGION]
--删除其它车限高速[LIMIT_OTHERS]列
alter table T_DEVICE_REGION drop column limit_others;

--删除服务器类型组表[T_DEVICE_SERVER_TYPE_GROUP]
DROP TABLE T_DEVICE_SERVER_TYPE_GROUP;
--删除服务器类型组与服务器类型关系表表[T_DEVICE_ASSO_GROUP]
DROP TABLE T_DEVICE_ASSO_GROUP;
--删除监控系统与服务器类型关系表[T_DEVICE_ASSO_SERVER_TYPE]
DROP TABLE T_DEVICE_ASSO_SERVER_TYPE;
--删除服务器类型表[T_DEVICE_SERVER_TYPE]
DROP TABLE T_DEVICE_SERVER_TYPE;

--新增服务器组件表[T_DEVICE_SERVER_APPLICATION]
CREATE TABLE T_DEVICE_SERVER_APPLICATION 
(
   SERVER_APP_ID        VARCHAR2(32)         NOT NULL,
   SERVER_ID            VARCHAR2(32),
   APP_TYPE             VARCHAR(32)          NOT NULL,
   SERVER_PORT          VARCHAR2(128),
   CONTEX               VARCHAR2(128),
   SUFFIX               VARCHAR2(8),
   REMARK               VARCHAR2(32),
   CONSTRAINT PK_T_DEVICE_SERVER_APPLICATION PRIMARY KEY (SERVER_APP_ID)
);

COMMENT ON TABLE T_DEVICE_SERVER_APPLICATION IS
'存放服务器用途信息，如通信服务、流媒体服务、图像存储服务等。';
      
alter table T_DEVICE_SERVER_APPLICATION
  add constraint FK_SERVERAPP_SERVER foreign key (SERVER_ID)
  references T_DEVICE_SERVER (SERVER_ID) on delete cascade;

--新增接入平台表[T_SERVER_ACCESS_PLAT]
CREATE TABLE T_DEVICE_SERVER_PLAT
(
   SERVER_PLAT_ID       VARCHAR2(32)         NOT NULL,
   SERVER_PLAT_NAME     VARCHAR2(128)        NOT NULL,
--   SERVER_PLAT_TYPE     VARCHAR2(16)         NOT NULL,
   REMARK               VARCHAR2(256),
   SURVEY_SYSTEM_ID     VARCHAR2(32),
   CONSTRAINT PK_T_SERVER_ACCESS_PLAT PRIMARY KEY (SERVER_PLAT_ID)
);

alter table T_DEVICE_SERVER_PLAT
  add constraint FK_T_SERVER_PLAT_TO_SURVEY_SYS foreign key (SURVEY_SYSTEM_ID)
  references t_device_survey_sys (SURVEY_SYSTEM_ID) on delete cascade;
  
COMMENT ON TABLE T_DEVICE_SERVER_PLAT IS '接入平台';


alter table T_DEVICE_SYS
  add constraint FK_DEVSYS_PLAT foreign key (SERVER_PLAT_ID)
  references t_device_server_plat (SERVER_PLAT_ID) on delete set null;

--新增接入平台与服务器组件关系表[T_SERVER_PLAT_ASSO_APPLICATION]
CREATE TABLE T_DEVICE_SERVER_APP_SSO_PLAT
(
   SERVER_APP_ID        VARCHAR2(32),
   SERVER_PLAT_ID       VARCHAR2(32)
);

alter table T_DEVICE_SERVER_APP_SSO_PLAT
  add constraint FK_PLATSSOAPP_TO_APP foreign key (SERVER_APP_ID)
  references T_DEVICE_SERVER_APPLICATION (SERVER_APP_ID) on delete cascade;
  
alter table T_DEVICE_SERVER_APP_SSO_PLAT
  add constraint FK_PLATSSOAPP_TO_PLAT foreign key (SERVER_PLAT_ID)
  references T_DEVICE_SERVER_PLAT (SERVER_PLAT_ID) on delete cascade;
 

--新增电子监控系统与接入平台关系表[T_DEVICE_ASSO_SERVER_PLAT]
--CREATE TABLE T_DEVICE_ASSO_SERVER_PLAT 
--(
--   DEVICE_ID            VARCHAR2(32),
--   SERVER_PLAT_ID       VARCHAR2(32)
--);

--COMMENT ON TABLE T_DEVICE_ASSO_SERVER_PLAT IS '监控系统与接入平台类型关系表';

--ALTER TABLE T_DEVICE_ASSO_SERVER_PLAT
--   ADD CONSTRAINT FK_SYSSSOPLAT_TO_PLAT FOREIGN KEY (SERVER_PLAT_ID)
--      REFERENCES T_DEVICE_SERVER_PLAT (SERVER_PLAT_ID) on delete cascade;

--ALTER TABLE T_DEVICE_ASSO_SERVER_PLAT
--   ADD CONSTRAINT FK_SYSSSOPLAT_TO_SYS FOREIGN KEY (DEVICE_ID)
--      REFERENCES T_DEVICE_SYS (DEVICE_ID) on delete cascade;


-- Add/modify columns 
-- Create/Recreate primary, unique and foreign key constraints 


spool off

exit;