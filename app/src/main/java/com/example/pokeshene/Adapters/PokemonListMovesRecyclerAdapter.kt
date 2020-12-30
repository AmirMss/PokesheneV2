package com.example.pokeshene.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokeshene.Models.PokemonInfoResponse
import com.example.pokeshene.R
import kotlinx.android.synthetic.main.pokemon_list_moves_row_layout.view.*

class PokemonListMovesRecyclerAdapter(val data: PokemonInfoResponse) : RecyclerView.Adapter<PokemonListMovesRecyclerAdapter.CustomViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellForRow = layoutInflater.inflate(R.layout.pokemon_list_moves_row_layout, parent, false)

        return CustomViewHolder(
            cellForRow
        )
    }

    override fun getItemCount(): Int {
        return data.moves.count()
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val pokemon = data.moves[position]

        holder.itemView.textViewMoveName.text = pokemon.move.name
    }

    class CustomViewHolder(v: View): RecyclerView.ViewHolder(v) {

    }
}
