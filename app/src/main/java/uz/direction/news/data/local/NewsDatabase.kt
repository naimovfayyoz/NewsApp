package uz.direction.news.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import uz.direction.news.model.Article
import uz.direction.news.model.BreakingNews

@Database(entities = [Article::class, BreakingNews::class], version = 1)
abstract class NewsArticleDatabase : RoomDatabase() {

    abstract fun newsArticleDao(): NewsDao
}