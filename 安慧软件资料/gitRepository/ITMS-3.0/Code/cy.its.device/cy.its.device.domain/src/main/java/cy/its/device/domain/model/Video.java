package cy.its.device.domain.model;

public class Video extends SysParam {
	private String deviceId;

	private String domeGunlock;

	private String videoResolution;

	private String videoSuperviseType;

	private String dailyPreset;

	private String alarmPreset;

	private String isBackstageRecovery;

    private String cameraSn;

    private String previewParam;

    private String playbackParam;

    private String videoAccessMode;

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getDomeGunlock() {
		return domeGunlock;
	}

	public void setDomeGunlock(String domeGunlock) {
		this.domeGunlock = domeGunlock;
	}

	public String getVideoResolution() {
		return videoResolution;
	}

	public void setVideoResolution(String videoResolution) {
		this.videoResolution = videoResolution;
	}

	public String getVideoSuperviseType() {
		return videoSuperviseType;
	}

	public void setVideoSuperviseType(String videoSuperviseType) {
		this.videoSuperviseType = videoSuperviseType;
	}

	public String getDailyPreset() {
		return dailyPreset;
	}

	public void setDailyPreset(String dailyPreset) {
		this.dailyPreset = dailyPreset;
	}

	public String getAlarmPreset() {
		return alarmPreset;
	}

	public void setAlarmPreset(String alarmPreset) {
		this.alarmPreset = alarmPreset;
	}

	public String getIsBackstageRecovery() {
		return isBackstageRecovery;
	}

	public void setIsBackstageRecovery(String isBackstageRecovery) {
		this.isBackstageRecovery = isBackstageRecovery;
	}

    public String getCameraSn() {
        return cameraSn;
    }

    public void setCameraSn(String cameraSn) {
        this.cameraSn = cameraSn;
    }

    public String getPreviewParam() {
        return previewParam;
    }

    public void setPreviewParam(String previewParam) {
        this.previewParam = previewParam;
    }

    public String getPlaybackParam() {
        return playbackParam;
    }

    public void setPlaybackParam(String playbackParam) {
        this.playbackParam = playbackParam;
    }

    public String getVideoAccessMode() {
        return videoAccessMode;
    }

    public void setVideoAccessMode(String videoAccessMode) {
        this.videoAccessMode = videoAccessMode;
    }
	
		@Override
	public void attatchSys(String deviceId) {
		this.setDeviceId(deviceId);
	}
}