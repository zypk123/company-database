package com.huntkey.rx.sceo.serviceCenter.provider.core;

import java.text.SimpleDateFormat;
import java.util.*;

import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.sceo.serviceCenter.common.emun.TableType;
import com.huntkey.rx.sceo.serviceCenter.provider.orm.util.DBUtil;
import com.huntkey.rx.sceo.serviceCenter.provider.orm.util.UUID22;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.serviceCenter.common.model.InputArgument;
import com.huntkey.rx.sceo.serviceCenter.common.model.NodeConstant;
import com.huntkey.rx.sceo.serviceCenter.provider.client.EDMClient;
import com.huntkey.rx.sceo.serviceCenter.provider.orm.config.PersistanceConstant;
import com.huntkey.rx.sceo.serviceCenter.provider.orm.core.DBType;
import com.huntkey.rx.sceo.serviceCenter.provider.service.Persistance;

/***********************************************************************
 * @author chenxj
 *
 * @email: kaleson@163.com
 *
 * @date : 2017年7月13日 下午2:34:00
 *
 **********************************************************************/
@Component
public class ServiceParse {
	private static Logger logger = LoggerFactory.getLogger(ServiceParse.class);

    @Autowired
    private EDMClient EDMClient;

    @Value("${edm.version}")
    private String edmVersion;

    /**
     * 返回属性集
     *
     * @param edmName
     * @return
     */
    public List<String> getProperties(String edmName) {
        Result sub_result = EDMClient.getOrmAttrEdmCode(edmVersion, edmName, "");
        Object ob = sub_result.getData();
        if (ob == null) {
			String errMsg = "The result of getOrmAttrEdmCode method is null";
			logger.error(errMsg);
			throw new IllegalArgumentException(errMsg);
		}
		String str = ob.toString();
		str = str.replaceAll("\\[","");
		str = str.replaceAll("\\]","");
		str= str.replaceAll("\\{","");
		str= str.replaceAll("\\}","");
		String[] tmp = str.split("=");
		if (tmp.length != 2 || !edmName.equals(tmp[0])){
			String errMsg = "The format of getOrmAttrEdmCode's rusult is error";
			logger.error(errMsg);
			throw new IllegalArgumentException(errMsg);
		}
		String data = tmp[1];
		String[] array = data.split(",");
		List<String> porpertiesList = Arrays.asList(array);
		return porpertiesList;
	}

	/**
	 * 根据edmName 获取edm中定义的对象类型属性引用的类名称
	 *
	 * @param edmName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String, String> getRefMapping(String edmName){
		Map<String, String> result = null;

		Result rest_result = EDMClient.getEdmAttrObject(edmVersion, edmName);

        Map<String, Object> _map = (Map<String, Object>) rest_result.getData();
        if (_map.containsKey(edmName)) {
            result = (Map<String, String>) _map.get(edmName);
        } else {
            result = new HashMap<String, String>();
        }

        return result;
    }

    /**
     * 获取类的短名
     * @param edmName
     * @return
     */
    public String getShortName(String edmName) {
        // 如果是关联表操作，去掉后缀 _link;
        if (edmName.endsWith(Persistance.PREFIX)) {
            edmName = edmName.replace(Persistance.PREFIX, "");
        }

        Object obj = EDMClient.getEdmShortName(edmVersion, edmName).getData();
        if (obj == null) {
            return null;
        }
        return obj.toString();
    }

    /**
     * 获取数据库类型
     * @param edmName
     * @return
     */
    public DBType getDBType(String edmName) {
        String edmShortName = "";
        Result result = null;
        // 如果是关联表操作，去掉后缀 _link;
        if (edmName.endsWith(Persistance.PREFIX)) {
            edmShortName = edmName.replace(Persistance.PREFIX, "");
            result = EDMClient.getEdmNameByShortName(edmVersion,edmShortName);
            edmName = (String) result.getData();
        }
        result = EDMClient.getDbType(edmVersion, edmName);
        String type = (String) result.getData();
//		type = "mysql";
		if (DBType.HBASE.getName().equals(type)) {
			return DBType.HBASE;
		} else if (DBType.MYSQL.getName().equals(type)) {
			return DBType.MYSQL;
		} else {
			String errMsg = "It's failed to get the type of DB by the case that the class is not exists or  error by the function of getDbType in edm";
			logger.error(errMsg);
			throw new IllegalArgumentException(errMsg);
		}
	}


