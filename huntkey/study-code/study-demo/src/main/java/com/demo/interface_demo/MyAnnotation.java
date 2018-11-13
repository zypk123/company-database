package com.demo.interface_demo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) // 表示该注解可以在运行期被使用
@Target(ElementType.TYPE) // 表示注解的类型是使用在Type上，就是类上，同样可以换成的属性还有Field和Method
public @interface MyAnnotation {

    public String name();

    public String value();


}
