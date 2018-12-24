package cy.its.device.repository.led;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Service;

import cy.its.com.util.Base64Utils;
import cy.its.com.util.StringUtil;
import cy.its.device.domain.model.led.LedProg;
import cy.its.device.domain.model.led.LedProgCycle;
import cy.its.device.domain.model.led.LedProgEmergency;
import cy.its.device.domain.model.led.LedProgGrab;
import cy.its.device.domain.model.led.LedProgTimed;
import cy.its.device.domain.model.led.LedTimedParam;
import cy.its.device.domain.model.led.ReponseResult;
import cy.its.service.ledClient.ProgManager;
import cy.its.service.ledClient.generated.DeviceInfo;
import cy.its.service.ledClient.generated.EHorizontalAlign;
import cy.its.service.ledClient.generated.EMediaType;
import cy.its.service.ledClient.generated.EMidTextStyle;
import cy.its.service.ledClient.generated.EPlayStyle;
import cy.its.service.ledClient.generated.EProgramPriority;
import cy.its.service.ledClient.generated.EProgramShowMode;
import cy.its.service.ledClient.generated.EProgramType;
import cy.its.service.ledClient.generated.EVerticalAlign;
import cy.its.service.ledClient.generated.Media;
import cy.its.service.ledClient.generated.Program;
import cy.its.service.ledClient.generated.ProgramResponse;
import cy.its.service.ledClient.generated.TArea;
import cy.its.service.ledClient.generated.Text;
import cy.its.device.domain.repository.led.ILedProgControlRepository;

@Service
public class LedProgControlRepository implements ILedProgControlRepository {

	@Override
	public ReponseResult issueProgram(String serverIp, String deviceSysNbr, LedProg ledProg) throws Exception {
		try {
			DeviceInfo devInfo = new DeviceInfo();
			devInfo.devNo = deviceSysNbr;
			ProgramResponse pr = ProgManager.AddProgram(serverIp, devInfo, convert(ledProg));
			if (pr.responseCode == 0) {
				return new ReponseResult(true, null, null);
			} else {
				return new ReponseResult(false, pr.responseCode, pr.responseString);
			}
		} catch (Exception e) {
			return new ReponseResult(false, null, e.getMessage());
		}
	}

	@Override
	public ReponseResult modifyProgramInRemote(String serverIp, String deviceSysNbr, LedProg ledProg) throws Exception {
		try {
			DeviceInfo devInfo = new DeviceInfo();
			devInfo.devNo = deviceSysNbr;
			
			ProgramResponse pr = ProgManager.DeleteProgram(serverIp, devInfo, Integer.parseInt(ledProg.getProgramNo()));
			if (pr.responseCode == 0 || pr.responseCode == 3001) {
				pr = ProgManager.AddProgram(serverIp, devInfo, convert(ledProg));
				if (pr.responseCode == 0) {
					return new ReponseResult(true, null, null);
				} else {
					return new ReponseResult(false, pr.responseCode, pr.responseString);
				}
			} else {
				return new ReponseResult(false, pr.responseCode, pr.responseString);
			}
		} catch (Exception e) {
			return new ReponseResult(false, null, null);
		}
		
//		return execute(serverIp, deviceSysNbr, convert(ledProg), (s, d, k) -> ProgManager.ModifyProgram(s, d, k));
	}

	@Override
	public ReponseResult removeProgramInRemote(String ledSvrIp, String deviceSysNbr, int programNo) {		
		try {
			DeviceInfo devInfo = new DeviceInfo();
			devInfo.devNo = deviceSysNbr;
			ProgramResponse pr = ProgManager.DeleteProgram(ledSvrIp, devInfo, programNo);
			if (pr.responseCode == 0) {
				return new ReponseResult(true, null, null);
			} else {
				if(pr.responseCode == 3001) {
					return new ReponseResult(true, null, null);
				} else {
					return new ReponseResult(false, pr.responseCode, pr.responseString);
				}
			}
		} catch (Exception e) {
			return new ReponseResult(false, null, null);
		}
	}

