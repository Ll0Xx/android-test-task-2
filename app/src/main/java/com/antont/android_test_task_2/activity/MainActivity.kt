package com.antont.android_test_task_2.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.antont.android_test_task_2.databinding.ActivityMainBinding
import com.antont.android_test_task_2.viewmodel.MainViewModel
import com.antont.android_test_task_2.viewmodel.MainViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: MainViewModel =
            ViewModelProvider(this, MainViewModelFactory(application))[MainViewModel::class.java]
    }
}