package com.robin.news30.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.robin.news30.R
import com.robin.news30.activity.MainActivity
import com.robin.news30.databinding.FragmentNewsListBinding
import com.robin.news30.utils.ImageLoader
import com.robin.news30.utils.PreferenceRepository
import com.robin.news30.utils.Utils
import com.techyourchance.dagger2course.screens.common.fragments.BaseFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


class NewsListFragment : BaseFragment(), View.OnClickListener {

    @Inject
    lateinit var imageLoader: ImageLoader

    @Inject
    lateinit var preferenceRepository: PreferenceRepository

    private var _binding: FragmentNewsListBinding? = null
    private val binding get() = _binding!!

    private val args = Bundle()

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

        _binding = FragmentNewsListBinding.inflate(inflater, container, false)

        (activity as MainActivity).updateTittle(resources.getString(R.string.app_name))

//        if (!Utils.hasNetwork(context)) {
//            val toast = Toast.makeText(context, "No Internet Connection", Toast.LENGTH_LONG)
//            toast.show()
//        }

        binding.apply {
            imageLoader.loadImage(vergeLogo, Utils.getImageUrlFromSourceName("the-verge"))
            imageLoader.loadImage(wiredLogo, Utils.getImageUrlFromSourceName("wired"))
            imageLoader.loadImage(techcrunchLogo, Utils.getImageUrlFromSourceName("techcrunch"))
            imageLoader.loadImage(hinduLogo, Utils.getImageUrlFromSourceName("the-hindu"))
            imageLoader.loadImage(espnLogo, Utils.getImageUrlFromSourceName("espn-cric-info"))
            imageLoader.loadImage(redditLogo, Utils.getImageUrlFromSourceName("reddit-r-all"))
            imageLoader.loadImage(tnwLogo, Utils.getImageUrlFromSourceName("the-next-web"))
            imageLoader.loadImage(engadgetLogo, Utils.getImageUrlFromSourceName("engadget"))
            imageLoader.loadImage(scientistLogo, Utils.getImageUrlFromSourceName("new-scientist"))

            verge.setOnClickListener(this@NewsListFragment)
            wired.setOnClickListener(this@NewsListFragment)
            techcrunch.setOnClickListener(this@NewsListFragment)
            hindu.setOnClickListener(this@NewsListFragment)
            espn.setOnClickListener(this@NewsListFragment)
            reddit.setOnClickListener(this@NewsListFragment)
            tnw.setOnClickListener(this@NewsListFragment)
            engadget.setOnClickListener(this@NewsListFragment)
            scientist.setOnClickListener(this@NewsListFragment)
        }

        return binding.root
    }

    private fun setInformation(source: String) {
        lifecycleScope.launch {
            preferenceRepository.setNewsSource(source)
        }
        args.apply {
            putString("source", source)
            putString("Name", Utils.getName(source))
        }
        val navOptions =
            NavOptions.Builder().setEnterAnim(R.anim.nav_default_enter_anim).setExitAnim(
                R.anim.nav_default_exit_anim
            ).setPopEnterAnim(R.anim.nav_default_pop_enter_anim)
                .setPopExitAnim(R.anim.nav_default_pop_exit_anim)
                .build()
        findNavController().navigate(R.id.sourceNews, args, navOptions)
    }

    override fun onClick(v: View?) {
        val newsSource = when (v) {
            binding.verge -> "the-verge"
            binding.wired -> "wired"
            binding.techcrunch -> "techcrunch"
            binding.hindu -> "the-hindu"
            binding.reddit -> "reddit-r-all"
            binding.espn -> "espn-cric-info"
            binding.tnw -> "the-next-web"
            binding.scientist -> "new-scientist"
            binding.engadget -> "engadget"
            else -> "the-verge"
        }
        setInformation(newsSource)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
