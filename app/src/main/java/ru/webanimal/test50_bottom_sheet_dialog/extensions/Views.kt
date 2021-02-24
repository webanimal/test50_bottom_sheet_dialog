package ru.webanimal.test50_bottom_sheet_dialog.extensions

import android.view.View

var View.isVisible
    set(visible) {
        visibility = when {
            visible -> View.VISIBLE
            else -> View.GONE
        }
    }
    get() = visibility == View.VISIBLE

fun View.hideAnimation() {
    animate().setDuration(250)
        .alpha(0.1f)
        .scaleX(0.1f)
        .scaleY(0.1f)
        .withEndAction {
            visibility = View.INVISIBLE
        }
}

fun View.showAnimation() {
    animate().setDuration(250)
        .alpha(1f)
        .scaleX(1f)
        .scaleY(1f)
        .withStartAction {
            visibility = View.VISIBLE
        }
}

fun View?.show() {
    this?.visibility = View.VISIBLE
}

fun View?.hide() {
    this?.visibility = View.GONE
}

fun View?.conceal() {
    this?.visibility = View.INVISIBLE
}