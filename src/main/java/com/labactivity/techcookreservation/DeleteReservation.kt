package com.labactivity.techcookreservation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.labactivity.techcookreservation.databinding.ActivityDeleteReservationBinding
import com.labactivity.techcookreservation.databinding.PopupLayoutBinding
import kotlinx.coroutines.launch

class DeleteReservation : AppCompatActivity() {
    private lateinit var binding:ActivityDeleteReservationBinding
    private lateinit var popupWindow: PopupWindow
    private lateinit var popupBinding: PopupLayoutBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var reservationDao: ReservationDao
    private var reservation:ArrayList<Item> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeleteReservationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Display list
        displayList()

        //BACK BUTTON
        binding.btnBack.setOnClickListener(){
            this.finish()
        }

        //DELETE BUTTON
        binding.btnDelete.setOnClickListener(){
            // Set up the pop-up window
            popupBinding = PopupLayoutBinding.inflate(layoutInflater)
            popupWindow = PopupWindow(popupBinding.root, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true)

            popupBinding.btnConfirm.setOnClickListener {
                val enteredNumber:Int = popupBinding.edtNum.text.toString().toInt()

                lifecycleScope.launch(){
                    //get database instance
                    val database = DatabaseProvider.getDatabase(this@DeleteReservation)

                    //access database through Dao
                    reservationDao = database.reservationDao()

                    if(enteredNumber > 0 && enteredNumber <= reservation.size){
                        var id = getReservation()[enteredNumber-1].id
                        reservationDao.deleteUserById(id)
                        displayList()
                        Toast.makeText(applicationContext, "Reservation #$enteredNumber is removed", Toast.LENGTH_LONG).show()
                        popupWindow.dismiss()
                    } else {
                        Toast.makeText(applicationContext, "The number is not on the list", Toast.LENGTH_LONG).show()
                    }
                }
            }

            // Dismiss the pop-up window when clicked outside
            popupBinding.root.setOnClickListener {
                popupWindow.dismiss()
            }

            popupWindow.showAtLocation(binding.root, Gravity.CENTER, 0, 0)
        }
    }

    private fun displayList(){
        //Initializing Recycler View
        recyclerView = binding.myList
        recyclerView.layoutManager = LinearLayoutManager(this)
        getReservation().clear()

        //Accessing database
        lifecycleScope.launch{
            //getting database instance
            val database = DatabaseProvider.getDatabase(this@DeleteReservation)

            //accessing database through Dao
            reservationDao = database.reservationDao()

            //retrieving Reservations from database
            val resList = reservationDao.getAllReservation()

            //Storing Reservation in an Arraylist
            var assign = 1
            for(res in resList){
                var item = Item(assign = assign, id = res.id, name = res.name, date = res.date,
                    time = res.time, children = res.children, adults = res.adults, subtotal = res.subtotal)
                getReservation().add(item)
                assign++
            }

            //Displays Reservation List
            recyclerView.adapter = ItemAdapter(this@DeleteReservation, getReservation())
        }
    }
    private fun getReservation(): ArrayList<Item>{
        return this.reservation
    }
}