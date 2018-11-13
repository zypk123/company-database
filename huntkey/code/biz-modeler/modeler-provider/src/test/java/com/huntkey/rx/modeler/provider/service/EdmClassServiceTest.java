package com.huntkey.rx.modeler.provider.service;

import com.huntkey.rx.modeler.common.model.EdmClass;
import com.huntkey.rx.modeler.common.model.vo.EdmClassVO;
import com.huntkey.rx.modeler.provider.ModelerProviderApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.AbstractHandlerMethodMapping;

import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ModelerProviderApplication.class)
public class EdmClassServiceTest {

	@Autowired
	EdmClassService edmClassService;

	@Autowired
	DiscoveryClient discoveryClient;

	@Autowired
	ApplicationContext context;
	
	@Test
	public void testupdateIsDelByClassIdAndMethodId() {
		//edmClassService.updateIsDelByClassIdAndMethodId("1","2");
	}

	@Test
	public void testGetRelateClass() {
		String id = "1180805f4243444ca95be9fc2eff4254";
		List<EdmClassVO> list = new ArrayList<EdmClassVO>();
		list = edmClassService.getRelateClass(id);
		for(EdmClassVO edmClassVO:list) {
			System.out.println(edmClassVO.toString());
		}
	}

	@Test
	public void testTree() {
		String modelerId = "f691d19633a64639b3ab8a2e4617fc35";
		String[] edmcNameEns = {"empowera"};
		EdmClassVO edmClassVO = edmClassService.queryClassTree(modelerId, edmcNameEns);
		System.out.println(edmClassVO.toString());
	}

	@Test
	public void testCopy() {
		String id = "7820d881d4a140ed92fd66df5878005b";
		EdmClass edmClass = new EdmClass();
		edmClass.setId(id);
		edmClassService.copy(edmClass);
	}

	@Test
	public void getClasses() {
		String id = "7820d881d4a140ed92fd66df5878005b";
		EdmClass edmClass = new EdmClass();
		edmClass.setId(id);
		edmClassService.copy(edmClass);
	}

	@Test
	public void testDelete() {
		String classId = "011b24278e1a46ac8229b652b555f492";
		edmClassService.delete(classId);
	}

	@Test
	public void testget() {
		//String classId = "6f84e22366e211e7b2e4005056bc4879";
		//edmClassService.selectAssemblesByEdmcId(classId);
		//List<String> list = discoveryClient.getServices();
		//ServiceInstance instance = discoveryClient.getLocalServiceInstance();
//		List<ServiceInstance> list = discoveryClient.getInstances("FORMULA-PROVIDER");
//		System.out.println();
//		for(ServiceInstance s: list){
//			System.out.println(s.getHost());
//		}
		Iterator var3 = context.getBeansOfType(AbstractHandlerMethodMapping.class).entrySet().iterator();

		while(var3.hasNext()) {
			Map.Entry<String, AbstractHandlerMethodMapping> bean = (Map.Entry)var3.next();
			Map<?, HandlerMethod> methods = ((AbstractHandlerMethodMapping)bean.getValue()).getHandlerMethods();
			Iterator var6 = methods.entrySet().iterator();

			while(var6.hasNext()) {
				Map.Entry<?, HandlerMethod> method = (Map.Entry)var6.next();
				Map<String, String> map = new LinkedHashMap();
				String s= ((HandlerMethod)method.getValue()).toString();
				if( s.indexOf("springframework") ==-1){
					System.out.println(method.getKey().toString());
				}
				//map.put("bean", bean.getKey());
				//map.put("method", ((HandlerMethod)method.getValue()).toString());

				//result.put(method.getKey().toString(), map);
			}
		}
		//context.getApplicationName()
	}
}
