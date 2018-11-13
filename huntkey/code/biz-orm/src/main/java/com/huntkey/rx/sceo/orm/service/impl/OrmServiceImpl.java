package com.huntkey.rx.sceo.orm.service.impl;

import com.github.pagehelper.PageHelper;
import com.huntkey.rx.base.BaseEntity;
import com.huntkey.rx.base.LinkdetailEntity;
import com.huntkey.rx.commons.utils.rest.Pagination;
import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.commons.utils.uuid.UuidCreater;
import com.huntkey.rx.edm.constant.EdmProperty;
import com.huntkey.rx.edm.entity.EdmEntity;
import com.huntkey.rx.sceo.orm.common.constant.Constant;
import com.huntkey.rx.sceo.orm.common.constant.EdmSysColumnName;
import com.huntkey.rx.sceo.orm.common.model.OrmColumn;
import com.huntkey.rx.sceo.orm.common.model.OrmParam;
import com.huntkey.rx.sceo.orm.common.type.SQLCurdEnum;
import com.huntkey.rx.sceo.orm.common.type.SQLOperatorEnum;
import com.huntkey.rx.sceo.orm.common.type.SQLRelationEnum;
import com.huntkey.rx.sceo.orm.common.util.EdmUtil;
import com.huntkey.rx.sceo.orm.common.util.OrmAccessUtil;
import com.huntkey.rx.sceo.orm.common.util.PersistentUtil;
import com.huntkey.rx.sceo.orm.dao.OrmMapper;
import com.huntkey.rx.sceo.orm.exception.ORMException;
import com.huntkey.rx.sceo.orm.service.OrmService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.*;

/**
 * Created by licj on 2017/11/17.
 */
@Service
public class OrmServiceImpl implements OrmService {

    //删除时XML 用到的关键字
    protected final static String DELETE_TIME = "delTime";
    protected final static String SQL = "sql";

    private static Logger logger = LoggerFactory.getLogger(OrmServiceImpl.class);

    @Autowired
    private OrmMapper ormMapper;

    //确保一些系统值
    protected String ensureId(String entityID) {
        return (entityID == null || "".equals(entityID)) ? UuidCreater.uuid() : entityID;
    }

    /**
     * 标识查询条件的数据是否有效的 XML 字符串
     *
     * @param isLogicDelete
     * @return
     */
    protected String getOrmParamValidXml(boolean isLogicDelete) {
        return EdmSysColumnName.ISDEL + SQLOperatorEnum.EQ.getOperatorWithSpace() + (isLogicDelete ? "1 " : "0 ");
    }

    /**
     * 根据sql 的操作模式对系统字段进行设置
     *
     * @param t           操作实体对象
     * @param sqlCurdEnum sql的Curd的操作的类型
     * @param <T>
     */
    protected <T extends BaseEntity> void ensureSysColumns(T t, SQLCurdEnum sqlCurdEnum) throws Exception {
        Date date = new Date();
        boolean isChangeData = false;
        //根据操作模式设置数据库的系统字段
        if (SQLCurdEnum.INSERT.equals(sqlCurdEnum)) {
            String id = ensureId(t.getId());
            String user = t.getCreuser();
            t.setCretime(date);
            t.setModtime(date);
            t.setModuser(user);
            t.setId(id);
            isChangeData = true;
        } else if (SQLCurdEnum.UPDATE.equals(sqlCurdEnum)) {
            t.setModtime(date);
            isChangeData = true;
        }

        //根据通用约定对Edm类中的一些字段进行默认数据设置
        if (isChangeData) {
            //EdmEntity 分支设置某字段值
            Class<? extends BaseEntity> enityClazz = t.getClass();
            if (EdmEntity.class.isAssignableFrom(enityClazz)) {
                String edmClassName = EdmUtil.getEdmClassName(enityClazz);
                EdmUtil.setFieldValue(t, EdmProperty.EDMD_CLASS, edmClassName);
            }
        }
        return;
    }

