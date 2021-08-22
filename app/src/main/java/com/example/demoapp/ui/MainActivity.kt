package com.example.demoapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demoapp.R
import com.example.demoapp.databinding.ActivityMainBinding
import com.example.demoapp.other.Status
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject lateinit var mainViewModel: MainViewModel

    @Inject lateinit var adapter: EmployeeAdapter

    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        initView()
        handleObserver()

    }

    private fun initView(){
        binding.rvEmployees.adapter = adapter
        binding.statefulView.showProgress()
        binding.swiperefresh.setOnRefreshListener {
            mainViewModel.getEmpFlow()
        }
    }

    private fun handleObserver(){
        mainViewModel.newList.observe(this, Observer {
            if(it.isNotEmpty()){
                binding.statefulView.showContent()
                binding.swiperefresh.isRefreshing = false
                adapter.submitList(it)
            }
        })
    }
}