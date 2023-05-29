package com.labactivity.techcookreservation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.labactivity.techcookreservation.databinding.ActivityViewReservationsBinding
import kotlinx.coroutines.launch

class ViewReservations : AppCompatActivity() {
    private lateinit var binding:ActivityViewReservationsBinding
    private lateinit var reservationDao: ReservationDao
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewReservationsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView = binding.myList
        recyclerView.layoutManager = LinearLayoutManager(this)


        //back button
        binding.btnBack.setOnClickListener(){
            this.finish()
        }

        //Setting up Reservation List

        lifecycleScope.launch{
            //getting database instance
            val database = DatabaseProvider.getDatabase(this@ViewReservations)

            //accessing database through Dao
            reservationDao = database.reservationDao()

            //retrieving Reservations from database
            val resList = reservationDao.getAllReservation()

            //Arraylist for Reservations
            var reservation:ArrayList<Item> = ArrayList()

            //Storing Reservation in an Arraylist from database
            var assign = 1
            for(res in resList){
                var item = Item(assign = assign, id = res.id, name = res.name, date = res.date,
                    time = res.time, children = res.children, adults = res.adults, subtotal = res.subtotal)
                reservation.add(item)
                assign++
            }

           //Displays Reservation List
           recyclerView.adapter = ItemAdapter(this@ViewReservations, reservation)
        }
    }
}