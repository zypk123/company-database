set define off
spool 13_1_设备区间启用记录.log


--修改区间系统表，增加使用状态更新时间字段
alter table T_DEVICE_REGION ADD ENABLE_UPDATE_DATE DATE ;
comment on column T_DEVICE_REGION.ENABLE_UPDATE_DATE is '使用状态更新时间';

--修改使用状态日志表
alter table T_DEVICE_SYS_USE_STATUS ADD DEVICE_OR_REGION CHAR(1) ;
comment on column T_DEVICE_SYS_USE_STATUS.DEVICE_OR_REGION is '设备或区间。1.设备，2.区间';



spool off

exit;
