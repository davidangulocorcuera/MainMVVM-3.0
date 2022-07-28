package com.plexus.marvel.usescase.splash

import android.view.View
import com.plexus.data.storage.database.LocalRepository
import com.plexus.domain.Character
import com.plexus.marvel.R
import com.plexus.marvel.application.App
import com.plexus.marvel.base.BaseFragment
import com.plexus.marvel.databinding.FragmentSplashBinding
import com.plexus.marvel.usescase.home.HomeFragment
import kotlinx.coroutines.*

class SplashFragment :
    BaseFragment<SplashViewModel, FragmentSplashBinding>(SplashViewModel::class.java) {
    override fun getLayoutRes(): Int {
        return R.layout.fragment_splash
    }

    override fun viewCreated(view: View?) {
        mBinding.viewModel = viewModel
        mBinding.lifecycleOwner = this
        initActions()
        viewModel.getAllCharacters()
    }

    private fun initActions() {
        viewModel.onCharactersLoaded = ::onCharactersLoaded
        viewModel.onErrorLoadingCharacters = ::onErrorLoadingCharacters
    }

    private fun onCharactersLoaded(characters: ArrayList<Character>) {
        CoroutineScope(Job()).launch {
            LocalRepository().saveAllCharacters(characters, App().getDatabase(requireContext()))
            withContext(Dispatchers.Main) {
                navigator.navigate(HomeFragment())
            }
        }
    }

    private fun onErrorLoadingCharacters() {
        showErrorSnackBar(getString(R.string.splash_error_message))
    }

}