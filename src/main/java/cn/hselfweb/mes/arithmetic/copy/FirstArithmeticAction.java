package cn.hselfweb.mes.arithmetic.copy;

import cn.hselfweb.mes.enity.CraftExtend;
import cn.hselfweb.mes.arithmetic.entity.XLList;
import cn.hselfweb.mes.arithmetic.pojo.ArithmeticParams;
import cn.hselfweb.mes.arithmetic.pojo.FirstArithmeticParams;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FirstArithmeticAction {

    public static String execute() throws Exception {
        //加载数据
        XLList data = XLList.Load();
        //====================所有设备================
        int DeviceNumber = data.getDeviceNumber();
        //list_sum(i)(j)  表示第j+1个工件的第i+1道工序
        List<List<CraftExtend>> list_sum = data.getList_sum();//用于存储工艺和和工序的关系
        //设备默认编号由1开始，使用几个则编号至几
        List<List<CraftExtend>> listM_base_sum = new ArrayList<List<CraftExtend>>(DeviceNumber);// 生成    设备加工序列排序集合  的集合    (最后的结果集，甘特图展示)
        //(每个元素是一个设备上的加工工序集合 eg： 设备一：工件一的第一道工序，工件三的第二道工序，...;设备二：工件三的第一道工序，工件x的xx，...;....;....;)
        for (int d = 1; d <= DeviceNumber; d++) {
            List<CraftExtend> listM_base = new ArrayList<CraftExtend>();// 生成设备加工序列排序集合
            listM_base_sum.add(listM_base);
        }
        //=======================用于存储LIST_SUM的每个基本工艺的详细工序=========================


        //为每道工序添加效率
        //添加效率
        double xl = 0.0;
        int time = 0;
        int totaltime = 0;
        for (int i = 0; i < list_sum.size(); i++) {
            List<CraftExtend> list = list_sum.get(i);
            for (int j = 0; j < list.size(); j++) {
                //	int x=j-1;
                time = 0;
                totaltime = 0;
                CraftExtend ec = list.get(j);
                xl = 0.0;
                if (i != 0) {
//					System.out.println(" ec.getTime():"+ec.getTime()+"   ec.getName:"+ec.getName());
                    for (int k = 0; k <= i; k++) {
                        time = time + list_sum.get(k).get(j).getTime();
                        //		System.out.println("ec.getTime()--time:"+time);
                    }

                    //time = time + ec.getTime();
//					System.out.println("time:"+time);
                    for (int p = 0; p < list_sum.size(); p++) {
                        totaltime = totaltime + list_sum.get(p).get(j).getTime();
                        //		System.out.println("ec.getTime()--totaltime:"+totaltime);
                    }
                    xl = ((double) time) / ((double) totaltime);
                    System.out.println(ec.getName() + "效率:" + xl + time + totaltime);

                    list.get(j).setXiaolv(xl);

                } else {
                    time = ec.getTime();
//					System.out.println("time:"+time);
                    for (int p = 0; p < list_sum.size(); p++) {
                        totaltime = totaltime + list_sum.get(p).get(j).getTime();
//					System.out.println("ec.getTime()--totaltime:"+totaltime);					
                    }

                    xl = ((double) time) / ((double) totaltime);
                    System.out.println(ec.getName() + "效率:" + xl + time + totaltime);
                    list.get(j).setXiaolv(xl);
                }
            }
        }

        for (List<CraftExtend> list : list_sum) {
            Collections.sort(list);  //效率排序
        }

        //调度算法的接口
        //首先创建算法工厂类的对象
        ArithmeticFactory af = ArithmeticFactory.getInstance();
        Arithmetic arithmetic = af.creatArithmetic("B");

        System.out.println("运行的是效率算法的实例！");

        //算法参数对象的创建
        ArithmeticParams aaii = FirstArithmeticParams.getInstance();
        aaii.setDeviceNumber(DeviceNumber);
        aaii.setList_sum(list_sum);
        aaii.setListM_base_sum(listM_base_sum);

        //给算法中传递算法参数对象
        ArithmeticParams resultAA = arithmetic.sort(aaii);

        return "success";

    }
}