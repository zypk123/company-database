package com.huntkey.rx.modeler.common.util;

import com.huntkey.rx.commons.utils.datetime.DateUtil;
import com.huntkey.rx.modeler.common.model.*;
import com.huntkey.rx.modeler.common.model.vo.*;

/**
 * Created by licj on 2017/5/2.
 */
public class  ModelToVO {

	public static EdmAttachmentVO edmAttachmentToVO(EdmAttachment edmAttachment) {
		EdmAttachmentVO edmAttachmentVO = new EdmAttachmentVO();
		edmAttachmentVO.setId(edmAttachment.getId());
		edmAttachmentVO.setEdmaEdmcId(edmAttachment.getEdmaEdmcId());
		edmAttachmentVO.setEdmaType(edmAttachment.getEdmaType());
		edmAttachmentVO.setEdmaName(edmAttachment.getEdmaName());
		edmAttachmentVO.setEdmaSourceName(edmAttachment.getEdmaSourceName());
		edmAttachmentVO.setEdmaPath(edmAttachment.getEdmaPath());
		edmAttachmentVO.setEdmaSeq(edmAttachment.getEdmaSeq());
		edmAttachmentVO.setIsDel(edmAttachment.getIsDel());
		edmAttachmentVO.setAddtime(edmAttachment.getAddtime());
		edmAttachmentVO.setAdduser(edmAttachment.getAdduser());
		edmAttachmentVO.setModtime(edmAttachment.getModtime());
		edmAttachmentVO.setModuser(edmAttachment.getModuser());
		edmAttachmentVO.setAcctid(edmAttachment.getAcctid());
		if (null != edmAttachment.getAddtime()) {
			edmAttachmentVO.setAddtimeStr(DateUtil.formatDate(edmAttachment.getAddtime()));
		}
		if (null != edmAttachment.getModtime()) {
			edmAttachmentVO.setModtimeStr(DateUtil.formatDate(edmAttachment.getModtime()));
		}
		return edmAttachmentVO;
	}

	public static EdmClassVO edmClassToVO(EdmClass edmClass) {
		EdmClassVO edmClassVO = new EdmClassVO();
		edmClassVO.setId(edmClass.getId());
		edmClassVO.setEdmcEdmdId(edmClass.getEdmcEdmdId());
		edmClassVO.setEdmcCode(edmClass.getEdmcCode());
		edmClassVO.setEdmcName(edmClass.getEdmcName());
		edmClassVO.setEdmcNameEn(edmClass.getEdmcNameEn());
		edmClassVO.setEdmcShortName(edmClass.getEdmcShortName());
		edmClassVO.setEdmcParentId(edmClass.getEdmcParentId());
		edmClassVO.setEdmcVer(edmClass.getEdmcVer());
		edmClassVO.setEdmcPrincipal(edmClass.getEdmcPrincipal());
		edmClassVO.setEdmcUseDesc(edmClass.getEdmcUseDesc());
		edmClassVO.setEdmcLevel(edmClass.getEdmcLevel());
		edmClassVO.setEdmcIndustryCode(edmClass.getEdmcIndustryCode());
		edmClassVO.setEdmcSeq(edmClass.getEdmcSeq());
		edmClassVO.setIsDefined(edmClass.getIsDefined());
		edmClassVO.setIsDel(edmClass.getIsDel());
		edmClassVO.setIsEntity(edmClass.getIsEntity());
		edmClassVO.setDatabaseType(edmClass.getDatabaseType());
		edmClassVO.setAddtime(edmClass.getAddtime());
		edmClassVO.setAdduser(edmClass.getAdduser());
		edmClassVO.setModtime(edmClass.getModtime());
		edmClassVO.setModuser(edmClass.getModuser());
		edmClassVO.setAcctid(edmClass.getAcctid());
		edmClassVO.setObjectOnCloud(edmClass.getObjectOnCloud());
		edmClassVO.setNormalPresent(edmClass.getNormalPresent());
		edmClassVO.setEdmcShowId(edmClass.getEdmcShowId());
		edmClassVO.setTablename(edmClass.getTablename());
		if (null != edmClass.getAddtime()) {
			edmClassVO.setAddtimeStr(DateUtil.formatDate(edmClass.getAddtime()));
		}
		if (null != edmClass.getModtime()) {
			edmClassVO.setModtimeStr(DateUtil.formatDate(edmClass.getModtime()));
		}
		return edmClassVO;
	}