	@Override
	public String[] getPlayingProgNoArr(String ledSvrIp, String deviceSysNbr) throws Exception {
		try {
			DeviceInfo devInfo = new DeviceInfo();
			devInfo.devNo = deviceSysNbr;
			ProgramResponse pr = ProgManager.GetPlayingProgram(ledSvrIp, devInfo);
			if (pr.responseCode == 0) {
				if (pr.listProgram != null) {
					return Arrays.asList(pr.listProgram).stream().map(p -> p.programNo).collect(Collectors.toList())
							.toArray(new String[0]);
				}
				return null;
			} else {
				if (pr.responseCode == 3001) {
					return null;
				} else {
					throw new Exception("获取正在播放的节目列表失败," + pr.responseString);
				}
			}
		} catch (Exception e) {
			throw new Exception("获取正在播放的节目列表失败," + e.getMessage());
		}
	}

	@Override
	public void swapProgram(String serverIp, String deviceSysNbr, String progNo1, String progNo2) throws Exception {
		DeviceInfo dev = new DeviceInfo();
		dev.devNo = deviceSysNbr;
		ProgramResponse pr = ProgManager.GetPrograms(serverIp, dev);

		if (pr.responseCode != 0) {
			throw new Exception("获取节目列表失败");
		}

		Program p1 = null, p2 = null;
		if (pr.listProgram != null) {
			String tmp;
			for (Program p : pr.listProgram) {
				tmp = String.valueOf(p.programNo);
				if (progNo1.equals(tmp)) {
					p1 = p;
				} else if (progNo2.equals(tmp)) {
					p2 = p;
				}
			}
		}

		if (p1 != null && p2 != null) {
			p1.programNo = Integer.parseInt(progNo2);
			p2.programNo = Integer.parseInt(progNo1);
			ProgramResponse r1 = ProgManager.ModifyProgram(serverIp, dev, p1);
			ProgramResponse r2 = ProgManager.ModifyProgram(serverIp, dev, p2);
			if (r1.responseCode != 0 || r2.responseCode != 0) {
				// 尝试恢复节目
				p1.programNo = Integer.parseInt(progNo1);
				p2.programNo = Integer.parseInt(progNo2);
				ProgManager.ModifyProgram(serverIp, dev, p1);
				ProgManager.ModifyProgram(serverIp, dev, p2);
				throw new Exception("同步节目至前端失败");
			}
		} else {
			throw new Exception("未获取到指定的节目");
		}
	}

	@SuppressWarnings("unchecked")
	private Program convert(LedProg ledProg) throws Exception {
		Program prog = new Program();
		prog.programNo = Integer.parseInt(ledProg.getProgramNo());
		prog.showMode = "0".equals(ledProg.getShowMode()) ? EProgramShowMode.ProgramShowModeNonExclusive
				: EProgramShowMode.ProgramShowModeExclusive;
		prog.priority = convert(ledProg.getProgramPriority());

		if (ledProg instanceof LedProgGrab) {
			format(prog, (LedProgGrab) ledProg);
		} else if (ledProg instanceof LedProgCycle) {
			format(prog, (LedProgCycle) ledProg);
		} else if (ledProg instanceof LedProgTimed) {
			format(prog, (LedProgTimed) ledProg);
		} else if (ledProg instanceof LedProgEmergency) {
			prog.programType = EProgramType.ProgramTypeEmergency;
		}
		
		Document doc = DocumentHelper.parseText(ledProg.getProgramContent());
		
		Element root = doc.getRootElement();
		

		List<Element> lst = root.elements("Text");		
		if(lst != null && lst.size() > 0){
			prog.listText = new Text[lst.size()];
			for (int i = 0; i < lst.size(); i++) {
				prog.listText[i] = convertText(lst.get(i), i + 1);
			}
		}
		
		lst = root.elements("Picture");
		if(lst != null && lst.size() > 0){
			prog.listMedia = new Media[lst.size()];
			for (int i = 0; i < lst.size(); i++) {
				prog.listMedia[i] = convertMedia(lst.get(i), i + 1);
			}
		}

		return prog;
	}

