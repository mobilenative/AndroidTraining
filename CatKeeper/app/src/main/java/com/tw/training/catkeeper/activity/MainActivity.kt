package com.tw.training.catkeeper.activity

import android.os.Bundle
import android.os.Handler
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import com.tw.training.catkeeper.R
import com.tw.training.catkeeper.adapter.BannerAdapter
import com.tw.training.catkeeper.fragment.CatsNearByFragment
import com.tw.training.catkeeper.fragment.MyCatsFragment

class MainActivity : AppCompatActivity(), ViewPager.OnPageChangeListener, View.OnClickListener {
    private lateinit var mViewPager: ViewPager
    private lateinit var mLeftTabView: Button
    private lateinit var mRightTabView: Button
    private lateinit var mIndicatorView: ViewGroup
    private val mBannerInterval = 1000L
    private var mPreviousPosition: Int = 0

    private val mImageResIds = arrayListOf(R.drawable.banner_1,
            R.drawable.banner_2, R.drawable.banner_3, R.drawable.banner_4)
    private val mHandler: Handler = Handler()

    private val mBannerRunnable: Runnable = object: Runnable {
        override fun run() {
            mViewPager.currentItem++
            mHandler.postDelayed(this, mBannerInterval)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initBannerView()
        initTabView()
        initFragmentView()
    }

    private fun initTabView() {
        mLeftTabView = findViewById(R.id.left_tab)
        mRightTabView = findViewById(R.id.right_tab)
        mLeftTabView.isSelected = true

        mLeftTabView.setOnClickListener(this)
        mRightTabView.setOnClickListener(this)
    }

    private fun initFragmentView() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.fragment_container, CatsNearByFragment.newInstance())
        fragmentTransaction.commit()
    }

    override fun onResume() {
        super.onResume()
        mHandler.postDelayed(mBannerRunnable, mBannerInterval)
    }

    override fun onClick(v: View?) {
        val transaction = supportFragmentManager
                .beginTransaction()

        when(v?.id) {
            R.id.left_tab -> {
                mLeftTabView.isSelected = true
                mRightTabView.isSelected = false
                transaction.replace(R.id.fragment_container, CatsNearByFragment.newInstance())
            }
            R.id.right_tab -> {
                mLeftTabView.isSelected = false
                mRightTabView.isSelected = true
                        transaction.replace(R.id.fragment_container, MyCatsFragment.newInstance())
            }
        }
        transaction.commit()
    }

    private fun initBannerView() {
        mViewPager = findViewById(R.id.viewpager)
        mIndicatorView = findViewById(R.id.indicator_layout)

        val imageLists = arrayListOf<ImageView>()
        mImageResIds.forEach {
            val iv = ImageView(this)
            iv.scaleType = ImageView.ScaleType.FIT_XY
            iv.setImageResource(it)
            imageLists.add(iv)
        }

        mViewPager.adapter = BannerAdapter(imageLists)
        mViewPager.addOnPageChangeListener(this)
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
        mIndicatorView.getChildAt(mPreviousPosition % mImageResIds.size).setBackgroundResource(R.drawable.banner_indicator_unselected)
        mIndicatorView.getChildAt(position % mImageResIds.size).setBackgroundResource(R.drawable.banner_indicator_selected)
    }

    override fun onDestroy() {
        super.onDestroy()
        mHandler.removeCallbacks(mBannerRunnable)
    }
}
