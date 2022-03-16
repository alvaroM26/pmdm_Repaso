package com.example.pmdm_repaso.ui

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pmdm_repaso.databinding.RecyclerlistBinding
import com.example.pmdm_repaso.list.Personajes

class RecyclerFiltradoAdapter (var personas: MutableList<Personajes>) :   RecyclerView.Adapter<RecyclerFiltradoAdapter.TextoViewHolder>(){

    private val colorrojo = Color.RED
    private val colorverde = Color.GREEN
    private var naves: String = ""

    fun setpelicula(personaje: String) {
        this.naves = personaje
    }

    class TextoViewHolder(var itemBinding: RecyclerlistBinding) :    RecyclerView.ViewHolder(itemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextoViewHolder {
        val binding =   RecyclerlistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TextoViewHolder(binding)

    }

    override fun onBindViewHolder(holder: TextoViewHolder, pos: Int) {
        if (personas[pos].vehicles.contains(naves))
            holder.itemBinding.name.setTextColor(colorverde)
        else
            holder.itemBinding.name.setTextColor(colorrojo)
        holder.itemBinding.name.text = personas[pos].name

    }

    override fun getItemCount(): Int {
        return personas.size
    }
}