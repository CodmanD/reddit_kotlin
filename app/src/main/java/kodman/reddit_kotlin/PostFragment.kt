package kodman.reddit_kotlin

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable

import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide

import kodman.reddit_kotlin.databinding.PostFragmentBinding
import java.io.File
import java.io.FileOutputStream


class PostFragment : Fragment() {

    companion object {
        fun newInstance() = PostFragment()
    }

    private var binding: PostFragmentBinding? = null
    lateinit var url: String
    //private lateinit var viewModel: PostViewModel

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val view = inflater.inflate(R.layout.post_fragment, container, false)
        this.binding = PostFragmentBinding.bind(view)

        this.binding?.toolbar?.inflateMenu(R.menu.menu)
        //setHasOptionsMenu(true)


        this.binding?.toolbar?.menu?.getItem(0)?.setOnMenuItemClickListener {
            saveImage()
        }
        return view
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)

    }

//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//
//        Log.d("TAG", "onCreateOptionsMenu")
//        inflater.inflate(R.menu.menu, menu)
//        super.onCreateOptionsMenu(menu, inflater)
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        Log.d("TAG", "ItemSelectedMenu ")
//        // Handle item selection
//        return when (item.itemId) {
//            R.id.download_menu -> {
//                Log.d("TAG", "Push menu ")
//                downLoadFile()
//                true
//            }
//
//            else -> super.onOptionsItemSelected(item)
//        }
//    }

    private fun saveImageStorage(finalBitmap: Bitmap): Boolean {

        var root: String =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).toString();
        // Log.d("TAG", "root = " + root)
        var myDir: File = File(root +  "/reddit");


        if (!myDir.exists()) {
            myDir.mkdirs();
            //  Log.d("TAG", "mydir !exist = " + myDir)
        }
        var fname: String = ""
        try {
            fname = url.split("/").toTypedArray().get(3).split("?").toTypedArray().get(0)
        } catch (e: Exception) {
            fname = System.currentTimeMillis().toString() + ".jpg"
        }
        //   url.replaceFirst("https://preview.redd.it/".toRegex(), "/")
        //      .split("?").toTypedArray().get(0)



        //Log.d("TAG", "FileName = " + fname)
        var file: File = File(myDir, fname);
        //Log.d("TAG", "mydir !exist = " + myDir)
        if (file.exists()) {
            file.delete();
            // Log.d("TAG", "file delete = " + file)
        }
        try {
            var out: FileOutputStream = FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
            return true

        } catch (e: Exception) {
            e.printStackTrace();
            return false
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {

        when (requestCode) {
            1 -> {
                if (grantResults.isNotEmpty() && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED
                ) {
                    if ((ContextCompat.checkSelfPermission(
                            this.requireActivity(),
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                        ) ===
                                PackageManager.PERMISSION_GRANTED)
                    ) {

                        Toast.makeText(context, "Permission Granted", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
                }
                return
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    @RequiresApi(Build.VERSION_CODES.R)
    private fun saveImage(): Boolean {

        if (ContextCompat.checkSelfPermission(
                this.requireContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )

            == PackageManager.PERMISSION_DENIED
        ) {
            //Log.d("TAG", "Permission Granted")
            val list = listOf<String>(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
            )

            // arrayOf(Manifest.permission.ACCESS_FINE_LOCATION)
            requestPermissions(
                list.toTypedArray(),
                1
            );
        } else {
            val bitmap = (binding?.imageView?.getDrawable() as BitmapDrawable).bitmap
            if (bitmap != null) {
                if (saveImageStorage(bitmap))
                    Toast.makeText(context, getString(R.string.file_ok), Toast.LENGTH_SHORT).show()
                else
                    Toast.makeText(context, getString(R.string.file_error), Toast.LENGTH_SHORT)
                        .show()
            }

        }
        return false
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //viewModel = ViewModelProvider(this).get(PostViewModel::class.java)
        // TODO: Use the ViewModel
        val arg: PostFragmentArgs by navArgs()
        // Log.d("TAG", arg.post)

        binding?.toolbar?.setNavigationOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                findNavController().popBackStack()
                // back button pressed
            }
        })
        url = arg.post.toString().replace("amp;", "", false)

        binding?.let {
            Glide.with(this).load(url).into(it.imageView)

        };

    }

}


