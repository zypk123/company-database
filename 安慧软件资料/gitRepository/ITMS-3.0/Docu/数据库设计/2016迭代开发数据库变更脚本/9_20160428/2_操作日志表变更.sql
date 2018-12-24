set define off
spool 9_2_违法嫌疑表变更.log

-- Add/modify columns 
alter table T_SYS_OPERATION_LOG add ORG_PRIVILEGE_CODE VARCHAR(8);
-- Add comments to the columns 
comment on column T_SYS_OPERATION_LOG.ORG_PRIVILEGE_CODE
  is '机构权限代码';

alter table T_SYS_OPERATION_LOG rename column OPE_USER_NAME to OPE_USER_ID;

spool off

exit;