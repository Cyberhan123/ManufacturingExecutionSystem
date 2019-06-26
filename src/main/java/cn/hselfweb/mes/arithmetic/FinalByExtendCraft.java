package cn.hselfweb.mes.arithmetic;

//import java.util.ArrayList;
//import java.util.Collections;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.mes.arithmetic.pojo.FreeTimeByExtendCraft;
import com.mes.entity.CraftExtend;

public class FinalByExtendCraft {

    public List<List<CraftExtend>> sortByExtendCraft(List<List<CraftExtend>> list_sum,int DeviceNumber,List<List<CraftExtend>> listM_base_sum){
		String message = "";
   	 for (List<CraftExtend> l : list_sum) {
			for (int i = 0; i < l.size(); i++) {
				CraftExtend gongxu0 = l.get(i);
				if (gongxu0.getDevice() != null) {// 设备号为空时不参与排序
					for (int d = 1; d <= DeviceNumber; d++) {// 根据设备数量进行的循环
						if (gongxu0.getDevice().equals("M" + d)) {
							if (gongxu0.getGid() == 1) {// 如果是某工件的第一道工序
								if (listM_base_sum.get(d - 1).isEmpty()) {// 判断是否为该设备的第一道工序
									gongxu0.setEndtime(gongxu0.getTime());
									gongxu0.setBegintime(0);
									gongxu0.setDid(1);// 位于该设备的第一道工序
									listM_base_sum.get(d - 1).add(gongxu0);
									gongxu0.setList(listM_base_sum.get(d - 1));// 关联到设备序列
									System.out.println("M" + d + "添加第一道工序成功");
								} else {
									listM_base_sum.get(d - 1).add(gongxu0);
									gongxu0.setList(listM_base_sum.get(d - 1));// 关联到设备序列
									gongxu0.setDid(listM_base_sum.get(d - 1)
											.size());
									gongxu0.setEndtime(listM_base_sum
											.get(d - 1)
											.get(gongxu0.getDid() - 2)
											.getEndtime()
											+ gongxu0.getTime());// 该设备上的前一道工序的结束时间+本道工序需要的时间
									gongxu0.setBegintime(listM_base_sum
											.get(d - 1)
											.get(gongxu0.getDid() - 2)
											.getEndtime());
									System.out.println("M" + d + "添加成功");
								}
							} else {// 不是工件的第一道工序则需要判断工件的前一道工序是否已经完成
								if (listM_base_sum.get(d - 1).isEmpty()) {// 判断是否为该设备的第一道工序
									gongxu0.setDid(1);// 位于该设备的第一道工序
									listM_base_sum.get(d - 1).add(gongxu0);// 若为该设备的第一道工序，则必须在前一道工序完成的基础上加工
									gongxu0.setList(listM_base_sum.get(d - 1));// 关联到设备序列
									gongxu0.setEndtime(gongxu0.getExtendCraftByPreEcId()  
											.getEndtime() + gongxu0.getTime());// 该道工序的前一道关联工序的结束时间+本道工序需要的时间
									gongxu0.setBegintime(gongxu0.getExtendCraftByPreEcId()
											.getEndtime());
									System.out.println("M" + d
											+ "工序未成功，为该设备的第一道工序");

								} else {// 不是该设备的第一道工序
									if (listM_base_sum
											.get(d - 1)
											.get(listM_base_sum.get(d - 1)
													.size() - 1).getEndtime() >= gongxu0
											.getExtendCraftByPreEcId().getEndtime()) {// 工件的前一道工序已经完成
										listM_base_sum.get(d - 1).add(gongxu0);
										gongxu0.setList(listM_base_sum
												.get(d - 1));// 关联到设备序列
										gongxu0.setDid(listM_base_sum
												.get(d - 1).size());
										gongxu0.setEndtime(listM_base_sum
												.get(d - 1)
												.get(gongxu0.getDid() - 2)
												.getEndtime()
												+ gongxu0.getTime());// 该设备上的前一道工序的结束时间+本道工序需要的时间
										gongxu0.setBegintime(listM_base_sum
												.get(d - 1)
												.get(gongxu0.getDid() - 2)
												.getEndtime());
										System.out.println("M" + d + "工序成功");
									} else {// 工件的前一道工序未完成
										listM_base_sum.get(d - 1).add(gongxu0);
										gongxu0.setList(listM_base_sum
												.get(d - 1));// 关联到设备序列
										gongxu0.setDid(listM_base_sum
												.get(d - 1).size());
										gongxu0.setEndtime(gongxu0
												.getExtendCraftByPreEcId().getEndtime()
												+ gongxu0.getTime());// 该道工序的前一道关联工序的结束时间+本道工序需要的时间
										gongxu0.setBegintime(gongxu0
												.getExtendCraftByPreEcId().getEndtime());
										System.out.println("M" + d + "工序未成功");
									}
								}
							}
						}
					}
				}
			}
		}
   	 message = message + "算法运行后的结果"+"<br>";
		// 输出设置
		for (int d = 1; d <= DeviceNumber; d++) {
			if (!listM_base_sum.get(d - 1).isEmpty()) {
//				System.out.println("M" + d + ":");
				message = message +"M" + d + ":"+"<br>";
				for (CraftExtend u : listM_base_sum.get(d - 1))
//					System.out.println("工件名：" + u.getName() + "设备号"
//							+ u.getDevice() + "所需要花费时间" + u.getTime()
//							+ "工序起始时间" + u.getBegintime() + "工序结束时间"
//							+ u.getEndtime());
					message = message + "工件名：" + u.getName() + "设备号"
	 							+ u.getDevice() + "所需要花费时间" + u.getTime()
	 							+ "工序起始时间" + u.getBegintime() + "工序结束时间"
	 							+ u.getEndtime()+"<br>";
			}
		}
		
		
		List<FreeTimeByExtendCraft> lists_freetime = new ArrayList<FreeTimeByExtendCraft>();
				 		for (List<CraftExtend> l : listM_base_sum) {// 遍历所有的工序，找到所有的空闲时间
				 			for (CraftExtend g : l) {
				 				if (g.getDid() != l.size()) {
				 					if ((g.getEndtime() != l.get(g.getDid()).getBegintime())) {// 下一道工序的开始的时间不等于本道工序的结束时间
				 						FreeTimeByExtendCraft ft = new FreeTimeByExtendCraft();
				 						ft.setDevice(g.getDevice());
				 						ft.setListdevice(l);
				 						ft.setDid(l.get(g.getDid()).getDid());
				 						ft.setBegintime(g.getEndtime());
				 						ft.setEndtime(l.get(g.getDid()).getBegintime());
				 						ft.setTime(ft.getEndtime() - ft.getBegintime());
				 						ft.setPregongxu(g);// 同一设备上的前一道工序
				 						ft.setPostgongxu(l.get(g.getDid()));// 同一设备上的后一道工序
				 						lists_freetime.add(ft);
				 					}
				 				}
				 			}
				 		}
				 		
				 	  int begintime_max = 0;//优化前时间
				 	  int endtime_max = 0;//优化后时间
				       do{
				     	    begintime_max = endtime_max;
				 		   /* for (List<GongXu> l : listM_base_sum) {//获取当前加工序列中的最大加工时间
				 		    	if(l.get(l.size()-1).getEndtime()>begintime_max){
				 		    		begintime_max = l.get(l.size()-1).getEndtime();    		
				 		    	}	
				 		    }*/
				 		    System.out.println("begintime_max"+begintime_max);
				 			System.out.println("***************调节算法执行**************");
				 		System.out.println("间隙");
				 		Collections.sort(lists_freetime);
				 		for (FreeTimeByExtendCraft f : lists_freetime) {
				 			System.out.println("设备号:" + f.getDevice() + "开始时间："
				 					+ f.getBegintime() + "结束时间：" + f.getEndtime() + "占用时间"
				 					+ f.getTime() + "前一道工序信息" + f.getPregongxu().getDevice()
				 					+ f.getPregongxu().getDid() + "后一道工序信息"
				 					+ f.getPostgongxu().getDevice()
				 					+ f.getPostgongxu().getDid());

				 			// 调节算法入口

				 		    
				 			List<CraftExtend> listM = f.getListdevice();// 现将后两道工序进行调换
				 			if ((f.getPostgongxu().getDid() + 1) <= listM.size()) {// 防止pregongxu和postgongxu出现空指针异常
				 				CraftExtend pregongxu = listM.get(f.getPostgongxu().getDid() - 1);// 前一道工序
				 				CraftExtend postgongxu = listM.get(f.getPostgongxu().getDid());// 后一道工序
				 				if (f.getBegintime() > postgongxu.getExtendCraftByPreEcId().getEndtime()) {// 如果交换后的工序的开始时间>同工件的前一道工序的结束时间
				 					// 直接交换即可
				 					postgongxu.setBegintime(f.getBegintime());
				 					postgongxu.setEndtime(f.getBegintime()
				 							+ listM.get(f.getPostgongxu().getDid()).getTime());
				 					if(pregongxu.getExtendCraftByPreEcId().getEndtime()<postgongxu.getEndtime()){//这里还需要判断pregongxu交换后是否能够直接接在postgongxu后而不需要产生缝隙
				 						//不需要产生缝隙
				 						pregongxu.setBegintime(postgongxu.getEndtime());
				 						pregongxu.setEndtime(postgongxu.getEndtime()
				 								+ pregongxu.getTime());
				 					}else{
				 						//需要产生缝隙
				 						pregongxu.setBegintime(pregongxu.getExtendCraftByPreEcId().getEndtime());
				 						pregongxu.setEndtime(pregongxu.getExtendCraftByPreEcId().getEndtime()
				 								+ pregongxu.getTime());
				 					}
				 					

				 					// 交换did
				 					int did = 0;
				 					did = postgongxu.getDid();
				 					postgongxu.setDid(pregongxu.getDid());
				 					pregongxu.setDid(did);

				 					// 交换在链表中前后位置
				 					List<CraftExtend> l = null;
				 					l = pregongxu.getList();// 工序所在的设备序列
				 					l.set((did - 1), pregongxu);
				 					l.set((did - 2), postgongxu);

				 					if ((f.getPostgongxu().getDid() + 2) <= listM.size()) {//** 如果交换后，后面有两道工序以上
				 						for (int i = f.getPostgongxu().getDid() + 1; i <= listM//**pregongxu
				 								.size(); i++) {// 判断接下来是否能够往前移动

				 							if (listM.get(i - 1).getExtendCraftByPreEcId().getEndtime() < listM
				 									.get(i - 2).getEndtime()) {// 该工序所在工件的上一道工序的结束时间小于调换后的工序的结束时间
				 								// 可以直接往前移动
				 								listM.get(i - 1).setBegintime(
				 										listM.get(i - 2).getEndtime());
				 								listM.get(i - 1).setEndtime(
				 										listM.get(i - 2).getEndtime()
				 												+ listM.get(i - 1).getTime());
				 	

				 							} else if ((listM.get(i - 1).getExtendCraftByPreEcId()
				 									.getEndtime() > listM.get(i - 2)
				 									.getEndtime())
				 									&& (listM.get(i - 1).getExtendCraftByPreEcId()
				 											.getEndtime() < listM.get(i - 1)
				 											.getBegintime())) {
				 								// 该工序所在工件的上一道工序的结束时间大于调换后的工序时间的结束时间但小于本道工序的开始时间
				 								// 可以往前移但会产生新的空隙
				 								listM.get(i - 1).setBegintime(
				 										listM.get(i - 1).getExtendCraftByPreEcId()
				 												.getEndtime());// 该工序所在工件的上一道工序的结束时间
				 								listM.get(i - 1).setEndtime(
				 										listM.get(i - 1).getExtendCraftByPreEcId()
				 												.getEndtime()
				 												+ listM.get(i - 1).getTime());

				 							} else {
				 								// 不能交换保持原样
				 							}
				 						}
				 					} else {// 接下来的工序只剩下一道或者接下里没有工序
				 						if ((f.getPostgongxu().getDid() + 1) == listM.size()) {// 接下来还有一道工序
				 							if (pregongxu.getEndtime() != listM.get(
				 									f.getPostgongxu().getDid()).getBegintime()) {// 如果他与同设备前道工序之间有空隙
				 								if (listM.get(f.getPostgongxu().getDid())
				 										.getExtendCraftByPreEcId().getEndtime() < listM
				 										.get(f.getPostgongxu().getDid())
				 										.getBegintime()) {// 判断是否能往前移动
				 									if (listM.get(f.getPostgongxu().getDid())
				 											.getExtendCraftByPreEcId().getEndtime() < pregongxu
				 											.getEndtime()) {
				 										// 直接移动不产生缝隙
				 										listM.get(f.getPostgongxu().getDid())
				 												.setBegintime(
				 														pregongxu.getEndtime());
				 										listM.get(f.getPostgongxu().getDid())
				 												.setEndtime(
				 														listM.get(
				 																f.getPostgongxu()
				 																		.getDid())
				 																.getBegintime()
				 																+ listM.get(
				 																		f.getPostgongxu()
				 																				.getDid())
				 																		.getTime());

				 									} else if (listM
				 											.get(f.getPostgongxu().getDid())
				 											.getExtendCraftByPreEcId().getEndtime() < listM
				 											.get(f.getPostgongxu().getDid())
				 											.getBegintime()) {
				 										// 可移动，但移动后将会产生间隙
				 										listM.get(f.getPostgongxu().getDid())
				 												.setBegintime(
				 														listM.get(
				 																f.getPostgongxu()
				 																		.getDid())
				 																.getExtendCraftByPreEcId()
				 																.getEndtime());
				 										listM.get(f.getPostgongxu().getDid())
				 												.setEndtime(
				 														listM.get(
				 																f.getPostgongxu()
				 																		.getDid())
				 																.getBegintime()
				 																+ listM.get(
				 																		f.getPostgongxu()
				 																				.getDid())
				 																		.getTime());
				 									} else {
				 										// 不可往前移动
				 									}
				 								}
				 							}
				 						}
				 					}
				 				} else if ((postgongxu.getExtendCraftByPreEcId().getEndtime()
				 						- f.getBegintime() < f.getTime())
				 						&& (postgongxu.getExtendCraftByPreEcId().getEndtime()
				 								- f.getBegintime() > 0)) {// 同工件的前一道工序的结束时间-间隙的开始时间<原来间隙的时间
				 					// 交换产生新的空隙
				 					postgongxu.setBegintime(postgongxu.getExtendCraftByPreEcId()
				 							.getEndtime());
				 					postgongxu.setEndtime(postgongxu.getBegintime()
				 							+ postgongxu.getTime());
				 					
				 					if(pregongxu.getExtendCraftByPreEcId().getEndtime()<postgongxu.getEndtime()){//这里还需要判断pregongxu交换后是否能够直接接在postgongxu后而不需要产生缝隙
				 						//不需要产生缝隙
				 						pregongxu.setBegintime(postgongxu.getEndtime());
				 						pregongxu.setEndtime(postgongxu.getEndtime()
				 								+ pregongxu.getTime());
				 					}else{
				 						//需要产生缝隙
				 						pregongxu.setBegintime(pregongxu.getExtendCraftByPreEcId().getEndtime());
				 						pregongxu.setEndtime(pregongxu.getExtendCraftByPreEcId().getEndtime()
				 								+ pregongxu.getTime());
				 					}

				 					// 交换did
				 					int did = 0;
				 					did = postgongxu.getDid();
				 					postgongxu.setDid(pregongxu.getDid());
				 					pregongxu.setDid(did);

				 					// 交换在链表中前后位置
				 					List<CraftExtend> l = null;
				 					l = pregongxu.getList();// 工序所在的设备序列
				 					l.set((did - 1), pregongxu);
				 					l.set((did - 2), postgongxu);

				 					if ((f.getPostgongxu().getDid() + 2) <= listM.size()) {// 如果交换后，后面有两道工序以上
				 						for (int i = f.getPostgongxu().getDid() + 2; i <= listM
				 								.size(); i++) {// 判断接下来是否能够交换
				 							if (listM.get(i - 1).getExtendCraftByPreEcId().getEndtime() < listM
				 									.get(i - 2).getEndtime()) {// 该工序所在工件的上一道工序的结束时间小于调换后的工序时间
				 								// 可以直接交换
				 								listM.get(i - 1).setBegintime(
				 										listM.get(i - 2).getEndtime());
				 								listM.get(i - 1).setEndtime(
				 										listM.get(i - 2).getEndtime()
				 												+ listM.get(i - 1).getTime());
				 							
				 							} else if ((listM.get(i - 1).getExtendCraftByPreEcId()
				 									.getEndtime() > listM.get(i - 2)
				 									.getEndtime())
				 									&& (listM.get(i - 1).getExtendCraftByPreEcId()
				 											.getEndtime() < listM.get(i - 1)
				 											.getBegintime())) {
				 								// 该工序所在工件的上一道工序的结束时间大于调换后的工序时间但小于本道工序的开始时间
				 								// 可以交换但会产生新的空隙
				 								listM.get(i - 1).setBegintime(
				 										listM.get(i - 1).getExtendCraftByPreEcId()
				 												.getEndtime());// 该工序所在工件的上一道工序的结束时间
				 								listM.get(i - 1).setEndtime(
				 										listM.get(i - 1).getExtendCraftByPreEcId()
				 												.getEndtime()
				 												+ listM.get(i - 1).getTime());
				 						

				 							} else {
				 								// 不能交换保持原样
				 							}
				 						}
				 					} else {// 接下来的工序只剩下一道或者接下里没有工序
				 						if ((f.getPostgongxu().getDid() + 1) == listM.size()) {// 接下来还有一道工序
				 							if (pregongxu.getEndtime() != listM.get(
				 									f.getPostgongxu().getDid()).getBegintime()) {// 如果他与同设备前道工序之间有空隙
				 								if (listM.get(f.getPostgongxu().getDid())
				 										.getExtendCraftByPreEcId().getEndtime() < listM
				 										.get(f.getPostgongxu().getDid())
				 										.getBegintime()) {// 判断是否能往前移动
				 									if (listM.get(f.getPostgongxu().getDid())
				 											.getExtendCraftByPreEcId().getEndtime() < pregongxu
				 											.getEndtime()) {
				 										// 直接移动不产生缝隙
				 										listM.get(f.getPostgongxu().getDid())
				 												.setBegintime(
				 														pregongxu.getEndtime());
				 										listM.get(f.getPostgongxu().getDid())
				 												.setEndtime(
				 														listM.get(
				 																f.getPostgongxu()
				 																		.getDid())
				 																.getBegintime()
				 																+ listM.get(
				 																		f.getPostgongxu()
				 																				.getDid())
				 																		.getTime());

				 									} else if (listM
				 											.get(f.getPostgongxu().getDid())
				 											.getExtendCraftByPreEcId().getEndtime() < listM
				 											.get(f.getPostgongxu().getDid())
				 											.getBegintime()) {
				 										// 可移动，但移动后将会产生间隙
				 										listM.get(f.getPostgongxu().getDid())
				 												.setBegintime(
				 														listM.get(
				 																f.getPostgongxu()
				 																		.getDid())
				 																.getExtendCraftByPreEcId()
				 																.getEndtime());
				 										listM.get(f.getPostgongxu().getDid())
				 												.setEndtime(
				 														listM.get(
				 																f.getPostgongxu()
				 																		.getDid())
				 																.getBegintime()
				 																+ listM.get(
				 																		f.getPostgongxu()
				 																				.getDid())
				 																		.getTime());
				 									} else {
				 										// 不可往前移动
				 									}
				 								}
				 							}
				 						}
				 					}
				 				} else {
				 					// 无法交换
				 				}

				 			}
				 		}
				 		for (List<CraftExtend> l : listM_base_sum) {//获取当前加工序列中的最大加工时间
				 	    	if(l.get(l.size()-1).getEndtime()>endtime_max){
				 	    		endtime_max = l.get(l.size()-1).getEndtime();	
				 	    	}
				 	    }	    
				 		System.out.println("endtime_max"+endtime_max);
				 		
				 	}while(begintime_max!=endtime_max);

				 		
				 	 // 输出设置
				 		System.out.println("调节算法运行后的结果");
				 		message = message + "算法运行后的结果"+"<br>";
				 		for (int d = 1; d <= DeviceNumber; d++) {
				 			if (!listM_base_sum.get(d - 1).isEmpty()) {
//				 				System.out.println("M" + d + ":");
				 				message = message +"M" + d + ":"+"<br>";
				 				for (CraftExtend u : listM_base_sum.get(d - 1))
//				 					System.out.println("工件名：" + u.getName() + "设备号"
//				 							+ u.getDevice() + "所需要花费时间" + u.getTime()
//				 							+ "工序起始时间" + u.getBegintime() + "工序结束时间"
//				 							+ u.getEndtime());
				 						message = message + "工件名：" + u.getName() + "设备号"
	 				 							+ u.getDevice() + "所需要花费时间" + u.getTime()
	 				 							+ "工序起始时间" + u.getBegintime() + "工序结束时间"
	 				 							+ u.getEndtime()+"<br>";
				 			}
				 		}
				 		 return listM_base_sum;
	}
  
}
