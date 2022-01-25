package ru.mrrobot1413.testTask.ui.home

import android.os.Bundle
import android.os.Handler
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
import androidx.recyclerview.widget.DividerItemDecoration
import kotlinx.coroutines.delay
import ru.mrrobot1413.testTask.utils.NetworkUtil


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
            homeViewModel.getPosts()

            recyclerView.adapter = adapter.withLoadStateFooter(HomeLoadStateFooter())
            recyclerView.layoutManager = LinearLayoutManager(requireContext())

            val dividerItemDecoration = DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
            recyclerView.addItemDecoration(dividerItemDecoration)

            lifecycleScope.launch {
                adapter.loadStateFlow.collectLatest { loadStates ->
                    progress.isVisible = loadStates.refresh is LoadState.Loading
                    recyclerView.isVisible = loadStates.refresh is LoadState.NotLoading
                    btnRetry.isVisible = loadStates.refresh is LoadState.Error
                    binding.btnRetry.isVisible = !NetworkUtil.isInternetAvailable(requireContext())
                }
            }

            btnRetry.setOnClickListener {
                homeViewModel.getPosts()
            }
        }
    }

    private fun initObservers() {
        homeViewModel.posts.observe(viewLifecycleOwner) {
            lifecycleScope.launch {
                adapter.submitData(it)
            }
        }
        homeViewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }
}