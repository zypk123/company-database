package cy.its.device.domain.model.led;


import cy.its.com.util.StringUtil;
import cy.its.device.domain.model.Sys;
import cy.its.device.domain.model.SysParam;
import cy.its.device.domain.repository.led.ILedProgRepository;
import cy.its.device.domain.repository.led.ILedRemoteManageRepository;
import cy.its.device.domain.repository.led.ILedSpecRepository;
import cy.its.device.domain.service.ISurveySystemService;

/**
 * LED
 * 
 * @author STJ
 *
 */
public class Led extends SysParam {
	private String deviceId;

    private String specId;

    private String relatedVideoId;

    private String ledLevel;

    private String timeOnoffPlan;

    private String outDoors;

    private Short maxViewRange;

    private Short minViewPixel;

    private String screedContractor;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getSpecId() {
        return specId;
    }

    public void setSpecId(String specId) {
        this.specId = specId;
    }

    public String getRelatedVideoId() {
        return relatedVideoId;
    }

    public void setRelatedVideoId(String relatedVideoId) {
        this.relatedVideoId = relatedVideoId;
    }

    public String getLedLevel() {
        return ledLevel;
    }

    public void setLedLevel(String ledLevel) {
        this.ledLevel = ledLevel;
    }

    public String getTimeOnoffPlan() {
        return timeOnoffPlan;
    }

    public void setTimeOnoffPlan(String timeOnoffPlan) {
        this.timeOnoffPlan = timeOnoffPlan;
    }

    public String getOutDoors() {
        return outDoors;
    }

    public void setOutDoors(String outDoors) {
        this.outDoors = outDoors;
    }

    public Short getMaxViewRange() {
        return maxViewRange;
    }

    public void setMaxViewRange(Short maxViewRange) {
        this.maxViewRange = maxViewRange;
    }

    public Short getMinViewPixel() {
        return minViewPixel;
    }

    public void setMinViewPixel(Short minViewPixel) {
        this.minViewPixel = minViewPixel;
    }

    public String getScreedContractor() {
        return screedContractor;
    }

    public void setScreedContractor(String screedContractor) {
        this.screedContractor = screedContractor;
    }

	/**
	 * 检查诱导屏规格
	 * 
	 * @throws Exception
	 */
	public void checkSpec() throws Exception {
		if (StringUtil.isNullOrEmpty(this.specId)) {
			throw new Exception("诱导屏规格不可为空");
		}
	}

	public void checkLedSpec(Sys sys, ILedSpecRepository ledSpecRepository) throws Exception {
		if (StringUtil.isNullOrEmpty(sys.getDeviceIp())) {
			throw new Exception("诱导屏IP未指定");
		}

		this.checkSpec();
		LedSpec ledSpec = ledSpecRepository.aggregateOfId(this.specId);
		if (ledSpec == null) {
			throw new Exception("诱导屏规格不存在");
		}

		if ("CYLedPlayer".equals(ledSpec.getLedDeviceModel())) {
			// 屏幕型号为 超远LED同步屏
			if (sys.getDevicePort() == null) {
				throw new Exception("当前屏为超远LED同步屏 请指定端口");
			}
		}

		if (StringUtil.isNullOrEmpty(sys.getServerPlatId())) {
			throw new Exception("诱导屏设备未配置服务器平台");
		}
	}

	public void enable(Sys sys, ILedSpecRepository ledSpecRepository, ISurveySystemService surveySystemService,
			ILedRemoteManageRepository ledRemoteManageRepository) throws Exception {
		LedSpec spec = ledSpecRepository.aggregateOfId(this.specId);
		String appTypeComm = "1";
		String ip = surveySystemService.serverIpOfPlatId(sys.getServerPlatId(), appTypeComm);
		if (!ledRemoteManageRepository.addLedDevice(ip, sys, this, spec)) {
			throw new Exception("向控制服务器添加诱导屏设备失败,无法启用");
		}
	}

	public void stop(Sys sys, ILedProgRepository ledProgRepository, ISurveySystemService surveySystemService,
			ILedRemoteManageRepository ledRemoteManageRepository) throws Exception {
		int progCount = ledProgRepository.progCountOfLed(this.deviceId);
		if (progCount > 0) {
			throw new Exception("当前诱导屏下存在节目,禁止删除");
		}

		String appTypeComm = "1";
		String ip = surveySystemService.serverIpOfPlatId(sys.getServerPlatId(), appTypeComm);

		if (!ledRemoteManageRepository.deleteLedDevice(ip, sys.getDeviceSysNbr())) {
			throw new Exception("从控制服务器上删除诱导屏设备失败, 无法停用");
		}
	}

	public void checkLedCanModify(Led oldLed, Sys sys, ILedProgRepository ledProgRepository) throws Exception {

		this.checkSpec();

		if (!this.specId.equals(oldLed.getSpecId())) {
			// 诱导屏规格发生变化
			// 检查诱导屏有无关联的节目
			int progCount = ledProgRepository.progCountOfLed(oldLed.getDeviceId());
			if (progCount > 0) {
				throw new Exception("当前诱导屏下存在节目, 禁止改变诱导屏设备的规格");
			}

			if (sys.isInUseStatus()) {
				// 当前诱导屏处于启用状态
				throw new Exception("当前诱导屏处于启用状态, 禁止改变诱导屏设备的规格");
			}
		}
	}

//	public boolean setLedPower(ILedControlRepository ledControlRepository,
//			ISysRepository sysRepository, ISurveySystemService surveySystemService, 
//			boolean onOrOff) {		
//		Sys sys = sysRepository.aggregateOfId(deviceId);
//		if (sys == null) {
//			throw new Exception("当前LED设备不存在");
//		}
//
//		if (!sys.isInUseStatus()) {
//			throw new Exception("当前LED设备未处于启用状态,禁止发送控制指令");
//		}
//
//		if (!StringUtil.isNullOrEmpty(sys.getServerPlatId())) {
//			throw new Exception("当前LED设备未配置接入平台");
//		}
//
//		String ledSvrIp = surveySystemService.serverIpOfPlatId(sys.getServerPlatId(), "1");
//		
//		ledControlRepository.setLedPower(lstParam.get(1), lstParam.get(0), onOrOff)
//	}
//	
//	public void setLedPowerTimed() {
//		
//	}
//	
//	public void setLedBrightness() {
//		
//	}
//	
//	public void syncLedTime() {
//		
//	}
	
	@Override
	public void attatchSys(String deviceId) {
		this.deviceId = deviceId;
	}
}