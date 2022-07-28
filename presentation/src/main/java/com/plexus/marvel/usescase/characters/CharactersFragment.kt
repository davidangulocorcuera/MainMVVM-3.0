package com.plexus.marvel.usescase.characters

import android.view.View
import com.plexus.marvel.R
import com.plexus.marvel.base.BaseFragment
import com.plexus.marvel.databinding.FragmentCharactersBinding

class CharactersFragment :
    BaseFragment<CharactersViewModel, FragmentCharactersBinding>(CharactersViewModel::class.java) {
    override fun getLayoutRes(): Int {
        return R.layout.fragment_characters
    }

    override fun viewCreated(view: View?) {
        mBinding.viewModel = viewModel
        mBinding.lifecycleOwner = this
    }

}