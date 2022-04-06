package com.example.trackingpayment.VIEW

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
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
        val listePeriyod = arrayListOf<PeriyodListe>(
            PeriyodListe.Yıllık,
            PeriyodListe.Aylık,
            PeriyodListe.Haftalık)
        val adap : ArrayAdapter<PeriyodListe> = ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,listePeriyod)

        binding.spPeriyod.adapter = adap
        odemeTipControl()
    }

    fun btnSil_OnClick(view: View) {
        odemeTipOperation.deleteOdemeTip(odemeTip!!.id!!)
        setResult(RESULT_OK)
        finish()
    }
    fun btnKaydet_Onclick(view: View) {
        odemeTip!!.baslik = binding.etOdemeTip.text.toString()
        odemeTip!!.odemePeriyod = binding.spPeriyod.selectedItem.toString()
        odemeTip!!.periyodGun = binding.etPeriyodGun.text.toString().toInt()

        if (odemeTip!!.id == null)
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
    private fun odemeTipControl()
    {
        val odemeTipId = intent.getIntExtra("odemeTipId",-1)
        if (odemeTipId == -1)
        {
            odemeTip = OdemeTip()
            binding.btnSil.visibility = View.GONE
        }
        else
        {
            odemeTip = odemeTipOperation.bringOdemeTip(odemeTipId)
            binding.etOdemeTip.setText(odemeTip!!.baslik)
            binding.etPeriyodGun.setText(odemeTip!!.periyodGun.toString())
            binding.spPeriyod.selectedItem.toString()
            binding.btnSil.visibility = View.VISIBLE
        }
    }

    fun periyodKontrol(list : ArrayList<PeriyodListe>)
    {

    }

}