    /**
     * 新增参数解析
     *
     * @param argument
     * @return
     */
    public Set<ServiceData> parseInputArgument4add(InputArgument argument, DBType dbType) {
        Set<ServiceData> dataset = new HashSet<ServiceData>();

		String edmName = argument.getEdmName();
		JSONArray array = argument.getParams();
		if(array == null || array.size() == 0){
			String errMsg = "It's some exceptions by the params parameter in the JSON data in the function named parseInputArgument4add!";
			logger.error(errMsg);
			throw new IllegalArgumentException(errMsg);
		}
		if(!StringUtil.isNullOrEmpty(edmName) && edmName.contains(".")){
			//如果edmName带.说明新增加的是子表数据，新增加子表数据需要校验pid属性是否存在，如果不存在校验不通过，即使插入成功也是垃圾数据
			for(Object object: array){
				JSONObject jsonObject = (JSONObject)object;
				if(jsonObject.containsKey(NodeConstant.PID)){
						continue;
				}else{
					String errMsg = "To add child table data, the params parameter must contain the pid property field";
					logger.error(errMsg);
					throw new IllegalArgumentException(errMsg);
				}
			}
		}
		parseParam4add(dataset, edmName, array, dbType);

        return dataset;
    }

    /**
     * 根据参数 id和 edmName，删除主表数据和关联子表的业务数据
     *
     * @param edmName
     * @param id
     * @return
     */
    private Set<RemoveData> parseInputArgument4remove(String edmName, String id) {
        Result rest_result = EDMClient.getOrmAttrEdmCode(edmVersion, edmName, "");
        Set<RemoveData> result = new HashSet<RemoveData>();

        // 先删除主表
        RemoveData removeData = new RemoveData(edmName);
        JSONObject where = packageWhere(NodeConstant.ID, "=", id);
        removeData.addWhereData(where);
        result.add(removeData);
//        //后删除子表
//        @SuppressWarnings("unchecked")
//        Map<String, Object> _map = (Map<String, Object>) rest_result.getData();
//
//        // 如果有属性集
//        if (!_map.isEmpty()) {
//            // 再根据Pid 删除子表数据
//            @SuppressWarnings("unchecked")
//            List<String> _list = (List<String>) _map.get(edmName);
//            for (Iterator<String> iterator = _list.iterator(); iterator.hasNext(); ) {
//                String _attrCode = iterator.next();
//                String _edmName = edmName + "." + _attrCode;
//                RemoveData _removeData = new RemoveData(_edmName);
//                JSONObject _where = packageWhere(NodeConstant.PID, "=", id);
//                _removeData.addWhereData(_where);
//                result.add(_removeData);
//            }
//        }

        return result;
    }

    /**
     * 删除参数解析
     *
     * @param argument
     * @return
     */
    public Set<RemoveData> parseInputArgument4remove(InputArgument argument) {

        Set<RemoveData> result = new HashSet<RemoveData>();

        String edmName = argument.getEdmName();
        JSONArray array = argument.getParams();

		if(array == null || array.size() == 0){
			String errMsg = "It's some exceptions by the params parameter in the JSON data in the function named parseInputArgument4remove()";
			logger.error(errMsg);
			throw new IllegalArgumentException(errMsg);
		}

        for (int i = 0; i < array.size(); i++) {
            JSONObject obj = array.getJSONObject(i);
            if (!obj.containsKey(NodeConstant.ID)) {
                continue;
            }
            String id = obj.getString(NodeConstant.ID);

            Set<RemoveData> _set = this.parseInputArgument4remove(edmName, id);
            result.addAll(_set);
        }

        return result;
    }

    /**
     * 修改参数解析
     *
     * @param argument
     * @return
     */
    public Set<ServiceData> parseInputArgument4update(InputArgument argument, DBType type) {
        Set<ServiceData> dataset = new HashSet<ServiceData>();
        String edmName = argument.getEdmName();
        JSONArray array = argument.getParams();

        parseParam4update(dataset, edmName, array, type);

        return dataset;
    }

    private JSONObject packageWhere(String attr, String operator, String value) {
        JSONObject _where = new JSONObject();
        _where.put(NodeConstant.ATTR, attr);
        _where.put(NodeConstant.OPERATOR, operator);
        _where.put(NodeConstant.VALUE, value);

        return _where;
    }

