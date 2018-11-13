ALTER TABLE ftm_function_classify ADD fncc_classify_code varchar(255) NOT NULL COMMENT '分类编码';
ALTER TABLE ftm_function_classify ADD fucc_classify_binary blob NOT NULL COMMENT '编译后的class文件内容';