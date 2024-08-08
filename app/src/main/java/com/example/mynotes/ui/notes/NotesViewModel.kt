package com.example.mynotes.ui.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject

class NotesViewModel : ViewModel() {
    private val db = FirebaseFirestore.getInstance()

    private val _notes = MutableLiveData<List<Note>>()
    val notes: LiveData<List<Note>> = _notes

    fun fetchNotes() {
        db.collection("notes")
            .get()
            .addOnSuccessListener { result ->
                val notesList = mutableListOf<Note>()
                for (document in result) {
                    val note = document.toObject<Note>()
                    notesList.add(note)
                }
                _notes.value = notesList
            }
            .addOnFailureListener { exception ->
//                Toast.makeText(context, "Error al mostrar notas", Toast.LENGTH_SHORT).show()
            }
    }
}

data class Note(
    val title: String = "",
    val content: String = ""
)