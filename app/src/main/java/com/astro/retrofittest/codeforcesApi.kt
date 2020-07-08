package com.astro.retrofittest

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*
import kotlin.collections.HashMap


//https://codeforces.com/api/user.info?handles=DmitriyH;Fefer_Ivan
const val BASE_URL = "https://codeforces.com/api/"

interface codeforcesApi{

    @GET("user.info?")
    fun getUser(@Query("handles") handlesSearch: String): Call<List<codeforcesClass>>
    //@Query("srsearch") srsearch: String

    /*companion object {
        fun create(): codeforcesApi {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(
                    GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(codeforcesApi::class.java)
        }
    }*/

}