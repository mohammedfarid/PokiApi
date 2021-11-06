package com.farid.pokemonapi.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.farid.pokemonapi.common.Resource
import com.farid.pokemonapi.domain.use_case.get_pokemon_details.GetPokemonDetailUseCase
import com.farid.pokemonapi.domain.use_case.get_pokemons_count.GetPokemonsCountUsesCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getPokemonsCountUsesCase: GetPokemonsCountUsesCase,
    private val getPokemonDetailUseCase: GetPokemonDetailUseCase
) : ViewModel() {
    private val _state = MutableLiveData<PokemonsCountState>()
    val state: LiveData<PokemonsCountState> = _state

    fun getPokemonsCount() {
        getPokemonsCountUsesCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = PokemonsCountState(pokemons = result.data)
                }
                is Resource.Error -> {
                    _state.value = PokemonsCountState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _state.value = PokemonsCountState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private val _stateDetails = MutableLiveData<PokemonsDetailsState>()
    val stateDetail: LiveData<PokemonsDetailsState> = _stateDetails

    fun getPokemonDetails(id: String) {
        getPokemonDetailUseCase(id).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _stateDetails.value = PokemonsDetailsState(pokemonDetail = result.data)
                }
                is Resource.Error -> {
                    _stateDetails.value = PokemonsDetailsState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _stateDetails.value = PokemonsDetailsState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}