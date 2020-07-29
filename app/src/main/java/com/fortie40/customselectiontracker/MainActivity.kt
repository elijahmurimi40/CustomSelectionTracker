package com.fortie40.customselectiontracker

import android.os.Bundle
import android.view.ActionMode
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.fortie40.customselectiontracker.helperclasses.HelperFunctions.showShortSnackBar
import com.fortie40.customselectiontracker.models.ProgrammingLanguage
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: Adapter
    private var actionMode: ActionMode? = null
    private var isInActionMode = false
    private var count: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getNames()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        adapter.onSaveInstanceState(outState)
        outState.putBoolean(IS_IN_ACTION_MODE, isInActionMode)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        if (savedInstanceState.getBoolean(IS_IN_ACTION_MODE)) {
            actionMode = startActionMode(ActionModeCallBack())
            adapter.onRestoreInstanceState(savedInstanceState)

            count = adapter.getSelectedItemCount()
            actionMode!!.title = count.toString()
            actionMode!!.invalidate()
        }
    }

    private val onClick = { data: ProgrammingLanguage ->
        showShortSnackBar(names_item, data.name)
    }

    @Suppress("UNUSED_PARAMETER")
    private fun onLongClick(data: ProgrammingLanguage, position: Int): Boolean {
        if (actionMode == null)
            actionMode = startActionMode(ActionModeCallBack())

        println(position)
        toggleSelection(position)
        return true
    }

    private fun toggleSelection(position: Int) {
        adapter.toggleSelection(position)
        count = adapter.getSelectedItemCount()

        if (count == 0) {
            actionMode?.finish()
        } else {
            actionMode?.title = count.toString()
            actionMode?.invalidate()
        }
    }

    private fun getNames() {
        val names: List<String> = arrayListOf(
            "Fortie40", "Java", "Kotlin", "C++", "PHP", "Javascript", "Objective-C", "Swift",
            "Groovy", "Haskell", "JQuery", "KRYPTON", "LotusScript", "Mortran", "NewLISP", "Orwell",
            "Hopscotch", "JScript", "AngelScript", "Bash", "Clojure", "C", "COBOL", "CSS",
            "Cybil", "Pascal", "Perl", "Smalltalk", "SQL", "Unicon", "Ubercode", "Fortran",
            "Hollywood", "SMALL", "Lisp", "PureScript", "R++", "XQuery", "YAML", "ZOPL"
        )

        val programmingLanguages = arrayListOf<ProgrammingLanguage>()
        for (i in 1..names.size) {
            val name = names[i - 1]
            val initial = name[0].toString()
            val programmingLanguage = ProgrammingLanguage(i, initial, name)
            programmingLanguages.add(programmingLanguage)
        }

        adapter = Adapter(programmingLanguages, onClick, ::onLongClick)
        names_item.adapter = adapter
    }

    inner class ActionModeCallBack : ActionMode.Callback {
        override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
            return when (item?.itemId) {
                R.id.action_delete -> {
                    showShortSnackBar(names_item, getString(R.string.delete_action))
                    true
                }
                else -> false
            }
        }

        override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
            isInActionMode = true
            mode?.menuInflater?.inflate(R.menu.menu_main, menu)
            return true
        }

        override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
            return false
        }

        override fun onDestroyActionMode(mode: ActionMode?) {
            isInActionMode = false
            adapter.clearSelection()
            actionMode = null
        }
    }
}