package com.example.trackingpayment.BLL.ADAPTER

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.trackingpayment.BLL.VIEWHOLDER.OdemeGecmisViewHolder
import com.example.trackingpayment.BLL.VIEWHOLDER.OdemeTipViewHolder
import com.example.trackingpayment.R


class OdemeGecmisAdapter (val context: Context, var liste:ArrayList<String>, val itemClick : (position : Int)->Unit):
    RecyclerView.Adapter<OdemeGecmisViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OdemeGecmisViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.rvc_odeme_gecmis,parent,false)
        return OdemeGecmisViewHolder(v,itemClick)
    }

    override fun onBindViewHolder(holder: OdemeGecmisViewHolder, position: Int) {
        holder.bindData(context, liste.get(position))
    }

    override fun getItemCount(): Int {
        return liste.size
    }
}