	public static EdmConnectVO edmConnectToVO(EdmConnect edmConnect) {
		EdmConnectVO edmConnectVO = new EdmConnectVO();
		edmConnectVO.setId(edmConnect.getId());
		edmConnectVO.setEdcnEdmpId(edmConnect.getEdcnEdmpId());
		edmConnectVO.setEdcnLinkPreservable(edmConnect.getEdcnLinkPreservable());
		edmConnectVO.setEdcnUpdateType(edmConnect.getEdcnUpdateType());
		edmConnectVO.setEdcnUpdateTime(edmConnect.getEdcnUpdateTime());
		edmConnectVO.setAsyncTypePriority(edmConnect.getAsyncTypePriority());
		edmConnectVO.setIsDel(edmConnect.getIsDel());
		edmConnectVO.setAddtime(edmConnect.getAddtime());
		edmConnectVO.setAdduser(edmConnect.getAdduser());
		edmConnectVO.setModtime(edmConnect.getModtime());
		edmConnectVO.setModuser(edmConnect.getModuser());
		edmConnectVO.setAcctid(edmConnect.getAcctid());
		if (null != edmConnect.getAddtime()) {
			edmConnectVO.setAddtimeStr(DateUtil.formatDate(edmConnect.getAddtime()));
		}
		if (null != edmConnect.getModtime()) {
			edmConnectVO.setModtimeStr(DateUtil.formatDate(edmConnect.getModtime()));
		}
		return edmConnectVO;
	}

	public static EdmConvoluteVO edmConvoluteToVO(EdmConvolute edmConvolute) {
		EdmConvoluteVO edmConvoluteVO = new EdmConvoluteVO();
		edmConvoluteVO.setId(edmConvolute.getId());
		edmConvoluteVO.setEdcoEdmpId(edmConvolute.getEdcoEdmpId());
		edmConvoluteVO.setEdcoConvoluteFormula(edmConvolute.getEdcoConvoluteFormula());
		edmConvoluteVO.setEdcoFormulaDesc(edmConvolute.getEdcoFormulaDesc());
		edmConvoluteVO.setIsDel(edmConvolute.getIsDel());
		edmConvoluteVO.setAddtime(edmConvolute.getAddtime());
		edmConvoluteVO.setAdduser(edmConvolute.getAdduser());
		edmConvoluteVO.setModtime(edmConvolute.getModtime());
		edmConvoluteVO.setModuser(edmConvolute.getModuser());
		edmConvoluteVO.setAcctid(edmConvolute.getAcctid());
		if (null != edmConvolute.getAddtime()) {
			edmConvoluteVO.setAddtimeStr(DateUtil.formatDate(edmConvolute.getAddtime()));
		}
		if (null != edmConvolute.getModtime()) {
			edmConvoluteVO.setModtimeStr(DateUtil.formatDate(edmConvolute.getModtime()));
		}
		return edmConvoluteVO;
	}

