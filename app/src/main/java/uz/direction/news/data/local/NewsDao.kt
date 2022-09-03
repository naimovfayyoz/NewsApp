package uz.direction.news.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import uz.direction.news.model.Article
import uz.direction.news.model.BreakingNews

@Dao
interface NewsDao {

    @Query("SELECT * FROM breaking_news INNER JOIN news_articles ON articleUrl = url")
    fun getAllBreakingNewsArticles(): Flow<List<Article>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticles(articles: List<Article>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBreakingNews(breakingNews: List<BreakingNews>)

    @Query("DELETE FROM breaking_news")
    suspend fun deleteAllBreakingNews()
}