package cn.hselfweb.mes.interfaces;

import java.util.List;

import com.mes.entity.Workcenter;

public interface WorkcenterService {
	public int addWorkcenter(Workcenter wc) ;
	public void modifyWorkcenter(Workcenter wc);
	public void deleteWorkcenter(Workcenter wc);
	
	public Workcenter findWorkcenterById(int id);
	
	public List<Workcenter> findAllWorkcenter();
	
	public List<Workcenter> findWorkcenterByPropertys(String model,
			String[] propertyName, String[] value);
}
