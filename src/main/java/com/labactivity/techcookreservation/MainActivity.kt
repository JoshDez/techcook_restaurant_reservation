package com.labactivity.techcookreservation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.labactivity.techcookreservation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnViewRes.setOnClickListener(){
            val intent = Intent(this, ViewReservations::class.java)
            startActivity(intent)
        }

        binding.btnMakeRes.setOnClickListener(){
            val intent = Intent(this, MakeReservation::class.java)
            startActivity(intent)
        }

        binding.btnDelRes.setOnClickListener(){
            val intent = Intent(this, DeleteReservation::class.java)
            startActivity(intent)
        }

        binding.btnGenRep.setOnClickListener(){
            val intent = Intent(this, GenerateReport::class.java)
            startActivity(intent)
        }

    }
}