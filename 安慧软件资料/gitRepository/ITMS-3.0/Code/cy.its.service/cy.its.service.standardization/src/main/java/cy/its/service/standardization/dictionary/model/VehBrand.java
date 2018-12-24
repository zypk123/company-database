package cy.its.service.standardization.dictionary.model;

import cy.its.service.common.dataAccess.MapColumn;

public class VehBrand {
	@MapColumn
    private String brandCode;

    @MapColumn
    private String subBrand;

    @MapColumn
    private String brand;

	public String getBrandCode() {
		return brandCode;
	}

	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}

	public String getSubBrand() {
		return subBrand;
	}

	public void setSubBrand(String subBrand) {
		this.subBrand = subBrand;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
    
    
    
}
