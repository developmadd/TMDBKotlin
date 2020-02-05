package com.madd.madd.tmdbkotlin.Fragments.MovieDetail


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.madd.madd.tmdbkotlin.R

/**
 * A simple [Fragment] subclass.
 */
class MovieDetailFragment : Fragment() {

    var movieId = ""


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_detail, container, false)
    }


}
