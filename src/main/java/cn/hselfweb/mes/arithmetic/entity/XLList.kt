package cn.hselfweb.mes.arithmetic.entity

import cn.hselfweb.mes.enity.CraftExtend
import com.google.gson.Gson
import com.google.gson.stream.JsonReader

import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.InputStreamReader
import java.util.ArrayList

class XLList {
    var deviceNumber: Int = 0
    var list_sum: MutableList<MutableList<CraftExtend>> = ArrayList()

    companion object {
        private val dataPath = "data.json"
        /**
         * 实例化  调度工序列表
         * @throws FileNotFoundException
         */
        @Throws(FileNotFoundException::class)
        fun Load(): XLList {
            val gson = Gson()
            val reader = JsonReader(InputStreamReader(FileInputStream(dataPath)))
            val ListC = gson.fromJson<XLList>(reader, XLList::class.java)

            val gxCount = ListC.list_sum.size
            val gjCount = ListC.list_sum[0].size
            for (i in 0 until gjCount) {
                for (j in 0 until gxCount) {
                    ListC.list_sum[j][i].gid = j + 1
                    if (j != 0) {
                        //设置前序工序
                        ListC.list_sum[j][i].extendCraftByPreEcId = ListC.list_sum[j - 1][i]
                    }
                    if (j < gxCount - 1) {
                        //设置后序工序
                        ListC.list_sum[j][i].extendCraftByAftEcId = ListC.list_sum[j + 1][i]
                    }

                }
            }
            return ListC
        }
    }

}
