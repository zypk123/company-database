package com.huntkey.rx.modeler.common.model.vo;

import com.huntkey.rx.modeler.common.model.EdmProperty;

import java.util.Date;
import java.util.List;

/**
 * Created by liangh on 2017/4/14.
 */
public class EdmPropertyVO  extends EdmProperty {

    //主键id
    private String id;

    //类id
    private String edmpEdmcId;

    //父属性id
    private String edmpParentId;

    //属性编码
    private String edmpCode;

    //属性名称
    private String edmpName;

    //属性描述
    private String edmpDesc;

    //属性值类型
    private String edmpValueType;

    //数据类型
    private String edmpDataType;

    //属性值长度
    private String edmpValueSize;

    //属性限值id
    private String edmpValueLimit;

    //属性公式id
    private String edmpFormula;

    //属性值
    private String edmpValue;

    //属性修改方法id
    private String edmpEdmmId;

    //对象类id
    private String edmpObjEdmcId;

    //排序
    private Integer edmpSeq;

    //是否索引
    private Byte isIndex;

    //是否可见
    private Byte isVisible;

    //是否自定义
    private Byte isDefined;

    //是否删除状态位
    private Byte isDel;

    //是否特征值
    private Byte isCharacter;

    //创建时间
    private Date addtime;

    //创建人
    private String adduser;

    //修改时间
    private Date modtime;

    //修改人
    private String moduser;

    //账户标识
    private Byte acctid;

    //表名
    private String tablename;

    //--------------扩展字段-------------------

    //属性扩展
    private String edmpExtendProperty;

    //所属类名
    private String edmpEdmcName;

    //所属类英文名称
    private String edmpEdmcNameEn;

    //属性值类型名称
    private String edmpValueName;

    //属性值数据类型名称
    private String edmpDataName;

    //修改响应方法名称
    private String edmpEdmmName;

    //父属性名称
    private String edmpParentName;
    
    // 添加时间字符串格式
	private String addtimeStr;
	
	// 修改时间字符串格式
	private String modtimeStr;

	//级别
    private int level;

    //触发条件
    private String triggerCond;

	//子属性
    private List<EdmPropertyVO> children;

    //级联编码
    private String casEdmpCode;

    //级联名称
    private String casEdmpName;

    //属性限值
    private String propertLimit;

    //属性限值描述
    private String propertLimitDesc;

    //属性公式
    private String propertFormula;

    //是否父类属性
    private boolean isFather;

    private String enumName;//枚举对象名

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getEdmpEdmcId() {
        return edmpEdmcId;
    }

    public void setEdmpEdmcId(String edmpEdmcId) {
        this.edmpEdmcId = edmpEdmcId == null ? null : edmpEdmcId.trim();
    }

    public String getEdmpParentId() {
        return edmpParentId;
    }

    public void setEdmpParentId(String edmpParentId) {
        this.edmpParentId = edmpParentId == null ? null : edmpParentId.trim();
    }

    public String getEdmpCode() {
        return edmpCode;
    }

    public void setEdmpCode(String edmpCode) {
        this.edmpCode = edmpCode == null ? null : edmpCode.trim();
    }

    public String getEdmpName() {
        return edmpName;
    }

    public void setEdmpName(String edmpName) {
        this.edmpName = edmpName == null ? null : edmpName.trim();
    }

    public String getEdmpDesc() {
        return edmpDesc;
    }

    public void setEdmpDesc(String edmpDesc) {
        this.edmpDesc = edmpDesc == null ? null : edmpDesc.trim();
    }

    public String getEdmpValueType() {
        return edmpValueType;
    }

    public void setEdmpValueType(String edmpValueType) {
        this.edmpValueType = edmpValueType == null ? null : edmpValueType.trim();
    }

    public String getEdmpValueSize() {
        return edmpValueSize;
    }

    public void setEdmpValueSize(String edmpValueSize) {
        this.edmpValueSize = edmpValueSize == null ? null : edmpValueSize.trim();
    }

    public String getEdmpValueLimit() {
        return edmpValueLimit;
    }

    public void setEdmpValueLimit(String edmpValueLimit) {
        this.edmpValueLimit = edmpValueLimit == null ? null : edmpValueLimit.trim();
    }

    public String getEdmpFormula() {
        return edmpFormula;
    }

    public void setEdmpFormula(String edmpFormula) {
        this.edmpFormula = edmpFormula == null ? null : edmpFormula.trim();
    }

    public String getEdmpValue() {
        return edmpValue;
    }

