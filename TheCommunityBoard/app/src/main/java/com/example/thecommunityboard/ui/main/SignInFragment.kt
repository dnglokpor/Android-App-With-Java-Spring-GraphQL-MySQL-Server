package com.example.thecommunityboard.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.apollographql.apollo3.exception.ApolloException
import com.example.tcbspringbootgraphqlserver.CheckUserQuery
import com.example.thecommunityboard.R
import com.example.thecommunityboard.databinding.SignInBinding
import com.example.thecommunityboard.io.apolloClient
import com.example.thecommunityboard.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.*
import com.example.thecommunityboard.ui.util.hideKeyboard
import com.example.thecommunityboard.ui.util.revealPasswordListener
import com.example.thecommunityboard.ui.util.validatePassword
import com.example.thecommunityboard.ui.util.validateUsername

private const val TAG = "SignInFragment"

class SignInFragment : Fragment() {
    private lateinit var binding : SignInBinding
    private val model: MainViewModel by activityViewModels()

    // custom OnClickListener for sign in
    private val signInHandler = View.OnClickListener {
        Log.d(TAG, "handler called")
        // check data
        val requestIsValid = validateUsername(binding.usernameText) && validatePassword(binding.passwordText)
        if (requestIsValid) {
            // coroutine fetch
            CoroutineScope(Dispatchers.IO).launch {
                val result = authenticate()
                Dispatchers.Main.dispatch(coroutineContext) {binding.queryIndicator.hide()}
                if (result) { // user found
                    Log.d(TAG, "User found")
                    Dispatchers.Main.dispatch(coroutineContext) {
                        Toast.makeText(
                            requireContext(),
                            "Welcome ${model.getUser().userId}",
                            Toast.LENGTH_LONG
                        ).show()
                        val action = SignInFragmentDirections
                            .actionSignInFragmentToUserMainFragment(model.getUsername())
                        findNavController().navigate(action)
                    }
                } else { // user not found
                    Log.d(TAG, "User NOT found")
                    Dispatchers.Main.dispatch(coroutineContext) {
                        Toast.makeText(
                            requireContext(),
                            "Please verify your identifiers",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
            // start loading animation
            binding.queryIndicator.show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // various buttons listeners
        binding.passwordText.setOnTouchListener(revealPasswordListener)
        binding.signInButton.setOnClickListener(signInHandler)
        binding.passwordText.setOnKeyListener { v, keyCode, _ -> hideKeyboard(v, keyCode) }
        binding.signUpButton.setOnClickListener { moveToSignUp(it) }
    }

    private fun moveToSignUp(view: View){
        Log.d(TAG, "moveToSignUp called")
        view.findNavController()
            .navigate(R.id.action_signInFragment_to_signUpFragment)
    }

    /**
     * checks the users credentials through in a coroutine
     */
    private suspend fun authenticate(): Boolean {
        Log.d(TAG, "authenticate called")
        val username = binding.usernameText.text.toString()
        val password = binding.passwordText.text.toString()
        val response = try {
            apolloClient.query(CheckUserQuery(username, password)).execute()
        } catch (e: ApolloException) {
            Log.e(TAG, "Authentication failure", e)
            null
        }
        val user = response?.data?.checkUser
        if (user != null)
            model.setUser(User(user.id.toLong(), user.userId, user.birthDate))
        return user != null
    }
}