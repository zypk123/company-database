package cy.its.device.mybatis.client;

import java.util.List;
import java.util.Map;

import cy.its.device.domain.model.Contractor;

public interface ContractorMapper {
    int deleteByPrimaryKey(String contractorId);

    int insert(Contractor record);

    int insertSelective(Contractor record);

    Contractor selectByPrimaryKey(String contractorId);

    int updateByPrimaryKeySelective(Contractor record);

    int updateByPrimaryKey(Contractor record);
    
    List<Contractor> selectContractors(Map<String, Object> params);
    
    Integer countContractors(Map<String, Object> params);
}