package ru.mrrobot1413.testTask.ui.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.mrrobot1413.testTask.R
import ru.mrrobot1413.testTask.databinding.FragmentHomeBinding
import ru.mrrobot1413.testTask.network.RequestStatus

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {
    private val binding: FragmentHomeBinding by viewBinding()

    private val homeViewModel by lazy {
        ViewModelProvider(this)[HomeViewModel::class.java]
    }

    private val adapter by lazy {
        HomeFragmentRecyclerViewAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        initObservers()
    }

    private fun init() {
        with(binding) {
            homeViewModel.getPosts(1)

            recyclerView.adapter = adapter.withLoadStateFooter(HomeLoadStateFooter())
            recyclerView.layoutManager = LinearLayoutManager(requireContext())

            lifecycleScope.launch {
                adapter.loadStateFlow.collectLatest { loadStates ->
                    progress.isVisible = loadStates.refresh is LoadState.Loading
                    recyclerView.isVisible = loadStates.refresh is LoadState.NotLoading
                }
            }
        }
    }

    private fun initObservers() {
        homeViewModel.posts.observe(viewLifecycleOwner) {
            lifecycleScope.launch { adapter.submitData(it) }
        }
        homeViewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }
}