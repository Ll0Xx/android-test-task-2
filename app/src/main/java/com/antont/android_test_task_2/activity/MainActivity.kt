package com.antont.android_test_task_2.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.antont.android_test_task_2.adapter.PostsAdapter
import com.antont.android_test_task_2.data.Post
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

        val posts:ArrayList<Post> = arrayListOf()
        val adapter = PostsAdapter(posts)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        viewModel.loading.observe(this) {
            binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
            binding.recyclerView.visibility = if (it) View.GONE else View.VISIBLE
        }

        viewModel.posts.observe(this) {
            val startIndex = posts.size
            posts.addAll(it.posts)
            adapter.notifyItemRangeInserted(startIndex, it.posts.size)
        }

        viewModel.retrievePostsData(1)
    }
}