package com.example.jodhymobiledevmorning.Data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataDatabaseHelper (context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "dataapp.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "allData"
        private const val COLUMN_ID = "id"
        private const val COLUMN_TITLE = "title"
        private const val COLUMN_CONTENT = "content"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = "CREATE TABLE $TABLE_NAME ($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COLUMN_TITLE TEXT, $COLUMN_CONTENT TEXT)"
        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val dropTableQuery = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(dropTableQuery)
        onCreate(db)
    }
    fun insertData(data: Data){
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_TITLE, data.title)
            put(COLUMN_CONTENT, data.content)
        }
        db.insert(TABLE_NAME, null, values)
        db.close()
    }
    fun getAllData(): List<Data> {
        val dataList = mutableListOf<Data>()
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME"
        val cursor = db.rawQuery(query, null)

        while (cursor.moveToNext()){
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
            val title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE))
            val content = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CONTENT))

            val data = Data(id, title, content)
            dataList.add(data)
        }
        cursor.close()
        db.close()
        return dataList
    }
    fun updateData(data: Data){
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_TITLE, data.title)
            put(COLUMN_CONTENT, data.content)
        }
        val whereClause = "$COLUMN_ID = ?"
        val whereArgs = arrayOf(data.id.toString())
        db.update(TABLE_NAME, values, whereClause, whereArgs)
        db.close()
    }
    fun getDataByID(dataId: Int): Data {
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME WHERE $COLUMN_ID = $dataId"
        val cursor = db.rawQuery(query, null)
        cursor.moveToFirst()

        val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
        val title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE))
        val content = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CONTENT))

        cursor.close()
        db.close()
        return Data(id, title, content)
    }
    fun deleteData(dataId: Int){
        val db = writableDatabase
        val whereClause = "$COLUMN_ID = ?"
        val whereArgs = arrayOf(dataId.toString())
        db.delete(TABLE_NAME, whereClause, whereArgs)
        db.close()
    }
}