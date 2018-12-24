package cy.its.device.mybatis.client;

import cy.its.device.domain.model.Equipment;

public interface EquipmentMapper {
    int deleteByPrimaryKey(String equipmentId);

    int insert(Equipment record);

    int insertSelective(Equipment record);

    Equipment selectByPrimaryKey(String equipmentId);
    
    Equipment selectByEquipmentNbr(String equipmentNbr);
    
    int updateByPrimaryKeySelective(Equipment record);

    int updateByPrimaryKey(Equipment record);
}