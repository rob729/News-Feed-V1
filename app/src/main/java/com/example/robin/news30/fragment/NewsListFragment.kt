package com.example.robin.news30.fragment


import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import coil.api.load
import coil.transform.CircleCropTransformation
import com.example.robin.news30.R
import com.example.robin.news30.databinding.FragmentNewsListBinding
import com.example.robin.news30.utils.Utils

class NewsListFragment : Fragment() {

    private val args = Bundle()
    private val sp: SharedPreferences by lazy {
        PreferenceManager.getDefaultSharedPreferences(
            context
        )
    }
    private val editor: SharedPreferences.Editor by lazy { sp.edit() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentNewsListBinding>(
            inflater,
            R.layout.fragment_news_list,
            container,
            false
        )

        if (!Utils.hasNetwork(context)) {
            val toast = Toast.makeText(context, "No Internet Conection", Toast.LENGTH_LONG)
            toast.show()
        }

        binding.vergeLogo.load("https://cdn.vox-cdn.com/uploads/chorus_asset/file/7395359/ios-icon.0.png") {
            crossfade(true)
            placeholder(R.drawable.ic_panorama_black_24dp)
            transformations(CircleCropTransformation())

        }

        binding.wiredLogo.load("https://www.wired.com/apple-touch-icon.png") {
            crossfade(true)
            placeholder(R.drawable.ic_panorama_black_24dp)
            transformations(CircleCropTransformation())
        }

        binding.hinduLogo.load("https://icon-locator.herokuapp.com/lettericons/T-120-ffffff.png") {
            crossfade(true)
            placeholder(R.drawable.ic_panorama_black_24dp)
            transformations(CircleCropTransformation())
        }

        binding.techcrunchLogo.load("https://cdn.techcrunch.cn/wp-content/themes/vip/techcrunch-cn-2014/assets/images/homescreen_TCIcon_ipad_2x.png") {
            crossfade(true)
            placeholder(R.drawable.ic_panorama_black_24dp)
            transformations(CircleCropTransformation())
        }

        binding.espnLogo.load("https://images-na.ssl-images-amazon.com/images/I/21h-OE4-X7L._SY355_.png") {
            crossfade(true)
            placeholder(R.drawable.ic_panorama_black_24dp)
            transformations(CircleCropTransformation())
        }

        binding.redditLogo.load("https://www.redditstatic.com/mweb2x/favicon/120x120.png") {
            crossfade(true)
            placeholder(R.drawable.ic_panorama_black_24dp)
            transformations(CircleCropTransformation())
        }

        binding.tnwLogo.load("https://cdn0.tnwcdn.com/wp-content/themes/cyberdelia/assets/icons/apple-touch-icon-120x120.png?v=1540388712") {
            crossfade(true)
            placeholder(R.drawable.ic_panorama_black_24dp)
            transformations(CircleCropTransformation())
        }

        binding.engadgetLogo.load("https://s.blogsmithmedia.com/www.engadget.com/assets-h530cd5ea4f940095be1a3f313af013dc/images/apple-touch-icon-120x120.png?h=232a14b1a350de05a49b584a62abac9e") {
            crossfade(true)
            placeholder(R.drawable.ic_panorama_black_24dp)
            transformations(CircleCropTransformation())
        }

        binding.scientistLogo.load("https://www.newscientist.com/wp-content/themes/new-scientist/img/layup/touch-icon/144x144.png") {
            crossfade(true)
            placeholder(R.drawable.ic_panorama_black_24dp)
            transformations(CircleCropTransformation())
        }


        binding.verge.setOnClickListener {
            setInformation("the-verge")
        }

        binding.wired.setOnClickListener {
            setInformation("wired")
        }

        binding.techcrunch.setOnClickListener {
            setInformation("techcrunch")
        }

        binding.hindu.setOnClickListener {
            setInformation("the-hindu")
        }

        binding.espn.setOnClickListener {
            setInformation("espn-cric-info")
        }

        binding.reddit.setOnClickListener {
            setInformation("reddit-r-all")
        }

        binding.tnw.setOnClickListener {
            setInformation("the-next-web")
        }
        binding.engadget.setOnClickListener {
            setInformation("engadget")
        }
        binding.scientist.setOnClickListener {
            setInformation("new-scientist")
        }

        return binding.root
    }

    private fun setInformation(source: String) {
        editor.putString("source", source)
        editor.apply()
        args.putString("Name", setName(source))
        val navOptions =
            NavOptions.Builder().setEnterAnim(R.anim.nav_default_enter_anim).setExitAnim(
                R.anim.nav_default_exit_anim
            ).setPopEnterAnim(R.anim.nav_default_pop_enter_anim)
                .setPopExitAnim(R.anim.nav_default_pop_exit_anim)
                .build()
        findNavController().navigate(R.id.sourceNews, args, navOptions)
    }

    private fun setName(source: String): String {
        return when (source) {
            "the-verge" -> "Verge"
            "wired" -> "Wired"
            "techcrunch" -> "Techcrunch"
            "the-hindu" -> "The Hindu"
            "espn-cric-info" -> "ESPN"
            "reddit-r-all" -> "Reddit"
            "the-next-web" -> "The Next Web"
            "engadget" -> "Engadget"
            "new-scientist" -> "New Scientist"
            else -> "News"
        }
    }

}
