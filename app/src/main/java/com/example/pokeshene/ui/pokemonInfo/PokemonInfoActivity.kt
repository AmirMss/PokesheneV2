package com.example.pokeshene.ui.pokemonInfo

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.pokeshene.Adapters.PokemonListMovesRecyclerAdapter
import com.example.pokeshene.Models.Data
import com.example.pokeshene.R
import com.squareup.picasso.Picasso

import kotlinx.android.synthetic.main.activity_pokemon_info.*

class PokemonInfoActivity: AppCompatActivity() {

    private lateinit var pokemonInfoViewModel: PokemonInfoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_pokemon_info)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //close activity on back arrow click
        toolbar.setNavigationOnClickListener {
            finish()
        }

        //get parameters sent from mainActivity startIntent
        val data = intent.extras?.getSerializable("pokemon") as Data
        val mainImage = intent.extras?.getString("pokemonMainImage")

        pokemonInfoViewModel = ViewModelProviders.of(this, PokemonInfoViewModelFactory(data, mainImage)).get(PokemonInfoViewModel::class.java)

        pokemonInfoViewModel.title.observe(this, Observer {
            title = it
        })

        pokemonInfoViewModel.pokemonMainImage.observe(this, Observer {
            Picasso.get().load(it).into(imageViewPokemon)
        })

        pokemonInfoViewModel.getPokemonHeight().observe(this, Observer {
            textViewPokemonHeight.text = it
        })
        pokemonInfoViewModel.getPokemonWeight().observe(this, Observer {
            textViewPokemonWeight.text = it
        })

        pokemonInfoViewModel.getPokemonInfo()?.observe(this, Observer {
            runOnUiThread {
                Picasso.get().load(it?.sprites?.front_default).into(imageViewSprite1)
                Picasso.get().load(it?.sprites?.back_default).into(imageViewSprite2)
                Picasso.get().load(it?.sprites?.front_shiny).into(imageViewSprite3)
                Picasso.get().load(it?.sprites?.back_shiny).into(imageViewSprite4)

                it?.types?.let {
                    textViewType1.text = it[0].type?.name
                    if (it.count() > 1)
                        textViewType2.text = it[1].type?.name
                    else
                        textViewType.text = resources.getString(R.string.type)
                }

                recyclerViewPokemonMoves.adapter =
                    PokemonListMovesRecyclerAdapter(it!!)
            }
        })

        pokemonInfoViewModel.getInfoException()?.observe(this, Observer {
            runOnUiThread {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        })

        pokemonInfoViewModel.getBadResponse()?.observe(this, Observer {
            runOnUiThread {
                Toast.makeText(this, applicationContext.getString(it), Toast.LENGTH_SHORT).show()
            }
        })

        pokemonInfoViewModel.fetchPokemonInfo()
    }
}
