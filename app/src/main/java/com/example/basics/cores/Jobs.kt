package com.example.basics.cores

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        val job = launch {
//            delay(5000)
            println("Job is working")

            val prepareJob = launch {
                println("prepareJob is started working")
                delay(5000)
                println("prepareJob is stop working")
            }
            prepareJob.invokeOnCompletion {
                println("prepareJob is completed.")
            }

            val sendJob = launch {
                println("sendJob is started working")
                delay(5000)
                println("sendJob is stop working")
            }
            sendJob.invokeOnCompletion {
                println("sendJob is completed.")
            }
        }

        job.invokeOnCompletion {
            println("jobs is completed.")
        }
        println("Job is going to be cancel.")
        delay(5000)
        job.cancel()
    }
}