package com.huntkey.rx.sceo.orm.common.util;

import com.huntkey.rx.base.BaseEntity;
import com.huntkey.rx.base.PropertyBaseEntity;
import com.huntkey.rx.sceo.orm.common.constant.EdmSysColumnName;
import com.huntkey.rx.sceo.orm.common.model.OrmColumn;
import com.huntkey.rx.sceo.orm.common.model.OrmColumnFactory;
import com.huntkey.rx.sceo.orm.common.type.SQLCurdEnum;
import com.huntkey.rx.sceo.orm.common.type.TableEnum;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by licj on 2017/11/17.
 * 持久化工具类
 */
public class PersistentUtil {

    //
//    public final static void main(String[] args) {
//        TableEnum tableEnum = PersistentUtil.getEntityTableType(AreaEntity.class);
//        System.out.println(AreaEntity.class.getName() + ":" + tableEnum.getDesc());
//        tableEnum = PersistentUtil.getEntityTableType(LinkdetailEntity.class);
//        System.out.println(LinkdetailEntity.class.getName() + ":" + tableEnum.getDesc());
//        tableEnum = PersistentUtil.getEntityTableType(RempRempSkillSetaEntity.class);
//        System.out.println(RempRempSkillSetaEntity.class.getName() + ":" + tableEnum.getDesc());
//        try{
//           Class entity = getEntityClassByEdmName("employee");
//           System.out.println(entity.getName());
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//
//        return;
//    }

    /**
     * 判断是否link able
     *
     * @param entityClazz
     * @return
     */
    @Deprecated
    public static boolean isLinkTable(Class<? extends BaseEntity> entityClazz) {
        return EdmUtil.isLinkTable(entityClazz);
    }

    /**
     * 判断实体类对应的表类型
     *
     * @param enityClazz
     * @return
     */
    @Deprecated
    public static TableEnum getEntityTableType(Class<? extends BaseEntity> enityClazz) {
        return EdmUtil.getEntityTableType(enityClazz);
    }

    /**
     * 获取Edm类名
     *
     * @param edmClass
     * @return
     * @throws Exception
     */
    @Deprecated
    public static String getEdmClassName(Class<? extends BaseEntity> edmClass) throws Exception {
        return EdmUtil.getEdmClassName(edmClass);
    }

    /**
     * 获取从属表的表名
     *
     * @param entityClazz
     * @param edmClazz
     * @return
     * @throws Exception
     */
    @Deprecated
    public static String getDependentEntityTableName(Class<? extends BaseEntity> entityClazz,
                                                     Class<? extends BaseEntity> edmClazz) throws Exception {
        return EdmUtil.getDependentEntityTableName(entityClazz, edmClazz);
    }

    /**
     * 获取属性集的表名
     *
     * @param entityClazz
     * @return
     * @throws Exception
     */
    @Deprecated
    public static String getAttributeTableName(Class<? extends BaseEntity> entityClazz) throws Exception {
        return EdmUtil.getEntityTableName(entityClazz);
    }

    /**
     * 获取实体表名
     *
     * @param entityClazz
     * @return
     * @throws Exception
     */
    @Deprecated
    public static String getEntityTableName(Class<? extends BaseEntity> entityClazz) throws Exception {
        return EdmUtil.getEntityTableName(entityClazz);
    }

    /**
     * link 表组装规则
     *
     * @param edmClazz edm 的类
     * @return
     * @throws Exception
     */
    @Deprecated
    public static String getLinkTableName(Class<? extends BaseEntity> edmClazz) throws Exception {
        return EdmUtil.getLinkTableName(edmClazz);
    }

    /**
     * 根据字段获取列名
     *
     * @param field
     * @return
     */
    public static String getColumnName(Field field) {
        return field.getName().toLowerCase();
    }

    /**
     * 获取主键值
     *
     * @param t pojo对象
     * @return
     */
    public static <T> Object getPrimaryValue(T t) throws Exception {
        return FieldUtil.getFieldValue(t, FieldUtil.getPrimaryField(t.getClass()));
    }


    /**
     * 获取所有列名
     *
     * @param clazz
     * @return
     */
    public static List<String> getAllColumnNames(Class<? extends BaseEntity> clazz) {
        List<String> columnNames = new ArrayList<String>();
        List<Field> fields = FieldUtil.getPersistentFields(clazz);
        for (Field field : fields) {
            columnNames.add(PersistentUtil.getColumnName(field));
        }
        return columnNames;
    }


