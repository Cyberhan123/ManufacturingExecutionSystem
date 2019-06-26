package cn.hselfweb.mes.interfaces;

import java.util.List;

import com.mes.entity.Staff;

public interface StaffService {
	public int addStaff(Staff s) ;
	public void modifyStaff(Staff s);
	public void deleteStaff(Staff s);	
	public Staff findStaffById(int sid);	
	public List<Staff> findAllStaff();	
	public List<Staff> findStaffByPropertys(String model,
			String[] propertyName, String[] value);
}
