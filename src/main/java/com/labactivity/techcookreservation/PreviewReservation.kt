package com.labactivity.techcookreservation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.labactivity.techcookreservation.databinding.ActivityPreviewReservationBinding
import kotlinx.coroutines.launch


class PreviewReservation : AppCompatActivity() {
    private lateinit var binding:ActivityPreviewReservationBinding
    private lateinit var reservationDao:ReservationDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPreviewReservationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Default Values
        val defToStore = false
        val defChildren = 0
        val defAdults = 0
        val defSubtotal = 0

        //Retrieving data from previous activity
        val passedName = intent.getStringExtra("name")
        val passedDate = intent.getStringExtra("date")
        val passedTime = intent.getStringExtra("time")
        val passedChildren = intent.getIntExtra("children", defChildren)
        val passedAdults = intent.getIntExtra("adults", defAdults)
        val passedSubTotal = intent.getIntExtra("subtotal", defSubtotal)

        //defines if this activity will store data to the database
        val toStore = intent.getBooleanExtra("toStore", defToStore)

        binding.txtName.setText(passedName)
        binding.txtDate.setText(passedDate)
        binding.txtTime.setText(passedTime)
        binding.txtChildren.setText("$passedChildren")
        binding.txtAdults.setText("$passedAdults")
        binding.txtSubTotal.setText("PHP $passedSubTotal")


        binding.btnConfirm.setOnClickListener(){

            if(toStore){
                lifecycleScope.launch{

                    val reservation = Reservation(name = "$passedName", date = "$passedDate", time = "$passedTime",
                        children = passedChildren, adults = passedAdults, subtotal = passedSubTotal)

                    //get database instance
                    val database = DatabaseProvider.getDatabase(this@PreviewReservation)

                    //access database through Dao
                    reservationDao = database.reservationDao()

                    //Insert Reservation in the database
                    reservationDao.insertReservation(reservation)

                    intent = Intent(this@PreviewReservation, MainActivity::class.java)
                    startActivity(intent)
                    this@PreviewReservation.finish()
                }
            } else {
                this.finish()
            }
        }

        binding.btnBack.setOnClickListener(){
            this.finish()
        }

    }
}

