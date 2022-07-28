package com.plexus.marvel.usescase.characterdetail

import android.view.View
import com.plexus.domain.Character
import com.plexus.marvel.R
import com.plexus.marvel.base.BaseFragment
import com.plexus.marvel.databinding.FragmentCharacterDetailBinding

class CharacterDetailFragment :
    BaseFragment<CharacterDetailViewModel, FragmentCharacterDetailBinding>(CharacterDetailViewModel::class.java) {
    override fun getLayoutRes(): Int {
        return R.layout.fragment_character_detail
    }

    override fun viewCreated(view: View?) {
        mBinding.viewModel = viewModel
        mBinding.lifecycleOwner = this
    }

    private fun initActions(){
        viewModel.onCharacterLoaded = ::onCharacterLoaded
        viewModel.onErrorLoadingCharacter = ::onErrorLoadingCharacter
    }

    private fun onCharacterLoaded(character: Character){

    }
    private fun onErrorLoadingCharacter(){

    }

}