    public void setEdmpValue(String edmpValue) {
        this.edmpValue = edmpValue == null ? null : edmpValue.trim();
    }

    public String getEdmpEdmmId() {
        return edmpEdmmId;
    }

    public void setEdmpEdmmId(String edmpEdmmId) {
        this.edmpEdmmId = edmpEdmmId == null ? null : edmpEdmmId.trim();
    }

    public String getEdmpObjEdmcId() {
        return edmpObjEdmcId;
    }

    public void setEdmpObjEdmcId(String edmpObjEdmcId) {
        this.edmpObjEdmcId = edmpObjEdmcId == null ? null : edmpObjEdmcId.trim();
    }

    public Integer getEdmpSeq() {
        return edmpSeq;
    }

    public void setEdmpSeq(Integer edmpSeq) {
        this.edmpSeq = edmpSeq;
    }

    public Byte getIsDefined() {
        return isDefined;
    }

    public void setIsDefined(Byte isDefined) {
        this.isDefined = isDefined;
    }

    public Byte getIsDel() {
        return isDel;
    }

    public void setIsDel(Byte isDel) {
        this.isDel = isDel;
    }

    public Date getAddtime() {
        if(this.addtime != null){
            return new Date(this.addtime.getTime());
        }else{
            return null;
        }
    }

    public void setAddtime(Date addtime) {
        if(addtime != null){
            this.addtime = (Date)addtime.clone();
        }else{
            this.addtime = null;
        }
    }
    public String getAdduser() {
        return adduser;
    }

    public void setAdduser(String adduser) {
        this.adduser = adduser == null ? null : adduser.trim();
    }

    public Date getModtime() {
        if(this.modtime != null){
            return new Date(this.modtime.getTime());
        }else{
            return null;
        }
    }

    public void setModtime(Date modtime) {
        if(modtime != null){
            this.modtime = (Date)modtime.clone();
        }else{
            this.modtime = null;
        }
    }

    public String getModuser() {
        return moduser;
    }

    public void setModuser(String moduser) {
        this.moduser = moduser == null ? null : moduser.trim();
    }

    public Byte getAcctid() {
        return acctid;
    }

    public void setAcctid(Byte acctid) {
        this.acctid = acctid;
    }

    public String getEdmpEdmcName() {
        return edmpEdmcName;
    }

    public void setEdmpEdmcName(String edmpEdmcName) {
        this.edmpEdmcName = edmpEdmcName;
    }

    public String getEdmpEdmcNameEn() {
        return edmpEdmcNameEn;
    }

    public void setEdmpEdmcNameEn(String edmpEdmcNameEn) {
        this.edmpEdmcNameEn = edmpEdmcNameEn;
    }

    public String getEdmpValueName() {
        return edmpValueName;
    }

    public void setEdmpValueName(String edmpValueName) {
        this.edmpValueName = edmpValueName;
    }

    public String getEdmpDataName() {
        return edmpDataName;
    }

    public void setEdmpDataName(String edmpDataName) {
        this.edmpDataName = edmpDataName;
    }

    public String getEdmpEdmmName() {
        return edmpEdmmName;
    }

    public void setEdmpEdmmName(String edmpEdmmName) {
        this.edmpEdmmName = edmpEdmmName;
    }

    public String getEdmpParentName() {
        return edmpParentName;
    }

    public void setEdmpParentName(String edmpParentName) {
        this.edmpParentName = edmpParentName;
    }

    public String getEdmpExtendProperty() {
        return edmpExtendProperty;
    }

    public void setEdmpExtendProperty(String edmpExtendProperty) {
        this.edmpExtendProperty = edmpExtendProperty;
    }
    
    public String getAddtimeStr() {
		return addtimeStr;
	}

	public void setAddtimeStr(String addtimeStr) {
		this.addtimeStr = addtimeStr;
	}

	public String getModtimeStr() {
		return modtimeStr;
	}

	public void setModtimeStr(String modtimeStr) {
		this.modtimeStr = modtimeStr;
	}

    public List<EdmPropertyVO> getChildren() {
        return children;
    }

