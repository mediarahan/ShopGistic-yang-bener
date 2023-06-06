package com.example.shopgistic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import android.content.Intent
import android.net.Uri
import androidx.activity.result.contract.ActivityResultContracts

//array kategori produk ada disini

class MainActivity : AppCompatActivity() {

    private val goodsInputLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val data: Intent? = result.data
            val userInput = data?.getStringExtra("userInput")
            val pictureUri = data?.getStringExtra("pictureUri")
            val selectedImageUri = Uri.parse(pictureUri)
            val item = IsiListMainMenu(userInput ?: "", selectedImageUri)
            mList.add(item)
            adapter.notifyItemInserted(mList.size - 1)
        }
    }

    private lateinit var recyclerView: RecyclerView
    private var mList = ArrayList<IsiListMainMenu>()
    private lateinit var adapter: IsiAdapter

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

    }
}