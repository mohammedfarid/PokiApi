package com.farid.pokemonapi.domain.model


data class PokemonDetail(
    var name: String? = null,
    var frontImage: String? = null,
    var backImage: String? = null,
    var moves: List<String?>? = null,
    var state: List<StateItem?>? = null
)

data class StateItem(
    var stateName: String? = null,
    var stateValue: String? = null
)
