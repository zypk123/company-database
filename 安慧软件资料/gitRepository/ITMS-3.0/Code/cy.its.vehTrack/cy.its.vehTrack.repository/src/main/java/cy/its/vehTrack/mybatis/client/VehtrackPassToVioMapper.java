package cy.its.vehTrack.mybatis.client;


import java.util.List;
import java.util.Map;


import cy.its.vehTrack.mybatis.model.VehtrackPassToVio;

public interface VehtrackPassToVioMapper {
    int deleteByExample(String id);
    @SuppressWarnings("rawtypes")
	List<VehtrackPassToVio> selectVioList(Map map);
    int updateByExampleSelective(@SuppressWarnings("rawtypes") Map map);
}