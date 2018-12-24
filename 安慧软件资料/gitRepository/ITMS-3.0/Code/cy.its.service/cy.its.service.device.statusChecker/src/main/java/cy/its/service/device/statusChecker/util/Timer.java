package cy.its.service.device.statusChecker.util;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import cy.its.service.common.log.LogManager;

public class Timer {
	Runnable runner;
	int delay;
	int period;
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