	public static EdmLinkVO edmLinkToVO(EdmLink edmLink) {
		EdmLinkVO edmLinkVO = new EdmLinkVO();
		edmLinkVO.setId(edmLink.getId());
		edmLinkVO.setEdmlEdmpId(edmLink.getEdmlEdmpId());
		edmLinkVO.setEdmlEdmpLinkId(edmLink.getEdmlEdmpLinkId());
		edmLinkVO.setEdmlCond(edmLink.getEdmlCond());
		edmLinkVO.setEdmlFormula(edmLink.getEdmlFormula());
		edmLinkVO.setIsDel(edmLink.getIsDel());
		edmLinkVO.setAddtime(edmLink.getAddtime());
		edmLinkVO.setAdduser(edmLink.getAdduser());
		edmLinkVO.setModtime(edmLink.getModtime());
		edmLinkVO.setModuser(edmLink.getModuser());
		edmLinkVO.setAcctid(edmLink.getAcctid());
		if (null != edmLink.getAddtime()) {
			edmLinkVO.setAddtimeStr(DateUtil.formatDate(edmLink.getAddtime()));
		}
		if (null != edmLink.getModtime()) {
			edmLinkVO.setModtimeStr(DateUtil.formatDate(edmLink.getModtime()));
		}
		return edmLinkVO;
	}

	public static EdmMethodArgVO edmMethodArgToVO(EdmMethodArg edmMethodArg) {
		EdmMethodArgVO edmMethodArgVO = new EdmMethodArgVO();
		edmMethodArgVO.setId(edmMethodArg.getId());
		edmMethodArgVO.setEdmaEdmmId(edmMethodArg.getEdmaEdmmId());
		edmMethodArgVO.setEdmaType(edmMethodArg.getEdmaType());
		edmMethodArgVO.setEdmaDataType(edmMethodArg.getEdmaDataType());
		edmMethodArgVO.setEdmaName(edmMethodArg.getEdmaName());
		edmMethodArgVO.setEdmaDesc(edmMethodArg.getEdmaDesc());
		edmMethodArgVO.setEdmaSeq(edmMethodArg.getEdmaSeq());
		edmMethodArgVO.setIsDel(edmMethodArg.getIsDel());
		edmMethodArgVO.setAddtime(edmMethodArg.getAddtime());
		edmMethodArgVO.setAdduser(edmMethodArg.getAdduser());
		edmMethodArgVO.setModtime(edmMethodArg.getModtime());
		edmMethodArgVO.setModuser(edmMethodArg.getModuser());
		edmMethodArgVO.setAcctid(edmMethodArg.getAcctid());
		edmMethodArgVO.setIsEdit(0);
		if (null != edmMethodArg.getAddtime()) {
			edmMethodArgVO.setAddtimeStr(DateUtil.formatDate(edmMethodArg.getAddtime()));
		}
		if (null != edmMethodArg.getModtime()) {
			edmMethodArgVO.setModtimeStr(DateUtil.formatDate(edmMethodArg.getModtime()));
		}
		return edmMethodArgVO;
	}

	public static EdmMethodTypeVO edmMethodTypeToVO(EdmMethodType edmMethodType) {
		EdmMethodTypeVO edmMethodTypeVO = new EdmMethodTypeVO();
		edmMethodTypeVO.setId(edmMethodType.getId());
		edmMethodTypeVO.setEdmtParentId(edmMethodType.getEdmtParentId());
		edmMethodTypeVO.setEdmtCode(edmMethodType.getEdmtCode());
		edmMethodTypeVO.setEdmtName(edmMethodType.getEdmtName());
		edmMethodTypeVO.setEdmtDesc(edmMethodType.getEdmtDesc());
		edmMethodTypeVO.setEdmtSeq(edmMethodType.getEdmtSeq());
		edmMethodTypeVO.setIsDel(edmMethodType.getIsDel());
		edmMethodTypeVO.setAdduser(edmMethodType.getAdduser());
		edmMethodTypeVO.setAddtime(edmMethodType.getAddtime());
		edmMethodTypeVO.setModtime(edmMethodType.getModtime());
		edmMethodTypeVO.setModuser(edmMethodType.getModuser());
		edmMethodTypeVO.setAcctid(edmMethodType.getAcctid());
		if (null != edmMethodType.getAddtime()) {
			edmMethodTypeVO.setAddtimeStr(DateUtil.formatDate(edmMethodType.getAddtime()));
		}
		if (null != edmMethodType.getModtime()) {
			edmMethodTypeVO.setModtimeStr(DateUtil.formatDate(edmMethodType.getModtime()));
		}
		return edmMethodTypeVO;
	}

