package cn.hselfweb.mes.service;

import cn.hselfweb.mes.interfaces.WorkcenterService;
import com.mes.entity.Workcenter;

import java.util.List;

public class WorkcenterServiceImp implements WorkcenterService {


	@Override
	public int addWorkcenter(Workcenter wc) {
		return 0;
	}

	@Override
	public void modifyWorkcenter(Workcenter wc) {

	}

	@Override
	public void deleteWorkcenter(Workcenter wc) {

	}

	@Override
	public Workcenter findWorkcenterById(int id) {
		return null;
	}

	@Override
	public List<Workcenter> findAllWorkcenter() {
		return null;
	}

	@Override
	public List<Workcenter> findWorkcenterByPropertys(String model, String[] propertyName, String[] value) {
		return null;
	}
}
