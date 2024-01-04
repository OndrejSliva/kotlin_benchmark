import counter.Counter
import counter.DecrementCounter
import counter.IncrementCounter

class CounterStorage(private val counters: List<Counter>) {

    private val incrementCounters = counters.filterIsInstance<IncrementCounter>()
    private val decrementCounters = counters.filterIsInstance<DecrementCounter>()

    fun getAll(): List<Counter> {
        return counters
    }

    fun getIncrement(): List<IncrementCounter> = incrementCounters

    fun getDecrement(): List<DecrementCounter> = decrementCounters

}