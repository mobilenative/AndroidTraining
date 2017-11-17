package com.tw.training.catkeeper.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import com.tw.training.catkeeper.R
import com.tw.training.catkeeper.activity.CatDetailActivity
import com.tw.training.catkeeper.adapter.CatListAdapter
import com.tw.training.catkeeper.model.GetNearbyCatResponse
import com.tw.training.catkeeper.presenter.NearbyCatContract
import com.tw.training.catkeeper.presenter.NearybyCatPresenter
import com.tw.training.catkeeper.view.RecycleViewDivider

class NearbyCatFragment : BaseFragment(), NearbyCatContract.View, CatListAdapter.OnItemClickedListener {

    @BindView(R.id.recycler_view)
    lateinit var mCatList: RecyclerView

    private lateinit var mAdapter: CatListAdapter

    private lateinit var mPresenter: NearybyCatPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter = NearybyCatPresenter(this)
    }

    override fun createView(inflater: LayoutInflater): View {
        val view = inflater.inflate(R.layout.fragment_nearby_cat, null)
        ButterKnife.bind(this, view)
        initRecyclerView()
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setData()


    }

    override fun onResume() {
        super.onResume()
        mPresenter.onStart()
    }

    override fun onStop() {
        super.onStop()
        mPresenter.onStop()
    }

    private fun initRecyclerView() {
        mCatList.layoutManager = LinearLayoutManager(activity)
        mCatList.addItemDecoration(RecycleViewDivider(
                LinearLayoutManager.HORIZONTAL,
                30,
                ContextCompat.getColor(activity,
                R.color.paleGrey)))
    }

    override fun onGetDataSucceed(data: List<GetNearbyCatResponse.MomentsBean>?) {
        mAdapter.data = data
        mAdapter.notifyDataSetChanged()

        Toast.makeText(context, "get data succeed!", Toast.LENGTH_SHORT).show()
    }

    override fun onGetDataFail(msg: String) {
        Toast.makeText(context, "get data failed!", Toast.LENGTH_SHORT).show()
    }

    private fun setData() {
        var data = ArrayList<GetNearbyCatResponse.MomentsBean>()
        mAdapter = CatListAdapter(activity, data)
        mAdapter.onItemClickedListener = this
        mCatList.adapter = mAdapter
    }

    override fun onItemClicked(view: View, position: Int) {
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, view.findViewById(R.id.avatar), "avatar_transition")
        ActivityCompat.startActivity(activity, Intent(activity, CatDetailActivity::class.java), options.toBundle())
    }
}
