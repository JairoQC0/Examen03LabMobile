package com.jairoqc.laboratorio3examen


import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.jairoqc.laboratorio3examen.adapter.TeacherAdapter
import com.jairoqc.laboratorio3examen.databinding.ActivityMainBinding
import com.jairoqc.laboratorio3examen.models.TeacherViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: TeacherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(TeacherViewModel::class.java)

        setupRecyclerView()
        observeViewModel()
        viewModel.fetchTeachers()
    }

    private fun setupRecyclerView() {
        binding.rvTeachers.layoutManager = LinearLayoutManager(this)
    }

    private fun observeViewModel() {
        viewModel.teacherList.observe(this) { teachers ->
            binding.rvTeachers.adapter = TeacherAdapter(teachers, this)
        }

        viewModel.isLoading.observe(this) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        viewModel.errorMessage.observe(this) { error ->
            Toast.makeText(this, "Error: $error", Toast.LENGTH_SHORT).show()
        }
    }
}