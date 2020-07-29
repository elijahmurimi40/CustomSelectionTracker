package com.fortie40.customselectiontracker

import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView

abstract class SelectableAdapter<VH: RecyclerView.ViewHolder>: RecyclerView.Adapter<VH>() {
    private var selectedItems = ArrayList<Int>()

    fun getSelectedItemCount(): Int {
        return selectedItems.size
    }

    private fun getSelectedItems(): ArrayList<Int> {
        val items = ArrayList<Int>(selectedItems.size)
        for (i in 0 until selectedItems.size) {
            items.add(selectedItems[i])
        }
        return items
    }

    fun isSelected(position: Int): Boolean {
        return selectedItems.contains(position)
    }

    open fun clearSelection() {
        val selection = getSelectedItems()
        selectedItems.clear()
        for (i in selection) {
            notifyItemChanged(i)
        }
    }

    open fun toggleSelection(position: Int) {
        if (selectedItems.contains(position)) {
            selectedItems.remove(position)
        } else {
            selectedItems.add(position)
        }
        notifyItemChanged(position)
    }

    fun onSaveInstanceState(outState: Bundle) {
        outState.putIntegerArrayList(SELECTED_ITEMS, getSelectedItems())
    }

    fun onRestoreInstanceState(savedInstanceState: Bundle) {
        selectedItems = savedInstanceState.getIntegerArrayList(SELECTED_ITEMS)!!
    }
}