package com.example.trackingpayment.VIEW

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.trackingpayment.DAL.OdemeTipOperation
import com.example.trackingpayment.MODELS.OdemeTip
import com.example.trackingpayment.R
import com.example.trackingpayment.databinding.ActivityYeniOdemeTipiBinding

class YeniOdemeTipi : AppCompatActivity() {
    lateinit var binding: ActivityYeniOdemeTipiBinding
    var odemeTip : OdemeTip? = null
    var odemeTipOperation : OdemeTipOperation

    init {
        odemeTipOperation = OdemeTipOperation(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityYeniOdemeTipiBinding.inflate(layoutInflater)
        setContentView(binding.root)
        odemeTipControl()

    }

    fun btnSil_OnClick(view: View) {
        odemeTipOperation.deleteOdemeTip(odemeTip!!.Id!!)
        setResult(RESULT_OK)
        finish()
    }
    fun btnKaydet_Onclick(view: View) {
        odemeTip!!.Baslik = binding.etOdemeTip.text.toString()
       // odemeTip!!.PeriyodGun = binding.etPeriyodGun.text.toString()

        if (odemeTip!!.Id == null)
        {
            odemeTipOperation.addOdemeTip(odemeTip!!)
        }
        else
        {
            odemeTipOperation.updateOdemeTip(odemeTip!!)
        }
        setResult(RESULT_OK)
        finish()
    }
    fun odemeTipControl()
    {
        val odemeTipId = intent.getIntExtra("odemeTipId",-1)
        if (odemeTipId == -1)
        {
            odemeTip = OdemeTip()
            binding.btnSil.visibility = View.GONE
        }
        else
        {
            //spinner seçeneklerini ekle spPeriyod haftalık aylık yıllık
            odemeTip = odemeTipOperation.allOdemeTip(odemeTipId)
            binding.etOdemeTip.setText(odemeTip!!.Baslik)
            binding.etPeriyodGun.setText(odemeTip!!.PeriyodGun.toString())

            binding.btnSil.visibility = View.VISIBLE
        }
    }
}