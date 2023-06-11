package com.example.shopgistic

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class IsiDetailProdukAdapter(val mList: ArrayList<IsiListDetailProduk>) :
    RecyclerView.Adapter<IsiDetailProdukAdapter.IsiListViewHolder>() {

    inner class IsiListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val logo: ImageView = itemView.findViewById(R.id.logoIv)
        val titleTv: TextView = itemView.findViewById(R.id.titleTv)
        //val priceTextView: TextView = itemView.findViewById(R.id.editTextPrice)
        //val weightTextView: TextView = itemView.findViewById(R.id.editTextWeight)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IsiListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.isi_macammacamgoods,parent,false)

        return IsiListViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: IsiListViewHolder, position: Int) {
        val item = mList[position]
        holder.titleTv.text = item.title
        //TAMBAHIN KODINGAN YANG BUAT WEIGHT SAMA PRICE DISINI NANTI

        // Load the logo image using Glide or any other image loading library
        Glide.with(holder.itemView)
            .load(item.logo)
            .into(holder.logo)
    }

}
