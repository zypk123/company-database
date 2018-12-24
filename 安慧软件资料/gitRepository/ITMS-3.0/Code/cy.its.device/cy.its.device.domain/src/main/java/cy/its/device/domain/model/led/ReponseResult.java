package cy.its.device.domain.model.led;

public class ReponseResult {

	private boolean success;
	
	private Integer responseCode;

	private String responseString;
	

	public ReponseResult(boolean success, Integer responseCode, String responseString) {
		super();
		this.success = success;
		this.responseCode = responseCode;
		this.responseString = responseString;
	}

	public boolean getSuccess() {
		return success;
	}
	
	public Integer getResponseCode() {
		return responseCode;
	}

	public String getResponseString() {
		return responseString;
	}	
}
