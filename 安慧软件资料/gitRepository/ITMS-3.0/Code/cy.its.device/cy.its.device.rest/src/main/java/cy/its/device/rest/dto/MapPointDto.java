/**
 * @Title: MapPointDto.java
 * @Package cy.its.device.rest.dto
 * @Description: 地图point dto
 * @author chenzhiying chenzy@cychina.cn
 * @date 2015年10月30日 下午3:18:06
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */

package cy.its.device.rest.dto;

/**
  * @ClassName: MapPointDto
  * @Description: 地图point dto
  * @author chenzhiying chenzy@cychina.cn
  * @date 2015年10月30日 下午3:18:06
  *
  */

public class MapPointDto {
	private double x;	
	private double y;
	/**
	 * getter method
	 * @return the x
	 */
	
	public double getX() {
		return x;
	}
	/**
	 * setter method
	 * @param x the x to set
	 */
	
	public void setX(double x) {
		this.x = x;
	}
	/**
	 * getter method
	 * @return the y
	 */
	
	public double getY() {
		return y;
	}
	/**
	 * setter method
	 * @param y the y to set
	 */
	
	public void setY(double y) {
		this.y = y;
	}
	

}
