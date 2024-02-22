package com.example.deezermusicapp

import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.deezermusicapp.Model.Data
import com.example.deezermusicapp.databinding.SongLayoutBinding


class SongAdapter(var context: Context, var songList: MutableList<Data>) :
    Adapter<SongAdapter.MyViewHolder>() {

    private var lastSelection: MediaPlayer? = null

    class MyViewHolder(var bind: SongLayoutBinding) : ViewHolder(bind.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var view = SongLayoutBinding.inflate(LayoutInflater.from(context), parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return songList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        var songs = songList[position]

        var mediaPlayer = MediaPlayer.create(context, songs.songLink.toUri())

        holder.bind.songTitle.text = songs.title
        holder.bind.artist.text = songs.artist.name
        holder.bind.duration.text = songs.duration.toString()
        holder.bind.playBtn.setOnClickListener {
//            holder.bind.playBtn.setImageResource(R.drawable.pause)
            if (lastSelection != null && lastSelection!!.isPlaying) {
                lastSelection!!.pause()
            }
            lastSelection = mediaPlayer
            mediaPlayer.start()
            holder.bind.songTitle.isSelected = true
            holder.bind.playBtn.setColorFilter(Color.RED,PorterDuff.Mode.SRC_IN)
            holder.bind.pauseBtn.setColorFilter(Color.BLACK,PorterDuff.Mode.SRC_IN)
        }

        holder.bind.pauseBtn.setOnClickListener {
            mediaPlayer.pause()
            holder.bind.songTitle.isSelected = false
            holder.bind.pauseBtn.setColorFilter(Color.RED,PorterDuff.Mode.SRC_IN)
            holder.bind.playBtn.setColorFilter(Color.BLACK,PorterDuff.Mode.SRC_IN)
        }
    }
}