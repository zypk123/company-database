package cy.its.device.mybatis.client.led;

import java.util.List;
import java.util.Map;

import cy.its.device.domain.model.led.LedContentLib;

public interface LedContentLibMapper {
	
    int deleteByPrimaryKey(String contentId);

    int insert(LedContentLib record);

    int insertSelective(LedContentLib record);

    LedContentLib selectByPrimaryKey(String contentId);

    int updateByPrimaryKeySelective(LedContentLib record);

    int updateByPrimaryKey(LedContentLib record);

    int countContentLibs(Map<String, Object> params);

	List<LedContentLib> selectContentLibs(Map<String, Object> params);
}