    /**
     * 获取空值外的所有列名-字段值映射（columnName,fieldValue）
     *
     * @param t
     * @param isContainsNullValue 是否包含空值字段，true 包含，false 不包含
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> Map<String, Object> getColumnValueMapping(T t, boolean isContainsNullValue)
            throws Exception {
        Map<String, Object> mapping = new LinkedHashMap<String, Object>();
        List<Field> fields = FieldUtil.getPersistentFields(t.getClass());
        for (Field field : fields) {
            Object fieldValue = FieldUtil.getFieldValue(t, field);
            if (!isContainsNullValue) {
                if (fieldValue != null) {
                    mapping.put(PersistentUtil.getColumnName(field), fieldValue);
                }
            } else {
                mapping.put(PersistentUtil.getColumnName(field), fieldValue);
            }

        }
        return mapping;
    }

    /**
     * 获取所有的字段，包括空值
     *
     * @param t
     * @param isContainsNullValue 是否包含空值字段，true 包含，false 不包含
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> List<OrmColumn> getAllSQLColumns(T t, boolean isContainsNullValue)
            throws Exception {
        List<OrmColumn> sqlColumns = new ArrayList<OrmColumn>();
        List<Field> fields = FieldUtil.getPersistentFields(t.getClass());
        for (Field field : fields) {
            if (!isContainsNullValue) {
                Object fieldValue = FieldUtil.getFieldValue(t, field);
                if (fieldValue != null) {
                    sqlColumns.add(OrmColumnFactory.createSQLColumn(t, field));
                }
            } else {
                sqlColumns.add(OrmColumnFactory.createSQLColumn(t, field));
            }
        }
        return sqlColumns;
    }


    public static <T> List<OrmColumn> getSQLColumns(T t, boolean isContainSysNotNullField,
                                                    boolean isContainsNullValue) throws Exception {
        List<OrmColumn> sqlColumns = new ArrayList<OrmColumn>();
        List<Field> fields = FieldUtil.getPersistentFields(t.getClass());
        //排除不能为空的字段
        Field primaryField = FieldUtil.getPrimaryField(t.getClass());
        Field creUserField = FieldUtil.getCreuserField(t.getClass());
        Field creTimeField = FieldUtil.getCretimeField(t.getClass());
        if (!isContainSysNotNullField) {
            if (primaryField != null) {
                fields.remove(primaryField);
            }
            if (creUserField != null) {
                fields.remove(creUserField);
            }
            if (creTimeField != null) {
                fields.remove(creTimeField);
            }
        }

        for (Field field : fields) {
            Object fieldValue = FieldUtil.getFieldValue(t, field);
            if (fieldValue == null) {
                if (isContainsNullValue) {
                    sqlColumns.add(OrmColumnFactory.createSQLColumn(t, field));
                }
            } else {
                sqlColumns.add(OrmColumnFactory.createSQLColumn(t, field));
            }
        }
        return sqlColumns;
    }


    /**
     * 列名-值 键值对转为pojo,若mapping为空,则返回null对象
     *
     * @param mapping
     * @param clazz
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> T parseToBean(Map<String, Object> mapping, Class<T> clazz) throws Exception {
        if (mapping == null || mapping.size() == 0) {
            return null;
        }
        T t = clazz.newInstance();
        for (Map.Entry<String, Object> entry : mapping.entrySet()) {
            String columnName = entry.getKey().toLowerCase();
            if (EdmSysColumnName.ISDEL.equals(columnName)) {
                continue;
            }

            Field field = FieldUtil.getFieldByColumnName(clazz, columnName);
            if (field != null) {
                FieldUtil.setFieldValue(t, field, entry.getValue());
            }
        }
        return t;
    }

    /**
     * mapList 转换为实体类
     *
     * @param entityClazz
     * @param mapList
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> List<T> mapListToBeanList(Class<T> entityClazz, List<Map<String, Object>> mapList) throws Exception {
        List<T> rsBeanList = new ArrayList<T>();
        if (mapList != null && mapList.size() != 0) {
            for (Map<String, Object> mapping : mapList) {
                rsBeanList.add(PersistentUtil.parseToBean(mapping, entityClazz));
            }
        }
        return rsBeanList;
    }

    /**
     * 添加属性集类的特有字段
     *
     * @param mainClazz   主表类类型
     * @param edmEntity   属性集的父对象
     * @param t           属性集对象
     * @param sqlCurdEnum sql 操作的类型
     * @param <T>
     * @return
     * @throws Exception
     */
    @Deprecated
    public static <T extends PropertyBaseEntity> T setPropertyBaseEntitySysColumns(
            Class<? extends BaseEntity> mainClazz, BaseEntity edmEntity, T t, SQLCurdEnum sqlCurdEnum)
            throws Exception {
        return EdmUtil.setPropertyBaseEntitySysColumns(mainClazz, edmEntity, t, sqlCurdEnum);
    }

    /**
     * 添加属性集类的特有字段
     *
     * @param mainClazz            所属主类
     * @param edmEntity            属性集的上级对象
     * @param propertyBaseEntities 属性集
     * @param sqlCurdEnum          sql的操作类型
     * @param <T>
     * @return 上级对象传递给属性集对象的数据
     * @throws Exception
     */
    @Deprecated
    public static <T extends PropertyBaseEntity> List<T> setPropertyBaseEntitiesSysColumns(
            Class<? extends BaseEntity> mainClazz, BaseEntity edmEntity, List<T> propertyBaseEntities, SQLCurdEnum sqlCurdEnum)
            throws Exception {
        return EdmUtil.setPropertyBaseEntitiesSysColumns(mainClazz, edmEntity, propertyBaseEntities, sqlCurdEnum);
    }


    /**
     * 改成驼峰式
     *
     * @param edmcNameEn 类英文名
     * @return
     */
    @Deprecated
    public static String convertClassName(String edmcNameEn) {
        return EdmUtil.convertClassName(edmcNameEn);
    }

    /**
     * 获取edm实体类
     *
     * @param edmcNameEn edm 实体类在modeler的英文名称
     * @return
     * @throws ClassNotFoundException
     */
    @Deprecated
    public static Class<? extends BaseEntity> getEntityClassByEdmName(String edmcNameEn)
            throws ClassNotFoundException {
        return EdmUtil.getEntityClassByEdmName(edmcNameEn);
    }

}
