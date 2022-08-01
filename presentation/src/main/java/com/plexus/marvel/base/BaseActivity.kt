package com.plexus.marvel.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.plexus.marvel.R

abstract class BaseActivity<VM : BaseViewModel, DB : ViewDataBinding>(private val mViewModelClass: Class<VM>) :
    AppCompatActivity(),GlobalAction {

    @LayoutRes
    abstract fun getLayoutRes(): Int

    val binding by lazy {
        DataBindingUtil.setContentView(this, getLayoutRes()) as DB
    }
     val navigator: Navigator by lazy {
        Navigator(this)
    }

    val viewModel by lazy {
        ViewModelProvider(this).get(mViewModelClass)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        initViewModel(viewModel)
        super.onCreate(savedInstanceState)
    }

    abstract fun initViewModel(viewModel: VM)

    fun showErrorSnackBar(text: String) {
        val parentLayout = findViewById<View>(android.R.id.content)
        val snackBar = Snackbar.make(parentLayout, text, Snackbar.LENGTH_SHORT)
        snackBar.view.setBackgroundColor(ContextCompat.getColor(this, R.color.colorRed))
        snackBar.setActionTextColor(ContextCompat.getColor(this, R.color.colorSuperLight))
        snackBar.show()

    }

}