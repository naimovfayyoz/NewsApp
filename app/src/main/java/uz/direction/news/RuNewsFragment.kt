package uz.direction.news

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import uz.direction.news.data.network.Country
import uz.direction.news.data.network.RetrofitService
import uz.direction.news.data.adapter.RecyclerViewAdapter
import uz.direction.news.data.adapter.ViewPagerAdapter
import uz.direction.news.data.model.News
import uz.direction.news.data.repository.NewsRepository
import uz.direction.news.databinding.RuNewsFragmentBinding
import java.text.FieldPosition


class RuNewsFragment : Fragment(R.layout.ru_news_fragment) {


    private var _binding: RuNewsFragmentBinding? = null
    private val binding: RuNewsFragmentBinding get() = _binding!!

    private val myAdapter by lazy { RecyclerViewAdapter() }
    private val repository = NewsRepository(RetrofitService.newsService)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = RuNewsFragmentBinding.inflate(layoutInflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val position = arguments?.getInt(POSITION_ARG)

        position?.let {
            when (position) {
                0 -> {
                    repository.getNews(Country.US)
                }
                1 -> {
                    repository.getNews(Country.RU)
                }
            }
        }
        setupRecyclerView()
        repository.newsLiveData.observe(viewLifecycleOwner) { response ->
            myAdapter.setData(response.articles)
            myAdapter.setOnClickListener { _, position ->
                val intent =
                    Intent(Intent.ACTION_VIEW).setData(Uri.parse(response.articles[position].url))
                startActivity(intent)
            }
        }

    }

    companion object {
        var POSITION_ARG = "position_arg"

        @JvmStatic
        fun newInstance(position: Int) = RuNewsFragment().apply {
            arguments = Bundle().apply {
                putInt(POSITION_ARG, position)
            }
        }
    }


    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = myAdapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        repository.cancelJob()
    }
}