set define off
spool 21_2_过车表变更.log

alter table T_VEHTRACK_PASS modify image_url_path VARCHAR2(256);

spool off

exit;
