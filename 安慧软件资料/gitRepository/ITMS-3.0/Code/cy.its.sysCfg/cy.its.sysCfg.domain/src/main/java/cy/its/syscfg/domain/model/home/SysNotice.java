package cy.its.syscfg.domain.model.home;

import java.util.Date;

import cy.its.com.domain.AggregateRoot;
import cy.its.com.util.StringUtil;
import cy.its.syscfg.domain.model.duty.User;
import cy.its.syscfg.domain.repository.duty.IUserRepository;

public class SysNotice extends AggregateRoot {
    private String noticeId;

    private String userId;

    private String noticeTitle;

    private String noticeContent;

    private Date createTime;

    private Date updateTime;

    public String getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(String noticeId) {
        this.noticeId = noticeId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    public String getNoticeContent() {
        return noticeContent;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

	@Override
	public String getIdentityId() {
		return this.noticeId;
	}
	
	/**
	 * 验证用户合法性
	 * @param userRepository
	 * @throws Exception 
	 */
	public void validateUser(IUserRepository userRepository) throws Exception {
		if(StringUtil.isNullOrEmpty(this.userId)){
			throw new Exception("公告通知必须指定发布者");
		}
		
		User user = userRepository.aggregateOfId(this.userId);		
		if(user == null){
			throw new Exception("公告通知指定的发布者不存在");
		}
	}
}