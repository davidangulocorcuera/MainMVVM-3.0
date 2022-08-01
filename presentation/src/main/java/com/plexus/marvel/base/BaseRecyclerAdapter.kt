package com.plexus.marvel.base

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerAdapter<E, VH : BaseRecyclerAdapter.ViewHolder?,DB : ViewDataBinding> :
    RecyclerView.Adapter<VH> {
    var LOG_TAG = ""
    var list: List<E>
    open lateinit var mBinding: DB
    private var inflater: LayoutInflater? = null

    constructor() {
        list = ArrayList()
        LOG_TAG = this.javaClass.simpleName
    }

    constructor(list: List<E>?) {
        var list = list
        if (list == null) {
            list = ArrayList()
        }
        this.list = list
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        onBindViewHolder(holder, position, getItem(position))
    }

    private fun onBindViewHolder(holder: VH, position: Int, item: E) {
        holder?.bind(position)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun getItem(position: Int): E {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return list[position].hashCode().toLong()
    }


    protected fun getInflater(context: Context?): LayoutInflater? {
        if (inflater == null) {
            inflater = LayoutInflater.from(context)
        }
        return inflater
    }

    abstract class ViewHolder
        (itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        abstract fun bind(position: Int)
    }
}