package com.example.pokeshene.Models

class PokemonInfoResponse(var height: Int, var weight: Int, val sprites: Sprites?, val abilities : ArrayList<Abilities>, val types: ArrayList<Types>, val moves: ArrayList<Moves>)

class Abilities (val ability: Ability, val is_hidden: Boolean, val slot: Int)

class Ability(val name: String, val url: String)

class Sprites(val back_default: String, val back_shiny: String, val front_default: String, val front_shiny: String)

class Types(val type: Type?)
class Type(val name: String?)

class Moves(val move: Move)
class Move(val name: String)