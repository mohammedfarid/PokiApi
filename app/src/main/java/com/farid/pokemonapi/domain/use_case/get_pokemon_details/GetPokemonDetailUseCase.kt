package com.farid.pokemonapi.domain.use_case.get_pokemon_details

import com.farid.pokemonapi.common.Resource
import com.farid.pokemonapi.data.remote.dto.toPokemonDetail
import com.farid.pokemonapi.domain.model.PokemonDetail
import com.farid.pokemonapi.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetPokemonDetailUseCase @Inject constructor(
    private val repository: MainRepository
) {
    operator fun invoke(id: String): Flow<Resource<PokemonDetail>> = flow {
        try {
            emit(Resource.Loading())
            val pokemonDetail = repository.getPokemonDetails(id).toPokemonDetail()
            emit(Resource.Success(pokemonDetail))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}