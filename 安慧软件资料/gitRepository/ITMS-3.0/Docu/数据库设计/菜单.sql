--t_sys_role表：
--删除表数据:
delete from t_sys_role;

--添加角色权限信息
insert into t_sys_role (role_id,role_name,role_enable_flag,role_remark,data_access_type) 
values ('9a86c55384ac4f4eb0e848a28d6ea8d6','管理员','1','维护人员','0');


--t_sys_user表：
--删除表数据:
delete from t_sys_user ;


--添加用户信息
insert into T_SYS_USER (user_id, other_org_id, org_id, police_id, login_name, login_password, permission_ip_list, is_online, latest_login_time, total_login_counts, name, valid_date)
values ('355e8f04ce7447929dde896e289c84ef', null, '53000000', '26ad6bde49b643e5b24086a751c8e257', 'ADMIN', 'e10adb3949ba59abbe56e057f20f883e', null, '2', to_date('14-03-2016 11:14:40', 'dd-mm-yyyy hh24:mi:ss'), 0, '管理员', null);



--配置菜单
--t_sys_menu表,  t_sys_function表:

--删除表数据：
delete from t_sys_menu;

--(一级菜单)
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('01','01','电子地图','','root',1,1,null,'themes/default/images/dzdt.png');
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('02','02','视频监控','','root',2,1,null,'themes/default/images/spjk.png');
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('03','03','车辆查控','','root',4,1,null,'themes/default/images/clck.png');
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('04','04','违法管理','','root',5,1,null,'themes/default/images/wfgl.png');
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('05','05','信息发布','','root',6,1,null,'themes/default/images/xxfb.png');
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('06','06','设备运维','','root',7,1,null,'themes/default/images/sbyw.png');
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('07','07','统计分析','','root',8,1,null,'themes/default/images/tjfx.png');
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('08','08','系统管理','','root',9,1,null,'themes/default/images/xtgl.png');
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('09','09','路况监控','','root',3,1,null,'themes/default/images/lkjk.png');
--（电子地图）
--（二级菜单）
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('0101','0101','设备分布','','01',1,1);
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('0102','0102','交通态势','','01',2,1);
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('0103','0103','轨迹查询','','01',3,1);
--（三级菜单）
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('010101','010101','设备分布','tpls/deviceManage/deviceMonitor/device-moniter-onGIS.html','0101',1,1);
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('010201','010201','交通态势','tpls/trafficMonitor/trafficState/traffic-state.html','0102',1,1);
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('010301','010301','轨迹查询','tpls/passVehicle/vehicleQuery/vehicle-path-query.html','0103',1,1);

--（视频监控）
--（二级菜单）
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('0201','0201','视频监控','','02',1,1);
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('0202','0202','视频回放','','02',2,1);
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('0203','0203','视频巡航','','02',3,1);
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('0204','0204','巡航方案','','02',4,1);
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('0205','0205','巡航日志','','02',5,1);
--（三级菜单）
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('020101','020101','视频监控','tpls/trafficMonitor/videoMonitor/realTimeMonitor.html','0201',1,1);
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('020201','020201','视频回放','tpls/trafficMonitor/videoMonitor/playback.html','0202',1,1);
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('020301','020301','视频巡航','tpls/trafficMonitor/videoMonitor/cruise.html','0203',1,1);
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('020401','020401','巡航方案','tpls/trafficMonitor/videoMonitor/cruisePlanConfig.html','0204',1,1);
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('020501','020501','巡航日志','tpls/trafficMonitor/videoMonitor/cruise-log-list.html','0205',1,1);

--（车辆查控）
--（二级菜单）
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('0301','0301','过车监控','','03',1,1);
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('0302','0302','车辆查询','','03',2,1);
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('0303','0303','车辆布控','','03',3,1);
--（三级菜单）
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('030101','030101','过车监控','tpls/passVehicle/passMonitor/pass-vehicle-monitor.html','0301',1,1);

insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('030201','030201','车辆查询','tpls/passVehicle/vehicleQuery/vehicle-query-all.html','0302',1,1);

insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('030301','030301','布控撤控','tpls/passVehicle/controlManager/control-uncontrol-manager.html','0303',1,1);
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('030302','030302','假套牌车管理','tpls/passVehicle/controlManager/false-deck-manager.html','0303',2,1);
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('030303','030303','黄标车管理','tpls/passVehicle/controlManager/yellow-label-car-manager.html','0303',3,1);
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('030304','030304','布控报警','tpls/passVehicle/alarmManager/control-manager-list.html','0303',4,1);

--（违法管理）
--（二级菜单）
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('0401','0401','违法查询','','04',1,1);
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('0402','0402','违法上传','','04',2,1);
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('0403','0403','区间测速网','','04',3,1);
--（二级菜单）
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('040101','040101','违法查询','tpls/violationMgr/ViolationManager/ViolationQuery.html','0401',1,1);

insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('040201','040201','违法上传','tpls/violationMgr/ViolationManager/UploadQuery.html','0402',1,1);

insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('040301','040301','区间测速网','test','0403',1,1);
--（信息发布）
--（二级菜单）
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('0501','0501','信息发布','','05',1,1);
--（三级菜单）
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('050101','050101','信息发布','test','0501',1,1);

--（设备运维）
--（二级菜单）
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('0601','0601','设备监控','','06',1,1);
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('0602','0602','备案管理','','06',2,1);
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('0603','0603','检定管理','','06',3,1);
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('0604','0604','申报审批','','06',4,1);
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('0605','0605','维护管理','','06',5,1);
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('0606','0606','服务监控管理','','06',6,1);
--（三级菜单）
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('060101','060101','设备运行监控','tpls/deviceManage/deviceMonitor/device-status-monitor.html','0601',1,1);
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('060102','060102','卡口传输轨迹','tpls/deviceManage/systemMaintainManage/data-transmission-path-list.html','0601',2,1);

insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('060201','060201','设备备案管理','tpls/deviceManage/deviceConfig/device-sys-list.html','0602',01,1);
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('060202','060202','卡口备案','tpls/deviceManage/deviceRecord/bayonet-manage-list.html','0602',02,1);
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('060203','060203','视频','test','0602',03,0);
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('060204','060204','固定测速','test','0602',04,0);
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('060205','060205','区间测速','tpls/deviceManage/deviceConfig/interval-system-device-list.html','0602',05,1);
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('060206','060206','移动测速','test','0602',06,0);
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('060207','060207','电子警察','test','0602',07,0);
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('060208','060208','交通事件检测','test','0602',08,0);
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('060209','060209','气象监控','test','0602',09,0);
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('060210','060210','诱导屏','test','0602',10,0);
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('060211','060211','信号机','test','0602',11,0);
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('060212','060212','违停','test','0602',12,0);
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('060213','060213','大车占道','test','0602',13,0);
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('060214','060214','隧道监控','test','0602',14,0);
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('060215','060215','酒精检测仪','test','0602',15,0);
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('060216','060216','点位管理','tpls/sysManagement/roadNetworkMessage/point-message.html','0602',18,1);
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('060217','060217','厂商信息','tpls/deviceManage/informationManage/company-manage-list.html','0602',17,1);
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('060218','060218','合同信息','tpls/deviceManage/informationManage/contract-manage-list.html','0602',16,1);

insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('060301','060301','检定管理','test','0603',1,1);

insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('060401','060401','设备申报审批','tpls/deviceManage/deviceSystemApplicationEndorsement/deviceApplicationEndorsement/device-build-list.html','0604',1,1);
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('060402','060402','系统申报审批','tpls/deviceManage/deviceSystemApplicationEndorsement/systemApplicationEndorsement/sys-application-list.html','0604',2,1);

insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('060501','060501','故障告警管理','tpls/deviceManage/deviceMaintenance/fault-alarm-manager.html','0605',1,1);
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('060502','060502','维护单管理','tpls/deviceManage/deviceMaintenance/maintenance-manage.html','0605',2,1);

insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('060601','060601','监控中心服务器','tpls/deviceManage/informationManage/monitoring-center-manage-list.html','0606',1,1);
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('060602','060602','接入平台','tpls/deviceManage/accessPlatform/access-platform-list.html','0606',2,1);
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('060603','060603','服务器类型','tpls/deviceManage/informationManage/server-type-manage-list.html','0606',3,1);
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('060604','060604','后台服务监测','test','0606',4,1);

