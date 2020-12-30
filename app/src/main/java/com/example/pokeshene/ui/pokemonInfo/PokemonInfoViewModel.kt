package com.example.pokeshene.ui.pokemonInfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokeshene.Models.Data
import com.example.pokeshene.Models.PokemonInfoResponse
import com.example.pokeshene.R
import com.google.gson.GsonBuilder
import okhttp3.*
import java.io.IOException

class PokemonInfoViewModel(private val data: Data, private val mainImage: String?): ViewModel() {

    private val pokemonInfoLiveData: MutableLiveData<PokemonInfoResponse> = MutableLiveData<PokemonInfoResponse>()
    private val pokemonHeight: MutableLiveData<String> = MutableLiveData<String>()
    private val pokemonWeight: MutableLiveData<String> = MutableLiveData<String>()
    private val infoException: MutableLiveData<String> = MutableLiveData<String>()
    private val badResponse: MutableLiveData<Int> = MutableLiveData<Int>()
    val title: MutableLiveData<String> = MutableLiveData<String>()
    val pokemonMainImage: MutableLiveData<String> = MutableLiveData<String>()

    init {
        title.value = data.name
        pokemonMainImage.value = mainImage
    }

    fun getPokemonInfo(): LiveData<PokemonInfoResponse?>? {
        return pokemonInfoLiveData
    }

    fun getPokemonHeight(): LiveData<String> {
        return pokemonHeight
    }

    fun getPokemonWeight(): LiveData<String> {
        return pokemonWeight
    }

    fun getInfoException(): LiveData<String>? {
        return infoException
    }

    fun getBadResponse(): LiveData<Int>? {
        return badResponse
    }

    fun fetchPokemonInfo() {
        val client = OkHttpClient()
        val request = Request.Builder().url(data.url).build()

        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call?, response: Response?) {

                if (response?.code() == 200) {
                    val body = response.body()?.string()

                    val gson = GsonBuilder().create()

                    val res = gson.fromJson(body, PokemonInfoResponse::class.java)

                    pokemonInfoLiveData.postValue(res)
                    pokemonHeight.postValue((convertToMeters(res.height)))
                    pokemonWeight.postValue((convertToKg(res.weight)))
                } else {
                    badResponse.postValue(R.string.wrongResponse)
                }
            }

            override fun onFailure(call: Call?, e: IOException?) {
                infoException.postValue(e.toString())
            }
        })
    }

    private fun convertToMeters(height: Int): String {
        return (height.toDouble() / 10).toString() + "m"
    }
    private fun convertToKg(height: Int): String {
        return (height.toDouble() / 10).toString() + "kg"
    }
}