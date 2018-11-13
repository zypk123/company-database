package com.huntkey.rx.modeler.provider.service.impl;

import com.alibaba.druid.filter.config.ConfigTools;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.fastjson.JSON;
import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.modeler.common.model.*;
import com.huntkey.rx.modeler.common.util.Constant;
import com.huntkey.rx.modeler.provider.dao.*;
import com.huntkey.rx.modeler.provider.service.EdmToDbService;
import com.huntkey.rx.modeler.provider.util.SQLCreator;
import com.huntkey.rx.modeler.provider.util.SQLCreatorHelper;
import com.huntkey.rx.modeler.provider.util.TableCreator;
import com.huntkey.rx.modeler.provider.util.TableStructureGetter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhuyj on 2017/9/22.
 */
@Service
@Transactional(readOnly = true)
public class EdmToDbServiceImpl implements EdmToDbService {

    private static Logger log = LoggerFactory.getLogger(EdmLinkServiceImpl.class);

    @Autowired
    private EdmIndexMapper edmIndexMapper;
    @Autowired
    private EdmIndexDetailMapper edmIndexDetailMapper;
    @Autowired
    private EdmPropertyMapper edmPropertyMapper;
    @Autowired
    private EdmClassMapper edmClassMapper;
    @Autowired
    private EdmModelerMapper edmModelerMapper;


    @Value("${driver}")
    private String driver;
    @Value("${url}")
    private String url;
    @Value("${name}")
    private String name;
    @Value("${password}")
    private String password;
    @Value("${ip}")
    private String ip;

    /**
     * 指定类id，生成类和类中属性集的表
     * @param ids
     */
    @Override
    @Transactional
    public String dbCreator(String[] ids) {
        if (ids == null || ids.length <= 0) {
            return "未指定类";
        }
        List<EdmClass> edmClassList = new ArrayList<>();
        for (String id : ids) {
            EdmClass edmClass = getEdmClass(id);
            if (edmClass == null) {
                continue;
            }
            edmClassList.add(edmClass);
        }
        if (edmClassList == null || edmClassList.size() <= 0) {
            return "未找到类";
        }
        return getTables(edmClassList,null);
    }


    /**
     * 指定版本号，生成当前版本下所有类和属性集的表
     *
     * @param version
     */
    @Override
    public String dbAllCreator(String version) {
        String edmdId = edmModelerMapper.selectEdmdIdByVer(version);

        if (StringUtil.isNullOrEmpty(edmdId)) {
            return "版本不存在";
        }
        List<EdmClass> edmClassList = edmClassMapper.getAllEdmcClassByEdmdId(edmdId);

        if (edmClassList == null || edmClassList.size() <= 0) {
            return "未找到类";
        }
        return getTables(edmClassList,null);
    }

    /**
     * 指定版本号，生成当前版本下所有类和属性集的表
     *
     * @param version
     */
    @Override
    public String dbPassive(String version,Map<String,String> pathMap) {
        String edmdId = edmModelerMapper.selectEdmdIdByVer(version);

        if (StringUtil.isNullOrEmpty(edmdId)) {
            return "版本不存在";
        }
        List<EdmClass> edmClassList = edmClassMapper.getAllEdmcClassByEdmdId(edmdId);

        if (edmClassList == null || edmClassList.size() <= 0) {
            return "未找到类";
        }
        return getTables(edmClassList,pathMap);
    }

    /**
     * 生成表
     * @param edmClassList
     * @return
     */
    private String getTables(List<EdmClass> edmClassList,Map<String,String> pathMap) {
        log.info("开始获取数据源");
        DataSource dataSource = getDataSource(pathMap);
        SQLCreatorHelper sqlCreatorHelper = new SQLCreatorHelper();
        log.info("根据新的数据源获取数据库连接");
        try{
            Map<String, EdmTable> oldEdmTableMap = getAllOldTableList(dataSource);
            log.info("旧的表长度："+oldEdmTableMap.size());
            log.info("历史表结构已获取");
            try{
                for (EdmClass edmClass : edmClassList) {
                    String errMsg = getDetail(edmClass, oldEdmTableMap, sqlCreatorHelper);
                    if(!StringUtil.isNullOrEmpty(errMsg)){
                        return errMsg;
                    }
                }
            }catch (Exception e){
                log.error("类解析失败",e);
            }

        }catch (Exception e){
            log.error("历史表获取失败",e);
        }


        log.info("创建表文件");
        SQLCreator.sqlCreate(sqlCreatorHelper.getMap(), sqlCreatorHelper.getIndexList());
        log.info("创建存储过程");
        createProcedure(dataSource);
        TableCreator tableCreator = new TableCreator();
        FileSystemResource resource = new FileSystemResource("./test.sql");
        Long length = 0L;
        try {
            length = resource.contentLength();
        } catch (IOException e) {
          log.error("获取resource失败",e);
        }
        if (length == 0L) {
            return "脚本文件为空";
        } //文件为空时，返回
        try {
            tableCreator.addDataSource(dataSource)
                    .addSqlScript(resource)
                    .excuteSql();
        } catch (SQLException e) {
            return "sql发生未知异常！";
        }
        return "执行成功";
    }

