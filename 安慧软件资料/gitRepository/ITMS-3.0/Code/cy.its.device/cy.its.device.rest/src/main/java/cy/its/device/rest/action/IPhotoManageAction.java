package cy.its.device.rest.action;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cy.its.device.rest.dto.PhotoInfoDto;

public interface IPhotoManageAction {
	
	/**
	 * 添加保存实景图片
	 * @param form 实景图片地址
	 */
	public int addPhotoInfo(PhotoInfoDto form) throws Exception;
	
	/**
	 * 查询指定电子监控系统的多张图片
	 * @param deviceId 电子监控系统ID
	 */
	public List<PhotoInfoDto> queryPhotoByDeviceId(String deviceId) throws Exception;
	
	/**
	 * 
	  * deletePhotoes(删除设备照片)
	  * @Title: deletePhotoes
	  * @Description: 删除设备照片
	  * @param @param photoIdlst    设定文件
	  * @return void    返回类型
	  * @throws
	 */
	public int deletePhotoes(String photoIdStr) throws Exception;
}
