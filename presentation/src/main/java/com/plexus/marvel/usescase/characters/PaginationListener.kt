package com.plexus.marvel.usescase.characters

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * © Class created by David Angulo , david.angulocorcuera@plexus.es
 * */

abstract class PaginationListener(
    private val manager: GridLayoutManager
) : RecyclerView.OnScrollListener() {


    override
    fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val totalItemCount: Int = manager.itemCount
        val lastVisibleItemPosition: Int = manager.findLastVisibleItemPosition()


        if (lastVisibleItemPosition == totalItemCount - 1 && !isLoading()) {
            loadMoreItems()
        }

    }

    abstract fun loadMoreItems()
    abstract fun isLoading(): Boolean


}