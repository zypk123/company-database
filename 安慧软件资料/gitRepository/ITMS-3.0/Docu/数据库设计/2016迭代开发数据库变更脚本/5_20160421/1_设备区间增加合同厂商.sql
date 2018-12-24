set define off
spool 5_1_设备区间增加合同厂商.log

alter table t_device_region add(contract_id VARCHAR2(32), contractor_id VARCHAR2(32));

spool off

exit;
