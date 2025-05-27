package com.idz.colman24class2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.idz.colman24class2.adapter.StudentsRecyclerAdapter
import com.idz.colman24class2.model.Model
import com.idz.colman24class2.model.Student

class StudentsListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: StudentsRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_students_list, container, false)

        recyclerView = view.findViewById(R.id.students_list_activity_recycler_view)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        adapter = StudentsRecyclerAdapter(Model.shared.students)
        adapter.listener = object : OnItemClickListener {
            override fun onItemClick(position: Int) {
                Log.d("TAG", "On click Activity listener on position $position")
                val intent = Intent(requireContext(), StudentDetailsActivity::class.java)
                intent.putExtra("student_index", position)
                startActivity(intent)
            }

            override fun onItemClick(student: Student?) {
                Log.d("TAG", "On student clicked name: ${student?.name}")
            }
        }

        recyclerView.adapter = adapter

        return view
    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
    }
}

