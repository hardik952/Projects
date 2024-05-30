package com.example.noteapplication

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface NoteDao {

    @Insert
    fun createNote(note: Note)

    @Query("SELECT * FROM note_table ORDER BY id DESC")
    fun getAllNote():MutableList<Note>

    @Update
    fun updateNote(note: Note)

    @Delete
    fun deleteNote(note: Note)
}