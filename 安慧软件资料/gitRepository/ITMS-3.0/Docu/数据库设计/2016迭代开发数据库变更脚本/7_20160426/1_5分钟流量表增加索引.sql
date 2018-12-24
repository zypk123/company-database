set define off
spool 7_1_5分钟流量表增加索引.log

create index IDX_FLOW_DEV_TIME on T_FLOW_FIVE_MIN (DEVICE_SYS_NBR, COUNT_TIME);
  
spool off

exit;