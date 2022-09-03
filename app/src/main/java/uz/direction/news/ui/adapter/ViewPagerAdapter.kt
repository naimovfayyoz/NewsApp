package uz.direction.news.ui.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import uz.direction.news.ui.CategoryNewsFragment

class ViewPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 7

    override fun createFragment(position: Int) = CategoryNewsFragment.newInstance(position)

}

