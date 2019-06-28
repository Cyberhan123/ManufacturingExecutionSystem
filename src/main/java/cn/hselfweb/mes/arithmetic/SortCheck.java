package cn.hselfweb.mes.arithmetic;

import cn.hselfweb.mes.enity.CraftExtend;

import java.util.List;

public class SortCheck {

   public void FirstDevice_FirstCraft(CraftExtend ec, List<List<CraftExtend>> listM_base_sum, int order) {
        ec.setList(listM_base_sum.get(order - 1));
        ec.setDid(1);
        ec.setEndtime(ec.getTime());
        ec.setBegintime(0);
        listM_base_sum.get(order - 1).add(ec);
        //System.out.println("设备" + order + "添加第一道工序成功" + "工序" + ec.getName());
    }

    //非设备的第一道工序(工件序列中的第一道工序)
    void UnFirstDevice_FirstCraft(CraftExtend ec, List<List<CraftExtend>> listM_base_sum, int order) {
        ec.setList(listM_base_sum.get(order - 1));
        ec.setDid(listM_base_sum.get(order - 1).size() + 1);
//        System.out.println("ec.setDid" + ec.getDid());
        ec.setBegintime(listM_base_sum.get(order - 1).get(ec.getDid() - 2).getEndtime());
        ec.setEndtime(listM_base_sum.get(order - 1).get(ec.getDid() - 2).getEndtime() + ec.getTime());
        listM_base_sum.get(order - 1).add(ec);
    }

    void FirstDevice_UnFirstCraft(CraftExtend ec, List<List<CraftExtend>> listM_base_sum, int order) {
        ec.setDid(1);
        ec.setList(listM_base_sum.get(order - 1));
        ec.setBegintime(ec.getExtendCraftByPreEcId().getEndtime());
        ec.setEndtime(ec.getExtendCraftByPreEcId().getEndtime() + ec.getTime());
        listM_base_sum.get(order - 1).add(ec);
    }


    void UnFirstDevice_UnFirstCraft_Finish(CraftExtend ec, List<List<CraftExtend>> listM_base_sum, int order) {
        ec.setList(listM_base_sum.get(order - 1));// 关联到设备序列
        ec.setDid(listM_base_sum.get(order - 1).size() + 1);
        ec.setEndtime(listM_base_sum.get(order - 1).get(ec.getDid() - 2).getEndtime() + ec.getTime());
        ec.setBegintime(listM_base_sum.get(order - 1).get(ec.getDid() - 2).getEndtime());
        listM_base_sum.get(order - 1).add(ec);
        System.out.println("非设备第一道工序，非工件第一道工序 (前道工序完成)  在设备" + order + "添加工序" + ec.getName() + "成功");
    }

    //前道工序未完成
    void UnFirstDevice_UnFirstCraft_UnFinish(CraftExtend ec, List<List<CraftExtend>> listM_base_sum, int order) {
        ec.setList(listM_base_sum.get(order - 1));
        ec.setDid(listM_base_sum.get(order - 1).size() + 1);
        ec.setEndtime(ec.getExtendCraftByPreEcId().getEndtime() + ec.getTime());
        ec.setBegintime(ec.getExtendCraftByPreEcId().getEndtime());
        listM_base_sum.get(order - 1).add(ec);
    }
}




