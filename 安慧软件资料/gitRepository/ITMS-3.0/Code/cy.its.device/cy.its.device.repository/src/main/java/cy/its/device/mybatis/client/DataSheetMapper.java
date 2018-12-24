package cy.its.device.mybatis.client;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cy.its.device.domain.model.DataSheet;

public interface DataSheetMapper {
    int deleteByPrimaryKey(String dataSheetId);

    int insert(DataSheet record);

    int insertSelective(DataSheet record);

    DataSheet selectByPrimaryKey(String dataSheetId);

    int updateByPrimaryKeySelective(DataSheet record);

    int updateByPrimaryKey(DataSheet record);
    
    List<DataSheet> selectDataSheets(@Param("serverId") String serverId);
}