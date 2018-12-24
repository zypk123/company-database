package cy.its.device.domain.model.led;

/**
 * 轮播节目
 * 
 * @author STJ
 *
 */
public class LedProgCycle extends LedProg {

	/**
	 * 播放时长
	 */
	private Short playTime;

	/**
	 * 播放次数
	 */
	private Short playTimes;

	public LedProgCycle() {
	}

	public LedProgCycle(Short playTime, Short playTimes) {
		super();
		this.playTime = playTime;
		this.playTimes = playTimes;
	}

	public Short getPlayTime() {
		return playTime;
	}

	public void setPlayTime(Short playTime) {
		this.playTime = playTime;
	}

	public Short getPlayTimes() {
		return playTimes;
	}

	public void setPlayTimes(Short playTimes) {
		this.playTimes = playTimes;
	}

	@Override
	public void checkPlayParams() throws Exception {
		if (this.playTime != null && this.playTimes != null) {
			throw new Exception("播放时长和播放次数参数只能指定一个");
		}

		if (this.playTime == null && this.playTimes == null) {
			throw new Exception("播放时长和播放次数参数必须要指定一个");
		}
	}
}
