package cy.its.violation.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.com.util.ObjectMapUtils;
import cy.its.com.util.StringUtil;
import cy.its.violation.domain.model.config.VioUploadLog;
import cy.its.violation.mybatis.client.T_Vio_Upload_LogMapper;
import cy.its.violation.mybatis.model.T_Vio_Upload_Log;

@Service
public class VioUploadLogRepository implements IVioUploadLogRepository {

	@Autowired
	T_Vio_Upload_LogMapper t_Vio_Upload_LogMapper;

	@Override
	public VioUploadLog aggregateOfId(String id) throws Exception {
		T_Vio_Upload_Log obj = t_Vio_Upload_LogMapper.selectByPrimaryKey(id);
		VioUploadLog targetUnit = new VioUploadLog();
		ObjectMapUtils.parseObject(targetUnit, obj);
		return targetUnit;
	}

	@Override
	public String save(VioUploadLog obj) {
		obj.setUploadLogId(StringUtil.generateUUID());
		T_Vio_Upload_Log saveObj = new T_Vio_Upload_Log();
		ObjectMapUtils.parseObject(saveObj, obj);
		t_Vio_Upload_LogMapper.insertSelective(saveObj);
		return saveObj.getUploadLogId();
	}

	@Override
	public int delete(String id) {
		return t_Vio_Upload_LogMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int update(VioUploadLog obj) {
		T_Vio_Upload_Log targetObject = new T_Vio_Upload_Log();

		ObjectMapUtils.parseObject(targetObject, obj);
		return t_Vio_Upload_LogMapper.updateByPrimaryKeySelective(targetObject);
	}

}
