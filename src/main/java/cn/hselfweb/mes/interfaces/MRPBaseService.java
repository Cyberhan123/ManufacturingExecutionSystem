package cn.hselfweb.mes.interfaces;

import java.util.List;

import com.mes.entity.MRPBase;

public interface MRPBaseService {
	public int addMRPBase(MRPBase mrpb) ;
	public void modifyMRPBase(MRPBase mrpb);
	public void deleteMRPBase(MRPBase mrpb);
	
	public MRPBase findMRPBaseById(int id);
	
	public List<MRPBase> findAllMRPBase();
	
	public List<MRPBase> findMRPBaseByPropertys(String model,
			String[] propertyName, String[] value);
}
