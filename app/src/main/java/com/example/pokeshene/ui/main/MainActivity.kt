package com.example.pokeshene.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.pokeshene.Adapters.PokemonListRecyclerAdapter
import com.example.pokeshene.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mainActivityViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)

        var pos = 0
        btnNext.setOnClickListener {
            pos++
            listViewPokemonList.scrollToPosition(pos)
        }

        btnPrevious.setOnClickListener {
            pos--
            listViewPokemonList.scrollToPosition(pos)
        }

        mainActivityViewModel.getInfoException()?.observe(this, Observer {
            runOnUiThread {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        })

        mainActivityViewModel.getBadResponse()?.observe(this, Observer {
            runOnUiThread {
                Toast.makeText(this, applicationContext.getString(it), Toast.LENGTH_SHORT).show()
            }
        })

        mainActivityViewModel.onPokemonInfoFetched().observe(this, Observer {
            runOnUiThread {
                listViewPokemonList.adapter =
                    PokemonListRecyclerAdapter(
                        it,
                        this@MainActivity
                    )
            }
        })

        mainActivityViewModel.fetchPokemonInfo()
    }
}
