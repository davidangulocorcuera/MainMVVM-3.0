package com.plexus.marvel.usescase.splash

import android.view.View
import com.plexus.data.storage.database.LocalRepository
import com.plexus.domain.Character
import com.plexus.marvel.R
import com.plexus.marvel.application.App
import com.plexus.marvel.base.BaseFragment
import com.plexus.marvel.databinding.FragmentSplashBinding
import com.plexus.marvel.usescase.characters.CharactersState
import com.plexus.marvel.usescase.home.HomeFragment
import com.plexus.marvel.usescase.home.SplashState
import kotlinx.coroutines.*

/**
 * © Class created by David Angulo , david.angulocorcuera@plexus.es
 * */

class SplashFragment :
    BaseFragment<SplashViewModel, FragmentSplashBinding>(SplashViewModel::class.java) {
    override fun getLayoutRes(): Int {
        return R.layout.fragment_splash
    }

    override fun viewCreated(view: View?) {
        mBinding.viewModel = viewModel
        mBinding.lifecycleOwner = this
        observeState()
        viewModel.getAllCharacters()
    }

    private fun observeState(){
        viewModel.splashState.observe(viewLifecycleOwner){
            when(it){
                is SplashState.CharactersLoadedState -> onCharactersLoaded(it.characters)
                is SplashState.ErrorLoadingCharactersState -> onErrorLoadingCharacters()
            }
        }
    }

    fun onCharactersLoaded(characters: ArrayList<Character>) {
        CoroutineScope(Job()).launch {
            LocalRepository().saveAllCharacters(characters, App().getDatabase(requireContext()))
            withContext(Dispatchers.Main) {
                navigator.navigate(HomeFragment())
            }
        }
    }

    fun onErrorLoadingCharacters() {
        viewModel.showErrorButton.value = true
        showErrorSnackBar(getString(R.string.splash_error_message))
    }

}