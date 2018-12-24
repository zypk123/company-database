------------------------------------------------------
-- Export file for user ITMS3                       --
-- Created by Administrator on 2015-12-16, 17:13:14 --
------------------------------------------------------

set define off
spool 创建统计表类型序列.log

prompt
prompt Creating table X_D_D_DEVICE_STATUS
prompt ==================================
prompt
create table ITMS3.X_D_D_DEVICE_STATUS
(
  device_id     VARCHAR2(32) not null,
  org_id        VARCHAR2(32) not null,
  device_type   VARCHAR2(2) not null,
  contract_id   VARCHAR2(32),
  contractor_id VARCHAR2(32),
  begin_time    DATE,
  end_time      DATE,
  status        VARCHAR2(2),
  stat_online   VARCHAR2(2)
)
tablespace ITMS_DATA
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 8K
    minextents 1
    maxextents unlimited
  );
comment on table ITMS3.X_D_D_DEVICE_STATUS
  is '设备每日运行状态统计表';
comment on column ITMS3.X_D_D_DEVICE_STATUS.device_id
  is '设备ID';
comment on column ITMS3.X_D_D_DEVICE_STATUS.org_id
  is '机构ID';
comment on column ITMS3.X_D_D_DEVICE_STATUS.device_type
  is '设备类型';
comment on column ITMS3.X_D_D_DEVICE_STATUS.contract_id
  is '合同ID';
comment on column ITMS3.X_D_D_DEVICE_STATUS.contractor_id
  is '厂商ID';
comment on column ITMS3.X_D_D_DEVICE_STATUS.begin_time
  is '开始时间';
comment on column ITMS3.X_D_D_DEVICE_STATUS.end_time
  is '结束时间';
comment on column ITMS3.X_D_D_DEVICE_STATUS.status
  is '设备状态 (未启用、启用 、停用 、报废)';
comment on column ITMS3.X_D_D_DEVICE_STATUS.stat_online
  is '是否统计在线率（标识）';

prompt
prompt Creating table X_D_D_STATUS_LOG
prompt ===============================
prompt
create table ITMS3.X_D_D_STATUS_LOG
(
  status_log_id       VARCHAR2(32) not null,
  device_id           VARCHAR2(32),
  org_id              VARCHAR2(32),
  device_type         VARCHAR2(2) not null,
  contract_id         VARCHAR2(32),
  contractor_id       VARCHAR2(32),
  statistics_date     DATE,
  online_status       VARCHAR2(2),
  duration_secs       NUMBER(10),
  fault_secs          NUMBER(10),
  fault_times         NUMBER(10),
  correct_should_secs NUMBER(10),
  stat_online         VARCHAR2(2)
)
tablespace ITMS_DATA
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 8K
    minextents 1
    maxextents unlimited
  );
comment on table ITMS3.X_D_D_STATUS_LOG
  is '设备日在线率统计表';
comment on column ITMS3.X_D_D_STATUS_LOG.status_log_id
  is '设备记录ID';
comment on column ITMS3.X_D_D_STATUS_LOG.device_id
  is '设备ID';
comment on column ITMS3.X_D_D_STATUS_LOG.org_id
  is '机构ID';
comment on column ITMS3.X_D_D_STATUS_LOG.device_type
  is '设备类型';
comment on column ITMS3.X_D_D_STATUS_LOG.contract_id
  is '合同编号';
comment on column ITMS3.X_D_D_STATUS_LOG.contractor_id
  is '厂商ID';
comment on column ITMS3.X_D_D_STATUS_LOG.statistics_date
  is '统计日期';
comment on column ITMS3.X_D_D_STATUS_LOG.online_status
  is '在线状态【1 正常在线 2 有故障（全天故障或部分时段故障）3 应在线 却没有在线记录】';
comment on column ITMS3.X_D_D_STATUS_LOG.duration_secs
  is '在线持续时长';
comment on column ITMS3.X_D_D_STATUS_LOG.fault_secs
  is '故障持续时长';
comment on column ITMS3.X_D_D_STATUS_LOG.fault_times
  is '故障发生次数';
