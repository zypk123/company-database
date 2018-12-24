set define off
spool 18_1_系统区域表变更.log

alter table t_sys_area  modify(SITE_CODE_LIST VARCHAR2(512));

spool off

exit;
