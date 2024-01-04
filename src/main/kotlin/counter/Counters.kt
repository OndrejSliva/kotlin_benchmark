package counter

import CounterStorage
import events.CountEvent
import events.DecrementEvent
import events.IncrementEvent

class Counters(private val counterStorage: CounterStorage) {

    fun countAll(countEvent: CountEvent) {
        counterStorage.getAll().forEach { it.count(countEvent) }
    }

    fun countConcrete(countEvent: CountEvent) {
        when (countEvent) {
            is IncrementEvent -> counterStorage.getIncrement().forEach { it.count(countEvent) }
            is DecrementEvent -> counterStorage.getDecrement().forEach { it.count(countEvent) }
        }
    }

}