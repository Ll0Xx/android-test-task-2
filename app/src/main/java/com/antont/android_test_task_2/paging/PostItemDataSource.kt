package com.antont.android_test_task_2.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.antont.android_test_task_2.retrofit.RetrofitHelper
import com.antont.android_test_task_2.retrofit.data.Post
import retrofit2.HttpException
import java.io.IOException

class PostItemDataSource(private val callback:PostDataLoaded? = null) : PagingSource<Int, Post>() {
    companion object {
        private const val STARTING_PAGE_INDEX = 1
    }

    override fun getRefreshKey(state: PagingState<Int, Post>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Post> {
        val position = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = RetrofitHelper.retrofitService.getPostPage(position)
            val movies = response.posts
            callback?.dataLoaded()
            LoadResult.Page(
                data = movies,
                prevKey = if (position == STARTING_PAGE_INDEX) null else -1,
                nextKey = if (movies.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}

interface PostDataLoaded{
    fun dataLoaded()
}