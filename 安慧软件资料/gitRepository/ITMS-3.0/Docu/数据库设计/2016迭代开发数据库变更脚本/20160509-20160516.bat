@echo off
::用户名
set user=itms3
::用户密码
set password=itms3
::数据库连接名
set oranetname=127.0.0.1/orcl

sqlplus %user%/%password%@%oranetname% @12_20160509/1_创建号牌前缀表.sql
sqlplus %user%/%password%@%oranetname% @13_20160510/1_设备区间启用记录.sql
sqlplus %user%/%password%@%oranetname% @13_20160510/2_违法嫌疑车辆表变更.sql
sqlplus %user%/%password%@%oranetname% @13_20160510/3_设备状态统计中间表变更.sql
sqlplus %user%/%password%@%oranetname% @13_20160510/4_在线率统计存储过程.sql
sqlplus %user%/%password%@%oranetname% @13_20160510/系统代码.sql
sqlplus %user%/%password%@%oranetname% @14_20160513/1_修改违法嫌疑车辆表.sql
sqlplus %user%/%password%@%oranetname% @15_20160516/1_导出触发器.sql
sqlplus %user%/%password%@%oranetname% @15_20160516/2_菜单与功能.sql

@ECHO.
@ECHO Completed
@ECHO.
pause
@echo on
