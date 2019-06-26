package cn.hselfweb.mes.service;

import cn.hselfweb.mes.interfaces.WorkpieceService;
import com.mes.entity.Workpiece;

import java.util.List;

public class WorkpieceServiceImp implements WorkpieceService {


	@Override
	public int addWorkpiece(Workpiece w) {
		return 0;
	}

	@Override
	public void modifyWorkpiece(Workpiece w) {

	}

	@Override
	public void deleteWorkpiece(Workpiece w) {

	}

	@Override
	public Workpiece findWorkpieceById(int wid) {
		return null;
	}

	@Override
	public List<Workpiece> findAllWorkpiece() {
		return null;
	}

	@Override
	public List<Workpiece> findWorkpieceByPropertys(String model, String[] propertyName, String[] value) {
		return null;
	}
}
