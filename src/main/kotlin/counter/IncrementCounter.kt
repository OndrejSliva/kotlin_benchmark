package counter

import events.IncrementEvent

class IncrementCounter: Counter {
    private var count = 0

    override fun count(incrementEvent: IncrementEvent) {
        count += incrementEvent.amountToIncrement
    }

    override fun getCount(): Int = count
}