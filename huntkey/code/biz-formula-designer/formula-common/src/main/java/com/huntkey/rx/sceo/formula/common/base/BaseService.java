package com.huntkey.rx.sceo.formula.common.base;

import com.huntkey.rx.commons.utils.uuid.UuidCreater;
import com.huntkey.rx.sceo.formula.common.util.Constant;

import java.lang.reflect.Method;
import java.util.Date;

/**
 * Service基类
 *
 * @author zhangyu
 * @create 2017-07-04 9:49
 **/
public abstract class BaseService<M extends BaseEntity> {

    /**
     * 保存方法之前调用该方法，为实体ID赋值uuid
     *
     * @param entity
     */
    public void preSave(M entity) {
        String rex = "^set[a-zA-Z]{4}Id$";
        // 通过反射对象得到方法
        Method[] methods = entity.getClass().getDeclaredMethods();
        // 遍历所有方法
        for (Method method : methods) {
            // 得到方法名
            String methodNname = method.getName();
            // 正则匹配方法
            if (methodNname.matches(rex)) {
                //如果匹配
                try {
                    // 调用方法
                    method.invoke(entity, UuidCreater.uuid());
                    break;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    /**
     * 新增默认保存数据
     *
     * @param entity
     */
    public void saveSetting(M entity) {
        this.preSave(entity);
        entity.setAddtime(new Date());
        entity.setIsenable((byte) 1);
        entity.setAdduser(Constant.ADDUSER);
        entity.setModtime(new Date());
        entity.setModuser(Constant.MODUSER);
    }

    /**
     * 修改默认保存数据
     *
     * @param entity
     */
    public void updateSetting(M entity) {
        entity.setModtime(new Date());
        entity.setModuser(Constant.MODUSER);
    }


}
