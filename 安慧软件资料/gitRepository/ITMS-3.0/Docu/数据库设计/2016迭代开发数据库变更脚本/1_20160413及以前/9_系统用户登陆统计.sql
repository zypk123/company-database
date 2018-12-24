set define off
spool 9_系统用户登陆统计.log

create table T_SYS_OPERATE_MENU_LOG
(
  user_id      VARCHAR2(32) not null,
  menu_id      VARCHAR2(32) not null,
  men_name     VARCHAR2(100),
  operate_time DATE,
  remark       VARCHAR2(128) not null
);
comment on table T_SYS_OPERATE_MENU_LOG
  is '用户操作菜单日志';
comment on column T_SYS_OPERATE_MENU_LOG.user_id
  is '用户唯一标识';
comment on column T_SYS_OPERATE_MENU_LOG.menu_id
  is '菜单ID';
comment on column T_SYS_OPERATE_MENU_LOG.men_name
  is '菜单名称';
comment on column T_SYS_OPERATE_MENU_LOG.operate_time
  is '操作时间';
comment on column T_SYS_OPERATE_MENU_LOG.remark
  is '备注';

create table T_SYS_USER_LOGIN_LOG
(
  user_id    VARCHAR2(32) not null,
  user_ip    VARCHAR2(32),
  login_time DATE,
  remark     VARCHAR2(128) not null
)
;
comment on table T_SYS_USER_LOGIN_LOG
  is '用户登录日志表';
comment on column T_SYS_USER_LOGIN_LOG.user_id
  is '用户唯一标识';
comment on column T_SYS_USER_LOGIN_LOG.user_ip
  is '登录IP';
comment on column T_SYS_USER_LOGIN_LOG.login_time
  is '登录时间';
comment on column T_SYS_USER_LOGIN_LOG.remark
  is '备注';

create table T_SYS_USER_STATUS
(
  user_id      VARCHAR2(32) not null,
  login_count  NUMBER(12),
  current_time DATE,
  remark       VARCHAR2(128) not null
);

comment on table T_SYS_USER_STATUS
  is '用户状态表';
comment on column T_SYS_USER_STATUS.user_id
  is '用户唯一标识';
comment on column T_SYS_USER_STATUS.login_count
  is '登录次数';
comment on column T_SYS_USER_STATUS.current_time
  is '最近一次登录时间';
comment on column T_SYS_USER_STATUS.remark
  is '备注';


spool off

exit;
