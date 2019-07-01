package cn.hselfweb.mes.arithmetic

import cn.hselfweb.mes.arithmetic.pojo.ArithmeticParams

abstract class Arithmetic {
    abstract fun sort(arithmeticParams: ArithmeticParams): ArithmeticParams
}
