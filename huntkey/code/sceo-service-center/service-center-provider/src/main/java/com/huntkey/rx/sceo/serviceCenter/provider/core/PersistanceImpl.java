package com.huntkey.rx.sceo.serviceCenter.provider.core;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.sceo.serviceCenter.common.model.FullInputArgument;
import com.huntkey.rx.sceo.serviceCenter.common.model.InputArgument;
import com.huntkey.rx.sceo.serviceCenter.common.model.NodeConstant;
import com.huntkey.rx.sceo.serviceCenter.common.model.SearchParam;
import com.huntkey.rx.sceo.serviceCenter.provider.core.vallidate.DataValidateFactory;
import com.huntkey.rx.sceo.serviceCenter.provider.orm.core.DBType;
import com.huntkey.rx.sceo.serviceCenter.provider.orm.core.DataSet;
import com.huntkey.rx.sceo.serviceCenter.provider.orm.exception.DBException;
import com.huntkey.rx.sceo.serviceCenter.provider.orm.model.FullCriteria;
import com.huntkey.rx.sceo.serviceCenter.provider.orm.service.PersistanceService;
import com.huntkey.rx.sceo.serviceCenter.provider.service.Persistance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/***********************************************************************
 * @author chenxj
 *
 * @email: kaleson@163.com
 *
 * @date : 2017年7月13日 上午11:36:20
 *
 **********************************************************************/
@Service
public class PersistanceImpl implements Persistance {

    private static Logger logger = LoggerFactory.getLogger(PersistanceImpl.class);

    @Autowired
    private ServiceParse serviceParse;

    @Autowired
    private PersistanceService persistanceService;

    // @Autowired
    // private DataValidate dataValidate;

    @Autowired
    private DataValidateFactory dataValidateFactory;

    @Override
    @Transactional
    public Set<String> save(InputArgument args) throws DBException {

        Long beginGetdbTime = System.currentTimeMillis();
        String edmName = args.getEdmName();
        DBType type = serviceParse.getDBType(edmName);
        Long endGetdbTime = System.currentTimeMillis();
        logger.debug("---save-getDBType结束所用时间{}", endGetdbTime - beginGetdbTime);
        Set<String> ids = new HashSet<String>();

        // 1. 服务参数拆分；
        Set<ServiceData> serviceDatas = serviceParse.parseInputArgument4add(args, type);

        // 组装服务系统时间
        String errMsg = null;
        // 没有新增的元素，直接返回参数异常;
        if (serviceDatas.isEmpty()) {
            errMsg = "It isn't has any element in the params of adding";
            logger.error(errMsg);
            throw new IllegalArgumentException(errMsg);
        }

        Long parseTime = System.currentTimeMillis();
        logger.debug("---save-参数解析所用时间{}", parseTime - endGetdbTime);
        // 2. 服务参数有效性校验
        for (Iterator<ServiceData> iterator = serviceDatas.iterator(); iterator.hasNext(); ) {
            ServiceData serviceData = iterator.next();
            if (dataValidateFactory.getDataValidate(type).validate4add(serviceData) == false) {
                errMsg = "Failed to examine the params of adding :" + serviceData;
                logger.error(errMsg);
                throw new IllegalArgumentException(errMsg);
            }
        }
        Long validateTime = System.currentTimeMillis();
        logger.debug("---save-参数校验所用时间{}", validateTime - parseTime);

        // 3. 服务调用
        for (Iterator<ServiceData> iterator = serviceDatas.iterator(); iterator.hasNext(); ) {
            ServiceData serviceData = iterator.next();
            FullCriteria criteria = new FullCriteria(serviceData, serviceParse.getShortName(edmName));
            String _idArray = persistanceService.add(type, criteria);
            // 返回多个id
            if (edmName.equals(criteria.getEdmName())) {
                String[] idsArray = _idArray.split(",");
                List<String> _idslist = Arrays.asList(idsArray);
                ids.addAll(_idslist);
            }
        }
        Long endTime = System.currentTimeMillis();
        logger.debug("---save-保存数据所用时间{}", endTime - validateTime);

        return ids;
    }

