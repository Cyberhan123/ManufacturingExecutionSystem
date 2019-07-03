package cn.hselfweb.mes.arithmetic.pojo

import cn.hselfweb.mes.enity.CraftExtend

class FreeTimeByExtendCraft : Comparable<FreeTimeByExtendCraft> {

    lateinit var device: String// 占用设备
    lateinit var listdevice: List<CraftExtend>//占用设备的工序排序
    var endtime: Int = 0 //空闲结束时间
    var begintime: Int = 0//空闲开始时间
    var time: Int = 0 //空间持续时间
    var did: Int = 0 //在设备上的下一道工序的编号
    lateinit var pregongxu: CraftExtend //前一道工序
    lateinit var postgongxu: CraftExtend//后一道工序

    var i: Int = 0   //记录位于第几个list_sum.get(i)位置
    var j: Int = 0  //记录位于第几个list_sum.get(i).get(j)
    override fun compareTo(other: FreeTimeByExtendCraft): Int {
        if (this.time > other.time) {
            return 1
        } else if (this.time == other.time) {
            return 0
        }
        return -1
    }

}
