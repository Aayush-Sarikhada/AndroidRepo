package com.example.android.recyclerViewPractice.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.Spinner
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.android.R
import com.example.android.recyclerViewPractice.models.SpinnerStateDataModel

class SpinnerRVAdapter(
    val context: Context,
    val dataList: List<SpinnerStateDataModel>,
    private val onDeleteSpinnerButtonClicked: (Int) -> Unit,
    private val onItemSelected: (Int, Int) -> Unit
) :
    RecyclerView.Adapter<SpinnerRVAdapter.ViewHolder>() {

    private val spinnerRangeList = (1..100).toList()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val spinner: Spinner = view.findViewById(R.id.spinner)
        val btnDeleteSpinner: ImageButton = view.findViewById(R.id.btn_delete_spinner)

        init {
            btnDeleteSpinner.setOnClickListener {
                onDeleteSpinnerButtonClicked(adapterPosition)
            }

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    dataList[adapterPosition].spinnerValue = spinnerRangeList[position]
                    onItemSelected(
                        dataList[adapterPosition].spinnerValue,
                        adapterPosition
                    )
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.view_spinner_number, parent, false)
        val vh = ViewHolder(view)
        vh.spinner.adapter =
            ArrayAdapter(
                context,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                spinnerRangeList
            )
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            btnDeleteSpinner.isVisible = position != 0
            spinner.isSelected = dataList[position].spinnerValue != 0
            if (spinner.isSelected) {
                spinner.setSelection(dataList[position].spinnerValue)
            } else {
                spinner.setSelection(0)
            }
        }
    }

}