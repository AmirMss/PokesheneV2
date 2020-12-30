package com.example.pokeshene.Models

import androidx.annotation.Keep
import java.io.Serializable


//use @keep to serialize object and pass data through activities
@Keep
class PokemonListResponse(val count: Int, val next: String, val results: ArrayList<Data>)
@Keep
class Data(val name: String, val url: String?): Serializable