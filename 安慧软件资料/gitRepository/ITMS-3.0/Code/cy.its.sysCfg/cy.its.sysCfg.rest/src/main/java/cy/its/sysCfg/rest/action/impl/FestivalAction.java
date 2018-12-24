package cy.its.sysCfg.rest.action.impl;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cy.its.com.util.ObjectMapUtils;
import cy.its.com.util.StringUtil;
import cy.its.sysCfg.rest.action.IFestivalAction;
import cy.its.sysCfg.rest.dto.FestivalDto;
import cy.its.syscfg.domain.model.config.SysFestival;
import cy.its.syscfg.domain.model.sysCode.Code;
import cy.its.syscfg.domain.service.ISysCodeService;
import cy.its.syscfg.domain.service.ISysFestivalService;

@RestController
@RequestMapping("/sysCfg/sysFestivalAction")
public class FestivalAction implements IFestivalAction {
	
	@Autowired
	ISysFestivalService sysFestivalService;
	
	@Autowired
	ISysCodeService sysCodeService;
	
	@RequestMapping("/selectAllFestivalList")
	@Override
	public Map<String, Object> selectAllFestivalList(@ModelAttribute("festivalDto") FestivalDto festivalDto) throws Exception {
		ArrayList<FestivalDto> pDtos = new ArrayList<FestivalDto>();
		String year = festivalDto.getYear();
		String festivalType = festivalDto.getFestivalType();
		List<SysFestival> sysFestivals = sysFestivalService.findAllFestivals(year,festivalType);
		for (SysFestival sysfestival : sysFestivals) {
			FestivalDto dto = new FestivalDto();
			ObjectMapUtils.parseObject(dto,sysfestival);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if(sysfestival.getStartDate() != null){
				dto.setStartDate(sdf.format(sysfestival.getStartDate()));
			}
			if(sysfestival.getEndDate() != null){
				dto.setEndDate(sdf.format(sysfestival.getEndDate()));
			}
			if(sysfestival.getYear() != null){
				dto.setYear(sdf.format(sysfestival.getYear()).substring(0,4));
			}
			pDtos.add(dto);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("error", "");
		Map<String, Serializable> maprow = new HashMap<String, Serializable>();
		maprow.put("rows", pDtos);
		maprow.put("total", pDtos.size());
		map.put("result", maprow);
		return map;
	}
	
	@RequestMapping("/codeListOfType")
	public List<Code> codeListOfType(@RequestParam("codeType") String codeType) throws Exception
	{
		return sysCodeService.codeListOfType(codeType);
	}

	@RequestMapping("/updateFestival")
	@Override
	public String updateFestival(@ModelAttribute("FestivalDto") FestivalDto festivalDto)throws Exception {
		// TODO Auto-generated method stub
		
		SysFestival sysFestival = new SysFestival();
		ObjectMapUtils.parseObject(sysFestival,festivalDto);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(!StringUtil.isNullOrEmpty(festivalDto.getStartDate())){
			sysFestival.setStartDate(sdf.parse(festivalDto.getStartDate()));
		}
		if(!StringUtil.isNullOrEmpty(festivalDto.getEndDate())){
			sysFestival.setEndDate(sdf.parse(festivalDto.getEndDate()));
		}
		if(!StringUtil.isNullOrEmpty(festivalDto.getYear())){
			sysFestival.setYear(sdf.parse(festivalDto.getYear() + "-01-01"));
		}
		List<SysFestival> list = sysFestivalService.findAllFestivals(festivalDto.getYear(),festivalDto.getFestivalType());
		if(list.size() > 0){
			//如果查找到数据，如果查询到的数据和要修改的ID不一致
			if(festivalDto.getFestivalId().equals(list.get(0).getFestivalId())){
				sysFestivalService.updateFestival(sysFestival);
			}else{
				return "nbrError";
			}
		}
		
		 return "success";
	}

	@RequestMapping("/createFestival")
	@Override
	public String createFestival(@ModelAttribute("FestivalDto") FestivalDto festivalDto) throws Exception {
		// TODO Auto-generated method stub
		SysFestival sysFestival = new SysFestival();
		ObjectMapUtils.parseObject(sysFestival,festivalDto);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(!StringUtil.isNullOrEmpty(festivalDto.getStartDate())){
			sysFestival.setStartDate(sdf.parse(festivalDto.getStartDate()));
		}
		if(!StringUtil.isNullOrEmpty(festivalDto.getEndDate())){
			sysFestival.setEndDate(sdf.parse(festivalDto.getEndDate()));
		}
		if(!StringUtil.isNullOrEmpty(festivalDto.getYear())){
			sysFestival.setYear(sdf.parse(festivalDto.getYear()+ "-01-01"));
		}
		List<SysFestival> list = sysFestivalService.findAllFestivals(festivalDto.getYear(),festivalDto.getFestivalType());
		if(list.size() > 0)//如果查找到数据，如果查询到的数据和要修改的ID不一致
		{
			return "nbrError";
		}
		 sysFestivalService.createFestival(sysFestival);
		 return "success";
	}

	@RequestMapping("/removeFestival")
	@Override
	public String removeFestival(String festivalId) {
		// TODO Auto-generated method stub
		 sysFestivalService.removeFestival(festivalId);
		 return "success";
	}
	
	/**
	 * 删除节假日
	 */
	@RequestMapping("/deleteFestival")
	@Override
	public String deleteFestival(@RequestParam("ids") String ids) {
		
		String[] removeIds = ids.split(",");
		for(String festivalId : removeIds){
			sysFestivalService.removeFestival(festivalId);
		}
		return "success";
	}
}
