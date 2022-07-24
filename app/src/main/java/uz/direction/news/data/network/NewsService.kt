package uz.direction.news.data.network

import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query
import uz.direction.news.Resource
import uz.direction.news.data.model.Article
import uz.direction.news.data.model.News

interface NewsService {

    @GET("v2/top-headlines?language=en")
    suspend fun getRuNews(@Query("category") category: Category): Resource<List<News>>
}