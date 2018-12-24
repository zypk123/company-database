package cy.its.service.analysisAlarm;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import cy.its.service.analysisAlarm.module.CacheManager;

/**
 * @author 李磊
 * 5分钟  刷一次缓存信息
 */
public class AlarmScheduleService  implements Job{

	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		//重新加载，配置信息
		CacheManager.initWarnList(CacheManager.alermTypev, "");
		CacheManager.initWarnList(CacheManager.alermTypewt, "");
		CacheManager.initWarnList(CacheManager.alermTyper, "");
		CacheManager.initWarnList(CacheManager.alermTypeice, "");
		CacheManager.initWarnList(CacheManager.alermTypewd, "");
		CacheManager.initWarnList(CacheManager.alermTypetr, "");
	}

}
