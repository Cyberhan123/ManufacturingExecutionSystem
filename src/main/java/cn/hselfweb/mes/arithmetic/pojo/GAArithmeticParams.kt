package cn.hselfweb.mes.arithmetic.pojo


class GAArithmeticParams private constructor() : ArithmeticParams() {

    //释放实例的方法
    fun releaseInstance() {
        if (instance == null) {

        } else {
            instance = null
        }
    }

    companion object {
        //继承了ArithmeticArgumentInterface接口中公有的参数和方法，在本类中实现一些私有的参数和方法
        //打算做成singleton模式

        private var instance: ArithmeticParams? = null

        //	public  List<List<ExtendCraft>> list_sum = new ArrayList<List<ExtendCraft>>();// 生成特定工序包含的加工序列集合的集合
        //	public  int DeviceNumber = 0;
        //	public  List<List<ExtendCraft>> listM_base_sum = new ArrayList<List<ExtendCraft>>();// 生成设备加工序列排序集合的集合
        //获取实例的方法
        @Synchronized
        fun getInstance(): ArithmeticParams {

            if (instance == null)
                instance = GAArithmeticParams()
            return instance as ArithmeticParams
        }
    }


}



