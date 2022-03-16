package com.example.pmdm_repaso.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pmdm_repaso.list.Personajes
import com.example.pmdm_repaso.ui.listapersonajes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ActivityPersonajesFiltradoViewModel : ViewModel() {

    private val _isVisible by lazy { MediatorLiveData<Boolean>() }
    val isVisible: LiveData<Boolean>
        get() = _isVisible

    private val _responseText by lazy { MediatorLiveData<String>() }
    val responseText: LiveData<String>
        get() = _responseText

    private val _responseList by lazy { MediatorLiveData<List<Personajes>>() }
    val responseList: LiveData<List<Personajes>>
        get() = _responseList

    suspend fun setIsVisibleInMainThread(value: Boolean) = withContext(Dispatchers.Main) {
        _isVisible.value = value
    }

    suspend fun setResponseTextInMainThread(value: String) = withContext(Dispatchers.Main) {
        _responseText.value = value
    }

    suspend fun setResponseListInMainThread(value: List<Personajes>) = withContext(Dispatchers.Main) {
        _responseList.value = value
    }

    fun  descargarPersonajes(){

        viewModelScope.launch {

            withContext(Dispatchers.IO) {
                setIsVisibleInMainThread(true)
                setResponseListInMainThread(listapersonajes!!)
                setResponseTextInMainThread("Hay "+responseList.value!!.size )
                setIsVisibleInMainThread(false)
            }
        }

    }

}