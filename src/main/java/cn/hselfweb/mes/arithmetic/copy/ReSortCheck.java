package cn.hselfweb.mes.arithmetic.copy;

import cn.hselfweb.mes.enity.CraftExtend;
import cn.hselfweb.mes.arithmetic.pojo.FreeTimeByExtendCraft;

import java.util.ArrayList;
import java.util.List;

public class ReSortCheck {
    /*该类用调节算法的排序中所包含的算法函数*/
    //判断所有的闲暇时间
    public List<FreeTimeByExtendCraft> GetFreeTimes(List<List<CraftExtend>> listM_base_sum) {
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
        return lists_freetime;
    }

    //交换Did
    public int ChangeDid(CraftExtend pregongxu, CraftExtend postgongxu) {
        int did = 0;
        did = postgongxu.getDid();
        postgongxu.setDid(pregongxu.getDid());
        pregongxu.setDid(did);
        return did;
    }

    //交换在链表中的位置(这里的d与ChangeDid返回的did是一致的)
    public void ChangeInList(CraftExtend pregongxu, CraftExtend postgongxu, int d) {
        List<CraftExtend> l = null;
        l = pregongxu.getList();// 工序所在的设备序列
        l.set((d - 1), pregongxu);
        l.set((d - 2), postgongxu);
    }

