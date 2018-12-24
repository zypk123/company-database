set define off
spool 24_3_功能菜单.log

prompt Deleting T_SYS_MENU...
delete from T_SYS_MENU;
commit;
prompt Deleting T_SYS_FUNCTION...
delete from T_SYS_FUNCTION;
commit;
prompt Loading T_SYS_FUNCTION...
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('08080101', '080801', '节假日配置', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('06020502', '060205', '区间测速停用', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('06020503', '060205', '区间测速报废', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('06020504', '060205', '区间测速删除', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('07030101', '070301', '套牌车分析', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('07030201', '070302', '危险驾驶车辆分析', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('07030301', '070303', '危险驾驶整体分析', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('07030401', '070304', '违法时空分析', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('07030501', '070305', '无牌车分析', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('07030601', '070306', '卡口号牌识别率分析', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('07030701', '070307', '区域案件对碰分析', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('07030801', '070308', '伴随车辆分析', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('07030901', '070309', '高危地域车辆分析', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('07031201', '070312', '新近出现车辆分析', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('06020105', '060201', '设备编辑', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('07031301', '070313', '违法嫌疑车辆分析', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('08090101', '080901', '短信管理', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('06010101', '060101', '设备监控', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('06010102', '060101', '故障查询', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('06010103', '060101', '维护单查询', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('07031001', '070310', '数据延迟分析', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('06020103', '060201', '设备停用', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('06020104', '060201', '设备报废', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('06020203', '060202', '卡口停用', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('06020204', '060202', '卡口报废', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('07020601', '070206', '实时流量分析', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('06020300', '060203', '区间测速查看', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('06020200', '060202', '卡口查看', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('06020100', '060201', '设备查看', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('06010100', '060101', '运行监控查看', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('07020701', '070207', '点位流量趋势分析', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('05010202', '050102', '发布记录管理', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('05010303', '050103', '内容库', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('06021901', '060219', '诱导屏规格', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('0001', '00', '通知公告', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('0002', '00', '下载专区', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('06020102', '060201', '设备删除', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('07031101', '070311', '车辆活动时段规律分析', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('06040201', '060402', '系统申报审批', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('01010101', '010101', '设备分布', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('06050301', '060503', '日常维护', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('01020101', '010201', '交通势态', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('01030101', '010301', '轨迹查询', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('02010101', '020101', '视频监控', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('02020101', '020201', '视频回放', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('02030101', '020301', '视频巡航', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('02040101', '020401', '巡航方案', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('02050101', '020501', '巡航日志', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('03010101', '030101', '过车监控', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('03020101', '030201', '车辆查询', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('03030101', '030301', '布控撤控', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('03030201', '030302', '假套牌车管理', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('03030301', '030303', '黄标车管理', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('03030401', '030304', '布控报警', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('04010101', '040101', '违法查询', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('04020101', '040201', '违法上传', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('04030101', '040301', '区间测速网', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('05010101', '050101', '信息发布', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('06010201', '060102', '卡口传输轨迹', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('06020101', '060201', '设备启用', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('06020201', '060202', '卡口启用', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('06020301', '060203', '视频', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('06020401', '060204', '固定测速', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('06020501', '060205', '区间测速启用', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('06020601', '060206', '移动测速', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('06020701', '060207', '电子警察', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('06020801', '060208', '交通事件检测', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('06020901', '060209', '气象监控', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('06021001', '060210', '诱导屏', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('06021101', '060211', '信号机', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('06021201', '060212', '违停', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('06021301', '060213', '大车占道', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('06021401', '060214', '隧道监控', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('06021501', '060215', '酒精检测仪', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('06021601', '060216', '点位管理', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('06021701', '060217', '厂商', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('06021801', '060218', '合同', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('06030101', '060301', '检定管理', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('06040101', '060401', '设备申报审批', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('06050101', '060501', '故障告警管理', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('06050201', '060502', '维护单管理', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('06060101', '060601', '监控中心服务器', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('06060201', '060602', '接入平台', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('06060301', '060603', '服务器类型', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('06060401', '060604', '后台服务监测', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('07010101', '070101', '设备接入率', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('07010201', '070102', '设备在线率', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('07010301', '070103', '设备检定率', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('07010401', '070104', '设备故障率', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('07020101', '070201', '单卡口分析', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('07020201', '070202', '道路卡口分析', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('07020301', '070203', '辖区卡口分析', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('07020401', '070204', '多点卡口分析', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('07020501', '070205', '节假日流量分析', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('08010101', '080101', '机构信息', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('08010201', '080102', '用户信息', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('08020101', '080201', '角色权限', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('08030101', '080301', '系统代码', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('08040101', '080401', '操作日志', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('08050101', '080501', '道路信息', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('08050201', '080502', '路段信息', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('08050301', '080503', '路口信息', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('08060101', '080601', '交警机构', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('08060201', '080602', '服务资源', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('08070101', '080701', '文件下载', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('09010101', '090101', '路况监控', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('09020101', '090201', '交通管制登记', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('09020201', '090202', '交通事件登记', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('09020301', '090203', '交通气象登记', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('09030101', '090301', '阀值设置', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('09040101', '090401', '流量监控段', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('06030301', '060303', '设备未登记', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('06020202', '060202', '卡口删除', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('06030201', '060302', '检定证书同步', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('0000', '00', '查看', '1', null, null);
insert into T_SYS_FUNCTION (function_code, menu_code, function_name, function_flag, function_dependency, service_id)
values ('08000001', '080000', '系统管理首页', '1', null, null);
commit;
prompt 116 records loaded
prompt Loading T_SYS_MENU...
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('070312', '070312', '新近出现车辆分析', 'tpls/passVehicle/strangeVehManager/strange-veh-search.html', '0703', '12', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('070313', '070313', '违法嫌疑车辆分析', 'tpls/passVehicle/suspectedIllegalManager/suspected-illegal-veh-analysis.html', '0703', '13', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('0606', '0606', '服务监控管理', null, '06', '6', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('060202', '060202', '卡口备案', 'tpls/deviceManage/deviceRecord/bayonet-manage-list.html', '0602', '02', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('060213', '060213', '大车占道', 'test', '0602', '13', '0', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('060218', '060218', '合同信息', 'tpls/deviceManage/informationManage/contract-manage-list.html', '0602', '16', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('070101', '070101', '设备接入率', 'tpls/deviceManage/deviceStatistics/device-access-rate.html', '0701', '1', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('0804', '0804', '操作日志', null, '08', '4', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('0807', '0807', '文件下载', null, '08', '7', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('080301', '080301', '系统代码', 'tpls/sysManagement/systemCode/sysCode-list.html', '0803', '1', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('0903', '0903', '阀值设置', null, '09', '3', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('0703', '0703', '大数据分析', null, '07', '3', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('070301', '070301', '套牌车分析', 'tpls/passVehicle/cloneCarManager/clone-car-analysis-list.html', '0703', '1', '0', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('070302', '070302', '危险驾驶车辆分析', 'tpls/passVehicle/dangerCarManager/danger-car-query-list.html', '0703', '2', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('070303', '070303', '危险驾驶整体分析', 'tpls/passVehicle/dangerCarManager/danger-car-whole-list.html', '0703', '3', '0', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('070304', '070304', '违法时空分析', 'tpls/passVehicle/highRateSpaceTimeManager/highRate-space-time-anaylsis.html', '0703', '4', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('070305', '070305', '无牌车分析', 'tpls/passVehicle/nullPlateManager/nullPlate-car-query-list.html', '0703', '5', '0', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('070306', '070306', '卡口号牌识别率分析', 'tpls/passVehicle/plateRecognitionManagement/plate-recognition-list.html', '0703', '6', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('070307', '070307', '区域案件对碰分析', 'tpls/passVehicle/collisionManager/case-collision-analyse.html', '0703', '7', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('070308', '070308', '伴随车辆分析', 'tpls/passVehicle/accompanyCarManager/accompany-query-all.html', '0703', '8', '0', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('070309', '070309', '高危地域车辆分析', 'tpls/passVehicle/dangerAreaManager/dangerarea-vehicle-analyse.html', '0703', '9', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('070310', '070310', '卡口数据延迟分析', 'tpls/passVehicle/dataLatencyManager/data-lantency-anylsis.html', '0703', '10', '0', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('00', '00', '首页', null, 'root', '0', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('060219', '060219', '诱导屏规格', 'tpls/informationDelivery/inducedScreenRelease/specifications-config-list.html', '0602', '19', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('070311', '070311', '车辆活动时段规律分析', 'tpls/passVehicle/activeRegularManager/veh-active-regular-analysis.html', '0703', '11', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('01', '01', '电子地图', null, 'root', '1', '1', null, 'themes/default/images/dzdt.png');
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('02', '02', '视频监控', null, 'root', '2', '1', null, 'themes/default/images/spjk.png');
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('03', '03', '车辆查控', null, 'root', '4', '1', null, 'themes/default/images/clck.png');
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('04', '04', '违法管理', null, 'root', '5', '1', null, 'themes/default/images/wfgl.png');
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('05', '05', '信息发布', null, 'root', '6', '1', null, 'themes/default/images/xxfb.png');
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('06', '06', '设备运维', null, 'root', '7', '1', null, 'themes/default/images/sbyw.png');
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('07', '07', '统计分析', null, 'root', '8', '1', null, 'themes/default/images/tjfx.png');
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('08', '08', '系统管理', null, 'root', '9', '1', null, 'themes/default/images/xtgl.png');
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('0809', '0809', '短信管理', null, '08', '9', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('0101', '0101', '设备分布', null, '01', '1', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('0102', '0102', '交通态势', null, '01', '2', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('0103', '0103', '轨迹查询', null, '01', '3', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('010101', '010101', '设备分布', 'tpls/deviceManage/deviceMonitor/device-moniter-onGIS.html', '0101', '1', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('010201', '010201', '交通态势', 'tpls/trafficMonitor/trafficState/traffic-situation.html', '0102', '1', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('010301', '010301', '轨迹查询', 'tpls/passVehicle/vehicleQuery/vehicle-path-query.html', '0103', '1', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('0201', '0201', '视频监控', null, '02', '1', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('0202', '0202', '视频回放', null, '02', '2', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('0203', '0203', '视频巡航', null, '02', '3', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('0204', '0204', '巡航方案', null, '02', '4', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('0205', '0205', '巡航日志', null, '02', '5', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('020101', '020101', '视频监控', 'tpls/trafficMonitor/videoMonitor/realTimeMonitor.html', '0201', '1', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('020201', '020201', '视频回放', 'tpls/trafficMonitor/videoMonitor/playback.html', '0202', '1', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('020301', '020301', '视频巡航', 'tpls/trafficMonitor/videoMonitor/cruise.html', '0203', '1', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('020401', '020401', '巡航方案', 'tpls/trafficMonitor/videoMonitor/cruise-plan-config.html', '0204', '1', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('020501', '020501', '巡航日志', 'tpls/trafficMonitor/videoMonitor/cruise-log-list.html', '0205', '1', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('0301', '0301', '过车监控', null, '03', '1', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('0302', '0302', '车辆查询', null, '03', '2', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('0303', '0303', '车辆布控', null, '03', '3', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('030101', '030101', '过车监控', 'tpls/passVehicle/passMonitor/pass-vehicle-monitor.html', '0301', '1', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('030201', '030201', '车辆查询', 'tpls/passVehicle/vehicleQuery/vehicle-query-all.html', '0302', '1', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('030301', '030301', '布控撤控', 'tpls/passVehicle/controlManager/control-uncontrol-manager.html', '0303', '1', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('030302', '030302', '假套牌车管理', 'tpls/passVehicle/controlManager/false-deck-manager.html', '0303', '2', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('030303', '030303', '黄标车管理', 'tpls/passVehicle/controlManager/yellow-label-car-manager.html', '0303', '3', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('030304', '030304', '布控报警', 'tpls/passVehicle/alarmManager/control-manager-list.html', '0303', '4', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('0401', '0401', '违法查询', null, '04', '1', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('0402', '0402', '违法上传', null, '04', '2', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('0403', '0403', '区间测速网', null, '04', '3', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('040101', '040101', '违法查询', 'tpls/violationMgr/ViolationManager/ViolationQuery.html', '0401', '1', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('040201', '040201', '违法上传', 'tpls/violationMgr/ViolationManager/UploadQuery.html', '0402', '1', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('040301', '040301', '区间测速网', 'test', '0403', '1', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('0501', '0501', '信息发布', null, '05', '1', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('050101', '050101', '信息发布', 'test', '0501', '1', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('0601', '0601', '设备监控', null, '06', '1', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('0602', '0602', '备案管理', null, '06', '2', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('0603', '0603', '检定管理', null, '06', '3', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('0604', '0604', '申报审批', null, '06', '4', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('0605', '0605', '维护管理', null, '06', '5', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('060101', '060101', '设备运行监控', 'tpls/deviceManage/deviceMonitor/device-status-monitor.html', '0601', '1', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('060102', '060102', '卡口传输轨迹', 'tpls/deviceManage/systemMaintainManage/data-transmission-path-list.html', '0601', '2', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('060201', '060201', '设备管理备案', 'tpls/deviceManage/deviceConfig/device-sys-list.html', '0602', '01', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('060203', '060203', '视频', 'test', '0602', '03', '0', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('060204', '060204', '固定测速', 'test', '0602', '04', '0', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('060205', '060205', '区间测速', 'tpls/deviceManage/deviceConfig/interval-system-device-list.html', '0602', '05', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('060206', '060206', '移动测速', 'test', '0602', '06', '0', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('060207', '060207', '电子警察', 'test', '0602', '07', '0', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('060208', '060208', '交通事件检测', 'test', '0602', '08', '0', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('060209', '060209', '气象监控', 'test', '0602', '09', '0', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('060210', '060210', '诱导屏', 'test', '0602', '10', '0', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('060211', '060211', '信号机', 'test', '0602', '11', '0', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('060212', '060212', '违停', 'test', '0602', '12', '0', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('060214', '060214', '隧道监控', 'test', '0602', '14', '0', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('060215', '060215', '酒精检测仪', 'test', '0602', '15', '0', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('060216', '060216', '点位管理', 'tpls/sysManagement/roadNetworkMessage/point-message.html', '0602', '18', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('060217', '060217', '厂商信息', 'tpls/deviceManage/informationManage/company-manage-list.html', '0602', '17', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('060301', '060301', '设备检定管理', 'tpls/deviceManage/deviceCertification/certification-info-list.html', '0603', '1', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('060401', '060401', '设备申报审批', 'tpls/deviceManage/deviceSystemApplicationEndorsement/deviceApplicationEndorsement/device-build-list.html', '0604', '1', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('060402', '060402', '系统申报审批', 'tpls/deviceManage/deviceSystemApplicationEndorsement/systemApplicationEndorsement/sys-application-list.html', '0604', '2', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('060501', '060501', '故障告警管理', 'tpls/deviceManage/deviceMaintenance/fault-alarm-manager.html', '0605', '1', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('060502', '060502', '维护单管理', 'tpls/deviceManage/deviceMaintenance/maintenance-manage.html', '0605', '2', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('060601', '060601', '监控中心服务器', 'tpls/deviceManage/informationManage/monitoring-center-manage-list.html', '0606', '1', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('060602', '060602', '接入平台', 'tpls/deviceManage/accessPlatform/access-platform-list.html', '0606', '2', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('060603', '060603', '服务器类型', 'tpls/deviceManage/informationManage/server-type-manage-list.html', '0606', '3', '0', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('060604', '060604', '后台服务监测', 'test', '0606', '4', '0', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('0701', '0701', '设备考核', null, '07', '1', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('0702', '0702', '交通流量统计', null, '07', '2', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('070102', '070102', '设备在线率', 'tpls/deviceManage/deviceStatistics/device-online-rate.html', '0701', '2', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('070103', '070103', '设备检定率', 'tpls/deviceManage/deviceStatistics/device-certification-rate.html', '0701', '3', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('070104', '070104', '设备故障率', 'tpls/deviceManage/deviceStatistics/device-fault-rate.html', '0701', '4', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('070201', '070201', '单卡口分析', 'tpls/statisticsAnalysis/flowAnalysis/single-site-flow-analysis.html', '0702', '1', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('070202', '070202', '道路卡口分析', 'tpls/statisticsAnalysis/flowAnalysis/road-flow-analysis.html', '0702', '2', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('070203', '070203', '辖区卡口分析', 'tpls/statisticsAnalysis/flowAnalysis/district-flow-analysis.html', '0702', '3', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('070204', '070204', '多点卡口分析', 'tpls/statisticsAnalysis/flowAnalysis/multi-site-flow-analysis.html', '0702', '4', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('070205', '070205', '节假日流量分析', 'tpls/statisticsAnalysis/flowAnalysis/holidayflowAnalysis.html', '0702', '5', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('0801', '0801', '机构用户', null, '08', '1', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('0802', '0802', '角色权限', null, '08', '2', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('0803', '0803', '系统代码', null, '08', '3', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('0805', '0805', '路网信息', null, '08', '5', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('0806', '0806', '交通基础信息', null, '08', '6', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('080101', '080101', '机构信息', 'tpls/sysManagement/org/org-list.html', '0801', '1', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('080102', '080102', '用户信息', 'tpls/sysManagement/user/user-list.html', '0801', '2', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('080201', '080201', '角色权限', 'tpls/sysManagement/rolePermission/role-permission-message.html', '0802', '1', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('080401', '080401', '操作日志', 'tpls/sysManagement/operationLog/operation-log-list.html', '0804', '1', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('080501', '080501', '道路信息', 'tpls/sysManagement/roadNetworkMessage/road-message-list.html', '0805', '1', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('080502', '080502', '路段信息', 'tpls/sysManagement/roadNetworkMessage/road-section-message-list.html', '0805', '2', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('080503', '080503', '路口信息', 'tpls/sysManagement/roadNetworkMessage/crossing-message-list.html', '0805', '3', '0', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('080601', '080601', '交警机构', 'tpls/sysManagement/traffic/traffic-police.html', '0806', '1', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('080602', '080602', '服务资源', 'test', '0806', '2', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('080701', '080701', '文件下载', 'tpls/sysManagement/downLoad/download-file.html', '0807', '1', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('080901', '080901', '短信管理', 'tpls/sysManagement/msgManage/message-list.html', '0809', '1', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('0902', '0902', '交通事件管理', null, '09', '2', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('0904', '0904', '流量监控段', null, '09', '4', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('090101', '090101', '路况监控', 'tpls/trafficMonitor/trafficState/traffic-situation.html', '0901', '1', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('090201', '090201', '交通管制登记', 'tpls/trafficMonitor/manualEvent/traffic-control.html', '0902', '1', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('090202', '090202', '交通事件登记', 'tpls/trafficMonitor/manualEvent/manual-event.html', '0902', '2', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('090203', '090203', '交通气象登记', 'tpls/trafficMonitor/manualEvent/manual-weather.html', '0902', '3', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('090301', '090301', '阀值设置', 'tpls/trafficMonitor/trafficWeather/weather-threshold-setting.html', '0903', '1', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('090401', '090401', '流量监控段', 'tpls/sysManagement/roadNetworkMessage/region-message-list.html', '0904', '1', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('080801', '080801', '节假日配置', 'tpls/sysManagement/festival/festival-setting.html', '0808', '1', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('0808', '0808', '节日设定', null, '08', '8', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('060302', '060302', '检定证书同步', 'tpls/deviceManage/deviceCertification/certification-synchronize-list.html', '0603', '2', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('060303', '060303', '设备未登记', 'tpls/deviceManage/deviceCertification/device-unregistered-list.html', '0603', '3', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('070206', '070206', '实时流量分析', 'tpls/statisticsAnalysis/flowAnalysis/real-time-pass-analysis.html', '0702', '6', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('0800', '0800', '系统管理概况', null, '08', '0', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('080000', '080000', '系统管理首页', 'tpls/sysManagement/home/sys-home.html', '0800', '1', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('070207', '070207', '点位流量趋势分析', 'tpls/statisticsAnalysis/sectionflowAnalysis/site-flow-trend-analysis.html', '0702', '7', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('060503', '060503', '日常维护', 'tpls/deviceManage/deviceMaintenance/manitain-daily-manage.html', '0605', '3', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('050103', '050103', '内容库', 'tpls/informationDelivery/contentLibraryManage/content-library-manage-list.html', '0501', '3', '1', null, null);
insert into T_SYS_MENU (menu_code, sys_config_id, menu_name, target_url, parent_code, sort_index, menu_enable_flag, remark, menu_image_url)
values ('050102', '050102', '发布记录管理', 'tpls/informationDelivery/releaseInfoManage/release-info-manage-list.html', '0501', '2', '1', null, null);
commit;
prompt 143 records loaded
prompt Done.

spool off

exit;