    /**
     * 整理获取类属性集详细信息
     *
     * @param edmClass
     * @param oldEdmTableMap
     * @return
     */
    private String  getDetail(EdmClass edmClass, Map<String, EdmTable> oldEdmTableMap, SQLCreatorHelper sqlCreatorHelper) {

        String errMsg = "";
        // 所有属性
        List<EdmProperty> allProperties = new ArrayList<>();

        // 所有属性集
        List<EdmProperty> allAssembles = new ArrayList<>();

        // 所有索引
        List<DBIndex> indexList = new ArrayList<>();

        EdmTable edmTable = new EdmTable();

        // 获取类属性，包含父类属性,并转化成EdmTable
        errMsg = getEdmTable(edmClass, allProperties, edmTable);
        if(!StringUtil.isNullOrEmpty(errMsg)){
            return errMsg;
        }
        //虚类不生成表
        if (edmClass.getIsEntity() != (byte) 1) {
            edmTable = null;
        }

        // 将EdmTable 分类
        Map<String, List<EdmTable>> map = classifyEdmTable(edmTable, oldEdmTableMap.get(edmClass.getEdmcNameEn()));

//        // 生成link表
//        errMsg = getLinkTable(map, edmTable, oldEdmTableMap);
//        if(!StringUtil.isNullOrEmpty(errMsg)){
//            return errMsg;
//        }

        //获取属性集
        errMsg = getEdmProperty(edmClass, map, oldEdmTableMap, allProperties, allAssembles);
        if(!StringUtil.isNullOrEmpty(errMsg)){
            return errMsg;
        }
        // 获取索引信息
        errMsg = getIndex(edmClass, allProperties, allAssembles, indexList);
        if(!StringUtil.isNullOrEmpty(errMsg)){
            return errMsg;
        }

        SQLCreatorHelper temp = new SQLCreatorHelper(map, indexList);
        sqlCreatorHelper.setMap(mergeMap(sqlCreatorHelper.getMap(), temp.getMap()));
        sqlCreatorHelper.getIndexList().addAll(temp.getIndexList());
        sqlCreatorHelper.setIndexList(sqlCreatorHelper.getIndexList());

        return errMsg;
    }


    /**
     * 获取类属性，包含父类属性,并转化成EdmTable
     *
     * @param edmClass
     * @return
     */
    private String getEdmTable(EdmClass edmClass, List<EdmProperty> allProperties, EdmTable edmTable) {
        // 父类属性
        List<EdmProperty> parentProperties = new ArrayList<>();

        // 获取类里面的属性
        List<EdmProperty> ownerProperties = getProperties(edmClass.getId());
        allProperties.addAll(ownerProperties);

        // 获取父类属性
        List<EdmClass> parentClasses = getParentClass(edmClass.getId());
        if (parentClasses != null) {
            // 父类属性不为空
            for (EdmClass parentClass : parentClasses) {
                if (parentClass == null || StringUtil.isNullOrEmpty(parentClass.getId())) { continue; }
                parentProperties.addAll(getProperties(parentClass.getId()));
            }
        }
        allProperties.addAll(parentProperties);

        getEdmTableByClass(edmClass, allProperties, edmTable);

        // 类属性校验
        return checkProperties(edmTable);
    }

//    /**
//     * 获取类关联表
//     *
//     * @param edmTable
//     * @param oldEdmTableMap
//     * @return
//     */
//    private String getLinkTable(Map<String, List<EdmTable>> map, EdmTable edmTable, Map<String, EdmTable> oldEdmTableMap) {
//        //以_linkdetail结尾的类的关联表，由于该表的字段是不变的，所以该表不需要比较字段，直接判断表是否存在，如果不存在则将表加入createList
//        EdmTable table = new EdmTable();
//        if (oldEdmTableMap.get(edmTable.getTableShortName().concat(Constant.LINK_NAME)) == null) {
//            table.setFlag(edmTable.getFlag());
//            table.setRemarks(edmTable.getRemarks());
//            table.setTableName("");
//            table.setTableShortName(edmTable.getTableShortName());
//            if (map.get("createList") != null) {
//                map.get("createList").add(table);
//            } else {
//                List<EdmTable> createList = new ArrayList<>();
//                createList.add(table);
//                map.put("createList", createList);
//            }
//        }
//        return "";
//    }