    /**
     * 根据id获取对象
     *
     * @param entityClazz
     * @param entityID
     * @param <T>
     * @return
     * @throws Exception
     */
    @Override
    public <T extends BaseEntity> T load(Class<T> entityClazz, Serializable entityID) throws Exception {
        OrmAccessUtil.accessNull(entityID);
        String tableName = EdmUtil.getEntityTableName(entityClazz);
        List<String> columnNames = PersistentUtil.getAllColumnNames(entityClazz);

        Map<String, Object> param = new HashMap<String, Object>();
        param.put(Constant.TABLE_NAME, tableName);//获取表名
        param.put(Constant.PRIMARY_VALUE, entityID.toString());//获取主键值
        param.put(Constant.COLUMN_NAMES, columnNames);//获取列名
        return PersistentUtil.parseToBean(ormMapper.selectByPrimaryKey(param), entityClazz);
    }

    /**
     * 插入数据（包含空值）
     *
     * @param t
     * @param <T>
     * @return
     * @throws Exception
     */
    @Override
    public <T extends BaseEntity> Serializable insert(T t) throws Exception {
        OrmAccessUtil.accessNull(t);
        ensureSysColumns(t, SQLCurdEnum.INSERT);
        Class<? extends BaseEntity> clazz = t.getClass();
        String tableName = EdmUtil.getEntityTableName(clazz);
        List<OrmColumn> ormColumns = PersistentUtil.getAllSQLColumns(t, true);

        Map<String, Object> param = new HashMap<String, Object>();
        param.put(Constant.TABLE_NAME, tableName);
        param.put(Constant.ORM_COLUMNS, ormColumns);
        return (1 == ormMapper.insert(param) ? t.getId() : null);
    }

    /**
     * 插入数据（不包含null）
     *
     * @param t
     * @param <T>
     * @return
     * @throws Exception
     */
    @Override
    public <T extends BaseEntity> Serializable insertSelective(T t) throws Exception {
        OrmAccessUtil.accessNull(t);
        ensureSysColumns(t, SQLCurdEnum.INSERT);
        Map<String, Object> param = new HashMap<String, Object>();
        Class entityClazz = t.getClass();
        String tableName = EdmUtil.getEntityTableName(entityClazz);
        List<OrmColumn> ormColumns = PersistentUtil.getAllSQLColumns(t, false);
        param.put(Constant.TABLE_NAME, tableName);
        param.put(Constant.ORM_COLUMNS, ormColumns);
        return (1 == ormMapper.insert(param) ? t.getId() : null);
    }

    /**
     * 批量插入(包含null)
     *
     * @param list
     * @param <T>
     * @return
     * @throws Exception
     */
    @Override
    public <T extends BaseEntity> int insert(List<T> list) throws Exception {
        OrmAccessUtil.accessNull(list);
        if (list.size() == 0) {
            return 0;
        }
        Class entityClazz = list.get(0).getClass();
        String tableName = EdmUtil.getEntityTableName(entityClazz);
        List<String> columnNames = PersistentUtil.getAllColumnNames(entityClazz);
        List<List<OrmColumn>> dataList = new ArrayList<List<OrmColumn>>(list.size() + 1);
        Date date = new Date();
        for (T t : list) {
            ensureSysColumns(t, SQLCurdEnum.INSERT);
            t.setCretime(date);
            t.setModtime(date);
            List<OrmColumn> SQLColumns = PersistentUtil.getAllSQLColumns(t, true);
            dataList.add(SQLColumns);
        }

        Map<String, Object> param = new HashMap<String, Object>();
        param.put(Constant.TABLE_NAME, tableName);
        param.put(Constant.COLUMN_NAMES, columnNames);
        param.put(Constant.DATA_LIST, dataList);
        return ormMapper.insertBatch(param);
    }

    /**
     * 根据ID 逻辑删除单条数据
     *
     * @param clazz
     * @param entityID
     * @param <T>
     * @return
     * @throws Exception
     */
    @Override
    public <T extends BaseEntity> int delete(Class<T> clazz, Serializable entityID) throws Exception {
        OrmAccessUtil.accessNull(entityID);
        if (null == entityID) {
            return 0;
        }
        String tableName = EdmUtil.getEntityTableName(clazz);
        Map<String, Object> whereParams = new HashMap<String, Object>();
        whereParams.put(EdmSysColumnName.ID, entityID.toString());
        String whereExp = OrmParam.formatCondtionXML(EdmSysColumnName.ID, SQLOperatorEnum.EQ, EdmSysColumnName.ID)
                + SQLRelationEnum.AND.getRelationWithSpace()
                + this.getOrmParamValidXml(false);
        Map<String, Object> param = new HashMap<String, Object>();
        param.put(Constant.TABLE_NAME, tableName);
        param.put(Constant.WHERE_PARAM, whereParams);
        param.put(Constant.WHERE_EXP, whereExp);
        param.put(DELETE_TIME, new Date());

        return ormMapper.deleteByCondition(param);
    }

