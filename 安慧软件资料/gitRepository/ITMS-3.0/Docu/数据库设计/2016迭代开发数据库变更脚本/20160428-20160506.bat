@echo off
::用户名
set user=itms3
::用户密码
set password=itms3
::数据库连接名
set oranetname=127.0.0.1/orcl

sqlplus %user%/%password%@%oranetname% @9_20160428/1_违法嫌疑表变更.sql
sqlplus %user%/%password%@%oranetname% @9_20160428/2_操作日志表变更.sql
sqlplus %user%/%password%@%oranetname% @10_20160504/1_系统表、故障表和角色表变更.sql
sqlplus %user%/%password%@%oranetname% @10_20160504/2_大区间表.sql
sqlplus %user%/%password%@%oranetname% @11_20160506/1_统计在线率.sql
sqlplus %user%/%password%@%oranetname% @11_20160506/2_系统代码和功能菜单.sql
sqlplus %user%/%password%@%oranetname% @11_20160506/3_大数据表变更.sql


@ECHO.
@ECHO Completed
@ECHO.
pause
@echo on
