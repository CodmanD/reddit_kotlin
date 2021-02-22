package kodman.reddit_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import kodman.reddit_kotlin.repository.TopRepository
import kodman.reddit_kotlin.repository.RedditApiService


import androidx.lifecycle.Observer
import kodman.reddit_kotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }


}

//class PostRecyclerAdapter(private val values: List<Post>) :
//    RecyclerView.Adapter<PostRecyclerAdapter.PostViewHolder>() {
//
//   lateinit var     binding : ItemPostBinding
//
//    override fun getItemCount() = values.size
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
////        val itemView =
////            LayoutInflater.from(parent?.context).inflate(R.layout.item_post, parent, false)
//
//    var  inflater :   LayoutInflater = LayoutInflater.from(parent.getContext())
//           this.binding = DataBindingUtil.inflate(inflater, R.layout.item_post, parent, false)
//        println("----------"+binding.toString())
//        return PostViewHolder(this.binding.root)
//       // return MyViewHolder(itemView)
//    }
//
//    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
//
//        binding.TVAuthor.text=values[position].author
//        binding.TVDescription.text=values[position].description
//binding.invalidateAll()
//
//       // holder.tvAuthor?.text = values[position].author
//      //  holder.tvDescription?.text = values[position].description
//    }
//
//    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//      //  var tvAuthor: TextView? = null
//      //  var tvDescription: TextView? = null
//
//
////        var binding = inflate(
////        itemView,
////        R.layout.item_post,
////        itemView,
////        false
////        )
//
//        init {
//
//        //    tvAuthor = bindingAdapter.TVAuthor)
//         //   tvDescription = itemView?.findViewById(R.id.TVDescription)
//
////            tvAuthor = itemView?.findViewById(R.id.TVAuthor)
////            tvDescription = itemView?.findViewById(R.id.TVDescription)
//        }
//    }


//}