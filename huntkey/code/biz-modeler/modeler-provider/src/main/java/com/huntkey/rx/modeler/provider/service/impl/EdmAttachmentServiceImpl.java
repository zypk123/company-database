package com.huntkey.rx.modeler.provider.service.impl;

import com.github.tobato.fastdfs.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.service.DefaultGenerateStorageClient;
import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.commons.utils.uuid.UuidCreater;
import com.huntkey.rx.modeler.common.model.EdmAttachment;
import com.huntkey.rx.modeler.common.model.EdmAttachmentExample;
import com.huntkey.rx.modeler.common.model.vo.EdmAttachmentVO;
import com.huntkey.rx.modeler.common.util.Constant;
import com.huntkey.rx.modeler.common.util.ExcelUtil;
import com.huntkey.rx.modeler.common.util.ModelToVO;
import com.huntkey.rx.modeler.provider.dao.EdmAttachmentMapper;
import com.huntkey.rx.modeler.provider.dao.EdmCodeMapper;
import com.huntkey.rx.modeler.provider.dao.EdmConvoluteMapper;
import com.huntkey.rx.modeler.provider.service.EdmAttachmentService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by licj on 2017/4/13.
 */
@Service
@Transactional(readOnly = true)
public class EdmAttachmentServiceImpl implements EdmAttachmentService {

	private static Logger log = LoggerFactory.getLogger(EdmAttachmentServiceImpl.class);

	@Autowired
	private EdmAttachmentMapper edmAttachmentMapper;

	@Autowired
	private EdmConvoluteMapper edmConvoluteMapper;

	@Autowired
	private EdmCodeMapper edmCodeMapper;

	@Autowired
	private DefaultGenerateStorageClient defaultGenerateStorageClient;

	@Override
	@Transactional(readOnly = false)
	public int delete(String id) {
		return edmAttachmentMapper.updateIsDelByPrimaryKey(id);
	}

	@Override
	@Transactional(readOnly = false)
	public int insert(EdmAttachment edmAttachment) {
		Date date = new Date();
		Integer seq = null;
		String edmcId = edmAttachment.getEdmaEdmcId();
		if (!StringUtil.isNullOrEmpty(edmcId)) {
			seq = edmAttachmentMapper.getMaxSeqByEdmcId(edmcId);
			if (seq != null) {
				seq = seq + 1;
			} else {
				seq = 1;
			}
		}
		edmAttachment.setId(UuidCreater.uuid());
		edmAttachment.setAddtime(date);
		edmAttachment.setModtime(date);
		edmAttachment.setEdmaSeq(seq);
		edmAttachment.setIsDel((byte) 0);
		return edmAttachmentMapper.insert(edmAttachment);
	}

	@Override
	@Transactional(readOnly = false)
	public int update(EdmAttachment attachment) {
		attachment.setModtime(new Date());
		return edmAttachmentMapper.updateByPrimaryKeySelective(attachment);
	}

	@Override
	@Transactional(readOnly = false)
	public void move(String[] ids) {
		int seq = 1;
		if(null != ids && ids.length>0) {
			for(String id:ids) {
				if(!StringUtil.isNullOrEmpty(id)) {
					edmAttachmentMapper.updateSeqById(id,seq);
					seq++;
				}
			}
		}
	}

