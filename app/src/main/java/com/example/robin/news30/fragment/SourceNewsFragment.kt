package com.example.robin.news30.fragment

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.robin.news30.R
import com.example.robin.news30.adapter.NewsAdapter
import com.example.robin.news30.databinding.FragmentSourceNewsBinding
import com.example.robin.news30.utils.NetworkStateReceiver
import com.example.robin.news30.viewmodel.NewsSourceViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class SourceNewsFragment : Fragment(), NetworkStateReceiver.NetworkStateReceiverListener {

    private val newsSourceViewModel: NewsSourceViewModel by viewModel()
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

        val sp = PreferenceManager.getDefaultSharedPreferences(context)
        val source = sp.getString("source", "the-verge")

        newsSourceViewModel.source = source

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
        binding.recyclerView.adapter = context?.let {
            NewsAdapter(
                newsSourceViewModel,
                this,
                it
            )
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.setItemViewCacheSize(8)

        newsSourceViewModel.news.observe(this, Observer {
            if (it != null) {
                binding.recyclerView.visibility = View.VISIBLE
            }
        })

        newsSourceViewModel.newsLoadError.observe(this, Observer {
            if (it) {
                binding.loading.visibility = View.GONE
                binding.errorTxt.visibility = View.VISIBLE
                binding.recyclerView.visibility = View.GONE
            } else {
                binding.loading.visibility = View.VISIBLE
                binding.errorTxt.visibility = View.GONE
                binding.recyclerView.visibility = View.VISIBLE
            }
        })

        newsSourceViewModel.loading.observe(this, Observer {
            if (it) {
                binding.loading.visibility = View.VISIBLE
                binding.errorTxt.visibility = View.GONE
                binding.recyclerView.visibility = View.GONE
            } else {
                binding.loading.visibility = View.GONE
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