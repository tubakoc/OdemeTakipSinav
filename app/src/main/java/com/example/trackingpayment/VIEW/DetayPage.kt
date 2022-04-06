package com.example.trackingpayment.VIEW


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trackingpayment.BLL.ADAPTER.OdemeGecmisAdapter
import com.example.trackingpayment.DAL.OdemeGecmisOperation
import com.example.trackingpayment.databinding.ActivityDetayPageBinding
import com.example.trackingpayment.MODELS.OdemeGecmis


class DetayPage : AppCompatActivity() {
    lateinit var binding: ActivityDetayPageBinding
    var odemeGecmisList = ArrayList<OdemeGecmis>()
    var odemeGecmisOperation : OdemeGecmisOperation

    init {
        odemeGecmisOperation = OdemeGecmisOperation(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetayPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        odemeGecmisList = odemeGecmisOperation.allOdemeGecmis()
        val adapter = OdemeGecmisAdapter(this,odemeGecmisList,::rvItem_OnClick)
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        binding.rvGecmisodeme.layoutManager =layoutManager
        binding.rvGecmisodeme.addItemDecoration(DividerItemDecoration(this,layoutManager.orientation))
        binding.rvGecmisodeme.adapter = adapter


        val id = intent.getStringExtra("id")
        val baslik =intent.getStringExtra("baslik")
        val odemePeriyod = intent.getStringExtra("odemePeriyod")
        val periyodGun = intent.getIntExtra("periyodGun",0)

        binding.tipBaslik.text = baslik
        binding.odemePeriyod.text = odemePeriyod
        binding.periyodGun.text = periyodGun.toString()
    }

    fun btnDuzenle_OnClick(view: View) {
        val intent = Intent(this,YeniOdemeTipi::class.java)

    }

    fun btnOdemeEkle_OnClick(view: View) {
        val intent = Intent(this,OdemeEkle::class.java)
        odemeEkleResultLauncher.launch(intent)
    }

    fun rvItem_OnClick(position: Int)
    {
        var id = "id"
        var tutar ="tutar"
        var tarih = "tarih"
        val intent = Intent(this,OdemeEkle::class.java)
        var item = odemeGecmisList[position]
        intent.putExtra(id,item.id)
        intent.putExtra(tutar,item.odemeTutar)
        intent.putExtra(tarih,item.odemeTarih)
        startActivity(intent)
    }

    val odemeEkleResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult(),::odemeEkleResult)

    fun odemeEkleResult(result: ActivityResult)
    {
        if (result.resultCode == RESULT_OK)
        {
            odemeGecmisList.clear()
            odemeGecmisList.addAll(odemeGecmisOperation.allOdemeGecmis())
            binding.rvGecmisodeme!!.adapter!!.notifyDataSetChanged()
        }
    }
/*
    fun odemeTipDuzenle(result: ActivityResult)
    {
        if (result.resultCode == RESULT_OK)
        {

        }
    }*/
}