package com.example.noteapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.noteapplication.databinding.LayoutNotesBinding

class AdapterNote(private var context: Context, private var noteList: MutableList<Note>) :
    Adapter<AdapterNote.MyViewHolder>() {

    var noteDeleteListener: ((position: Int, note: Note) -> Unit)? = null
    var noteEditListener: ((position: Int, note: Note) -> Unit)? = null

    class MyViewHolder(var bind: LayoutNotesBinding) : ViewHolder(bind.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var view = LayoutNotesBinding.inflate(LayoutInflater.from(context), parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var notes = noteList[position]

        holder.bind.tvNote.text = notes.note
        holder.bind.btnDelete.setOnClickListener {
            noteDeleteListener?.invoke(position,notes)
        }
        holder.bind.btnEdit.setOnClickListener {
            noteEditListener?.invoke(position,notes)
        }
    }
    fun setNote(mutableList: MutableList<Note>){
        noteList = mutableList
    }
    fun deleteNote(position:Int){
        noteList.removeAt(position)
        notifyItemRemoved(position)
    }
}