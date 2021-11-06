package com.farid.pokemonapi.presentation

import com.farid.pokemonapi.domain.model.Pokemons

data class PokemonsCountState(
    val isLoading: Boolean = false,
    val pokemons: Pokemons? = null,
    val error: String = ""
)
