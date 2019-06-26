package cn.hselfweb.mes.arithmetic.copy;

import cn.hselfweb.mes.enity.CraftExtend;

import java.util.List;

/*
 *
 * 该类用于初次算法的排序中所包含的算法函数
 *
 * */
public class SortCheck {

    //设备的第一道工序(工件序列中的第一道工序)
    public void FirstDevice_FirstCraft(Integer j, Integer i, CraftExtend ec, List<List<CraftExtend>> listM_base_sum, int shunxu) {

        ec.setList(listM_base_sum.get(shunxu - 1));// 关联到设备序列
        ec.setDid(1);   //位于设备上的第几个位置
        ec.setEndtime(ec.getTime());
        ec.setBegintime(0);
        listM_base_sum.get(shunxu - 1).add(ec);
        System.out.println("设备" + shunxu + "添加第一道工序成功" + "工序" + ec.getName());
        //传入一个List<CraftExtend>  把当前的began  和end  传给   list.next.getExtendCraftByPreEcId.setBegintime**setEndtime

    }

    //非设备的第一道工序(工件序列中的第一道工序)
    public void UnFirstDevice_FirstCraft(Integer j, Integer i, CraftExtend ec, List<List<CraftExtend>> listM_base_sum, int shunxu) {

        ec.setList(listM_base_sum.get(shunxu - 1));// 关联到设备序列
        ec.setDid(listM_base_sum.get(shunxu - 1).size() + 1);
        System.out.println("ec.setDid" + ec.getDid());
        ec.setBegintime(listM_base_sum.get(shunxu - 1).get(ec.getDid() - 2).getEndtime());
        // 该设备上的前一道工序的结束时间+本道工序需要的时间
        ec.setEndtime(listM_base_sum.get(shunxu - 1).get(ec.getDid() - 2).getEndtime() + ec.getTime());
        listM_base_sum.get(shunxu - 1).add(ec);
        System.out.println("非设备的第一道工序,工序第一道     在设备" + shunxu + "添加工序" + ec.getName() + "成功");
	/*	//添加后道工序的前道工序信息
				l.get(j).setBegintime(listM_base_sum.get(shunxu - 1).get(ec.getDid() - 2).getEndtime());
				l.get(j).setEndtime(listM_base_sum.get(shunxu - 1).get(ec.getDid() - 2).getEndtime()+ ec.getTime());
	*/
    }

    //设备的第一道工序(不是工件序列中的第一道工序)  (第一道工序，有的设备没有被用到。以后工序时使用了某设备。    本例中未涉及这种情况)
    public void FirstDevice_UnFirstCraft(Integer j, Integer i, CraftExtend ec, List<List<CraftExtend>> listM_base_sum, int shunxu) {
        ec.setDid(1);// 位于该设备的第一道工序

        ec.setList(listM_base_sum.get(shunxu - 1));// 关联到设备序列
        // 该道工序的前一道关联工序的结束时间+本道工序需要的时间
        //ec.getExtendCraftByPreEcId().getEndtime()   无效
        ec.setBegintime(ec.getExtendCraftByPreEcId().getEndtime());
        ec.setEndtime(ec.getExtendCraftByPreEcId().getEndtime() + ec.getTime());
        listM_base_sum.get(shunxu - 1).add(ec);// 若为该设备的第一道工序，则必须在前一道工序完成的基础上加工
        System.out.println("开始时间" + ec.getExtendCraftByPreEcId().getEndtime() + "结束时间" + (ec.getExtendCraftByPreEcId().getEndtime() + ec.getTime()));
        System.out.println("设备" + shunxu
                + "工序未成功，为该设备的第一道工序");
	/*	//添加后道工序的前道工序信息
				l.get(j).setBegintime(ec.getExtendCraftByPreEcId().getEndtime());
				l.get(j).setEndtime(ec.getExtendCraftByPreEcId().getEndtime() + ec.getTime());
	*/
    }

    //不是设备的第一道工序(不是工件序列中的第一道工序)
    //前道工序已完成
    public void UnFirstDevice_UnFirstCraft_Finish(Integer j, Integer i, CraftExtend ec, List<List<CraftExtend>> listM_base_sum, int shunxu) {

        ec.setList(listM_base_sum.get(shunxu - 1));// 关联到设备序列
        ec.setDid(listM_base_sum.get(shunxu - 1).size() + 1);
        // 该设备上的前一道工序的结束时间+本道工序需要的时间
        ec.setEndtime(listM_base_sum.get(shunxu - 1).get(ec.getDid() - 2).getEndtime() + ec.getTime());
        System.out.println(ec.getName() + "ec.getEndtime" + ec.getEndtime());
        ec.setBegintime(listM_base_sum.get(shunxu - 1).get(ec.getDid() - 2).getEndtime());
        listM_base_sum.get(shunxu - 1).add(ec);
        System.out.println("非设备第一道工序，非工件第一道工序 (前道工序完成)  在设备" + shunxu + "添加工序" + ec.getName() + "成功");
        //添加后道工序的前道工序信息
	/*			l.get(j).setBegintime(listM_base_sum.get(shunxu - 1).get(ec.getDid() - 2).getEndtime());
				l.get(j).setEndtime(listM_base_sum.get(shunxu - 1).get(ec.getDid() - 2).getEndtime()+ ec.getTime());
	*/
    }

    //前道工序未完成
    public void UnFirstDevice_UnFirstCraft_UnFinish(Integer j, Integer i, CraftExtend ec, List<List<CraftExtend>> listM_base_sum, int shunxu) {
        //前道工序未完成需要等待

        ec.setList(listM_base_sum.get(shunxu - 1));// 关联到设备序列
        ec.setDid(listM_base_sum.get(shunxu - 1).size() + 1);
        // 该道工序的前一道关联工序的结束时间+本道工序需要的时间
        ec.setEndtime(ec.getExtendCraftByPreEcId().getEndtime() + ec.getTime());
        //ec.getExtendCraftByPreEcId().getEndtime()   获取不到
        ec.setBegintime(ec.getExtendCraftByPreEcId().getEndtime());
        listM_base_sum.get(shunxu - 1).add(ec);
        System.out.println("非设备第一道工序，非工件第一道工序 (前道工序未完成)  在设备" + shunxu + "添加工序" + ec.getName() + "成功");
        //添加后道工序的前道工序信息
	/*			l.get(j).setBegintime(ec.getExtendCraftByPreEcId().getEndtime());
				l.get(j).setEndtime(ec.getExtendCraftByPreEcId().getEndtime()+ ec.getTime());
	*/
    }
}




