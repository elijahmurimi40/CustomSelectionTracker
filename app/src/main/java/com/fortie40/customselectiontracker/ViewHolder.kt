package com.fortie40.customselectiontracker

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ViewHolder(nItemView: View): RecyclerView.ViewHolder(nItemView) {
    private val name = nItemView.findViewById<TextView>(R.id.name)

    fun bind(nameA: String) {
        name.text = nameA
    }
}