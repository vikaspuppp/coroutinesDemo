package com.example.basics.cores

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

fun main() {
    runBlocking {
        launch(Dispatchers.Default) {
            println("First context: ${coroutineContext.toString()}")
            withContext(Dispatchers.IO) {
                println("Second context: ${coroutineContext.toString()}")
            }
            println("Third context: ${coroutineContext.toString()}")
        }
    }
}