package com.antont.android_test_task_2.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.antont.android_test_task_2.RetrofitHelper
import com.antont.android_test_task_2.data.PostsPage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(private val application: Application) : AndroidViewModel(application) {

    val posts: MutableLiveData<PostsPage> = MutableLiveData<PostsPage>()
    val loading: MutableLiveData<Boolean> = MutableLiveData(true)
    val errorMessage = MutableLiveData<String>()

    fun retrievePostsData(pageNum: Int): MutableLiveData<PostsPage> {
        loading.postValue(true)
        CoroutineScope(Dispatchers.Default).launch {
            launch(Dispatchers.IO) {
                val response = RetrofitHelper.retrofitService.getPostPage(pageNum)
                response.enqueue(object : Callback<PostsPage> {
                    override fun onResponse(
                        call: Call<PostsPage>,
                        response: Response<PostsPage>
                    ) {
                        loading.postValue(false)
                        if (response.isSuccessful) {
                            posts.postValue(response.body())
                        } else {
                            errorMessage.postValue("Error : ${response.message()} ")
                        }
                    }

                    override fun onFailure(call: Call<PostsPage>, t: Throwable) {
                        loading.postValue(false)
                        errorMessage.postValue("Error : ${t.message} ")
                    }
                })
            }
        }

        return posts
    }
}

class MainViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(application) as T
    }
}