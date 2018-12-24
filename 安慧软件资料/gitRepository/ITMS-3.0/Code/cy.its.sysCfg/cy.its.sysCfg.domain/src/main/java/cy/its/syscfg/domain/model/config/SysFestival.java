package cy.its.syscfg.domain.model.config;

import java.util.Date;

import cy.its.com.domain.Entity;

public class SysFestival extends Entity<String> {
    private String festivalId;

    private String festivalType;

    private Date startDate;

    private Date endDate;

    private Date year;

    private String remark;

    public SysFestival()
    {
    	
    }
    
    
	public SysFestival(String festivalId, String festivalType, Date startDate, Date endDate, Date year,
			String remark) {
		super();
		this.festivalId = festivalId;
		this.festivalType = festivalType;
		this.startDate = startDate;
		this.endDate = endDate;
		this.year = year;
		this.remark = remark;
	}
	
    public String getFestivalId() {
        return festivalId;
    }

    public void setFestivalId(String festivalId) {
        this.festivalId = festivalId;
    }

    public String getFestivalType() {
        return festivalType;
    }

    public void setFestivalType(String festivalType) {
        this.festivalType = festivalType;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getYear() {
        return year;
    }

    public void setYear(Date year) {
        this.year = year;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

	@Override
	public String getIdentityId() {
		// TODO Auto-generated method stub
		return null;
	}
}