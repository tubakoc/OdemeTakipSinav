package com.example.trackingpayment.VIEW


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trackingpayment.BLL.ADAPTER.OdemeGecmisAdapter
import com.example.trackingpayment.DAL.OdemeGecmisOperation
import com.example.trackingpayment.DAL.OdemeTipOperation
import com.example.trackingpayment.databinding.ActivityDetayPageBinding
import com.example.trackingpayment.MODELS.OdemeGecmis
import com.example.trackingpayment.MODELS.OdemeTip
import com.example.trackingpayment.R


class DetayPage : AppCompatActivity() {
      var id :Int = 0

    lateinit var binding: ActivityDetayPageBinding
    var odemeTipList = ArrayList<OdemeTip>()
    var odemeTip : OdemeTip ? = null
    var odemeGecmis : OdemeGecmis ? = null
    var odemeGecmisList = ArrayList<OdemeGecmis>()
    var odemeGecmisOperation : OdemeGecmisOperation
    var odemeTipOperation : OdemeTipOperation

    init {
        odemeGecmisOperation = OdemeGecmisOperation(this)
        odemeTipOperation = OdemeTipOperation(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetayPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        id = intent.getIntExtra("id",0)
        odemeGecmisRcyler()

        odemeTipDetayBilgi()

    }

    private fun odemeTipDetayBilgi() {

        val baslik =intent.getStringExtra("baslik")
        val odemePeriyod = intent.getStringExtra("odemePeriyod")
        val periyodGun = intent.getIntExtra("periyodGun",0)

        binding.tipBaslik.text = baslik
        binding.odemePeriyod.text = odemePeriyod
        binding.periyodGun.text = periyodGun.toString()
    }

    fun btnDuzenle_OnClick(view: View) {
        val intent = Intent(this,YeniOdemeTipi::class.java)
       // intent.putExtra("odemeTipId",odemeTip!!.id)
        startActivity(intent)

    }

    fun btnOdemeEkle_OnClick(view: View) {
        val intent = Intent(this,OdemeEkle::class.java)
        odemeEkleResultLauncher.launch(intent)

    }

    fun rvItem_OnClick(position: Int)
    {
      deleteOdeme(position)

    }


    val odemeEkleResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult(),::odemeEkleResult)

    fun odemeTipduzenle(position: Int)
    {
        var id = "id"
        var baslik ="baslik"
        var odemePeriyod = "odemePeriyod"
        var periyodGun = "periyodGun"

        val intent = Intent(this,OdemeEkle::class.java)
        var item = odemeTipList[position]
        intent.putExtra(id,item.id)
        intent.putExtra(baslik,item.baslik)
        intent.putExtra(odemePeriyod,item.odemePeriyod)
        intent.putExtra(periyodGun,item.periyodGun)

    }
    fun odemeEkleResult(result: ActivityResult)
    {
        if (result.resultCode == RESULT_OK)
        {

            odemeGecmisList.clear()
            odemeGecmisList.addAll(odemeGecmisOperation.allOdemeGecmis())
            binding.rvGecmisodeme!!.adapter!!.notifyDataSetChanged()
        }
    }
    private fun deleteOdeme(id :Int)
    {
        odemeGecmis = odemeGecmisList.get(id)
        val adb = AlertDialog.Builder(this)
        adb.setTitle("Sil")
        adb.setMessage("Silmek istediğine emin misin")
        adb.setPositiveButton(R.string.sil){_,_ ->
            odemeGecmisOperation.deleteOdemeGecmis(id)
            binding.rvGecmisodeme.adapter!!.notifyDataSetChanged()
        }
        adb.show()

    }


    private fun odemeGecmisRcyler()
    {
        odemeGecmisList = odemeGecmisOperation.allOdemeGecmis()
        val adapter = OdemeGecmisAdapter(this,odemeGecmisList,::rvItem_OnClick)
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        binding.rvGecmisodeme.layoutManager =layoutManager
        binding.rvGecmisodeme.addItemDecoration(DividerItemDecoration(this,layoutManager.orientation))
        binding.rvGecmisodeme.adapter = adapter
    }



}