comment on column ITMS3.X_D_D_STATUS_LOG.correct_should_secs
  is '应在线时长（一日时长扣除故障时长）';
comment on column ITMS3.X_D_D_STATUS_LOG.stat_online
  is '是否统计在线率 因为设备故障时长也要记录，设备时长是针对全部启用设备的';

prompt
prompt Creating table X_D_D_UNUSUAL_TIME
prompt =================================
prompt
create table ITMS3.X_D_D_UNUSUAL_TIME
(
  log_id        VARCHAR2(32) not null,
  device_id     VARCHAR2(32),
  start_time    DATE,
  end_time      DATE,
  duration_secs NUMBER(10)
)
tablespace ITMS_DATA
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 8K
    minextents 1
    maxextents unlimited
  );
comment on table ITMS3.X_D_D_UNUSUAL_TIME
  is '设备故障或离线情况小时表';
comment on column ITMS3.X_D_D_UNUSUAL_TIME.device_id
  is '设备ID';
comment on column ITMS3.X_D_D_UNUSUAL_TIME.start_time
  is '开始时间';
comment on column ITMS3.X_D_D_UNUSUAL_TIME.end_time
  is '结束时间';
comment on column ITMS3.X_D_D_UNUSUAL_TIME.duration_secs
  is '故障、离线持续时长（单位：秒）';

prompt
prompt Creating table X_D_M_STATUS_LOG
prompt ===============================
prompt
create table ITMS3.X_D_M_STATUS_LOG
(
  status_log_id    VARCHAR2(32) not null,
  device_id        VARCHAR2(32),
  org_id           VARCHAR2(32),
  device_type      VARCHAR2(2) not null,
  contract_id      VARCHAR2(32),
  contractor_id    VARCHAR2(32),
  statistics_month DATE,
  primary_online   NUMBER(5,4),
  correct_online   NUMBER(5,4),
  stat_online      VARCHAR2(2)
)
tablespace ITMS_DATA
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 8K
    minextents 1
    maxextents unlimited
  );
comment on column ITMS3.X_D_M_STATUS_LOG.device_id
  is '设备ID';
comment on column ITMS3.X_D_M_STATUS_LOG.org_id
  is '机构ID';
comment on column ITMS3.X_D_M_STATUS_LOG.device_type
  is '设备类型';
comment on column ITMS3.X_D_M_STATUS_LOG.contract_id
  is '合同ID';
comment on column ITMS3.X_D_M_STATUS_LOG.contractor_id
  is '厂商ID';
comment on column ITMS3.X_D_M_STATUS_LOG.statistics_month
  is '统计月份';
comment on column ITMS3.X_D_M_STATUS_LOG.primary_online
  is '原始在线率';
comment on column ITMS3.X_D_M_STATUS_LOG.correct_online
  is '修正在线率';
comment on column ITMS3.X_D_M_STATUS_LOG.stat_online
  is '是否统计在线率';

prompt
prompt Creating table X_D_M_UNUSUAL_TIME
prompt =================================
prompt
create table ITMS3.X_D_M_UNUSUAL_TIME
(
  log_id        VARCHAR2(32) not null,
  device_id     VARCHAR2(32),
  start_time    DATE,
  end_time      DATE,
  duration_secs NUMBER(10)
)
tablespace ITMS_DATA
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 8K
    minextents 1
    maxextents unlimited
  );
comment on table ITMS3.X_D_M_UNUSUAL_TIME
  is '设备故障或离线情况日表';
comment on column ITMS3.X_D_M_UNUSUAL_TIME.device_id
  is '设备ID';
comment on column ITMS3.X_D_M_UNUSUAL_TIME.start_time
  is '开始时间';
comment on column ITMS3.X_D_M_UNUSUAL_TIME.end_time
  is '结束时间';
comment on column ITMS3.X_D_M_UNUSUAL_TIME.duration_secs
  is '离线或故障持续时长';