    @Override
    @Transactional
    public Set<String> update(InputArgument args) throws DBException {
        Long beginGetdbTime = System.currentTimeMillis();
        String edmName = args.getEdmName();
        DBType type = serviceParse.getDBType(edmName);
        Long endGetdbTime = System.currentTimeMillis();
        logger.debug("---update-getDBType结束所用时间{}", endGetdbTime - beginGetdbTime);
        Set<String> ids = new HashSet<String>();

        // 1. 服务参数拆分；
        Set<ServiceData> serviceDatas = serviceParse.parseInputArgument4update(args, type);

        String errMsg = null;
        if (serviceDatas.isEmpty()) {
            errMsg = "It isn't has any element in the params of updating ";
            logger.error(errMsg);
            throw new IllegalArgumentException(errMsg);
        }

        Long parseTime = System.currentTimeMillis();
        logger.debug("---update-参数解析所用时间{}", parseTime - endGetdbTime);
        // 2. 服务参数有效性校验
        for (Iterator<ServiceData> iterator = serviceDatas.iterator(); iterator.hasNext(); ) {
            ServiceData serviceData = iterator.next();
            if (dataValidateFactory.getDataValidate(type).validate4update(serviceData) == false) {
                errMsg = "Failed to examine the params of adding :" + serviceData;
                logger.error(errMsg);
                throw new IllegalArgumentException(errMsg);
            }
        }

        Long validateTime = System.currentTimeMillis();
        logger.debug("---update-参数校验所用时间{}", validateTime - parseTime);
        // 3. 服务调用
        for (Iterator<ServiceData> iterator = serviceDatas.iterator(); iterator.hasNext(); ) {
            ServiceData serviceData = iterator.next();
            FullCriteria criteria = new FullCriteria(serviceData, serviceParse.getShortName(edmName));
            persistanceService.update(type, criteria);

            // 暂时不处理
            JSONArray jsonArray = criteria.getDataset();
            if (!StringUtil.isNullOrEmpty(jsonArray) && jsonArray.size() > 0) {
                for (Object object : jsonArray) {
                    JSONObject jsonObject = (JSONObject) object;
                    ids.add(jsonObject.get(NodeConstant.ID).toString());
                }
            }
//			 ids.add(serviceData.getString(NodeConstant.ID));
        }
        Long endTime = System.currentTimeMillis();
        logger.debug("---update-更新数据所用时间{}", endTime - validateTime);

        return ids;
    }

    @Override
    @Transactional
    public void remove(InputArgument args) throws DBException {
        Long beginParseTime = System.currentTimeMillis();
        Set<RemoveData> removes = serviceParse.parseInputArgument4remove(args);
        String errMsg = null;
        if (removes.isEmpty()) {
            errMsg = "Failed to examine case that it is not exists the columns named 'id'!";
            logger.error(errMsg);
            throw new IllegalArgumentException(errMsg);
        }
        Long endParseTime = System.currentTimeMillis();
        logger.debug("---remove-解析所用时间{}", endParseTime - beginParseTime);

        String edmName = args.getEdmName();
        DBType type = serviceParse.getDBType(edmName);

        Long getDbTypeTime = System.currentTimeMillis();
        logger.debug("---remove-取edm dbtype所用时间{}", getDbTypeTime - endParseTime);

        // 循环删除关联子表的数据
        for (Iterator<RemoveData> iterator = removes.iterator(); iterator.hasNext(); ) {
            RemoveData removeData = iterator.next();
            FullCriteria criteria = new FullCriteria(removeData, serviceParse.getShortName(edmName));

            persistanceService.delete(type, criteria);
        }
        Long deleteTime = System.currentTimeMillis();
        logger.debug("---remove-删除数据所用时间{}", deleteTime - getDbTypeTime);
    }

    @Override
    public JSONObject find(InputArgument args) throws DBException {

        Long beginGetdbTime = System.currentTimeMillis();
        String edmName = args.getEdmName();
        DBType type = serviceParse.getDBType(edmName);
        SearchData search = new SearchData(edmName);
        search.putAll(args.getSearch());
        Long endGetdbTime = System.currentTimeMillis();
        logger.debug("---find-getDBType结束所用时间{}", endGetdbTime - beginGetdbTime);
        // 3. 校验参数,包括校验查询的列/查询条件、排序条件
        if (dataValidateFactory.getDataValidate(type).validate4search(search) == false) {
            String errMsg = "Failed to examine the params of searching:" + search;
            logger.error(errMsg);
            throw new IllegalArgumentException(errMsg);
        }
        Long validateTime = System.currentTimeMillis();
        logger.debug("---find-validate结束所用时间{}", validateTime - endGetdbTime);

        // 4. 调用orm服务
        FullCriteria criteria = new FullCriteria(search, serviceParse.getShortName(edmName));

        DataSet result = persistanceService.find(type, criteria);
        Long endTime = System.currentTimeMillis();
        logger.debug("---find-调用orm服务所用时间{}", endTime - validateTime);
        return result.getJsonObject();
    }

