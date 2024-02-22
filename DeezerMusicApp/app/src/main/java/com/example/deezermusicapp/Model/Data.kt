package com.example.deezermusicapp.Model

import com.google.gson.annotations.SerializedName

data class Data(
    val id: Long,
    val title: String,
    @SerializedName("preview")
    val songLink: String,
    val artist: Artist,
    val duration: Int,
)