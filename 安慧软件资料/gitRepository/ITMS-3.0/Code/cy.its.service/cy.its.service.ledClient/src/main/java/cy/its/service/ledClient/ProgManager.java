package cy.its.service.ledClient;

import cy.its.service.ledClient.generated.DeviceInfo;
import cy.its.service.ledClient.generated.Program;
import cy.its.service.ledClient.generated.ProgramResponse;
import cy.its.service.ledClient.proxy.TgcsInterfacePrxFactory;

public class ProgManager {

	public static ProgramResponse AddProgram(String server, DeviceInfo devInfo, Program prog) throws Exception {
		devInfo.screenNo = 1;
		return TgcsInterfacePrxFactory.getProxy(server).AddProgram(devInfo, prog);
	}

	public static ProgramResponse GetPlayingProgram(String server, DeviceInfo devInfo) throws Exception {
		return TgcsInterfacePrxFactory.getProxy(server).GetPlayingProgram(devInfo);
	}

	public static ProgramResponse ModifyProgram(String server, DeviceInfo devInfo, Program prog) throws Exception {
		return TgcsInterfacePrxFactory.getProxy(server).ModifyProgram(devInfo, prog);
	}

	public static ProgramResponse DeleteProgram(String server, DeviceInfo devInfo, int programNo) throws Exception {
		return TgcsInterfacePrxFactory.getProxy(server).DeleteProgram(devInfo, programNo);
	}

	public static ProgramResponse GetPrograms(String server, DeviceInfo devInfo) throws Exception {
		return TgcsInterfacePrxFactory.getProxy(server).GetPrograms(devInfo);
	}

	public static ProgramResponse ClearProgram(String server, DeviceInfo devInfo) throws Exception {
		return TgcsInterfacePrxFactory.getProxy(server).ClearProgram(devInfo);
	}

	public static ProgramResponse ExcludeProgram(String server, DeviceInfo devInfo, Program prog) throws Exception {
		return TgcsInterfacePrxFactory.getProxy(server).ExcludeProgram(devInfo, prog);
	}
}
