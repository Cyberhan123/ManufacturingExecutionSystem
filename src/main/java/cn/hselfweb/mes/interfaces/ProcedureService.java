package cn.hselfweb.mes.interfaces;

import java.util.List;

import com.mes.entity.Procedure;


public interface ProcedureService {

	public int addProcedure(Procedure p) ;
	public void modifyProcedure(Procedure p);
	public void deleteProcedure(Procedure p);
	
	public Procedure findProcedureById(int id);
	
	public List<Procedure> findAllProcedure();
	
	public List<Procedure> findProcedureByPropertys(String model,
			String[] propertyName, String[] value);
	
	public Procedure findProcedureByProperty(String model,
			String propertyName, String value);
	
}
