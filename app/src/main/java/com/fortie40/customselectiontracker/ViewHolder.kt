package com.fortie40.customselectiontracker

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fortie40.customselectiontracker.models.ProgrammingLanguage

class ViewHolder(nItemView: View): RecyclerView.ViewHolder(nItemView) {
    private val icon = nItemView.findViewById<TextView>(R.id.icon)
    private val name = nItemView.findViewById<TextView>(R.id.name)

    fun bind(
        data: ProgrammingLanguage,
        clickListener: (ProgrammingLanguage) -> Unit,
        longClickListener: (ProgrammingLanguage) -> Boolean
    ) {
        icon.text = data.initial
        name.text = data.name

        name.setOnClickListener { clickListener(data) }
        name.setOnLongClickListener { longClickListener(data) }
    }
}