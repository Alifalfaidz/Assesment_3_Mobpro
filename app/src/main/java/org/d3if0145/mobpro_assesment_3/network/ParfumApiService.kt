package org.d3if0145.mobpro_assesment_3.network


import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.d3if0145.mobpro_assesment_3.model.OpStatus
import org.d3if0145.mobpro_assesment_3.model.Parfum
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

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
    suspend fun getParfum(@Header("Authorization") useremail: String): List<Parfum>



    @POST("B7zTqr/parfum")
    suspend fun postparfum(@Body parfum: Parfum): Parfum

    @DELETE("B7zTqr/parfum/{id}")
    suspend fun deleteparfum(@Path("id") id:String )
}


object ParfumApi{
    val service: ParfumApiService by lazy {
        retrofit.create(ParfumApiService::class.java)
    }


    fun getParfumUrl(imageId: String): Bitmap?{
        return try {
            val decodedString = Base64.decode(imageId, Base64.DEFAULT)
            BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}

enum class ApiStatus {LOADING, SUCCESS, FAILED}