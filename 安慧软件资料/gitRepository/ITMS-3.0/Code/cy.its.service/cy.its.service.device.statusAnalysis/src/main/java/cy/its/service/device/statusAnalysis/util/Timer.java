package cy.its.service.device.statusAnalysis.util;

import java.util.concurrent.ExecutorService;
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
		} catch (Throwable e) {
			LogManager.error(e);
		}
	}
}

class Timer1 {
	Runnable runner;
	int delay;
	int period;
	ExecutorService service;
	boolean isStop = false;

	public Timer1(Runnable runner, int delay, int period) {
		this.runner = runner;
		this.delay = delay;
		this.period = period;
		this.service = Executors.newSingleThreadExecutor();
	}

	public void start() {
		service.submit(() -> {
			try {
				Thread.sleep(delay);
			} catch (Exception e) {
				e.printStackTrace();
			}

			while (!isStop) {
				try {
					this.runner.run();
				} catch (Exception e) {
				}

				try {
					Thread.sleep(this.period);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void stop() {
		try {
			isStop = true;
			service.shutdown();
			service.awaitTermination(Integer.MAX_VALUE, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			LogManager.error(e);
		}
	}
}