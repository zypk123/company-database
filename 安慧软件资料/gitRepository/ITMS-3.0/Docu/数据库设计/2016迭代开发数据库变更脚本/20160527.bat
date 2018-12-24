@echo off
::用户名
set user=itms3
::用户密码
set password=itms3
::数据库连接名
set oranetname=127.0.0.1/orcl

sqlplus %user%/%password%@%oranetname% @16_20160527/1_违法嫌疑表变更.sql

@ECHO.
@ECHO Completed
@ECHO.
pause
@echo on
