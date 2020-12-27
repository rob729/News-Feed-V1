package com.robin.news30.fragment

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.robin.news30.activity.MainActivity
import com.robin.news30.adapter.NewsAdapter
import com.robin.news30.databinding.FragmentSourceNewsBinding
import com.robin.news30.model.NewsResource
import com.robin.news30.utils.NetworkStateReceiver
import com.robin.news30.viewmodel.NewsSourceViewModel
import com.techyourchance.dagger2course.screens.common.fragments.BaseFragment
import javax.inject.Inject

class NewsSourceFragment : BaseFragment(), NetworkStateReceiver.NetworkStateReceiverListener {

    @Inject
    lateinit var newsSourceViewModel: NewsSourceViewModel

    @Inject
    lateinit var newsAdapter: NewsAdapter

    private var _binding: FragmentSourceNewsBinding? = null
    private val binding get() = _binding!!

    private var networkStateReceiver: NetworkStateReceiver? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        injector.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentSourceNewsBinding.inflate(inflater, container, false)
        (activity as MainActivity).updateTittle(" ")
        networkStateReceiver = NetworkStateReceiver()
        networkStateReceiver!!.addListener(this)
        context?.registerReceiver(
            networkStateReceiver,
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )
        viewBindings()
        return binding.root
    }

    private fun viewBindings() {
        binding.apply {
            recyclerView.apply {
                addItemDecoration(
                    DividerItemDecoration(
                        context,
                        DividerItemDecoration.VERTICAL
                    )
                )
                adapter = newsAdapter
                recyclerView.layoutManager = LinearLayoutManager(context)
            }

            newsSourceViewModel.news.observe(viewLifecycleOwner, {
                when (it.status) {
                    NewsResource.Status.LOADING -> {
                        loading.visibility = View.VISIBLE
                        errorTxt.visibility = View.GONE
                    }

                    NewsResource.Status.SUCCESS -> {
                        newsAdapter.submitList(it.data?.articles)
                        recyclerView.scheduleLayoutAnimation()
                        loading.visibility = View.GONE
                        errorTxt.visibility = View.GONE
                    }

                    NewsResource.Status.ERROR -> {
                        Log.e("TAG", it.message + " error")
                        loading.visibility = View.GONE
                        errorTxt.visibility = View.VISIBLE
                    }
                }
            })

        }
    }

    override fun networkAvailable() {
        if (binding.errorTxt.visibility == View.VISIBLE) {
            newsSourceViewModel.fetchNews()
        }
    }

    override fun networkUnavailable() {
        Snackbar.make(
            binding.recyclerView,
            "Please check your internet connection",
            Snackbar.LENGTH_SHORT
        ).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        networkStateReceiver?.removeListener(this)
        context?.unregisterReceiver(networkStateReceiver)
    }

}