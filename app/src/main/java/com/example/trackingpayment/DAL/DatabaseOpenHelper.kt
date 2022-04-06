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
    override fun onCreate(db: SQLiteDatabase?) {
        val baslik = "Baslik"
        val odemePeriyod = "OdemePeriyod"
        val periyodGun = "PeriyodGun"
        val odemeTarih = "odemeTarih"
        val odemeTutar = "odemeTutar"

        val sql = "CREATE TABLE OdemeTip(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, baslik TEXT, odemePeriyod TEXT,periyodGun INTEGER,FOREIGN KEY(id) REFERENCES OdemeGecmis(id))"
        val sqlodeme = "CREATE TABLE OdemeGecmis(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,odemeTarih TEXT,odemeTutar INTEGER)"
        db?.execSQL(sql)
        db?.execSQL(sqlodeme)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS OdemeTip")
        db!!.execSQL("DROP TABLE IF EXISTS OdemeGecmis")
        onCreate(db)

    }
}