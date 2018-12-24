package cy.its.violation.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.com.util.ObjectMapUtils;
import cy.its.com.util.StringUtil;
import cy.its.violation.domain.model.config.VioAssoAction;
import cy.its.violation.mybatis.client.T_Vio_Asso_ActionMapper;
import cy.its.violation.mybatis.model.T_Vio_Asso_Action;

@Service
public class VioAssoActionRepository implements IVioAssoActionRepository {

	@Autowired
	T_Vio_Asso_ActionMapper t_Vio_Asso_ActionMapper;

	@Override
	public VioAssoAction aggregateOfId(String id) throws Exception {
		T_Vio_Asso_Action obj = t_Vio_Asso_ActionMapper.selectByPrimaryKey(id);
		VioAssoAction vioAssoAction = new VioAssoAction();
		ObjectMapUtils.parseObject(vioAssoAction, obj);
		return vioAssoAction;
	}

	@Override
	public String save(VioAssoAction obj) {
		obj.setVioActionMatchId(StringUtil.generateUUID());
		T_Vio_Asso_Action saveObj = new T_Vio_Asso_Action();
		ObjectMapUtils.parseObject(saveObj, obj);
		t_Vio_Asso_ActionMapper.insertSelective(saveObj);
		return saveObj.getVioActionMatchId();
	}

	@Override
	public int delete(String id) {
		return t_Vio_Asso_ActionMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int update(VioAssoAction obj) {
		T_Vio_Asso_Action targetObject = new T_Vio_Asso_Action();
		ObjectMapUtils.parseObject(targetObject, obj);
		return t_Vio_Asso_ActionMapper.updateByPrimaryKey(targetObject);
	}

	@Override
	public List<VioAssoAction> SelectByViolationCode(String violationCode) {
		List<T_Vio_Asso_Action> lstResult = t_Vio_Asso_ActionMapper.selectByViolationCode(violationCode);
		// System.out.println(SqlHelper.getMapperSql(customForVioMapper,
		// "selectViolations", map));
		return lstResult.stream().map((c) -> {
			VioAssoAction obj = new VioAssoAction();
			ObjectMapUtils.parseObject(obj, c);
			return obj;
		}).collect(Collectors.toList());
	}

}
