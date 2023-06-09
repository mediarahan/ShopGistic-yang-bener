package com.example.shopgistic

import android.provider.BaseColumns

object DatabaseContract {

    // Define IsiListTable table name and column names
    object CategoryTable : BaseColumns {
        const val TABLE_NAME = "isi_list"
        const val COLUMN_CATEGORY_ID = "category_id"
        const val COLUMN_TITLE = "title"
        const val COLUMN_LOGO = "logo"
    }

    // Define Category table name and column names
    object ProductTable : BaseColumns {
        const val TABLE_NAME = "category"
        const val COLUMN_PRODUCT_ID = "product_id"
        const val COLUMN_PRODUCT_NAME = "product_name"
        const val COLUMN_PRODUCT_LOGO = "product_logo"
        const val COLUMN_PRODUCT_WEIGHT = "product_weight"
        const val COLUMN_PRODUCT_PRICE = "product_price"
        // Add other columns if needed
    }

    // SQL statement for creating the IsiListTable
    const val SQL_CREATE_CATEGORY_TABLE = """
        CREATE TABLE ${CategoryTable.TABLE_NAME} (
            ${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT,
            ${CategoryTable.COLUMN_TITLE} TEXT,
            ${CategoryTable.COLUMN_LOGO} TEXT,
        )
    """

    // SQL statement for creating the Subcategory table
    const val SQL_CREATE_PRODUCT_TABLE = """
        CREATE TABLE ${ProductTable.TABLE_NAME} (
            ${ProductTable.COLUMN_PRODUCT_ID} INTEGER PRIMARY KEY AUTOINCREMENT,
            ${ProductTable.COLUMN_PRODUCT_LOGO} TEXT,
            ${ProductTable.COLUMN_PRODUCT_NAME} TEXT,
            ${ProductTable.COLUMN_PRODUCT_WEIGHT} REAL,
            ${ProductTable.COLUMN_PRODUCT_PRICE} REAL,
            ${ProductTable.COLUMN_PRODUCT_ID} INTEGER,
            FOREIGN KEY (${ProductTable.COLUMN_PRODUCT_LOGO}) REFERENCES ${CategoryTable.TABLE_NAME} (${CategoryTable.COLUMN_CATEGORY_ID})
                ON DELETE CASCADE
                ON UPDATE CASCADE
        )
    """

    // ... other table definitions and SQL statements ...
}