	private void format(Program prog, LedProgTimed tmp) {
		prog.programType = EProgramType.ProgramTypeTimed;
		LedTimedParam tp = tmp.getLedTimedParam();
		prog.timingMode = tp.getTimeMode();
		if (!StringUtil.isNullOrEmpty(tp.getStartDate())) {
			String[] arr = tp.getStartDate().split("/");
			prog.beginYear = Integer.parseInt(arr[0]);
			prog.beginMonth = Integer.parseInt(arr[1]);
			prog.beginDay = Integer.parseInt(arr[2]);
		}

		if (!StringUtil.isNullOrEmpty(tp.getEndDate())) {
			String[] arr = tp.getEndDate().split("/");
			prog.endYear = Integer.parseInt(arr[0]);
			prog.endMonth = Integer.parseInt(arr[1]);
			prog.endDay = Integer.parseInt(arr[2]);
		}

		if (!StringUtil.isNullOrEmpty(tp.getStartTime())) {
			String[] arr = tp.getStartTime().split("/");
			prog.beginHour = Integer.parseInt(arr[0]);
			prog.beginMinute = Integer.parseInt(arr[1]);
			prog.beginSecond = Integer.parseInt(arr[2]);
		}

		if (!StringUtil.isNullOrEmpty(tp.getEndTime())) {
			String[] arr = tp.getEndTime().split("/");
			prog.endHour = Integer.parseInt(arr[0]);
			prog.endMinute = Integer.parseInt(arr[1]);
			prog.endSecond = Integer.parseInt(arr[2]);
		}

		if (!StringUtil.isNullOrEmpty(tp.getWeek())) {
			prog.weekSelect = Integer.parseInt(tp.getWeek().replace(",", ""), 2);
		}
	}

	private void format(Program prog, LedProgCycle tmp) {
		prog.programType = EProgramType.ProgramTypeCycle;
		if (tmp.getPlayTime() != null) {
			prog.playTime = tmp.getPlayTime();
		}
		if (tmp.getPlayTimes() != null) {
			prog.playTimes = tmp.getPlayTimes();
		}
	}

	private void format(Program prog, LedProgGrab tmp) {
		prog.programType = EProgramType.ProgramTypeGrab;
		if (tmp.getPlayTime() != null) {
			prog.playTime = tmp.getPlayTime();
		}
		if (tmp.getPlayTimes() != null) {
			prog.playTimes = tmp.getPlayTimes();
		}
		if (tmp.getPlayDelay() != null) {
			prog.playDelay = tmp.getPlayDelay();
		}
	}

	private EProgramPriority convert(String progPriority) {		
		EProgramPriority priority = EProgramPriority.ProgramPriorityNotice;
		if(!StringUtil.isNullOrEmpty(progPriority)){
			switch (progPriority) {
			case "0":
				// 0:通知
				priority = EProgramPriority.ProgramPriorityNotice;
				break;
			case "1":
				// 1:警告
				priority = EProgramPriority.ProgramPriorityWarnning;
				break;
			case "2":
				// 2:关键的
				priority = EProgramPriority.ProgramPriorityCritical;
				break;
			case "3":
				// 3:紧急的
				priority = EProgramPriority.ProgramPriorityUrgent;
				break;
			case "4":
				// 4:计数作用
				priority = EProgramPriority.ProgramPriorityCount;
				break;
			default:
				break;
			}
		}
		return priority;
	}

