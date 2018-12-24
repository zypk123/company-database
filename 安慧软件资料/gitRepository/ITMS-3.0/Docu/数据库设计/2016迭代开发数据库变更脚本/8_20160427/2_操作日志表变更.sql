set define off
spool 8_2_操作日志表变更.log

drop  table T_SYS_OPERATE_MENU_LOG;
create table T_SYS_OPERATE_MENU_LOG
(
  USER_ID      VARCHAR2(32) not null,
  MENU_ID      VARCHAR2(32) not null,
  MEN_NAME     VARCHAR2(100),
  OPERATE_TIME DATE,
  REMARK       VARCHAR2(128) not null,
  ORG_ID       VARCHAR2(32) not null,
  OPER_IP      VARCHAR2(60)
);

-- Add comments to the table 
comment on table T_SYS_OPERATE_MENU_LOG
  is '用户操作菜单日志';
-- Add comments to the columns 
comment on column T_SYS_OPERATE_MENU_LOG.USER_ID
  is '用户唯一标识';
comment on column T_SYS_OPERATE_MENU_LOG.MENU_ID
  is '菜单ID';
comment on column T_SYS_OPERATE_MENU_LOG.MEN_NAME
  is '菜单名称';
comment on column T_SYS_OPERATE_MENU_LOG.OPERATE_TIME
  is '操作时间';
comment on column T_SYS_OPERATE_MENU_LOG.REMARK
  is '备注';
comment on column T_SYS_OPERATE_MENU_LOG.ORG_ID
  is '机构';
comment on column T_SYS_OPERATE_MENU_LOG.OPER_IP
  is '操作人IP';

spool off

exit;