package com.farid.pokemonapi.data.repository

import com.farid.pokemonapi.data.remote.APIs
import com.farid.pokemonapi.data.remote.dto.PokemonDetailsDto
import com.farid.pokemonapi.data.remote.dto.PokemonsDto
import com.farid.pokemonapi.domain.repository.MainRepository
import com.google.gson.Gson
import javax.inject.Inject

class MainRepositoryImp @Inject constructor(
    private val api: APIs
) : MainRepository {
    override suspend fun getPokemonsCounts(): PokemonsDto {
        return Gson().fromJson(api.getPokemons(), PokemonsDto::class.java)
    }

    override suspend fun getPokemonDetails(id: String): PokemonDetailsDto {
        return Gson().fromJson(api.getPokemonDetail(id), PokemonDetailsDto::class.java)
    }
}