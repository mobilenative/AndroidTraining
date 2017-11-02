package com.tw.training.catkeeper.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View

import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.tw.training.catkeeper.R
import com.tw.training.catkeeper.domain.CatsNearby

/**
 * Created by pchen on 26/10/2017.
 */
class CatsNearbyAdapter(context: Context, var mCatsNearbyList: List<CatsNearby>?) :
        RecyclerView.Adapter<CatsNearbyAdapter.CatsNearbyViewHolder>() {

    private val mInflater: LayoutInflater = LayoutInflater.from(context)
    var mProfileClickListener: OnProfileClickListener? = null

    override fun getItemCount(): Int {
        return mCatsNearbyList?.size ?: 0
    }

    override fun onBindViewHolder(holder: CatsNearbyAdapter.CatsNearbyViewHolder?, position: Int) {
        holder?.mNameTv?.text = mCatsNearbyList?.get(position)?.mCatName
        holder?.mPosition = position
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CatsNearbyAdapter.CatsNearbyViewHolder {
        val itemView = mInflater.inflate(R.layout.cats_nearby_item, parent, false)
        return CatsNearbyViewHolder(itemView)
    }

    inner class CatsNearbyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mPosition = 0
        val mNameTv: TextView = itemView.findViewById(R.id.cat_name)
        val mAvatarIv: ImageView = itemView.findViewById(R.id.cat_avatar)

        init {
            mAvatarIv.setOnClickListener {
                mProfileClickListener?.onProfileClick(mPosition)

            }
        }
    }

    interface OnProfileClickListener {
        fun onProfileClick(position: Int)
    }
}