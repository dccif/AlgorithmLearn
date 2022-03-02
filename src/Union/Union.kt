package Union

class Union {
    class Element<V>(var value: V)

    class UnionFindSet<V>(var list: List<V>) {
        val elementMap: HashMap<V, Element<V>> = HashMap()
        val fatherMap: HashMap<Element<V>, Element<V>> = HashMap()
        val sizeMap: HashMap<Element<V>, Int> = HashMap()

        init {
            val sizeMap: HashMap<Element<V>, Int> = HashMap()
            for (value in list) {
                val element = Element<V>(value)
                elementMap[value] = element
                fatherMap[element] = element
                sizeMap[element] = 1
            }
        }
    }

}