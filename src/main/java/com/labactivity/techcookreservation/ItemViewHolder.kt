package com.labactivity.techcookreservation

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.labactivity.techcookreservation.databinding.ItemLayoutBinding

class ItemViewHolder(
    private val context: Context,
    private val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root)
, View.OnClickListener{

    private lateinit var name:String
    private lateinit var date:String
    private lateinit var time:String
    private var children:Int = 0
    private var adults:Int = 0
    private var subtotal:Int = 0


    init{
        binding.root.setOnClickListener(this)
    }
    fun bind(item:Item){
        this.name = item.name
        this.date = item.date
        this.time = item.time
        this.children = item.children
        this.adults = item.adults
        this.subtotal = item.subtotal


        binding.txtNum.text = "#${item.assign}"
        binding.txtName.text = name
        binding.txtDetails.text = "$date  |  $time"

    }

    override fun onClick(v: View?) {
        val intent = Intent(context, PreviewReservation::class.java)

        intent.putExtra("name", name)
        intent.putExtra("date", date)
        intent.putExtra("time", time)
        intent.putExtra("children", children)
        intent.putExtra("adults", adults)
        intent.putExtra("subtotal", subtotal)
        intent.putExtra("toStore", false)
        context.startActivity(intent)

    }
}