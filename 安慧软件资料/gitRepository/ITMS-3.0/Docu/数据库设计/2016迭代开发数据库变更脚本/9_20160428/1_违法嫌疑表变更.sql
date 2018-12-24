set define off
spool 9_1_违法嫌疑表变更.log

alter table T_VEHTRACK_PASS_TO_VIO modify IDENTIFY_TYPE VARCHAR2(6);
comment on column T_VEHTRACK_PASS_TO_VIO.IDENTIFY_TYPE IS '违法嫌疑类型';



spool off

exit;