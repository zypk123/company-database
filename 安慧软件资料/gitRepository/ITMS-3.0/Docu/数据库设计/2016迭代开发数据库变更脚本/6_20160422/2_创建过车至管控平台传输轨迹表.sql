set define off
spool 6_2_创建过车至管控平台传输轨迹表.log

-- Create table
create table T_DEVICE_DATA_PATH_ITMS
(
  snap_nbr                    VARCHAR2(32) not null,
  pass_time                   DATE not null,
  server_receiving_time       DATE,
  after_server_receiving_time DATE,
  ice2mq_time                 DATE,
  pre_time                    DATE,
  after_time                  DATE,
  itms_time                   DATE not null,
  total_time                  NUMBER(12,2),
  device_key                  VARCHAR2(64),
  sys_component_id            VARCHAR2(32),
  device_id                   VARCHAR2(32) not null,
  org_privilege_code          VARCHAR2(8) not null,
  device_sys_nbr              VARCHAR2(32),
  site_code                   VARCHAR2(16),
  road_code                   VARCHAR2(6)
);
-- Add comments to the table 
comment on table T_DEVICE_DATA_PATH_ITMS
  is '记录过车数据不经过稽查布控系统直接到管控平台传输轨迹';
-- Add comments to the columns 
comment on column T_DEVICE_DATA_PATH_ITMS.snap_nbr
  is '抓拍编号';
comment on column T_DEVICE_DATA_PATH_ITMS.pass_time
  is '过车时间';
comment on column T_DEVICE_DATA_PATH_ITMS.server_receiving_time
  is '监控服务器处理时间';  
comment on column T_DEVICE_DATA_PATH_ITMS.after_server_receiving_time
  is '后置监控服务器处理时间';
comment on column T_DEVICE_DATA_PATH_ITMS.ice2mq_time
  is 'ICE2MQ处理时间';
comment on column T_DEVICE_DATA_PATH_ITMS.pre_time
  is '前置机处理时间';
comment on column T_DEVICE_DATA_PATH_ITMS.after_time
  is '后置机处理时间';
comment on column T_DEVICE_DATA_PATH_ITMS.itms_time
  is '管控平台接收时间';
comment on column T_DEVICE_DATA_PATH_ITMS.total_time
  is '总耗时，单位为秒。';
comment on column T_DEVICE_DATA_PATH_ITMS.device_key
  is '唯一值';
comment on column T_DEVICE_DATA_PATH_ITMS.sys_component_id
  is '组件ID';
comment on column T_DEVICE_DATA_PATH_ITMS.device_id
  is '电子监控系统ID';
comment on column T_DEVICE_DATA_PATH_ITMS.org_privilege_code
  is '机构权限过滤代码';
comment on column T_DEVICE_DATA_PATH_ITMS.device_sys_nbr
  is '电子监控系统编号';
comment on column T_DEVICE_DATA_PATH_ITMS.site_code
  is '点位代码';
comment on column T_DEVICE_DATA_PATH_ITMS.road_code
  is '道路代码';
-- Create/Recreate indexes 
create index IDX_T_DATA_PATH_ITMS on T_DEVICE_DATA_PATH_ITMS (PASS_TIME, ORG_PRIVILEGE_CODE, DEVICE_SYS_NBR, SITE_CODE, ROAD_CODE);
  
spool off

exit;