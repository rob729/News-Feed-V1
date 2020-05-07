package com.example.robin.news30.fragment

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.robin.news30.R
import com.example.robin.news30.adapter.NewsAdapter
import com.example.robin.news30.databinding.FragmentSourceNewsBinding
import com.example.robin.news30.model.NewsResource
import com.example.robin.news30.utils.NetworkStateReceiver
import com.example.robin.news30.viewmodel.NewsSourceViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class NewsSourceFragment : DaggerFragment(), NetworkStateReceiver.NetworkStateReceiverListener {

    @Inject
    lateinit var newsSourceViewModel: NewsSourceViewModel

    @Inject
    lateinit var newsAdapter: NewsAdapter

    lateinit var binding: FragmentSourceNewsBinding
    private var networkStateReceiver: NetworkStateReceiver? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_source_news, container, false
        )

        newsSourceViewModel.source = arguments?.get("source").toString()

        networkStateReceiver = NetworkStateReceiver()
        networkStateReceiver!!.addListener(this)
        context?.registerReceiver(
            networkStateReceiver,
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )

        newsSourceViewModel.fetchRepos()

        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
        binding.recyclerView.adapter = newsAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)

        newsSourceViewModel.news.observe(this, Observer {
            when (it.status) {
                NewsResource.Status.LOADING -> {
                    binding.loading.visibility = View.VISIBLE
                    binding.errorTxt.visibility = View.GONE
                }

                NewsResource.Status.SUCCESS -> {
                    newsAdapter.submitList(it.data?.articles)
                    binding.loading.visibility = View.GONE
                    binding.errorTxt.visibility = View.GONE
                }

                NewsResource.Status.ERROR -> {
                    Log.e("TAG", it.message + " error")
                    binding.loading.visibility = View.GONE
                    binding.errorTxt.visibility = View.VISIBLE
                }
            }
        })


        return binding.root
    }

    override fun networkAvailable() {
        if (binding.errorTxt.visibility == View.VISIBLE) {
            newsSourceViewModel.fetchRepos()
        }
    }

    override fun networkUnavailable() {
        Toast.makeText(context, "Please check your internet connection", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        networkStateReceiver?.removeListener(this)
        context?.unregisterReceiver(networkStateReceiver)
    }

}