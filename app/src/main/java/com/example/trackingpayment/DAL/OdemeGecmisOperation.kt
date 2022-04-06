package com.example.trackingpayment.DAL

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.trackingpayment.MODELS.OdemeGecmis

class OdemeGecmisOperation (context: Context)  {
    var dbOpenHelper : DatabaseOpenHelper
    var odemeGecmisDatabase : SQLiteDatabase? = null
    init {
        dbOpenHelper= DatabaseOpenHelper(context,"OdemeTipDb",null,1)// hata verse db ismi değiştir1
    }

    fun open()
    {
        odemeGecmisDatabase = dbOpenHelper.writableDatabase
    }

    fun close()
    {
        if (odemeGecmisDatabase != null && odemeGecmisDatabase!!.isOpen)
        {
            odemeGecmisDatabase!!.close()
        }
    }

    fun addOdemeGecmis(odemeGecmis : OdemeGecmis)
    {
        val cv = ContentValues()
       addupdateCode(odemeGecmis,cv)
        odemeGecmisDatabase!!.insert("OdemeGecmis",null,cv)
        close()
    }

    fun updateOdemeGecmis(odemeGecmis : OdemeGecmis)
    {
        val cv = ContentValues()
        addupdateCode(odemeGecmis,cv)
        odemeGecmisDatabase!!.update("OdemeGecmis",cv,"id = ?", arrayOf(odemeGecmis.id.toString()))
        close()
    }

    fun deleteOdemeGecmis(id : Int)
    {
        open()
        odemeGecmisDatabase!!.delete("OdemeGecmis","id = ?", arrayOf(id.toString()))

        close()

    }


    @SuppressLint("Range")
    fun allOdemeGecmis() : ArrayList<OdemeGecmis>
    {
        val odemeGecmisList = ArrayList<OdemeGecmis>()

        open()
        val sql = "Select * from OdemeGecmis"
        val c = odemeGecmisDatabase!!.rawQuery(sql,null)

        if (c.moveToFirst())
        {
            var odemeGecmis : OdemeGecmis
            do{
                odemeGecmis = OdemeGecmis()
                odemeGecmis.id = c.getColumnIndex("id")
                odemeGecmis.odemeTarih = c.getString(c.getColumnIndex("odemeTarih"))
                odemeGecmis.odemeTutar = c.getInt(c.getColumnIndex("odemeTutar"))
                odemeGecmisList.add(odemeGecmis)

            }while (c.moveToNext())
        }
        close()
        return odemeGecmisList
    }


    @SuppressLint("Range")
    fun bringOdemeGecmis(id : Int) : OdemeGecmis?
    {

        var odemeGecmis : OdemeGecmis? = null
        open()
        val sql = "Select * from OdemeGecmis where id = ?"
        val c = odemeGecmisDatabase!!.rawQuery(sql, arrayOf(id.toString()))

        if (c.moveToFirst())
        {
            odemeGecmis = OdemeGecmis()
            odemeGecmis.id = c.getColumnIndex("id")
            odemeGecmis.odemeTarih = c.getString(c.getColumnIndex("odemeTarih"))
            odemeGecmis.odemeTutar = c.getInt(c.getColumnIndex("odemeTutar"))
        }
        close()
        return odemeGecmis
    }

    fun addupdateCode(odemeGecmis: OdemeGecmis,cv : ContentValues)
    {
        cv.put("odemeTarih",odemeGecmis.odemeTarih)
        cv.put("odemeTutar",odemeGecmis.odemeTutar)

        open()
    }
}