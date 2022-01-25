package ru.mrrobot1413.testTask.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.mrrobot1413.testTask.data.GetPostsResponseData
import ru.mrrobot1413.testTask.repository.CacheRepository
import ru.mrrobot1413.testTask.repository.PostsRepository
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val postsRepository: PostsRepository,
    private val cacheRepository: CacheRepository
) : ViewModel() {

    private val _posts = MutableLiveData<PagingData<GetPostsResponseData>>()
    val posts = _posts

    private val _error = MutableLiveData<String>()
    val error = _error

    fun getPosts() {
        viewModelScope.launch {
            try {
                postsRepository.getPosts().cachedIn(viewModelScope).collect {
                    _posts.postValue(it)
                }
            } catch (e: Exception) {
                _error.postValue(e.message)
            }
        }
    }
}