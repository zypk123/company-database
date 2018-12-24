package cy.its.violation.mybatis.client;

import cy.its.violation.mybatis.model.T_Vio_White_Vehicle;

public interface T_Vio_White_VehicleMapper {

	int deleteByPrimaryKey(String vehicleId);

	int insert(T_Vio_White_Vehicle record);

	int insertSelective(T_Vio_White_Vehicle record);

	T_Vio_White_Vehicle selectByPrimaryKey(String vehicleId);

	int updateByPrimaryKeySelective(T_Vio_White_Vehicle record);

	int updateByPrimaryKey(T_Vio_White_Vehicle record);
}