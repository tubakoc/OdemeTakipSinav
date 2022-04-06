package com.example.trackingpayment.BLL.VIEWHOLDER

import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.trackingpayment.MODELS.OdemeTip
import com.example.trackingpayment.R

class OdemeTipViewHolder (itemView : View, var itemClick : (position : Int) -> Unit , var odemeEkleClick : (position : Int) -> Unit) :  RecyclerView.ViewHolder(itemView){

    var tipBaslik : TextView
    var tipPeriyod : TextView
    var tipGun : TextView
    var odemeEkle : Button


    init {
        tipBaslik =itemView.findViewById(R.id.tipBaslik)
        tipPeriyod = itemView.findViewById(R.id.tipTarih)
        tipGun = itemView.findViewById(R.id.tipTutar)
        odemeEkle = itemView.findViewById(R.id.odemeEkle)

        itemView.setOnClickListener{
            itemClick(adapterPosition)
        }

        odemeEkle.setOnClickListener {
            odemeEkleClick(adapterPosition)

        }
    }

    fun bindData(context: Context, odemeTip: OdemeTip)
    {
        tipBaslik.text = odemeTip.baslik
        tipPeriyod.text = odemeTip.odemePeriyod
        tipGun.text = odemeTip.periyodGun.toString()

    }
}