    @Override
    public JSONObject load(InputArgument args) throws DBException {
        // 2. 组装参数
        String edmName = args.getEdmName();
        DBType type = serviceParse.getDBType(edmName);

        SearchData search = new SearchData(edmName);
        search.putAll(args.getSearch());

        String searchType = "base";

        if (search.containsKey("type")) {
            searchType = search.getString("type");
        }

        // 基本数据属性加载
        if (searchType.equals("base")) {
            return loadBaseAttrubites(type, search);
        } else if (searchType.equals("sub")) {
            return this.loadSubAttrubites(type, search);
        } else if (searchType.equals("ref")) {
            return this.loadRefAttrubites(type, search);
        } else if (searchType.equals("all")) {
            return this.loadAllAttrubites(type, search);
        } else {
            // 默认获取基本属性数据
            return loadBaseAttrubites(type, search);
        }
    }

    /**
     * 只获取对象的基本属性；
     *
     * @param type,search
     * @return
     * @throws DBException
     */
    private JSONObject loadBaseAttrubites(DBType type, SearchData search) throws DBException {
        String edmName = search.getEdmName();

        // 3. 校验参数,包括校验查询的列/查询条件、排序条件
        if (dataValidateFactory.getDataValidate(type).validate4search(search) == false) {
            String errMsg = "Failed to examine the params of loadBaseAttrubites:" + search;
            logger.error(errMsg);
            throw new IllegalArgumentException(errMsg);
        }

        // 4. 调用orm服务
        FullCriteria criteria = new FullCriteria(search, serviceParse.getShortName(edmName));

        DataSet result = persistanceService.load(type, criteria);

        return result.getJsonObject();
    }

    /**
     * 获取对象的基本属性数据 + 引用对象的数据
     *
     * @param type
     * @param search
     * @return
     * @throws DBException
     */
    private JSONObject loadRefAttrubites(DBType type, SearchData search) throws DBException {
        String edmName = search.getEdmName();

        // 1. 校验参数,包括校验查询的列/查询条件、排序条件
        if (dataValidateFactory.getDataValidate(type).validate4search(search) == false) {
            String errMsg = "Failed to examine the params of loadRefAttrubites:" + search;
            logger.error(errMsg);
            throw new IllegalArgumentException(errMsg);
        }

        // 2. 调用orm服务,获取基本数据类型
        FullCriteria criteria = new FullCriteria(search, serviceParse.getShortName(edmName));
        DataSet result = persistanceService.load(type, criteria);

        // 3. 查询关联对象的数据
        Map<String, String> _refMapping = serviceParse.getRefMapping(edmName);

        JSONObject mainData = result.getJsonObject();
        JSONArray dataset = mainData.getJSONArray(NodeConstant.DATASET);
        for (int i = 0; i < dataset.size(); i++) {
            JSONObject _node = (JSONObject) dataset.get(i);
            for (Iterator<String> iterator = _node.keySet().iterator(); iterator.hasNext(); ) {
                String _key = iterator.next();
                if (_refMapping.containsKey(_key)) {
                    String _id = _node.getString(_key);
                    String _edmName = _refMapping.get(_key);
                    // 根据_id,_edmName查询关联对象的数据
                    JSONArray jsonArray = new JSONArray();
                    jsonArray.add(_id);
                    SearchData searchData = new SearchData(_edmName);
                    searchData.put("type", "base");
                    searchData.put("ids", jsonArray);
                    FullCriteria fullCriteria = new FullCriteria(searchData, serviceParse.getShortName(_edmName));
                    DataSet refDataSet = persistanceService.load(type, fullCriteria);
                    if (!StringUtil.isNullOrEmpty(refDataSet)
                            && !StringUtil.isNullOrEmpty(refDataSet.getJsonObject())) {
                        if (refDataSet.getJsonObject().getJSONArray(NodeConstant.DATASET).size() > 0) {
                            _node.put(_key, refDataSet.getJsonObject().getJSONArray(NodeConstant.DATASET).get(0));
                        }
                    }

                }
            }
        }

        return mainData;
    }

