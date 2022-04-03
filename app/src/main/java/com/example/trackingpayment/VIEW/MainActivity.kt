package com.example.trackingpayment.VIEW

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.trackingpayment.DAL.OdemeTipOperation
import com.example.trackingpayment.MODELS.OdemeTip
import com.example.trackingpayment.R

class MainActivity : AppCompatActivity() {
    //adım 5 devam ettir
    var odemeTipList = ArrayList<OdemeTip>()
    var odemeTipOperation : OdemeTipOperation
    init {
        odemeTipOperation = OdemeTipOperation(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun btnYeniOdemeTipiEkle_OnClick(view: View) {
        val intent = Intent(this,YeniOdemeTipi::class.java)
        startActivityForResult(intent,1)
    }
}