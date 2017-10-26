package com.tw.training.catkeeper.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View

import android.view.ViewGroup
import android.widget.TextView
import com.tw.training.catkeeper.R

/**
 * Created by pchen on 26/10/2017.
 */
class CatsNearbyAdapter(private val mContext: Context):
        RecyclerView.Adapter<CatsNearbyAdapter.CatsNearbyViewHolder>() {

    private val mInflater: LayoutInflater = LayoutInflater.from(mContext)

    override fun getItemCount(): Int {
        return 1000
    }

    override fun onBindViewHolder(holder: CatsNearbyAdapter.CatsNearbyViewHolder?, position: Int) {
        holder?.nameTv?.text = "Hello Kitty"
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CatsNearbyAdapter.CatsNearbyViewHolder {
        val itemView = mInflater.inflate(R.layout.cats_nearby_item, parent, false)
        return CatsNearbyViewHolder(itemView)
    }

    class CatsNearbyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val nameTv: TextView = itemView.findViewById(R.id.cat_name)
    }
}