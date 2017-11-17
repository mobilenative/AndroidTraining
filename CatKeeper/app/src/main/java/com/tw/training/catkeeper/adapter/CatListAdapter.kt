package com.tw.training.catkeeper.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.tw.training.catkeeper.R
import com.tw.training.catkeeper.model.GetNearbyCatResponse

class CatListAdapter(private val context: Context, var data: List<GetNearbyCatResponse.MomentsBean>?) : RecyclerView.Adapter<CatListAdapter.CatViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CatViewHolder? {
        return CatViewHolder(inflater.inflate(R.layout.cat_item_layout, null))
    }

    override fun getItemCount(): Int {
        return data!!.size
    }

    override fun onBindViewHolder(holder: CatViewHolder?, position: Int) {
        if(holder == null) {
            return
        }

        val bean = data!![position]
        holder.itemView.tag = position
        Glide.with(context).load(bean.avatar!!.image).into(holder.mAvatar)
        holder.mTextName.text = bean.cat
        holder.mTextDes.text = bean.message

    }

    inner class CatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mAvatar: ImageView
        var mTextName: TextView
        var mTextDes: TextView

        init {
            itemView.setOnClickListener { onItemClickedListener?.onItemClicked(itemView, itemView.tag.toString().toInt()) }

            mAvatar = itemView.findViewById(R.id.avatar)
            mTextName = itemView.findViewById(R.id.name)
            mTextDes = itemView.findViewById(R.id.description)

        }
    }

    var onItemClickedListener: OnItemClickedListener? = null

    interface OnItemClickedListener {
        fun onItemClicked(view: View, position: Int)
    }
}