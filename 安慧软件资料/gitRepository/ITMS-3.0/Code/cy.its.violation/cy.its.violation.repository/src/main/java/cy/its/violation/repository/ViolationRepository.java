package cy.its.violation.repository;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.com.util.ObjectMapUtils;
import cy.its.com.util.ParamUtil;
import cy.its.com.util.StringUtil;
import cy.its.violation.domain.criteria.ViolationCriteria;
import cy.its.violation.domain.model.violation.FilterStatistic;
import cy.its.violation.domain.model.violation.UnFilterStatistic;
import cy.its.violation.domain.model.violation.Violation;
import cy.its.violation.mybatis.client.CustomForVioMapper;
import cy.its.violation.mybatis.client.T_Vio_ViolationMapper;
import cy.its.violation.mybatis.model.T_VIO_FILTERSTATISTIC;
import cy.its.violation.mybatis.model.T_VIO_UNFILTERSTATISTIC;
import cy.its.violation.mybatis.model.T_Vio_Violation;
import cy.its.violation.util.SqlHelper;

@Service
public class ViolationRepository implements IViolationRepository {

	@Autowired
	CustomForVioMapper customForVioMapper;

	@Autowired
	T_Vio_ViolationMapper t_Vio_ViolationMapper;

	@Autowired
	SqlSessionFactoryBean sqlSessionFactory;

	private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public Violation aggregateOfId(String id) throws Exception {
		T_Vio_Violation vio = t_Vio_ViolationMapper.selectByPrimaryKey(id);
		Violation violation = new Violation();
		ObjectMapUtils.parseObject(violation, vio);
		return violation;
	}

	public String save(Violation obj) {
		obj.setViolationId(StringUtil.generateUUID());
		T_Vio_Violation vio = new T_Vio_Violation();
		ObjectMapUtils.parseObject(vio, obj);
		if (t_Vio_ViolationMapper.insertSelective(vio) > 0) {
			return obj.getViolationId();
		} else {
			return null;
		}
	}

	public int delete(String id) {
		return t_Vio_ViolationMapper.deleteByPrimaryKey(id);
	}

	public int update(Violation obj) {
		T_Vio_Violation vio = new T_Vio_Violation();
		ObjectMapUtils.parseObject(vio, obj);
		return t_Vio_ViolationMapper.updateByPrimaryKeySelective(vio);
	}

	public List<Violation> findViolations(ViolationCriteria violationCriteria) {
		Map<String, Object> map = parseVioParams(violationCriteria);
		String sql = SqlHelper.getMapperSql(customForVioMapper, "countViolations", map);
		if (violationCriteria.getNeedTotal()) {
			violationCriteria.setTotal(customForVioMapper.countViolations(map));
		}

		List<T_Vio_Violation> lsTVio = customForVioMapper.selectViolations(map);

		// System.out.println(System.currentTimeMillis());
		return lsTVio.stream().map((c) -> {
			Violation vio = new Violation();
			ObjectMapUtils.parseObject(vio, c);
			return vio;
		}).collect(Collectors.toList());
	}

	@Override
	public int countViolation(ViolationCriteria violationCriteria) {
		Map<String, Object> map = parseVioParams(violationCriteria);

		return customForVioMapper.countViolations(map);
	}

	/**
	 * 更新违法记录表(T_VIO_VIOLATION) 更新条件是: 违法记录ID, 锁定状态为未锁定; 更新结果:
	 * 锁定状态变更为已锁定;锁定时间为指定锁定时间;锁定人为指定锁定人;
	 * 
	 * @param violation
	 * @return
	 */
	public int updateForLock(Violation violation) {
		T_Vio_Violation vio = new T_Vio_Violation();
		ObjectMapUtils.parseObject(vio, violation);
		Map<String, Object> map = ParamUtil.parseParams(vio);
		map.put("condLockFlag", "0");

		return customForVioMapper.updateVioForLockOrNot(map);
	}

	/**
	 * 更新违法记录表(T_VIO_VIOLATION) 更新条件是: 违法记录ID, 锁定状态为已锁定; 更新结果:
	 * 锁定状态变更为未锁定;锁定时间为空;锁定人为空;
	 * 
	 * @param violation
	 * @return
	 */
	public int updateForUnlock(Violation violation) {
		T_Vio_Violation vio = new T_Vio_Violation();
		ObjectMapUtils.parseObject(vio, violation);
		Map<String, Object> map = ParamUtil.parseParams(vio);
		map.put("condLockFlag", "1");

		return customForVioMapper.updateVioForLockOrNot(map);
	}

	public int updateVioByCondition(ViolationCriteria violationCriteria) {
		Map<String, Object> map = parseVioParams(violationCriteria);
		String sql = SqlHelper.getMapperSql(customForVioMapper, "updateVioByCondition", map);
		System.out.println(sql);
		return customForVioMapper.updateVioByCondition(map);
	}

	private Map<String, Object> parseVioParams(ViolationCriteria violationCriteria) {

		Map<String, Object> map = ParamUtil.parseParams(violationCriteria);

		if (!StringUtil.isNullOrEmpty(violationCriteria.fuzzyPlateNbr)) {
			map.replace("fuzzyPlateNbr", "%" + violationCriteria.fuzzyPlateNbr + "%");
		}

		if (!StringUtil.isNullOrEmpty(violationCriteria.orgCode)) {
			map.replace("orgCode", violationCriteria.orgCode + "%");
		}

		if (!StringUtil.isNullOrEmpty(violationCriteria.org_authority_code)) {
			map.replace("org_authority_code", violationCriteria.org_authority_code + "%");
		}
		return map;
	}

	@Override
	public List<UnFilterStatistic> findUnFilterStatistic(ViolationCriteria violationCriteria) {
		Map<String, Object> map = parseVioParams(violationCriteria);
		List<T_VIO_UNFILTERSTATISTIC> lstResult = customForVioMapper.getUnfilterStatistic(map);
		// System.out.println(SqlHelper.getMapperSql(customForVioMapper,
		// "getUnfilterStatistic", map));
		// System.out.println(SqlHelper.getMapperSql(customForVioMapper,
		// "selectViolations", map));
		return lstResult.stream().map((c) -> {
			UnFilterStatistic obj = new UnFilterStatistic();
			ObjectMapUtils.parseObject(obj, c);
			return obj;
		}).collect(Collectors.toList());
	}

	@Override
	public List<FilterStatistic> findFilterStatistic(ViolationCriteria violationCriteria) {
		Map<String, Object> map = parseVioParams(violationCriteria);
		List<T_VIO_FILTERSTATISTIC> lstResult = customForVioMapper.getFilterStatistic(map);
		// System.out.println(SqlHelper.getMapperSql(customForVioMapper,
		// "getUnfilterStatistic", map));
		// System.out.println(SqlHelper.getMapperSql(customForVioMapper,
		// "selectViolations", map));
		return lstResult.stream().map((c) -> {
			FilterStatistic obj = new FilterStatistic();
			ObjectMapUtils.parseObject(obj, c);
			return obj;
		}).collect(Collectors.toList());
	}

	@Override
	public int deleteVioByCondition(ViolationCriteria violationCriteria) {
		Map<String, Object> map = parseVioParams(violationCriteria);
		return customForVioMapper.deleteVioByCondition(map);
	}

}
