package com.example.trackingpayment.BLL.ADAPTER

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.trackingpayment.BLL.VIEWHOLDER.OdemeTipViewHolder
import com.example.trackingpayment.MODELS.OdemeTip
import com.example.trackingpayment.R

class OdemeTipAdapter(val context: Context, var liste: ArrayList<OdemeTip>, val itemClick: (position : Int)->Unit,var odemeEkleClick : (position : Int) -> Unit):
    RecyclerView.Adapter<OdemeTipViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OdemeTipViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.rvc_odemetiptasarim,parent,false)
        return OdemeTipViewHolder(v,itemClick,odemeEkleClick)
    }

    override fun onBindViewHolder(holder: OdemeTipViewHolder, position: Int) {
        holder.bindData(context, liste.get(position))

    }

    override fun getItemCount(): Int {
        return liste.size
    }
}