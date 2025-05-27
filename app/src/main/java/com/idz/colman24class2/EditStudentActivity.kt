package com.idz.colman24class2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.idz.colman24class2.model.Model
import com.idz.colman24class2.model.Student

class EditStudentActivity : AppCompatActivity() {

    private lateinit var student: Student
    private var studentIndex: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_student)

        studentIndex = intent.getIntExtra("student_index", -1)
        if (studentIndex == -1) {
            finish()
            return
        }

        student = Model.shared.students[studentIndex]

        val nameEditText: EditText = findViewById(R.id.EditStudentName)
        val idEditText: EditText = findViewById(R.id.EditStudentID)
        val phoneEditText: EditText = findViewById(R.id.EditStudentPhone)
        val addressEditText: EditText = findViewById(R.id.EditStudentAddress)

        val saveButton: Button = findViewById(R.id.EditStudentSaveButton)
        val cancelButton: Button = findViewById(R.id.EditStudentCancelButton)
        val deleteButton: Button = findViewById(R.id.EditStudentDeleteButton)
        val toolbar: Toolbar = findViewById(R.id.EditStudentToolbar)


        nameEditText.setText(student.name)
        idEditText.setText(student.id)
        phoneEditText.setText(student.phone)
        addressEditText.setText(student.address)

        setSupportActionBar(toolbar)
        supportActionBar?.title = "Edit Student"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)


        saveButton.setOnClickListener {
            student.name = nameEditText.text.toString().trim()
            student.id = idEditText.text.toString().trim()
            student.phone = phoneEditText.text.toString().trim()
            student.address = addressEditText.text.toString().trim()
            finish()
        }

        deleteButton.setOnClickListener {
            Model.shared.students.removeAt(studentIndex)

            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
            startActivity(intent)
        }


        cancelButton.setOnClickListener {
            finish()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
