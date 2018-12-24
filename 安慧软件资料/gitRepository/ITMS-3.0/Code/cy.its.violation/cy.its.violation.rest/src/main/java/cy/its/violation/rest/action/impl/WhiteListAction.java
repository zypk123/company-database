/**
 * @Title: WhiteListAction.java
 * @Package cy.its.violation.rest.action.impl
 * @Description: TODO(这里要填写用途)
 * @author wangyf wangyf@cychina.cn
 * @date 2015年11月4日 下午3:32:10
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */

package cy.its.violation.rest.action.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cy.its.com.util.ObjectMapUtils;
import cy.its.violation.domain.criteria.WhiteVehicleCriteria;
import cy.its.violation.domain.model.config.WhiteVehicle;
import cy.its.violation.domain.service.IWhiteVehicleService;
import cy.its.violation.rest.action.IWhiteListAction;
import cy.its.violation.rest.dto.WhiteListDto;

/**
 * @ClassName: WhiteListAction
 * @Description: TODO(这里要填写用途)
 * @author wangyf wangyf@cychina.cn
 * @date 2015年11月4日 下午3:32:10
 *
 */
@RestController
@RequestMapping("/violaton/whiteListAction")
public class WhiteListAction implements IWhiteListAction {

	@Autowired
	IWhiteVehicleService whiteVehicleService;

	/*
	 * <p>Title: searchWhiteList</p> <p>Description:查询 </p>
	 * 
	 * @param whiteListDto
	 * 
	 * @return
	 * 
	 * @see
	 * cy.its.violation.rest.action.IWhiteListAction#searchWhiteList(cy.its.
	 * violation.rest.dto.WhiteListDto)
	 */

	@RequestMapping("/searchWhiteList")
	@Override
	public Map searchWhiteList(WhiteListDto whiteListDto) {
		WhiteVehicleCriteria whiteCriteria = new WhiteVehicleCriteria();
		// 客户端的条件转换给领域条件(号牌号码,号牌类型)
		whiteCriteria.plateNbr = whiteListDto.getPlateNbr();
		whiteCriteria.plateType = whiteListDto.getPlateType();
		ObjectMapUtils.parseObject(whiteCriteria, whiteListDto);
		ArrayList<WhiteListDto> lstView = new ArrayList<WhiteListDto>();
		List<WhiteVehicle> lst = whiteVehicleService.findWhiteVehicles(whiteCriteria);
		for (WhiteVehicle whiteV : lst) {
			WhiteListDto whiteLst = new WhiteListDto();
			// 将领域的日期Date类型转换客户端String
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			whiteLst.setValidEndDate(sdf.format(whiteV.getValidEndDate()));
			ObjectMapUtils.parseObject(whiteLst, whiteV);
			lstView.add(whiteLst);
		}
		Map map = new HashMap();
		map.put("error", "");
		Map maprow = new HashMap();
		maprow.put("rows", lstView);
		map.put("result", maprow);
		return map;
	}

	@RequestMapping("/searchWhiteVioList")
	@Override
	public Map searchWhiteVioList(WhiteListDto whiteListDto) {
		// TODO
		Map map = new HashMap();
		map.put("error", "");
		Map maprow = new HashMap();
		maprow.put("rows", "");
		map.put("result", maprow);
		return map;
	}

	/*
	 * <p>Title: createWhiteList</p> <p>Description:创建新车辆信息 </p>
	 * 
	 * @param whiteListDto
	 * 
	 * @return
	 * 
	 * @see
	 * cy.its.violation.rest.action.IWhiteListAction#createWhiteList(cy.its.
	 * violation.rest.dto.WhiteListDto)
	 */

	@RequestMapping("/saveWhiteLst")
	@Override
	public String createWhiteList(WhiteListDto whiteListDto) throws ParseException {
		WhiteVehicle whiteVehicle = new WhiteVehicle();
		// 将客户端日期String类型转换为领域Date类型
		DateFormat dfmat = new SimpleDateFormat("yyyy-MM-dd");
		whiteVehicle.setValidEndDate(dfmat.parse(whiteListDto.getValidEndDate()));
		ObjectMapUtils.parseObject(whiteVehicle, whiteListDto);

		whiteVehicleService.save(whiteVehicle);
		return "success";
	}

	/*
	 * <p>Title: goUpdateRoad</p> <p>Description:更新车辆信息</p>
	 * 
	 * @param whiteListDto
	 * 
	 * @return
	 * 
	 * @see cy.its.violation.rest.action.IWhiteListAction#goUpdateRoad(cy.its.
	 * violation.rest.dto.WhiteListDto)
	 */

	@RequestMapping("/updateWhiteLst")
	@Override
	public String goUpdateWhiteList(WhiteListDto whiteListDto) throws ParseException {
		DateFormat dfmat = new SimpleDateFormat("yyyy-MM-dd");
		WhiteVehicle whiteVehicle = new WhiteVehicle();
		whiteVehicle.setValidEndDate(dfmat.parse(whiteListDto.getValidEndDate()));
		ObjectMapUtils.parseObject(whiteVehicle, whiteListDto);
		int resultWhite = whiteVehicleService.update(whiteVehicle);

		if (resultWhite == 1) {
			return "success";
		} else {
			return "false";
		}
	}

	/*
	 * <p>Title: goDeleteRoad</p> <p>Description:批量删除车辆信息 </p>
	 * 
	 * @param whiteListStrId
	 * 
	 * @return
	 * 
	 * @see
	 * cy.its.violation.rest.action.IWhiteListAction#goDeleteRoad(java.lang.
	 * String)
	 */

	@RequestMapping("/deleteWhiteLst")
	@Override

	public String goDeleteWhiteList(@RequestParam("vehicleIdStr") String whiteListStrId) {
		String whiteLstId[] = whiteListStrId.split(",");
		int resultWhiteId = 0;
		for (int i = 0; i < whiteLstId.length; i++) {
			resultWhiteId += whiteVehicleService.delete(whiteLstId[i]);
		}
		if (resultWhiteId == whiteLstId.length) {
			return "success";
		} else {

			return "false";
		}
	}

	/*
	 * <p>Title: goRemoveRoad</p> <p>Description:单个删除信息 </p>
	 * 
	 * @param whiteListId
	 * 
	 * @return
	 * 
	 * @see
	 * cy.its.violation.rest.action.IWhiteListAction#goRemoveRoad(java.lang.
	 * String)
	 */
	@RequestMapping("/removeWhiteLst")
	@Override
	public String goRemoveWhiteList(@RequestParam("vehicleId") String whiteListId) {
		int resultWhiteListId = whiteVehicleService.delete(whiteListId);
		if (resultWhiteListId == 1) {
			return "success";
		} else {
			return "false";
		}
	}

}
