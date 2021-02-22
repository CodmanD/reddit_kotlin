package kodman.reddit_kotlin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kodman.reddit_kotlin.model.Children
import kodman.reddit_kotlin.model.Top
import kodman.reddit_kotlin.repository.TopRepository
import kodman.reddit_kotlin.util.Coroutines
import kotlinx.coroutines.Job

class TopViewModel(

    private val repository: TopRepository) :
    ViewModel() {
    private lateinit var job: Job


    val _posts = MutableLiveData<Top>()
    val posts: LiveData<Top>
        get() = _posts




    fun getPosts() {
        job = Coroutines.ioThenMain(
            { repository.getPosts() },
            { _posts.value = it }
        )
    }

    override fun onCleared() {
        super.onCleared()
        if(::job.isInitialized) job.cancel()
    }



}