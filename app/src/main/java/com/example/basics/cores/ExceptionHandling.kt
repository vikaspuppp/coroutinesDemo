package com.example.basics.cores

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

fun main() {
    runBlocking {
        val exceptionHandler =
            CoroutineExceptionHandler { coroutineContext: CoroutineContext, throwable: Throwable ->
                println("Job exception handle: ${throwable.localizedMessage}")
            }

        val job = GlobalScope.launch(exceptionHandler) {
            println("Global job scope is on work.")
            throw NullPointerException("Hey!! you have just pass value = null")
        }
        job.onJoin

        val deferred = GlobalScope.async {
            println("Global async scope is on work.")
            throw NullPointerException("Hey!! you have just pass data = null")
        }

        try {
            deferred.await()
        } catch (e: Exception) {
            println("Deferred exception handle: ${e.localizedMessage}")
        }

    }
}