package com.plexus.marvel.usescase.characterdetail

import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.plexus.domain.Character
import com.plexus.marvel.R
import com.plexus.marvel.base.BaseFragment
import com.plexus.marvel.databinding.FragmentCharacterDetailBinding
import com.plexus.marvel.usescase.characters.CharactersState
import com.plexus.marvel.utils.Constants.Companion.EXTRA_CHARACTER_ID
import com.plexus.marvel.utils.getImageUrl

/**
 * © Class created by David Angulo , david.angulocorcuera@plexus.es
 * */

class CharacterDetailFragment :
    BaseFragment<CharacterDetailViewModel, FragmentCharacterDetailBinding>(CharacterDetailViewModel::class.java) {
    override fun getLayoutRes(): Int {
        return R.layout.fragment_character_detail
    }

    override fun viewCreated(view: View?) {
        mBinding.viewModel = viewModel
        mBinding.action = this
        mBinding.lifecycleOwner = this
        observeState()
        arguments?.getInt(EXTRA_CHARACTER_ID)?.apply {
            viewModel.getCharacterDetail(this)
        } ?: kotlin.run {
            onErrorLoadingCharacter()
        }

    }

    private fun observeState() {
        viewModel.characterState.observe(viewLifecycleOwner) {
            when (it) {
                is CharacterDetailState.CharacterLoadedState -> onCharacterLoaded(it.character)
                is CharacterDetailState.ErrorLoadingCharacterState -> onErrorLoadingCharacter()
            }
        }
    }


    private fun onCharacterLoaded(character: Character) {
        viewModel.character.value = character
        Glide.with(requireContext())
            .load(character.getImageUrl())
            .transform(CenterCrop())
            .into(mBinding.ivCharacter)
    }

    private fun onErrorLoadingCharacter() {
        viewModel.showErrorButton.value = true
        showErrorSnackBar(getString(R.string.splash_error_message))
    }

}