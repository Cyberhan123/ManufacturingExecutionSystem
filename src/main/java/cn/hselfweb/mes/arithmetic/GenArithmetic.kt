package cn.hselfweb.mes.arithmetic

import cn.hselfweb.mes.arithmetic.pojo.Craft
import cn.hselfweb.mes.arithmetic.pojo.Device
import cn.hselfweb.mes.arithmetic.pojo.Gene
import cn.hselfweb.mes.arithmetic.pojo.Store
import kotlin.random.Random

/**
 * 遗传算法
 * 种群存在可能性（解空间）
 * 个体 与   种群（所有工件的工序   所有工件的工序的集合）
 * 适度函数     （总体时间最短）
 * 遗传算子     （接近最短的所有工件工序 选单件工序最短且符合机器占用规则）
 */

lateinit var populations: List<Gene>

class GenArithmetic {

    var machine: Int = 0          //机器数量
    var job: Int = 0              //工件数量
    var process: Int = 0          //任务总数
    var chromosome_size: Int = 0  //染色体长度

    /*      输入例子
    规则 工件1 工序1->工序2->工序3
        工件2 工序2->工序1->工序3
        工件3 工序2->工序3->工序1
     */
    lateinit var role: List<List<Craft>>
    /*     输出例子
            工序1 工序2 工序3
   机器1
   机器2
   机器3
    */
    lateinit var result: List<List<Craft>>

    fun randint(start: Int, end: Int): Int {
        return Random.nextInt(32767) % (end - start + 1) + start
    }

    fun randint(end: Int): Int {
        return Random.nextInt(32767) % (end + 1)
    }

    fun calculateFitness(gene: Gene, store: Store) {
        var fulfillTime = 0;
        for ( i : gene.chromosome) {
            jobId = Int (i) - 1,
            processId = store.processIds[jobId],
            machineId = matrix.Machine[jobId][processId],
            time = matrix.Time[jobId][processId];
            store.processIds[jobId]++;
            store.startTime[jobId][processId] = processId == 0 ? store.machineWorkTime[machineId] : max(store.endTime[jobId][processId-1], store.machineWorkTime[machineId]);
            store.machineWorkTime[machineId] = store.startTime[jobId][processId] + time;
            store.endTime[jobId][processId] = store.machineWorkTime[machineId];
            if (store.machineWorkTime[machineId] > fulfillTime) {
                fulfillTime = store.machineWorkTime[machineId];
            }
        }
        gene.fitness = fulfillTime;
    }

}
