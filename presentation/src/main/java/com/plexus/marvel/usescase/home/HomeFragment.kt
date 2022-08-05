package com.plexus.marvel.usescase.home

import android.view.View
import com.plexus.marvel.R
import com.plexus.marvel.base.BaseFragment
import com.plexus.marvel.databinding.FragmentHomeBinding
import com.plexus.marvel.usescase.characters.CharactersFragment

/**
 * © Class created by David Angulo , david.angulocorcuera@plexus.es
 * */

class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>(HomeViewModel::class.java) {
    override fun getLayoutRes(): Int = R.layout.fragment_home


    override fun viewCreated(view: View?) {
        mBinding.viewModel = viewModel
        mBinding.lifecycleOwner = this
        setButtonListener()
    }

    private fun setButtonListener() {
        mBinding.btnSeeCharacters.setOnClickListener {
            navigator.navigate(CharactersFragment(), true)
        }
    }

}