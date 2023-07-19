package com.example.android.stockexchangeui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.android.R
import com.example.android.stockexchangeui.data.StockData

class StockSpinnerAdapter(private val context: Context, dataList: List<StockData>) :
    ArrayAdapter<StockData>(context, 0, dataList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return initView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return initView(position, convertView, parent)
    }

    private fun initView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.row_spinner_item, parent, false)
        }
        val tvStockName = view?.findViewById<TextView>(R.id.stock_name)
        val stockDataItem = getItem(position)
        tvStockName?.text = stockDataItem?.stockName
        return view!!
    }

}