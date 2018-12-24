package cy.its.device.domain.model.led;

public class LedPowerGroup {
	public int startHour;

	public int startMinute;

	public int endHour;

	public int endMinute;

	public LedPowerGroup(int startHour, int startMinute, int endHour, int endMinute) {
		super();
		this.startHour = startHour;
		this.startMinute = startMinute;
		this.endHour = endHour;
		this.endMinute = endMinute;
	}
}
