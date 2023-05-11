package com.vaibhavmojidra.androidkotlindemocoroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.vaibhavmojidra.androidkotlindemocoroutines.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private var count:Int=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.incrementButton.setOnClickListener {
            binding.counterTextview.text=(count++).toString()
        }
        binding.startLongRunningTask.setOnClickListener {
            //This will block the process and phone and app will hang
            //longRunningTask()
            CoroutineScope(Dispatchers.IO).launch {
                longRunningTask("1")
            }
        }
        binding.startLongRunningTask2.setOnClickListener {
            //This will block the process and phone and app will hang
            //longRunningTask()
            CoroutineScope(Dispatchers.IO).launch {
                longRunningTask("2")
            }
        }
    }

    private fun longRunningTask(taskNumber:String){
        Log.i("MyTag","Long running task started")
        for(i in 1..2000000){
            Log.i("MyTag$taskNumber","Long running task $taskNumber : download is running $i . Thread Name ${Thread.currentThread().name}")
        }
        Log.i("MyTag","Long running task finish")
    }
}