package cy.its.syscfg.mybatis.client;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cy.its.syscfg.domain.model.duty.UserSignature;

public interface UserSignatureMapper {
	
	/**
	 * 根据用户ID查找用户签章
	 * @param userId
	 * @return
	 */
	List<UserSignature> findSignatureByUserId(@Param("userId") String userId);

	/**
	 * 插入用户签章信息记录
	 * @param sign
	 * @return
	 */
	void saveUserSignature(UserSignature sign);
	
	/**
	 * 删除用户下的所有签章
	 */
	void removeUserSignature(@Param("userId") String userId);

}
