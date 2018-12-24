package cy.its.syscfg.domain.model.sysCode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import cy.its.com.domain.AggregateRoot;

public class CodeType extends AggregateRoot {

	/**
	 * 代码类型
	 */
	private java.lang.String codeType;

	/**
	 * 类型名称
	 */
	public java.lang.String codeTypeName;

	/**
	 * 编辑标识
	 */
	public java.lang.String editable;

	/**
	 * 启用标识
	 */
	public java.lang.String enableFlag;

	/**
	 * 英文名称
	 */
	public java.lang.String englishName;

	/**
	 * 正则表达式
	 */
	public java.lang.String regex;

	/**
	 * 系统代码列表
	 */
	private Map<String, Code> codeMap;

	public CodeType(String codeType, String codeTypeName, List<Code> codeList) {
		this.codeType = codeType;
		this.codeTypeName = codeTypeName;

		codeMap = new HashMap<String, Code>();

		if (codeList != null) {
			try {
				for (Code code : codeList) {
					if(!codeMap.containsKey(code.getCodeNo())){
						codeMap.put(code.getCodeNo(), code);
					}
				}
			} catch (Exception e) {
				System.out.println(1);
			}
//			codeMap = codeList.stream().collect(
//					Collectors.toMap(Code::getCodeNo, (c) -> c));
		}
	}

	@Override
	public String getIdentityId() {
		return codeType;
	}

	public List<Code> codeList() {
		return codeMap != null ? codeMap.values().stream()
				.collect(Collectors.toList()) : null;
	}
	
	public Code code(String codeNo) {
		return codeMap != null && 
				codeMap.containsKey(codeNo)? codeMap.get(codeNo):null;
	}
}
