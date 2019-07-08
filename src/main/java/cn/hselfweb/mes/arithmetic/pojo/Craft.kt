package cn.hselfweb.mes.arithmetic.pojo

class Craft {
    //工件编号
    var craftId:Long = 0
    //工序名称
    lateinit var name:String
    //工序耗时
    var time:Int = 0
    //占用的机器编号
    var deviceId:Long = 0
}