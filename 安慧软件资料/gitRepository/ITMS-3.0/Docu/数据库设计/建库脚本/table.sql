-- Create table
create table T_SYS_ASSO_USER_DESK
(
  asso_id     VARCHAR2(32) not null,
  id          VARCHAR2(32),
  user_id     VARCHAR2(32),
  customer_id VARCHAR2(32),
  desk_id     VARCHAR2(32)
)
tablespace ITMS_DATA
  pctfree 10
  initrans 1
  maxtrans 255;
-- Add comments to the table 
comment on table T_SYS_ASSO_USER_DESK
  is '用户操作台关系表';
-- Add comments to the columns 
comment on column T_SYS_ASSO_USER_DESK.asso_id
  is '关系ID';
comment on column T_SYS_ASSO_USER_DESK.id
  is 'id';
comment on column T_SYS_ASSO_USER_DESK.user_id
  is '用户id';
comment on column T_SYS_ASSO_USER_DESK.customer_id
  is '客户id';
comment on column T_SYS_ASSO_USER_DESK.desk_id
  is '操作台id';
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_SYS_ASSO_USER_DESK
  add constraint PK_T_SYS_ASSO_USER_DESK primary key (ASSO_ID)
  using index 
  tablespace ITMS_DATA
  pctfree 10
  initrans 2
  maxtrans 255;


-- Create table
create table T_SYS_ASSO_USER_ROLE
(
  asso_id VARCHAR2(32) not null,
  role_id VARCHAR2(32) not null,
  user_id VARCHAR2(32) not null
)
tablespace ITMS_DATA
  pctfree 10
  initrans 1
  maxtrans 255;
-- Add comments to the table 
comment on table T_SYS_ASSO_USER_ROLE
  is '用户角色关系表';
-- Add comments to the columns 
comment on column T_SYS_ASSO_USER_ROLE.asso_id
  is '关系ID';
comment on column T_SYS_ASSO_USER_ROLE.role_id
  is '角色ID';
comment on column T_SYS_ASSO_USER_ROLE.user_id
  is '用户ID';
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_SYS_ASSO_USER_ROLE
  add constraint PK_T_ASSO_USER_ROLE primary key (ASSO_ID)
  using index 
  tablespace ITMS_DATA
  pctfree 10
  initrans 2
  maxtrans 255;

