package cy.its.vehTrack.domain.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.vehTrack.domain.repository.ICloneCarRespository;
import cy.its.vehTrack.domain.service.ICloneCarService;

@Service
public class CloneCarService implements ICloneCarService {
	
	@Autowired
	ICloneCarRespository cloneCarRespository;

	@Override
	public Map<String, Object> findCloneCarInfo(String query,String queryDB) throws Exception {
		// TODO Auto-generated method stub
		return this.cloneCarRespository.findCloneCarInfo(query, queryDB);
	}

	/**
	 * Ì×ÅÆ³µ×´Ì¬¸üÐÂ
	 * @param clone_vehicle_id
	 * @param clone_flag
	 * @throws Exception 
	 */
	@Override
	public void updateConfirmFlag(String clone_vehicle_id, String clone_flag,String confirm_desc) throws Exception {
		this.cloneCarRespository.updateConfirmFlag(clone_vehicle_id, clone_flag,confirm_desc);
	}


}
