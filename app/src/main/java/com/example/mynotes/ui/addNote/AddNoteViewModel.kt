package com.example.mynotes.ui.favorite

import android.widget.Toast
import androidx.lifecycle.ViewModel
import android.content.Context
import com.google.firebase.firestore.FirebaseFirestore

class AddNoteViewModel : ViewModel() {

    private val db = FirebaseFirestore.getInstance()

    fun addNote(context: Context, title: String, content: String) {
        val note = hashMapOf("title" to title, "content" to content)

        db.collection("notes")
            .add(note)
            .addOnSuccessListener {
                Toast.makeText(context, "Nota añadida con éxito", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                Toast.makeText(context, "Error al añadir nota: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }
}
