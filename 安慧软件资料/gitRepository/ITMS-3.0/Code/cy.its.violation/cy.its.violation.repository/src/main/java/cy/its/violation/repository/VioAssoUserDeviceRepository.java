package cy.its.violation.repository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.com.util.ObjectMapUtils;
import cy.its.com.util.ParamUtil;
import cy.its.com.util.StringUtil;
import cy.its.violation.domain.criteria.AssoUserDeviceCriteria;
import cy.its.violation.domain.model.config.VioAssoUserDevice;
import cy.its.violation.mybatis.client.T_Vio_Asso_User_DeviceMapper;
import cy.its.violation.mybatis.model.T_Vio_Asso_User_Device;

@Service
public class VioAssoUserDeviceRepository implements IVioAssoUserDeviceRepository {

	@Autowired
	T_Vio_Asso_User_DeviceMapper t_Vio_Asso_User_DeviceMapper;

	@Override
	public VioAssoUserDevice aggregateOfId(String id) throws Exception {
		T_Vio_Asso_User_Device obj = t_Vio_Asso_User_DeviceMapper.selectByPrimaryKey(id);
		VioAssoUserDevice userDevice = new VioAssoUserDevice();
		ObjectMapUtils.parseObject(userDevice, obj);
		return userDevice;
	}

	@Override
	public String save(VioAssoUserDevice obj) {
		obj.setAssoUserDeviceId(StringUtil.generateUUID());
		T_Vio_Asso_User_Device saveObj = new T_Vio_Asso_User_Device();
		ObjectMapUtils.parseObject(saveObj, obj);
		t_Vio_Asso_User_DeviceMapper.insertSelective(saveObj);
		return saveObj.getAssoUserDeviceId();
	}

	@Override
	public int delete(String id) {
		return t_Vio_Asso_User_DeviceMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int deleteByUserID(String userID) {
		return t_Vio_Asso_User_DeviceMapper.deleteByUserID(userID);
	}

	@Override
	public int update(VioAssoUserDevice obj) {
		T_Vio_Asso_User_Device targetObject = new T_Vio_Asso_User_Device();

		ObjectMapUtils.parseObject(targetObject, obj);
		return t_Vio_Asso_User_DeviceMapper.updateByPrimaryKeySelective(targetObject);
	}

	@Override
	public List<VioAssoUserDevice> findVioAssoUserDevice(AssoUserDeviceCriteria crieria) {
		Map<String, Object> map = ParamUtil.parseParams(crieria);
		;
		List<T_Vio_Asso_User_Device> lstResult = t_Vio_Asso_User_DeviceMapper.selectAssoUserDevices(map);
		// System.out.println(SqlHelper.getMapperSql(customForVioMapper,
		// "selectViolations", map));
		return lstResult.stream().map((c) -> {
			VioAssoUserDevice obj = new VioAssoUserDevice();
			ObjectMapUtils.parseObject(obj, c);
			return obj;
		}).collect(Collectors.toList());
	}
}
