set define off
spool 4_2_增加流量监测系统.log

--创建流量参数表
create table T_DEVICE_FLOW
(
  device_id               VARCHAR2(32) not null,
  related_led_id          VARCHAR2(128)
);
 
comment on table T_DEVICE_FLOW is '流量检测设备表';

comment on column T_DEVICE_FLOW.device_id is '电子监控系统ID';
comment on column T_DEVICE_FLOW.related_led_id is '关联LED列表';

alter table T_DEVICE_FLOW add constraint PK_T_DEVICE_FLOW primary key (DEVICE_ID) ;
  
alter table T_DEVICE_FLOW add constraint FK_FLOW_SYS foreign key (DEVICE_ID)
  references T_DEVICE_SYS (DEVICE_ID) on delete cascade;
  
--卡口系统表增加是否支持流量检测字段
alter table T_DEVICE_TOLLGATE_SYS add is_flow_support CHAR(1) default 1;
comment on column T_DEVICE_TOLLGATE_SYS.is_flow_support
  is '是否支持流量监测  1 支持 0 不支持';

--违法取证表增加是否支持流量检测字段
alter table T_DEVICE_VIO_DEVICE add is_flow_support CHAR(1) default 1;
comment on column T_DEVICE_VIO_DEVICE.is_flow_support
  is '是否支持流量监测  1 支持 0 不支持';

spool off

exit;