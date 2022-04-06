package com.example.trackingpayment.VIEW

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.trackingpayment.DAL.OdemeGecmisOperation
import com.example.trackingpayment.MODELS.OdemeGecmis
import com.example.trackingpayment.databinding.ActivityOdemeEkleBinding

class OdemeEkle : AppCompatActivity() {
    lateinit var binding : ActivityOdemeEkleBinding
    var odemeGecmis : OdemeGecmis? = null
    var odemeGecmisOperation : OdemeGecmisOperation

    init {
        odemeGecmisOperation = OdemeGecmisOperation(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOdemeEkleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var alinanId = intent.getIntExtra("OdemeEkleId",0)
        odemeGecmisDetay()

        odemeGecmisControl()

    }

    private fun odemeGecmisDetay() {
        val id = intent.getIntExtra("id",0)
        val tutar =intent.getIntExtra("tutar",0)
        val tarih = intent.getStringExtra("tarih")

        binding.odemeMiktar.setText(tutar.toString(), TextView.BufferType.EDITABLE);
        binding.editTextDate.setText(tarih, TextView.BufferType.EDITABLE);
    }

    fun btnOdemeKaydet_OnClick(view: View) {
        odemeGecmis!!.odemeTarih = binding.editTextDate.text.toString()
        odemeGecmis!!.odemeTutar = binding.odemeMiktar.text.toString().toInt()

        if (odemeGecmis!!.id == null)
        {
            odemeGecmisOperation.addOdemeGecmis(odemeGecmis!!)
        }
        else
        {
            odemeGecmisOperation.updateOdemeGecmis(odemeGecmis!!)
        }
        setResult(RESULT_OK)
        finish()
    }
/*
    fun silBtn_OnClick(view: View) {
        odemeGecmisOperation.deleteOdemeGecmis(odemeGecmis!!.id!!)
        setResult(RESULT_OK)
        finish()
    }*/

    private fun odemeGecmisControl()
    {
        val odemeGecmisId = intent.getIntExtra("odemeGecmisId",-1)
        if (odemeGecmisId == -1)
        {
            odemeGecmis = OdemeGecmis()
            binding.silbtn.visibility = View.GONE
        }
        else
        {
            odemeGecmis = odemeGecmisOperation.bringOdemeGecmis(odemeGecmisId)
            binding.odemeMiktar.setText(odemeGecmis!!.odemeTutar.toString())
            binding.editTextDate.setText(odemeGecmis!!.odemeTarih)

            binding.silbtn.visibility= View.VISIBLE
        }
    }

}