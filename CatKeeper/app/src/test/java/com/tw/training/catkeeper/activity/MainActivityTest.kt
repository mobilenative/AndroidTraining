package com.tw.training.catkeeper.activity

import android.widget.Button
import com.tw.training.catkeeper.R
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner

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
        assertFalse(myCatBtn.isSelected)
    }

    @Test
    fun shouldSwitchCorrectlyWhenMyCat() {
        myCatBtn.performClick()

        assertTrue(myCatBtn.isSelected)
        assertFalse(nearByBtn.isSelected)
    }
}