	Text convertText(Element textNode, int textNo) throws Exception {
		Text text = new Text();
		text.textNo = textNo;

//		if (!StringUtil.isNullOrEmpty(ledText.getTextXml())) {
//			Document doc = DocumentHelper.parseText(ledText.getTextXml());
//			Element textNode = doc.getRootElement();
			text.area = extractTArea(textNode);
			text.area.left = Integer.parseInt(textNode.element("startX").getText());
			text.area.top = Integer.parseInt(textNode.element("startY").getText());
			text.area.height = Integer.parseInt(textNode.element("height").getText());
			text.area.width = Integer.parseInt(textNode.element("width").getText());
			Element contentsNode = textNode.element("Contents");
			text.content = Base64Utils.decodeString(contentsNode.elementTextTrim("textinfo"), "utf-8");
			// 文本水平对齐方式：1-左对齐，2-右对齐，3-水平居中
			text.hAlign = EHorizontalAlign.HorizontalAlignMid;
			switch (contentsNode.elementTextTrim("fonthalignment")) {
			case "1":
				text.hAlign = EHorizontalAlign.HorizontalAlignLeft;
				break;
			case "2":
				text.hAlign = EHorizontalAlign.HorizontalAlignRight;
				break;
			case "3":
				text.hAlign = EHorizontalAlign.HorizontalAlignMid;
				break;
			default:
				break;
			}

			// 文本垂直对齐方式：1-上对齐，2-下对齐，3-垂直居中
			text.vAlign = EVerticalAlign.VerticalAlignMid;
			switch (contentsNode.elementTextTrim("fontvalignment")) {
			case "1":
				text.vAlign = EVerticalAlign.VerticalAlignTop;
				break;
			case "2":
				text.vAlign = EVerticalAlign.VerticalAlignBottom;
				break;
			case "3":
				text.vAlign = EVerticalAlign.VerticalAlignMid;
				break;
			default:
				break;
			}

			// 特效-PlayStyleNone-0无特效,PlayStyleMove2Left-1连续左移,PlayStyleMove2Right-2连续右移,
			// PlayStyleMove2Up-3连续上移,PlayStyleMove2Down-4连续下移
			text.playStyle = EPlayStyle.PlayStyleMove2Left;
			switch (contentsNode.elementTextTrim("enterEffect")) {
			case "PlayStyleNone":
				text.playStyle = EPlayStyle.PlayStyleNone;
				break;
			case "PlayStyleMove2Left":
				text.playStyle = EPlayStyle.PlayStyleMove2Left;
				break;
			case "PlayStyleMove2Right":
				text.playStyle = EPlayStyle.PlayStyleMove2Right;
				break;
			case "PlayStyleMove2Up":
				text.playStyle = EPlayStyle.PlayStyleMove2Top;
				break;
			case "PlayStyleMove2Down":
				text.playStyle = EPlayStyle.PlayStyleMove2Bottom;
				break;
			default:
				break;
			}

			text.playSpeed = Integer.parseInt(contentsNode.elementTextTrim("enterSpeed"));
			text.exitStyle = EPlayStyle.PlayStyleNone;
			// text.int playTime;
			// text.int playTimes;
			text.fontName = Base64Utils.decodeString(contentsNode.elementTextTrim("font"), "utf-8");
			text.fontSize = Integer.parseInt(contentsNode.elementTextTrim("fontsize"));
			text.fontColor = Integer.parseInt(contentsNode.elementTextTrim("fontcolor"));
			text.bold = Integer.parseInt(contentsNode.elementTextTrim("fontbold"));
			text.italic = Integer.parseInt(contentsNode.elementTextTrim("fontitalic"));
			text.underline = Integer.parseInt(contentsNode.elementTextTrim("fontunderline"));
			text.midTextStyle = EMidTextStyle.MidTextStyleNormal;
			// text.lineSpacing = ;
//		}

		return text;

	}

