package com.farid.pokemonapi.common

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager


/**
 * Created by Mohammed Farid on 8/4/2021
 * Contact me : m.farid.shawky@gmail.com
 */
object KeyboardUtils {
    fun hideSoftKeyboard(activity: Activity) {
        val inputMethodManager: InputMethodManager = activity.getSystemService(
            Activity.INPUT_METHOD_SERVICE
        ) as InputMethodManager
        if (inputMethodManager.isAcceptingText) {
            inputMethodManager.hideSoftInputFromWindow(
                activity.currentFocus?.windowToken,
                0
            )
        }
    }

    fun hideKeyboard(view: View?, activity: Activity) {
        val inputMethodManager =
            activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager?
        if (inputMethodManager?.isAcceptingText == true) {
            inputMethodManager.hideSoftInputFromWindow(view?.windowToken, 0)
        }
    }
}