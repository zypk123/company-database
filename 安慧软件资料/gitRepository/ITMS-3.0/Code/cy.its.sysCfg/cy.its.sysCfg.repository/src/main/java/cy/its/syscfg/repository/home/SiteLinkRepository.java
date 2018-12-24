package cy.its.syscfg.repository.home;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.syscfg.domain.model.home.SysSiteLink;
import cy.its.syscfg.domain.repository.home.ISiteLinkRepository;
import cy.its.syscfg.mybatis.client.SysSiteLinkMapper;


@Service
public class SiteLinkRepository implements ISiteLinkRepository {

	@Autowired
	SysSiteLinkMapper sysSiteLinkMapper;
	
	@Override
	public SysSiteLink aggregateOfId(String id) throws Exception {
		return sysSiteLinkMapper.selectByPrimaryKey(id);
	}

	@Override
	public String save(SysSiteLink obj) {
		 sysSiteLinkMapper.insertSelective(obj);
		 return obj.getIdentityId();
	}

	@Override
	public int delete(String id) {
		return sysSiteLinkMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int update(SysSiteLink obj) {
		return sysSiteLinkMapper.updateByPrimaryKeySelective(obj);
	}

	@Override
	public List<SysSiteLink> allSiteLinks() {
		return sysSiteLinkMapper.selectAll();
	}

}
