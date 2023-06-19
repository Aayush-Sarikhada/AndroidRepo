package com.example.recyclerViewPractice.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import com.example.androidpracticeproject.R
import com.example.androidpracticeproject.databinding.ViewArithmeticCardBinding
import com.example.recyclerViewPractice.models.ArithmeticCardStateDataModel
import com.example.recyclerViewPractice.models.SpinnerStateDataModel
import com.example.recyclerViewPractice.utils.Utils

class ArithmeticCardRVAdapter(
    private val context: Context,
    private val dataList: List<ArithmeticCardStateDataModel>,
    private val onDeleteBtnClickListener: (Int) -> Unit
) : RecyclerView.Adapter<ArithmeticCardRVAdapter.ViewHolder>() {

    private enum class PayloadTypes {
        STR_TOTAL, RV_SPINNER
    }

    inner class ViewHolder(val binding: ViewArithmeticCardBinding) : RecyclerView.ViewHolder(binding.root) {
        val btnDelete: ImageButton = binding.imgDeleteCard
        val etFirstNumber: EditText = binding.etFirstNumber
        val etSecondNumber: EditText = binding.etSecondNumber
        val rvForSpinner: RecyclerView = binding.rvSpinner
        val rvForImages: RecyclerView = binding.rvImages
        val btnAddMoreSpinners: Button = binding.btnAddMoreSpinner
        val btnAddMoreImage: Button = binding.btnAddMoreImages
        val tvTotal: TextView = binding.tvTotalLabel
        init {
            btnDelete.setOnClickListener {
                onDeleteBtnClickListener(adapterPosition)
            }

//            etFirstNumber.setOnKeyListener { _, keyCode, event ->
//                if ((event.action == KeyEvent.ACTION_DOWN) &&
//                    (keyCode == KeyEvent.KEYCODE_ENTER)) {
//                    Log.d("MAIN","H1")
//                    dataList[adapterPosition].strFirstEditTextValue =
//                        if (etFirstNumber.text.isEmpty()) "0" else etFirstNumber.text.toString()
//                    notifyItemChanged(adapterPosition, PayloadTypes.STR_TOTAL)
//                    return@setOnKeyListener true
//                }
//                false
//            }
//
//            etSecondNumber.setOnKeyListener { _, keyCode, event ->
//                Log.d("MAIN","J1")
//                if ((event.action == KeyEvent.ACTION_DOWN) &&
//                    (keyCode == KeyEvent.KEYCODE_ENTER)) {
//                    dataList[adapterPosition].strSecondEditTextValue =
//                        if (etSecondNumber.text.isEmpty()) "0" else etSecondNumber.text.toString()
//                    notifyItemChanged(adapterPosition, PayloadTypes.STR_TOTAL)
//                    return@setOnKeyListener true
//                }
//                false
//            }

            etFirstNumber.addTextChangedListener {
                it?.let {
                    dataList[adapterPosition].strFirstEditTextValue =
                        if (it.isEmpty()) "0" else it.toString()
                }
                notifyItemChanged(adapterPosition, PayloadTypes.STR_TOTAL)
            }

            etSecondNumber.addTextChangedListener {
                it?.let {
                    dataList[adapterPosition].strSecondEditTextValue =
                        if (it.isEmpty()) "0" else it.toString()
                }
                notifyItemChanged(adapterPosition, PayloadTypes.STR_TOTAL)
            }

            btnAddMoreImage.setOnClickListener {
                dataList[adapterPosition].imagesIDsArray.add(Utils.getAvatarImgId())
                rvForImages.adapter?.notifyItemInserted(dataList[adapterPosition].imagesIDsArray.size - 1)
            }

            btnAddMoreSpinners.setOnClickListener {
                dataList[adapterPosition].spinnerStateList.add(SpinnerStateDataModel())
                rvForSpinner.adapter?.notifyItemInserted(dataList[adapterPosition].spinnerStateList.size - 1)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_arithmetic_card, parent, false)
        val binding = ViewArithmeticCardBinding.bind(view)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            btnDelete.isVisible = position != 0
            tvTotal.text = "Total: ${dataList[position].total}"
            binding.arithmeticCard = dataList[adapterPosition]
            rvForSpinner.adapter = SpinnerRVAdapter(
                context,
                dataList[position].spinnerStateList,
                onDeleteSpinnerButtonClicked =  { spinnerPosition ->
                    dataList[position].spinnerStateList.removeAt(spinnerPosition)
                    rvForSpinner.adapter?.notifyItemRemoved(spinnerPosition)
                }
            ) { selectedSpinnerValue, selectedSpinnerPos ->
                dataList[position].spinnerStateList[selectedSpinnerPos].spinnerValue = selectedSpinnerValue
                notifyItemChanged(selectedSpinnerPos,PayloadTypes.RV_SPINNER)
            }

            rvForImages.adapter = ImagesRVAdapter(
                context,
                dataList[position].imagesIDsArray
            ) { imgPosition ->
                dataList[position].imagesIDsArray.removeAt(imgPosition)
                rvForImages.adapter?.notifyItemRemoved(imgPosition)
            }


        }
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int, payloads: MutableList<Any>) {
        if (payloads.isNotEmpty()) {
            when (payloads[0]) {
                PayloadTypes.STR_TOTAL -> {
                    dataList[position].total =
                        dataList[position].strFirstEditTextValue.toBigInteger() * dataList[position].strSecondEditTextValue.toBigInteger()
                    Log.d("ARITH_RV", "first: ${dataList[position].strFirstEditTextValue.toBigInteger()}")
                    Log.d("ARITH_RV", "second: ${dataList[position].strSecondEditTextValue.toBigInteger()}")
                    holder.tvTotal.text = "Total: ${dataList[position].total}"
                }

                PayloadTypes.RV_SPINNER -> {
                    dataList[position]
                }
            }
            return
        }
        super.onBindViewHolder(holder, position, payloads)
    }

    private fun onReturnKeyPressed(et: EditText) {

    }

}