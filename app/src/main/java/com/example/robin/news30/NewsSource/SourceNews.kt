package com.example.robin.news30.NewsSource


import android.os.Bundle
import android.preference.PreferenceManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.robin.news30.Data.NewsAdapter
import com.example.robin.news30.NewsListFragment
import com.example.robin.news30.R
import com.example.robin.news30.databinding.FragmentSourceNewsBinding
import org.koin.android.viewmodel.ext.android.viewModel


class SourceNews : Fragment() {

    private val newsSourceViewModel: NewsSourceViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentSourceNewsBinding>(inflater,
            R.layout.fragment_source_news, container, false)
        //val newsSourceViewModel = ViewModelProviders.of(this).get(NewsSourceViewModel::class.java)

        val sp = PreferenceManager.getDefaultSharedPreferences(context)
        val source = sp.getString("source", "the-verge")

        newsSourceViewModel.source = source

        newsSourceViewModel.fetchRepos()

        binding.recyclerView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        binding.recyclerView.adapter = context?.let { NewsAdapter(newsSourceViewModel, this, it) }
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.setItemViewCacheSize(8)

        newsSourceViewModel.news.observe(this, Observer {
            if(it != null){
                binding.recyclerView.visibility = View.VISIBLE
            }
        })

        newsSourceViewModel.newsLoadError.observe(this, Observer {
            if(it){
                binding.loading.visibility = View.GONE
                binding.errorTxt.visibility = View.VISIBLE
                binding.recyclerView.visibility = View.GONE
            }else{
                binding.loading.visibility = View.VISIBLE
                binding.errorTxt.visibility = View.GONE
                binding.recyclerView.visibility = View.VISIBLE
            }
        })

        newsSourceViewModel.loading.observe(this, Observer {
            if(it){
                binding.loading.visibility = View.VISIBLE
                binding.errorTxt.visibility = View.GONE
                binding.recyclerView.visibility = View.GONE
            }else{
                binding.loading.visibility = View.GONE

            }
        })
        return binding.root
    }


}
