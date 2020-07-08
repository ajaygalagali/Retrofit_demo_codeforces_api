package com.astro.retrofittest.data_cf

import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

//https://codeforces.com/api/user.info?handles=DmitriyH;Fefer_Ivan
interface cf_api {
    @GET("user.info")
    fun  getUserinfo(@Query("handles")userName:String
    ): Call<cf_data>

    companion object{
        operator fun invoke() : cf_api{
            return Retrofit.Builder()
//                .client(okhttp3.OkHttpClient())
                .baseUrl("https://codeforces.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(cf_api::class.java)
        }
    }


}