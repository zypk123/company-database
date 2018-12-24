package ah.its.wrokflow.repository.Impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ah.its.wrokflow.repository.CommonBaseRepositoryI;
import ah.its.wrokflow.repository.dao.CommonBaseObj;
import ah.its.wrokflow.repository.domain.CommonBaseMapper;

@Service
public class CommonBaseRepositoryImpl implements CommonBaseRepositoryI {
	
	
	@Autowired
	private CommonBaseMapper commonBaseMapper;//查询操作类
	@Override
	public List<CommonBaseObj> selectAllData(Map map, String method) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		//获取实例类对象
		Class clazz = commonBaseMapper.getClass(); 
		//获取实例方法
		Method md = clazz.getDeclaredMethod(method,Map.class);
		
		//调用对象方法，返回数据
		return (List<CommonBaseObj>) md.invoke(commonBaseMapper,map);
	}

}
