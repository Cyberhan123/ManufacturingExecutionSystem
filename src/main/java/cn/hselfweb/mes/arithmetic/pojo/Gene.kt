package cn.hselfweb.mes.arithmetic.pojo

data class Gene(val chromosome: String, val fitness: Int) : Comparable<Gene> {

    override fun equals(other: Any?): Boolean = when {
        other === this -> true
        other !is Gene -> false
        // chromosome == other.chromosome && fitness == other.fitness;
        else -> other.chromosome == chromosome && other.fitness == fitness
    }
    override fun compareTo(other: Gene): Int = compareValuesBy(this, other, Gene::chromosome)

}