package com.idz.colman24class2

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.idz.colman24class2.model.Model
import com.idz.colman24class2.model.Student
import androidx.appcompat.widget.Toolbar

class AddStudentActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_student)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val saveButton: Button = findViewById(R.id.add_student_activity_save_button)
        val cancelButton: Button = findViewById(R.id.add_student_activity_cancel_button)

        val nameEditText: EditText = findViewById(R.id.add_student_activity_name_edit_text)
        val idEditText: EditText = findViewById(R.id.add_student_activity_id_edit_text)

        val savedMessageTextView: TextView = findViewById(R.id.add_student_activity_save_message_text_view)

        cancelButton.setOnClickListener {
            finish()
        }

        val phoneEditText: EditText = findViewById(R.id.editTextPhone)
        val addressEditText: EditText = findViewById(R.id.editTextTextPostalAddress)

        saveButton.setOnClickListener {
            val name = nameEditText.text.toString().trim()
            val id = idEditText.text.toString().trim()
            val phone = phoneEditText.text.toString().trim()
            val address = addressEditText.text.toString().trim()

            if (name.isNotEmpty() && id.isNotEmpty() && phone.isNotEmpty() && address.isNotEmpty()) {
                val newStudent = Student(
                    name = name,
                    id = id,
                    isChecked = false,
                    phone = phone,
                    address = address
                )

                Model.shared.students.add(newStudent)
                finish()
            } else {
                savedMessageTextView.text = "Please fill out all fields."
            }
        }

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Add Student"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}








