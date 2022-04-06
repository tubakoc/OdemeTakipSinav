package com.example.trackingpayment.VIEW

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trackingpayment.BLL.ADAPTER.OdemeTipAdapter
import com.example.trackingpayment.DAL.OdemeTipOperation
import com.example.trackingpayment.MODELS.OdemeTip
import com.example.trackingpayment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var odemeTipList = ArrayList<OdemeTip>()
    var odemeTipOperation : OdemeTipOperation

    init {
        odemeTipOperation = OdemeTipOperation(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        odemeTipRecyler()
    }

    fun btnYeniOdemeTipiEkle_OnClick(view: View) {
        val intent = Intent(this,YeniOdemeTipi::class.java)
        yeniOdemeResultLauncher.launch(intent)

    }
    fun rvOdeme_OnClick(position : Int)
    {
        var id = "id"
        var baslik ="baslik"
        var odemePeriyod = "odemePeriyod"
        var periyodGun = "periyodGun"
        val intent = Intent(this,DetayPage::class.java)
        var item = odemeTipList[position]
        intent.putExtra(id,item.id)
        intent.putExtra(baslik,item.baslik)
        intent.putExtra(odemePeriyod,item.odemePeriyod)
        intent.putExtra(periyodGun,item.periyodGun)
        startActivity(intent)
    }



    var yeniOdemeResultLauncher =registerForActivityResult(ActivityResultContracts.StartActivityForResult(),::yeniOdemeResult)

fun yeniOdemeResult(result: ActivityResult)
    {
        if (result.resultCode == RESULT_OK)
        {
            odemeTipList.clear()
            odemeTipList.addAll(odemeTipOperation.allOdemeTip())
            binding.rvOdemeler.adapter!!.notifyDataSetChanged()
        }
    }

    fun odemeTipRecyler()
    {
        odemeTipList = odemeTipOperation.allOdemeTip()

        val adapter = OdemeTipAdapter(this,odemeTipList,::rvOdeme_OnClick,::odemeEkleClick)
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.rvOdemeler.layoutManager = layoutManager
        binding.rvOdemeler.addItemDecoration(DividerItemDecoration(this,layoutManager.orientation))
        binding.rvOdemeler.adapter = adapter
    }

    private fun odemeEkleClick(i: Int) {
        val intent = Intent(this,OdemeEkle::class.java)
        var item = odemeTipList[i].id
        intent.putExtra("OdemeEkleId",item)
        startActivity(intent)
    }


}