package com.example.recyclerViewPractice.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidpracticeproject.R
import com.example.recyclerViewPractice.models.SpinnerStateDataModel

class SpinnerRVAdapter(val context: Context, private val dataList: List<SpinnerStateDataModel>) :
    RecyclerView.Adapter<SpinnerRVAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val spinner: Spinner = view.findViewById(R.id.spinner)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        Log.d("ARITHMETIC_RV","onCreateViewHolder called FOR SPINNER RV")
        val view = LayoutInflater.from(context).inflate(R.layout.view_spinner_number, parent, false)
        val vh = ViewHolder(view)
        vh.spinner.adapter = ArrayAdapter(context,R.layout.support_simple_spinner_dropdown_item,(1..100).toList())
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        Log.d("ARITHMETIC_RV","COUNT CALLED FOR SPINNER RV")
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d("ARITHMETIC_RV","POS: $position")
        holder.apply {
            spinner.isSelected = dataList[position].spinnerValue != 0
            if(spinner.isSelected) {
                spinner.setSelection(dataList[position].spinnerValue - 1)
            }
        }
    }

}