    /**
     * 获取属性集属性
     *
     * @param edmClass
     * @param map
     * @param oldEdmTableMap
     * @param allProperties
     * @return
     */
    private String getEdmProperty(EdmClass edmClass, Map<String, List<EdmTable>> map, Map<String, EdmTable> oldEdmTableMap, List<EdmProperty> allProperties, List<EdmProperty> allAssembles) {
        allAssembles.addAll(getAssembles(edmClass.getId()));
        for (EdmProperty assemble : allAssembles) {
            List<EdmProperty> propertiesOfAssemble = getPropertiesOfAssemble(assemble.getId());
            EdmTable tempEdmTable = getEdmTableByAssemble(assemble, propertiesOfAssemble);
            String errMsg = checkProperties(tempEdmTable);
            if (!StringUtil.isNullOrEmpty(errMsg)){
                return errMsg;
            }
            Map<String, List<EdmTable>> tempMap = classifyEdmTable(tempEdmTable, oldEdmTableMap.get(assemble.getEdmpCode()));
            allProperties.addAll(propertiesOfAssemble);
            map = mergeMap(map, tempMap);
        }
        allProperties.addAll(allAssembles);
        return null;
    }

    /**
     * 生成索引
     *
     * @param edmClass
     * @param allProperties
     * @param allAssembles
     * @return
     */
    private String getIndex(EdmClass edmClass, List<EdmProperty> allProperties, List<EdmProperty> allAssembles, List<DBIndex> indexList) {
        List<EdmIndex> indexs = getIndexs(edmClass.getId());
        for (EdmIndex edmIndex : indexs) {
            if (edmIndex == null || StringUtil.isNullOrEmpty(edmIndex.getId())) {
                continue;
            }
            List<EdmIndexDetail> details = getIndexDetails(edmIndex.getId());
            DBIndex dbIndex = getDBIndex(edmIndex, details, edmClass, allProperties, allAssembles);
            if (dbIndex == null) {
                continue;
            }
            indexList.add(dbIndex);
        }
        return null;
    }


    /**
     * 属性校验
     * @param edmTable
     * @return
     */
    private String checkProperties(EdmTable edmTable){
        if (edmTable == null) { return null; }
        List<EdmColumn> columnList = edmTable.getColumnList();
        Map<String, String> map = new HashMap<>();
        for (EdmColumn edmColumn:columnList) {
            if (map.get(edmColumn.getColumnName()) != null) {
                return "表"+edmTable.getTableName()+"中，字段"+edmColumn.getColumnName()+"重复，请检查后在进行生成";
            } else {
                map.put(edmColumn.getColumnName(), "");
            }

            // 检查类型
            String type = edmColumn.getTypeName();
            String size = edmColumn.getColumnSize();
            if(Constant.INT_DB.equals(type)||Constant.VARCHAR_DB.equals(type)||Constant.TEXT_DB.equals(type)||Constant.OBJECT_DB.equals(type)){
                if(!isNumeric(size)){
                    return "表"+edmTable.getTableName()+"中，字段"+edmColumn.getColumnName()+"类型与长度不匹配。请检查后在生成";
                }
            }
        }
        return null;
    }

