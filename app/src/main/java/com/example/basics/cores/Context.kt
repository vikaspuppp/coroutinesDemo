package com.example.basics.cores

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        launch(CoroutineName("vikasCoroutines")) {
            println("this is run by: ${coroutineContext.get(CoroutineName.Key)}")
        }
    }
}