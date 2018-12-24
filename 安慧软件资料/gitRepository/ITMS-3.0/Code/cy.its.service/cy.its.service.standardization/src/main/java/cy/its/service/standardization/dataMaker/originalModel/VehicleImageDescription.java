package cy.its.service.standardization.dataMaker.originalModel;

import java.util.Map;

public class VehicleImageDescription {

	private VehicleImageIdentity imageIdentity;

	private int snapShotMode;

	private int frameIndex;

	private int imageWidth;

	private int imageHeight;

	private int imageWidthStep;

	private ImageFormat format;

	private String timeStamp;

	private int imageDataLength;

	private int imageDataCRC;

	private String watermark;

	private ImageLocation location;

	private Map<String, String> properies;

	public VehicleImageIdentity getImageIdentity() {
		return imageIdentity;
	}

	public void setImageIdentity(VehicleImageIdentity imageIdentity) {
		this.imageIdentity = imageIdentity;
	}

	public int getSnapShotMode() {
		return snapShotMode;
	}

	public void setSnapShotMode(int snapShotMode) {
		this.snapShotMode = snapShotMode;
	}

	public int getFrameIndex() {
		return frameIndex;
	}

	public void setFrameIndex(int frameIndex) {
		this.frameIndex = frameIndex;
	}

	public int getImageWidth() {
		return imageWidth;
	}

	public void setImageWidth(int imageWidth) {
		this.imageWidth = imageWidth;
	}

	public int getImageHeight() {
		return imageHeight;
	}

	public void setImageHeight(int imageHeight) {
		this.imageHeight = imageHeight;
	}

	public int getImageWidthStep() {
		return imageWidthStep;
	}

	public void setImageWidthStep(int imageWidthStep) {
		this.imageWidthStep = imageWidthStep;
	}

	public ImageFormat getFormat() {
		return format;
	}

	public void setFormat(ImageFormat format) {
		this.format = format;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public int getImageDataLength() {
		return imageDataLength;
	}

	public void setImageDataLength(int imageDataLength) {
		this.imageDataLength = imageDataLength;
	}

	public int getImageDataCRC() {
		return imageDataCRC;
	}

	public void setImageDataCRC(int imageDataCRC) {
		this.imageDataCRC = imageDataCRC;
	}

	public String getWatermark() {
		return watermark;
	}

	public void setWatermark(String watermark) {
		this.watermark = watermark;
	}

	public ImageLocation getLocation() {
		return location;
	}

	public void setLocation(ImageLocation location) {
		this.location = location;
	}

	public Map<String, String> getProperies() {
		return properies;
	}

	public void setProperies(Map<String, String> properies) {
		this.properies = properies;
	}

}
