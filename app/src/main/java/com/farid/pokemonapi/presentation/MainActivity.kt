package com.farid.pokemonapi.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.farid.pokemonapi.R
import com.farid.pokemonapi.databinding.ActivityMainBinding
import com.farid.pokemonapi.domain.model.PokemonDetail
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    private val mainViewModel: MainViewModel by viewModels()
    var countPokemon = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        mainViewModel.getPokemonsCount()

        mainViewModel.state.observe(this@MainActivity) {
            if (it?.isLoading == false) {
                Toast.makeText(
                    this@MainActivity,
                    "Pokemon Count: ${it.pokemons?.countPokemons}",
                    Toast.LENGTH_LONG
                ).show()
                Log.d("COUNT", "Pokemon Count: ${it.pokemons?.countPokemons}")
                it.pokemons?.countPokemons?.let { result ->
                    countPokemon = result
                    callPokemon(result)
                }
            }
        }


        mainViewModel.stateDetail.observe(this@MainActivity) {
            refreshUI()
            if (it?.isLoading == false) {
                it.pokemonDetail?.let { pokemonDetail ->
                    Log.d("DETAIL", "Pokemon Details: $pokemonDetail")
                    updateUI(pokemonDetail)
                } ?: run {
                    callPokemon(countPokemon)
                }

            }
        }

        binding?.toolbar?.menu?.findItem(R.id.btn_refresh)?.setOnMenuItemClickListener {
            callPokemon(countPokemon)
            true
        }
        binding?.swipeRefresh?.setOnRefreshListener {
            refreshUI()
            callPokemon(countPokemon)
        }
    }

    private fun updateUI(pokemonDetail: PokemonDetail?) {
        binding?.tvPokemonName?.text = pokemonDetail?.name?.toUpperCase()

        binding?.ivFront?.let {
            Glide.with(this@MainActivity).load(pokemonDetail?.frontImage).into(it)
        }
        binding?.ivBack?.let {
            Glide.with(this@MainActivity).load(pokemonDetail?.backImage).into(it)
        }

        pokemonDetail?.moves?.let { list ->
            var str = ""
            list.forEach {
                if (str.isEmpty()) {
                    str = it ?: ""
                } else {
                    str += "\n $it"
                }
            }
            binding?.tvPokemonMovesTitle?.visibility = View.VISIBLE
            binding?.tvPokemonMoves?.text = str
        }
        pokemonDetail?.state?.let { list ->
            var str = ""
            list.forEach {
                if (str.isEmpty()) {
                    str = "Name: ${it?.stateName} Value: ${it?.stateValue}"
                } else {
                    str += "\t Name: ${it?.stateName} Value: ${it?.stateValue}"
                }
            }
            binding?.tvPokemonStatisticsTitle?.visibility = View.VISIBLE
            binding?.tvPokemonStat?.text = str
        }
    }

    private fun callPokemon(countPokemons: Int?) {
        val pokemonValue = (1 until (countPokemons ?: 1)).random()
        mainViewModel.getPokemonDetails(pokemonValue.toString())
    }

    private fun refreshUI() {
        if (binding?.swipeRefresh?.isRefreshing == true) {
            binding?.swipeRefresh?.isRefreshing = false
        }
    }

}