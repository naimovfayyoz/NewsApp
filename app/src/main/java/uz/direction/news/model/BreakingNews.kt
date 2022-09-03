package uz.direction.news.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "breaking_news")
data class BreakingNews(
    val articleUrl: String,
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
)