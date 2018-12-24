set define off
spool 6_设备和装备增加检定标识.log

alter table T_DEVICE_EQUIPMENT add(verification_mark varchar2(5));
alter table T_DEVICE_SYS add(verification_mark varchar2(5));
alter table T_DEVICE_REGION add(verification_mark varchar2(5));


COMMENT ON COLUMN T_DEVICE_EQUIPMENT."VERIFICATION_MARK" IS
'检定标识 0 不需要检定 1 需要检定';

COMMENT ON COLUMN T_DEVICE_SYS."VERIFICATION_MARK" IS
'检定标识 0 不需要检定 1 需要检定';

COMMENT ON COLUMN T_DEVICE_REGION."VERIFICATION_MARK" IS
'检定标识 0 不需要检定 1 需要检定';


spool off

exit;