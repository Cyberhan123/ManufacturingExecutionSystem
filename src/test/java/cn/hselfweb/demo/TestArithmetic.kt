package cn.hselfweb.demo

import cn.hselfweb.mes.arithmetic.entity.CraftTime
import cn.hselfweb.mes.arithmetic.entity.DeviceTime
import cn.hselfweb.mes.enity.CraftExtend
import cn.hselfweb.mes.enity.ProcessDispatchResult
import org.junit.Test
import java.util.ArrayList

class TestArithmetic {
    @Test
    private fun testA() {

        //List<List> lists = new ArrayList<List>();// list1-list4的集合就是我们现在的list_sum
        //调度算法的接口
        //首先创建算法工厂类的对象
        val af = ArithmeticFactory.instance
        val arithmetic = af.creatArithmetic("B")

        println("运行的是效率算法的实例！")
        val DeviceNumber = 3
        val listM_base_sum: MutableList<MutableList<CraftExtend>> = ArrayList()
        var craftExtend:CraftExtend
        craftExtend.
        //算法参数对象的创建
        val list_sum: MutableList<MutableList<CraftExtend>> = ArrayList()
        val aaii = FirstArithmeticParams.getInstance()
        aaii.DeviceNumber = DeviceNumber
        aaii.list_sum = list_sum
        aaii.listM_base_sum = listM_base_sum

        //给算法中传递算法参数对象
        val resultAA = arithmetic!!.sort(aaii)

        for (d in 1..DeviceNumber) {
            for (ec in resultAA.listM_base_sum[d - 1]) {
                val pdr = ProcessDispatchResult()
                //TODO: 这块不知道有没有完全解决
//                pdr.mrpBase = bmrp
//                w = workpieceRepository.findWorkpieceByWorkpieceId(ec.wpid)
//                pdr.workpiece = w
//                pdr.extendCraft = ec
//                pdr.pdrPreEc = craftExtendRepository.findCraftExtendsByPbId((ec.pbId))
//                pdr.pdrAftEc = craftExtendRepository.findCraftExtendsByProcedureId(ec.procedureId)
//                pdr.pdrPreEcTime = ec.begintime
//                pdr.pdrAftEcTime = ec.endtime
//                pdr.baseDevice = deviceRepository.findDeviceByDeviceId(procedureTypeRepository.findProcedureTypeByProcedureTypeId(procedureRepository.findProcedureByProcedureId(ec.procedureId).ptId).did)
//                //这里可以添加其他信息
//                processDispatchResult.add(pdr)

            }
        }
        //获取每个设备的加工时间最大值
        val listdevicetime = ArrayList<DeviceTime>()

        var name = ""
        for (d in 1..DeviceNumber) {
            var max = 0
            for (ec in resultAA.listM_base_sum[d - 1]) {
                if (ec.getEndtime() > max) {
                    max = ec.getEndtime()
                    name = ec.getDevice()
                }
            }
            val dt = DeviceTime()
            dt.deviceName = name
            dt.deviceTime = max
            listdevicetime.add(dt)
        }

        //获取每个工件的加工时间最大值
        val listcrafttime = ArrayList<CraftTime>()

        for (d in 0 until DeviceNumber) {
            val list = resultAA.listM_base_sum[d]
            for (j in list.indices) {
                var ec = list[j]

                if (listcrafttime.size == 0) {

                    //获取最后一道工序
                    if (ec.paId != 0.toLong()) {
                        do {
                            ec = ec.extendCraftByAftEcId
                            //System.out.println("ec.paId ="+ec.paId );
                        } while (ec.paId != 0.toLong())
                        val ct = CraftTime()

                        ct.craftName = ec.wpname
                        ct.craftTime = ec.endtime
                        //	System.out.println("ec.getWpname()="+ec.getWpname()+"~~~~~~"+"ec.getEndtime()"+ec.getEndtime());
                        listcrafttime.add(ct)
                    } else {
                        val ct = CraftTime()
                        ct.craftName = ec.wpname
                        ct.craftTime = ec.endtime
                        //	System.out.println("ec.getWpname()="+ec.getWpname()+"~~~~~~"+"ec.getEndtime()"+ec.getEndtime());

                        listcrafttime.add(ct)
                    }

                } else {
                    var u = true
                    for (ctt in listcrafttime) {
                        if (ctt.craftName.equals(ec.wpname)) {
                            u = false
                        } else {

                        }
                    }
                    if (u) {
                        if (ec.paId != 0.toLong()) {
                            do {
                                ec = ec.extendCraftByAftEcId
                            } while (ec.paId != 0.toLong())
                            val ct = CraftTime()
                            ct.craftName = ec.wpname
                            ct.craftTime = ec.endtime
                            //		System.out.println("ec.getWpname()="+ec.getWpname()+"~~~~~~"+"ec.getEndtime()"+ec.getEndtime());
                            listcrafttime.add(ct)
                        } else {
                            val ct = CraftTime()
                            ct.craftName = ec.wpname
                            ct.craftTime = ec.endtime
                            //		System.out.println("ec.getWpname()="+ec.getWpname()+"~~~~~~"+"ec.getEndtime()"+ec.getEndtime());

                            listcrafttime.add(ct)
                        }
                    }

                }
            }

            //				System.out.println("max1"+max1);
        }
    }
}