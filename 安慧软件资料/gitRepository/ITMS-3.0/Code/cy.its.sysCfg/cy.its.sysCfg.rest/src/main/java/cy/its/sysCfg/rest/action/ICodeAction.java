package cy.its.sysCfg.rest.action;

import java.util.List;
import java.util.Map;

import cy.its.sysCfg.rest.dto.CodeDto;
import cy.its.sysCfg.rest.dto.CodeTypeTreeDto;

public interface ICodeAction {
	//CodeType树
	public List<CodeTypeTreeDto> initSysCodeTypeData()throws Exception;
	//更新
	public String goUpdateCode(CodeDto codeDto);
	//批量删除
	public int deleteCode(String codeIdStr);
	//查询
	Map<String,Object> selectAllCodeType(CodeDto codeDto) throws Exception;
	//创建系统代码
	String createCode(CodeDto codeDto);
	//单个删除
	String  removeCode(String codeId);
	
}
