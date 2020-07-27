package com.fortie40.customselectiontracker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fortie40.customselectiontracker.models.ProgrammingLanguage

class Adapter(names: List<ProgrammingLanguage>): RecyclerView.Adapter<ViewHolder>(){
    private var originalList = names

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.name_layout, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return originalList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val programmingLanguage = originalList[position]
        holder.bind(programmingLanguage.initial, programmingLanguage.name)
    }
}