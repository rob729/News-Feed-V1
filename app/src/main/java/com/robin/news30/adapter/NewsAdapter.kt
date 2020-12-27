package com.robin.news30.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.robin.news30.databinding.ItemRowBinding
import com.robin.news30.model.Articles
import com.robin.news30.utils.ImageLoader
import com.robin.news30.utils.Utils

class NewsAdapter(val imageLoader: ImageLoader) :
    ListAdapter<Articles, NewsAdapter.ViewHolder>(ArticleDiffCallbacks()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemRowBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class ViewHolder(private val binding: ItemRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Articles) {
            item.urlToImage?.let { imageLoader.loadImage(binding.imgNews, it) }
            binding.titleNews.text = item.title
            binding.card.preventCornerOverlap = false
            binding.detail.text = item.description
            binding.relLayout.setOnClickListener { Utils.setWebView(item.url) }
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