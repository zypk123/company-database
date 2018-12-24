package cy.its.violation.mybatis.client;

import java.util.List;
import java.util.Map;

import cy.its.violation.mybatis.model.T_Vio_Asso_User_Device;

public interface T_Vio_Asso_User_DeviceMapper {
	int deleteByPrimaryKey(String assoUserDeviceId);

	int deleteByUserID(String userID);

	int insert(T_Vio_Asso_User_Device record);

	int insertSelective(T_Vio_Asso_User_Device record);

	T_Vio_Asso_User_Device selectByPrimaryKey(String assoUserDeviceId);

	int updateByPrimaryKeySelective(T_Vio_Asso_User_Device record);

	int updateByPrimaryKey(T_Vio_Asso_User_Device record);

	List<T_Vio_Asso_User_Device> selectAssoUserDevices(Map<String, Object> params);
}