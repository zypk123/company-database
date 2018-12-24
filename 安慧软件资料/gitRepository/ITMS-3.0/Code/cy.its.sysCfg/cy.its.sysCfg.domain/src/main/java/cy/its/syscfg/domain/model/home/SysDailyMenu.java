package cy.its.syscfg.domain.model.home;

import java.util.Date;

import cy.its.com.domain.AggregateRoot;
import cy.its.com.util.StringUtil;
import cy.its.syscfg.domain.model.duty.User;
import cy.its.syscfg.domain.repository.duty.IUserRepository;

public class SysDailyMenu extends AggregateRoot {
    private String dailyMenuId;

    private String menuCode;

    private String userId;

    private Long visitCount;

    private Date updateTime;

    private String sortIndex;

    public String getDailyMenuId() {
        return dailyMenuId;
    }

    public void setDailyMenuId(String dailyMenuId) {
        this.dailyMenuId = dailyMenuId;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(Long visitCount) {
        this.visitCount = visitCount;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(String sortIndex) {
        this.sortIndex = sortIndex;
    }

	@Override
	public String getIdentityId() {
		return this.dailyMenuId;
	}
	
	/**
	 * 验证用户合法性
	 * @param userRepository
	 * @throws Exception 
	 */
	public void validateUser(IUserRepository userRepository) throws Exception {
		if(StringUtil.isNullOrEmpty(this.userId)){
			throw new Exception("用户常用链接必须指定用户");
		}
		
		User user = userRepository.aggregateOfId(this.userId);		
		if(user == null){
			throw new Exception("用户常用链接指定的用户不存在");
		}
	}
}