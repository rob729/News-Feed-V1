package com.robin.news30.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.robin.news30.databinding.ItemRowBinding
import com.robin.news30.model.Articles
import com.robin.news30.utils.ImageLoader
import com.robin.news30.utils.Utils

class NewsAdapter(
    val imageLoader: ImageLoader,
    val utils: Utils
) :
    ListAdapter<Articles, NewsAdapter.ViewHolder>(ArticleDiffCallbacks()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRowBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class ViewHolder(private val binding: ItemRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Articles) {
            binding.apply {
                item.urlToImage?.let { imageLoader.loadImage(imgNews, it) }
                titleNews.text = item.title
                card.preventCornerOverlap = false
                detail.text = item.description
                relLayout.setOnClickListener { utils.setWebView(item.url) }
            }
        }

    }

    class ArticleDiffCallbacks : DiffUtil.ItemCallback<Articles>() {
        override fun areItemsTheSame(oldItem: Articles, newItem: Articles): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Articles, newItem: Articles): Boolean {
            return oldItem == newItem
        }
    }
}