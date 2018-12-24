set define off
spool 13_3_设备状态统计中间表变更.log

alter table x_d_d_device_status rename column stat_online to ownership;
alter table x_d_d_status_log rename column stat_online to ownership;
alter table x_d_m_status_log rename column stat_online to ownership;




spool off

exit;
