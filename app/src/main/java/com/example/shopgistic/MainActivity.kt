package com.example.shopgistic

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.net.Uri
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity(), IsiAdapter.OnItemClickListener {
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var dbRead: SQLiteDatabase

    private lateinit var recyclerView: RecyclerView
    private val mList = ArrayList<IsiListMainMenu>()
    private lateinit var adapter: IsiAdapter

    private val goodsInputLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val data: Intent? = result.data
            val userInput = data?.getStringExtra("userInput")
            val pictureUri = data?.getStringExtra("pictureUri") ?: ""

            dbRead.beginTransaction()
            try {
                val values = ContentValues().apply {
                    put(DatabaseContract.CategoryTable.COLUMN_TITLE, userInput ?: "")
                    put(DatabaseContract.CategoryTable.COLUMN_LOGO, pictureUri)
                }

                val rowId = dbRead.insert(DatabaseContract.CategoryTable.TABLE_NAME, null, values)
                Log.d("Database", "Inserted row ID: $rowId")

                // Set the transaction as successful
                dbRead.setTransactionSuccessful()
            } finally {
                // End the transaction
                dbRead.endTransaction()
            }

            addDataToList() // Refresh the list after inserting new data
            adapter.notifyItemInserted(mList.size - 1)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHelper = DatabaseHelper(applicationContext)
        dbRead = dbHelper.readableDatabase

        val clickToInputMenu = findViewById<FloatingActionButton>(R.id.addCategory)
        clickToInputMenu.setOnClickListener {
            val intent = Intent(this, GoodsCategoryInput::class.java)
            goodsInputLauncher.launch(intent)
        }

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)

        addDataToList()
        adapter = IsiAdapter(mList, this)
        recyclerView.adapter = adapter
    }

    override fun onItemClick(item: IsiListMainMenu) {
        val intent = Intent(this, IsiGoodsActivity::class.java)
        intent.putExtra("categoryId", item.categoryId)
        startActivity(intent)
    }

    private fun addDataToList() {
        mList.clear()

        dbRead.beginTransaction()
        try {
            val projection = arrayOf(
                DatabaseContract.CategoryTable.COLUMN_TITLE,
                DatabaseContract.CategoryTable.COLUMN_LOGO
            )

            val cursor = dbRead.query(
                DatabaseContract.CategoryTable.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
            )

            while (cursor.moveToNext()) {
                val title = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.CategoryTable.COLUMN_TITLE))
                val logoUriString = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.CategoryTable.COLUMN_LOGO))
                val logoUri = Uri.parse(logoUriString)

                val item = IsiListMainMenu(title, logoUri)
                mList.add(item)
            }

            cursor.close()

            // Set the transaction as successful
            dbRead.setTransactionSuccessful()
        } finally {
            // End the transaction
            dbRead.endTransaction()
        }
    }
}