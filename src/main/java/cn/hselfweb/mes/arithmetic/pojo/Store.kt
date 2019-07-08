package cn.hselfweb.mes.arithmetic.pojo


class Store {
    var machineWorkTime = IntArray(N) //机器工作时间
    var processIds = IntArray(N)     //对应任务的工序
    var endTime = Array(N) { IntArray(N) } //job process => endtime
    val startTime = Array(N) { IntArray(N) }   //job process => starttime

}