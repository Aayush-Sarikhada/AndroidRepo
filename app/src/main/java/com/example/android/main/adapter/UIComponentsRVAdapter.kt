package com.example.android.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.android.R
import com.example.android.databinding.RowStartScreenBinding
import com.example.android.main.model.ScreenType

class UIComponentsRVAdapter(private val dataList: List<ScreenType>) :
    RecyclerView.Adapter<UIComponentsRVAdapter.ViewHolder>() {

    inner class ViewHolder(view: RowStartScreenBinding) : RecyclerView.ViewHolder(view.root) {
        val btnStartActivity = view.btnStartActivity

        init {
            btnStartActivity.setOnClickListener {
                TODO("Add moving activity code")
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: RowStartScreenBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.row_start_screen,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.btnStartActivity.tag = dataList[position].id
        holder.btnStartActivity.text = dataList[position].name
    }

}