package counter

import events.CountEvent
import events.DecrementEvent
import events.IncrementEvent

sealed interface Counter {

    fun count(countEvent: CountEvent) {
        when (countEvent) {
            is DecrementEvent -> count(countEvent)
            is IncrementEvent -> count(countEvent)
        }
    }

    fun count(incrementEvent: IncrementEvent) {

    }

    fun count(decrementEvent: DecrementEvent) {

    }

    fun getCount(): Int

}