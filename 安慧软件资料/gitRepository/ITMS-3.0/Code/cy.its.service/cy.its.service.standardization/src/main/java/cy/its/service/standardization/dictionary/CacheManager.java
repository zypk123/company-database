package cy.its.service.standardization.dictionary;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import cy.its.service.common.SignalEvent;
import cy.its.service.common.StringUtil;
import cy.its.service.common.dataAccess.DbExecuter;
import cy.its.service.common.ioc.Export;
import cy.its.service.common.ioc.Import;
import cy.its.service.common.log.LogManager;
import cy.its.service.common.rabbitmqClient.QueueHandler;
import cy.its.service.standardization.IMaker;

@Export
public class CacheManager implements IMaker {

	@Import
	List<Cache> caches;

	Boolean isLoaded = false;

	boolean close = false;
	
	Timer timer;
	
	public void initialize() {
		loadAllCache();
	}

	@Override
	public QueueHandler getQueueHandler() {

		Set<String> rkSet = new HashSet<String>();
		caches.forEach(c -> {
			if (c.getRelatedRouteKeys() != null && c.getRelatedRouteKeys().size() > 0) {
				for (String rtKey : c.getRelatedRouteKeys()) {
					rkSet.add(rtKey);
				}
			}
		});

		return new QueueHandler("StandardizationEvent_" + this.hashCode() + "" + System.currentTimeMillis() % 1000,
				false, true, rkSet.toArray(new String[0]), this);
	}

	@Override
	public Boolean receive(String routingKey, String message) {
		LogManager.info("接收到缓存变更通知:" + routingKey);
		for (Cache cache : caches) {
			if (cache.getRelatedRouteKeys() != null && cache.getRelatedRouteKeys().contains(routingKey)) {
				cache.receiveChange();
			}
		}

		return true;
	}

	public void close() {
		close = true;
		timer.stop();
	}

	private void loadAllCache() {
		timer = new Timer(()->{
		while (!close) {
			try {
				DbExecuter.customNonQuerys((da) -> {
					for (Cache iCache : caches) {
						iCache.load(da);
					}
				});
				break;
			} catch (Throwable e) {
				LogManager.error("缓存加载失败, " + StringUtil.getErrorDetail(e));
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e1) {
					LogManager.error(e1);
				}
				continue;
			}
		}

		isLoaded = true;
		}, 10, 300000);
		
		timer.start();
	}

	public boolean isLoaded() {
		return isLoaded;
	}

}


class Timer {
	Runnable runner;
	SignalEvent e = new SignalEvent();
	int delay;
	int period;
	Boolean isStop = false;
	ScheduledExecutorService service;

	public Timer(Runnable runner, int delay, int period) {
		this.runner = runner;
		this.delay = delay;
		this.period = period;
		this.service = Executors.newScheduledThreadPool(1);
	}

	public void start() {
		service.scheduleWithFixedDelay(this.runner, this.delay, this.period, TimeUnit.MILLISECONDS);
	}

	public void stop() {		
		try {
			service.shutdown();
			service.awaitTermination(Integer.MAX_VALUE, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			LogManager.error(e);
		}		
	}
}
