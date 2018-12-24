package cy.its.device.rest.action.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cy.its.com.util.DateUtil;
import cy.its.com.util.ObjectMapUtils;
import cy.its.device.domain.criteria.LedContentLibCriteria;
import cy.its.device.domain.model.led.LedContentLib;
import cy.its.device.domain.service.impl.LedService;
import cy.its.device.rest.action.ILedContentLibAction;
import cy.its.device.rest.dto.LedContentLibDto;

@RequestMapping("/device/ledContentLib")
@RestController
public class LedContentLibAction implements ILedContentLibAction {
	@Autowired
	private LedService ledservice;
	
	/**
	 * 添加新的内容库，1表示成功，其他表示失败
	 */
	@RequestMapping("/addLedContentLib")
	@Override
	public int addLedContentLib(LedContentLibDto form) throws Exception {
		//内容类型默认为1
		form.setContentType(form.getContentType()==null?"1":form.getContentType());
		LedContentLib ledContentLib = new LedContentLib();
		ObjectMapUtils.parseObject(ledContentLib, form);//将源对象属性复制到目标对象上
		ledservice.createContentLib(ledContentLib, form.getCurrentUserLoginName());
		return 1;
	}
	/**
	 * 删除内容库1表示成功，其他表示失败
	 */
	@RequestMapping("/removeLedContentLib")
	@Override
	public int removeLedContentLib(String ids) throws Exception {
		for (String id : ids.split(",")) {
			ledservice.removeContentLib(id);
		}
		return 1;
	}
	/**
	 * 修改内容库1表示成功，其他表示失败
	 */
	@RequestMapping("/updateLedContentLib")
	@Override
	public int updateLedContentLib(LedContentLibDto form) throws Exception {
		if(StringUtils.isEmpty(form.getContentId())){
			throw new Exception("源信息已经被删除");
		}
		//LedContentLib contentlib = ledservice.contentLibOfId(form.getContentId());
		LedContentLib contentlib = new LedContentLib();
		if(!StringUtils.isEmpty(form.getCreateTime())){
			contentlib.setCreateTime(DateUtil.parseDate(form.getCreateTime()));
		}
		ObjectMapUtils.parseObject(contentlib, form);		
		ledservice.updateContentLib(contentlib, form.getCurrentUserLoginName());
		return 1;
	}
	/**
	 * 查询内容对象结果集
	 */
	@RequestMapping("/findLedContentLibList")
	@Override
	public Map<String, Object> findLedContentLibList(LedContentLibDto form) throws Exception {
		LedContentLibCriteria criteria = new LedContentLibCriteria();
		//设置当前页数
		criteria.setPageNum(form.getPageNumber());
		//设置每页最大显示个数
		criteria.setPageSize(form.getPageSize());
		//是否需要统计总数
		criteria.setNeedTotal(true);
		ObjectMapUtils.parseObject(criteria, form);
		//找出数据库里内容库集合
		List<LedContentLib> LedContentLibList= ledservice.findContentLibs(criteria);
		//返回页面的结果集
		ArrayList<LedContentLibDto> listView = new ArrayList<>();
		for(LedContentLib ledContentLib:LedContentLibList){
			//新建页面数据包装类，把每个映射类数据放回此类中
			LedContentLibDto ledContentLibDto = new LedContentLibDto();
			if(ledContentLib.getCreateTime()!=null){
				ledContentLibDto.setCreateTime(DateUtil.formatDate(ledContentLib.getCreateTime()));
			}
			ObjectMapUtils.parseObject(ledContentLibDto,ledContentLib);
			listView.add(ledContentLibDto);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("error", "");
		Map<String, Serializable> maprow = new HashMap<String, Serializable>();
		maprow.put("rows", listView);
		maprow.put("total", criteria.getTotal());
		map.put("result", maprow);
		return map;
	}

}
