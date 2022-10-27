package com.plexus.marvel.base

import android.os.Bundle
import com.plexus.marvel.R

/**
 * © Class created by David Angulo , david.angulocorcuera@plexus.es
 * */

class Navigator(private val activity: BaseActivity) {

    private var fragmentManager = activity.supportFragmentManager

    /**
     * Function for manage fragments navigation
     *
     * @param [fragmentToGo] The [BaseFragment] you will navigate to
     * @param [addBackStack] A Boolean for manage the back navigation
     * @param [arguments] data you want to send to the [BaseFragment] you will navigate
     * @param [onResult] A callback to send back any data of interest
     */

    fun navigate(
        fragmentToGo: BaseFragment,
        addBackStack: Boolean = false,
        arguments: Bundle? = null,
    ) {

        val transaction = fragmentManager.beginTransaction()

        if (addBackStack) transaction.addToBackStack(fragmentToGo.LOG_TAG)

        transaction.replace(R.id.fragmentContainerMain, fragmentToGo, fragmentToGo.LOG_TAG)

        if (arguments != null) fragmentToGo.arguments = arguments

        transaction.commit()
    }

    /**
     * Function for back to last fragment in backStack
     */
    fun goToLastFragment() {
        fragmentManager.popBackStackImmediate()
    }

}