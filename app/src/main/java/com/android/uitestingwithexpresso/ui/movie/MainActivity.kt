package com.android.uitestingwithexpresso.ui.movie

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import com.android.uitestingwithexpresso.R
import com.android.uitestingwithexpresso.databinding.ActivityMainBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.codingwithmitch.espressouitestexamples.data.source.MoviesDataSource
import com.codingwithmitch.espressouitestexamples.data.source.MoviesRemoteDataSource
import com.codingwithmitch.espressouitestexamples.factory.MovieFragmentFactory
import com.codingwithmitch.espressouitestexamples.ui.movie.MovieDetailFragment
const val GALLERY_REQUEST_CODE = 1234
const val REQUEST_IMAGE_CAPTURE = 12345
const val KEY_IMAGE_DATA = "data"
class MainActivity : AppCompatActivity() {
    private val TAG: String = "AppDebug"
    private var binding: ActivityMainBinding? = null

    // dependencies (typically would be injected with dagger)
    lateinit var requestOptions: RequestOptions
    lateinit var moviesDataSource: MoviesDataSource

    override fun onCreate(savedInstanceState: Bundle?) {
        initDependencies()
        supportFragmentManager.fragmentFactory = MovieFragmentFactory(
            requestOptions,
            moviesDataSource
        )
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)


//        binding?.buttonOpenGallery?.setOnClickListener {
//            pickFromGallery()
//        }
//        binding?.buttonLaunchCamera?.setOnClickListener {
//            dispatchCameraIntent()
//        }

        init()
    }

    private fun init() {
        // if there is no fragment attached to any screen or any activity it will return 0 the will attache it
        if (supportFragmentManager.fragments.size == 0) {
            val movieId = 1
            val bundle = Bundle()
            bundle.putInt("movie_id", movieId)
            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.container,
                    /** before fragment factory you have to initialize the fragment here but because the fragment factory you can pass the class directly*/
                    MovieDetailFragment::class.java, bundle
                )
                .commit()
        }
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if(resultCode == Activity.RESULT_OK){
//            Log.d(TAG, "RESULT_OK")
//            when(requestCode){
//
//                GALLERY_REQUEST_CODE -> {
//                    Log.d(TAG, "GALLERY_REQUEST_CODE detected.")
//                    data?.data?.let { uri ->
//                        Log.d(TAG, "URI: $uri")
//                        Glide.with(this)
//                            .load(uri)
//                            .into(binding?.image!!)
//                    }
//                }
//            }
//        }
//    }

//    private fun pickFromGallery() {
//        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
//        startActivityForResult(intent, GALLERY_REQUEST_CODE)
//    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            Log.d(TAG, "RESULT_OK")
            when (requestCode) {

                REQUEST_IMAGE_CAPTURE -> {
                    Log.d(TAG, "REQUEST_IMAGE_CAPTURE detected.")
                    data?.extras.let { extras ->
                        if (extras == null || !extras.containsKey(KEY_IMAGE_DATA)) {
                            return
                        }
                        val imageBitmap = extras[KEY_IMAGE_DATA] as Bitmap?
                        binding?.image?.setImageBitmap(imageBitmap)
                    }
                }
            }
        }
    }

    private fun initDependencies() {

        // glide
        requestOptions = RequestOptions
            .placeholderOf(R.drawable.default_image)
            .error(R.drawable.default_image)

        // Data Source
        moviesDataSource = MoviesRemoteDataSource()
    }
    private fun dispatchCameraIntent() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
    }

}