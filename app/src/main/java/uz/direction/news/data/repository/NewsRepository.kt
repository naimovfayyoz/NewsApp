package uz.direction.news.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import uz.direction.news.data.network.Country
import uz.direction.news.data.model.News
import uz.direction.news.data.network.NewsService

class NewsRepository(private val newsService: NewsService) {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    val newsLiveData: MutableLiveData<News> = MutableLiveData()

    fun getNews(country: Country) {
        coroutineScope.launch {
            val newsResponse = newsService.getNews(country)
            Log.d("TAG1", "getNews: ")

            if (newsResponse.isSuccessful) {
                Log.d("TAG1", "getNews: ")
                newsLiveData.postValue(newsResponse.body())
            }
        }
    }

    fun cancelJob() {
        coroutineScope.cancel()
    }
}