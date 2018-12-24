package cy.its.device.rest.action.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cy.its.com.util.ObjectMapUtils;
import cy.its.device.domain.model.Photo;
import cy.its.device.domain.service.ISystemAttachService;
import cy.its.device.rest.action.IPhotoManageAction;
import cy.its.device.rest.dto.PhotoInfoDto;

@RestController
@RequestMapping("/device/photoInfoManage")
public class PhotoManageAction implements IPhotoManageAction{
	
	@Autowired
	ISystemAttachService systemAttachService;
	
	/**
	 * 添加保存实景图片
	 * @param form 实景图片地址
	 */
	@RequestMapping(value = "/addPhotoInfo")
	public int addPhotoInfo(@ModelAttribute("form") PhotoInfoDto form) throws Exception {
		// TODO Auto-generated method 
		String photoStr[] = form.getPhoto().split(",");
		List<Photo> photoes = new ArrayList<Photo>();
		for (int i = 0; i < photoStr.length; i++) {
			Photo photo = new Photo();
			photo.setDeviceId(form.getDeviceId());
			photo.setPhoto(photoStr[i]);
			photoes.add(photo);
		}
		systemAttachService.createPhotoes(photoes);
		return 1;
	}
	
	/**
	 * 查询指定电子监控系统的多张图片
	 * @param deviceId 电子监控系统ID
	 */
	@RequestMapping(value = "/queryPhotoByDeviceId")
	public List<PhotoInfoDto> queryPhotoByDeviceId(@RequestParam("deviceId") String deviceId) throws Exception{
		List<Photo> list = systemAttachService.photesOfDevice(deviceId);
		List<PhotoInfoDto> lst = new ArrayList<PhotoInfoDto>();
		for (int i = 0; i < list.size(); i++) {
			PhotoInfoDto photoDto = new PhotoInfoDto();
			ObjectMapUtils.parseObject(photoDto, list.get(i));
			lst.add(photoDto);
		}
		return lst;
	}
	
	/**
	 * 
	  * deletePhotoes(删除设备照片)
	  * @Title: deletePhotoes
	  * @Description: 删除设备照片
	  * @param @param photoIdlst    设定文件
	  * @return void    返回类型
	  * @throws
	 */
	@RequestMapping(value = "/deletePhotoes")
	public int deletePhotoes(@RequestParam("photoIdStr") String photoIdStr) throws Exception{
		String photoId[] = photoIdStr.split(",");
		List<String> photoIdlst = new ArrayList<String>();
		for (int i = 0; i < photoId.length; i++) {
			photoIdlst.add(photoId[i]);
		}
		systemAttachService.deletePhotoes(photoIdlst);
		return 1;
	}
}
