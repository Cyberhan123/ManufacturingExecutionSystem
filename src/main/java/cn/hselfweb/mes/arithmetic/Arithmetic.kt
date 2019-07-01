package cn.hselfweb.mes.arithmetic

import cn.hselfweb.mes.arithmetic.pojo.ArithmeticParams

abstract class Arithmetic {

    var arithmeticParams: ArithmeticParams? = null
    abstract fun sort(arithmeticParams: ArithmeticParams): ArithmeticParams
}
