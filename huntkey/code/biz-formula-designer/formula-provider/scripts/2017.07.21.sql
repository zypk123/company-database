CREATE TABLE `source_system_record_mapping` (
  `recd_id` varchar(32) NOT NULL COMMENT '编号',
  `source_name` varchar(255) NOT NULL COMMENT '源名称',
  `source_mapping_id` varchar(32) NOT NULL COMMENT '源关联编号',
  `source_mapping_type` varchar(32) NOT NULL COMMENT '源关联类型：[1:属性公式、2:属性限值、3:关联条件]',
  `inside_id` varchar(32) NOT NULL COMMENT '内部编号',
  `inside_type` varchar(32) NOT NULL COMMENT '内部类型：[1:属性公式、2:属性限值、3:关联条件]',
  `isenable` tinyint(4) NOT NULL DEFAULT '1' COMMENT '是否可用: 1 可用， 0 不可用',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `adduser` varchar(32) NOT NULL DEFAULT 'N/A' COMMENT '创建人',
  `modtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `moduser` varchar(32) NOT NULL DEFAULT 'N/A' COMMENT '修改人',
  `acctid` int(11) NOT NULL DEFAULT '1' COMMENT '区域标识',
  PRIMARY KEY (`recd_id`),
  UNIQUE KEY `source_mapping_id` (`source_mapping_id`,`source_mapping_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='源系统记录映射表';