package cn.hselfweb.mes.arithmetic.pojo

import cn.hselfweb.mes.enity.CraftExtend
import java.util.*

abstract class ArithmeticParams {
    //算法库中的算法参数的抽象类，抽象类中存放各个算法所需要的共同的参数和方法，如工作重心等
    //不能使用接口，接口中不能对参数设置get和set方法
    //get和set方法只是为了结构化
    var list_sum: MutableList<MutableList<CraftExtend>> = ArrayList()// 生成特定工序包含的加工序列集合的集合    （ 每个工件的工序集未一个元素）
    var DeviceNumber = 0
    var listM_base_sum: MutableList<MutableList<CraftExtend>> = ArrayList()// 生成设备加工序列排序集合的集合    （结果集）


}
