package com.example.thecommunityboard.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.thecommunityboard.R
import com.example.thecommunityboard.model.Note
import com.example.thecommunityboard.ui.util.randomColor

const val TAG = "MyNoteAdapter"
class MyNotesAdapter (private val context: Context, private val notes: List<Note>)
    : RecyclerView.Adapter<MyNotesAdapter.NoteViewHolder>(){
    class NoteViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.note_title)
        val body: TextView = view.findViewById(R.id.note_body)
        val signature: TextView = view.findViewById(R.id.note_signature)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.note, parent, false)
        return NoteViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.title.findViewById<CardView>(R.id.note_card).background.setTint(randomColor())
        val note = notes[position]
        holder.title.text = note.title
        holder.body.text = note.body
        holder.signature.text = note.author
    }

    override fun getItemCount(): Int {
        if
        return notes.size
    }
}