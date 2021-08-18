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

class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository
):ViewModel(){

    private val _res = MutableLiveData<Resource<EmployeeResponse>>()

    val res : LiveData<Resource<EmployeeResponse>>
        get() = _res

    init {
//        getEmployees()
//        getEmp()
        getEmpFlow()
    }

    var newList = MutableLiveData<List<UserResponse>>()

//    private fun getEmployees()  = viewModelScope.launch {
//        _res.postValue(Resource.loading(null))
//        mainRepository.getEmployee().let {
//            if (it.isSuccessful){
//                _res.postValue(Resource.success(it.body()))
//            }else{
//                _res.postValue(Resource.error(it.errorBody().toString(), null))
//            }
//        }
//    }

//    private fun getEmp(){
//
//        viewModelScope.launch {
//            var response = mainRepository.getEmployee()
//            withContext(Dispatchers.Main){
//                if(response.isSuccessful){
//                    newList.value = response.body()!!.data
////                    newList.postValue(response.body()!!.data)
//                }
//            }
//        }
//    }

    private fun getEmpFlow(){

        viewModelScope.launch {

            mainRepository.getFlowEmployee().catch {
                Log.i("ErrorViewModel",it.message.toString())
            }.collect {
                Log.i("SuccessViewModel",it.toString())
                newList.value = it
            }

        }
    }

}