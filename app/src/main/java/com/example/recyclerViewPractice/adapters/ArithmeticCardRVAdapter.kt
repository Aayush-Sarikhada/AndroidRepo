package com.example.recyclerViewPractice.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
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
import com.example.recyclerViewPractice.Constants
import com.example.recyclerViewPractice.PayloadTypes
import com.example.recyclerViewPractice.models.ArithmeticCardStateDataModel
import com.example.recyclerViewPractice.models.SpinnerStateDataModel
import com.example.recyclerViewPractice.utils.Utils
import com.google.android.material.divider.MaterialDivider
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

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
        private val etFirstNumber: TextInputEditText = binding.etFirstNumber
        private val etSecondNumber: TextInputEditText = binding.etSecondNumber
        val btnDelete: ImageButton = binding.imgDeleteCard
        val rvForSpinner: RecyclerView = binding.rvSpinner
        val rvForImages: RecyclerView = binding.rvImages
        val btnAddMoreCards: Button = binding.btnAddMoreCards
        val dividerBottom: MaterialDivider = binding.dividerBottom
        val tvTotal: TextView = binding.tvTotalLabel

        init {

            btnDelete.setOnClickListener {
                onDeleteBtnClickListener(adapterPosition)
            }

            etFirstNumber.addTextChangedListener {
                it?.let {text ->
                    dataList[adapterPosition].strFirstEditTextValue =
                        if (text.isEmpty()) "0" else text.toString()
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
            holder.tvTotal.text = "Total: ${if(currentCard.total.toString() == "0") "" else currentCard.total }"
            binding.arithmeticCard = dataList[adapterPosition]

            rvForSpinner.adapter = SpinnerRVAdapter(
                context,
                dataList[position].spinnerStateList,
                onDeleteSpinnerButtonClicked = { spinnerPosition ->
                    dataList[position].spinnerStateList.removeAt(spinnerPosition)
                    rvForSpinner.adapter?.notifyItemRemoved(spinnerPosition)
                    dataList[position].updateTotal()
                    holder.tvTotal.text = "Total: ${if(currentCard.total.toString() == "0") "" else currentCard.total }"
                }
            ) { selectedSpinnerValue, selectedSpinnerPos ->
                dataList[position].spinnerStateList[selectedSpinnerPos].spinnerValue =
                    selectedSpinnerValue
                dataList[position].updateTotal()
                holder.tvTotal.text = "Total: ${if(currentCard.total.toString() == "0") "" else currentCard.total }"
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
                    dataList[position].updateTotal()
                    val currentCard = dataList[position]
                    val total = currentCard.total
                    val spinTotal = currentCard.totalSpinnerRV
                    holder.tvTotal.text = "Total: ${if(currentCard.total.toString() == "0") "" else currentCard.total }"
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