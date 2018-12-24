package cy.its.violation.repository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.com.util.ObjectMapUtils;
import cy.its.com.util.ParamUtil;
import cy.its.com.util.StringUtil;
import cy.its.violation.domain.criteria.WhiteVehicleCriteria;
import cy.its.violation.domain.model.config.WhiteVehicle;
import cy.its.violation.mybatis.client.T_Vio_White_VehicleMapper;
import cy.its.violation.mybatis.client.WhiteVehicleMapper;
import cy.its.violation.mybatis.model.T_Vio_White_Vehicle;

@Service
public class WhiteVehicleRepository implements IWhiteVehicleRepository {

	@Autowired
	T_Vio_White_VehicleMapper t_vio_white_vehiclemapper;

	@Autowired
	WhiteVehicleMapper whiteVehicleMapper;

	@Override
	public WhiteVehicle aggregateOfId(String id) throws Exception {
		T_Vio_White_Vehicle obj = t_vio_white_vehiclemapper.selectByPrimaryKey(id);
		WhiteVehicle whiteVehicle = new WhiteVehicle();
		ObjectMapUtils.parseObject(whiteVehicle, obj);
		return whiteVehicle;
	}

	@Override
	public String save(WhiteVehicle obj) {
		obj.setVehicleId(StringUtil.generateUUID() );
		T_Vio_White_Vehicle whiteVehicle = new T_Vio_White_Vehicle();
		
		ObjectMapUtils.parseObject(whiteVehicle, obj);
		
		t_vio_white_vehiclemapper.insertSelective(whiteVehicle);
		return whiteVehicle.getVehicleId();

	}

	@Override
	public int delete(String id) {
		return t_vio_white_vehiclemapper.deleteByPrimaryKey(id);
	}

	@Override
	public int update(WhiteVehicle obj) {
		T_Vio_White_Vehicle whiteVehicle = new T_Vio_White_Vehicle();
		ObjectMapUtils.parseObject(whiteVehicle, obj);
		return t_vio_white_vehiclemapper.updateByPrimaryKeySelective(whiteVehicle);
	}

	@Override
	public List<WhiteVehicle> findWhiteVehicle(WhiteVehicleCriteria crieria) {
		Map<String, Object> map = parseParams(crieria);
		List<T_Vio_White_Vehicle> lstResult = whiteVehicleMapper.selectPageWhiteVehicles(map);
		// System.out.println(SqlHelper.getMapperSql(customForVioMapper,
		// "selectViolations", map));
		return lstResult.stream().map((c) -> {
			WhiteVehicle obj = new WhiteVehicle();
			ObjectMapUtils.parseObject(obj, c);
			return obj;
		}).collect(Collectors.toList());
	}

	@Override
	public int countWhiteVehicle(WhiteVehicleCriteria crieria) {
		Map<String, Object> map = parseParams(crieria);
		return whiteVehicleMapper.countWhiteVehicles(map);
	}

	private Map<String, Object> parseParams(WhiteVehicleCriteria criteria) {

		Map<String, Object> map = ParamUtil.parseParams(criteria);

		if (!StringUtil.isNullOrEmpty(criteria.fuzzyPlateNbr)) {
			map.replace("fuzzyPlateNbr", "%" + criteria.fuzzyPlateNbr + "%");
		}
		return map;
	}

}
