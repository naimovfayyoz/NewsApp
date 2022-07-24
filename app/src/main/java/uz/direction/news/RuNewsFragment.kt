package uz.direction.news

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import uz.direction.news.data.network.Category
import uz.direction.news.data.network.RetrofitService
import uz.direction.news.data.adapter.RecyclerViewAdapter
import uz.direction.news.data.repository.NewsRepository
import uz.direction.news.databinding.RuNewsFragmentBinding


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
        setupRecyclerView()

        lifecycleScope.launchWhenStarted {
            position?.let {
                when (position) {
                    0 -> {
                        repository.quotes.collect{
                            if (it is Resource.Success) {
                                val data=it.data!!
                                    myAdapter.submitList(data[0].articles)
                            }
                        }

                    }
//                    1 -> {
//                        repository.getRuNews(Category.ENTERTAINMENT)
//                    }
//                    2 -> {
//                        repository.getRuNews(Category.BUSINESS)
//                    }
//                    3 -> {
//                        repository.getRuNews(Category.HEALTH)
//                    }
//                    4 -> {
//                        repository.getRuNews(Category.SCIENCE)
//                    }
//                    5 -> {
//                        repository.getRuNews(Category.SPORTS)
//                    }
//                    6 -> {
//                        repository.getRuNews(Category.TECHNOLOGY)
//                    }
                    else -> {}
                }
            }
        }



//        repository.newsLiveData.observe(viewLifecycleOwner) { response ->
//            myAdapter.setData(response.articles)
//            myAdapter.setOnClickListener { _, position ->
//                val intent =
//                    Intent(Intent.ACTION_VIEW).setData(Uri.parse(response.articles[position].url))
//                startActivity(intent)
//            }
//        }

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