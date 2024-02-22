package com.example.deezermusicapp.Model

import com.google.gson.annotations.SerializedName

data class Artist(
    val id: Int,
    val name: String,
    @SerializedName("picture_medium")
    val picture: String,
)