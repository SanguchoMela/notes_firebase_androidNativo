package com.example.mynotes.ui.notes

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject

class NotesViewModel : ViewModel() {
    private val db = FirebaseFirestore.getInstance()

    private val _notes = MutableLiveData<List<Note>>()
    val notes: LiveData<List<Note>> = _notes

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    fun fetchNotes() {
        db.collection("notes")
            .get()
            .addOnSuccessListener { result ->
                val notesList = mutableListOf<Note>()
                for (document in result) {
                    val note = document.toObject<Note>().copy(id = document.id)
                    notesList.add(note)
                }
                _notes.value = notesList
            }
            .addOnFailureListener { exception ->
                _errorMessage.value = "Error al cargar las notas: ${exception.message}"
            }
    }

    fun deleteNote(note: Note) {
        db.collection("notes").document(note.id).delete()
            .addOnSuccessListener {
                fetchNotes()
            }
            .addOnFailureListener { exception ->
                _errorMessage.value = "Error al eliminar la nota: ${exception.message}"
            }
    }
}

data class Note(
    val id: String = "",
    val title: String = "",
    val content: String = ""
)