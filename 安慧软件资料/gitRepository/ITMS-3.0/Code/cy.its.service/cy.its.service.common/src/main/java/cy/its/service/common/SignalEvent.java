package cy.its.service.common;

public final class SignalEvent {

	private Boolean isNotify = false;

	public synchronized void set() {
		notify();
		isNotify = true;
	}

	public synchronized void waitOne(long period) {
		try {
			if (isNotify) {
				isNotify = false;
				return;
			}

			wait(period);

			isNotify = false;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public synchronized void waitOne() {
		try {
			if (isNotify) {
				isNotify = false;
				return;
			}
			
			wait();

			isNotify = false;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}