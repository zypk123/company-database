@echo off
::用户名
set user=itms3
::用户密码
set password=itms3
::数据库连接名
set oranetname=127.0.0.1/orcl

sqlplus %user%/%password%@%oranetname% @16_20160523/1_系统代码.sql
sqlplus %user%/%password%@%oranetname% @17_20160525/1_检定相关表增加唯一索引.sql
sqlplus %user%/%password%@%oranetname% @17_20160525/2_违法嫌疑表增加索引.sql
sqlplus %user%/%password%@%oranetname% @17_20160525/3_巡航日志表变更.sql
sqlplus %user%/%password%@%oranetname% @18_20160526/1_系统区域表变更.sql
sqlplus %user%/%password%@%oranetname% @18_20160526/2_功能菜单.sql


@ECHO.
@ECHO Completed
@ECHO.
pause
@echo on
