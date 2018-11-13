package com.huntkey.rx.modeler.provider.service;

import com.huntkey.rx.modeler.common.model.EdmMethod;
import com.huntkey.rx.modeler.provider.ModelerProviderApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ModelerProviderApplication.class)
public class EdmMethodServiceTest {

	@Autowired
	EdmMethodService edmMethodService;
	
	@Test
	public void testInsert() {
		EdmMethod edmMethod = new EdmMethod();
		edmMethod.setEdmmName("TESTTESESSDSDSSSSSSSSSSS");
		edmMethodService.insert(edmMethod);
	}

	@Test
	public void testInsertObject() {
		
	}

	@Test
	public void testSelectByExample() {

		System.out.println("========="+edmMethodService.selectByExample("",
				"1", "", "",
				"1",1, 10));
	}

	@Test
	public void testSelectByPrimaryKey() {
		System.out.println("======"+edmMethodService.selectByPrimaryKey("1"));
	}
	
	
	@Test
	public void checkUniqueTest() {
		//System.out.println("==========="+edmMethodService.checkUnique("方法1-4"));
	}

	@Test
	public void testUpdateByPrimaryKey() {
		
	}

	@Test
	public void testDeleteByPrimaryKey() {
		
	}

	@Test
	public void testMove() {
		
	}

	@Test
	public void testGetClassesByMethodId() {
		
	}

	@Test
	public void testExportEdmMethodVOToExcel() {
		
	}

	@Test
	public void testImportEdmMethodVOFromExcel() {
		
	}

	@Test
	public void testQueryClassMethodTypeByClassId() {
		
	}

}
