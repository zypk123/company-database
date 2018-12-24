package cy.its.syscfg.repository.sysConfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.com.util.ObjectMapUtils;
import cy.its.com.util.StringUtil;
import cy.its.syscfg.domain.model.config.SysFestival;
import cy.its.syscfg.domain.model.sysCode.Code;
import cy.its.syscfg.domain.repository.sysConfig.ISysFestivalRepository;
import cy.its.syscfg.mybatis.client.T_Sys_FestivalMapper;
import cy.its.syscfg.mybatis.model.T_Sys_Festival;


@Service
public class SysFestivalRepository implements ISysFestivalRepository {

	@Autowired
	T_Sys_FestivalMapper t_Sys_FestivalMapper;

	@Override
	public String createFestival(SysFestival obj) {
		obj.setFestivalId(StringUtil.generateUUID());
		T_Sys_Festival sysFestival = new T_Sys_Festival();
		ObjectMapUtils.parseObject(sysFestival, obj);
		t_Sys_FestivalMapper.insertSelective(sysFestival);
		
		return sysFestival.getFestivalId();
	}

	@Override
	public int delete(String id) {
		// TODO Auto-generated method stub
		return t_Sys_FestivalMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int updateFestival(SysFestival obj) {
		// TODO Auto-generated method stub
		T_Sys_Festival sysFestival = new T_Sys_Festival();
		ObjectMapUtils.parseObject(sysFestival, obj);

		return t_Sys_FestivalMapper.updateByPrimaryKeySelective(sysFestival);
	}


	@Override
	public List<Code> findFestivalCodeTypes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SysFestival> findAllFestivals(String year,String festivalType) {
		// TODO Auto-generated method stub
		Map  map   = new  HashMap();
		map.put("year", year);
		map.put("festivalType", festivalType);
		List<T_Sys_Festival> lstFestival = t_Sys_FestivalMapper.selectAllFestivals(map);

		return lstFestival.stream().map((c) -> {
			SysFestival festival = new SysFestival();
			ObjectMapUtils.parseObject(festival, c);
			return festival;
		}).collect(Collectors.toList());
	}

}
