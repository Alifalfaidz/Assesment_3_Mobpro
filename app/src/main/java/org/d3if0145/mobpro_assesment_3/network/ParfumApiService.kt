package org.d3if0145.mobpro_assesment_3.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://restapiparfum-default-rtdb.firebaseio.com/Parfum"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface ParfumApiService {
    @GET("static-api.json")
    suspend fun getParfum(): String
}

object ParfumApi{
    val service: ParfumApiService by lazy {
        retrofit.create(ParfumApiService::class.java)
    }
}