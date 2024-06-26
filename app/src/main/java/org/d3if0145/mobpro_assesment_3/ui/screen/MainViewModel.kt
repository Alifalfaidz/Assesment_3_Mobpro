package org.d3if0145.mobpro_assesment_3.ui.screen

import android.graphics.Bitmap
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.d3if0145.mobpro_assesment_3.model.Parfum
import org.d3if0145.mobpro_assesment_3.network.ApiStatus
import org.d3if0145.mobpro_assesment_3.network.ParfumApi
import java.io.ByteArrayOutputStream

class MainViewModel : ViewModel() {

    private val _postResponse = MutableLiveData<Parfum>()
    val postResponse: LiveData<Parfum> = _postResponse

    var data = mutableStateOf(emptyList<Parfum>())
        private set

    var status = MutableStateFlow(ApiStatus.LOADING)
        private set


    fun retrieveData(useremail: String) {
        viewModelScope.launch(Dispatchers.IO) {
            status.value = ApiStatus.LOADING
            try {
                val parfums = ParfumApi.service.getParfum(useremail)
                data.value = parfums
                status.value = ApiStatus.SUCCESS
            } catch (e: Exception) {
                Log.d("MainViewModel", "Failure: ${e.message}")
                status.value = ApiStatus.FAILED
            }
        }
    }
    fun saveData(parfum: Parfum) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = ParfumApi.service.postparfum(parfum)
                data.value = data.value + response
                _postResponse.postValue(response)
                Log.d("MainViewModel", "Success")
            } catch (e: Exception) {
                Log.e("MainViewModel", "Failure ${e.message}")
            }
        }
    }

    suspend fun deleteImage(userId: String, id: String) {
        try {
            val result = ParfumApi.service.deleteparfum(id)
            data.value = data.value.filter { it.id != id }
            Log.d("MainViewModel", "Berhasil menghapus gambar")
            retrieveData(userId)
        } catch (e: Exception) {
            Log.d("MainViewModel", "Gagal Hapus gambar: ${e.message}")
        }
    }

}