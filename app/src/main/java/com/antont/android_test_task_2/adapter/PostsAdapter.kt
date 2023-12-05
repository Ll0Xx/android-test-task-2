package com.antont.android_test_task_2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.antont.android_test_task_2.R
import com.antont.android_test_task_2.retrofit.data.Post
import com.antont.android_test_task_2.databinding.RecyclerPostsViewBinding
import com.squareup.picasso.Picasso
import java.time.format.DateTimeFormatter

class PostsAdapter :
    PagingDataAdapter<Post, PostsAdapter.PostViewHolder>(PassengerListDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding =
            RecyclerPostsViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val item: Post? = getItem(position)
        item?.let {
            holder.bind(it)
        }
    }

    override fun onBindViewHolder(
        holder: PostViewHolder, position: Int, payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            holder.bind(payloads[0] as Post)
        }
    }

    inner class PostViewHolder(private val binding: RecyclerPostsViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(post: Post) {
            binding.apply {
                Picasso.get().load(post.photo).placeholder(R.drawable.baseline_account_circle_24)
                    .into(postUserImage)
                postUserName.text = post.userName
                postDate.text =
                    post.date?.format(DateTimeFormatter.ofPattern("dd LLL yyyy HH:mm a"))
                postMessage.text = post.message
                post.photo?.let {
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
    }
}

class PassengerListDiffCallback : DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Post, newItem: Post) = oldItem == newItem

    override fun getChangePayload(oldItem: Post, newItem: Post): Any? {
        if (oldItem != newItem) {
            return newItem
        }
        return super.getChangePayload(oldItem, newItem)
    }
}