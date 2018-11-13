package com.huntkey.rx.modeler.provider.service;

import com.huntkey.rx.modeler.common.model.EdmCode;
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

public class EdmPropertyServiceTest {
	@Autowired
	private EdmPropertyService edmPropertyService;

	@Autowired
	private EdmCodeService edmCodeService;

	@Autowired
	private EdmMethodService edmMethodService;

	@Autowired
	private EdmLinkService edmLinkService;

	@Test
	@Ignore
	public void getConnetsTest() {
		try {
			String id = "eb26f1b4967544ef876a60b6a15ddb8a";
			List<EdmLinkVO> resultlist = edmPropertyService.getConnects(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}

	@Test
	public void getConvoluteTest() {
		try {
			// String id = "3aea7077830e43dfb32523c812859d07";
			String id = "g123b234234234";
			EdmConvoluteVO result = edmPropertyService.getConvolute(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}

	@Test
	public void getLinksTest() {
		try {
			String id = "1b55ea6aa07a442db6538c9ea87f163c";
			// List<EdmLinkVO> list = edmPropertyService.getLinks(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}

	@Test
	public void getUnits() {
		try {
			String id = "11bbd8742cea499e83971d61e3a2210b";
			List<EdmUnitVO> list = edmPropertyService.getUnits(id);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}

	@Test
	public void updateEdmUnitTypeBranchTest() {
		String edunUnitType = "我测试的type";
		String edunEdmpId = "98f053db7e6743d78940f2a153c0c04a";
		//edmPropertyService.updateEdmUnitTypeBatch(edunUnitType, edunEdmpId);
	}
	
	@Test
	public void checkUniqueTest() {
		//System.out.println("==========="+edmPropertyService.checkUnique("zwmc", "中文名称","cb2927b383a04d4fa974efbc4409950a"));
	}

	@Test
	public void testGetUniqueEdmpCode() {
		String newEdmpCode =
				edmPropertyService.getUniqueEdmpCodeByClassId("01ff92f7081048f1be29e7218519198c");//96012af8d7b24ced8c87ed7c1e6537db
		System.out.println("newEdmpCode="+newEdmpCode);
	}

	@Test
	public void testGetPropertyTreeByClassId() {
		String classId = "9bbc44e1e1394bfda7d217d9f7a24f0c";
		//List<EdmPropertyVO> treeList = edmPropertyService.getPropertyTreeByClassId(classId);
//		for(EdmPropertyVO edmPropertyVO:treeList) {
//			System.out.println(edmPropertyVO.toString());
//		}
	}

	@Test
	public void testGetDataTypeByProperty() {
		String codeValue = "normalObj";
		List<EdmCode> edmCodeList = edmCodeService.getDataTypeByPropertyValue(codeValue);
		for(EdmCode edmCode:edmCodeList) {
			System.out.println(edmCode.toString());
		}
	}

	@Test
	public void testHello() {
		System.out.println("1111");
	}

	@Test
	public void testCodeTree() {
		List<EdmCode> list = edmCodeService.getTreeData("edm_industry_code");
		for(EdmCode edmCode:list) {
			System.out.println(edmCode.toString());
		}
	}
//
//	@Test
//	public void testCheckEdmpName() {
//		String s = "";
//		s = edmPropertyService.checkEdmpName("678","63eb64aba2614db0ac6b7985fef449b6");
//		System.out.println(s);
//	}

	@Test
	public void testMove() {
		String ids[] = {"c7e01f4b97a2483989fa7928b43d18b4","98f6bd2316be49f4905b4a7d5c48a928"};
		edmPropertyService.move(ids);
	}

	@Test
	public void testGetTreeData() {
		List<EdmCode> list = new ArrayList<EdmCode>();
		list = edmCodeService.getTreeData("edm_industry_code");
		if(list.size() == 0) {
			System.out.println("null");
		}else {
			for(EdmCode edmCode:list) {
				System.out.println(edmCode.toString());
			}
		}
	}

	@Test
	public void testGetAllSubProperties() {
		String id = "81fdda6f1eb34bc980d6d96d3702329d";
		List<EdmProperty> list = edmPropertyService.getAllSubProperties(id);
		if(list.size()>0) {
			for(EdmProperty edmProperty:list) {
				System.out.println(edmProperty.toString());
			}
		}else {
			System.out.println("null");
		}
	}

	@Test
	public void testDeleteExtendProperty() {
		String id = "e986f8c6725546979563f39d38399260";
		edmPropertyService.deleteExtendProperty(id);
	}

	@Test
	public void testselectByPrimaryKey() {
		EdmMethodAndArgVO edmMethodAndArgVO = edmMethodService.selectByPrimaryKey("9e7d425f948e4194b4abf56a94a83f2d");
		//System.out.println(edmMethodAndArgVO.getEdmMethod_show().toString());
		//System.out.println(edmMethodAndArgVO.getEdmMethodreturn_show().toString());
	}

	@Test
	public void testCheckLinkId() {
		String edmpId0 = "f38707df6b6b4c30ad635ea4bb9ce3a3";
		String linkid0 = "afb8d867b3374bc1a5345116557ac7c6";
		String errorStr0 = edmLinkService.checkLinkId(edmpId0, linkid0);
		System.out.println("errorStr0="+errorStr0);

		String edmpId1 = "c23ac01d56714ff795bcf991890551c1";
		String linkid1 = "6f946d18377e4ce693e06c3a434f1e9b";
		String errorStr1 = edmLinkService.checkLinkId(edmpId1, linkid1);
		System.out.println("errorStr1="+errorStr1);

		String edmpId2 = "6f946d18377e4ce693e06c3a434f1e9b";
		String linkid2 = "6f946d18377e4ce693e06c3a434f1e9b";
		String errorStr2 = edmLinkService.checkLinkId(edmpId2, linkid2);
		System.out.println("errorStr2="+errorStr2);

		String edmpId3 = "d2b4c657f24a49718917b35a86bfaa5b";
		String linkid3 = "72da7e2225b7453c9f06b595ea85f8bb";
		String errorStr3 = edmLinkService.checkLinkId(edmpId3, linkid3);
		System.out.println("errorStr3="+errorStr3);
	}

	@Test
	public void testEdmpCode() {
		/*String edmpCode1 = "abs001";
		String code1 = edmpCode1.substring(edmpCode1.length()-3,edmpCode1.length());
		System.out.println("code1="+code1);

		String edmpCode2 = "_002";
		String code2 = edmpCode2.substring(edmpCode2.length()-3,edmpCode2.length());
		System.out.println("code2="+code2);

		String edmpCode3 = "003";
		String code3 = edmpCode3.substring(edmpCode3.length()-3,edmpCode3.length());
		System.out.println("code3="+code3);*/

		String classId = "962b2fa3d5954306ad8f3742e0219e45";
		String maxCode = edmPropertyService.getUniqueEdmpCodeByClassId(classId);
		System.out.println("maxCode="+maxCode);
	}

	@Test
	public void testChangePropertiesVisible() {
		String[] ids = {"63d922ce773611e78bba005056bc4879", "63da16a1773611e78bba005056bc4879", "648d03df773611e78bba005056bc4879",};
		//int i = edmPropertyService.changePropertiesVisible(ids, (byte)1);
		//System.out.println(i); // 显示 3
	}


}
