ALTER TABLE tac_condition_related ADD cndr_class1_name_en VARCHAR(255) COMMENT '类1英文名称';
ALTER TABLE tac_condition_related ADD cndr_class2_name_en VARCHAR(255) COMMENT '类2英文名称';
ALTER TABLE tac_condition_related ADD cndr_prop1_code VARCHAR(255) COMMENT '属性1的code';
ALTER TABLE tac_condition_related ADD cndr_prop2_code VARCHAR(255) COMMENT '属性2的code';

ALTER TABLE tac_property_related ADD prpl_class1_name_en VARCHAR(255) COMMENT '类1英文名称';
ALTER TABLE tac_property_related ADD prpl_prop1_code VARCHAR(255) COMMENT '属性1的code';
ALTER TABLE tac_property_related ADD prpl_class2_name_en VARCHAR(255) COMMENT '类2英文名称';
ALTER TABLE tac_property_related ADD prpl_prop2_code VARCHAR(255) COMMENT '属性2的code';

ALTER TABLE `tac_condition_related`
MODIFY COLUMN `cndr_prop_origin_code`  varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '属性1编码' AFTER `acctid`,
MODIFY COLUMN `cndr_prop2_origin_code`  varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '属性2编码' AFTER `cndr_prop_origin_code`,
MODIFY COLUMN `cndr_class1_name_en`  varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类1英文名称' AFTER `cndr_prop2_origin_code`,
MODIFY COLUMN `cndr_prop1_code`  varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '属性1的code' AFTER `cndr_class1_name_en`,
MODIFY COLUMN `cndr_class2_name_en`  varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类2英文名称' AFTER `cndr_prop1_code`,
MODIFY COLUMN `cndr_prop2_code`  varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '属性2的code' AFTER `cndr_class2_name_en`;

ALTER TABLE `source_system_record_mapping`
MODIFY COLUMN `source_mapping_id`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '源关联编号' AFTER `source_name`;

