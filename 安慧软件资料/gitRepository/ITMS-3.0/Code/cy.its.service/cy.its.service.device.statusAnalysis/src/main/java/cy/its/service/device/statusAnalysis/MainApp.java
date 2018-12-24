package cy.its.service.device.statusAnalysis;

import cy.its.service.common.ice.grid.AppService;
import cy.its.service.common.ioc.AppContext;
import cy.its.service.common.ioc.Export;
import cy.its.service.common.ioc.Import;
import cy.its.service.common.log.LogManager;

/**
 * @Title: MainApp.java
 * @Package cy.its.service.device.statusAnalysis
 * @Description: 状态分析服务入口类
 * @author lijun@cychina.cn
 * @date 2015年11月4日 下午3:21:24
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 *      Company: 安徽超远信息技术有限公司 Copyright: Copyright (c) 2015
 */
@Export
public class MainApp extends AppService {

	@Import
	LoadRunner loadRunner;

	/**
	 * 程序入口
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			LogManager.createDefaultLogger("status", "status");
			AppContext.init("cy.its.service.device.statusAnalysis");
			Run(AppContext.getBean(MainApp.class), args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void Start(String[] args) throws Exception {
		loadRunner.start();
	}

	@Override
	public void Stop() throws Exception {
		loadRunner.stop();
	}

	@Override
	public String getAppName() {
		return "statusAnalysis";
	}
}