--（统计分析）
--（二级菜单）
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('0701','0701','设备考核','','07',1,1);
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('0702','0702','交通流量统计','','07',2,1);
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('0703','0703','大数据分析','','07',3,1);
--（三级菜单）
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('070101','070101','设备接入率','tpls/deviceManage/deviceStatistics/device-access-rate.html','0701',1,1);
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('070102','070102','设备在线率','tpls/deviceManage/deviceStatistics/device-online-rate.html','0701',2,1);
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('070103','070103','设备检定率','tpls/deviceManage/deviceStatistics/device-certification-rate.html','0701',3,1);
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('070104','070104','设备故障率','tpls/deviceManage/deviceStatistics/device-fault-rate.html','0701',4,1);

insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('070201','070201','单卡口分析','tpls/statisticsAnalysis/flowAnalysis/single-site-flow-analysis.html','0702',1,1);
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('070202','070202','道路卡口分析','tpls/statisticsAnalysis/flowAnalysis/road-flow-analysis.html','0702',2,1);
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('070203','070203','辖区卡口分析','tpls/statisticsAnalysis/flowAnalysis/district-flow-analysis.html','0702',3,1);
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('070204','070204','多点卡口分析','tpls/statisticsAnalysis/flowAnalysis/multi-site-flow-analysis.html','0702',4,1);
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('070205','070205','节假日流量分析','tpls/statisticsAnalysis/flowAnalysis/holidayflowAnalysis.html','0702',5,1);

insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('070301','070301','套牌车分析','tpls/passVehicle/cloneCarManager/clone-car-analysis-list.html','0703',1,1);
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('070302','070302','危险驾驶TopN分析','tpls/passVehicle/dangerCarManager/danger-car-query-list.html','0703',2,1);
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('070303','070303','危险驾驶整体分析','tpls/passVehicle/dangerCarManager/danger-car-whole-list.html','0703',3,1);
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('070304','070304','危险驾驶分项分析','tpls/passVehicle/collisionManager/case-collision-analyse.html','0703',4,1);
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('070305','070305','无牌车分析','tpls/passVehicle/nullPlateManager/nullPlate-car-query-list.html','0703',5,1);
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('070306','070306','号牌识别率','tpls/passVehicle/plateRecognitionManagement/plate-recognition-list.html','0703',6,1);
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('070307','070307','区域案件对碰分析','tpls/passVehicle/collisionManager/case-collision-analyse.html','0703',7,1);
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('070308','070308','伴随车辆分析','tpls/passVehicle/accompanyCarManager/accompany-query-all.html','0703',8,1);
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('070309','070309','高危地域车辆分析','tpls/passVehicle/dangerAreaManager/dangerarea-vehicle-analyse.html','0703',9,1);

--（系统管理）
--（二级菜单）
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('0801','0801','机构用户','','08',1,1);
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('0802','0802','角色权限','','08',2,1);
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('0803','0803','系统代码','','08',3,1);
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('0804','0804','操作日志','','08',4,1);
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('0805','0805','路网信息','','08',5,1);
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('0806','0806','交通基础信息','','08',6,1);
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('0807','0807','文件下载','','08',7,1);
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('0808','0808','节日设定','','08',8,1);
--（三级菜单）
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('080101','080101','机构信息','tpls/sysManagement/org/org-list.html','0801',1,1);
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('080102','080102','用户信息','tpls/sysManagement/user/user-list.html','0801',2,1);

insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('080201','080201','角色权限','tpls/sysManagement/rolePermission/role-permission-message.html','0802',1,1);

insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('080301','080301','系统代码','tpls/sysManagement/systemCode/sysCode-list.html','0803',1,1);

insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('080401','080401','操作日志','tpls/sysManagement/operationLog/operation-log-list.html','0804',1,1);

insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('080501','080501','道路信息','tpls/sysManagement/roadNetworkMessage/road-message-list.html','0805',1,1);
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('080502','080502','路段信息','tpls/sysManagement/roadNetworkMessage/road-section-message-list.html','0805',2,1);
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('080503','080503','路口信息','tpls/sysManagement/roadNetworkMessage/crossing-message-list.html','0805',3,1);

insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('080601','080601','交警机构','tpls/sysManagement/traffic/traffic-police.html','0806',1,1);
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('080602','080602','服务资源','test','0806',2,1);

insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('080701','080701','文件下载','tpls/sysManagement/downLoad/download-file.html','0807',1,1);

insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('080801','080801','节假日配置','tpls/sysManagement/festival/festival-setting.html','0808',1,1);

--（路况监控）
--（二级菜单）
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('0901','0901','路况监控','','09',1,1);
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('0902','0902','交通事件管理','','09',2,1);
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('0903','0903','阀值设置','','09',3,1);
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('0904','0904','流量监控段','','09',4,1);
--（三级菜单）
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('090101','090101','路况监控','tpls/trafficMonitor/trafficState/traffic-state.html','0901',1,1);

insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('090201','090201','交通管制登记','tpls/trafficMonitor/manualEvent/traffic-control.html','0902',1,1);
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('090202','090202','交通事件登记','tpls/trafficMonitor/manualEvent/manual-event.html','0902',2,1);
insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('090203','090203','交通气象登记','tpls/trafficMonitor/manualEvent/manual-weather.html','0902',3,1);

insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('090301','090301','阀值设置','tpls/trafficMonitor/trafficWeather/weather-threshold-setting.html','0903',1,1);

insert into t_sys_menu (menu_code,sys_config_id,menu_name,target_url,parent_code,sort_index,menu_enable_flag)
values('090401','090401','流量监控段','tpls/sysManagement/roadNetworkMessage/region-message-list.html','0904',1,1);


--功能配置
delete from t_sys_function;

--（电子地图）
insert into t_sys_function(function_code,menu_code,function_name,function_flag)
values('01010101','010101','设备分布','1');
insert into t_sys_function(function_code,menu_code,function_name,function_flag)
values('01020101','010201','交通势态','1');
insert into t_sys_function(function_code,menu_code,function_name,function_flag)
values('01030101','010301','轨迹查询','1');

--（视频监控）
insert into t_sys_function(function_code,menu_code,function_name,function_flag)
values('02010101','020101','视频监控','1');
insert into t_sys_function(function_code,menu_code,function_name,function_flag)
values('02020101','020201','视频回放','1');
insert into t_sys_function(function_code,menu_code,function_name,function_flag)
values('02030101','020301','视频巡航','1');
insert into t_sys_function(function_code,menu_code,function_name,function_flag)
values('02040101','020401','巡航方案','1');
insert into t_sys_function(function_code,menu_code,function_name,function_flag)
values('02050101','020501','巡航日志','1');

--（车辆查控）
insert into t_sys_function(function_code,menu_code,function_name,function_flag)
values('03010101','030101','过车监控','1');

insert into t_sys_function(function_code,menu_code,function_name,function_flag)
values('03020101','030201','车辆查询','1');

insert into t_sys_function(function_code,menu_code,function_name,function_flag)
values('03030101','030301','布控撤控','1');
insert into t_sys_function(function_code,menu_code,function_name,function_flag)
values('03030201','030302','假套牌车管理','1');
insert into t_sys_function(function_code,menu_code,function_name,function_flag)
values('03030301','030303','黄标车管理','1');
insert into t_sys_function(function_code,menu_code,function_name,function_flag)
values('03030401','030304','布控报警','1');

insert into t_sys_function(function_code,menu_code,function_name,function_flag)
values('03040101','030401','套配车分析','1');


insert into t_sys_function(function_code,menu_code,function_name,function_flag)
values('03050101','030501','危险驾驶TopN分析','1');
insert into t_sys_function(function_code,menu_code,function_name,function_flag)
values('03050201','030502','危险驾驶整体分析','1');
insert into t_sys_function(function_code,menu_code,function_name,function_flag)
values('03050301','030503','危险驾驶分项分析','1');
insert into t_sys_function(function_code,menu_code,function_name,function_flag)
values('03050401','030504','无牌车分析','1');
insert into t_sys_function(function_code,menu_code,function_name,function_flag)
values('03050501','030505','号牌识别率','1');

--（违法管理）
insert into t_sys_function(function_code,menu_code,function_name,function_flag)
values('04010101','040101','违法查询','1');

insert into t_sys_function(function_code,menu_code,function_name,function_flag)
values('04020101','040201','违法上传','1');

insert into t_sys_function(function_code,menu_code,function_name,function_flag)
values('04030101','040301','区间测速网','1');

