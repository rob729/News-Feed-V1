package com.example.robin.news30.Data

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.robin.news30.NewsSource.NewsSourceViewModel
import com.example.robin.news30.R
import com.squareup.picasso.Picasso
import java.util.ArrayList
import com.example.robin.news30.databinding.LayoutRowBinding

class NewsAdapter internal constructor(
    listViewModel: NewsSourceViewModel,
    lifecycleOwner: LifecycleOwner,
    private val ctx: Context
) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {


    private var articlesList: ArrayList<Articles>? = ArrayList()

    var binding: LayoutRowBinding? = null

    val itemCounts: Int = articlesList!!.size

    init {
        listViewModel.news.observe(lifecycleOwner, Observer{ repos ->

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
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.layout_row, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(@NonNull viewHolder: ViewHolder, i: Int) {
        viewHolder.bind(articlesList!![i])
    }

    override fun getItemCount(): Int {
         return  articlesList!!.size
    }

    inner class ViewHolder(@NonNull itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            binding = DataBindingUtil.bind(itemView)
            itemView.setOnClickListener {
                val i = Intent(Intent.ACTION_VIEW, Uri.parse(articlesList!![adapterPosition].url))
                ctx.startActivity(i)
            }
        }

        fun bind(articles: Articles) {
            Picasso.get().load(articles.urlToImage).into(binding?.imgNews)
            binding!!.titleNews.text  = articles.title
            binding!!.detail.text  = articles.description
        }
    }
}
