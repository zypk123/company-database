set define off
spool 14_1_修改违法嫌疑车辆表.log

--修改违法嫌疑车辆表
alter table t_vehtrack_pass_to_vio drop column IDENTIFY_ACCORDANCE;
alter table t_vehtrack_pass_to_vio add IDENTIFY_ACCORDANCE clob;

alter table t_vehtrack_pass_to_vio drop column VIO_SUSPECTED_DESC;
alter table t_vehtrack_pass_to_vio add  VIO_SUSPECTED_DESC VARCHAR2(1024);
comment on column t_vehtrack_pass_to_vio.VIO_SUSPECTED_DESC is '违法嫌疑描述。比如超速20%';



spool off

exit;
