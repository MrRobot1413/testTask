package ru.mrrobot1413.testTask.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import ru.mrrobot1413.testTask.data.GetPostsResponseData
import ru.mrrobot1413.testTask.databinding.ItemListHomeBinding

class HomeFragmentRecyclerViewAdapter : PagingDataAdapter<GetPostsResponseData, HomeFragmentRecyclerViewHolder>(
    object : DiffUtil.ItemCallback<GetPostsResponseData>() {
        override fun areItemsTheSame(
            oldItem: GetPostsResponseData,
            newItem: GetPostsResponseData
        ): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: GetPostsResponseData,
            newItem: GetPostsResponseData
        ): Boolean = newItem == oldItem
    }
)  {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeFragmentRecyclerViewHolder {
        return HomeFragmentRecyclerViewHolder(
            ItemListHomeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HomeFragmentRecyclerViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        }
    }
}