	Media convertMedia(Element picNode, int mediaNo) throws DocumentException, UnsupportedEncodingException {
		Media media = new Media();
		media.mediaNo = mediaNo;

//		if (!StringUtil.isNullOrEmpty(ledMedia.getMediaXml())) {
//			Document doc = DocumentHelper.parseText(ledMedia.getMediaXml());
//			Element picNode = doc.getRootElement();
			media.area = extractTArea(picNode);
			Element contentsNode = picNode.element("Contents");

			media.mediaType = EMediaType.MediaTypePicture;			
//			String pathUrl = Base64Utils.decodeString(contentsNode.elementTextTrim("path"), "utf-8");
			//todo:
//			media.mediaRawData = getFileFromServer(pathUrl);
			media.mediaRawData = Base64Utils.decode(contentsNode.elementTextTrim("picdata"));
			// media.String mediaName;
			// media.String webUrl;
			switch (contentsNode.elementTextTrim("enterEffect")) {
			case "PlayStyleNone":
				media.playStyle = EPlayStyle.PlayStyleNone;
				break;
			case "PlayStyleMove2Left":
				media.playStyle = EPlayStyle.PlayStyleMove2Left;
				break;
			case "PlayStyleMove2Right":
				media.playStyle = EPlayStyle.PlayStyleMove2Right;
				break;
			case "PlayStyleMove2Up":
				media.playStyle = EPlayStyle.PlayStyleMove2Top;
				break;
			case "PlayStyleMove2Down":
				media.playStyle = EPlayStyle.PlayStyleMove2Bottom;
				break;
			default:
				break;
			}
			media.playSpeed = Integer.parseInt(contentsNode.elementTextTrim("enterSpeed"));
			// media.int playTime;
			media.exitStyle = EPlayStyle.PlayStyleNone;
//		}

		return media;

	}

	private TArea extractTArea(Element textNode) {
		TArea area = new TArea();
		area.left = Integer.parseInt(textNode.element("startX").getText());
		area.top = Integer.parseInt(textNode.element("startY").getText());
		area.height = Integer.parseInt(textNode.element("height").getText());
		area.width = Integer.parseInt(textNode.element("width").getText());
		area.vAlign = EVerticalAlign.VerticalAlignTop;
		area.hAlign = EHorizontalAlign.HorizontalAlignLeft;
		return area;
	}

	private <K> ReponseResult execute(String serverIp, String deviceSysNbr, K k, PrFunc<K> func) {
		try {
			DeviceInfo devInfo = new DeviceInfo();
			devInfo.devNo = deviceSysNbr;
			ProgramResponse pr = func.apply(serverIp, devInfo, k);
			if (pr.responseCode == 0) {
				return new ReponseResult(true, null, null);
			} else {
				return new ReponseResult(false, pr.responseCode, pr.responseString);
			}
		} catch (Exception e) {
			return new ReponseResult(false, null, null);
		}
	}

	@Override
	public void changeProgNoSeq(String ledSvrIp, String deviceSysNbr, Map<Integer, Integer> oldNewProgNoMap)
			throws Exception {
		DeviceInfo dev = new DeviceInfo();
		dev.devNo = deviceSysNbr;
		ProgramResponse pr = ProgManager.GetPrograms(ledSvrIp, dev);

		if (pr.responseCode == 0) {
			List<Program> progArr = Arrays.asList(pr.listProgram);
			List<Program> targetProgArr = progArr.stream().filter(p -> oldNewProgNoMap.containsKey(p.programNo))
					.collect(Collectors.toList());
			for (Program p : targetProgArr) {
				ProgManager.DeleteProgram(ledSvrIp, dev, p.programNo);
			}
			
			for (Program p : targetProgArr) {
				p.programNo = oldNewProgNoMap.get(p.programNo);
				ProgManager.AddProgram(ledSvrIp, dev, p);
			}
		} else {
			throw new Exception(pr.responseString);
		}
	}

}

@FunctionalInterface
interface PrFunc<T> {
	ProgramResponse apply(String svrIp, DeviceInfo devInfo, T t) throws Exception;
}
