package com.example.basics.cores

import kotlinx.coroutines.*

fun main() {
    println("Coroutines block is started")
    runBlocking {
        launch {
            delay(1000)
            println("runBlocking block is started")
        }

        GlobalScope.launch {
            delay(500)
            println("GlobalScope block is started")

        }

        coroutineScope {
            launch {
                delay(1500)
                println("coroutineScope block is started")
            }
        }

    }
    println("Coroutines block is end")
}