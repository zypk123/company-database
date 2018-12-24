package cy.its.device.domain.model.led;

public class LedProgNoChange {
	public String targetProgId;
	public String oldProgNo;
	public String newProgNo;
	
	public LedProgNoChange(String targetProgId, String oldProgNo, String newProgNo) {
		super();
		this.targetProgId = targetProgId;
		this.oldProgNo = oldProgNo;
		this.newProgNo = newProgNo;
	}
}