	/**
	 * 附件下载
	 * 
	 * @param id
	 * @param response
	 * @return
	 */
	public void download(String id, HttpServletResponse response) {
		String path = "";
		String fileName = "";
		// 根据附件id查询附件名称和路径
		EdmAttachment edmAttachment = edmAttachmentMapper.selectByPrimaryKey(id);
		if (null != edmAttachment) {
			path = edmAttachment.getEdmaPath();
			fileName = edmAttachment.getEdmaName();
			if (!StringUtil.isNullOrEmpty(path) && !StringUtil.isNullOrEmpty(fileName)) {
				path = path.replace("'", "");
				fileName = fileName.replace("'", "");
				FileInputStream fis = null;
				try {
					response.setContentType("application/x-msdownload;");
					response.setHeader("Content-disposition",
							"attachment; filename=" + new String(fileName.getBytes("utf-8"), "utf-8"));
					path = "" + path;
					File file = new File(URLDecoder.decode(path, "utf-8"));
					if (file.exists()) {
						fis = new FileInputStream(file);
						int i = fis.available();
						byte data[] = new byte[i];
						fis.read(data);
						OutputStream os = response.getOutputStream();
						os.write(data);
						os.flush();
						os.close();
						fis.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					// 如果fos不是null，才需要close()
					if (fis != null) {
						// 为了保证close()一定会执行，就放到这里了
						try {
							fis.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
	}

	@Override
	public List<EdmAttachmentVO> selectEdmAttachmentsByCid(String id) {
		EdmAttachmentExample example = new EdmAttachmentExample();
		example.createCriteria().andIsDelNotEqualTo((byte)1).andEdmaEdmcIdEqualTo(id);
		example.setOrderByClause("edma_seq asc");
		List<EdmAttachment> attachments = edmAttachmentMapper.selectByExample(example);
		List<EdmAttachmentVO> edmAttachmentVOS = null;
		if (null != attachments && attachments.size() > 0) {
			edmAttachmentVOS = new ArrayList<EdmAttachmentVO>();
			for (EdmAttachment edmAttachment : attachments) {
				EdmAttachmentVO edmAttachmentVO = ModelToVO.edmAttachmentToVO(edmAttachment);
				edmAttachmentVO
						.setTypeValue(edmCodeMapper.selectAttachMentTypeByCodeValue(edmAttachment.getEdmaType()));
				edmAttachmentVOS.add(edmAttachmentVO);
			}
		}
		return edmAttachmentVOS;
	}

	@Override
	public String checkUnique(String edmaName,String edmaEdmcId) {
		String errorStr = null;
		if(StringUtil.isNullOrEmpty(edmaName)){
			errorStr = "附件名不能为空";
			return errorStr;
		}
		if(StringUtil.isNullOrEmpty(edmaEdmcId)){
			errorStr = "类ID不能为空";
			return errorStr;
		}
		EdmAttachmentExample checkExample = new EdmAttachmentExample();
		checkExample.createCriteria().andEdmaNameEqualTo(edmaName).andEdmaEdmcIdEqualTo(edmaEdmcId).andIsDelNotEqualTo((byte)1);
		List<EdmAttachment> edmAttachmentList = edmAttachmentMapper.selectByExample(checkExample);
		if (null != edmAttachmentList && edmAttachmentList.size() > 0) {
			errorStr = "附件名已存在";
			return errorStr;
		}
		return errorStr;
	}

	@Override
	public String getAttachmentNameById(String id) {
		return edmAttachmentMapper.getAttachmentNameById(id);
	}

	@Override
	public ResponseEntity<byte[]> fastDFSDownload(String id, HttpServletResponse response) {
		String groupName = Constant.FILE_GROUP_NAME;
		EdmAttachment edmAttachment = edmAttachmentMapper.selectByPrimaryKey(id);
		byte[] content = null;
		HttpHeaders headers = new HttpHeaders();
		try {
			String edmPath = edmAttachment.getEdmaPath().replace(groupName+"/","");
			//String filename = edmAttachment.getEdmaName()+ edmPath.substring(edmPath.lastIndexOf("."));
			String filename = edmAttachment.getEdmaSourceName();
			System.out.println("xiazai edmAttachment.getEdmaSourceName()="+edmAttachment.getEdmaSourceName());
			DownloadByteArray callback = new DownloadByteArray();
			content = defaultGenerateStorageClient.downloadFile(groupName,edmPath, callback);
			headers.setContentDispositionFormData("attachment",  new String(filename.getBytes("UTF-8"),"iso-8859-1"));
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<byte[]>(content, headers, HttpStatus.CREATED);
		//return null;
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteBatch(String[] ids) {
		if(null != ids && ids.length>0){
			for (String id : ids){
				edmAttachmentMapper.updateIsDelByPrimaryKey(id);
			}
		}
	}
}
