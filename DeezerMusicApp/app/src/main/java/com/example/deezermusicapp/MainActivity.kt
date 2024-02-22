package com.example.deezermusicapp

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.deezermusicapp.Model.Artist
import com.example.deezermusicapp.Model.Data
import com.example.deezermusicapp.Model.SearchList
import com.example.deezermusicapp.Model.SearchResponse
import com.example.deezermusicapp.Network.ApiClient
import com.example.deezermusicapp.databinding.ActivityMainBinding
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    var searchList = mutableListOf<SearchList>()
    private lateinit var searchAdapter: ArrayAdapter<String>

    var songList = mutableListOf<Data>()
    lateinit var songAdapter: SongAdapter

    var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prepareSearchList()
        searchAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, searchList.map { it.name })
        binding.spinner.setAdapter(searchAdapter)
        binding.spinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                var selectedArtist = searchList[position].name

                mediaPlayer?.let {
                    if (it.isPlaying) {
                        it.stop()
                        it.reset()
                        it.release()
                        mediaPlayer = null
                    }
                }

                fetchSong(selectedArtist)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                Log.d("TAG", "onNothingSelected: ")
            }
        }

        songAdapter = SongAdapter(this, songList)
        binding.songRecyclerView.adapter = songAdapter
        binding.songRecyclerView.layoutManager = LinearLayoutManager(this)

        fetchSong(searchList.first().name)
    }

    private fun fetchSong(artist: String) {
        ApiClient.init().getSong(ApiClient.getHeaders(), artist).enqueue(object :
            Callback<SearchResponse> {
            override fun onResponse(
                call: Call<SearchResponse>,
                response: Response<SearchResponse>
            ) {
                if (response.isSuccessful) {
                    var res = response.body()?.data
                    res?.let {
                        songAdapter.songList = it
                        songAdapter.notifyDataSetChanged()
                    }
                }
            }

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                Log.d("TAG", "onFailure: ")
            }
        })
    }

    private fun prepareSearchList() {
        searchList.add(SearchList(1, "eminem"))
        searchList.add(SearchList(2, "Darshan Raval"))
        searchList.add(SearchList(3, "Justin Bieber"))
        searchList.add(SearchList(4, "Tylor Swift"))
        searchList.add(SearchList(5, "Kishor Kumar"))
        searchList.add(SearchList(6, "Mohammed Rafi"))
        searchList.add(SearchList(2, "Sunidhi Chauhan"))
        searchList.add(SearchList(7, "Alan Walker"))
        searchList.add(SearchList(8, "A. R. Rahman"))
        searchList.add(SearchList(9, "Atif Aslam"))
        searchList.add(SearchList(10, "Billie eilish"))
        searchList.add(SearchList(11, "Snoop Dogg"))
        searchList.add(SearchList(12, "Arijit Singh"))
        searchList.add(SearchList(13, "Sonu Nigam"))
        searchList.add(SearchList(14, "Shreya Ghosal"))
        searchList.add(SearchList(15, "B Praak"))
    }
}