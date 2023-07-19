package com.example.android.recyclerViewPractice.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import com.example.android.R
import com.example.android.databinding.ViewArithmeticCardBinding
import com.example.android.recyclerViewPractice.PayloadTypes
import com.example.android.recyclerViewPractice.models.ArithmeticCardStateDataModel
import com.example.android.recyclerViewPractice.models.SpinnerStateDataModel
import com.example.android.recyclerViewPractice.utils.Utils
import com.google.android.material.divider.MaterialDivider
import com.google.android.material.textfield.TextInputEditText

class ArithmeticCardRVAdapter(
    private val context: Context,
    private val dataList: List<ArithmeticCardStateDataModel>,
    private val onDeleteBtnClickListener: (Int) -> Unit,
    private val onAddCardListener: () -> Unit
) : RecyclerView.Adapter<ArithmeticCardRVAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ViewArithmeticCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val btnAddMoreSpinners: Button = binding.btnAddMoreSpinner
        private val btnAddMoreImage: Button = binding.btnAddMoreImages
        val etFirstNumber: TextInputEditText = binding.etFirstNumber
        val etSecondNumber: TextInputEditText = binding.etSecondNumber
        val btnDelete: ImageButton = binding.imgDeleteCard
        val rvForSpinner: RecyclerView = binding.rvSpinner
        val rvForImages: RecyclerView = binding.rvImages
        val btnAddMoreCards: Button = binding.btnAddMoreCards
        val dividerBottom: MaterialDivider = binding.dividerBottom
        val tvTotal: TextView = binding.tvTotalLabel

        init {

            tvTotal.isFocusable = true

            btnDelete.setOnClickListener {
                onDeleteBtnClickListener(adapterPosition)
            }

            etFirstNumber.addTextChangedListener {
                if (etFirstNumber.hasFocus()) {
                    it?.let { text ->
                        dataList[adapterPosition].strFirstEditTextValue =
                            if (text.isEmpty()) "0" else text.toString()
                    }
                    notifyItemChanged(adapterPosition, PayloadTypes.STR_TOTAL)
                }
            }

            etSecondNumber.addTextChangedListener {
                if (etSecondNumber.hasFocus()) {
                    it?.let {
                        dataList[adapterPosition].strSecondEditTextValue =
                            if (it.isEmpty()) "0" else it.toString()
                    }
                    notifyItemChanged(adapterPosition, PayloadTypes.STR_TOTAL)
                }
            }

            btnAddMoreImage.setOnClickListener {
                dataList[adapterPosition].imagesIDsArray.add(Utils.getAvatarImgId())
                rvForImages.adapter?.notifyItemInserted(dataList[adapterPosition].imagesIDsArray.size - 1)
                rvForImages.scrollToPosition(dataList[adapterPosition].imagesIDsArray.size - 1)
            }

            btnAddMoreSpinners.setOnClickListener {
                dataList[adapterPosition].spinnerStateList.add(SpinnerStateDataModel())
                rvForSpinner.adapter?.notifyItemInserted(dataList[adapterPosition].spinnerStateList.size - 1)
            }

            btnAddMoreCards.setOnClickListener {
                dividerBottom.visibility = MaterialDivider.GONE
                btnAddMoreCards.visibility = Button.GONE
                onAddCardListener()
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
            dividerBottom.isVisible = position == dataList.size - 1
            btnAddMoreCards.isVisible = position == dataList.size - 1
            val currentCard = dataList[position]
            etFirstNumber.setText(if (currentCard.strFirstEditTextValue == "0") "" else currentCard.strFirstEditTextValue)
            etSecondNumber.setText(if (currentCard.strSecondEditTextValue == "0") "" else currentCard.strSecondEditTextValue)
            tvTotal.text =
                "Total: ${if (currentCard.total.toString() == "0") "" else currentCard.total}"
            binding.arithmeticCard = dataList[adapterPosition]

            rvForSpinner.adapter = SpinnerRVAdapter(
                context,
                dataList[adapterPosition].spinnerStateList,
                onDeleteSpinnerButtonClicked = { spinnerPosition ->
                    dataList[adapterPosition].spinnerStateList.removeAt(spinnerPosition)
                    rvForSpinner.adapter?.notifyItemRemoved(spinnerPosition)
                    dataList[adapterPosition].updateTotal()
                    holder.tvTotal.text =
                        "Total: ${if (currentCard.total.toString() == "0") "" else currentCard.total}"
                }
            ) { selectedSpinnerValue, selectedSpinnerPos ->
                dataList[adapterPosition].spinnerStateList[selectedSpinnerPos].spinnerValue =
                    selectedSpinnerValue
                dataList[adapterPosition].updateTotal()
                holder.tvTotal.text =
                    "Total: ${if (currentCard.total.toString() == "0") "" else currentCard.total}"
            }

            rvForImages.adapter = ImagesRVAdapter(
                context,
                dataList[adapterPosition].imagesIDsArray
            ) { imgPosition ->
                Log.d("RV", "HEllo: $position")
                dataList[adapterPosition].imagesIDsArray.removeAt(imgPosition)
                rvForImages.adapter?.notifyItemRemoved(imgPosition)
            }

        }
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int, payloads: MutableList<Any>) {
        if (payloads.isNotEmpty()) {
            when (payloads[0]) {
                PayloadTypes.STR_TOTAL -> {
                    dataList[position].updateTotal()
                    val currentCard = dataList[position]
                    holder.tvTotal.text =
                        "Total: ${if (currentCard.total.toString() == "0") "" else currentCard.total}"
                }

                PayloadTypes.BTN_ADD_MORE_CARDS -> {
                    holder.dividerBottom.visibility = Button.VISIBLE
                    holder.btnAddMoreCards.visibility = Button.VISIBLE
                }
            }
            return
        }
        super.onBindViewHolder(holder, position, payloads)
    }

}