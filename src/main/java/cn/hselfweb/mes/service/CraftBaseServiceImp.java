package cn.hselfweb.mes.service;

import cn.hselfweb.mes.interfaces.CraftBaseService;
import com.mes.entity.CraftBase;

import java.util.List;

public class CraftBaseServiceImp implements CraftBaseService {


	@Override
	public int addCraftBase(CraftBase cb) {
		return 0;
	}

	@Override
	public void modifyCraftBase(CraftBase cb) {

	}

	@Override
	public void deleteCraftBase(CraftBase cb) {

	}

	@Override
	public CraftBase findCraftBaseById(int id) {
		return null;
	}

	@Override
	public List<CraftBase> findAllCraftBase() {
		return null;
	}

	@Override
	public List<CraftBase> findCraftBaseByPropertys(String model, String[] propertyName, String[] value) {
		return null;
	}

	@Override
	public CraftBase findCraftBaseByProperty(String model, String propertyName, String value) {
		return null;
	}
}
