package ru.webanimal.test50_bottom_sheet_dialog.extensions

import android.view.View
import com.google.android.material.bottomsheet.BottomSheetBehavior

val BottomSheetBehavior<*>.stateName: String
    get() = when (state) {
        BottomSheetBehavior.STATE_COLLAPSED -> "STATE_COLLAPSED($state)"
        BottomSheetBehavior.STATE_DRAGGING -> "STATE_DRAGGING($state)"
        BottomSheetBehavior.STATE_EXPANDED -> "STATE_EXPANDED($state)"
        BottomSheetBehavior.STATE_HALF_EXPANDED -> "STATE_HALF_EXPANDED($state)"
        BottomSheetBehavior.STATE_HIDDEN -> "STATE_HIDDEN($state)"
        BottomSheetBehavior.STATE_SETTLING -> "STATE_SETTLING($state)"
        else -> "STATE($state)"
    }

inline val BottomSheetBehavior<View>.isExpanded: Boolean
    get() = state == BottomSheetBehavior.STATE_EXPANDED

fun BottomSheetBehavior<View>?.expand() {
    this?.state = BottomSheetBehavior.STATE_EXPANDED
}

fun BottomSheetBehavior<View>?.collapse() {
    this?.state = BottomSheetBehavior.STATE_COLLAPSED
}