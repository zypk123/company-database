ALTER TABLE `tpl_condition`
ADD COLUMN `pre_varchar`  varchar(255) NULL COMMENT '辅助字段';

ALTER TABLE `tac_condition_related`
ADD COLUMN `link_clss_id`  varchar(255) NULL DEFAULT NULL COMMENT '相关类主键id 便于查询';

