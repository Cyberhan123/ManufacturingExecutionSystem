package cn.hselfweb.mes.arithmetic.entity

import cn.hselfweb.mes.enity.CraftExtend
import com.google.gson.Gson
import com.google.gson.stream.JsonReader
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.InputStreamReader
import java.util.*

class GAList {
    var DeviceNumber: Int = 0
    var list_sum: List<List<CraftExtend>> = ArrayList()

    companion object {
        private val dataPath = "GAData.json"
        /**
         * 实例化  调度工序列表
         * @throws FileNotFoundException
         */
        @Throws(FileNotFoundException::class)
        fun Load(): GAList {
            val gson = Gson()
            val reader = JsonReader(InputStreamReader(FileInputStream(dataPath)))
            val ListC = gson.fromJson<GAList>(reader, GAList::class.java)
            val gjCount = ListC.list_sum.size
            val gxCount = ListC.list_sum[0].size
            for (i in 0 until gjCount) {
                for (j in 0 until gxCount) {
                    ListC.list_sum[i][j].gid = j + 1
                    ListC.list_sum[i][j].wpid = (i + 1).toLong()
                    if (j != 0) {
                        //设置前序工序
                        ListC.list_sum[i][j].extendCraftByPreEcId = ListC.list_sum[i][j - 1]
                    }
                    if (j < gxCount - 1) {
                        //设置后序工序
                        ListC.list_sum[i][j].extendCraftByAftEcId = ListC.list_sum[i][j + 1]
                    }

                }
            }
            return ListC
        }
    }

}
