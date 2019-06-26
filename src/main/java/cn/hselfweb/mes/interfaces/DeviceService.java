package cn.hselfweb.mes.interfaces;

import java.util.List;

import com.mes.entity.Device;

public interface DeviceService {
	public int addDevice(Device d) ;
	public void modifyDevice(Device d);
	public void deleteDevice(Device d);
	
	public Device findDeviceById(int id);
	
	public List<Device> findAllDevice();
	
	public List<Device> findDeviceByPropertys(String model,
			String[] propertyName, String[] value);
}
