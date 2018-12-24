package cy.its.violation.mybatis.client;

import java.util.List;
import java.util.Map;

import cy.its.violation.mybatis.model.T_Vio_White_Vehicle;

public interface WhiteVehicleMapper {

	List<T_Vio_White_Vehicle> selectPageWhiteVehicles(Map<String, Object> params);

	int countWhiteVehicles(Map<String, Object> params);
}
