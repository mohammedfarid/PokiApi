package com.farid.pokemonapi.domain.repository

import com.farid.pokemonapi.data.remote.dto.PokemonDetailsDto
import com.farid.pokemonapi.data.remote.dto.PokemonsDto

interface MainRepository {
    suspend fun getPokemonsCounts(): PokemonsDto
    suspend fun getPokemonDetails(id:String):PokemonDetailsDto
}