import counter.Counters
import counter.DecrementCounter
import counter.IncrementCounter
import events.DecrementEvent
import events.IncrementEvent
import kotlin.time.Duration
import kotlin.time.measureTime

fun main(args: Array<String>) {
    val incrementCounters1 = (1..1000).map { IncrementCounter() }
    val decrementCounters1 = (1..1000).map { DecrementCounter() }
    val countersStorage = CounterStorage(incrementCounters1 + decrementCounters1)
    val counters1 = Counters(countersStorage)
    val events = listOf(IncrementEvent(1), DecrementEvent(1), IncrementEvent(2), DecrementEvent(2))

    val measurements = hashMapOf(
        "single" to arrayListOf<Duration>(),
        "every" to arrayListOf<Duration>(),
    )

    val countOfMeasurements = 10_000

    for (measurementId in 1..countOfMeasurements) {
        if (measurementId % 1000 == 0) {
            println("$measurementId/$countOfMeasurements")
        }

        val timeSingleEveryTime = measureTime {
            for (i in 1..1_000) {
                for (event in events) {
                    counters1.countConcrete(event)
                }
            }
        }

        val timeFilterEveryTime = measureTime {
            for (i in 1..1_000) {
                for (event in events) {
                    counters1.countAll(event)
                }
            }
        }

        measurements.getValue("every").add(timeFilterEveryTime)
        measurements.getValue("single").add(timeSingleEveryTime)
    }

    printSummary("timeFilterSingleTime", measurements.getValue("single"))
    println()
    printSummary("timeFilterEveryTime", measurements.getValue("every"))
}

fun printSummary(measurementName: String, measurementTimes: List<Duration>) {
    println(measurementName)

    val sumOfDurations = measurementTimes.reduce { acc, duration -> duration + acc }

    println("min: ${measurementTimes.min()}")
    println("max: ${measurementTimes.max()}")
    println("avg: ${sumOfDurations / measurementTimes.size}")
    println("median: ${measurementTimes.sorted()[measurementTimes.size / 2]}")
    println("first: ${measurementTimes[0]}")
    println("last: ${measurementTimes[measurementTimes.lastIndex]}")
    println("sum: $sumOfDurations")
}