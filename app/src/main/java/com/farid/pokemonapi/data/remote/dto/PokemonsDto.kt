package com.farid.pokemonapi.data.remote.dto

import android.os.Parcelable
import com.farid.pokemonapi.domain.model.Pokemons
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonsDto(

    @field:SerializedName("next")
    val next: String? = null,

    @field:SerializedName("previous")
    val previous: String? = null,

    @field:SerializedName("count")
    val count: Int? = null,

    @field:SerializedName("results")
    val results: List<ResultsItem?>? = null
) : Parcelable

@Parcelize
data class ResultsItem(

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("url")
    val url: String? = null
) : Parcelable


fun PokemonsDto.toPokemons(): Pokemons {
    return Pokemons(
        countPokemons = count
    )
}