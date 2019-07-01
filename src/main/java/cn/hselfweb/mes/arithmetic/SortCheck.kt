package cn.hselfweb.mes.arithmetic

import cn.hselfweb.mes.enity.CraftExtend

class SortCheck {
    /*该类用于初次算法的排序中所包含的算法函数*/
    //设备的第一道工序(工件序列中的第一道工序)
    fun FirstDevice_FirstCraft(j: Int?, i: Int?, craftExtend: CraftExtend, listM_base_sum: MutableList<MutableList<CraftExtend>>, shunxu: Int) {

        craftExtend.setList(listM_base_sum[shunxu - 1])// 关联到设备序列
        craftExtend.setDid(1)   //位于设备上的第几个位置
        craftExtend.setEndtime(craftExtend.getTime())
        craftExtend.setBegintime(0)
        listM_base_sum[shunxu - 1].add(craftExtend)
        println("设备" + shunxu + "添加第一道工序成功" + "工序" + craftExtend.getName())
        //传入一个List<CraftExtend>  把当前的began  和end  传给   list.next.getExtendCraftByPreEcId.setBegintime**setEndtime

    }

    //非设备的第一道工序(工件序列中的第一道工序)
    fun UnFirstDevice_FirstCraft(j: Int?, i: Int?, craftExtend: CraftExtend, listM_base_sum: MutableList<MutableList<CraftExtend>>, shunxu: Int) {

        craftExtend.setList(listM_base_sum[shunxu - 1])// 关联到设备序列
        craftExtend.setDid(listM_base_sum[shunxu - 1].size + 1)
        System.out.println("craftExtend.setDid" + craftExtend.getDid())
        craftExtend.setBegintime(listM_base_sum[shunxu - 1][craftExtend.getDid() - 2].getEndtime())
        // 该设备上的前一道工序的结束时间+本道工序需要的时间
        craftExtend.setEndtime(listM_base_sum[shunxu - 1][craftExtend.getDid() - 2].getEndtime() + craftExtend.getTime())
        listM_base_sum[shunxu - 1].add(craftExtend)
        println("非设备的第一道工序,工序第一道     在设备" + shunxu + "添加工序" + craftExtend.getName() + "成功")
        /*	//添加后道工序的前道工序信息
				l.get(j).setBegintime(listM_base_sum.get(shunxu - 1).get(craftExtend.getDid() - 2).getEndtime());
				l.get(j).setEndtime(listM_base_sum.get(shunxu - 1).get(craftExtend.getDid() - 2).getEndtime()+ craftExtend.getTime());
	*/
    }

    //设备的第一道工序(不是工件序列中的第一道工序)  (第一道工序，有的设备没有被用到。以后工序时使用了某设备。    本例中未涉及这种情况)
    fun FirstDevice_UnFirstCraft(j: Int?, i: Int?, craftExtend: CraftExtend, listM_base_sum: MutableList<MutableList<CraftExtend>>, shunxu: Int) {
        craftExtend.did = 1// 位于该设备的第一道工序

        craftExtend.list = listM_base_sum[shunxu - 1]// 关联到设备序列
        // 该道工序的前一道关联工序的结束时间+本道工序需要的时间
        //craftExtend.getExtendCraftByPreEcId().getEndtime()   无效
        craftExtend.begintime = craftExtend.extendCraftByPreEcId.endtime
        craftExtend.endtime = craftExtend.extendCraftByPreEcId.endtime + craftExtend.time
        listM_base_sum[shunxu - 1].add(craftExtend)// 若为该设备的第一道工序，则必须在前一道工序完成的基础上加工
        System.out.println("开始时间" + craftExtend.extendCraftByPreEcId.endtime + "结束时间" + craftExtend.extendCraftByPreEcId.endtime + craftExtend.time)
        println("设备" + shunxu
                + "工序未成功，为该设备的第一道工序")
        /*	//添加后道工序的前道工序信息
				l.get(j).setBegintime(craftExtend.getExtendCraftByPreEcId().getEndtime());
				l.get(j).setEndtime(craftExtend.getExtendCraftByPreEcId().getEndtime() + craftExtend.getTime());
	*/
    }

    //不是设备的第一道工序(不是工件序列中的第一道工序)
    //前道工序已完成
    fun UnFirstDevice_UnFirstCraft_Finish(j: Int?, i: Int?, craftExtend: CraftExtend, listM_base_sum: MutableList<MutableList<CraftExtend>>, shunxu: Int) {

        craftExtend.list = listM_base_sum[shunxu - 1]// 关联到设备序列
        craftExtend.did = listM_base_sum[shunxu - 1].size + 1
        // 该设备上的前一道工序的结束时间+本道工序需要的时间
        craftExtend.endtime = listM_base_sum[shunxu - 1][craftExtend.did - 2].endtime + craftExtend.time
        println(craftExtend.name + "craftExtend.getEndtime" + craftExtend.endtime)
        craftExtend.begintime = listM_base_sum[shunxu - 1][craftExtend.did - 2].endtime
        listM_base_sum[shunxu - 1].add(craftExtend)
        println("非设备第一道工序，非工件第一道工序 (前道工序完成)  在设备" + shunxu + "添加工序" + craftExtend.getName() + "成功")
        //添加后道工序的前道工序信息
        /*			l.get(j).setBegintime(listM_base_sum.get(shunxu - 1).get(craftExtend.getDid() - 2).getEndtime());
				l.get(j).setEndtime(listM_base_sum.get(shunxu - 1).get(craftExtend.getDid() - 2).getEndtime()+ craftExtend.getTime());
	*/
    }

    //前道工序未完成
    fun UnFirstDevice_UnFirstCraft_UnFinish(j: Int?, i: Int?, craftExtend: CraftExtend, listM_base_sum: MutableList<MutableList<CraftExtend>>, shunxu: Int) {
        //前道工序未完成需要等待

        craftExtend.list = listM_base_sum[shunxu - 1]// 关联到设备序列
        craftExtend.did = listM_base_sum[shunxu - 1].size + 1
        // 该道工序的前一道关联工序的结束时间+本道工序需要的时间
        craftExtend.endtime = craftExtend.extendCraftByPreEcId.endtime + craftExtend.time
        //craftExtend.getExtendCraftByPreEcId().getEndtime()   获取不到
        craftExtend.begintime = craftExtend.extendCraftByPreEcId.endtime
        listM_base_sum[shunxu - 1].add(craftExtend)
        println("非设备第一道工序，非工件第一道工序 (前道工序未完成)  在设备" + shunxu + "添加工序" + craftExtend.name + "成功")
        //添加后道工序的前道工序信息
        /*			l.get(j).setBegintime(craftExtend.getExtendCraftByPreEcId().getEndtime());
				l.get(j).setEndtime(craftExtend.getExtendCraftByPreEcId().getEndtime()+ craftExtend.getTime());
	*/
    }
}




