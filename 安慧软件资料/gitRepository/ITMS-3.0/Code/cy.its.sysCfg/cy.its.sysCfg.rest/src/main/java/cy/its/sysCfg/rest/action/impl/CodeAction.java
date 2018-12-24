package cy.its.sysCfg.rest.action.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cy.its.com.util.ObjectMapUtils;
import cy.its.sysCfg.rest.action.ICodeAction;
import cy.its.sysCfg.rest.dto.CodeDto;

import cy.its.sysCfg.rest.dto.CodeTypeTreeDto;
import cy.its.syscfg.domain.model.sysCode.Code;
import cy.its.syscfg.domain.model.sysCode.CodeType;
import cy.its.syscfg.domain.service.ISysCodeService;

@RestController
@RequestMapping("/conCfg/SysCodeAction")
public class CodeAction implements ICodeAction {

	@Autowired
	ISysCodeService sysCodeService;

	@RequestMapping("/getSysCodeTypeData")
	public List<CodeTypeTreeDto> initSysCodeTypeData() throws Exception {
		// 创建公共代码对象
		CodeTypeTreeDto ggdmDto = new CodeTypeTreeDto();
		// 设置Id,文本
		ggdmDto.setId("0");
		ggdmDto.setText("公用代码表");
		// 设置子节点
		ggdmDto.setChildren(new ArrayList<CodeTypeTreeDto>());
		CodeTypeTreeDto xtglDto = new CodeTypeTreeDto();
		xtglDto.setId("1");
		xtglDto.setText("系统管理表");
		xtglDto.setChildren(new ArrayList<CodeTypeTreeDto>());
		CodeTypeTreeDto clckDto = new CodeTypeTreeDto();
		clckDto.setId("2");
		clckDto.setText("车辆查控表");
		clckDto.setChildren(new ArrayList<CodeTypeTreeDto>());
		CodeTypeTreeDto wfmkDto = new CodeTypeTreeDto();
		wfmkDto.setId("3");
		wfmkDto.setText("违法模块表");
		wfmkDto.setChildren(new ArrayList<CodeTypeTreeDto>());
		CodeTypeTreeDto sbywDto = new CodeTypeTreeDto();
		sbywDto.setId("4");
		sbywDto.setText("设备运维表");
		sbywDto.setChildren(new ArrayList<CodeTypeTreeDto>());
		CodeTypeTreeDto lkjkDto = new CodeTypeTreeDto();
		lkjkDto.setId("5");
		lkjkDto.setText("路口监控表");
		lkjkDto.setChildren(new ArrayList<CodeTypeTreeDto>());
	
		 CodeTypeTreeDto xxfbDto = new CodeTypeTreeDto();
		  xxfbDto.setId("6");
		  xxfbDto.setText("信息发布表");
		  xxfbDto.setChildren(new ArrayList<CodeTypeTreeDto>());
		  
		/**
		 * CodeTypeTreeDto zhddDto=new CodeTypeTreeDto(); 
		 * zhddDto.setId("7");
		 * zhddDto.setText("指挥调度表");
		 * zhddDto.setChildren(new ArrayList<CodeTypeTreeDto>()); 
		 * CodeTypeTreeDto qwglDto=new CodeTypeTreeDto();
		 * qwglDto.setId("8"); 
		 * qwglDto.setText("勤务管理表");
		 * qwglDto.setChildren(new ArrayList<CodeTypeTreeDto>());
		 */
		// 返回页面list集合
		List<CodeTypeTreeDto> lstView = new ArrayList<CodeTypeTreeDto>();
		// 调用领域查询方法,返回代码类型lst集合
		List<CodeType> lst = sysCodeService.findCodeTypes();
		// 遍历代码类型中的每一项
		for (CodeType ct : lst) {
			// 判断代码类型Id匹配首字母为0，为一组
			if (ct.getIdentityId().startsWith("0")) {
				// 创建子节点对象
				CodeTypeTreeDto ggdmCDto = new CodeTypeTreeDto();
				ggdmCDto.setId(ct.getIdentityId());// 获得Id
				ggdmCDto.setText(ct.codeTypeName);// 获得文本名
				List<CodeTypeTreeDto> ltP = ggdmDto.getChildren();
				// 将子节点信息添加到子节点文件夹
				ltP.add(ggdmCDto);
			} else if (ct.getIdentityId().startsWith("1")) {
				CodeTypeTreeDto xtglCDto = new CodeTypeTreeDto();
				xtglCDto.setId(ct.getIdentityId());// 获得Id
				xtglCDto.setText(ct.codeTypeName);// 获得文本名
				List<CodeTypeTreeDto> ltS = xtglDto.getChildren();
				// 将子节点信息添加到子节点文件夹
				ltS.add(xtglCDto);
			} else if (ct.getIdentityId().startsWith("2")) {
				CodeTypeTreeDto clckCDto = new CodeTypeTreeDto();
				clckCDto.setId(ct.getIdentityId());// 获得Id
				clckCDto.setText(ct.codeTypeName);// 获得文本名
				List<CodeTypeTreeDto> ltB = clckDto.getChildren();
				// 将子节点信息添加到子节点文件夹
				ltB.add(clckCDto);
			} else if (ct.getIdentityId().startsWith("3")) {
				CodeTypeTreeDto wfmkCDto = new CodeTypeTreeDto();
				wfmkCDto.setId(ct.getIdentityId());// 获得Id
				wfmkCDto.setText(ct.codeTypeName);// 获得文本名
				List<CodeTypeTreeDto> ltL = wfmkDto.getChildren();
				// 将子节点信息添加到子节点文件夹
				ltL.add(wfmkCDto);
			} else if (ct.getIdentityId().startsWith("4")) {
				CodeTypeTreeDto sbywCDto = new CodeTypeTreeDto();
				sbywCDto.setId(ct.getIdentityId());// 获得Id
				sbywCDto.setText(ct.codeTypeName);// 获得文本名
				List<CodeTypeTreeDto> ltD = sbywDto.getChildren();
				// 将子节点信息添加到子节点文件夹
				ltD.add(sbywCDto);
			} else if(ct.getIdentityId().startsWith("5")){
				CodeTypeTreeDto lkjkCDto = new CodeTypeTreeDto();
				lkjkCDto.setId(ct.getIdentityId());// 获得Id
				lkjkCDto.setText(ct.codeTypeName);
				List<CodeTypeTreeDto> ltlkjk = lkjkDto.getChildren();
				// 将子节点信息添加到子节点文件夹
				ltlkjk.add(lkjkCDto);
			} else if(ct.getIdentityId().startsWith("6")){
				CodeTypeTreeDto xxfbCDto = new CodeTypeTreeDto();
				xxfbCDto.setId(ct.getIdentityId());// 获得Id
				xxfbCDto.setText(ct.codeTypeName);
				List<CodeTypeTreeDto> ltxxfb = xxfbDto.getChildren();
				// 将子节点信息添加到子节点文件夹
				ltxxfb.add(xxfbCDto);
			} 
		}
		lstView.add(ggdmDto);
		lstView.add(xtglDto);
		lstView.add(clckDto);
		lstView.add(wfmkDto);
		lstView.add(sbywDto);
		lstView.add(lkjkDto);
		lstView.add(xxfbDto);
		return lstView;
	}

