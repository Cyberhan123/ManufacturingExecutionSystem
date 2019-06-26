package cn.hselfweb.mes.service;

import cn.hselfweb.mes.interfaces.ProcedureService;
import com.mes.entity.Procedure;

import java.util.List;

public class ProcedureServiceImp implements ProcedureService {

	@Override
	public int addProcedure(Procedure p) {
		return 0;
	}

	@Override
	public void modifyProcedure(Procedure p) {

	}

	@Override
	public void deleteProcedure(Procedure p) {

	}

	@Override
	public Procedure findProcedureById(int id) {
		return null;
	}

	@Override
	public List<Procedure> findAllProcedure() {
		return null;
	}

	@Override
	public List<Procedure> findProcedureByPropertys(String model, String[] propertyName, String[] value) {
		return null;
	}

	@Override
	public Procedure findProcedureByProperty(String model, String propertyName, String value) {
		return null;
	}
}
