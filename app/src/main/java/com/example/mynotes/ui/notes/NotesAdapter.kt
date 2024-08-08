package com.example.mynotes.ui.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mynotes.R

class NotesAdapter (private val onDeleteClick: (Note) -> Unit) : ListAdapter<Note, NotesAdapter.NoteViewHolder>(NoteDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(getItem(position), onDeleteClick)
    }

    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.textViewTitle)
        private val contentTextView: TextView = itemView.findViewById(R.id.textViewContent)
        private val deleteButton: ImageButton = itemView.findViewById(R.id.buttonDelete)

        fun bind(note: Note, onDeleteClick: (Note) -> Unit) {
            titleTextView.text = note.title
            contentTextView.text = note.content

            deleteButton.setOnClickListener {
                onDeleteClick(note)
            }
        }
    }

    class NoteDiffCallback : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.title == newItem.title && oldItem.content == newItem.content
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem
        }
    }
}
