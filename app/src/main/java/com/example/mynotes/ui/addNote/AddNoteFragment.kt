package com.example.mynotes.ui.addNote

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.mynotes.databinding.FragmentAddnoteBinding
import com.example.mynotes.ui.favorite.AddNoteViewModel

class AddNoteFragment : Fragment() {

    private val addNoteViewModel: AddNoteViewModel by viewModels()
    private var _binding: FragmentAddnoteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddnoteBinding.inflate(inflater, container, false)

        // Handle add note button click
        binding.addNoteButton.setOnClickListener {
            val title = binding.inputNoteTitle.text.toString()
            val content = binding.inputNote.text.toString()
            if (title.isNotEmpty() && content.isNotEmpty()) {
                addNoteViewModel.addNote(requireContext(), title, content)
                binding.inputNoteTitle.text.clear()
                binding.inputNote.text.clear()
                Toast.makeText(context, "Nota añadida", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Completa ambos campos", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}