    /**
     * 根据条件删除数据
     *
     * @param clazz    泛型类型
     * @param ormParam 删除条件
     * @param <T>
     * @return 返回删除记录行数
     */
    @Override
    public <T extends BaseEntity> int delete(Class<T> clazz, OrmParam ormParam) throws Exception {
        OrmAccessUtil.accessNull(ormParam);
        String tableName = EdmUtil.getEntityTableName(clazz);
        String whereExp = "";
        if (ormParam.getWhereExp() != null) {
            whereExp = ormParam.getWhereExp()
                    + SQLRelationEnum.AND.getRelationWithSpace();
        }
        whereExp = whereExp + this.getOrmParamValidXml(false);

        Map<String, Object> param = new HashMap<String, Object>();
        param.put(Constant.TABLE_NAME, tableName);
        param.put(Constant.WHERE_PARAM, ormParam.getWhereParams());
        param.put(Constant.WHERE_EXP, whereExp);
        param.put(DELETE_TIME, new Date());
        return ormMapper.deleteByCondition(param);
    }

    /**
     * 根据id更新数据（包含空值）
     *
     * @param t   更新对象
     * @param <T>
     * @return 返回更新记录
     * @throws Exception
     */
    @Override
    public <T extends BaseEntity> int update(T t) throws Exception {
        OrmAccessUtil.accessNull(t);
        ensureSysColumns(t, SQLCurdEnum.UPDATE);
        Class<? extends BaseEntity> entityClazz = t.getClass();
        String tableName = EdmUtil.getEntityTableName(entityClazz);
        List<OrmColumn> ormColumns = PersistentUtil.getSQLColumns(t, false, true);
        Object primaryValue = PersistentUtil.getPrimaryValue(t);

        Map<String, Object> param = new HashMap<String, Object>();
        param.put(Constant.TABLE_NAME, tableName);
        param.put(Constant.PRIMARY_VALUE, primaryValue);
        param.put(Constant.ORM_COLUMNS, ormColumns);
        return ormMapper.updateByPrimaryKey(param);
    }

    /**
     * 根据id更新数据（不包含空值）
     *
     * @param t   更新对象
     * @param <T>
     * @return 更行记录行数
     * @throws Exception
     */
    @Override
    public <T extends BaseEntity> int updateSelective(T t)
            throws Exception {
        OrmAccessUtil.accessNull(t);
        ensureSysColumns(t, SQLCurdEnum.UPDATE);
        Class<? extends BaseEntity> entityClazz = t.getClass();
        String tableName = EdmUtil.getEntityTableName(entityClazz);
        List<OrmColumn> ormColumns = PersistentUtil.getSQLColumns(t, false, false);
        Object primaryValue = PersistentUtil.getPrimaryValue(t);

        Map<String, Object> param = new HashMap<String, Object>();
        param.put(Constant.TABLE_NAME, tableName);
        param.put(Constant.PRIMARY_VALUE, primaryValue);
        param.put(Constant.ORM_COLUMNS, ormColumns);
        return ormMapper.updateByPrimaryKey(param);
    }