	public static EdmMethodVO edmMethodToVO(EdmMethod edmMethod) {
		EdmMethodVO edmMethodVO = new EdmMethodVO();
		edmMethodVO.setId(edmMethod.getId());
		edmMethodVO.setEdmmType(edmMethod.getEdmmType());
		edmMethodVO.setIsCover(edmMethod.getIsCover());
		edmMethodVO.setEdmmName(edmMethod.getEdmmName());
		edmMethodVO.setEdmmDesc(edmMethod.getEdmmDesc());
		edmMethodVO.setEdmmProgramType(edmMethod.getEdmmProgramType());
		edmMethodVO.setEdmmProgramStorage(edmMethod.getEdmmProgramStorage());
		edmMethodVO.setEdmmProgramSourceName(edmMethod.getEdmmProgramSourceName());
		edmMethodVO.setEdmmTriggerCond(edmMethod.getEdmmTriggerCond());
		edmMethodVO.setEdmmVer(edmMethod.getEdmmVer());
		edmMethodVO.setEdmmDevelopDept(edmMethod.getEdmmDevelopDept());
		edmMethodVO.setEdmmProgrammer(edmMethod.getEdmmProgrammer());
		edmMethodVO.setEdmmSeq(edmMethod.getEdmmSeq());
		edmMethodVO.setEdmmStatus(edmMethod.getEdmmStatus());
		edmMethodVO.setEdmmUpdateDesc(edmMethod.getEdmmUpdateDesc());
		edmMethodVO.setIsDefined(edmMethod.getIsDefined());
		edmMethodVO.setIsDel(edmMethod.getIsDel());
		edmMethodVO.setAdduser(edmMethod.getAdduser());
		edmMethodVO.setAddtime(edmMethod.getAddtime());
		edmMethodVO.setModtime(edmMethod.getModtime());
		edmMethodVO.setModuser(edmMethod.getModuser());
		edmMethodVO.setAcctid(edmMethod.getAcctid());
		edmMethodVO.setEdmmArithmeticDesc(edmMethod.getEdmmArithmeticDesc());
		edmMethodVO.setEdmmArithmeticStorage(edmMethod.getEdmmArithmeticStorage());
		edmMethodVO.setEdmmArithmeticSourceName(edmMethod.getEdmmArithmeticSourceName());
		edmMethodVO.setEdmmRequestType(edmMethod.getEdmmRequestType());
		edmMethodVO.setEdmmMethodType(edmMethod.getEdmmMethodType());
		edmMethodVO.setEdmmExecRate(edmMethod.getEdmmExecRate());
		edmMethodVO.setEdmmPlanId(edmMethod.getEdmmPlanId());
		edmMethodVO.setEdmmJobId(edmMethod.getEdmmJobId());
		edmMethodVO.setEdmmEdmcId(edmMethod.getEdmmEdmcId());
		edmMethodVO.setEdmmExecuteType(edmMethod.getEdmmExecuteType());
		edmMethodVO.setTimeout(edmMethod.getTimeout());
		if (null != edmMethod.getAddtime()) {
			edmMethodVO.setAddtimeStr(DateUtil.formatDate(edmMethod.getAddtime()));
		}
		if (null != edmMethod.getModtime()) {
			edmMethodVO.setModtimeStr(DateUtil.formatDate(edmMethod.getModtime()));
		}
		return edmMethodVO;
	}

