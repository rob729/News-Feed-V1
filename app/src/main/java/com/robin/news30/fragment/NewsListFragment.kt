package com.robin.news30.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import coil.api.load
import coil.transform.CircleCropTransformation
import com.bumptech.glide.RequestManager
import com.robin.news30.R
import com.robin.news30.activity.MainActivity
import com.robin.news30.databinding.FragmentNewsListBinding
import com.robin.news30.utils.Utils
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_news_list.*
import javax.inject.Inject


class NewsListFragment : DaggerFragment(), View.OnClickListener {

    @Inject
    lateinit var requestManager: RequestManager

    private val args = Bundle()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentNewsListBinding>(
            inflater,
            R.layout.fragment_news_list,
            container,
            false
        )

        (activity as MainActivity).title_toolbar.text = resources.getString(R.string.app_name)

        if (!Utils.hasNetwork(context)) {
            val toast = Toast.makeText(context, "No Internet Conection", Toast.LENGTH_LONG)
            toast.show()
        }

        loadImageIntoImageView(binding.vergeLogo, Utils.getImageUrlFromSourceName("the-verge"))
        loadImageIntoImageView(binding.wiredLogo, Utils.getImageUrlFromSourceName("wired"))
        loadImageIntoImageView(binding.techcrunchLogo, Utils.getImageUrlFromSourceName("techcrunch"))
        loadImageIntoImageView(binding.hinduLogo, Utils.getImageUrlFromSourceName("the-hindu"))
        loadImageIntoImageView(binding.espnLogo, Utils.getImageUrlFromSourceName("espn-cric-info"))
        loadImageIntoImageView(binding.redditLogo, Utils.getImageUrlFromSourceName("reddit-r-all"))
        loadImageIntoImageView(binding.tnwLogo, Utils.getImageUrlFromSourceName("the-next-web"))
        loadImageIntoImageView(binding.engadgetLogo, Utils.getImageUrlFromSourceName("engadget"))
        loadImageIntoImageView(binding.scientistLogo, Utils.getImageUrlFromSourceName("new-scientist"))

        binding.verge.setOnClickListener(this)
        binding.wired.setOnClickListener(this)
        binding.techcrunch.setOnClickListener(this)
        binding.hindu.setOnClickListener(this)
        binding.espn.setOnClickListener(this)
        binding.reddit.setOnClickListener(this)
        binding.tnw.setOnClickListener(this)
        binding.engadget.setOnClickListener(this)
        binding.scientist.setOnClickListener(this)

        return binding.root
    }

    private fun setInformation(source: String) {
        args.putString("source", source)
        args.putString("Name", Utils.setName(source))
        val navOptions =
            NavOptions.Builder().setEnterAnim(R.anim.nav_default_enter_anim).setExitAnim(
                R.anim.nav_default_exit_anim
            ).setPopEnterAnim(R.anim.nav_default_pop_enter_anim)
                .setPopExitAnim(R.anim.nav_default_pop_exit_anim)
                .build()
        findNavController().navigate(R.id.sourceNews, args, navOptions)
    }

    override fun onClick(v: View?) {
        Log.e("TAG", "$v view")
        when(v){
            verge -> { setInformation("the-verge")
                Log.e("TAG", "verge view")}
            wired -> { setInformation("wired") }
            techcrunch -> { setInformation("techcrunch") }
            hindu-> { setInformation("the-hindu") }
            reddit -> {  setInformation("reddit-r-all") }
            espn -> { setInformation("espn-cric-info") }
            tnw -> { setInformation("the-next-web") }
            scientist -> { setInformation("new-scientist") }
            engadget -> {  setInformation("engadget") }
            else -> { Log.e("TAG", "$v view")}
        }
    }

    private fun loadImageIntoImageView(imageView: ImageView, url: String){
        requestManager.load(url)
            .circleCrop()
            .into(imageView)
    }

}
