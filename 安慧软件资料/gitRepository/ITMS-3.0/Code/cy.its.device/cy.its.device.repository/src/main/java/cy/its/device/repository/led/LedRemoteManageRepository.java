package cy.its.device.repository.led;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import cy.its.device.domain.model.Sys;
import cy.its.device.domain.model.led.Led;
import cy.its.device.domain.model.led.LedSpec;
import cy.its.device.domain.repository.led.ILedRemoteManageRepository;
import cy.its.service.ledClient.LedManager;
import cy.its.service.ledClient.generated.TgcsDevice;

@Service
public class LedRemoteManageRepository implements ILedRemoteManageRepository {

	@Override
	public boolean addLedDevice(String ip, Sys sys, Led led, LedSpec spec) throws Exception {
		TgcsDevice tgcsDev = convert(sys, spec);
		// 检查前端服务器上设备是否存在
		TgcsDevice devInRemote = getLedInRemote(ip, sys.getDeviceSysNbr());		
		if(devInRemote != null) {
			// 前端服务器上存在此设备, 比较关键属性
			List<String> lstDiff = compareForDiff(tgcsDev, devInRemote);
			if(lstDiff.size() > 0) {
				// 存在不一致的属性
				throw new Exception("当前诱导屏在服务器已存在, 但关键属性不一致, 请检查本地配置, 不同的属性有:"+ String.join(",", lstDiff));
			}
			
			return true;
		} else {
			// 前端服务器上不存在此设备, 直接添加
			int retCode = LedManager.AddDevice(ip, new TgcsDevice[] { tgcsDev });
			return retCode == 0;
		}
	}


	@Override
	public boolean deleteLedDevice(String ip, String deviceSysNbr) throws Exception {
		// 检查前端服务器上设备是否存在
		TgcsDevice devInRemote = getLedInRemote(ip, deviceSysNbr);
		if(devInRemote == null) {
			// led设备不存在, 不做任何处理
			return true;
		}
		
//		TgcsDevice tgcsDev = new TgcsDevice();
//		tgcsDev.devNo = deviceSysNbr;
		int retCode = LedManager.DeleteDevice(ip, new TgcsDevice[]{devInRemote});		
		return retCode == 0;
	}
	

	@Override
	public void modifyLedDevice(String ip, Sys sys, Led led, LedSpec spec) {
		

	}

	/**
	 * 根据设备编号获取远程服务器上的LED设备
	 * @param ip
	 * @param deviceSysNbr
	 * @return
	 * @throws Exception
	 */
	private TgcsDevice getLedInRemote(String ip, String deviceSysNbr) throws Exception {		
		TgcsDevice[] tgcsDevArr = LedManager.GetDevices(ip);		
		TgcsDevice devInRemote = null;
		if(tgcsDevArr != null && tgcsDevArr.length > 0){
			devInRemote = Arrays.asList(tgcsDevArr).stream().filter(c->deviceSysNbr.equals(c.devNo)).findFirst().orElse(null);
		}
		return devInRemote;
	}

	private TgcsDevice convert(Sys sys, LedSpec spec) {
		TgcsDevice tgcsDev = new TgcsDevice();
		tgcsDev.devNo = sys.getDeviceSysNbr();
		// 1 无任何意义 只是为了防止调用出错
		tgcsDev.regionNo = "1";
		tgcsDev.devName = sys.getDeviceName();
		tgcsDev.devType = spec.getLedDeviceType();
		tgcsDev.devModel = spec.getLedDeviceModel();
		tgcsDev.devIp = sys.getDeviceIp();
		if (sys.getDevicePort() != null) {
			tgcsDev.devPort = sys.getDevicePort().intValue();
		}
		tgcsDev.witdh = spec.getPixesWidth();
		tgcsDev.height = spec.getPixesHeight();
		tgcsDev.colorType = Integer.parseInt(spec.getColorType());
		return tgcsDev;
	}

	private List<String> compareForDiff(TgcsDevice tgcsDev, TgcsDevice devInRemote) {
		List<String> lstDiff = new ArrayList<String>();
		if (!tgcsDev.devName.equals(devInRemote.devName)) {
			lstDiff.add("名称");
		}
		if (!tgcsDev.devType.equals(devInRemote.devType)) {
			lstDiff.add("LED设备类型");
		}
		if (!tgcsDev.devModel.equals(devInRemote.devModel)) {
			lstDiff.add("LED设备型号");
		}
		if (!tgcsDev.devIp.equals(devInRemote.devIp)) {
			lstDiff.add("设备IP");
		}
		if (tgcsDev.devPort != devInRemote.devPort) {
			lstDiff.add("端口");
		}
		if (tgcsDev.witdh != devInRemote.witdh) {
			lstDiff.add("像素宽");
		}
		if (tgcsDev.height != devInRemote.height) {
			lstDiff.add("像素高");
		}
		if (tgcsDev.colorType != devInRemote.colorType) {
			lstDiff.add("颜色类型");
		}
		
		return lstDiff;
	}
	
}