    /**
     * 判断String 是否为数字
     * @param str
     * @return
     */
    public boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }

    /**
     * modeler中索引转DBIndex类
     *
     * @param edmIndex
     * @param details
     * @param edmClass
     * @param properties
     * @param assembles
     * @return
     */
    private DBIndex getDBIndex(EdmIndex edmIndex, List<EdmIndexDetail> details, EdmClass edmClass
            , List<EdmProperty> properties, List<EdmProperty> assembles) {
        if (edmIndex == null || StringUtil.isNullOrEmpty(edmIndex.getEdmcId())) {
            return null;
        }
        DBIndex dbIndex = new DBIndex();
        dbIndex.setIndexName(edmIndex.getIndexName());
        dbIndex.setIndexType(getIndexType(edmIndex.getIndexType()));
        if (StringUtil.isNullOrEmpty(edmIndex.getEdmpAssId())) {
            dbIndex.setTableName(edmClass.getEdmcNameEn());
        } else {
            for (EdmProperty assemble : assembles) {
                if (edmIndex.getEdmpAssId().equals(assemble.getId())) {
                    dbIndex.setTableName(assemble.getEdmpCode());
                }
            }
        }
        for (EdmIndexDetail detail : details) {
            for (EdmProperty property : properties) {
                if (detail.getEdmpId().equals(property.getId())) {
                    dbIndex.getColumnList().add(property.getEdmpCode());
                }
            }
        }

        if (dbIndex.getColumnList().size() == 0) {
            dbIndex.setColumnList(null);
        }
        return dbIndex;
    }

    /**
     * 将EdmTable分类
     *
     * @param edmTable
     * @param oldEdmTable
     * @return
     */
    private Map<String, List<EdmTable>> classifyEdmTable(EdmTable edmTable, EdmTable oldEdmTable) {
        Map<String, List<EdmTable>> map = new HashMap<>();
        if (edmTable == null) { return map; }
        // 创建表list
        List<EdmTable> createList = new ArrayList<>();
        // 修改字段List
        List<EdmTable> updateList = new ArrayList<>();
        List<EdmColumn> updateColumnList = new ArrayList<>();
        // 添加字段List
        List<EdmTable> addList = new ArrayList<>();
        List<EdmColumn> addColumnList = new ArrayList<>();
        //删除字段
        List<EdmTable> dropList = new ArrayList<>();
        List<EdmColumn> dropColumnList = new ArrayList<>();

        if (oldEdmTable == null || StringUtil.isNullOrEmpty(oldEdmTable.getTableName())) {
            createList.add(edmTable);
            map.put("createList", createList);
            return map;
        }
        if (!oldEdmTable.getTableName().equals(edmTable.getTableName())) {
            createList.add(edmTable);
            map.put("createList", createList);
            return map;
        }

        List<EdmColumn> edmColumnList = edmTable.getColumnList();
        List<EdmColumn> oldEdmColumnList = oldEdmTable.getColumnList();
        List<Integer> indexsOfAddColumn = new ArrayList<>();
        List<Integer> indexsOfUpdateColumn = new ArrayList<>();
        List<Integer> indexsOfNotDropColumn = new ArrayList<>();
        Boolean foundFlag = false;
        for (int i = 0; i < edmColumnList.size(); i++) {
            for (int j = 0; j < oldEdmColumnList.size(); j++) {
                if (edmColumnList.get(i).getColumnName().equals(oldEdmColumnList.get(j).getColumnName())) {
                    foundFlag = true;
                    indexsOfNotDropColumn.add(j);
                    if (!edmColumnList.get(i).equals(oldEdmColumnList.get(j))) {
                        indexsOfUpdateColumn.add(i);
                    }
                }
            }
            if (!foundFlag) {
                indexsOfAddColumn.add(i);
            }
            foundFlag = false;
        }
        for (Integer integer : indexsOfAddColumn) {
            addColumnList.add(edmColumnList.get(integer));
        }
        for (Integer integer : indexsOfUpdateColumn) {
            updateColumnList.add(edmColumnList.get(integer));
        }
        foundFlag = false;
        for (int i = 0; i < oldEdmColumnList.size(); i++) {
            for (Integer integer : indexsOfNotDropColumn) {
                if (i == integer) {
                    foundFlag = true;
                    break;
                }
            }
            if (!foundFlag) {
                dropColumnList.add(oldEdmColumnList.get(i));
            }
            foundFlag = false;
        }
        if (updateColumnList != null && updateColumnList.size() > 0) {
            EdmTable table = copyEdmTable(edmTable);
            table.setColumnList(updateColumnList);
            updateList.add(table);
            map.put("updateList", updateList);
        }
        if (addColumnList != null && addColumnList.size() > 0) {
            EdmTable table = copyEdmTable(edmTable);
            table.setColumnList(addColumnList);
            addList.add(table);
            map.put("addList", addList);
        }
        if (dropColumnList != null && dropColumnList.size() > 0) {
            EdmTable table = copyEdmTable(edmTable);
            for (EdmColumn edmColumn : dropColumnList) {
                Date date = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
                String dateString = formatter.format(date);
                edmColumn.setDelName(edmColumn.getColumnName() + "_" + dateString + "_delete");
            }
            table.setColumnList(dropColumnList);
            dropList.add(table);
            map.put("dropList", dropList);
        }

        return map;
    }

    /**
     * 获取DB中所有表结构，转为EdmTable
     *
     * @param dataSource
     * @return
     */
    private Map<String, EdmTable> getAllOldTableList(DataSource dataSource) {
        log.info("1.new Table() start ....");
        TableStructureGetter tableStructureCreator = new TableStructureGetter(dataSource);
        log.info("1.new Table() end ....");

        log.info("..... ....");

        log.info("2.getAllEdmTables start ....");
        List<EdmTable> list = tableStructureCreator.getAllEdmTables();
        log.info("2.getAllEdmTables end ....");
        Map<String, EdmTable> map = new HashMap<>();
        for (EdmTable edmTable : list) {
            map.put(edmTable.getTableName(), edmTable);
        }

        return map;
    }

    /**
     * 创建一个存储过程，防止删除索引时，索引不存在报错
     *
     * @param dataSource
     */
    private void createProcedure(DataSource dataSource) {
        Connection con = null;
        Statement stem = null;
        try {
            con = dataSource.getConnection();
            stem = con.createStatement();
            stem.execute("DROP PROCEDURE IF EXISTS `DelIndex`;");
            stem.execute("CREATE PROCEDURE `DelIndex`(" +
                    "IN tablename VARCHAR(200)," +
                    "IN idxname VARCHAR(200)) " +
                    "BEGIN " +
                    "DECLARE str VARCHAR(250);" +
                    "DECLARE cnt INTEGER;" +
                    "SET @str = concat(' drop index ',idxname,' on ',tablename);" +
                    "SELECT count(*) INTO cnt FROM information_schema.statistics WHERE table_name=tablename AND index_name=idxname;" +
                    "IF cnt > 0 THEN " +
                    "PREPARE stmt FROM @str; " +
                    "EXECUTE stmt; " +
                    "END IF;" +
                    "END;");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stem.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取Edm类信息，如果类为非实体类，返回null
     *
     * @param id
     * @return
     */
    private EdmClass getEdmClass(String id) {
        if (StringUtil.isNullOrEmpty(id)) {
            return null;
        }
        EdmClass edmClass = edmClassMapper.selectByPrimaryKey(id);
        if (edmClass == null) {
            return null;
        }
//        if (edmClass.getIsEntity() != (byte) 1) { return null; }
        return edmClass;
    }

    /**
     * 获取父类集合
     *
     * @param id 类id
     * @return
     */
    private List<EdmClass> getParentClass(String id) {
        if (StringUtil.isNullOrEmpty(id)) {
            return null;
        }
        EdmClass edmClass = getEdmClass(id);
        if (edmClass == null) {
            return null;
        }

        List<EdmClass> list = new ArrayList<>();
        String parentId = edmClass.getEdmcParentId();
        while (!StringUtil.isNullOrEmpty(parentId)) {
            edmClass = edmClassMapper.selectByPrimaryKey(parentId);
            if (edmClass == null) {
                break;
            }
            parentId = edmClass.getEdmcParentId();
//            if (edmClass.getIsEntity() != (byte) 1) { continue; }
            list.add(edmClass);
        }

        return list;
    }

    /**
     * 获取类的属性（不包含属性集，常量属性,和属性集中属性）
     *
     * @param classId
     * @return
     */
    private List<EdmProperty> getProperties(String classId) {
        if (StringUtil.isNullOrEmpty(classId)) {
            return null;
        }
        EdmPropertyExample example = new EdmPropertyExample();
        EdmPropertyExample.Criteria criteria = example.createCriteria();
        criteria.andEdmpEdmcIdEqualTo(classId).andIsDelNotEqualTo((byte) 1)
                .andEdmpValueTypeNotEqualTo(Constant.PROPERTY_TYPE_CONST)
                .andEdmpValueTypeNotEqualTo(Constant.PROPERTY_TYPE_ASSEMBLE)
                .andEdmpParentIdIsNull();

        return edmPropertyMapper.selectByExample(example);
    }

    /**
     * 获取类的属性集
     *
     * @param classId
     * @return
     */
    private List<EdmProperty> getAssembles(String classId) {
        if (StringUtil.isNullOrEmpty(classId)) {
            return null;
        }

        EdmClass edmClass = getEdmClass(classId);
        if (edmClass == null) {
            return null;
        }

        EdmPropertyExample example = new EdmPropertyExample();
        EdmPropertyExample.Criteria criteria = example.createCriteria();
        criteria.andEdmpEdmcIdEqualTo(classId).andIsDelNotEqualTo((byte) 1)
                .andEdmpValueTypeEqualTo(Constant.PROPERTY_TYPE_ASSEMBLE);

        List<EdmProperty> allAseembles = edmPropertyMapper.selectByExample(example);
        setAssembleCode(edmClass, allAseembles); //根据规则重新设置属性集edmpCode

        return allAseembles;
    }

    /**
     * 按照规则设置属性集code名称 规则： edmcShortName_edmpCode(a,b,c....) a为第一级属性集，b为第二集属性集，以此类推
     *
     * @param edmClass
     * @param allAssembles
     * @return
     */
    private void setAssembleCode(EdmClass edmClass, List<EdmProperty> allAssembles) {
        Map<String, EdmProperty> map = new HashMap<>();
        for (EdmProperty assemble : allAssembles) {
            map.put(assemble.getId(), assemble);
        }

        for (EdmProperty assemble : allAssembles) {
            String parentId = assemble.getEdmpParentId();
            int suffix = 'a';
            while (!StringUtil.isNullOrEmpty(parentId)) {
                suffix++;
                EdmProperty property = map.get(parentId);
                if (property == null) {
                    break;
                }
                parentId = property.getEdmpParentId();
            }
            String shortName = edmClass.getEdmcShortName();
            String edmpCode = assemble.getEdmpCode();
            String suffixStr = edmpCode != null ? "_" + edmpCode.toLowerCase().concat("" + (char) (suffix)) : "_" + (char) (suffix);
            String code = shortName != null ? shortName.concat(suffixStr) : suffixStr;
            assemble.setEdmpCode(code);
        }
    }

    /**
     * 获取属性集中的属性(不包含属性集，常量属性)
     *
     * @param id
     * @return
     */
    private List<EdmProperty> getPropertiesOfAssemble(String id) {
        if (StringUtil.isNullOrEmpty(id)) {
            return null;
        }
        EdmPropertyExample example = new EdmPropertyExample();
        EdmPropertyExample.Criteria criteria = example.createCriteria();
        criteria.andEdmpParentIdEqualTo(id).andIsDelNotEqualTo((byte) 1)
                .andEdmpValueTypeNotEqualTo(Constant.PROPERTY_TYPE_CONST)
                .andEdmpValueTypeNotEqualTo(Constant.PROPERTY_TYPE_ASSEMBLE);

        return edmPropertyMapper.selectByExample(example);
    }

    /**
     * 获取类的索引
     *
     * @param classId
     * @return
     */
    private List<EdmIndex> getIndexs(String classId) {
        if (StringUtil.isNullOrEmpty(classId)) {
            return null;
        }
        EdmIndexExample example = new EdmIndexExample();
        EdmIndexExample.Criteria criteria = example.createCriteria();
        criteria.andEdmcIdEqualTo(classId).andIsDelNotEqualTo((byte) 1);

        return edmIndexMapper.selectByExample(example);
    }

    /**
     * 获取索引的详细信息
     *
     * @param indexId
     * @return
     */
    private List<EdmIndexDetail> getIndexDetails(String indexId) {
        if (StringUtil.isNullOrEmpty(indexId)) {
            return null;
        }
        EdmIndexDetailExample example = new EdmIndexDetailExample();
        EdmIndexDetailExample.Criteria criteria = example.createCriteria();
        criteria.andIndexIdEqualTo(indexId).andIsDelNotEqualTo((byte) 1);

        return edmIndexDetailMapper.selectByExample(example);
    }

    /**
     * 根据类转换为table
     *
     * @param edmClass
     * @return
     */
    private void getEdmTableByClass(EdmClass edmClass, List<EdmProperty> properties,EdmTable edmTable) {
        edmTable.setTableName(edmClass.getEdmcNameEn());
        edmTable.setTableShortName(edmClass.getEdmcShortName());
        edmTable.setFlag(Constant.CLASS_FLAG);
        String remarks = StringUtil.isNullOrEmpty(edmClass.getEdmcUseDesc()) ? edmClass.getEdmcName() : edmClass.getEdmcUseDesc();
        edmTable.setRemarks(remarks);
        List<EdmColumn> allColumnList = new ArrayList<>();
        for (EdmProperty edmProperty : properties) {
            if (edmProperty == null) {
                continue;
            }
            allColumnList.add(getEdmColumn(edmProperty));
        }
        edmTable.setColumnList(allColumnList);
    }

    /**
     * 根据属性集转换为table
     *
     * @param
     * @return
     */
    private EdmTable getEdmTableByAssemble(EdmProperty edmProperty, List<EdmProperty> properties) {
        EdmTable edmTable = new EdmTable();

        edmTable.setTableName(edmProperty.getEdmpCode());
        edmTable.setFlag(Constant.PROPERTY_FLAG);
        String remarks = StringUtil.isNullOrEmpty(edmProperty.getEdmpDesc()) ? edmProperty.getEdmpName() : edmProperty.getEdmpDesc();
        edmTable.setRemarks(remarks);

        List<EdmColumn> edmColumnList = new ArrayList<>();
        for (EdmProperty property : properties) {
            if (edmProperty == null) {
                continue;
            }
            edmColumnList.add(getEdmColumn(property));
        }
        edmTable.setColumnList(edmColumnList);

        return edmTable;
    }

    /**
     * 获取虚类下的属性集
     *
     * @param parentClasses
     * @return
     */
    private List<EdmProperty> getEdmProperty(List<EdmClass> parentClasses) {
//        boolean isEntity = false;
        List<EdmProperty> edmProperties = new ArrayList<>();
        for (EdmClass edmClass : parentClasses) {
            if (edmClass.getIsEntity() == 0) {
                // 虚类
                // 获取该虚类下的所有属性集
                List<EdmProperty> tempEdmProperties = getAssembles(edmClass.getId());
                if (!(StringUtil.isNullOrEmpty(tempEdmProperties) || tempEdmProperties.size() == 0)) {
                    edmProperties.addAll(tempEdmProperties);
                }
            }

        }
        return edmProperties;
    }

    /**
     * 索引类型转换
     *
     * @param indexType
     * @return
     */
    private String getIndexType(String indexType) {
        String type = "";
        switch (indexType) {
            case Constant.ORDINARY:
                type = Constant.INDEX;
                break;
            case Constant.ONLY:
                type = Constant.UNIQUE;
                break;
            case Constant.COMBINATION:
                type = Constant.INDEX;
                break;
            case Constant.PRIMARY:
                type = Constant.PRIMARY_KEY;
                break;
            default:
                break;
        }

        return type;
    }

    /**
     * 字段类型转换
     *
     * @param edmProperty
     * @return
     */
    private String getType(EdmProperty edmProperty) {
        String type = Constant.NORMAL_OBJ.equals(edmProperty.getEdmpValueType()) ? edmProperty.getEdmpDataType() : edmProperty.getEdmpValueType();
        String dbType = "";
        if (type == null) {
            return Constant.VARCHAR_DB;
        }
        switch (type) {
            case Constant.INT_MODELER:
                dbType = Constant.INT_DB;
                break;
            case Constant.VARCHAR_MODELER:
                dbType = Constant.VARCHAR_DB;
                break;
            case Constant.DECIMAL_MODELER:
                dbType = Constant.DECIMAL_DB;
                break;
            case Constant.TEXT_MODELER:
                dbType = Constant.TEXT_DB;
                break;
            case Constant.DATE_MODELER:
                dbType = Constant.DATE_DB;
                break;
            case Constant.OBJECT_MODELER:
                dbType = Constant.OBJECT_DB;
                break;
            case Constant.MEASUREMENT_MODELER:
                dbType = Constant.MEASUREMENT_DB;
                break;
            case Constant.OBJECT_LINK_MODELER:
                dbType = Constant.OBJECT_LINK_DB;
                break;
            case Constant.ENUM_MODELER:
                dbType = Constant.ENUM_DB;
                break;
            case Constant.CONVOLUTION_MODELER:
                dbType = Constant.CONVOLUTION_DB;
                break;
            case Constant.CLASS_MODELER:
                dbType = Constant.CLASS_DB;
                break;
            default:
                break;
        }

        return dbType;
    }

    /**
     * 获取字段长度
     *
     * @param edmProperty
     * @return
     */
    private String getColumnSize(EdmProperty edmProperty) {
        String type = Constant.NORMAL_OBJ.equals(edmProperty.getEdmpValueType()) ? edmProperty.getEdmpDataType() : edmProperty.getEdmpValueType();
        String size = "";
        if (type == null) {
            return Constant.VARCHAR_DB_SIZE;
        }
        switch (type) {
            case Constant.INT_MODELER:
                size = Constant.INT_DB_SIZE;
                break;
            case Constant.VARCHAR_MODELER:
                size = StringUtil.isNullOrEmpty(edmProperty.getEdmpValueSize()) ? Constant.VARCHAR_DB_SIZE : edmProperty.getEdmpValueSize();
                break;
            case Constant.DECIMAL_MODELER:
                size = StringUtil.isNullOrEmpty(edmProperty.getEdmpValueSize()) ? Constant.DECIMAL_DB_SIZE : edmProperty.getEdmpValueSize();
                break;
            case Constant.TEXT_MODELER:
                size = Constant.TEXT_DB_SIZE;
                break;
            case Constant.DATE_MODELER:
                size = Constant.DATE_DB_SIZE;
                break;
            case Constant.OBJECT_MODELER:
                size = Constant.OBJECT_DB_SIZE;
                break;
            case Constant.MEASUREMENT_MODELER:
                size = Constant.MEASUREMENT_DB_SIZE;
                break;
            case Constant.OBJECT_LINK_MODELER:
                size = Constant.OBJECT_LINK_DB_SIZE;
                break;
            case Constant.ENUM_MODELER:
                size = Constant.ENUM_DB_SIZE;
                break;
            case Constant.CONVOLUTION_MODELER:
                size = Constant.CONVOLUTION_DB_SIZE;
                break;
            case Constant.CLASS_MODELER:
                size = Constant.CLASS_DB_SIZE;
                break;
            default:
                break;
        }

        return size;
    }

    /**
     * 获取是否可空
     *
     * @param columnType
     * @return
     */
    private String getIsNullAble(String columnType) {
        String isNullAble = "";
        switch (columnType) {
            default:
                isNullAble = "YES";
                break;
        }

        return isNullAble;
    }

    /**
     * 获取字段默认值
     *
     * @param columnType
     * @param edmProperty
     * @return
     */
    private String getColumnDefault(String columnType, EdmProperty edmProperty) {
        if ("TEXT".equals(columnType)) {
            return "TEXT";
        }
        String value = edmProperty.getEdmpValue();
        if (value == null) {
            return "NULL";
        }
        if (value.isEmpty()) {
            switch (columnType) {
                case Constant.INT_DB:
                    value = "NULL";
                    break;
                case Constant.DECIMAL_DB:
                    value = "NULL";
                    break;
                default:
                    value = "NULL";
                    break;
            }
        }
        return value;
    }

    /**
     * 属性转DB字段
     *
     * @param edmProperty
     * @return
     */
    private EdmColumn getEdmColumn(EdmProperty edmProperty) {
        EdmColumn edmColumn = new EdmColumn();
        edmColumn.setColumnName(edmProperty.getEdmpCode());
        edmColumn.setTypeName(getType(edmProperty));
        edmColumn.setColumnSize(getColumnSize(edmProperty));
        edmColumn.setIsNullable(getIsNullAble(edmColumn.getTypeName()));
        edmColumn.setColumnDefault(getColumnDefault(edmColumn.getTypeName(), edmProperty));
        String remarks = StringUtil.isNullOrEmpty(edmProperty.getEdmpDesc()) ? edmProperty.getEdmpName() : edmProperty.getEdmpDesc();
        edmColumn.setRemarks(remarks);
        return edmColumn;
    }

    /**
     * 复制EdmTable
     *
     * @param edmTable
     * @return
     */
    private EdmTable copyEdmTable(EdmTable edmTable) {
        EdmTable table = new EdmTable();
        table.setTableName(edmTable.getTableName());
        table.setRemarks(edmTable.getRemarks());
        table.setPrimaryKey(edmTable.getPrimaryKey());
        table.setFlag(edmTable.getFlag());
        table.setTableShortName(edmTable.getTableShortName());

        return table;
    }

    /**
     * 合并map
     *
     * @param to
     * @param from
     * @return
     */
    private Map<String, List<EdmTable>> mergeMap(Map<String, List<EdmTable>> to, Map<String, List<EdmTable>> from) {
        if (to == null || from == null) {
            return null;
        }
        if (to.get("createList") == null) {
            if (from.get("createList") != null) {
                to.put("createList", from.get("createList"));
            }
        } else {
            if (from.get("createList") != null) {
                to.get("createList").addAll(from.get("createList"));
            }
        }
        if (to.get("updateList") == null) {
            if (from.get("updateList") != null) {
                to.put("updateList", from.get("updateList"));
            }
        } else {
            if (from.get("updateList") != null) {
                to.get("updateList").addAll(from.get("updateList"));
            }
        }
        if (to.get("addList") == null) {
            if (from.get("addList") != null) {
                to.put("addList", from.get("addList"));
            }
        } else {
            if (from.get("addList") != null) {
                to.get("addList").addAll(from.get("addList"));
            }
        }
        if (to.get("dropList") == null) {
            if (from.get("dropList") != null) {
                to.put("dropList", from.get("dropList"));
            }
        } else {
            if (from.get("dropList") != null) {
                to.get("dropList").addAll(from.get("dropList"));
            }
        }

        return to;
    }




    /**
     * 获取数据源 TODO
     *
     * @return
     */
    public DataSource getDataSource(Map<String,String> pathMap) {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(name);
//        dataSource.setPassword(password);
        try {
            dataSource.setPassword(ConfigTools.decrypt(password));

        } catch (Exception e) {
            e.printStackTrace();
        }
        if(pathMap!=null) {
            createDataSource(dataSource, pathMap);
            try {
                Thread.sleep(3000);
                log.info("创建新的数据源");
                DruidDataSource newDataSource = new DruidDataSource();
                newDataSource.setDriverClassName(driver);
                newDataSource.setUrl("jdbc:mysql://"+ip+":3306/"+pathMap.get("dataBase")+"?allowMultiQueries=true&useSSL=true");
                newDataSource.setUsername(pathMap.get("name"));
                newDataSource.setPassword(pathMap.get("dbPassword"));
                log.info("创建新的数据源成功！");
                return newDataSource;
            }catch (Exception e){
                log.error("创建数据源失败",e);
            }

        }
        return dataSource;
    }


    /**
     *
     * @param dataSource
     */
    private void createDataSource(DataSource dataSource,Map<String,String> pathMap) {
        Connection con = null;
        Statement stem = null;
        try {
            con = dataSource.getConnection();
            stem = con.createStatement();
            log.info("create dataBase!!");
            stem.execute("CREATE DATABASE "+pathMap.get("dataBase")+";");
            log.info("create user!!");
            stem.execute("CREATE USER '"+pathMap.get("name")+"'@'%' "+" IDENTIFIED BY '"+pathMap.get("dbPassword")+"';");
            log.info("授权!!");
            stem.execute("GRANT ALL ON "+pathMap.get("dataBase")+".* TO "+"'"+pathMap.get("name")+"'"+"@'%';");

            stem.execute("flush privileges;");
            log.info("创建数据源成功，用户为"+pathMap.get("name")+"密码为："+pathMap.get("dbPassword")+"数据源为："+pathMap.get("dataBase"));
        } catch (SQLException e) {
           log.error("创建或授权失败",e);
        } finally {
            try {
                stem.close();
                con.close();
            } catch (SQLException e) {
                log.error("关闭连接失败 ",e);
            }
        }
    }
}
