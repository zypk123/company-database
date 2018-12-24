package cy.its.sysCfg.rest.action;

import java.util.List;

import cy.its.sysCfg.rest.dto.DataGridResult;

import cy.its.sysCfg.rest.dto.UserDto;

import cy.its.syscfg.domain.model.duty.User;

public interface IUserAction {

	public int delete(int id);
	
	public List<User> getUserRoot(String rootId) throws Exception;
	
	DataGridResult<UserDto> selectUser(UserDto userInfo) throws Exception;

	String save(UserDto userInfo) throws Exception;
}
