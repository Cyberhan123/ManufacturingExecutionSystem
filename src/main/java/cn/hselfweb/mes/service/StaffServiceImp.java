package cn.hselfweb.mes.service;

import cn.hselfweb.mes.interfaces.StaffService;
import com.mes.entity.Staff;

import java.util.List;

public class StaffServiceImp implements StaffService {

	@Override
	public int addStaff(Staff s) {
		return 0;
	}

	@Override
	public void modifyStaff(Staff s) {

	}

	@Override
	public void deleteStaff(Staff s) {

	}

	@Override
	public Staff findStaffById(int sid) {
		return null;
	}

	@Override
	public List<Staff> findAllStaff() {
		return null;
	}

	@Override
	public List<Staff> findStaffByPropertys(String model, String[] propertyName, String[] value) {
		return null;
	}
}
