package com.example.mysubmisiongithub

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainViewModel : ViewModel() {

    private val _userList = MutableLiveData<List<ItemsItem>>()
    val userList: LiveData<List<ItemsItem>> = _userList

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    companion object{
        private const val TAG = "MAINVIEWMODEL"
        private const val QUERY = ""
    }

    init {
        searchUser(QUERY)
    }


     fun searchUser(QUERY: String) {
        _isLoading.value   = true
        val users = ApiConfig.getApiService().getUser(QUERY)
        users.enqueue(object : Callback<GithubResponse> {
            override fun onResponse(
                call: Call<GithubResponse>,
                response: Response<GithubResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful){
                    val userList = response.body()?.items
                    _userList.value = userList
                }else {
                    Log.e(TAG, "Gagal: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<GithubResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG,  "Gagal: ${t.message.toString()}")
            }
        })
    }



}