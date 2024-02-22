package com.example.noteapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.noteapplication.databinding.DialogNotesBinding
import com.example.noteapplication.databinding.LayoutNotesBinding

class NoteAdapter(var context: Context,var noteList: MutableList<Model>)
    : RecyclerView.Adapter<NoteAdapter.MyViewHolder>(){
        class MyViewHolder(var binding: LayoutNotesBinding) : ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var view = LayoutNotesBinding.inflate(LayoutInflater.from(context),parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var nList = noteList[position]
        holder.binding.tvMsg.text = nList.message

        holder.binding.btnDelete.setOnClickListener {
            noteList.remove(nList)
            notifyItemRemoved(position)
        }
        holder.binding.btnEdit.setOnClickListener{
            var bind = DialogNotesBinding.inflate(LayoutInflater.from(context))
            var builder = AlertDialog.Builder(context)
            builder.setView(bind.root)
            var dialog = builder.create()
            dialog.show()

            bind.tvMsgDialog.setText(nList.message)

            bind.btnUpdate.setOnClickListener {
                var updatedMsg = bind.tvMsgDialog.text.toString().trim()

                noteList[position].message = updatedMsg

//                holder.binding.tvMsg.text = updatedMsg

                notifyItemChanged(position)

                dialog.dismiss()

            }
        }
    }
}