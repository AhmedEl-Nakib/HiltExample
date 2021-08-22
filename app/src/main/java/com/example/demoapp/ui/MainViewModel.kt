package com.example.demoapp.ui

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demoapp.models.Employee
import com.example.demoapp.models.EmployeeResponse
import com.example.demoapp.models.UserResponse
import com.example.demoapp.other.Resource
import com.example.demoapp.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainViewModel @Inject constructor(private val mainRepository: MainRepository):ViewModel(){

    init {
        getEmpFlow()
    }
    var newList = MutableLiveData<List<UserResponse>>()

    fun getEmpFlow(){

        viewModelScope.launch {
            mainRepository.getFlowEmployee().catch {
                Log.i("ErrorViewModel",it.message.toString())
            }.collect {
                newList.value = it
            }

        }
    }

}