package com.example.jodhymobiledevmorning.Data

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.jodhymobiledevmorning.R
import com.example.jodhymobiledevmorning.UpdateData

class DataAdapter (private var data: List<Data>, context: Context) : RecyclerView.Adapter<DataAdapter.DataViewHolder>() {

    private val db: DataDatabaseHelper = DataDatabaseHelper(context)

    inner class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val contentTextView: TextView = itemView.findViewById(R.id.contentTextView)
        val updateButton: ImageView = itemView.findViewById(R.id.updateButton)
        val deleteButton: ImageView = itemView.findViewById(R.id.deleteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.data_item, parent, false)
        return DataViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val data = data[position]
        holder.titleTextView.text = data.title
        holder.contentTextView.text = data.content
        holder.updateButton.setOnClickListener {
            val intent = Intent(holder.itemView.context, UpdateData::class.java).apply {
                putExtra("data_id", data.id)
            }
            holder.itemView.context.startActivity(intent)
        }
        holder.deleteButton.setOnClickListener {
            db.deleteData(data.id)
            refreshData(db.getAllData())
            Toast.makeText(holder.itemView.context,"Data Delete", Toast.LENGTH_SHORT).show()
        }
    }
    fun refreshData(newData: List<Data>){
        data = newData
        notifyDataSetChanged()
    }
}