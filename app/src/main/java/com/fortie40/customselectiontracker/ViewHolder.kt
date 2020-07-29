package com.fortie40.customselectiontracker

import android.graphics.Color
import android.util.TypedValue
import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.fortie40.customselectiontracker.models.ProgrammingLanguage

class ViewHolder(nItemView: View): RecyclerView.ViewHolder(nItemView) {
    private val icon = nItemView.findViewById<TextView>(R.id.icon)
    private val nameCard = nItemView.findViewById<CardView>(R.id.name_card)
    private val name = nItemView.findViewById<TextView>(R.id.name)

    fun bind(
        isSelected: Boolean,
        data: ProgrammingLanguage,
        clickListener: (ProgrammingLanguage) -> Unit,
        longClickListener: (ProgrammingLanguage, Int) -> Boolean
    ) {
        icon.setOnClickListener { longClickListener(data, adapterPosition) }
        nameCard.setOnClickListener { clickListener(data) }
        nameCard.setOnLongClickListener { longClickListener(data, adapterPosition) }

        name.text = data.name

        if (isSelected) {
            bind()
        } else {
            bind(data.initial)
        }
    }

    private fun bind() {
        icon.text = ""
        icon.setBackgroundResource(R.drawable.circle_icon)
        itemView.setBackgroundColor(Color.CYAN)
        nameCard.setCardBackgroundColor(Color.CYAN)
    }

    private fun bind(initial: String) {
        icon.text = initial
        icon.setBackgroundResource(R.drawable.circle)

        val context = icon.context
        val a = TypedValue()
        context.theme.resolveAttribute(android.R.attr.windowBackground, a, true)
        if (a.type >= TypedValue.TYPE_FIRST_COLOR_INT && a.type <= TypedValue.TYPE_LAST_COLOR_INT) {
            val color = a.data
            itemView.setBackgroundColor(color)
            nameCard.setCardBackgroundColor(color)
        } else {
            val d = ContextCompat.getDrawable(context, a.resourceId)
            ViewCompat.setBackground(itemView, d)
            ViewCompat.setBackground(nameCard, d)
        }
    }
}