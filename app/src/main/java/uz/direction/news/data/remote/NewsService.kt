package uz.direction.news.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import uz.direction.news.model.News
import uz.direction.news.utill.Category

interface NewsService {

    @GET("v2/top-headlines?language=en")
    suspend fun getRuNews(@Query("category") category: Category): Response<News>
}