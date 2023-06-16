package com.example.recyclerViewPractice.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.androidpracticeproject.R
import com.example.recyclerViewPractice.models.ArithmeticCardStateDataModel

class ArithmeticCardRVAdapter(
    private val context: Context,
    private val dataList: List<ArithmeticCardStateDataModel>
) : RecyclerView.Adapter<ArithmeticCardRVAdapter.ViewHolder>() {

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val btnDelete: ImageButton = view.findViewById(R.id.img_delete_card)
        val etFirstNumber: EditText = view.findViewById(R.id.et_first_number)
        val etSecondNumber: EditText = view.findViewById(R.id.et_second_number)
        val rvForSpinner: RecyclerView = view.findViewById(R.id.rv_spinner)
        val rvForImages: RecyclerView = view.findViewById(R.id.rv_images)
        val btnAddMoreSpinners: Button = view.findViewById(R.id.btn_add_more_spinner)
        val btnAddMoreImage: Button = view.findViewById(R.id.btn_add_more_images)
        val tvTotal: TextView = view.findViewById(R.id.tv_total_label)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.d("ARITHMETIC_RV", "onCreateViewHolder for arithmetic rv")
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_arithmetic_card, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        Log.d("ARITHMETIC_RV", "get item count for arithmetic rv ${dataList.size}")
        return dataList.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d("ARITHMETIC_RV","onbindViewHolder called for arithmetic rv, pos: $position")
        holder.apply {
            btnDelete.isVisible = position != 0
            etFirstNumber.setText(dataList[0].strFirstEditTextValue)
            etSecondNumber.setText(dataList[0].strSecondEditTextValue)
            rvForSpinner.adapter = SpinnerRVAdapter(context, dataList[position].spinnerStateList)
            btnAddMoreSpinners.setOnClickListener {
                Toast.makeText(context, "ADD MORE SPINNER", Toast.LENGTH_SHORT).show()
            }
            btnAddMoreImage.setOnClickListener {
                Toast.makeText(context, "ADD MORE IMAGES", Toast.LENGTH_SHORT).show()
            }
            tvTotal.text = "Total: ${dataList[position].total}"
        }
    }

}