package cn.hselfweb.mes.interfaces;

import java.util.List;

import com.mes.entity.StaffAbility;

public interface StaffAbilityService {
	public int addStaffAbility(StaffAbility sa) ;
	public void modifyStaffAbility(StaffAbility sa);
	public void deleteStaffAbility(StaffAbility sa);
	public StaffAbility findStaffAbilityById(int said);
	public List<StaffAbility> findAllStaffAbility();
	public List<StaffAbility> findStaffAbilityByPropertys(String model,
			String[] propertyName, String[] value);
}
