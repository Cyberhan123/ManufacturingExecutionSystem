package cn.hselfweb.mes.arithmetic

import cn.hselfweb.mes.arithmetic.pojo.FreeTimeByExtendCraft
import cn.hselfweb.mes.enity.CraftExtend
import java.util.*

class ReSortCheck {
    /*该类用调节算法的排序中所包含的算法函数*/
    //判断所有的闲暇时间
    fun GetFreeTimes(listM_base_sum: List<List<CraftExtend>>): List<FreeTimeByExtendCraft> {
        val lists_freetime = ArrayList<FreeTimeByExtendCraft>()
        for (l in listM_base_sum) {// 遍历所有的工序，找到所有的空闲时间
            for (g in l) {
                if (g.getDid() !== l.size) {
                    if (g.getEndtime() !== l[g.getDid()].getBegintime()) {// 下一道工序的开始的时间不等于本道工序的结束时间
                        val ft = FreeTimeByExtendCraft()
                        //TODO:这块是我注释的
//                        ft.setDevice(g.getDevice())
//                        ft.setListdevice(l)
//                        ft.setDid(l[g.getDid()].getDid())
//                        ft.setBegintime(g.getEndtime())
//                        ft.setEndtime(l[g.getDid()].getBegintime())
//                        ft.setTime(ft.getEndtime() - ft.getBegintime())
//                        ft.setPregongxu(g)// 同一设备上的前一道工序
//                        ft.setPostgongxu(l[g.getDid()])// 同一设备上的后一道工序
                        lists_freetime.add(ft)
                    }
                }
            }
        }
        return lists_freetime
    }

    //交换Did
    fun ChangeDid(pregongxu: CraftExtend, postgongxu: CraftExtend): Int {
        var did: Int = postgongxu.did
        postgongxu.did = pregongxu.did
        pregongxu.did = did
        return did
    }

    //交换在链表中的位置(这里的d与ChangeDid返回的did是一致的)
    fun ChangeInList(pregongxu: CraftExtend, postgongxu: CraftExtend, d: Int) {
        var l: MutableList<CraftExtend>? = pregongxu.list
        // 工序所在的设备序列
        l!![d - 1] = pregongxu
        l[d - 2] = postgongxu
    }

