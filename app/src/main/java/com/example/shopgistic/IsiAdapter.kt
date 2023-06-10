package com.example.shopgistic

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class IsiAdapter(val mList: ArrayList<IsiListMainMenu>, private val itemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<IsiAdapter.IsiListViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(item: IsiListMainMenu)
    }

    inner class IsiListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val logo: ImageView = itemView.findViewById(R.id.logoIv)
        val titleTv: TextView = itemView.findViewById(R.id.titleTv)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                val clickedItem = mList[position]
                itemClickListener.onItemClick(clickedItem)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IsiListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.isi_kategorigoods, parent, false)

        return IsiListViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: IsiListViewHolder, position: Int) {
        val item = mList[position]
        holder.titleTv.text = item.title

        Glide.with(holder.itemView)
            .load(item.logo)
            .into(holder.logo)
    }
}
