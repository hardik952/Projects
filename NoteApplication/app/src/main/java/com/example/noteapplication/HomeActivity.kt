package com.example.noteapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteapplication.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    lateinit var binding: ActivityHomeBinding
    private var noteList = mutableListOf<Model>()
    lateinit var noteAdapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)


    binding.btnAdd.setOnClickListener {

        noteAdapter = NoteAdapter(this,noteList)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = noteAdapter

        var msg = binding.etMessage.text.toString()
        noteList.add(0,Model(msg))
        noteAdapter.notifyItemInserted(0)


        intent.putExtra("MSG",msg)



    }
    }
}