package com.codingwithmitch.espressouitestexamples.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.uitestingwithexpresso.R
import com.android.uitestingwithexpresso.databinding.FragmentMovieDetailBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.codingwithmitch.espressouitestexamples.data.Movie
import com.codingwithmitch.espressouitestexamples.data.source.MoviesDataSource
import com.codingwithmitch.espressouitestexamples.data.source.MoviesRemoteDataSource


class MovieDetailFragment constructor(
    val requestOptions: RequestOptions,
    val moviesDataSource: MoviesDataSource
): Fragment(){

    private lateinit var movie: Movie

    /**
     * In production the MoviesRemoteDataSource would be either:
     * 1) Be injected with a DI framework like dagger
     * 2) Be passed as a constructor param to the Fragment (if using FragmentFactory)
     * This is a simple use case so I'm just writing it here.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { args ->
            args.getInt("movie_id").let{ movieId ->
                moviesDataSource.getMovie(movieId)?.let{ movieFromRemote ->
                    movie = movieFromRemote
                }
            }
        }
    }

    var binding: FragmentMovieDetailBinding?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         binding= FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setMovieDetails()

        binding?.movieDirectiors?.setOnClickListener {
            val bundle = Bundle()
            bundle.putStringArrayList("args_directors", movie.directors)
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, DirectorsFragment::class.java, bundle)
                ?.addToBackStack("DirectorsFragment")
                ?.commit()
        }

        binding?.movieStarActors?.setOnClickListener {
            val bundle = Bundle()
            bundle.putStringArrayList("args_actors", movie.star_actors)
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, StarActorsFragment::class.java, bundle)
                ?.addToBackStack("StarActorsFragment")
                ?.commit()
        }
    }

    private fun setMovieDetails(){
        movie.let{ nonNullMovie ->
            Glide.with(this)
                .load(nonNullMovie.image)
                .into(binding?.movieImage!!)
            binding?.movieTitle?.text = nonNullMovie.title
            binding?.movieDescription?.text = nonNullMovie.description
        }
    }

}

















