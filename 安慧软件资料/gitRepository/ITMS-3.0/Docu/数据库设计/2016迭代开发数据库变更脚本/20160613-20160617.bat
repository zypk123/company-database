@echo off
::用户名
set user=itms3
::用户密码
set password=itms3
::数据库连接名
set oranetname=127.0.0.1/orcl

sqlplus %user%/%password%@%oranetname% @22_20160613/1_预置位表变更.sql
sqlplus %user%/%password%@%oranetname% @23_20160616/1_新增短信记录表.sql
sqlplus %user%/%password%@%oranetname% @24_20160617/1_系统代码表变更.sql
sqlplus %user%/%password%@%oranetname% @24_20160617/2_系统代码.sql
sqlplus %user%/%password%@%oranetname% @24_20160617/3_功能菜单.sql
sqlplus %user%/%password%@%oranetname% @24_20160617/4_设备报警表创建.sql
sqlplus %user%/%password%@%oranetname% @24_20160617/5_用户表更新.sql



@ECHO.
@ECHO Completed
@ECHO.
pause
@echo on
