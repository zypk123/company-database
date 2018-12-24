set define off
spool 10_固定测速系统代码名称调整.log

update T_SYS_CODE c set c.code_name = '固定点测速' where c.code_type = '400' and c.code_no = '04';
commit;


spool off

exit;