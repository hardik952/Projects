package com.example.noteapplication

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.noteapplication.databinding.DialogNotesBinding
import com.example.noteapplication.databinding.LayoutNotesBinding

class NoteAdapter(private var context: Context, private var noteList: MutableList<Note>) :
    RecyclerView.Adapter<NoteAdapter.MyViewHolder>() {

    var noteDeleteClickListener: ((position: Int, note: Note) -> Unit)? = null
//    var noteEditClickListener: ((position: Int, note: Note) -> Unit)? = null

    class MyViewHolder(var binding: LayoutNotesBinding) : ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var view = LayoutNotesBinding.inflate(LayoutInflater.from(context), parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var notes = noteList[position]
//        holder.binding.tvMsg.text = notes.note

        holder.binding.btnDelete.setOnClickListener {
//            noteList.remove(notes)
//            notifyItemRemoved(position)
            noteDeleteClickListener?.invoke(position,notes)
        }
        holder.binding.btnEdit.setOnClickListener {

        }
    }

    fun deleteNote(position: Int){
        noteList.removeAt(position)
        notifyDataSetChanged()
    }
    fun setNote(mutableList: MutableList<Note>){
        noteList = mutableList
        notifyDataSetChanged()
    }
//    fun editNoteDialog(){
//        var bind = DialogNotesBinding.inflate(LayoutInflater.from(context))
//        var builder = AlertDialog.Builder(context)
//        builder.setView(bind.root)
//        var dialog = builder.create()
//        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//        dialog.show()
//
//        AppDatabase.getInstance(context)?.noteDao()!!.getSingleNote()
////        bind.tvMsgDialog.setText(notes.message)
////        bind.tvNameDialog.setText(notes.name)
////        bind.tvEmailDialog.setText(notes.email)
//
//        bind.btnUpdate.setOnClickListener {
//            var updatedMsg = bind.tvMsgDialog.text.toString().trim()
//            var updatedName = bind.tvNameDialog.text.toString().trim()
//            var updatedEmail = bind.tvEmailDialog.text.toString().trim()
//
////            noteList[position].message = updatedMsg
////            noteList[position].email = updatedEmail
////            noteList[position].name = updatedName
////            holder.binding.tvMsg.text = updatedMsg
//
////            notifyItemChanged(position)
////            notifyDataSetChanged()
////            noteEditClickListener?.invoke(position,notes)
//            dialog.dismiss()
//        }
//    }
}