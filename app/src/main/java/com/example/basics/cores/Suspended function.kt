package com.example.basics.cores

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun main() {

    GlobalScope.launch { completeMessage() }
    GlobalScope.launch { updateMessage() }
    print("hello, ")
    Thread.sleep(2000)
    println("Function call $funCallCount times.")
}

var funCallCount = 0

suspend fun completeMessage() {
    delay(500)
    println("world")
    println("Complete Message is called")
    funCallCount++
}

suspend fun updateMessage() {
    delay(1500)
    println("Complete Message is called")
    funCallCount++
}