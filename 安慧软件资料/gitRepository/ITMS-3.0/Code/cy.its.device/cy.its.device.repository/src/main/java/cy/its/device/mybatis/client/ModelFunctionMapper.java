package cy.its.device.mybatis.client;

import cy.its.device.domain.model.ModelFunction;
import cy.its.device.domain.model.ModelFunctionKey;

public interface ModelFunctionMapper {
    int deleteByPrimaryKey(ModelFunctionKey key);

    int insert(ModelFunction record);

    int insertSelective(ModelFunction record);

    ModelFunction selectByPrimaryKey(ModelFunctionKey key);

    int updateByPrimaryKeySelective(ModelFunction record);

    int updateByPrimaryKey(ModelFunction record);
}