prompt
prompt Creating table X_P_H_PASS
prompt =========================
prompt
create table ITMS3.X_P_H_PASS
(
  vehicle_seq_id     NUMBER(10),
  org_code           VARCHAR2(32),
  org_authority_code VARCHAR2(32),
  district_code      VARCHAR2(6),
  road_code          VARCHAR2(6),
  site_code          VARCHAR2(16),
  direction_type     VARCHAR2(16),
  pass_time          DATE not null,
  vehicle_type       VARCHAR2(4),
  num_flow           NUMBER(5) not null,
  place_local_city   NUMBER(5),
  place_local_pr     NUMBER(5),
  place_other_pr     NUMBER(5),
  place_military     NUMBER(5),
  place_unknown      NUMBER(5),
  avg_speed          NUMBER(6,2),
  create_date        DATE
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
comment on table ITMS3.X_P_H_PASS
  is '小时过车流量表';
comment on column ITMS3.X_P_H_PASS.org_code
  is '机构编码';
comment on column ITMS3.X_P_H_PASS.org_authority_code
  is '机构权限过滤编码';
comment on column ITMS3.X_P_H_PASS.district_code
  is '行政区划';
comment on column ITMS3.X_P_H_PASS.road_code
  is '道路编码';
comment on column ITMS3.X_P_H_PASS.site_code
  is '点位编码';
comment on column ITMS3.X_P_H_PASS.direction_type
  is '方向类型';
comment on column ITMS3.X_P_H_PASS.pass_time
  is '过车时间';
comment on column ITMS3.X_P_H_PASS.vehicle_type
  is '车辆类型';
comment on column ITMS3.X_P_H_PASS.num_flow
  is '过车数据';
comment on column ITMS3.X_P_H_PASS.place_local_city
  is '本地车';
comment on column ITMS3.X_P_H_PASS.place_local_pr
  is '本省车';
comment on column ITMS3.X_P_H_PASS.place_other_pr
  is '外省车';
comment on column ITMS3.X_P_H_PASS.place_military
  is '军警车';
comment on column ITMS3.X_P_H_PASS.place_unknown
  is '属地未知';
comment on column ITMS3.X_P_H_PASS.avg_speed
  is '平均速度';
comment on column ITMS3.X_P_H_PASS.create_date
  is '入库日期或更新日期';

prompt
prompt Creating table X_P_H_PASS_QTR
prompt =============================
prompt
create table ITMS3.X_P_H_PASS_QTR
(
  vehicle_seq_id     NUMBER(10),
  org_code           VARCHAR2(32),
  org_authority_code VARCHAR2(32),
  district_code      VARCHAR2(6),
  road_code          VARCHAR2(6),
  site_code          VARCHAR2(16),
  direction_type     VARCHAR2(16),
  pass_time          DATE,
  vehicle_type       VARCHAR2(4),
  pass_num           NUMBER(5) not null,
  place_local_city   NUMBER(5),
  place_local_pr     NUMBER(5),
  place_other_pr     NUMBER(5),
  place_military     NUMBER(5),
  place_unknown      NUMBER(5),
  avg_speed          NUMBER(6,2),
  create_date        DATE
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
comment on table ITMS3.X_P_H_PASS_QTR
  is '15分钟过车流量表';
comment on column ITMS3.X_P_H_PASS_QTR.org_code
  is '机构编码';
comment on column ITMS3.X_P_H_PASS_QTR.org_authority_code
  is '机构权限过滤编码';
comment on column ITMS3.X_P_H_PASS_QTR.district_code
  is '行政区划';
comment on column ITMS3.X_P_H_PASS_QTR.road_code
  is '道路编码';
comment on column ITMS3.X_P_H_PASS_QTR.site_code
  is '点位编码';
comment on column ITMS3.X_P_H_PASS_QTR.direction_type
  is '方向类型';
comment on column ITMS3.X_P_H_PASS_QTR.pass_time
  is '过车时间';
comment on column ITMS3.X_P_H_PASS_QTR.vehicle_type
  is '车辆类型';
comment on column ITMS3.X_P_H_PASS_QTR.pass_num
  is '过车数';
comment on column ITMS3.X_P_H_PASS_QTR.place_local_city
  is '本地车';
comment on column ITMS3.X_P_H_PASS_QTR.place_local_pr
  is '本省车';
comment on column ITMS3.X_P_H_PASS_QTR.place_other_pr
  is '外省车';
comment on column ITMS3.X_P_H_PASS_QTR.place_military
  is '军警车';
comment on column ITMS3.X_P_H_PASS_QTR.place_unknown
  is '属地未知';
comment on column ITMS3.X_P_H_PASS_QTR.avg_speed
  is '平均速度';
comment on column ITMS3.X_P_H_PASS_QTR.create_date
  is '入库日期或更新日期';

prompt
prompt Creating table X_P_IO_PASS
prompt ==========================
prompt
create table ITMS3.X_P_IO_PASS
(
  vehicle_seq_id     NUMBER(10),
  org_code           VARCHAR2(32) not null,
  org_authority_code VARCHAR2(32),
  district_code      VARCHAR2(6) not null,
  site_code          VARCHAR2(16) not null,
  direction_type     VARCHAR2(2) not null,
  enter_or_exit_city CHAR(1),
  vehicle_type       VARCHAR2(3),
  pass_date          DATE not null,
  num_flow           NUMBER(5) not null,
  place_local_city   NUMBER(5),
  place_local_pr     NUMBER(5),
  place_other_pr     NUMBER(5),
  place_military     NUMBER(5),
  place_unknown      NUMBER(5),
  avr_speed          NUMBER(6,2),
  create_date        DATE not null
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
comment on table ITMS3.X_P_IO_PASS
  is '进出城卡口数据表';
comment on column ITMS3.X_P_IO_PASS.org_code
  is '机构编码';
comment on column ITMS3.X_P_IO_PASS.org_authority_code
  is '机构权限编码';
comment on column ITMS3.X_P_IO_PASS.district_code
  is '行政区划';
comment on column ITMS3.X_P_IO_PASS.site_code
  is '点位编码';
comment on column ITMS3.X_P_IO_PASS.direction_type
  is '方向类型';
comment on column ITMS3.X_P_IO_PASS.enter_or_exit_city
  is '进出城标记';
comment on column ITMS3.X_P_IO_PASS.vehicle_type
  is '车辆类型';
comment on column ITMS3.X_P_IO_PASS.pass_date
  is '过车日期';
comment on column ITMS3.X_P_IO_PASS.num_flow
  is '过车数';
comment on column ITMS3.X_P_IO_PASS.place_local_city
  is '本地车';
comment on column ITMS3.X_P_IO_PASS.place_local_pr
  is '本省车';
comment on column ITMS3.X_P_IO_PASS.place_other_pr
  is '外省车';
comment on column ITMS3.X_P_IO_PASS.place_military
  is '军警车';
comment on column ITMS3.X_P_IO_PASS.place_unknown
  is '属地未知';
comment on column ITMS3.X_P_IO_PASS.avr_speed
  is '平均速度';
comment on column ITMS3.X_P_IO_PASS.create_date
  is '入库时间或更新时间';

prompt
prompt Creating table X_P_M_PASS
prompt =========================
prompt
create table ITMS3.X_P_M_PASS
(
  vehicle_seq_id     NUMBER(10),
  org_code           VARCHAR2(32),
  org_authority_code VARCHAR2(32),
  district_code      VARCHAR2(6),
  road_code          VARCHAR2(6),
  site_code          VARCHAR2(16) not null,
  direction_type     VARCHAR2(16),
  lane               VARCHAR2(2),
  vehicle_type       VARCHAR2(4),
  veh_localization   VARCHAR2(2),
  pass_month         CHAR(6) not null,
  avr_flow           NUMBER(10) not null,
  avr_flow_lm        NUMBER(10),
  avr_flow_ly        NUMBER(10),
  create_date        DATE
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
comment on table ITMS3.X_P_M_PASS
  is '月过车流量表';
comment on column ITMS3.X_P_M_PASS.org_code
  is '机构编码';
comment on column ITMS3.X_P_M_PASS.org_authority_code
  is '机构权限编码';
comment on column ITMS3.X_P_M_PASS.district_code
  is '行政区划';
comment on column ITMS3.X_P_M_PASS.road_code
  is '道路编码';
comment on column ITMS3.X_P_M_PASS.site_code
  is '点位编码';
comment on column ITMS3.X_P_M_PASS.direction_type
  is '方向类型';
comment on column ITMS3.X_P_M_PASS.lane
  is '车道';
comment on column ITMS3.X_P_M_PASS.vehicle_type
  is '车辆类型';
comment on column ITMS3.X_P_M_PASS.veh_localization
  is '车辆属地';
comment on column ITMS3.X_P_M_PASS.pass_month
  is '月份';
comment on column ITMS3.X_P_M_PASS.avr_flow
  is '平均流量';
comment on column ITMS3.X_P_M_PASS.avr_flow_lm
  is '上月流量';
comment on column ITMS3.X_P_M_PASS.avr_flow_ly
  is '上年流量';
comment on column ITMS3.X_P_M_PASS.create_date
  is '入库日期或更新日期';

prompt
prompt Creating table X_P_S_D_PASS
prompt ===========================
prompt
create table ITMS3.X_P_S_D_PASS
(
  vehicle_seq_id     NUMBER(10),
  org_code           VARCHAR2(32),
  org_authority_code VARCHAR2(32),
  district_code      VARCHAR2(6),
  road_code          VARCHAR2(6) not null,
  site_code          VARCHAR2(16) not null,
  direction_type     VARCHAR2(2) not null,
  lane               VARCHAR2(2),
  vehicle_type       VARCHAR2(3),
  veh_localization   CHAR(1),
  pass_date          DATE,
  pass_num           NUMBER,
  avg_speed          NUMBER,
  create_date        DATE
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
comment on table ITMS3.X_P_S_D_PASS
  is '日过车流量表';
comment on column ITMS3.X_P_S_D_PASS.org_code
  is '机构编码';
comment on column ITMS3.X_P_S_D_PASS.org_authority_code
  is '机构权限编码';
comment on column ITMS3.X_P_S_D_PASS.district_code
  is '行政区划';
comment on column ITMS3.X_P_S_D_PASS.road_code
  is '道路编码';
comment on column ITMS3.X_P_S_D_PASS.site_code
  is '点位代码';
comment on column ITMS3.X_P_S_D_PASS.direction_type
  is '方向类型';
comment on column ITMS3.X_P_S_D_PASS.lane
  is '车道';
comment on column ITMS3.X_P_S_D_PASS.vehicle_type
  is '车辆类型';
comment on column ITMS3.X_P_S_D_PASS.veh_localization
  is '车辆属地';
comment on column ITMS3.X_P_S_D_PASS.pass_date
  is '过车日期';
comment on column ITMS3.X_P_S_D_PASS.pass_num
  is '过车数';
comment on column ITMS3.X_P_S_D_PASS.avg_speed
  is '平均速度';
comment on column ITMS3.X_P_S_D_PASS.create_date
  is '入库时间或更新时间';

prompt
prompt Creating sequence SEQ_X_P_H_PASS
prompt ================================
prompt
create sequence ITMS3.SEQ_X_P_H_PASS
minvalue 1
maxvalue 99999999999999999999
start with 5001
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_X_P_H_PASS_QTR
prompt ====================================
prompt
create sequence ITMS3.SEQ_X_P_H_PASS_QTR
minvalue 1
maxvalue 99999999999999999999
start with 1028621
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_X_P_S_D_PASS
prompt ==================================
prompt
create sequence ITMS3.SEQ_X_P_S_D_PASS
minvalue 1
maxvalue 99999999999999999999
start with 64521
increment by 1
cache 20;

prompt
prompt Creating type FAULT_SUB_TIME_OBJ
prompt ================================
prompt
CREATE OR REPLACE TYPE ITMS3."FAULT_SUB_TIME_OBJ" IS OBJECT
(
  DEVICE_ID VARCHAR2(32),
  BEGINTIME date,
  ENDTIME   date,
  DURATION NUMBER
)
/

prompt
prompt Creating type FAULT_SUB_TIME_TABLE
prompt ==================================
prompt
CREATE OR REPLACE TYPE ITMS3.FAULT_SUB_TIME_TABLE IS TABLE OF FAULT_SUB_TIME_OBJ
/


spool off
