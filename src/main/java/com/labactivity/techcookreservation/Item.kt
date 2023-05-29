package com.labactivity.techcookreservation

data class Item(
    val assign:Int,
    val id:Long,
    val name:String,
    val date:String,
    val time:String,
    val children:Int,
    val adults:Int,
    val subtotal:Int
)