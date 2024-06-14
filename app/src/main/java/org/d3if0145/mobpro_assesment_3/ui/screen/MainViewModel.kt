package org.d3if0145.mobpro_assesment_3.ui.screen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if0145.mobpro_assesment_3.model.Parfum
import org.d3if0145.mobpro_assesment_3.network.ParfumApi

class MainViewModel : ViewModel() {


    var data = mutableStateOf(emptyList<Parfum>())
        private set

    init {
        retrieveData()
    }

    private fun retrieveData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                data.value = ParfumApi.service.getParfum()
            } catch (e: Exception) {
                Log.d("MainViewModel", "Failure: ${e.message}")
            }
        }
    }
}