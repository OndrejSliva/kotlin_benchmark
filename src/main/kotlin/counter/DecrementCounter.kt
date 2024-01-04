package counter

import events.DecrementEvent

class DecrementCounter: Counter {
    private var count = 0

    override fun count(decrementEvent: DecrementEvent) {
        count -= decrementEvent.amountToDecrement
    }

    override fun getCount(): Int = count
}