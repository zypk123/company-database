set define off
spool 19_1_违法嫌疑表变更.log

alter table t_vehtrack_pass_to_vio modify confirm_flag default '3';

spool off

exit;
