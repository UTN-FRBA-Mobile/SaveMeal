package com.example.savemeal.active_deliveries

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.savemeal.R

class ActiveDeliveriesAdapter(dataSet: ArrayList<String>) : RecyclerView.Adapter<ActiveDeliveriesAdapter.ViewHolder>(){

    var dataSet:ArrayList<String>? = null

    init{
        this.dataSet = dataSet
    }


    private lateinit var mListener: onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position:Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener){
        mListener=listener
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var vista = view
        var textView: TextView
        init {
            textView = vista.findViewById(R.id.delivery_title)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.layout_active_deliveries, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item = dataSet?.get(position)
        viewHolder.textView?.text = item
    }

    override fun getItemCount(): Int {
        return this.dataSet?.count()!!
    }

}