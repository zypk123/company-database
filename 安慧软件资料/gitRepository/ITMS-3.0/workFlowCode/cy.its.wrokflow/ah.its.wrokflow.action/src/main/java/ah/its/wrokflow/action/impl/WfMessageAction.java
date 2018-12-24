package ah.its.wrokflow.action.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ah.its.workFlow.util.CodeGenUtil;
import ah.its.wrokflow.action.WfMessageActionI;
import ah.its.wrokflow.domain.WfMessageDomainI;
import ah.its.wrokflow.domain.util.ReturnResultUtil;
import ah.its.wrokflow.dto.Message;
import ah.its.wrokflow.repository.dao.WfMessage;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * @Title: WfMessageAction.java
 * @Package ah.its.wrokflow.action.impl
 * @Description: 通知公告controller
 * @author chengj@cychina.cn
 * @version V1.0
 */
@RestController
@RequestMapping("/workFlow/wfMessageService")
public class WfMessageAction implements WfMessageActionI {

	@Autowired
	private WfMessageDomainI wfMessageDomainImpl;

	@Override
	@RequestMapping(value = "/queryMessageById/{messageId}")
	public Map queryMessageById(@PathVariable String messageId) {
		WfMessage message = wfMessageDomainImpl.queryMessageById(messageId);
		return ReturnResultUtil.returnSuccessResult(message);
	}

	@Override
	@RequestMapping(value = "/queryMessagesByRecord")
	public Map queryMessagesByRecord(@RequestBody Message message) {
		WfMessage wfmessage = JSONObject.parseObject(
				JSONObject.toJSONString(message), WfMessage.class);
		PageHelper.startPage(message.getStartPage(), message.getPageSize());
		List<WfMessage> list = wfMessageDomainImpl
				.queryMessagesByRecord(wfmessage);
		PageInfo page = new PageInfo(list);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", list);
		result.put("totalsize", page.getTotal());
		return ReturnResultUtil.returnSuccessResult(result);
	}

	@Override
	@RequestMapping(value = "/addMessage")
	public Map addMessage(@RequestBody Message message) {
		WfMessage wfmessage = JSONObject.parseObject(
				JSONObject.toJSONString(message), WfMessage.class);
		wfmessage.setMessageId(CodeGenUtil.getUDDICode());
		wfmessage.setMessageTime(new Date());
		int i = wfMessageDomainImpl.addMessage(wfmessage);
		return ReturnResultUtil.returnSuccessResult(i);
	}

	@Override
	@RequestMapping(value = "/updateMessage")
	public Map updateMessage(@RequestBody Message message) {
		WfMessage wfmessage = JSONObject.parseObject(
				JSONObject.toJSONString(message), WfMessage.class);
		wfmessage.setMessageTime(new Date());
		int i = wfMessageDomainImpl.updateMessage(wfmessage);
		return ReturnResultUtil.returnSuccessResult(i);
	}

	@Override
	@RequestMapping(value = "/deleteMessageById/{messageId}")
	public Map deleteMessageById(@PathVariable String messageId) {
		int i = wfMessageDomainImpl.deleteMessageById(messageId);
		return ReturnResultUtil.returnSuccessResult(i);
	}
}