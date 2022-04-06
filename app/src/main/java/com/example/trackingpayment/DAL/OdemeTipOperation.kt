package com.example.trackingpayment.DAL

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.trackingpayment.MODELS.OdemeTip

class OdemeTipOperation (context: Context) {
    var dbOpenHelper : DatabaseOpenHelper
    var odemeTipDatabase : SQLiteDatabase? = null

    init {
        dbOpenHelper= DatabaseOpenHelper(context,"OdemeTipDb",null,1)
    }



    fun open()
    {
        odemeTipDatabase = dbOpenHelper.writableDatabase
    }

    fun close()
    {
        if (odemeTipDatabase != null && odemeTipDatabase!!.isOpen)
        {
            odemeTipDatabase!!.close()
        }
    }

    fun addOdemeTip(odemeTip : OdemeTip)
    {
        val cv = ContentValues()
        addupdateCode(odemeTip,cv)
        odemeTipDatabase!!.insert("OdemeTip",null,cv)
        close()

    }

    fun updateOdemeTip(odemeTip : OdemeTip)
    {
        val cv = ContentValues()
       addupdateCode(odemeTip,cv)
        odemeTipDatabase!!.update("OdemeTip",cv,"id = ?", arrayOf(odemeTip.id.toString()))
        close()
    }

    fun deleteOdemeTip(id : Int)
    {
        open()
        odemeTipDatabase!!.delete("OdemeTip","id = ?", arrayOf(id.toString()))

        close()

    }

/*
    private fun fetcAllOdemeTip() : Cursor
    {
        val sorgu = "Select * from OdemeTip"

        return odemeTipDatabase!!.rawQuery(sorgu,null)
    }*/

     @SuppressLint("Range")
     fun allOdemeTip() : ArrayList<OdemeTip>
    {
        val odemeTipList = ArrayList<OdemeTip>()

        open()
        val sql = "Select * from OdemeTip"
        val c = odemeTipDatabase!!.rawQuery(sql,null)

        if (c.moveToFirst())
        {
            var odemeTip : OdemeTip
            do{
                odemeTip = OdemeTip()
                odemeTip.id = c.getColumnIndex("id")
                odemeTip.baslik = c.getString(c.getColumnIndex("baslik"))
                odemeTip.odemePeriyod = c.getString(c.getColumnIndex("odemePeriyod"))
                odemeTip.periyodGun = c.getInt(c.getColumnIndex("periyodGun"))
                odemeTipList.add(odemeTip)
            }while (c.moveToNext())


        }
        close()
        return odemeTipList
    }

    @SuppressLint("Range")
    fun bringOdemeTip(id: Int) : OdemeTip? {
        var odemeTip: OdemeTip? = null
        open()
        val sql = "Select * from OdemeTip where id = ?"
        val c = odemeTipDatabase!!.rawQuery(sql, arrayOf(id.toString()))

        if (c.moveToFirst()) {
            odemeTip = OdemeTip()
            odemeTip.id = c.getColumnIndex("id")
            odemeTip.baslik = c.getString(c.getColumnIndex("baslik"))
            odemeTip.odemePeriyod = c.getString(c.getColumnIndex("odemePeriyod"))
            odemeTip.periyodGun = c.getInt(c.getColumnIndex("periyodGun"))
        }
        close()

        return odemeTip

    }

    fun addupdateCode(odemeTip : OdemeTip,cv : ContentValues)
    {
        cv.put("Baslik",odemeTip.baslik)
        cv.put("OdemePeriyod",odemeTip.odemePeriyod)
        cv.put("PeriyodGun",odemeTip.periyodGun)
        open()
    }





}