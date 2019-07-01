package cn.hselfweb.mes.arithmetic

//import java.util.ArrayList;
//import java.util.Collections;

//import java.util.Collections;

import cn.hselfweb.mes.arithmetic.pojo.ArithmeticParams
import java.util.*

class FirstArithmeticObjectByExtendCraft private constructor()// TODO Auto-generated constructor stub
    : Arithmetic() {

    //释放实例的方法
    fun releaseInstance() {
        if (instance == null) {

        } else {
            instance = null
        }
    }

    override fun sort(aa: ArithmeticParams): ArithmeticParams {
        val dc = SortCheck()
        for (j in 0 until aa.list_sum.size) {

            val l = aa.list_sum.get(j)
            for (i in l.indices) {

                val gongxu0 = l.get(i)
                /*
				 *
				 *
				 * 初始排序有问题      修改初始排序过程。
				 *
				 *
				 *
				 * */
                if (gongxu0.device != null) {// 设备号为空时不参与排序
                    for (d in 1..aa.DeviceNumber) {// 根据设备数量进行的循环
                        if (gongxu0.device == "SB$d") {
                            if (gongxu0.gid === 1) {// 如果是某工件的第一道工序
                                if (aa.listM_base_sum[d - 1].isEmpty()) {// 判断是否为该设备的第一道工序

                                    // 位于该设备的第一道工序
                                    // l:list_sum.get(j); i: ;gongxu0 = l.get(i)当前工序list_sum.get(j).get(i);d:第几个设备
                                    //System.out.println("设备第一道工序，工件第一道工序开始");
                                    dc.FirstDevice_FirstCraft(j, i, gongxu0, aa.listM_base_sum, d)

                                } else {
                                    //System.out.println("非设备第一道工序，工件第一道工序开始");
                                    dc.UnFirstDevice_FirstCraft(j, i, gongxu0, aa.listM_base_sum, d)
                                }
                            } else {// 不是工件的第一道工序则需要判断工件的前一道工序是否已经完成
                                if (aa.listM_base_sum[d - 1].isEmpty()) {// 判断是否为该设备的第一道工序

                                    dc.FirstDevice_UnFirstCraft(j, i, gongxu0, aa.listM_base_sum, d)

                                } else {// 不是该设备的第一道工序
                                    System.out.println("设备最后一道工序结束时间" + aa.listM_base_sum[d - 1][aa.listM_base_sum.get(d - 1).size - 1].getEndtime()
                                            + "前一道工序结束时间" + gongxu0.extendCraftByPreEcId.endtime
                                    )
                                    if (aa.listM_base_sum[d - 1][aa.listM_base_sum.get(d - 1).size - 1].endtime//机器上前一道工序完成时间
                                            >= gongxu0.extendCraftByPreEcId.endtime) {// 工件的前一道工序已经完成
                                        // gongxu0.getExtendCraftByPreEcId().getEndtime()   工序前一道结束时间
                                        println("前一道工序完成")

                                        dc.UnFirstDevice_UnFirstCraft_Finish(j, i, gongxu0, aa.listM_base_sum, d)
                                    } else {// 工件的前一道工序未完成
                                        println("前一道工序未完成")
                                        dc.UnFirstDevice_UnFirstCraft_UnFinish(j, i, gongxu0, aa.listM_base_sum, d)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }


        //aa.listM_base_sum 按设备加工顺序存储工序信息。sb1上的1、2.。。。；sb2上的1、2.。。。。


        //   	 message = message + "初始算法运行后的结果"+"<br>"+"\n";
        println("Now初始算法运行后的结果" + "<br>" + "\n")
        // 输出设置
        for (d in 1..aa.DeviceNumber) {
            if (!aa.listM_base_sum.get(d - 1).isEmpty()) {
                println("SB$d:")
                //				message = message +"SB" + d + ":"+"<br>"+"\n";
                for (u in aa.listM_base_sum.get(d - 1))
                    System.out.println("工件名：" + u.name + "设备号"
                            + u.device + "~~~~~机器上顺序~~~~~~" + u.did + "所需要花费时间" + u.time
                            + "工序起始时间" + u.getBegintime() + "工序结束时间"
                            + u.endtime)
            }
        }

        //获取所有的空闲时间
        val rsc = ReSortCheck()
        val lists_freetime = rsc.GetFreeTimes(aa.listM_base_sum)

        var begintime_max = 0//优化前时间
        var endtime_max = 0//优化后时间
        do {
            begintime_max = endtime_max
            /* for (List<GongXu> l : listM_base_sum) {//获取当前加工序列中的最大加工时间
				 		    	if(l.get(l.size()-1).getEndtime()>begintime_max){
				 		    		begintime_max = l.get(l.size()-1).getEndtime();
				 		    	}
				}*/
            println("begintime_max$begintime_max")
            println("***************调节算法执行**************")
            println("间隙")
            Collections.sort(lists_freetime)
            for (f in lists_freetime) {
                System.out.println("设备号:" + f.device + "开始时间："
                        + f.begintime + "结束时间：" + f.endtime + "占用时间"
                        + f.time + "前一道工序信息" + f.pregongxu!!.device
                        + f.pregongxu!!.name + "后一道工序信息"
                        + f.postgongxu!!.device
                        + f.pregongxu!!.name)

                // 调节算法入口


                val listM = f.listdevice// 现将后两道工序进行调换
                if (f.postgongxu!!.did + 1 <= listM!!.size) {// 防止pregongxu和postgongxu出现空指针异常
                    val pregongxu = listM[f.postgongxu!!.did - 1]// 前一道工序
                    val postgongxu = listM[f.postgongxu!!.did]// 后一道工序
                    if (f.begintime > postgongxu.extendCraftByPreEcId.endtime) {// 如果交换后的工序的开始时间>同工件的前一道工序的结束时间
                        rsc.ChangeStatus1(f, pregongxu, postgongxu, listM)
                    } else if (postgongxu.extendCraftByPreEcId.endtime - f.begintime < f.time && postgongxu.extendCraftByPreEcId.endtime - f.begintime > 0) {// 同工件的前一道工序的结束时间-间隙的开始时间<原来间隙的时间
                        rsc.ChangeStatus2(f, pregongxu, postgongxu, listM)
                    } else {
                        // 无法交换
                        rsc.ChangeStatus3(f, pregongxu, postgongxu, listM)
                    }

                }
            }

            for (l in aa.listM_base_sum) {//获取当前加工序列中的最大加工时间
                if (l.get(l.size - 1).getEndtime() > endtime_max) {
                    endtime_max = l.get(l.size - 1).getEndtime()
                }
            }
            println("endtime_max$endtime_max")

        } while (begintime_max != endtime_max)


        // 输出设置
        println("调节算法运行后的结果")
        for (d in 1..aa.DeviceNumber) {
            if (!aa.listM_base_sum.get(d - 1).isEmpty()) {
                println("SB$d:")
                for (u in aa.listM_base_sum.get(d - 1))
                    System.out.println("工件名：" + u.getName() + "设备号"
                            + u.getDevice() + "~~~~~机器上顺序~~~~~~" + u.getDid() + "所需要花费时间" + u.getTime()
                            + "工序起始时间" + u.begintime + "工序结束时间"
                            + u.getEndtime())
            }
        }

        return aa
    }

    companion object {
        private var instance: FirstArithmeticObjectByExtendCraft? = null
        @Synchronized
        fun getInstance(): FirstArithmeticObjectByExtendCraft {
            if (instance == null)
                instance = FirstArithmeticObjectByExtendCraft()
            return instance as FirstArithmeticObjectByExtendCraft

        }
    }
}
