set define off
spool 24_4_设备报警表创建.log

prompt Creating T_DEVICE_ALARM...
create table T_DEVICE_ALARM
(
  alarm_id           VARCHAR2(32) not null,
  alarm_type         VARCHAR2(2) not null,
  alarm_sub_type     VARCHAR2(5),
  alarm_device_id    VARCHAR2(32) not null,
  start_time         DATE not null,
  end_time           DATE,
  collect_way        CHAR(1),
  alarm_disc         VARCHAR2(256),
  org_privilege_code VARCHAR2(8) not null,
  create_time        DATE not null
)
;
comment on table T_DEVICE_ALARM
  is '保存设备相关的报警信息';
comment on column T_DEVICE_ALARM.alarm_id
  is '唯一标识';
comment on column T_DEVICE_ALARM.alarm_type
  is '1:设备报警 2:服务器报警 3:其他';
comment on column T_DEVICE_ALARM.alarm_sub_type
  is '设备类，对应code:478 其他可为空';
comment on column T_DEVICE_ALARM.alarm_device_id
  is '报警关联的设备ID，可以是设备，也可以是服务器等';
comment on column T_DEVICE_ALARM.start_time
  is '报警开始时间';
comment on column T_DEVICE_ALARM.end_time
  is '报警结束时间，可以为空';
comment on column T_DEVICE_ALARM.collect_way
  is '1前端设备上传 2系统分析 3人工添加';
comment on column T_DEVICE_ALARM.alarm_disc
  is '故障描述';
comment on column T_DEVICE_ALARM.org_privilege_code
  is '机构权限编码，用作权限过滤';
comment on column T_DEVICE_ALARM.create_time
  is '创建时间';
alter table T_DEVICE_ALARM
  add constraint PK_T_DEVICE_ALARM primary key (ALARM_ID);

prompt Creating T_DEVICE_ALARM_HANDLE...
create table T_DEVICE_ALARM_HANDLE
(
  handle_id     VARCHAR2(32) not null,
  alarm_id      VARCHAR2(32) not null,
  is_validity   VARCHAR2(1) not null,
  handle_disc   VARCHAR2(256),
  handle_time   DATE not null,
  handle_person VARCHAR2(32) not null
)
;
comment on table T_DEVICE_ALARM_HANDLE
  is '存储报警处置信息';
comment on column T_DEVICE_ALARM_HANDLE.handle_id
  is '唯一标识';
comment on column T_DEVICE_ALARM_HANDLE.alarm_id
  is '报警ID';
comment on column T_DEVICE_ALARM_HANDLE.is_validity
  is '1有效0无效';
comment on column T_DEVICE_ALARM_HANDLE.handle_person
  is '处置用户ID';
alter table T_DEVICE_ALARM_HANDLE
  add constraint PK_T_DEVICE_ALARM_HANDLE primary key (HANDLE_ID);

prompt Creating T_DEVICE_ALARM_STATUS...
create table T_DEVICE_ALARM_STATUS
(
  alarm_id      VARCHAR2(32) not null,
  is_accept     CHAR(1),
  accept_time   DATE,
  accpet_person VARCHAR2(32),
  is_handle     CHAR(1),
  is_validity   CHAR(1),
  handle_person VARCHAR2(32)
)
;
comment on table T_DEVICE_ALARM_STATUS
  is '记录报警信息状态';
comment on column T_DEVICE_ALARM_STATUS.is_accept
  is '0否1是';
comment on column T_DEVICE_ALARM_STATUS.is_handle
  is '0否1是';
comment on column T_DEVICE_ALARM_STATUS.is_validity
  is '0否1是';
alter table T_DEVICE_ALARM_STATUS
  add constraint PK_T_DEVICE_ALARM_STATUS primary key (ALARM_ID);



spool off

exit;
