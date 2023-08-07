//package com.example.myapplication.ui.student
//
//import android.os.Bundle
//import android.view.View
//import androidx.fragment.app.Fragment
//import androidx.lifecycle.ViewModelProvider
//import com.example.myapplication.adapter.StudentAdapter
//import com.example.myapplication.model.StudentViewModel
//
//
//class StudentFragment : Fragment() {
//    private lateinit var studentViewModel: StudentViewModel
//    private lateinit var adapter: StudentAdapter
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        studentViewModel = ViewModelProvider(this).get(StudentViewModel::class.java)
//
//        // Initialize your RecyclerView and its adapter
//
//        studentViewModel.allStudents.observe(viewLifecycleOwner, { students ->
//            // Update your RecyclerView
//            adapter.setStudents(students)
//        })
//
//        // Setup your search view listener
//        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                if (!query.isNullOrEmpty()) {
//                    studentViewModel.searchStudents(query).observe(viewLifecycleOwner, { students ->
//                        // Update your RecyclerView
//                        adapter.setStudents(students)
//                    })
//                }
//                return true
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                // You may want to do some filtering here in case of a large list of students
//                return true
//            }
//        })
//    }
//}