package com.example.pmdm_repaso.ui

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pmdm_repaso.databinding.RecyclerlistBinding
import com.example.pmdm_repaso.list.Naves

class RecyclerNavesAdapter (var pelis: MutableList<Naves>) : RecyclerView.Adapter<RecyclerNavesAdapter.TextoViewHolder>() {

    private val colorrojo = Color.RED
    private val colorverde = Color.GREEN
    private var personaje: String = ""

    fun setpersonaje(personaje: String) {
        this.personaje = personaje
    }

    class TextoViewHolder(var itemBinding: RecyclerlistBinding) :    RecyclerView.ViewHolder(itemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextoViewHolder {
        val binding =  RecyclerlistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TextoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TextoViewHolder, pos: Int) {

        holder.itemBinding.name.text = pelis[pos].name
        if (pelis[pos].pilots.contains(personaje))
            holder.itemBinding.name.setTextColor(colorverde)
        else
            holder.itemBinding.name.setTextColor(colorrojo)

        holder.itemBinding.all.setOnClickListener {

            val intent =    Intent(holder.itemBinding.root.context, PersonajesFiltradosActivity::class.java)
            intent.putExtra("pelicula", pelis[pos].url)
            holder.itemBinding.root.context.startActivity(intent)
       }

    }

    override fun getItemCount(): Int {
        return pelis.size
    }

}