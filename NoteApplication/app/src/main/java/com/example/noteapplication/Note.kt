package com.example.noteapplication

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity("note_table")
data class Note(
    @PrimaryKey(autoGenerate = true)
    var id :Int = 0,
    var note:String
)
