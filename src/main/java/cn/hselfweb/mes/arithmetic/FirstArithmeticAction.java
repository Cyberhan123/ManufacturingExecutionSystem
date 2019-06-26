package cn.hselfweb.mes.arithmetic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.mes.arithmetic.pojo.ArithmeticParams;
import com.mes.arithmetic.pojo.CraftTime;
import com.mes.arithmetic.entity.DeviceTime;
import com.mes.arithmetic.pojo.FirstArithmeticParams;
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

public class FirstArithmeticAction extends ActionSupport {
	
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
		//基本工艺
		List<CraftBase> listbc = new ArrayList<CraftBase>();
		//设备
		List<Device> listbd = new ArrayList<Device>();
		
		List<ProcessDispatchResult> listpdr = new ArrayList<ProcessDispatchResult>();
		
		
		//对每个mrp进行排序处理
		String[] bmrpid = (String[]) session.getAttribute("taskmrp");
		
		//HttpServletRequest request = ServletActionContext.getRequest();
		//String langtype[]=request.getParameterValues("mrpbid");
		System.out.println("待调度的MRP单id："+Integer.parseInt(bmrpid[0]));
		//Integer bmrpid = (Integer) application.getAttribute("bmrpid");
		MRPBase bmrp = mrpbService.findMRPBaseById(Integer.parseInt(bmrpid[0]));
        	
        System.out.println("mrp单编号"+bmrp.getId());
        
		List<List<CraftExtend>> listM_base_sum = new ArrayList<List<CraftExtend>>();// 生成    设备加工序列排序集合  的集合    (最后的结果集，甘特图展示)
		//(每个元素是一个设备上的加工工序集合 eg： 设备一：工件一的第一道工序，工件三的第二道工序，...;设备二：工件三的第一道工序，工件x的xx，...;....;....;)
		
		//根据bmrp获取emrp
        
        String[] MRPExtends1= {"mrpb_id"};
		String[] MRPExtends2= {String.valueOf(bmrp.getId())};
		List<MRPExtend> emrps = mrpeService.findMRPExtendByPropertys("MRPExtend", MRPExtends1, MRPExtends2);
		
