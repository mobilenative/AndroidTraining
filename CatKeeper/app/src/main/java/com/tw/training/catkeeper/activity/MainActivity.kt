package com.tw.training.catkeeper.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.view.ViewGroup
import android.widget.ImageView
import com.tw.training.catkeeper.R
import com.tw.training.catkeeper.adapter.BannerAdapter

class MainActivity : AppCompatActivity(), ViewPager.OnPageChangeListener {
    private lateinit var mViewPager: ViewPager
    private lateinit var mIndicatorView: ViewGroup

    private var mPreviousPosition = 0
    private val mImageResIds = arrayListOf<Int>(R.mipmap.banner_icon_1,
            R.mipmap.banner_icon_2, R.mipmap.banner_icon_3, R.mipmap.banner_icon_4)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initBanner()
    }

    private fun initBanner() {
        mViewPager = findViewById(R.id.viewpager)
        mIndicatorView = findViewById(R.id.indicator_veiw)
        var imageList = ArrayList<ImageView>()

        mImageResIds.forEach {
            val iv = ImageView(this)
            iv.scaleType = ImageView.ScaleType.FIT_XY
            iv.setImageResource(it)
            imageList.add(iv)
        }

        mViewPager.adapter = BannerAdapter(imageList)
        mViewPager.addOnPageChangeListener(this)
        mPreviousPosition = mViewPager.currentItem
    }

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
        updateIndicator(position)
        mPreviousPosition = mViewPager.currentItem
    }

    private fun updateIndicator(position: Int) {
        mIndicatorView.getChildAt(position)
                .setBackgroundResource(R.drawable.banner_indicator_selected)
        mIndicatorView.getChildAt(mPreviousPosition)
                .setBackgroundResource(R.drawable.banner_indicator_unselected)
    }
}
