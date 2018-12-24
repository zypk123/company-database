package cy.its.service.util;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
* @Title: TableColumn.java 
* @Package cy.its.service.util 
* @Description:数据库对应字段
* @author lil@cychina.cn
* @date 2015年12月15日 下午5:06:51 
* @version V1.0   
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TableColumn {
	
	//对应字段值
	public String name()  default "className";
	
}