	public static EdmModelerVO edmModelerToVO(EdmModeler edmModeler) {
		EdmModelerVO edmModelerVO = new EdmModelerVO();
		edmModelerVO.setId(edmModeler.getId());
		edmModelerVO.setEdmdParentId(edmModeler.getEdmdParentId());
		edmModelerVO.setEdmdCode(edmModeler.getEdmdCode());
		edmModelerVO.setEdmdVer(edmModeler.getEdmdVer());
		edmModelerVO.setEdmdUpdateDesc(edmModeler.getEdmdUpdateDesc());
		edmModelerVO.setEdmdStatus(edmModeler.getEdmdStatus());
		if(edmModeler.getEdmdReleaseTime()!=null){
			edmModelerVO.setEdmdReleaseTimeStr(DateUtil.formatDate(edmModeler.getEdmdReleaseTime()));
		}
		if(edmModeler.getEdmdExpireTime()!=null){
			edmModelerVO.setEdmdExpireTimeStr(DateUtil.formatDate(edmModeler.getEdmdExpireTime()));
		}
		//edmModelerVO.setEdmdReleaseTime(edmModeler.getEdmdReleaseTime());
		//edmModelerVO.setEdmdExpireTime(edmModeler.getEdmdExpireTime());
		edmModelerVO.setIsDel(edmModeler.getIsDel());
		edmModelerVO.setAdduser(edmModeler.getAdduser());
		edmModelerVO.setAddtime(edmModeler.getAddtime());
		edmModelerVO.setModtime(edmModeler.getModtime());
		edmModelerVO.setModuser(edmModeler.getModuser());
		edmModelerVO.setAcctid(edmModeler.getAcctid());
		if (null != edmModeler.getAddtime()) {
			edmModelerVO.setAddtimeStr(DateUtil.formatDate(edmModeler.getAddtime()));
		}
		if (null != edmModeler.getModtime()) {
			edmModelerVO.setModtimeStr(DateUtil.formatDate(edmModeler.getModtime()));
		}
		return edmModelerVO;
	}

	public static EdmClassFormatVO edmClassFormatToVO(EdmClassFormat edmClassFormat) {
		EdmClassFormatVO edmClassFormatVO = new EdmClassFormatVO();
		edmClassFormatVO.setId(edmClassFormat.getId());
		edmClassFormatVO.setEdmfEdmcId(edmClassFormat.getEdmfEdmcId());
		edmClassFormatVO.setEdmfEdmpId(edmClassFormat.getEdmfEdmpId());
		edmClassFormatVO.setEdmfConnector(edmClassFormat.getEdmfConnector());
		edmClassFormatVO.setEdmfSeq(edmClassFormat.getEdmfSeq());
		edmClassFormatVO.setIsDel(edmClassFormat.getIsDel());
		edmClassFormatVO.setAdduser(edmClassFormat.getAdduser());
		edmClassFormatVO.setAddtime(edmClassFormat.getAddtime());
		edmClassFormatVO.setModtime(edmClassFormat.getModtime());
		edmClassFormatVO.setModuser(edmClassFormat.getModuser());
		edmClassFormatVO.setAcctid(edmClassFormat.getAcctid());
		if (null != edmClassFormat.getAddtime()) {
			edmClassFormatVO.setAddTimeStr(DateUtil.parseFormatDate(edmClassFormat.getAddtime()));
		}
		return edmClassFormatVO;
	}

