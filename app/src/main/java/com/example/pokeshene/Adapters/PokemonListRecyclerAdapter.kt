package com.example.pokeshene.Adapters

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.pokeshene.ui.pokemonInfo.PokemonInfoActivity
import com.example.pokeshene.Models.PokemonListResponse
import com.example.pokeshene.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.pokemon_list_row_layout.view.*

class PokemonListRecyclerAdapter(val data: PokemonListResponse, val activity: Activity) : RecyclerView.Adapter<PokemonListRecyclerAdapter.CustomViewHolder>() {

    private val urlHDImages = "https://pokeres.bastionbot.org/images/pokemon/"
    private  val imageExtension = ".png"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellForRow = layoutInflater.inflate(R.layout.pokemon_list_row_layout, parent, false)

        return CustomViewHolder(
            cellForRow
        )
    }

    override fun getItemCount(): Int {
        return data.results.count()
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val pokemon = data.results[position]

        holder.itemView.textViewPokemonName.text = pokemon.name

        //cliquer sur l'image permet d'ouvrir une nouvelle Activity
        //il faut changer pour capturer le click sur toute la cellule
        holder.itemView.imageViewPokemon.setOnClickListener {

            //put @keep and serializable in class in order to pass the object to another activity
            // (PokemonInfoResponse class)
            val intent = Intent(activity, PokemonInfoActivity::class.java)
            intent.putExtra("pokemon", pokemon)
            intent.putExtra("pokemonMainImage", urlHDImages + (position+1) + imageExtension)

            activity.startActivity(intent)
        }

        pokemon?.url?.let {

            //custom url to get HD images
            Picasso.get().load(urlHDImages + (position+1) + imageExtension).into(holder.itemView.imageViewPokemon, object :
                Callback {
                override fun onSuccess() {
                    //nothing to do
                }

                override fun onError(e: Exception?) {
                    activity.runOnUiThread {
                        Toast.makeText(activity, activity.getString(R.string.imageLoadError), Toast.LENGTH_SHORT).show()
                    }
                }

            })
        }?: run{

        }
    }

    class CustomViewHolder(v: View): RecyclerView.ViewHolder(v) {

    }
}
