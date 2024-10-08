package logic

object Logic1 {

    fun solutionTreasureChest(weight1: Float, value1: Float, weight2: Float, value2: Float, maxW: Float): Float{
        if(weight1==weight2 && value1==value2) return -1f //invalid. you can't bring more than one item of each type
        var maxValue = 0f
        if (weight1 + weight2 <= maxW) {
            maxValue = maxOf(maxValue, value1 + value2)
        }
        // Check if we can take the first item
        if (weight1 <= maxW) {
            maxValue = value1
        }

        // Check if we can take the second item
        if (weight2 <= maxW) {
            maxValue = maxOf(maxValue, value2)
        }

        return maxValue
    }
}