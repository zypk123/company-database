set define off
spool 22_1_预置位表变更.log

--清理预置位表历史数据以确保下面的外键sql能成功执行
delete t_traffic_preset p where not exists(select 1 from t_device_sys d 
where p.device_id=d.device_id);

--增加t_device_sys级联删除外键
alter table t_traffic_preset add constraint FK_T_DEVICE_SYS
foreign key(device_id) references t_device_sys(device_id) on delete cascade;

spool off

exit;
