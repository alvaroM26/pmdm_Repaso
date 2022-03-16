package com.example.pmdm_repaso.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pmdm_repaso.list.ListaNaves
import com.example.pmdm_repaso.list.Naves
import com.google.gson.Gson
import kotlinx.coroutines.*
import okhttp3.*
import java.io.IOException

class ActivityNavesViewModel  : ViewModel() {

    private val _isVisible by lazy { MediatorLiveData<Boolean>() }
    val isVisible: LiveData<Boolean>
        get() = _isVisible

    private val _responseText by lazy { MediatorLiveData<String>() }
    val responseText: LiveData<String>
        get() = _responseText

    private val _responsePelisList by lazy { MediatorLiveData<List<Naves>>() }
    val responsePelisList: LiveData<List<Naves>>
        get() = _responsePelisList

    suspend fun setIsVisibleInMainThread(value: Boolean) = withContext(Dispatchers.Main) {
        _isVisible.value = value
    }


    suspend fun setResponseTextInMainThread(value: String) = withContext(Dispatchers.Main) {
        _responseText.value = value
    }

    suspend fun setResponsePelisListInMainThread(value: List<Naves>) =
        withContext(Dispatchers.Main) {
            _responsePelisList.value = value
        }

    fun descargarNaves(url:String){

        viewModelScope.launch {

            withContext(Dispatchers.IO) {
                setIsVisibleInMainThread(true)

                val client = OkHttpClient()

                val request = Request.Builder()
                request.url(url)
                val call = client.newCall(request.build())
                call.enqueue(object : Callback {
                    override fun onFailure(call: Call, e: IOException) {
                        println(e.toString())
                        CoroutineScope(Dispatchers.Main).launch {
                            delay(2000)
                            setResponseTextInMainThread("Algo ha ido mal")
                            setIsVisibleInMainThread(false)
                        }

                    }

                    override fun onResponse(call: Call, response: Response) {
                        println(response.toString())
                        response.body?.let { responseBody ->
                            val body = responseBody.string()
                            println(body)
                            val gson = Gson()

                            val peliculas = gson.fromJson(body, ListaNaves::class.java)

                            println(peliculas)

                            CoroutineScope(Dispatchers.Main).launch {
                                delay(2000)
                                setIsVisibleInMainThread(false)

                                setResponsePelisListInMainThread(peliculas.results)

                                setResponseTextInMainThread("Hemos obtenido ${peliculas.results.count()} ")
                            }
                        }
                    }
                })
            }
        }
    }

}