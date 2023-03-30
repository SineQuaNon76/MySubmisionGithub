package com.example.mysubmisiongithub

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("search/users")
    @Headers("Authorization: token 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")
    fun getUser(@Query("q") query: String): Call<GithubResponse>


    @GET("users/{username}")
    @Headers("Authorization: token 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")
    fun getDetailUser(@Path("username") username: String): Call<DetailUserResponse>

    @GET("users/{username}/followers")
    @Headers("Authorization: token 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")
    fun getFollowers(@Path("username") username: String): Call<List<ItemsItem>>

    @GET("users/{username}/following")
    @Headers("Authorization: token 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")
    fun getFollowing(@Path("username") username: String): Call<List<ItemsItem>>

}