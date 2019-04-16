package com.example.robin.news30


import android.os.Bundle
import android.preference.PreferenceManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.robin.news30.databinding.FragmentNewsListBinding
import com.squareup.picasso.Picasso


class NewsListFragment : Fragment() {

    lateinit var source: String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentNewsListBinding>(inflater, R.layout.fragment_news_list, container, false )
        val action = NewsListFragmentDirections.actionNewsListFragmentToSourceNews()
        if(!Utils.hasNetwork(context)) {
            val toast = Toast.makeText(context, "No Internet Conection", Toast.LENGTH_LONG)
            toast.show()
        }
        val sp = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = sp.edit()
        Picasso.get().load("https://cdn.vox-cdn.com/uploads/chorus_asset/file/7395359/ios-icon.0.png")
            .into(binding.vergeLogo)
        Picasso.get().load("https://www.wired.com/apple-touch-icon.png").into(binding.wiredLogo)
        Picasso.get().load("https://icon-locator.herokuapp.com/lettericons/T-120-ffffff.png")
            .into(binding.hinduLogo)
        Picasso.get()
            .load("https://cdn.techcrunch.cn/wp-content/themes/vip/techcrunch-cn-2014/assets/images/homescreen_TCIcon_ipad_2x.png")
            .into(binding.techcrunchLogo)
        Picasso.get().load("http://a.espncdn.com/wireless/mw5/r1/images/bookmark-icons-v2/espn-icon-120x120.png")
            .into(binding.espnLogo)
        Picasso.get().load("https://www.redditstatic.com/mweb2x/favicon/120x120.png").into(binding.redditLogo)
        Picasso.get()
            .load("https://cdn0.tnwcdn.com/wp-content/themes/cyberdelia/assets/icons/apple-touch-icon-120x120.png?v=1540388712")
            .into(binding.tnwLogo)
        Picasso.get()
            .load("https://s.blogsmithmedia.com/www.engadget.com/assets-h530cd5ea4f940095be1a3f313af013dc/images/apple-touch-icon-120x120.png?h=232a14b1a350de05a49b584a62abac9e")
            .into(binding.engadgetLogo)
        Picasso.get()
            .load("https://www.newscientist.com/wp-content/themes/new-scientist/img/layup/touch-icon/144x144.png")
            .into(binding.scientistLogo)


        binding.verge.setOnClickListener {
            source = "the-verge"
            editor.putString("source", source)
            editor.apply()
            findNavController().navigate(action)
        }

        binding.wired.setOnClickListener {
            source = "wired"
            editor.putString("source", source)
            editor.apply()
            findNavController().navigate(action)
        }

        binding.techcrunch.setOnClickListener {
            source = "techcrunch"
            editor.putString("source", source)
            editor.apply()
            findNavController().navigate(action)
        }

        binding.hindu.setOnClickListener {
            source = "the-hindu"
            editor.putString("source", source)
            editor.apply()
            findNavController().navigate(action)
        }

        binding.espn.setOnClickListener {
            source = "espn-cric-info"
            editor.putString("source", source)
            editor.apply()
            findNavController().navigate(action)
        }

        binding.reddit.setOnClickListener {
            source = "reddit-r-all"
            editor.putString("source", source)
            editor.apply()
            findNavController().navigate(action)
        }

        binding.tnw.setOnClickListener {
            source = "the-next-web"
            editor.putString("source", source)
            editor.apply()
            findNavController().navigate(action)
        }
        binding.engadget.setOnClickListener {
            source = "engadget"
            editor.putString("source", source)
            editor.apply()
            findNavController().navigate(action)
        }
        binding.scientist.setOnClickListener {
            source = "new-scientist"
            editor.putString("source", source)
            editor.apply()
            findNavController().navigate(action)
        }

        return binding.root
    }



}
