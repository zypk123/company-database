package com.huntkey.rx.modeler.provider.service;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.huntkey.rx.modeler.common.model.EdmMethodArg;
import com.huntkey.rx.modeler.common.model.vo.EdmMethodArgVO;
import com.huntkey.rx.modeler.provider.ModelerProviderApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ModelerProviderApplication.class)
public class EdmMethodArgServiceTest {

	@Autowired
	EdmMethodArgService edmMethodArgService;

	@Test
	public void testDeleteByPrimaryKey() {
		edmMethodArgService.deleteByPrimaryKey("test4p");
	}

	@Test
	public void testInsertInputArg() {
		EdmMethodArg edmMethodArg = new EdmMethodArg();
		// edmMethodArgVO.setId("10000");
		edmMethodArg.setEdmaName("输入参数Test");
		edmMethodArg.setEdmaDesc("输入参数3描述");
		edmMethodArg.setModtime(new Date());
		edmMethodArg.setModuser("admin");
		edmMethodArgService.insertInputArg(edmMethodArg);
	}

	@Test
	public void testInsertReturnArg() {
		EdmMethodArg edmMethodArg = new EdmMethodArg();
		// edmMethodArgVO.setId("20000");
		edmMethodArg.setEdmaName("返回值参数Test");
		edmMethodArg.setEdmaDesc("返回值参数3描述");
		edmMethodArg.setModtime(new Date());
		edmMethodArg.setModuser("admin");
		edmMethodArgService.insertReturnArg(edmMethodArg);
	}

	@Test
	public void testUpdateByPrimaryKey() {
		EdmMethodArg edmMethodArg = new EdmMethodArg();
		edmMethodArg.setId("test3P1");
		edmMethodArg.setEdmaName("输入参数TestTestTest");
		edmMethodArgService.updateByPrimaryKey(edmMethodArg);
	}
	
	@Test
	public void testcheckUnique(String edmaName,String edmaEdmmId) {
		edmMethodArgService.checkUnique("3","e6cf7bde2381496ca581dcb9358c161b");
	}
	
	


}
