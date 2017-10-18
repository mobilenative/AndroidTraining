package com.tw.training.catkeeper.activity

import android.widget.Button
import com.tw.training.catkeeper.BuildConfig
import com.tw.training.catkeeper.R
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/**
 * Created by pchen on 18/10/2017.
 */
@RunWith(RobolectricTestRunner::class)
class MainActivityTest {
    private lateinit var mainActivity: MainActivity
    private lateinit var nearByBtn: Button
    private lateinit var myCatBtn: Button

    @Before
    fun setup() {
        mainActivity = Robolectric.setupActivity(MainActivity::class.java)
        nearByBtn = mainActivity.findViewById(R.id.left_tab)
        myCatBtn = mainActivity.findViewById(R.id.right_tab)
    }

    @Test
    fun shouldSwitchCorrectlyWhenClickNearBy() {
        nearByBtn.performClick()

        assertTrue(nearByBtn.isSelected)
        assertFalse(nearByBtn.isSelected)
    }

    @Test
    fun shouldSwitchCorrectlyWhenMyCat() {
        nearByBtn = mainActivity.findViewById<Button>(R.id.left_tab)
        myCatBtn = mainActivity.findViewById<Button>(R.id.right_tab)

        nearByBtn.performClick()

        assertTrue(nearByBtn.isSelected)
        assertFalse(nearByBtn.isSelected)
    }
}