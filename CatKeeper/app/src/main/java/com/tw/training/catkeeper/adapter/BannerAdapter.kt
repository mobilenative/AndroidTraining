package com.tw.training.catkeeper.adapter

import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

/**
 * Created by pchen on 19/10/2017.
 */
class BannerAdapter(imageList: List<ImageView>): PagerAdapter() {
    private val mImageList = imageList

    override fun isViewFromObject(view: View?, `object`: Any?): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return mImageList.size
    }

    override fun instantiateItem(container: ViewGroup?, position: Int): Any {
        val imageView: ImageView = mImageList[position]
        container?.addView(imageView)

        return imageView
    }

    override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any?) {
        container?.removeView(mImageList[position])
    }
}