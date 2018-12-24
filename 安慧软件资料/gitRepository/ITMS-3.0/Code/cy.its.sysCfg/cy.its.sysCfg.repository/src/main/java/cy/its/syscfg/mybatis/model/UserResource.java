/**
 * @Title: UserResource.java
 * @Package cy.its.syscfg.mybatis.model
 * @Description: TODO(这里要填写用途)
 * @author zuop zuop@cychina.cn
 * @date 2015年11月18日 下午2:12:13
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */

package cy.its.syscfg.mybatis.model;

/**
  * @ClassName: UserResource
  * @Description: 用户权限
  * @author zuop zuop@cychina.cn
  * @date 2015年11月18日 下午2:12:13
  *
  */
public class UserResource {
	
	/**
	 * 一级菜单编码
	 */
	private String menuLevel1Code;
	
	/**
	 * 一级菜单名称
	 */
	private String menuLevel1Name;
	/**
	 * 一级菜单链接
	 */
	private String targetURL1;
	
	/**
	 * 一级菜单图片链接
	 */
	private String menuLevel1Image;
	
	/**
	 * 二级菜单编码
	 */
	private String menuLevel2Code;
	
	/**
	 * 二级菜单名称
	 */
	private String menuLevel2Name;
	
	/**
	 * 二级菜单图片链接
	 */
	private String menuLevel2Image;
	
	/**
	 * 一级菜单链接
	 */
	private String targetURL2;
	
	/**
	 * 三级菜单编码
	 */
	private String menuLevel3Code;
	
	/**
	 * 三级菜单名称
	 */
	private String menuLevel3Name;
	
	/**
	 * 三级菜单图片链接
	 */
	private String menuLevel3Image;
	
	/**
	 * 三级菜单链接
	 */
	private String targetURL3;
	
	/**
	 * 功能点编码
	 */
	private String functionCode;
	
	/**
	 * 功能点名称
	 */
	private String functionName;

	/**
	 * getter method
	 * @return the menuLevel1Code
	 */
	
	public String getMenuLevel1Code() {
		return menuLevel1Code;
	}

	/**
	 * setter method
	 * @param menuLevel1Code the menuLevel1Code to set
	 */
	
	public void setMenuLevel1Code(String menuLevel1Code) {
		this.menuLevel1Code = menuLevel1Code;
	}

	/**
	 * getter method
	 * @return the menuLevel1Name
	 */
	
	public String getMenuLevel1Name() {
		return menuLevel1Name;
	}

	/**
	 * setter method
	 * @param menuLevel1Name the menuLevel1Name to set
	 */
	
	public void setMenuLevel1Name(String menuLevel1Name) {
		this.menuLevel1Name = menuLevel1Name;
	}

	/**
	 * getter method
	 * @return the menuLevel2Code
	 */
	
	public String getMenuLevel2Code() {
		return menuLevel2Code;
	}

	/**
	 * setter method
	 * @param menuLevel2Code the menuLevel2Code to set
	 */
	
	public void setMenuLevel2Code(String menuLevel2Code) {
		this.menuLevel2Code = menuLevel2Code;
	}

	/**
	 * getter method
	 * @return the menuLevel2Name
	 */
	
	public String getMenuLevel2Name() {
		return menuLevel2Name;
	}

	/**
	 * setter method
	 * @param menuLevel2Name the menuLevel2Name to set
	 */
	
	public void setMenuLevel2Name(String menuLevel2Name) {
		this.menuLevel2Name = menuLevel2Name;
	}

	/**
	 * getter method
	 * @return the menuLevel3Code
	 */
	
	public String getMenuLevel3Code() {
		return menuLevel3Code;
	}

	/**
	 * setter method
	 * @param menuLevel3Code the menuLevel3Code to set
	 */
	
	public void setMenuLevel3Code(String menuLevel3Code) {
		this.menuLevel3Code = menuLevel3Code;
	}

	/**
	 * getter method
	 * @return the menuLevel3Name
	 */
	
	public String getMenuLevel3Name() {
		return menuLevel3Name;
	}

	/**
	 * setter method
	 * @param menuLevel3Name the menuLevel3Name to set
	 */
	
	public void setMenuLevel3Name(String menuLevel3Name) {
		this.menuLevel3Name = menuLevel3Name;
	}

	/**
	 * getter method
	 * @return the functionCode
	 */
	
	public String getFunctionCode() {
		return functionCode;
	}

	/**
	 * setter method
	 * @param functionCode the functionCode to set
	 */
	
	public void setFunctionCode(String functionCode) {
		this.functionCode = functionCode;
	}

	/**
	 * getter method
	 * @return the functionName
	 */
	
	public String getFunctionName() {
		return functionName;
	}

	/**
	 * setter method
	 * @param functionName the functionName to set
	 */
	
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	/**
	 * getter method
	 * @return the targetURL1
	 */
	
	public String getTargetURL1() {
		return targetURL1;
	}

	/**
	 * setter method
	 * @param targetURL1 the targetURL1 to set
	 */
	
	public void setTargetURL1(String targetURL1) {
		this.targetURL1 = targetURL1;
	}

	/**
	 * getter method
	 * @return the menuLevel1Image
	 */
	
	public String getMenuLevel1Image() {
		return menuLevel1Image;
	}

	/**
	 * setter method
	 * @param menuLevel1Image the menuLevel1Image to set
	 */
	
	public void setMenuLevel1Image(String menuLevel1Image) {
		this.menuLevel1Image = menuLevel1Image;
	}

	/**
	 * getter method
	 * @return the menuLevel2Image
	 */
	
	public String getMenuLevel2Image() {
		return menuLevel2Image;
	}

	/**
	 * setter method
	 * @param menuLevel2Image the menuLevel2Image to set
	 */
	
	public void setMenuLevel2Image(String menuLevel2Image) {
		this.menuLevel2Image = menuLevel2Image;
	}

	/**
	 * getter method
	 * @return the targetURL2
	 */
	
	public String getTargetURL2() {
		return targetURL2;
	}

	/**
	 * setter method
	 * @param targetURL2 the targetURL2 to set
	 */
	
	public void setTargetURL2(String targetURL2) {
		this.targetURL2 = targetURL2;
	}

	/**
	 * getter method
	 * @return the menuLevel3Image
	 */
	
	public String getMenuLevel3Image() {
		return menuLevel3Image;
	}

	/**
	 * setter method
	 * @param menuLevel3Image the menuLevel3Image to set
	 */
	
	public void setMenuLevel3Image(String menuLevel3Image) {
		this.menuLevel3Image = menuLevel3Image;
	}

	/**
	 * getter method
	 * @return the targetURL3
	 */
	
	public String getTargetURL3() {
		return targetURL3;
	}

	/**
	 * setter method
	 * @param targetURL3 the targetURL3 to set
	 */
	
	public void setTargetURL3(String targetURL3) {
		this.targetURL3 = targetURL3;
	}
}
