package com.farid.pokemonapi.common

/**
 * Created by Mohammed Farid on 10/13/2021
 * Contact me : m.farid.shawky@gmail.com
 */
sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T?) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
    class Loading<T>(data: T? = null) : Resource<T>(data)
}
