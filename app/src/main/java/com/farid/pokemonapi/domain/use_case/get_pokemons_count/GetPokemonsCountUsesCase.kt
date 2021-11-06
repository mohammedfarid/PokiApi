package com.farid.pokemonapi.domain.use_case.get_pokemons_count

import com.farid.pokemonapi.common.Resource
import com.farid.pokemonapi.data.remote.dto.toPokemons
import com.farid.pokemonapi.domain.model.Pokemons
import com.farid.pokemonapi.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetPokemonsCountUsesCase @Inject constructor(
    private val repository: MainRepository
) {
    operator fun invoke(): Flow<Resource<Pokemons>> = flow{
        try {
            emit(Resource.Loading())
            val pokemons = repository.getPokemonsCounts().toPokemons()
            emit(Resource.Success(pokemons))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}