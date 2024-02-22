package com.example.newsapi.Model

data class Country(
    var id:Int,
    var name : String,
    var code:String
){
    override fun toString(): String {
        return code
    }
}
