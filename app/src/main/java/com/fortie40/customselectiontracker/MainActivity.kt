package com.fortie40.customselectiontracker

import android.os.Bundle
import android.view.ActionMode
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.fortie40.customselectiontracker.helperclasses.HelperFunctions.showShortSnackBar
import com.fortie40.customselectiontracker.models.ProgrammingLanguage
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.name_layout.*

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: Adapter
    private var actionMode: ActionMode? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getNames()
    }

    private val onClick = { data: ProgrammingLanguage ->
        showShortSnackBar(name_card, data.name)
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
        val count = adapter.getSelectedItemCount()

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
                    showShortSnackBar(name_card, getString(R.string.delete_action))
                    true
                }
                else -> false
            }
        }

        override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
            mode?.menuInflater?.inflate(R.menu.menu_main, menu)
            return true
        }

        override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
            return false
        }

        override fun onDestroyActionMode(mode: ActionMode?) {
            adapter.clearSelection()
            actionMode = null
        }
    }
}