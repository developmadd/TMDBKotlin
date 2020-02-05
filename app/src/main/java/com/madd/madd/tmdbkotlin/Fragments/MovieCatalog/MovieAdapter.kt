package com.madd.madd.tmdbkotlin.Fragments.MovieCatalog

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.madd.madd.tmdbkotlin.HTTP.Models.MovieList
import com.madd.madd.tmdbkotlin.HTTP.TMDBModule
import com.madd.madd.tmdbkotlin.R

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    var context:Context? = null
    var movieEvents:MovieEvents? = null
    var movieList:ArrayList<MovieList.Movie>? = null

    constructor(movieList: ArrayList<MovieList.Movie>, movieEvents: MovieEvents) {
        this.movieEvents = movieEvents
        this.movieList = movieList
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.section_content,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movieList!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movieList!![position])
    }



    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        @BindView(R.id.IV_Section_Content_Poster) var imageViewPoster: ImageView? = null
        @BindView(R.id.TV_Section_Content_Title)  var textViewTitle: TextView? = null

        init {
            ButterKnife.bind(this, itemView)
        }

        fun bind(movie: MovieList.Movie) {
            if (!movie.posterPath!!.isEmpty()) {
                Glide.with(imageViewPoster!!)
                    .load(movie.posterPath)
                    .placeholder(
                        ContextCompat.getDrawable(
                            context!!,
                            R.mipmap.ic_launcher_foreground
                        )
                    )
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(imageViewPoster!!)
            } else {
                Glide.with(imageViewPoster!!)
                    .load(R.mipmap.ic_launcher_foreground)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(imageViewPoster!!)
            }

            textViewTitle!!.setText(movie.title)

            itemView.setOnClickListener {
                movieEvents!!.onMovieClick(movie)
            }

            val itemMinLimit = TMDBModule.TMDB_PAGINATE_STEP
            if (movieList!!.size >= itemMinLimit && adapterPosition === movieList!!.size - 5) {
                movieEvents!!.onRequestNextPage()
            }
        }
    }


    interface MovieEvents {
        fun onMovieClick(selectedMovie: MovieList.Movie)
        fun onRequestNextPage()

    }

}