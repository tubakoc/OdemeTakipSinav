package com.example.trackingpayment.VIEW

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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
        odemeTipList = odemeTipOperation.allOdemeTip()

        val adapter = OdemeTipAdapter(this,odemeTipList,::rvOdeme_OnClick)
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.rvOdemeler.layoutManager = layoutManager
        binding.rvOdemeler.addItemDecoration(DividerItemDecoration(this,layoutManager.orientation))
        binding.rvOdemeler.adapter = adapter



    }

    fun btnYeniOdemeTipiEkle_OnClick(view: View) {
        val intent = Intent(this,YeniOdemeTipi::class.java)
        startActivityForResult(intent,1)
    }
    fun rvOdeme_OnClick(position : Int)
    {
        val intent = Intent(this,YeniOdemeTipi::class.java)
        intent.putExtra("odemeTipId",odemeTipList.get(position).id)

        startActivityForResult(intent,2)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK)
        {
            odemeTipList.clear()
            odemeTipList.addAll(odemeTipOperation.allOdemeTip())
            binding.rvOdemeler.adapter!!.notifyDataSetChanged()
        }
    }
}