    /**
     * 根据条件更新（不包含空值）
     *
     * @param t        更新数据对象
     * @param ormParam 更新条件参数
     * @param <T>
     * @return 更新记录行数
     * @throws Exception
     */
    @Override
    public <T extends BaseEntity> int updateSelective(T t, OrmParam ormParam)
            throws Exception {
        OrmAccessUtil.accessNull(t, ormParam);
        ensureSysColumns(t, SQLCurdEnum.UPDATE);
        String tableName = EdmUtil.getEntityTableName(t.getClass());
        Map<String, Object> map = PersistentUtil.getColumnValueMapping(t, false);
        ormParam.setColumnValueMapping(map);

        Map<String, Object> param = new HashMap<String, Object>();
        param.put(Constant.TABLE_NAME, tableName);
        param.put(Constant.COLUMN_VALUE_MAPPING, ormParam.getColumnValueMapping());
        param.put(Constant.WHERE_PARAM, ormParam.getWhereParams());
        param.put(Constant.WHERE_EXP, ormParam.getWhereExp());
        return ormMapper.updateByConditionSelective(param);
    }

    /**
     * 根据查询条件查询结果，以pojo返回(不支持SQL统计函数查询)
     *
     * @param clazz    实体类类型
     * @param ormParam 查询条件参数
     * @param <T>
     * @return
     * @throws Exception
     */
    @Override
    public <T extends BaseEntity> List<T> selectBeanList(Class<T> clazz, OrmParam ormParam)
            throws Exception {
        OrmAccessUtil.accessNull(ormParam);
        if (ormParam.isHasAggregateColumn()){
            throw new ORMException("It is not allowed to use the function to get the AggregateColumn");
        }
//        //不允许group by 查询使用selectBeanList函数
//        if ( 0 < ormParam.getGroupByColumns().size() || null != ormParam.getHavingExp()) {
//            throw new ORMException("It is not allowed to use the function named selectBeanList for select by group");
//        }
        List<Map<String, Object>> list = selectMapList(clazz, ormParam);
        return PersistentUtil.mapListToBeanList(clazz, list);
    }

    /**
     * 根据条件查出指定的列名数据
     *
     * @param clazz    实体类类型
     * @param ormParam 查询条件参数
     * @param <T>
     * @return
     * @throws Exception
     */
    @Override
    public <T extends BaseEntity> List<Map<String, Object>> selectMapList(Class<T> clazz, OrmParam ormParam)
            throws Exception {
        OrmAccessUtil.accessNull(ormParam);
        String tableName = EdmUtil.getEntityTableName(clazz);
        List<String> columns = ormParam.getColumns();
        if (null == columns || columns.size() == 0) {
            ormParam.setColumns(PersistentUtil.getAllColumnNames(clazz));
        }
        String whereExp = "";
        if (ormParam.getWhereExp() != null) {
            whereExp = ormParam.getWhereExp()
                    + SQLRelationEnum.AND.getRelationWithSpace();
        }
        whereExp = whereExp + this.getOrmParamValidXml(ormParam.isInvalid());

        Map<String, Object> param = new HashMap<String, Object>();
        param.put(Constant.TABLE_NAME, tableName);
        param.put(Constant.DISTINCT, ormParam.isDistinct());
        param.put(Constant.COLUMN_NAMES, ormParam.getColumns());
        param.put(Constant.WHERE_PARAM, ormParam.getWhereParams());
        param.put(Constant.WHERE_EXP, whereExp);
        param.put(Constant.GROUP_COLUMN_NAMES, ormParam.getGroupByColumns());
        param.put(Constant.HAVING_EXP, ormParam.getHavingExp());
        param.put(Constant.ORDER_EXP, ormParam.getOrderExp());
//        if (ormParam.getPageSize() != null && ormParam.getPageNo() != null) {
//            Map<String, Integer> page = new HashMap<String, Integer>();
//            page.put(Constant.PAGE_SIZE, ormParam.getPageSize());
//            page.put(Constant.START_PAGE, (ormParam.getPageNo() - 1) * ormParam.getPageSize());
//            param.put(Constant.PAGE, page);
//        }
        return ormMapper.selectByCondition(param);
    }

