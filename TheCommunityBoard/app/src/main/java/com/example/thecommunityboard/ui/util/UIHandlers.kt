package com.example.thecommunityboard.ui.util

import android.content.Context
import android.text.InputType
import android.util.Log
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.example.thecommunityboard.R
import com.google.android.material.textfield.TextInputEditText

private const val TAG = "UIHandlers"

val revealPasswordListener = View.OnTouchListener { v, event ->
    v.performClick()
    Log.d(TAG, "eye listener called")
    with(v as TextInputEditText){
        val DRAWABLE_RIGHT = 2
        if (event.action == MotionEvent.ACTION_UP) {
            if (event.rawX >= right - compoundDrawables[DRAWABLE_RIGHT].bounds.width()) {
                // solved the pipe separator problem by using raw hex value of constant
                // 0x81 is Input.TYPE_CLASS_TEXT|Input.TYPE_TEXT_VARIATION_PASSWORD
                // 0x91 is Input.TYPE.TEXT.VARIATION_VISIBLE_PASSWORD
                v.inputType = if (v.inputType == 0x81) 0x91 else 0x81
                setSelection(text.toString().length) // put cursor at the end
                setTextAppearance(R.style.Widget_TheCommunityBoard_EditText) // reformat text
            }
        }
    }
    return@OnTouchListener false
}
fun hideKeyboard(view: View, keyCode: Int): Boolean {
    if (keyCode == KeyEvent.KEYCODE_ENTER) {
        // Hide the keyboard
        val inputMethodManager =
            view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        return true
    }
    return false
}