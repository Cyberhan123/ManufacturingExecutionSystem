package cn.hselfweb.mes.service;

import cn.hselfweb.mes.interfaces.MRPBaseService;
import com.mes.entity.MRPBase;

import java.util.List;

public class MRPBaseServiceImp implements MRPBaseService {


	@Override
	public int addMRPBase(MRPBase mrpb) {
		return 0;
	}

	@Override
	public void modifyMRPBase(MRPBase mrpb) {

	}

	@Override
	public void deleteMRPBase(MRPBase mrpb) {

	}

	@Override
	public MRPBase findMRPBaseById(int id) {
		return null;
	}

	@Override
	public List<MRPBase> findAllMRPBase() {
		return null;
	}

	@Override
	public List<MRPBase> findMRPBaseByPropertys(String model, String[] propertyName, String[] value) {
		return null;
	}
}
