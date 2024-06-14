package org.d3if0145.mobpro_assesment_3.network


import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.d3if0145.mobpro_assesment_3.model.Parfum
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://retoolapi.dev/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface ParfumApiService {
    @GET("B7zTqr/parfum")
    suspend fun getParfum(): List<Parfum>
}

object ParfumApi{
    val service: ParfumApiService by lazy {
        retrofit.create(ParfumApiService::class.java)
    }

    fun getParfumUrl(image: String): String{
        return "${BASE_URL}B7zTqr/$image"
    }
}

enum class ApiStatus {LOADING, SUCCESS, FAILED}