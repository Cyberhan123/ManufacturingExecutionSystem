package cn.hselfweb.mes.arithmetic


class ArithmeticFactory {

    fun creatArithmetic(sortName: String): Arithmetic? {
        var arithmetic: Arithmetic? = null
        val sortname = sortName.toCharArray()
        // jdk 1.7过后switch就支持String类型了。
        when (sortname[0]) {
            'B' // 基于效率函数的排序算法
            -> arithmetic = FirstArithmeticObjectByExtendCraft.getInstance()
            'S' -> {
            }
            'I' -> {
            }
            'M' -> {
            }
            'D' -> {
            }
            'H' -> {
            }
        }//sort = new SelectSort(arrays, range);
        //sort = new InsertSort(arrays, range);
        //sort = new MergeSort(arrays, range);
        //	        	   sort = new SystemSort(arrays, range);
        //sort = new HeapSort(arrays, range);
        return arithmetic
    }

    //释放实例的方法
    fun releaseFactoryInstance() {
        if (factoryInstance == null) {

        } else {
            factoryInstance = null
        }
    }

    companion object {
        private var factoryInstance: ArithmeticFactory? = null

        val instance: ArithmeticFactory
            @Synchronized get() {

                if (factoryInstance == null)
                    factoryInstance = ArithmeticFactory()
                return factoryInstance!!
            }
    }
}
