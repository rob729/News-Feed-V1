package com.example.robin.news30.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.NonNull
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.example.robin.news30.R
import com.example.robin.news30.databinding.ItemRowBinding
import com.example.robin.news30.model.Articles
import com.example.robin.news30.viewmodel.NewsSourceViewModel
import com.thefinestartist.finestwebview.FinestWebView
import java.util.*

class NewsAdapter internal constructor(
    listViewModel: NewsSourceViewModel,
    lifecycleOwner: LifecycleOwner,
    private val ctx: Context
) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {


    private var articlesList: ArrayList<Articles>? = ArrayList()

    var binding: ItemRowBinding? = null

    val itemCounts: Int = articlesList!!.size

    init {
        listViewModel.news.observe(lifecycleOwner, Observer { repos ->

            if (articlesList != null)
                articlesList!!.clear()
            if (repos != null) {
                Log.e("TAG", "" + repos.totalResults)
                articlesList = ArrayList(repos.articles)
                notifyDataSetChanged()

            }
        })
    }

    @NonNull
    override fun onCreateViewHolder(@NonNull viewGroup: ViewGroup, i: Int): ViewHolder {
        val view =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.item_row, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(@NonNull viewHolder: ViewHolder, i: Int) {
        viewHolder.bind(articlesList!![i])
    }

    override fun getItemCount(): Int {
        return articlesList!!.size
    }

    inner class ViewHolder(@NonNull itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            binding = DataBindingUtil.bind(itemView)
            itemView.setOnClickListener {

                FinestWebView.Builder(ctx)
                    .showUrl(true)
                    .showSwipeRefreshLayout(true)
                    .webViewBuiltInZoomControls(true)
                    .titleColorRes(R.color.white)
                    .urlColorRes(R.color.white)
                    .show(articlesList!![adapterPosition].url)
            }
        }

        fun bind(articles: Articles) {
            binding?.imgNews?.load(articles.urlToImage) {
                crossfade(true)
                placeholder(R.drawable.ic_loading)
                binding?.imgNews?.scaleType = ImageView.ScaleType.CENTER_CROP
            }
            binding!!.titleNews.text = articles.title
            binding!!.card.preventCornerOverlap = false
            binding!!.detail.text = articles.description
        }
    }
}
