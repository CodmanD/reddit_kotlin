package kodman.reddit_kotlin


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import kodman.reddit_kotlin.databinding.ItemPostBinding
import kodman.reddit_kotlin.model.Top
import java.util.*


class TopRecyclerViewAdapter(
    private val posts: Top,

) : RecyclerView.Adapter<TopRecyclerViewAdapter.TopViewHolder>() {

    override fun getItemCount() = posts.data.children.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        TopViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_post,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: TopViewHolder, position: Int) {
        holder.itemPostBinding.children = posts.data.children[position].data


        var time = timeAgo(posts.data.children[position].data.created)
        holder.itemPostBinding.TVCommentsr.text=posts.data.children[position].data.num_comments.toString()+
                holder.itemView.context.getString(R.string.comments)

        holder.itemPostBinding.TVDate.text =
            time.toString() + holder.itemView.context.getString(R.string.hours_ago)

        //
//        if(position%2==0)
//            holder.itemView.setBackground(holder.itemView.resources.getDrawable(R.drawable.color_title));
//        else
//            holder.itemView.setBackground(holder.itemView.resources.getDrawable(R.drawable.color_title2));
//        //colorTile(holder.itemView,position)
        holder.itemPostBinding.postLL.setOnClickListener {

            val arg = posts.data.children[position].data.preview.images[0].source.url

           // Log.d("TAG", "push to : " + arg)
            val action = TopFragmentDirections.actionTopFragmentToPostFragment(arg)

            it.findNavController().navigate(action)
            // Toast.makeText(holder.itemView.getContext(),"OOOps"+posts[position].data.author,Toast.LENGTH_SHORT).show()

            // listener.onRecyclerViewItemClick(holder.recyclerviewMovieBinding.buttonBook, movies[position])
        }


//        holder.recyclerviewMovieBinding.buttonBook.setOnClickListener {
//            listener.onRecyclerViewItemClick(holder.recyclerviewMovieBinding.buttonBook, movies.data.children[position].data)
//        }
//        holder.recyclerviewMovieBinding.layoutLike.setOnClickListener {
//            listener.onRecyclerViewItemClick(holder.recyclerviewMovieBinding.layoutLike, movies.data.children[position].data)
//        }
    }

    @BindingAdapter("app:hideIfZero")
    fun colorTile(view: View, index: Int) {
        when(index)
        {
            1 -> view.setBackground(view.resources.getDrawable(R.drawable.ic_launcher_background));
           // Color.ORANGE -> "охотник"
        }

    }


    inner class TopViewHolder(
        val itemPostBinding: ItemPostBinding
    ) : RecyclerView.ViewHolder(itemPostBinding.root)


    fun timeAgo(created: Double): Int? {
        // val x: Double = LocalDateTime.now().millisecondsSinceEpoch - created.toInt() * 1000

        var timestamp = System.currentTimeMillis()

        val x = System.currentTimeMillis() - created.toLong()

        val dd = Date(x)

        val hours = Date(timestamp - created.toLong() * 1000).hours + 8

        val createdAt = Date(x)

        return hours


    }

}

