package com.farid.pokemonapi.data.remote

import com.google.gson.JsonObject
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Mohammed Farid on 7/18/2021
 * Contact me : m.farid.shawky@gmail.com
 */
interface APIs {
    @GET("pokemon")
    suspend fun getPokemons(): JsonObject

    @GET("pokemon/{id}")
    suspend fun getPokemonDetail(@Path("id") id: String): JsonObject
}