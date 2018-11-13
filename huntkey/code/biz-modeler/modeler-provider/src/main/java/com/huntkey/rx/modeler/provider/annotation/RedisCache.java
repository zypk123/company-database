package com.huntkey.rx.modeler.provider.annotation;

import java.lang.annotation.*;

/**
 * Created by yexin on 2017/10/14.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface RedisCache {
    Class type();
}
