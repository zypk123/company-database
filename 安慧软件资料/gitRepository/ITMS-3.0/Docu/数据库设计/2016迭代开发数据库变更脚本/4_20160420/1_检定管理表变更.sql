set define off
spool 4_1_检定管理表变更.log

--删除检定信息表deviceId字段
alter table T_DEVICE_CERTIFICATION drop (device_id);

alter table T_DEVICE_CERTIFICATION_RECORD add(REMARK varchar2(256));

COMMENT ON COLUMN T_DEVICE_CERTIFICATION_RECORD.REMARK IS '备注';

spool off

exit;