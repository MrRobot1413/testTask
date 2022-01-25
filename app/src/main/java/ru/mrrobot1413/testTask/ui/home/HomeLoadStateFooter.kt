package ru.mrrobot1413.testTask.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.mrrobot1413.testTask.databinding.ItemLoadFooterBinding

class HomeLoadStateFooter:
    LoadStateAdapter<HomeLoadStateFooter.LoadStateViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val binding = ItemLoadFooterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return LoadStateViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) = holder.bind(loadState)

    inner class LoadStateViewHolder(private val binding: ItemLoadFooterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(loadState: LoadState) {
            with(binding) {
                progress.isVisible = loadState is LoadState.Loading
            }
        }
    }
}