package com.example.mynotes.ui.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.mynotes.databinding.FragmentNotesBinding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.fragment.app.viewModels

class NotesFragment : Fragment() {

    private val notesViewModel: NotesViewModel by viewModels()
    private var _binding: FragmentNotesBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: NotesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = NotesAdapter { note ->
            notesViewModel.deleteNote(note)
        }

        binding.recyclerViewNotes.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewNotes.adapter = adapter

        notesViewModel.notes.observe(viewLifecycleOwner) { notes ->
            adapter.submitList(notes)
        }

        notesViewModel.errorMessage.observe(viewLifecycleOwner) { message ->
            message?.let {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        }

        notesViewModel.fetchNotes()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
