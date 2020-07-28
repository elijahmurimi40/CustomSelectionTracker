package com.fortie40.customselectiontracker

import android.view.LayoutInflater
import android.view.ViewGroup
import com.fortie40.customselectiontracker.models.ProgrammingLanguage

class Adapter(
    private val names: List<ProgrammingLanguage>,
    private val clickListener: (ProgrammingLanguage) -> Unit,
    private val longClickListener: (ProgrammingLanguage, Int) -> Boolean
): SelectableAdapter<ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.name_layout, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return names.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val programmingLanguage = names[position]
        val isSelected = isSelected(position)
        holder.bind(isSelected, programmingLanguage, clickListener, longClickListener)
    }
}