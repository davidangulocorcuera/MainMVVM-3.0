package com.plexus.marvel.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

/**
 * © Class created by David Angulo
 * */

abstract class BaseFragment<VM : BaseViewModel, DB : ViewDataBinding>(private val mViewModelClass: Class<VM>) :
    Fragment() {
    lateinit var viewModel: VM
    open lateinit var mBinding: DB

    val LOG_TAG = this::class.java.simpleName


    fun init(inflater: LayoutInflater, container: ViewGroup) {
        mBinding = DataBindingUtil.inflate(inflater, getLayoutRes(), container, false)
    }


    @LayoutRes
    protected abstract fun getLayoutRes(): Int
    protected abstract fun viewCreated(view: View?)


    private fun getViewM(): VM = ViewModelProvider(this).get(mViewModelClass)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getViewM()
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        viewCreated(view)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        container?.apply { init(inflater, container) }
        super.onCreateView(inflater, container, savedInstanceState)
        return mBinding.root
    }

}