package com.madd.madd.tmdbkotlin.Fragments.MovieDetail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.madd.madd.tmdbkotlin.HTTP.Models.Cast
import com.madd.madd.tmdbkotlin.R

class ActorAdapter : RecyclerView.Adapter<ActorAdapter.ViewHolder> {

    var actorList = ArrayList<Cast.Actor>()

    constructor(actorList: ArrayList<Cast.Actor>) : super() {
        this.actorList = actorList
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.section_actor,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return actorList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(actorList[position])
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var imageView: ImageView
        var textViewName: TextView
        var textViewCharacter: TextView

        init {
            imageView = itemView.findViewById(R.id.IV_Section_Actor)
            textViewName = itemView.findViewById(R.id.TV_Section_Actor_Name)
            textViewCharacter = itemView.findViewById(R.id.TV_Section_Actor_Character)
        }

        fun bind(actor:Cast.Actor){

            if (actor.profilePath!!.isNotEmpty()) {
                Glide.with(imageView)
                    .load(actor.profilePath)
                    .placeholder(ContextCompat.getDrawable(imageView.context, R.drawable.image_not_user))
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(imageView)
            } else {
                Glide.with(imageView)
                    .load(R.drawable.image_not_user)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(imageView)
            }

            textViewName.text = actor.name
            textViewCharacter.text = "\"" + actor.character + "\""

        }

    }

}