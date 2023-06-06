package com.example.shopgistic

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class IsiGoodsActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private var mList = ArrayList<IsiListGoodsBeras>()
    private lateinit var adapter: IsiAdapter<IsiListGoodsBeras>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.isi_macammacamgoods)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)

        addDataToList()
        adapter = IsiAdapter(mList)
        recyclerView.adapter = adapter
    }

    private fun addDataToList() {
        mList.add(IsiListGoodsBeras("Beras", R.drawable.padidankapas))
        mList.add(IsiListGoodsBeras("Jagung", R.drawable.padidankapas))
        mList.add(IsiListGoodsBeras("Singkong", R.drawable.padidankapas))
    }
}


