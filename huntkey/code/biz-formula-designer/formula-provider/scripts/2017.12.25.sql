alter table tac_condition_related add column const_type int(11) comment '常量类型 1-输入框,2-枚举 3-对象';
alter table tac_condition_related add column cndt_object_number varchar(1024) comment '对象编号';
alter table tpl_condition add column const_type int(11) comment '常量类型 1-输入框,2-枚举 3-对象';
alter table tpl_condition add column cndt_object_number varchar(1024) comment '对象编号';
alter table tpl_condition modify column cndt_value_class_id varchar(120) COMMENT '如果cndt的类型为class, 此列存该class的id' ;
alter table tpl_condition modify column cndt_prop_origin_code varchar(1024)  COMMENT '属性编码';