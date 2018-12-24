set define off
spool 10_1_系统表、故障表和角色表变更.log

alter table T_DEVICE_SYS modify status_type null;

-- Add/modify columns 
alter table T_DEVICE_FAULT modify is_validity default 3;
-- Add comments to the columns 
comment on column T_DEVICE_FAULT.is_validity
  is '故障/报警有效性。1-有效；2-无效；3-未确认';
  
alter table T_SYS_ROLE ADD ORG_CODE VARCHAR2(32) ;
comment on column T_SYS_ROLE.ORG_CODE is '机构编码';

spool off

exit;