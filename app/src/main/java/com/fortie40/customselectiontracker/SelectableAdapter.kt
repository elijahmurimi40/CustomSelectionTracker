package com.fortie40.customselectiontracker

import android.util.SparseBooleanArray
import androidx.recyclerview.widget.RecyclerView

abstract class SelectableAdapter<VH: RecyclerView.ViewHolder>: RecyclerView.Adapter<VH>() {
    private val selectedItems = SparseBooleanArray()

    fun getSelectedItemCount(): Int {
        return selectedItems.size()
    }

    private fun getSelectedItems(): List<Int> {
        val items = ArrayList<Int>(selectedItems.size())
        for (i in 0 until selectedItems.size()) {
            items.add(selectedItems.keyAt(i))
        }
        return items
    }

    fun isSelected(position: Int): Boolean {
        return getSelectedItems().contains(position)
    }

    open fun clearSelection() {
        val selection = getSelectedItems()
        selectedItems.clear()
        for (i in selection) {
            notifyItemChanged(i)
        }
    }

    open fun toggleSelection(position: Int) {
        if (selectedItems[position, false]) {
            selectedItems.delete(position)
        } else {
            selectedItems.put(position, true)
        }
        notifyItemChanged(position)
    }
}