package com.example.noteapplication

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteapplication.databinding.ActivityMainBinding
import com.example.noteapplication.databinding.DialogNotesBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var noteList = mutableListOf<Note>()
    private lateinit var noteAdapter: AdapterNote
    private var db: AppDatabase? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = AppDatabase.getInstance(this)

        noteAdapter = AdapterNote(this, noteList)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = noteAdapter

        binding.addNote.setOnClickListener {
            var note = binding.etNote.text.toString().trim()
            if (binding.etNote.text.isEmpty()){
                Toast.makeText(this, "Please write something", Toast.LENGTH_SHORT).show()
            }else{
                addNote(note)
                Toast.makeText(this, "Note Added", Toast.LENGTH_SHORT).show()
//            finish()
//            startActivity(intent)
                binding.etNote.text.clear()
                recreate()
            }
        }
        noteAdapter.noteDeleteListener = { position, note ->
            db?.noteDao()!!.deleteNote(note)
            Toast.makeText(this, "Note Deleted", Toast.LENGTH_SHORT).show()
            noteAdapter.deleteNote(position)
        }
        noteAdapter.noteEditListener = { position, note ->
            noteUpdateDialog(position,note)
        }
    }

    private fun addNote(note: String) {
        thread(start = true) {
            var notes = Note(note = note)
            db?.noteDao()!!.createNote(notes)
        }

    }

    private fun readNotes() {
        noteList = db?.noteDao()!!.getAllNote()
        noteAdapter.setNote(noteList)
        noteAdapter.notifyDataSetChanged()
    }

    override fun onResume() {
        super.onResume()
        readNotes()
    }

    private fun noteUpdateDialog(position: Int, note: Note) {
        var bind = DialogNotesBinding.inflate(LayoutInflater.from(this))
        var builder = AlertDialog.Builder(this)
            .setView(bind.root)
        var dialog = builder.create()
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
        note.let {
            bind.tvNoteDialog.setText(note.note)
        }
//        var updatedNote = bind.tvNoteDialog.text.toString().trim()
//        var noteObject =  Note(note = updatedNote)
        bind.btnUpdate.setOnClickListener {
                var updatedNote = bind.tvNoteDialog.text.toString().trim()
                var notes = noteList[position]
                notes.note = updatedNote
                db?.noteDao()!!.updateNote(notes)
                Toast.makeText(this, "Note Updated", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
                recreate()
        }
    }
}