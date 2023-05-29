package com.labactivity.techcookreservation

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.labactivity.techcookreservation.databinding.ActivityMakeReservationBinding


class MakeReservation : AppCompatActivity() {
    private lateinit var binding: ActivityMakeReservationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMakeReservationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var hr:ArrayList<String> = ArrayList<String>()
        var min:ArrayList<String> = ArrayList<String>()
        var dy:ArrayList<String> = ArrayList<String>()

        //USER RESERVATION DATA MEMBERS

        //name
        var name:String = ""
        //date
        var date:String = ""
        var month:String = ""
        var day:String = ""
        var year:String = ""
        //time
        var time:String = ""
        var hours:String = ""
        var minutes:String = ""
        var midday:String = ""
        //customers
        var noOfChildren:Int = 0
        var noOfAdults:Int = 0
        //subtotal
        var subTotal:Int = 0


        //Submit Button
        binding.btnSubmit.setOnClickListener(){

            name = binding.edtName.text.toString()

            //initializing time variable
            if(hours.isNotBlank() && minutes.isNotBlank()){
                time = "$hours:$minutes $midday"
            }
            //initializing date variable
            if(month.isNotBlank() && day.isNotBlank() && year.isNotBlank()){
                date = "$month $day, $year"
            }

            //Preparing data members to pass to Preview
            if(name.isNotBlank() && date.isNotBlank() && time.isNotBlank()){

                if(noOfChildren == 0 && noOfAdults == 0){
                    Toast.makeText(applicationContext, "Please add at least one customer", Toast.LENGTH_LONG).show()
                    return@setOnClickListener
                }

                val intent = Intent(this, PreviewReservation::class.java)
                intent.putExtra("name" , name)
                intent.putExtra("date" , date)
                intent.putExtra("time" , time)
                intent.putExtra("children" , noOfChildren)
                intent.putExtra("adults" , noOfAdults)
                intent.putExtra("subtotal" , subTotal)
                intent.putExtra("toStore" , true)
                startActivity(intent)

                name = ""
                date = ""
                time = ""

            } else {
                Toast.makeText(applicationContext, "Please enter all the required fields", Toast.LENGTH_LONG).show()
            }
        }

        //Back Button
        binding.btnBack.setOnClickListener(){
            //ends current activity and goes back to menu
            this.finish()
        }



        //INITIALIZING DATA MEMBERS
        //Hours
        hr.add("Hours:")
        for( i in 1..12){
            if (i >= 10){
                hr.add("${i}")
            }else{
                hr.add("0${i}")
            }

        }
        val hoursAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, hr)
        hoursAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spnHours.adapter = hoursAdapter

        binding.spnHours.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if(hr[position].contains("Hours:")){
                    hours = ""
                    return
                }
                hours = hr[position]
                // Handle the selected item
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Handle the case where no item is selected
            }
        }

        //Minutes
        min.add("Minutes:")
        for( i in 0..59){
            if (i >= 10){
                min.add("${i}")
            }else{
                min.add("0${i}")
            }
        }
        val minutesAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, min)
        hoursAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spnMinutes.adapter = minutesAdapter

        binding.spnMinutes.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if(min[position].contains("Minutes:")){
                    minutes = ""
                    return
                }
                minutes = min[position]
                // Handle the selected item
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Handle the case where no item is selected
            }
        }

        //Midday
        val middays = listOf("PM","AM")
        val middayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, middays)
        hoursAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spnMidday.adapter = middayAdapter

        binding.spnMidday.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                midday = middays[position]
                // Handle the selected item
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Handle the case where no item is selected
            }
        }

        //Month
        val months = resources.getStringArray(R.array.month_options)
        val monthAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, months)
        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spnMonths.adapter = monthAdapter

        binding.spnMonths.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if(months[position].contains("Month:")){
                    month = ""
                    return
                }
                month = months[position]
                // Handle the selected item
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Handle the case where no item is selected
            }
        }

        //Day
        dy.add("Day:")
        for( i in 1..31){
            if (i >= 10){
                dy.add("${i}")
            }else{
                dy.add("0${i}")
            }
        }
        val daysAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, dy)
        daysAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spnDays.adapter = daysAdapter

        binding.spnDays.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if(dy[position].contains("Day:")){
                    day = ""
                    return
                }
                day = dy[position]
                // Handle the selected item
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Handle the case where no item is selected
            }
        }

        //Year
        val years = resources.getStringArray(R.array.year_options)
        val yearsAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, years)
        yearsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spnYears.adapter = yearsAdapter

        binding.spnYears.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if(years[position].contains("Year:")){
                    year = ""
                    return
                }
                year = years[position]
                // Handle the selected item
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Handle the case where no item is selected
            }
        }



        //Adults, Children, Subtotal

        //Adults
        val twAdult = object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                // Not used in this program
            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                if(charSequence.isEmpty()){
                    noOfAdults = 0
                    subTotal = updateSubtotal(noOfAdults, noOfChildren)
                    return
                } else {
                    noOfAdults = charSequence.toString().toInt()
                    subTotal = updateSubtotal(noOfAdults, noOfChildren)
                }

            }

            override fun afterTextChanged(editable: Editable) {
                // Not used in this example
            }
        }
        binding.edtTxtAdult.addTextChangedListener(twAdult)

        //Children
        val twChild = object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                // Not used in this program
            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                if(charSequence.isEmpty()){
                    noOfChildren = 0
                    subTotal = updateSubtotal(noOfAdults, noOfChildren)
                    return
                } else {
                    noOfChildren = charSequence.toString().toInt()
                    subTotal = updateSubtotal(noOfAdults, noOfChildren)
                }
            }

            override fun afterTextChanged(editable: Editable) {
                // Not used in this program
            }
        }
        binding.edtTxtChild.addTextChangedListener(twChild)
    }
    fun updateSubtotal(adults:Int, children:Int) :Int{
        var adultTotal  = 500 * adults
        var childTotal  = 300 * children
        var reserve:Int


        binding.txtChildrenTotal.setText("Children Total:    PHP ${childTotal}")
        binding.txtAdultsTotal.setText("Adult Total      :    PHP ${adultTotal}")

        if(childTotal == 0 && adultTotal == 0){
            reserve = 0
            binding.txtResTotal.setText("Reservation    :    PHP 0")
        }else{
            reserve = 230
            binding.txtResTotal.setText("Reservation    :    PHP 230")
        }



        var subTotal = childTotal + adultTotal + reserve
        binding.txtTotal.setText("Overall Total   :    PHP ${subTotal}")

        return subTotal
    }
}