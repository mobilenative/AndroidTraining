package com.tw.training.catkeeper.presenter

import com.tw.training.catkeeper.R
import com.tw.training.catkeeper.domain.CatsNearby

/**
 * Created by pchen on 27/10/2017.
 */
class CatsNearbyPresenter(private val mCatsNearbyView: CatsNearbyContract.View):
        CatsNearbyContract.Presenter {

    override fun start() {
        loadingCatsNearbyList()
    }

    private fun loadingCatsNearbyList() {
         //Fetch Cats List
        val catsNearbyList = ArrayList<CatsNearby>()
        (1..100).forEach {
            val catsNearby = CatsNearby("Kitty$it", "6 minutes ago",
                    listOf(R.drawable.cat_item_1, R.drawable.cat_item_2, R.drawable.cat_item_3))
            catsNearbyList.add(catsNearby)
        }
        mCatsNearbyView.showNearbyCats(catsNearbyList)
    }
}