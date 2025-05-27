package com.idz.colman24class2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.Toolbar


class MainActivity : AppCompatActivity() {

    private var fragmentOne: StudentsListFragment? = null
    private var buttonFour: Button? = null
    private var inDisplayFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        fragmentOne = StudentsListFragment()
        display(fragmentOne)

        buttonFour = findViewById(R.id.main_activity_button_four)
        buttonFour?.setOnClickListener {
            val intent = Intent(this, AddStudentActivity::class.java)
            startActivity(intent)
        }
        val toolbar: Toolbar = findViewById(R.id.toolbar2)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Students List"
    }

    private fun display(fragment: Fragment?) {
        fragment?.let {
            supportFragmentManager.beginTransaction().apply {
                add(R.id.main_activity_frame_layout, it)

                inDisplayFragment?.let {
                    remove(it)
                }

                addToBackStack("TAG")
                commit()
            }

            inDisplayFragment = fragment
        }
    }
}
