package cn.hselfweb.mes.arithmetic.copy;

import cn.hselfweb.mes.arithmetic.pojo.ArithmeticParams;

public abstract class Arithmetic {

	public ArithmeticParams arithmeticParams = null;
	public abstract  ArithmeticParams   sort(ArithmeticParams arithmeticParams);
}