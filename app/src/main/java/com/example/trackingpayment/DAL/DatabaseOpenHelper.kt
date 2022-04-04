package com.example.trackingpayment.DAL

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseOpenHelper (
    context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context, name, factory, version) {
    override fun onCreate(p0: SQLiteDatabase?) {
        val baslik = "Baslik"
        val odemePeriyod = "OdemePeriyod"
        val periyodGun = "PeriyodGun"


        val sql = "CREATE TABLE OdemeTip(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, baslik TEXT, odemePeriyod TEXT,periyodGun INTEGER)"
        p0?.execSQL(sql)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }
}