--（信息发布）
insert into t_sys_function(function_code,menu_code,function_name,function_flag)
values('05010101','050101','信息发布','1');

--（设备运维）
insert into t_sys_function(function_code,menu_code,function_name,function_flag)
values('06010101','060101','设备监控','1');
insert into t_sys_function(function_code,menu_code,function_name,function_flag)
values('06010102','060101','故障查询','1');
insert into t_sys_function(function_code,menu_code,function_name,function_flag)
values('06010103','060101','维护单查询','1');


insert into t_sys_function(function_code,menu_code,function_name,function_flag)
values('06010201','060102','卡口传输轨迹','1');

insert into t_sys_function(function_code,menu_code,function_name,function_flag)
values('06020101','060201','设备备案管理','1');

insert into t_sys_function(function_code,menu_code,function_name,function_flag)
values('06020201','060202','卡口启用','1');
insert into t_sys_function(function_code,menu_code,function_name,function_flag)
values('06020202','060202','卡口删除','1');

insert into t_sys_function(function_code,menu_code,function_name,function_flag)
values('06020301','060203','视频','1');
insert into t_sys_function(function_code,menu_code,function_name,function_flag)
values('06020401','060204','固定测速','1');
insert into t_sys_function(function_code,menu_code,function_name,function_flag)
values('06020501','060205','区间测速','1');
insert into t_sys_function(function_code,menu_code,function_name,function_flag)
values('06020601','060206','移动测速','1');
insert into t_sys_function(function_code,menu_code,function_name,function_flag)
values('06020701','060207','电子警察','1');
insert into t_sys_function(function_code,menu_code,function_name,function_flag)
values('06020801','060208','交通事件检测','1');
insert into t_sys_function(function_code,menu_code,function_name,function_flag)
values('06020901','060209','气象监控','1');
insert into t_sys_function(function_code,menu_code,function_name,function_flag)
values('06021001','060210','诱导屏','1');
insert into t_sys_function(function_code,menu_code,function_name,function_flag)
values('06021101','060211','信号机','1');
insert into t_sys_function(function_code,menu_code,function_name,function_flag)
values('06021201','060212','违停','1');
insert into t_sys_function(function_code,menu_code,function_name,function_flag)
values('06021301','060213','大车占道','1');
insert into t_sys_function(function_code,menu_code,function_name,function_flag)
values('06021401','060214','隧道监控','1');
insert into t_sys_function(function_code,menu_code,function_name,function_flag)
values('06021501','060215','酒精检测仪','1');
insert into t_sys_function(function_code,menu_code,function_name,function_flag)
values('06021601','060216','点位管理','1');
insert into t_sys_function(function_code,menu_code,function_name,function_flag)
values('06021701','060217','厂商','1');
insert into t_sys_function(function_code,menu_code,function_name,function_flag)
values('06021801','060218','合同','1');

insert into t_sys_function(function_code,menu_code,function_name,function_flag)
values('06030101','060301','检定管理','1');

insert into t_sys_function(function_code,menu_code,function_name,function_flag)
values('06040101','060401','设备申报审批','1');
insert into t_sys_function(function_code,menu_code,function_name,function_flag)
values('06040201','060402','系统申报审批','1');

insert into t_sys_function(function_code,menu_code,function_name,function_flag)
values('06050101','060501','故障告警管理','1');
insert into t_sys_function(function_code,menu_code,function_name,function_flag)
values('06050201','060502','维护单管理','1');

insert into t_sys_function(function_code,menu_code,function_name,function_flag)
values('06060101','060601','监控中心服务器','1');
insert into t_sys_function(function_code,menu_code,function_name,function_flag)
values('06060201','060602','接入平台','1');
insert into t_sys_function(function_code,menu_code,function_name,function_flag)
values('06060301','060603','服务器类型','1');
insert into t_sys_function(function_code,menu_code,function_name,function_flag)
values('06060401','060604','后台服务监测','1');

--（统计分析）
insert into t_sys_function(function_code,menu_code,function_name,function_flag)
values('07010101','070101','设备接入率','1');
insert into t_sys_function(function_code,menu_code,function_name,function_flag)
values('07010201','070102','设备在线率','1');
insert into t_sys_function(function_code,menu_code,function_name,function_flag)
values('07010301','070103','设备检定率','1');
insert into t_sys_function(function_code,menu_code,function_name,function_flag)
values('07010401','070104','设备故障率','1');

