package com.tw.training.catkeeper.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.tw.training.catkeeper.R
import com.tw.training.catkeeper.utils.ViewPagerScroller
import com.tw.training.catkeeper.adapter.BannerAdapter
import com.tw.training.catkeeper.fragment.MyCatFragment
import com.tw.training.catkeeper.fragment.NearbyCatFragment
import com.tw.training.catkeeper.presenter.MainContract
import com.tw.training.catkeeper.presenter.MainPresenter

class MainActivity : AppCompatActivity(), MainContract.View, ViewPager.OnPageChangeListener {
    private val message = 0
    private val interval = 4000L

    @BindView(R.id.left_tab)
    lateinit var mLeftTab: TextView

    @BindView(R.id.right_tab)
    lateinit var mRightTab: TextView

    @BindView(R.id.ad_viewpager)
    lateinit var mViewPager: ViewPager

    @BindView(R.id.indicator)
    lateinit var mIndicator: ViewGroup

    private var mImageResIds = arrayOf(R.mipmap.cat_1, R.mipmap.cat_2, R.mipmap.cat_3, R.mipmap.cat_4)

    private var mPresenter: MainContract.Presenter = MainPresenter(this)

    private var mPrePosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("cat_keeper", "activity oncreate")

        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)

        setupViewPager()
        mPresenter.onStart()

        setupFragment()
    }


    private lateinit var nearbyCatFragment: NearbyCatFragment
    private var myCatFragment: MyCatFragment = MyCatFragment()

    private fun setupFragment() {
        nearbyCatFragment = NearbyCatFragment()
        var transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fragment_container, nearbyCatFragment)
        transaction.commit()
    }

    override fun onResume() {
        super.onResume()
        Log.i("cat_keeper", "activity onresume")
        handler.sendEmptyMessageDelayed(message, interval)
    }

    override fun onStop() {
        super.onStop()
        Log.i("cat_keeper", "activity onstop")
        handler.removeMessages(message)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("cat_keeper", "activity ondestroy")
        mPresenter.onStop()
    }

    @OnClick(R.id.left_tab)
    fun onLeftTabClicked() = mPresenter.switchToNearbyCat()


    @OnClick(R.id.right_tab)
    fun onRightTabClicked() = mPresenter.switchToMyCat()

    override fun switchToNearbyCat() {
        mRightTab.isEnabled = true
        mLeftTab.isEnabled = false

        var transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, nearbyCatFragment)
        transaction.commit()
    }

    override fun switchToMyCat() {
        mLeftTab.isEnabled = true
        mRightTab.isEnabled = false

        var transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, myCatFragment)
        transaction.commit()
    }

    private fun setupViewPager() {
        //create ImageView for each image resource
        val mViewContainer = ArrayList<ImageView>()
        for (i in 0 until mImageResIds.size) {
            // Compare to XML, we cal also create View with code
            val view = ImageView(this)
            view.scaleType = ImageView.ScaleType.FIT_XY
            view.setImageResource(mImageResIds[i])
            mViewContainer.add(view)
        }

        // set ViewPager fields
        mViewPager.adapter = BannerAdapter(mViewContainer)
        mViewPager.addOnPageChangeListener(this)
        mViewPager.currentItem = 1000

        // use ViewPagerScroller to control animation speed
        ViewPagerScroller(this).initViewPagerScroll(mViewPager)
    }

    override fun onPageScrollStateChanged(p0: Int) {
    }

    override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
    }

    override fun onPageSelected(position: Int) {
        val newPosition = position % mImageResIds.size
        updateIndicator(mPrePosition, newPosition)
        mPrePosition = newPosition
    }

    private fun updateIndicator(pre: Int, now: Int) {
        (mIndicator.getChildAt(pre) as ImageView).setImageResource(R.drawable.dot_bg_white)
        (mIndicator.getChildAt(now) as ImageView).setImageResource(R.drawable.dot_bg_green)

    }

    // Inherit from Handler and create an instance
    private val handler = @SuppressLint("HandlerLeak")
    object : Handler() {
        override fun handleMessage(msg: android.os.Message) {
            mViewPager.currentItem++
            sendEmptyMessageDelayed(message, interval)
        }
    }
}
