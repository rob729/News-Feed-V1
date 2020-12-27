package com.robin.news30.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
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
import kotlinx.coroutines.launch
import javax.inject.Inject


class NewsListFragment : BaseFragment(), View.OnClickListener {

    @Inject
    lateinit var imageLoader: ImageLoader

    @Inject
    lateinit var preferenceRepository: PreferenceRepository

    @Inject
    lateinit var utils: Utils

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
        viewBindings()
        return binding.root
    }

    private fun viewBindings() {
        binding.apply {
            vergeLogo.loadNewsLogo()
            wiredLogo.loadNewsLogo()
            techcrunchLogo.loadNewsLogo()
            hinduLogo.loadNewsLogo()
            espnLogo.loadNewsLogo()
            redditLogo.loadNewsLogo()
            tnwLogo.loadNewsLogo()
            engadgetLogo.loadNewsLogo()
            scientistLogo.loadNewsLogo()

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

    }

    private fun setInformation(source: String) {
        lifecycleScope.launch {
            preferenceRepository.setNewsSource(source)
        }
        args.apply {
            putString("source", source)
            putString("Name", utils.getName(source))
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
        setInformation(getNewsDomain(v))
    }

    private fun getNewsDomain(view: View?): String {
        return when (view) {
            binding.verge, binding.vergeLogo -> getString(R.string.verge_domain)
            binding.wired, binding.wiredLogo -> getString(R.string.wired_domain)
            binding.techcrunch, binding.techcrunchLogo -> getString(R.string.techcrunch_domain)
            binding.hindu, binding.hinduLogo -> getString(R.string.the_hindu_domain)
            binding.reddit, binding.redditLogo -> getString(R.string.reddit_domain)
            binding.espn, binding.espnLogo -> getString(R.string.espn_domain)
            binding.tnw, binding.tnwLogo -> getString(R.string.the_next_web_domain)
            binding.scientist, binding.scientistLogo -> getString(R.string.new_scientist_domain)
            binding.engadget, binding.engadgetLogo -> getString(R.string.engadget_domain)
            else -> getString(R.string.verge_domain)
        }
    }

    private fun ImageView.loadNewsLogo() {
        imageLoader.loadImage(this, utils.getImageUrlFromDomainName(getNewsDomain(this)))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
