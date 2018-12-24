package cy.its.syscfg.repository.sysConfig;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.com.util.ObjectMapUtils;
import cy.its.com.util.StringUtil;
import cy.its.syscfg.domain.model.config.SysConfig;
import cy.its.syscfg.domain.repository.sysConfig.ISysConfigRepository;
import cy.its.syscfg.mybatis.client.SysConfigMapper;
import cy.its.syscfg.mybatis.client.T_Sys_ConfigMapper;
import cy.its.syscfg.mybatis.model.T_Sys_Config;
import cy.its.syscfg.util.Convert;

@Service
public class SysConfigRepository implements ISysConfigRepository {

	@Autowired
	T_Sys_ConfigMapper t_Sys_ConfigMapper;
	
	@Autowired
	SysConfigMapper sysConfigMapper;
	
	@Override
	public SysConfig aggregateOfId(String id) throws Exception {
		T_Sys_Config sysConfig = t_Sys_ConfigMapper.selectByPrimaryKey(id); 

		return Convert
				.convert(sysConfig);
	}

	@Override
	public String save(SysConfig obj) {
		// TODO Auto-generated method stub
		obj.setConfigId(StringUtil.generateUUID());
		T_Sys_Config sysConfig = new T_Sys_Config();
		ObjectMapUtils.parseObject(sysConfig, obj);
		t_Sys_ConfigMapper.insertSelective(sysConfig);

		return sysConfig.getConfigId();
	}

	@Override
	public int delete(String id) {
		// TODO Auto-generated method stub
		return t_Sys_ConfigMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int update(SysConfig obj) {
		// TODO Auto-generated method stub
		T_Sys_Config sysConfig = new T_Sys_Config();
		ObjectMapUtils.parseObject(sysConfig, obj);

		return t_Sys_ConfigMapper.updateByPrimaryKeySelective(sysConfig);
	}

	@Override
	public List<SysConfig> findConfig(String type) {
		//System.out.println(SqlHelper.getMapperSql(sysConfigMapper, "selectConfigByType", type));
		List<T_Sys_Config> lstConfig = sysConfigMapper.selectConfigByType(type);

		return lstConfig.stream().map((c) -> {
			SysConfig config = new SysConfig();
			ObjectMapUtils.parseObject(config, c);
			return config;
		}).collect(Collectors.toList());
	}

	@Override
	public int updateByPrimaryKeySelective(SysConfig record) {
		// TODO Auto-generated method stub
		T_Sys_Config con=new T_Sys_Config();
		ObjectMapUtils.parseObject(con, record);
		return t_Sys_ConfigMapper.updateByPrimaryKeySelective(con);
	}

	@Override
	public int updateByPrimaryKey(SysConfig record) {
		// TODO Auto-generated method stub
		return 0;
	}

}
