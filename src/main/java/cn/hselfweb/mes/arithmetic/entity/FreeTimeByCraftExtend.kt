package cn.hselfweb.mes.arithmetic.entity

import cn.hselfweb.mes.enity.CraftExtend

class FreeTimeByCraftExtend : Comparable<FreeTimeByCraftExtend> {

    var device: String? = null// 占用设备
    var listdevice: List<CraftExtend>? = null//占用设备的工序排序
    var endtime: Int = 0//空闲结束时间
    var begintime: Int = 0//空闲开始时间
    var time: Int = 0//空间持续时间
    var did: Int = 0//在设备上的下一道工序的编号
    var pregongxu: CraftExtend? = null//前一道工序
    var postgongxu: CraftExtend? = null//后一道工序

    override fun compareTo(o: FreeTimeByCraftExtend): Int {
        if (o != null) {
            if (this.time > o.time) {
                return 1
            } else if (this.time == o.time) {
                return 0
            }
        }
        return -1
    }

}
