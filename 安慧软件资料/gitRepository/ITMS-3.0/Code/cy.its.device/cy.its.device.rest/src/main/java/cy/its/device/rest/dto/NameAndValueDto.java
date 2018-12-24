/**
 * @Title: NameAndValueDto.java
 * @Package cy.its.device.rest.dto
 * @Description: 名称与value dto
 * @author chenzhiying chenzy@cychina.cn
 * @date 2015年11月25日 下午4:29:51
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */

package cy.its.device.rest.dto;

/**
  * @ClassName: NameAndValueDto
  * @Description: 名称与value dto
  * @author chenzhiying chenzy@cychina.cn
  * @date 2015年11月25日 下午4:29:51
  *
  */

public class NameAndValueDto {
	private String name;		
	private int value;
	/**
	 * getter method
	 * @return the name
	 */
	
	public String getName() {
		return name;
	}
	/**
	 * setter method
	 * @param name the name to set
	 */
	
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * getter method
	 * @return the value
	 */
	
	public int getValue() {
		return value;
	}
	/**
	 * setter method
	 * @param value the value to set
	 */
	
	public void setValue(int value) {
		this.value = value;
	}	
}