    /**
     * 修改对象时，把参数拆分后存储到dataset集合中
     *
     * @param dataset：返回的数据集合
     * @param edmName：        类名称
     * @param array:          json数组
     */
    private void parseParam4update(Set<ServiceData> dataset, String edmName, JSONArray array, DBType dbtype) {
        // 判断数组元素的类型；如果每一个元素都是数组或者都是键值对，则一次性处理， 否则需要抽出每个元素的主表数据，再遍历元素的数组；
        ServiceData serviceData = new ServiceData(edmName);
        //设置es 索引字段
        if (DBType.HBASE.equals(dbtype)) {
            serviceData.put(NodeConstant.ES_FIELDS, getEdmEsIndex(edmName));
        }

        for (int i = 0; i < array.size(); i++) {
            int type = getNodeType(array.getJSONObject(i));
            if (type < 3) {
                // 处理系统时间；
                //原来这个地方for拿到最外层
                JSONObject _obj = array.getJSONObject(i);

                if (!_obj.containsKey(PersistanceConstant.P_MODTIME)) {
                    _obj.put(PersistanceConstant.P_MODTIME, getCurrentTime());
                }
                serviceData.getDataset().add(array.get(i));
            } else if (type == 3) { // 如果子元素时混合的类型，说明此节点是主从表关系；
                // 新建一个属性集合暂存属性集类型的节点；
                Map<String, JSONArray> subAttrubites = new HashMap<String, JSONArray>();

                // 遍历数组，把主表的元素拉出来；
                JSONObject obj = array.getJSONObject(i);

                // 存储普通属性
                JSONObject props = new JSONObject();

                for (Iterator<String> iterator = obj.keySet().iterator(); iterator.hasNext(); ) {
                    String key = iterator.next();
                    String value = obj.getString(key);

                    // 如果元素是数组格式，
                    if (isArray(value)) {
                        JSONArray sub_array = obj.getJSONArray(key);

                        // 把属性集存储到list中，后续继续遍历；
                        if (subAttrubites.containsKey(key)) {
                            subAttrubites.get(key).addAll(sub_array);
                        } else {
                            subAttrubites.put(key, sub_array);
                        }
                    } else {
                        props.put(key, value);
                    }
                }

                // 处理系统时间；
                if (!props.containsKey(PersistanceConstant.P_MODTIME)) {
                    props.put(PersistanceConstant.P_MODTIME, getCurrentTime());
                }

                serviceData.addData(props);

                // 开始遍历属性集
                Set<String> keys = subAttrubites.keySet();
                for (Iterator<String> iterator = keys.iterator(); iterator.hasNext(); ) {
                    String key = iterator.next();
                    String _edmName = edmName + "." + key;

                    parseParam4update(dataset, _edmName, subAttrubites.get(key), dbtype);
                }
            } else {
            	String errMsg = "json 参数无效!";
				logger.error(errMsg);
				throw new IllegalArgumentException(errMsg);
            }
        }

        dataset.add(serviceData);
    }

    /**
     * 设置 es 索引
     *
     * @param edmName
     * @return
     */
    @SuppressWarnings("unchecked")
    List<String> getEdmEsIndex(String edmName) {
        List<String> fields = new ArrayList<String>();
        if (edmName.endsWith(Persistance.PREFIX)) {
            // 如果是 _link_records 的记录表；索引传空对象，后台把所有业务数据都入索引;
            fields.clear();
        } else {
            //无论是主表还是属性集的子表，都需要指明索引；
            Object es_fields = EDMClient.getOrmIndexs(edmVersion, edmName).getData();
            @SuppressWarnings("unchecked")
            Map<String, Object> _map = (Map<String, Object>) es_fields;
//			serviceData.put(NodeConstant.ES, _map.get(edmName));
            // getOrmIndexs接口有bug，返回的数据节点并非参数edmName，而是返回的主类名称，临时加工一下数据如下，后续需要修正接口
            String _edmName = edmName;
            fields = (List<String>) _map.get(_edmName);
            //如果是属性集子表,索引需要增加pid字段
            if (edmName.contains(".")) {
                fields.add(NodeConstant.PID);
            }
            //serviceData.put(NodeConstant.ES_FIELDS, _fields);
        }
        return fields;
    }

