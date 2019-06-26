package cn.hselfweb.mes.arithmetic.copy;

import cn.hselfweb.mes.enity.CraftExtend;
import cn.hselfweb.mes.arithmetic.pojo.ArithmeticParams;
import cn.hselfweb.mes.arithmetic.pojo.FreeTimeByExtendCraft;

import java.util.Collections;
import java.util.List;

public class FirstArithmeticObjectByExtendCraft extends Arithmetic{

	private static FirstArithmeticObjectByExtendCraft instance = null;

	private  FirstArithmeticObjectByExtendCraft() {
		super();
		// TODO Auto-generated constructor stub
	}
	public static synchronized FirstArithmeticObjectByExtendCraft getInstance() {

		if (instance == null)
			instance = new FirstArithmeticObjectByExtendCraft();
		return instance;

	}

	//释放实例的方法
	public void releaseInstance(){
		if(instance == null){

		}else{
			instance = null;
		}
	}

	public ArithmeticParams sort(ArithmeticParams aa){
//		String message = "";
		SortCheck dc = new SortCheck();
		for (int j = 0; j < aa.list_sum.size(); j++) {

			List<CraftExtend> l = aa.list_sum.get(j);
			for (int i = 0; i < l.size(); i++) {

				CraftExtend gongxu0 = l.get(i);
				if (gongxu0.getDevice() != null) {// 设备编号：为空时不参与排序
					for (int d = 1; d <= aa.DeviceNumber; d++) {// 根据设备数量进行的循环
						if (gongxu0.getDevice().equals("设备" + d)) {

							if (gongxu0.getGid() == 1) {// 如果是某工件的第一道工序
								if (aa.listM_base_sum.get(d - 1).isEmpty()) {// 判断是否为该设备的第一道工序 									

									// 位于该设备的第一道工序
									// l:list_sum.get(j); i: ;gongxu0 = l.get(i)当前工序list_sum.get(j).get(i);d:第几个设备
									//System.out.println("设备第一道工序，工件第一道工序开始");
									dc.FirstDevice_FirstCraft(j,i,gongxu0,aa.listM_base_sum,d);

								} else {
									//System.out.println("非设备第一道工序，工件第一道工序开始");
									dc.UnFirstDevice_FirstCraft(j,i,gongxu0,aa.listM_base_sum,d);
								}
							} else {// 不是工件的第一道工序则需要判断工件的前一道工序是否已经完成
								if (aa.listM_base_sum.get(d - 1).isEmpty()) {// 判断是否为该设备的第一道工序

									dc.FirstDevice_UnFirstCraft(j,i,gongxu0,aa.listM_base_sum,d);

								} else {// 不是该设备的第一道工序
									System.out.println("设备最后一道工序结束时间"+ aa.listM_base_sum.get(d - 1).get(aa.listM_base_sum.get(d - 1).size() - 1).getEndtime()
											+"前一道工序结束时间"+gongxu0.getExtendCraftByPreEcId().getEndtime()
											);
									if (aa.listM_base_sum.get(d - 1).get(aa.listM_base_sum.get(d - 1).size() - 1).getEndtime()//机器上前一道工序完成时间
											>= gongxu0.getExtendCraftByPreEcId().getEndtime()) {// 工件的前一道工序已经完成     
										// gongxu0.getExtendCraftByPreEcId().getEndtime()   工序前一道结束时间
										System.out.println("前一道工序完成");

										dc.UnFirstDevice_UnFirstCraft_Finish(j,i,gongxu0,aa.listM_base_sum,d);
									} else {// 工件的前一道工序未完成
										System.out.println("前一道工序未完成");
										dc.UnFirstDevice_UnFirstCraft_UnFinish(j,i,gongxu0,aa.listM_base_sum,d);
									}
								}
							}
						}
					}
				}						
			}			
		}


		//aa.listM_base_sum 按设备加工顺序存储工序信息。sb1上的1、2.。。。；sb2上的1、2.。。。。


		System.out.println("Now初始算法运行后的结果"+"<br>"+"\n");
		// 输出设置
		for (int d = 1; d <= aa.DeviceNumber; d++) {
			if (!aa.listM_base_sum.get(d - 1).isEmpty()) {
				System.out.println("设备" + d + ":");
				//				message = message +"设备" + d + ":"+"<br>"+"\n";
				for (CraftExtend u : aa.listM_base_sum.get(d - 1))
					System.out.println("工件名：" + u.getName() + "设备编号："
							+ u.getDevice() +"~~~~~机器上顺序~~~~~~"+u.getDid()+ "所需要花费时间" + u.getTime()
							+ "工序起始时间" + u.getBegintime() + "工序结束时间"
							+ u.getEndtime());
			}
		}

		//获取所有的空闲时间
		ReSortCheck rsc = new ReSortCheck();
						List<FreeTimeByExtendCraft> lists_freetime = rsc.GetFreeTimes(aa.listM_base_sum);

						int begintime_max = 0;//优化前时间
						int endtime_max = 0;//优化后时间
						do{
							begintime_max = endtime_max;
							/* for (List<GongXu> l : listM_base_sum) {//获取当前加工序列中的最大加工时间
				 		    	if(l.get(l.size()-1).getEndtime()>begintime_max){
				 		    		begintime_max = l.get(l.size()-1).getEndtime();    		
				 		    	}	
				}*/
							//			    System.out.println("begintime_max"+begintime_max);
							System.out.println("***************调节算法执行**************");
							System.out.println("间隙");
							Collections.sort(lists_freetime);
							for (FreeTimeByExtendCraft f : lists_freetime) {
								System.out.println("设备编号：" + f.getDevice() + "~~~开始时间："
										+ f.getBegintime() + "~~~结束时间：" + f.getEndtime() + "~~~占用时间："
										+ f.getTime() + "~~~前一道工序信息：" + f.getPregongxu().getDevice()+ "~~~"
										+ f.getPregongxu().getName() + "~~~后一道工序信息："
										+ f.getPostgongxu().getDevice()+ "~~~"
										+ f.getPostgongxu().getName());

								// 调节算法入口


								List<CraftExtend> listM = f.getListdevice();// 现将后两道工序进行调换
								if ((f.getPostgongxu().getDid() + 1) <= listM.size()) {// 防止pregongxu和postgongxu出现空指针异常
									CraftExtend pregongxu = listM.get(f.getPostgongxu().getDid() - 1);// 前一道工序
									CraftExtend postgongxu = listM.get(f.getPostgongxu().getDid());// 后一道工序
									if (f.getBegintime() > postgongxu.getExtendCraftByPreEcId().getEndtime()) {// 如果交换后的工序的开始时间>同工件的前一道工序的结束时间
										rsc.ChangeStatus1(f, pregongxu, postgongxu, listM);
									} else if ((postgongxu.getExtendCraftByPreEcId().getEndtime()
											- f.getBegintime() < f.getTime())
											&& (postgongxu.getExtendCraftByPreEcId().getEndtime()
													- f.getBegintime() > 0)) {// 同工件的前一道工序的结束时间-间隙的开始时间<原来间隙的时间
										rsc.ChangeStatus2(f, pregongxu, postgongxu, listM);
									} else {
										// 无法交换
										rsc.ChangeStatus3(f, pregongxu, postgongxu, listM);
									}

								}
							}

							for (List<CraftExtend> l : aa.listM_base_sum) {//获取当前加工序列中的最大加工时间
								if(l.get(l.size()-1).getEndtime()>endtime_max){
									endtime_max = l.get(l.size()-1).getEndtime();	
								}
							}	    
							System.out.println("endtime_max"+endtime_max);

						}while(begintime_max!=endtime_max);


						// 输出设置
						System.out.println("调节算法运行后的结果");
						for (int d = 1; d <= aa.DeviceNumber; d++) {
							if (!aa.listM_base_sum.get(d - 1).isEmpty()) {
								System.out.println("设备" + d + ":");
								for (CraftExtend u : aa.listM_base_sum.get(d - 1))
									System.out.println("工件名：" + u.getName() + "~~~设备编号："
											+ u.getDevice() +"~~~机器上顺序："+u.getDid()+ "~~~所需要花费时间" + u.getTime()
											+ "~~~工序起始时间" + u.getBegintime() + "~~~工序结束时间"
											+ u.getEndtime());
							}
						}

						return aa;	 		 
	}


}
