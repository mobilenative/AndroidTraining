package com.tw.training.catkeeper.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tw.training.catkeeper.R
import com.tw.training.catkeeper.adapter.CatsNearbyAdapter

/**
 * Created by pchen on 25/10/2017.
 */
class CatsNearByFragment : Fragment() {

    private lateinit var mRecyclerView: RecyclerView

    companion object {
        fun newInstance(): CatsNearByFragment {
            return CatsNearByFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.cats_nearby, container, false)
        mRecyclerView = view.findViewById(R.id.recycler_view)

        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mRecyclerView.layoutManager = LinearLayoutManager(activity)
        mRecyclerView.adapter = CatsNearbyAdapter(activity)
    }
}