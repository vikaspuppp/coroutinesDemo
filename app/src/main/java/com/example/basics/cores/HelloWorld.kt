package com.example.basics.cores

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
//    helloWorld()

//    lightWeightTest()
}

private fun lightWeightTest() {
    runBlocking {
        repeat(1000000) {
            launch { print(".") }
        }
    }
}

private fun helloWorld() {
    GlobalScope.launch() {
        delay(1000)
        println("World")
    }
    print("Hello, ")
    Thread.sleep(2000)
}