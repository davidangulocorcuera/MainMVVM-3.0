package com.plexus.marvel.usescase.characters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.plexus.domain.Character
import com.plexus.marvel.R
import com.plexus.marvel.base.BaseRecyclerAdapter
import com.plexus.marvel.databinding.ItemCharacterBinding
import com.plexus.marvel.utils.getImageUrl

/**
 * © Class created by David Angulo , david.angulocorcuera@plexus.es
 * */

class CharactersAdapter(
    var context: Context,
    operations: MutableList<Character>,
    var viewModel: CharactersViewModel
) :
    BaseRecyclerAdapter<Character, CharactersAdapter.ViewHolder, ItemCharacterBinding>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_character, parent, false
        )
        return ViewHolder(mBinding.root)
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    init {
        list = operations
    }

    inner class ViewHolder(var view: View) : BaseRecyclerAdapter.ViewHolder(view) {
        private lateinit var current: Character

        override fun bind(position: Int) {
            current = getItem(position)
            setValues()
        }

        private fun setValues() {
            mBinding.item = current
            mBinding.viewModel = viewModel
            Glide.with(context)
                .load(current.getImageUrl())
                .transform(CenterCrop())
                .into(mBinding.ivCharacter)
        }
    }
}