package cy.its.syscfg.domain.model.home;

import cy.its.com.domain.AggregateRoot;

public class SysSiteLink extends AggregateRoot {
    private String linkId;

    private String linkName;

    private String linkIcon;

    private String linkUrl;

    public String getLinkId() {
        return linkId;
    }

    public void setLinkId(String linkId) {
        this.linkId = linkId;
    }

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }

    public String getLinkIcon() {
        return linkIcon;
    }

    public void setLinkIcon(String linkIcon) {
        this.linkIcon = linkIcon;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

	@Override
	public String getIdentityId() {
		return this.linkId;
	}
}