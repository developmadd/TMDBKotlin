package com.madd.madd.tmdbkotlin.Fragments.MovieCatalog


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.SearchView
import android.widget.TextView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import com.madd.madd.tmdbkotlin.HTTP.Models.MovieList

import com.madd.madd.tmdbkotlin.R
import com.madd.madd.tmdbkotlin.Utilities.Utilities
import javax.inject.Inject
import kotlin.collections.ArrayList

/**
 * A simple [Fragment] subclass.
 */
class MovieCatalogFragment : Fragment(), MovieCatalogContract.View {


    val POPULAR_TYPE = 0
    val UPCOMING_TYPE = 1
    val TOP_RATED_TYPE = 2

    @Inject var presenter: MovieCatalogContract.Presenter? = null
    @BindView(R.id.CTNR_Movie_Catalog)     var recyclerView: RecyclerView? = null
    @BindView(R.id.SV_Movie_Catalog)       var searchView: SearchView? = null
    @BindView(R.id.TV_Movie_Catalog_Empty) var textViewEmpty: TextView? = null
    @BindView(R.id.PB_Movie_Catalog)       var progressBar: ProgressBar? = null



    private val movieList = ArrayList<MovieList.Movie>()
    private var page = 1
    private var searchBarAnimationStatus = false

    private var onMovieSelected: MovieCatalogContract.View.MovieSelected? = null
    private var listType: Int = 0




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_movie_catalog, container, false)
        loadView()

        return v
    }

    fun loadView(){

        recyclerView!!.setHasFixedSize(true)
        recyclerView!!.itemAnimator = DefaultItemAnimator()
        val movieAdapter = MovieAdapter(movieList, object : MovieAdapter.MovieEvents {
            override fun onMovieClick(selectedMovie: MovieList.Movie) {
                presenter!!.selectMovie(selectedMovie)
                Utilities.hideKeyboardFrom(searchView!!)
            }

            override fun onRequestNextPage() {
                presenter!!.requestMovieList()
            }
        })
        recyclerView!!.layoutManager = GridLayoutManager(context,3)
        recyclerView!!.adapter = movieAdapter


        searchView!!.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                presenter!!.filterMovieList(query!!)
                if (query.isEmpty()) {
                    Utilities.hideKeyboardFrom(searchView!!)
                }
                return false
            }

        })

        recyclerView!!.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                // No realizar animaciones mientras una se lleva a cabo
                if (!searchBarAnimationStatus) {

                    val firstVisibleItem =(recyclerView.layoutManager as LinearLayoutManager)
                            .findFirstVisibleItemPosition()

                    if (dy > 0 && firstVisibleItem != 0) {
                        Utilities.animateTranslationY(searchView!!,-40, object :
                            Utilities.GetAnimationStatus {
                            override fun animationStatus(animationStatus: Boolean) {
                                searchBarAnimationStatus = animationStatus
                            }
                        })
                        Utilities.animateTranslationY(recyclerView, 0, null)
                    } else if (dy < 0) {
                        Utilities.animateTranslationY(searchView!!,0, object : Utilities.GetAnimationStatus{
                            override fun animationStatus(animationStatus: Boolean) {
                                searchBarAnimationStatus = animationStatus
                            }
                        })
                        Utilities.animateTranslationY(recyclerView, 40, null)
                    }

                }

            }
        })

    }


    override fun onResume() {
        super.onResume()
        presenter!!.setView(this)
    }

    override fun onStop() {
        super.onStop()
        presenter!!.unSubscribeReactive()
    }


    override fun showMovie(movie: MovieList.Movie) {
        movieList.add(movie)
        recyclerView!!.adapter!!.notifyItemInserted(movieList.size - 1)
    }

    override fun clearMovieList() {
        movieList.clear()
        page = 1
        recyclerView!!.adapter!!.notifyDataSetChanged()
    }

    override fun showEmptyListError() {
        textViewEmpty!!.setVisibility(View.VISIBLE)
        textViewEmpty!!.setText("Sin resultados")
    }

    override fun showInternetError() {
        textViewEmpty!!.setVisibility(View.VISIBLE)
        textViewEmpty!!.setText("Sin conexión a internet")
    }

    override fun hideError() {
        textViewEmpty!!.setVisibility(View.GONE)
    }

    override fun showProgressBar() {
        progressBar!!.setVisibility(View.VISIBLE)
    }

    override fun hideProgressBar() {
        progressBar!!.setVisibility(View.GONE)
    }

    override fun openMovieDetail(movie: MovieList.Movie) {
        onMovieSelected!!.onMovieClick(movie)
    }

















    override fun getMovieList(): ArrayList<MovieList.Movie> {
        return movieList
    }

    override fun getPage(): Int {
        return page
    }

    override fun setPage(page: Int) {
        this.page = page
    }

    override fun getListType(): Int {
        return listType
    }





}
