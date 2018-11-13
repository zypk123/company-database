package com.huntkey.rx.modeler.provider.service;

import com.huntkey.rx.modeler.common.model.EdmCode;
import com.huntkey.rx.modeler.common.model.EdmIndex;
import com.huntkey.rx.modeler.common.model.EdmProperty;
import com.huntkey.rx.modeler.common.model.vo.*;
import com.huntkey.rx.modeler.provider.ModelerProviderApplication;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by linziy on 2017/4/25.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ModelerProviderApplication.class)

public class EdmIndexServiceTest {
	@Autowired
	private EdmIndexService edmIndexService;



	@Test
	public void insertTest() {
		try {
			EdmIndexVO edmIndexVO = new EdmIndexVO();
			edmIndexVO.setEdmcId("2222222a");
			edmIndexVO.setEdmpAssId("33333333a");
			edmIndexVO.setIndexName("测试2");
			edmIndexVO.setIsDel((byte)0);
			edmIndexVO.setIndexProppertyIds("66666,7777,8888,99999");
			int flag = edmIndexService.insert(edmIndexVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}

	@Test
	public void updateTest() {
		try {
			EdmIndexVO edmIndexVO = new EdmIndexVO();
			edmIndexVO.setId("cb8bdbdc0f4c4078b972f547fdd37157");
			edmIndexVO.setEdmcId("2222222");
			edmIndexVO.setEdmpAssId("33333333");
			edmIndexVO.setIndexName("测试1");
			edmIndexVO.setIsDel((byte)0);
			edmIndexVO.setIndexProppertyIds("1111a,2222a,3333a,4444a");
			int flag = edmIndexService.update(edmIndexVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}

	@Test
	public void deletesTest() {
		try {
			String arr[] = {"cb8bdbdc0f4c4078b972f547fdd37157","e24a49158ebf491a834f5b6d5745f5cc"};

			int flag = edmIndexService.deletes(arr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}

	@Test
	public void checkIndexNameTest() {
		try {
			String indexName ="测试2";
			String edmcId="2222222a";
			String id ="5e11f1abc1044aa3b3b035f3aeb5c584";

			String errStr = edmIndexService.checkIndexName(indexName,edmcId,id);
			System.err.println("=================="+errStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}

}
