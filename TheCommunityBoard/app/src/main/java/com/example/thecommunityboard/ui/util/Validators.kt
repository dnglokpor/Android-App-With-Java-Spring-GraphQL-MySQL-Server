package com.example.thecommunityboard.ui.util

import android.util.Log
import android.widget.TextView
import com.example.thecommunityboard.R

fun validateUsername(view: TextView?): Boolean {
    var valid = false
    if (view != null) {
        val pattern = Regex("^[A-Za-z](?:\\w){5,}$")
        val username = view.text.toString()
        valid = username.isNotEmpty() && pattern.matches(username)
        if (!valid) {
            if (username.isEmpty())
                view.error = view.resources.getString(R.string.empty_field_error, "username")
            else
                view.error = view.resources.getString(R.string.username_hint)
            view.text = ""
        }
    }
    return valid
}

/**
 *
 */
fun validateDate(view: TextView?): Boolean{
    var valid = false
    if (view != null) {
        val date = view.text.toString().split(Regex("[\\W]")).filterNot {it.isEmpty()}
        Log.d("validateDate", date.toString())
        valid = date.size == 3 &&
            Regex("[A-Za-z]{3}").matches(date[0]) &&
              Regex("[1-9]{1,2}").matches(date[1]) && (date[1].toInt() in 1..31) &&
              Regex("[1-9]{4}").matches(date[2])
        if (!valid) {
            view.error = "Enter a valid date!"
            view.text = ""
        }
    }
    return valid
}
/**
 * checks if a the view contains a password that meets requirements.
 * reset the text if the password doesn't meet requirement
 * @param view the view to check
 * @return true if the password meets requirements
 */
fun validatePassword(view: TextView?): Boolean {
    var valid = false
    if (view != null) {
        val pattern = Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{10,}$")
        val password = view.text.toString()
        valid = password.isNotEmpty() && pattern.matches(password)
        if (! valid) {
            if (password.isEmpty())
                view.error = view.resources.getString(R.string.empty_field_error, "password")
            else
                view.error = view.resources.getString(R.string.password_hint)
            view.text = ""
        }
    }
    return valid
}