package cy.its.service.imageQuery;

import java.util.ArrayList;
import java.util.List;

import cy.its.service.common.ConstValue;
import cy.its.service.common.StringUtil;
import cy.its.service.imageQuery.ice.ImageProxy;

public class ViolationImage extends SurveyImage {

	public static byte[] query(String encryptImageUrl) {

		return getImage(encryptImageUrl, "Î¥·¨", ImageProxy.VIOLATION);
	}

	public static List<byte[]> queryMulti(String encryptImageUrls) {
		if (!StringUtil.isNullOrEmpty(encryptImageUrls)) {
			String[] encryptUrlArr = encryptImageUrls.split(ConstValue.SEMICOLON);
			List<byte[]> lstImage = new ArrayList<byte[]>(encryptUrlArr.length);
			for (String encryptUrl : encryptUrlArr) {
				lstImage.add(query(encryptUrl));
			}
			return lstImage;
		}

		return null;

	}
}
