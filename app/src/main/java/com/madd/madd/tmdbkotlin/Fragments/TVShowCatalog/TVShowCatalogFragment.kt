package com.madd.madd.tmdbkotlin.Fragments.TVShowCatalog


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsSeekBar
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.madd.madd.tmdbkotlin.DI.App
import com.madd.madd.tmdbkotlin.HTTP.Models.TVShowList

import com.madd.madd.tmdbkotlin.R
import com.madd.madd.tmdbkotlin.Utilities.Utilities
import javax.inject.Inject
import kotlin.collections.ArrayList

/**
 * A simple [Fragment] subclass.
 */
class TVShowCatalogFragment : Fragment(), TVShowCatalogContract.View {

    companion object{
        val POPULAR_TYPE = 0
        val ON_AIR_TYPE = 1
        val TOP_RATED_TYPE = 2
    }

    @Inject
    lateinit var presenter:TVShowCatalogContract.Presenter
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private lateinit var textViewError: TextView
    private lateinit var progressBar: ProgressBar

    override var tvShowList = ArrayList<TVShowList.TVShow>()
    override var page = 1
    override var listType = 0

    private lateinit var tvShowAdapter:TVShowAdapter
    private var searchBarAnimationStatus = false
    var onTVShowSelected:TVShowCatalogContract.View.TVShowSelected? = null





    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_tvshow_catalog, container, false)
        (activity!!.application as App).component!!.inject(this)

        bindUI(v)
        loadView()

        presenter.setView(this)
        presenter.requestTVShowList()
        return v;
    }

    private fun bindUI(v:View){
        recyclerView = v.findViewById(R.id.CTNR_TVShow_Catalog)
        searchView = v.findViewById(R.id.SV_TVShow_Catalog)
        textViewError = v.findViewById(R.id.TV_TVShow_Catalog_Empty)
        progressBar = v.findViewById(R.id.PB_TVShow_Catalog)
    }

    private fun loadView(){

        recyclerView.setHasFixedSize(true)
        recyclerView.itemAnimator = DefaultItemAnimator()
        tvShowAdapter = TVShowAdapter(tvShowList,( object : TVShowAdapter.TVShowEvents{
            override fun onTVShowClick(selectedTVShow: TVShowList.TVShow) {
                presenter.selectTVShow(selectedTVShow)
            }

            override fun onRequestNextPage() {
                presenter.requestTVShowList()
            }

        }))
        recyclerView.layoutManager = GridLayoutManager(context!!,3)
        recyclerView.adapter = tvShowAdapter

        searchView.setOnQueryTextListener((object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                presenter.filterTVShowList(query!!)
                if( query.isEmpty() ){
                    Utilities.hideKeyboardFrom(searchView)
                }
                return false
            }

        }))


        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

                // No realizar animaciones mientras una se lleva a cabo
                if (!searchBarAnimationStatus) {

                    val firstVisibleItem =
                        (recyclerView.layoutManager as LinearLayoutManager)
                            .findFirstVisibleItemPosition()

                    if (dy > 0 && firstVisibleItem != 0) {
                        Utilities.animateTranslationY(searchView,-40, object : Utilities.GetAnimationStatus {
                            override fun animationStatus(animationStatus: Boolean) {
                                searchBarAnimationStatus = animationStatus
                            }
                        })
                        Utilities.animateTranslationY(recyclerView, 0, null)
                    } else if (dy < 0) {
                        Utilities.animateTranslationY(searchView,0,( object : Utilities.GetAnimationStatus{
                            override fun animationStatus(animationStatus: Boolean) {
                                searchBarAnimationStatus = animationStatus
                            }
                        }))
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


    override fun showTVShowList(tvShowList: List<TVShowList.TVShow>, page: Int) {
        val fromIndex = this.tvShowList.size
        val toIndex = this.tvShowList.size + tvShowList.size
        this.tvShowList.addAll(tvShowList)
        this.page = page
        tvShowAdapter.notifyItemRangeInserted(fromIndex, toIndex)
    }

    override fun clearTVShowList() {
        this.tvShowList.clear()
        this.page = 1
        tvShowAdapter.notifyDataSetChanged()
    }

    override fun showEmptyListError() {
        textViewError.text = "Sin resultados"
        textViewError.visibility = View.VISIBLE
    }

    override fun showInternetError() {
        textViewError.text = "Sin conexión a Internet"
        textViewError.visibility = View.VISIBLE
    }

    override fun hideError() {
        textViewError.visibility = View.GONE
        textViewError.text = ""
    }

    override fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }

    override fun openTVShowDetail(tvShow: TVShowList.TVShow) {
        onTVShowSelected!!.onTVShowClick(tvShow)
    }








}
