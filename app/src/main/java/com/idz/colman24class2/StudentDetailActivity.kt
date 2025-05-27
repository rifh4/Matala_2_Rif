package com.idz.colman24class2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.idz.colman24class2.model.Model
import com.idz.colman24class2.model.Student

class StudentDetailsActivity : AppCompatActivity() {

    private lateinit var student: Student
    private var studentIndex: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_details)

        studentIndex = intent.getIntExtra("student_index", -1)
        if (studentIndex == -1) {
            finish()
            return
        }

        student = Model.shared.students[studentIndex]

        val nameTextView: TextView = findViewById(R.id.StudentName)
        val idTextView: TextView = findViewById(R.id.StudentID)
        val phoneTextView: TextView = findViewById(R.id.StudentPhone)
        val addressTextView: TextView = findViewById(R.id.StudentAddress)
        val checkedBox: CheckBox = findViewById(R.id.student_details_checked)
        val toolbar: Toolbar = findViewById(R.id.toolbar3)
        val editButton: Button = findViewById(R.id.EditButton)

        nameTextView.text = student.name
        idTextView.text = student.id
        phoneTextView.text = student.phone
        addressTextView.text = student.address
        checkedBox.isChecked = student.isChecked

        checkedBox.setOnCheckedChangeListener { _, isChecked ->
            student.isChecked = isChecked
        }

        setSupportActionBar(toolbar)
        supportActionBar?.title = "Student Details"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        editButton.setOnClickListener {
            val intent = Intent(this, EditStudentActivity::class.java)
            intent.putExtra("student_index", studentIndex)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()

        student = Model.shared.students[studentIndex]

        // Rebind views
        findViewById<TextView>(R.id.StudentName).text = student.name
        findViewById<TextView>(R.id.StudentID).text = student.id
        findViewById<TextView>(R.id.StudentPhone).text = student.phone
        findViewById<TextView>(R.id.StudentAddress).text = student.address
        findViewById<CheckBox>(R.id.student_details_checked).isChecked = student.isChecked
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
