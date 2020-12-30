package com.example.pokeshene.ui.pokemonInfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pokeshene.Models.Data

class PokemonInfoViewModelFactory(private val data: Data, private val mainImage: String?): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = PokemonInfoViewModel(
        data, mainImage
    ) as T
}