		//====================为每道工序赋值找到所有设备================
		Iterator<MRPExtend> itmrp = emrps.iterator();	
		while(itmrp.hasNext()){
//			System.out.print("在循环0中");
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
//				System.out.print("在循环1中");
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
		
		int DeviceNumber = listbd.size();
		System.out.println("DeviceNumber:"+DeviceNumber);
		for (int d = 1; d <= DeviceNumber; d++) {
			List<CraftExtend> listM_base = new ArrayList<CraftExtend>();// 生成设备加工序列排序集合
			listM_base_sum.add(listM_base);
		}
		

		//车间设备信息初始化(从数据库中获取相关车间信息)
		
		//=======================为获取的设备进行编号===========================
/*		Map<String,Integer> bdmap = new HashMap<String,Integer>();
		int bdnum = 1;
		for(Device bd:listbd){	
			System.out.println("设备ID："+bd.getId()+" 设备名："+bd.getName()+"设备号："+bdnum);
			bdmap.put(bd.getName(),bdnum);
			bdnum++;
		}*/
		
		List<List<CraftExtend>> list_sum = new ArrayList<List<CraftExtend>>();//用于存储工艺和和工序的关系
		String[] CraftExtends1= {"cb_id"};
		String[] CraftExtends2= {String.valueOf(listbc.get(0).getId())};
		List<CraftExtend> CraftExtendsCount = ceService.findCraftExtendByPropertys("CraftExtend", CraftExtends1, CraftExtends2);
		for(int ii=0;ii<CraftExtendsCount.size();ii++){//现在仅考虑每个工件都拥有相同数量的加工序列
			List<CraftExtend> list = new ArrayList<CraftExtend>();// 特定工序包含的加工序列集合（纵向）
			list_sum.add(list);
		}
		
		
		//=======================用于存储LIST_SUM的每个基本工艺的详细工序=========================
		//  
		//  list_sum  第一道工序（）；第二道工序（）；....
		int pidd = 1;
		for(CraftBase bc : listbc){
			//用于list_sum的顺序输入
			//根据基本工艺获取详细工艺（工序）
			System.out.println("在循环中~~~~~");
			String[] CraftExtends3= {"cb_id"};
			String[] CraftExtends4= {String.valueOf(bc.getId())};
			List<CraftExtend> ecs = ceService.findCraftExtendByPropertys("CraftExtend", CraftExtends3, CraftExtends4); 
		
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
		    	ec.setWpid(pidd);    //所属工件的编号
		    	//根据ec扩展工艺查找工序 
		    	//pts.findProcedureTypeById(ps.findProcedureByProperty("Procedure","p_id",String.valueOf(ec.getPid())).getPtId())
		    	ec.setName(ptService.findProcedureTypeById(pService.findProcedureById(ec.getPid()).getPtId()).getName());
		    	ec.setTime(ptService.findProcedureTypeById(pService.findProcedureById(ec.getPid()).getPtId()).getAvgTime());
		        ec.setDevice(dService.findDeviceById(ptService.findProcedureTypeById(pService.findProcedureById(ec.getPid()).getPtId()).getDid()).getName());
		        //这样获取的是  。他的前序工艺，但是是   new的一个新实例。 所以不在数据库中存储的临时变量无法获取。
		        //ec.setExtendCraftByPreEcId(ceService.findCraftExtendByProperty("CraftExtend", "p_id", String.valueOf(ec.getPbId())));		      
		        //ec.setExtendCraftByAftEcId(ceService.findCraftExtendByProperty("CraftExtend", "p_id", String.valueOf(ec.getPaId())));
		       
		        System.out.println("在循环中~~~~~");
		        list_sum.get(i-1).add(ec);
		        
		        //替换ces.findCraftExtendByProperty("CraftExtend", "p_id", String.valueOf(ps.findProcedureById(ec.getPaId()).getId()))
		        if(ec.getPaId()!=0){
		        	//将后道工序加入list_sum
		        	do{
		        		//list_sum.get().get()前一道工序的  后续  set  ec
		        		ec = ceService.findCraftExtendByProperty("CraftExtend", "p_id", String.valueOf(ec.getPaId()));
		        		CraftExtend eclast=list_sum.get(i-1).get(pidd-1);
		        		eclast.setExtendCraftByAftEcId(ec);
		        		ec.setExtendCraftByPreEcId(eclast);
			        	ec.setGid(++i);
			        	ec.setWpname(wService.findWorkpieceById(cbService.findCraftBaseById(ec.getCbId()).getWid()).getName());
			        	ec.setWpid(pidd);
			        	ec.setName(ptService.findProcedureTypeById(pService.findProcedureById(ec.getPid()).getPtId()).getName());
				    	ec.setTime(ptService.findProcedureTypeById(pService.findProcedureById(ec.getPid()).getPtId()).getAvgTime());
				        ec.setDevice(dService.findDeviceById(ptService.findProcedureTypeById(pService.findProcedureById(ec.getPid()).getPtId()).getDid()).getName());
				        
				        //ec.setExtendCraftByPreEcId(ceService.findCraftExtendByProperty("CraftExtend", "p_id", String.valueOf(ec.getPbId())));		      
				        //ec.setExtendCraftByAftEcId(ceService.findCraftExtendByProperty("CraftExtend", "p_id", String.valueOf(ec.getPaId())));
				        System.out.println("在循环2中~~~~~");
				        
				       list_sum.get(i-1).add(ec);
//				        System.out.println("在循环3中");
			    	}while(ec.getPaId()!=0);
		        	
		        }
		    }else{
		    	//获取第一个工序
		    	int i = 1;
		    	int p = 1;
		    	int q = 2;
		    	ec.setGid(i);
		    	ec.setWpname(wService.findWorkpieceById(cbService.findCraftBaseById(ec.getCbId()).getWid()).getName());
	        	ec.setWpid(pidd);
	        	ec.setName(ptService.findProcedureTypeById(pService.findProcedureById(ec.getPid()).getPtId()).getName());
		    	ec.setTime(ptService.findProcedureTypeById(pService.findProcedureById(ec.getPid()).getPtId()).getAvgTime());
		        ec.setDevice(dService.findDeviceById(ptService.findProcedureTypeById(pService.findProcedureById(ec.getPid()).getPtId()).getDid()).getName());
		        
		        //ec.setExtendCraftByPreEcId(ceService.findCraftExtendByProperty("CraftExtend", "p_id", String.valueOf(ec.getPbId())));		      
		        //ec.setExtendCraftByAftEcId(ceService.findCraftExtendByProperty("CraftExtend", "p_id", String.valueOf(ec.getPaId())));
		        System.out.println("在循环3中~~~~~");
		        list_sum.get(i-1).add(ec);
		        
		        if(ec.getPaId()!=0){
		        	do{
		           		ec = ceService.findCraftExtendByProperty("CraftExtend", "p_id", String.valueOf(ec.getPaId()));
		           		CraftExtend eclast=list_sum.get(i-1).get(pidd-1);
		        		eclast.setExtendCraftByAftEcId(ec);
		        		ec.setExtendCraftByPreEcId(eclast);
			        	
		           		ec.setGid(++i);
			        	ec.setWpname(wService.findWorkpieceById(cbService.findCraftBaseById(ec.getCbId()).getWid()).getName());
			        	ec.setWpid(pidd);
			        	ec.setName(ptService.findProcedureTypeById(pService.findProcedureById(ec.getPid()).getPtId()).getName());
				    	ec.setTime(ptService.findProcedureTypeById(pService.findProcedureById(ec.getPid()).getPtId()).getAvgTime());
				        ec.setDevice(dService.findDeviceById(ptService.findProcedureTypeById(pService.findProcedureById(ec.getPid()).getPtId()).getDid()).getName());
				       
				        //ec.setExtendCraftByPreEcId(ceService.findCraftExtendByProperty("CraftExtend", "p_id", String.valueOf(ec.getPbId())));		      
				        //ec.setExtendCraftByAftEcId(ceService.findCraftExtendByProperty("CraftExtend", "p_id", String.valueOf(ec.getPaId())));						
				        list_sum.get(i-1).add(ec);
				        System.out.println("在循环4中");
			    	}while(ec.getPaId()!=0);		        	
		        }
		    }
		    pidd++;
			}
		
