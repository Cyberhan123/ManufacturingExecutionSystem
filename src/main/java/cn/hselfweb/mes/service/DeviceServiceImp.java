package cn.hselfweb.mes.service;

import cn.hselfweb.mes.interfaces.DeviceService;
import com.mes.entity.Device;

import java.util.List;


public class DeviceServiceImp implements DeviceService {


	@Override
	public int addDevice(Device d) {
		return 0;
	}

	@Override
	public void modifyDevice(Device d) {

	}

	@Override
	public void deleteDevice(Device d) {

	}

	@Override
	public Device findDeviceById(int id) {
		return null;
	}

	@Override
	public List<Device> findAllDevice() {
		return null;
	}

	@Override
	public List<Device> findDeviceByPropertys(String model, String[] propertyName, String[] value) {
		return null;
	}
}