    /**
     * 新增对象时，把参数拆分后存储到dataset集合中
     *
     * @param dataset：返回的数据集合
     * @param edmName：        类名称
     * @param array:          json数组
     * @param :               是否要加上es属性
     */
    private void parseParam4add(Set<ServiceData> dataset, String edmName, JSONArray array, DBType dbType) {
        // 判断数组元素的类型；如果每一个元素都是数组或者都是键值对，则一次性处理， 否则需要抽出每个元素的主表数据，再遍历元素的数组；
        ServiceData serviceData = new ServiceData(edmName);

        //第一歩： 处理索引
        if (DBType.HBASE.equals(dbType)) {
            serviceData.put(NodeConstant.ES_FIELDS, getEdmEsIndex(edmName));
        }
        for (int i = 0; i < array.size(); i++) {
            int type = getNodeType(array.getJSONObject(i));
            if (type < 3) {
                JSONObject _obj = array.getJSONObject(i);
                if (!_obj.containsKey(NodeConstant.ID)){
                    _obj.put(NodeConstant.ID, getUUID());
                }

                // 处理系统时间；
                if (!_obj.containsKey(PersistanceConstant.P_ADDTIME)) {
                    _obj.put(PersistanceConstant.P_ADDTIME, getCurrentTime());
                }

                //判断如果是子表增加classname字段
                if(edmName.contains(".")){
                    if (!_obj.containsKey(PersistanceConstant.CLASSNAME)) {
                        _obj.put(PersistanceConstant.CLASSNAME, getClassName(edmName));
                    }
                }

                serviceData.getDataset().add(array.get(i));
            } else if (type == 3) { // 如果子元素时混合的类型，说明此节点是主从表关系；
                // 新建一个属性集合暂存属性集类型的节点；
                Map<String, JSONArray> subAttrubites = new HashMap<String, JSONArray>();
                String _id = null;
                JSONObject obj = array.getJSONObject(i);
                if (obj.containsKey(NodeConstant.ID)){
                    _id = obj.getString(NodeConstant.ID);
                }else{
                    _id = getUUID();
                }

                // 存储普通属性
                JSONObject props = new JSONObject();
                props.put(NodeConstant.ID, _id);

                //取主表的 creuser，用于保存到子表中
                String creuser = null;
                if (obj.containsKey(PersistanceConstant.P_ADDUSER)){
                    Object object = obj.get(PersistanceConstant.P_ADDUSER);
                    if (object !=null){
                        creuser = object.toString();
                    }
                }

                // 把普通属性存储到容器 props中；
                for (Iterator<String> iterator = obj.keySet().iterator(); iterator.hasNext(); ) {
                    String key = iterator.next();
                    String value = obj.getString(key);

                    // 如果元素是数组格式，
                    if (isArray(value)) {
                        // 组装父节点id
                        JSONArray sub_array = obj.getJSONArray(key);
                        for (int j = 0; j < sub_array.size(); j++) {
                            sub_array.getJSONObject(j).put(NodeConstant.PID, _id);
                            sub_array.getJSONObject(j).put(PersistanceConstant.P_ADDUSER, creuser);
                        }

                        // 把属性集存储到list中，后续继续遍历；
                        if (subAttrubites.containsKey(key)) {
                            subAttrubites.get(key).addAll(sub_array);
                        } else {
                            subAttrubites.put(key, sub_array);
                        }
                    } else {
                        props.put(key, value);
                    }
                }

                // 处理系统时间；
                if (!props.containsKey(PersistanceConstant.P_ADDTIME)) {
                    props.put(PersistanceConstant.P_ADDTIME, getCurrentTime());
                }

                //判断如果是子表增加classname字段
                if(edmName.contains(".")){
                    if (!props.containsKey(PersistanceConstant.CLASSNAME)) {
                        props.put(PersistanceConstant.CLASSNAME, getClassName(edmName));
                    }
                }

                serviceData.addData(props);

                // 开始遍历属性集
                Set<String> keys = subAttrubites.keySet();
                for (Iterator<String> iterator = keys.iterator(); iterator.hasNext(); ) {
                    String key = iterator.next();
                    String _edmName = edmName + "." + key;

                    parseParam4add(dataset, _edmName, subAttrubites.get(key), dbType);
                }
            } else {
				String errMsg = "json 参数无效!";
				logger.error(errMsg);
				throw new IllegalArgumentException(errMsg);

            }
        }


        dataset.add(serviceData);
    }

    /**
     * 从edmname中取对应的主表的classname
     * @param edmName
     * @return classname
     */
    public static String getClassName(String edmName){
        //取最后一个.
        String className = edmName.substring(0, edmName.indexOf("."));
        return className;
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static String getCurrentTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String now = formatter.format(new Date());
        return now;
    }

    /**
     * 判断字符串是否为数组
     *
     * @param s
     * @return
     */
    public static boolean isArray(String s) {
        if (s == null) {
            return false;
        }

        if (s.startsWith("[{") && s.endsWith("}]")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断节点类型--> 1: 表示key - value 键值对; 2： 纯粹的数组类型 ; 3：混合的数据类型.
     *
     * @param node
     * @return
     */
    public static int getNodeType(Object node) {
        String v = "[{";
        String value = node.toString();

        // 如果不包含"[{"，说明时k-v键值对;
        if (!value.contains(v)) {
            return 1;
        }

        // 否则包括"[{", 如果不是以"[{"打头，或者包含多个符合"[{",说明是混合性类型;肯定是混合性数据类型;
        if (!value.startsWith(v) || value.substring(1).indexOf(v) > 0) {
            return 3;
        }

        // 干净的数组类型
        return 2;
    }

    public static String getUUID() {
        //32 位uuid
        return UUID.randomUUID().toString().replace("-","");
        //22 位的uuid
     //  return  UUID22.createUUID22();
    }

}