    //对后续工序的移动（第一种情况）
    public void MoveTheNext1(FreeTimeByExtendCraft f, CraftExtend pregongxu, CraftExtend postgongxu, List<CraftExtend> listM) {
        if ((f.getPostgongxu().getDid() + 2) <= listM.size()) {// 如果交换后，后面有两道工序以上
            for (int i = f.getPostgongxu().getDid() + 1; i <= listM
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
    }

    //对后续工序的移动（第一种情况）
    public void MoveTheNext2(FreeTimeByExtendCraft f, CraftExtend pregongxu, CraftExtend postgongxu, List<CraftExtend> listM) {
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
    }


    // 如果交换后的工序的开始时间>同工件的前一道工序的结束时间(可以直接交换)
    public void ChangeStatus1(FreeTimeByExtendCraft f, CraftExtend pregongxu, CraftExtend postgongxu, List<CraftExtend> listM) {
        int postbegintime = postgongxu.getBegintime();//临时存储后一道工序的开始时间
        int postendtime = postgongxu.getEndtime();//临时存储后一道工序的结束时间
        int prebegintime = pregongxu.getBegintime();//临时存储前一道工序的开始时间
        int preendtime = pregongxu.getEndtime();//临时存储前一道工序的结束时间
        postgongxu.setBegintime(f.getBegintime());
        postgongxu.setEndtime(f.getBegintime()
                + listM.get(f.getPostgongxu().getDid()).getTime());
        if (pregongxu.getExtendCraftByPreEcId().getEndtime() < postgongxu.getEndtime()) {//这里还需要判断pregongxu交换后是否能够直接接在postgongxu后而不需要产生缝隙
            //不需要产生缝隙
            pregongxu.setBegintime(postgongxu.getEndtime());
            pregongxu.setEndtime(postgongxu.getEndtime()
                    + pregongxu.getTime());
        } else {
            //需要产生缝隙
            pregongxu.setBegintime(pregongxu.getExtendCraftByPreEcId().getEndtime());
            pregongxu.setEndtime(pregongxu.getExtendCraftByPreEcId().getEndtime()
                    + pregongxu.getTime());
        }


        /*测试中，判断交换后第二道工序的结束时间是否小于其同工件后一道工序的开始时间*/
//			System.out.println("@@@@@@@@@"+pregongxu.getEndtime());
//			System.out.println("@@@@@@@@@"+pregongxu.getExtendCraftByAftEcId().getEcName());
        if (pregongxu.getExtendCraftByAftEcId() == null) {
            //已经是该工件的最后一道工序，可以不用进行判断
            // 交换did
            int did = this.ChangeDid(pregongxu, postgongxu);

            // 交换在链表中前后位置
            this.ChangeInList(pregongxu, postgongxu, did);

            //对后续工序的移动
            MoveTheNext1(f, pregongxu, postgongxu, listM);
        } else {
            if (pregongxu.getEndtime() < pregongxu.getExtendCraftByAftEcId().getBegintime()) {
                //可以交换
                // 交换did
                int did = this.ChangeDid(pregongxu, postgongxu);

                // 交换在链表中前后位置
                this.ChangeInList(pregongxu, postgongxu, did);

                //对后续工序的移动
                MoveTheNext1(f, pregongxu, postgongxu, listM);
            } else {
                //交换后将出现问题，该空隙将无法交换
                pregongxu.setBegintime(prebegintime);
                pregongxu.setEndtime(preendtime);
                postgongxu.setBegintime(postbegintime);
                postgongxu.setEndtime(postendtime);
            }
        }
    }

    // 同工件的前一道工序的结束时间-间隙的开始时间<原来间隙的时间
    //交换将会产生新的间隙
    public void ChangeStatus2(FreeTimeByExtendCraft f, CraftExtend pregongxu, CraftExtend postgongxu, List<CraftExtend> listM) {
        int postbegintime = postgongxu.getBegintime();//临时存储后一道工序的开始时间
        int postendtime = postgongxu.getEndtime();//临时存储后一道工序的结束时间
        int prebegintime = pregongxu.getBegintime();//临时存储前一道工序的开始时间
        int preendtime = pregongxu.getEndtime();//临时存储前一道工序的结束时间

        // 交换产生新的空隙
        postgongxu.setBegintime(postgongxu.getExtendCraftByPreEcId()
                .getEndtime());
        postgongxu.setEndtime(postgongxu.getBegintime()
                + postgongxu.getTime());

        if (pregongxu.getExtendCraftByPreEcId().getEndtime() < postgongxu.getEndtime()) {//这里还需要判断pregongxu交换后是否能够直接接在postgongxu后而不需要产生缝隙
            //不需要产生缝隙
            pregongxu.setBegintime(postgongxu.getEndtime());
            pregongxu.setEndtime(postgongxu.getEndtime()
                    + pregongxu.getTime());
        } else {
            //需要产生缝隙
            pregongxu.setBegintime(pregongxu.getExtendCraftByPreEcId().getEndtime());
            pregongxu.setEndtime(pregongxu.getExtendCraftByPreEcId().getEndtime()
                    + pregongxu.getTime());
        }


        /*测试中，判断交换后第二道工序的结束时间是否小于其同工件后一道工序的开始时间*/
        if (pregongxu.getExtendCraftByAftEcId() == null) {
            //已经是该工件的最后一道工序，可以不用进行判断
            // 交换did
            int did = this.ChangeDid(pregongxu, postgongxu);

            // 交换在链表中前后位置
            this.ChangeInList(pregongxu, postgongxu, did);


            //对后续工序的移动
            MoveTheNext2(f, pregongxu, postgongxu, listM);
        } else {
            if (pregongxu.getEndtime() < pregongxu.getExtendCraftByAftEcId().getBegintime()) {
                //可以交换
                // 交换did
                int did = this.ChangeDid(pregongxu, postgongxu);

                // 交换在链表中前后位置
                this.ChangeInList(pregongxu, postgongxu, did);


                //对后续工序的移动
                MoveTheNext2(f, pregongxu, postgongxu, listM);
            } else {
                //交换后将出现问题，该空隙将无法交换
                pregongxu.setBegintime(prebegintime);
                pregongxu.setEndtime(preendtime);
                postgongxu.setBegintime(postbegintime);
                postgongxu.setEndtime(postendtime);
            }
        }
    }

    public void ChangeStatus3(FreeTimeByExtendCraft f, CraftExtend pregongxu, CraftExtend postgongxu, List<CraftExtend> listM) {
        //无法进行交换，抛异常
    }


}
