package com.example.recyclerViewPractice.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.Spinner
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.androidpracticeproject.R
import com.example.recyclerViewPractice.models.SpinnerStateDataModel

class SpinnerRVAdapter(
    val context: Context,
    private val dataList: List<SpinnerStateDataModel>,
    private val onDeleteSpinnerButtonClicked: (Int) -> Unit,
    private val onItemSelected: (Int, Int) -> Unit
) :
    RecyclerView.Adapter<SpinnerRVAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val spinner: Spinner = view.findViewById(R.id.spinner)
        val btnDeleteSpinner: ImageButton = view.findViewById(R.id.btn_delete_spinner)

        init {
            btnDeleteSpinner.setOnClickListener {
                onDeleteSpinnerButtonClicked(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.view_spinner_number, parent, false)
        val vh = ViewHolder(view)
        vh.spinner.adapter =
            ArrayAdapter(context, R.layout.support_simple_spinner_dropdown_item, (0..100).toList())
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            btnDeleteSpinner.isVisible = position != 0
            spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    dataList[adapterPosition].spinnerValue = position
                    onItemSelected(dataList[adapterPosition].spinnerValue, adapterPosition)
                    Log.d("SPINNER_RV", "SELECTED: ${dataList[adapterPosition].spinnerValue}")
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}

            }
            spinner.isSelected = dataList[position].spinnerValue != 0
            if (spinner.isSelected) {
                spinner.setSelection(dataList[position].spinnerValue - 1)
            }
        }
    }

}