package com.example.pmdm_repaso.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pmdm_repaso.databinding.ActivityNavesBinding
import com.example.pmdm_repaso.list.Naves
import com.example.pmdm_repaso.viewmodel.ActivityNavesViewModel

var data: String? = ""

class NavesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNavesBinding
    private val viewModel: ActivityNavesViewModel by viewModels()
    private var lista: MutableList<Naves>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val url="https://swapi.dev/api/vehicles/"
        binding = ActivityNavesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        data = intent.getStringExtra("personaje")
        println(data)
        viewModel.descargarNaves(url)
        initObserver()
    }

    private fun initObserver() {
        viewModel.isVisible.observe(this) { isVisible ->
            if (isVisible) setVisible() else setGone()
        }

        viewModel.responseText.observe(this) { responseText ->
            showToast(responseText)
        }

        viewModel.responsePelisList.observe(this) {
            setRecycler(it as MutableList<Naves>)
        }
    }

    private fun setVisible() {
        binding.pbDownloading.visibility = View.VISIBLE
    }

    private fun setGone() {
        binding.pbDownloading.visibility = View.GONE
    }

    private fun showToast(text: String) {
        Toast.makeText(this@NavesActivity, text, Toast.LENGTH_SHORT).show()

    }

    private fun setRecycler(lista: MutableList<Naves>) {

        val adapter = RecyclerNavesAdapter(lista)
        adapter.setpersonaje(data!!)

        if (this.lista == null)
            this.lista = lista
        binding.recycler.layoutManager = LinearLayoutManager(this@NavesActivity)
        binding.recycler.adapter = adapter
    }

}