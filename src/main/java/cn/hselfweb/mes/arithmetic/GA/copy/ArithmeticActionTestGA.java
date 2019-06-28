package cn.hselfweb.mes.arithmetic.GA.copy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import cn.hselfweb.mes.arithmetic.GA.linkedlist.DoubleLinkedList;
import cn.hselfweb.mes.enity.CraftExtend;
import cn.hselfweb.mes.enity.Device;
import org.apache.struts2.ServletActionContext;

import com.mes.arithmetic.GA.linkedlist.DoubleLinkedList;

import com.mes.arithmetic.entity.CraftTime;
import com.mes.arithmetic.entity.DeviceTime;
import com.mes.arithmetic.entity.GAList;
import com.mes.entity.CraftBase;
import com.mes.entity.CraftExtend;
import com.mes.entity.Device;
import com.mes.entity.MRPBase;
import com.mes.entity.MRPExtend;
import com.mes.entity.ProcessDispatchResult;
import com.mes.entity.Workpiece;
import com.mes.service.CraftBaseService;
import com.mes.service.CraftExtendService;
import com.mes.service.DeviceService;
import com.mes.service.MRPBaseService;
import com.mes.service.MRPExtendService;
import com.mes.service.ProcedureService;
import com.mes.service.ProcedureTypeService;
import com.mes.service.WorkpieceService;
import com.opensymphony.xwork2.ActionSupport;

public class ArithmeticActionTestGA  {

	public static final String execute() throws Exception {
		
		GAList data = GAList.Load();

		int JM[][] = new int[8][8];
		int T[][] = new int[8][8];
		
		//  设备默认编号由1开始，使用几个则编号至几
		List<Device> listbd = new ArrayList<Device>();
		for (int i = 0; i < data.getDeviceNumber(); i++) {
			Device device = new Device();
			device.setId(i+1);
			device.setName("设备"+(i+1));
			listbd.add(device);
		}
		
		//=======================为获取的设备进行编号===========================
		Map<String,Integer> bdmap = new HashMap<String,Integer>();
		int bdnum = 1;
		for(Device bd:listbd){	
			System.out.println("设备ID："+bd.getId()+" 设备名："+bd.getName()+"设备号："+bdnum);
			bdmap.put(bd.getName(),bdnum);
			bdnum++;
		}
		
		List<List<CraftExtend>> list_sum = data.getList_sum();//用于存储工艺和和工序的关系
		
		//=======================用于存储LIST_SUM的每个基本工艺的详细工序=========================
        //输出list_sum
		for(int i = 0;i<list_sum.size();i++){
			System.out.println("*******工件名"+list_sum.get(i).get(0).getWpname()+":");
			for(CraftExtend ec:list_sum.get(i)){
				System.out.println("工序编号："+ec.getGid());
				System.out.println("工件名称："+ec.getName());
				System.out.println("工件编号："+ec.getWpid());
				System.out.println("工序加工时间："+ec.getTime());
				System.out.println("工序的加工设备："+ec.getDevice());
			}
		}
		
		//根据工序的信息编码成为数组JM[][],T[][]
	    for(int j=0;j<list_sum.size();j++){  	
	    	List<CraftExtend> l =  list_sum.get(j);
	    	Collections.sort(l);
	    	for(int i=0;i<l.size();i++){
	    		JM[j][i] = bdmap.get(l.get(i).getDevice());	
	    		T[j][i] = l.get(i).getTime();
	    	}
	    }
		
		  //JM[][]输出
	    System.out.println("JM:");
	    for(int i=0;i<JM.length;i++){
	    	System.out.println();
	    	for(int j=0;j<JM[i].length;j++){
	    		System.out.print("     "+JM[i][j]);
	    	}
	    }
	    System.out.println();
	  //T[][]输出
	    System.out.println("T:");
	    for(int i=0;i<T.length;i++){
	    	System.out.println();
	    	for(int j=0;j<T[i].length;j++){
	    		System.out.print("     "+T[i][j]);
	    	}
	    }
	    
	    //================用于存储工件间的关联关系如前后制约=====================
		List<DoubleLinkedList> dlllist = new ArrayList<DoubleLinkedList>();
		
		for(List<CraftExtend> lce:list_sum){
			for(CraftExtend e:lce){
				e.setBegintime(0);
				e.setEndtime(0);
			}
		}

	    PopulationTest pp = new PopulationTest(10,JM,T,listbd,bdmap,list_sum,dlllist);
	    int in = 1;
		while(!pp.isEvolutionDone()){
		pp.evolve();
		in++;
		}
		
		System.out.println("最优染色体序列"+pp.bestIndividual.toString());
		System.out.println("最优染色体"+pp.bestIndividual.calFitness());
 		//最优染色体解码后的集合
		List<Device> listcjend = pp.bestIndividual.decodeGene();
		System.out.println("解码成功"+listcjend.size());
		for(Device bd:listcjend){
			List<CraftExtend> listec = bd.getListgx();
			for(CraftExtend ec:listec){
				System.out.println("占用设备ID："+ec.getDevice()+"工序名称："+ec.getName()+"~~~~开始时间："+ec.getBegintime()+"~~~~结束时间："+ec.getEndtime());
			}
		}
        return "success";
       
   }
}