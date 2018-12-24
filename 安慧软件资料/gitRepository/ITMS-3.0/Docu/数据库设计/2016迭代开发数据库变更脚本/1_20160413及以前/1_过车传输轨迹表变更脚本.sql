set define off
spool 1_过车传输轨迹表变更脚本.log

alter table T_DEVICE_DATA_PATH add device_sys_nbr VARCHAR2(32);
alter table T_DEVICE_DATA_PATH add site_code VARCHAR2(16);
alter table T_DEVICE_DATA_PATH add road_code VARCHAR2(6);
comment on column T_DEVICE_DATA_PATH.device_sys_nbr  is '电子监控系统编号';
comment on column T_DEVICE_DATA_PATH.site_code is '点位代码';
comment on column T_DEVICE_DATA_PATH.road_code is '道路代码';

alter table T_DEVICE_DATA_PATH add after_server_receiving_time date;
comment on column T_DEVICE_DATA_PATH.after_server_receiving_time
  is '后置监控服务器处理时间';

create index IDX_T_DATA_PATH on T_DEVICE_DATA_PATH (pass_time, org_privilege_code, device_sys_nbr, site_code, road_code);

spool off

exit;