	/**
	 * 查询操作
	 */
	@RequestMapping("/selectAllCode")
	@Override
	public Map<String, Object> selectAllCodeType(CodeDto codeDto) throws Exception {
		// 查询Code信息列表
		List<Code> list = sysCodeService.codeListOfType(codeDto.getCodeType());
		// 把查询结果转换成查询列表行
		ArrayList<CodeDto> lstView = new ArrayList<CodeDto>();
		// 遍历list
		if(list !=null){
			for (Code lst : list) {
				CodeDto codeDt = new CodeDto();
				// 转换查询结果行的其他列
				ObjectMapUtils.parseObject(codeDt, lst);
				lstView.add(codeDt);
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("error", "");
		Map<String, Serializable> maprow = new HashMap<String, Serializable>();
		maprow.put("rows", lstView);
		maprow.put("total", lstView.size());
		map.put("result", maprow);
		return map;

	}

	/**
	 * 删除系统代码操作
	 */
	@RequestMapping("/deleteCode")
	@Override
	public int deleteCode(@RequestParam("codeIdStr") String codeIdStr) {
				
				String[] idarr = codeIdStr.split(",");
				
				//通过Arrays.asList将字符串数组转化为List<String>集合对象

				List<String> idList = Arrays.asList(idarr);

				Map<String,Object> map = new HashMap<String,Object>();

				//将集合对象放置到HashMap中

				map.put("idList", idList);

				//调用mysqlbatis删除方法

				sysCodeService.deleteCode(map);
				// 变更通知
				sysCodeService.sysCodeChanged();;
								
				return 1;
	}

	/**
	 * 创建系统代码操作
	 */
	@Override
	@RequestMapping("/saveCode")
	public String createCode(CodeDto codeDto) {
		// 实例化Code对象
		Code code = new Code(codeDto.getCodeNo(), codeDto.getCodeType(), codeDto.getCodeName(), codeDto.getRemark(),
				codeDto.getEnableFlag());
		code.editable = codeDto.getEditable();

		saveCode(code);// 调用saveCode方法;创建系统代码

		sysCodeService.sysCodeChanged();// 变更通知

		return "success";
	}

	private void saveCode(Code code) {

		sysCodeService.createCode(code);

	}
	/**
	 * 单个删除操作
	 */
	@RequestMapping("/removeCode")
	@Override
	public String removeCode(@RequestParam("codeId") String codeId) {
		
		sysCodeService.removeCode(codeId);// 删除系统代码
		
		sysCodeService.sysCodeChanged();// 变更通知
		
		return "success";
	}

	/**
	 * 更新操作
	 */
	@RequestMapping("/updateCode")
	@Override
	public String goUpdateCode(@ModelAttribute(value = "codeId") CodeDto codeDto) {

		Code code = new Code(codeDto.getCodeId(), codeDto.getCodeNo(), codeDto.getCodeType(), codeDto.getCodeName(),
				codeDto.getRemark(), codeDto.getEnableFlag());
		code.editable = codeDto.getEditable();
	
		updateCode(code);// 调用updateCode方法;更新系统代码

		sysCodeService.sysCodeChanged();// 变更通知

		return "success";
	}

	private void updateCode(Code code) {
		sysCodeService.updateCode(code);
	}

	@RequestMapping("/testValidate")
	public Boolean testValidate(String paramName){
		return new Boolean(paramName);
	}

}
