package cn.hselfweb.mes.arithmetic;

import cn.hselfweb.mes.enity.CraftExtend;

import java.util.Collections;
import java.util.List;

public class FirstArithmeticObjectByExtendCraft {


    public void run(List<List<CraftExtend>> list_sum, List<List<CraftExtend>> listM_base_sum, int deviceNumber) {
        SortCheck sortCheck = new SortCheck();
        for (int j = 0; j < list_sum.size(); j++) {

            List<CraftExtend> l = list_sum.get(j);
            for (int i = 0; i < l.size(); i++) {
                CraftExtend craftExtend = l.get(i);
                /**
                 *
                 *
                 * 初始排序有问题      修改初始排序过程。
                 *
                 *
                 *
                 * */
                if (craftExtend.getDevice() != null) {
                    for (int d = 1; d <= deviceNumber; d++) {
                        if (craftExtend.getDevice().equals("SB" + d)) {
                            if (craftExtend.getGid() == 1) {
                                if (listM_base_sum.get(d - 1).isEmpty()) {
                                    sortCheck.FirstDevice_FirstCraft(craftExtend, listM_base_sum, d);

                                } else {

                                    sortCheck.UnFirstDevice_FirstCraft(craftExtend, listM_base_sum, d);
                                }
                            } else {
                                if (listM_base_sum.get(d - 1).isEmpty()) {

                                    sortCheck.FirstDevice_UnFirstCraft(craftExtend, listM_base_sum, d);

                                } else {
                                    System.out.println("设备最后一道工序结束时间" + listM_base_sum.get(d - 1).get(listM_base_sum.get(d - 1).size() - 1).getEndtime()
                                            + "前一道工序结束时间" + craftExtend.getExtendCraftByPreEcId().getEndtime()
                                    );
                                    if (listM_base_sum.get(d - 1).get(listM_base_sum.get(d - 1).size() - 1).getEndtime() >= craftExtend.getExtendCraftByPreEcId().getEndtime()) {
                                        System.out.println("前一道工序完成");
                                        sortCheck.UnFirstDevice_UnFirstCraft_Finish(craftExtend, listM_base_sum, d);
                                    } else {
                                        System.out.println("前一道工序未完成");
                                        sortCheck.UnFirstDevice_UnFirstCraft_UnFinish(craftExtend, listM_base_sum, d);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }


        //aa.listM_base_sum 按设备加工顺序存储工序信息。sb1上的1、2.。。。；sb2上的1、2.。。。。

/*
//   	 message = message + "初始算法运行后的结果"+"<br>"+"\n";
        System.out.println("Now初始算法运行后的结果" + "<br>" + "\n");
        // 输出设置
        for (int d = 1; d <= deviceNumber; d++) {
            if (!listM_base_sum.get(d - 1).isEmpty()) {
                System.out.println("SB" + d + ":");
//				message = message +"SB" + d + ":"+"<br>"+"\n";
                for (CraftExtend u : listM_base_sum.get(d - 1))
                    System.out.println("工件名：" + u.getName() + "设备号"
                            + u.getDevice() + "~~~~~机器上顺序~~~~~~" + u.getDid() + "所需要花费时间" + u.getTime()
                            + "工序起始时间" + u.getBegintime() + "工序结束时间"
                            + u.getEndtime());
            }
        }
*/
        //获取所有的空闲时间
        ReSortCheck rsc = new ReSortCheck();
        List<FreeTimeByExtendCraft> lists_freetime = rsc.GetFreeTimes(listM_base_sum);
        int begintime_max = 0;
        int endtime_max = 0;
        do {
            begintime_max = endtime_max;
				 /* for (List<GongXu> l : listM_base_sum) {//获取当前加工序列中的最大加工时间
				 		    	if(l.get(l.size()-1).getEndtime()>begintime_max){
				 		    		begintime_max = l.get(l.size()-1).getEndtime();
				 		    	}
				}*/
            System.out.println("begintime_max" + begintime_max);
            System.out.println("***************调节算法执行**************");
            System.out.println("间隙");
            Collections.sort(lists_freetime);
            for (FreeTimeByExtendCraft f : lists_freetime) {
                System.out.println("设备号:" + f.getDevice() + "开始时间："
                        + f.getBegintime() + "结束时间：" + f.getEndtime() + "占用时间"
                        + f.getTime() + "前一道工序信息" + f.getPregongxu().getDevice()
                        + f.getPregongxu().getName() + "后一道工序信息"
                        + f.getPostgongxu().getDevice()
                        + f.getPostgongxu().getName());

                // 调节算法入口

                List<CraftExtend> listM = f.getListdevice();
                if ((f.getPostgongxu().getDid() + 1) <= listM.size()) {
                    CraftExtend pregongxu = listM.get(f.getPostgongxu().getDid() - 1);
                    CraftExtend postgongxu = listM.get(f.getPostgongxu().getDid());
                    if (f.getBegintime() > postgongxu.getExtendCraftByPreEcId().getEndtime()) {
                        rsc.ChangeStatus1(f, pregongxu, postgongxu, listM);
                    } else if ((postgongxu.getExtendCraftByPreEcId().getEndtime()
                            - f.getBegintime() < f.getTime())
                            && (postgongxu.getExtendCraftByPreEcId().getEndtime()
                            - f.getBegintime() > 0)) {
                        rsc.ChangeStatus2(f, pregongxu, postgongxu, listM);
                    } else {
                        rsc.ChangeStatus3(f, pregongxu, postgongxu, listM);
                    }

                }
            }
            for (List<CraftExtend> l : listM_base_sum) {
                if (l.get(l.size() - 1).getEndtime() > endtime_max) {
                    endtime_max = l.get(l.size() - 1).getEndtime();
                }
            }
            //  System.out.println("endtime_max" + endtime_max);

        } while (begintime_max != endtime_max);

/*
        // 输出设置
        System.out.println("调节算法运行后的结果");
        for (int d = 1; d <= deviceNumber; d++) {
            if (!listM_base_sum.get(d - 1).isEmpty()) {
                System.out.println("SB" + d + ":");
                for (CraftExtend u : listM_base_sum.get(d - 1))
                    System.out.println("工件名：" + u.getName() + "设备号"
                            + u.getDevice() + "~~~~~机器上顺序~~~~~~" + u.getDid() + "所需要花费时间" + u.getTime()
                            + "工序起始时间" + u.getBegintime() + "工序结束时间"
                            + u.getEndtime());
            }
        }
  */
    }
}
