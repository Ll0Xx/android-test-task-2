package com.antont.android_test_task_2.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.antont.android_test_task_2.data.Post
import com.antont.android_test_task_2.databinding.RecyclerPostsViewBinding

class PostsAdapter(private val posts: List<Post>) : RecyclerView.Adapter<PostsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            RecyclerPostsViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    inner class ViewHolder(private val binding: RecyclerPostsViewBinding) : RecyclerView.ViewHolder(binding.root)
}