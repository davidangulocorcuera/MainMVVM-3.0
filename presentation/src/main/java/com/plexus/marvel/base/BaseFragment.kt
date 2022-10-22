package com.plexus.marvel.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.plexus.marvel.components.CustomTopAppBar

/**
 * © Class created by David Angulo , david.angulocorcuera@plexus.es
 * */

abstract class BaseFragment<VM : BaseViewModel>(private val mViewModelClass: Class<VM>) :
    Fragment(), GlobalAction {
    lateinit var viewModel: VM

    private val baseActivity: BaseActivity<*, *>
        get() {
            return activity as BaseActivity<*, *>
        }

    val navigator: Navigator
        get() {
            return baseActivity.navigator
        }


    val LOG_TAG = this::class.java.simpleName


    open fun viewCreated(view: View?) {}

    @Composable
    protected abstract fun SetComposeView()


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
        return ComposeView(requireContext()).apply {
            setContent {
                SetComposeView()
            }
        }
    }
}