package cn.hselfweb.mes.arithmetic

import cn.hselfweb.mes.arithmetic.pojo.FreeTimeByExtendCraft
import cn.hselfweb.mes.enity.CraftExtend
import java.util.*

class FinalByExtendCraft {
    fun sortByExtendCraft(list_sum: MutableList<MutableList<CraftExtend>>, DeviceNumber: Int, listM_base_sum: MutableList<MutableList<CraftExtend>>): MutableList<MutableList<CraftExtend>> {
        var message = ""
        for (l in list_sum) {
            for (i in l.indices) {
                val gongxu0 = l[i]
                if (gongxu0.device != null) {// 设备号为空时不参与排序
                    for (d in 1..DeviceNumber) {// 根据设备数量进行的循环
                        if (gongxu0.device == "M$d") {
                            if (gongxu0.gid == 1) {// 如果是某工件的第一道工序
                                if (listM_base_sum[d - 1].isEmpty()) {// 判断是否为该设备的第一道工序
                                    gongxu0.endtime = gongxu0.time
                                    gongxu0.begintime = 0
                                    gongxu0.did = 1// 位于该设备的第一道工序
                                    listM_base_sum[d - 1].add(gongxu0)
                                    gongxu0.list = listM_base_sum[d - 1]// 关联到设备序列
                                    println("M" + d + "添加第一道工序成功")
                                } else {
                                    listM_base_sum[d - 1].add(gongxu0)
                                    gongxu0.list = listM_base_sum[d - 1]// 关联到设备序列
                                    gongxu0.did = listM_base_sum[d - 1]
                                            .size
                                    gongxu0.endtime = listM_base_sum[d - 1][gongxu0.did - 2]
                                            .endtime + gongxu0.time// 该设备上的前一道工序的结束时间+本道工序需要的时间
                                    gongxu0.begintime = listM_base_sum[d - 1][gongxu0.did - 2]
                                            .endtime
                                    println("M" + d + "添加成功")
                                }
                            } else {// 不是工件的第一道工序则需要判断工件的前一道工序是否已经完成
                                if (listM_base_sum[d - 1].isEmpty()) {// 判断是否为该设备的第一道工序
                                    gongxu0.did = 1// 位于该设备的第一道工序
                                    listM_base_sum[d - 1].add(gongxu0)// 若为该设备的第一道工序，则必须在前一道工序完成的基础上加工
                                    gongxu0.list = listM_base_sum[d - 1]// 关联到设备序列
                                    gongxu0.endtime = gongxu0.extendCraftByPreEcId
                                            .endtime + gongxu0.time// 该道工序的前一道关联工序的结束时间+本道工序需要的时间
                                    gongxu0.begintime = gongxu0.extendCraftByPreEcId
                                            .endtime
                                    println("M" + d
                                            + "工序未成功，为该设备的第一道工序")

                                } else {// 不是该设备的第一道工序
                                    if (listM_base_sum[d - 1][listM_base_sum[d - 1]
                                                    .size - 1].endtime >= gongxu0
                                                    .extendCraftByPreEcId.endtime) {// 工件的前一道工序已经完成
                                        listM_base_sum[d - 1].add(gongxu0)
                                        gongxu0.list = listM_base_sum[d - 1]// 关联到设备序列
                                        gongxu0.did = listM_base_sum[d - 1].size
                                        gongxu0.endtime = listM_base_sum[d - 1][gongxu0.did - 2]
                                                .endtime + gongxu0.time// 该设备上的前一道工序的结束时间+本道工序需要的时间
                                        gongxu0.begintime = listM_base_sum[d - 1][gongxu0.did - 2]
                                                .endtime
                                        println("M" + d + "工序成功")
                                    } else {// 工件的前一道工序未完成
                                        listM_base_sum[d - 1].add(gongxu0)
                                        gongxu0.list = listM_base_sum[d - 1]// 关联到设备序列
                                        gongxu0.did = listM_base_sum[d - 1].size
                                        gongxu0.endtime = gongxu0
                                                .extendCraftByPreEcId.endtime + gongxu0.time// 该道工序的前一道关联工序的结束时间+本道工序需要的时间
                                        gongxu0.begintime = gongxu0
                                                .extendCraftByPreEcId.endtime
                                        println("M" + d + "工序未成功")
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        message = message + "算法运行后的结果" + "<br>"
        // 输出设置
        for (d in 1..DeviceNumber) {
            if (!listM_base_sum[d - 1].isEmpty()) {
                //				System.out.println("M" + d + ":");
                message = message + "M" + d + ":" + "<br>"
                for (u in listM_base_sum[d - 1])
                //					System.out.println("工件名：" + u.getName() + "设备号"
                //							+ u.getDevice() + "所需要花费时间" + u.getTime()
                //							+ "工序起始时间" + u.getBegintime() + "工序结束时间"
                //							+ u.getEndtime());
                    message = (message + "工件名：" + u.name + "设备号"
                            + u.device + "所需要花费时间" + u.time
                            + "工序起始时间" + u.begintime + "工序结束时间"
                            + u.endtime + "<br>")
            }
        }
        val lists_freetime = ArrayList<FreeTimeByExtendCraft>()
        for (l in listM_base_sum) {// 遍历所有的工序，找到所有的空闲时间
            for (g in l) {
                if (g.did != l.size) {
                    if (g.endtime != l[g.did].begintime) {// 下一道工序的开始的时间不等于本道工序的结束时间
                        val ft = FreeTimeByExtendCraft()

                        ft.device = g.device
                        //TODO: 这块有问题
                      //  ft.listdevice =
                        ft.did = l[g.did].did
                        ft.begintime = g.endtime
                        ft.endtime = l[g.did].begintime
                        ft.time = ft.endtime - ft.begintime
                        ft.postgongxu = g// 同一设备上的前一道工序
                        ft.postgongxu = l[g.did]// 同一设备上的后一道工序
                        lists_freetime.add(ft)
                    }
                }
            }
        }

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
            lists_freetime.sort()
            for (f in lists_freetime) {
                System.out.println("设备号:" + f.device + "开始时间："
                        + f.begintime + "结束时间：" + f.endtime + "占用时间"
                        + f.time + "前一道工序信息" + f.postgongxu!!.device
                        + f.postgongxu!!.did + "后一道工序信息"
                        + f.postgongxu!!.device
                        + f.postgongxu!!.did)

                // 调节算法入口

                val listM = f.listdevice// 现将后两道工序进行调换
                if (f.postgongxu!!.did + 1 <= listM!!.size) {// 防止pregongxu和postgongxu出现空指针异常
                    val pregongxu = listM[f.postgongxu!!.did - 1]// 前一道工序
                    val postgongxu = listM[f.postgongxu!!.did]// 后一道工序
                    if (f.begintime > postgongxu.extendCraftByPreEcId.endtime) {// 如果交换后的工序的开始时间>同工件的前一道工序的结束时间
                        // 直接交换即可
                        postgongxu.begintime = f.begintime
                        postgongxu.endtime = f.begintime + listM[f.postgongxu!!.did].time
                        if (pregongxu.extendCraftByPreEcId.endtime < postgongxu.endtime) {//这里还需要判断pregongxu交换后是否能够直接接在postgongxu后而不需要产生缝隙
                            //不需要产生缝隙
                            pregongxu.begintime = postgongxu.endtime
                            pregongxu.endtime = postgongxu.endtime + pregongxu.time
                        } else {
                            //需要产生缝隙
                            pregongxu.begintime = pregongxu.extendCraftByPreEcId.endtime
                            pregongxu.endtime = pregongxu.extendCraftByPreEcId.endtime + pregongxu.time
                        }


                        // 交换did
                        var did = 0
                        did = postgongxu.did
                        postgongxu.did = pregongxu.did
                        pregongxu.did = did

                        // 交换在链表中前后位置
                        var l: MutableList<CraftExtend>? = pregongxu.list
                        // 工序所在的设备序列
                        l!![did - 1] = pregongxu
                        l[did - 2] = postgongxu

                        if (f.postgongxu!!.did + 2 <= listM.size) {//** 如果交换后，后面有两道工序以上
                            for (i in f.postgongxu!!.did + 1..listM//**pregongxu
                                    .size) {// 判断接下来是否能够往前移动

                                if (listM[i - 1].extendCraftByPreEcId.endtime < listM[i - 2].endtime) {// 该工序所在工件的上一道工序的结束时间小于调换后的工序的结束时间
                                    // 可以直接往前移动
                                    listM[i - 1].begintime = listM[i - 2].endtime
                                    listM[i - 1].endtime = listM[i - 2].endtime + listM[i - 1].time


                                } else if (listM[i - 1].extendCraftByPreEcId
                                                .endtime > listM[i - 2]
                                                .endtime && listM[i - 1].extendCraftByPreEcId
                                                .endtime < listM[i - 1]
                                                .begintime) {
                                    // 该工序所在工件的上一道工序的结束时间大于调换后的工序时间的结束时间但小于本道工序的开始时间
                                    // 可以往前移但会产生新的空隙
                                    listM[i - 1].begintime = listM[i - 1].extendCraftByPreEcId.endtime// 该工序所在工件的上一道工序的结束时间
                                    listM[i - 1].endtime = listM[i - 1].extendCraftByPreEcId.endtime + listM[i - 1].time

                                } else {
                                    // 不能交换保持原样
                                }
                            }
                        } else {// 接下来的工序只剩下一道或者接下里没有工序
                            if (f.postgongxu!!.did + 1 === listM.size) {// 接下来还有一道工序
                                if (pregongxu.endtime != listM[f.postgongxu!!.did].begintime) {// 如果他与同设备前道工序之间有空隙
                                    if (listM[f.postgongxu!!.did]
                                                    .extendCraftByPreEcId.endtime < listM[f.postgongxu!!.did]
                                                    .begintime) {// 判断是否能往前移动
                                        if (listM[f.postgongxu!!.did].extendCraftByPreEcId.endtime < pregongxu.endtime) {
                                            // 直接移动不产生缝隙
                                            listM[f.postgongxu!!.did].begintime = pregongxu.endtime
                                            listM[f.postgongxu!!.did].endtime = listM[f.postgongxu!!.did].begintime + listM.get(f.postgongxu!!.did)
                                                    .time

                                        } else if (listM[f.postgongxu!!.did]
                                                        .extendCraftByPreEcId.endtime < listM[f.postgongxu!!.did]
                                                        .begintime) {
                                            // 可移动，但移动后将会产生间隙
                                            listM[f.postgongxu!!.did].begintime = listM[f.postgongxu!!.did].extendCraftByPreEcId.endtime
                                            listM[f.postgongxu!!.did].endtime = listM[f.postgongxu!!.did].begintime + listM[f.postgongxu!!.did].time
                                        } else {
                                            // 不可往前移动
                                        }
                                    }
                                }
                            }
                        }
                    } else if (postgongxu.extendCraftByPreEcId.endtime - f.begintime < f.time && postgongxu.extendCraftByPreEcId.endtime - f.begintime > 0) {// 同工件的前一道工序的结束时间-间隙的开始时间<原来间隙的时间
                        // 交换产生新的空隙
                        postgongxu.begintime = postgongxu.extendCraftByPreEcId.endtime
                        postgongxu.endtime = postgongxu.begintime + postgongxu.time

                        if (pregongxu.extendCraftByPreEcId.endtime < postgongxu.endtime) {//这里还需要判断pregongxu交换后是否能够直接接在postgongxu后而不需要产生缝隙
                            //不需要产生缝隙
                            pregongxu.begintime = postgongxu.endtime
                            pregongxu.endtime = postgongxu.endtime + pregongxu.time
                        } else {
                            //需要产生缝隙
                            pregongxu.begintime = pregongxu.extendCraftByPreEcId.endtime
                            pregongxu.endtime = pregongxu.extendCraftByPreEcId.endtime + pregongxu.time
                        }

                        // 交换did
                        var did: Int = postgongxu.did
                        postgongxu.did = pregongxu.did
                        pregongxu.did = did

                        // 交换在链表中前后位置
                        var l: MutableList<CraftExtend>? = null
                        l = pregongxu.list// 工序所在的设备序列
                        l!![did - 1] = pregongxu
                        l[did - 2] = postgongxu

                        if (f.postgongxu!!.did + 2 <= listM.size) {// 如果交换后，后面有两道工序以上
                            for (i in f.postgongxu!!.did + 2..listM
                                    .size) {// 判断接下来是否能够交换
                                if (listM[i - 1].extendCraftByPreEcId.endtime < listM[i - 2].endtime) {// 该工序所在工件的上一道工序的结束时间小于调换后的工序时间
                                    // 可以直接交换
                                    listM[i - 1].begintime = listM[i - 2].endtime
                                    listM[i - 1].endtime = listM[i - 2].endtime + listM[i - 1].time
                                } else if (listM.get(i - 1).extendCraftByPreEcId.endtime > listM[i - 2].endtime && listM[i - 1].extendCraftByPreEcId.endtime < listM[i - 1].begintime) {
                                    // 该工序所在工件的上一道工序的结束时间大于调换后的工序时间但小于本道工序的开始时间
                                    // 可以交换但会产生新的空隙
                                    listM[i - 1].begintime = listM[i - 1].extendCraftByPreEcId.endtime// 该工序所在工件的上一道工序的结束时间
                                    listM[i - 1].endtime = listM[i - 1].extendCraftByPreEcId.endtime + listM[i - 1].time


                                } else {
                                    // 不能交换保持原样
                                }
                            }
                        } else {// 接下来的工序只剩下一道或者接下里没有工序
                            if (f.postgongxu!!.did + 1 === listM.size) {// 接下来还有一道工序
                                if (pregongxu.endtime != listM[f.postgongxu!!.did].begintime) {// 如果他与同设备前道工序之间有空隙
                                    if (listM[f.postgongxu!!.did].extendCraftByPreEcId.endtime < listM[f.postgongxu!!.did].begintime) {// 判断是否能往前移动
                                        when {
                                            listM[f.postgongxu!!.did].extendCraftByPreEcId.endtime < pregongxu.endtime -> {
                                                // 直接移动不产生缝隙
                                                listM[f.postgongxu!!.did].begintime = pregongxu.endtime
                                                listM[f.postgongxu!!.did].endtime = listM[f.postgongxu!!.did].begintime + listM[f.postgongxu!!.did].time
                                            }
                                            listM[f.postgongxu!!.did].extendCraftByPreEcId.endtime < listM[f.postgongxu!!.did].begintime -> {
                                                // 可移动，但移动后将会产生间隙
                                                listM[f.postgongxu!!.did].begintime = listM[f.postgongxu!!.did].extendCraftByPreEcId.endtime
                                                listM[f.postgongxu!!.did].endtime = listM[f.postgongxu!!.did].begintime + listM[f.postgongxu!!.did].time
                                            }
                                            else -> {
                                                // 不可往前移动
                                            }
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
            for (l in listM_base_sum) {//获取当前加工序列中的最大加工时间
                if (l[l.size - 1].endtime > endtime_max) {
                    endtime_max = l[l.size - 1].endtime
                }
            }
            println("endtime_max$endtime_max")

        } while (begintime_max != endtime_max)


        // 输出设置
        println("调节算法运行后的结果")
        message = message + "算法运行后的结果" + "<br>"
        for (d in 1..DeviceNumber) {
            if (!listM_base_sum[d - 1].isEmpty()) {
                //				 				System.out.println("M" + d + ":");
                message = message + "M" + d + ":" + "<br>"
                for (u in listM_base_sum[d - 1])
                //				 					System.out.println("工件名：" + u.getName() + "设备号"
                //				 							+ u.getDevice() + "所需要花费时间" + u.getTime()
                //				 							+ "工序起始时间" + u.begintime + "工序结束时间"
                //				 							+ u.getEndtime());
                    message = (message + "工件名：" + u.name + "设备号"
                            + u.device + "所需要花费时间" + u.time
                            + "工序起始时间" + u.begintime + "工序结束时间"
                            + u.endtime + "<br>")
            }
        }
        return listM_base_sum
    }

}