    //对后续工序的移动（第一种情况）
    fun MoveTheNext1(f: FreeTimeByExtendCraft, pregongxu: CraftExtend, postgongxu: CraftExtend, listM: List<CraftExtend>) {
        if (f.postgongxu!!.did + 2 <= listM.size) {// 如果交换后，后面有两道工序以上
            for (i in f.postgongxu!!.did + 1..listM
                    .size) {// 判断接下来是否能够往前移动

                if (listM[i - 1].extendCraftByPreEcId.endtime < listM[i - 2].endtime) {// 该工序所在工件的上一道工序的结束时间小于调换后的工序的结束时间
                    // 可以直接往前移动
                    listM[i - 1].begintime = listM[i - 2].endtime
                    listM[i - 1].endtime = listM[i - 2].endtime + listM[i - 1].time


                } else if (listM[i - 1].extendCraftByPreEcId.endtime > listM[i - 2].endtime && listM[i - 1].extendCraftByPreEcId.endtime < listM[i - 1].begintime) {
                    // 该工序所在工件的上一道工序的结束时间大于调换后的工序时间的结束时间但小于本道工序的开始时间
                    // 可以往前移但会产生新的空隙
                    listM[i - 1].begintime = listM[i - 1].extendCraftByPreEcId
                            .endtime// 该工序所在工件的上一道工序的结束时间
                    listM[i - 1].endtime = listM[i - 1].extendCraftByPreEcId.endtime + listM[i - 1].time

                } else {
                    // 不能交换保持原样
                }
            }
        } else {// 接下来的工序只剩下一道或者接下里没有工序
            if (f.postgongxu!!.did + 1 === listM.size) {// 接下来还有一道工序
                if (pregongxu.getEndtime() !== listM[f.postgongxu!!.did].getBegintime()) {// 如果他与同设备前道工序之间有空隙
                    if (listM[f.postgongxu!!.did]
                                    .extendCraftByPreEcId.endtime < listM[f.postgongxu!!.did]
                                    .getBegintime()) {// 判断是否能往前移动
                        if (listM[f.postgongxu!!.did]
                                        .extendCraftByPreEcId.endtime < pregongxu
                                        .endtime) {
                            // 直接移动不产生缝隙
                            listM[f.postgongxu!!.did].begintime = pregongxu.endtime
                            listM[f.postgongxu!!.did].endtime = listM[f.postgongxu!!.did]
                                    .begintime + listM[f.postgongxu!!.did].time

                        } else if (listM[f.postgongxu!!.did]
                                        .extendCraftByPreEcId.endtime < listM[f.postgongxu!!.did].begintime) {
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
    }

    //对后续工序的移动（第一种情况）
    fun MoveTheNext2(f: FreeTimeByExtendCraft, pregongxu: CraftExtend, postgongxu: CraftExtend, listM: List<CraftExtend>) {
        if (f.postgongxu!!.did + 2 <= listM.size) {// 如果交换后，后面有两道工序以上
            for (i in f.postgongxu!!.did + 2..listM
                    .size) {// 判断接下来是否能够交换
                if (listM[i - 1].extendCraftByPreEcId.endtime < listM[i - 2].endtime) {// 该工序所在工件的上一道工序的结束时间小于调换后的工序时间
                    // 可以直接交换
                    listM[i - 1].begintime = listM[i - 2].endtime
                    listM[i - 1].endtime = listM[i - 2].endtime + listM[i - 1].time

                } else if (listM[i - 1].extendCraftByPreEcId
                                .endtime > listM[i - 2]
                                .endtime && listM[i - 1].extendCraftByPreEcId
                                .endtime < listM[i - 1]
                                .begintime) {
                    // 该工序所在工件的上一道工序的结束时间大于调换后的工序时间但小于本道工序的开始时间
                    // 可以交换但会产生新的空隙
                    listM[i - 1].begintime = listM[i - 1].extendCraftByPreEcId
                            .endtime// 该工序所在工件的上一道工序的结束时间
                    listM[i - 1].endtime = listM[i - 1].extendCraftByPreEcId.endtime + listM[i - 1].time


                } else {
                    // 不能交换保持原样
                }
            }
        } else {// 接下来的工序只剩下一道或者接下里没有工序
            if (f.postgongxu!!.did + 1 === listM.size) {// 接下来还有一道工序
                if (pregongxu.getEndtime() !== listM[f.postgongxu!!.did].getBegintime()) {// 如果他与同设备前道工序之间有空隙
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
    }


    // 如果交换后的工序的开始时间>同工件的前一道工序的结束时间(可以直接交换)
    fun ChangeStatus1(f: FreeTimeByExtendCraft, pregongxu: CraftExtend, postgongxu: CraftExtend, listM: List<CraftExtend>) {
        val postbegintime = postgongxu.getBegintime()//临时存储后一道工序的开始时间
        val postendtime = postgongxu.getEndtime()//临时存储后一道工序的结束时间
        val prebegintime = pregongxu.getBegintime()//临时存储前一道工序的开始时间
        val preendtime = pregongxu.getEndtime()//临时存储前一道工序的结束时间
        postgongxu.setBegintime(f.begintime)
        postgongxu.setEndtime(f.begintime + listM[f.postgongxu!!.did].getTime())
        if (pregongxu.getExtendCraftByPreEcId().getEndtime() < postgongxu.getEndtime()) {//这里还需要判断pregongxu交换后是否能够直接接在postgongxu后而不需要产生缝隙
            //不需要产生缝隙
            pregongxu.setBegintime(postgongxu.getEndtime())
            pregongxu.setEndtime(postgongxu.getEndtime() + pregongxu.getTime())
        } else {
            //需要产生缝隙
            pregongxu.setBegintime(pregongxu.getExtendCraftByPreEcId().getEndtime())
            pregongxu.setEndtime(pregongxu.getExtendCraftByPreEcId().getEndtime() + pregongxu.getTime())
        }


        /*测试中，判断交换后第二道工序的结束时间是否小于其同工件后一道工序的开始时间*/
        //			System.out.println("@@@@@@@@@"+pregongxu.getEndtime());
        //			System.out.println("@@@@@@@@@"+pregongxu.getExtendCraftByAftEcId().getEcName());
        if (pregongxu.getExtendCraftByAftEcId() == null) {
            //已经是该工件的最后一道工序，可以不用进行判断
            // 交换did
            val did = this.ChangeDid(pregongxu, postgongxu)

            // 交换在链表中前后位置
            this.ChangeInList(pregongxu, postgongxu, did)

            //对后续工序的移动
            MoveTheNext1(f, pregongxu, postgongxu, listM)
        } else {
            if (pregongxu.getEndtime() < pregongxu.getExtendCraftByAftEcId().begintime) {
                //可以交换
                // 交换did
                val did = this.ChangeDid(pregongxu, postgongxu)

                // 交换在链表中前后位置
                this.ChangeInList(pregongxu, postgongxu, did)

                //对后续工序的移动
                MoveTheNext1(f, pregongxu, postgongxu, listM)
            } else {
                //交换后将出现问题，该空隙将无法交换
                pregongxu.setBegintime(prebegintime)
                pregongxu.setEndtime(preendtime)
                postgongxu.setBegintime(postbegintime)
                postgongxu.setEndtime(postendtime)
            }
        }
    }

    // 同工件的前一道工序的结束时间-间隙的开始时间<原来间隙的时间
    //交换将会产生新的间隙
    fun ChangeStatus2(f: FreeTimeByExtendCraft, pregongxu: CraftExtend, postgongxu: CraftExtend, listM: List<CraftExtend>) {
        val postbegintime = postgongxu.begintime//临时存储后一道工序的开始时间
        val postendtime = postgongxu.getEndtime()//临时存储后一道工序的结束时间
        val prebegintime = pregongxu.begintime//临时存储前一道工序的开始时间
        val preendtime = pregongxu.getEndtime()//临时存储前一道工序的结束时间

        // 交换产生新的空隙
        postgongxu.setBegintime(postgongxu.getExtendCraftByPreEcId()
                .getEndtime())
        postgongxu.setEndtime(postgongxu.begintime + postgongxu.getTime())

        if (pregongxu.getExtendCraftByPreEcId().getEndtime() < postgongxu.getEndtime()) {//这里还需要判断pregongxu交换后是否能够直接接在postgongxu后而不需要产生缝隙
            //不需要产生缝隙
            pregongxu.setBegintime(postgongxu.getEndtime())
            pregongxu.setEndtime(postgongxu.getEndtime() + pregongxu.getTime())
        } else {
            //需要产生缝隙
            pregongxu.setBegintime(pregongxu.getExtendCraftByPreEcId().getEndtime())
            pregongxu.setEndtime(pregongxu.getExtendCraftByPreEcId().getEndtime() + pregongxu.getTime())
        }


        /*测试中，判断交换后第二道工序的结束时间是否小于其同工件后一道工序的开始时间*/
        if (pregongxu.getExtendCraftByAftEcId() == null) {
            //已经是该工件的最后一道工序，可以不用进行判断
            // 交换did
            val did = this.ChangeDid(pregongxu, postgongxu)

            // 交换在链表中前后位置
            this.ChangeInList(pregongxu, postgongxu, did)


            //对后续工序的移动
            MoveTheNext2(f, pregongxu, postgongxu, listM)
        } else {
            if (pregongxu.getEndtime() < pregongxu.getExtendCraftByAftEcId().begintime) {
                //可以交换
                // 交换did
                val did = this.ChangeDid(pregongxu, postgongxu)

                // 交换在链表中前后位置
                this.ChangeInList(pregongxu, postgongxu, did)


                //对后续工序的移动
                MoveTheNext2(f, pregongxu, postgongxu, listM)
            } else {
                //交换后将出现问题，该空隙将无法交换
                pregongxu.setBegintime(prebegintime)
                pregongxu.setEndtime(preendtime)
                postgongxu.setBegintime(postbegintime)
                postgongxu.setEndtime(postendtime)
            }
        }
    }

    fun ChangeStatus3(f: FreeTimeByExtendCraft, pregongxu: CraftExtend, postgongxu: CraftExtend, listM: List<CraftExtend>) {
        //无法进行交换，抛异常
    }


}
