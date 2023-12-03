package com.example.jodhymobiledevmorning

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jodhymobiledevmorning.Data.DataAdapter
import com.example.jodhymobiledevmorning.Data.DataDatabaseHelper
import com.example.jodhymobiledevmorning.databinding.FragmentHomeBinding

class Home : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var db: DataDatabaseHelper
    private lateinit var dataAdapter: DataAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db = DataDatabaseHelper(requireContext())
        dataAdapter = DataAdapter(db.getAllData(), requireContext())

        binding.dataRecycleView.layoutManager = LinearLayoutManager(requireContext())
        binding.dataRecycleView.adapter = dataAdapter

        binding.addButton.setOnClickListener {
            val intent = Intent(requireContext(), add_data::class.java)
            startActivity(intent)
        }

        binding.dataHeading.setOnClickListener {
            startActivity(Intent(requireContext(), Dashboard::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        dataAdapter.refreshData(db.getAllData())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
