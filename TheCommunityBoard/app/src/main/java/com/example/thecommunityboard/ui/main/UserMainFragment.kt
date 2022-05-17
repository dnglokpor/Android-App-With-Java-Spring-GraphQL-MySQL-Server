package com.example.thecommunityboard.ui.main

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.thecommunityboard.R
import com.example.thecommunityboard.adapter.MyNotesAdapter
import com.example.thecommunityboard.databinding.UserMainBinding

private const val TAG = "UserMainFragment"

class UserMainFragment: Fragment() {
    private lateinit var binding: UserMainBinding
    private lateinit var recyclerView: RecyclerView
    private val model: MainViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true) // display my two buttons
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = UserMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // display welcome message
        //requireActivity().getString(R.string.welcome_message, arguments?.get("username")) // not needed
        // start recycler view
        recyclerView = binding.localNotes
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        recyclerView.adapter = MyNotesAdapter(requireContext(), model.getLocalNotes()) // send notes in
        // various buttons listeners
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.user_main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.board_button -> {
                Toast.makeText(requireContext(), "Board icon clicked", Toast.LENGTH_LONG)
                    .show()
                true
            }
            R.id.settings_button -> {
                Toast.makeText(requireContext(), "Settings icon clicked", Toast.LENGTH_LONG)
                    .show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}