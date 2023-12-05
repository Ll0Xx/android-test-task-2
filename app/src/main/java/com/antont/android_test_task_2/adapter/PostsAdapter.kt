package com.antont.android_test_task_2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.antont.android_test_task_2.R
import com.antont.android_test_task_2.data.Post
import com.antont.android_test_task_2.databinding.RecyclerPostsViewBinding
import com.squareup.picasso.Picasso
import java.time.format.DateTimeFormatter


class PostsAdapter(private val posts: List<Post>) :
    RecyclerView.Adapter<PostsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            RecyclerPostsViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            val postInfo = posts[position]
            Picasso.get().load(postInfo.photo).placeholder(R.drawable.baseline_account_circle_24)
                .into(postUserImage)
            postUserName.text = postInfo.userName
            postDate.text =
                postInfo.date?.format(DateTimeFormatter.ofPattern("dd LLL yyyy HH:mm a"))
            postMessage.text = postInfo.message
            postInfo.photo?.let {
                postImage.visibility = View.VISIBLE
                Picasso.get().load(it).placeholder(R.drawable.broken_image).into(postImage)
            }

            expandView.setOnClickListener {
                if (postContent.visibility == View.VISIBLE) {
                    postContent.visibility = View.GONE
                    expandView.setBackgroundResource(R.drawable.expand_more_24px)
                } else {
                    postContent.visibility = View.VISIBLE
                    expandView.setBackgroundResource(R.drawable.expand_less_24px)
                }
            }
        }
    }

    inner class ViewHolder(val binding: RecyclerPostsViewBinding) :
        RecyclerView.ViewHolder(binding.root)
}