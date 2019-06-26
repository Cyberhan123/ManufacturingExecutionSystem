package cn.hselfweb.mes.arithmetic.GA;

import cn.hselfweb.mes.enity.*;
import cn.hselfweb.mes.interfaces.*;
import cn.hselfweb.mes.arithmetic.GA.linkedlist.DoubleLinkedList;
import cn.hselfweb.mes.arithmetic.pojo.CraftTime;
import cn.hselfweb.mes.arithmetic.pojo.DeviceTime;
import com.mes.arithmetic.GA.linkedlist.DoubleLinkedList;
import com.mes.service.CraftExtendService;
import com.mes.service.MRPExtendService;
import com.mes.service.ProcedureTypeService;
import org.apache.struts2.ServletActionContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

public class ArithmeticActionTestGA{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1660626512992304833L;
	private HttpServletRequest request;
	private HttpSession session;
	private ServletContext application;
	
	private MRPBaseService mrpbService;
	private MRPExtendService mrpeService;
	private CraftBaseService cbService;
	private CraftExtendService ceService;
	private ProcedureService pService;
	private ProcedureTypeService ptService;
	private Workpiece w;
	private WorkpieceService wService;
	private DeviceService dService;
	public MRPBaseService getMrpbService() {
		return mrpbService;
	}
	public void setMrpbService(MRPBaseService mrpbService) {
		this.mrpbService = mrpbService;
	}
	public MRPExtendService getMrpeService() {
		return mrpeService;
	}
	public void setMrpeService(MRPExtendService mrpeService) {
		this.mrpeService = mrpeService;
	}
	public CraftBaseService getCbService() {
		return cbService;
	}
	public void setCbService(CraftBaseService cbService) {
		this.cbService = cbService;
	}
	public CraftExtendService getCeService() {
		return ceService;
	}
	public void setCeService(CraftExtendService ceService) {
		this.ceService = ceService;
	}
	public ProcedureService getpService() {
		return pService;
	}
	public void setpService(ProcedureService pService) {
		this.pService = pService;
	}
	public ProcedureTypeService getPtService() {
		return ptService;
	}
	public void setPtService(ProcedureTypeService ptService) {
		this.ptService = ptService;
	}
	public Workpiece getW() {
		return w;
	}
	public void setW(Workpiece w) {
		this.w = w;
	}
	public WorkpieceService getwService() {
		return wService;
	}
	public void setwService(WorkpieceService wService) {
		this.wService = wService;
	}
	public DeviceService getdService() {
		return dService;
	}
	public void setdService(DeviceService dService) {
		this.dService = dService;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String execute() throws Exception {

		request = ServletActionContext.getRequest();
		session = request.getSession();
		application = session.getServletContext();

		int JM[][] = new int[8][8];
		int T[][] = new int[8][8];
		
		List<CraftBase> listbc = new ArrayList<CraftBase>();
		List<Device> listbd = new ArrayList<Device>();
		List<ProcessDispatchResult> listpdr = new ArrayList<ProcessDispatchResult>();
		
		
		//对每个mrp进行排序处理
		String[] bmrpid = (String[]) session.getAttribute("taskmrp");
		
		//HttpServletRequest request = ServletActionContext.getRequest();
		//String langtype[]=request.getParameterValues("mrpbid");

		//Integer bmrpid = (Integer) application.getAttribute("bmrpid");
		MRPBase bmrp = mrpbService.findMRPBaseById(Integer.parseInt(bmrpid[0]));
        	
        System.out.println("mrp单编号"+bmrp.getId());
        
//		List<List<CraftExtend>> listM_base_sum = new ArrayList<List<CraftExtend>>();// 生成设备加工序列排序集合的集合
		//根据bmrp获取emrp
        
        String[] MRPExtends1= {"mrpb_id"};
		String[] MRPExtends2= {String.valueOf(bmrp.getId())};
		List<MRPExtend> emrps = mrpeService.findMRPExtendByPropertyss("MRPExtend", MRPExtends1, MRPExtends2);
		
		//====================为每道工序赋值找到所有设备================
		Iterator<MRPExtend> itmrp = emrps.iterator();	
		while(itmrp.hasNext()){
			System.out.print("在循环0中");
			MRPExtend em = (MRPExtend)itmrp.next();
			w = wService.findWorkpieceById(em.getWid());
			
			//根据工件获取工艺
			CraftBase bc = cbService.findCraftBaseByProperty("CraftBase","w_id",String.valueOf(w.getId()));
			
			listbc.add(bc);
			//根据基本工艺获取详细工艺（工序）
			String[] CraftExtends1= {"cb_id"};
			String[] CraftExtends2= {String.valueOf(bc.getId())};
			List<CraftExtend> ecs = ceService.findCraftExtendByPropertys("CraftExtend", CraftExtends1, CraftExtends2);
			
			Iterator<CraftExtend> it = ecs.iterator();	
			while(it.hasNext()){
				boolean b = true;
				CraftExtend ec = (CraftExtend)it.next();
				ec.setCbId(bmrp.getId());
				ec.setGname(w.getName());
				ec.setWpname(w.getName());
				ec.setWpid(w.getId());
				//System.out.println("工件名："+ec.getGname());
				//根据CraftExtend生成所需要的设备加工序列
				System.out.print("在循环1中");
				//根据详细工艺获取设备
				Device bd = dService.findDeviceById(ptService.findProcedureTypeById(pService.findProcedureById(ec.getPid()).getPtId()).getDid());
				
				if(listbd.size()==0){
					
				}else{
					for(Device bdd:listbd){
						if(bdd.getId()==bd.getId()){
							b = false;
						}
					}
				}
			    if(b){
			    	listbd.add(bd);//存储所有用到的设备
			    }
			}
		}
		
		
		
		
		
//		int DeviceNumber = listbd.size();
////		System.out.println("adasdasdasssssssss"+DeviceNumber);
//		
//		for (int d = 1; d <= DeviceNumber; d++) {
//		List<CraftExtend> listM_base = new ArrayList<CraftExtend>();// 生成设备加工序列排序集合
//		listM_base_sum.add(listM_base);
//	}

		//车间设备信息初始化(从数据库中获取相关车间信息)
		
		//=======================为获取的设备进行编号===========================
		Map<String,Integer> bdmap = new HashMap<String,Integer>();
		int bdnum = 1;
		for(Device bd:listbd){	
			System.out.println("设备ID："+bd.getId()+" 设备名："+bd.getName()+"设备号："+bdnum);
			bdmap.put(bd.getName(),bdnum);
			bdnum++;
		}
		
		List<List<CraftExtend>> list_sum = new ArrayList<List<CraftExtend>>();//用于存储工艺和和工序的关系
		for(int ii=0;ii<listbc.size();ii++){//现在仅考虑每个工件都拥有相同数量的加工序列
			List<CraftExtend> list = new ArrayList<CraftExtend>();// 特定工序包含的加工序列集合（纵向）
			list_sum.add(list);
		}
		
		
		//=======================用于存储LIST_SUM的每个基本工艺的详细工序=========================
		int pidd = 1;
		for(CraftBase bc : listbc){
			//用于list_sum的顺序输入
			//根据基本工艺获取详细工艺（工序）
			System.out.println("在循环2中");
			String[] CraftExtends1= {"cb_id"};
			String[] CraftExtends2= {String.valueOf(bc.getId())};
			List<CraftExtend> ecs = ceService.findCraftExtendByPropertys("CraftExtend", CraftExtends1, CraftExtends2);
		
			//对具体的每道工序进行遍历
			Iterator<CraftExtend> it = ecs.iterator();
			CraftExtend ec = (CraftExtend)it.next();
			
			//ec.getCraftExtendByPreEcId()替换获取前道工艺
		    if(ec.getPbId()!=0){//ceService.findCraftExtendByProperty("CraftExtend", "p_id", String.valueOf(pService.findProcedureById(ec.getPbId()).getId())
		    	do{
			    	ec = ceService.findCraftExtendByProperty("CraftExtend", "p_id", String.valueOf(ec.getPbId()));
		    	}while(ec.getPbId()!=0);
		    	//获取第一个工序
		    	int i = 1;
		    	ec.setGid(i);
		    	ec.setWpname(wService.findWorkpieceById(cbService.findCraftBaseById(ec.getCbId()).getWid()).getName());//ec.getWpname()
		    	ec.setWpid(pidd);
		    	//根据ec扩展工艺查找工序
		    	//pts.findProcedureTypeById(ps.findProcedureByProperty("Procedure","p_id",String.valueOf(ec.getPid())).getPtId())
		    	ec.setName(ptService.findProcedureTypeById(pService.findProcedureById(ec.getPid()).getPtId()).getName());
		    	ec.setTime(ptService.findProcedureTypeById(pService.findProcedureById(ec.getPid()).getPtId()).getAvgTime());
		        ec.setDevice(dService.findDeviceById(ptService.findProcedureTypeById(pService.findProcedureById(ec.getPid()).getPtId()).getDid()).getName());
		       
		        list_sum.get(ec.getWpid()-1).add(ec);
		        
		        //替换ces.findCraftExtendByProperty("CraftExtend", "p_id", String.valueOf(ps.findProcedureById(ec.getPaId()).getId()))
		        if(ec.getPaId()!=0){
		        	do{
		        		int padd = ec.getPid();
		        		ec = ceService.findCraftExtendByProperty("CraftExtend", "p_id", String.valueOf(ec.getPaId()));
			        	ec.setGid(++i);
			        	ec.setWpname(wService.findWorkpieceById(cbService.findCraftBaseById(ec.getCbId()).getWid()).getName());
			        	ec.setWpid(pidd);
			        	ec.setName(ptService.findProcedureTypeById(pService.findProcedureById(ec.getPid()).getPtId()).getName());
				    	ec.setTime(ptService.findProcedureTypeById(pService.findProcedureById(ec.getPid()).getPtId()).getAvgTime());
				        ec.setDevice(dService.findDeviceById(ptService.findProcedureTypeById(pService.findProcedureById(ec.getPid()).getPtId()).getDid()).getName());
				       
				        list_sum.get(ec.getWpid()-1).add(ec);
				        System.out.println("在循环3中");
			    	}while(ec.getPaId()!=0);
		        	
		        }
		    }else{
		    	//获取第一个工序
		    	int i = 1;
		    	ec.setGid(i);
		    	ec.setWpname(wService.findWorkpieceById(cbService.findCraftBaseById(ec.getCbId()).getWid()).getName());
	        	ec.setWpid(pidd);
	        	ec.setName(ptService.findProcedureTypeById(pService.findProcedureById(ec.getPid()).getPtId()).getName());
		    	ec.setTime(ptService.findProcedureTypeById(pService.findProcedureById(ec.getPid()).getPtId()).getAvgTime());
		        ec.setDevice(dService.findDeviceById(ptService.findProcedureTypeById(pService.findProcedureById(ec.getPid()).getPtId()).getDid()).getName());
		        
		        list_sum.get(ec.getWpid()-1).add(ec);
		        
		        if(ec.getPaId()!=0){
		        	do{
		        		int padd = ec.getPid();
		        		
		           		ec = ceService.findCraftExtendByProperty("CraftExtend", "p_id", String.valueOf(ec.getPaId()));
			        	ec.setGid(++i);
			        	ec.setWpname(wService.findWorkpieceById(cbService.findCraftBaseById(ec.getCbId()).getWid()).getName());
			        	ec.setWpid(pidd);
			        	ec.setName(ptService.findProcedureTypeById(pService.findProcedureById(ec.getPid()).getPtId()).getName());
				    	ec.setTime(ptService.findProcedureTypeById(pService.findProcedureById(ec.getPid()).getPtId()).getAvgTime());
				        ec.setDevice(dService.findDeviceById(ptService.findProcedureTypeById(pService.findProcedureById(ec.getPid()).getPtId()).getDid()).getName());

				        list_sum.get(ec.getWpid()-1).add(ec);
				        System.out.println("在循环4中");
			    	}while(ec.getPaId()!=0);
		        	
		        }
		    }
		    pidd++;
			}
        //输出list_sum
		for(int i = 0;i<list_sum.size();i++){
			System.out.println("*******工件名"+list_sum.get(i).get(0).getWpname()+":");
			for(CraftExtend ec:list_sum.get(i)){
				System.out.println("工序编号："+ec.getGid());
				System.out.println("工件名称："+ec.getWpname());
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
		CraftExtend ecC1 = new CraftExtend();
		CraftExtend ecB1 = new CraftExtend();
		/*ecC1.setWpid(3);
		ecC1.setGid(1);
		ecB1.setWpid(2);
		ecB1.setGid(1);*/
		DoubleLinkedList dll = new DoubleLinkedList();
//		Node node = new Node(ecB1);
//		dll.setHead(node);
//		dll.addFirst(ecC1);
//		dlllist.add(dll);
        
		
		for(List<CraftExtend> lce:list_sum){
			for(CraftExtend e:lce){
				e.setBegintime(0);
				e.setEndtime(0);
			}
		}

	    PopulationTest pp = new PopulationTest(10,JM,T,listbd,bdmap,list_sum,dlllist);
//		pp.toString();
	    int in = 1;
		while(!pp.isEvolutionDone()){
//		pp.evaluate();
			System.out.print("在循环5中");
	    System.out.println("*****************************&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&第"+in+"代遗传迭代");
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
				System.out.println("占用设备ID："+ptService.findProcedureTypeById(pService.findProcedureByProperty("Procedure","p_id",String.valueOf(ec.getPid())).getPtId()).getDid()+"工序名称："+ptService.findProcedureTypeById(pService.findProcedureByProperty("Procedure","p_id",String.valueOf(ec.getPid())).getPtId()).getName()+"~~~~开始时间："+ec.getBegintime()+"~~~~结束时间："+ec.getEndtime());
			}
		}
		application.setAttribute("listcjend", listcjend);
		
				for(int d = 0; d < listcjend.size(); d++){
					Device bd = listcjend.get(d);
					List<CraftExtend> listec = bd.getListgx();
				    for (CraftExtend ec : listec){
//                    System.out.println("wpname"+ec.getWpname());
//                    System.out.println(ec.getGname());
					
					ProcessDispatchResult pdr = new ProcessDispatchResult();
					pdr.setMrpBase(bmrp);
					w = wService.findWorkpieceById(ec.getWpid());
					pdr.setWorkpiece(w);
					pdr.setExtendCraft(ec);
					pdr.setPdrPreEc(ceService.findCraftExtendByProperty("CraftExtend", "p_id", String.valueOf(ec.getPbId())));
					pdr.setPdrAftEc(ceService.findCraftExtendByProperty("CraftExtend", "p_id", String.valueOf(ec.getPaId())));
					pdr.setPdrPreEcTime(ec.getBegintime());
					pdr.setPdrAftEcTime(ec.getEndtime());
					pdr.setBaseDevice(dService.findDeviceById(ptService.findProcedureTypeById(pService.findProcedureById(ec.getPid()).getPtId()).getDid()));
					//这里可以添加其他信息
					listpdr.add(pdr);

				}
		   } 

//        //获取每个设备的加工时间最大值
		 List<DeviceTime> listdevicetime = new ArrayList<DeviceTime>();
		 
		 String name = "";
		 for(int d = 1; d <= listcjend.size(); d++){
			    int max = 0;
			    Device bd = listcjend.get(d-1);
			    for(CraftExtend ec:bd.getListgx()){
			    	if(ec.getEndtime()>max){
	                	 max = ec.getEndtime();  
	                	 name = ec.getDevice();
	                   } else{  
	                   }
			    }
				 DeviceTime dt = new DeviceTime();
				 dt.setDeviceName(name);
				 dt.setDeviceTime(max);
				 listdevicetime.add(dt);
////				 System.out.println(name);
////				 System.out.println(max);
		 } 
		 
//		//获取每个工件的加工时间最大值
		 List<CraftTime> listcrafttime = new ArrayList<CraftTime>();
//		 int max1 = 0;
//		 String name1 = "";
		 for(int d = 1; d <= listcjend.size(); d++){
			 Device bd = listcjend.get(d-1);
				for (CraftExtend ec:bd.getListgx()){
                  if(listcrafttime.size()==0){
                	  
                	  //获取最后一道工序
                	 if(ec.getPaId()!=0){
      		        	do{
      		        		if(ec.getPaId()%4!=0){
      		        			ec = list_sum.get(ec.getWpid()-1).get((ec.getPaId()%4)-1);//ceService.findCraftExtendByProperty("CraftExtend", "p_id", String.valueOf(ec.getPaId()));
      		        		}else {
      		        			ec = list_sum.get(ec.getWpid()-1).get(3);
      		        		}
      			    	}while(ec.getPaId()!=0);  
      		        	CraftTime ct = new CraftTime();   		      	
      					ct.setCraftName(ec.getWpname());
      					ct.setCraftTime(ec.getEndtime());
      					listcrafttime.add(ct);
      		        }else{
      		        	CraftTime ct = new CraftTime();   		      	
      					ct.setCraftName(ec.getWpname());
      					ct.setCraftTime(ec.getEndtime());
      					listcrafttime.add(ct);
      		        }
                	  
                  }else{
                	  boolean u = true;
                	  for(CraftTime ctt : listcrafttime){
                		 if(ctt.getCraftName().equals(ec.getWpname())){
                			 u = false;
                		 }else{
                			
                		 }
                	  }
                	  if(u){
                		  if(ec.getPaId()!=0){
            		        	do{
            		        		if(ec.getPaId()%4!=0){
              		        			ec = list_sum.get(ec.getWpid()-1).get((ec.getPaId()%4)-1);//ceService.findCraftExtendByProperty("CraftExtend", "p_id", String.valueOf(ec.getPaId()));
              		        		}else {
              		        			ec = list_sum.get(ec.getWpid()-1).get(3);
              		        		}
            			    	}while(ec.getPaId()!=0);  
            		        	CraftTime ct = new CraftTime();
            					ct.setCraftName(ec.getWpname());
            					ct.setCraftTime(ec.getEndtime());
            					listcrafttime.add(ct);
            		        }else{
            		        	CraftTime ct = new CraftTime();   		      	
            					ct.setCraftName(ec.getWpname());
            					ct.setCraftTime(ec.getEndtime());
            					listcrafttime.add(ct);
            		        }
                	  }                	  
                  }
				}
			}
//		application.setAttribute("listdevicetimetest", listdevicetime);
//		application.setAttribute("listcrafttimetest", listcrafttime);
//        application.setAttribute("listpdrtest", listpdr);
//        gantt_out.ganttshouw(listcjend,listdevicetime);
        return "success";
       
   }
}