	public static EdmPropertyVO edmPropertyToVO(EdmProperty edmProperty) {
		EdmPropertyVO edmPropertyVO = new EdmPropertyVO();
		edmPropertyVO.setId(edmProperty.getId());
		edmPropertyVO.setEdmpEdmcId(edmProperty.getEdmpEdmcId());
		edmPropertyVO.setEdmpParentId(edmProperty.getEdmpParentId());
		edmPropertyVO.setEdmpCode(edmProperty.getEdmpCode());
		edmPropertyVO.setEdmpName(edmProperty.getEdmpName());
		edmPropertyVO.setEdmpDesc(edmProperty.getEdmpDesc());
		edmPropertyVO.setEdmpValueType(edmProperty.getEdmpValueType());
		edmPropertyVO.setEdmpValueSize(edmProperty.getEdmpValueSize());
		edmPropertyVO.setEdmpValueLimit(edmProperty.getEdmpValueLimit());
		edmPropertyVO.setEdmpFormula(edmProperty.getEdmpFormula());
		edmPropertyVO.setEdmpValue(edmProperty.getEdmpValue());
		edmPropertyVO.setEdmpEdmmId(edmProperty.getEdmpEdmmId());
		edmPropertyVO.setEdmpDataType(edmProperty.getEdmpDataType());
		edmPropertyVO.setEdmpObjEdmcId(edmProperty.getEdmpObjEdmcId());
		edmPropertyVO.setTriggerCond(edmProperty.getTriggerCond());
		edmPropertyVO.setEdmpSeq(edmProperty.getEdmpSeq());
		edmPropertyVO.setIsIndex(edmProperty.getIsIndex());
		edmPropertyVO.setIsDefined(edmProperty.getIsDefined());
		edmPropertyVO.setIsVisible(edmProperty.getIsVisible());
		edmPropertyVO.setIsDel(edmProperty.getIsDel());
		edmPropertyVO.setIsCharacter(edmProperty.getIsCharacter());
		edmPropertyVO.setAdduser(edmProperty.getAdduser());
		edmPropertyVO.setAddtime(edmProperty.getAddtime());
		edmPropertyVO.setModtime(edmProperty.getModtime());
		edmPropertyVO.setModuser(edmProperty.getModuser());
		edmPropertyVO.setAcctid(edmProperty.getAcctid());
		edmPropertyVO.setTablename(edmProperty.getTablename());
		if (null != edmProperty.getAddtime()) {
			edmPropertyVO.setAddtimeStr(DateUtil.formatDate(edmProperty.getAddtime()));
		}
		if (null != edmProperty.getModtime()) {
			edmPropertyVO.setModtimeStr(DateUtil.formatDate(edmProperty.getModtime()));
		}
		return edmPropertyVO;
	}

	public static EdmUnitVO edmUnitToVO(EdmUnit edmUnit) {
		EdmUnitVO edmUnitVO = new EdmUnitVO();
		edmUnitVO.setId(edmUnit.getId());
		edmUnitVO.setEdunEdmpId(edmUnit.getEdunEdmpId());
		edmUnitVO.setEdunQtyEdmpId(edmUnit.getEdunQtyEdmpId());
		edmUnitVO.setIsDel(edmUnit.getIsDel());
		edmUnitVO.setAdduser(edmUnit.getAdduser());
		edmUnitVO.setAddtime(edmUnit.getAddtime());
		edmUnitVO.setModtime(edmUnit.getModtime());
		edmUnitVO.setModuser(edmUnit.getModuser());
		edmUnitVO.setAcctid(edmUnit.getAcctid());
		if (null != edmUnit.getAddtime()) {
			edmUnitVO.setAddtimeStr(DateUtil.formatDate(edmUnit.getAddtime()));
		}
		if (null != edmUnit.getModtime()) {
			edmUnitVO.setModtimeStr(DateUtil.formatDate(edmUnit.getModtime()));
		}
		return edmUnitVO;
	}

