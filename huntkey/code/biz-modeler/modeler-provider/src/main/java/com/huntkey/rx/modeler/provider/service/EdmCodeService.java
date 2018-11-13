package com.huntkey.rx.modeler.provider.service;


import com.huntkey.rx.modeler.common.model.EdmCode;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface EdmCodeService {

	List<EdmCode> queryEdmCodeListByCodeType(String codeType);

	List<EdmCode> queryIndustries();

	List<EdmCode> getDataTypeByPropertyValue(String codeValue);

	Map<String,List<EdmCode>> getDictsByCodes(String [] codeTypes);

	List<EdmCode> getTreeData(String codeType);

}