package com.huntkey.rx.base;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target(ElementType.FIELD)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface PropertyAnnotation {

	// 数据类型, 对应表列： edmp_data_type
	String dataType() default "";

	// 属性公式 对应表列：edmp_formula
	String fomula() default "";

	// 属性限值 对应表列：edmp_value_limit
	String limitFomula() default "";

	// 修改响应 对应表列：edmp_edmm_id，返回id
	String relatedBy() default "";

	// 属性类别 对应表列： edmp_value_type
	String dataClass() default "";

	// // 联动响应类别
	// // 是否保存联动记录

	// 是否缺省可见 对应表列：is_visible
	boolean isVisible() default false;

	// 是否对象呈现属性, 对应表列： is_character
	boolean isShow() default false;

	// 对应的类名称
	String className() default "";
	
	// 物理表对应的列名称
	String fieldName() default "";

}