    public void setChildren(List<EdmPropertyVO> children) {
        this.children = children;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Byte getIsIndex() {
        return isIndex;
    }

    public void setIsIndex(Byte isIndex) {
        this.isIndex = isIndex;
    }

    @Override
    public String getTriggerCond() {
        return triggerCond;
    }

    @Override
    public void setTriggerCond(String triggerCond) {
        this.triggerCond = triggerCond;
    }

    @Override
    public String getEdmpDataType() {
        return edmpDataType;
    }

    @Override
    public void setEdmpDataType(String edmpDataType) {
        this.edmpDataType = edmpDataType;
    }

    public String getCasEdmpCode() {
        return casEdmpCode;
    }

    public void setCasEdmpCode(String casEdmpCode) {
        this.casEdmpCode = casEdmpCode;
    }

    public String getCasEdmpName() {
        return casEdmpName;
    }

    public void setCasEdmpName(String casEdmpName) {
        this.casEdmpName = casEdmpName;
    }

    @Override
    public Byte getIsCharacter() {
        return isCharacter;
    }

    @Override
    public void setIsCharacter(Byte isCharacter) {
        this.isCharacter = isCharacter;
    }

    @Override
    public Byte getIsVisible() {
        return isVisible;
    }

    @Override
    public void setIsVisible(Byte isVisible) {
        this.isVisible = isVisible;
    }


    public String getPropertLimit() {
        return propertLimit;
    }

    public void setPropertLimit(String propertLimit) {
        this.propertLimit = propertLimit;
    }

    public String getPropertLimitDesc() {
        return propertLimitDesc;
    }

    public void setPropertLimitDesc(String propertLimitDesc) {
        this.propertLimitDesc = propertLimitDesc;
    }

    public String getPropertFormula() {
        return propertFormula;
    }

    public void setPropertFormula(String propertFormula) {
        this.propertFormula = propertFormula;
    }

    public boolean isFather() {
        return isFather;
    }

    public void setFather(boolean father) {
        isFather = father;
    }

    public String getEnumName() {
        return enumName;
    }

    public void setEnumName(String enumName) {
        this.enumName = enumName;
    }

    public String getTablename() {
        return tablename;
    }

    public void setTablename(String tablename) {
        this.tablename = tablename == null ? null : tablename.trim();
    }

    @Override
    public String toString() {
        return "EdmPropertyVO{" +
                "id='" + id + '\'' +
                ", edmpEdmcId='" + edmpEdmcId + '\'' +
                ", edmpParentId='" + edmpParentId + '\'' +
                ", edmpCode='" + edmpCode + '\'' +
                ", edmpName='" + edmpName + '\'' +
                ", edmpDesc='" + edmpDesc + '\'' +
                ", edmpValueType='" + edmpValueType + '\'' +
                ", edmpDataType='" + edmpDataType + '\'' +
                ", edmpValueSize='" + edmpValueSize + '\'' +
                ", edmpValueLimit='" + edmpValueLimit + '\'' +
                ", edmpFormula='" + edmpFormula + '\'' +
                ", edmpValue='" + edmpValue + '\'' +
                ", edmpEdmmId='" + edmpEdmmId + '\'' +
                ", edmpObjEdmcId='" + edmpObjEdmcId + '\'' +
                ", edmpSeq=" + edmpSeq +
                ", isIndex=" + isIndex +
                ", isVisible=" + isVisible +
                ", isDefined=" + isDefined +
                ", isDel=" + isDel +
                ", isCharacter=" + isCharacter +
                ", addtime=" + addtime +
                ", adduser='" + adduser + '\'' +
                ", modtime=" + modtime +
                ", moduser='" + moduser + '\'' +
                ", acctid=" + acctid +
                ", edmpExtendProperty='" + edmpExtendProperty + '\'' +
                ", edmpEdmcName='" + edmpEdmcName + '\'' +
                ", edmpEdmcNameEn='" + edmpEdmcNameEn + '\'' +
                ", edmpValueName='" + edmpValueName + '\'' +
                ", edmpDataName='" + edmpDataName + '\'' +
                ", edmpEdmmName='" + edmpEdmmName + '\'' +
                ", edmpParentName='" + edmpParentName + '\'' +
                ", addtimeStr='" + addtimeStr + '\'' +
                ", modtimeStr='" + modtimeStr + '\'' +
                ", level=" + level +
                ", triggerCond='" + triggerCond + '\'' +
                ", children=" + children +
                ", casEdmpCode='" + casEdmpCode + '\'' +
                ", casEdmpName='" + casEdmpName + '\'' +
                ", propertLimit='" + propertLimit + '\'' +
                ", propertLimitDesc='" + propertLimitDesc + '\'' +
                ", propertFormula='" + propertFormula + '\'' +
                ", isFather=" + isFather +
                ", enumName='" + enumName + '\'' +
                ", tablename=" + tablename +
                '}';
    }
}
