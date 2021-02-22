package kodman.reddit_kotlin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kodman.reddit_kotlin.repository.TopRepository


@Suppress("UNCHECKED_CAST")
class TopViewModelFactory(
    private val repository: TopRepository
) : ViewModelProvider.NewInstanceFactory(){



        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return TopViewModel(repository) as T
        }


    }