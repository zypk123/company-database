package cy.its.violation.mybatis.client;

import cy.its.violation.mybatis.model.T_Vio_Upload_Log;

public interface T_Vio_Upload_LogMapper {
    int deleteByPrimaryKey(String uploadLogId);

    int insert(T_Vio_Upload_Log record);

    int insertSelective(T_Vio_Upload_Log record);

    T_Vio_Upload_Log selectByPrimaryKey(String uploadLogId);

    int updateByPrimaryKeySelective(T_Vio_Upload_Log record);

    int updateByPrimaryKey(T_Vio_Upload_Log record);
}