		//list_sum.get(1).get(0).setEndtime(888);    //只可查到已有    数据 
		//System.out.println("~~~~~~~~第一个工件第三道工序endTime~~~~~~~~"+list_sum.get(1).get(0).getEndtime()+"~~~~~~~~~~"
		//+list_sum.get(3).get(0).getExtendCraftByPreEcId().getExtendCraftByPreEcId().getEndtime());
      
		//输出list_sum
		/*for(int i = 0;i<list_sum.size();i++){
			System.out.println("*******工件名"+list_sum.get(i).get(0).getWpname()+":"+list_sum.size());
			for(CraftExtend ec:list_sum.get(i)){
				System.out.println("工序编号："+ec.getGid());
				System.out.println("工件名称："+ec.getWpname());
				System.out.println("工件编号："+ec.getWpid());
				System.out.println("工序加工时间："+ec.getTime());
				System.out.println("工序的加工设备："+ec.getDevice());
			}
		}
		*/
		
		//为每道工序添加效率
		//添加效率
		double xl =0.0 ;
		int time = 0;
		int totaltime = 0;
		for(int i=0;i< list_sum.size();i++){
			List<CraftExtend> list= list_sum.get(i);
			for(int j=0;j<list.size();j++){	
			//	int x=j-1;
				time = 0;
				totaltime = 0;
				CraftExtend ec=list.get(j);
				xl = 0.0;
				if(ec.getPbId()!=0){	    	
					System.out.println("ec.getPbId()!=0 "+ec.getPbId()+" ec.getTime():"+ec.getTime()+"   ec.getName:"+ec.getName());
							for (int k = 0; k <=i; k++) {
								time = time + list_sum.get(k).get(j).getTime();
						//		System.out.println("ec.getTime()--time:"+time);
							}
					
					//time = time + ec.getTime();
					System.out.println("time:"+time);
					for(int p=0;p<list_sum.size();p++){						
						totaltime = totaltime + list_sum.get(p).get(j).getTime();
				//		System.out.println("ec.getTime()--totaltime:"+totaltime);
					}
					xl = ((double)time)/((double)totaltime);
			//		System.out.println("效率:"+xl+time+totaltime);
					
					list.get(j).setXiaolv(xl);

				}else{ 						
					time = ec.getTime();
//					System.out.println("time:"+time);
					for(int p=0;p<list_sum.size();p++){					
						totaltime = totaltime + list_sum.get(p).get(j).getTime();
//					System.out.println("ec.getTime()--totaltime:"+totaltime);					
					}
									
					xl = ((double)time)/((double)totaltime);
					list.get(j).setXiaolv(xl);
				}
			}
		}
    	
       
        //显示效率计算结果
		for(List<CraftExtend> list : list_sum){
			
			Collections.sort(list);  //效率排序     			
			System.out.println("List:");
			
		}
		/*
		for(List<CraftExtend> list : list_sum){
			
			  
			System.out.println("排序结果");
			for(CraftExtend ec :list){
				
				if(ec.getExtendCraftByAftEcId()!=null&&ec.getExtendCraftByPreEcId()!=null){
					System.out.println("Device:"+ec.getDevice()+"~Name:"+ec.getName()+"~time:"+ec.getTime()+"~Gid:"+ec.getGid()+"~XiaoLv:"+ec.getXiaolv());
				}else if(ec.getExtendCraftByPreEcId()!=null){
					System.out.println("Device:"+ec.getDevice()+"~Name:"+ec.getName()+"~time:"+ec.getTime()+"~Gid:"+ec.getGid()+"~XiaoLv:"+ec.getXiaolv());
				}else{
					System.out.println("Device:"+ec.getDevice()+"~Name:"+ec.getName()+"~time:"+ec.getTime()+"~Gid:"+ec.getGid()+"~XiaoLv:"+ec.getXiaolv());
				}

			}
		}*/		
		
		
		
