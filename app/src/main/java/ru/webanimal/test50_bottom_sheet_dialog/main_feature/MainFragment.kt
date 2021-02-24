package ru.webanimal.test50_bottom_sheet_dialog.main_feature

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.widget.SwitchCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ru.webanimal.test50_bottom_sheet_dialog.R
import ru.webanimal.test50_bottom_sheet_dialog.extensions.collapse
import ru.webanimal.test50_bottom_sheet_dialog.extensions.conceal
import ru.webanimal.test50_bottom_sheet_dialog.extensions.expand
import ru.webanimal.test50_bottom_sheet_dialog.extensions.hideAnimation
import ru.webanimal.test50_bottom_sheet_dialog.extensions.isExpanded
import ru.webanimal.test50_bottom_sheet_dialog.extensions.show
import ru.webanimal.test50_bottom_sheet_dialog.extensions.showAnimation
import ru.webanimal.test50_bottom_sheet_dialog.extensions.stateName

class MainFragment : Fragment() {

	private var itemSearchRoot: ViewGroup? = null
	private var tvBehaviorStateText: TextView? = null
	private var fabAddAccount: FloatingActionButton? = null
	private var vBsShade: View? = null
	private var llShowSearchItem: ViewGroup? = null
	private var swtShowSearchItem: SwitchCompat? = null
	private var bsBehavior: BottomSheetBehavior<View>? = null

	private var isSearchViewVisible = false
	
	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		
		return inflater.inflate(R.layout.fragment_main, container, false)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		setupViews(view)
		view.findViewById<FrameLayout>(R.id.layBsSettings)?.let {
			setupMainSettings(it)
		}
	}

	override fun onDestroyView() {
		clearViews()

		super.onDestroyView()
	}

	private fun setupViews(rootView: View) {
		itemSearchRoot = rootView.findViewById(R.id.itemSearchRoot)
		tvBehaviorStateText = rootView.findViewById(R.id.tvBehaviorStateTitle)
		fabAddAccount = rootView.findViewById(R.id.fabAddAccount)
		vBsShade = rootView.findViewById(R.id.vBsShade)
		llShowSearchItem = rootView.findViewById(R.id.llShowSearchItem)
		swtShowSearchItem = rootView.findViewById(R.id.swtShowSearchItem)

		fabAddAccount?.setOnClickListener {
			if (bsBehavior?.isExpanded == true) {
				bsBehavior?.collapse()

			} else {
				bsBehavior?.expand()
			}
		}

		llShowSearchItem?.setOnClickListener {
			isSearchViewVisible = !isSearchViewVisible
			swtShowSearchItem?.isChecked = isSearchViewVisible
			if (bsBehavior?.isExpanded == false) {
				updateSearchVisibility(isSearchViewVisible)
			}
		}
	}

	private fun clearViews() {
		fabAddAccount?.setOnClickListener(null)
		llShowSearchItem?.setOnClickListener(null)

		itemSearchRoot = null
		tvBehaviorStateText = null
		fabAddAccount = null
		vBsShade = null
		llShowSearchItem = null
		swtShowSearchItem = null
		bsBehavior = null

	}

	private fun setupMainSettings(behaviorHolder: View) {
		bsBehavior = BottomSheetBehavior.from(behaviorHolder)
		bsBehavior?.addBottomSheetCallback(object: BottomSheetBehavior.BottomSheetCallback() {
			/**
			  public static final int STATE_DRAGGING = 1;
			  public static final int STATE_SETTLING = 2;
			  public static final int STATE_EXPANDED = 3;
			  public static final int STATE_COLLAPSED = 4;
			  public static final int STATE_HIDDEN = 5;

			  /** The bottom sheet is half-expanded (used when mFitToContents is false). */
			  public static final int STATE_HALF_EXPANDED = 6;
			* */
			override fun onStateChanged(bottomSheet: View, newState: Int) {
				onBsBehaviorStateChanged(newState = newState)
			}

			override fun onSlide(bottomSheet: View, slideOffset: Float) { /* Ignored */ }
		})
	}

	private fun onBsBehaviorStateChanged(newState: Int) {
		tvBehaviorStateText?.text = bsBehavior?.stateName ?: newState.toString()

		when (newState) {
			BottomSheetBehavior.STATE_DRAGGING, // 1
			BottomSheetBehavior.STATE_SETTLING -> { // 2
				updateSearchVisibility(show = false)
				updateFabVisibility(show = false)
				updateBsShadeVisibility(show = true)
			}
			BottomSheetBehavior.STATE_EXPANDED -> { // 3
				updateSearchVisibility(show = false)
				updateFabVisibility(show = false)
				updateBsShadeVisibility(show = false)
			}
			BottomSheetBehavior.STATE_COLLAPSED, // 4
			BottomSheetBehavior.STATE_HIDDEN, // 5
			BottomSheetBehavior.STATE_HALF_EXPANDED -> { // 6
				updateSearchVisibility(show = isSearchViewVisible)
				updateFabVisibility(show = true)
				updateBsShadeVisibility(show = true)
			}
		}
	}

	private fun updateSearchVisibility(show: Boolean) {
		if (show) {
			itemSearchRoot?.showAnimation()

		} else {
			itemSearchRoot?.hideAnimation()
		}
	}

	private fun updateFabVisibility(show: Boolean) {
		if (show) {
			fabAddAccount?.showAnimation()

		} else {
			fabAddAccount?.hideAnimation()
		}
	}

	private fun updateBsShadeVisibility(show: Boolean) {
		if (show) {
			vBsShade.show()

		} else {
			vBsShade.conceal()
		}
	}
	
	companion object {
		fun create() = MainFragment()
	}
}