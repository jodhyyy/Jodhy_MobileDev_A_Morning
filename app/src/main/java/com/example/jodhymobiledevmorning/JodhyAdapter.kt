package com.example.jodhymobiledevmorning

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class JodhyAdapter(val dataJodhy: List<ResultsItem>): RecyclerView.Adapter<JodhyAdapter.MyViewHolder>() {
    class MyViewHolder (view: View): RecyclerView.ViewHolder(view){
        val imgJodhy = view.findViewById<ImageView>(R.id.item_image_jodhy)
        val nameJodhy = view.findViewById<TextView>(R.id.item_name_jodhy)
        val statusJodhy = view.findViewById<TextView>(R.id.item_status_jodhy)
        val speciesJodhy = view.findViewById<TextView>(R.id.item_species_jodhy)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataJodhy.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.nameJodhy.text = dataJodhy[position].name
        holder.statusJodhy.text = dataJodhy[position].status
        holder.speciesJodhy.text = dataJodhy[position].species

        Glide.with(holder.imgJodhy)
            .load(dataJodhy[position].image)
            .error(R.drawable.ic_launcher_background)
            .into(holder.imgJodhy)

        holder.itemView.setOnClickListener {
            val name = dataJodhy[position].name
            Toast.makeText(holder.itemView.context,"${name}", Toast.LENGTH_SHORT).show()
        }
    }
}
