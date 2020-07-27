package com.fortie40.customselectiontracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fortie40.customselectiontracker.helperclasses.HelperFunctions.showShortSnackBar
import com.fortie40.customselectiontracker.models.ProgrammingLanguage
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.name_layout.*

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getNames()
    }

    private val onClick = { data: ProgrammingLanguage ->
        showShortSnackBar(name_card, data.name)
    }

    private fun onLongClick(data: ProgrammingLanguage): Boolean {
        println(data.id)
        return true
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
}