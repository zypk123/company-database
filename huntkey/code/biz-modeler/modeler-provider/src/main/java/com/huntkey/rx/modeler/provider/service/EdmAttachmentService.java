package com.huntkey.rx.modeler.provider.service;

import com.huntkey.rx.modeler.common.model.EdmAttachment;
import com.huntkey.rx.modeler.common.model.vo.EdmAttachmentVO;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by licj on 2017/4/13.
 */
public interface EdmAttachmentService {

    int delete(String id);

    int insert(EdmAttachment edmAttachment);

    int update(EdmAttachment attachment);

	/**
	 * 附件上移、下移
	 * @param ids
	 */
	void move(String[] ids);

    List<EdmAttachmentVO> selectEdmAttachmentsByCid(String id);
    
	/**
	 * 检验附件名称唯一性
	 * @param edmaName
	 * @param edmaEdmcId
	 * @return
	 */
	String checkUnique(String edmaName,String edmaEdmcId);

	/**
	 * 通过id查找附件名
	 * @param id
	 * @return
	 */
	String getAttachmentNameById(String id);

	ResponseEntity<byte[]> fastDFSDownload(String id, HttpServletResponse response);

	/**
	 * 批量删除
	 * @param ids
	 */
	void deleteBatch(String[] ids);
}
