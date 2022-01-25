package ru.mrrobot1413.testTask.ui.home

import androidx.recyclerview.widget.RecyclerView
import ru.mrrobot1413.testTask.data.GetPostsResponseData
import ru.mrrobot1413.testTask.databinding.ItemListHomeBinding

class HomeFragmentRecyclerViewHolder(private val binding: ItemListHomeBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: GetPostsResponseData) {
        with(binding) {
            txtTitle.text = item.title
            txtBody.text = item.body
        }
    }
}