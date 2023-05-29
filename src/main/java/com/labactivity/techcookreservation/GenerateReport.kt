package com.labactivity.techcookreservation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.labactivity.techcookreservation.databinding.ActivityGenerateReportBinding
import kotlinx.coroutines.launch

class GenerateReport : AppCompatActivity() {
    private lateinit var binding:ActivityGenerateReportBinding
    private lateinit var reservationDao: ReservationDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGenerateReportBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var totalRes = 0
        var totalChild = 0
        var totalAdult = 0
        var totalCustomer = 0
        var grandTotal = 0


        lifecycleScope.launch{
            val database = DatabaseProvider.getDatabase(this@GenerateReport)

            reservationDao = database.reservationDao()

            val reservations = reservationDao.getAllReservation()


            //Creating report
            for(res in reservations){
                totalChild += res.children
                totalAdult += res.adults
                grandTotal += res.subtotal

            }
            totalRes += reservations.size
            totalCustomer += totalChild + totalAdult

            binding.txtGrandTotal.text = "PHP $grandTotal"
            binding.txtTotalAdults.text = "$totalAdult"
            binding.txtTotalChildren.text = "$totalChild"
            binding.txtTotalCust.text = "$totalCustomer"
            binding.txtTotalRes.text = "$totalRes"
        }

        binding.btnBack.setOnClickListener(){
            this.finish()
        }


    }
}