    /**
     * 根据条件统计数量
     *
     * @param entityClazz 实体类的类型
     * @param ormParam    查询条件参数
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T extends BaseEntity> long count(Class<T> entityClazz, OrmParam ormParam) throws Exception {
        OrmAccessUtil.accessNull(ormParam);
        String tableName = EdmUtil.getEntityTableName(entityClazz);
        String whereExp = "";
        if (ormParam.getWhereExp() != null) {
            whereExp = ormParam.getWhereExp()
                    + SQLRelationEnum.AND.getRelationWithSpace();
        }
        whereExp = whereExp + this.getOrmParamValidXml(ormParam.isInvalid());

        Map<String, Object> param = new HashMap<String, Object>();
        param.put(Constant.TABLE_NAME, tableName);
        param.put(Constant.WHERE_PARAM, ormParam.getWhereParams());
        param.put(Constant.WHERE_EXP, whereExp);
        return ormMapper.countByCondtion(param);
    }

    //############################################################################################################################
    //###################################################主类从属表###############################################################

    /**
     * 根据pid 获取属性集的有效数据
     *
     * @param pid:              属性集表的外键,对应主对象的id
     * @param entityClazz：属性集类型
     * @param mainClazz：主类
     * @param <T>
     * @return
     * @throws Exception
     */
    @Override
    public <T extends BaseEntity> List<T> getAttrubuteSetByPID(Serializable pid, Class<T> entityClazz, Class<? extends BaseEntity> mainClazz) throws Exception {
        OrmAccessUtil.accessNull(pid);
        String tableName = EdmUtil.getEntityTableName(entityClazz);
        List<String> columns = PersistentUtil.getAllColumnNames(entityClazz);
        //组装条件参数
        Map<String, Object> whereParams = new HashMap<String, Object>();
        whereParams.put(EdmSysColumnName.PID, pid);
        whereParams.put(EdmSysColumnName.CLASSNAME, EdmUtil.getEdmClassName(mainClazz));
        //组装条件
        String whereExp = OrmParam.formatCondtionXML(EdmSysColumnName.PID, SQLOperatorEnum.EQ, EdmSysColumnName.PID)
                + SQLRelationEnum.AND.getRelationWithSpace()
                + OrmParam.formatCondtionXML(EdmSysColumnName.CLASSNAME, SQLOperatorEnum.EQ, EdmSysColumnName.CLASSNAME)
                + SQLRelationEnum.AND.getRelationWithSpace()
                + this.getOrmParamValidXml(false);
        //组装查询map
        Map<String, Object> param = new HashMap<String, Object>();
        param.put(Constant.TABLE_NAME, tableName);
        param.put(Constant.COLUMN_NAMES, columns);
        param.put(Constant.WHERE_PARAM, whereParams);
        param.put(Constant.WHERE_EXP, whereExp);
        List<Map<String, Object>> rsMapList = ormMapper.selectByCondition(param);
        return PersistentUtil.mapListToBeanList(entityClazz, rsMapList);
    }

    //########################################A gorgeous dividing line##################################################
    //联动属性表相关操作

    /**
     * 加载Link 表的数据
     *
     * @param edmClazz
     * @param entityID
     * @return
     * @throws Exception
     */
    @Override
    public LinkdetailEntity loadLink(Class<? extends BaseEntity> edmClazz, Serializable entityID)
            throws Exception {
        OrmAccessUtil.accessNull(entityID);
        String tableName = EdmUtil.getLinkTableName(edmClazz);
        List<String> columnNames = PersistentUtil.getAllColumnNames(LinkdetailEntity.class);

        Map<String, Object> param = new HashMap<String, Object>();
        param.put(Constant.TABLE_NAME, tableName);//获取表名
        param.put(Constant.PRIMARY_VALUE, entityID.toString());//获取主键值
        param.put(Constant.COLUMN_NAMES, columnNames);//获取列名
        return PersistentUtil.parseToBean(ormMapper.selectByPrimaryKey(param), LinkdetailEntity.class);
    }

    /**
     * 新增记录集数据
     *
     * @param edmClazz
     * @param entity
     * @return
     * @throws Exception
     */
    @Override
    public Serializable insertLink(Class<? extends BaseEntity> edmClazz, LinkdetailEntity entity)
            throws Exception {
        OrmAccessUtil.accessNull(entity);
        ensureSysColumns(entity, SQLCurdEnum.INSERT);
        String tableName = EdmUtil.getLinkTableName(edmClazz);
        List<OrmColumn> ormColumns = PersistentUtil.getAllSQLColumns(entity, true);

        Map<String, Object> param = new HashMap<String, Object>();
        param.put(Constant.TABLE_NAME, tableName);
        param.put(Constant.ORM_COLUMNS, ormColumns);
        return (1 == ormMapper.insert(param)) ? entity.getId() : null;
    }

