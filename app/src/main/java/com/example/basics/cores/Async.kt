package com.example.basics.cores

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.random.Random

fun main() {
    runBlocking {
        val firstDeferred = async { getFirstValue() }
        val secondDeferred = async { getSecondValue() }
        println("Please wait while we are processing your data...")
        delay(1000)
        val firstValue = firstDeferred.await()
        val secondValue = secondDeferred.await()
        println("total of sum : ${firstValue + secondValue}")
    }
}

suspend fun getFirstValue(): Int {
    delay(1000)
    val value = Random.nextInt(100)
    println("first value: $value")
    return value
}

suspend fun getSecondValue(): Int {
    delay(3000)
    val value = Random.nextInt(100)
    println("second value: $value")
    return value
}