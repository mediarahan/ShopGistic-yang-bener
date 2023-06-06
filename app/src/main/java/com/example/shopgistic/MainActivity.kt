package com.example.shopgistic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import android.content.Intent
import android.net.Uri
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import com.bumptech.glide.Glide

//array kategori produk ada disini

class MainActivity : AppCompatActivity() {

    private val goodsInputLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val data: Intent? = result.data
            val userInput = data?.getStringExtra("userInput")
            val pictureResId = data?.getStringExtra("pictureUri")
            val convertUri = Uri.parse(pictureResId)
            val item = IsiListMainMenu(userInput ?: "", R.drawable.padidankapas)
            mList.add(item)
            adapter.notifyItemInserted(mList.size - 1)
        }
    }

    private lateinit var recyclerView: RecyclerView
    private var mList = ArrayList<IsiListMainMenu>()
    private lateinit var adapter: IsiAdapter<IsiListMainMenu>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Buat Button Ke menu lain
        val buttonClick = findViewById<Button>(R.id.button2)
        buttonClick.setOnClickListener {
            val intent = Intent(this, IsiGoodsActivity::class.java)
            startActivity(intent)
        }

        //Buat Button Ke menu Input
        val clickToInputMenu = findViewById<Button>(R.id.goodsCategoryInputButton)
        clickToInputMenu.setOnClickListener {
            val intent = Intent(this, GoodsInput::class.java)
            goodsInputLauncher.launch(intent)
        }

        recyclerView = findViewById(R.id.recyclerView)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)

        addDataToList()
        adapter = IsiAdapter(mList)
        recyclerView.adapter = adapter

    }

    private fun addDataToList(){
        mList.add(IsiListMainMenu("Beras dan Bahan Pokok", R.drawable.padidankapas))
        mList.add(IsiListMainMenu("Daging dan Ikan", R.drawable.dagingikan))
        mList.add(IsiListMainMenu("Sayuran dan Buah - Buahan", R.drawable.sayurbuah))
        mList.add(IsiListMainMenu("Produk Susu dan Telur", R.drawable.susutelur))
        mList.add(IsiListMainMenu("Makanan Siap Saji", R.drawable.siapsaji))
    }
}