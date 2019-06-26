package cn.hselfweb.mes.interfaces;

import java.util.List;

import com.mes.entity.CraftBase;


public interface CraftBaseService {

	public int addCraftBase(CraftBase cb) ;
	public void modifyCraftBase(CraftBase cb);
	public void deleteCraftBase(CraftBase cb);
	
	public CraftBase findCraftBaseById(int id);
	
	public List<CraftBase> findAllCraftBase();
	
	public List<CraftBase> findCraftBaseByPropertys(String model,
			String[] propertyName, String[] value);
	public CraftBase findCraftBaseByProperty(String model,
			String propertyName, String value);
	
}
