package kodman.reddit_kotlin

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("image")
fun loadImage(view: ImageView, url: String) {
    Glide.with(view)

        .load(url)
        .placeholder(view.context.getDrawable(R.drawable.ic_launcher_foreground))
        .into(view)
}