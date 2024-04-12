package com.example.unitest

class Counter {
    private var value_: Int = 0

//    companion object {
//        val value_: Int = 0
//    }

    fun getValue(): Int {
        return value_
    }

    fun increment() {
        value_ = value_ + 1
    }


}


data class CounterImmutable(var valueData: Int) {

}