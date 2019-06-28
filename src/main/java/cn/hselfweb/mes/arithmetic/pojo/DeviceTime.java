package cn.hselfweb.mes.arithmetic.pojo;

public class DeviceTime {
   
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public Integer getDeviceTime() {
		return deviceTime;
	}
	public void setDeviceTime(Integer deviceTime) {
		this.deviceTime = deviceTime;
	}
	private String deviceName;//设备名称
    private Integer deviceTime;//设备最长时间

}
