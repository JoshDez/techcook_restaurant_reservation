package com.labactivity.techcookreservation

import androidx.room.*

@Dao
interface ReservationDao {
    //Retrieve all of reservation_table
    @Query("SELECT * FROM reservation_table")
    suspend fun getAllReservation(): List<Reservation>

    //Insert
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertReservation(Reservation: Reservation)

    //Delete
    @Query("DELETE FROM reservation_table WHERE id = :id")
    suspend fun deleteUserById(id: Long)
}