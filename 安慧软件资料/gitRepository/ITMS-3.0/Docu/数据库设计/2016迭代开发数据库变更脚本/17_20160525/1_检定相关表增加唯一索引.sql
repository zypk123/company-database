set define off
spool 17_1_检定相关表增加唯一索引.log

--检定信息表中[证书编号]加唯一索引
create unique index IDX_DEVICECERTIFICATION_01 on T_DEVICE_CERTIFICATION (CERTIFICATION_NBR);
--检定证书批量导入记录表[检定证书,批次]添加联合索引
create unique index IDX_CERTIFICATIONRECORD_01 on T_DEVICE_CERTIFICATION_RECORD (CERTIFICATION_NBR,IMPORT_DATE);


spool off

exit;