package ru.webanimal.test50_bottom_sheet_dialog.extensions

import android.view.ViewGroup

var ViewGroup.isVisible
    set(visible) {
        visibility = when {
            visible -> ViewGroup.VISIBLE
            else -> ViewGroup.GONE
        }
    }
    get() = visibility == ViewGroup.VISIBLE

fun ViewGroup.hideAnimation() {
    animate().setDuration(250)
        .alpha(0.1f)
        .scaleX(0.1f)
        .scaleY(0.1f)
        .withEndAction {
            visibility = ViewGroup.INVISIBLE
        }
}

fun ViewGroup.showAnimation() {
    animate().setDuration(250)
        .alpha(1f)
        .scaleX(1f)
        .scaleY(1f)
        .withStartAction {
            visibility = ViewGroup.VISIBLE
        }
}

fun ViewGroup?.show() {
    this?.visibility = ViewGroup.VISIBLE
}

fun ViewGroup?.hide() {
    this?.visibility = ViewGroup.GONE
}

fun ViewGroup?.conceal() {
    this?.visibility = ViewGroup.INVISIBLE
}