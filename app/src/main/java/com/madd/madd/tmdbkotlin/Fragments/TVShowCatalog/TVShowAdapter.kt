package com.madd.madd.tmdbkotlin.Fragments.TVShowCatalog

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.madd.madd.tmdbkotlin.HTTP.Models.TVShowList
import com.madd.madd.tmdbkotlin.HTTP.TMDBModule
import com.madd.madd.tmdbkotlin.R
import org.w3c.dom.Text

class TVShowAdapter: RecyclerView.Adapter<TVShowAdapter.ViewHolder> {



    var tvShowEvents:TVShowEvents? = null
    var tvShowList = ArrayList<TVShowList.TVShow>()

    constructor(tvShowList: ArrayList<TVShowList.TVShow>, tvShowEvents: TVShowEvents?) {
        this.tvShowEvents = tvShowEvents
        this.tvShowList = tvShowList
    }





    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.section_content,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return tvShowList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(tvShowList[position])
    }




    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var imageViewPoster:ImageView
        var textViewTitle:TextView

        init {
            imageViewPoster = itemView.findViewById(R.id.IV_Section_Content_Poster)
            textViewTitle = itemView.findViewById(R.id.TV_Section_Content_Title)
        }

        fun bind(tvShow:TVShowList.TVShow){

            if (tvShow.posterPath!!.isNotEmpty()) {
                Glide.with(imageViewPoster.context)
                    .load(tvShow.posterPath)
                    .placeholder(
                        ContextCompat.getDrawable(
                            imageViewPoster.context,
                            R.mipmap.ic_launcher_foreground
                        )
                    )
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(imageViewPoster)
            } else {
                Glide.with(imageViewPoster)
                    .load(R.mipmap.ic_launcher_foreground)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(imageViewPoster)
            }

            textViewTitle.text = tvShow.name

            itemView.setOnClickListener { view -> tvShowEvents!!.onTVShowClick(tvShow) }

            val itemMinLimit = TMDBModule.TMDB_PAGINATE_STEP
            if (tvShowList.size >= itemMinLimit && adapterPosition == tvShowList.size - 5) {
                tvShowEvents!!.onRequestNextPage()
            }
        }


    }

    interface TVShowEvents{

        fun onTVShowClick(selectedTVShow:TVShowList.TVShow)
        fun onRequestNextPage()

    }

}