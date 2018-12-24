package cy.its.sysCfg.rest.action.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cy.its.com.util.ObjectMapUtils;
import cy.its.sysCfg.rest.action.IMessageAction;
import cy.its.sysCfg.rest.dto.MessageDto;
import cy.its.syscfg.domain.criteria.MessageCriteria;
import cy.its.syscfg.domain.service.IMessageService;

@RestController
@RequestMapping("/sysCfg/MessageAction")
public class MessageAction implements IMessageAction {
	@Autowired
	IMessageService messageService;
	
	@RequestMapping("/getMessageList")
	public Object getMessageList(@ModelAttribute("messageDto") MessageDto messageDto) throws Exception {
		MessageCriteria messageCriteria = new MessageCriteria();
		ObjectMapUtils.parseObject(messageCriteria, messageDto);
		messageCriteria.setOrderName(messageDto.getOrderName());
		messageCriteria.setOrderType(messageDto.getOrderType());
		PageHelper.startPage(messageDto.getPageNumber(),messageDto.getPageSize());  
		Page<Map<String, String>> page = (Page<Map<String, String>>) messageService.getMessageList(messageCriteria); 
		List<Map<String, String>> certificationList = page.getResult() ;
		return parseToJson(page, certificationList);
	}
	
	/**
	 * @Title: parseToJson 
	 * @Description: 转为Json 
	 * @param pageRs
	 * @param obj 
	 * @return JSONObject返回类型
	 * @throws
	 */
	private JSONObject parseToJson(Page<?> pageRs, Object obj) {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("error", "");
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("total", pageRs.getTotal());
		jsonObject.put("rows", obj);
		jsonObj.put("result", jsonObject);
		return jsonObj;
	}
}


