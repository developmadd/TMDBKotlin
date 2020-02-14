package com.madd.madd.tmdbkotlin.Fragments.MovieCatalog


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.madd.madd.tmdbkotlin.DI.App
import com.madd.madd.tmdbkotlin.HTTP.Models.MovieList

import com.madd.madd.tmdbkotlin.R
import com.madd.madd.tmdbkotlin.Utilities.Utilities
import javax.inject.Inject
import kotlin.collections.ArrayList

/**
 * A simple [Fragment] subclass.
 */
class MovieCatalogFragment : Fragment(), MovieCatalogContract.View {



    companion object {
        val POPULAR_TYPE = 0
        val UPCOMING_TYPE = 1
        val TOP_RATED_TYPE = 2
    }


    @Inject
    lateinit var presenter: MovieCatalogContract.Presenter
    lateinit var recyclerView: RecyclerView
    lateinit var searchView: SearchView
    lateinit var textViewEmpty: TextView
    lateinit var progressBar: ProgressBar


    override var movieList = ArrayList<MovieList.Movie>()
    override var page = 1
    override var listType: Int = 0

    var onMovieSelected: MovieCatalogContract.View.MovieSelected? = null
    private var searchBarAnimationStatus = false
    private lateinit var movieAdapter: MovieAdapter




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_movie_catalog, container, false)
        (activity!!.application as App).component!!.inject(this)

        bindUI(v)
        loadView()

        presenter.setView(this)
        presenter.requestMovieList()

        return v
    }

    fun bindUI(v:View){
        recyclerView = v.findViewById(R.id.CTNR_Movie_Catalog)
        searchView = v.findViewById(R.id.SV_Movie_Catalog)
        textViewEmpty = v.findViewById(R.id.TV_Movie_Catalog_Empty)
        progressBar = v.findViewById(R.id.PB_Movie_Catalog)
    }

    fun loadView(){

        recyclerView.setHasFixedSize(true)
        recyclerView.itemAnimator = DefaultItemAnimator()
        movieAdapter = MovieAdapter(movieList, object : MovieAdapter.MovieEvents {
            override fun onMovieClick(selectedMovie: MovieList.Movie) {
                presenter.selectMovie(selectedMovie)
                Utilities.hideKeyboardFrom(searchView)
            }

            override fun onRequestNextPage() {
                presenter.requestMovieList()
            }
        })
        recyclerView.layoutManager = GridLayoutManager(context,3)
        recyclerView.adapter = movieAdapter


        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                presenter.filterMovieList(query!!)
                if (query.isEmpty()) {
                    Utilities.hideKeyboardFrom(searchView)
                }
                return false
            }

        })

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                // No realizar animaciones mientras una se lleva a cabo
                if (!searchBarAnimationStatus) {

                    val firstVisibleItem =(recyclerView.layoutManager as LinearLayoutManager)
                            .findFirstVisibleItemPosition()

                    if (dy > 0 && firstVisibleItem != 0) {
                        Utilities.animateTranslationY(searchView,-40, object :
                            Utilities.GetAnimationStatus {
                            override fun animationStatus(animationStatus: Boolean) {
                                searchBarAnimationStatus = animationStatus
                            }
                        })
                        Utilities.animateTranslationY(recyclerView, 0, null)
                    } else if (dy < 0) {
                        Utilities.animateTranslationY(searchView,0, object : Utilities.GetAnimationStatus{
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
        presenter.setView(this)
    }


    override fun showMovieList(movieList: List<MovieList.Movie>, page: Int) {
        val fromIndex = this.movieList.size
        val toIndex = this.movieList.size + movieList.size
        this.movieList.addAll(movieList)
        this.page = page
        movieAdapter.notifyItemRangeInserted(fromIndex,toIndex)
    }


    override fun clearMovieList() {
        movieList.clear()
        page = 1
        movieAdapter.notifyDataSetChanged()
    }

    override fun showEmptyListError() {
        textViewEmpty.visibility = View.VISIBLE
        textViewEmpty.text = "Sin resultados"
    }

    override fun showInternetError() {
        textViewEmpty.visibility = View.VISIBLE
        textViewEmpty.text = "Sin conexión a internet"
    }

    override fun hideError() {
        textViewEmpty.visibility = View.GONE
    }

    override fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }

    override fun openMovieDetail(movie: MovieList.Movie) {
        onMovieSelected!!.onMovieClick(movie)
    }




















}
