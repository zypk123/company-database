package com.huntkey.rx.purchase.provider.base;

import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.edm.entity.ResourceEntity;
import com.huntkey.rx.purchase.common.exception.ApplicationException;
import com.huntkey.rx.purchase.common.model.base.CurrentSessionEntity;
import com.huntkey.rx.purchase.common.util.JsonUtils;
import com.huntkey.rx.purchase.common.util.NullUtils;
import com.huntkey.rx.purchase.provider.service.BizFormService;
import com.huntkey.rx.sceo.orm.service.OrmService;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;
import java.util.Date;

/**
 *  资源类实体类的参考实现
 *
 * @author yaoss
 */
public abstract class BaseResourceService<T extends ResourceEntity> implements IBaseResourceService {

    private Class<T> entityClass;

    public BaseResourceService(){
        entityClass =  (Class <T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Autowired
    public OrmService ormService;
    @Autowired
    public BizFormService bizFormService;

    /**
     * 设置单据类的entity的EdmClass
     * @return
     */
    public abstract String getEntityEdmClassName();

    /**
     * 在save之前，可以自行设置一些其它实体类属性，如果没有，该方法不重写。
     * @param entity
     */
    public abstract void beforeSaveSetEntityProperty(T entity);

    @Override
    public Result saveResource(JSONObject params) {
        String json = params.toJSONString();
        Object paramsObj = JSONObject.parse(json);
        // 驼峰转下划线
        JsonUtils.camel2UnderLine(paramsObj);
        T entity = JSONObject.parseObject(JSONObject.toJSONString(paramsObj), entityClass);
        Result result = new Result();

        String id = entity.getId();
        beforeSaveSetEntityProperty(entity);
        try{
            CurrentSessionEntity sessionEntity = bizFormService.getCurrentSessionInfo();
            if(StringUtil.isNullOrEmpty(id)) {
                entity.setEdmd_ente(sessionEntity.getEnterpriseId());
                entity.setEdmd_class(NullUtils.valueOf(getEntityEdmClassName()));
                entity.setCretime(new Date());
                entity.setCreuser(sessionEntity.getEmployeeId());
                entity.setModtime(entity.getCretime());
                entity.setModuser(sessionEntity.getEmployeeId());
                ormService.insertSelective(entity);
            }else{
                entity.setModtime(new Date());
                entity.setModuser(sessionEntity.getEmployeeId());
                ormService.updateSelective(entity);
            }
        }catch (Exception e) {
            e.printStackTrace();
            throw new ApplicationException(Result.RECODE_ERROR, e.getMessage());
        }

        return result;
    }



}
