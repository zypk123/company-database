package cy.its.device.mybatis.client.led;

import java.util.List;

import cy.its.device.domain.model.led.LedField;

public interface LedFieldMapper {
    int deleteByPrimaryKey(String fieldId);

    int insert(LedField record);

    int insertSelective(LedField record);

    LedField selectByPrimaryKey(String fieldId);

    int updateByPrimaryKeySelective(LedField record);

    int updateByPrimaryKey(LedField record);

	List<LedField> selectAll();
}