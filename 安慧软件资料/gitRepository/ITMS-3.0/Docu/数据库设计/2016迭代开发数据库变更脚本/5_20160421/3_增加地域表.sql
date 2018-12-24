set define off
spool 5_3_增加地域表.log

-- Create table
create table T_SYS_AREA
(
  area_id            VARCHAR2(32) not null,
  area_name          VARCHAR2(64) not null,
  area_type          VARCHAR2(2) default '0' not null,
  site_code_list     VARCHAR2(256),
  org_id             VARCHAR2(256),
  org_privilege_code VARCHAR2(256)
);
-- Add comments to the table 
comment on table T_SYS_AREA
  is '区域表';
-- Add comments to the columns 
comment on column T_SYS_AREA.area_id
  is '区域ID';
comment on column T_SYS_AREA.area_name
  is '区域名称';
comment on column T_SYS_AREA.area_type
  is '区域类型';
comment on column T_SYS_AREA.site_code_list
  is '点位列表';
comment on column T_SYS_AREA.org_privilege_code
  is '组织机构权限代码';
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_SYS_AREA
  add constraint T_SYS_ARETID primary key (AREA_ID);


spool off

exit;
