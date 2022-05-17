package com.example.thecommunityboard.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.apollographql.apollo3.exception.ApolloException
import com.example.tcbspringbootgraphqlserver.AddUserMutation
import com.example.thecommunityboard.databinding.SignUpBinding
import com.example.thecommunityboard.io.apolloClient
import com.example.thecommunityboard.model.User
import com.example.thecommunityboard.ui.util.*
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.coroutines.*

private const val TAG = "SignUpFragment"

class SignUpFragment : Fragment() {
    private lateinit var binding : SignUpBinding
    private val model: MainViewModel by activityViewModels()

    // custom listener for sign up button
    private val signUpHandler = View.OnClickListener {
        Log.d(TAG, "sign up handler called")
        // check each field and display errors if necessary
        if (!binding.termsCheck.isChecked){
            Toast.makeText(requireContext(), "You must check the checkbox",Toast.LENGTH_LONG)
                .show()
        }else{
            val requestIsValid = validateUsername(binding.usernameText) &&
                  validateDate(binding.bdateText) && validatePassword(binding.passwordText) &&
                  binding.termsCheck.isChecked
            if (requestIsValid) {
                // coroutine fetch of user
                CoroutineScope(Dispatchers.IO).launch {
                    val username = binding.usernameText.text.toString()
                    val birthdate = binding.bdateText.text.toString()
                    val password = binding.passwordText.text.toString()
                    val result = registerNewUser(username, birthdate, password) // server query
                    Dispatchers.Main.dispatch(coroutineContext) {binding.queryIndicator.hide()}
                    if (result) {
                        Log.d(TAG, "User created successfully")
                        Dispatchers.Main.dispatch(coroutineContext) {
                            Toast.makeText(
                                requireContext(),
                                "Welcome ${model.getUser().userId}",
                                Toast.LENGTH_LONG
                            ).show()
                            val action = SignUpFragmentDirections
                                .actionSignUpFragmentToUserMainFragment(model.getUsername())
                            findNavController().navigate(action)
                        }
                    } else {
                        Log.d(TAG, "User NOT created")
                        Dispatchers.Main.dispatch(coroutineContext) {
                            Toast.makeText(
                                requireContext(),
                                "Couldn't add user. See error logs.",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }
                // start loading animation
                binding.queryIndicator.show()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // date picker instance and setup
        val mDateBuilder = MaterialDatePicker.Builder.datePicker()
        with(mDateBuilder){
            setTitleText("Select Date of Birth")
            setSelection(MaterialDatePicker.todayInUtcMilliseconds())
            setInputMode(MaterialDatePicker.INPUT_MODE_TEXT)
        }
        val mDatePicker =  mDateBuilder.build()
        mDatePicker.addOnPositiveButtonClickListener {
            setBdate(mDatePicker.headerText)
        }

        // buttons action handling
        binding.bdateInputButton.setOnClickListener {
            mDatePicker.show(requireActivity().supportFragmentManager,
                "MaterialDatePicker")
            Log.d(TAG, "datePicker launched")
        }
        binding.passwordText.setOnTouchListener(revealPasswordListener)
        binding.passwordText.setOnKeyListener { v, keyCode, _ -> hideKeyboard(v, keyCode) }
        binding.signUpButton.setOnClickListener(signUpHandler)
    }

    private suspend fun registerNewUser(username: String, birthdate: String, password: String): Boolean{
        Log.d(TAG, "registerNewUser called")
        val response = try {
            apolloClient.mutation(AddUserMutation(username, birthdate, password))
                .execute()
        } catch (e: ApolloException) {
            Log.e(TAG, "User registration failure", e)
            null
        }
        val user = response?.data?.addUser
        if (user != null)
            model.setUser(User(user.id.toLong(), user.userId, user.birthDate))
        return user != null
    }

    private fun setBdate(date: String){
        Log.d(TAG, "setBdate called with $date")
        binding.bdateInput.editText!!.setText(date)
    }
}