    /**
     * 获取对象的基本属性数据 + 属性集的数据
     *
     * @param type
     * @param search
     * @return
     * @throws DBException
     */
    private JSONObject loadSubAttrubites(DBType type, SearchData search) throws DBException {
        // TODO load 的sub 模式
        String edmName = search.getEdmName();

        // 1. 校验参数,包括校验查询的列/查询条件、排序条件
        if (dataValidateFactory.getDataValidate(type).validate4search(search) == false) {
            String errMsg = "Failed to examine the params of loadSubAttrubites:" + search;
            logger.error(errMsg);
            throw new IllegalArgumentException(errMsg);
        }

        // 2. 调用orm服务,获取基本数据类型
        FullCriteria criteria = new FullCriteria(search, serviceParse.getShortName(edmName));
        DataSet subDataSet = persistanceService.load(type, criteria);

        // 3.查找属性集数据
        List<String> porpertiesList = serviceParse.getProperties(edmName);
        if (!StringUtil.isNullOrEmpty(subDataSet) && !StringUtil.isNullOrEmpty(subDataSet.getJsonObject())) {
            JSONArray dataset = subDataSet.getJsonObject().getJSONArray(NodeConstant.DATASET);
            if (dataset.size() == 0) {
                return null;
            }
            // 遍历id
            Iterator<Object> it = dataset.iterator();
            while (it.hasNext()) {
                JSONObject object = (JSONObject) it.next();
                String id = object.getString("id");
                for (String name : porpertiesList) {
                    String pname = edmName + "." + name;
                    SearchParam searchParam = new SearchParam(pname);
                    searchParam.addCond_equals("pid", id);
                    FullInputArgument searchArgument = new FullInputArgument(searchParam.toJSONString());
                    JSONObject result = find(searchArgument);
                    // 拼凑属性集
                    object.put(name, result.getJSONArray("data"));
                }
            }
            return subDataSet.getJsonObject();
        }
        return null;
    }

    /**
     * 查询对象的基本数据 + 属性集的数据 + 关联对象的数据
     *
     * @param type
     * @param search
     * @return
     * @throws DBException
     */
    private JSONObject loadAllAttrubites(DBType type, SearchData search) throws DBException {
        // TODO 待实现
        return null;
    }

    @Override
    public long count(InputArgument args) throws DBException {
        // 2. 组装参数
        String edmName = args.getEdmName();
        DBType type = serviceParse.getDBType(edmName);

        SearchData search = new SearchData(edmName);
        search.putAll(args.getSearch());

        // 3. 校验参数,包括校验查询的列/查询条件、排序条件
        if (dataValidateFactory.getDataValidate(type).validate4search(search) == false) {
            String errMsg = "Failed to examine the params of count:" + search;
            logger.error(errMsg);
            throw new IllegalArgumentException(errMsg);
        }

        // 4. 调用orm服务
        FullCriteria criteria = new FullCriteria(search, serviceParse.getShortName(edmName));

        long count = persistanceService.count(type, criteria);

        return count;
    }

    public JSONObject richfind(InputArgument args) throws DBException {
        // 2. 组装参数
        String edmName = args.getEdmName();
        DBType type = serviceParse.getDBType(edmName);

        SearchData search = new SearchData(edmName);
        search.putAll(args.getSearch());

        // 3. 校验参数,包括校验查询的列/查询条件、排序条件
        if (dataValidateFactory.getDataValidate(type).validate4richfind(search) == false) {
            String errMsg = "Failed to examine the params of richfind:" + search;
            logger.error(errMsg);
            throw new IllegalArgumentException(errMsg);
        }

        //need to add
        // 4. 调用orm服务
        FullCriteria criteria = new FullCriteria(search, serviceParse.getShortName(edmName));
        DataSet result = persistanceService.richfind(type, criteria);
        return result.getJsonObject();
    }

    @Override
    public List<Map<String, Object>> query(String sql) {
        return persistanceService.query(sql);
    }
}
