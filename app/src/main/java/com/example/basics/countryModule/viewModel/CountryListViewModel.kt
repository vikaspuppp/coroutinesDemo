package com.example.basics.countryModule.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.basics.countryModule.model.CountryModel
import com.example.basics.countryModule.network.CountryServices
import kotlinx.coroutines.*

class CountryListViewModel : ViewModel() {

    val countryList by lazy { MutableLiveData<ArrayList<CountryModel>>() }
    private val countryApi by lazy { CountryServices.getCountryServices() }
    val countryError by lazy { MutableLiveData<String>() }
    var job: Job? = null
    private val coroutineExceptionHandler by lazy {
        CoroutineExceptionHandler { coroutineContext, throwable ->
            onError("Exception: ${throwable.localizedMessage}")
        }
    }

    fun refresh() {
        job = CoroutineScope(Dispatchers.IO + coroutineExceptionHandler).launch {
            val response = countryApi.getCountryList()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    countryList.value = response.body()
                } else {
                    onError("Error: ${response.message()}")
                }
            }
        }
    }

    fun onError(message: String) {
        countryError.value = message
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}