package com.antont.android_test_task_2.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.antont.android_test_task_2.paging.PostDataLoaded
import com.antont.android_test_task_2.retrofit.data.Post
import com.antont.android_test_task_2.paging.PostItemDataSource
import kotlinx.coroutines.flow.Flow


class MainViewModel : ViewModel() {
    val loading: MutableLiveData<Boolean> = MutableLiveData(true)
    fun getMovieList(): Flow<PagingData<Post>> {
        loading.postValue(true)
        return Pager(
            config = PagingConfig(
                pageSize = 50, enablePlaceholders = false, initialLoadSize = 50
            ), pagingSourceFactory = {
                PostItemDataSource(object : PostDataLoaded {
                    override fun dataLoaded() {
                        loading.postValue(false)
                    }
                })
            }, initialKey = 1
        ).flow.cachedIn(viewModelScope)
    }
}