package com.example.trackingpayment.BLL.VIEWHOLDER

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.trackingpayment.R

class OdemeGecmisViewHolder  (itemView : View, var itemClick : (position : Int) -> Unit):  RecyclerView.ViewHolder(itemView) {

    var tipTarih : TextView
    var tipTutar : TextView


    init {
        tipTarih =itemView.findViewById(R.id.tipTarih)
        tipTutar = itemView.findViewById(R.id.tipTutar)

        itemView.setOnClickListener{
            itemClick(adapterPosition)
        }
    }
    fun bindData(context: Context, metin:String)
    {

    }
}