insert into t_sys_function(function_code,menu_code,function_name,function_flag)
values('07020101','070201','单卡口分析','1');
insert into t_sys_function(function_code,menu_code,function_name,function_flag)
values('07020201','070202','道路卡口分析','1');
insert into t_sys_function(function_code,menu_code,function_name,function_flag)
values('07020301','070203','辖区卡口分析','1');
insert into t_sys_function(function_code,menu_code,function_name,function_flag)
values('07020401','070204','多点卡口分析','1');
insert into t_sys_function(function_code,menu_code,function_name,function_flag)
values('07020501','070205','节假日流量分析','1');

insert into t_sys_function(function_code,menu_code,function_name,function_flag)
values('07030101','070301','套牌车分析','1');
insert into t_sys_function(function_code,menu_code,function_name,function_flag)
values('07030201','070302','危险驾驶TopN分析','1');
insert into t_sys_function(function_code,menu_code,function_name,function_flag)
values('07030301','070303','危险驾驶整体分析','1');
insert into t_sys_function(function_code,menu_code,function_name,function_flag)
values('07030401','070304','危险驾驶分项分析','1');
insert into t_sys_function(function_code,menu_code,function_name,function_flag)
values('07030501','070305','无牌车分析','1');
insert into t_sys_function(function_code,menu_code,function_name,function_flag)
values('07030601','070306','号牌识别率','1');
insert into t_sys_function(function_code,menu_code,function_name,function_flag)
values('07030701','070307','区域案件对碰分析','1');
insert into t_sys_function(function_code,menu_code,function_name,function_flag)
values('07030801','070308','伴随车辆分析','1');
insert into t_sys_function(function_code,menu_code,function_name,function_flag)
values('07030901','070309','高危地域车辆分析','1');

--（系统管理）
insert into t_sys_function(function_code,menu_code,function_name,function_flag)
values('08010101','080101','机构信息','1');
insert into t_sys_function(function_code,menu_code,function_name,function_flag)
values('08010201','080102','用户信息','1');

insert into t_sys_function(function_code,menu_code,function_name,function_flag)
values('08020101','080201','角色权限','1');

insert into t_sys_function(function_code,menu_code,function_name,function_flag)
values('08030101','080301','系统代码','1');

insert into t_sys_function(function_code,menu_code,function_name,function_flag)
values('08040101','080401','操作日志','1');

insert into t_sys_function(function_code,menu_code,function_name,function_flag)
values('08050101','080501','道路信息','1');
insert into t_sys_function(function_code,menu_code,function_name,function_flag)
values('08050201','080502','路段信息','1');
insert into t_sys_function(function_code,menu_code,function_name,function_flag)
values('08050301','080503','路口信息','1');

insert into t_sys_function(function_code,menu_code,function_name,function_flag)
values('08060101','080601','交警机构','1');
insert into t_sys_function(function_code,menu_code,function_name,function_flag)
values('08060201','080602','服务资源','1');

insert into t_sys_function(function_code,menu_code,function_name,function_flag)
values('08070101','080701','文件下载','1');

insert into t_sys_function(function_code,menu_code,function_name,function_flag)
values('08080101','080801','节假日配置','1');

--（路况监控）
insert into t_sys_function(function_code,menu_code,function_name,function_flag)
values('09010101','090101','路况监控','1');

insert into t_sys_function(function_code,menu_code,function_name,function_flag)
values('09020101','090201','交通管制登记','1');
insert into t_sys_function(function_code,menu_code,function_name,function_flag)
values('09020201','090202','交通事件登记','1');
insert into t_sys_function(function_code,menu_code,function_name,function_flag)
values('09020301','090203','交通气象登记','1');

insert into t_sys_function(function_code,menu_code,function_name,function_flag)
values('09030101','090301','阀值设置','1');

insert into t_sys_function(function_code,menu_code,function_name,function_flag)
values('09040101','090401','流量监控段','1');



--t_sys_role_permission表:
--删除表数据：
delete from t_sys_role_permission;

insert into t_sys_role_permission(role_id,function_code) select '9a86c55384ac4f4eb0e848a28d6ea8d6',function_code from t_sys_function


