package com.labactivity.techcookreservation

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reservation_table")
data class Reservation(
    @PrimaryKey(autoGenerate = true)
    val id:Long = 0,
    val name:String,
    val date:String,
    val time:String,
    val children:Int,
    val adults:Int,
    val subtotal:Int
)
