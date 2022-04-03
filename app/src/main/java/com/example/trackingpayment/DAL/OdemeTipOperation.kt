package com.example.trackingpayment.DAL

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.trackingpayment.MODELS.OdemeTip

class OdemeTipOperation (context: Context) {
    var dbOpenHelper : DatabaseOpenHelper
    var OdemeTipDatabase : SQLiteDatabase? = null

    init {
        dbOpenHelper= DatabaseOpenHelper(context,"OdemeTipDb",null,1)
    }



    fun open()
    {
        OdemeTipDatabase = dbOpenHelper.writableDatabase
    }

    fun close()
    {
        if (OdemeTipDatabase != null && OdemeTipDatabase!!.isOpen)
        {
            OdemeTipDatabase!!.close()
        }
    }

    fun addOdemeTip(odemeTip : OdemeTip) : Long
    {
        val cv = ContentValues()
        cv.put("Baslik",odemeTip.Baslik)
        cv.put("OdemePeriyod",odemeTip.OdemePeriyod)
        cv.put("PeriyodGun",odemeTip.PeriyodGun)

        open()
        val etkilenenKayit = OdemeTipDatabase!!.insert("OdemeTip",null,cv)
        close()
        return etkilenenKayit
    }

    fun updateOdemeTip(odemeTip : OdemeTip)
    {
        val cv = ContentValues()
        cv.put("Baslik",odemeTip.Baslik)
        cv.put("OdemePeriyod",odemeTip.OdemePeriyod)
        cv.put("PeriyodGun",odemeTip.PeriyodGun)

        open()
        OdemeTipDatabase!!.update("Yapilacak",cv, "Id = ?",
            arrayOf(arrayOf(odemeTip.Id).toString()))
        close()
    }

    fun deleteOdemeTip(Id : Int)
    {
        open()
        OdemeTipDatabase!!.delete("OdemeTip","Id = ?",arrayOf(Id.toString()))

    }


    private fun fetcAllOdemeTip() : Cursor
    {
        val sorgu = "Select * from OdemeTip"

        return OdemeTipDatabase!!.rawQuery(sorgu,null)
    }

    // hata verse başka yöntem dene
     @SuppressLint("Range")
     fun allOdemeTip() : ArrayList<OdemeTip>    //tüm tabloyu getir hata verse diğer yöntem dene
    {
        val odemeTipList = ArrayList<OdemeTip>()
        var odemeTip : OdemeTip
        open()
        var c : Cursor = fetcAllOdemeTip()

        if (c.moveToFirst())
        {
            do{
                odemeTip = OdemeTip()
                odemeTip.Id = c.getInt(0)
                odemeTip.Baslik = c.getString(c.getColumnIndex("Baslik"))
                odemeTip.OdemePeriyod = c.getString(c.getColumnIndex("OdemePeriyod"))
                odemeTip.PeriyodGun = c.getInt(c.getColumnIndex("PeriyodGun"))
                odemeTipList.add(odemeTip)
            }while (c.moveToNext())


        }
        close()
        return odemeTipList
    }

    @SuppressLint("Range")
    fun allOdemeTip(Id: Int) : OdemeTip?
    {
        var odemeTip : OdemeTip? = null
        open()
        val sql = "Select * from OdemeTip where Id = ?"
        val c = OdemeTipDatabase!!.rawQuery(sql, arrayOf(Id.toString()))
        if (c.moveToFirst())
        {
            odemeTip = OdemeTip()
            odemeTip.Id = c.getInt(0)
            odemeTip.Baslik = c.getString(c.getColumnIndex("Baslik"))
            odemeTip.OdemePeriyod = c.getString(c.getColumnIndex("OdemePeriyod"))
            odemeTip.PeriyodGun = c.getInt(c.getColumnIndex("PeriyodGun"))
        }
        close()

        return odemeTip


/*
    fun addupdateCode(odemeTip : OdemeTip)
    {
        val cv = ContentValues()
        cv.put("Baslik",odemeTip.Baslik)
        cv.put("OdemePeriyod",odemeTip.OdemePeriyod)
        cv.put("PeriyodGun",odemeTip.PeriyodGun)
        open()
    }
    */



}
}