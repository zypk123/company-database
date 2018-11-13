ALTER TABLE `tac_condition_related`
ADD COLUMN `cndr_type_flag`  int(11) NULL COMMENT '流程函数类型 1属性 2 函数',
ADD COLUMN `class_type_flag`  int(11) NULL COMMENT '属性类类型 1 类 2 相关类';
ADD COLUMN `link_class_or_class_id`  varchar(255) NULL DEFAULT NULL COMMENT '类或者关联类id';

ALTER TABLE `tfd_class_related`
ADD COLUMN `type`  int(1) NULL COMMENT 'type用来区分是class过来的还是formula  2class' ;
ALTER TABLE `tfd_class_related`
ADD COLUMN `clss_link_class_id`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '相关类id';




