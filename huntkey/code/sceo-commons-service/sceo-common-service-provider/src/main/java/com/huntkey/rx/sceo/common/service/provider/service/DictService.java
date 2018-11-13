package com.huntkey.rx.sceo.common.service.provider.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.huntkey.rx.commons.utils.rest.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huntkey.rx.commons.abstracts.BaseService;
import com.huntkey.rx.commons.constant.FlagConstant;
import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.commons.utils.uuid.UuidCreater;
import com.huntkey.rx.sceo.common.service.common.model.Dict;
import com.huntkey.rx.sceo.common.service.provider.dao.DictDao;


@Service
@Transactional(readOnly=true)
public class DictService extends BaseService {
	@Autowired
	private DictDao dictDao;
	
	@Resource(name = "redisTemplate")
	private RedisTemplate<String,List<Dict>> dictsRedisTemplate;
	
	private final static String ALL_DICTS = "all_fisrt_dict_list";
	
	public List<Dict> selAllDicts(){
		List<Dict> dicts = dictsRedisTemplate.opsForValue().get(ALL_DICTS);
		if(null == dicts){
			dicts = dictDao.selAllDicts();
			dictsRedisTemplate.opsForValue().set(ALL_DICTS, dicts);
		}
		return dicts;
	}
	
	/**
	 * 查询字典子元素
	 * @param pid 父节点ID
	 * @return List<Dict>
	 */
	public List<Dict> selDictsByParent(String pid){
		return dictDao.selDictsByParent(pid);
	}
	
	/**
	 * 根据ID查询一条字典信息
	 * @param id
	 * @return
	 */
	public Dict selDictById(String id) {
		return dictDao.selDictById(id);
	}
	
	/**
	 * 添加字典，如果没有ID，会自动生成一个UUID
	 * @param dict
	 * @return ID
	 */
	@Transactional(readOnly=false)
	public void addDict(Dict dict){
		if(StringUtil.isNullOrEmpty(dict.getId())){
			dict.setId(UuidCreater.uuid());
		}
		dict.setDeleteFlag(FlagConstant.FLAG_UN_DELETED);
		int res = dictDao.insert(dict);
		if(res == 1){ //更新缓存
			dictsRedisTemplate.opsForValue().set(ALL_DICTS, dictDao.selAllDicts());
		}
	}
	
	/**
	 * 更新字典
	 * @param dict
	 */
	@Transactional(readOnly=false)
	public int updateDict(Dict dict){
		dict.setDeleteFlag(FlagConstant.FLAG_UN_DELETED);
		int res = dictDao.update(dict);
		if(res == 1){ //更新缓存
			dictsRedisTemplate.opsForValue().set(ALL_DICTS, dictDao.selAllDicts());
		}
		return res;
	}

	/**
	 * 删除字典,包括所有的子节点
	 * @param id 当前节点的ID
	 * @return
	 */
	@Transactional(readOnly=false)
	public void delete(String id) {
		List<String> del_ids = new ArrayList<String>(); //将所有子节点的ID存放在list中
		del_ids.add(id); //首先加入本身
		batchQueryChildId(id,del_ids); //递归查询出嵌套的子节点ID
		System.out.println(del_ids.size());
		int res = dictDao.delete(del_ids);
		if(res == del_ids.size()){ //更新缓存
			dictsRedisTemplate.opsForValue().set(ALL_DICTS, dictDao.selAllDicts());
		}
	}
	
	/**
	 * 给出一个PID查询出所有的子节点ID做批量删除使用
	 * @param pid
	 * @return
	 */
	private void batchQueryChildId(String pid,List<String> del_ids){
		List<Dict> dicts = dictDao.selDictsByParent(pid);
		if(dicts.size()>0){
			for (Dict dict : dicts) {
				del_ids.add(dict.getId());
				batchQueryChildId(dict.getId(),del_ids);
			}
		}
	}

	/**
	 * 查询当前节点下是否存在子节点
	 * @param pid
	 * @return
	 */
	public boolean haveChildNode(String pid) {
		int count = dictDao.isHaveChild(pid);
		return count > 0 ? true : false;
	}

	/**
	 * 查询当前节点的所有父节点信息
	 * @param id
	 * @return
	 */
	public Map<String, Dict> getAllParent(String id) {
		List<Dict> list = new ArrayList<Dict>();
		getParent(list,id); //递归查询至最顶层
		Map<String, Dict> res = new LinkedHashMap<String, Dict>();
		for (int i = 0; i < list.size(); i++) { //map的size存储最顶层的节点
			res.put(list.get(i).getValue(), list.get(i));
		}
		return res;
	}
	
	private void getParent(List<Dict> list, String id){
		//1.查询出当前信息
		Dict dict = dictDao.selDictById(id);
		//2.找出parentId
		String parnetId = dict.getParentId();
		//3.判断当前是否为顶层节点,顶层节点parentId为0
		if(!"0".equals(parnetId)){
			list.add(dict);
			getParent(list,parnetId);
		}
	}

	/**
	 * 查询当前节点的所有父节点信息,并按顺序返回
	 * @param id
	 * @return
	 */
	public Map<String, Dict> getParentBySort(String id) {
		String[] xzqh = new String[]{"province","city","town"};
		List<Dict> list = new ArrayList<Dict>();
		getParent(list,id); //递归查询至最顶层
		Map<String, Dict> res = new LinkedHashMap<String, Dict>();
		for (int i = 0; i < list.size(); i++) { //map的size存储最顶层的节点
			res.put(xzqh[list.size()-i-1], list.get(i));
		}
		return  res;
	}

    /**
     * 根据CODE值查询字典值的信息
     * @param code 字典信息的CODE
     * @return 字典列表
     */
	public List<Dict> getDictsByCode(String code) {
		Map<String,String> param = new HashMap<String,String>();
		param.put("code", code);
		param.put("type", Dict.DICT_DEFAULT);
		return dictDao.getDictsByCode(param);
	}
	
	/*public static void main(String[] args) {
		//Map<String, String> map = new HashMap<String,String>();
		Map<String,String> map = new LinkedHashMap<String,String>();
		//List<String,String> list = 
		map.put("110200","北京市");
		map.put("110020","朝阳区");
		map.put("110000","北京");
		System.out.println(map.toString());
	}*/

	/**
	 * 根据一组CODE值查询对应的字典信息
	 * @param codes +
	 * @return
	 */
	public Map<String,List<Dict>> getDictsByCodes(String [] codes) {
		Map<String,List<Dict>> result = new HashMap<String,List<Dict>>();
		List<Dict> list = dictDao.getDictsByCodes(codes);
		if(list.size() > 0){
			List<Dict> temp = null;
			for (String code : codes) {
				temp = new ArrayList<Dict>();
				for (Dict dict : list) {
					if(code.equals(dict.getCode())){
						temp.add(dict);
					}
				}
				result.put(code, temp);
			}
		}
		return result;
	}

	/**
	 * 根据CODE与VALUE查询DICT信息
	 * @param code
	 * @param value
	 * @return
	 */
	public Dict getDictByCodeVal(String code, String value) {
		Map<String,String> param = new HashMap<String,String>();
		param.put("code", code);
		param.put("value", value);
		return dictDao.getDictByCodeVal(param);
	}

}
