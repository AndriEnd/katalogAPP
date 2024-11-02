package com.example.katalogapp

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView


class ListBarangAdapter(private val listBarang: ArrayList<Barang>) : RecyclerView.Adapter<ListBarangAdapter.ListViewHolder>() {
    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (namaBarang, price, description, photo) = listBarang[position]
        holder.imgPhoto.setImageResource(photo)
        holder.tvBarang.text = namaBarang
        holder.tvPrice.text = price.toString()
        holder.tvDescription.text = description

        holder.itemView.setOnClickListener {
            onItemClickCallback?.onItemClicked(listBarang[holder.adapterPosition])
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListBarangAdapter.ListViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_row_barang, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listBarang.size
    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_item)
        val tvBarang: TextView = itemView.findViewById(R.id.tv_name_item)
        val tvPrice: TextView = itemView.findViewById(R.id.tv_price)
        val tvDescription: TextView = itemView.findViewById(R.id.tv_item_description)

    }
    interface OnItemClickCallback {
        fun onItemClicked(data: Barang)
    }
}
