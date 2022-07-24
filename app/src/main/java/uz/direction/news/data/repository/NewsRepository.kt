package uz.direction.news.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import uz.direction.news.Resource
import uz.direction.news.data.model.Article
import uz.direction.news.data.network.Category
import uz.direction.news.data.model.News
import uz.direction.news.data.network.NewsService

class NewsRepository(private val newsService: NewsService) {
    private val _quotes = MutableStateFlow<Resource<List<News>>>(Resource.InitialState())
    val quotes = _quotes.asStateFlow()

    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    // val newsLiveData: MutableLiveData<News> = MutableLiveData()


    private fun getNews(category:Category)=coroutineScope.launch {
            _quotes.value=newsService.getRuNews(category)
        }


    fun cancelJob() {
        coroutineScope.cancel()
    }
}