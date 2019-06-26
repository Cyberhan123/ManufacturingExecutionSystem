package cn.hselfweb.mes.interfaces;

import java.util.List;

import com.mes.entity.Workpiece;

public interface WorkpieceService {
	public int addWorkpiece(Workpiece w) ;
	public void modifyWorkpiece(Workpiece w);
	public void deleteWorkpiece(Workpiece w);	
	public Workpiece findWorkpieceById(int wid);	
	public List<Workpiece> findAllWorkpiece();	
	public List<Workpiece> findWorkpieceByPropertys(String model,
			String[] propertyName, String[] value);
}
