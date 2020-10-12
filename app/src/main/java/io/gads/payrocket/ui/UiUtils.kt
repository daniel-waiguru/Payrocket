package io.gads.payrocket.ui

import android.app.Activity
import android.content.ContextWrapper
import android.graphics.Color
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.google.android.material.snackbar.Snackbar
import io.gads.payrocket.R
import io.gads.payrocket.model.ResultWrapper


fun showErrorSnackBar(view: View, msgId: Int) {
    if (msgId > 0) {
        hideKeyboard(view)
        Snackbar.make(
            view,
            msgId,
            Snackbar.LENGTH_SHORT
        ).setBackgroundTint(Color.RED).show()
    }
}

fun showErrorSnackBar(view: View, msg: String = "") {
    if (msg.isNotBlank()) {
        hideKeyboard(view)
        Snackbar.make(
            view,
            msg,
            Snackbar.LENGTH_SHORT
        ).setBackgroundTint(Color.RED).show()
    }
}

fun showErrorSnackBar(view: View, error: ResultWrapper<Any>) {
    when (error) {
        is ResultWrapper.GenericError -> {
            showErrorSnackBar(
                view,
                error.error ?: view.context.getString(R.string.something_went_wrong)
            )
        }

        is ResultWrapper.NetworkError -> showErrorSnackBar(
            view,
            R.string.no_internet
        )
    }
}

fun hideKeyboard(view: View) {
    var context = view.context
    while (context !is Activity && context is ContextWrapper) {
        context = context.baseContext
    }

    val inputMethodManager =
        (context as Activity).getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager

    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)

}
