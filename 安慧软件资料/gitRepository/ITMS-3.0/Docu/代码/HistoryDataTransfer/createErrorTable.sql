-----------------------------------------------------
-- Export file for user ITMS3                      --
-- Created by Administrator on 2016-05-13, 9:26:32 --
-----------------------------------------------------

set define off
spool createErrorTable.log

prompt
prompt Creating table X_P_D_PASS_ERROR
prompt ===============================
prompt
create table ITMS3.X_P_D_PASS_ERROR
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

prompt
prompt Creating table X_P_PASS_ERROR
prompt =============================
prompt
create table ITMS3.X_P_PASS_ERROR
(
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


spool off
