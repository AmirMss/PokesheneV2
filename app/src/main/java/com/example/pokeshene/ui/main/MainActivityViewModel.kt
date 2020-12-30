package com.example.pokeshene.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokeshene.Models.PokemonListResponse
import com.example.pokeshene.R
import com.google.gson.GsonBuilder
import okhttp3.*
import java.io.IOException

class MainActivityViewModel: ViewModel() {

    private val pokemonInfoLiveData: MutableLiveData<PokemonListResponse> = MutableLiveData<PokemonListResponse>()
    private val infoException: MutableLiveData<String> = MutableLiveData<String>()
    private val badResponse: MutableLiveData<Int> = MutableLiveData<Int>()
    private val request = Request.Builder().url("https://pokeapi.co/api/v2/pokemon?limit=151").build()

    fun onPokemonInfoFetched(): LiveData<PokemonListResponse> {
        return pokemonInfoLiveData
    }

    fun getInfoException(): LiveData<String>? {
        return infoException
    }

    fun getBadResponse(): LiveData<Int>? {
        return badResponse
    }

    fun fetchPokemonInfo() {
        val client = OkHttpClient()

        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call?, response: Response?) {

                if (response?.code() == 200) {
                    val body = response.body()?.string()

                    val gson = GsonBuilder().create()

                    val res = gson.fromJson(body, PokemonListResponse::class.java)

                    pokemonInfoLiveData.postValue(res)
                } else {
                    badResponse.postValue(R.string.wrongResponse)
                }
            }

            override fun onFailure(call: Call?, e: IOException?) {
                infoException.postValue(e.toString())
            }
        })
    }
}