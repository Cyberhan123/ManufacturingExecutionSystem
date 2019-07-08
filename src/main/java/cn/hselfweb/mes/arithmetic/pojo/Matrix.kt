package cn.hselfweb.mes.arithmetic.pojo

const val N = 200

class Matrix {
    companion object {
        val Device = Array(N){ IntArray(N) }
        val Time = Array(N){ IntArray(N) }    //job process => time
        val Process = Array(N){ IntArray(N) }//job machine => process
    }
}