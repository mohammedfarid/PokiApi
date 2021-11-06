package com.farid.pokemonapi.presentation

import com.farid.pokemonapi.domain.model.PokemonDetail

data class PokemonsDetailsState(
    val isLoading: Boolean = false,
    val pokemonDetail: PokemonDetail? = null,
    val error: String = ""
)
