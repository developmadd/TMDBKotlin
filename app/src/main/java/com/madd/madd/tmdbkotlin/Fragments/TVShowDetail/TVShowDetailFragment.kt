package com.madd.madd.tmdbkotlin.Fragments.TVShowDetail


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.madd.madd.tmdbkotlin.DI.App
import com.madd.madd.tmdbkotlin.HTTP.Models.Cast
import com.madd.madd.tmdbkotlin.HTTP.Models.TVShow

import com.madd.madd.tmdbkotlin.R
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class TVShowDetailFragment : Fragment(), TVShowDetailContract.View {


    lateinit var textViewTitle: TextView
    lateinit var textViewYear: TextView
    lateinit var textViewGenre: TextView
    lateinit var textViewAverage: TextView
    lateinit var textViewSeasons: TextView
    lateinit var textViewEpisodes: TextView
    lateinit var imageViewStar: ImageView
    lateinit var imageViewPoster: ImageView
    lateinit var imageViewBackdrop: ImageView
    lateinit var textViewOverview: TextView
    lateinit var recyclerView: RecyclerView
    lateinit var textViewCastError: TextView
    lateinit var progressBar: ProgressBar

    @Inject
    lateinit var presenter:TVShowDetailContract.Presenter
    lateinit var actorAdapter:ActorAdapter

    var tvShow:TVShow? = null
    override var tvShowId = ""
    var actorList = ArrayList<Cast.Actor>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_tvshow_detail, container, false)
        (activity!!.application as App).component!!.inject(this)

        bindUI(v)
        loadView()

        presenter.setView(this)
        presenter.getTVShow()
        presenter.getCast()

        return  v
    }








    override fun onResume() {
        super.onResume()
        presenter.setView(this)
    }

    fun bindUI(v:View){
        textViewTitle = v.findViewById(R.id.TV_TVShow_Detail_Title)
        textViewYear = v.findViewById(R.id.TV_TVShow_Detail_Year)
        textViewGenre= v.findViewById(R.id.TV_TVShow_Detail_Genre)
        textViewAverage= v.findViewById(R.id.TV_TVShow_Detail_Average)
        textViewSeasons= v.findViewById(R.id.TV_TVShow_Detail_Seasons)
        textViewEpisodes= v.findViewById(R.id.TV_TVShow_Detail_Episodes)
        imageViewStar= v.findViewById(R.id.IV_TVShow_Detail_Star)
        imageViewPoster= v.findViewById(R.id.IV_TVShow_Detail_Poster)
        imageViewBackdrop= v.findViewById(R.id.IV_TVShow_Detail_Backdrop)
        textViewOverview= v.findViewById(R.id.TV_TVShow_Detail_Overview)
        recyclerView = v.findViewById(R.id.CTNR_TVShow_Detail_Cast)
        textViewCastError = v.findViewById(R.id.TV_TVShow_Detail_Cast_Error)
        progressBar = v.findViewById(R.id.PB_TVShow_Detail)

    }
    fun loadView(){

        actorAdapter = ActorAdapter(actorList)
        recyclerView.setHasFixedSize(true)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.layoutManager = LinearLayoutManager(context,
            LinearLayoutManager.HORIZONTAL,false)
        recyclerView.adapter = actorAdapter

    }


    fun fillTVShowInfo(){
        if (tvShow!!.posterPath!!.isNotEmpty()) {
            Glide.with(imageViewPoster)
                .load(tvShow!!.posterPath)
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageViewPoster)
        } else {
            Glide.with(imageViewBackdrop)
                .load(R.drawable.image_not_picture)
                .centerCrop()
                .into(imageViewPoster)
        }

        if (tvShow!!.backdropPath!!.isNotEmpty()) {
            Glide.with(imageViewBackdrop)
                .load(tvShow!!.backdropPath)
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageViewBackdrop)
        } else {
            Glide.with(imageViewBackdrop)
                .load(R.drawable.image_not_picture)
                .centerCrop()
                .into(imageViewBackdrop)
        }


        imageViewStar.visibility = View.VISIBLE
        textViewTitle.text = tvShow!!.name
        textViewGenre.text = tvShow!!.genre
        textViewYear.text = tvShow!!.year
        textViewEpisodes.text = "Episodios: " + tvShow!!.episodesNumber
        textViewSeasons.text = "Temporadas: " + tvShow!!.seasonsNumber
        textViewAverage.text = tvShow!!.voteAverage
        textViewOverview.text = if (tvShow!!.overview!!.isEmpty())
            "Sin descripción disponible"
        else
            tvShow!!.overview
    }


    override fun showTVShow(tvShow: TVShow) {
        this.tvShow = tvShow
        fillTVShowInfo()
    }

    override fun showCast(cast: Cast) {
        textViewCastError.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE
        actorList.addAll(cast.actorList)
        actorAdapter.notifyDataSetChanged()
    }

    override fun showLoadingProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoadingProgress() {
        progressBar.visibility = View.GONE
    }

    override fun showTVShowError() {
        Toast.makeText(
            context,
            "Ha ocurrido un problema al tratar de mostrar el contenido seleccionado",
            Toast.LENGTH_SHORT).show()

        activity!!.onBackPressed()
    }

    override fun showCastError() {
        textViewCastError.visibility = View.VISIBLE
        recyclerView.visibility = View.GONE
    }

}
