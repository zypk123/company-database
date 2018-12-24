package cy.its.device.domain.model.led;

/**
 * 插播节目
 * 
 * @author STJ
 *
 */
public class LedProgGrab extends LedProg {
	/**
	 * 播放时长
	 */
	private Short playTime;

	/**
	 * 播放次数
	 */
	private Short playTimes;

	/**
	 * 播放延时(秒)
	 */
	private Short playDelay;

	public LedProgGrab() {
	}

	public LedProgGrab(Short playTime, Short playTimes, Short playDelay) {
		super();
		this.playTime = playTime;
		this.playTimes = playTimes;
		this.playDelay = playDelay;
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

	public Short getPlayDelay() {
		return playDelay;
	}

	public void setPlayDelay(Short playDelay) {
		this.playDelay = playDelay;
	}

	@Override
	public void checkPlayParams() throws Exception {
		if (this.playDelay == null) {
			throw new Exception("未指定播放延时");
		}

		if (this.playTime != null && this.playTimes != null) {
			throw new Exception("播放时长和播放次数参数只能指定一个");
		}

		if (this.playTime == null && this.playTimes == null) {
			throw new Exception("播放时长和播放次数参数必须要指定一个");
		}
	}
}
