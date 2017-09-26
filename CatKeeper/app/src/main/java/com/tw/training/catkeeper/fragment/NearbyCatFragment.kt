package com.tw.training.catkeeper.fragment


import android.os.Bundle
import android.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.tw.training.catkeeper.R

class NearbyCatFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank, container, false)
    }

}
