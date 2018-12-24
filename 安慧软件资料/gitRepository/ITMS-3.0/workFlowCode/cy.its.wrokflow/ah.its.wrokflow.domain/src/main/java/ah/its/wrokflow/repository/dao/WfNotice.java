package ah.its.wrokflow.repository.dao;

import java.util.Date;

public class WfNotice {
    private String noticeId;

    private String noticeTile;

    private String noticeContent;

    private String noticeUser;

    private Date noticeTime;

    private String remark;

    public String getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(String noticeId) {
        this.noticeId = noticeId;
    }

    public String getNoticeTile() {
        return noticeTile;
    }

    public void setNoticeTile(String noticeTile) {
        this.noticeTile = noticeTile;
    }

    public String getNoticeContent() {
        return noticeContent;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
    }

    public String getNoticeUser() {
        return noticeUser;
    }

    public void setNoticeUser(String noticeUser) {
        this.noticeUser = noticeUser;
    }

    public Date getNoticeTime() {
        return noticeTime;
    }

    public void setNoticeTime(Date noticeTime) {
        this.noticeTime = noticeTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}