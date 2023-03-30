package com.example.mysubmisiongithub

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel: ViewModel() {

    private val _detailUser = MutableLiveData<DetailUserResponse>()
    val detailUser : LiveData<DetailUserResponse> = _detailUser

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    private val _followers = MutableLiveData<List<ItemsItem>>()
    val followers : LiveData<List<ItemsItem>> = _followers

    private val _following = MutableLiveData<List<ItemsItem>>()
    val following : LiveData<List<ItemsItem>>  = _following

    init {
        detailUserRespone()
    }

    companion object{
        private const val TAG = "DETAILVIEWMODEL"

    }

     fun detailUserRespone(username: String = "") {
        _isLoading.value = true
        val detail = ApiConfig.getApiService().getDetailUser(username)
        detail.enqueue(object : Callback<DetailUserResponse>{
            override fun onResponse(
                call: Call<DetailUserResponse>,
                response: Response<DetailUserResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful){
                    _detailUser.value = response.body()
                }else{
                    Log.e(TAG, "Gagal: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<DetailUserResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG ,"Gagal ${t.message}")
            }
        })
    }

    fun getFollowers(username: String = ""){
        _isLoading.value = true
        val client = ApiConfig.getApiService().getFollowers(username)
        client.enqueue(object : Callback<List<ItemsItem>>{
            override fun onResponse(
                call: Call<List<ItemsItem>>,
                response: Response<List<ItemsItem>>
            ) {
                _isLoading.value = false
                if (response.isSuccessful){
                    _followers.value = response.body()
                    Log.e(TAG, "onResponse: ${response.body()} folowerssssssss")
                }else{
                    Log.e(TAG, "Gagal : ${response.message()} folowerssssssss")
                }
            }

            override fun onFailure(call: Call<List<ItemsItem>>, t: Throwable) {
                _isLoading.value= false
                Log.e(TAG, "Gagal : ${t.message.toString()} followers")
            }

        })
    }

    fun getFollowing(username: String = ""){
        _isLoading.value = true
        val client = ApiConfig.getApiService().getFollowing(username)
        client.enqueue(object : Callback<List<ItemsItem>> {
            override fun onResponse(
                call: Call<List<ItemsItem>>,
                response: Response<List<ItemsItem>>
            ) {
                _isLoading.value = false
                if (response.isSuccessful){
                    _following.value = response.body()
                    Log.d(TAG, "onResponse: ${response.body()}following")
                }else {
                    Log.e(TAG, "GAGAL ${response.message()} folowing")
                }
            }

            override fun onFailure(call: Call<List<ItemsItem>>, t: Throwable) {
                Log.e(TAG, "GAGAL ${t.message.toString()} following")
            }

        })
    }
}