    /**
     * 查询link 表
     *
     * @param edmClazz
     * @param ormParam
     * @return
     * @throws Exception
     */
    @Override
    public List<LinkdetailEntity> selectBeanListLink(Class<? extends BaseEntity> edmClazz, OrmParam ormParam)
            throws Exception {
        OrmAccessUtil.accessNull(ormParam);
        String tableName = EdmUtil.getLinkTableName(edmClazz);
        List<String> columns = ormParam.getColumns();
        if (null == columns || columns.size() == 0) {
            ormParam.setColumns(PersistentUtil.getAllColumnNames(LinkdetailEntity.class));
        }

        String whereExp = "";
        if (ormParam.getWhereExp() != null) {
            whereExp = ormParam.getWhereExp()
                    + SQLRelationEnum.AND.getRelationWithSpace();
        }
        whereExp = whereExp + this.getOrmParamValidXml(ormParam.isInvalid());

        Map<String, Object> param = new HashMap<String, Object>();
        param.put(Constant.TABLE_NAME, tableName);
        param.put(Constant.COLUMN_NAMES, ormParam.getColumns());
        param.put(Constant.WHERE_PARAM, ormParam.getWhereParams());
        param.put(Constant.WHERE_EXP, whereExp);
        param.put(Constant.ORDER_EXP, ormParam.getOrderExp());
        //分页查询使用selectPagedBeanList 接口
//        if (ormParam.getPageSize() != null && ormParam.getPageNo() != null) {
//            Map<String, Integer> page = new HashMap<String, Integer>();
//            page.put(Constant.PAGE_SIZE, ormParam.getPageSize());
//            page.put(Constant.START_PAGE, (ormParam.getPageNo() - 1) * ormParam.getPageSize());
//            param.put(Constant.PAGE, page);
//        }
        return PersistentUtil.mapListToBeanList(LinkdetailEntity.class, ormMapper.selectByCondition(param));
    }

    /**
     * 分页接口
     *
     * @param entityClazz 实体类
     * @param ormParam    条件参数
     * @param <T>
     * @return
     * @throws Exception
     */
    @Override
    public <T extends BaseEntity> Pagination<T> selectPagedBeanList(Class<T> entityClazz, OrmParam ormParam) throws Exception {
        OrmAccessUtil.accessNull(ormParam);
        if (!StringUtil.isNullOrEmpty(ormParam)) {
            int pageNum = ormParam.getPageNo();
            int pageSize = ormParam.getPageSize();
            PageHelper.startPage(pageNum, pageSize);
        }
        List<? extends BaseEntity> list = selectBeanList(entityClazz, ormParam);
        Long totalCount = count(entityClazz, ormParam);
        Pagination p = new Pagination(list, ormParam.getPageNo(), ormParam.getPageSize(), totalCount);
        return p;
    }

    /**
     * 分页接口
     *
     * @param entityClazz 实体类
     * @param ormParam    条件参数
     * @param <T>
     * @return 返回Pagination 中的data是List<Map>
     * @throws Exception
     */
    @Override
    public <T extends BaseEntity> Pagination<T> selectPagedMapList(Class<T> entityClazz, OrmParam ormParam) throws Exception {
        OrmAccessUtil.accessNull(ormParam);
        if (!StringUtil.isNullOrEmpty(ormParam)) {
            int pageNum = ormParam.getPageNo();
            int pageSize = ormParam.getPageSize();
            PageHelper.startPage(pageNum, pageSize);
        }
        List<Map<String, Object>> list = selectMapList(entityClazz, ormParam);
        Long totalCount = count(entityClazz, ormParam);
        Pagination p = new Pagination(list, ormParam.getPageNo(), ormParam.getPageSize(), totalCount);
        return p;
    }

    /**
     * 可执行的select 的 sql 语句
     *
     * @param sql 可执行sql
     * @return
     * @throws Exception
     */
    @Override
    public List<Map<String, Object>> getDataBySql(String sql) throws Exception {
        OrmAccessUtil.isValidSelectSql(sql);
        return ormMapper.selectBySql(sql);
    }

}
