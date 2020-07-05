package com.example.basics.cores

import kotlinx.coroutines.*

fun main() {
    runBlocking {
//        launch(Dispatchers.Main) {
//            print("Thread main name is ${Thread.currentThread().name} ")
//        }
        launch(Dispatchers.Default) {
            println("Thread Default name is ${Thread.currentThread().name} ")
        }
        launch(Dispatchers.Unconfined) {
            println("Thread Unconfined1 name is ${Thread.currentThread().name} ")
            delay(1000)
            println("Thread Unconfined2 name is ${Thread.currentThread().name} ")
        }
        launch(Dispatchers.IO) {
            println("Thread IO name is ${Thread.currentThread().name} ")
        }
        launch(newSingleThreadContext("vikasThread")) {
            println("Thread newSingleThreadContext name is ${Thread.currentThread().name} ")
        }
    }
}