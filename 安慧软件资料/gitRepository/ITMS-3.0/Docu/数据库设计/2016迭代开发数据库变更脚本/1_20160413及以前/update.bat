@echo off
::用户名
set user=itms3
::用户密码
set password=itms3
::数据库连接名
set oranetname=127.0.0.1/orcl
sqlplus %user%/%password%@%oranetname% @1_过车传输轨迹表变更脚本.sql
sqlplus %user%/%password%@%oranetname% @2_设备和路网调整变更脚本.sql
sqlplus %user%/%password%@%oranetname% @3_用户签章脚本.sql
sqlplus %user%/%password%@%oranetname% @4_视频巡航变更.sql
sqlplus %user%/%password%@%oranetname% @5_平台首页.sql
sqlplus %user%/%password%@%oranetname% @6_设备和装备增加检定标识.sql
sqlplus %user%/%password%@%oranetname% @7_设备与装备视图变更.sql
sqlplus %user%/%password%@%oranetname% @8_视频接入方式定义.sql
sqlplus %user%/%password%@%oranetname% @9_系统用户登陆统计.sql
sqlplus %user%/%password%@%oranetname% @10_固定测速系统代码名称调整.sql
sqlplus %user%/%password%@%oranetname% @11_检定管理相关表变更.sql
sqlplus %user%/%password%@%oranetname% @12_假套牌车变更.sql
sqlplus %user%/%password%@%oranetname% @98_系统代码.sql
sqlplus %user%/%password%@%oranetname% @99_功能权限菜单.sql
@ECHO.
@ECHO Completed
@ECHO.
pause
@echo on