		//
		//
		//效率计算全部正确。list_sum 正确   
		//
		//
		
		
		
		//List<List> lists = new ArrayList<List>();// list1-list4的集合就是我们现在的list_sum
		//调度算法的接口
		//首先创建算法工厂类的对象
		ArithmeticFactory af = ArithmeticFactory.getInstance();
		Arithmetic arithmetic = af.creatArithmetic("B");
				 
		 System.out.println("运行的是效率算法的实例！");
		 
		 //算法参数对象的创建
		 ArithmeticParams aaii = FirstArithmeticParams.getInstance();
		 aaii.setDeviceNumber(DeviceNumber);
		 aaii.setList_sum(list_sum);
		 aaii.setListM_base_sum(listM_base_sum);
		 
		 //给算法中传递算法参数对象
		 ArithmeticParams resultAA = arithmetic.sort(aaii);
		 
		 for(int d = 1; d <= DeviceNumber; d++){
				for (CraftExtend ec : resultAA.getListM_base_sum().get(d - 1)){
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
        //获取每个设备的加工时间最大值
		 List<DeviceTime> listdevicetime = new ArrayList<DeviceTime>();
		 
		 String name = "";
		 for(int d = 1; d <= DeviceNumber; d++){
			    int max = 0;
				for (CraftExtend ec : resultAA.getListM_base_sum().get(d - 1)){
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
		 } 
		 
		//获取每个工件的加工时间最大值
		 List<CraftTime> listcrafttime = new ArrayList<CraftTime>();
					
		 for(int d = 0; d < DeviceNumber; d++){
			 
			 List<CraftExtend> list= resultAA.getListM_base_sum().get(d);	
				for (int j=0;j<list.size();j++){
					
					CraftExtend ec =list.get(j);
					
					if(listcrafttime.size()==0){
                	  
                	  //获取最后一道工序
                	 if(ec.getPaId()!=0){
      		        	do{      		        		
      		        		ec = ec.getExtendCraftByAftEcId();
      		        		//System.out.println("ec.getPaId()="+ec.getPaId());
      			    	}while(ec.getPaId()!=0);  
      		        	CraftTime ct = new CraftTime(); 
      		        	
      					ct.setCraftName(ec.getWpname());
      					ct.setCraftTime(ec.getEndtime());
      				//	System.out.println("ec.getWpname()="+ec.getWpname()+"~~~~~~"+"ec.getEndtime()"+ec.getEndtime());
      					listcrafttime.add(ct);
      		        }else{
      		        	CraftTime ct = new CraftTime();   		      	
      					ct.setCraftName(ec.getWpname());
      					ct.setCraftTime(ec.getEndtime());
      				//	System.out.println("ec.getWpname()="+ec.getWpname()+"~~~~~~"+"ec.getEndtime()"+ec.getEndtime());

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
            		        		ec = ec.getExtendCraftByAftEcId();           			        	
            			    	}while(ec.getPaId()!=0);  
            		        	CraftTime ct = new CraftTime();   		      	
            					ct.setCraftName(ec.getWpname());
            					ct.setCraftTime(ec.getEndtime());
              			//		System.out.println("ec.getWpname()="+ec.getWpname()+"~~~~~~"+"ec.getEndtime()"+ec.getEndtime());
            					listcrafttime.add(ct);
            		        }else{
            		        	CraftTime ct = new CraftTime();   		      	
            					ct.setCraftName(ec.getWpname());
            					ct.setCraftTime(ec.getEndtime());
              			//		System.out.println("ec.getWpname()="+ec.getWpname()+"~~~~~~"+"ec.getEndtime()"+ec.getEndtime());

            					listcrafttime.add(ct);
            		        }
                	  }
                	  
                  }
				}
				
//				System.out.println("max1"+max1);
			}
		 		 
	/*	 for(int x=0;x<resultAA.getListM_base_sum().size();x++){						
			 List<CraftExtend> ecList=resultAA.getListM_base_sum().get(x);
			 List<CraftExtend> listcjend.add(ecList);			 			 
		 }*/
		 
		application.setAttribute("listdevicetime", listdevicetime);
		application.setAttribute("listcrafttime", listcrafttime);
        application.setAttribute("listpdr", listpdr);
       /* for(int w=0;w<resultAA.getListM_base_sum().size();w++){
        	
        	for(int e=0;e<resultAA.getListM_base_sum().get(w).size();e++){
        		
        		CraftExtend now=resultAA.getListM_base_sum().get(w).get(e);
        		System.out.println("now.getDevice()"+now.getDevice()+"~~~~~~"+"now.getBegintime()"+now.getBegintime()+"~~~~~~"+"now.getEndtime()"+now.getEndtime());
        	}
        	
        }*/
        gantt_out_ef.ganttshouw(resultAA.getListM_base_sum(),listdevicetime);
        return "success";
       
   }
}