package com.example.a7minuteworkout.model

data class Exercise(
    val id:Int,
    val name:String,
    val image:Int,
    var isComplete:Boolean,
    var isSelected:Boolean
)