	/**
	 * EdmLink 与 EdmConnect 组成EdmLinkVO 扩展
	 * @param edmLink
	 * @param edmConnect
	 * @return
	 */
	public static EdmLinkVO edmConnectAndLinkToLinkVO(EdmLink edmLink,EdmConnect edmConnect){
		EdmLinkVO edmLinkVO = new EdmLinkVO();
		//关联表数据给扩展赋值
		if(edmLink != null){
			edmLinkVO.setId(edmLink.getId());
			edmLinkVO.setEdmlEdmpId(edmLink.getEdmlEdmpId());
			edmLinkVO.setEdmlEdmpLinkId(edmLink.getEdmlEdmpLinkId());
			edmLinkVO.setEdmlCond(edmLink.getEdmlCond());
			edmLinkVO.setEdmlFormula(edmLink.getEdmlFormula());
			//用link 表的时间做记录
			edmLinkVO.setIsDel(edmLink.getIsDel());
			edmLinkVO.setAddtime(edmLink.getAddtime());
			edmLinkVO.setAdduser(edmLink.getAdduser());
			edmLinkVO.setModtime(edmLink.getModtime());
			edmLinkVO.setModuser(edmLink.getModuser());
			edmLinkVO.setAcctid(edmLink.getAcctid());
		}
		//联动表数据给扩展赋值
		if (edmConnect != null){
			edmLinkVO.setEdmConnectId(edmConnect.getId());
			edmLinkVO.setEdcnLinkPreservable(edmConnect.getEdcnLinkPreservable());
			edmLinkVO.setEdclUpdateType(edmConnect.getEdcnUpdateType());
			edmLinkVO.setEdclUpdateTime(edmConnect.getEdcnUpdateTime());
		}
		//补充时间
		if (null != edmLink.getAddtime()) {
			edmLinkVO.setAddtimeStr(DateUtil.formatDate(edmLink.getAddtime()));
		}
		if (null != edmLink.getModtime()) {
			edmLinkVO.setModtimeStr(DateUtil.formatDate(edmLink.getModtime()));
		}
		return edmLinkVO;
	}

	public static EdmCondVO edmCondToVO(EdmCond edmCond) {
		EdmCondVO edmCondVO = new EdmCondVO();
		edmCondVO.setId(edmCond.getId());
		edmCondVO.setEdcoEdmpId(edmCond.getEdcoEdmpId());
		edmCondVO.setEdcoCond(edmCond.getEdcoCond());
		edmCondVO.setIsDel(edmCond.getIsDel());
		edmCondVO.setAdduser(edmCond.getAdduser());
		edmCondVO.setAddtime(edmCond.getAddtime());
		edmCondVO.setModtime(edmCond.getModtime());
		edmCondVO.setModuser(edmCond.getModuser());
		edmCondVO.setAcctid(edmCond.getAcctid());
		if (null != edmCond.getAddtime()) {
			edmCondVO.setAddtimeStr(DateUtil.formatDate(edmCond.getAddtime()));
		}
		if (null != edmCond.getModtime()) {
			edmCondVO.setModtimeStr(DateUtil.formatDate(edmCond.getModtime()));
		}
		return edmCondVO;
	}

	public static EdmIndexVO edmIndexToVO(EdmIndex edmIndex) {
		EdmIndexVO edmIndexVO = new EdmIndexVO();
		edmIndexVO.setId(edmIndex.getId());
		edmIndexVO.setEdmcId(edmIndex.getEdmcId());
		edmIndexVO.setEdmpAssId(edmIndex.getEdmpAssId());
		edmIndexVO.setIsDel(edmIndex.getIsDel());
		edmIndexVO.setAdduser(edmIndex.getAdduser());
		edmIndexVO.setAddtime(edmIndex.getAddtime());
		edmIndexVO.setModtime(edmIndex.getModtime());
		edmIndexVO.setModuser(edmIndex.getModuser());
		edmIndexVO.setAcctid(edmIndex.getAcctid());
		edmIndexVO.setIndexName(edmIndex.getIndexName());
		edmIndexVO.setIndexType(edmIndex.getIndexType());
		edmIndexVO.setIndexVer(edmIndex.getIndexVer());
		if (null != edmIndex.getAddtime()) {
			edmIndexVO.setAddtimeStr(DateUtil.formatDate(edmIndex.getAddtime()));
		}
		if (null != edmIndex.getModtime()) {
			edmIndexVO.setModtimeStr(DateUtil.formatDate(edmIndex.getModtime()));
		}
		return edmIndexVO;
	}
}


