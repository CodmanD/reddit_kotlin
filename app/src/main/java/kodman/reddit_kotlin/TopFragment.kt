package kodman.reddit_kotlin

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.setContentView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kodman.reddit_kotlin.databinding.TopFragmentBinding
import kodman.reddit_kotlin.repository.RedditApiService
import kodman.reddit_kotlin.repository.TopRepository

class TopFragment : Fragment(R.layout.top_fragment){

    companion object {
        fun newInstance() = TopFragment()
    }
    private var binding: TopFragmentBinding? = null





    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.top_fragment, container, false)
        this.binding = TopFragmentBinding.bind(view)
        return view


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        val api = RedditApiService()
        val repository = TopRepository(api)

       val factory = TopViewModelFactory(repository)
       val viewModel = ViewModelProviders.of(this,factory).get(TopViewModel::class.java)

         viewModel.getPosts()

        binding?.postRV?.layoutManager = LinearLayoutManager(this.requireContext())
        viewModel.posts.observe(this.viewLifecycleOwner, Observer { posts ->
            binding?.postRV.also {

                it?.layoutManager = LinearLayoutManager(this.context)
                it?.setHasFixedSize(true)
              it?.adapter = TopRecyclerViewAdapter(posts)
            }
        })
    }




}