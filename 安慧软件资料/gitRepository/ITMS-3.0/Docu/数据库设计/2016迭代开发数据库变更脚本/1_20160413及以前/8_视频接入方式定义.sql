set define off
spool 8_视频接入方式定义.log

 update t_sys_code_type c set c.code_type_name = '视频接入方式' where c.code_type = '485';
 
 commit;
 
 
spool off

exit;