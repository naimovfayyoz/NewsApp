package uz.direction.news

import androidx.recyclerview.widget.DiffUtil
import uz.direction.news.data.model.Article

class ArticleComparator : DiffUtil.ItemCallback<Article>() {
    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Article, newItem: Article) = oldItem == newItem
}