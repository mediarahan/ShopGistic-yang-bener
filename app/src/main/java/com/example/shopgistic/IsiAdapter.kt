package com.example.shopgistic

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

private const val VIEW_TYPE_MAIN_MENU = 0
private const val VIEW_TYPE_GOODS_BERAS = 1


class IsiAdapter<T> (var mList: List<T>) :
    RecyclerView.Adapter<IsiAdapter<T>.IsiListViewHolder>() {

    inner class IsiListViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val logo : ImageView = itemView.findViewById(R.id.logoIv)
        val titleTv : TextView = itemView.findViewById(R.id.titleTv)
    }

    override fun getItemViewType(position: Int): Int {
        return when (mList[position]) {
            is IsiListMainMenu -> VIEW_TYPE_MAIN_MENU
            is IsiListGoodsBeras -> VIEW_TYPE_GOODS_BERAS
            else -> throw IllegalArgumentException("Invalid item type at position $position")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IsiListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = when (viewType) {
            VIEW_TYPE_MAIN_MENU -> inflater.inflate(R.layout.isi_kategorigoods, parent, false)
            VIEW_TYPE_GOODS_BERAS -> inflater.inflate(R.layout.macammacamgoods, parent, false)
            else -> throw IllegalArgumentException("Invalid view type: $viewType")
        }
        return IsiListViewHolder(itemView)
    }


    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: IsiListViewHolder, position: Int) {
        when(val item = mList[position]) {
            is IsiListMainMenu -> {
                holder.logo.setImageResource(item.logo)
                holder.titleTv.text = item.title
            }
            is IsiListGoodsBeras -> {
                holder.logo.setImageResource(item.logo)
                holder.titleTv.text = item.title
            }
        }
    }

}