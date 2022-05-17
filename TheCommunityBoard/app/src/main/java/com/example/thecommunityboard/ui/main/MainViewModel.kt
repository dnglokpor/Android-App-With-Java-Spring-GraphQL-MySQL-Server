package com.example.thecommunityboard.ui.main

import androidx.lifecycle.ViewModel
import com.example.thecommunityboard.model.Note
import com.example.thecommunityboard.model.User

class MainViewModel : ViewModel() {
    private var user: User? = null
    private var localNotes: List<Note> = mutableListOf() // empty by default

    // data accessor
    fun setUser(u: User){if(user == null) user = u}
    fun logOut() {
        // clean up
        user = null
        localNotes = mutableListOf()
    }
    fun getUser():User {
        if (user != null)
            return user!!
        throw AssertionError("no user defined!!!")
    }
    fun getLocalNotes():List<Note> {return localNotes}
    fun getUserId() : String = getUser().userId
    fun getUsername(): String = getUser().userId.split('#')[0]
    fun getBirthdate(): String = getUser().birthDate.toString()
}