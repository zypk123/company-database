@echo off
::用户名
set user=itms3
::用户密码
set password=itms3
::数据库连接名
set oranetname=127.0.0.1/orcl

sqlplus %user%/%password%@%oranetname% @2_20160414/1_取消路段表路段代码唯一约束.sql
sqlplus %user%/%password%@%oranetname% @3_20160419/1_视频巡航日志表增加机构ID.sql
sqlplus %user%/%password%@%oranetname% @4_20160420/1_检定管理表变更.sql
sqlplus %user%/%password%@%oranetname% @4_20160420/2_增加流量监测系统.sql
sqlplus %user%/%password%@%oranetname% @5_20160421/1_设备区间增加合同厂商.sql
sqlplus %user%/%password%@%oranetname% @5_20160421/2_增加检定率统计视图.sql
sqlplus %user%/%password%@%oranetname% @5_20160421/3_增加地域表.sql
sqlplus %user%/%password%@%oranetname% @5_20160421/4_故障维护调整.sql
sqlplus %user%/%password%@%oranetname% @5_20160421/5_系统代码.sql
sqlplus %user%/%password%@%oranetname% @6_20160422/1_功能菜单.sql
sqlplus %user%/%password%@%oranetname% @6_20160422/2_创建过车至管控平台传输轨迹表.sql
sqlplus %user%/%password%@%oranetname% @7_20160426/1_5分钟流量表增加索引.sql
sqlplus %user%/%password%@%oranetname% @8_20160427/1_增加字段.sql
sqlplus %user%/%password%@%oranetname% @8_20160427/2_操作日志表变更.sql
sqlplus %user%/%password%@%oranetname% @8_20160427/3_违法表索引变更.sql

@ECHO.
@ECHO Completed
@ECHO.
pause
@echo on
