package cy.its.syscfg.repository.home;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.com.bus.EventBus;
import cy.its.syscfg.domain.model.home.SysDailyMenu;
import cy.its.syscfg.domain.repository.home.IDailyMenuRepository;
import cy.its.syscfg.mybatis.client.SysDailyMenuMapper;

@Service
public class DailyMenuRepository implements IDailyMenuRepository {

	@Autowired
	SysDailyMenuMapper sysDailyMenuMapper;

	@Autowired
	EventBus eventBus;

	@Override
	public SysDailyMenu aggregateOfId(String id) throws Exception {
		return sysDailyMenuMapper.selectByPrimaryKey(id);
	}

	@Override
	public String save(SysDailyMenu obj) {
		sysDailyMenuMapper.insertSelective(obj);
		return obj.getDailyMenuId();
	}

	@Override
	public int delete(String id) {
		return sysDailyMenuMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int update(SysDailyMenu obj) {
		return sysDailyMenuMapper.updateByPrimaryKeySelective(obj);
	}

	@Override
	public List<SysDailyMenu> dailyMenusOfUser(String userId) {
		return sysDailyMenuMapper.selectMenusByUserId(userId);
	}

	@Override
	public void deleteByUserPermmission() {
		System.out.println(sysDailyMenuMapper.deleteByUserPermmission());
	}

}
