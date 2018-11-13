
DROP DATABASE IF EXISTS `biz_formula`;
CREATE DATABASE `biz_formula` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE biz_formula;

-- ----------------------------
--  Table structure for `ftm_function_classify`
-- ----------------------------
DROP TABLE IF EXISTS `ftm_function_classify`;
CREATE TABLE `ftm_function_classify` (
  `fncc_id` varchar(32) NOT NULL COMMENT '编号',
  `fncc_classify_name` varchar(255) DEFAULT NULL COMMENT '分类名称',
  `fncc_classify_desc` varchar(1024) DEFAULT NULL COMMENT '分类描述',
  `fncc_state` varchar(255) DEFAULT NULL COMMENT '分类状态 1 无效 0 有效',
  `isenable` tinyint(4) NOT NULL DEFAULT '1' COMMENT '是否可用: 1 可用， 0 不可用',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `adduser` varchar(32) NOT NULL DEFAULT 'N/A' COMMENT '创建人',
  `modtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `moduser` varchar(32) NOT NULL DEFAULT 'N/A' COMMENT '修改人',
  `acctid` int(11) NOT NULL DEFAULT '1' COMMENT '区域标识',
  PRIMARY KEY (`fncc_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='函数分类表';

-- ----------------------------
--  Table structure for `tac_condition_related`
-- ----------------------------
DROP TABLE IF EXISTS `tac_condition_related`;
CREATE TABLE `tac_condition_related` (
  `cndr_id` varchar(32) NOT NULL COMMENT '编号',
  `cndr_prop_related_id` varchar(32) DEFAULT NULL COMMENT '属性关联编号',
  `cndr_prop_related` varchar(255) DEFAULT NULL COMMENT '关联属性',
  `cndr_seq` int(10) DEFAULT NULL COMMENT '序号',
  `cndr_prop` varchar(255) DEFAULT NULL COMMENT '属性1',
  `cndr_prop2` varchar(255) DEFAULT NULL COMMENT '属性2',
  `cndr_operate` varchar(255) DEFAULT NULL COMMENT '操作符',
  `cndr_value` varchar(255) DEFAULT NULL COMMENT '值',
  `cndr_value_type` char(10) DEFAULT NULL COMMENT '值类型 enum[constant, variant]',
  `isenable` tinyint(4) NOT NULL DEFAULT '1' COMMENT '是否可用: 1 可用， 0 不可用',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `adduser` varchar(32) NOT NULL DEFAULT 'N/A' COMMENT '创建人',
  `modtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `moduser` varchar(32) NOT NULL DEFAULT 'N/A' COMMENT '修改人',
  `acctid` int(11) NOT NULL DEFAULT '1' COMMENT '区域标识',
  `cndr_prop_origin_code` varchar(64) NOT NULL COMMENT '属性1编码',
  `cndr_prop2_origin_code` varchar(64) NOT NULL COMMENT '属性2编码',
  PRIMARY KEY (`cndr_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='属性关联条件表';

-- ----------------------------
--  Table structure for `tac_property_related`
-- ----------------------------
DROP TABLE IF EXISTS `tac_property_related`;
CREATE TABLE `tac_property_related` (
  `prpl_id` varchar(32) NOT NULL DEFAULT '' COMMENT '编号',
  `prpl_class_related_from` varchar(255) NOT NULL COMMENT '关联开始属性',
  `prpl_class_related_to` varchar(255) NOT NULL COMMENT '关联结束属性',
  `prpl_condition_name` varchar(255) NOT NULL COMMENT '条件名称',
  `prpl_condition_formula` varchar(1024) NOT NULL COMMENT '条件公式',
  `prpl_condition_desc` varchar(255) NOT NULL COMMENT '条件描述',
  `isenable` tinyint(4) NOT NULL DEFAULT '1' COMMENT '是否可用: 1 可用， 0 不可用',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `adduser` varchar(32) NOT NULL DEFAULT 'N/A' COMMENT '创建人',
  `modtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `moduser` varchar(32) NOT NULL DEFAULT 'N/A' COMMENT '修改人',
  `acctid` int(11) NOT NULL DEFAULT '1' COMMENT '区域标识',
  PRIMARY KEY (`prpl_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='属性关联表';

-- ----------------------------
--  Table structure for `tdf_user_behavior`
-- ----------------------------
DROP TABLE IF EXISTS `tdf_user_behavior`;
CREATE TABLE `tdf_user_behavior` (
  `usrb_id` varchar(32) NOT NULL COMMENT '编号',
  `usrb_user_id` varchar(30) NOT NULL COMMENT '用户编号',
  `usrb_recent_use` blob COMMENT '最近最多使用的公式',
  `isenable` tinyint(4) NOT NULL DEFAULT '1' COMMENT '是否可用: 1 可用， 0 不可用',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `adduser` varchar(32) NOT NULL DEFAULT 'N/A' COMMENT '创建人',
  `modtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `moduser` varchar(32) NOT NULL DEFAULT 'N/A' COMMENT '修改人',
  `acctid` int(11) NOT NULL DEFAULT '1' COMMENT '区域标识',
  PRIMARY KEY (`usrb_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='用户操作公式行为表';

-- ----------------------------
--  Table structure for `tfd_class_related`
-- ----------------------------
DROP TABLE IF EXISTS `tfd_class_related`;
CREATE TABLE `tfd_class_related` (
  `clss_id` varchar(32) NOT NULL DEFAULT '' COMMENT '编号',
  `clss_class_id` varchar(32) DEFAULT NULL COMMENT '类id',
  `clss_formula_id` varchar(32) DEFAULT NULL COMMENT '关联公式编号',
  `clss_seq` int(10) DEFAULT NULL COMMENT '顺序',
  `clss_class_related_name` varchar(255) DEFAULT NULL COMMENT '相关类名称',
  `clss_alias_name` varchar(255) DEFAULT NULL COMMENT '相关类别名',
  `clss_edmc_name_en` varchar(255) DEFAULT NULL COMMENT 'edm类英文名称',
  `clss_condition_name` varchar(255) DEFAULT NULL COMMENT '条件名称',
  `clss_condition_formula` varchar(1024) DEFAULT NULL COMMENT '条件公式',
  `clss_condition_desc` varchar(255) DEFAULT NULL COMMENT '条件描述',
  `isenable` tinyint(4) NOT NULL DEFAULT '1' COMMENT '是否可用: 1 可用， 0 不可用',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `adduser` varchar(32) NOT NULL DEFAULT 'N/A' COMMENT '创建人',
  `modtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `moduser` varchar(32) NOT NULL DEFAULT 'N/A' COMMENT '修改人',
  `acctid` int(11) NOT NULL DEFAULT '1' COMMENT '区域标识',
  PRIMARY KEY (`clss_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='公式相关类表';

-- ----------------------------
--  Table structure for `tfd_formula`
-- ----------------------------
DROP TABLE IF EXISTS `tfd_formula`;
CREATE TABLE `tfd_formula` (
  `frmu_id` varchar(32) NOT NULL COMMENT '编号',
  `frmu_formula_content` varchar(10240) DEFAULT NULL,
  `frmu_formula_style` varchar(10240) DEFAULT NULL,
  `isenable` tinyint(4) NOT NULL DEFAULT '1' COMMENT '是否可用: 1 可用， 0 不可用',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `adduser` varchar(32) NOT NULL DEFAULT 'N/A' COMMENT '创建人',
  `modtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `moduser` varchar(32) NOT NULL DEFAULT 'N/A' COMMENT '修改人',
  `acctid` int(11) NOT NULL DEFAULT '1' COMMENT '区域标识',
  PRIMARY KEY (`frmu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='公式表';

-- ----------------------------
--  Table structure for `tfm_function_compiled`
-- ----------------------------
DROP TABLE IF EXISTS `tfm_function_compiled`;
CREATE TABLE `tfm_function_compiled` (
  `func_id` varchar(32) NOT NULL COMMENT '编号',
  `func_fun_id` varchar(32) NOT NULL COMMENT '函数编号',
  `func_class_full_name` varchar(255) NOT NULL COMMENT '类全名[包含类包路径]',
  `func_class_binary` blob NOT NULL COMMENT '编译后的class文件内容',
  `func_fun_source` blob NOT NULL COMMENT '类文件的源码',
  `isenable` tinyint(4) NOT NULL DEFAULT '1' COMMENT '是否可用: 1 可用， 0 不可用',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `adduser` varchar(32) NOT NULL DEFAULT 'N/A' COMMENT '创建人',
  `modtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `moduser` varchar(32) NOT NULL DEFAULT 'N/A' COMMENT '修改人',
  `acctid` int(11) NOT NULL DEFAULT '1' COMMENT '区域标识',
  PRIMARY KEY (`func_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='函数编译表';

-- ----------------------------
--  Table structure for `tfm_function_definition`
-- ----------------------------
DROP TABLE IF EXISTS `tfm_function_definition`;
CREATE TABLE `tfm_function_definition` (
  `fund_id` varchar(32) NOT NULL COMMENT '编号',
  `fund_fun_name` varchar(255) NOT NULL COMMENT '函数名称',
  `fund_fun_catagory_id` varchar(32) NOT NULL COMMENT '函数分类',
  `fund_state` char(8) NOT NULL COMMENT 'enum[prohibit, inusing]',
  `fund_fun_desc` varchar(255) NOT NULL COMMENT '函数描述',
  `fund_modify_remarks` varchar(255) DEFAULT NULL COMMENT '变更摘要',
  `isenable` tinyint(4) NOT NULL DEFAULT '1' COMMENT '是否可用: 1 可用， 0 不可用',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `adduser` varchar(32) NOT NULL DEFAULT 'N/A' COMMENT '创建人',
  `modtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `moduser` varchar(32) NOT NULL DEFAULT 'N/A' COMMENT '修改人',
  `acctid` int(11) NOT NULL DEFAULT '1' COMMENT '区域标识',
  PRIMARY KEY (`fund_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='函数定义表';

-- ----------------------------
--  Table structure for `tpl_condition`
-- ----------------------------
DROP TABLE IF EXISTS `tpl_condition`;
CREATE TABLE `tpl_condition` (
  `cndt_id` varchar(32) NOT NULL COMMENT '编号',
  `cndt_prop_clss_id` varchar(32) DEFAULT NULL COMMENT '属性或者相关类编号',
  `cndt_seq` int(10) DEFAULT NULL COMMENT '序号',
  `cndt_prop` varchar(255) DEFAULT NULL COMMENT '属性',
  `cndt_operate` varchar(255) DEFAULT NULL COMMENT '操作符',
  `cndt_value` varchar(255) DEFAULT NULL COMMENT '值',
  `cndt_value_type` char(10) DEFAULT NULL COMMENT '值类型 enum[constant, variant]',
  `isenable` tinyint(4) NOT NULL DEFAULT '1' COMMENT '是否可用: 1 可用， 0 不可用',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `adduser` varchar(32) NOT NULL DEFAULT 'N/A' COMMENT '创建人',
  `modtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `moduser` varchar(32) NOT NULL DEFAULT 'N/A' COMMENT '修改人',
  `acctid` int(11) NOT NULL DEFAULT '1' COMMENT '区域标识',
  `cndt_value_class_id` varchar(32) DEFAULT NULL COMMENT '如果cndt的类型为class, 此列存该class的id',
  `cndt_prop_origin_code` varchar(64) NOT NULL COMMENT '属性编码',
  `cndt_value_origin_code` varchar(64) NOT NULL COMMENT '值编码',
  PRIMARY KEY (`cndt_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='条件表';

-- ----------------------------
--  Table structure for `tpl_property_limit`
-- ----------------------------
DROP TABLE IF EXISTS `tpl_property_limit`;
CREATE TABLE `tpl_property_limit` (
  `prpr_id` varchar(32) NOT NULL COMMENT '编号',
  `prpr_prop_mata` varchar(255) NOT NULL COMMENT '属性元数据',
  `prpr_prop_display` varchar(255) NOT NULL COMMENT '属性表现值',
  `prpr_condition_formula` varchar(1024) DEFAULT NULL COMMENT '条件公式',
  `prpr_condition_name` varchar(255) DEFAULT NULL COMMENT '条件名称',
  `prpr_condition_desc` varchar(255) DEFAULT NULL COMMENT '条件描述',
  `isenable` tinyint(4) NOT NULL DEFAULT '1' COMMENT '是否可用: 1 可用， 0 不可用',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `adduser` varchar(32) NOT NULL DEFAULT 'N/A' COMMENT '创建人',
  `modtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `moduser` varchar(32) NOT NULL DEFAULT 'N/A' COMMENT '修改人',
  `acctid` int(11) NOT NULL DEFAULT '1' COMMENT '区域标识',
  PRIMARY KEY (`prpr_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='属性限值表';

-- ----------------------------
--  Table structure for `tvm_locale_variant_mapping`
-- ----------------------------
DROP TABLE IF EXISTS `tvm_locale_variant_mapping`;
CREATE TABLE `tvm_locale_variant_mapping` (
  `lclv_id` varchar(32) NOT NULL COMMENT '编号',
  `lclv_formula_id` varchar(32) DEFAULT NULL COMMENT '公式编号',
  `lclv_var_id` varchar(32) DEFAULT NULL COMMENT '变量编号',
  `isenable` tinyint(4) NOT NULL DEFAULT '1' COMMENT '是否可用: 1 可用， 0 不可用',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `adduser` varchar(32) NOT NULL DEFAULT 'N/A' COMMENT '创建人',
  `modtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `moduser` varchar(32) NOT NULL DEFAULT 'N/A' COMMENT '修改人',
  `acctid` int(11) NOT NULL DEFAULT '1' COMMENT '区域标识',
  PRIMARY KEY (`lclv_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='公式局部变量映射表';

-- ----------------------------
--  Table structure for `tvm_system_variant_mapping`
-- ----------------------------
DROP TABLE IF EXISTS `tvm_system_variant_mapping`;
CREATE TABLE `tvm_system_variant_mapping` (
  `syst_id` varchar(32) NOT NULL COMMENT '编号',
  `syst_formula_id` varchar(32) DEFAULT NULL COMMENT '公式编号',
  `syst_var_id` varchar(32) DEFAULT NULL COMMENT '变量编号',
  `isenable` tinyint(4) NOT NULL DEFAULT '1' COMMENT '是否可用: 1 可用， 0 不可用',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `adduser` varchar(32) NOT NULL DEFAULT 'N/A' COMMENT '创建人',
  `modtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `moduser` varchar(32) NOT NULL DEFAULT 'N/A' COMMENT '修改人',
  `acctid` int(11) NOT NULL DEFAULT '1' COMMENT '区域标识',
  PRIMARY KEY (`syst_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='公式系统变量映射表';

-- ----------------------------
--  Table structure for `tvm_variant`
-- ----------------------------
DROP TABLE IF EXISTS `tvm_variant`;
CREATE TABLE `tvm_variant` (
  `vrnt_id` varchar(32) NOT NULL COMMENT '编号',
  `vrnt_var_name` varchar(255) DEFAULT NULL COMMENT '变量名',
  `vrnt_var_desc` varchar(255) DEFAULT NULL COMMENT '变量描述',
  `formula_id` varchar(32) DEFAULT NULL COMMENT '公式id',
  `vrnt_var_type` char(5) DEFAULT NULL COMMENT '变量类型 enum[math, text, date, set, other]',
  `vrnt_var_scope` char(6) DEFAULT NULL COMMENT '变量范围 enum[system, locale]',
  `vrnt_formula_id` varchar(32) DEFAULT NULL COMMENT '变量公式',
  `vrnt_state` char(8) DEFAULT NULL COMMENT '变量状态 enum[prohibit, inusing]',
  `vrnt_modify_remarks` varchar(255) DEFAULT NULL COMMENT '变更摘要',
  `isenable` tinyint(4) NOT NULL DEFAULT '1' COMMENT '是否可用: 1 可用， 0 不可用',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `adduser` varchar(32) NOT NULL DEFAULT 'N/A' COMMENT '创建人',
  `modtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `moduser` varchar(32) NOT NULL DEFAULT 'N/A' COMMENT '修改人',
  `acctid` int(11) NOT NULL DEFAULT '1' COMMENT '区域标识',
  PRIMARY KEY (`vrnt_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='变量表';
